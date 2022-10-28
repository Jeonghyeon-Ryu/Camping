<template>
    <swiper :navigation="true" :pagination="{clickable: true,}" :modules="modules" class="mySwiper">    
            <swiper-slide v-for="image of images">
                <img :src="'http://13.125.95.210:85/java/recruImg/'+image.imgPath+'/'+image.storedName"/>
            </swiper-slide>
            <swiper-slide v-for="img of imgUrl">
                <img :src="img"/>
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
    props: ['recruId','addImage'],
    data : function() {
        return {
            images : [],
            imgUrl : []
        }
    },
    created : function() {
        const recruId = this.recruId;
        const component=this;
        fetch('http://13.125.95.210:85/java/recruImg/'+recruId)
        .then(result => result.json())
        .then(result => {
            component.images = result;
            component.resultImages = result;
            console.log(result)
        })
        .catch(err => console.log(err))
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
    watch: {
        addImage() {
            this.imgUrl = [];
            for(let img of this.addImage){
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
    /* -webkit-align-items: center;
    align-items: center; */
}

.swiper-slide img {
    display: block;
    width: 100%;
    height: auto;
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