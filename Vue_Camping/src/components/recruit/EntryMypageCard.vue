<template>
    <div class="entry-mini-container">
        <div class="entry-mini-box">
            <div class="entry-mini-card row">
                <div class="recru-mini-img">
                    <img src="@/assets/img/camping_gear07.jpg" alt="">
                </div>
                <div class="col">
                    <div class="recru-mini-title row">
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
        }
    },
    created : function(){
        this.getEntryRecru();
    },
    methods :{
        getEntryRecru(){
            const component = this;
            //서버에서 신청 대상 모집글 정보를 가져온다
            fetch(`http://localhost:8087/java/recru/${component.recruId}`)
                .then((response) =>response.json()) 
                .then(data => { 
                    console.log(data);
                    component.recruInfo = data;  
                    component.$emit('setRecruStatus',data.recruStatus)
                }).catch(err=>console.log(err));
        }
    }
}
</script>
<style scoped src="@/assets/css/recruit/EntryMypageCard.css" />