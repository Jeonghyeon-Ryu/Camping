<template>
    <div class="recru-input-container">
        <div class="recru-input-back" >
        </div>
        <div class="recru-input-box">
            <h3>Camping Recruitment</h3>
            <form id="recru-form" role="form" >
                <div class="recru-info-box">
                    <div class="recru-info-car">
                        <p>차량 유무</p>
                        <div class="in-level">
                            <label><input type="radio" v-model="recruInfo.my_car" value="1">있음</label>
                            <label><input type="radio" v-model="recruInfo.my_car" value="0">없음</label>
                        </div>
                    </div>
                </div>
                <div class="recru-info-box">
                    <p>희망 동행자</p>
                    <div class="in-level">
                        <div class="recru-info-sex">
                            <label><input type="radio" v-model="recruInfo.wish_sex" value="0">남</label>
                            <label><input type="radio" v-model="recruInfo.wish_sex" value="1">여</label>
                            <label><input type="radio" v-model="recruInfo.wish_sex" value="2" checked>무관</label>
                        </div>
                        <div class="recru-info-age">
                            <label><input type="checkbox" v-model="recruInfo.wish_age" value="2">20대</label>
                            <label><input type="checkbox" v-model="recruInfo.wish_age" value="3">30대</label>
                            <label><input type="checkbox" v-model="recruInfo.wish_age" value="4">40대</label>
                            <label><input type="checkbox" v-model="recruInfo.wish_age" value="5">50대 이상</label>
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
                                <option selected disabled>장비 분류</option>
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
                            <input type="file" class="btn btn-sm recru-mygear-img" style="margin:0 5px;max-width:210px;">
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
                                <option value='' disabled>장비분류</option>
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
                            <input type="file" class="btn btn-sm recru-needgear-img" style="margin:0 5px;max-width:210px;">
                        </li>
                    </ul>
                </div>
                    
                <hr>
                <div class="recru-info-box">
                    <div class="recru-info-title">
                        <label><span>제목 </span><input type="text" v-model="recruInfo.recru_title"></label>
                    </div>
                    <div class="recru-info-content">
                        <label><span>내용</span> <textarea v-model="recruInfo.recru_content" cols="30" rows="5"></textarea></label>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <div class="recru-info-mynote">
                        <div>
                            <span>나의 노트</span>
                            <ul v-for="note in myNote" v-bind:key="myNote.note_id">
                                <li class="recru-mynote-select"><input type="radio" v-model="recruInfo.note_id">{{note.note_title}}</li>
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
                            <label>출발지<input type="text" v-model="recruInfo.starting_spot"></label>
                        </li>
                        <li class="recru-info-campP">
                            <label>도착지<input type="text" v-model="recruInfo.camping_spot"></label>
                        </li>
                        <li class="recru-info-number">
                            <label>모집인원 <input type="number" v-model="recruInfo.recru_num" min="1" de></label>
                        </li>
                        <li class="recru-info-day">
                            <label>여행 날짜 <input type="date" class="select-date" v-model="recruInfo.go_date"></label> 
                            ~ <input type="date" class="select-date" v-model="recruInfo.come_date">
                        </li>
                    </ul>
                </div>
                <div class="recru-info-last">
                    <label><span>마감일</span> <input type="date" v-model="recruInfo.close_date" class="select-date"></label>
                    <button class="btn bg-gradient-success btn-md"
                            v-on:click.prevent="uploadContent">등록</button>
                </div>
            
            </form>
        </div>
    </div>
</template>

<script>
import MaterialBadge from "@/components/MaterialBadge.vue";
export default{
    components : {
        MaterialBadge
    },
    data : function(){
      return {
        recruInfo : {   wish_sex : '',
                        wish_age : [],
                        my_car : '',
                        my_gear : '',
                        need_gear :'',
                        gear_img:[],
                        recru_title : '',
                        recru_content :'',
                        note_id: 0,
                        starting_spot: '',
                        camping_spot: '',
                        recru_num : 0,
                        go_date : '',
                        come_date : '',
                        close_date: '',
                        },
        myNote:[{
            note_id : 1,
            note_title : '노트제목입니다'
            },
            {
            note_id : 2,
            note_title : '노트제목입니다'
        }]
      }
    },
    methods : {
        uploadContent : function(event){
                this.setGearList('mygear');
                this.setGearList('needgear');
                console.log(this.recruInfo);
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
                        +"<input type='file' class='btn btn-sm' style='margin:0 5px;max-width:210px;'>"
                        +"<button type='button' class='btn btn-sm btn-outline-danger' >삭제</button>";
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
                gearList = gearList+ ','+ gearNames[i].value+','+gearTypes[i].value+','+gearNum[i].value ;
            }
            if(menu==='mygear'){
                this.recruInfo.my_gear = gearList;
            }else{
                this.recruInfo.need_gear = gearList;
            }
        },
        writeNewNote : function(){
            alert('노트쓰기 페이지로 연결하기')
        },
        getGearList : function(){
            alert
        }
    },
    
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
    width: 800px;
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
    left: 20vw;
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

</style>
