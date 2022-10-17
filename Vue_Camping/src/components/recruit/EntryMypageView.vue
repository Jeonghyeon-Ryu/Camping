<template>
<div class="entry-mypage-container">
      <div class="entry-mypage-box">
        <div class="entry-mypage-title">
            <h2>여기 갈래</h2>
        </div>
        <div v-for="entryPost in EntryList" :key="entryPost.entryId" class="entry-mypage-mini-box" >
            <div class="entry-mypage-status">
                <p class="entry-recru-status status-box">모집완료</p>
                <p class="entry-status status-box">취소대기</p>
            </div>
            <div class="row">
                <EntryMypageCard v-bind:recruId="entryPost.recruId"></EntryMypageCard>
                <div class="entry-mypage-btn">
                        <button class="entry-review-btn">동행자 평가</button>
                        <button class="entry-status-btn">여행완료</button>
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
            memberId : localStorage.getItem("email"),//세션에서 받을 로그인 정보
            EntryList : []
        }
    },
    mounted(){
        this.loadData();
    },
    methods : {
        loadData : function(){
            //로그인한 유저의 아이디로 신청정보를 받아온다
            const memberId= this.memberId;
            fetch(`http://localhost:8087/java/recru/entry/mypage/${memberId}`,{
                method : "post",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify(memberId)
            })
            .then((response) =>response.json()) 
            .then(data => { 
                console.log(data);
                this.EntryList = data;  
            }).catch(err=>console.log(err));
        }
    }
}
</script>
<style scoped src="@/assets/css/recruit/myPage.css" />