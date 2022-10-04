<template>
    <div class="container mt-5">
        <div class="position-relative border-radius-xl overflow-hidden shadow-lg mb-7">
            <div class="container">
                <div class="recru-detail-header row">
                    <h2>{{recruPost.title}}</h2>
                </div>
                <div class="recru-detail-body row">
                    <div class="recru-detail-img col-lg-6 my-auto" >
                        <img src='../assets/img/camping_gear01.jpg'>
                    </div>
                    <div class="recru-detail-info col-lg-6 my-auto">
                        <div class="recru-detail-user">
                            <a href="javascript:;">
                                <MaterialAvatar :image="team4" alt="Image placeholder" size="sm" />
                            </a>
                            <h6>{{recruPost.writer}}</h6>
                            <p>{{recruPost.date}}</p>
                            <button class="btn badge badge-light bg-light badge-md rounded-pill text-dark"></button>
                        </div>
                        <div class="recru-detail-post">
                            <p><span>성별 : </span></p>
                            <p><span>연령대 : </span></p>
                            <hr>
                            <p style="font-weight: bold;">이런 분 함께해요</p>
                            <p><span>성별 : </span></p>
                            <p><span>연령대 : </span></p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="recru-detail-body col-lg-6 my-auto">
                        <div>
                            <p><span>여행 예정 날짜 : </span></p>
                            <p><span>출발 : </span></p>
                            <p><span>도착 : </span></p>
                            <p><span>모집 인원 : </span></p>
                        </div>
                        <div>
                            <p><span>갖고있어요 : </span></p>
                            <p><span>필요해요 : </span></p>
                        </div>
                        <div class="recru-detail-content">
                            <p>모집내용 ~~~ 동산에는 사랑의 풀이 돋고 이상의 꽃이 피고 
                                희망의 놀이 뜨고 열락의 새가 운다사랑의 풀이 없으면 
                                인간은 사막이다 오아이스도 없는 사막이다 보이는 끝까지 
                                찾아다녀도 목숨이 있는 때까지 방황하여도 보이는 것은 
                                거친 모래뿐일 것이다</p>
                                <p><sapn>모집기간 :</sapn> </p>
                            </div>
                        </div>
                    </div>
                    <div class="recru-detail-map">
                        <img class="recru-map" src="../assets/img/camping_gear02.jpg">
                    </div>
            </div>
        </div>
    </div>
</template>
<script>
    import MaterialAvatar from "@/components/MaterialAvatar.vue";
    import team4 from "../assets/img/team-4.jpg";
    export default{
        components : {
            MaterialAvatar
        },
        data:function(){
            return{
                recruPost:{
                    title:'글제목',
                    writer:'',
                    date:''
                }
            }
        }
    }
</script>
<style scoped>
      /* 공통 부분 */
  * {
    margin: 0;
    padding: 0;
    list-style: none;
    font-style: none;
    box-sizing: border-box;
  }
  .recru-detail-header, recru-detail-header div{
    margin: 10px;
}
  /* 장비사진, 지도 크기 */
  .recru-detail-img, .recru-detail-info { 
    width: 40%;
    display: block;
    text-align: center;
  }
  .recru-detail-img img, .recru-detail-map img{
    width: 100%;
  }
  /* 유저 프로필 */
  .recru-detail-user{
    display: flex;  
    flex-wrap: nowrap;
    margin: 10px;
  }

</style>