<template>
  <div class="write_fn">
    <div class="left_container">
      <div class="btn_container">
        <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
        <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
      </div>
    </div>
    <div class='table_container'>
      <td class="row-button-container" @mouseover="showBtn">
        <button class='row_delbtn' v-if="btnActive==true"><img src="@/assets/img/note/trash.png" @click="delRow($event)"
            @mouseout="hideBtn"></button>
      </td>
      <table class='maked_table'>
        <template v-for="(tr, i) in data" :key="i" :value="data">
          <tr class='item'>
            <td class="row-button-container" @mouseover="showBtn">
              <button class='row_delbtn' v-if="btnActive==true" :value="data"><img src="@/assets/img/note/trash.png"
                  @click="delRow($event)" @mouseout="hideBtn"></button>
            </td>
            <template v-for="(td, j) in tr" :key="j" :value="data">


              <td class='item_td'><input width="100px" type="text" class="input_text">{{td}}</td>
            </template>
          </tr>
        </template>
      </table>
      <button class='col_addbtn'><img src="@/assets/img/note/right_arrow.png" @click="addCol"></button>
    </div>
  </div>
</template>
<script>
export default {
  props: ['data'],
  methods: {
    
    // addRow: function (e) {
    //   let thisTable = e.target.parentNode.nextSibling;
    //   let copyRow = $(thisTable).children().eq(0).clone(true); //첫번째tr복사

    //   copyRow.children().children(".input_text").val(""); //복사한 tr의 input박스 안에꺼 지우기
    //   $(thisTable).append(copyRow);
    //   console.log(thisTable);
      

    // },
    // addCol: function (e) {
    //   //let tablePlace = e.target.parentElement.parentElement.parentElement;
    //   let thisTable = e.target.parentElement.previousSibling;

    //   for (let i = 0; i < $('.maked_table').children().length; i++) {
    //     $(thisTable).children().eq(i).append('<td class="item_td" ><input type="text" class="input_text"></td>');
    //   }
    //   console.log($(thisTable).children());
    //   console.log("여기꺼 지우지마")
    // },
    delRow: function (e) {
      let findRow = e.target.parentElement.parentElement.parentElement;

      if ($('.item').length > 1) {
        $(findRow).remove();

      }
    },
  }
}
</script>

<style scoped src="@/assets/css/note/WriteNote.css">

</style>