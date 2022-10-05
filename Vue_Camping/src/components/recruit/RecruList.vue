<template>
    <div class="container mt-5">      
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
        <div class="container mt-5">
            <!-- 카드 -->
            <div class="recru-card-box">
                <div v-for="recruInfo in recruPosts" :key="recruInfo.title">
                    <RecruCard v-bind:recruCard="recruInfo"></RecruCard>
                    <!-- <router-link tag="RouterCard" v-bind:to="{name:recruCard,params:{recruCard:'recruInfo'}}"></router-link> -->
                </div>
            </div>      
        </div>
    </div>
</template>
<script>
    import KeywordSearch from "./KeywordSearch.vue";
    import RecruFilter from "./RecruFilter.vue";
    import RecruCard from "./RecruCard.vue";

    export default{
        components: {
            KeywordSearch,
            RecruFilter,
            RecruCard
        },
        data : function(){
            return{
                filterStatus : false,
                recruPosts : [
                                {
                                gear_img : '@/assets/img/gear04.jpg',
                                title : 'tnwjdfasfd',
                                my_gear : '4인용텐트',
                                need_gear : '타프',
                                go_date : '2022/11/11',
                                camping_point : '순천만'
                                },
                                {
                                gear_img : '@/assets/img/gear06.jpg',
                                title : '수정된222',
                                my_gear : '4인용텐트222',
                                need_gear : '타프',
                                go_date : '2022/11/11',
                                camping_point : '순천만'
                                },
                                {
                                gear_img : '@/assets/img/gear06.jpg',
                                title : '수정된23322sssssssadsdasddddddddddddddasdsaddsdsds',
                                my_gear : '4인용텐트 길게 길게 글을 쓰면 어떻게 될까요 dffff  fffff ff fff ffffffffffffffffffff222',
                                need_gear : '타프만 필요한가 가진건 아무것도 없고 캠핑에 대해 아는 것도 일도 없는 생초보라 지식가득 장비 가득 고인물을 원합니다 츄라이 츄라이',
                                go_date : '2022/11/11',
                                camping_point : '순천만'
                                }
                             ]
            }
        },
        methods: {
            toggleFilter : function(){
                this.filterStatus = !this.filterStatus;
            }
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
    /* 카드 목록 */
  .recru-card-box{
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }
</style>