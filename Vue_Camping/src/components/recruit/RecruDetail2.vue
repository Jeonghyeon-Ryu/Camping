<template>
    <div class="recru-detail-container">
        <div class="recru-detail-box ">            
            <div class="recru-detail-row recru-detail-post">
                <div class="recru-detail-col">
                    <div class="recru-detail-img" >
                        <img src='@/assets/img/camping_gear01.jpg'>
                    </div>
                    <div class="recru-detail-map ">
                        <img class="recru-map" src="@/assets/img/camping_gear02.jpg">
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
                    {{note_id}}
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
            <div class="recru-detail-row recru-entry-list">
                <h3>함께해요 신청 내역</h3>
            </div>
        </div>        
    </div>
                

</template>
<script>
    export default{
        data:function(){
            return{
                user : {
                    sex : '',
                    birth : new Date()
                },
                recruPost:{
                    title:'글제목',
                    writer:'writer',
                    date: '22/11/11 00:00:00',
                    car_yn : '없음',
                    wish_sex : '무관',
                    wish_age : 20,
                    goDate : new Date()+1,
                    starting_point : '',
                    camping_point: '',
                    recru_num : 1,
                    note_id : 0
                }
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
        width: 80%;
        min-width: 600px;
        padding: 20px;
        box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05) !important;
        text-align: left;
     } 
     .recru-detail-row{
        display: flex;
        justify-content: center;
    }
    .recru-detail-col{
        width: 45%;
    }
    .recru-detail-post span{
        font-weight: bold;
        width: 90px;
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
        margin: 0 10px;
    }


    /* 장비사진, 지도 크기 */
    .recru-detail-img, .recru-detail-info ,.recru-detail-map{ 
        height: 400px;
        max-width: 400px;
        margin: 10px;
        display: block;
        overflow: hidden;
        border-radius: 5px;
    }
    .recru-detail-img img, .recru-detail-map img{
        height: 100%;
        min-width: 100%;
    }
    .recru-detail-info p {
        margin: 7px;
    }
</style>