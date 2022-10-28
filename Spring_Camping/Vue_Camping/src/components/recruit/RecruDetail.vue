<template>
    <div class="recru-detail-container">
        <div class="recru-detail-box ">            
            <div class="recru-detail-row">
                <div class="recru-detail-title">
                    <h2>{{recruPost.recruTitle}}</h2>
                </div>
                <div class="recru-status-box" :class="statusClass">
                    <RecruStatus :recruStatus="recruPost.recruStatus"></RecruStatus>
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
                        <img :src="'http://13.125.95.210:85/java/profile/'+storedProfile.imagePath+'/'+storedProfile.storedName"  name="profile_img" alt="profile img">
                        <p><span>{{recruPost.nickname}}</span></p>
                        <p>{{yyyyMMddhhmmss(recruPost.writeDate) }}</p>
                        <button class="report-btn" @click="reportItem">🚨신고</button>
                    </div>
                    <div class="recru-detail-contents">
                        <br>
                        <div class="recru-detail-row">
                            <span><h3 style="margin-top:6px">모집기간</h3></span>
                            <p >{{recruPost.closingDate}}<span v-if="recruPost.closingDate==null">미정</span></p>
                        </div>
                        <h3>모집자 정보</h3>
                        <p><span>성별 </span>{{recruPost.sex}}</p>
                        <p><span>연령대  </span>{{userage}}</p>
                        <p><span>차량 유무 </span>{{recruPost.carYn==1 ? '있음':'없음'}}</p>
                        <br>
                        
                        <h3>이런 분</h3>
                        <p><span>성별  </span>{{userSex}}</p>
                        <p><span>연령대  </span>{{recruPost.wishAge}}</p>
                        <br>
                        <h3>함께 해요</h3>
                        <p><span>여행 예정 날짜  </span>{{recruPost.goDate}} ~ {{recruPost.comeDate}}</p>
                        <p><span>출발지역  </span>{{recruPost.startingPoint}}</p>
                        <p><span>도착지 </span>{{recruPost.campingPoint}} <button class="findCamp" @click="isCampViewed=!isCampViewed">🚩</button></p>  
                        <div v-if="isCampViewed"  class="show_region_camp">
                            <h4>이 지역의 캠핑스팟</h4>
                            <p v-if="campSites.length==0" style="text-align:center">등록된 캠핑스팟이 없습니다!
                                <br>새로운 정보를 등록해주세요
                                <br><button @click="$router.push({name:'CampRegister'})">등록하러 가기</button>
                            </p>
                            <p v-for="site in campSites" :key="site.campId">
                                <span class="recruPost_camp" @click="getCampDetail(site.campId, $event)">{{site.campName}}</span> {{site.campAddress}}
                            </p>                      
                        </div>
                            <p><span>모집 인원  </span>{{recruPost.recruNum}}</p>
                        <br>
                        <p><span>갖고있어요  </span>{{ gearList(recruPost.myGear)}}</p>
                        <p><span>필요해요  </span>{{gearList(recruPost.needGear)}}</p>
                        <br>
                        <div class="recruPost-content">{{recruPost.recruContent}}</div>
                    </div>
                </div>
            </div>
            <div class="recru-detail-row mynote-page">
                <div>
                    {{notePost.id}}
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
            <div v-if="(userRole==1&&recruPost.recruStatus>0)||userRole==3" class="recru-detail-sol deposit-status-box">
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

        <!-- 게시글 관리 버튼 -->
        <div class=" recru-detail-btn">
            <a id="kakaotalk-sharing-btn">
                <img src="@/assets/img/Table/share.png"
                    alt="카카오톡 공유 보내기 버튼"
                    @click="sendLinkDefault"
                    style="width:30px;margin: 10px;" />
            </a>
            <div class="recru-entry-btn">
                <!-- 모집자/신청자가 아닌 경우 -->
                <button v-if="userRole==0 && rStatus==0" class="btn-green hover-shadow" type="button" @click="entryInsertForm">동행신청</button>
                <!-- 신청자인 경우 -->
                <button v-if="userRole==2" type="button" style="color: green;background: rgba(228,239,231,0.7);font-weight: bold;">신청 중</button>
                <button v-if="userRole==2||userRole==3" class="hover-shadow" type="button" @click="entryDelete" style="color:gray; background: nlightgray; ">신청 취소</button>
            </div>
            <div v-if="userRole==1" class="recru-writer-btn">
                <!-- 모집자(작성자)인 경우 -->
                <button v-if="rStatus==0" type="button" @click="recruFinish">모집완료</button>
                <button v-if="rStatus==0" type="button" @click="recruUpdate">수정</button>
                <button type="button" @click="userDelete">삭제</button>
                <button v-if="rStatus==1" type="button" @click="recruReview">후기등록</button>
            </div>
            <div v-if="userRole==4" class="recru-writer-btn">
                <!-- 관리자인 경우 -->
                <button type="button" @click="adminDelete">접근제한</button>
            </div>
        </div>
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
import RecruStatus from '@/components/recruit/RecruStatus.vue';
import RecruSaveHeart from './RecruSaveHeart.vue';
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
    ModalView,
    RecruStatus,
    RecruSaveHeart
},
    data:function(){
        return{
            //롤 지정 : 0일반유저, 1모집자, 2신청중인 사람, 3신청수락된 사람, 4관리자 
            memberId : sessionStorage.getItem("email"),
            storedProfile : '',
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
            isCampViewed : false,
            campSites :[]
        }
    },
    created (){
        this.loadRecruData();
    },
    computed : {
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
            fetch("http://13.125.95.210:85/java/recru/"+recruId)
            .then((response) =>response.json()) 
            .then(data => { 
                component.recruPost = data;  
                console.log(component.recruPost);

                //프로필 이미지 가져오기
                fetch('http://13.125.95.210:85/java/profile/' + component.recruPost.memberId)
                .then(result => result.json())
                .then(result => {
                    component.storedProfile = result;
                }).catch(err => console.log(err));

                //캠핑장 정보 조회
                var region = component.recruPost.campingPoint;
                console.log(region)
                fetch(`http://13.125.95.210:85/java/recru/campingPoint/${region}`)
                .then(result => result.json())
                .then(result => {
                    component.campSites = result;
                    console.log(component.campSites)
                }).catch(err => console.log(err));

                //서버에서 모집글에 대한 참가목록 조회
                let recruId = 0;
                recruId = this.recruId;
                fetch("http://13.125.95.210:85/java/recru/entry/"+recruId)
                .then((response) =>response.json()) 
                .then(data => { 
                    component.entryPost = data;  
                    //참가목록 저장         
                    //롤 지정 : 0일반유저, 1모집자, 2신청중인 사람, 3신청수락된 사람, 4관리자 
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
                    }else if(this.$store.state.auth == 0){
                        component.userRole = 4;
                    }else{
                        component.userRole = 0;
                    }
                    console.log('role ' + component.userRole)
                }).catch(err=>console.log(err));
            })            
        }, 
        getCampDetail(campId, e) {
            e.preventDefault();
            let id = campId;
            this.$router.push({name:'CampDetail', params: {campId : id}});
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
                        fetch('http://13.125.95.210:85/java/recru',{
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
            console.log(this.$store.state.email);
            if(this.$store.state.email==null){
                console.log('널입니다')
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
                    }
                })
            }else{
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
                        fetch('http://13.125.95.210:85/java/report', {
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
            }
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
        },
        entryInsertForm : function(){
            if(!this.memberId){
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
                    }
                })
            }else{
                this.isModalViewed = true;
            }
        },
        recruUpdate : function(){
            this.$router.push({name : 'RecruUpdate',params : {recruId : this.recruId}})
        },
        userDelete : function(){
            if(this.entryCount >0){
               alert('삭제불가')
            }else{
                this.changeShowInfo(1)
            }
        },
        adminDelete : function(){
            this.changeShowInfo(2)
        },
        recruReview : function(){
            this.$router.push({name : 'RecruReview',params : {recruId : this.recruId}})
        },
        changeShowInfo : function(status){
            var changeInfo = {
                recruId : this.recruId,
                status : status
            };
            console.log(changeInfo)
            fetch('http://13.125.95.210:85/java/recru/showStatus', {
                method : "PUT",
                headers : {"Content-Type" : "application/json"},
                body :  JSON.stringify(changeInfo)
            }).then(result => result.text())
                    .then(result => {
                        console.log(result)
                        if (result == 1) {
                            Swal.fire({
                                icon: 'success',
                                title: '삭제되었습니다',
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
                            this.$router.push({name : 'RecruList'})
                        } else {
                            Swal.fire({
                                icon: 'error',
                                title: '실패되었습니다',
                                text:'서버를 다시 확인해주세요.',
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
        },
        sawl : Swal.mixin({
            toast: true,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        }),
        sendLinkDefault(){
            try {
                if (Kakao) {
                    Kakao.init('3f57d02e134f1067307cbaec0b42c437');// 사용하려는 앱의 JavaScript 키 입력
                };
            } catch(e) {};
            var infoTitle = this.recruPost.recruTitle;
            var infoContent = this.recruPost.recruContent;
            var num = this.recruPost.recruId;
            Kakao.Share.createDefaultButton({
                container: '#kakaotalk-sharing-btn',
                objectType: 'feed',
                content: {
                title : infoTitle,
                description: infoContent,
                imageUrl:
                    'https://ifh.cc/g/dTGkp9.jpg',
                link: {
                    mobileWebUrl: 'http://localhost:8088/RecruList',
                    webUrl: 'http://localhost:8088/RecruList',
                },
                },
                
                social: {
                likeCount: 286,
                commentCount: 45,
                sharedCount: 845,
                },
                buttons: [
                {
                    title: '캠핑갈래 홈',
                    link: {
                    mobileWebUrl: 'http://localhost:8087/',
                    webUrl: 'http://localhost:8087/',
                    },
                },
                {
                    title: '페이지 이동',
                    link: {
                    mobileWebUrl: 'http://localhost:8087/recru/detail/'+num,
                    webUrl: 'http://localhost:8087/recru/detail/'+num,
                    },
                },
                ],
            })    
        }
    }
}
</script>
<style scoped src="@/assets/css/recruit/recruDetail.css" />