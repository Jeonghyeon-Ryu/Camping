<template>
  <div class="mynote_container" style="border: 5px solid lightgray; border-radius: 10px;">
    <div class="mynote_region">
      <div class="write_region">
        <div id="pdfDiv">
          <input placeholder="제목" class="note_title" :value=title>
          <div class="sortable">
            <template v-if="datas">
              <CreateLine v-for="item of datas" :type="item.type" :data="item.data" @creArea="CreArea($event)">
              </CreateLine>
            </template>

            <CreTextarea :type="childOrder[0]" @creArea="CreArea($event)" v-if="textAmount >= 1">
            </CreTextarea>
            <CreTextarea :type="childOrder[1]" @creArea="CreArea($event)" v-if="textAmount >= 2">
            </CreTextarea>
            <CreTextarea :type="childOrder[2]" @creArea="CreArea($event)" v-if="textAmount >= 3">
            </CreTextarea>
            <CreTextarea :type="childOrder[3]" @creArea="CreArea($event)" v-if="textAmount >= 4">
            </CreTextarea>
            <CreTextarea :type="childOrder[4]" @creArea="CreArea($event)" v-if="textAmount >= 5">
            </CreTextarea>
            <CreTextarea :type="childOrder[5]" @creArea="CreArea($event)" v-if="textAmount >= 6">
            </CreTextarea>
            <CreTextarea :type="childOrder[6]" @creArea="CreArea($event)" v-if="textAmount >= 7">
            </CreTextarea>
            <CreTextarea :type="childOrder[7]" @creArea="CreArea($event)" v-if="textAmount >= 8">
            </CreTextarea>
            <CreTextarea :type="childOrder[8]" @creArea="CreArea($event)" v-if="textAmount >= 9">
            </CreTextarea>
            <CreTextarea :type="childOrder[9]" @creArea="CreArea($event)" v-if="textAmount >= 10">
            </CreTextarea>
            <CreTextarea :type="childOrder[10]" @creArea="CreArea($event)" v-if="textAmount >= 11">
            </CreTextarea>
            <CreTextarea :type="childOrder[11]" @creArea="CreArea($event)" v-if="textAmount >= 12">
            </CreTextarea>
            <CreTextarea :type="childOrder[12]" @creArea="CreArea($event)" v-if="textAmount >= 13">
            </CreTextarea>
            <CreTextarea :type="childOrder[13]" @creArea="CreArea($event)" v-if="textAmount >= 14">
            </CreTextarea>
            <CreTextarea :type="childOrder[14]" @creArea="CreArea($event)" v-if="textAmount >= 15">
            </CreTextarea>
            <CreTextarea :type="childOrder[15]" @creArea="CreArea($event)" v-if="textAmount >= 16">
            </CreTextarea>
            <CreTextarea :type="childOrder[16]" @creArea="CreArea($event)" v-if="textAmount >= 17">
            </CreTextarea>
            <CreTextarea :type="childOrder[17]" @creArea="CreArea($event)" v-if="textAmount >= 18">
            </CreTextarea>
            <CreTextarea :type="childOrder[18]" @creArea="CreArea($event)" v-if="textAmount >= 19">
            </CreTextarea>
            <CreTextarea :type="childOrder[19]" @creArea="CreArea($event)" v-if="textAmount >= 20">
            </CreTextarea>
            <CreTextarea :type="childOrder[20]" @creArea="CreArea($event)" v-if="textAmount >= 21">
            </CreTextarea>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery';
import CreTextarea from './CreTextarea.vue';
import MyNoteList from './MynoteList.vue';
import CreateLine from './CreateLine.vue';


export default {
  props :['noteId'],
  data() {
    return {
      title: '',
      textAmount: 1,
      childOrder: ['textBox'],
      noteInfo: [],    
      showInfo: {},
      datas: [],
    }
  },
  created() {
    console.log(this.noteId)
    //this.noteId = this.$router.params.myNoteId;
    fetch("http://13.125.95.210:85/java/GoMyNote/" + this.noteId)
      .then(result => result.json())
      .then(result => {
        this.title = result.title;
        console.log('===================================')
        console.log(result)
        for (let i = 0; i < result.noteContents.length; i++) {
          //가져온 값을 textarea에 뿌려주기
          if (result.noteContents[i].indexOf('textarea') >= 0) {
            let temp = result.noteContents[i].substring(result.noteContents[i].indexOf('value="') + 7, result.noteContents[i].indexOf('"></textarea>'));
            this.datas.push(
              {
                type: 0,
                data: temp
              }
            )
          } else if (result.noteContents[i].indexOf('table_container') >= 0) {
            //가져온 값을 table로 만들어주기 (2차원 배열 구조) 뿌려주기
            let tempData = [];
            let temp = result.noteContents[i].split('</tr>');
            for (let j = 0; j < temp.length - 1; j++) {
              let rowData = [];
              while (temp[j].indexOf('value="') >= 0) {
                temp[j] = temp[j].substring(temp[j].indexOf('value="') + 7, temp[j].length);
                rowData.push(temp[j].substring(0, temp[j].indexOf('">')));
              }
              tempData.push(rowData);
            }
            this.datas.push(
              {
                type: 1,
                data: tempData
              }
            )
            //checkList
          } else if (result.noteContents[i].indexOf('check_box_list') >= 0) {
            let temp = result.noteContents[i].split('<div class="box_container">'); //배열의 형태로 5개의 덩어리
            let checkVal = [];
            let textVal = [];
            let values = [];

            for (let j = 0; j < temp.length - 1; j++) {
              temp[j] = temp[j].substring(temp[j].indexOf('value="') + 7, temp[j].length);
              checkVal.push(temp[j].substring(0, temp[j].indexOf('">')));

              temp[j] = temp[j].substring(temp[j].indexOf('value="') + 7, temp[j].length);
              textVal.push(temp[j].substring(0, temp[j].indexOf('">')));
            }
            values.push(checkVal);
            values.push(textVal);

            this.datas.push(
              {
                type: 2,
                data: values
              }
            )
          } /*else if (result.noteContents[i].indexOf('IMG') >= 0) {
            let values = [];
            fetch('http://13.125.95.210:85/java/WriteNoteInfo' + this.noteId)
              .then(result => result.json())
              .then(result => {

                this.images = result;
                this.resultImages = result;

                console.log(this.images);
                console.log(this.resultImages);
              })
              .catch(err => console.log(err))
          }*/
        }
        this.$forceUpdate();
      })
  },
  methods: {
    CreArea: function (e) {
      this.childOrder.push('textBox');
      this.textAmount++;
    },
    creTableBox: function (e) {
      this.childOrder.push('tableBox');
      this.textAmount++;
    },
    creCheckbox: function (e) {
      this.childOrder.push('checkboxBox');
      this.textAmount++;
    },
    creImgBox: function (e) {
      this.childOrder.push('imgBox');
      this.textAmount++;
    },
    showCheckBox(e) {
      this.show = !this.show;
    },
      /*saveNote: function (e) {
      let lineAll = document.querySelectorAll('.write_fn');
      let contents = [];
      console.log(contents);
      //작성되는 거 구분해서 객체화
      for (let i = 0; i < lineAll.length; i++) {
        let lineValue = '';

        if (lineAll[i].querySelector('textarea') != undefined) {
          lineType = 'TEXT';
          lineValue = lineAll[i].querySelector('textarea').value;
          //<태그 자체를 저장>
          textTag = '<textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" v-on:keydown.shift="shiftfDown($event)" v-on:keydown.enter="creTextarea($event)">' + lineValue + '</textarea>'

        } else if (lineAll[i].querySelector('table') != undefined) {
          lineType = 'TABLE';
          lineValue = lineAll[i].querySelector('.maked_table');
          tableTag = `<div class='table_container'>
                                <button class='row_addbtn' ><img src="@/assets/img/note/down_arrow.png" @click="addRow" @mouseover="changeShow"></button>
                                <table class='maked_table'>`
          // tr 행 반복
          let lineTr = lineValue.querySelectorAll('tr');
          lineValue = [];
          for (let j = 0; j < lineTr.length; j++) {
            let temp = [];
            tableTag += `<tr class='item' > 
                                    <td class="row-button-container" @mouseover="showBtn">
                                        <button class='row_delbtn'  v-if="btnActive==true"><img src="@/assets/img/note/trash.png" @click="delRow($event)" @mouseout="hideBtn"></button>
                                    </td>`
            // td 열 반복
            let lineTd = lineTr[j].querySelectorAll('td input');

            for (let k = 0; k < lineTd.length; k++) {
              //temp.push(lineTd[k].value);
              tableTag += ` <td class='item_td'><input width="100px" type="text" class="input_text" value="` + lineTd[k].value + `"></td>`
            };
            tableTag += `</tr>`
          }
          tableTag += `
                </table>
                <button class='col_addbtn'><img src="@/assets/img/note/right_arrow.png" @click="addCol($event)"></button> 
                </div>
                `
        } else if (lineAll[i].querySelector('input[type="checkbox"]') != undefined) {
          lineType = 'CHECK';
          lineValue = [];
          let checkBoxList = lineAll[i].querySelectorAll('.check_box_list');
          checkBoxTag = `<div class='check_box_list'>`;

          for (let j = 0; j < checkBoxList.length; j++) {

            let lineCheckbox = checkBoxList[j].querySelector('.noteCheckbox');
            let lineCheckText = checkBoxList[j].querySelector('.checkbox_text').value;
            let isChecked = lineCheckbox.checked;
            checkBoxTag += `
                                <input type='checkbox' class='noteCheckbox' value="`+ isChecked + `" name="myCheck"><input type="text" class="checkbox_text" name="myCheck" value="`
              + lineCheckText + `">
                                <div class="checkbox_button_container">
                                    <button class="add_checkbox"><img src="@/assets/img/note/plus.png" class="add_img" @click="addCeheckList"></button>
                                    <button class="del_checkbox"><img src="@/assets/img/note/minus.png" class="del_img" @click="delCeheckList"></button>
                                </div>
                                `
            lineValue.push({
              status: isChecked,
              text: lineCheckText
            });
          };
          checkBoxTag += `</div>`
        }
      };
      //작성한 내용 보내기
      let title = document.querySelector('.note_title').value;
      let fetchData = {
        "title": title,
        "noteContents": contents,
        "email": sessionStorage.getItem("email")
      }
      fetch('http://13.125.95.210:85/java/MyNoteInfo', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(fetchData)
      }).then(result => {
        // console.log(result);
        this.$router.push({ name: "MynoteList" });
      })
    }*/
  },
  components: { CreTextarea, CreateLine }
}

</script>
<style scoped src="@/assets/css/note/ReadOnly.css"/>