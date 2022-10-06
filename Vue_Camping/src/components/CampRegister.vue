<template>
  <div class="camp-register">
      <h2>캠핑장 등록</h2>
      <div class="camp-register-image-container">
        <div class="camp-register-image-preview">
          <div v-for="(img,index) of imagesUrl" :id="index" class="image-preview-div">
            <img :src="img"/>
            <input type="button" value="X" @click="deletePreview($event)" class="image-preview-delete-button"/>
          </div>
        </div>
        <label>사진등록
          <input @change="changeImage($event)" @dragenter.prevent @dragover.prevent @drop.prevent="dropImage($event)" type="file" multiple style="display:none;">
        </label>
      </div>
      <form class="camp-register-form" action="" onsubmit="return false">
        <label>캠핑장 이름<input type="text" class="camp-register-name"></label>
        <label>주소
          <input @keyup.enter="campAddress()" type="text" class="camp-register-address">
          <img @click="campAddress()" class="address-search-button" src="../assets/img/icons/search-20.png"/>
          <div class="camp-register-kakaomap"><KakaoMap :search="search"></KakaoMap></div>
        </label>
        <label>사이트 수<input type="text" class="camp-register-site"></label>
        <label>가격<input type="text" class="camp-register-price"></label>
        <div>정보
          <div>
            <button type="button">화장실</button>
            <button type="button">주차장</button>
            <button type="button">샤워장</button>
            <button type="button">쓰레기장</button>
            <button type="button">데크</button>
            <button type="button">수영장</button>
            <button type="button">바비큐</button>
            <button type="button">장비대여</button>
            <button type="button">조리도구</button>
          </div>
        </div>
        <label>기타정보<textarea class="camp-register-info"></textarea></label>
        <div class="camp-register-form-button-container">
          <button type="button" @click="campRegister()">등록</button>
          <button type="reset">취소</button>
        </div>
      </form>
    </div>
</template>

<script>
  import { reactive, computed } from 'vue';
  import KakaoMap from './KakaoMap.vue';

  export default {
    data(){
      return {
        images : [],
        imagesUrl : []
      }
    },
    components:{
      KakaoMap
    },
    methods : {
      changeImage(e) {
        this.images = e.target.files;
        for(let file of this.images){
          this.imageLoader(file);
        }
      },
      imageLoader(file) {
        this.imagesUrl.push(URL.createObjectURL(file));
      },
      dropImage(e) {
        let files = {};
        e.preventDefault();
        e.stopPropagation();
        let dt = e.dataTransfer;
        files = dt.files;
        for(let f of files){
          this.imageLoader(f);
        }
      },
      deletePreview(e) {
        let parentDiv = e.target.parentElement;
        let tempimages = [];
        let tempimagesUrl = [];

        for(let i=0; i<this.images.length; i++){
          if(i != parentDiv.getAttribute('id')) {
            tempimages.push(this.images[i]);
            tempimagesUrl.push(this.imagesUrl[i]);
          }
        }
        this.images = tempimages;
        this.imagesUrl = tempimagesUrl;
      },
      campAddress(){
        let search = document.querySelector('.camp-register-address').value;
        let kakaoContainer = document.querySelector('.camp-register-kakaomap');
        console.log(search);
        if(search==null || search==''){
          alert('검색할 장소를 입력하세여');
          kakaoContainer.classList.remove('active');
        } else {
          if(!kakaoContainer.classList.contains('active')){
            kakaoContainer.classList.toggle('active');
          }
          this.search = search;
        }
        
      }
    }
  }
</script>

<style scoped src="@/assets/css/campRegister.css"></style>
<style scoped>
  .image-preview-div {
    display:inline-block;
    position:relative;
    width:150px;
    height:120px;
    margin:5px;
    border:1px solid #00f;
    z-index:1;
  }
  .image-preview-div img {
    width:100%;
    height:100%;
    z-index:none;
  }
  .image-preview-delete-button {
    width:30px;
    height:30px;
    position:absolute;
    font-size:24px;
    right:0px;
    bottom:0px;
    z-index:999;
    background-color:rgba(255,255,255,0.1);
    color:#f00;
    border:none;
    cursor:pointer;
  }
</style>