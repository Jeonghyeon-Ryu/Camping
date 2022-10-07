<template>
  <div>    
      <div>
          <section class="pay-money-container">
              <input type="number" placeholder="금액 입력" v-model="price" >
              <div class="button" @click="PaymentBtn">결제</div>
          </section>
      </div>
  </div>
</template>

<script>
//import IMP from 'vue-iamport';
var IMP = window.IMP;

export default {
  name: "test",
  data() {
      return {
          
      };
  },
  created() {
      document.cookie = "safeCookie1=foo; SameSite=Lax";
      document.cookie = "safeCookie2=foo";
      document.cookie = "crossCookie=bar; SameSite=None; Secure";
  },
  methods: {
      PaymentBtn: function () {
          IMP.init("imp45403724");
          IMP.request_pay({
              pg: "html5_inicis",
              pay_method: "card",
              merchant_uid: "ORD20180131-00011135",
              name: "캠핑갈래 보증금",
              amount: 10000,
              buyer_email: "gildong@gmail.com",
              buyer_name: "홍길동",
              buyer_tel: "010-4242-4242",
              buyer_addr: "서울특별시 강남구 신사동",
              buyer_postcode: "01181"
          }, rsp => {
              console.log(rsp);
              if (rsp.success) {
                  console.log("결제 성공");
              }
              else {
                  console.log("결제 실패");
              }
          });
      }
  },

}
</script>


<style scoped>
.pay-money-container{ display:flex; justify-content: center; align-items: center; height:100vh; }
.button{ width: 200px; height:40px; background-color:#ffffff; border:1px #a8a8a8 solid; color:black; display:flex; align-items: center; justify-content: center; cursor:pointer; }
</style>