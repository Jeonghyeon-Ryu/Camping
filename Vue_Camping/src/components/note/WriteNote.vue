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
                            <option value="">?????? ??????</option>
                            <option value="??????">??????</option>
                            <option value="??????">??????</option>
                            <option value="??????">??????</option>
                            <option value="??????">??????</option>
                            <option value="?????? ??????">?????? ??????</option>
                        </select>
                        <div class="text_size"></div>
                        <select id="fontSize">
                            <option value="">?????? ??????</option>
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
                    <button type="submit" class="save_btn" @click="saveNote($event)">??????</button>
                </div>
            </div>
            <div class="write_region">
                <div id="pdfDiv">
                    <input placeholder="??????" class="note_title">
                    <div class="sortable">
                        <!--<template v-if="datas">
                            <CreateLine v-for="item of datas" :type="item.type" :data="item.data"
                                @creArea="CreArea($event)"></CreateLine>
                        </template>-->
                        <template v-for="(child, i) of childOrder" :key="i">
                            <CreTextarea :type="child" @saveImg="saveImg" @creArea="CreArea($event)"
                                v-if="textAmount >= i + 1">
                            </CreTextarea>
                        </template>
                        <!--<CreTextarea :type="childOrder[1]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 2">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[2]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 3">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[3]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 4">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[4]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 5">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[5]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 6">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[6]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 7">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[7]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 8">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[8]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 9">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[9]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 10">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[10]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 11">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[11]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 12">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[12]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 13">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[13]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 14">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[14]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 15">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[15]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 16">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[16]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 17">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[17]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 18">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[18]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 19">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[19]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 20">
                        </CreTextarea>
                        <CreTextarea :type="childOrder[20]" @saveImg="saveImg" @creArea="CreArea($event)"
                            v-if="textAmount >= 21">
                        </CreTextarea>-->
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


export default {
    data() {
        return {
            textAmount: 1,
            isEditable: true,
            childOrder: ['textBox'],
            noteInfo: [],
            noteId: this.$route.params.noteId,
            showInfo: {},
            images: []
        }
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
            let imgTag;
            let contents = [];
            console.log(contents);
            //???????????? ??? ???????????? ?????????
            for (let i = 0; i < lineAll.length; i++) {
                let lineValue = '';

                if (lineAll[i].querySelector('textarea') != undefined) {
                    lineValue = lineAll[i].querySelector('textarea').value;
                    //<?????? ????????? ??????>
                    textTag = '<textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" v-on:keydown.shift="shiftfDown($event)" v-on:keydown.enter="creTextarea($event)" value="' + lineValue + '"></textarea>'
                    contents.push(textTag);
                } else if (lineAll[i].querySelector('table') != undefined) {
                    lineValue = lineAll[i].querySelector('.maked_table');
                    tableTag = `<div class='table_container'>
                                    <button class='row_addbtn' ><img src="@/assets/img/note/down_arrow.png" @click="addRow" @mouseover="changeShow"></button>
                                    <table class='maked_table'>`
                    // tr ??? ??????
                    let lineTr = lineValue.querySelectorAll('tr');
                    lineValue = [];
                    for (let j = 0; j < lineTr.length; j++) {
                        // td ??? ??????
                        let lineTd = lineTr[j].querySelectorAll('td input');
                        tableTag += `<tr class='item' > 
                                        <td class="row-button-container" @mouseover="showBtn">
                                            <button class='row_delbtn'  v-if="btnActive==true"><img src="@/assets/img/note/trash.png" @click="delRow($event)" @mouseout="hideBtn"></button>
                                        </td>`
                        for (let k = 0; k < lineTd.length; k++) {
                            tableTag += ` <td class='item_td'><input width="100px" type="text" class="input_text" value="` + lineTd[k].value + `"></td>`
                        };
                        tableTag += `</tr>`
                    }
                    tableTag += `</table>
                                <button class='col_addbtn'><img src="@/assets/img/note/right_arrow.png" @click="addCol($event)"></button> 
                                </div>`;
                    contents.push(tableTag);
                } else if (lineAll[i].querySelector('input[type="checkbox"]') != undefined) {
                    lineValue = [];
                    let checkBoxList = lineAll[i].querySelectorAll('.check_box_list');
                    checkBoxTag = `<div class='check_box_list'>`;
                    for (let j = 0; j < checkBoxList.length; j++) {
                        let lineCheckbox = checkBoxList[j].querySelector('.noteCheckbox');
                        let lineCheckText = checkBoxList[j].querySelector('.checkbox_text').value;
                        let isChecked = lineCheckbox.checked;
                        checkBoxTag += `<input type='checkbox' class='noteCheckbox' name="myCheck" value="` + isChecked + `"><input type="text" class="checkbox_text" name="myCheck" value="` + lineCheckText + `">
                                            <div class="box_container">
                                                <div class="checkbox_button_container">
                                                    <button class="add_checkbox"><img src="@/assets/img/note/plus.png" class="add_img" @click="addCeheckList"></button>
                                                    <button class="del_checkbox"><img src="@/assets/img/note/minus.png" class="del_img" @click="delCeheckList"></button>
                                                </div>
                                            </div>`
                        contents.push({
                            status: isChecked,
                            text: lineCheckText
                        });
                    };
                    checkBoxTag += `</div>`
                    //???????????? content
                    contents.push(checkBoxTag);
                    //?????????
                } else if (lineAll[i].querySelector('.used-insert-image-preview') != undefined) {
                    
                    let imgContainer = lineAll[i].querySelector('.img_container');
                    let imgBox = imgContainer.querySelectorAll('.image-preview-div');
                    let imgCount = imgBox.length;

                    contents.push(
                        'IMG:' + imgCount
                    );
                    console.log("contents??? imgCount??? ??? ???????????? ??????")
                    console.log(contents);
                    //console.log("contents");
                    //console.log(contents);     
                }
            };
            //????????? ?????? ?????????
            let title = document.querySelector('.note_title').value;
            let formData = new FormData();
            for (let image of this.images) {
                formData.append("files", image);
            }
            formData.append("title", title);
            formData.append("noteContents", contents);
            formData.append("email", this.$store.state.email);
            formData.forEach((value, key) => {
                console.log(value);
            })
            fetch('http://localhost:8087/java/WriteNoteInfo', {
                method: 'POST',
                headers: {},
                body: formData
            }).then(result => {
                console.log("insert fetch ??????")
                console.log(result);
                this.$router.push({
                    name: "MynoteList"
                })
            })
        },
        //???????????? ????????? ?????? ????????????
        saveImg(images) {
            let dt = new DataTransfer();
            for (let i = 0; i < images.length; i++) {
                dt.items.add(images[i]); //kind??? type
            }
            for (let i = 0; i < this.images.length; i++) {
                dt.items.add(this.images[i]);
            }
            this.images = dt.files;
            console.log('image ', this.images);
        }
    },
    components: { CreTextarea }
}

</script>
<style scoped src="@/assets/css/note/WriteNote.css">

</style>

