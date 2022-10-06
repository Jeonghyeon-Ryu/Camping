<template>
<div class="entry-mypage-container">
      <div class="entry-mypage-box">
        <div class="entry-mypage-title">
            <h2>보증금 확인</h2>
        </div>
        <div v-for="entryPost in EntryList" :key="entryPost.entryId" class="entry-mypage-mini-box" >
            <div class="row">
                <EntryMypageCard v-bind:entryInfo="entryPost" style="width:70%"></EntryMypageCard>
                <DepositStatus></DepositStatus>
            </div>
            <div class="entry-mypage-bar"></div>
        </div>
      </div>
  </div>
</template>

<script>
import EntryMypageCard from "@/components/recruit/EntryMypageCard.vue";
import EntryPost from "@/assets/rectuitInfo/EntryPost.js";
import DepositStatus from "./DepositStatus.vue";
export default{
        components: {
    EntryMypageCard,
    DepositStatus
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
<style scoped>
</style>