<template>
    <swiper :navigation="true" :pagination="{clickable: true,}" :modules="modules" class="mySwiper">
        <swiper-slide v-for="usedImage of images">
            <img :src="'http://13.125.95.210:85/java/used/showImage/'+usedImage.usedPath+'/'+usedImage.usedStoredName" onclick="window.open(this.src)">
        </swiper-slide>
    </swiper>
</template>
<script>
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue";

// Import Swiper styles
import "swiper/css";
import "swiper/css/pagination";

// import required modules
import { Navigation, Pagination } from "swiper";

export default {
    props: ['usedId'],
    data : function() {
        return {
            images : []
        }
    },
    created : function() {
      fetch('http://13.125.95.210:85/java/used/usedImage/'+this.usedId)
        .then(result => result.json())
        .then(result => {
            this.images = result;
            console.log(result)
        })
        .catch(err => console.log(err))

        console.log('http://13.125.95.210:85/java/used/usedImage/'+this.usedId)
    },  
    components: {
        Swiper,
        SwiperSlide,
    },
    setup() {
        return {
            modules: [Navigation, Pagination],
        };
    },
};
</script>

<style scoped>
.swiper{
    width: 100%;
    height: 100%;
}
.swiper-slide{
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
    align-items:center;
}
.swiper-slide img {
    display: block;
    width: 100%;
    height: 100%;
    object-fit: cover;
}
</style>
<style>
.swiper-button-next,
.swiper-button-prev,
.swiper-container-rtl .swiper-button-prev,
.swiper-container-rtl .swiper-button-next {
    color: #F7EDDA;
    vertical-align: bottom;
}
.swiper-pagination-bullet {
    background: #F7EDDA;
    vertical-align: bottom;
}
.swiper-pagination-bullet-active {
    background: #F7EDDA;
}
</style>