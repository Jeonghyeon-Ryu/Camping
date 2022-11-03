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
                    <button type="submit" class="save_btn" @click="updateNote($event)">수정</button>
                </div>
            </div>
            <div class="write_region">
                <div id="pdfDiv">
                    <input placeholder="제목" class="note_title" :value=title>
                    <div class="sortable">
                        <template v-if="datas">
                            <template v-for="item of datas">
                                <CreateLine :type="item.type" :data="item.data" :storedImages="storedImages"
                                    @creArea="CreArea($event)" @changeImage="changeImage"></CreateLine>
                            </template>
                        </template>
                        <template v-for="(child, i) of childOrder" :key="i">
                            <CreTextarea :type="childOrder[i]" @creArea="CreArea($event)" @saveImg="saveImg"
                                v-if="textAmount >= i + 1">
                            </CreTextarea>
                        </template>
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
            title: '',
            textAmount: 1,
            childOrder: ['textBox'],
            noteInfo: [],
            noteId: this.$route.params.noteId,
            showInfo: {},
            datas: [],
            images: [],
            storedImages: []

        }
    },
    created() {
        window.scrollTo(0,0);
        // this.noteId = this.$router.params.myNoteId;
        fetch("http://13.125.95.210:85/java/GoMyNote/" + this.noteId)
            .then(result => result.json())
            .then(result => {
                this.title = result.title;
                console.log('=============기존에 작성했던 정보======================')
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
                    } else if (result.noteContents[i].indexOf('imgPath') >= 0) {
                        //저장된 이미지 이름, 경로 가져오기
                        let temp = result.noteContents[i];
                        let values = [];
                        console.log('tempIndexOf>>', temp.indexOf("imgPath:"))

                        // while (temp.indexOf("imgPath") != -1) {
                        for (let k = 0; k < 3; k++) {
                            if (temp.indexOf('imgPath') >= 0) {
                                let imgPathIndex = temp.indexOf("imgPath:");
                                let storedNameIndex = temp.indexOf("|storedName:");
                                console.log('tempIndexOf>>', storedNameIndex)
                                let imgPathValue = temp.substring(imgPathIndex + 8, storedNameIndex);
                                let storedNameValue = '';
                                temp = temp.substring(storedNameIndex, temp.length);
                                console.log('tempIndexOf>>', imgPathValue)
                                storedNameValue = temp.substring(12, temp.indexOf('$'));
                                console.log('tempIndexOf>>', storedNameValue)

                                temp = temp.substring(temp.indexOf('$'), temp.length);
                                console.log('tempIndexOf>>', temp)
                                values.push(
                                    {
                                        imgPath: imgPathValue,
                                        storedName: storedNameValue
                                    }
                                )
                            }

                        }
                        this.datas.push(
                            {
                                type: 3,
                                data: values
                            }
                        )
                    }
                }
                this.$forceUpdate();
            });
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
        changeImage(images) {
            this.images = images;
        },
        updateNote: function (e) {
            let lineAll = document.querySelectorAll('.write_fn');
            let lineText = [];
            let lineTable = [];
            let textTag;
            let tableTag;
            let checkBoxTag;
            let imgTag;
            let contents = [];

            //작성되는 거 구분해서 객체화
            for (let i = 0; i < lineAll.length; i++) {
                let lineValue = '';

                if (lineAll[i].querySelector('textarea') != undefined) {
                    lineValue = lineAll[i].querySelector('textarea').value;
                    //<태그 자체를 저장>
                    textTag = '<textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" v-on:keydown.shift="shiftfDown($event)" v-on:keydown.enter="creTextarea($event)" value="' + lineValue + '"></textarea>'
                    contents.push(textTag);
                } else if (lineAll[i].querySelector('table') != undefined) {
                    lineValue = lineAll[i].querySelector('.maked_table');
                    tableTag = `<div class='table_container'>
                                    <button class='row_addbtn' ><img src="@/assets/img/note/down_arrow.png" @click="addRow" @mouseover="changeShow"></button>
                                    <table class='maked_table'>`
                    // tr 행 반복
                    let lineTr = lineValue.querySelectorAll('tr');
                    lineValue = [];
                    for (let j = 0; j < lineTr.length; j++) {
                        // td 열 반복
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
                    let checkBoxList = lineAll[i].querySelectorAll('input[type="checkbox"]');
                    let checkBoxText = lineAll[i].querySelectorAll('input[type="text"]');
                    
                    checkBoxTag = `<div class='check_box_list'>`;   
                    for (let j = 0; j < checkBoxList.length; j++) {
                        //let lineCheckbox = checkBoxList[j].querySelector('.noteCheckbox');
                        let lineCheckText = checkBoxText[j].value;
                        //console.log(">>>>>>lineCheckbox", lineCheckbox)
                        console.log("lineCheckText>>>>>>", lineCheckText)

                        let isChecked = checkBoxList[j].checked;
                        console.log("isChecked>>>>>>", isChecked)
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
                    contents.push(checkBoxTag);lineValue = [];
                    //이미지
                } else if (lineAll[i].querySelector('.image-preview-div') != undefined) {
                    console.log('>>>>', (lineAll[i].querySelector('.image-preview-div')));
                    let imgs = lineAll[i].querySelectorAll('.image-preview-div');
                    let newImgCount = 0;
                    let imgValue = '';
                    let imgValues = '';

                    // let temp = 'IMG:'
                    for (let img of imgs) {
                        if (img.getAttribute("id").indexOf('b') >= 0) {
                            newImgCount++;
                        } else if (img.getAttribute("id").indexOf('a') >= 0) { //기존 파일(id=a)이라면
                            //let imgSrcs = img.querySelectorAll('img');
                            //console.log(imgs.length);

                            let imgSrc = img.querySelector('img').getAttribute('src');

                            //console.log(imgSrc.indexOf('GoMyNote/'))
                            let temp = imgSrc.substring(imgSrc.indexOf('GoMyNote/') + 9, imgSrc.length)
                            let imgPath = temp.substring(0, temp.indexOf('/'));

                            temp = temp.substring(temp.indexOf('/'), temp.length);

                            let storedName = temp.substring(1, temp.length);
                            imgValue += 'imgPath:' + imgPath + '|storedName:' + storedName + '$';
                        }
                    } 
                    imgValue += newImgCount;
                    contents.push(imgValue);
                }
            };
            //작성한 DB에 저장(수정버튼)
            let title = document.querySelector('.note_title').value;
            let noteId = this.noteId;
            let formData = new FormData();
            for (let image of this.images) {
                formData.append("files", image);
            }

            formData.append("title", title);
            formData.append("noteId", noteId);
            formData.append("noteContents", contents);
            formData.append("email", this.$store.state.email);
            formData.forEach((value, key) => {
                console.log(key + " : ", value);
            })

            fetch('http://13.125.95.210:85/java/UpdateNoteInfo', {
                method: 'POST',
                headers: {},
                body: formData
            }).then(result => {
                console.log("이미지 fetch 결과")
                console.log(result);
                this.$router.push({
                    name: "MynoteList"
                })
            })

        },
        saveImg(images) {
            let dt = new DataTransfer();
            for (let i = 0; i < images.length; i++) {
                dt.items.add(images[i]); //kind와 type
            }
            for (let i = 0; i < this.images.length; i++) {
                dt.items.add(this.images[i]);
            }
            this.images = dt.files;
            console.log('image ', this.images);
        }
    },
    components: { CreTextarea, CreateLine }
}

</script>
<style scoped src="@/assets/css/note/WriteNote.css">

</style>