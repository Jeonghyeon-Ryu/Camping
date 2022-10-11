<template>
  <!--상단(사진,정보)-->
  <div id="container">
    <h2>상세보기</h2>
    <div id="container2">
      <div class="used-head">
        <form class="used-pics">
          <fieldset>
            <legend><h2>상품사진</h2></legend>
            <div class="used-pic">
              <div id='att_zone'>
                <label class="input-file-button" for="btnAtt">
                  <img src="image/addPic.jpg" width="100%">
                  <input type='file' id='btnAtt' accept="image/*" multiple='multiple' />
                </label>
              </div>
            </div>
          </fieldset>
        </form>
        <form class="used-info">
          <fieldset>
            <legend><h2>상품정보</h2></legend>
            <ul>
              <li>
                <label for="inputWriter">작성자</label>
                campingGo
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
          </fieldset>
        </form>
      </div>
      <!--하단(상품설명, 전송버튼)-->
      <div class="used-foot">
        <form class="used-desc">
          <fieldset>
            <legend><h2>상품설명</h2></legend>
            <textarea class="used-text" placeholder="상품에 대한 설명을 입력해주세요"></textarea>
          </fieldset>
          </form>
        <div class="submit-area">
          <button class="submittest" type="submit">작성 완료</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

</script>
<style scoped>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }
    #container {
      margin: 0 auto;
      margin-top: 2%;
      width: 80%;
      padding: 2%;
    }

    #container2 {
      width: 100%;
      flex-direction: column;
    } 
    h2{
      text-align: center;
      color:rgb(22, 148, 32);
    }
    h2,
    h3,
    h4 {
      padding: 1%;
    }
    /* fieldset{
      border: none;
    } */
    legend{
      width: 200px;
      text-align: left;
    }
    .used-heads {
      width: 100%;
      height: 450px;
      padding: 10px;
      /*background-color: #e6e6e6;*/
      display: flex;
    }
    .pic-upload {
      display: none;
    }
    .used-pics {
      width: 65%;
      flex-wrap: nowrap;
    }
    .used-pic {
      width: 100%;
      height: 100%;
      padding: 2%;
      /* background-color: #ebcbcb; */
    }
    ul {
      list-style: none;
      font-size: 1.2em;
      font-weight:bold;
    }
    ul input, ul select{
      width: 300px;
      height: 40px;
      padding: 10px;
    }
    li{
      display: flex;
      justify-content: space-between;
    }
    .used-info {
      width: 35%;
      height: 100%;
      flex-wrap: nowrap;
      padding-left: 10px;
    }
    label{
      width: 100px;
      display: inline-block;
      text-align: center;
    }
    .used-foot {
      padding: 10px;
      flex-wrap: wrap;
    }
    .used-desc {
      text-align: center;
    }
    .used-text {
      width: 90%;
      height: 170px;
      padding: 2%;
      border: none;
      background-color: #e6e6e6;
      resize: none;
    }
    .submit-area {
      padding: 5%;
      text-align: center;
    }
    #att_zone {
      margin: 0 auto;
      width: 95%;
      min-width: 50%;
      height: 350px;
      max-height: 350px;
      padding: 10px;
      border: 1px solid #e6e6e6;
      overflow:auto;
      overflow-x:hidden;
    }
    #att_zone:empty:before {
      content: attr(data-placeholder);
      color: #999;
      font-size: .9em;
    }
    #btnAtt{
      display:none;
    }
    .input-file-button{
      width: 150px;
      height: 150px;
      padding: 2%;
      color: white;
      cursor: pointer;
    }
</style>