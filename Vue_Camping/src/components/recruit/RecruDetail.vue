<template>
    <div class="recru-detail-container">
        <div class="recru-detail-box ">            
            <div class="recru-detail-row">
                <div class="recru-detail-title">
                    <h2>{{recruPost.recruTitle}}</h2>
                </div>
                <div class="recru-detail-status" :class="statusClass">
                    <p>{{recruStatus}}</p>
                </div>
            </div>
            <div class="recru-detail-row recru-detail-post">
                <div class="recru-detail-col">
                    <div class="recru-detail-img" >
                        <RecruDetailImage :recruId="recruId"></RecruDetailImage>
                    </div>
                    <div v-if="recruPost.startingPoint" id="map" class="recru-detail-map ">
                        <RecruMap :startingPoint="recruPost.startingPoint" 
                                :campingPoint="recruPost.campingPoint" />
                    </div>
                </div>
                <div class="recru-detail-col" style="margin-top : 30px">
                    
                    <div class="recru-detail-user">
                        <img src="@/assets/img/bg9.jpg" name="profile_img" alt="profile img">
                        <p><span>{{recruPost.nickname}}</span></p>
                        <p>{{yyyyMMddhhmmss(recruPost.writeDate) }}</p>
                        <button class="report-btn" @click="reportItem">신고</button>
                    </div>
                    <div class="recru-detail-contents">
                        <br>
                        <h3 style="font-weight: bold;">모집자 정보</h3>
                        <p><span>성별 </span>{{recruPost.sex}}</p>
                        <p><span>연령대  </span>{{userage}}</p>
                        <p><span>차량 유무 </span>{{recruPost.carYn==1 ? '있음':'없음'}}</p>
                        <br>
                        <h3 style="font-weight: bold;">이런 분</h3>
                        <p><span>성별  </span>{{userSex}}</p>
                        <p><span>연령대  </span>{{recruPost.wishAge}}</p>
                        <br>
                        <h3 style="font-weight: bold;">함께 해요</h3>
                        <p><span>여행 예정 날짜  </span>{{recruPost.goDate}} ~ {{recruPost.comeDate}}</p>
                        <p><span>출발지역  </span>{{recruPost.startingPoint}}</p>
                        <p><span>도착지 </span>{{recruPost.campingPoint}}</p>
                        <p><span>모집 인원  </span>{{recruPost.recruNum}}</p>
                        <br>
                        <p><span>갖고있어요  </span>{{ gearList(recruPost.myGear)}}</p>
                        <p><span>필요해요  </span>{{gearList(recruPost.needGear)}}</p>
                        <br>
                        <p>{{recruPost.recruContent}}</p>
                        <br>
                        <p><span>모집기간 : </span>{{recruPost.closingDate}}<span v-if="recruPost.closingDate==null">미정</span></p>
                    </div>
                </div>
            </div>
            <div class="recru-detail-row mynote-page">
                <div>
                    {{notePost.id}}
                </div>
            </div>

            <!-- 게시글 관리 버튼 -->
            <div class="recru-detail-row recru-detail-btn">
                <div class="recru-entry-btn">
                    <!-- 모집자/신청자가 아닌 경우 -->
                    <button v-if="userRole==0" class="btn-green hover-shadow" type="button" @click="isModalViewed=true">동행 신청</button>
                    <!-- 신청자인 경우 -->
                    <button v-if="userRole==2" type="button" style="color: green;background: rgba(228,239,231,0.7);font-weight: bold;">신청 중</button>
                    <button v-if="userRole==2||userRole==3" class="hover-shadow" type="button" @click="entryDelete" style="color:gray; background: nlightgray; ">신청 취소</button>
                </div>
                <div v-if="userRole==1" class="recru-writer-btn">
                    <!-- 모집자(작성자)인 경우 -->
                    <button v-if="rStatus==0" type="button" @click="recruFinish">모집완료</button>
                    <button v-if="rStatus==0" type="button" @click="recruUpdate">수정</button>
                    <button type="button" @click="recruDelete">삭제</button>
                </div>
                <div v-if="userRole==4" class="recru-writer-btn">
                    <!-- 관리자인 경우 -->
                    <button type="button" @click="">접근제한</button>
                    <button type="button" @click="">삭제</button>
                </div>
            </div>
            <!-- 신청내역 : 글 작성자에게만 보임 -->
            <div v-if="userRole==1" class="recru-detail-sol recru-entry-post">
                <h3>함께해요 신청 내역</h3>
                <div class="recru-entry-list">
                    <div class="recru-detail-row">
                        <div v-for="entryInfo in entryPost" :key="entryInfo.entryId">
                            <div v-if="entryInfo.entryStatus==0">
                                <EntryStandByCard v-bind:entryCard="entryInfo"></EntryStandByCard>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 신청 수락된 내역 :  -->
            <div class="recru-detail-sol recru-entry-post">
                <h3>현재 동행자 ({{entryCount}}/{{recruPost.recruNum}})</h3>
                <div class="recru-entry-list">
                    <div class="recru-detail-row">
                        <div v-for="entryInfo in entryPost" :key="entryInfo.entryId">
                            <div v-if="entryInfo.entryStatus==1">
                                <EntryCard v-bind:entryCard="entryInfo"></EntryCard>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div v-if="userRole==1||userRole==3||userRole==4" class="recru-detail-sol deposit-status-box">
                <h3>보증금 상태</h3>
                <DepositStatus :recruId="recruId"></DepositStatus>
            </div>
        </div>     
         <!-- 동행신청 버튼 모달창 -->
         <ModalView v-if="isModalViewed" @close-modal="isModalViewed=false">
            <EntryInsert 
                v-bind:recruId="recruPost.recruId" 
                @close-modal="isModalViewed=false"
                @close-recru="recruPost.recruStatus=1" >
            </EntryInsert>
        </ModalView>
    </div>
    

</template>

<script>
import EntryStandByCard from '@/components/recruit/EntryStandByCard.vue';
import EntryCard from '@/components/recruit/EntryCard.vue';
import RecruDetailImage from '@/components/recruit/RecruDetailImage.vue';
import RecruMap from '@/components/recruit/RecruMap.vue';
import DepositStatus from '@/components/recruit/DepositStatus.vue';
import EntryInsert from '@/components/recruit/EntryInsert.vue';
import ModalView from '@/components/recruit/ModalView.vue';
import Swal from 'sweetalert2';
export default{
    name : "RecruDetail",
    props : {
        recruId : String
    },
    components :{
        EntryStandByCard,
        EntryCard,
        RecruDetailImage,
        RecruMap,
        DepositStatus,
        EntryInsert,
        ModalView
    },
    data:function(){
        return{
            //롤 지정 : 0일반유저, 1모집자, 2신청중인 사람, 3신청수락된 사람, (4관리자 )
            memberId : localStorage.getItem("email"),
            userRole : 0,
            recruPost : {},
            entryPost : [],
            entryingMember : '',
            entredMember : '',
            statusClass:'',
            notePost : {},
            sendRecruInfo : {
                recruId : 0, 
                recruStatus : 1,
                memberId : ''
            },
            isModalViewed : false,
        }
    },
    created (){
        this.loadRecruData();
    },
    computed : {
        recruStatus(){
            if (this.recruPost.recruStatus==0){
                this.statusClass = 'recru_status_red'
                return '모집중'
            }else if(this.recruPost.recruStatus==1){
                this.statusClass = 'recru_status_green'
                return '모집완료'
            }else{
                this.statusClass = 'recru_status_gray'
                return '모집취소'
            }
        },
        userage(){
            //희망 연령대 계산
            const today = new Date();          
            let birth = this.recruPost.birth;
            birth = typeof birth === 'string' ? birth.substring(0, 4) : '';
            let age = today.getFullYear() - birth + 1;
            
            if(age<30){
                return '20대'
            }else if(age<40){
                return '30대'
            }else if(age<50){
                return '40대'
            }else{
                return '50대 이상'
            }
        },
        entryCount(){
            //현재 수락된 동행자 수 계산
            let entryCount = 0
            this.entryPost.forEach(entry=>{
                if(entry.entryStatus==1){
                    entryCount ++;
                }
            })
            return entryCount;
        },
        userSex(){
            //희망 성별
            let gender = this.recruPost.wishSex;
            if(gender==1){
                return '남자'
            }else if(gender==2){
                return '여자'
            }else{
                return '무관'
            }
        },
        rStatus(){
            let status = this.recruPost.recruStatus;
            return status;
        }
    },
    methods :{
        loadRecruData : function(){
            // 서버에서 단건조회
            let recruId = this.recruId;
            let component = this;
            fetch("http://localhost:8087/java/recru/"+recruId)
            .then((response) =>response.json()) 
            .then(data => { 
                component.recruPost = data;  
                console.log(component.recruPost);
        
                // 서버에서 모집글에 대한 참가목록 조회
                let recruId = 0;
                recruId = this.recruId;
                fetch("http://localhost:8087/java/recru/entry/"+recruId)
                .then((response) =>response.json()) 
                .then(data => { 
                    component.entryPost = data;  
                    //참가목록 저장         
                    //롤 지정 : 0일반유저, 1모집자, 2신청중인 사람, 3신청수락된 사람, (4관리자 )
                    component.entryPost.forEach(entry => {
                        if(entry.memberId == component.memberId && entry.entryStatus ==0){
                            //신청중인 사람
                            component.entryingMember+=entry.memberId+' ';
                            component.userRole = 2;
                        }else if(entry.memberId == component.memberId && entry.entryStatus ==1){
                            //신청수락된 사람
                            component.entredMember+=entry.memberId+' ';
                            component.userRole = 3;
                        }
                    })
                    let writer = component.recruPost.memberId;
                    if(component.userRole==2 || component.userRole==3){
                    }else if(component.memberId == writer){
                        component.userRole = 1;
                    }else{
                        component.userRole = 0;
                    }
                    console.log('role ' + component.userRole)
                }).catch(err=>console.log(err));
            })

        },
        loadDepositData : function(){
            //보증금 상황 조회

        },    
        yyyyMMddhhmmss : function(value){
            if(value == '') return '';
    
            var js_date = new Date(value);

            // 연도, 월, 일, 시, 분, 초 추출
            var year = js_date.getFullYear();
            var month = js_date.getMonth() + 1;
            var day = js_date.getDate();
            var hours = ('0' + js_date.getHours()).slice(-2); 
            var minutes = ('0' + js_date.getMinutes()).slice(-2);
            var seconds = ('0' + js_date.getSeconds()).slice(-2); 
            
            if(month < 10) {month = '0' + month;}
            if(day < 10){day = '0' + day;}
        
            return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes  + ':' + seconds;
        },
        recruFinish : function(){
            let component = this;
            //모집 완료 버튼
            Swal.fire({
                title: '모집을 완료하시겠습니가?',
                text: '대기중인 신청을 모두 확인하셨나요? 모집완료는 되돌릴 수 없습니다.',
                icon: 'warning',
                
                showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                confirmButtonText: '모집완료', // confirm 버튼 텍스트 지정
                cancelButtonText: '취소', // cancel 버튼 텍스트 지정
                })
                .then(result => {
                    //모집상태를 완료로 변경, 보증정보 insert
                    if (result.isConfirmed) { 
                        component.sendRecruInfo.recruId = component.recruId;
                        component.sendRecruInfo.memberId = component.memberId;
                        component.sendRecruInfo.recruStatus = 1;
                        fetch('http://localhost:8087/java/recru',{
                            method : "PUT",
                            headers : {"Content-Type" : "application/json"},
                            body :  JSON.stringify(component.sendRecruInfo)
                        }) 
                        .then(Response => Response.json())  
                        .then(data => { 
                            Swal.fire('승인이 완료되었습니다.', '즐거운 여행 되세요!', 'success');
                            component.recruPost.recruStatus=1;
                            component.loadRecruData();
                        }).catch(err=>console.log(err))
                    }
                });
            
        },
        reportItem() {
            let item = Swal.fire({
                title: '신고',
                html:
                    '<select id="swal-input1" class="swal2-select" style="font-size:13px;">' +
                    '<option value="" disabled="">신고 분류</option>' +
                    '<option value="잘못된 정보">잘못된 정보</option>' +
                    '<option value="게시글 규정 위반">게시글 규정 위반</option>' +
                    '<option value="기타">기타</option>' +
                    '</select>' +
                    '<textarea id="swal-input2" class="swal2-textarea" style="resize:none; width:80%; font-size:12px;" maxlength="450" placeholder="신고 사유를 입력하세요"></textarea>',
                focusConfirm: false,
                showCancelButton: true,
                confirmButtonText: '신고',
                cancelButtonText: '취소',
                confirmButtonColor: 'rgba(6,68,32,0.8)',
                preConfirm: () => {
                    let fetchData = {
                        "boardId": this.recruId,
                        "boardDivision": 1,
                        "reportDivision": document.getElementById('swal-input1').value,
                        "reportContent": document.getElementById('swal-input2').value,
                        "email": this.$store.state.email
                    }

                    console.log(fetchData);
                    fetch('http://localhost:8087/java/report', {
                        method: 'POST',
                        headers: {'Content-Type': 'application/json' },
                        body: JSON.stringify(fetchData)
                    }).then(result => result.text())
                        .then(result => {
                            if (result == "true") {
                                Swal.fire({
                                    icon: 'success',
                                    title: '신고 완료 !',
                                    toast: true,
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                        //this.$router.push({ path: '/recru/detail/' + this.recruId, });
                                    }
                                })
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: '신고 실패 !',
                                    text:'계속 실패하면 고객센터에 문의해주세요.',
                                    toast: true,
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                    }
                                })
                            }
                            console.log(result);
                        })
                    return false;
                }
            })
            console.log(item);
        },
        gearList(gears){
            //필요한 장비 배열 정리
            if(gears){
                let gearList = gears.split(',');
                let str = '';
                for(let i=0; i<gearList.length; i+=3){
                    if(i!=0) str+= ', ';
                    str += gearList[i]+'('+gearList[i+1]+') '+gearList[i+2]+'개';
                }
                return str;
            }
        }
    }
}
</script>
<style scoped src="@/assets/css/recruit/recruDetail.css" />