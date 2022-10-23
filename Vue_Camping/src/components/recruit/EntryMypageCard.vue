<template>
    <div class="entry-mini-container">
        <div class="entry-mini-box">
            <div class="entry-mini-card row">
                <div class="recru-mini-img" @click="goDetail" style="cursor: pointer;">
                    <div v-if="image.imageId==0">
                        <img src="@/assets/img/bg9.jpg" alt="camping gear">
                    </div>
                    <div v-if="image.imageId>0">
                        <img :src="'http://localhost:8087/java/recruImg/'+image.imgPath+'/'+image.storedName" alt="캠핑도구 사진"/>
                    </div>
                </div>
                <div class="col">
                    <div class="recru-mini-title row" @click="goDetail" style="cursor: pointer;">
                        <h3>{{recruInfo.recruTitle}}</h3>
                    </div>
                    <div class="recru-mini-contents">
                        <p>마감일 : {{recruInfo.closingDate}}</p>
                        <p>여행날짜 : {{recruInfo.goDate}} ~ {{recruInfo.comeDate}}</p>
                        <p>출발지 : {{recruInfo.startingPoint}}</p>
                        <p>도착지 : {{recruInfo.campingPoint}}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

export default{
    props :{recruId : Number},
    data: function(){
        return{
            recruInfo : {},
            image: "",
        }
    },
    created : function(){
        this.getEntryRecru();
    },
    methods :{
        getEntryRecru(){
            const recruId = this.recruId;
            //서버에서 신청 대상 모집글 정보를 가져온다
            fetch(`http://localhost:8087/java/recru/${recruId}`)
                .then((response) =>response.json()) 
                .then(data => { 
                    this.recruInfo = data;  
                    this.$emit('setRecruStatus',data.recruStatus)
                    var today = new Date();
                    console.log(today-data.goDate)
                    if(data.goDate < today){
                        this.$emit('setTripStatus','대기')
                    }else{
                        this.$emit('setTripStatus','완료')
                    }
                }).catch(err=>console.log(err));
            
            fetch(`http://localhost:8087/java/recruImg/${recruId}`)
                .then(result => result.json())
                .then(result => {
                this.image = result[0];
            })
                .catch(err => console.log(err));
        },
        goDetail(){
            //상세페이지로 이동하는 메소드 호출
            this.$emit('goRecruDetail',this.recruInfo.recruId)
        }
    },
}
</script>
<style scoped src="@/assets/css/recruit/EntryMypageCard.css" />