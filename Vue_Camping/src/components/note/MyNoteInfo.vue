<template>

  <div class="mynote_container">
      <nav class="mynote_menu">
          <div class="use_method">이용 방법</div>
          <div class="note_mine">내가 작성한 노트</div>
          <div class="invited_note">내가 초대받은 노트</div>
          <div class="write_note">노트 작성하기</div>
      </nav>

      <div class="mynote_region">
          <div class="write_menu">
              <div class="fn_menu">
                  <div class="add_fn_container">
                      <div class="cre_table"><img src="@/assets/img/note/table.png" @click="creTableBox($event)">
                      </div>
                      <div class="cre_checkbox"><img src="@/assets/img/note/checkList.png"
                              @click="creCheckbox($event)"></div>
                      <div class="cre_textbox"><img src="@/assets/img/note/textbox.png"></div>
                      <div class="img_capture"><img src="@/assets/img/note/img_capture.png"
                              @click="creImgBox($event)"></div>
                  </div>
                  <div class="text_fn_container">
                      <div class="text_highlighter"></div>
                      <div class="highlighter_menu">
                          <button id="bold" class="css_button"><img src="@/assets/img/note/bold.png"></button>
                          <button id="italic" class="css_button"><img src="@/assets/img/note/italic.png"> </button>
                          <button id="underLine" class="css_button"><img
                                  src="@/assets/img/note/unerline.png"></button>
                      </div>
                      <div class="text_sort"></div>
                      <div class="sort_menu">
                          <button id="justifyLeft" class="css_button"><img
                                  src="@/assets/img/note/align_left.png"></button>
                          <button id="justifyCenter" class="css_button"><img
                                  src="@/assets/img/note/align_center.png"></button>
                          <button id="justifyRight" class="css_button"><img
                                  src="@/assets/img/note/align_right.png"></button>
                      </div>
                      <div class="text_font"></div>
                      <select id="fontName">
                          <option value="">글꼴 선택</option>
                          <option value="돋움">돋움</option>
                          <option value="굴림">굴림</option>
                          <option value="궁서">궁서</option>
                          <option value="바탕">바탕</option>
                          <option value="맑은 고딕">맑은 고딕</option>
                      </select>
                      <div class="text_size"></div>
                      <select id="fontSize">
                          <option value="">글자 크기</option>
                          <option value="1">4px</option>
                          <option value="2">8px</option>
                          <option value="3">10px</option>
                          <option value="4">12px</option>
                          <option value="5">16px</option>
                          <option value="6">20px</option>
                          <option value="7">30px</option>
                      </select>
                      <div id="font_color"></div>
                      <select id="foreColor">
                          <option value="#000000" data-color="#000000">black</option>
                          <option value="#808080" data-color="#808080">Gray</option>
                          <option value="#A9A9A9" data-color="#A9A9A9">Darkgray</option>
                          <option value="#D3D3D3" data-color="#D3D3D3">lightgray</option>
                          <option value="#C0C0C0" data-color="#C0C0C0">silver</option>

                          <option value="#FF0000" data-color="#FF0000">red</option>
                          <option value="#FF69B4" data-color="#FF69B4">hotpink</option>
                          <option value="#FF4500" data-color="#FF4500">orangered</option>
                          <option value="#EE82EE" data-color="#EE82EE">violet</option>
                          <option value="#FFB6C1" data-color="#FFB6C1">lightPink</option>

                          <option value="#0000FF" data-color="#0000FF">blue</option>
                          <option value="#000080" data-color="#000080">navy</option>
                          <option value="#87CEEB" data-color="#87CEEB">skyblue</option>
                          <option value="#87CEFA" data-color="#87CEFA">lightskyblue</option>
                          <option value="#E0FFFF" data-color="#E0FFFF">LightCyan</option>

                          <option value="#FFFF00" data-color="#FFFF00">yellow</option>
                          <option value="#FFD700" data-color="#FFD700">gold</option>
                          <option value="#F5DE83" data-color="#F5DE83">wheat</option>
                          <option value="#FAFAD2" data-color="#FAFAD2">LightGold</option>
                          <option value="#FFFFFF" data-color="#FFFFFF">white</option>

                          <option value="#A0522D" data-color="#A0522D">sienna</option>
                          <option value="#CD5C5C" data-color="#CD5C5C">indianred</option>
                          <option value="#B8860B" data-color="#B8860B">darkgoldenrod</option>
                          <option value="#32CD32" data-color="#32CD32">limegreen</option>
                          <option value="#48D1CC" data-color="#48D1CC">mediumturquoise</option>

                          <option value="#006400" data-color="#006400">DarkGreen</option>
                          <option value="#CD5C5C" data-color="#CD5C5C">indianred</option>
                          <option value="#6495ED" data-color="#6495ED">cornflowerblue</option>
                          <option value="#DC143C" data-color="#DC143C">crimson</option>
                          <option value="#C71585" data-color="#C71585">mediumvioletred</option>
                      </select>
                  </div>
                  <button type="button" class="btn btn-primary" id="savePdf"><img
                          src="@/assets/img/note/save_pdf.png"></button>
                  <button type="submit" class="save_btn" @click="saveNote($event)">저장</button>
              </div>
          </div>
          <div class="write_region">
              <div id="pdfDiv">
                  <input placeholder="제목" class="note_title">
                  <div class="sortable">
                      <CreateLine v-for="item of datas" :type="0" :data="yyy"></CreateLine>
                      
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
  data() {
      return {
          textAmount: 1,
          isEditable: true,
          childOrder: ['textBox'],
          noteInfo : [],
          noteId : this.$route.params.noteId,
          showInfo : {},
          datas : [
            {
                type : '0',
                data : ''
            }
        ]
      }
  },
  created() {
      console.log(this.noteId);
      //this.noteId = this.$router.params.myNoteId;

     fetch("http://localhost:8087/java/GoMyNote/" + this.noteId)
      .then(result => result.json())
      .then(result=>{
        console.log('fetch data ', result);
        for(let i=0; i<result.noteContents.length; i++){
            if(result.noteContents[i].indexOf('textarea')>=0){
                this.datas[i].type=0;
                let temp = result.noteContents[i].substring(result.noteContents[i].indexOf('value="')+7,result.noteContents[i].indexOf('"></textarea>'));
                console.log(temp);
                this.datas[i].data=temp;
            }
        }
        console.log('내가출력 : ', this.datas);
        this.$forceUpdate();





        /********************************************************* */
        this.showInfo = result; 
/****************
       // console.log(this.showInfo.noteContents[0])
        for(let i=0; i<this.showInfo.noteContents.length; i++){ 
          
          let sortable = document.querySelector('.sortable');
          //let btnContainer = sortable.querySelector('.btn_container');
          
          let writeFn = document.createElement('div');
          writeFn.classList.add('write_fn');
          writeFn.innerHTML = `<div class="left_container">
          <div class="btn_container">
              <div class="drag_btn"><img class="drag_img" src="@/assets/img/note/drag.png"> </div>
              <div class="del_line"><img class="dleLine_img" src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
          </div>
          </div>  `+ this.showInfo.noteContents[i];
          sortable.append(writeFn);            
          
          let title = document.querySelector('.note_title')
          title.value = this.showInfo.title;
          
        //   for(let i=0; i<this.showInfo.noteContents.length; i++){ 
        //     console.log(this.showInfo.noteContents[i].querySelectorAll('.write_place'));
        //   }
          
          //-------공통생성 태그 : style적용
          let btnContainer = document.querySelectorAll('.btn_container');
         
          
          writeFn.setAttribute("style","cursor: pointer; display: inline-block; position: relative; display: flex; flex-direction: row; margin-bottom: 10px; border: none; height: fit-content;");
          //모든 btn_container에 스타일  
          for(let i=0; i<btnContainer.length; i++ ){
            btnContainer[i].setAttribute("style", "display: flex; flex-direction: row; opacity: 0.6; transition-duration: 0.5s;")
          }
          //각 버튼에 스타일
          let dragBtn = document.querySelectorAll('.drag_btn');
          let dragImg = document.querySelectorAll('.drag_img');
          let delLineBtn = document.querySelectorAll('.del_line');
          for(let i=0; i<dragBtn.length; i++ ){
            dragBtn[i].setAttribute("style", "width: 25px;height: 25px; font-size: 5px; cursor: pointer;");
            //dragImg[i].setAttribute("src", "/src/assets/img/note/drag.png;");
            delLineBtn[i].setAttribute("style", "width: 25px;height: 25px; font-size: 5px; cursor: pointer;");
          }
          
          //------생성되는 textarea css적용
          let writePlace = document.querySelectorAll('.write_place');
         for(let i=0; i<writePlace.length; i++){ 
            writePlace[i].setAttribute("style", "display: inline-block; height: fit-content; width: 90%; border : 1px solid lightgray");
         }

         //------생성되는 테이블 css적용
            //maked_table_place 
        let tablePlace = document.querySelectorAll('.maked_table_place');
        for(let i=0; i<tablePlace.length; i++){ 
            tablePlace[i].setAttribute("style", "margin-bottom: 20px;");
        }
            //table_container
        let tableContainer = document.querySelectorAll('.table_container');
        for(let i=0; i<tableContainer.length; i++){ 
            tableContainer[i].setAttribute("style", "display: flex; position: relative; width: fit-content;");
        }
            //col_add, row_add
        let rowAdd = document.querySelectorAll('.row_addbtn');
        let colAdd = document.querySelectorAll('.col_addbtn');
        for(let i=0; i<rowAdd.length; i++){ 
            rowAdd[i].setAttribute("style", "position: absolute;bottom: -30px;height: 30px;width: 75%;left: 11%;background-color: transparent; border: none; opacity: 0.6; transition-duration: 0.5s;");
            rowAdd[i].setAttribute("style", "right: 0;border: none;background-color: transparent;opacity: 0.6;transition-duration: 0.5s; padding: 10px;");
        }
            //maked_table
        let makedTable = document.querySelectorAll('.maked_table');
        for(let i=0; i<makedTable.length; i++){ 
            makedTable[i].setAttribute("style", "border-collapse: collapse; max-width: 800px; height: 10%;");
        }
            // td
        let td = document.querySelectorAll('.maked_table td');
        for(let i=0; i<td.length; i++){ 
            td[i].setAttribute("style", "border: 2px solid lightgray;");
        }
        let itemTd = document.querySelectorAll('.itme_td');
        for(let i=0; i<td.length; i++){ 
            td[i].setAttribute("style", "border: 2px solid lightgray;");
        }
        console.log(td);
        }  
      })
***********************/
      // fetch(`http://localhost:8087/java/GoMyNote/${noteId}`, {
      //         method: "GET",
      //         headers: { "Content-Type": "application/json" },
      //     })
      //         .then((response) => response.json())
      //         .then(data => {
      //             //console.log(data.noteId);
      //             //console.log(this.myNoteId);
              
      //         this.noteInfo = data;
      //         console.log(noteInfo);
      //     }).catch(err => console.log(err));
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
      saveNote: function (e) {
          let lineAll = document.querySelectorAll('.write_fn');
          let lineText = [];
          let lineTable = [];
          let textTag;
          let tableTag;
          let checkBoxTag;
          let contents = [];

          //작성되는 거 구분해서 객체화
          for (let i = 0; i < lineAll.length; i++) {
              let lineType = '';
              let lineValue = '';
              let linePosition = i;
  
              if (lineAll[i].querySelector('textarea') != undefined) {
                  lineType = 'TEXT';
                  lineValue = lineAll[i].querySelector('textarea').value;
                  //<태그 자체를 저장>
                  
                  textTag = '<textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" v-on:keydown.shift="shiftfDown($event)" v-on:keydown.enter="creTextarea($event)">' + lineValue + '</textarea>'

                  console.log(textTag);
                  contents.push(textTag);

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
                          tableTag += ` <td class='item_td'><input width="100px" type="text" class="input_text" value="`+ lineTd[k].value +`"></td>`
                      };
                      tableTag += `</tr>`
                  }
                  tableTag +=`
                  </table>
                  <button class='col_addbtn'><img src="@/assets/img/note/right_arrow.png" @click="addCol($event)"></button> 
                  </div>
                  `
                  console.log(tableTag);
                  contents.push(tableTag);


              } else if (lineAll[i].querySelector('input[type="checkbox"]') != undefined) {
                  lineType = 'CHECK';
                  lineValue = [];
                  let checkBoxList = lineAll[i].querySelectorAll('.check_box_list');
                  checkBoxTag = `
                                    <div class='check_box_list'>
                                    `;

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
                  console.log(checkBoxTag);
                  contents.push(checkBoxTag);
              }
          };
          //작성한 내용 보내기
          let title = document.querySelector('.note_title').value;
          let fetchData = {
              "title" : title,
              "noteContents" : contents,
              "email" : localStorage.getItem("email")
          }
          console.log(fetchData);

          fetch('http://localhost:8087/java/WriteNote',{
              method : 'POST',
              headers :{
                  'Content-Type' :'application/json' 
              },
              body : JSON.stringify(fetchData)
          }).then(result => { 
              console.log(result);
              this.$router.push({name:"MynoteList"});
              
          })
          
          
      }
      },
       components : { CreTextarea, CreateLine }
  }

</script>
<style scoped src="@/assets/css/note/WriteNote.css"></style>