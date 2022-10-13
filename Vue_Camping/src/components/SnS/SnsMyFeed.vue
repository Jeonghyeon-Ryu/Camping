<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="search" @keyup="checkEnter($event)" v-model="searchText" placeholder="검색어를 입력해주세요.">
      <button @click="doSearch" style="display: none;">조회</button>
      <!-- <input type="button" @click="doClear" value="X"> -->
    </div>
    <div class="sns-page-id-container">
      <div class="sns-page-id">
        <span>나의 게시물(그러나 남의 화면도 똑같은)</span>
      </div>
    </div>


    <!--  -->
    <div class="sns-detail-page-container">
      <div class="sns-write-id-container">
        <div class="sns-write-form-id">
          <div class="sns-write-id">
            <img v-bind:src="snsFeedImg">
          </div>
        </div>
        <div class="sns-write-form-id">
          <div class="sns-write-id">
            <input type="text" value="게시글 갯수(숫자)">
          </div>
        </div>
      </div>
      <div class="sns-profile">
        <div class="sns-profile-nickname">
          <input type="text" value="닉네임">
        </div>
        <div class="sns-profile-text">
          <textarea v-model="snsProfileText"></textarea>
         <!-- / <textarea v-model="snsWriteText" placeholder="내용을 입력하세요"></textarea> -->
        </div>
      </div>
    </div>
    <!--  -->

    <div class="sns-img-container">
      <div class="sns-img-row-conatiner" :key="snsImg.No" v-for="snsImg in snsImgs">
        <div class="sns-img">
          <input type="text" :value="snsImg.No" style="display :none;">
          <img v-bind:src="snsImg.Img">
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import img1 from "@/assets/img/sns/이미지1.jpg"
import img2 from "@/assets/img/sns/이미지2.jpg"
import img3 from "@/assets/img/sns/이미지3.jpg"
import img4 from "@/assets/img/sns/이미지4.jpg"
import img5 from "@/assets/img/sns/이미지5.jpg"
import img6 from "@/assets/img/sns/이미지6.jpg"
export default {
  data: () => {
    return {
      snsSearch: '검색어를 입력하세요.',
      searchText: '',
      snsImgs: [
        { Img: img1, Name: 'snsMaingImg1', No: '1111' },
        { Img: img2, Name: 'snsMaingImg2', No: '2222' },
        { Img: img3, Name: 'snsMaingImg3', No: '3333' },
        { Img: img4, Name: 'snsMaingImg4', No: '4444' },
        { Img: img5, Name: 'snsMaingImg5', No: '5555' },
        { Img: img6, Name: 'snsMaingImg6', No: '6666' },
      ],
      snsFeedImg : img4,
      snsProfileText : '프로필 소개글 안냐세용 게시글 소개글입니다 그만하고싶고요 힘들고요 저는 그냥 말하는 감자고요 아무것도 안됩니다 누르지마세요',
    }
  },
  //검색
  methods: {
    doSearch() {
      console.log(this.searchText)
    },
    checkEnter(event) {
      if (event.keyCode == 13) {
        this.doSearch()
      }
    }
  }
}
</script>


<style scoped>
/* .sns-container{
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
    }
    .sns-image{
      
      width: 30%;
    } */
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
.sns-searchbox input[type=text] {
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
.sns-searchbox input[type=button] {
  padding: 15px;
  border: none;
  border-radius: 5px;
  background-color: rgb(255, 255, 255);
}
.sns-page-id-container {
  display: flex;
  justify-content: center;
}
.sns-page-id {
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
.sns-page-id span {
  font-weight: bold;
  margin-top: 7px;
}
.sns-detail-page-container {
  width: 60vw;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
}
.sns-detail-container {
  width: 100%;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  margin-top: 15px;
}
.sns-write-id-container {
  width: 100%;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  margin-top: 15px;
  position: relative;
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
.sns-write-id input:focus {
  outline: none;
}
.sns-img-container {
  width: 70vw;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin-top: 15px;
}
.sns-img {
  margin-bottom: 10px;
  margin-right: 10px;
  cursor: pointer;
}

.sns-profile{
  width: 100%;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  margin-top: 15px;
  position: relative;
}

.sns-profile-nickname{
  width: 100%;
    height: auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: left;
    margin-top: 15px;
    position: relative;
    
}

.sns-profile-nickname input{
  border: none;

}

.sns-profile-nickname input:focus {
  outline: none;
}
.sns-profile-text textarea{
  
  margin-top: 15px;
      width: 28vw;
      height: 5vw;
      min-width: 184px;
      min-height: 20px;
      border: none;
      resize: none;
      border-radius: 5px;
}

.sns-profile-text textarea:focus {
  outline: none;
}
</style>