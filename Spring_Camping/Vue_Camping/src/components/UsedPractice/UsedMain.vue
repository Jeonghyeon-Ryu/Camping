<template>
  <!--상단(필터, 검색)-->
  <div id="container">
    <div id="container2">
      <form onsubmit="return false">
        <div class="used-headd">
          <div class="used-title">
            <div class="used-nav"></div>
            <div class="used-main" v-if="usedAd">
              <!-- <swiper :autoplay="true" 
            :modules="modules" class="mySwiper">
              <swiper-slide><img src="@/assets/img/bg10.png" alt="텐트 중고거래 광고" /></swiper-slide>
              <swiper-slide><img src="@/assets/img/bg11.png" alt="의자 중고거래 광고" /></swiper-slide>
            </swiper> -->
              <img src="@/assets/img/bg10.png" alt="텐트 중고거래 광고">
            </div>
          </div>
          <!--검색-->
          <div class="used-search">
            <div class="used-selected">
              <ul>
                <li v-if="myCategory != ''" @click="gearSelected">{{ myCategory }} ✖</li>
                <li v-if="dealStatus != '' && dealStatus != 9" @click="dealSelected">{{ dealStatus }} X</li>
                <li v-if="dealStatus != '' && dealStatus === 0" @click="dealSelected">거래가능 ✖</li>
                <li v-if="dealStatus != '' && dealStatus === 1" @click="dealSelected">거래중 ✖</li>
                <li v-if="dealStatus != '' && dealStatus === 2" @click="dealSelected">거래완료 ✖</li>

                <li v-if="regionSelect != ''" @click="regionSelected">{{ regionSelect }} ✖</li>
                <li v-if="regionSelect2 != ''" @click="region2Selected">{{ regionSelect2 }} ✖</li>
                <li v-if="minPrice != ''" @click="priceSelected">{{ minPrice }} ~ {{ maxPrice }} ✖</li>
              </ul>
            </div>

            <div class="used-searchbox">
              <div>
                <input type="text" name="search" placeholder="어떤 물건을 찾으시나요?" v-model="keyword"
                  @keyup.enter="searchList()">
                <img v-bind:src="searchImg" @click='searchList'>
              </div>
            </div>
          </div>
        </div>
        <!-- 필터 -->
        <div class="used-filter">
          <ul class="used-filter-ul">
            <li>
              <label for="inputCate">카테고리</label>
              <select v-model="myCategory" name="usedCategory" selected>
                <option value='' disabled>카테고리 선택</option>
                <option value="텐트">텐트</option>
                <option value="타프">타프</option>
                <option value="가구">가구</option>
                <option value="침구">침구</option>
                <option value="조리도구">조리도구</option>
                <option value="조명">조명</option>
                <option value="수납">수납</option>
                <option value="공구">공구</option>
                <option value="냉난방">냉난방</option>
                <option value="기타">기타</option>
              </select>
            </li>
            <li>
              <label for="inputDeal">거래상태</label>
              <select v-model="dealStatus" name="dealStatus" selected>
                <option value='9' disabled>거래상태 선택</option>
                <option value=0>거래가능</option>
                <option value=1>거래중</option>
                <option value=2>거래완료</option>
              </select>
            </li>
          </ul>
          <ul class="used-filter-ur">
            <li>
              <label for="inputPlace">지역</label>
              <select v-model="regionSelect" id="districtSelect" name='city' @change="districtChange">
                <option value='' disabled>시/도</option>
                <option value=''>전체</option>
                <option value='서울'>서울특별시</option>
                <option value='부산'>부산광역시</option>
                <option value='대구'>대구광역시</option>
                <option value='인천'>인천광역시</option>
                <option value='광주'>광주광역시</option>
                <option value='대전'>대전광역시</option>
                <option value='울산'>울산광역시</option>
                <option value='경기'>경기도</option>
                <option value='강원'>강원도</option>
                <option value='충북'>충청북도</option>
                <option value='충남'>충청남도</option>
                <option value='전북'>전라북도</option>
                <option value='전남'>전라남도</option>
                <option value='경북'>경상북도</option>
                <option value='경남'>경상남도</option>
                <option value='제주특별자치도'>제주도</option>
              </select>
              <select v-model="regionSelect2" name='county' id="citySelect">
                <option value disabled>시/군/구</option>
                <option value='전체'>전체</option>
              </select>
            </li>
            <li>
              <label for="inputPrice">가격범위</label>
              <!-- <Slider></Slider> -->
              <!-- <VueSimpleRangeSlider
                style="width : 200px;
                      font-size : 12px;"

                :min="this.minPrice"
                :max="100"
                
                exponential
                v-model="state.range">
                <template #prefix="{ value }">￦ </template>
              </VueSimpleRangeSlider> -->
              <input v-model="minPrice" type="number" id="inputPrice" placeholder="0" name="minPrice" min="1000"
                max="10000000" step="1000">
              <p>~</p>
              <input v-model="maxPrice" type="number" id="inputPrice" placeholder="0" name="maxPrice" min="1000"
                max="10000000" step="1000">
            </li>
          </ul>
        </div>


        <!--본문-->
        <div class="used-body">
          <!-- :min="this.minPrice"
        :max="this.maxPrice" -->

          <div v-if="keywordValue" class="검색결과">
            <h4>'{{ keywordValue }}' 에 대한 검색 결과</h4>
            <!-- <div class="searched-filter">
            최신순 | 낮은가격순 | 높은가격순
          </div> -->
          </div>
          <div v-if="usedMsg" class="usedMsg">
            <img src="@/assets/img/used/nosearch.png" alt="검색결과없음" style="width:200px">
            <h2>{{ usedMsg }}</h2>
            <h4>다른 키워드로 검색해주세요</h4>
          </div>
          <div class="cards">
            <div v-for="card in usedList" :key="card.id">
              <router-link tag="div" v-bind:to="{ name: 'usedDetail', params: { usedId: card.usedId } }">
                <UsedCard v-bind:usedCard="card"></UsedCard>
              </router-link>
            </div>
          </div>


        </div>
      </form>
      <!--하단-->
      <div class="used-foote">
        <!-- <router-link tag="div" v-bind:to="{name:'usedInsert'}">
        <button>+</button> 
      </router-link> -->
        <router-link tag="div" v-if="this.$store.state.email != null" v-bind:to="{ name: 'myUsedSave' }">
          <button>🤍</button>
        </router-link>
        <!-- <router-link tag="div" v-bind:to="{name:'usedReview'}">
        <button>review</button> 
      </router-link> -->
        <button v-on:click='usedInsert'>+</button>
        <!--<button v-on:click='usedInsert'>♥</button> -->
      </div>
    </div>
  </div>
  <div id="bottomSensor"></div>
</template>
<script>
import img1 from "@/assets/img/used/search.png"
import UsedCard from "@/components/UsedPractice/UsedCard.vue"
import district from "@/assets/district.js"
import Swal from 'sweetalert2';

import { Swiper, SwiperSlide } from "swiper/vue";
import "swiper/css";
import { Navigation, Pagination } from "swiper";

export default {
  components: {
    UsedCard,
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      keywordValue: '',
      keyword: '',
      usedList: [],
      myCategory: '',
      regionSelect: '',
      regionSelect2: '',
      minPrice: '',
      maxPrice: '',
      searchImg: img1,
      dealStatus: 9,
      pageNum: 1,
      usedMsg: false,
      usedAd: true,
    }
  },
  methods: {
    cardDetail: function () {
      this.$router.push({ name: 'usedDetail' })
    },
    usedInsert: function () {
      if (this.$store.state.email != null) {
        this.$router.push({ name: 'usedInsert' })
      } else {
        Swal.fire({
          icon: 'warning',
          title: '로그인 후에 작성할 수 있어요',
          toast: true,
          showConfirmButton: false,
          timer: 1300,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })
      }
    },
    usedDetail: function (usedId) {
      //클릭하면은 디테일 페이지로 넘겨 넘길때, 번호를 넘겨야해
      this.$router.push({ name: 'usedDetail', params: { usedId: usedId } })

    },
    gearSelected: function () {
      this.myCategory = '';
    },
    dealSelected: function () {
      this.dealStatus = '';
    },
    regionSelected: function () {
      this.regionSelect = '';
    },
    region2Selected: function () {
      this.regionSelect2 = '';
    },
    priceSelected: function () {
      this.minPrice = '';
      this.maxPrice = '';
    },
    districtChange: function () {
      //지역선택
      let sido = document.querySelector('#districtSelect');
      let sigu = document.querySelector('#citySelect');
      let sidoName = sido.value;
      let cityArr = ["서울", "부산", "인천", "대구", "광주", "대전", "울산", "경기", "강원", "충북", "충남", "경북", "경남", "전북", "전남", "제주특별자치도"];

      sigu.options.length = 1;  //저장내역 삭제

      let cityIndex = cityArr.indexOf(sidoName);

      let cityList = district.data[cityIndex][sidoName];  //도시배열
      for (let i in cityList) {
        var opt = document.createElement("option");
        opt.value = cityList[i];
        opt.innerHTML = cityList[i];
        sigu.appendChild(opt);
      }
    },
    searchList: function () {
      //키워드 검색+필터검색 결과 받아오기
      this.usedAd = false;
      let data = {
        keyword: this.keyword,
        usedCategory: this.myCategory,
        minPrice: this.minPrice,
        maxPrice: this.maxPrice,
        usedPlace: this.regionSelect,
        dealStatus: this.dealStatus,
      }
      if (this.regionSelect2) {
        data.usedPlace += ' ' + this.regionSelect2;
      }
      const keyword = this.keyword;
      this.keywordValue = keyword;
      console.log(data)
      fetch("http://13.125.95.210:85/java/used/usedSearch", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
      })
        .then((response) => response.json())
        .then(data => {
          console.log(data);
          this.usedList = data;
          if (this.usedList.length < 1) {
            this.usedMsg = "검색 결과가 없어요"
          } else {
            this.usedMsg = "";
          }
        }).catch(err => console.log(err));
    },
    selectMinUsedPrice() {
      let priceList = [];

      for (let i = 0; i < this.usedList.length; i++) {
        priceList.push(this.usedList[i].usedPrice);
      }

      let min = Math.min.apply(null, priceList);
      let max = Math.max.apply(null, priceList);

      this.minPrice = min;
      this.maxPrice = max;


      console.log('최고가' + this.maxPrice)
      console.log('최저가' + this.minPrice)
    },
    addScrollWatcher: function () {
      const bottomSensor = document.querySelector("#bottomSensor")
      const watcher = scrollMonitor.create(bottomSensor)
      watcher.enterViewport(() => {
        // 서버 과부하를 막기 위한 딜레이
        setTimeout(() => {
          this.pageNum = this.pageNum + 1;
          this.loadDataPage();
        }, 300)
      })
    },
    loadDataPage: function () {
      fetch('http://13.125.95.210:85/java/used/usedMain/' + this.pageNum)
        .then(Response => Response.json())  //json 파싱 
        .then(data => {
          for (let key in data) {
            this.usedList.push(data[key]);
          }
          console.log(data);
          // this.usedList = data;
          // this.selectMinUsedPrice();
        }).catch(err => console.log(err))
    },
    mainSearch: function () {
      let data = {
        keyword: this.$route.query.search,
      }
      
      fetch("http://13.125.95.210:85/java/used/usedSearch", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
      })
        .then((response) => response.json())
        .then(data => {
          console.log(data);
          this.usedList = data;
          if (this.usedList.length < 1) {
            this.usedMsg = "검색 결과가 없어요"
          } else {
            this.usedMsg = "";
          }
        }).catch(err => console.log(err));
    }
  },
  mounted() {
    this.addScrollWatcher()
  },
  //created-페이지 열자마자 실행
  created() {
    console.log(this.$route.query.search);
    let searchdata = this.$route.query.search;
    
    if (searchdata != undefined) {
      //메인에서 서치하면 접근하는 경로
      this.mainSearch();
    }else{
      //전체조회
      this.loadDataPage();
    }
  },
  setup() {
    return {
    };
  },
}

</script>




<style scoped src="@/assets/css/used/UsedMain.css" />