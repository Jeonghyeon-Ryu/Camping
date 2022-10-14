<template>
  <div class="map_wrap">
    <div id="map"></div>

    <div id="menu_wrap" class="bg_white">
        <div class="option">
            <!-- <div>
                <form onsubmit="searchPlaces(); return false;">
                    키워드 : <input type="text" value="이태원 맛집" id="keyword" size="15"> 
                    <button type="submit">검색하기</button> 
                </form>
            </div> -->
        </div>
        <!-- <hr> -->
        <ul id="placesList">
          <li v-for="(places, index) of searchResult" @mouseover="mouseOverList($event)" @mouseout="mouseOutList()" @click="placeListClick($event)" class="item">
            <span v-bind:class="'markerbg marker_'+(index+1)"></span>
            <div class="info">
              <h5> {{places.place_name}} </h5>
              <span> {{places.road_address_name}} </span>
              <span class="jibun gray"> {{places.address_name}} </span>
              <span class="tel"> {{places.phone}} </span>
            </div>
          </li>
        </ul>
        <div id="pagination">
          <a href="#" v-for="page of paginations.last" :class="{'on' : isCurrent(page)}" @click="clickPage($event)">{{page}}</a>
        </div>
    </div>
</div>
</template>

<script>
  import Swal from 'sweetalert2';
export default {
  name: "KakaoMap",
  props : ['search'],
  data() {
    return {
      markers: [],
      infowindow: null,
      map: null,
      searchResult: [],
      paginations : []
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e1e52b4e2ae3417c2eab9d6c37859a8e&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      let ps = new kakao.maps.services.Places();
      this.infowindow = new kakao.maps.InfoWindow({zIndex:1});
      if(this.search!='' && this.search!=undefined){
        ps.keywordSearch(this.search,this.placesSearchCB);
      } 
    },
    searchPlace(search) {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      let ps = new kakao.maps.services.Places();
      console.log('search place ' + search);
      ps.keywordSearch(search, this.placesSearchCB);
    },  
    placesSearchCB(data, status, pagination) {
      // data : 검색 결과 내용
      // status : 검색 결과 응답
      // pagination : 페이지 번호 처음은 1번.
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 검색 목록과 마커를 표출합니다
        this.removeMarker();            // 새로 읽어오기전 마커 지우기 ( 비바인딩 )
        this.searchResult = data;       // 장소 Data 바인딩 되어있음.
        this.paginations = pagination;  // 페이지 번호 바인딩 되어있음.
        this.displayPlaces();           // 화면 표시
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
          Toast.fire({
            toast: true,
            position: 'center-center',
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
            },
            icon: 'warning',
            title: '검색 결과가 없습니다.'
          });
          return;
      } else if (status === kakao.maps.services.Status.ERROR) {
          Toast.fire({
            toast: true,
            position: 'center-center',
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
            },
            icon: 'error',
            title: '검색 결과 중 오류가 발생했습니다.'
          });
          return;
      }
    },
    displayPlaces() {
      let menuEl = document.getElementById('menu_wrap');
      let bounds = new kakao.maps.LatLngBounds()
      // 뭐에 쓰이는건지 모르겠음.
      let listStr = '';

      for ( var i=0; i<this.searchResult.length; i++ ) {
        // 마커를 생성하고 지도에 표시합니다
        let placePosition = new kakao.maps.LatLng(this.searchResult[i].y, this.searchResult[i].x);
        let marker = this.addMarker(placePosition, i);
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);
        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        // (function(marker, title) {
        // let title = this.searchResult[i].place_name;
        // kakao.maps.event.addListener(marker, 'mouseover', (marker, title) => {
        //   this.displayInfowindow(marker, title);
        // });
        // kakao.maps.event.addListener(marker, 'mouseout', () => {
        //   this.infowindow.close();
        // });
        // document.querySelector('.item').addEventListener('mouseover', (marker, title) => {
        //     this.displayInfowindow(marker, title);
        // });
        // document.querySelector('.item').onmouseout =  () => {
        //     this.infowindow.close();
        // };
        // })(marker, places[i].place_name);
        // fragment.appendChild(itemEl);
      }
      menuEl.scrollTop = 0;
      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      this.map.setBounds(bounds);
    },
    placeListClick(e){
      // 마커 번호, 이름 받아오기
      let result = this.getMarkerAndName(e.target);
      this.$emit('setAddress',result.address);
    },  
    mouseOverList(e){
      // 마커 번호, 이름 받아오기
      let result = this.getMarkerAndName(e.target);
      // 지도에 마커 표시
      this.displayInfowindow(result.marker, result.title);
    },
    mouseOutList() {
      this.infowindow.close();
    },  
    getMarkerAndName(el){
      while(!el.classList.contains('item')){
        el = el.parentElement;
      }
      // 마커
      let index = el.querySelector('.markerbg').className.indexOf('marker_');
      let marker = this.markers[el.querySelector('.markerbg').className.substr(index+7,1)];
      // 이름
      let title = el.querySelector('h5').innerText;
      // 주소
      let address = el.querySelector('.jibun').innerText;

      return {marker, title, address};
    },
    addMarker(position, idx, title) {
      var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png'; // 마커 이미지 url, 스프라이트 이미지를 씁니다
      let imageSize = new kakao.maps.Size(36, 37);  // 마커 이미지의 크기
      let imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
          };
      let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions);
      let marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
          });
      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker);  // 배열에 생성된 마커를 추가합니다
      return marker;
    },
    removeMarker() {
      for ( var i = 0; i < this.markers.length; i++ ) {
        this.markers[i].setMap(null);
      }   
      this.markers = [];
    },
    isCurrent(page) {
      if(page==this.paginations.current){
        return true;
      } else {
        return false;
      }
    },
    clickPage(e) {
      e.preventDefault();
      this.paginations.gotoPage(e.target.innerText);
    },
    // 검색결과 목록 하단에 페이지번호를 표시는 함수
    displayPagination(pagination) {
      let paginationEl = document.getElementById('pagination');
      let fragment = document.createDocumentFragment();
      let i; 
          
      // 기존에 추가된 페이지번호를 삭제
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
      }

      for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
          el.className = 'on';
        } else {
          el.onclick = (function(i) {
            return function() {
              pagination.gotoPage(i);
            }
          })(i);
        }
        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    },
    displayInfowindow(marker, title) {
      var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

      this.infowindow.setContent(content);
      this.infowindow.open(this.map, marker);
    },
    // 검색결과 목록의 자식 Element를 제거하는 함수.
    removeAllChildNods(el) {   
      while (el.hasChildNodes()) {
          el.removeChild (el.lastChild);
      }
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map { height:100%; }
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:350px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:300px; height: calc(100% - 20px); margin:10px 0 0 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
/* total width */
#menu_wrap::-webkit-scrollbar {
    background-color:#fff;
    width:16px
}
/* background of the scrollbar except button or resizer */
#menu_wrap::-webkit-scrollbar-track {
    background-color:#fff
}
#menu_wrap::-webkit-scrollbar-track:hover {
    background-color:#f4f4f4
}
/* scrollbar itself */
#menu_wrap::-webkit-scrollbar-thumb {
    background-color:rgb(228,239,231) ;
    border-radius:16px;
    border:5px solid #fff
}
#menu_wrap::-webkit-scrollbar-thumb:hover {
    background-color:rgb(6,68,32);
    border:4px solid #f4f4f4
}
/* set button(top and bottom of the scrollbar) */
#menu_wrap::-webkit-scrollbar-button {display:none}

  .bg_white {background:#fff;}
  #menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
  #menu_wrap .option{text-align: center;}
  #menu_wrap .option p {margin:10px 0;}  
  #menu_wrap .option button {margin-left:5px;}
  /* #placesList li {list-style: none; } */
  #placesList>>>.item {
    position:relative;
    border-bottom:1px solid #888;
    overflow: hidden;
    cursor: pointer;
    min-height: 65px;
  }
  #placesList>>>.item span {display: block;margin-top:4px;}
  #placesList>>>.item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
  #placesList>>>.item .info{padding:10px 0 10px 55px;}
  #placesList>>>.info .gray {color:#8a8a8a;}
  #placesList>>>.info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
  #placesList>>>.info .tel {color:#009900;}
  #placesList>>>.item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
  #placesList>>>.item .marker_1 {background-position: 0 -10px;}
  #placesList>>>.item .marker_2 {background-position: 0 -56px;}
  #placesList>>>.item .marker_3 {background-position: 0 -102px}
  #placesList>>>.item .marker_4 {background-position: 0 -148px;}
  #placesList>>>.item .marker_5 {background-position: 0 -194px;}
  #placesList>>>.item .marker_6 {background-position: 0 -240px;}
  #placesList>>>.item .marker_7 {background-position: 0 -286px;}
  #placesList>>>.item .marker_8 {background-position: 0 -332px;}
  #placesList>>>.item .marker_9 {background-position: 0 -378px;}
  #placesList>>>.item .marker_10 {background-position: 0 -423px;}
  #placesList>>>.item .marker_11 {background-position: 0 -470px;}
  #placesList>>>.item .marker_12 {background-position: 0 -516px;}
  #placesList>>>.item .marker_13 {background-position: 0 -562px;}
  #placesList>>>.item .marker_14 {background-position: 0 -608px;}
  #placesList>>>.item .marker_15 {background-position: 0 -654px;}
  #menu_wrap>>>#pagination {margin:10px auto;text-align: center;}
  #pagination>>>a {display:inline-block;margin-right:10px;}
  #pagination>>>.on {font-weight: bold; cursor: default;color:#777;}
  .inactive { display: none; }
  .active { display: block; }
</style>