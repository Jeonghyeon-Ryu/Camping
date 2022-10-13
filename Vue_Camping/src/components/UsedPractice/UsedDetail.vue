<template>
  <div id="container">
    <form id="container2">
        <div class="used-heads">
          <!-- 상품사진 -->
          <div class="used-pic">
                <img v-bind:src="usedPic">
          </div>
          <!-- 우측 상품정보 -->
          <div class="used-info">
            <ul>
              <li><h2>{{usedName}}</h2></li>
              <li><h3>￦{{usedPrice}}</h3></li>
              <hr>
              <!-- 좋아요, 조회수, 신고 -->
              <div class="used-info2">
                <div class="used-info3">
                <div class="used-cnt">
                  ❤ {{usedLike}} 👁‍🗨 {{usedCnt}}
                </div>
                <div class="used-report">
                  <!-- 신고기능가져오기 -->
                  <p @click="report()">신고하기</p>
                </div>
              </div>
              <!-- 상품정보2 -->
                  <li>
                    카테고리 : {{usedCategory}}
                  </li>
                  <li>상태 : {{usedCondition}}</li>
                  <li>
                    거래지역 : {{usedPlace}}
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
              {{usedContent}}
            </div>
          </div>
          <div class="vertical_line"></div>
          <!-- 작성자 정보-->
          <div class="used-writer">
                  <img v-bind:src="used_pic">
                  {{usedWriter}}
            <div class="used-writer-post">
              <!-- 올린게시물정보(코드써야함) -->
            </div>
          </div>
          <div class="info-buttons">
                  <button type="button" class="like-button">찜하기</button>
                  <button type="button" class="chat-button">채팅하기</button>
                  <button type="button" class="delete-button" v-if="usedWriter==1" @click="usedDelete()">삭제하기</button>
                  <button type="button" class="restrict-button" v-if="usedWriter==0">접근제한</button>       
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
        usedLike: '6',
        usedCnt: '30',
        usedPic : img1,
        usedWriter : 'campingGo',
        usedCategory : '텐트',
        usedName : '4인용 텐트',
        usedPrice : '100,000',
        usedCondition : '상',
        usedPlace : '대구광역시 서구 내당동',
        usedContent : '1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ '    
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
      usedDelete: function(){
        // 삭제하는메서드..?
      }
    },
    //created-페이지 열자마자 실행
    created(){
      //내용조회
      fetch('http://localhost:8088/java/used/usedDetail') 
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