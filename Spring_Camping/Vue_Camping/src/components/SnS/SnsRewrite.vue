<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <SnsSearch @showHashList="showHashList"></SnsSearch>
    </div>
    <div class="sns-write-container">
      <div class="sns-write-button">
        <span class="sns-button-background">게시물 수정</span>
        <input type="button" @click="doPost" value="게시글 공유">
      </div>
      <div class="sns-write-form-container">
        <div class="sns-write-form">

          <div class="sns-upload-image-container">
            <div class="sns-upload-image-preview">
            </div>
          </div>
        </div>


        <form class="sns-write-form" id="snsForm">
          <div class="sns-write-form-id-form">
            <div class="sns-write-form-id">
              <div class="sns-write-id">
                <img :src="'http://13.125.95.210:85/java/profile/'+storedProfile.imagePath+'/'+storedProfile.storedName">
              </div>
            </div>
            <div class="sns-write-form-id">
              <div class="sns-write-id">
                <input type="text" :value="snsItem.nickname" readonly>
              </div>
            </div>
          </div>
          <div class="sns-write-context">
            <textarea :value="snsItem.content" name="content"></textarea>
            <input type="text" :value="snsItem.writeNo" style="display :none;" name="writeNo">
          </div>
          <div class="sns-write-hashtag">
            <textarea :value="snsItem.hashtag" name="hashtag"></textarea>
          </div>
          <div class="sns-write-place">
            <input :value="snsItem.place" type="text" name="place" placeholder="장소">
          </div>
          <!-- 위치검색 -->
          <!-- <div class="sns-write-location">
            <input v-model="snsWriteLocation" type="text" name="location" placeholder="위치등록">
          </div> -->
          <label class="sns-register-address-container">
            <div class="sns-write-location">
              <input @keyup.enter="snsAddress()" type="text" :value="snsItem.location" name="location" class="sns-register-address"
                placeholder="예시 : 주소 입력후 엔터를 누르세요.">
              <img @click="snsAddress()" class="address-search-button" src="@/assets/img/icons/search-20.png" />
            </div>
            <div class="sns-register-kakaomap">
              <KakaoMap @setAddress="setAddress" :search="search" ref="kamap"></KakaoMap>
            </div>
          </label>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import SnsSearch from './SnsSearch.vue';
import KakaoMap from '../KakaoMap.vue';
import Swal from 'sweetalert2';
export default {

  //DB연결
  created: function () {
    fetch('http://13.125.95.210:85/java/snsDetail/' + this.writeNo)
      .then(response => response.json())
      .then(result => {
        this.snsItem = result
      })
      .catch(err => console.log(err));
    //서버에서 제대로 받아왔는지 확인.
    console.log(this.snsItem);

    fetch('http://13.125.95.210:85/java/snsImage/' + this.writeNo)
      .then(response => response.json())
      .then(result => {
        this.snsImgs = result
      })
      .catch(err => console.log(err));
    //서버에서 제대로 받아왔는지 확인.
    
    //프로필 이미지
    fetch('http://13.125.95.210:85/java/profile/' + this.$store.state.email)
      .then(result => result.json())
      .then(result => {
        this.storedProfile = result;
      }).catch(err => console.log(err));
  },

  data: function () {
    return {
      // snsWriteId: '작성자id',
      // snsWriteText: '내용',
      // snsWriteHashtag: '#해시태그',
      // snsWritePlace: '장소',
      // snsWriteLocation: '위치',

      writeNo: this.$route.params.writeNo,
      snsItem: {},
      place: '',
      search: '',
      //프로필 이미지
      storedProfile: '',
      searchText: '',
      
      images: [],
      imagesUrl: [],

    };
  },
  components: {
    KakaoMap,
  },
  //검색
  methods: {
    doSearch() {
      console.log(this.searchText);
    },
    checkEnter(event) {
      if (event.keyCode == 13) {
        this.doSearch();
      }
    },
    //게시글 공유
    //-텍스트
    doPost() {
      let snsInfo = {};
      console.log(new FormData(document.querySelector('#snsForm')));
      new FormData(document.querySelector('#snsForm')).forEach((value, key) => snsInfo[key] = value);
      console.log(snsInfo);

      fetch('http://13.125.95.210:85/java/snsUpdate', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(snsInfo)
      }).then(result => result.text())
        .then(result => console.log(result))
        .catch(err => console.log(err))

      // window.alert("수정이 완료되었습니다.")
      if (confirm("수정하시겠습니까?")) {
        window.location.href = ("/sns/detail/" + this.writeNo)
      }


      //스윗알러트
      // Swal.fire({
      //     title: '게시글을 공유하겠습니까?',
      //     // text: '다시 되돌릴 수 없습니다. 신중하세요.',
      //     icon: 'warning',

      //     showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
      //     confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
      //     cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
      //     confirmButtonText: '공유', // confirm 버튼 텍스트 지정
      //     cancelButtonText: '취소', // cancel 버튼 텍스트 지정

      //     // reverseButtons: true, // 버튼 순서 거꾸로

      //   }).then(result => {
      //     // 만약 Promise리턴을 받으면,
      //     if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면

      //       Swal.fire('승인이 완료되었습니다.', '화끈하시네요~!', 'success');
      //       window.location.href = ("/sns/detail/" + this.writeNo)
      //     }
      //   });
      // }
    },
    //정현님 위치지도 참고
    snsAddress() {
      let search = document.querySelector('.sns-register-address').value;
      //enter치기전까지 입력한 값
      console.log(search);

      this.snsItem.location = search;
      let kakaoContainer = document.querySelector('.sns-register-kakaomap');
      if (search == null || search == '') {
        Swal.fire({
          icon: 'warning',
          title: '검색할 장소를 입력하세요.',
          toast: true,
          position: 'center-center',
          showConfirmButton: false,
          timer: 2000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })
        kakaoContainer.classList.remove('active');
      } else {
        if (!kakaoContainer.classList.contains('active')) {
          kakaoContainer.classList.toggle('active');
        }
        this.search = search;
        this.$refs.kamap.searchPlace(search);
        console.log(this.$refs.kamap.searchPlace(search))
      }
    },
    setAddress(address) {
      let kakaoContainer = document.querySelector('.sns-register-kakaomap');
      kakaoContainer.classList.remove('active');
      document.querySelector('.sns-register-address').value = address;
      console.log(address);

      //지도 검색한 장소 이름 가져오기
      let list = document.querySelector('#placesList');
      let items = list.querySelectorAll('.item');
      let place = '';

      for (let item of items) {
        if (item.querySelector('.info').querySelectorAll('span')[1].innerText == address) {
          place = item.querySelector('.info').querySelector('h5').innerText;
          break;
        }
      }
      document.querySelector('.sns-write-place input').value = place;

    }

  },
  components: {
    SnsSearch
  },
}


</script>

<style scoped src="@/assets/css/sns/SnsWrite.css">

</style>