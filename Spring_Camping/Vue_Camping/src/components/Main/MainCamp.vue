<template>
  <div class="main-container">
    <!-- <h3>추천 캠핑장</h3> -->
    <div class="camp-card-container">
      <VerticalCard :datas="mainCampData"></VerticalCard>
    </div>
  </div>
</template>
<script>
import VerticalCard from '../Card/VerticalCard.vue';


export default {
    data: function () {
        return {
            mainCampData: '',
        };
    },
    created: function () {
      fetch('http://13.125.95.210:85/java/camp/'+1)
      .then(result=>result.json())
      .then(result => {
        this.mainCampData = result
      })
    },
    components: { VerticalCard }
};
</script>
<style scoped>
h3{
  width: 100%;
  color:rgb(221, 221, 221);
  text-align: left !important; 
  padding-left: 180px;
  display: inline-block;
}
.main-container {
  margin-top:1rem;
  width:100%;
  padding:1rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.camp-card-container {
  width:85%;
  height: 350px;

}
</style>