<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <SnsSearch @showHashList="showHashList"></SnsSearch>
    </div>
    <div class="sns-write-container">
      <div class="sns-write-button">
        <span class="sns-button-background">새 게시물 작성</span>
        <input type="button" @click="doPost" value="게시글 공유">
      </div>
      <div class="sns-write-form-container">
        <div class="sns-write-form">

          <div class="sns-upload-image-container">
            <div class="sns-register-image-preview">
            <ImagePreview :images="images" :isNotList="false" @deleteImages="deleteImages"></ImagePreview>
          </div>
            <form class="sns-upload-image-preview" id="sns-register-image-form" encrypt="multipart/form-data"
              style="padding:20px;">
              <label>사진등록
                <input @change="changeImage($event)" @dragenter.prevent @dragover.prevent
                  @drop.prevent="dropImage($event)" type="file" multiple style="display:none;">
              </label>
            </form>
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
                <input type="text" :value=this.nickname name="nickname" readonly>
                <input type="text" :value=this.email name="email" style="display :none;" readonly>
              </div>
            </div>
          </div>
          <div class="sns-write-context">
            <textarea v-model="snsWriteText" placeholder="내용을 입력하세요" name="content"></textarea>
          </div>
          <div class="sns-write-hashtag">
            <textarea v-model="snsWriteHashtag" placeholder="해시태그" name="hashtag"></textarea>
          </div>
          <div class="sns-write-place">
            <input :value="this.place" type="text" name="place" placeholder="장소">
          </div>
          <!-- 위치검색 -->
          <!-- <div class="sns-write-location">
            <input v-model="snsWriteLocation" type="text" name="location" placeholder="위치등록">
          </div> -->
          <label class="sns-register-address-container">
            <div class="sns-write-location">
              <input @keyup.enter="snsAddress()" type="text" name="location" class="sns-register-address"
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
import ImagePreview from '../ImagePreview.vue';
import Swal from 'sweetalert2';
export default {
  //DB연결
  created: function () {
    //현재위치..노...
    //     navigator.geolocation.getCurrentPosition(function(pos) {
    //     var latitude = pos.coords.latitude;
    //     var longitude = pos.coords.longitude;
    //     alert("현재 위치는 : " + latitude + ", "+ longitude);
    // });

    //프로필 이미지
    fetch('http://13.125.95.210:85/java/profile/' + this.$store.state.email)
      .then(result => result.json())
      .then(result => {
        this.storedProfile = result;
      }).catch(err => console.log(err));
  },

  data: function () {
    return {
      nickname: this.$store.state.nickname,
      email: this.$store.state.email,
      snsWriteId: '작성자id',
      snsWriteText: '내용',
      snsWriteHashtag: '#해시태그',
      snsWritePlace: '장소',
      snsWriteLocation: '위치',
      place: '',
      search: '',
      // snsWriteIdImg: img1,
      //프로필 이미지
      storedProfile: '',
      searchText: '',
      images: [],
      imagesUrl: [],
    };
  },
  components: {
    KakaoMap,
    ImagePreview
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


      // let snsInfo = {};
      // console.log(new FormData(document.querySelector('#snsForm')));
      // new FormData(document.querySelector('#snsForm')).forEach((value, key) => snsInfo[key] = value);
      // console.log(snsInfo);

      // fetch('http://13.125.95.210:85/java/sns', {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json'

      //   },
      //   body: JSON.stringify(snsInfo)
      // }).then(result => result.text())
      //   .then(result => console.log(result))
      //   .catch(err => console.log(err))


      let sns = new FormData(document.querySelector('#snsForm'));

      for (let image of this.images) {
        sns.append("files", image);
      }
      sns.forEach((value, key) => {
        console.log(value);
      })
      // console.log((document.querySelector('.info h5').text));



      // location.value = (document.querySelector('.info h5').value);
      // console.log(location);

      //null이 아닌 값들이 null일때


      fetch('http://13.125.95.210:85/java/sns', {
        method: 'POST',
        headers: {},
        body: sns
      }).then(result => result.text())
        .then(result => {
          console.log(result);
          if (result == "true") {
            Swal.fire({
              icon: 'success',
              title: '게시글이 정상적으로 등록되었습니다.',
              toast: true,
              position: 'center-center',
              showConfirmButton: false,
              timer: 2000,
              timerProgressBar: true,
              didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
                this.$router.push({ name: 'SnsMain' })
              }
            })
          }else if(result != "true"){
            // alert("사진과 내용은 필수입력항목입니다.");
            Swal.fire({
                    icon: 'info',
                    title: '사진과 내용을 반드시 입력해주세요.',
                    // text: '계속해서 등록되지 않으면 고객센터로 문의해주세요.',
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
          }
          //null이 아닌 값들이 null일때
        })
    },

    //-사진
    deleteImages(updatedImages) {
      this.images = updatedImages;
      document.querySelector('#sns-register-image-container input[type="file"]').files = updatedImages;
    },
    changeImage(e) {
      console.log(e.target);
      let dt = new DataTransfer();
      for (let i = 0; i < e.target.files.length; i++) {
        dt.items.add(e.target.files[i]);
      }
      this.images = dt.files;
      e.target.files = dt.files;
    },
    //정현님 위치지도 참고
    snsAddress() {
      let search = document.querySelector('.sns-register-address').value;
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

    },
  },
  components: {
    SnsSearch
  },
}

</script>

<style scoped src="@/assets/css/sns/SnsWrite.css">
</style>