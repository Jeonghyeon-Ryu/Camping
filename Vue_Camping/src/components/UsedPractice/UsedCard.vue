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
                <ul>
                  <li><h3>{{usedCard.used_name}}</h3></li>
                  <li>{{usedCard.used_price}}</li>
                  <li><h5>{{usedCard.used_place}}</h5></li>
                  <li>{{usedCard.used_status}}</li>
                </ul>
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
<style scoped src="@/assets/css/UsedMain.css">
</style>