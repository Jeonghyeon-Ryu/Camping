<template>
    <div class="entry-mini-container">
        <div class="entry-mini-box">
            <div class="entry-mini-card row">
                <div class="recru-mini-img">
                    <img src="@/assets/img/camping_gear07.jpg" alt="">
                </div>
                <div class="col">
                    <div class="recru-mini-title row">
                        <h3>{{recruInfo.recru_title}}</h3>
                    </div>
                    <div class="recru-mini-contents">
                        <p>마감일 : {{recruInfo.closing_date}}</p>
                        <p>여행날짜 : {{recruInfo.go_date}} ~ {{recruInfo.come_date}}</p>
                        <p>출발지 : {{recruInfo.starting_point}}</p>
                        <p>도착지 : {{recruInfo.camping_point}}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import RecruPost from "@/assets/rectuitInfo/RecruPost.js";

export default{
    props :{recruId : String},
    components : {
        RecruPost
    },
    data: function(){
        return{
            recruInfo : {},
        }
    },
    created : function(){
        this.getEntryRecru();
    },
    methods :{
        getEntryRecru(){
            //서버에서 신청 대상 모집글 정보를 가져오는 메서드
            for(let i=0 ; i<RecruPost.data.length; i++){
                if((RecruPost.data[i]["recru_id"] == this.recruId)){
                    this.recruInfo = RecruPost.data[i];
                }
            }
        }
    }
}
</script>
<style>
/* 공통 부분 */
* {
    margin: 0;
    padding: 0;
    list-style: none;
    font-style: none;
    box-sizing: border-box;
}
.entry-mini-container{
    width: 60%;
    min-width: 520px;
}
.entry-mini-box{
    padding: 5px 10px;
    text-align: left;
}
.entry-mini-card{
    padding: 20px;
    background: white;
}
.row{
    display: flex;
}
.recru-mini-img{
    width: 140px;
    height: 140px;
    margin-right: 20px;
}
.recru-mini-img img{
    width: 100%;
    height: 100%;
}
.recru-mini-title h3{
    margin: 3px 10px 0 0;
}

.recru-mini-contents p{
    margin: 5px 0;
    text-align: left;
}

</style>