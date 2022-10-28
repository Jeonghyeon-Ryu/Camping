<template>
  <div class="vertical-card-container">
    <div class="card" v-for="data of datas">
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
        <button class="card-button" type="button">More Info</button>
      </div>
    </div>
  </div>
</template>
<script>
import CampListImage from '../Camping/CampListImage.vue';

export default {
  props: ["datas"],
  data: function () {
    return {
      resultDatas: '',
    }
  },
  components: { CampListImage }
}
</script>
<style scoped src="./VerticalCard.css">

</style>