<template>
<!-- 카드 -->
<div class="recru-card">
  <!-- 카드 헤더 -->
  <div class="recru-card-header" >
      <div class="card-header-img">
          <img src="require(`@/assets/img/{{recruCard.filename}}`)" alt="camping gear">
      </div>
      <div class = "card-header-text" > 모집중 </div >
  </div>
  <!--  카드 바디 -->
  
  <div class="recru-card-body">
    <div class="card-body-title">
      <h1>{{recruCard.title}}</h1>
    </div>
    <!--  카드 바디 내용 -->
    <p class="card-body-my"><span>갖고있어요 :</span>{{recruCard.my_gear}} </p>
    <p class="card-body-need"><span>필요해요 :</span>{{recruCard.need_gear}}</p>
    <hr style="margin:5px;">
    <p class="card-body-go"><span>출발 :</span>{{recruCard.go_date}}</p>
    <p class="card-body-spot"><span>도착지 :</span> {{recruCard.camping_point}}</p>
  </div>
  <div class = "recru-card-wish" > 
    <img v-if="isHeart" src='../assets/img/heart.png' @click="changeHeart" alt="찜한 카드">
    <img v-if="!isHeart" src='../assets/img/noheart.png' @click="changeHeart" alt="찜한 카드">
  </div>
</div>  
</template>

<script>
export default{
    name : "RecruList",
    // props : [recruCard],
    data : function(){
      return {
        isHeart : true,
        myHeart : require('../assets/img/heart.png'),
        recruCard :{
            filename:'',
            my_gear:'',
            need_gear:'',
            go_date:'',
            camping_point:''
        },
        myHeart : 'heart.png'
      }
    },
    methods: {
      changeHeart : function(){
        this.isHeart=!this.isHeart;
      }
    }
}
</script>

<style scoped>
/* 공통 부분 */
* {
    margin: 0;
    padding: 0;
    list-style: none;
    font-style: none;
    box-sizing: border-box;
  }

  /* 카드설정 - 반응형 미디어쿼리 */
  @media(min-width:550px) {
    .recru-card {
      /* max min */
      height: 300px;
      width: 20vw;
      min-width: 240px;
      border-radius: 15px;
      margin : 10px;
      box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
      overflow: hidden;
    }
  }
  @media (max-width:549px) {
    .recru-card {
      /* max min */
      height: 300px;
      width: 70vw;
      min-width: 220px;
      border-radius: 15px;
      margin : 10px;
      box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
      overflow: hidden;
      
    }
  }
  
  /* 카드 헤더 */
  .recru-card-header {
    width: 100%;
    height: 70%;
    border-radius: 15px 15px 0 0;
    display: flex;
    justify-content: space-between;
    
  }
  .card-header-img{
    width: 70%;
  }
  /* 카드 헤더 이미지 */
  .card-header-img img{
    height: 320px;
    min-width: 100%;
  }
  .recru-card:hover .recru-card-header{
    opacity: 0.2;
    height: 0;
    transition: all 0.5s;
    -webkit-transition: all 0.5s;
  }
  /* 카드 헤더 글씨 - 모집상태 */
  .card-header-text{
    font-weight: bold;
    margin: 10px 10px;
    min-width: 50px;
    color: greenyellow;
  } 
  .recru-card:hover .card-header-text{
    display: none;
  }
  /* 카드 바디 - 준상세 */
  .recru-card-body{
    padding: 10px;
    margin-top: 10px;
    height: 85%;
    transition: all 0.3s;
  }
  .recru-card-body span{
    color: darkred;
  }
  .recru-card-body h1 {
    color: white;
    font-size: 22px;
    font-weight: bold;
  }
  .recru-card-body p{
    font-weight: bold;
    visibility: hidden;
    margin: 0;
    font-size: 14px;
  }
  .recru-card:hover .recru-card-body h1, .recru-card:hover .recru-card-body  p{
    color: black;
    visibility: visible;
  }
  
  .card-body-my, .card-body-need{
    max-height: 43.2px;
    overflow: hidden;
  }        
  /* 카드 저장 아이콘 */
  .recru-card-wish{
    position: relative;
    left: 45%;
    margin-bottom:5px;
  }
  .recru-card-wish:hover{
    color: crimson;
  }
  .recru-card-wish img{
    width: 30px;
  }
</style>