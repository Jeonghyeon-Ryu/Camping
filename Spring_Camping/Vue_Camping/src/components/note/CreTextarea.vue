<template>
    <div class="write_fn">
        <div class="left_container">
            <div class="btn_container">
                <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
                <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
            </div>
        </div>
        <div v-if="type == 'textBox'" class="textarea_container">
            <textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" v-on:keydown.shift="shiftfDown($event)"
                v-on:keydown.enter="creTextarea($event)"></textarea>
        </div>
        <div v-if="type == 'tableBox'" class='maked_table_place' v-on:keydown.enter="creTextarea($event)">
            <div class='table_container'>
                <button class='row_addbtn'><img src="@/assets/img/note/down_arrow.png" @click="addRow"
                        @mouseover="changeShow"></button>
                <table class='maked_table'>
                    <tr class='item'>
                        <td class="row-button-container" @mouseover="showBtn($event)">
                            <button class='row_delbtn' @mouseover="showBtn($event)"><img src="@/assets/img/note/trash.png"
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
        <div v-if="type == 'checkboxBox'" class="checkbox_place" @click="checkListFn($event)" @mouseover="showImg($event)">
            <div class='checkbox_list'>
                <div class="box_container">
                    <input type='checkbox' class='noteCheckbox' name="myCheck" value="true">
                    <input type="text" class="checkbox_text" name="myCheck">
                </div>
                <div class="checkbox_button_container" >
                    <button class="add_checkbox"><img src="@/assets/img/note/plus.png" class="add_img"></button>
                    <button class="del_checkbox"><img src="@/assets/img/note/minus.png" class="del_img"></button>
                </div>
            </div>
        </div>
        <div class="img_container" v-if="type == 'imgBox'">
            <div class='used-insert-image-preview'>
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
            let tableContainer = e.target.parentElement;
            while (!tableContainer.classList.contains('table_container')) {
                tableContainer = tableContainer.parentElement;
            }

            let table = tableContainer.querySelector('.maked_table');
            let trs = table.querySelectorAll('tr');

            for (let tr of trs) {
                let td = document.createElement('td');
                td.setAttribute('class', 'item_td');
                td.setAttribute('style', 'width:200px; height:fit-content; border:2px solid lightgray;')
                let input = document.createElement('input');
                input.setAttribute('type', 'text');
                input.setAttribute('class', 'input_text');
                input.setAttribute('style', 'border:none; outline:none; width:98%; height:100%;')
                td.append(input);
                tr.append(td);
            }
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
            console.log("asdasd");
        },
        checkListFn(e){
            let btnName = e.target.classList[0];
            console.log('...',btnName)
            if(btnName == 'add_checkbox' || btnName=='add_img'){
                console.log('...!!!!!!!!!!!!!')
                this.addCheckList(e);
            }else if(btnName == 'del_checkbox' || btnName=='del_img'){
                console.log('...!!!!!!!!!!!!!')
                this.delCheckList(e);
            }
        },
        addCheckList: function (e) {
            
            let checkboxPlace = e.target;

            while (!checkboxPlace.classList.contains('checkbox_place')) {
                checkboxPlace = checkboxPlace.parentElement;
            }
            console.log("...",checkboxPlace)
            //checkboxPlace.setAttribute('style', 'display: flex; width: 300px;')

            let checkboxList = document.createElement('div');
            checkboxList.setAttribute('class', 'checkbox_list');
            checkboxList.setAttribute('style', 'display:flex;');

            let boxContainer = document.createElement('div');
            boxContainer.setAttribute('class', 'box_container');
            boxContainer.setAttribute('style', 'padding:0; display:flex; margin-left: 1%;');


            let btnContainer = document.createElement('div');
            btnContainer.setAttribute('class', 'checkbox_button_container');
            btnContainer.setAttribute('style', 'display:flex; margin-left: 5px; width:40px; ');

            let checkbox = document.createElement('input');
            checkbox.setAttribute('type', 'checkbox');
            checkbox.setAttribute('class', 'noteCheckbox');
            checkbox.setAttribute('name', 'myCheck');
            checkbox.setAttribute('style', 'border:none;');

            let textbox = document.createElement('input');
            textbox.setAttribute('type', 'text');
            textbox.setAttribute('class', 'checkbox_text');
            textbox.setAttribute('name', 'myCheck');
            textbox.setAttribute('style', 'width:200px; height:90%; margin: 0 0px 10px 10px; border: 1px solid lightgray; border-radius: 5%;');


            //boxcontatiner 완성
            boxContainer.append(checkbox);
            boxContainer.append(textbox);


            let addButton = document.createElement('button');
            addButton.setAttribute('class', 'add_checkbox');
            addButton.setAttribute('style', 'width: 25px; height: 25px; background-color: transparent; border: none;  padding: 10px; margin: 0;');
            addButton.style.cssText

            //add버튼
            let addImg = document.createElement('img')
            addImg.setAttribute('class', 'add_img')
            addImg.setAttribute('style', 'padding-bottom:20px;') 
            let addImgSrc = document.querySelector('.add_img')
            addImg.src = addImgSrc.src;

            //버튼에 이미지
            addButton.append(addImg);

            //del버튼
            let delButton = document.createElement('button');
            delButton.setAttribute('class', 'del_checkbox');
            delButton.setAttribute('style', 'width: 25px; height: 25px; background-color: transparent; border: none;  padding: 10px; margin: 0;');
            let delImg = document.createElement('img')
            delImg.setAttribute('class', 'del_img');
            delImg.setAttribute('style', 'padding-bottom:20px;') 
            delImg.src = "src/assets/img/note/minus.png"
            let delImgSrc = document.querySelector('.del_img')
            delImg.src = delImgSrc.src;
            
            delButton.append(delImg);

            btnContainer.append(addButton);
            btnContainer.append(delButton);
            
            checkboxList.append(boxContainer);
            checkboxList.append(btnContainer);
            checkboxPlace.append(checkboxList);
        },
        delCheckList: function (e) {
  
            let target = e.target.classList[0];
            
            /*  여기서부터 시작
            if(target == 'add_' )
            
            let checkboxList = e.target.parentElement.parentElement;
            if ($(checkboxPlace).children().length > 1) {
                $(checkboxList).remove();
            }*/
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
            for (let i = 0; i < this.images.length; i++) {
                dt.items.add(this.images[i]);
            }
            this.images = dt.files; //파일 name, date
            e.target.files = dt.files;


            this.$emit("saveImg", this.images);
        },

    },
    components: { ImagePreview }
}
</script>
<style scoped src="@/assets/css/note/WriteNote.css">

</style>