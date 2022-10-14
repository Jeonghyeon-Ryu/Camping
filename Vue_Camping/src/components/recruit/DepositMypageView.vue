<template>
<div class="entry-mypage-container">
      <div class="entry-mypage-box">
        <div class="entry-mypage-title">
            <h2>보증금 확인</h2>
        </div>
        <div v-for="entryPost in EntryList" :key="entryPost.entryId" class="entry-mypage-mini-box" >
            <div class="row">
                <!-- 모집/참가한 모집 중 모집 기한이 만료된 모집글 내역 -->
                <EntryMypageCard v-bind:recruId="entryPost.recru_id" style="width:70%"></EntryMypageCard>
                <!-- 유저id와 모집글id로 보증내역에 접근 (deposit정보 보내기) 지금은 임의로 대체함-->
                <DepositStatus v-bind:depositId="entryPost.recru_id"></DepositStatus>
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
            EntryPost
        },
        data : function(){
            return{
                EntryList : [],
                depositId : ''
            }
        },
        created(){
            //접속한 아이디의 신청/모집 내역 중 모집 완료된 내역 가져오기
            //신청상태 : 0 신청중, 1 수락, 2 취소대기, 3 취소 4 거절
            //모집상태 : 0 모집중, 1 모집완료, 2 실패
            this.EntryList = EntryPost.data;

            //유저id와 모집글id로 보증내역에 접근해서 해당 보증내역 찾기 (id..만 아니라 전부 넘길까?)


        }
    }
</script>

<style scoped src="@/assets/css/recruit/myPage.css" />