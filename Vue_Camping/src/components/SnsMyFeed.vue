<template>
    <div class="sns-container">
    <div class="sns-searchbox">
      <input type="text" placeholder="검색어를 입력하세요">
      <input type="button" value="X">
      <p class="result"></p>
    </div>
    <div class="sns-img-container">
      <div class="sns-img">
        <div class="sns-write-number" style="display :none;">1111</div>
        <img src="이미지1.jfif">
      </div>
      <div class="sns-img">
        <div class="sns-write-number" style="display :none;">222</div>
        <img src="이미지2.jfif">
      </div>
      <div class="sns-img">
        <div class="sns-write-number" style="display :none;">333</div>
        <img src="이미지3.jfif">
      </div>
      <div class="sns-img">
        <div class="sns-write-number" style="display :none;">4444</div>
        <img src="이미지4.jfif">
      </div>
      <div class="sns-img">
        <div class="sns-write-number" style="display :none;">5555</div>
        <img src="이미지5.jfif">
      </div>
      <div class="sns-img">
        <div class="sns-write-number" style="display :none;">6666</div>
        <img src="이미지6.jfif">
      </div>
    </div>
  </div>
</template>


<script>
    export default{
   data : function(){
 
   }  
  }
</script>


<style scoped>
    /* .sns-container{
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
    }
    .sns-image{
      
      width: 30%;
    } */
    /* 공통 부분 */
    * {
      margin: 0;
      padding: 0;
      list-style: none;
      font-style: none;
      box-sizing: border-box;
    }

    .sns-container {
      width: 100vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
    }

    .sns-searchbox {
      width: 100vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      margin-bottom: 10px;
      margin-top: 10px;
    }

    .sns-searchbox input[type=text] {
      padding: 15px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .sns-searchbox input[type=button] {
      padding: 15px;
      border: none;
      border-radius: 5px;
      background-color: rgb(255, 255, 255);
    }

    .sns-img-container {
      width: 70vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
    }

    .sns-img {
      margin-bottom: 10px;
      margin-right: 10px;
      cursor: pointer;
    }
</style>