<template>
<div class="entry-mypage-container">
      <div class="entry-mypage-box">
        <div class="entry-mypage-title">
            <h2>캠핑 동행 관리</h2>
        </div>
        <div v-for="entryPost in EntryList" :key="entryPost.entryId" class="entry-mypage-mini-box" >
            <div class="entry-mypage-status">
                <p class="entry-recru-status status-box">모집완료</p>
                <p class="entry-status status-box">취소대기</p>
            </div>
            <div class="row">
                <EntryMypageCard v-bind:recruId="entryPost.recru_id"></EntryMypageCard>
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
import EntryPost from "@/assets/rectuitInfo/EntryPost.js";
export default{
        components: {
            EntryMypageCard
        },
        data : function(){
            return{
                EntryList : []
            }
        },
        mounted(){
            this.EntryList = EntryPost.data
        }
    }
</script>
<style scoped src="@/assets/css/recruit/myPage.css" />