<template>
    <div class="recru-detail-container">
        <div class="recru-detail-box ">            
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
                    <div class="recru-detail-title">
                        <h2>{{recruPost.title}}</h2>
                    </div>
                    <div class="recru-detail-user">
                        <img src="@/assets/img/bg9.jpg" name="profile_img" alt="profile img">
                        <p><span>{{recruPost.writer}}</span></p>
                        <p>{{recruPost.date}}</p>
                        <button class="report-btn">신고</button>
                    </div>
                    <div class="recru-detail-contents">
                        <br>
                        <h3 style="font-weight: bold;">모집자 정보</h3>
                        <p><span>성별 </span>{{user.sex}}</p>
                        <p><span>연령대  </span>{{userage}}</p>
                        <p><span>차량 유무 </span>{{recruPost.car_yn}}</p>
                        <br>
                        <h3 style="font-weight: bold;">이런 분</h3>
                        <p><span>성별  </span>{{recruPost.wish_sex}}</p>
                        <p><span>연령대  </span>{{recruPost.wish_age}}</p>
                        <br>
                        <h3 style="font-weight: bold;">함께 해요</h3>
                        <p><span>여행 예정 날짜  </span>{{recruPost.go_date}}</p>
                        <p><span>출발지역  </span>{{recruPost.starting_point}}</p>
                        <p><span>도착지 </span>{{recruPost.camping_point}}</p>
                        <p><span>모집 인원  </span>{{recruPost.recru_num}}</p>
                        <br>
                        <p><span>갖고있어요  </span></p>
                        <p><span>필요해요  </span></p>
                        <br>
                        <p>
                            모집내용 ~~~ 동산에는 사랑의 풀이 돋고 이상의 꽃이 피고 
                            희망의 놀이 뜨고 열락의 새가 운다사랑의 풀이 없으면 
                            인간은 사막이다 오아이스도 없는 사막이다 보이는 끝까지 
                            찾아다녀도 목숨이 있는 때까지 방황하여도 보이는 것은 
                            거친 모래뿐일 것이다
                        </p>
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
            <div class="recru-detail-row">
                <div class="recru-entry-btn">
                    <button>동행 신청</button>
                    <button>신청 취소</button>
                </div>
                <div class="recru-writer-btn">
                    <button>수정</button>
                    <button>삭제</button>
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
        </div>        
    </div>
                

</template>

<script>
    import EntryStandByCard from './EntryStandByCard.vue';
    import EntryCard from './EntryCard.vue';
    import RecruDetailImage from './RecruDetailImage.vue';
    import RecruMap from './RecruMap.vue';
    export default{
        components :{
    EntryStandByCard,
    EntryCard,
    RecruDetailImage,
    RecruMap
},
        data:function(){
            return{
                user : {
                    sex : '남',
                    birth : new Date()
                },
                recruPost:{
                    title:'글제목',
                    writer:'writerwriterwriter',
                    date: '22/11/11 00:00:00',
                    car_yn : '없음',
                    wish_sex : '무관',
                    wish_age : 20,
                    goDate : new Date()+1,
                    starting_point : '',
                    camping_point: '',
                    recru_num : 1,
                    note_id : 0
                },
                notePost : {
                    id : 0,
                    member_id : 0,
                    note_contents : '노트 내용1'
                },
                entryPost : [{
                    entry_id : 0,
                    member_id : 0,
                    entry_car : '없음',
                    entry_gear: '텐트',
                    entry_date: new Date()
                },
                {
                    entry_id : 1,
                    member_id : 1,
                    entry_car : '없음',
                    entry_gear: '텐트',
                    entry_date: new Date()
                },
                {
                    entry_id : 2,
                    member_id : 2,
                    entry_car : '없음',
                    entry_gear: '텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 텐트 ',
                    entry_date: new Date()
                }],
                entryCount : 0
            }
        },
        computed : {
            userage(){
                //let generation = '';

                const today = new Date();
                
                const birthDate = this.user.birth;

                let age = today.getFullYear()
                        - birthDate.getFullYear()
                        + 1;

                return age;

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
        width: 100%;
        display: flex;
        justify-content: center;
    }
    .recru-detail-box{
        width: 90%;
        max-width: 1200px;
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
        margin: 20px 0 ;
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
</style>