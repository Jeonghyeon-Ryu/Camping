<template>
    <div class="recru-detail-container">
        <div class="recru-detail-box ">
            <div class="recru-detail-header">
                <h2>{{recruPost.title}}</h2>
            </div>
            <div class="recru-detail-body ">
                <div class="recru-detail-img" >
                    <img src='@/assets/img/camping_gear01.jpg'>
                </div>
                <div class="recru-detail-info">
                    <div class="recru-detail-user">
                        <img src="@/assets/img/bg9.jpg" name="profile_img" alt="profile img">
                        <p>{{recruPost.writer}}</p>
                        <button class="report-btn">신고</button>
                    </div>
                    <div class="recru-detail-date">
                        <p>{{recruPost.date}}</p>
                    </div>
                    <div class="recru-detail-post">
                        <p><span>성별 : </span></p>
                        <p><span>연령대 : </span></p>
                        <br>
                        <p style="font-weight: bold;">이런 분 함께해요</p>
                        <p><span>성별 : </span></p>
                        <p><span>연령대 : </span></p>
                    </div>
                </div>
            </div>
            <div class="recru-detail-body">
                <div class="recru-detail-info">
                    <div>
                        <p><span>여행 예정 날짜 : </span></p>
                        <p><span>출발 : </span></p>
                        <p><span>도착 : </span></p>
                        <p><span>모집 인원 : </span></p>
                    </div>
                    <div>
                        <p><span>갖고있어요 : </span></p>
                        <p><span>필요해요 : </span></p>
                    </div>
                    <div class="recru-detail-content">
                        <p>모집내용 ~~~ 동산에는 사랑의 풀이 돋고 이상의 꽃이 피고 
                            희망의 놀이 뜨고 열락의 새가 운다사랑의 풀이 없으면 
                            인간은 사막이다 오아이스도 없는 사막이다 보이는 끝까지 
                            찾아다녀도 목숨이 있는 때까지 방황하여도 보이는 것은 
                            거친 모래뿐일 것이다</p>
                            <p><sapn>모집기간 :</sapn> </p>
                        </div>
                    </div>
                </div>
                <div class="recru-detail-map ">
                    <img class="recru-map" src="@/assets/img/camping_gear02.jpg">
                </div>
        </div>  
       
    </div>
</template>
<script>
    export default{
        data:function(){
            return{
                recruPost:{
                    title:'글제목',
                    writer:'writer',
                    date:new Date()
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
     /* 제목 */
     .recru-detail-header{
        margin: 10px;
     }
     /* 사진, 간략정보 */
    .recru-detail-body{
        display: flex;
        justify-content: center;
        text-align: left;
    }
    /* 유저 프로필 */
    .recru-detail-user{
        display: flex;  
        margin: 10px;
    }
    .recru-detail-user img{
        width: 40px;
        height: 40px;
        border-radius: 50%;
    }
    .recru-detail-user p{
        vertical-align: middle;
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
    }
    /* 기본정보 */
   .recru-detail-post > p{
       margin: 5px;
   }
    /* 장비사진, 지도 크기 */
    .recru-detail-img, .recru-detail-info { 
        width: 45%;
        max-width: 380px;
        margin: 10px;
        display: block;
    }
    .recru-detail-img img, .recru-detail-map img{
        width: 100%;
    }
    .recru-detail-info p {
        margin: 7px;
    }
</style>