<template>
  <div id="container">
    <form id="container2">
        <div class="used-heads">
            <div class="used-pic">
                <img v-bind:src="used_pic">
            </div>
          <div class="used-info">
            <ul>
              <li><h2>{{used_name}}</h2></li>
              <li><h3>￦{{used_price}}</h3></li>
              <hr>
              <div class="used-info2">
                <div class="used-info3">
                  <li>
                    카테고리 : {{myGearType}}
                  </li>
                  <li>상태 : {{used_sangtae}}</li>
                  <li>
                    거래지역 : {{used_place}}
                  </li>
                </div>
                <div class="used-report">
                  <!-- 신고기능가져오기 -->
                  <p @click="report()">신고하기</p>
                </div>
              </div>
            </ul>
          </div>
        </div>
        <hr class="horizontal_line">
        <div class="used-foot">
          <div class="used-desc">
            <p><b>상품 설명</b></p>
            <hr class="horizontal-line">
            <div class="used-content">
              {{used_content}}
            </div>
          </div>
          <div class="vertical_line"></div>
          <div class="used-writer">
                  <img v-bind:src="used_pic">
                  {{used_writer}}
                </div>
          <div class="info-buttons">
                  <button type="button" class="like-button">찜하기</button>
                  <button type="button" class="chat-button">채팅하기</button>
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
        used_pic : img1,
        used_writer : 'campingGo',
        myGearType : '텐트',
        used_name : '4인용 텐트',
        used_price : '100,000',
        used_sangtae : '상',
        used_place : '대구광역시 서구 내당동',
        used_content : '1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ 1회 사용했습니다. 사용감 없고 깨끗해요 ! 오후 6시 이후 두류역이나 중앙로역에서 직거래 가능합니다. 택배거래도 가능하니 편하게 채팅 주세요 ~ '    
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
      }
    }
  }
</script>
<style scoped src="@/assets/css/used/UsedDetail.css">

</style>
<!-- 사진 슬라이드. 판매자 게시물 출력,찜버튼/채팅버튼 -->