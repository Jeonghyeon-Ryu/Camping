<!-- 이미지 미리보기 컴포넌트 ver.1 -->
<!-- 
    [ Made by 류정현 ]
    [ 자식 프로퍼티 ]
        - images : FileList  
        - <ImagePreview :images="파일리스트" @deleteImages="deleteImages"></ImagePreview>
    [ 부모 정의 필요 이벤트 ]
        - 부모 삭제 이벤트 ( 필수사항 )
    deleteImages(updatedImages) {
        this.images = updatedImages;
        document.querySelector('.camp-register-image-container input[type="file"]').files=updatedImages;
    },

        - 부모 Input File 이벤트 ( 선택사항 )
    changeImage(e) {
        console.log(e.target.files);
        let dt = new DataTransfer();
        for(let i=0; i<e.target.files.length; i++){
            dt.items.add(e.target.files[i]);
        }
        this.images = dt.files;
        e.target.files = dt.files;
    }
-->
 
<template>
    <div class="image-preview-container">
        <div class="image-preview-div">
            <img :src="imagesUrl" />
            <input type="button" value="X" @mouseover="mouseoverImage($event)" @mouseout="mouseoutImage($event)" @click="deleteImage($event)" class="image-preview-delete-button" />
        </div>
    </div>
</template>

<script>
    export default {
        props : ['image'],
        data() {
            return {
                imageFiles : [],
                imagesUrl : ''
            }
        },
        methods : {
            deleteImage(e) {
                let parentDiv = e.target.parentElement;
                let dt = new DataTransfer();
                let tempimages = [];
                let tempimagesUrl = [];
                for(let i=0; i<this.imageFiles.length; i++){
                    if(i != parentDiv.getAttribute('id')) {
                        dt.items.add(this.imageFiles[i]);
                        // tempimages.push(this.imageFiles[i]);
                        tempimagesUrl.push(this.imagesUrl[i]);
                    } else {

                    }
                }
                this.imageFiles = dt.files;
                this.imagesUrl = tempimagesUrl;
                this.$emit('deleteImages', dt.files);
            },
            insertImage(images) {
                let dt = new DataTransfer();
                for(let image of images){
                    if(image.type.indexOf('image')>=0) {
                        dt.items.add(image);
                    }
                }
                this.imageFiles = dt.files;
                for(let image of this.imageFiles){
                    this.imagesUrl = URL.createObjectURL(image);
                }
            },
            mouseoverImage(e) {
                e.target.classList.add('delete-button-active');
                e.target.previousElementSibling.classList.add('filter-blur');
            },
            mouseoutImage(e) {
                e.target.classList.remove('delete-button-active');
                e.target.previousElementSibling.classList.remove('filter-blur');
            }
        },
        watch : {
            image(newImage, oldImage) {
                if(newImage.length >= oldImage.length){
                    this.insertImage(newImage);
                }
            }
        }
    }
</script>

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
@media screen and (max-width:576px) {

}
@media screen and (min-width:577px ) and (max-width:1200px) {
    
}
@media screen and (min-width:1201px ) {
    
}
.image-preview-div {
    display: inline-block;
    position: relative;
    width: 100%;
    height: 100%;
    z-index: 1;
    cursor: pointer;
    border:none;
}

.image-preview-div img {
    width: 100%;
    height: 100%;
    z-index: none;
}

.delete-button-active{
    opacity:1 !important;
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
    opacity: 0;
}
</style>