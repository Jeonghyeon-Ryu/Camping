<template>
  <div class="write_fn">
    <div class="left_container">
      <div class="btn_container">
        <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
        <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
      </div>
    </div>
    <div class='table_container'>
      <button class='row_addbtn'><img src="@/assets/img/note/down_arrow.png" @click="addRow"
          @mouseover="changeShow"></button>
      <table class='maked_table'>
        <!-- <tr class='item' v-for="(tr, i) in datas" :key="i">
          <td class="row-button-container" @mouseover="showBtn" v-for=" in datas" :key="i">
            <button class='row_delbtn' v-if="btnActive==true"><img src="@/assets/img/note/trash.png"
                @click="delRow($event)" @mouseout="hideBtn"></button>
          </td>
          <td class='item_td' v-for="(td, j) in tr" :key="j"><input width="100px" type="text" class="input_text"></td>
          <td class='item_td'><input width="100px" type="text" class="input_text"></td>
        </tr>
        <tr class='item'>
          <td class="row-button-container">
            <button class='row_delbtn'><img src="@/assets/img/note/trash.png"></button>
          </td>
          <td class='item_td'><input width="100px" type="text" class="input_text"></td>
          <td class='item_td'><input width="100px" type="text" class="input_text"></td>
        </tr> -->
        <tr class='item' v-for="(tr, i) in datas" :key="i">
          <td class="row-button-container" @mouseover="showBtn" v-for=" in datas" :key="i">
            <button class='row_delbtn' v-if="btnActive==true"><img src="@/assets/img/note/trash.png"
                @click="delRow($event)" @mouseout="hideBtn"></button>
          </td>
          <td class='item_td' v-for="(td, j) in tr" :key="j"><input width="100px" type="text" class="input_text">{{td.data}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
export default {
  props: ['data'],

  methods: {

    addRow: function (e) {
      let thisTable = e.target.parentNode.nextSibling;
      let copyRow = $(thisTable).children().eq(0).clone(true); //첫번째tr복사

      copyRow.children().children(".input_text").val(""); //복사한 tr의 input박스 안에꺼 지우기
      $(thisTable).append(copyRow);

    },
    addCol: function (e) {
      //let tablePlace = e.target.parentElement.parentElement.parentElement;
      let thisTable = e.target.parentElement.previousSibling;

      for (let i = 0; i < $('.maked_table').children().length; i++) {
        $(thisTable).children().eq(i).append('<td class="item_td"><input width="100px" type="text" class="input_text"></td>');
      }
      console.log($(thisTable).children());
    },
    delRow: function (e) {
      let findRow = e.target.parentElement.parentElement.parentElement;

      if ($('.item').length > 1) {
        $(findRow).remove();

      }
    },
  }
}
</script>

<style scoped>
.textbox {
  background-color: beige;
  border: 3px solid beige;
  line-height: 10px;
}

.write_place {
  display: inline-block;
  height: fit-content;
  width: 90%;
  border: none;
}
</style>