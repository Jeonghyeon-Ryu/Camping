<template>
<div class="entry-mypage-container">
      <div class="entry-mypage-box">
        <div class="entry-mypage-title">
            <h2>보증금 확인</h2>
        </div>
        <div v-for="entryPost in EntryList" :key="entryPost.entryId" class="entry-mypage-mini-box" >
            <div class="row">
                <EntryMypageCard v-bind:recruId="entryPost.recru_id" style="width:70%"></EntryMypageCard>
                <DepositStatus v-bind:entryId="entryPost.entryId"></DepositStatus>
            </div>
            <div class="entry-mypage-bar"></div>
        </div>
      </div>
  </div>
</template>

<script>
import EntryMypageCard from "@/components/recruit/EntryMypageCard.vue";    //참가한 모집내역 카드 컴포넌트
import DepositStatus from "./DepositStatus.vue";    //보증단계 카드 컴포넌트

import EntryPost from "@/assets/rectuitInfo/EntryPost.js";  


export default{
        components: {
            EntryMypageCard,
            DepositStatus,
        },
        data : function(){
            return{
                EntryList : []
            }
        },
        created(){
            //아이디의 신청 내역 가져오기
            this.EntryList = EntryPost.data;
        }
    }
</script>

<style scoped src="@/assets/css/recruit/myPage.css" />
<style scoped>
</style>