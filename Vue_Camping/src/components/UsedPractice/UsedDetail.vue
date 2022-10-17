<template>
  <div id="container">
    <form id="container2">
        <div class="used-heads">
          <!-- 상품사진 -->
          <div class="used-pic">
            <div v-for="usedImage of images"><img :src="'http://localhost:8087/java/used/showImage/'+usedImage.usedPath+'/'+usedImage.usedStoredName"></div>
          </div>
          <!-- 상품명, 가격 -->
          <div class="used-info">
            <ul>
              <div>
              <!-- <li class="usedStatus"><img v-bind:src="usedStatus"></li> -->
              </div>
              <div class="used-flex">
                <div class="used-name">
                  <li><h2>{{usedList.usedName}}</h2></li>
                </div>
                <div class="used-status">
                  <li><div v-if="usedList.dealStatus==0" class="dealGreen">거래가능</div>
                      <div v-if="usedList.dealStatus==1" class="dealRed">거래중</div>
                      <div v-if="usedList.dealStatus==2" class="dealGray">거래완료</div></li>
                </div>  
              </div>
              <li><h3>￦{{usedList.usedPrice}}</h3></li>
              <hr>
              <!-- 좋아요, 조회수, 신고 -->
              <div class="used-info2">
                <div class="used-info3">
                <div class="used-cnt">
                  ❤ {{usedList.usedLike}} 👁‍🗨 {{usedList.usedCnt}}
                </div>
                <div class="used-report">
                  <!-- 신고기능가져오기 -->
                  <p v-if="usedList.usedWriter != memberId" @click="report()">신고하기</p>
                  <div v-if="usedList.usedWriter === memberId">
                    <select name="dealStatus">
                    <option value='' disabled selected>거래상태 변경</option>
                    <option value="거래가능">거래가능</option>
                    <option value="거래중">거래중</option>
                    <option value="거래완료">거래완료</option>
                  </select>
                  </div>
                </div>
              </div>
              <!-- 상품정보2 -->
                  <li>
                    🧾카테고리 : {{usedList.usedCategory}}
                  </li>
                  <li>🎭상태 : <span v-if="usedList.usedCondition==0">상</span>
                             <span v-if="usedList.usedCondition==1">중</span>
                             <span v-if="usedList.usedCondition==2">하</span>
                  </li>
                  <li>
                    🚩거래지역 : {{usedList.usedPlace}}
                  </li>
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
          <div class="vertical_line"></div>
          <!-- 작성자 정보-->
          <div class="used-writer">
                  <img v-bind:src="usedPic">
                  {{usedList.nickName}}
            <div class="used-writer-post">
              <!-- 올린게시물정보(코드써야함) -->
            </div>
          </div>
          <div class="info-buttons">
                  <button type="button" class="like-button" v-if="usedList.usedWriter != memberId">찜하기</button>
                  <button type="button" class="chat-button" v-if="usedList.usedWriter != memberId">채팅하기</button>
                  <button type="button" class="update-button" v-if="usedList.usedWriter === memberId" @click="usedUpdate()">수정하기</button>
                  <button type="button" class="delete-button" v-if="usedList.usedWriter === memberId" @click="usedDelete()">삭제하기</button>
                  <button type="button" class="restrict-button" @click="usedRestrict()" v-if="memberId === 'admin'">접근제한</button>       
          </div>
        </div>
      </form>
  </div>
</template>
<script>
  import img1 from "@/assets/img/sns/snsControll.png";
  import Swal from 'sweetalert2';


  export default {
    data(){
      return{
        memberId : this.$store.state.email,
        usedList : [],
        images : [], 
        usedId : this.$route.params.usedId,
        usedStatus: img1
      }
    },
    methods: {
      upload : function(){
        const picUpload = document.querySelector('.pic-upload');
        const upload = document.querySelector('.uploadarea');
        picUpload.click();
      },
      confirm: function(){
        let fetchData = {};
            new FormData(document.querySelector('#container2')).forEach((value,key) => fetchData[key]=value);
            console.log(fetchData);
      },
      usedUpdate: function(){
        this.$router.push({name : 'usedUpdate'})
      },
      usedDelete: function(){
        let fetchData = {};
        fetchData["usedId"] = this.usedId;
        console.log(fetchData)
        // 삭제하는메서드..?
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
            fetch('http://localhost:8087/java/used/statusUpdate',{
                    method : "PUT",
                    headers : {"Content-Type" : "application/json"},
                    body : JSON.stringify(fetchData)
                }) 
                .then(Response => Response.json())  //json 파싱 
                .then(data => { 
                    console.log(data)

                }).catch(err=>console.log(err))
                .then(Swal.fire('삭제되었습니다'))
                .finally(this.$router.push({name : 'usedMain'}))
            }
         }
        );
      },
      usedRestrict: function(){
        let fetchData = {};
        fetchData["usedId"] = this.usedId;
        console.log(fetchData)
        // 삭제하는메서드..?
        Swal.fire({
          title: '',
          text: '이 게시물에 접근 제한을 설정하시겠습니까?',
          icon: 'warning',
          
          confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
          cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
          confirmButtonText: '네', // confirm 버튼 텍스트 지정
          cancelButtonText: '아니오', // cancel 버튼 텍스트 지정
          
          reverseButtons: true, // 버튼 순서 거꾸로
          
        }).then(result => {
          // 만약 Promise리턴을 받으면,
          if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
            fetch('http://localhost:8087/java/used/statusRestrict',{
                    method : "PUT",
                    headers : {"Content-Type" : "application/json"},
                    body : JSON.stringify(fetchData)
                }) 
                .then(Response => Response.json())  //json 파싱 
                .then(data => { 
                    console.log(data)

                }).catch(err=>console.log(err))
                .then(Swal.fire('접근제한 설정이 완료되었습니다'))
            }
         }
        );
      },
      report : function(){
        //신고창띄우기
      }
    },
    //created-페이지 열자마자 실행
    created(){
      //클릭함수로 가져온 번호기반으로 게시글 전체 단건조회 메소드 실행
      //메퍼.xml에 where= used_id {} --->>> 메퍼.xml
      //여기다 함수 실행. 무슨 방식으로 가져올건지(put.get./// + url (/selectone),
      // data L) 
      //내용조회
      fetch('http://localhost:8087/java/used/usedDetail/'+this.usedId) 
                .then(Response => Response.json())  //json 파싱 
                .then(data => { 
                    console.log(data)
                    this.usedList = data;
                }).catch(err=>console.log(err))

      fetch('http://localhost:8087/java/used/usedImage/'+this.usedId)
        .then(result => result.json())
        .then(result => {
            this.images = result;
        })
        .catch(err => console.log(err))
    }
  }
</script>
<style scoped src="@/assets/css/used/UsedDetail.css">

</style>
<!-- 사진 슬라이드. 판매자 게시물 출력,찜버튼/채팅버튼 -->