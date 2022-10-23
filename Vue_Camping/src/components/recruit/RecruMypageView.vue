<template>
  <div class="recru-mypage-container ">
        <div class="recru-mypage-box col">
            <div class="recru-mypage-title">
                <h2>나랑 가자</h2>
            </div>
          <!-- 리스트 -->
          <div class="container row">
              <!-- 카드 -->
              <div class="recru-card-box row">
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
          memberId : this.$store.state.email,
          recruPosts : [],
        }
    },
    created(){
        //접속한 아이디의 동행모집글 가져오기
        this.loadData();
        //모집상태 : 0 모집중, 1 모집완료, 2 실패
        
    },
    methods : {
      loadData : function(){
            // 서버에서 전체 리스트 가져오기
            fetch("http://localhost:8087/java/recru/mypage/"+this.memberId)
            .then((response) =>response.json()) 
            .then(data => { 
                console.log(data);
                this.recruPosts = data;  

            }).catch(err=>console.log(err));
        },
    }
}
</script>

<style scoped>
/* 공통부분 */
* {
margin: 0;
padding: 0;
list-style: none;
font-style: none;
box-sizing: border-box;
}
a{
  text-decoration: none;
}
.row{
display: flex;
flex-wrap: wrap;
justify-content: center;
}
.col{
display: block;
}
.recru-mypage-container{
  margin: 150px 0;  
}
.recru-mypage-box{
  width: 90%;
  margin: 0 auto;
  justify-content: center;
}
.recru-mypage-title h2{
  text-align: left;
  padding: 30px 0 30px 50px;
}
.recru-card-box{
  justify-content: left;  
}
</style>
  