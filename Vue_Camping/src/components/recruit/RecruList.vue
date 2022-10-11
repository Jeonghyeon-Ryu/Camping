<template>
    <div class="recru-list-container">      
        <div id="recru-list-header">
            <h2>캠핑 동행 모집</h2>
        </div>
        <!-- 검색 -->
        <KeywordSearch v-bind:srhKeyword="srhKeyword"></KeywordSearch>
        <div style="text-align: center;">
            <!-- 필터버튼 -->
            <button @click="toggleFilter" class="btn badge badge-light bg-light badge-md false text-dark">필터</button>
        </div>
            <!-- 필터 -->
            <div id="recru-list-container" class="position-relative border-radius-xl overflow-hidden shadow-lg mb-7">
                <div v-if="filterStatus">
                    <RecruFilter/>
                </div>
            </div>
        <!-- 리스트 -->
        <div class="container">
            <!-- 카드 -->
            <div class="recru-card-box">
                <div v-for="recruInfo in recruPosts" :key="recruInfo.title">
                    <router-link tag="RouterCard" v-bind:to="{name:'recruDetail',params:{recruId:'recruInfo.id'}}">
                        <RecruCard v-bind:recruCard="recruInfo"></RecruCard>
                    </router-link>
                </div>
            </div>      
        </div>
    </div>
</template>
<script>
    import KeywordSearch from "@/components/KeywordSearch.vue";
    import RecruFilter from "@/components/recruit/RecruFilter.vue";
    import RecruCard from "@/components/recruit/RecruCard.vue";

    export default{
        components: {
            KeywordSearch,
            RecruFilter,
            RecruCard
        },
        data : function(){
            return{
                filterStatus : false,
                recruPosts : [ ]
            }
        },
        created(){
            // 서버에서 전체 리스트 가져오기
            fetch("http://localhost:8088/java/recru")
            .then((response) =>response.json()) 
            .then(data => { 
                console.log(data);
                this.recruPosts = data;  

            }).catch(err=>console.log(err));
        },
        methods: {
            toggleFilter : function(){
                this.filterStatus = !this.filterStatus;
            },
            searchList : function()
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
  .recru-list-container{
    margin-top: 150px;
  }
    /* 카드 목록 */
  .recru-card-box{
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }
</style>