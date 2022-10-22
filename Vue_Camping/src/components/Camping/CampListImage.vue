<template>
    <swiper :autoplay="{ delay: 2500, disableOnInteraction: false, }" :modules="modules" class="mySwiper">
        <swiper-slide v-for="image of images"><img
                :src="'http://localhost:8087/java/showImage/'+image.imagePath+'/'+image.storedName" /></swiper-slide>
        <swiper-slide v-for="img of imgUrl"><img :src="img" /></swiper-slide>
        <swiper-slide v-if="images.length==0"><img
                :src="'http://localhost:8087/java/showImage/default/default.png'" /></swiper-slide>
        </swiper>
</template>
<script>
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue";

// Import Swiper styles
import "swiper/css";
import "swiper/css/pagination";

// import required modules
import { Autoplay } from "swiper";

export default {
    props: ['campId', 'addImage'],
    data: function () {
        return {
            images: [],
            imgUrl: []
        }
    },
    created: function () {
        fetch('http://localhost:8087/java/campImage/' + this.campId)
            .then(result => result.json())
            .then(result => {
                this.images = result;
                this.resultImages = result;
            })
            .catch(err => console.log(err))
    },
    components: {
        Swiper,
        SwiperSlide,
    },
    setup() {
        return {
            modules: [ Autoplay ],
        };
    },
    watch: {
        addImage() {
            this.imgUrl = [];
            for (let img of this.addImage) {
                this.imgUrl.push(URL.createObjectURL(img));
            }
            console.log(this.imgUrl)
        }
    }
};
</script>

<style scoped>
.swiper {
    width: 100%;
    height: 100%;
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

.swiper-button-next,
.swiper-button-prev,
.swiper-container-rtl .swiper-button-prev,
.swiper-container-rtl .swiper-button-next {
    color: #F7EDDA;
}

.swiper-pagination {
    top: 10px;
}

.swiper-pagination-bullet {
    background: #F7EDDA;
}

.swiper-pagination-bullet-active {
    background: #F7EDDA;
}
</style>