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
                          <div class="cre_table"><img src="@/assets/img/note/table.png" @click ="creTableBox($event)"></div>
                          <div class="cre_checkbox"><img src="@/assets/img/note/checkList.png" @click="creCheckbox($event)"></div>
                          <div class="cre_textbox"><img src="@/assets/img/note/textbox.png"></div>
                          <div class="img_capture"><img src="@/assets/img/note/img_capture.png" @click="creImgBox($event)"></div>
                      </div>
                      <div class="text_fn_container">
                          <div class="text_highlighter"></div>
                          <div class="highlighter_menu">
                              <button id="bold" class="css_button"><img src="@/assets/img/note/bold.png"></button>
                              <button id="italic" class="css_button"><img src="@/assets/img/note/italic.png"> </button>
                              <button id="underLine" class="css_button"><img src="@/assets/img/note/unerline.png"></button>
                          </div>
                          <div class="text_sort"></div>
                          <div class="sort_menu">
                              <button id="justifyLeft" class="css_button"><img src="@/assets/img/note/align_left.png"></button>
                              <button id="justifyCenter" class="css_button"><img src="@/assets/img/note/align_center.png"></button>
                              <button id="justifyRight" class="css_button"><img src="@/assets/img/note/align_right.png"></button>
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
                      <button type="button" class="btn btn-primary" id="savePdf"><img src="@/assets/img/note/save_pdf.png"></button>
                  </div>
              </div>
              <div class="write_region">
                  <div id="pdfDiv">
                      <div placeholder="제목" class="note_title" contenteditable="true"></div>
                      <div class="sortable">
                          <CreTextarea :type="childOrder[0]" @creArea="CreArea($event)" v-if="textAmount >= 1"></CreTextarea>
                          <CreTextarea :type="childOrder[1]" @creArea="CreArea($event)" v-if="textAmount >= 2"></CreTextarea>
                          <CreTextarea :type="childOrder[2]" @creArea="CreArea($event)" v-if="textAmount >= 3"></CreTextarea>
                          <CreTextarea :type="childOrder[3]" @creArea="CreArea($event)" v-if="textAmount >= 4"></CreTextarea>
                          <CreTextarea :type="childOrder[4]" @creArea="CreArea($event)" v-if="textAmount >= 5"></CreTextarea>
                          <CreTextarea :type="childOrder[5]" @creArea="CreArea($event)" v-if="textAmount >= 6"></CreTextarea>
                          <CreTextarea :type="childOrder[6]" @creArea="CreArea($event)" v-if="textAmount >= 7"></CreTextarea>
                          <CreTextarea :type="childOrder[7]" @creArea="CreArea($event)" v-if="textAmount >= 8"></CreTextarea>
                          <CreTextarea :type="childOrder[8]" @creArea="CreArea($event)" v-if="textAmount >= 9"></CreTextarea>
                          <CreTextarea :type="childOrder[9]" @creArea="CreArea($event)" v-if="textAmount >= 10"></CreTextarea>
                          <CreTextarea :type="childOrder[10]" @creArea="CreArea($event)" v-if="textAmount >= 11"></CreTextarea>
                          <CreTextarea :type="childOrder[11]" @creArea="CreArea($event)" v-if="textAmount >= 12"></CreTextarea>
                          <CreTextarea :type="childOrder[12]" @creArea="CreArea($event)" v-if="textAmount >= 13"></CreTextarea>
                          <CreTextarea :type="childOrder[13]" @creArea="CreArea($event)" v-if="textAmount >= 14"></CreTextarea>
                          <CreTextarea :type="childOrder[14]" @creArea="CreArea($event)" v-if="textAmount >= 15"></CreTextarea>
                          <CreTextarea :type="childOrder[15]" @creArea="CreArea($event)" v-if="textAmount >= 16"></CreTextarea>
                          <CreTextarea :type="childOrder[16]" @creArea="CreArea($event)" v-if="textAmount >= 17"></CreTextarea>
                          <CreTextarea :type="childOrder[17]" @creArea="CreArea($event)" v-if="textAmount >= 18"></CreTextarea>
                          <CreTextarea :type="childOrder[18]" @creArea="CreArea($event)" v-if="textAmount >= 19"></CreTextarea>
                          <CreTextarea :type="childOrder[19]" @creArea="CreArea($event)" v-if="textAmount >= 20"></CreTextarea>
                          <CreTextarea :type="childOrder[20]" @creArea="CreArea($event)" v-if="textAmount >= 21"></CreTextarea>
                      </div>
                  </div>
              </div>
          </div>
        </div>
  
  </template>
  
  <script>
  import $ from 'jquery'
  import "bootstrap"
  import "bootstrap/dist/css/bootstrap.min.css"
  import CreTextarea from './CreTextarea.vue'; 
  
  const isEditable = ref(true);
    const message = ref("hello")


  export default{
    data() { 
      return { 
        textAmount : 1,
        isEditable: true,
        childOrder : ['textBox'],
      }
      console.log(textAmount);
    },
   
    methods: {
            CreArea : function(e){
            this.childOrder.push('textBox');   
            this.textAmount++;
            },
            creTableBox : function(e){ 
                this.childOrder.push('tableBox');
                this.textAmount++;
                
            },
            creCheckbox : function(e){ 
                this.childOrder.push('checkboxBox');
                this.textAmount++;
            },
            creImgBox : function(e){ 
                this.childOrder.push('imgBox');
                this.textAmount++;
            },
            showCheckBox(e) {
                this.show = !this.show;
                console.log(this.show);
            },
        
        },
        components: { CreTextarea } 
    }
    

  </script>
  
  <style scoped>
  .contenteditable { 
    width: 500px;
    border: 1px solid black;
  }
   
  .mynote_container {
      margin-top: 150px;
      width: 100%;
      min-width: 650px;
  }
  
  .mynote_menu {
      width: 100%;
      border: 1px solid #ddd;
      padding: 10px;
      display: flex;
      align-items: center;
      justify-content: center;
      min-width: 200px;
  }
  
  .mynote_menu>div {
      width: 20%;
      border: 1px solid lightgray;
      padding: 10px;
      margin: 15px;
      text-align: center;
      cursor: pointer;
  }
  
  .mynote_menu>div:hover {
      background-color: rgba(228, 239, 231, 0.7);
  }
  
  .howtouse_region {
      margin-top: 5px;
      width: 100%;
      border: 1px solid lightgray;
  }
  
  .mynote_list {
      display: flex;
      flex-direction: row;
  }
  
  .mynote_list>div {
      padding: 5px 0px 5px 0px;
  }
  
  .mynote_region {
      /*작성 페이지에서 가장 바깥 테두리*/
      margin-top: 5px;
      padding: 10px;
      width: 100%;
      height: 600px;
  
      vertical-align: left;
      display: flex;
      flex-direction: row;
      position: relative;
  }
  .write_menu {
      width: 120px;
      margin-right: 10px;
  }
  .write_region {
      /* position: relative; */
      width: calc(100% - 120px);
  
  }
  .note_title {
      width: 70%;
      height: 50px;
      margin: 5px 10px 10px 10px;
  }
  
  .text_highlighter {
      margin-top: 30px;
  }
  
  .write_fn {
      cursor: pointer;
      display: inline-block;
      position: relative;
      display: flex;
      flex-direction: row;
      margin-bottom: 10px;
      border: none;
      height: fit-content;
  }
  .write_fn>div {
      cursor: pointer
  }
  
  .fn_menu>div {
      width: 100%;
      cursor: pointer;
      padding: 5px;
      display: flex;
      flex-direction: column;
      align-items: center;
  }
  
  .fn_menu {
      border: 1px solid lightgray;
      width: 100%;
      display: flex;
      flex-direction: column;
      top: 30px;
      align-items: center;
  }
  
  .add_fn_container {
      margin-top: 20px;
      width: 100%;
      align-content: center;
  }
  
  .add_fn_container>div {
      margin-bottom: 10px;
      text-align: center;
  }
  
  .text_fn_container {
      display: flex;
      flex-direction: column;
  }
  
  .highlighter_menu {
      display: flex;
      flex-direction: row;
  }
  
  .highlighter_menu>button {
      margin: 0 3px 0 3px;
  }
  
  .sort_menu {
      display: flex;
      flex-direction: row;
      margin-top: 10px;
  }
  
  .sort_menu>button {
      display: flex;
      flex-direction: row;
      margin: 0 3px 0 3px;
  }
  
  .text_font {
      display: flex;
      flex-direction: row;
      margin-top: 10px;
  }
  
  .text_size {
      margin-top: 10px;
  }
  
  select {
      -moz-appearance: none;
      -webkit-appearance: none;
      appearance: none;
  
      font-family: "Noto Sansf KR", sans-serif;
      font-size: 1rem;
      font-weight: 400;
      line-height: 1.5;
  
      color: #444;
      background-color: #fff;
  
      padding: .6em 1.4em .5em .8em;
  
      margin-top: 10px;
  
      border: 1px solid #aaa;
      border-radius: .5em;
      box-shadow: 0 1px 0 1px rgba(0, 0, 0, .04);
      width: fit-content;
  }
  
  select:hover {
      border-color: #888;
  }
  
  select:focus {
      border-color: #aaa;
      box-shadow: 0 0 1px 3px rgba(197, 222, 248, 0.7);
      box-shadow: 0 0 0 3px -moz-mac-focusring;
      color: #222;
      outline: none;
  }
  
  select:disabled {
      opacity: 0.5;
  }
  .maked_table_place {
      /*display: flex;*/
      /*position: relative;*/
      margin-bottom: 20px;
  }
  .table_container{ 
      display : flex;
      position: relative;
      width: fit-content;
  }
  .maked_table {
      border-collapse: collapse;
      max-width: 800px;
      height: 10%;   
  }
  .maked_table td {
      border: 2px solid lightgray;
  }
  .item_td{ 
      width: 100px;
      height: fit-content;
  }
  .item_td>input{ 
      border: none;
      width: 90%;
      outline: none;
  }
  .input_text{ 
      width: 100px;
      height: 100%;
  }
  .del_row{ 
      width: 50px;
  }
  .col_addbtn {
      right: 0;
      border: none;
      background-color: transparent;
      opacity: 0.6;
      transition-duration: 0.5s;
      padding: 10px;
  }
  .row_addbtn {
      position: absolute;
      bottom: -30px;
      height: 30px;
      width: 75%;
      left: 11%;
      background-color: transparent;
      border: none;
      opacity: 0.6;
      transition-duration: 0.5s;
  }
  .row-button-container{
      width: 30px;
      border: none !important;
      
  }
  .row_delbtn {
      opacity: 0.6;
      transition-duration: 0.5s;
      background-color: transparent;
      border: none;
      
  }
  .fn_btn {
      font-size: 5px;
      width: 25px;
      height: 25px;
      border: 2px solid lightgray;
  }
  
  .drag_btn {
      width: 25px;
      height: 25px;
      font-size: 5px;
      cursor: pointer;
  }
  
  .del_line {
      width: 25px;
      height: 25px;
      font-size: 5px;
      cursor: pointer;
  }
  
  [contenteditable=true]:empty:before {
      content: attr(placeholder);
      display: block;
  }
  
 
  
  div[contenteditable=true]:focus {
      outline: none;
      background-color: white;
  }
  
  .sortable {
      height: fit-content;
      display: flex;
      flex-direction: column;
  }
  
  .write_place {
      display: inline-block;
      height: fit-content;
  }
  
  .check_box_place {
      display: flex;
      flex-direction: column;
      width: 400px;
  }
  
  .check_box_list {
      display: flex;
  }
  
  .checkbox_text{ 
      margin: 10px 0px 5px 10px;
      border: 1px solid lightgray;
      border-radius: 5%;
  
  }
  .checkbox_button_container{ 
      display : flex;
      margin-left: 5px;
      width: 40px;
  
  }
  .checkbox_button_container>button {
      width: 25px;
      height: 25px;
      opacity: 0.6;
      transition-duration: 0.4s;
      background-color: transparent;
      border: none;
      padding: 10px;
      margin: 0;      
  }
  .fn_btn_place {
      position: relative;
  }
  
  .textbox {
      background-color: beige;
      border: 3px solid beige;
      line-height: 10px;
  }
  .btn_container {
      display: flex;
      flex-direction: row;
      opacity: 0.6;
      transition-duration: 0.5s;
  }
  .btn_active {
      opacity: 0.6 !important;
  }
  
  #savePdf {
      height: fit-content;
      background-color: transparent;
      border: #fff;
  }
  </style>