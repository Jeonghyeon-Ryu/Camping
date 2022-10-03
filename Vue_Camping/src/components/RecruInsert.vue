<template>
    <div id="recru-container">
        <div class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column">
            <div class="position-relative h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center" loading="lazy" 
            style="background-image: url(&quot;/src/assets/img/forest.jpg&quot;); background-size: cover;">
            </div>
        </div>
        <div class="card d-flex blur justify-content-center shadow-lg my-sm-0 my-sm-6 mt-8 mb-5">
            <h3 style="padding:20px">Camping Recruitment</h3>
            <form id="recru-form" role="form" >
                <div class="recru-info-box">
                    <div class="recru-info-car">
                        <span>차량 유무</span>
                        <label><input type="radio" v-model="myCar" value="1">있음</label>
                        <label><input type="radio" v-model="myCar" value="0">없음</label>
                    </div>
                </div>
                <div class="recru-info-box">
                    <span>희망 동행자</span>
                    <div class="recru-info-sex">
                        <label><input type="radio" v-model="wishSex" value="0">남</label>
                        <label><input type="radio" v-model="wishSex" value="1">여</label>
                        <label><input type="radio" v-model="wishSex" value="2" checked>무관</label>
                    </div>
                    <div class="recru-info-age">
                        <label><input type="checkbox" v-model="wishAge" value="2">20대</label>
                        <label><input type="checkbox" v-model="wishAge" value="3">30대</label>
                        <label><input type="checkbox" v-model="wishAge" value="4">40대</label>
                        <label><input type="checkbox" v-model="wishAge" value="5">50대 이상</label>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <div class="recru-mygear-header">
                        <span>보유한 장비</span>
                        <button class="btn badge bg-info badge-md false " type="button" v-on:click="addGear('recru-mygear-body')">추가</button>                    
                        <button class="btn badge bg-dark badge-md false " type="button">내 장비 가져오기</button>
                    </div>
                    <ul @click="removeGear" id="recru-mygear-body">
                        <li><input type="text" class="recru-mygear-name" placeholder="장비 이름">
                            <select class="my-gear-type">
                                <option selected disabled>장비 분류</option>
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
                            <input type="number" class="recru-mygear-num gear-num" placeholder="수량">
                            <input type="file" class="btn btn-sm" style="margin:0 5px;max-width:210px;">
                        </li>
                    </ul>
                </div>
                <div class="recru-info-box">
                    <div class="recru-needgear-header">
                        <span>필요한 장비</span>
                        <MaterialBadge color="info" class="mx-1" v-on:click="addGear('recru-needgear-body')">추가</MaterialBadge>
                    </div>
                    <ul @click="removeGear" id="recru-needgear-body">
                        <li><input type="text" class="recru-mygear-name" placeholder="장비 이름">
                            <select class="my-gear-type">
                                <option selected disabled>장비 분류</option>
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
                            <input type="number" class="recru-mygear-num gear-num" placeholder="수량">
                            <input type="file" class="btn btn-sm" style="margin:0 5px;max-width:210px;">
                        </li>
                    </ul>
                </div>
                    
                <hr>
                <div class="recru-info-box">
                    <div class="recru-info-title">
                        <label><span>제목 </span><input type="text" v-model="recruTitle"></label>
                    </div>
                    <div class="recru-info-content">
                        <label><span>내용</span> <textarea v-model="recruContent" cols="30" rows="5"></textarea></label>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <div class="recru-info-mynote">
                        <span>나의 노트</span>
                        <ul v-for="note in myNote" v-bind:key="myNote.note_id">
                            <li class="recru-mynote-select">{{myNote.note_title}}</li>
                        </ul>
                        <button>새 노트 쓰기</button>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <span>여행정보</span>
                    <ul class="recru-box-name">
                        <li class="recru-info-startP">
                            <label>출발지<input type="text" v-model="startingSpot"></label>
                        </li>
                        <li class="recru-info-campP">
                            <label>도착지<input type="text" v-model="campingSpot"></label>
                        </li>
                        <li class="recru-info-number">
                            <label>모집인원 <input type="number" v-model="recruNum"></label>
                        </li>
                        <li class="recru-info-day">
                            <label>여행 날짜 <input type="date" class="select-date" v-model="goDate"></label> 
                            ~ <input type="date" class="select-date" v-model="comeDate">
                        </li>
                    </ul>
                </div>
                <div class="recru-info-last">
                    <label><span>마감일</span> <input type="date" v-model="closeDate" class="select-date"></label>
                    <button class="btn bg-gradient-success btn-md false false w-auto me-2"
                            v-on:click="uploadContent">등록</button>
                </div>
            
            </form>
        </div>
    </div>
</template>

<script>
import MaterialBadge from "@/components/MaterialBadge.vue";
export default{
    
    data : function(){
      return {
        wishSex : [],
        wishAge : [],
        myCar : [],
        myGearName : [],
        myGearType : [],
        myGearNum : 0,
        needGearName : [],
        needGearType : [],
        needGearNum : 0,
        recruTitle : '',
        recruContent :'',
        myNote : {
                note_id: 0,
                note_title:''
            },
        startingSpot: '',
        campingSpot: '',
        recruNum : 0,
        goDate : '',
        comeDate : '',
        closeDate: '',
      }
    },
    methods : {
        uploadContent : function(event){
            alert('등록')
            },
        addGear : function(menu){                
            const box = document.getElementById(menu);
            const li = document.createElement('li');
            menu = menu.substring(0,menu.indexOf("-b"));

            let str = "<input type='text' class='"+menu+"-name'>"
                        +" <select class='"+menu+"-type'>"
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
                        +"<input type='number' class='"+menu+"-num gear-num' style='width:50px;' placeholder='수량'>"
                        +"<input type='file' class='btn btn-sm' style='margin:0 5px;max-width:210px;'>"
                        +"<button type='button' class='btn btn-sm btn-outline-danger' >삭제</button>";
            li.innerHTML = str;
            box.appendChild(li);
        },
        removeGear : function(e){
            if(e.target.tagName=='BUTTON'){
                e.target.parentNode.remove();
            }
        }
    },
    
}
</script>

<style scoped>
#recru-container{
            width: 100%;
            display: flex;
            justify-content: center;
            padding: 10px;
        }
    
        
        #recru-form input,  #recru-form select, #recru-form button, #recru-form textarea{
            margin: 3px;
            border-radius: 3px;
            
        }
        #recru-form > div {
            width: 100%;
            padding: 0 20px;
        }
        #recru-form span{
            font-weight: 600;
            font-size: large;
        }
        .recru-info-title, .recru-info-content{
            width: 100%;
        }
        .recru-info-title input, .recru-info-content textarea{
            width: 500px;
        }
        /* 마이노트 */
        #recru-info-mynote ul{
            min-height: 60px;
            background-color: aliceblue;
        }
        .recru-info-last{
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }
        .recru-info-last{
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }
        /* 장비개수 */
        .gear-num{
            width: 50px;
        }
       
        .plus-btn:hover{
            box-shadow: 0 10px 15px -3px rgb(0 0 0 / 5%), 0 4px 6px -2px rgb(0 0 0 / 5%)
        }
        .btn-sm{
            margin: 0 7px;
        }
</style>
