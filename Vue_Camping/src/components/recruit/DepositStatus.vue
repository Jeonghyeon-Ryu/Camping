<template>
  <div class="deposit-progress">
    <div class="progressName-box container">
      <ul class="progressName">
        <li v-bind:class="progressName1"><span>모집완료</span></li>
        <li v-bind:class="progressName2"><span>입금확인</span></li>
        <li v-bind:class="progressName3"><span>반환동의</span></li>
        <li v-bind:class="progressName4"><span>반환확인</span></li>
      </ul>
    </div>
    <div class="container">
      <ul class="deposit-progressbar">
        <li v-bind:class="depositLv2"></li>
        <li v-bind:class="depositLv3">
          
            <button v-if="!isPayed && depositInfo.deposit_status==='2'" @click="sendMoney">입금</button>
            <!-- 모달 -->
            <ModalView v-if="isModalViewed" @close-modal="isModalViewed=false">
              <SendMoney></SendMoney>
            </ModalView>

            <p v-if="isPayed && depositInfo.deposit_status==='2'">여행준비 중</p>  
      
        </li>
        <li v-bind:class="depositLv4">
          <button v-if="depositInfo.deposit_id==='3'">확인</button> 
          <button v-if="depositInfo.deposit_id==='3'">노쇼</button>
        </li>
        <li></li>
      </ul>
    </div>
  </div>
</template>
<script>
import EntryPost from '@/assets/rectuitInfo/EntryPost.js';
import SendMoney from './SendMoney.vue';
import DepositPost from '@/assets/rectuitInfo/DepositPost.js';
import ModalView from './ModalView.vue';

export default {
  props : {depositId : String}, //deposit
  components : {
    EntryPost,
    SendMoney,
    DepositPost,
    ModalView
  },
  data :function() {
    return {
      depositInfo : {},   //deposit vo
      depositStatus : '', //
      isPayed : true,
      progressName1 : 'gray',
      progressName2 : 'gray',
      progressName3 : 'gray',
      progressName4 : 'gray',
      depositLv1 : '',
      depositLv2 : '',
      depositLv3 : '',
      depositLv4 : '',
      isModalViewed : false
    }
  },
  created(){
    const component = this;
    fetch("http://localhost:8087/java/recru/deposit/"+component.memberId)
    .then(Response => Response.json()) 
    .then(data => { 
        component.depositList = data;
    })
  
    
    //보증금 상태에 따라 active 설정을 준다
    switch(this.depositInfo.deposit_status) {
      case '4': 
        this.depositLv4='active';
        this.depositLv3='active';
        this.depositLv2='active';
        this.progressName4='active';
        break;
      case '3':  
        this.depositLv3='active';
        this.depositLv2='active';
        this.progressName3='active';
        break;
      case '2':  
        this.depositLv2='active';
        this.progressName2='active';
        break;
      case '1':  
        this.progressName1='active';
    }
    //보증금 입금 유무에 따라 버튼 상태를 바꾼다
    if(this.depositInfo.in_date ==''){
      this.isPayed =false;
    }else{
      this.isPayed = true;
    }
  },
  methods : {
    sendMoney : function(){
      this.isModalViewed = !this.isModalViewed;
    }
  }
}
</script>

<style scoped src="@/assets/css/recruit/depositStatus.css" />