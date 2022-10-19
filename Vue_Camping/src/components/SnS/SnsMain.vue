<template>
  <div class="sns-container">
    <!-- <div class="sns-searchbox">
      <input type="search" @keyup="checkEnter($event)" v-model="searchText" placeholder="검색어를 입력해주세요.">
      <button @click="doSearch" style="display: none;">조회</button>
    </div> -->

    <div class="sns-img-container">
      <!-- <SnsDetailImage></SnsDetailImage> -->
      <div class="sns-img-row-container" :key="snsImg.writeNo" v-for="snsImg in snsImgs" @click="getSnsDetail(snsImg.writeNo)">
        <div class="sns-img">
          <input type="text" :value="snsImg.writeNo" style="display :none;">
          <img :src="'http://localhost:8087/java/showSnsImage/'+snsImg.snsPath+'/'+snsImg.storedName">
          <!-- <img v-bind:src="snsImg.Img"> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import SnsDetailImage from './SnsDetailImage.vue';
export default {
  data: function () {
    return {
      page: 1,
      snsImgs: [],
    };
  },
  created: function () {
    fetch("http://localhost:8087/java/sns/" + this.page)
      .then(result => result.json())
      .then(result => {
        
        this.snsImgs = result;
      })
      .catch(err => console.log(err));
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
    }
  }
}
</script>



<style scoped>
.sns-container{
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
  }
  .sns-image{
    
    width: 30%;
  }


.sns-container {
  width: 100vw;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.sns-searchbox {
  width: 100vw;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin-bottom: 10px;
  margin-top: 10px;
}

.sns-searchbox input[type=search] {
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

/* .sns-searchbox input[type=button] {
    padding: 15px;
    border: none;
    border-radius: 5px;
    background-color: rgb(255, 255, 255);
  } */

.sns-img-container {
  width: 70%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.sns-img-row-container {
  width: 30%;
  height: 350px;
  cursor: pointer;
}

.sns-img {
  margin-bottom: 10px;
  margin-right: 10px;
  cursor: pointer;
  width: 100%;
  height: 100%;

}
.sns-img img{
  width: 100%;
  height: 100%;
  object-fit : cover;
}
</style>