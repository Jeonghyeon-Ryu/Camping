<template>
    <div class="recru-input-container">
        <div class="recru-input-back recru-back-box" >
        </div>
        <div class="recru-input-box">
            <h3>캠핑 동행 모집글 등록</h3>
            <form id="recru-form" role="form" method="post" enctype="multipart/form-data">
                <div class="recru-info-box">
                    <div class="recru-info-car">
                        <p>차량 유무</p>
                        <div class="in-level">
                            <label><input type="radio" v-model="recruInfo.carYn" value="1">있음</label>
                            <label><input type="radio" v-model="recruInfo.carYn" value="0">없음</label>
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
                            <label><input type="checkbox" name="wishAge" value="20대">20대</label>
                            <label><input type="checkbox" name="wishAge" value="30대">30대</label>
                            <label><input type="checkbox" name="wishAge" value="40대">40대</label>
                            <label><input type="checkbox" name="wishAge" value="50대이상">50대 이상</label>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <div class="recru-mygear-header">
                        <span>보유한 장비</span>
                        <button class="gear-add-btn recru-info-btn" type="button" v-on:click="addGear('recru-mygear-body')">추가</button>                    
                    </div>
                    <ul @click="removeGear" id="recru-mygear-body">
                        <li><input type="text" class="recru-mygear-name" placeholder="장비 이름">
                            <select class="recru-mygear-type">
                                <option value="기타" selected disabled>장비 분류</option>
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
                            <input type="number" class="gear-num recru-mygear-num" value="1" min="1">개
                            <input type="file" class="btn recru-mygear-img img" name="mygear"  style="margin:0 5px;max-width:210px;" >
                        </li>
                    </ul>
                </div>
                <div class="recru-info-box">
                    <div class="recru-needgear-header">
                        <span>필요한 장비</span>
                        <button class="gear-add-btn recru-info-btn" type="button" v-on:click="addGear('recru-needgear-body')">추가</button>                    
                    </div>
                    <ul @click="removeGear" id="recru-needgear-body">
                        <li><input type="text" class="recru-needgear-name" placeholder="장비 이름">
                            <select class="recru-needgear-type">
                                <option value="기타" selected disabled>장비 분류</option>
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
                            <input type="number" class="recru-needgear-num gear-num" value="1" min="1">개
                            <input type="file" class="btn recru-needgear-img img" name="mygear" style="margin:0 5px;max-width:210px;">
                        </li>
                    </ul>
                </div>
                    
                <hr>
                <div class="recru-info-box">
                    <div class="recru-info-title">
                        <label><span>제목 </span><input type="text" name="recru_input_title" v-model="recruInfo.recruTitle"></label>
                    </div>
                    <div class="recru-info-content">
                        <label><span style="float:left;margin-right:4px">내용</span> <textarea  name="recru_input_content" v-model="recruInfo.recruContent" cols="30" rows="5"></textarea></label>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <div class="recru-info-mynote">
                        <div>
                            <span>나의 노트</span>
                            <ul>
                                <li  class="recru-mynote-select in-level">
                                    <label><input type="radio" v-model="recruInfo.noteId" value="">선택하지 않기</label>
                                </li>
                                <li  v-for="note in myNote" v-bind:key="myNote.noteId" class="recru-mynote-select in-level">
                                    <label><input type="radio" v-model="recruInfo.noteId" :value="note.noteId">{{note.title}}</label>
                                </li>
                            </ul>
                        </div>
                        <div>
                            <button type="button" class="write-note-btn recru-info-btn" @click="$router.push({name:'WriteNote'})">새 노트 쓰기</button>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <span>여행정보</span>
                    <ul class="recru-box-name">
                        <li class="recru-info-startP">
                            <label>출발지</label>
                            <input type="text" name="startP_address_kakao" readonly @click="searchAddr" placeholder="도로명 주소 검색">
                            <img v-bind:src="searchImg" style="width:20px;margin:auto 0">
                            <input type="text" name="startP_address_detail" placeholder="상세주소" @click="chkSearchAddr"/>
                        </li>
                        <li class="recru-info-campP">
                            <label >도착지</label>
                            <input type="text" name="campP_address_kakao" readonly @click="searchAddr" placeholder="도로명 주소 검색">
                            <img v-bind:src="searchImg" style="width:20px;margin:auto 0">
                            <input type="text" name="campP_address_detail" placeholder="상세주소" @click="chkSearchAddr"/>
                            <button type="button" class="recru-info-btn" @click="isCampFindView=!isCampFindView">캠핑장 검색</button>
                        </li>
                        <div v-if="isCampFindView" class="search-camp">
                            <div>
                                <input type="text" id="search_camp_name" placeholder="캠핑장 이름 입력"  v-on:keydown.enter.prevent="searchCamp">
                                <button type="button" class="recru-info-btn" @click="searchCamp">검색</button>
                                <div v-if="isCampViewed"  class="show-camp-list">
                                    <p v-if="campSites.length==0" style="text-align:center">
                                        등록된 캠핑지가 없습니다!
                                        <br>새로운 정보를 등록해주세요
                                        <br><button  class="recru-info-btn"  @click="$router.push({name:'CampRegister'})">캠핑장 등록하러 가기</button>
                                    </p>
                                    <p v-for="site in campSites" :key="site.campId" class="recruPost_camp"  @click="getCampDetail(site.campAddress, $event)">
                                        <span class="recruPost_camp_name">{{site.campName}}</span> 
                                        <span class="recruPost_camp_address" >{{site.campAddress}}</span>
                                    </p>                      
                                </div>
                            </div>
                        </div>
                        <li class="recru-info-number">
                            <label for="recru-insert-num">모집인원</label> <input type="number" id="recru-insert-num" v-model="recruInfo.recruNum" min="1">
                        </li>
                        <li class="recru-info-day">
                            <label for="recru-insert-goDate">여행 날짜</label> 
                            <input type="date" id="recru-insert-goDate" class="select-date" name="recru_input_goDate" v-model="recruInfo.goDate"  :min="todayDate" :max="recruInfo.comeDate"> 
                            ~ <input type="date" class="select-date" name="recru_input_comeDate" v-model="recruInfo.comeDate" :min="recruInfo.goDate">
                        </li>
                    </ul>
                </div>
                <div class="recru-info-box">    
                    <div class="recru-info-last">
                        <label><span>마감일</span> 
                            <input type="date" name="recru_input_closingDate" class="select-date"  
                                    v-model="recruInfo.closingDate" :max="recruInfo.goDate" :min="todayDate"></label>
                    </div>
                </div>
                <div class="recru-insert-btn" style="text-align: center;">
                    <button class="recru-submit-btn recru-info-btn"
                    v-on:click.prevent="uploadContent">등록</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
import Swal from 'sweetalert2';
import img2 from "@/assets/img/search.png"

export default{
    props : ['campAddress','campName'],
    data : function(){
      return {
        searchImg : img2,
        wishAge :[],
        recruInfo : {  
            memberId : this.$store.state.email,
            wishSex : 0,
            wishAge : '',
            carYn : 0,
            myGear : '',
            needGear :'',
            recruTitle : '',
            recruContent :'',
            noteId: 0,
            startingPoint: '',
            campingPoint: '',
            recruNum : 1,
            goDate : '',
            comeDate : '',
            closingDate: '',
            nickname :this.$store.state.nickname
            },
        files:[],
        isCampFindView:false,
        isCampViewed : false,
        campSites : [],
        myNote:[],
        todayDate : new Date().toISOString().substring(0, 10)
      }
    },
    created(){
        //로그인 체크
        if(this.$store.state.email==null){
                Swal.fire({
                        title: '로그인이 필요한 서비스입니다.',
                        text: "로그인 페이지로 이동하겠습니까?",
                        icon: 'warning',
                        showCancelButton: true,
                        // confirmButtonColor: '#3085d6',
                        // cancelButtonColor: '#d33',
                        confirmButtonText: '네',
                        cancelButtonText : '아니오'
                    }).then((result) => {
                    if (result.isConfirmed) {
                        this.$router.push({ name: 'LoginSignup'})
                    }else{
                        this.$router.go(-1);
                    }
                })
        }
        //나의 노트 정보 가져오기
        const email = this.$store.state.email;
        fetch(`http://13.125.95.210:85/java/MyNoteList/${email}`) 
            .then(Response => Response.json())  
            .then(data => { 
                this.myNote = data;
                console.log(this.myNote)
            })
    },
    mounted(){
        console.log(this.campName)
        //주소정보 받았다면 도착지에 넣어주기
        if(this.campAddress != '' && this.campAddress != null){
            document.getElementsByName('campP_address_kakao')[0].value = this.campAddress;
            document.getElementsByName('campP_address_detail')[0].value = this.campName;
            document.getElementsByName('campP_address_detail')[0].focus();
        }
    },
    methods : {
        uploadContent : function(){
        //입력확인
        const inputValue = [
                    'recru_input_title','recru_input_content',
                    'startP_address_kakao','campP_address_kakao',
                    'recru_input_goDate','recru_input_comeDate',
                    'recru_input_closingDate'
                    ];
            const inputName = ['제목을', '내용을', '출발지를', '도착지를', '출발날짜를', '도착날짜를', '마감날짜를']
            for (let i=0; i<inputValue.length ; i++){
                var chkInput = document.getElementsByName(inputValue[i])[0];
                if(chkInput.value ==''){
                    this.toastSwal.fire({
                        text: `${inputName[i]} 입력해주세요. `,
                        icon : 'error'
                    })
                    chkInput.focus();
                    return;
                }
            }
            //장비 입력 확인
            const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g; //특수문자 체크 정규식
            let myGearNames = document.querySelectorAll('.recru-mygear-name');
            let needGearNames = document.querySelectorAll('.recru-needgear-name');
            for(let i=0 ; i<myGearNames.length ; i++){
                if(regExp.test(myGearNames[i].value)){
                    Swal.fire('장비 이름을 확인해주세요',"< > @ . , ; : & * ^ / $ 등의 특수문자는 입력할 수 없습니다.",'warning');
                    myGearNames[i].focus();
                    return;
                }
                if(myGearNames[i].value===''){
                    Swal.fire('장비 이름을 입력해주세요','장비는 하나 이상 입력해야 합니다. 추가할 장비가 없을 경우 x 버튼을 눌러 지워주세요','warning');
                    myGearNames[i].focus();
                    return;
                }
            }
            for(let i=0 ; i<needGearNames.length ; i++){
                if(regExp.test(needGearNames[i].value)){
                    Swal.fire('장비 이름을 확인해주세요',"< > @ . , ; : & * ^ / $ 등의 특수문자는 입력할 수 없습니다.",'warning');
                    needGearNames[i].focus();
                    return;
                }
                if(needGearNames[i].value===''){
                    Swal.fire('장비 이름을 입력해주세요','장비는 하나 이상 입력해야 합니다. 추가할 장비가 없을 경우 x 버튼을 눌러 지워주세요','warning');
                    needGearNames[i].focus();
                    return;
                }
            }
           
            //서버에 업로드
            // 장비와 희망연령을 string타입으로 변환
            this.setGearList('mygear'); 
            this.setGearList('needgear');
            this.setAgeAddr();
            console.log(this.recruInfo)
            //파일 배열에 이미지 넣기
            this.addFile(); 
            
            let recruVO = this.recruInfo;
            //서버를 통해 게시글 내용 insert
            fetch('http://13.125.95.210:85/java/recru',{
                method : "POST",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify(recruVO )
            }) 
            .then(Response => Response.json())  //json 파싱 
            .then(data => { 
                //이미지 업로드
                if(this.files.length>0){
                    this.fileUpload();  
                }
                Swal.fire('등록 완료','캠핑 모집글이 등록되었습니다. 여행을 떠나요!','success')
                .then(result => {
                    if (result.isConfirmed) { 
                        this.$router.push({name : 'RecruList'})
                    } 
                })
            }).catch(err=>console.log(err))
        },
        addFile : function(){
            //파일 배열에 이미지 넣기
            const imgfiles = document.querySelectorAll('.img');
            this.files.length=1;
            imgfiles.forEach((imgfile) => {           
                if(imgfile.value !=''){
                    this.files.push(imgfile.files[0])
                }
            })            
        },
        fileUpload : async function () {
            console.log( this.files)
            //서버에 이미지 업로드
            const formData = new FormData();
            this.files.forEach(file=>{
                formData.append('files', file);
            })
            fetch('http://13.125.95.210:85/java/recruImg',{
                    method : "POST",
                    headers : {},
                    body : formData
                }) 
                .then(Response => Response.text())  
                .then(data => { 
                    console.log(data)
                }).catch(err=>{
                    Swal.fire('등록 실패','문제가 계속될 경우 문의해주세요','error')
                    console.log(err)
                })
        },
        addGear : function(menu){                
            const box = document.getElementById(menu);
            const li = document.createElement('li');
            menu = menu.substring(0,menu.indexOf("-b"));

            let str = "<input type='text' class='"+menu+"-name' style='padding:5px;margin-left:3px;border:white;' placeholder='장비 이름'>"
                        +" <select class='"+menu+"-type' style='padding:5px;margin-right:3px;border:white;'>"
                            +"<option selected disabled>장비 분류</option>"
                            +"<option value='기타' value='텐트'>텐트</option>"
                            +"<option value='타프'>타프</option>"
                            +"<option value='가구'>가구</option>"
                            +"<option value='침구'>침구</option>"
                            +"<option value='조리도구'>조리도구</option>"
                            +"<option value='조명'>조명</option>"
                            +"<option value='수납'>수납</option>"
                            +"<option value='공구'>공구</option>"
                            +"<option value='냉난방'>냉난방</option>"
                            +"<option value='기타'>기타</option>"
                        +"</select>"
                        +"<input type='number' class='"+menu+"-num gear-num' style='width:50px;padding:5px;margin:3px;border:white;' value='1' placeholder='수량' min='1'>개"
                        +"<input type='file' class='btn "+menu+"-img img' style='margin:0 5px 0 16px;max-width:210px;' name='mygear' @change='addFile'>"
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
            //특수문자 체크 정규식
            const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g
            for(let i=1 ; i<gearNames.length ; i++){
                gearList = gearList+ ','+ gearNames[i].value+','+gearTypes[i].value+','+gearNum[i].value ;
            }
            if(menu==='mygear'){
                this.recruInfo.myGear = gearList;
            }else{
                this.recruInfo.needGear = gearList;
            }
        },
        setAgeAddr : function(){
            //나이 배열 - > String
            const query = 'input[name="wishAge"]:checked';
            const selectedAges =  document.querySelectorAll(query);                

            let result = '';
            selectedAges.forEach((age) => {
                result += age.value + ' ';
            });
            this.recruInfo.wishAge = result;

            //주소 -> 검색주소 + 상세주소
            this.recruInfo.startingPoint = document.getElementsByName('startP_address_kakao')[0].value+' '+document.getElementsByName('startP_address_detail')[0].value;
            this.recruInfo.campingPoint = document.getElementsByName('campP_address_kakao')[0].value+' '+document.getElementsByName('campP_address_detail')[0].value;
        },
        getGearList : function(){
            
            this.toastSwal.fire({
            icon:'error',
            text : '준비중입니다'
           })
        },
        searchAddr : function(e){
            //도로명주소 검색
            new daum.Postcode({
                oncomplete: function(data) { //선택시 입력값 세팅
                    e.target.value = data.address; // 주소 넣기
                    e.target.nextSibling.nextSibling.focus(); //상세입력 포커싱
                }
            }).open();
        },
        chkSearchAddr : function(e){
            //상제 주소 입력 전 도로명 주소 체크
            var searchAddr = e.target.previousSibling.previousSibling;
            if(!searchAddr.value){
                this.toastSwal.fire({
                    text: `도로명 주소를 먼저 입력하세요`,
                    icon : 'warning',
                })
                searchAddr.focus();
            }
        },
        searchCamp : function(){
            this.isCampViewed =true;
            //이름으로 캠핑장 검색
            var campName = document.querySelector('#search_camp_name').value
            var regionInfo = {
                    campName : campName
                }
                fetch(`http://13.125.95.210:85/java/recru/campingPoint`,{
                method : "POST",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify(regionInfo)
                }) 
                .then(result => result.json())
                .then(result => {
                    this.campSites = result;
                    console.log(this.campSites)
                }).catch(err => console.log(err));
        },
        getCampDetail(address, e) {
            e.preventDefault();
            document.getElementsByName('campP_address_kakao')[0].value = address;
            document.getElementsByName('campP_address_detail')[0].value = '';
        },
        toastSwal: Swal.mixin({
            toast: true,
            showConfirmButton: false,
            timer: 1000,
            position: 'center-center'
        })
            
        
    }
}
</script>

<style scoped src="@/assets/css/recruit/recruInsert.css"/>