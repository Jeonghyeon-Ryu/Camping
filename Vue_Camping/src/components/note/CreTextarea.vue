<template>
<div class="write_fn">
    <div class="left_container">
        <div class="btn_container">
            <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
            <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
        </div>
    </div>  
    <div v-if="type=='textBox'">
        <textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" 
                                    v-on:keydown.shift="shiftfDown($event)" 
                                    v-on:keydown.enter="creTextarea($event)">
        </textarea>
    </div>    
    <div v-if="type=='tableBox'" class='maked_table_place' v-on:keydown.enter="creTextarea($event)">
        <div class='table_container'>
            <button class='row_addbtn' ><img src="@/assets/img/note/down_arrow.png" @click="addRow" @mouseover="changeShow"></button>
            <table class='maked_table'> 
                <tr class='item' > 
                    <td class="row-button-container" @mouseover="showBtn">
                        <button class='row_delbtn'  v-if="btnActive==true"><img src="@/assets/img/note/trash.png" @click="delRow($event)" @mouseout="hideBtn"></button>
                    </td>
                    <td class='item_td'><input width="100px" type="text" class="input_text"></td>  
                    <td class='item_td'><input width="100px" type="text" class="input_text"></td>
                </tr>
                <tr class='item'>
                    <td class="row-button-container">
                        <button class='row_delbtn'><img src="@/assets/img/note/trash.png"></button>
                    </td>
                    <td class='item_td'><input type="text" class="input_text"></td> 
                    <td class='item_td'><input type="text" class="input_text"></td> 
                </tr>
            </table>
        </div>
    </div>          
    <div v-if="type=='checkboxBox'" class='check_box_list'>
        <input type='checkbox' class='noteCheckbox' value="true" name="myCheck"><input type="text" class="checkbox_text" name="myCheck">
        <div class="checkbox_button_container">
            <button class="add_checkbox"><img src="@/assets/img/note/plus.png" class="add_img" @click="addCeheckList"></button>
            <button class="del_checkbox"><img src="@/assets/img/note/minus.png" class="del_img" @click="delCeheckList"></button>
        </div>
    </div>
    <div v-if="type=='imgBox'" style="padding:30px;">
        <input type="file" accept="image/*" @change="fileChange"/>
        <p>
            upload이미지 : {{file.name}} fileSize : ({{file.size}}) / fileType : {{file.type}}
        </p>
    </div>
</div> 
  </template>

<script>
import $ from 'jquery'
import { defineProps, ref, computed, onMounted, watch } from 'vue';


const isEditable = ref(true);
const message = ref("hello");
    export default{ 
      data() {
        return{ 
            shiftSatus : false,
            file: '',
            btnActive : false,
            showCss: { 
                     opacity: '0.6 !important'
            }
        } 
      },
      props : ['type'] 
      ,
      methods : { 
         creTextarea : function(e){   
            if (!this.shiftSatus) {
                e.preventDefault();
            
                this.$emit('creArea');   
            }
         },
         delLine : function(e)  { 
            let thisWriteFn = e.target.parentNode.parentNode.parentNode.parentNode;  
            let AllwriteFn = document.querySelectorAll('.write_fn');
           
            if(AllwriteFn.length > 1){ 
                $(thisWriteFn).remove();
            }
         },
         shiftfUp : function (e){
            this.shiftSatus = false;
         },
         shiftfDown : function(e){ 
            this.shiftSatus = true;
         },
         creTable : function(e){ 
            this.$emit('creTableBox');
         },
         addRow : function(e) {
            let thisTable = e.target.parentNode.nextSibling;
            let copyRow = $(thisTable).children().eq(0).clone(true); //첫번째tr복사
            
            copyRow.children().children(".input_text").val(""); //복사한 tr의 input박스 안에꺼 지우기
            $(thisTable).append(copyRow);
            
         },
         addCol: function(e){ 
            //let tablePlace = e.target.parentElement.parentElement.parentElement;
            let thisTable = e.target.parentElement.previousSibling;
            
            for (let i = 0; i < $('.maked_table').children().length; i++) {
                $(thisTable).children().eq(i).append('<td class="item_td"><input width="100px" type="text" class="input_text"></td>'); 
            }
            console.log($(thisTable).children());
         },
         delRow: function(e){ 
            let findRow = e.target.parentElement.parentElement.parentElement;
            
            if ($('.item').length > 1) {
                $(findRow).remove();
                
            }
         },
         creCheckbox : function(e){ 
            this.$emit('checkboxBox');
        },
         creImg : function(e){ 
            this.$emit('imgBox');
         },
         fileChange: function(e){ 
            
            this.file = e.target.files[0];
         },
         enterPressed : function(){
            alert('Enter Pressed');
        },
        showBtn(e) {
                this.btnActive = true;
                
            },
        hideBtn(e){ 
            this.btnActive = false;
        },
        changShow(e){ 
            this.style.opacity = "opacity: 0.6";
        },
        addCeheckList : function(e){
            let checkboxPlace = e.target.parentElement.parentElement.parentElement.parentElement;
            let checkboxList = checkboxPlace.children;
            //let checkboxList = $(checkboxPlace).children('.check_box_list')[0];
        
            //let cloneBox = $(checkboxList).eq[0].clone(true);
            //cloneBox.children(".checkbox_text").val("");

            $(checkboxPlace).append(`
                <div class='check_box_list'>
                    <input type='checkbox' class='noteCheckbox' value="true" name="myCheck"><input type="text" class="checkbox_text" name="myCheck">
                    <div class="checkbox_button_container">
                        <button class="add_checkbox"><img src="@/assets/img/note/plus.png" class="add_img" @click="addCeheckList"></button>
                        <button class="del_checkbox"><img src="@/assets/img/note/minus.png" class="del_img" @click="delCeheckList"></button>
                    </div>
                </div>
            `);
        },
        delCeheckList : function(e) { 
            let checkboxPlace = e.target.parentElement.parentElement.parentElement;
            let checkboxList = e.target.parentElement.parentElement;
        
            if($(checkboxPlace).children().length > 1){
                $(checkboxList).remove();
            }
        }

    }
}
</script>
<style scoped src="@/assets/css/note/WriteNote.css">
.left_container{ 
    display: flex;
 
}
.write_place {
      border: 1px solid #ddd;
      color: black;
      font-size: 10px;
      width: calc(100% - 60px);
      padding: 5px;
      border: none;
      resize: none;
  }
.write_place:focus{ 
    outline: none;
}
</style>

