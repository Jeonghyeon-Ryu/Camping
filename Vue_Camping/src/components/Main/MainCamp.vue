<template>
  <swiper :pagination="true" :navigation="true"
    :modules="modules" class="mySwiper">
    <swiper-slide><img src="@/assets/img/main-image/main_1.jpg" /></swiper-slide>
    <swiper-slide><img src="@/assets/img/main-image/main_2.jpg" /></swiper-slide>
    <swiper-slide><img src="@/assets/img/main-image/main_3.jpg" /></swiper-slide>
    <swiper-slide><img src="@/assets/img/main-image/main_4.jpg" /></swiper-slide>
    <swiper-slide><img src="@/assets/img/main-image/main_5.jpg" /></swiper-slide>
  </swiper>
</template>
<script>
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue";
// Import Swiper styles
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
// import required modules
import { Navigation, Pagination } from "swiper";

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  data: function() {
    return {
      mainCampData: []
    }
  },
  created: function(){
    fetch('')
  },  
  setup() {
    return {
      modules: [Navigation, Pagination],
    };
  },
};
</script>
  
<style scoped src="@/assets/css/Main/MainSwiper.css">

</style>