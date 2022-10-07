<template>
    <div>
      <div id="map" class="map"></div>
    </div>
  </template>
  
  <script>
    export default {
      mounted() {
        window.kakao && window.kakao.maps ?
          this.initMap() :
          this.addKakaoMapScript();
      },
      data() {
        return {
          geocoder:null,
          startPoint : '대구 중구 달구벌대로 2095 ',
          campingSpot:'대구광역시 동구 동촌로 167'
        }
      },
      methods: {
        addKakaoMapScript() {
          const script = document.createElement("script");
          /* global kakao */
          script.onload = () => kakao.maps.load(this.initMap);
          script.src =
            "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=3f57d02e134f1067307cbaec0b42c437&libraries=services";
          document.head.appendChild(script);
        },
        initMap() {
          var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
          var options = {
            //지도를 생성할 때 필요한 기본 옵션
            center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
            level: 7 //지도의 레벨(확대, 축소 정도)
          };
  
          var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
  
          // 주소-좌표 변환 객체를 생성합니다
       
          this.geocoder = new kakao.maps.services.Geocoder();
          this.geocoder.addressSearch(this.startPoint, function (result, status) {
  
            // 정상적으로 검색이 완료됐으면 
            if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                map: map,
                position: coords
              });
  
              // 인포윈도우로 장소에 대한 설명을 표시합니다
              var infowindow = new kakao.maps.InfoWindow({
                content: '<div style="width:120px;text-align:center;padding:3px 0;">출발지</div>'
              });
              infowindow.open(map, marker);

            }
          });
  
          this.geocoder.addressSearch(this.campingSpot, function (result, status) {
  
            // 정상적으로 검색이 완료됐으면 
            if (status === kakao.maps.services.Status.OK) {
  
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
  
              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                map: map,
                position: coords
              });
  
              // 인포윈도우로 장소에 대한 설명을 표시합니다
              var infowindow = new kakao.maps.InfoWindow({
                content: '<div style="width:120px;text-align:center;padding:3px 0;">도착지</div>'
              });
              infowindow.open(map, marker);
  
              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                         //지도 이동
                const bounds = positions.reduce(
                    (bounds, position) => bounds.extend(position.latlng),
                    new kakao.maps.LatLngBounds()
                );
                this.map.setBounds(bounds);
                }
          });
        },
      }
    };
  </script>
  
  <style>
    .map {
      width: 100%;
      height: 400px;
    }
  </style>