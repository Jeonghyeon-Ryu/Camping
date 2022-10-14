<template>
    <swiper :autoplay="{ delay:2000, disableOnInteraction:false }" :pagination="true" :navigation="true" :modules="modules" class="mySwiper">
      <swiper-slide><img src="@/assets/img/main-image/main_1.jpg"/></swiper-slide>
      <swiper-slide><img src="@/assets/img/main-image/main_2.jpg"/></swiper-slide>
      <swiper-slide><img src="@/assets/img/main-image/main_3.jpg"/></swiper-slide>
      <swiper-slide><img src="@/assets/img/main-image/main_4.jpg"/></swiper-slide
      ><swiper-slide><img src="@/assets/img/main-image/main_5.jpg"/></swiper-slide>
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
  import { Autoplay, Navigation,Pagination } from "swiper";
  
  export default {
    components: {
      Swiper,
      SwiperSlide,
    },
    setup() {
      return {
        modules: [ Autoplay, Navigation, Pagination],
      };
    },
  };
  </script>
  
  <style scoped>
    .swiper {
        width: 100%;
        height: 60vh;
    }

    .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
    }

    .swiper-slide img {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
</style>