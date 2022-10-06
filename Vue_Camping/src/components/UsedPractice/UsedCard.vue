<template>
  <div v-on:click='cardDetail()' class="card">
            <div class="like">
              <img v-on:click='hearted()' v-bind:src="heartImg">
            </div>
            <div class="card-pic">
              <img v-bind:src="cardImg">
            </div>
            <div class="card-info">
                <ul class="info-left">
                  <li>{{usedCard.used_name}}</li>
                  <li>{{usedCard.used_place}}</li>
                </ul>
                <ul class="info-right">
                  <li>{{usedCard.used_price}}</li>
                  <li>{{usedCard.used_status}}</li>
                </ul>
            </div>
          </div>
</template>
<script>
  import img1 from "@/assets/img/dg3.jpg"
  import img2 from "@/assets/img/heart.png"

  export default{
    data(){
      return{
        cardImg : img1,
        heartImg : img2
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

       }
     }
  }
</script>
<style scoped src="@/assets/css/UsedMain.css">
  
</style>