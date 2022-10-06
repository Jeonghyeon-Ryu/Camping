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
          <div class="sns-upload-img">


            <div class="sns-image-preview">
              <div class="sns-img-upload-button">
              <div id='att_zone' data-placeholder='파일을 첨부 하려면 파일 선택 버튼을 클릭하거나 파일을 드래그앤드롭 하세요'></div>
            </div>
            <div class="sns-img-upload-button">
              <label for="btnAtt">업로드</label>
              <input type='file' id='btnAtt' multiple='multiple' />
            </div>
          </div>




        </div>
      </div>
      
      <div class="sns-write-form">
        <div class="sns-write-form-id-form">
          <div class="sns-write-form-id">
            <div class="sns-write-id">
               <img :src='snsWriteIdImg' alt=" "> 
            </div>
          </div>
          <div class="sns-write-form-id">
            <div class="sns-write-id">
              <input type="text" value="작성자 id">
            </div>
          </div>
        </div>
        <div class="sns-write-context">
          <textarea v-model="snsWriteText" placeholder="내용을 입력하세요"></textarea>

        </div>
        <div class="sns-write-location">
          <input type="button" value="위치추가">
          <p class="result"></p>

        </div>
      </div>
      
    </div>
  </div>
  </div>
</template>

<script>
import img1 from "@/assets/img/이미지1.jpg"
import { continueStatement } from "@babel/types"
  export default{
    components : {},
    data : ()=>{
      return {        
        snsWriteId : '작성자id',
        snsWriteText : '',
        snsWriteIdImg : img1,
        searchText : '',

        
      }
    },
    //검색
    methods : {
      doSearch(){
        console.log(this.searchText)
      },
      checkEnter(event){
        if(event.keyCode == 13) {
          this.doSearch()
        }
      },

      //게시글 공유
      //-텍스트
      doPost(){
        console.log(this.snsWriteText)
      },
      //-사진
     
    }
  }

</script>

<style scope>
/* 공통 부분 */
    * {
      margin: 0;
      padding: 0;
      list-style: none;
      font-style: none;
      box-sizing: border-box;
    }

    .sns-container {
      width: 100vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
    }

    .sns-searchbox {
      width: 100vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      margin-bottom: 10px;
      margin-top: 10px;
    }

    .sns-searchbox input[type=search] {
      padding: 15px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
/* 
    .sns-searchbox input[type=button] {
      padding: 15px;
      border: none;
      border-radius: 5px;
      background-color: rgb(255, 255, 255);
    } */

    .sns-write-container {
      width: 100vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
    }

    .sns-write-button {
      position: relative;
      width: 56vw;
      min-width: 800px;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      margin-bottom: 10px;
    }

    .sns-button-background {
      background-color: rgba(228, 239, 231, 0.7);
      width: 56vw;
      height: 40px;
      min-width: 800px;
      border-radius: 5px;
      display: flex;
      justify-content: center;
      color: rgb(255, 255, 255);
      font-weight: bold;

    }

    .sns-write-button input {
      position: absolute;
      background-color: rgba(228, 239, 231, 0.7);
      /* border: none; */
      
      right: 10px;
      margin-right: 10px;
      color: rgb(255, 255, 255);
      font-weight: bold;
      cursor: pointer;
      margin-top: 7px;
    }

    .sns-write-form-container {
      width: 100vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
    }

    .sns-upload-img {
      margin-right: 2vw;
    }

    .sns-write-form-id-form {

      display: flex;
      flex-wrap: wrap;
      justify-content: left;
    }

    .sns-write-id img {
      width: 3vw;
      height: 3vw;
      border-radius: 70%;
      margin-right: 1vw;
    }

    .sns-write-id input {
      margin-top: 0.5vw;
      padding: 0.3vw;
      border: none;
    }

    .sns-write-context textarea {
      margin-top: 5px;
      width: 28vw;
      height: 13vw;
      min-height: 250px;
      min-width: 184px;
      border: 1px solid;
      resize: none;
      border-radius: 5px;
    }

    .sns-write-location input {
      width: 28vw;
      height: 40px;
      background-color: rgb(223, 223, 223);
      border-radius: 5px;
      border: none;
      cursor: pointer;
      color: rgb(87, 87, 87);
      font-weight: bold;
    }

    /* 시도3 */
    #att_zone {
      margin-top: 14px;
      width: 503px;
      min-height: 135px;
      padding: 10px;
      border: 1px solid #999;
      border-radius: 5px;

    }

    #att_zone:empty:before {
      content: attr(data-placeholder);
      color: #999;
      font-size: .9em;
    }

    /* 파일첨부꾸미기 */
    .sns-image-preview input[type="file"] {
      position: absolute;
      width: 0;
      height: 0;
      padding: 0;
      overflow: hidden;
      border: 0;
    }

    .sns-image-preview label {
      display: inline-block;
      padding: 10px 20px;
      color: #fff;
      vertical-align: middle;
      background-color: #337ab7;
      cursor: pointer;
      border: 1px solid #2e6da4;
      border-radius: 5px;
    }

    /* named upload */
    .sns-image-preview #att_zone {
      display: inline-block;
      height: 100%;
      width: 26.5vw;
      min-width: 345px;
      font-size: 18px;
      padding: 0 10px;
      vertical-align: middle;
      background-color: #f5f5f5;
      border: 1px solid #ebebeb;
      border-radius: 5px;

    }


    /* 시도2 */
    /* #multiple-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
}

.image {
    display: block;
    width: 100%;
}
.image-label {
    position: relative;
    bottom: 22px;
    left: 5px;
    color: white;
    text-shadow: 2px 2px 2px black;
} */
  </style>