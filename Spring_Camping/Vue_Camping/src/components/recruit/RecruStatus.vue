<template>
  <div>
    <div class="recru-status" :class="statusClass">
          <p>{{status}}</p>
      </div>
  </div>
</template>
<script>
export default{
  props : {
    recruStatus : Number
  },
  data:function(){
    return {
      statusClass :''
    }
  },
  computed :{
    status(){
      if (this.recruStatus==0){
        this.statusClass = 'recru_status_green'
        return '모집중'
      }else if(this.recruStatus==2){
        this.statusClass = 'recru_status_gray'
        return '모집취소'  
      }else{
        this.statusClass = 'recru_status_red'
        return '모집완료'
      }
    },
  }
}
</script>
<style scoped>
 /* 게시글 상태 */
 .recru-status{
    margin: 25px 0 20px 10px;
    padding: 3px;
    border-radius: 5px;
    font-weight: bold;
 }
.recru_status_red{
    background: rgba(225, 208, 211, 0.7);
    color: rgb(58, 18, 26);
 } 
 .recru_status_green{
    background: rgba(228,239,231,0.7);
    color: rgb(6,68,32) ;
 }
 .recru_status_gray{
    background: gray;
    color: white;
 }
 p{
  margin: 0;
 }
</style>