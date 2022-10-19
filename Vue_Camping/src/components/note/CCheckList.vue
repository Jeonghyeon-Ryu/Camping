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
    </div>
</template>
<script>
export default {
  props: ['data'],

  created() { 
    console.log(this.data)
  },
  
  methods:{ 
    addCheckList: function (e) {
            let checkboxPlace = e.target.parentElement.parentElement.parentElement.parentElement;
            //let checkboxList = checkboxPlace.children;
            let checkboxList = checkboxPlace.querySelector('.check_box_list');
            //let checkboxList = $(checkboxPlace).children('.check_box_list')[0];
            console.log(checkboxPlace);
            //let cloneBox = $(checkboxList).eq[0].clone(true);
            //cloneBox.children(".checkbox_text").val("");
            $(checkboxPlace).append(`
                <div class='check_box_list' style="display : flex">
                    <div class="box_container">
                        <input type='checkbox' class='noteCheckbox'  name="myCheck" value="true">
                        <input type="text" class="checkbox_text" name="myCheck">
                    </div>
                    <div class="checkbox_button_container" style="display:flex; margin-left: 5px; width:40px">
                        <button class="add_checkbox"><img src="@/assets/img/note/plus.png" class="add_img" @click="addCeheckList"></button>
                        <button class="del_checkbox"><img src="@/assets/img/note/minus.png" class="del_img" @click="delCeheckList"></button>
                    </div>
                </div>
            `);
        },
        delCheckList: function (e) {
            let checkboxPlace = e.target.parentElement.parentElement.parentElement;
            let checkboxList = e.target.parentElement.parentElement;
            if ($(checkboxPlace).children().length > 1) {
                $(checkboxList).remove();
            }
        }
  }

}
</script>
<style scoped src="@/assets/css/note/WriteNote.css"></style>