<template>
  <div class="main-container">
    <h3>추천 캠핑장</h3>
    <div class="camp-card-container">
      <VerticalCard></VerticalCard>
    </div>
  </div>
</template>
<script>
import VerticalCard from '../Card/VerticalCard.vue';


export default {
    data: function () {
        return {
            mainCampData: []
        };
    },
    created: function () {
      fetch('http://localhost:8087/java/camp/'+1)
      .then(result=>result.json())
      .then(result => {
        console.log(result)
      })
    },
    components: { VerticalCard }
};
</script>
<style scoped>
.main-container {
  width:100%;
  padding:1rem;
}
.camp-card-container {
  width:100%;
}
</style>