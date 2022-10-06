<template>
  <!--상단(필터, 검색)-->
  <div id="container">
    <div id="container2">
      <div class="used-headd">
        <!--검색-->
        <div class="used-search">
          <div class="used-selected">
            <ul>
            <li v-if="myGearType != ''" @click="gearSelected">{{myGearType}} X</li>
            <li v-if="regionSelect != ''" @click="regionSelected">{{regionSelect}} X</li>
            <li v-if="regionSelect2 != ''" @click="region2Selected">{{regionSelect2}} X</li>
            <li v-if="minPrice != ''" @click="priceSelected">{{minPrice}} ~ {{maxPrice}} X</li>
            </ul>
          </div>
          <div class="used-searchbox">
            <div>
              <input type="text" placeholder="어떤 물건을 찾으시나요?">
              <img v-on:click='searchBtn()' v-bind:src="searchImg">
           </div>
          </div>

        </div>
      </div>
        <!-- 필터 -->
        <div class="used-filter">
          <ul class="used-filter-ul">
            <li>
              <label for="inputCate">카테고리</label>
              <select v-model="myGearType" selected>
                <option value='' disabled>카테고리를 선택하세요</option>
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
              <label for="inputPlace">지역</label>
              <form name=form>
                <select v-model="regionSelect" id="districtSelect" name='city' @change="districtChange">
                  <option value='전체'>전체</option>
                  <option value='서울특별시'>서울특별시</option>
                  <option value='부산광역시'>부산광역시</option>
                  <option value='대구광역시'>대구광역시</option>
                  <option value='인천광역시'>인천광역시</option>
                  <option value='광주광역시'>광주광역시</option>
                  <option value='대전광역시'>대전광역시</option>
                  <option value='울산광역시'>울산광역시</option>
                  <option value='경기도'>경기도</option>
                  <option value='강원도'>강원도</option>
                  <option value='충청북도'>충청북도</option>
                  <option value='충청남도'>충청남도</option>
                  <option value='전라북도'>전라북도</option>
                  <option value='전라남도'>전라남도</option>
                  <option value='경상북도'>경상북도</option>
                  <option value='경상남도'>경상남도</option>
                  <option value='제주도'>제주도</option>
                </select>
                <select v-model="regionSelect2" name='county' id="citySelect">
                  <option value='전체'>전체</option>
                </select>
              </form> 
            </li>
            <li>
              <label for="inputPrice">가격범위</label>
              <input v-model="minPrice" type="number" id="inputPrice" placeholder="0">
              <p>~</p>
              <input v-model="maxPrice" type="number" id="inputPrice" placeholder="0">
              <!-- <div id="slider"></div> -->
            </li>
          </ul>
        </div>


      <!--본문-->
      <div class="used-body">
        <div class="used-title">
          <h2>중고거래</h2>
        </div>
        <div class="cards">
          <div v-for="card in usedCards" :key="card.id">
            <UsedCard v-bind:usedCard="card"></UsedCard>
          </div>
        </div>
      </div>

      <!--하단-->
      <div class="used-foote">
        <button v-on:click='usedInsert()'>+</button>
      </div>
    </div>
  </div>
</template>
<script>
  import img1 from "@/assets/img/bg9.jpg"
  import img2 from "@/assets/img/search.png"
  import UsedCard from "@/components/UsedPractice/UsedCard.vue"
  import district from "@/assets/district.js"

  export default{
    components:{
    UsedCard,
    },
    data(){
      return{
        myGearType: '',
        regionSelect: '',
        regionSelect2: '',
        minPrice: '',
        maxPrice: '',
        cardImg : img1,
        searchImg : img2,
        usedCards : [{
          used_img : '',
          used_name : '4인용 텐트',
          used_place : '대구광역시 서구',
          used_price : '￦100,000',
          used_status : '거래중'
        },
        {
          used_img : '',
          used_name : '4인용 텐트',
          used_place : '대구광역시 서구',
          used_price : '￦100,000',
          used_status : '거래중'
        },
        {
          used_img : '',
          used_name : '4인용 텐트',
          used_place : '대구광역시 서구',
          used_price : '￦100,000',
          used_status : '거래중'
        },
        {
          used_img : '',
          used_name : '4인용 텐트',
          used_place : '대구광역시 서구',
          used_price : '￦100,000',
          used_status : '거래중'
        },
        {
          used_img : '',
          used_name : '4인용 텐트',
          used_place : '대구광역시 서구',
          used_price : '￦100,000',
          used_status : '거래중'
        },
        {
          used_img : '',
          used_name : '4인용 텐트',
          used_place : '대구광역시 서구',
          used_price : '￦100,000',
          used_status : '거래중'
        },
        {
          used_img : '',
          used_name : '4인용 텐트',
          used_place : '대구광역시 서구',
          used_price : '￦100,000',
          used_status : '거래중'
        }
      ],
        
      }
    },
    methods : {
      cardDetail: function(){
        this.$router.push({name : 'usedDetail'})
      },
      usedInsert: function(){
        this.$router.push({name : 'usedInsert'})
      },
      searchBtn: function(){
        SubmitEvent()
      },
      gearSelected: function(){
        this.myGearType = '';
      },
      regionSelected: function(){
        this.regionSelect = '';
      },
      region2Selected: function(){
        this.regionSelect2 = '';
      },
      priceSelected: function(){
        this.minPrice = '';
        this.maxPrice = '';
      },

      //지역선택
      districtChange: function(){
        let sido = document.querySelector('#districtSelect');
        let sigu = document.querySelector('#citySelect');
        let sidoName = sido.value;
        let cityArr = ["서울특별시","부산광역시","인천광역시","대구광역시","광주광역시","대전광역시","울산광역시","경기도","강원도","충청북도","충청남도","경상북도","경상남도","전라북도","전라남도","제주도"];

        sigu.options.length=1;  //저장내역 삭제

        let cityIndex = cityArr.indexOf(sidoName);

        let cityList = district.data[cityIndex][sidoName];  //도시배열
        for(let i in cityList){
                    var opt = document.createElement("option");
                    opt.value = cityList[i];
                    opt.innerHTML = cityList[i];
                    sigu.appendChild(opt);
       }

      }
    }
  }

</script>
<style scoped src="@/assets/css/UsedMain.css">
  
</style>