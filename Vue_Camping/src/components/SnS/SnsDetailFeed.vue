<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="search" @keyup="checkEnter($event)" v-model="searchText" placeholder="검색어를 입력해주세요.">
      <button @click="doSearch" style="display: none;">조회</button>
      <!-- <input type="button" @click="doClear" value="X"> -->
    </div>
    <div class="sns-page-id-container">
      <div class="sns-page-id">
        <span>게시물</span>
      </div>
    </div>
    <div class="sns-detail-page-container">
      <div class="sns-write-id-container">
        <div class="sns-write-form-id">
          <div class="sns-write-id">
            <img v-bind:src="snsMaingImg5">
          </div>
        </div>
        <div class="sns-write-form-id">
          <div class="sns-write-id">
            <input type="text" value="작성자id">
          </div>
        </div>
        <!-- <div class="sns-write-form-id"> -->
        <div class="sns-control-button">
          <img v-bind:src="snsControlImg" @click="doSnsController">
          <select disabled v-model="snsController" @change="doSelectController" name="choose-control-reason" value="게시글 관리">
            <option value="글 수정">글 수정</option>
            <option value="글 삭제">글 삭제</option>
            <option value="비공개..?">비공개..?</option>
          </select>
        </div>
        <!-- </div> -->
      </div>

      <div class="sns-detail-container">
        <div class="sns-detail-form1">
          <div class="sns-img-container">
            <swiper :navigation="true" :pagination="{ clickable:true }" :modules="modules" class="mySwiper">
              <swiper-slide><img v-bind:src="snsMaingImg1"></swiper-slide>
              <swiper-slide><img v-bind:src="snsMaingImg2"></swiper-slide>
              <swiper-slide><img v-bind:src="snsMaingImg3"></swiper-slide>
              <swiper-slide><img v-bind:src="snsMaingImg4"></swiper-slide>
              <swiper-slide><img v-bind:src="snsMaingImg5"></swiper-slide>
              <swiper-slide><img v-bind:src="snsMaingImg6"></swiper-slide>

            </swiper>
            <!-- <input type="text" v-bind:value="snsWriteNumber1"  style="display :none;">
        <img v-bind:src="snsMaingImg6"> -->



            <!-- carousel 사진 -->
            <!-- <div class="carousel">
  <div class="carousel-inner">
    <div class="carousel=item">
      <img />
    </div>
  </div>
</div> -->



          </div>
        </div>

        <div class="sns-detail-form1">
          <div class="sns-write-all-form-container">

            <div class="sns-write-context">
              <textarea v-model="snsWriteText" placeholder="글내용 글내용 #태그"></textarea>
            </div>
          </div>

          <div class="sns-push-button-container">

            <div class="sns-push-button-container1">
              <div class="sns-write-like-button">
                <img v-if="liked" v-on:click='hearted()' v-bind:src="heartImg">
                <img v-if="!liked" v-on:click="hearted()" v-bind:src="heartImg2">
              </div>
            </div>
            <div class="sns-push-button-container1">
              <div class="sns-write-comment-button">
                <img v-bind:src="commentImg">
              </div>
            </div>

            <div class="sns-push-button-container2">
              <div class="sns-notification">
                <img v-bind:src="notifyImg">
              </div>
            </div>
          </div>

          <div class="sns-detail-comment-form-container" :key="comment.no" v-for="comment in comments">
            <div class="sns-detail-comment-form">
              <div class="sns-comment-write-id-container">
                <div class="sns-comment-write-id">
                  <img v-bind:src="comment.Img">
                </div>
                <div class="sns-comment-write-id">
                  <input type="text" :value="comment.id">
                </div>
              </div>
              <div class="sns-comment-container">
                <div class="sns-comment">
                  <div class="sns-comment-write-context">
                    <textarea v-text="comment.text"></textarea>

                  </div>
                </div>
                <div class="sns-comment-date">
                  <input type="text" :value="comment.date">
                </div>
              </div>
            </div>


            <!-- <div class="sns-detail-comment-form">
            <div class="sns-comment-write-id-container">
            <div class="sns-comment-write-id">
              <img v-bind:src="snsMaingImg4">
            </div>
            <div class="sns-comment-write-id">
              <input type="text" value="댓글작성자id">
            </div>
          </div>
            <div class="sns-comment-container">
              <div class="sns-comment">
                <div class="sns-comment-write-context">
          <textarea v-model="snsCommentWriteText" placeholder="집에가고싶네요"></textarea>

        </div>
              </div>
              <div class="sns-comment-date">
                <input type="text" value="2022/09/30">
              </div>
            </div>
          </div> -->

          </div>
        </div>
      </div>
    </div>


    <!-- 모달창 -->
    <!-- <SnsReport -if="isModify"

      @cancelModify="cancelModify"
      @confirmModify="confirmModify">

    </SnsReport> -->
  </div>
</template>

<script>
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue"
import likeImg from "@/assets/img/used/heart.png"
import dislikeImg from "@/assets/img/used/heart2.png"
import commentImage from "@/assets/img/sns/comment.png"
import notify from "@/assets/img/sns/notify.png"
import control from "@/assets/img/sns/snsControll.png"

// // Import Swiper styles
// import "swiper/css";
// import "swiper/css/navigation";
// import "swiper/css/pagination";

// import required modules
import { Navigation, Pagination } from "swiper";

import img1 from "@/assets/img/sns/이미지1.jpg"
import img2 from "@/assets/img/sns/이미지2.jpg"
import img3 from "@/assets/img/sns/이미지3.jpg"
import img4 from "@/assets/img/sns/이미지4.jpg"
import img5 from "@/assets/img/sns/이미지5.jpg"
import img6 from "@/assets/img/sns/이미지6.jpg"
import SnsReport from "./SnsReport.vue"



export default {
  data: () => {
    return {
      snsWriteText: "",
      snsCommentWriteText: "",
      // snsWriteNumber1: "1111",
      // snsWriteNumber2: "2222",
      // snsWriteNumber3: "3333",
      // snsWriteNumber4: "4444",
      // snsWriteNumber5: "5555",
      // snsWriteNumber6: "6666",
      snsMaingImg1: img1,
      snsMaingImg2: img2,
      snsMaingImg3: img3,
      snsMaingImg4: img4,
      snsMaingImg5: img5,
      snsMaingImg6: img6,
      searchText: "",

      //댓글정보
      comments: [
        { Img: img3, id: "eee", no: "22", text: "매참김밥양파매움", date: "2022/08/08" },
        { Img: img4, id: "qqq", no: "33", text: "매참김밥양파많이매움", date: "2022/08/09" }
      ],

      //댓글 이미지
      commentImg: commentImage,
      //좋아요
      heartImg: likeImg,
      heartImg2: dislikeImg,
      liked: true,

      //신고 이미지
      notifyImg: notify,

      //글수정삭제
      //이미지
      snsControlImg: control,
      //선택문자
      snsController : '글 수정',
      //수정삭제show
      snsControlshow: true,




      // //모달창
      // isModify : false,

      //이미지슬라이더
      // slide : [
      // snsMaingImg1: img1,
      // snsMaingImg2: img2,
      // snsMaingImg3: img3,
      // snsMaingImg4: img4,
      // snsMaingImg5: img5,
      // snsMaingImg6: img6,
      // ]

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
      // },

      // //모달창
      // modify : function(){
      //   this.isModify = true;
    },

    //좋아요
    hearted: function () {
      this.liked = !this.liked
    },

    //글 수정삭제 버튼
    doSnsController(){
      //if안쓰고 active시키는방법인듯.. 싱기함...
      document.querySelector('.sns-control-button select').classList.toggle('active');
      if(document.querySelector('.sns-control-button select').disabled){
        document.querySelector('.sns-control-button select').disabled=false;
      }else{
        document.querySelector('.sns-control-button select').disabled=true;
      }
      // this.snsControlshow = !this.snsControlshow;
    },
    //글 수정삭제 컨트롤
    doSelectController(){
      console.log(this.snsController);      
    }
  },
  components: {
    SnsReport,
    Swiper,
    SwiperSlide,

  },
  setup() {
    return {
      modules: [Navigation, Pagination],
    };
  },
};
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

.swiper {
  width: 100%;
  height: auto;
}

.sns-container {
  width: 100vw;
  height: auto;
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

.sns-control-button{
  cursor: pointer;
  position: absolute;
  right: 0;
  
}
.sns-control-button img{
  cursor: pointer;
  position: absolute;
  right: 0;
}
.sns-control-button select{
  
  opacity: 0;
  transition: all 0.5s ease-in-out;
  
  margin-right: 35px;
}

.active{
  opacity: 1 !important;
  cursor: pointer;
}

.sns-detail-form1 {
  width: 50%;
}

.sns-img-container {
  width: 100%;
  height: 200px;
}

.sns-img-container img {
  width: 100%;
}

.sns-detail-comment-form-container {

  flex-wrap: wrap;
  justify-content: left;
}

.sns-comment-write-id-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
}

.sns-comment-write-id {
  width: 20%;
}

.sns-detail-comment-form {
  width: 95%;
  flex-wrap: wrap;
  justify-content: left;
}


.sns-comment-write-id-img img {
  width: 3vw;
  height: 3vw;
  border-radius: 70%;
  margin-right: 1vw;
}

.sns-push-button-container {
  display: flex;
  flex-wrap: wrap;
  /* justify-content: left; */
  width: 100%;
  position: relative;
}

.sns-push-button-container1 {
  margin-left: 15px;
  width: 5%;
}

.sns-write-like-button img {
  width: 100%;
  cursor: pointer;
}

.sns-write-comment-button img {
  width: 100%;
  cursor: pointer;
}

.sns-push-button-container2 {
  margin-right: 15px;
  width: 5%;
  position: absolute;
  right: 0;
}


.sns-notification img {

  width: 100%;
  height: 100%;
  /* min-width: 16.34px; */
  /* min-height: 16.34px; */
  cursor: pointer;
  /* justify-content: right; */


}

.sns-detail-comment-form-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  width: 100%;
}



.sns-comment-write-id img {
  width: 3vw;
  height: 3vw;
  border-radius: 70%;
  margin-right: 1vw;
}

.sns-comment-write-id input {
  margin-top: 0.5vw;
  padding: 0.3vw;
  border: none;
}

.sns-comment-write-id input:focus {
  outline: none;
}

.sns-comment-write-context textarea {
  margin-top: 5px;
  width: 24vw;
  height: 30px;
  border: 1px solid;
  resize: none;
  border-radius: 5px;
  margin-left: 15px;
}

.sns-comment-date input {
  border: none;
  text-align: right;
  margin-left: 50%;
}

.sns-comment-date input:focus {
  outline: none;
}
</style>