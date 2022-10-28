<template>
  <div class="vertical-card-container">
    <div class="card" v-for="data of resultDatas">
      <div class="card-image">
        <CampListImage :campId="data.campId"></CampListImage>
      </div>
      <div class="card-info">
        <div class="card-title">{{ data.campName }}</div>
        <div class="card-detail">{{ data.campDetail }}</div>
        <div class="card-footer">
          <p class="card-description">
            <img v-if="data.campInfo.toilet" width="20" src="@/assets/img/Camping/toilet.png" />
            <img v-if="data.campInfo.parking" width="20" src="@/assets/img/Camping/parking.png" />
            <img v-if="data.campInfo.shower" width="20" src="@/assets/img/Camping/shower.png" />
            <img v-if="data.campInfo.disposal" width="20" src="@/assets/img/Camping/disposal.png" />
            <img v-if="data.campInfo.deck" width="20" src="@/assets/img/Camping/deck.png" />
            <img v-if="data.campInfo.bbq" width="20" src="@/assets/img/Camping/bbq.png" />
            <img v-if="data.campInfo.swim" width="20" src="@/assets/img/Camping/swim.png" />
            <img v-if="data.campInfo.spoon" width="20" src="@/assets/img/Camping/spoon.png" />
            <img v-if="data.campInfo.lease" width="20" src="@/assets/img/Camping/lease.png" />
          </p>
        </div>
        <button class="card-button" type="button" @click="moreInfo(data.campId)">More Info</button>
      </div>
    </div>
  </div>
</template>
<script>
import CampListImage from '../Camping/CampListImage.vue';

export default {
  props: ["datas"],
  beforeUpdate() {
    this.dataProcess();
  },
  data: function () {
    return {
      resultDatas: '',
    }
  },
  methods: {
    moreInfo: function (campId) {
      this.$router.push({ name: 'CampDetail', params: { campId: campId } });
    },
    dataProcess: function () {
      let temp = this.datas;
      console.log(temp);
      for (let i = 0; i < temp.length; i++) {
        temp[i].campInfo = temp[i].campInfo.split(" ");
        let info = {
          toilet: false,
          parking: false,
          shower: false,
          disposal: false,
          deck: false,
          bbq: false,
          swin: false,
          spoon: false,
          lease: false
        };
        for (let infoTemp of temp[i].campInfo) {
          info[infoTemp] = true;
        }
        temp[i].campInfo = info;
      }
      this.resultDatas = temp;
    }
  },
  components: { CampListImage }
}
</script>
<style scoped src="./VerticalCard.css">

</style>