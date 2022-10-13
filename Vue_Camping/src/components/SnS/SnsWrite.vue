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

          <form class="sns-upload-image-container">
            <div class="sns-upload-image-preview">


              <!-- <div class="sns-img-preview"> -->
              <label>업로드
                <input type="file" multiple @change="changeImage($event)" @dragenter.prevent @dragover.prevent
                  @drop.prevent="dropImage($event)" style="display:none;">
                <div class="sss" data-placeholder='파일을 첨부 하려면 파일 선택 버튼을 클릭하거나 파일을 드래그앤드롭 하세요'>

                  <div v-for="(img,index) of imagesUrl" :id="index" class="sns-image-preview">
                    <img :src="img" />
                    <input type="button" value="X" @click="deletePreview($event)"
                      class="sns-image-preview-delete-button" />
                  </div>
                </div>
              </label>


              <!-- </div> -->
            </div>
          </form>
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
export default {
  //DB연결
  created : function(){
    
  },
  
  data: function(){
    return {
      snsWriteId: '작성자id',
      snsWriteText: '내용',
      snsWriteHashtag: '해시태그',
      snsWritePlace: '장소',
      snsWriteLocation: '위치',
      snsWriteIdImg: img1,
      searchText: '',
      images: [],
      imagesUrl: []
    };
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
      

      let sns = {};
      console.log(new FormData(document.querySelector('#snsForm')));
      new FormData(document.querySelector('#snsForm')).forEach((value,key) => sns[key]=value);
            console.log(sns);

    fetch('http://localhost:8087/java/sns', {
    method : 'POST',
    headers : {
      'Content-Type' : 'application/json'

    },
    body : JSON.stringify(sns)
  }).then(result => result.text())
  .then(result => console.log(result))
  .catch(err => console.log(err))
      
    },
    //-사진
    changeImage(e) {
      this.images = e.target.files;
      for (let image of this.images) {
        this.imageLoader(image);
      }
    },
    
    imageLoader(image) {
      this.imagesUrl.push(URL.createObjectURL(image));
    },
    dropImage(e) {
      let files = {};
      e.preventDefault();
      e.stopPropagation();
      let dt = e.dataTransfer;
      files = dt.files;
      for(let f of files) {
        this.imageLoader(f);
      }
    },
    deletePreview(e) {
      let parentDiv = e.target.parentElement;
      let tempimages = [];
      let tempimagesUrl = [];

      for(let i=0; i<this.images.length; i++){
        if(i != parentDiv.getAttribute('id')){
          tempimages.push(this.images[i]);
          tempimagesUrl.push(this.imagesUrl[i]);
        }
      }
      this.images = tempimages;
      this.imagesUrl = tempimagesUrl;
    }
  }
}

</script>

<style scoped src="@/assets/css/sns/SnsWrite.css"></style>