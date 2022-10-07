<template>
  <div class="deposit-progress">
    <div class="progressName-box container">
      <ul class="progressName">
        <li v-bind:class="progressName1"><span>모집완료</span></li>
        <li v-bind:class="progressName2"><span>입금확인</span></li>
        <li v-bind:class="progressName3"><span>반환동의</span></li>
        <li v-bind:class="progressName4"><span>입금확인</span></li>
      </ul>
    </div>
    <div class="container">
      <ul class="deposit-progressbar">
        <li v-bind:class="depositLv2"></li>
        <li v-bind:class="depositLv3">
          <button v-if="!isPayed && depositLv==2" @click="sendMoney">입금</button>
          <!-- 모달 -->
          <div v-show="is_show">
            <SendMoney></SendMoney>
          </div>
          <p v-if="isPayed && depositLv==2">여행준비 중..</p>  
        </li>
        <li v-bind:class="depositLv4">
          <button v-if="depositLv==3">확인</button> 
          <button v-if="depositLv==3">노쇼</button>
        </li>
        <li></li>
      </ul>
    </div>
  </div>
</template>
<script>
import EntryPost from '@/assets/rectuitInfo/EntryPost.js';
import SendMoney from './SendMoney.vue';

export default {
  props : {entryId : ''},
  components : {
    EntryPost,
    SendMoney,
    SendMoney
},
  data () {
    return {
      depositLv :'2',
      isPayed : true,
      progressName1 : 'gray',
      progressName2 : 'gray',
      progressName3 : 'gray',
      progressName4 : 'gray',
      depositLv1 : '',
      depositLv2 : '',
      depositLv3 : '',
      depositLv4 : '',
      openSendMoney : false,
      is_show : false
    }
  },
  mounted(){
    switch(this.depositLv) {
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
  },
  methods :{
    sendMoney(){
      this.is_show = !is_show;
    }
  }
}
</script>

<style scoped>
  .deposit-progress{
    width: 100%;
    height: 120px;
    min-width: 300px;
    margin: auto 0;
  }
  .container{
  width: 100%;
  z-index: 1;
}
.gray{
  color: lightgray;
}
.progressName-box{
  height:20px;
}
.progressName li{
  float: left;
  width: 25%;
  font-weight: bold;
  position: relative;
  text-align: center;
  margin: 0;
}
.deposit-progressbar li{
  float: left;
  width: 25%;
  position: relative;
  text-align: center;
}
.deposit-progressbar{
  margin-top: 10px;
  list-style: none;
  counter-reset: step;
}
/* 숫자 */
.deposit-progressbar li:before{
  content:"1";
  width: 30px;
  height: 30px;
  border: 2px solid #bebebe;
  display: block;
  margin: 0 auto 10px auto;
  border-radius: 50%;
  line-height: 27px;
  background: white;
  color: #bebebe;
  text-align: center;
  font-weight: bold;
}
.deposit-progressbar li:before{
  content:counter(step);
  counter-increment: step;
  width: 30px;
  height: 30px;
  border: 2px solid #bebebe;
  display: block;
  margin: 0 auto 10px auto;
  border-radius: 50%;
  line-height: 27px;
  background: white;
  color: #bebebe;
  text-align: center;
  font-weight: bold;
}
/* 라인 */
.deposit-progressbar li:after{
  content: '';
  position: absolute;
  width:100%;
  height: 3px;
  background: #979797;
  top: 15px;
  left: -50%;
  z-index: -1;
}
.deposit-progressbar li:first-child:after{
content: none;
}
/* 진행중표시 */
.deposit-progressbar li:first-child:before{
  border-color: #3aac5d;
  background: #3aac5d;
  color: white
}
.deposit-progressbar li.active + li:before{
border-color: #3aac5d;
background: #3aac5d;
color: white
}
.deposit-progressbar li.active + li:after{
 background: #3aac5d;
}
.active span{
  color: #3aac5d;
  font-size: large;
}

</style>