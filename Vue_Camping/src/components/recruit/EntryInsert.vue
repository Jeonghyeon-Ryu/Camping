<template>
    <div class="entry-insert-container">
        <div class="enrty-insert-title">
            <h3>참가 신청을 하시겠습니까? </h3>
        </div>
        <hr>
        <div class="entry-insert-content">
            <p>동행 신청을 위한 정보를 입력해주세요</p>
        </div>
        <div class="entry-insert-car">
            <div class="col">
                <p><span>차량유무</span></p>
                <label><input type="radio" v-model="recruEntry.entryCar" value="1">있음</label>
                <label><input type="radio" v-model="recruEntry.entryCar" value="0" checked>없음</label>
            </div>
        </div>
        <div class="entry-insert-gear">
            <div class="row">
                <p><span>보유한 장비</span></p>
                <button class="add-gear-btn" type="button" v-on:click="addGear('entry-gear-body')">추가</button>
            </div>
            <ul @click="removeGear" id="entry-gear-body">
                <li><input type="text" class="entry-gear-name" placeholder="장비 이름" size="18" maxlength="18" required>
                    <select class="entry-gear-type">
                        <option value='기타' selected disabled>장비분류</option>
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
                    <input type="number" class="entry-gear-num gear-num" value="1" min="1" max="999" style="width:50px;">개
                </li>
            </ul>
        </div>
        <div class="entry-insert-btn row" style="mragin:0;">
            <button type="button" class="accept-btn" @click="entryInsert">신청</button>
            <button type="button" class="refuse-btn" @click="$emit('close-modal')">취소</button>
        </div>
    </div>
</template>

<script>
import Swal from 'sweetalert2';
export default{
    props : {recruId : Number},
    data : function(){
        return {
            recruEntry : {
                recruId : '',
                entryCar : '0',
                entryGear : '',
                memberId : sessionStorage.getItem("email"), //작성자정보 -->세션에서 받아오기
                nickname : sessionStorage.getItem("nickname")
            },
            memberId : ''
        }
    },
    methods : {
        addGear : function(menu){     
            //장비 입력시 추가 가능           
            const box = document.getElementById(menu);
            const li = document.createElement('li');
            menu = menu.substring(0,menu.indexOf("-b"));

            let str = "<input type='text' class='"+menu+"-name' style='padding:5px;margin:2px;border:1px solid lightgray;border-radius:4px' placeholder='장비이름' size='18' maxlength='18'>"
                        +" <select class='"+menu+"-type' style='padding:5px;margin:2px;border:1px solid lightgray;border-radius:4px;'>"
                            +"<option value='' selected disabled>장비 분류</option>"
                            +"<option value='텐트'>텐트</option>"
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
                        +"<input type='number' class='"+menu+"-num gear-num' style='width:50px;padding:5px;margin:2px;border:1px solid lightgray;border-radius:4px;' value='1' min='1' max='999'>개"
                        +"<button type='button' class='btn' style='width:17px; height:17px;border-radius:50%;background:crimson;border:none;color:white;margin-left:2px' >x</button>";
            li.innerHTML = str;
            box.appendChild(li);
        },
        removeGear : function(e){
            //추가한 장비 삭제
            if(e.target.tagName=='BUTTON'){
                e.target.parentNode.remove();
            }
        },
        entryInsert : function(){
            //장비목록을 스트링 타입으로 묶기
            let gearNames = document.querySelectorAll('.entry-gear-name');
            let gearTypes = document.querySelectorAll('.entry-gear-type');
            let gearNum = document.querySelectorAll('.entry-gear-num');
            for(let i=0 ; i<gearNames.length ; i++){
                //특수문자 체크 정규식
                const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g
                if(regExp.test(gearNames[i].value)){
                    Swal.fire('장비 이름을 확인해주세요',"< > @ . , ; : & * ^ / $ 등의 특수문자는 입력할 수 없습니다.",'warning');
                    return;
                }
                if(gearNames[i].value===''){
                    Swal.fire('장비 이름을 입력해주세요','추가할 장비가 없는 경우 x 버튼을 눌러 지워주세요','warning');
                    gearNames[i].focus();
                    return;
                }
            }
            let gearList = '';
            for(let i=0 ; i<gearNames.length ; i++){
                if(i !=0) gearList+= ',';
                gearList += gearNames[i].value+','+gearTypes[i].value+','+gearNum[i].value ;
            }
            this.recruEntry.entryGear = gearList;
            const component = this;
            //신청 테이블에 인서트하기 
            this.recruEntry.recruId = this.recruId;
            let recruEntry = this.recruEntry;
            fetch('http://localhost:8087/java/recru/entry',{
                method : "POST",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify(recruEntry )
            }) 
            .then(Response => Response.json())  
            .then(data => { 
                if(data==1){
                    Swal.fire('신청 완료했습니다.','모집자가 신청을 수락할 경우 알림이 갑니다','success');
                    component.$emit('close-recru')
                }else{
                    Swal.fire('문제 발생!','신청이 처리되지 않았습니다. 문제가 계속될 경우 문의해주세요','error');
                }
                //Detail 페이지의 상태 변경
            }).catch(err=>{
                console.log(err);
                Swal.fire('문제 발생!','신청이 처리되지 않았습니다. 문제가 계속될 경우 문의해주세요','error');
            })
            //알림
            this.$emit('close-modal');
        }
    }
}
</script>

<style scoped src="@/assets/css/recruit/entryInsert.css" />
