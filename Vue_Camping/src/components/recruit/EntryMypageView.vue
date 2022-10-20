<template>
<div class="entry-mypage-container">
      <div class="entry-mypage-box">
        <div class="entry-mypage-title">
            <h2>여기 갈래</h2>
        </div>
        <div v-for="entryPost in EntryList" :key="entryPost.entryId" class="entry-mypage-mini-box" >
            <div class="entry-mypage-status">
                <p class="entry-recru-status status-box">{{rStatus}}</p>
                <p class="entry-status status-box">{{eStatus(entryPost.entryStatus)}}</p>
            </div>
            <div class="row">
                <EntryMypageCard v-bind:recruId="entryPost.recruId"
                    @setRecruStatus="recruStatus"></EntryMypageCard>
                <div class="entry-mypage-btn">
                        <button class="entry-review-btn">동행자 평가</button>
                        <button class="entry-status-btn">{{dayStatus}}</button>
                </div>
            </div>
            <div class="entry-mypage-bar"></div>
        </div>
      </div>
  </div>
</template>
<script>
import EntryMypageCard from "@/components/recruit/EntryMypageCard.vue";

export default{
    components: {
        EntryMypageCard
    },
    data : function(){
        return{
            memberId : sessionStorage.getItem("email"),//세션에서 받을 로그인 정보
            EntryList : [],
            rStatus : '',
        }
    },
    mounted(){
        this.loadData();
    },
    methods : {
        loadData : function(){
            //로그인한 유저의 아이디로 신청정보를 받아온다
            const component = this;
            fetch(`http://localhost:8087/java/recru/entry/${component.memberId}`,{
                method : "post",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify(component.memberId)
            })
            .then((response) =>response.json()) 
            .then(data => { 
                console.log(data);
                component.EntryList = data;
            }).catch(err=>console.log(err));
        },
        recruStatus: function(status){
            if (this.status==0){
                    this.rStatus = '모집중'
                }else if(status==1){
                    this.rStatus = '모집완료'
                }else{
                    this.rStatus = '모집취소'
                }
        },
        eStatus : function(status){
            if (status==0){
                    return '신청중'
                }else if(status==1){
                    return '신청 수락'
                }else if(status==2){
                    return '거절됨'
                }else if(status==2){
                    return '취소대기'
                }else{
                    return '취소'
                }
        }
    }
}
</script>
<style scoped src="@/assets/css/recruit/myPage.css" />