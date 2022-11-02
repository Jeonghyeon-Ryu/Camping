<template>
    <div class="write_fn" @mouseenter="show($event)" @mouseleave="hide($event)">
        <div class="left_container">
            <div class="btn_container">
                <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
                <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
            </div>
        </div>
        <div class="checkbox_place" @click="checkListFn($event)">
            <div class='checkbox_list' v-for="i of count">
                <div class="box_container">
                    <input type='checkbox' class='noteCheckbox' name="myCheck" v-model="data[0][i - 1]">
                    <input type="text" class="checkbox_text" name="myCheck" :value="data[1][i - 1]">
                </div>
                <div class="checkbox_button_container">
                    <button class="add_checkbox"><img src="@/assets/img/note/plus.png" class="add_img"
                            @click="addCheckList($event)"></button>
                    <button class="del_checkbox"><img src="@/assets/img/note/minus.png" class="del_img"
                            @click="delCheckList($event)"></button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import $ from 'jquery'

export default {
    props: ['data'],
    data() {
        return {
            count: ''
        }
    },
    created() {
        this.count = this.data[0].length;
    },
    methods: {
        show(e) {
            let target = e.target;
            let showTarget = target.querySelector('.btn_container');
            showTarget.setAttribute('style', 'opacity:0.6');
            let showAddDel = target.querySelector('.checkbox_button_container');
            showAddDel.setAttribute('style', 'opacity:0.6');
        },
        hide(e) {
            let target = e.target;
            let showTarget = target.querySelector('.btn_container');
            showTarget.setAttribute('style', 'opacity:0');
            let showAddDel = target.querySelector('.checkbox_button_container');
            showAddDel.setAttribute('style', 'opacity:0');
        },
        checkListFn(e) {
            let btnName = e.target.classList[0];
            console.log('...', btnName)
            if (btnName == 'add_checkbox') {
                this.addCheckList(e);
            } else if (btnName == 'del_checkbox' || btnName == 'del_img') {
                this.delCheckList(e);
            }
        },
        addCheckList: function (e) {
            let checkboxPlace = e.target;

            while (!checkboxPlace.classList.contains('checkbox_place')) {
                checkboxPlace = checkboxPlace.parentElement;
            }

            let checkboxList = document.createElement('div');
            checkboxList.setAttribute('class', 'checkbox_list');
            checkboxList.setAttribute('style', 'display:flex;');

            let boxContainer = document.createElement('div');
            boxContainer.setAttribute('class', 'box_container');
            boxContainer.setAttribute('style', 'padding:0; display:flex; margin-left:1%; margin-bottom:6px;');


            let btnContainer = document.createElement('div');
            btnContainer.setAttribute('class', 'checkbox_button_container');
            btnContainer.setAttribute('style', 'display:flex; margin-left: 5px; width:40px;');

            let checkbox = document.createElement('input');
            checkbox.setAttribute('type', 'checkbox');
            checkbox.setAttribute('class', 'noteCheckbox');
            checkbox.setAttribute('name', 'myCheck');
            checkbox.setAttribute('style', 'margin-top: 10px; scale: 1.5;');

            let textbox = document.createElement('input');
            textbox.setAttribute('type', 'text');
            textbox.setAttribute('class', 'checkbox_text');
            textbox.setAttribute('name', 'myCheck');
            textbox.setAttribute('style', 'width:250px; height:30px; font-size: 15px; margin: 10px 0px 5px 10px; border: 1px solid lightgray; border-radius: 4px;');

            //boxcontatiner 완성
            boxContainer.append(checkbox);
            boxContainer.append(textbox);

            let addButton = document.createElement('button');
            addButton.setAttribute('class', 'add_checkbox');
            addButton.setAttribute('style', 'width: 25px; height: 25px; opacity: 0.6; transition-duration: 0.4s; background-color: transparent; border: none;  padding: 10px; margin: 0;');
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
            let checkboxPlace = e.target;
            //target이 checkbox_place를 찾을때까지 부모로 올라가도록
            while (!checkboxPlace.classList.contains('checkbox_place')) {
                checkboxPlace = checkboxPlace.parentElement;
            }
            let checkboxList = e.target.parentElement.parentElement.parentElement;
            console.log("checkboxList,,,,,,,,,,,,,,,,,,", checkboxList)

            let checkboxLists = checkboxPlace.querySelectorAll('.checkbox_list');
            // console.log(">>>>>>>>>>", checkboxList)
            if (checkboxLists.length > 1) {
                checkboxList.remove();
            }
        }
    }
}
</script>
<style scoped src="@/assets/css/note/WriteNote.css">

</style>