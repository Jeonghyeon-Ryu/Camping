<template>
    <div class="recru-input-container">
        <div class="recru-update-back recru-back-box" >
        </div>
        <div v-if="recruInfo" class="recru-input-box">
            <h3>동행 모집글 수정</h3>
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
                            <label><input type="radio" v-model="recruInfo.wishSex" value="0">무관</label>
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
                        <button class="gear-mylist-btn recru-info-btn" type="button" @click="getGearList">내 장비 가져오기</button>
                    </div>
                    <ul @click="removeGear" id="recru-mygear-body">
                         
                    </ul>
                </div>
                <div class="recru-info-box">
                    <div class="recru-needgear-header">
                        <span>필요한 장비</span>
                        <button class="gear-add-btn recru-info-btn" type="button" v-on:click="addGear('recru-needgear-body')">추가</button>                    
                    </div>
                    <ul @click="removeGear" id="recru-needgear-body">
                       
                    </ul>
                </div>
                <div class="recru-info-box">
                   <span>기존에 등록된 이미지</span> (클릭시 삭제)
                    <div class="recru-show-image">
                        <div class="recru-show-image-box" v-for="image of images">
                            <div v-if="image.imageId!=0">
                                <p class="existing-img">{{image.originName}}</p>
                                <img class="show-image" :src="'http://13.125.95.210:85/java/recruImg/'+image.imgPath+'/'+image.storedName">
                                <img class="delete-image" src="@/assets/img/icons/close.png" @click="deleteImg">
                            </div>
                        </div>    
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <div class="recru-info-title">
                        <label><span>제목 </span><input type="text" name="recru_input_title" :value="recruInfo.recruTitle" ></label>
                    </div>
                    <div class="recru-info-content">
                        <label><span>내용</span> <textarea  name="recru_input_content" :value="recruInfo.recruContent" cols="30" rows="5"></textarea></label>
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
                            <button class="write-note-btn" @click="writeNewNote">새 노트 쓰기</button>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="recru-info-box">
                    <span>여행정보</span>
                    <ul class="recru-box-name">
                        <li class="recru-info-startP">
                            <label for="startP_addr_kakao">출발지 :</label> {{recruInfo.startingPoint}}
                        </li>
                        <li><input type="text" name="startP_addr_kakao" id="startP_addr_kakao"  readonly
                                    class="recru-addr-kakao" @click="searchAddr" placeholder="새로운 위치 검색">
                            <img v-bind:src="searchImg" style="width:20px;margin:auto 0">
                            <input type="text" name="startP_addr_detail" class="recru-addr-detail" placeholder="상세주소" @click="chkSearchAddr"/>
                        </li>
                        <li class="recru-info-campP">
                            <label for="campP_addr_kakao">도착지 :</label> {{recruInfo.campingPoint}}
                        </li>
                        <li>
                            <input type="text" name="campP_addr_kakao" id="campP_addr_kakao"  readonly
                                class="recru-addr-kakao" @click="searchAddr" placeholder="새로운 위치 검색" >
                            <img v-bind:src="searchImg" style="width:20px;margin:auto 0">
                            <input type="text" name="campP_addr_detail" class="recru-addr-detail" placeholder="상세주소" @click="chkSearchAddr"/>
                        </li>
                        <li class="recru-info-number">
                            <label>모집인원 <input type="number" name="recru_input_recruNum" :value="recruInfo.recruNum" min="1" de></label>
                        </li>
                        <li class="recru-info-day">
                            <label>여행 날짜 </label>
                                <input type="date" class="select-date" name="recru_input_goDate" v-model="recruInfo.goDate"> 
                            ~ <input type="date" class="select-date" name="recru_input_comeDate" v-model="recruInfo.comeDate">
                        </li>
                    </ul>
                </div>
                <div class="recru-info-last">
                    <label><span>마감일</span> <input type="date" name="recru_input_closingDate" :value="recruInfo.closingDate" class="select-date"></label>
                </div>
                <div class="recru-info-btn" style="text-align: center;">
                    <button class="btn bg-gradient-success btn-md"
                    v-on:click.prevent="updateContent">수정</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
import Swal from 'sweetalert2';
import img2 from "@/assets/img/search.png"

export default{
    data : function(){
      return {
        recruId : this.$route.params.recruId,
        searchImg : img2,
        wishAge :[],
        recruInfo : {  
            memberId :this.$store.state.email,
            wishSex : '',
            wishAge : '',
            carYn : '',
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
            nickname : 'admin'
            },
        deleteFiles :[],
        files:[],
        myNote:[{
            noteId : 1,
            noteTitle : '노트제목입니다'
            },
            {
            noteId : 2,
            noteTitle : '노트제목입니다'
        }],
        images:[]
      }
    },
    mounted (){
       this.loadData();
       this.loadImgs();
    },
    methods : {
        loadData : function(){
            // 서버에서 단건조회
            let recruId = this.recruId;
            let component = this;
            fetch("http://13.125.95.210:85/java/recru/"+recruId)
            .then((response) =>response.json()) 
            .then(data => { 
                component.recruInfo = data;  
                console.log(component.recruInfo);
                //나이 넣어주기
                if(component.recruInfo.wishAge){
                    var ages = component.recruInfo.wishAge.split(' ');
                    var ageChkBox = document.getElementsByName('wishAge');
                    ageChkBox.forEach(ageChk=>{
                        ages.forEach(age=>{
                            if(age==ageChk.value)
                                ageChk.checked=true;
                        })
                    })
                }
                //장비 목록 출력
                var myGearList = this.recruInfo.myGear.split(',');
                //장비 목록 출력
                var myGearList = this.recruInfo.myGear.split(',');
                for(let i=0; i<myGearList.length ; i+=3){
                    var menu = 'recru-mygear'
                    const box = document.getElementById('recru-mygear-body');
                    const li = document.createElement('li');
                    let str = `<input type='text' class='${menu}-name' style='padding:5px;margin:3px;border:white;' value="${myGearList[i]}" disabled>
                                <input class='${menu}-type' style='padding:5px;margin:3px;border:white;' value="${myGearList[i+1]}"disabled>
                                <input type='number' class='${menu}-num gear-num' style='width:50px;padding:5px;margin:3px;border:white;'disabled value='${myGearList[i+2]}'>개
                                <button type='button' class='btn' style='width:17px; height:17px;border-radius:50%;background:crimson;border:none;color:white;margin-left:2px' >x</button>`;
                    li.innerHTML = str;
                    box.appendChild(li);
                }
                var needGearList = this.recruInfo.needGear.split(',');
                for(let i=0; i<needGearList.length ; i+=3){
                    var menu = 'recru-needgear'
                    const box = document.getElementById('recru-needgear-body');
                    const li = document.createElement('li');
                    let str = `<input type='text' class='${menu}-name' style='padding:5px;margin:3px;border:white;' value="${needGearList[i]}" disabled>
                                <input class='${menu}-type' style='padding:5px;margin:3px;border:white;' value="${needGearList[i+1]}"disabled>
                                <input type='number' class='${menu}-num gear-num' style='width:50px;padding:5px;margin:3px;border:white;' value='${needGearList[i+2]}' disabled>개
                                <button type='button' class='btn' style='width:17px; height:17px;border-radius:50%;background:crimson;border:none;color:white;margin-left:2px' >x</button>`;
                    li.innerHTML = str;
                    box.appendChild(li);
                }

            // noteId
                
            })
        },
        loadImgs: function () {
            const recruId = this.recruId;
            const component = this;
            fetch("http://13.125.95.210:85/java/recruImg/" + recruId)
                .then(result => result.json())
                .then(result => {
                component.images=result;
                console.log(component.images)
            })
                .catch(err => console.log(err));
        },
        updateContent : function(){
        //기본입력확인
        const inputValue = [
                    'recru_input_title','recru_input_content',
                    'recru_input_goDate','recru_input_comeDate',
                    'recru_input_recruNum','recru_input_closingDate'
                    ];
            const inputName = ['제목을', '내용을', '여행 출발날짜를', '여행 도착날짜를', '모집인원을','마감날짜를']
            const dataName = ['recruTitle','recruContent','goDate','comeDate','recruNum','closingDate']
            for (let i=0; i<inputValue.length ; i++){
                var chkInput = document.getElementsByName(inputValue[i])[0];
                if(chkInput.value ==''){
                    Swal.fire({
                        text: `${inputName[i]} 입력해주세요. `,
                        icon : 'error',
                        toast: true,
                        showConfirmButton: false,
                        timer: 1000,
                        position: 'center-center'
                    })
                    chkInput.focus();
                    return;
                }else{
                   this.recruInfo[dataName[i]]= chkInput.value;
                }
            }
            //장비 입력 확인
            const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g; //특수문자 체크 정규식
            let myGearNames = document.querySelectorAll('.recru-mygear-name');
            let needGearNames = document.querySelectorAll('.recru-mygear-name');
            if(myGearNames.length==0 ||needGearNames.length==0){
                Swal.fire('장비를 등록해주세요',"보유장비와 필요장비는 각각 적어도 하나 이상 등록해야 합니다.",'warning');
            }
            for(let i=0 ; i<myGearNames.length ; i++){
                if(regExp.test(myGearNames[i].value)){
                    Swal.fire('장비 이름을 확인해주세요',"< > @ . , ; : & * ^ / $ 등의 특수문자는 입력할 수 없습니다.",'warning');
                    myGearNames[i].focus();
                    return;
                }
                if(myGearNames[i].value===''){
                    Swal.fire('장비 이름을 입력해주세요',' 추가할 장비가 없을 경우 x 버튼을 눌러 지워주세요','warning');
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
                    Swal.fire('장비 이름을 입력해주세요','추가할 장비가 없을 경우 x 버튼을 눌러 지워주세요','warning');
                    needGearNames[i].focus();
                    return;
                }
            }

            //새로 입력한 주소가 있는 경우 -> 검색주소 + 상세주소
            if(document.querySelector('#startP_addr_kakao').value){
                this.recruInfo.startingPoint = document.querySelector('#startP_addr_kakao').value 
                                                + document.getElementsByName('startP_addr_detail')[0].value
            }      
            if(document.querySelector('#campP_addr_kakao').value) {
                this.recruInfo.campingPoint = document.querySelector('#campP_addr_kakao').value
                                             + document.getElementsByName('campP_addr_detail')[0].value
            }
            // 장비와 희망연령을 string타입으로 변환
            this.setGearList('mygear'); 
            this.setGearList('needgear');
            this.setWishAge();
            //파일 배열에 이미지 넣기
            this.addFile(); 
            
            let recruVO = this.recruInfo;
            console.log(recruVO);

            //서버를 통해 게시글 내용 insert
            fetch('http://13.125.95.210:85/java/recru/updateAll',{
                method : "PUT",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify(recruVO )
            }) 
            .then(Response => Response.json())  //json 파싱 
            .then(data => { 
                //이미지 업로드
                this.fileUpdate();  
            }).catch(err=>console.log(err))
        },
        addFile : function(){
            //파일 배열에 이미지 넣기
            const newfiles = document.querySelectorAll('.img');
            this.files.length=1;
            newfiles.forEach((imgfile) => {           
                if(imgfile.value !=''){
                    this.files.push(imgfile.files[0])
                }
            })            
        },
        fileUpdate : async function () {
            //서버에 이미지 수정
            const formData = new FormData();
            if(this.files.length<=1){
                this.files ={};
                console.log('추가이미지없음')
            }else{
                this.files.forEach(file=>{
                    formData.append('files', file);
                })
                
                console.log(this.files)
            }
            if(this.deleteFiles.length<=0){
                this.deleteFiles =[0];
                console.log('삭제이미지없음')
            }else{
                this.deleteFiles.forEach(file=>{
                    formData.append('imgList', file);
                })
                
                console.log(this.deleteFiles)
            }
            formData.append('recruId', this.recruId);
        
            fetch('http://13.125.95.210:85/java/recruImg/update',{
                    method : "POST",
                    headers :{},
                    body : formData
                }) 
                .then(Response => Response.json())  
                .then(data => { 
                    console.log(data)
                    Swal.fire('수정 완료','캠핑 모집글이 수정되었습니다.','success')
                    .then(result => {
                        if (result.isConfirmed) { 
                            this.$router.go(-1)
                        } 
                    })
                }).catch(err=>{
                    Swal.fire('수정 실패','문제가 계속 될 경우 문의를 남겨주세요.','error')
                    console.log(err)
                })
            
        },
        addGear : function(menu){                
            const box = document.getElementById(menu);
            const li = document.createElement('li');
            menu = menu.substring(0,menu.indexOf("-b"));

            let str = "<input type='text' class='"+menu+"-name' style='padding:5px;margin:3px;border:white;'>"
                        +" <select class='"+menu+"-type' style='padding:5px;margin:3px;border:white;'>"
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
                        +"<input type='file' class='btn "+menu+"-img img' style='margin:0 5px;max-width:210px;' name='mygear' @change='addFile' accept='image/*'>"
                        +"<button type='button' class='btn' style='width:17px; height:17px;border-radius:50%;background:crimson;border:none;color:white;margin-left:2px' >x</button>";
            li.innerHTML = str;
            box.appendChild(li);
        },
        removeGear : function(e){
            if(e.target.tagName=='BUTTON'){
                e.target.parentNode.remove();
            }
        },
        deleteImg : function(e){
            let imgs = this.images;
            for(let i=0;i<imgs.length; i++){
                if(imgs[i].originName === e.target.parentNode.firstChild.innerHTML){
                    this.deleteFiles.push(imgs[i].originName)
                    this.images.splice(i,1)
                }
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
                this.recruInfo.myGear = gearList;
            }else{
                this.recruInfo.needGear = gearList;
            }
        },
        setWishAge : function(){
            const query = 'input[name="wishAge"]:checked';
            const selectedAges =  document.querySelectorAll(query);                

            let result = '';
            selectedAges.forEach((age) => {
                result += age.value + ' ';
            });
            this.recruInfo.wishAge = result;
        },
        writeNewNote : function(){
            alert('노트쓰기 페이지로 연결하기')
        },
        getGearList : function(){
            alert('장비가져오기');
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
        toastSwal: Swal.mixin({
            toast: true,
            showConfirmButton: false,
            timer: 1500,
            position: 'center-center'
        })
    }
}
</script>

<style scoped src="@/assets/css/recruit/recruInsert.css"/>