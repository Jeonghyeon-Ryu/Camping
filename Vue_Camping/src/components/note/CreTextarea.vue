<template>
    <div class="write_fn">
        <div class="left_container">
            <div class="btn_container">
                <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
                <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
            </div>
        </div>
        <div v-if="type=='textBox'">
            <textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" v-on:keydown.shift="shiftfDown($event)"
                v-on:keydown.enter="creTextarea($event)">
            </textarea>
        </div>
        <div v-if="type=='tableBox'" class='maked_table_place' v-on:keydown.enter="creTextarea($event)">
            <div class='table_container'>
                <button class='row_addbtn'><img src="@/assets/img/note/down_arrow.png" @click="addRow"
                        @mouseover="changeShow"></button>
                <table class='maked_table'>
                    <tr class='item'>
                        <td class="row-button-container" @mouseover="showBtn">
                            <button class='row_delbtn' v-if="btnActive==true"><img src="@/assets/img/note/trash.png"
                                    @click="delRow($event)" @mouseout="hideBtn"></button>
                        </td>
                        <td class='item_td'><input type="text" class="input_text"></td>
                        <td class='item_td'><input type="text" class="input_text"></td>
                    </tr>
                    <tr class='item'>
                        <td class="row-button-container">
                            <button class='row_delbtn'><img src="@/assets/img/note/trash.png"></button>
                        </td>
                        <td class='item_td'><input type="text" class="input_text"></td>
                        <td class='item_td'><input type="text" class="input_text"></td>
                    </tr>
                </table>
                <button class='col_addbtn'><img src="@/assets/img/note/right_arrow.png" @click="addCol"></button>
            </div>
        </div>
        <div v-if="type=='checkboxBox'" class="checkbox_place">
            <div  class='check_box_list'>
                <div class="box_container">
                    <input type='checkbox' class='noteCheckbox' name="myCheck" value="true">
                    <input type="text" class="checkbox_text" name="myCheck">
                </div>
                <div class="checkbox_button_container">
                    <button class="add_checkbox"><img src="@/assets/img/note/plus.png" class="add_img"
                            @click="addCheckList"></button>
                    <button class="del_checkbox"><img src="@/assets/img/note/minus.png" class="del_img"
                            @click="delCheckList"></button>
                </div>
            </div>
        </div>        
        <div class="img_container" v-if="type=='imgBox'">
            <div  class='used-insert-image-preview'>
                <ImagePreview :images="images" :isNotList="false" @deleteImages="deleteImages"></ImagePreview>
            </div>
            <div id="camp-register-image-form" encrypt="multipart/form-data" style="padding:20px;">
                <label>사진등록
                <input name="files" @change="changeImage($event)" @dragenter.prevent @dragover.prevent
                    @drop.prevent="dropImage($event)" type="file" multiple style="display:none;">
                </label>
            </div>
        </div>
    </div>
</template>

<script>
import { append, width } from 'dom7';
import $ from 'jquery'
import ImagePreview from '../ImagePreview.vue'; 

export default {
    data() {
        return {
            shiftSatus: false,
            //file: "",
            images: [],
            imagesUrl: []
        };
    },
    props: ["type"],
    
    methods: {
         // startDrag
         startDrag(event, item) {
            event.dataTransfer.dropEffect = "move"
            event.dataTransfer.effectAllowed = "move"
            event.dataTransfer.setData("selectedItem", item.content)
        },

        /**드래그 연습*/
        creTextarea: function (e) {
            if (!this.shiftSatus) {
                e.preventDefault();
                this.$emit("creArea");
            }
        },
        delLine: function (e) {
            let thisWriteFn = e.target.parentNode.parentNode.parentNode.parentNode;
            let AllwriteFn = document.querySelectorAll(".write_fn");
            if (AllwriteFn.length > 1) {
                $(thisWriteFn).remove();
            }
        },
        shiftfUp: function (e) {
            this.shiftSatus = false;
        },
        shiftfDown: function (e) {
            this.shiftSatus = true;
        },
        addRow: function (e) {
            let thisTable = e.target.parentNode.nextSibling;
            let copyRow = $(thisTable).children().eq(0).clone(true); //첫번째tr복사
            copyRow.children().children(".input_text").val(""); //복사한 tr의 input박스 안에꺼 지우기
            $(thisTable).append(copyRow);
            console.log('CreTextarea')

        },
        addCol: function (e) {
            let thisTable = e.target.parentElement.previousSibling;

            for (let i = 0; i < $('.maked_table').children().length; i++) {
                $(thisTable).children().eq(i).append('<td class="item_td"><input type="text" class="input_text"></td>');
            }
            console.log('CreTextarea')
        },
        delRow: function (e) {
            let findRow = e.target.parentElement.parentElement.parentElement;
            if ($('.item').length > 1) {
                $(findRow).remove();
            }
        },
        creTablebox: function (e) {
            this.$emit("tableBox");
        },
        creImg: function (e) {
            this.$emit("imgBox");
        },
        deleteImages(updatedImages) {
            this.images = updatedImages;
            document.querySelector('#sns-register-image-container input[type="file"]').files = updatedImages;
        },
        changeImage(e) {
            console.log(e.target);
            let dt = new DataTransfer();
            for (let i = 0; i < e.target.files.length; i++) {
                dt.items.add(e.target.files[i]);
        }
            this.images = dt.files;
            e.target.files = dt.files;
        },
        showBtn(e) {
            this.btnActive = true;
        },
        hideBtn(e) {
            this.btnActive = false;
        },
        changShow(e) {
            this.style.opacity = "opacity: 0.6";
        },
        addCheckList: function (e) {

             let checkboxPlace = e.target;
                          
            while(!checkboxPlace.classList.contains('checkbox_place')){ 
                checkboxPlace = checkboxPlace.parentElement;
            }
            console.log(checkboxPlace);
            
            //let checkboxList = checkboxPlace.querySelector('.check_box_list');
            //let btnContainer = checkboxPlace.querySelector('.checkbox_button_container');
            
                checkboxPlace.setAttribute('style', 'display: flex; width: 300px;')
                
                 let checkboxList = document.createElement('div');
                 checkboxList.setAttribute('class', 'check_box_list');
                 //checkboxList.setAttribute('style', 'display:flex;');

                 let boxContainer = document.createElement('div');
                 boxContainer.setAttribute('class', 'box_container');
                 boxContainer.setAttribute('style', 'padding:0; display:inline-block; margin-left: 1%; width: 40px;');

                 let btnContainer = document.createElement('div');
                 btnContainer.setAttribute('class', 'checkbox_button_container');
                 btnContainer.setAttribute('style', 'display:flex; margin-left: 5px; width:40px;');

                 let checkbox = document.createElement('input');
                 checkbox.setAttribute('type', 'checkbox');
                 checkbox.setAttribute('class', 'noteCheckbox');
                 checkbox.setAttribute('name','myCheck');
                 checkbox.setAttribute('style', 'border:none;');
                
                 boxContainer.append(checkbox);

                 let textbox = document.createElement('input');
                 textbox.setAttribute('type', 'text');
                 textbox.setAttribute('class', 'checkbox_text');
                 textbox.setAttribute('name','myCheck');
                 textbox.setAttribute('style', 'margin: 10px 0px 5px 10px; border: 1px solid lightgray; border-radius: 5%;');

                 boxContainer.append(textbox);

                 let addButton = document.createElement('button');
                 addButton.setAttribute('class', 'add_checkbox');
                 addButton.setAttribute('style', 'width: 25px; height: 25px;, background-color: transparent; border: none;  padding: 10px; margin: 0;');
                 addButton.style.cssText
                
                 //add버튼
                 let addImg = document.createElement('img')
                 addImg.setAttribute('class', 'add_img');
                 let addImgSrc = document.querySelector('.add_img')
                 addImg.src = addImgSrc.src;
                 addButton.append(addImg);

                 //del버튼
                 let delButton = document.createElement('button');
                 delButton.setAttribute('class', 'del_checkbox');
                 delButton.setAttribute('style', 'width: 25px; height: 25px;, background-color: transparent; border: none;  padding: 10px; margin: 0;');
                let delImg = document.createElement('img')
                 delImg.setAttribute('class', 'del_img');
                 delImg.src = "src/assets/img/note/minus.png"
                 let delImgSrc = document.querySelector('.del_img')
                 delImg.src = delImgSrc.src;
                 delButton.append(delImg);

                 btnContainer.append(addButton);
                 btnContainer.append(delButton);
                
                 checkboxList.append(boxContainer);
                 checkboxList.append(btnContainer)
                 checkboxPlace.append(checkboxList);
        
        },
        delCheckList: function (e) {
            let checkboxPlace = e.target.parentElement.parentElement.parentElement;
            let checkboxList = e.target.parentElement.parentElement;
            if ($(checkboxPlace).children().length > 1) {
                $(checkboxList).remove();
            }
        },
        deleteImages(updatedImages) {
            this.images = updatedImages;
            document.querySelector(".used-insert-image-container input[type=\"file\"]").files = updatedImages;
        },
        //write페이지
        changeImage(e) {
            let dt = new DataTransfer();
            for (let i = 0; i < e.target.files.length; i++) {
                dt.items.add(e.target.files[i]); //kind와 type
                
            }
            for(let i=0; i < this.images.length; i++){ 
                dt.items.add(this.images[i]);
            }
            this.images=dt.files; //파일 name, date
            e.target.files = dt.files;
           
            
            this.$emit("saveImg", this.images);
        },
        
    },
    components: { ImagePreview }
}
</script>
<style scoped src="@/assets/css/note/WriteNote.css">

</style>