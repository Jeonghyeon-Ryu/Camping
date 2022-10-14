<template>
  <div id="container">
    <form id="container2">
        <div class="used-heads">
          <!-- 상품사진 -->
          <div class="used-pic">
                <img v-bind:src="usedPic">
          </div>
          <!-- 상품명, 가격 -->
          <div class="used-info">
            <ul>
              <li><h2>{{usedList.usedName}}</h2></li>
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
                  <p @click="report()">신고하기</p>
                </div>
              </div>
              <!-- 상품정보2 -->
                  <li>
                    카테고리 : {{usedList.usedCategory}}
                  </li>
                  <li>상태 : {{usedList.usedCondition}}</li>
                  <li>
                    거래지역 : {{usedList.usedPlace}}
                  </li>
                  <select name="dealStatus">
                    <option value='' disabled selected>거래상태</option>
                    <option value="거래가능">거래가능</option>
                    <option value="거래중">거래중</option>
                    <option value="거래완료">거래완료</option>
                  </select>
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
                  {{usedList.usedWriter}}
            <div class="used-writer-post">
              <!-- 올린게시물정보(코드써야함) -->
            </div>
          </div>
          <div class="info-buttons">
                  <button type="button" class="like-button">찜하기</button>
                  <button type="button" class="chat-button">채팅하기</button>
                  <button type="button" class="update-button" @click="usedUpdate()">수정하기</button>
                  <button type="button" class="delete-button" @click="usedDelete()">삭제하기</button>
                  <button type="button" class="restrict-button" >접근제한</button>       
          </div>
        </div>
      </form>
  </div>
</template>
<script>
  import img1 from "@/assets/img/bg9.jpg"

  export default {
    data(){
      return{
        usedList : [],    
        usedId : this.$route.params.usedId
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
        // 삭제하는메서드..?
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
    }
  }
</script>
<style scoped src="@/assets/css/used/UsedDetail.css">

</style>
<!-- 사진 슬라이드. 판매자 게시물 출력,찜버튼/채팅버튼 -->