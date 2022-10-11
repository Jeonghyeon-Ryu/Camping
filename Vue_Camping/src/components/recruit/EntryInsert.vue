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
                <label><input type="radio" v-model="recru_entry.entry_car" value="1">있음</label>
                <label><input type="radio" v-model="recru_entry.entry_car" value="0" checked>없음</label>
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
                    <input type="number" class="entry-gear-num gear-num" placeholder="수량" min="1" style="width:60px;">
                    <button type='button' class='gear-delete-btn'>x</button>
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
    data : function(){
        return {
            recru_entry : {
                entry_car : '0',
                entry_gear : ''
            }
        }
    },
    methods : {
        addGear : function(menu){                
            const box = document.getElementById(menu);
            const li = document.createElement('li');
            menu = menu.substring(0,menu.indexOf("-b"));

            let str = "<input type='text' class='"+menu+"-name' style='padding:5px;margin:2px;border:1px solid lightgray;border-radius:4px' placeholder='장비이름' size='18' maxlength='18'>"
                        +" <select class='"+menu+"-type' style='padding:5px;margin:2px;border:1px solid lightgray;border-radius:4px;'>"
                            +"<option value='' selected disabled>장비 분류</option>"
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
                        +"<input type='number' class='"+menu+"-num gear-num' style='width:60px;padding:5px;margin:2px;border:1px solid lightgray;border-radius:4px;' placeholder='수량' min='1'>"
                        +"<button type='button' class='btn' style='width:17px; height:17px;border-radius:50%;background:crimson;border:none;color:white;margin-left:2px' >x</button>";
            li.innerHTML = str;
            box.appendChild(li);
        },
        removeGear : function(e){
            if(e.target.tagName=='BUTTON'){
                e.target.parentNode.remove();
            }
        },
        entryInsert : function(){
            //장비목록을 스트링 타입으로 묶기
            let gearNames = document.querySelectorAll('.entry-gear-name');
            let gearTypes = document.querySelectorAll('.entry-gear-type');
            let gearNum = document.querySelectorAll('.entry-gear-num');
            let gearList = gearNames[0].value+','+gearTypes[0].value+','+gearNum[0].value;
            for(let i=1 ; i<gearNames.length ; i++){
                if(gearNames[i].value===''){
                    Swal.fire('장비 이름을 입력해주세요','보유한 장비가 없는 경우 x 버튼을 눌러 지워주세요','warning');
                    gearNames[i].focus();
                    return;
                }
                gearList = gearList+ ','+ gearNames[i].value+','+gearTypes[i].value+','+gearNum[i].value ;
            }
            this.recru_entry.entry_gear = gearList;
            //신청 테이블에 인서트하기 (유저한테 보이는 버튼 바뀜)
            console.log(this.recru_entry)
            //알림
            Swal.fire('신청 완료했습니다.','모집자가 신청을 수락할 경우 알림이 갑니다','success');
            this.$emit('close-modal');
        }
    }
}
</script>

<style scoped>
.entry-insert-container{
    width: 400px;
    text-align: left;
    background: white;
    border-radius: 10px;
    box-shadow: 0 3px 3px -1px rgb(66, 66, 66), 0 3px 1px -2px rgb(58, 58, 58), 0 1px 5px rgb(64, 64, 64);
}
.entry-insert-gear input, .entry-insert-gear select {
    padding:5px;
    margin:3px;
    border: 1px solid lightgray;
    border-radius: 4px;
}
.entry-insert-container span{
    font-size: small;
    color: gray;

}
  .row{
      display: flex;
      flex-wrap: wrap;
  }

.entry-insert-container hr{
  margin: 0 10px;
}
/* 제목 */
.enrty-insert-title, .enrty-insert-content{   
    margin: 15px;
}
  .enrty-insert-title h3{
    font-size: 17px;
    padding-top:5px ;
  }
  /* 바디 */
.entry-insert-content{
    margin : 15px;
}
  .entry-insert-car, .entry-insert-gear{
    margin: 18px;
}
  .entry-insert-car p, .entry-insert-gear p{
    padding: 5px;
  }
  .entry-insert-car label{
    margin: 0 10px;
  }
  /* 장비 삭제 버튼 */
  .gear-delete-btn{
    border-radius:50%;
    background-color :crimson;
    border:none;
    color:white;
    margin-left:2px;
    width:16px; 
    height:16px;
    cursor: pointer;
  }
   /* 버튼 */
   .add-gear-btn{
    margin: 3px;

   }
   .entry-insert-btn{
      display: flex;
      justify-content: space-between;
  }
  .entry-insert-btn button{
      padding: 10px;
      border : none;
      border-top: 1px solid lightgray;
      background: none;
      margin: 0;
      width: 50%;
  }
  .accept-btn{
      color : rgb(55, 135, 204);
  }
  .accept-btn:hover{
    background: skyblue;
    color: white;
    box-shadow: 0 3px 3px #1a73e826, 0 3px 1px -2px #1a73e833, 0 1px 5px #1a73e826;
    cursor: pointer;
  }
  .refuse-btn{
      color: gray;
  }
  .refuse-btn:hover{
    background: gray;
    color: white;
    box-shadow: 0 3px 3px #34476726, 0 3px 1px -2px #34476733, 0 1px 5px #34476726;
    cursor: pointer;
}
</style>
