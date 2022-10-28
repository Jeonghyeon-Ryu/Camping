<template>
<div class="entry-mypage-container">
      <div class="entry-mypage-box">
        <div class="entry-mypage-title">
            <h2>보증금 관리</h2>
        </div>
        <div v-for="entryPost in EntryList" :key="entryPost.entryId" class="entry-mypage-mini-box" >
            <div class="row">
                <!-- 모집/참가한 모집 중 모집 기한이 만료된 모집글 내역 -->
                <EntryMypageCard :recruId="entryPost.recruId" @goRecruDetail="goRecruDetail" style="width:70%"></EntryMypageCard>
                <!-- 유저id와 모집글id로 보증내역에 접근 (deposit정보 보내기) 지금은 임의로 대체함-->
                <DepositStatus :recruId="entryPost.recruId"></DepositStatus>
            </div>
            <div class="entry-mypage-bar"></div>
        </div>
      </div>
  </div>
</template>

<script>
import EntryMypageCard from "@/components/recruit/EntryMypageCard.vue";    //참가한 모집내역 카드 컴포넌트
import DepositStatus from "./DepositStatus.vue";    //보증단계 카드 컴포넌트
import Swal from 'sweetalert2';
export default{
        components: {
            EntryMypageCard,
            DepositStatus,
        },
        data : function(){
            return{
                memberId : sessionStorage.getItem("email"),
                EntryList : [],
                depositId : ''
            }
        },
        created(){
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
            //접속한 아이디의 신청 내역 중 모집 완료된 내역 가져오기
            //신청상태 : 0 신청중, 1 수락, 2 취소대기, 3 취소 4 거절
            //모집상태 : 0 모집중, 1 모집완료, 2 실패
            const component = this;
            fetch("http://13.125.95.210:85/java/recru/entry/success/"+component.memberId,{
                method : "post",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify(component.memberId)
            })
            .then(Response => Response.json()) 
            .then(data => { 
                console.log(data)
                component.EntryList = data;
            })

        },
        methods :{
            goRecruDetail(recruId){
                //디테일 페이지로 이동
                this.$router.push({name:'recruDetail',params : {recruId : recruId}})
            }
        }
    }
</script>

<style scoped src="@/assets/css/recruit/myPage.css" />