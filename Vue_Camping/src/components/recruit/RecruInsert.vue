<template>
    <div class="recru-input-container">
        <div class="recru-input-back" >
        </div>
        <div class="recru-input-box">
            <h3>Camping Recruitment</h3>
            <form id="recru-form" role="form" method="post" enctype="multipart/form-data">
                <div class="recru-info-box">
                    <div class="recru-info-car">
                        <p>차량 유무</p>
                        <div class="in-level">
                            <label><input type="radio" v-model="recruInfo.myCar" value="1">있음</label>
                            <label><input type="radio" v-model="recruInfo.myCar" value="0">없음</label>
                        </div>
                    </div>
                </div>
                <div class="recru-info-box">
                    <p>희망 동행자</p>
                    <div class="in-level">
                        <div class="recru-info-sex">
                            <label><input type="radio" v-model="recruInfo.wishSex" value="1">남</label>
                            <label><input type="radio" v-model="recruInfo.wishSex" value="2">여</label>
                            <label><input type="radio" v-model="recruInfo.wishSex" value="0" checked>무관</label>
                        </div>
                        <div class="recru-info-age">
                            <label><input type="checkbox" name="wishAge" value="2">20대</label>
                            <label><input type="checkbox" name="wishAge" value="3">30대</label>
                            <label><input type="checkbox" name="wishAge" value="4">40대</label>
                            <label><input type="checkbox" name="wishAge" value="5">50대 이상</label>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <div class="recru-mygear-header">
                        <span>보유한 장비</span>
                        <button class="btn badge bg-info badge-md " type="button" v-on:click="addGear('recru-mygear-body')">추가</button>                    
                        <button class="btn badge bg-dark badge-md " type="button" @click="getGearList">내 장비 가져오기</button>
                    </div>
                    <ul @click="removeGear" id="recru-mygear-body">
                        <li><input type="text" class="recru-mygear-name" placeholder="장비 이름">
                            <select class="recru-mygear-type">
                                <option value="" selected disabled>장비 분류</option>
                                <option value="텐트">텐트</option>
                                <option value="타프">타프</option>
                                <option value="가구">가구</option>
                                <option value="침구">침구</option>
                                <option value="조리도구">조리도구</option>
                                <option value="조명">조명</option>
                                <option value="수납">수납</option>
                                <option value="공구">공구</option>
                                <option value="냉난방">냉난방</option>
                                <option value="기타">기타</option>
                            </select>
                            <input type="number" class="gear-num recru-mygear-num" placeholder="수량" min="1">
                            <input type="file" class="btn recru-mygear-img img" name="mygear"  style="margin:0 5px;max-width:210px;" >
                        </li>
                    </ul>
                </div>
                <div class="recru-info-box">
                    <div class="recru-needgear-header">
                        <span>필요한 장비</span>
                        <button class="btn badge bg-info badge-md " type="button" v-on:click="addGear('recru-needgear-body')">추가</button>                    
                    </div>
                    <ul @click="removeGear" id="recru-needgear-body">
                        <li><input type="text" class="recru-needgear-name" placeholder="장비 이름">
                            <select class="recru-needgear-type">
                                <option value='' selected disabled>장비분류</option>
                                <option value="tent">텐트</option>
                                <option value="tarp">타프</option>
                                <option value="furniture">가구</option>
                                <option value="bedding">침구</option>
                                <option value="kitchenTool">조리도구</option>
                                <option value="lamp">조명</option>
                                <option value="storage">수납</option>
                                <option value="tool">공구</option>
                                <option value="airCon">냉난방</option>
                                <option value="etc">기타</option>
                            </select>
                            <input type="number" class="recru-needgear-num gear-num" placeholder="수량" min="1">
                            <input type="file" class="btn recru-needgear-img img" name="mygear" style="margin:0 5px;max-width:210px;">
                        </li>
                    </ul>
                </div>
                    
                <hr>
                <div class="recru-info-box">
                    <div class="recru-info-title">
                        <label><span>제목 </span><input type="text" v-model="recruInfo.recruTitle"></label>
                    </div>
                    <div class="recru-info-content">
                        <label><span>내용</span> <textarea v-model="recruInfo.recruContent" cols="30" rows="5"></textarea></label>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <div class="recru-info-mynote">
                        <div>
                            <span>나의 노트</span>
                            <ul v-for="note in myNote" v-bind:key="myNote.noteId">
                                <li class="recru-mynote-select"><input type="radio" v-model="recruInfo.noteId">{{note.noteTitle}}</li>
                            </ul>
                        </div>
                        <div>
                            <button class="write-note-btn" @on="writeNewNote">새 노트 쓰기</button>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <span>여행정보</span>
                    <ul class="recru-box-name">
                        <li class="recru-info-startP">
                            <label>출발지<input type="text" v-model="recruInfo.startingSpot"></label>
                        </li>
                        <li class="recru-info-campP">
                            <label>도착지<input type="text" v-model="recruInfo.campingSpot"></label>
                        </li>
                        <li class="recru-info-number">
                            <label>모집인원 <input type="number" v-model="recruInfo.recru_num" min="1" de></label>
                        </li>
                        <li class="recru-info-day">
                            <label>여행 날짜 <input type="date" class="select-date" v-model="recruInfo.goDate"></label> 
                            ~ <input type="date" class="select-date" v-model="recruInfo.comeDate">
                        </li>
                    </ul>
                </div>
                <div class="recru-info-last">
                    <label><span>마감일</span> <input type="date" v-model="recruInfo.closeDate" class="select-date"></label>
                </div>
                <div class="recru-info-btn" style="text-align: center;">
                    <button class="btn bg-gradient-success btn-md"
                    v-on:click.prevent="uploadContent">등록</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
import { assertPipelineBareFunction } from '@babel/types';
import Swal from 'sweetalert2';
export default{
    data : function(){
      return {
        wishAge :[],
        recruInfo : {  
            writer : 'user2',
            wishSex : 0,
            wishAge : '',
            myCar : 0,
            myGear : '',
            needGear :'',
            recruTitle : '',
            recruContent :'',
            noteId: 0,
            startingSpot: '',
            campingSpot: '',
            recruNum : 0,
            goDate : '',
            comeDate : '',
            closeDate: '',
            },
        files:[],
        myNote:[{
            noteId : 1,
            noteTitle : '노트제목입니다'
            },
            {
            noteId : 2,
            noteTitle : '노트제목입니다'
        }]
      }
    },
    methods : {
        uploadContent : function(event){
                this.setGearList('mygear');
                this.setGearList('needgear');
                this.setWishAge();
                this.addFile(); //파일 배열에 이미지 넣기
                console.log(this.recruInfo)
                
                let recruVO = this.recruInfo;
                //서버를 통해 insert
                fetch('http://localhost:8088/java/recru',{
                    method : "POST",
                    headers : {"Content-Type" : "application/json"},
                    body : JSON.stringify(recruVO )
                }) 
                .then(Response => Response.json())  //json 파싱 
                .then(data => { 
                    console.log(data)
                    this.fileUpload();  //이미지 업로드
                }).catch(err=>console.log(err))
        },
        addFile : function(){
            const imgfiles = document.querySelectorAll('.img');
            this.files.length=1;
            imgfiles.forEach((imgfile) => {           
                if(imgfile.value !=''){
                    this.files.push(imgfile.files[0])
                }
            })
            console.log(this.files)
            
        },
        fileUpload :  async function () {
            const formData = new FormData();
            this.files.forEach(file=>{
                formData.append('files', file);
            })
            fetch('http://localhost:8088/java/recruImg',{
                    method : "POST",
                    headers : {},
                    body : formData
                }) 
                .then(Response => Response.json())  //json 파싱 
                .then(data => { 
                    console.log(data)
                }).catch(err=>console.log(err))
        },
        addGear : function(menu){                
            const box = document.getElementById(menu);
            const li = document.createElement('li');
            menu = menu.substring(0,menu.indexOf("-b"));

            let str = "<input type='text' class='"+menu+"-name' style='padding:5px;margin:3px;border:white;'>"
                        +" <select class='"+menu+"-type' style='padding:5px;margin:3px;border:white;'>"
                            +"<option selected disabled>장비 분류</option>"
                            +"<option value='tent'>텐트</option>"
                            +"<option value='tarp'>타프</option>"
                            +"<option value='furniture'>가구</option>"
                            +"<option value='bedding'>침구</option>"
                            +"<option value='kitchenTool'>조리도구</option>"
                            +"<option value='lamp'>조명</option>"
                            +"<option value='storage'>수납</option>"
                            +"<option value='tool'>공구</option>"
                            +"<option value='airCon'>냉난방</option>"
                            +"<option value='etc'>기타</option>"
                        +"</select>"
                        +"<input type='number' class='"+menu+"-num gear-num' style='width:50px;padding:5px;margin:3px;border:white;' placeholder='수량' min='1'>"
                        +"<input type='file' class='btn "+menu+"-img img' style='margin:0 5px;max-width:210px;' name='mygear' @change='addFile'>"
                        +"<button type='button' class='btn' style='width:17px; height:17px;border-radius:50%;background:crimson;border:none;color:white;margin-left:2px' >x</button>";
            li.innerHTML = str;
            box.appendChild(li);
        },
        removeGear : function(e){
            if(e.target.tagName=='BUTTON'){
                e.target.parentNode.remove();
            }
        },
        setGearList :function(menu){
            let gearNames = document.querySelectorAll('.recru-'+menu+'-name');
            let gearTypes = document.querySelectorAll('.recru-'+menu+'-type');
            let gearNum = document.querySelectorAll('.recru-'+menu+'-num');
            let gearList = gearNames[0].value+','+gearTypes[0].value+','+gearNum[0].value;
            for(let i=1 ; i<gearNames.length ; i++){
                if(gearNames[i].value===''){
                    Swal.fire('장비 이름을 입력해주세요','입력하지 않을 경우 x 버튼을 눌러 지워주세요','warning');
                    gearNames[i].focus();
                    return;
                }
                gearList = gearList+ ','+ gearNames[i].value+','+gearTypes[i].value+','+gearNum[i].value ;
            }
            if(menu==='mygear'){
                this.recruInfo.myGear = gearList;
            }else{
                this.recruInfo.needGear = gearList;
            }
        },
        setWishAge : function(){
            const query = 'input[name="wishAge"]:checked';
            const selectedAges =  document.querySelectorAll(query);                

            let result = '';
            selectedAges.forEach((el) => {
                result += el.value + ' ';
            });
            this.recruInfo.wishAge = result;
        },
        saveImage : function(file){
            const form = document.forms.namedItem('recru-form');
            const samples = document.querySelectorAll('sample');
            const blob = new Blob()
            const formData = new FormData(form);
            formData.append()
            for(let i=0;i<(this.recruInfo.myGear.length+this.recruInfo.needGear.length);i++){
                gearImgData.append("mygear", this.mygear)
            }
            console.log(gearImgData)
        },
        writeNewNote : function(){
            alert('노트쓰기 페이지로 연결하기')
        },
        getGearList : function(){
            alert('장비가져오기');
        }
        
    }
}
</script>

<style scoped>
.recru-input-container{
    margin-top: 150px;
    width: 100%;
    display: flex;
    position: relative;
    padding: 10px;
}
.recru-input-back{
    height: 1000px;
    width: 600px;
    margin : 1rem;
    flex-direction: column;
    justify-content: center;
    background-image: url("@/assets/img/vue-mk-header.jpg"); 
    background-size: cover;
    border-radius: 20px;
}
/* 입력폼 박스 */
.recru-input-box{
    position: absolute;
    left: 25vw;
    min-width: 622px;
    padding: 20px;
    border-radius: 20px;
    backdrop-filter:saturate(100%) blur(30px) ;
    background-color:rgba(255, 255, 255, 0.8) !important ;
    margin-top: 4rem;
    margin-bottom: 4rem;
    box-shadow: 0 10px 15px -3px rgb(0 0 0 / 10%), 0 4px 6px -2px rgb(0 0 0 / 5%) !important;
}
#recru-form{
    text-align: left;
}
#recru-form input,#recru-form select, #recru-form textarea{
    padding: 5px;
    margin: 3px;
    border: white;
}
#recru-form input[type=number]{
    width: 50px;
}
#recru-form p, #recru-form span{
    margin: 10px 0;
    font-weight: bold;
}

#recru-form hr{
    margin: 10px;
}

/* 들여쓰기 */
.in-level{
    margin-left: 20px;
}
/* 제목 */
.recru-input-box h3{
    margin: 20px;
}
.recru-info-title input,.recru-info-content textarea{
    width: 80%;
}
/* 노트 부분 */
.recru-info-mynote{
    display: flex;
    justify-content: space-between;
}
.write-note-btn{
    margin-right: 20px;
}

</style>
