<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <SnsSearch @showHashList="showHashList"></SnsSearch>
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
            <img
              :src="'http://13.125.95.210:85/java/profile/' + storedProfile.imagePath + '/' + storedProfile.storedName"
              @click="getSnsNickFeed(snsItem.nickname)">
            <!--프로필 이미지로 바꾸기 -->
          </div>
        </div>
        <div class="sns-write-form-id">
          <div class="sns-write-id">
            <input type="text" :value="snsItem.nickname" readonly>
          </div>
          <div class="sns-write-location">
            <input type="text" :value="snsItem.location" readonly>
          </div>
          <div class="sns-write-place">
            <input type="text" :value="snsItem.place" readonly>
          </div>
        </div>
        <div class="sns-control-button" v-show="$store.state.email == this.snsItem.email">
          <img v-bind:src="snsControlImg" @click="doSnsController">
          <select disabled @change="doSelectController($event)" name="choose-control-reason" value="게시글 관리">
            <option v-if="$store.state.auth != 0" value="글 수정" id="btn-update">글 수정</option>
            <option value="글 삭제" id="btn-delete">글 삭제</option>
          </select>
        </div>
      </div>
      <div class="sns-detail-container">
        <div class="sns-detail-form1">
          <div class="sns-img-container">
            <swiper :navigation="true" :pagination="{ clickable: true, }" :modules="modules" class="mySwiper">
              <swiper-slide v-for="snsImg of snsImgs">
                <input type="text" :value="snsImg.writeNo" style="display :none;" readonly>
                <img
                  v-bind:src="'http://13.125.95.210:85/java/showSnsImage/' + snsImg.snsPath + '/' + snsImg.storedName">
              </swiper-slide>
            </swiper>
          </div>
        </div>
        <div class="sns-detail-form1">
          <form class="sns-write-all-form-container" id="snsForm">
            <div class="sns-write-context">
              <textarea placeholder="글내용 글내용" :value="snsItem.content" readonly></textarea>
              <input type="text" :value="snsItem.writeNo" style="display :none;" name="writeNo" readonly>
            </div>
            <div class="sns-write-hashtag">
              <div placeholder="#태그" v-for="hashOne of snsItem.hashtag" readonly>
                <div v-html="hashOne" @click="ClickHashtag($event)"></div>
              </div>
            </div>
            <div class="sns-write-date">
              <p>{{ yyyyMMddhhmmss(snsItem.writeDate) }}</p>
            </div>
          </form>
          <div class="sns-push-button-container">
            <div class="sns-push-button-container1">
              <div>
                <p v-text="snsWriteCount"></p>
              </div>
              <div class="sns-write-like-button">
                
                <img v-if="liked == true" v-on:click='hearted()' v-bind:src="heartImg">
                <img v-if="liked == false" v-on:click="hearted()" v-bind:src="heartImg2">
              </div>
            </div>
            <div class="sns-push-button-container1">
              <div class="sns-write-comment-button">
                <img v-bind:src="commentImg" @click="writeComment()">
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
                  <img
                    :src="'http://13.125.95.210:85/java/profile/' + snsCommentItem.profile.imagePath + '/' + snsCommentItem.profile.storedName"
                    @click="getSnsNickFeed(snsCommentItem.nickname)">
                  <input type="text" :value="snsCommentItem.email" style="display :none;" readonly>
                </div>
                <div class="sns-comment-write-id">
                  <input type="text" :value="snsCommentItem.nickname" readonly>
                </div>
              </div>
              <div class="sns-comment-container">
                <div class="sns-comment">
                  <div class="sns-comment-write-context">
                    <div class="sns-comment-write-context-html" v-html="snsCommentItem.commentContent"
                      @click="clickCommentHash($event)"></div>
                  </div>
                </div>
                <div class="sns-comment-date">
                  <p>{{ yyyyMMddhhmmss(snsCommentItem.commentDate) }}</p>
                </div>
              </div>
            </div>
          </div>
          <div class="sns-write-comment-container">
            <div class="sns-search-list-container">
              <textarea placeholder="댓글을 작성하세요" v-model="commentTextarea"></textarea>
              <button type="button" @click="doComment" @keyup.enter="doComment">게시</button>
            </div>
            <div class="sns-search-list" v-if="searchResultNick != ''">
              <div class="sns-search-nick" @click="getNickname(item)" v-for="item of searchResultNick">{{ item }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import SnsSearch from './SnsSearch.vue';
import likeImg from "@/assets/img/sns/heart.png"
import dislikeImg from "@/assets/img/sns/heart2.png"
import commentImage from "@/assets/img/sns/comment.png"
import notify from "@/assets/img/sns/notify.png"
import control from "@/assets/img/sns/snsControll.png"
import { Swiper, SwiperSlide } from "swiper/vue";
import "swiper/css";
import "swiper/css/pagination";
import { Navigation, Pagination } from "swiper";
import Swal from "sweetalert2"
import { lastIndexOf } from "sockjs-client/lib/transport-list"
export default {
  data: function () {
    return {
      commentTextarea: '',
      snsImgs: [],
      writeNo: this.$route.params.writeNo,
      snsItem: {},
      snsCommentItems: [],
      snsCommentWriteText: "",
      //프로필 이미지
      storedProfile: '',
      searchNick: '',
      //닉네임모음
      searchNickname: [],
      //닉네임결과값
      searchResultNick: [],
      searchText: "",
      //댓글 이미지
      commentImg: commentImage,
      //좋아요
      snsWriteCount : "",
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
      nameTagIndex: -1,
      nameTagValue: '',
      spaceIndex: -1,
      isSearch: false,
    };
  },
  created: function () {
    fetch('http://13.125.95.210:85/java/snsDetail/' + this.writeNo)
      .then(response => response.json())
      .then(result => {
        this.snsItem = result

        let temp = this.snsItem.hashtag;
        let tempArr = temp.split(" ");
        for (let i = 0; i < tempArr.length; i++) {
          tempArr[i] = '<span style="cursor: pointer; color:#3f729b;" class="sss">' + tempArr[i] + '</span>';
          console.log(tempArr[i]);
        }
        this.snsItem.hashtag = tempArr;
        //프로필 이미지
        fetch('http://13.125.95.210:85/java/profile/' + this.snsItem.email)
          .then(result => result.json())
          .then(result => {
            this.storedProfile = result;
          }).catch(err => console.log(err));

      })
      .catch(err => console.log(err));

    //게시글 이미지 출력
    fetch('http://13.125.95.210:85/java/snsImage/' + this.writeNo)
      .then(response => response.json())
      .then(result => {
        this.snsImgs = result
      })
      .catch(err => console.log(err));
    //서버에서 제대로 받아왔는지 확인.

    //게시글 별 댓글 출력
    fetch('http://13.125.95.210:85/java/snsComment/' + this.writeNo)
      .then(response => response.json())
      .then(result => {
        // console.log(result);
        this.snsCommentItems = result

        for (let i = 0; i < this.snsCommentItems.length; i++) {

          let emails = this.snsCommentItems[i].email;
          //댓글프로필이미지
          //프로필 이미지
          fetch('http://13.125.95.210:85/java/profile/' + emails)
            .then(result => result.json())
            .then(result => {
              //객체 안에 객체 추가 snsCommentItems에 profile라는 이름으로 객체 추가된것임...짱신기
              this.snsCommentItems[i].profile = result;
              console.log(this.snsCommentItems[i]);

              //댓글태그내용 출력..
              console.log(this.snsCommentItems[i].commentContent);
            }).catch(err => console.log(err));
        }

      })
      .catch(err => console.log(err));

    //좋아요 상태 가져오기..
    if (this.$store.state.email != null) {
      fetch('http://13.125.95.210:85/java/save?boardId=' + this.writeNo + '&email=' + this.$store.state.email + '&boardDivision=3')
        .then(result => result.text())
        .then(result => {
          if (result == 'true') {
            this.liked = false;
            // console.log(this.liked);
            // console.log('좋아요 상태되는지' + result);
          }
        })
        .catch(err => console.log(err));
    }

    // 게시글의 좋아요 총 게시글 숫자 표시
    fetch('http://13.125.95.210:85/java/memberLikeWriteNoCount/' + this.writeNo)
      .then(response => response.text())
      .then(result => {
        console.log(result);
        this.snsWriteCount = +result
      })
      .catch(err => console.log(err));

    console.log(this.snsWriteCount);

    //닉네임검색일단쌔벼옴
    //닉네임 검색... 왜안되ㅈ는지....그지같음...
    fetch('http://13.125.95.210:85/java/snsnickname')
      .then(result => result.text())
      .then(result => result.substring(2, result.length - 2))
      .then(result => result.replaceAll('","', ' '))
      .then(result => result.split(' '))
      .then(result => result.join(' '))
      .then(result => {
        this.searchNickname.push(result)
        // console.log(result)
      })


    //   //댓글태그~~~~~ 가능하면 ..좋겠다... (입력한 상태에서 @닉네임  < 뽑아내기)
    // let result = this.commentTextarea.replaceAll(/@/gi, '<span></span>')
  },
  //검색
  methods: {
    //해시태그 클릭시 해시태그 검색출력페이지로
    ClickHashtag(e) {
      let target = e.target
      console.log(target);
      target = target.innerText;

      this.$router.push({ name: 'SnsMain', params: { hashtag: target } });

      // for(let cutTag of cutTags ){
      //   console.log(cutTag);
      // }

      // sss = sss.substring(1, sss.length);
    },
    //아이디프로필사진 클릭시 그사람마이페이지로
    getSnsNickFeed(nickname) {
      this.$router.push({ name: 'SnsMyFeed', params: { nickname: nickname } });
    },
    // doSearch() {
    //   console.log(this.searchText);
    // },
    // checkEnter(event) {
    //   if (event.keyCode == 13) {
    //     this.doSearch();
    //   }
    //   // },
    //   // //모달창
    //   // modify : function(){
    //   //   this.isModify = true;
    // },
    //좋아요
    hearted: function () {
      // this.liked = !this.liked
      if (this.liked == true) {
        // console.log("저장하기");
        this.doLike();
      }
      else if (this.liked == false) {
        // console.log("저장빼기");
        this.doDislike();
      }
    },
    //좋아요 저장하기 insert
    doLike() {
      //좋아요한 게시글 번호, 좋아요 누른 로그인한 사람 이메일
      //저장글번호
      //sns글번호
      let writeNo = document.querySelector('.sns-write-context input').value;
      //저장 분류(sns는 3)
      let boardDivision = 3;
      //이메일
      let email = this.$store.state.email;
      let like = {
        boardId: writeNo,
        boardDivision: boardDivision,
        email: email
      }

      // console.log(like);
      if (this.$store.state.email != null) {
        fetch('http://13.125.95.210:85/java/save', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(like)
        }).then(result => result.text())
          .then(result => {
            // console.log(result);
            if (result == "true") {
              this.liked = false;
              Swal.fire({
                confirmButtonText: '확인',
                title: '좋아요한 게시글에 저장되었습니다.',
              })
              this.snsWriteCount++;
              // .then((result) => {
              //   /* Read more about isConfirmed, isDenied below */
              //   // if (result.isConfirmed) {
              //   //   //현재페이지 새로고침!
              //   //   this.$router.go(0)
              //   // }
              // })
            }
          })
          .catch(err => console.log(err))
      }
    },
    //좋아요 저장하기 delete
    doDislike() {
      //좋아요한 게시글 번호, 좋아요 누른 로그인한 사람 이메일
      //저장글번호
      //sns글번호
      let writeNo = document.querySelector('.sns-write-context input').value;
      //저장 분류(sns는 3)
      let boardDivision = 3;
      //이메일
      let email = this.$store.state.email;
      let dislike = {
        boardId: writeNo,
        boardDivision: boardDivision,
        email: email
      }
      fetch('http://13.125.95.210:85/java/save', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(dislike)
      }).then(result => result.text())
        .then(result => {
          // console.log(result);
          if (result == "true") {
            this.liked = true;
            Swal.fire({
              confirmButtonText: '확인',
              title: '좋아요가 취소되었습니다.',
            })
            this.snsWriteCount--;
            // .then((result) => {
            //   /* Read more about isConfirmed, isDenied below */
            //   // if (result.isConfirmed) {
            //   //   //현재페이지 새로고침!
            //   //   this.$router.go(0)
            //   // }
            // })
          }
        })
        .catch(err => console.log(err))
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
      if (this.$store.state.auth != 0) {
        if (e.target.value == "글 수정") {
          this.getUpdate()
        } else if (e.target.value == "글 삭제") {
          this.getDelete()
        }
      } else {
        if (e.target.value == "글 삭제") {
          this.getDeleteByAdmin()
        }
      }
    },
    //글 수정
    getUpdate() {
      this.$router.push({ name: 'SnsRewrite', params: { writeNo: this.snsItem.writeNo } });
      // console.log('글을 수정하겟음')
      // fetch('http://13.125.95.210:85/java/snsUpdate')
      // console.log(result)
      //   .then(result => console.log(result))

    },
    //글 삭제
    getDelete() {
      let snsInfo = [];
      new FormData(document.querySelector('#snsForm')).forEach((value, key) => snsInfo[key] = value);

      console.log(snsInfo);

      if (confirm("삭제하시겠습니까?")) {
        fetch('http://13.125.95.210:85/java/snsDelete?writeNo=' + snsInfo.writeNo, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'

          },
          body: JSON.stringify(snsInfo)
        })
          .then(result => result.text())
          .then(result => {
            this.$router.push({ path: "/sns" })
          })
          .catch(err => console.log(err))
      }
    },
    //관리자 삭제
    getDeleteByAdmin() {
      if (confirm("삭제하시겠습니까?")) {

        fetch('http://13.125.95.210:85/java/snsDeleteByAdmin?writeNo=' + this.writeNo, {
          method: 'DELETE'
        }).then(result => result.text())
          .then(result => {
            this.$router.push({ path: "/sns" })
          }).catch(err => console.log(err))
      }
    },

    testClick: function () {
      console.log("test");
    },
    writeComment() {
      let coFocus = document.querySelector('.sns-search-list-container textarea');
      coFocus.focus();
    },
    //댓글 작성
    doComment() {
      //닉네임, 글번호, 이메일, 작성 텍스트 가져오기
      //작성텍스트
      let commentContent = document.querySelector('.sns-write-comment-container textarea').value;

      console.log(commentContent, "=======================");
      //닉네임만 스판태그로 감싸기
      commentContent = commentContent.replace(/(\@[a-zA-Z가-힣0-9]*)/gi, '<span style="cursor: pointer; color:#3f729b;" class="ttt">$1</span>');

      console.log(document.querySelectorAll('.ttt')[1]);

      // console.log(document.querySelectorAll('.ttt').innerText);

      // console.log(document.querySelectorAll('.ttt').value);

      // console.log(document.querySelectorAll('.ttt').innerHtml);

      // console.log(document.querySelectorAll('.sns-search-list-container').innerHtml);

      //글번호
      let writeNo = document.querySelector('.sns-write-context input').value;
      //닉네임
      let nickname = this.$store.state.nickname;
      // let nickname = "유저2";
      //이메일
      let email = this.$store.state.email;
      // let email = "user2";

      let comment = {
        commentContent: commentContent,
        writeNo: writeNo,
        nickname: nickname,
        email: email
      }

      // //닉네임만 스판태그로 감싸기
      // this.commentTextarea = this.commentTextarea.replace(/(\@[a-zA-Z]*)/gi, '<span>$1</span>');

      // debugger
      console.log(comment);

      fetch('http://13.125.95.210:85/java/comment', {
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

    //댓글에서 @닉네임 부분 가져오기
    clickCommentHash(e) {
      let target = e.target;
      console.log(target);
      let elnickList;
      if (!target.classList.contains('ttt')) {
        return;
      } else {
        elnickList = target.innerText;
        console.log(target);
      }
      //나의...
      // for (let i = 0; i<document.querySelectorAll('.ttt').length ; i++){
      //   let nickList = (document.querySelectorAll('.ttt')[i].innerText);
      // console.log(nickList);
      // let elnickList = nickList.substring(1, nickList.length);
      // console.log(elnickList);

      //가져온다음 @자르기
      elnickList = elnickList.substring(1, elnickList.length);
      // if(nickList.substring(1, nickList.length) == document.querySelector('.sns-comment-write-context-html')[i].
      // this.commentNickFeed(elnickList);
      this.$router.push({ name: 'SnsMyFeed', params: { nickname: elnickList } });

    },
    doSearch() {
      console.log(this.searchText)
    },
    checkEnter(event) {
      let searchValue = event.target.value;
      let tagIndex = searchValue.indexOf('@');
      if (tagIndex >= 0) {
        let temp = searchValue.substr(tagIndex + 1, searchValue.length);
        searchValue = temp.substr(0, temp.indexOf(' '));
        this.doNickHashtag(searchValue);
      }
      // console.log(event.target.value);
      // searchValue 에서 맨앞이 #인지 확인
      // let result1 = searchValue.substr(0, 1);
      // searchValue = searchValue.substr(1, searchValue.length);
      // #이면 -> 태그검색 함수로 function doSearchTag
      // if (result1 == '@') {
      //   this.doNickHashtag(searchValue); //searchVal의 흐름 2. 여기서 searchValue  =  searchVal인 것을 인지시켜줌
      //   // #이 없으면 -> 아이디검색 함수로 function doSearchId
      // } else {
      //   this.doNickHashtag(searchValue);
      // }

      if (event.keyCode == 13) {
        this.doSearch()
      }
    },
    //댓글에 닉네임태그 검색..!
    doNickHashtag(searchVal) {//searchValue의 공간만들어주기
      this.searchResultNick = [];
      // console.log(searchVal); // 내가 입력한거
      for (let snsList of this.searchNickname) { //
        // console.log(snsList.member_id);
        // let searchIdList = snsList.member_id;
        // console.log(searchId);
        let result2 = snsList.indexOf(searchVal);
        if (result2 >= 0) {
          let results3 = snsList.split(' ');
          let result5 = results3.filter(results4 => results4.includes(searchVal));
          // console.log(this.searchId);
          result5.forEach((element) => {
            if (!this.searchResultNick.includes(element)) {
              this.searchResultNick.push(element);
            }
          });
        }
      }
    },
    getNickname(item) {
      //클릭한 닉네임 input창에 가져오기
      console.log(item);
      console.log(this.commentTextarea);
      console.log(this.nameTagIndex);
      console.log(this.spaceIndex)
      let temp = this.commentTextarea.substring(0, this.nameTagIndex + 1) + item + this.commentTextarea.substring(this.spaceIndex, this.commentTextarea.length);
      console.log(temp);
      this.commentTextarea = temp;

      this.searchResultNick = '';

      //  console.log(this.commentTextarea.substring(this.nameTagIndex+1, this.spaceIndex));
      //  console.log(this.commentTextarea.replace((this.commentTextarea.substring(this.nameTagIndex+1, this.spaceIndex)), item));

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
          fetch('http://13.125.95.210:85/java/report', {
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
              // console.log(result);
            })
          return false;
        }
      })
    },
    nameTagSearch() {
      this.doNickHashtag(this.nameTagValue);
      console.log('탐색 : ', this.nameTagValue);
      console.log(this.searchResultNick);
    }
  },
  watch: {
    commentTextarea(newVal, oldVal) {
      // 길이 필요, @인덱스 저장 필요
      // 만약 @ 인덱스가 나오면 인덱스 저장, -부터 탐색시작 -> 공백까지
      // 길이는 @ 삭제될거를 대비해서 저장 삭제되면 현재 길이보다 작으니까,
      console.log('텍스트');
      this.nameTagIndex = newVal.lastIndexOf('@');
      this.spaceIndex = newVal.lastIndexOf(' ');
    },
    nameTagIndex(newVal, oldVal) {
      if (newVal > oldVal) {  // @ 입력했을때
        this.isSearch = true;
      } else if (newVal < oldVal) { // @ 지웟을때
        this.isSearch = false;
      }
    },
    spaceIndex(newVal, oldVal) {
      if (this.nameTagIndex < newVal && this.isSearch == true) { // @ 이후에 공백입력했을때,
        // 탐색시작 
        this.nameTagValue = this.commentTextarea.substring(this.nameTagIndex + 1, this.spaceIndex); // 이걸로 탐색함.
        this.nameTagSearch(this.nameTagValue);
        this.isSearch = false;
      } else {  // @ 이후에 공백 입력이 되지 않았을때,
        // 탐색 X 
      }
    }
  },
  components: {
    Swiper,
    SwiperSlide,
    SnsSearch
  },
  setup() {
    return {
      modules: [Navigation, Pagination],
    };
  }
}
</script>
<style scoped src="@/assets/css/sns/SnsDetailFeed.css">

</style>


