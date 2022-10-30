<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <SnsSearch @showHashList="showHashList" ref="sSearch"></SnsSearch>
    </div>

    <div class="sns-img-container">
      <!-- <SnsDetailImage></SnsDetailImage> -->
      <div class="sns-img-row-container" :key="snsImg.writeNo" v-for="snsImg in snsImgs" @click="getSnsDetail(snsImg.writeNo)">
        <div class="sns-img">
          <input type="text" :value="snsImg.writeNo" style="display :none;">
          <img :src="'http://13.125.95.210:85/java/showSnsImage/'+snsImg.snsPath+'/'+snsImg.storedName">
          <!-- <img v-bind:src="snsImg.Img"> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SnsSearch from './SnsSearch.vue';

export default {
  props: ['hashtag'],
  data: function () {
    return {
      page: 1,
      snsImgs: [],
    };
  },
  mounted: function () {
    if(this.hashtag != undefined && this.hashtag != 'undefined'){
      this.showHashList(this.hashtag);
    }else{
      fetch('http://13.125.95.210:85/java/sns/' + this.page)
      .then(result => result.json())
      .then(result => {
        
        this.snsImgs = result;
      })
      .catch(err => console.log(err));
    }
  },
  // components: { SnsDetailImage },
  //검색
  methods: {
    doSearch() {
      console.log(this.searchText)
    },
    checkEnter(event) {
      if (event.keyCode == 13) {
        this.doSearch()
      }
    },
    getSnsDetail(writeNo) {
      this.$router.push({ name: 'SnsDetail', params: { writeNo: writeNo } });
    },
    showHashList(hashtag){
      let hash = hashtag.substring(1, hashtag.length);
      this.$refs.sSearch.setTag(hashtag);

      console.log('http://13.125.95.210:85/java/hashtagList/' + hash + "/" +this.page);
        fetch('http://13.125.95.210:85/java/hashtagList/' + hash + "/" +this.page)
        .then(result => result.json())
        .then(result => {
          console.log(result);
          this.snsImgs = result;
          console.log(result);
        })
        .catch(err => console.log(err));
    }
  },
  components: {
    SnsSearch
  },
}
</script>

<style scoped src="@/assets/css/sns/SnsMain.css">
</style>