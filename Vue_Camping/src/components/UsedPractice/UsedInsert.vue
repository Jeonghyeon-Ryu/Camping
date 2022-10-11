<template>
  <div id="container">
    <form id="container2">
      <!-- <h3>상품 등록</h3> -->
        <div class="used-heads">
          <!-- 사진 -->
          <div class="used-pics">
            <h3>상품 사진</h3>
            <div class="used-pic">
              <div id='att_zone'>
                <label class="input-file-button" for="btnAtt">
                  <input type='file' name="pic" id='btnAtt' accept="image/*" multiple='multiple' />
                </label>
              </div>
            </div>
          </div>
          <!-- 상품 -->
          <div class="used-info">
            <ul>
              <li>
                <label for="inputWriter">작성자</label>
                <p>{{used_writer}}</p>
              </li>
              <li>
                <label for="inputName">상품명<span class="essential">*</span></label>
                <input type="text" name="used_name" id="inputName" placeholder="상품명을 등록해주세요(최대 20자)" maxlength="20" autofocus>
              </li>
              <li>
                <label for="inputPrice">가격<span class="essential">*</span></label>
                <input type="text" name="used_price" id="inputPrice" placeholder="가격을 숫자로 입력하세요" @keyup="inputNumberFormat(this)" >
              </li>
              <hr>
              <li>
                <label for="inputCate">카테고리</label>
                <select name="used_cate" id="used_cate" v-model="myGearType">
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
                  <label for="inputPlace">지역<span class="essential">*</span></label>
                  <form name="used_place">
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
                  </form> 
              </li>
              <li>
                  <label for="align">상태</label>
                  <div id="align">
                  <input type="radio" id="radio1" name="used_sangtae" value=1 checked>
                  <label for="radio1">상</label>
                  <input type="radio" id="radio2" name="used_sangtae" value=2>
                  <label for="radio2">중</label>
                  <input type="radio" id="radio3" name="used_sangtae" value=3>
                  <label for="radio3">하</label>
                  </div>
                </li>
              <hr>
              <li id="textbox">
                <label for="used_content">상품설명</label>
                <div class="used-desc">
                  <textarea name="used_content" class="used_content" placeholder="여러 장의 상품 사진과 구입 연도, 브랜드, 사용감, 하자 유무 등 구매자에게 필요한 정보를 꼭 포함해 주세요. (10자 이상)"></textarea>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <!-- 작성버튼 -->
        <div class="used-foot">
              <button @click.prevent="confirm()">작성 완료</button>
        </div>
      </form>
  </div>
</template>
<script>
  import district from "@/assets/district.js"

  export default {
    data(){
      return{
        myGearType: '',
        used_writer: 'campingGo',
        regionSelect: '',
        regionSelect2: '',
      }
    },
    methods: {
      // upload : function(){
      //   const picUpload = document.querySelector('.pic-upload');
      //   const upload = document.querySelector('.uploadarea');
      //   picUpload.click();
      // },
      confirm: function(){
        let fetchData = {};
        console.log();
            new FormData(document.querySelector('#container2')).forEach((value,key) => fetchData[key]=value);
            console.log(fetchData);
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
<style scoped src="@/assets/css/used/UsedInsert.css">

</style>
<!-- 
  사진: 미리보기
  상품명: 글자수 20자 제한
  가격: 숫자 천단위구분기호/숫자만입력 
  상품설명: 글자수제한 /글자카운트?-->
