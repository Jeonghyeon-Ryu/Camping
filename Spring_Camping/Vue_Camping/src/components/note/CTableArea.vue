<template>
  <div class="write_fn">
    <div class="left_container">
      <div class="btn_container">
        <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
        <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
      </div>
    </div>
    <div class='table_container'>
      <button class='row_addbtn'><img src="@/assets/img/note/down_arrow.png" @click="addRow($event)"
                        @mouseover="changeShow"></button>
      <table class='maked_table'>
        <template v-for="(tr, i) in data" :key="i">
          <tr class='item'>
            <td class="row-button-container" @mouseover="showBtn">
              <button class='row_delbtn' v-if="btnActive==true"><img src="@/assets/img/note/trash.png"
                  @click="delRow($event)" @mouseout="hideBtn"></button>
            </td>
            <template v-for="(td, j) in tr" :key="j">
              <td class='item_td'><input width="100px" type="text" class="input_text" :value="td"></td>
            </template>
          </tr>
        </template>
      </table>
      <button class='col_addbtn'><img src="@/assets/img/note/right_arrow.png" @click="addCol($event)"></button>
    </div>
  </div>
</template>
<script>
export default {
  props: ['data'],
  data: function () {
    return {
      btnActive: true
    }
  },
  methods: {
    // addRow: function (e) {
    //   let thisTable = e.target.parentNode.nextSibling;
    //   let copyRow = $(thisTable).children().eq(0).clone(true); //첫번째tr복사

    //   copyRow.children().children(".input_text").val(""); //복사한 tr의 input박스 안에꺼 지우기
    //   $(thisTable).append(copyRow);
    //   console.log(thisTable);


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
      let item = e.target.parentElement;
      while (!item.classList.contains('item')) {
        item = item.parentElement;
      }
      item.remove();
    },
  }

</script>

<style scoped src="@/assets/css/note/WriteNote.css">

</style>