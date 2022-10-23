<template>
<div class="entry-mypage-container">
      <div class="entry-mypage-box">
        <div class="entry-mypage-title">
            <h2>여기 갈래</h2>
        </div>
        <div v-for="entryPost in EntryList" :key="entryPost.entryId" class="entry-mypage-mini-box" >
            <div class="entry-mypage-status">
                <p class="entry-recru-status status-box">{{recruStatus(entryPost.recruStatus)}}</p>
                <p class="entry-status status-box">{{eStatus(entryPost.entryStatus)}}</p>
            </div>
            <div class="row">
                <EntryMypageCard v-bind:recruId="entryPost.recruId" @goRecruDetail="goRecruDetail"></EntryMypageCard>
                <div class="entry-mypage-btn">
                        <button v-if="entryPost.recruStatus==0" class="entry-review-btn">신청 취소</button>
                        <button v-if="entryPost.recruStatus==1" class="entry-review-btn">신청 취소</button>
                        <button v-if="entryPost.recruStatus==3" @click="recruReview" class="entry-review-btn">후기 등록</button>
                        <button v-if="entryPost.recruStatus==3" class="entry-status-btn" disabled>여행완료</button>
                </div>
            </div>
            <div class="entry-mypage-bar"></div>
        </div>
      </div>
  </div>
</template>
<script>
import EntryMypageCard from "@/components/recruit/EntryMypageCard.vue";
import Swal from 'sweetalert2';
export default{
    components: {
        EntryMypageCard
    },
    data : function(){
        return{
            memberId : this.$store.state.email,//세션에서 받을 로그인 정보
            EntryList : [],
            rStatus : '',
            tStatus : false
        }
    },
    mounted(){
        if(this.$store.state.email==null){
                Swal.fire({
                        title: '로그인이 필요한 서비스입니다.',
                        text: "로그인 페이지로 이동하겠습니까?",
                        icon: 'warning',
                        showCancelButton: true,
                        // confirmButtonColor: '#3085d6',
                        // cancelButtonColor: '#d33',
                        confirmButtonText: '네',
                        cancelButtonText : '아니오'
                    }).then((result) => {
                    if (result.isConfirmed) {
                        this.$router.push({ name: 'LoginSignup'})
                    }else{
                        this.$router.go(-1);
                    }
                })
        }
        this.loadData();
    },
    methods : {
        loadData : function(){
            //로그인한 유저의 아이디로 신청정보를 받아온다
            const component = this;
            fetch(`http://localhost:8087/java/recru/entry/mypage/${this.memberId}`)
            .then((response) =>response.json()) 
            .then(data => { 
                console.log(data);
                component.EntryList = data;
            }).catch(err=>console.log(err));
        },
        recruStatus: function(status){
            if (status==0){
                return '모집중'
            }else if(status==1){
                return '모집완료'
            }else{
                return '모집취소'
            }
        },
        eStatus : function(status){
            if (status==0){
                    return '수락 대기'
                }else if(status==1){
                    return '신청완료'
                }else if(status==2){
                    return '거절됨'
                }else if(status==2){
                    return '취소대기'
                }else{
                    return '취소'
                }
        },
        goRecruDetail(recruId){
            //디테일 페이지로 이동
            this.$router.push({name:'recruDetail',params : {recruId : recruId}})
        },
        recruReview : function(){
            this.$router.push({name : 'RecruReview',params : {recruId : this.recruId}})
        }
    }
}
</script>
<style scoped src="@/assets/css/recruit/myPage.css" />