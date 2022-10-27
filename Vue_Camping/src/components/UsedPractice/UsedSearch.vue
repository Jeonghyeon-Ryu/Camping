<template>
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
              <input v-model="minPrice" type="number" id="inputPrice" placeholder="0" name="minPrice" min="1000" max="10000000" step="1000">
              <p>~</p>
              <input v-model="maxPrice" type="number" id="inputPrice" placeholder="0" name="maxPrice" min="1000" max="10000000" step="1000">
            </li>
          </ul>
        </div>
</template>
<script>
   export default{
    components:{
    UsedCard,
    VueSimpleRangeSlider,
    },
    data(){
      return{
        keywordValue : '',
        keyword : '',
        usedList: [],
        myCategory: '',
        regionSelect: '',
        regionSelect2: '',
        minPrice: '',
        maxPrice: '',
        searchImg : img1,
        dealStatus: 9
      }
    },
    methods : {
      gearSelected: function(){
        this.myCategory = '';
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
        let cityArr = ["서울","부산","인천","대구","광주","대전","울산","경기","강원","충북","충남","경북","경남","전북","전남","제주특별자치도"];

        sigu.options.length=1;  //저장내역 삭제

        let cityIndex = cityArr.indexOf(sidoName);

        let cityList = district.data[cityIndex][sidoName];  //도시배열
        for(let i in cityList){
                    var opt = document.createElement("option");
                    opt.value = cityList[i];
                    opt.innerHTML = cityList[i];
                    sigu.appendChild(opt);
       }

      },
      searchList : function(event){
        //키워드 검색+필터검색 결과 받아오기
        let data = {
          keyword: this.keyword,
          usedCategory: this.myCategory,
          minPrice: this.minPrice,
          maxPrice: this.maxPrice,
          usedPlace: this.regionSelect,
          dealStatus: this.dealStatus,
        }
        if(this.regionSelect2){
          data.usedPlace += ' '+this.regionSelect2;
        }
        const keyword = this.keyword;
        this.keywordValue = keyword;
        console.log(data)
        fetch("http://localhost:8087/java/used/usedSearch",{
          method : "POST",
          headers : {"Content-Type" : "application/json"},
          body : JSON.stringify(data)
        })
          .then((response) =>response.json()) 
          .then(data => { 
            console.log(data);
            this.usedList = data;  
            if(this.usedList.length<1){
              this.recruMsg="검색 결과가 없습니다"
            }else{
               this.recruMsg="";
            }
          }).catch(err=>console.log(err));
      },
      selectMinUsedPrice() {
        let priceList = [];

        for(let i=0; i<this.usedList.length; i++) {
          priceList.push(this.usedList[i].usedPrice);
        }

        let min = Math.min.apply(null,priceList);
        let max = Math.max.apply(null,priceList);

        this.minPrice = min;
        this.maxPrice = max;

        
        console.log('최고가'+this.maxPrice)
        console.log('최저가'+this.minPrice)
      },
    },
    //created-페이지 열자마자 실행
    created(){
      //전체조회
      fetch('http://localhost:8087/java/used/usedMain') 
                .then(Response => Response.json())  //json 파싱 
                .then(data => { 
                    console.log(data);
                    this.usedList = data;
                    // this.selectMinUsedPrice();
                }).catch(err=>console.log(err))

    },
    setup() {
    // const state = reactive({ range: [20, 1000], number: 10 });
    // return { state };
  },
  }

</script>