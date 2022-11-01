<template>
  <div class="article-container">
    <div class="article-left">
      <div class="article-left-title">같이갈래?</div>
      <div class="recru-card-box">
        <div v-for="recruInfo in recruPosts" :key="recruInfo.recruId" style="position:relative;">
          <RecruStatus :recruStatus="recruInfo.recruStatus"
            style="position:absolute;width:70px;font-size:small;font-weight: bold; top:0;left: 20px;"></RecruStatus>
          <router-link tag="div" v-bind:to="{ name: 'recruDetail', params: { recruId: recruInfo.recruId } }"
            @click.prevent.stop>
            <RecruCard v-bind:recruCard="recruInfo"></RecruCard>
          </router-link>
        </div>
      </div>
    </div>
    <div class="article-right">
      <div class="article-right-top">
        <div class="article-left-title">중고장터</div>
        <div class="used-cards">
          <div v-for="card in usedList" :key="card.id">
            <router-link tag="div" v-bind:to="{ name: 'usedDetail', params: { usedId: card.usedId } }">
              <UsedCard v-bind:usedCard="card"></UsedCard>
            </router-link>
          </div>
        </div>
      </div>
      <div class="article-right-bottom">
        <div class="article-left-title">추천 후기</div>
        <div class="sns-cards">
          <div class="sns-card" v-for="snsImg of snsImgs" @click="clickSnsCard(snsImg.writeNo)">
            <input type="text" :value="snsImg.writeNo" style="display :none;">
            <img :src="'http://13.125.95.210:85/java/showSnsImage/' + snsImg.snsPath + '/' + snsImg.storedName">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import RecruCard from '../recruit/RecruCard.vue';
import RecruSaveHeart from '../recruit/RecruSaveHeart.vue';
import RecruStatus from '../recruit/RecruStatus.vue';
import UsedCard from '../UsedPractice/UsedCard.vue';

export default {
  data() {
    return {
      recruPosts: [],
      usedList: [],
      snsImgs: [],
    };
  },
  created: function () {
    fetch(`http://13.125.95.210:85/java/recru/page/3/1`)
      .then((response) => response.json())
      .then(data => {
        for (let key in data) {
          this.recruPosts.push(data[key]);
        }
      }).catch(err => console.log(err));

    // 중고
    fetch('http://13.125.95.210:85/java/used/usedMain/1')
      .then(Response => Response.json())  //json 파싱 
      .then(data => {
        for (let key in data) {
          this.usedList.push(data[key]);
        }
        console.log(data);
      }).catch(err => console.log(err))

    // SNS
    fetch('http://13.125.95.210:85/java/sns/1')
      .then(result => result.json())
      .then(result => {

        this.snsImgs = result;
      })
      .catch(err => console.log(err));
  },
  components: { RecruCard, RecruSaveHeart, RecruStatus, UsedCard }
} 
</script>
<style scoped>
a{
  text-decoration: none;
}
a:visited{
  color:black;
}
.article-container {
  width: 100%;
  height: fit-content;
  display: flex;
  flex-direction: column;
  /* justify-content: center;
  align-items: center; */
  position: relative;
  top: -400px;
}
.article-left {
  width: 100%;
  flex-wrap: wrap;
  /* padding: 20px; */
}
.article-left-title{
  width:100%;
  height: 40px;
  /* background-color: rgb(49, 69, 53); */
  padding: 10px 10px 10px 50px;
  text-align: left;
  font-weight: bold;
  font-size: 1.1em;
  color: rgb(54, 91, 61);
}
.recru-card-box{
  width: 100%;
  display: flex;
  padding: 0 1.5% 1.5% 1.5%;
  /* overflow-x: scroll; */
  margin: 0 auto;
}
.article-right {
  padding: 0 10px 0 10px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  /* align-items: flex-start;
  justify-content: flex-start; */
}

.article-right-top {
  width: 50%;
  height: 385px;
  padding: 10px;
  display: block;
  overflow-y: hidden;
}

.used-cards {
  display: flex;
  flex-wrap: wrap;
  height: 690px;
  overflow: hidden;
}

.used-cards>div {
  width: 50%;
}

.article-right-bottom {
  width: 50%;
  height: 380px;
  padding: 10px;
  display: block;
  overflow-y: hidden;
}
.sns-cards {
  display: flex;
  flex-wrap: wrap;
  height: 690px;
  overflow: hidden;
  /* justify-content: space-around; */
}
.sns-card {
  width: 300px;
  height:320px;
  margin:10px;
}
.sns-card img{
  border:1px solid gray;
  height:320px;;
  width:300px;
}
</style>