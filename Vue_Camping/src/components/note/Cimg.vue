<template>
  <div class='used-insert-image-preview'>
          <ImagePreview :images="images" :isNotList="false" @deleteImages="deleteImages"></ImagePreview>
  </div>
  <div class="camp-register-image-form" encrypt="multipart/form-data" style="padding:20px;">
    <label>사진등록
      <input @change="changeImage($event)" @dragenter.prevent @dragover.prevent
        @drop.prevent="dropImage($event)" type="file" multiple style="display:none;">
    </label>
  </div>
  <div class="show_img" v-for="(info,i) in storedImages" :key="i">
    <img :src="'http://localhost:8087/java/GoMyNote/' +info.imgPath+'/'+info.storedName">
  </div>
</template>

<script>
import ImagePreview from '../ImagePreview.vue';
export default {
  props : 'storedImages',

  data(){ 
    return{ 
      images: [],
      imagesUrl: [],
    }
  },
  created() { 
    console.log(storedImages);
  },
  components: {
    ImagePreview
  },
  methods: { 
    deleteImages(updatedImages) {
        this.images = updatedImages;
        document.querySelector('.used-insert-image-container input[type="file"]').files = updatedImages;
    },
    changeImage(e) {
        console.log(e.target);
        let dt = new DataTransfer();
        for (let i = 0; i < e.target.files.length; i++) {
          dt.items.add(e.target.files[i]);
        }
        this.images = dt.files;
        e.target.files = dt.files;
        console.log("여기요")
      },
  }

}
</script>

<style>
</style>