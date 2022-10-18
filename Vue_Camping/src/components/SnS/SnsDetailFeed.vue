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
            <img v-bind:src="snsMaingImg1">
          </div>
        </div>
        <div class="sns-write-form-id">
          <div class="sns-write-id">
            <input type="text" :value="snsItem.nickname">
          </div>
          <div class="sns-write-location">
            <input type="text" :value="snsItem.location">
          </div>
          <div class="sns-write-place">
            <input type="text" :value="snsItem.place">
          </div>
        </div>
        <!-- <div class="sns-write-form-id"> -->
        <div class="sns-control-button">
          <img v-bind:src="snsControlImg" @click="doSnsController">
          <select disabled @change="doSelectController($event)" name="choose-control-reason" value="게시글 관리">
            <option value="글 수정" id="btn-update">글 수정</option>
            <option value="글 삭제" id="btn-delete">글 삭제</option>
          </select>
        </div>
        <!-- </div> -->
      </div>

      <div class="sns-detail-container">
        <div class="sns-detail-form1">
          <div class="sns-img-container">
            <swiper :navigation="true" :pagination="{clickable: true,}" :modules="modules" class="mySwiper">
              <swiper-slide v-for="snsImg of snsImgs">
                <input type="text" :value="snsImg.writeNo" style="display :none;">
                <img v-bind:src="'http://localhost:8087/java/showSnsImage/'+snsImg.snsPath+'/'+snsImg.storedName">
              </swiper-slide>
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
          <form class="sns-write-all-form-container" id="snsForm">

            <div class="sns-write-context">
              <textarea placeholder="글내용 글내용" :value="snsItem.content"></textarea>
              <input type="text" :value="snsItem.writeNo" style="display :none;" name="writeNo">
            </div>
            <div class="sns-write-hashtag">
              <textarea placeholder="#태그" :value="snsItem.hashtag"></textarea>
            </div>
            <div class="sns-write-date">
              <p>{{yyyyMMddhhmmss(snsItem.writeDate)}}</p>
            </div>
          </form>

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
                <img v-bind:src="notifyImg" @click="reportItem()">
              </div>
            </div>
          </div>

          <div class="sns-detail-comment-form-container" :key="snsCommentItem.writeNo"
            v-for="snsCommentItem of snsCommentItems">
            <div class="sns-detail-comment-form">
              <div class="sns-comment-write-id-container">
                <div class="sns-comment-write-id">
                  <img src="">
                </div>
                <div class="sns-comment-write-id">
                  <input type="text" :value="snsCommentItem.nickname">
                </div>
              </div>
              <div class="sns-comment-container">
                <div class="sns-comment">
                  <div class="sns-comment-write-context">
                    <textarea :value="snsCommentItem.commentContent"></textarea>

                  </div>
                </div>
                <div class="sns-comment-date">
                  <p>{{yyyyMMddhhmmss(snsCommentItem.commentDate)}}</p>
                </div>
              </div>
            </div>
          </div>
          <hr />
          <div class="sns-write-comment-container">
            <textarea placeholder="댓글을 작성하세요"></textarea>
            <button type="button" @click="doComment" @keyup.enter="doComment">게시</button>
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
import likeImg from "@/assets/img/sns/heart.png"
import dislikeImg from "@/assets/img/sns/heart2.png"
import commentImage from "@/assets/img/sns/comment.png"
import notify from "@/assets/img/sns/notify.png"
import control from "@/assets/img/sns/snsControll.png"
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue";
// Import Swiper styles
import "swiper/css";
import "swiper/css/pagination";
// import required modules
import { Navigation, Pagination } from "swiper";
import img1 from "@/assets/img/sns/이미지1.jpg"
import img3 from "@/assets/img/sns/이미지3.jpg"
import img4 from "@/assets/img/sns/이미지4.jpg"

import Swal from "sweetalert2"

export default {
  created: function () {
    fetch('http://localhost:8087/java/snsDetail/' + this.writeNo)
      .then(response => response.json())
      .then(result => {
        this.snsItem = result
      })
      .catch(err => console.log(err));
    //서버에서 제대로 받아왔는지 확인.
    console.log(this.snsItem);

    //게시글 이미지 출력
    fetch('http://localhost:8087/java/snsImage/' + this.writeNo)
      .then(response => response.json())
      .then(result => {
        this.snsImgs = result
      })
      .catch(err => console.log(err));
    //서버에서 제대로 받아왔는지 확인.

    //게시글 별 댓글 출력
    fetch('http://localhost:8087/java/snsComment/' + this.writeNo)
      .then(response => response.json())
      .then(result => {
        console.log(result);
        this.snsCommentItems = result
      })
      .catch(err => console.log(err));
  },
  data: function () {
    return {
      snsImgs: [],
      writeNo: this.$route.params.writeNo,
      snsItem: {},
      snsWriteText: "",
      snsWriteHashtag: "",
      snsWriteDate: "",

      snsCommentItems: [],
      snsCommentWriteText: "",
      // snsWriteNumber1: "1111",
      // snsWriteNumber2: "2222",
      // snsWriteNumber3: "3333",
      // snsWriteNumber4: "4444",
      // snsWriteNumber5: "5555",
      // snsWriteNumber6: "6666",
      snsMaingImg1: img1,

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
    doSnsController() {
      //if안쓰고 active시키는방법인듯.. 싱기함...
      document.querySelector('.sns-control-button select').classList.toggle('active');
      if (document.querySelector('.sns-control-button select').disabled) {
        document.querySelector('.sns-control-button select').disabled = false;
      } else {
        document.querySelector('.sns-control-button select').disabled = true;
      }
      // this.snsControlshow = !this.snsControlshow;
    },
    // @click="getUpdate(snsItem.writeNo)"
    //글 수정삭제 컨트롤
    doSelectController(e) {
      console.log(e.target.value);
      if (e.target.value == "글 수정") {
        this.getUpdate()
      } else if (e.target.value == "글 삭제") {
        this.getDelete()
      }
    },
    //글 수정
    getUpdate() {
      this.$router.push({ name: 'SnsRewrite', params: { writeNo: this.snsItem.writeNo } });
      // console.log('글을 수정하겟음')
      // fetch('http://localhost:8087/java/snsUpdate')
      // console.log(result)
      //   .then(result => console.log(result))

    },
    //글 삭제
    getDelete() {
      let snsInfo = [];
      new FormData(document.querySelector('#snsForm')).forEach((value, key) => snsInfo[key] = value);

      console.log(snsInfo);

      fetch('http://localhost:8087/java/snsDelete?writeNo=' + snsInfo.writeNo, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'

        },
        body: JSON.stringify(snsInfo)
      })
        .then(result => result.text())
        .then(result => console.log(result))
        .catch(err => console.log(err))

      if (confirm("삭제하시겠습니까?")) {
        this.$router.push({ path: "/sns" })
        // console.log('글을 삭제하겟음')
        // fetch('http://localhost:8087/java/snsDelete')
        // console.log(result)
        //   .then(result => console.log(result))
      }
    },
    //댓글 작성
    doComment() {
      //닉네임, 글번호, 이메일, 작성 텍스트 가져오기
      //작성텍스트
      let commentContent = document.querySelector('.sns-write-comment-container textarea').value;
      //글번호
      let writeNo = document.querySelector('.sns-write-context input').value;
      //닉네임
      let nickname = this.$store.state.nickname
      // let nickname = "유저2";
      //이메일
      let email = this.$store.state.email
      // let email = "user2";

      let comment = {
        commentContent: commentContent,
        writeNo: writeNo,
        nickname: nickname,
        email: email
      }

      console.log(comment);

      fetch('http://localhost:8087/java/comment', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'

        },
        body: JSON.stringify(comment)
      }).then(result => result.text())
        .then(result => {
          console.log(result);
          if (result == "true") {
            Swal.fire({
              title: '댓글이 등록되었습니다',
              confirmButtonText: '확인',
            }).then((result) => {
              /* Read more about isConfirmed, isDenied below */
              if (result.isConfirmed) {
                //현재페이지 새로고침!
                this.$router.go(0)
              }
            })


          }
        })
        .catch(err => console.log(err))
    },
    //유리언니..
    yyyyMMddhhmmss: function (value) {
      if (value == '') return '';

      var js_date = new Date(value);

      // 연도, 월, 일, 시, 분, 초 추출
      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();
      var hours = ('0' + js_date.getHours()).slice(-2);
      var minutes = ('0' + js_date.getMinutes()).slice(-2);
      var seconds = ('0' + js_date.getSeconds()).slice(-2);

      if (month < 10) { month = '0' + month; }
      if (day < 10) { day = '0' + day; }

      return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
    },

    //신고
    reportItem() {
      let item = Swal.fire({
        title: '신고',
        html:
          '<select id="swal-input1" class="swal2-select" style="font-size:13px;">' +
          '<option value="" disabled="">신고 분류</option>' +
          '<option value="잘못된 정보">잘못된 정보</option>' +
          '<option value="게시글 규정 위반">게시글 규정 위반</option>' +
          '<option value="기타">기타</option>' +
          '</select>' +
          '<textarea id="swal-input2" class="swal2-textarea" style="resize:none; width:80%; font-size:12px;" maxlength="450" placeholder="신고 사유를 입력하세요"></textarea>',
        focusConfirm: false,
        showCancelButton: true,
        confirmButtonText: '신고',
        cancelButtonText: '취소',
        confirmButtonColor: 'rgba(6,68,32,0.8)',
        preConfirm: () => {
          let fetchData = {
            "boardId": this.writeNo,
            "boardDivision": 3,
            "reportDivision": document.getElementById('swal-input1').value,
            "reportContent": document.getElementById('swal-input2').value,
            "email": this.$store.state.email
          }

          console.log(fetchData);
          fetch('http://localhost:8087/java/report', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(fetchData)
          }).then(result => result.text())
            .then(result => {
              if (result == "true") {
                Swal.fire({
                  icon: 'success',
                  title: '신고 완료 !',
                  toast: true,
                  showConfirmButton: false,
                  timer: 1500,
                  timerProgressBar: true,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                    this.$router.push({ name: 'SnsDetail', params: { writeNo: this.snsItem.writeNo } });
                  }
                })
              } else {
                Swal.fire({
                  icon: 'error',
                  title: '신고 실패 !',
                  text: '계속 실패하면 고객센터에 문의해주세요.',
                  toast: true,
                  showConfirmButton: false,
                  timer: 1500,
                  timerProgressBar: true,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
                })
              }
              console.log(result);
            })

          return false;
        }
      })
      console.log(item);
    }
  },
  components: {
    Swiper,
    SwiperSlide,

  },
  setup() {
    return {
      modules: [Navigation, Pagination],
    };
  }
}
</script>

<style scoped>
.sns-container {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}

.sns-image {

  width: 30%;
}

.swiper {
  width: 100%;
  height: 100%;
}

.swiper-slide {
  text-align: center;
  font-size: 18px;
  background: #fff;

  /* Center slide text vertically */
  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  -webkit-align-items: center;
  align-items: center;
}

.swiper-slide img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.sns-container {
  margin-top: 150px;
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
  width: 300px;
  padding: 0.3vw;
  border: none;
}

.sns-write-id input:focus {
  outline: none;
}

.sns-write-location input {
  margin-top: 0.5vw;
  width: 300px;
  padding: 0.3vw;
  border: none;
}

.sns-write-location input:focus {
  outline: none;
}

.sns-write-place input {
  margin-top: 0.5vw;
  width: 300px;
  padding: 0.3vw;
  border: none;
}

.sns-write-place input:focus {
  outline: none;
}


.sns-control-button {
  cursor: pointer;
  position: absolute;
  right: 0;

}

.sns-control-button img {
  cursor: pointer;
  position: absolute;
  right: 0;
}

.sns-control-button select {

  opacity: 0;
  transition: all 0.5s ease-in-out;

  margin-right: 35px;
}

.active {
  opacity: 1 !important;
  cursor: pointer;
}

.sns-detail-form1 {
  width: 50%;
}

.sns-img-container {
  width: 100%;
  height: 400px;
}

.sns-img-container img {
  width: 100%;
  height: 100%;
  object-fit: cover;
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

.sns-write-context textarea {
  margin-top: 5px;
  width: 90%;
  height: 10vw;
  border: none;
  resize: none;
  border-radius: 5px;
  margin-left: 15px;
}

.sns-write-hashtag textarea {
  margin-top: 5px;
  width: 90%;
  height: 5vw;
  border: none;
  resize: none;
  border-radius: 5px;
  margin-left: 15px;
}

.sns-write-date p {
  margin-top: 5px;
  width: 90%;
  height: 15px;
  border: none;
  resize: none;
  border-radius: 5px;
  margin-left: 15px;
  text-align: right;
  margin-bottom: 8px;
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
  border: none;
  resize: none;
  border-radius: 5px;
  margin-left: 15px;
}

.sns-comment-date p {
  border: none;
  text-align: right;
  margin-left: 50%;
}

.sns-write-comment-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  width: 100%;
  min-width: 285px;
}

.sns-write-comment-container textarea {
  margin-top: 5px;
  width: 24vw;
  height: 30px;
  border: none;
  resize: none;
  border-radius: 5px;
  margin-left: 15px;
}

.sns-write-comment-container textarea:focus {
  outline: none;
}

.sns-write-comment-container button {
  cursor: pointer;
  background-color: #337ab7;
  border: 1px solid #2e6da4;
  border-radius: 5px;
  font-weight: bold;
  color: azure;
  padding: 2px;
  margin-left: 3%;

}
</style>