<template>
    <div class="write_fn">
        <div class="left_container">
            <div class="btn_container">
                <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
                <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
            </div>
        </div>
        <div class="checkbox_place">
            <div class='check_box_list'>
                <div class="box_container" v-for="i of count">       
                    <input type='checkbox' class='noteCheckbox' name="myCheck" v-model="data[0][i-1]">
                    <input type="text" class="checkbox_text" name="myCheck" :value="data[1][i-1]">      
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
    data (){
        return{ 
            count : ''
        }
    },
    created() {
        this.count = this.data[0].length;
    },
    methods: {
        addCheckList: function (e) {
            //let checkboxPlace = e.target.parentElement.parentElement.parentElement.parentElement;
            let checkBoxPlace = e.target.parentElement;
            console.log(">>>",checkBoxPlace)
            while(!checkboxPlace.classList.contains('checkbox_place')){ 
                checkboxPlace = checkBoxPlace.parentElement;
            }
            let boxContainer = checkboxPlace.querySelector('.box_container');
            let checkboxList = checkboxPlace.querySelector('.check_box_list');
            
            
            for(let i of checkboxList){
                let checkbox = document.createElement('input');
                checkbox.setAttribute('type', 'checkbox');
                checkbox.setAttribute('class', 'noteCheckbox');
                checkbox.setAttribute('name','myCheck');
                checkbox.setAttribute('style', 'border 2px solid red');
                boxContainer.append(checkbox);

                let textbox = document.createElement('input');
                textbox.setAttribute('type', 'text');
                textbox.setAttribute('class', 'checkbox_text');
                textbox.setAttribute('name','myCheck');

                boxContainer.append(textbox);
            }
            /*$(checkboxPlace).append(`
                <div class='check_box_list'>
                    <div class="box_container">
                        <input type='checkbox' class='noteCheckbox'  name="myCheck" value="true">
                        <input type="text" class="checkbox_text" name="myCheck">
                    </div>
                    <div class="checkbox_button_container" style="display:flex; margin-left: 5px; width:40px">
                        <button class="add_checkbox"><img src="@/assets/img/note/plus.png" class="add_img" @click="addCeheckList"></button>
                        <button class="del_checkbox"><img src="@/assets/img/note/minus.png" class="del_img" @click="delCeheckList"></button>
                    </div>
                </div>
            `);*/
            
            
        },
        delCheckList: function (e) {
            let checkboxPlace = e.target;
            //target이 checkbox_place를 찾을때까지 부모로 올라가도록
            while (!checkboxPlace.classList.contains('checkbox_place')) {
                checkboxPlace = checkboxPlace.parentElement;
            }
            let checkboxList = checkboxPlace.children;
            if ($(checkboxPlace).children().length > 1) {
                $(checkboxList).remove();
            }
        }
    }
}
</script>
<style scoped src="@/assets/css/note/WriteNote.css">

</style>