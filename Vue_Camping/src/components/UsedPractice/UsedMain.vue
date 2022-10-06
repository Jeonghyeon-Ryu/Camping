<template>
  <!--상단(필터, 검색)-->
  <div id="container">
    <div id="container2">
      <div class="used-headd">
        <h2>중고거래</h2>
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
                <select v-model="citySelect" name='city' onchange="change(this.selectedIndex);"  class=input >
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
                  <option value='제주'>제주도</option>
                </select>
                <select name='county'  class=select>
                  <option value=''>전체</option>
                </select>
              </form> 
            </li>
            <li>
              <label for="inputPrice">가격범위</label>
              <input type="number" id="inputPrice" placeholder="0">
              <p>~</p>
              <input type="number" id="inputPrice" placeholder="0">
            </li>
          </ul>
        </div>

        <!--검색-->
        <div class="used-search">
          <div class="used-searchbox">
            <div>
              <input type="text" placeholder="어떤 물건을 찾으시나요?">
              <img v-on:click='searchBtn()' v-bind:src="searchImg">
           </div>
          </div>
          <div id="used-selected">
            <span v-if="myGearType.value != null">{{myGearType}}</span><span disabled="true">{{myGearType}}</span>
            <span>{{citySelect}}</span><span>달서구</span><span>0~100,000</span>
          </div>
        </div>
      </div>
      {{myCate}}

      <!--본문-->
      <div class="used-body">
        <div class="cards">
          <div v-for="card in usedCards" :key="card.id">
            <UsedCard v-bind:usedCard="card"></UsedCard>
          </div>
        </div>
      </div>

      <!--하단-->
      <div class="used-foote">
        <button v-on:click='usedInsert()'>물건팔기</button>
      </div>
    </div>
  </div>
</template>
<script>
  import img1 from "@/assets/img/bg9.jpg"
  import img2 from "@/assets/img/search.png"
  import UsedCard from "@/components/UsedPractice/UsedCard.vue"

  export default{
    components:{
    UsedCard
    },
    data(){
      return{
        myGearType: '',
        citySelect: '',
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
      usedSelected: function(){
        
      }

      }
    }
    

</script>
<style scoped src="@/assets/css/UsedMain.css">
  
</style>