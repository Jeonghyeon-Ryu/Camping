<template>
  <div class="write_fn" @mouseenter="show($event)" @mouseleave="hide($event)">
    <div class="left_container" >
      <div class="btn_container">
        <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
        <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
      </div>
    </div>
    <div class='table_container' style="margin-bottom : 20px">
      <button class='row_addbtn'><img src="@/assets/img/note/down_arrow.png" @click="addRow($event)"
          @mouseover="changeShow"></button>
      <table class='maked_table' @click="tableListFn($event)">
        <template v-for="(tr, i) in data" :key="i">
          <tr class='item'>
            <td class="row-button-container">
              <button class='row_delbtn'><img src="@/assets/img/note/trash.png"
                  @click="delRow($event)" class="row_delimg"></button>
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
import $ from 'jquery'
export default {
  props: ['data'],
  data: function () {
    return {
      btnActive: true
    }
  },
  methods: {
    show(e){
      let target = e.target;
      let showTarget = target.querySelector('.btn_container');
      showTarget.setAttribute('style','opacity:0.6');
      
      let showDelRowTargets = target.querySelectorAll('.row-button-container');
      for(let i=0; i<showDelRowTargets.length; i++){
        let showDelRowTarget = showDelRowTargets[i];
        showDelRowTarget.setAttribute('style','opacity:0.6');
      }

      let colAddBtn = target.querySelector('.col_addbtn');
      colAddBtn.setAttribute('style','opacity:0.6');

      let rowAddBtn = target.querySelector('.row_addbtn');
      rowAddBtn.setAttribute('style','opacity:0.6');
    },
    hide(e){
      let target = e.target;
      let showTarget = target.querySelector('.btn_container');
      showTarget.setAttribute('style','opacity:0');
      
      let showDelRowTargets = target.querySelectorAll('.row-button-container');
      for(let i=0; i<showDelRowTargets.length; i++){
        let showDelRowTarget = showDelRowTargets[i];
        showDelRowTarget.setAttribute('style','opacity:0');
      }
      
      let colAddBtn = target.querySelector('.col_addbtn');
      colAddBtn.setAttribute('style','opacity:0');

      let rowAddBtn = target.querySelector('.row_addbtn');
      rowAddBtn.setAttribute('style','opacity:0');
    },
    
    tableListFn(e) {
      let btnName = e.target.classList[0];
      console.log('...', btnName)
      if (btnName == 'add_delbtn' || btnName == 'row_delimg') {
        this.delRow(e);
      }
    },
    addRow: function (e) {
      let thisTable = e.target.parentNode.nextSibling;
      let copyRow = $(thisTable).children().eq(0).clone(true); //첫번째tr복사
      copyRow.children().children(".input_text").val(""); //복사한 tr의 input박스 안에꺼 지우기
      $(thisTable).append(copyRow);

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
  },

}

</script>

<style scoped src="@/assets/css/note/WriteNote.css">

</style>