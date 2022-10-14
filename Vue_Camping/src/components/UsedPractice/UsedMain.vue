<template>
  <!--상단(필터, 검색)-->
  <div id="container">
    <form id="container2">
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
              <input type="text" name="search" placeholder="어떤 물건을 찾으시나요?">
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
              <select v-model="myGearType" name="usedCategory" selected>
                <option value='' disabled>카테고리 선택</option>
                <option value="전체">전체</option>
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
                <select v-model="regionSelect" id="districtSelect" name='city' @change="districtChange">
                  <option value diabled>시/도</option> 
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
                  <option value disabled>시/군/구</option>
                  <option value='전체'>전체</option>
                </select>
            </li>
            <li>                          
              <label for="inputPrice">가격범위</label>
              <input v-model="minPrice" type="range" id="inputPrice" placeholder="0" name="minPrice" min="1000" max="10000000" step="100">
              <p>~</p>
              <input v-model="maxPrice" type="range" id="inputPrice" placeholder="0" name="maxPrice" min="1000" max="10000000" step="100">
              <!-- <div id="slider"></div> -->
            </li>
          </ul>
        </div>


      <!--본문-->
      <div class="used-body">
        <div class="used-title">
          <h2>중고거래</h2>
        </div>
        <!-- 라우터링크 -->
        <!-- <router-link tag="div" v-bind:to="{name:'usedDetail',params:{usedId:'recruInfo.id'}}">
          <UsedCard v-bind:usedCard="card"></UsedCard>
        </router-link> -->
        
        <!-- 원래거 -->
        <div class="cards">
          <div v-for="card in usedList" :key="card.id" @click="usedDetail(card.usedId)">
            <UsedCard v-bind:usedCard="card"></UsedCard>
          </div>
        </div>



      </div>

      <!--하단-->
      <div class="used-foote">
        <button v-on:click='usedInsert()'>+</button>
        <button v-on:click='usedInsert()'>♥</button>
      </div>
    </form>
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
        usedList: [],
        myGearType: '',
        regionSelect: '',
        regionSelect2: '',
        minPrice: '',
        maxPrice: '',
        cardImg : img1,
        searchImg : img2,
      }
    },
    methods : {
      cardDetail: function(){
        this.$router.push({name : 'usedDetail'})
      },
      usedInsert: function(){
        this.$router.push({name : 'usedInsert'})
      },
      usedDetail: function(usedId){
        //클릭하면은 디테일 페이지로 넘겨 넘길때, 번호를 넘겨야해
        this.$router.push({name : 'usedDetail', params: {usedId:usedId }})
      
      },
      searchBtn: function(){
        let fetchData = {};
        console.log();
            new FormData(document.querySelector('#container2')).forEach((value,key) => fetchData[key]=value);
            console.log(fetchData);
            document.querySelector('#container2').submit();
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
      districtChange: function(){
        //지역선택
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
    },
    //created-페이지 열자마자 실행
    created(){
      //전체조회
      fetch('http://localhost:8088/java/used/usedMain') 
                .then(Response => Response.json())  //json 파싱 
                .then(data => { 
                    console.log(data)
                    this.usedList = data;
                }).catch(err=>console.log(err))

    }
  }

</script>
<style scoped src="@/assets/css/used/UsedMain.css">
  
</style>