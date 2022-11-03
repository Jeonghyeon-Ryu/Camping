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
  },
  components: { CreTextarea, CreateLine }
}

</script>
<style scoped src="@/assets/css/note/ReadOnly.css"/>