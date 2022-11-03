<template>
  <div class="write_fn" @mouseenter="show($event)" @mouseleave="hide($event)">
    <div class="left_container">
      <div class="btn_container">
        <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
        <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
      </div>
    </div>
    <div class="img_container">
      <div class='used-insert-image-preview'>
        <div v-if="data.length != 0" class="image-preview-container">
          <div v-for="(temp, index) of data" :id="'a'+index" class="image-preview-div">
            <img :src="'http://13.125.95.210:85/java/GoMyNote/' + temp.imgPath + '/' + temp.storedName" />
            <input type="button" value="X" @click="deleteImage($event)" class="image-preview-delete-button" />
          </div>
          <div v-for="(img, index) of imagesUrl" :id="'b'+index" class="image-preview-div">
            <img :src="img" />
            <input type="button" value="X" @click="deleteImage($event)" class="image-preview-delete-button" />
          </div>
        </div>
      </div>
      <div class="camp-register-image-form" encrypt="multipart/form-data" style="padding:20px;">
        <label>사진등록
          <input @change="changeImage($event)" @dragenter.prevent @dragover.prevent @drop.prevent="dropImage($event)"
            type="file" multiple style="display:none;">
        </label>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  props: ['data'],
  data() {
    return {
      images: [],
      imagesUrl: [],

    }
  },
  methods: {
    show(e){
      let target = e.target;
      let showTarget = target.querySelector('.btn_container');
      showTarget.setAttribute('style','opacity:0.6');
    },
    hide(e){
      let target = e.target;
      let showTarget = target.querySelector('.btn_container');
      showTarget.setAttribute('style','opacity:0');
    },
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

      this.imagesUrl = [];
      for (let image of this.images) {
        this.imagesUrl.push(URL.createObjectURL(image));
      }
      this.$emit('changeImage', this.images)
    },
  }

}
</script>

<style scoped src="@/assets/css/note/Cimg.css">

</style>