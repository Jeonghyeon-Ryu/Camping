<template>
<!-- 카드 -->
<div class="recru-card">
  <!-- 카드 헤더 -->
  <div class="recru-card-header" >
      <div class="card-header-img">
        <div v-if="image==''">
          <img src="@/assets/img/bg9.jpg" alt="camping gear">
        </div>
        <div v-if="image">
          <img :src="'http://localhost:8087/java/recruImg/'+image.imgPath+'/'+image.storedName"/>
        </div>
      </div>
      <div class = "card-header-text" :class="myClass" > {{recruStatus}} </div >
  </div>
 
  <!--  카드 바디 -->
  <div class="recru-card-body">
    <div class="card-body-title">
      <h1>{{recruCard.recruTitle}}</h1>
    </div>
    <!--  카드 바디 내용 -->
    <p class="card-body-my"><span>갖고있어요 :</span>{{recruCard.myGear}} </p>
    <p class="card-body-need"><span>필요해요 :</span>{{recruCard.needGear}}</p>
    <hr style="margin:5px;">
    <p class="card-body-go"><span>출발 :</span>{{recruCard.goDate}}</p>
    <p class="card-body-spot"><span>도착지 :</span> {{recruCard.campingPoint}}</p>
  </div>
  <div class = "recru-card-wish" > 
    <img v-if="isHeart" class="recru-heart" src='@/assets/img/heart.png' @click.stop="changeHeart" alt="찜한 카드">
    <img v-if="!isHeart" class="recru-heart" src='@/assets/img/noheart.png' @click.stop="changeHeart" alt="찜한 카드">
  </div>
</div>  
</template>
<script>
export default{
    name : "RecruCard",
    props : {recruCard : Object},
    data : function(){
      return {
        isHeart : true,
        recruStatus : '',
        myClass : '',
        image : ''
      }
    },
    created (){
      const recruId = this.recruCard.recruId;
      const component = this;
      fetch('http://localhost:8087/java/recruImg/'+recruId)
      .then(result => result.json())
      .then(result => {
        component.image = result[0];
      })
      .catch(err => console.log(err))
    },
    computed : {
      //원본데이터 변화를 감지하면
      //장비 리스트 형태 바꿔주기 (4인용텐트,텐트,3 => 4인용텐트(텐트)3개)
    },
    methods: {
      changeHeart : function(e){
        e.preventDefault();
        this.isHeart=!this.isHeart;
      },
      gearImg : function(){
        // import img from '~~~'해서 바인딩
        return img;
      }
    }
}
</script>

<style scoped src="@/assets/css/recruit/recruCard.css"/>