<template>
  <div class="camplist-container">
    <div class="camplist-search-container">
      <CampSearch @search="search"></CampSearch>
    </div>
    <ul class="cards">
      <li v-for="camp of camps" @click="getCampDetail(camp.campId, $event)">
        <a href="" class="card">
          <div class="card-img-container">
            <CampListImage :campId="camp.campId"></CampListImage>
          </div>
          <div class="card-overlay">
            <div class="card-header">
              <svg class="card-arc" xmlns="http://www.w3.org/2000/svg">
                <path />
              </svg>
              <!-- <img class="card-thumb" src="img/img1.jpg" alt="" /> -->
              <div class="card-header-text">
                <div v-text="camp.campId" class="camp-id" style="display:none"></div>
                <h3 v-text="camp.campName" class="card-title"></h3>
                <span v-text="camp.campAddress" class="card-tagline"></span>
                <span v-text="camp.regdate" class="card-status"></span>
              </div>
            </div>
            <p class="card-description">
              <img v-if="camp.campInfo.toilet" width="20" src="@/assets/img/Camping/toilet.png" />
              <img v-if="camp.campInfo.parking" width="20" src="@/assets/img/Camping/parking.png" />
              <img v-if="camp.campInfo.shower" width="20" src="@/assets/img/Camping/shower.png" />
              <img v-if="camp.campInfo.disposal" width="20" src="@/assets/img/Camping/disposal.png" />
              <img v-if="camp.campInfo.deck" width="20" src="@/assets/img/Camping/deck.png" />
              <img v-if="camp.campInfo.bbq" width="20" src="@/assets/img/Camping/bbq.png" />
              <img v-if="camp.campInfo.swim" width="20" src="@/assets/img/Camping/swim.png" />
              <img v-if="camp.campInfo.spoon" width="20" src="@/assets/img/Camping/spoon.png" />
              <img v-if="camp.campInfo.lease" width="20" src="@/assets/img/Camping/lease.png" />
            </p>
          </div>
        </a>
      </li>
    </ul>
  </div>
</template>
<script>
import CampListImage from './CampListImage.vue';
import CampSearch from './CampSearch.vue';
export default {
  data: function () {
    return {
      page: 1,
      endPage: '',
      camps: [],
      scrollIsStop: false
    };
  },
  created: function () {
    fetch("http://13.125.95.210:85/java/camp/" + this.page)
      .then(result => result.json())
      .then(result => {
        this.camps = this.campInfoProcess(result);
        console.log(this.camps);
      })
      .catch(err => console.log(err));
    fetch("http://13.125.95.210:85/java/camp/endPage")
      .then(result => result.text())
      .then(result => {
        this.endPage = +result;
      })
  },
  methods: {
    search(resultItem) {
      this.camps = this.campInfoProcess(resultItem);
    },
    campInfoProcess(result) {
      for (let i = 0; i < result.length; i++) {
        result[i].campInfo = result[i].campInfo.split(" ");
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
        for (let infoTemp of result[i].campInfo) {
          info[infoTemp] = true;
        }
        result[i].campInfo = info;
      }
      return result;
    },
    getCampDetail(campId, e) {
      e.preventDefault();
      let id = campId;
      this.$router.push({ name: 'CampDetail', params: { campId: id } });
    },
    onScroll(e) {
      let target = e.target;
      // scrollY:????????? ?????? + innerHeight:???????????? ?????? = ???????????? ???????????? ?????? ??????
      let currScrollY = target.innerHeight + target.scrollY;
      if ((currScrollY >= (document.body.offsetHeight - 100)) && this.scrollIsStop != true && this.page <= this.endPage) {
        // 200 ????????? ajax ?????? ??????.
        this.scrollIsStop = true;
        this.getPageItem();
        // ?????? ?????? Card ?????? ???  ?????????????????? ??????????????? ajax ??????????????? ?????????. ( ?????? ) 
        // ??? Append ??? ???????????? ????????? ????????? ????????? ??????????????????. ( ?????? )
      } else {
        this.scrollIsStop = false;
      }
    },
    getPageItem() {
      this.page++;
      fetch("http://13.125.95.210:85/java/camp/" + this.page)
        .then(result => result.json())
        .then(result => {
          for (let i = 0; i < result.length; i++) {
            result[i].campInfo = result[i].campInfo.split(" ");
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
            for (let infoTemp of result[i].campInfo) {
              info[infoTemp] = true;
            }
            result[i].campInfo = info;
          }
          this.camps.push(result);
          console.log(this.camps);
        })
        .catch(err => console.log(err));
    },
  },
  components: { CampListImage, CampSearch }
}
</script>
<style scoped src="@/assets/css/Camping/CampList.css">

</style>