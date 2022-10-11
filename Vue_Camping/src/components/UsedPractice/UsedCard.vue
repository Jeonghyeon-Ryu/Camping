<template>
  <div v-on:click='cardDetail()' class="card">
            <div class="like">
              <img v-if="liked" v-on:click='hearted()' v-bind:src="heartImg">
              <img v-if="!liked" v-on:click="hearted()" v-bind:src="heartImg2">
            </div>
            <div class="card-pic">
              <img v-bind:src="cardImg">
            </div>
            <div class="card-info">
                <div class="card-top">
                <ul class="card-info-l">
                  <li><h3>{{usedCard.used_name}}</h3></li>
                  <li><h4><span class="font-gray">{{usedCard.used_price}}</span></h4></li>
                </ul>
                <ul class="card-info-r">
                  <li>{{usedCard.used_status}}</li>
                </ul>      
                </div>
                <div class="card-bottom">
                  <div class="card-place">{{usedCard.used_place}}</div>
                  <div class="card-write"><span class="font-gray">{{usedCard.used_write}}</span></div>
              </div>
            </div>
          </div>
</template>
<script>
  import img1 from "@/assets/img/bg9.jpg"
  import img2 from "@/assets/img/used/heart.png"
  import img3 from "@/assets/img/used/heart2.png"

  export default{
    data(){
      return{
        cardImg : img1,
        heartImg : img2,
        heartImg2 : img3,
        liked: true
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