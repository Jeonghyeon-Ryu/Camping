<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="search" @keyup="checkEnter($event)" v-model="searchText" placeholder="검색어를 입력해주세요.">
      <button @click="doSearch" style="display: none;">조회</button>
      <!-- <input type="button" @click="doClear" value="X"> -->
    </div>
    <div class="sns-write-container">
      <div class="sns-write-button">
        <span class="sns-button-background">새 게시물 작성</span>
        <input type="button" @click="doPost" value="게시글 공유">
      </div>
      <div class="sns-write-form-container">
        <div class="sns-write-form">

          <div class="sns-upload-image-container">
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
                <img :src='snsWriteIdImg' alt=" ">
              </div>
            </div>
            <div class="sns-write-form-id">
              <div class="sns-write-id">
                <input type="text" value="작성자 닉네임" name="nickname">
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
            <input v-model="snsWritePlace" type="text" name="place">

            <p class="result"></p>

          </div>
          <div class="sns-write-location">
            <input v-model="snsWriteLocation" type="text" name="location">
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import img1 from "@/assets/img/sns/이미지1.jpg";
import ImagePreview from '../ImagePreview.vue';
import Swal from 'sweetalert2';
export default {
  //DB연결
  created: function () {

  },

  data: function () {
    return {
      snsWriteId: '작성자id',
      snsWriteText: '내용',
      snsWriteHashtag: '#해시태그',
      snsWritePlace: '장소',
      snsWriteLocation: '위치',
      snsWriteIdImg: img1,
      searchText: '',
      images: [],
      imagesUrl: [],
    };
  },
  components: {
    // KakaoMap, //not yet
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

      // fetch('http://localhost:8087/java/sns', {
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

      fetch('http://localhost:8087/java/sns', {
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
          }
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
    }
  },
}



</script>

<style scoped src="@/assets/css/sns/SnsWrite.css">

</style>