<template>
    <swiper :autoplay="{ delay:2000, disableOnInteraction:false }" :navigation="true" :modules="modules" class="mySwiper">
      <swiper-slide><img src="../assets/img/main-image/main_1.jpg"/></swiper-slide>
      <swiper-slide><img src="../assets/img/main-image/main_2.jpg"/></swiper-slide>
      <swiper-slide><img src="../assets/img/main-image/main_3.jpg"/></swiper-slide>
      <swiper-slide><img src="../assets/img/main-image/main_4.jpg"/></swiper-slide
      ><swiper-slide><img src="../assets/img/main-image/main_5.jpg"/></swiper-slide>
    </swiper>
  </template>
  <script>
  // Import Swiper Vue.js components
  import { Swiper, SwiperSlide } from "swiper/vue";
  
  // Import Swiper styles
  import "swiper/css";
  import "swiper/css/navigation";
  
  // import required modules
  import { Autoplay, Navigation } from "swiper";
  
  export default {
    components: {
      Swiper,
      SwiperSlide,
    },
    setup() {
      return {
        modules: [ Autoplay, Navigation ],
      };
    },
  };
  </script>
  
  <style>
    .swiper {
        width: 100vw;
        height: 650px;
    }

    .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;

        /* Center slide text vertically */
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
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