<template>
  <div class="recru-mypage-container">
        <div class="recru-mypage-box">
          <!-- 리스트 -->
        <div class="container">
            <!-- 카드 -->
            <div class="recru-card-box">
                <div v-for="recruInfo in recruPosts" :key="recruInfo.title">
                    <router-link tag="div" v-bind:to="{name:'recruDetail',params : {recruId : recruInfo.recruId}}">
                      <RecruCard v-bind:recruCard="recruInfo"></RecruCard>
                    </router-link>
                </div>
            </div>      
        </div>
        </div>
    </div>
  </template>
  <script>  
  import RecruCard from "@/components/recruit/RecruCard.vue";
  export default{
    components : {
      RecruCard
    },
    data : function(){
        return{
          recruPosts : [],
        }
    },
    created(){
        //접속한 아이디의 동행모집글 가져오기
        //모집상태 : 0 모집중, 1 모집완료, 2 실패
        
    }
}
  </script>
  