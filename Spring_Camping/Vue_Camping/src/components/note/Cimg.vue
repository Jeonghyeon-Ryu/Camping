<template>
  <div class="write_fn">
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

<style scoped>
.camp-register-image-form {
  display: flex;
}
</style>
<style scoped>
.image-preview-container {
  width: 100%;
  height: 100%;
}

/* total width */
.image-preview-container::-webkit-scrollbar {
  background-color: #fff;
  width: 16px;
}

/* background of the scrollbar except button or resizer */
.image-preview-container::-webkit-scrollbar-track {
  background-color: #fff;
}

.image-preview-container::-webkit-scrollbar-track:hover {
  background-color: #f4f4f4;
}

/* scrollbar itself */
.image-preview-container::-webkit-scrollbar-thumb {
  background-color: rgb(228, 239, 231);
  border-radius: 16px;
  border: 5px solid #fff;
}

.image-preview-container::-webkit-scrollbar-thumb:hover {
  background-color: rgb(6, 68, 32);
  border: 4px solid #f4f4f4;
}

/* set button(top and bottom of the scrollbar) */
.image-preview-container::-webkit-scrollbar-button {
  display: none;
}

@media screen and (max-width:576px) {}

@media screen and (min-width:577px) and (max-width:1200px) {}

@media screen and (min-width:1201px) {}

.image-preview-div {
  display: inline-block;
  position: relative;
  width: 150px;
  height: 120px;
  margin: 5px;
  border: 1px solid rgb(220, 220, 220);
  z-index: 1;
  cursor: pointer;
}

.image-preview-div img {
  width: 100%;
  height: 100%;
  z-index: none;
}

.delete-button-active {
  opacity: 1 !important;
}

.filter-blur {
  filter: blur(1px);
}

.image-preview-delete-button {
  width: 100%;
  height: 100%;
  position: absolute;
  font-size: 50px;
  right: 0px;
  bottom: 0px;
  z-index: 999;
  background-color: rgba(255, 255, 255, 0.1);
  color: rgba(255, 145, 0, 0.9);
  border: none;
  cursor: pointer;
  opacity: 0.3;
}
</style>