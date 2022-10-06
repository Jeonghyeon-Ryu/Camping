<template>
  <div id="container">
    <div id="container2">
      <h2>게시물 등록하기</h2>
        <div class="used-heads">
          <div class="used-pics">
            <h3>상품 사진</h3>
            <div class="used-pic">
            </div>
            <input type="file" class="pic-upload" accept="image/*" required multiple>
            <div class="uploadarea" @click="upload">
              사진 추가하기 
            </div>
          </div>
          <div class="used-info">
            <ul>
              <li>
                <label for="inputWriter">작성자</label>
              </li>
              <li>
                <label for="inputName">상품명</label>
                <input type="text" id="inputName" placeholder="상품명을 등록해주세요">
              </li>
              <li>
                <label for="inputCate">카테고리</label>
                <select v-model="myGearType">
                  <option selected disabled>장비 분류</option>
                  <option value="tent">텐트</option>
                  <option value="tarp">타프</option>
                  <option value="furniture">가구</option>
                  <option value="bedding">침구</option>
                  <option value="kitchenTool">조리도구</option>
                  <option value="lamp">조명</option>
                  <option value="storage">수납</option>
                  <option value="tool">공구</option>
                  <option value="airCon">냉난방</option>
                  <option value="etc">기타</option>
              </select>
              </li>
              <li>
                <label for="inputPrice">가격</label>
                <input type="text" id="inputPrice" placeholder="가격을 입력하세요">
              </li>
              <li>
                <label for="inputPlace">지역</label>
                <input type="text" id="inputPlace" placeholder="주소를 등록해주세요">
              </li>
            </ul>
          </div>
        </div>
        <div class="used-foot">
          <h3>상품 설명</h3>
          <div class="used-desc">
            <textarea class="used-text" placeholder="상품에 대한 설명을 입력해주세요"></textarea>
          </div>
          <div class="submit-area">
              <button class="btn bg-gradient-success btn-md false false w-auto me-2" type="submit">작성 완료</button>
          </div>
        </div>
      </div>
  </div>
</template>
<script>
  export default {
    methods: {
      upload : function(){
        const picUpload = document.querySelector('.pic-upload');
        const upload = document.querySelector('.uploadarea');
        picUpload.click();
      }
    }
  }
</script>
<style>
      *{
      margin:0;
      padding:0;
      box-sizing: border-box;
    }
    #container{
      width: 100%;
      padding: 2%;
    }
    #container2{
      width: 100%;
    }
    h2, h3, h4{
      padding: 1%;
    }
    ul{
      list-style: none;
    }
    .used-heads{
      width: 100%;
      height: 700px;
      padding: 2%;
      /*background-color: #e6e6e6;*/
      display: flex;
    }
    .pic-upload{
      display:none;
    }
    .used-pics{
      width: 65%;

      flex-wrap: nowrap;
    }
    .used-pic{
      width: 100%;
      height: 70%;
      padding: 2%;
      background-color: #ebcbcb;
    }
    .used-info{
      width: 35%;
      padding: 2%;
      flex-wrap: nowrap;
    }
    .used-foot{
      padding: 10px;
    }
    .used-desc{
      text-align:center;
    }
    .used-text{
      width: 90%;
      height: 170px;
      padding: 2%;
      border: none;
      background-color: #e6e6e6;
      resize: none;
    }
    .submit-area{
      padding: 5%;
      text-align: center;
    }

</style>