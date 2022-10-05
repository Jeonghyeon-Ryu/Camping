<template>
  <div v-on:click='cardDetail()' class="card">
            <div class="card-pic">
              <img v-bind:src="cardImg">
            </div>
            <div class="card-info">
                <ul class="info-left">
                  <li>{{usedCard.used_name}}</li>
                  <li>{{usedCard.used_place}}</li>
                </ul>
                <ul class="info=right">
                  <li>{{usedCard.used_price}}</li>
                  <li>{{usedCard.used_status}}</li>
                </ul>
            </div>
          </div>
</template>
<script>
  import img1 from "@/assets/img/bg9.jpg"

  export default{
    data(){
      return{
        cardImg : img1
      }
    },
    props: {
      usedCard : Object
    },  
    methods : {
       cardDetail: function(){
         this.$router.push({name : 'usedDetail'})
       }
     }
  }
</script>
<style scoped src="@/assets/css/UsedMain.css">
  
</style>