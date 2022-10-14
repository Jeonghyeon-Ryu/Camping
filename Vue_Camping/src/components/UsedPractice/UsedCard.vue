<template>
  <!-- 카드 -->
  <div v-on:click='cardDetail()' class="card">
              <!-- 카드 좋아요 -->
              <div class="like">
              <img v-if="liked" v-on:click='hearted()' v-bind:src="heartImg">
              <img v-if="!liked" v-on:click="hearted()" v-bind:src="heartImg2">
            </div>
            <!-- 카드사진 -->
            <div class="card-pic">
              <img v-bind:src="cardImg">
            </div>
            <!-- 카드정보 -->
            <div class="card-info">
                <div class="card-top">
                <ul class="card-info-l">
                  <li><h3>{{usedCard.usedName}}</h3></li>
                  <li><h4><span class="font-gray">￦ {{usedCard.usedPrice}}</span></h4></li>
                </ul>
                <ul class="card-info-r">
                  <li><div v-if="usedCard.dealStatus==0" class="dealGreen">거래가능</div>
                      <div v-if="usedCard.dealStatus==1" class="dealRed">거래중</div>
                      <div v-if="usedCard.dealStatus==2" class="dealGray">거래완료</div></li>
                </ul>      
                </div>
                <div class="card-bottom">
                  <div class="card-place">{{usedCard.usedPlace}}</div>
                  <div class="card-write"><span class="font-gray">{{usedCard.usedWrite}}</span></div>
              </div>
            </div>
          </div>
</template>
<script>
  import img1 from "@/assets/img/bg9.jpg"
  import img2 from "@/assets/img/used/heart.png"
  import img3 from "@/assets/img/used/heart2.png"

  export default{
    name: "UsedCard",
    props : {usedCard : Object},
    data(){
      return{
        cardImg : img1,
        heartImg : img2,
        heartImg2 : img3,
        liked: true,
        usedId : this.$route.params.usedId
      }
    },
    props: {
      usedCard : Object
    },  
    methods : {
       cardDetail: function(){
         this.$router.push({name : 'usedDetail'})
       },
       hearted: function(){
          this.liked = !this.liked
       }
     }
  }
</script>
<style scoped src="@/assets/css/used/UsedMain.css">
</style>