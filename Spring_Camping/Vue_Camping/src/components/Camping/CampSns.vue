<template>
  <div class="camp-sns-container">
    <div class="camp-sns-card" v-for="snsImg of snsImgs" @click="clickSnsCard(snsImg.writeNo)">
      <input type="text" :value="snsImg.writeNo" style="display :none;">
      <img :src="'http://localhost:8087/java/showSnsImage/' + snsImg.snsPath + '/' + snsImg.storedName">
    </div>
  </div>
</template>

<script>
export default {
  props: ['snsImgs'],
  methods: {
    clickSnsCard(writeNo) {
      this.$router.push({ name: 'SnsDetail', params: { writeNo: writeNo } });
    }
  }
}
</script>

<style scoped>
.camp-sns-container {
  height: 300px;
  width: 100%;
  overflow-x: auto;
  overflow-y: hidden;
  white-space: nowrap;
  padding:10px;
}

/* total width */
.camp-sns-container::-webkit-scrollbar {
  background-color: #fff;
  width: 16px
}

/* background of the scrollbar except button or resizer */
.camp-sns-container::-webkit-scrollbar-track {
  background-color: #fff
}

.camp-sns-container::-webkit-scrollbar-track:hover {
  background-color: #f4f4f4
}

/* scrollbar itself */
.camp-sns-container::-webkit-scrollbar-thumb {
  background-color: rgb(228, 239, 231);
  border-radius: 16px;
  border: 5px solid #fff
}

.camp-sns-container::-webkit-scrollbar-thumb:hover {
  background-color: rgb(6, 68, 32);
  border: 4px solid #f4f4f4
}

/* set button(top and bottom of the scrollbar) */
.camp-sns-container::-webkit-scrollbar-button {
  display: none
}

.camp-sns-card {
  display:inline-block;
  width: 250px;
  height: 250px;
  border: 1px solid rgb(240,240,240);
  border-radius: 10px;
  margin: 5px;
  cursor: pointer;
}
.camp-sns-card:hover {
  border: 2px solid gray;
  border-radius: 10px;
}
.camp-sns-card img {
  width:100%;
  height:100%;
}
</style>