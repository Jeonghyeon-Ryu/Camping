<template>
  <div class="container">
    <div class="swiper-container">
      <MainSwiper></MainSwiper>
    </div>
    <div class="search-container">
      <MainSearch></MainSearch>
    </div>
    <div class="search-camp">
      <MainCamp></MainCamp>
    </div>
    <div class="note">
      <img src="@/assets/img/main-note.jpg">
    </div>
  </div>
</template>


<script setup>
import MainCamp from '@/components/Main/MainCamp.vue';
import MainSearch from '@/components/Main/MainSearch.vue';
import MainSwiper from '../components/Main/MainSwiper.vue';
</script>
<style scoped>
  .container{
    position: relative;
  }
  .search-container{
    width: 800px;
    min-width: 500px;
    position: absolute;
    top: 100px;
    left:calc(50% - 400px);
    z-index: 1005;
  }
  .search-camp{
    position: relative;
    top: -450px;
    z-index: 1008;
  }
  .note{
    position: relative;
    top: -400px;
  }
  .note img{
    width: 100%;
  }
</style>
