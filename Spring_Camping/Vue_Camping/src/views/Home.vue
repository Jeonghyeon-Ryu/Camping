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
      <NoteBanner></NoteBanner>
    </div>
    <MainArticle></MainArticle>
  </div>
</template>


<script setup>
import MainArticle from '@/components/Main/MainArticle.vue';
import MainCamp from '@/components/Main/MainCamp.vue';
import MainSearch from '@/components/Main/MainSearch.vue';
import NoteBanner from '@/components/Main/NoteBanner.vue';
import MainSwiper from '../components/Main/MainSwiper.vue';
</script>
<style scoped>
.container {
  position: relative;
  height: 2100px;
}

.search-container {
  width: 800px;
  min-width: 500px;
  position: absolute;
  top: 200px;
  left: calc(50% - 400px);
  z-index: 1005;
}

.search-camp {
  position: absolute;
  width: 100%;
  top: 500px;
  z-index: 2010;
}

@media screen and (max-width:767px) {
  .note {
    position: absolute;
    width: 100%;
    height: 70px;
    top: calc(120vh - 70px);
    z-index: 1020;
  }
}

@media screen and (min-width:768px) and (max-width:991px) {
  .note {
    position: absolute;
    width: 100%;
    height: 90px;
    top: calc(120vh - 90px);
    z-index: 1020;
  }
}

@media screen and (min-width:992px) and (max-width:1200px) {
  .note {
    position: absolute;
    width: 100%;
    height: 120px;
    top: calc(120vh - 120px);
    z-index: 1020;
  }
}

@media screen and (min-width:1201px) {
  .note {
    position: absolute;
    width: 100%;
    height: 150px;
    top: calc(120vh - 150px);
    z-index: 1020;
  }
}
</style>
