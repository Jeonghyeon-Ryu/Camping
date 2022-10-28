<template>
  <div id="container">
    <form id="container2">
      <div class="used-heads">
        <!-- 상품사진 -->
        <div class="used-pic">
          <UsedDetailImage :usedId="usedId"></UsedDetailImage>
        </div>
        <!-- 상품명, 가격 -->
        <div class="used-info">
          <ul>
            <div>
              <!-- <li class="usedStatus"><img v-bind:src="usedStatus"></li> -->
            </div>
            <div class="used-flex">
              <div class="used-name">
                <li>
                  <h2>{{usedList.usedName}}</h2>
                </li>
              </div>
              <div class="used-status">
                <li>
                  <div v-if="usedList.dealStatus==0 && usedList.usedStatus === 0" class="dealGreen">거래가능</div>
                  <div v-if="usedList.dealStatus==1 && usedList.usedStatus === 0" class="dealRed">거래중</div>
                  <div v-if="usedList.dealStatus==2 && usedList.usedStatus === 0" class="dealGray">거래완료</div>
                  <div v-if="usedList.usedStatus != 0" class="statusBlack">접근제한</div>
                </li>
              </div>
            </div>
            <li>
              <h3>￦ {{usedList.usedPrice.toLocaleString('ko-KR')}}</h3>
            </li>
            <hr>
            <!-- 좋아요, 조회수, 신고 -->
            <div class="used-info4">
              <div class="used-info2">
                <div class="used-info3">
                  <div class="used-cnt">
                    🧡 찜 {{this.likeCnt}} · 👁‍🗨 조회수 {{usedList.usedCnt}} · 🕑 {{usedList.usedWrite}}
                  </div>
                  <div class="used-report">
                    <!-- 신고기능가져오기(다른유저가쓴글) -->
                    <p v-if="usedList.email != memberId" @click="reportItem()">신고하기</p>
                    <!-- 거래상태변경(본인이쓴글) -->
                    <div v-if="usedList.email === memberId && usedList.usedStatus === 0">
                      <select id="dealStatus" name="dealStatus" @change="dealChange()">
                        <option name="dealStatus" value='' disabled selected>거래상태 변경</option>
                        <option name="dealStatus" value="0">거래가능</option>
                        <option name="dealStatus" value="1">거래중</option>
                        <option name="dealStatus" value="2">거래완료</option>
                      </select>
                    </div>
                  </div>
                </div>
                <!-- 상품정보2 -->
                <li>
                  · 카테고리 : {{usedList.usedCategory}}
                </li>
                <li>· 상태 : <span v-if="usedList.usedCondition==0">상</span>
                  <span v-if="usedList.usedCondition==1">중</span>
                  <span v-if="usedList.usedCondition==2">하</span>
                </li>
                <li>
                  · 거래지역 : {{usedList.usedPlace}}
                </li>
              </div>
              <!-- <div class="used-head-btns">
                <input type="button" value="수정하기">
              </div> -->
            </div>
          </ul>
        </div>
      </div>
      <hr class="horizontal_line">
      <!-- 하단 -->
      <div class="used-foot">
        <div class="used-desc">
          <p><b>상품 설명</b></p>
          <hr class="horizontal-line">
          <div class="used-content">
            {{usedList.usedContent}}
          </div>
        </div>
        <!-- 작성자 정보-->
        <div class="used-writer">
          <div class="used-writer-info">
            <input type="hidden" :value="usedList.email">
            <div class="used-pf"><img
                :src="'http://13.125.95.210:85/java/profile/'+storedProfile.imagePath+'/'+storedProfile.storedName"></div>
            <div class="used-nk"><b>{{usedList.nickName}}</b></div>
          </div>
          <hr class="horizontal_line2">
          <div class="used-writer-post">
            <!-- 올린게시물정보(코드써야함) -->
            <div class="used-writer-deals">
              최근 상품
              <div class="used-writer-deal">
                <img src="@/assets/img/bg9.jpg">
                <!-- <router-link tag="div" v-bind:to="{name:'usedDetail', params : {usedId : card.usedId}}">
                  <UsedCard v-bind:usedCard="card"></UsedCard>
                </router-link> -->
              </div>
            </div>
            <div class="used-writer-home" @click="writerDetail()">
              <b>{{usedList.nickName}}</b> 님의 판매상품 더 구경하기
            </div>
            <div class="used-writer-sns" @click="writerSns(usedList.nickname)">
              <b>{{usedList.nickname}}</b> 님의 피드 방문하기
            </div>
          </div>
        </div>
        <div class="info-buttons">
          <button type="button" class="like-button"
            v-if="usedList.email != memberId && memberId !='admin' && this.liked === true" @click="hearted()">🤍
            찜하기</button>
          <button type="button" class="like-button2"
            v-if="usedList.email != memberId && memberId !='admin' && this.liked === false" @click="hearted()">🧡 찜
            취소</button>
          <button type="button" class="chat-button"
            v-if="usedList.email != memberId && memberId !='admin'  && usedList.dealStatus === 0" @click="sendingMsg()">쪽지하기</button>
          <button type="button" class="chat-button2"
            v-if="usedList.email != memberId && memberId !='admin' && usedList.dealStatus != 0">쪽지하기</button>
          <button type="button" class="update-button" v-if="usedList.email === memberId && usedList.dealStatus != 2"
            @click="usedUpdate()">수정하기</button>
          <button type="button" class="update-button2" v-if="usedList.email === memberId && usedList.dealStatus === 2"
            @click="usedUpdateFail()">수정하기</button>
          <button type="button" class="delete-button" v-if="usedList.email === memberId && usedList.dealStatus != 1"
            @click="usedDelete()">삭제하기</button>
          <button type="button" class="delete-button2" v-if="usedList.email === memberId && usedList.dealStatus === 1"
            @click="usedDeleteFail()">삭제하기</button>
          <button type="button" class="restrict-button" @click="usedRestrict()"
            v-if="memberId === 'admin'">접근제한</button>
        </div>
      </div>
    </form>
  </div>
</template>
<script>
import img1 from "@/assets/img/sns/snsControll.png";
import UsedDetailImage from "./UsedDetailImage.vue";
import Swal from 'sweetalert2';
import UsedCard from "@/components/UsedPractice/UsedCard.vue";


export default {
  data: function () {
    return {
      memberId: this.$store.state.email,
      usedList: [],
      images: [],
      usedId: this.$route.params.usedId,
      usedStatus: img1,
      liked: true,
      likeCnt: 0,
      storedProfile: '',
    }
  },
  components: {
    UsedDetailImage, UsedCard
  },
  methods: {
    writerDetail: function () {
      this.$router.push({ name: 'myUsed', params: { email: this.usedList.email } })
    },
    writerSns: function (nickname) {
      this.$router.push({ name: 'SnsMyFeed', params: { nickname: this.usedList.nickName } })
    },
    //쪽지보내기
    sendingMsg: function(){
      if(this.$store.state.email === null){
        Swal.fire({
          icon: 'warning',
          title: '로그인 후에 쪽지할 수 있어요',
          toast: true,
          showConfirmButton: false,
          timer: 1200,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
            // this.$router.push({ name: "LoginSignup" });
          }
        })
      }else{
      let item = Swal.fire({
          title: '<div class="mail-Title" style="font-size:0.6em; color: green;">판매자에게 쪽지 보내기</div>',
          html: 
          '<div class="mail-info" style="dislay:flex; border-radius:2px; width:80%; margin: 0 auto; padding: 5px; background-color:#f7f7f7"><div class="mail-usedName"> <span style="font-size:0.8em; color:#54b06d; font-weight:bold;">상품명 </span><span style="font-size:0.9em; font-weight:bold; color: #4a4a4a">'
            +this.usedList.usedName+'</span></div>'+
            '<div class="mail-usedPrice"> <span style="font-size:0.8em; color:#54b06d; font-weight:bold;">상품가격 </span><span style="font-size:0.9em; font-weight:bold; color: #4a4a4a">'+this.usedList.usedPrice+'</span><span style="font-size:0.8em; font-weight:bold;">원<span></div></div>'+
            '<textarea id="swal-input2" class="swal2-textarea" style="resize:none; width:80%; height: 200px; font-size:12px;" maxlength="200" placeholder="판매자에게 보낼 내용을 입력하세요"></textarea>',
          focusConfirm: false,
          showCancelButton: true,
          confirmButtonText: '전송',
          cancelButtonText: '취소',
          confirmButtonColor: '#54b06d',
          preConfirm: () => {
            let fetchData = {
              "usedId": this.usedId,
              "mailSender": this.$store.state.email,
              "mailReceiver": this.usedList.email,
              "mailContent": document.getElementById('swal-input2').value,
              "usedName": this.usedList.usedName,
              "sendNick": this.$store.state.nickname,
              "rcvNick": this.usedList.nickName,
            }

            console.log(fetchData);
            fetch('http://13.125.95.210:85/java/mail/sendMail', {
              method: 'POST',
              headers: {'Content-Type': 'application/json'},
              body: JSON.stringify(fetchData)
            }).then(result => result.text())
              .then(result => {
                if (result == "true") {
                  Swal.fire({
                    icon: 'success',
                    title: '전송되었습니다',
                    toast: true,
                    showConfirmButton: false,
                    timer: 900,
                    didOpen: (toast) => {
                      toast.addEventListener('mouseenter', Swal.stopTimer)
                      toast.addEventListener('mouseleave', Swal.resumeTimer)
                      this.$router.push({ path: '/used/UsedDetail/' + this.usedId, });
                    }
                  })
                } else {
                  Swal.fire({
                    icon: 'error',
                    title: '전송 실패 !',
                    text: '계속 실패하면 고객센터에 문의해주세요.',
                    toast: true,
                    showConfirmButton: false,
                    timer: 900,
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
    //찜하기
    hearted: function () {
      event.preventDefault();
      if (this.$store.state.email === null) {
        Swal.fire({
          icon: 'warning',
          title: '로그인 후에 찜해주세요',
          toast: true,
          showConfirmButton: false,
          timer: 1500,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
            // this.$router.push({ name: "LoginSignup" });
          }
        })
      } else if (this.$store.state.email === this.usedList.email) {
        Swal.fire({
          icon: 'warning',
          title: '내가 쓴 글은 찜할 수 없어요',
          toast: true,
          showConfirmButton: false,
          timer: 1000,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })
      } else {
        this.liked = !this.liked

        let save = {
          //boardId: document.querySelector('.used-id').value,
          boardId: this.usedList.usedId,
          boardDivision: 2,
          email: this.$store.state.email
        }


        console.log(save);
        console.log(save.boardId);

        if (this.liked === true) {
          fetch('http://13.125.95.210:85/java/save', {
            method: 'DELETE',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(save)
          })
            .then(Response => Response.text())  //json 파싱 
            .then(data => {
              if (data >= "1") {
                // 성공
                console.log("삭제되었습니다.")

                Swal.fire({
                  icon: 'error',
                  title: '찜 목록에서 삭제되었어요',
                  toast: true,
                  showConfirmButton: false,
                  timer: 1000,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
                })
              }
            }).catch(err => console.log(err))
          // this.$router.go()
        } else if (this.liked === false) {
          fetch('http://13.125.95.210:85/java/save', {
            method: 'POST',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(save)
          })
            .then(Response => Response.text())  //json 파싱 
            .then(data => {
              if (data >= "1") {
                // 성공
                console.log("입력되었습니다.")

                Swal.fire({
                  icon: 'success',
                  title: '찜 목록에 저장되었어요',
                  toast: true,
                  showConfirmButton: false,
                  timer: 1000,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
                })
                // this.$router.go()
                // this.$router.push({name : 'usedMain'})
              }
            }).catch(err => console.log(err))

        } else {
          console.log("좋아요실패??")
        }
      }
    },
    //거래상태변경
    dealChange: function () {
      let deal = document.querySelector('#dealStatus').value;

      let fetchData = {}
      fetchData["usedId"] = this.usedList.usedId;
      fetchData["dealStatus"] = deal;

      Swal.fire({
        title: '',
        text: '거래 상태를 변경하시겠습니까?',
        icon: 'warning',

        confirmButtonColor: '#65c66d', // confrim 버튼 색깔 지정
        cancelButtonColor: '#cc5050', // cancel 버튼 색깔 지정
        confirmButtonText: '네', // confirm 버튼 텍스트 지정
        cancelButtonText: '아니오', // cancel 버튼 텍스트 지정
        showCancelButton: true,

      })
        .then(result => {
          // 만약 Promise리턴을 받으면,
          if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
            fetch('http://13.125.95.210:85/java/used/dealUpdate', {
              method: "PUT",
              // body : fetchData
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(fetchData)
            })
              .then(Response => Response.json())  //json 파싱 
              .then(data => {
                if (data >= "1") {
                  // 성공
                  console.log("입력되었습니다.")
                } else {
                  // 실패                    
                  console.log("입력 실패")
                }
              }).catch(err => console.log(err))
            this.$router.go()
          }
        }
        );
    },
    //수정
    usedUpdate: function () {
      this.$router.push({ name: 'usedUpdate' })
    },
    //삭제
    usedDelete: function () {
      let fetchData = {};
      fetchData["usedId"] = this.usedId;
      console.log(fetchData)
      Swal.fire({
        title: '',
        text: '정말로 삭제하시겠습니까?',
        icon: 'warning',

        confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
        cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
        confirmButtonText: '네', // confirm 버튼 텍스트 지정
        cancelButtonText: '아니오', // cancel 버튼 텍스트 지정

        reverseButtons: true, // 버튼 순서 거꾸로

      }).then(result => {
        // 만약 Promise리턴을 받으면,
        if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
          fetch('http://13.125.95.210:85/java/used/statusUpdate', {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(fetchData)
          })
            .then(Response => Response.json())  //json 파싱 
            .then(data => {
              console.log(data)

            }).catch(err => console.log(err))
            .then(Swal.fire('삭제되었습니다'))
            .finally(this.$router.push({ name: 'usedMain' }))
        }
      }
      );
    },
    usedUpdateFail: function () {
      Swal.fire({
        icon: 'warning',
        title: '수정 불가',
        text: '거래 완료된 글입니다',
        toast: true,
        showConfirmButton: false,
        timer: 1200,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })
    },
    usedDeleteFail: function () {
      Swal.fire({
        icon: 'warning',
        title: '삭제 불가',
        text: '거래 중인 글입니다',
        toast: true,
        showConfirmButton: false,
        timer: 1200,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })
    },
    //접근제한
    usedRestrict: function () {
      let fetchData = {};
      fetchData["usedId"] = this.usedId;
      console.log(fetchData)
      Swal.fire({
        title: '',
        text: '이 게시물에 접근 제한을 설정하시겠습니까?',
        icon: 'warning',

        confirmButtonColor: '#65c66d', // confrim 버튼 색깔 지정
        cancelButtonColor: '#cc5050', // cancel 버튼 색깔 지정
        confirmButtonText: '네', // confirm 버튼 텍스트 지정
        cancelButtonText: '아니오', // cancel 버튼 텍스트 지정
        showCancelButton: true,

      }).then(result => {
        // 만약 Promise리턴을 받으면,
        if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
          fetch('http://13.125.95.210:85/java/used/statusRestrict', {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(fetchData)
          })
            .then(Response => Response.json())  //json 파싱 
            .then(data => {
              console.log(data)

            }).catch(err => console.log(err))
            .then(Swal.fire({
              icon: 'error',
              title: '접근 제한 설정이 완료되었습니다',
              toast: true,
              showConfirmButton: false,
              timer: 1200,
              didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
                this.$router.push({ name: "usedMain" });
              }
            }))
        }
      }
      );
    },
    //신고
    reportItem: function () {
      if (this.$store.state.email === null || this.$store.state.email === '') {
        Swal.fire({
          icon: 'warning',
          title: '로그인 후에 신고할 수 있어요',
          toast: true,
          showConfirmButton: false,
          timer: 1200,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })
      } else {
        let item = Swal.fire({
          title: '신고',
          html:
            '<select id="swal-input1" class="swal2-select" style="font-size:13px;">' +
            '<option value="" disabled="">신고 분류</option>' +
            '<option value="판매 금지 물품">판매 금지 물품</option>' +
            '<option value="사기 게시물">사기 게시물</option>' +
            '<option value="중고거래 게시물이 아님">중고거래 게시물이 아님</option>' +
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
              "boardId": this.usedId,
              "boardDivision": 2,
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
                      this.$router.push({ path: '/used/UsedDetail/' + this.usedId, });
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
    }
  },
  //created-페이지 열자마자 실행
  created() {
    //클릭함수로 가져온 번호기반으로 게시글 전체 단건조회 메소드 실행
    //메퍼.xml에 where= used_id {} --->>> 메퍼.xml
    //여기다 함수 실행. 무슨 방식으로 가져올건지(put.get./// + url (/selectone),
    // data L) 
    //내용조회
    const component = this;
    fetch('http://13.125.95.210:85/java/used/usedDetail/' + this.usedId)
      .then(Response => Response.json())  //json 파싱 
      .then(data => {
        console.log(data)
        component.usedList = data;

        fetch('http://13.125.95.210:85/java/profile/' + component.usedList.email)
          .then(result => result.json())
          .then(result => {
            this.storedProfile = result;
          }).catch(err => console.log(err));
      }).catch(err => console.log(err))


    fetch('http://13.125.95.210:85/java/used/usedImage/' + this.usedId)
      .then(result => result.json())
      .then(result => {
        this.images = result;
      })
      .catch(err => console.log(err))

    // fetch('http://13.125.95.210:85/java/profile/' + component.usedList.email)
    //   .then(result => result.json())
    //   .then(result => {
    //     this.storedProfile = result;
    //   }).catch(err => console.log(err));

    if (this.$store.state.email != null) {
      fetch('http://13.125.95.210:85/java/save?boardId=' + this.usedId + '&email=' + this.$store.state.email + '&boardDivision=' + 2)
        .then(result => result.text())
        .then(result => {
          if (result == 'true') {
            this.liked = false;
            console.log(this.liked);
            console.log('좋아요 저장내역 = ' + result);
          }
        })
        .catch(err => console.log(err));
    }

    //좋아요count
    fetch('http://13.125.95.210:85/java/used/updateLike/' + this.usedId)
      .then(result => result.json())
      .then(result => {
        this.likeCnt = result;
      })
      .catch(err => console.log(err))
  },
}

</script>












<style scoped src="@/assets/css/used/UsedDetail.css" />
