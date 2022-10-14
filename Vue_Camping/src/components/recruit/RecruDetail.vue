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
                        <img src='@/assets/img/camping_gear01.jpg'>
                        <RecruDetailImage></RecruDetailImage>
                    </div>
                    <div id="map" class="recru-detail-map ">
                        <RecruMap/>
                    </div>
                </div>
                <div class="recru-detail-col">
                    
                    <div class="recru-detail-user">
                        <img src="@/assets/img/bg9.jpg" name="profile_img" alt="profile img">
                        <p><span>{{recruPost.writer}}</span></p>
                        <p>{{yyyyMMddhhmmss(recruPost.writeDate) }}</p>
                        <button class="report-btn">신고</button>
                    </div>
                    <div class="recru-detail-contents">
                        <br>
                        <h3 style="font-weight: bold;">모집자 정보</h3>
                        <p><span>성별 </span>{{user.sex}}</p>
                        <p><span>연령대  </span>{{userage}}</p>
                        <p><span>차량 유무 </span>{{recruPost.carYn}}</p>
                        <br>
                        <h3 style="font-weight: bold;">이런 분</h3>
                        <p><span>성별  </span>{{recruPost.wishSex}}</p>
                        <p><span>연령대  </span>{{recruPost.wishAge}}대</p>
                        <br>
                        <h3 style="font-weight: bold;">함께 해요</h3>
                        <p><span>여행 예정 날짜  </span>{{recruPost.goDate}} ~ {{recruPost.comeDate}}</p>
                        <p><span>출발지역  </span>{{recruPost.startingPoint}}</p>
                        <p><span>도착지 </span>{{recruPost.campingPoint}}</p>
                        <p><span>모집 인원  </span>{{recruPost.recruNum}}</p>
                        <br>
                        <p><span>갖고있어요  </span></p>
                        <p><span>필요해요  </span></p>
                        <br>
                        <p>{{recruPost.recruContent}}</p>
                        <br>
                        <p><span>모집기간</span></p>
                    </div>
                </div>
            </div>
            <div class="recru-detail-row mynote-page">
                <div>
                    {{notePost.id}}
                </div>
            </div>

            <!-- 게시글 관리 버튼 -->
            <div class="recru-detail-row">
                <div class="recru-entry-btn">
                    <!-- 모집자/신청자가 아닌 경우 -->
                    <button type="button" @click="isModalViewed=true">동행 신청</button>
                    <!-- 신청자인 경우 -->
                    <button type="button" @click="entryDelete">신청 취소</button>
                </div>
                <div class="recru-writer-btn">
                    <!-- 모집자(작성자)인 경우 -->
                    <button type="button" @click="recruFinish">모집완료</button>
                    <button type="button" @click="recruUpdate">수정</button>
                    <button type="button" @click="recruDelete">삭제</button>
                </div>
               
            </div>
            <div class="recru-detail-sol recru-entry-post">
                <h3>함께해요 신청 내역</h3>
                <div class="recru-entry-list">
                    <div class="recru-detail-row">
                        <div v-for="entryInfo in entryPost" :key="entryInfo.entry_id">
                            <EntryStandByCard v-bind:entryCard="entryInfo"></EntryStandByCard>
                        </div>
                    </div>
                </div>
            </div>
            <div class="recru-detail-sol recru-entry-post">
                <h3>현재 동행자 ({{entryCount}}/{{recruPost.recru_num}})</h3>
                <div class="recru-entry-list">
                    <div class="recru-detail-row">
                        <div v-for="entryInfo in entryPost" :key="entryInfo.entry_id">
                            <EntryCard v-bind:entryCard="entryInfo"></EntryCard>
                        </div>
                    </div>
                </div>
            </div>
            <div class="recru-detail-sol deposit-status-box">
                <h3>보증금 상태</h3>
                <DepositStatus></DepositStatus>
            </div>
        </div>     

         <!-- 동행신청 버튼 모달창 -->
         <ModalView v-if="isModalViewed" @close-modal="isModalViewed=false">
            <EntryInsert v-bind:recruId="recruPost.recruId" @close-modal="isModalViewed=false"></EntryInsert>
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
                recruPost : {},
                recruStatus : '모집중',
                statusClass:'recru_status_ing',
                user : {
                    sex : '남',
                    birth : new Date()
                },
                recruPost:{},
                notePost : {
                    id : 0,
                    member_id : 0,
                    note_contents : '노트 내용1'
                },
                entryPost : [],
                entryCount : 2,
                isModalViewed : false,
                recru_entry : {
                    entry_car : '',
                    entry_gear : []
                }
            }
        },
        created (){
            this.loadRecruData()
        },
        computed : {
            userage(){
                //연령대 계산
                const today = new Date();          
                const birthDate = this.user.birth;

                let age = today.getFullYear()
                        - birthDate.getFullYear()
                        + 1;

                if(age<30){
                    return '20대'
                }else if(age<40){
                    return '30대'
                }else if(age<50){
                    return '40대'
                }else{
                    return '50대 이상'
                }
            }
        },
        methods :{
            loadRecruData : function(){
                // 서버에서 단건조회
                let recruId = this.recruId;
                fetch("http://localhost:8087/java/recru/"+recruId)
                .then((response) =>response.json()) 
                .then(data => { 
                    this.recruPost = data;  
                    console.log(this.recruPost);

                }).catch(err=>console.log(err));
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
                

                if(month < 10){
                    month = '0' + month;
                }
                if(day < 10){
                    day = '0' + day;
                }

                return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes  + ':' + seconds;
            },
            recruFinish : function(){
                Swal.fire({
                    title: '모집을 완료하시겠습니가?',
                    text: '모집완료는 다시 되돌릴 수 없습니다.',
                    icon: 'warning',
                    
                    showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                    confirmButtonText: '모집완료', // confirm 버튼 텍스트 지정
                    cancelButtonText: '취소', // cancel 버튼 텍스트 지정
                    
                    }).then(result => {
                    if (result.isConfirmed) { 

                        Swal.fire('승인이 완료되었습니다.', '즐거운 여행 되세요!', 'success');
                    }
                    });
               
            }
        }
    }
</script>
<style scoped>
      /* 공통 부분 */
    * {
        margin: 0;
        padding: 0;
        list-style: none;
        font-style: none;
        box-sizing: border-box;
    }
    /* 컨테이너 */
    .recru-detail-container{
        margin-top: 150px;
        margin-bottom: 150px;
        width: 100%;
        display: flex;
        justify-content: center;
    }
    .recru-detail-box{
        width: 90%;
        max-width: 1000px;
        padding: 10px;
        box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05) !important;
        text-align: left;
     } 
     .recru-detail-row{
        display: flex;
        flex-wrap: wrap;
    }
    .recru-detail-col{
        width: 45%;
        min-width: 420px;
        margin-right: 10px;
    }
    .recru-detail-post{
        justify-content: center;
    }
    .recru-detail-post span{
        font-weight: bold;
    }
    .recru-detail-post h3{
        color: rgb(132, 167, 147);
    }
     /* 제목 */
     .recru-detail-title{
        margin: 20px 0 10px 50px;
     }
     /* 게시글 상태 */
     .recru-detail-status{
        margin: 25px 0 20px 10px;
        padding: 3px;
        border-radius: 5px;
        font-size: small;
        font-weight: bold;
     }
     .recru_status_ing{
        background: rgb(255, 215, 221);
        color: crimson;
     }
    /* 유저 프로필 */
    .recru-detail-user{
        display: flex;  
    }
    .recru-detail-user img{
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-top: 10px;
    }
    .recru-detail-user p{
        padding: 10px 0 10px 10px;
    }
   .recru-detail-post p{
       margin: 10px 15px ;
   }
    .recru-detail-date{
        color: gray;
        font-size: small;
    }
    .report-btn{
        border-radius: 5px;
        width: 60px;
        height: 30px;
        font-size:13px;
        font-weight: bold;
        border: none;
        color: gray;
        margin: 15px 10px 0 10px;
    }


    /* 장비사진, 지도 크기 */
    .recru-detail-img, .recru-detail-info ,.recru-detail-map{ 
        height: 400px;
        width: 90%;
        min-width: 400px;
        margin: 30px 10px;
        display: block;
        overflow: hidden;
        border-radius: 5px;
    }
    .recru-detail-img img, .recru-detail-map img{
        height: 100%;
        width: 100%;
    }
    .recru-detail-info p {
        margin: 7px;
    }

    /* 동행신청 내역 */
    .recru-entry-post{
        padding: 20px;
        background: rgba(228,239,231,0.7) ;
        margin-top: 20px;
    }
    .recru-entry-post h3{
        margin-bottom: 20px;
    }
    .recru-entry-list{
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
    }

    /* 보증금 상태 */
    .deposit-status-box{
        padding: 20px;
        border: 5px solid rgba(228,239,231,0.7);
        margin: 20px 0;
    }
    .deposit-status-box h3{
        margin-bottom: 20px;
    }
</style>