<template>
    <div>    
        <div>
            <section class="pay-money-container">
                <h1>캠핑갈래</h1>
                <P>보증금 반환 계좌 정보</P>
                <input type="text" placeholder="은행명">
                <input type="text" placeholder="계좌번호">
                <input type="text" placeholder="예금주명">
                <p>개인정보 수집 및 동의</p>
                <p>개인 수집 및 동의</p>
                <div class="deposit-">캠핑갈래는 안전한 여행을 위한 보증금 제도를 실시하고 있습니다.
                    보증금은 여행 예정일 이후 상호 동의 하에 전액 반환되며, 
                    노쇼 신고가 들어올 경우 일부 금액이 동행자에게 지급됩니다.<br>
                    보증금 반환을 위해 입력된 개인정보를 수집합니다.
                    수집한 개인정보는 보증금 반환이외의 목적으로는 사용하지 않습니다.
                    개인정보의 수집 및 이용에 대한 동의를 거부할 수 있으며, 
                    이 경우 동행모집 서비스 이용이 어려울 수 있습니다.
                </div>
                <label><input name="agreebtn" type="checkbox">동의합니다.</label>
                <div class="button" @click="PaymentBtn">결제</div>
            </section>
        </div>
    </div>
</template>

<script>
//import IMP from 'vue-iamport';
var IMP = window.IMP;

export default {
    name: "sendMoney",
    data() {
        return {
            pay_method : 'card',
            merchant_uid : '',
            // name : user.name,
            // email : user.email,
            // tel : user.phone,

        };
    },
    created() {
        document.cookie = "safeCookie1=foo; SameSite=Lax";
        document.cookie = "safeCookie2=foo";
        document.cookie = "crossCookie=bar; SameSite=None; Secure";
    },
    methods: {
        PaymentBtn: function () {
            let agreeBtn = document.querySelector('agreebtn').checked
           console.log(agreeBtn)
            IMP.init("imp45403724");
            IMP.request_pay({
                pg: "html5_inicis",
                pay_method: this.pay_method,
                merchant_uid: "00011175",
                name: "캠핑갈래 보증금",
                amount: 100,
                buyer_email: "gildong@gmail.com",
                buyer_name: "홍길동",
                buyer_tel: "010-4242-4242",
                buyer_postcode: "1000"
            }, rsp => {
                console.log(rsp);
                if (rsp.success) {
                Swal.fire('보증금 입금 완료되었습니다','즐거운 여행을 떠납시다','success');
                this.$emit('close-modal');
                }
                else {
                    console.log("결제 실패");
                    this.$emit('close-modal');
                }
            });
        }
    },

}
</script>


<style scoped>
.pay-money-container{   
    height:400px; 
    background: white;
}
.button{ 
    width: 200px; height:40px; 
    background-color:#ffffff; 
    border:1px #a8a8a8 solid; 
    color:black;
    display:flex; 
    align-items: center; 
    justify-content: center; 
    cursor:pointer; 
    }
</style>