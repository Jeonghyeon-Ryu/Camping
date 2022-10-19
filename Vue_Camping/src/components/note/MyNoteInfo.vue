<template>

    <div class="mynote_container">
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
                            ....
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
                    <input placeholder="제목" class="note_title" :value=title>
                    <div class="sortable">
                        <template v-if="datas">
                            <CreateLine v-for="item of datas" :type="item.type" :data="item.data"
                                @creArea="CreArea($event)"></CreateLine>
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
    data() {
        return {
            title : '',
            textAmount: 1,
            childOrder: ['textBox'],
            noteInfo: [],
            noteId: this.$route.params.noteId,
            showInfo: {},
            datas: []
        }
    },
    created() {
        //this.noteId = this.$router.params.myNoteId;

        fetch("http://localhost:8087/java/GoMyNote/" + this.noteId)
            .then(result => result.json())
            .then(result => {
                this.title = result.title;
                console.log(result);
                for (let i = 0; i < result.noteContents.length; i++) {
                    //가져온 값을 textarea에 뿌려주기
                    if (result.noteContents[i].indexOf('textarea') >= 0) {
                        let temp = result.noteContents[i].substring(result.noteContents[i].indexOf('value="') + 7, result.noteContents[i].indexOf('"></textarea>'));
                        //console.log(result.noteContents[i])
                        
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
                            //console.log(rowData);
                            while (temp[j].indexOf('value="') >= 0) {
                                temp[j] = temp[j].substring(temp[j].indexOf('value="') + 7, temp[j].length);
                                rowData.push(temp[j].substring(0, temp[j].indexOf('">'))); 
                            }
                            tempData.push(rowData);
                            //console.log(tempData);
                        }
                        
                        this.datas.push(
                            {
                                type: 1,
                                data: tempData
                            }
                        )
                        console.log(tempData);
                    }
                }
                //console.log('내가출력 : ', this.datas);
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
                }
            };
            //작성한 내용 보내기
            let title = document.querySelector('.note_title').value;
            let fetchData = {
                "title": title,
                "noteContents": contents,
                "email": sessionStorage.getItem("email")
            }
            console.log(fetchData);

            fetch('http://localhost:8087/java/WriteNote', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(fetchData)
            }).then(result => {
                console.log(result);
                this.$router.push({ name: "MynoteList" });

            })


        }
    },
    components: { CreTextarea, CreateLine }
}

</script>
<style scoped src="@/assets/css/note/WriteNote.css">

</style>