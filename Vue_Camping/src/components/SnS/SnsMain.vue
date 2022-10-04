<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="text" placeholder="검색어를 입력하세요">
      <input type="button" value="X">
      <p class="result"></p>
    </div>
    <div class="sns-img-container">
      <div class="sns-img">
        <input type="text" v-bind:value="snsWriteNumber1"  style="display :none;">
        <img v-bind:src="snsMaingImg1">
      </div>
      <div class="sns-img">
        <input type="text" v-bind:value="snsWriteNumber2"  style="display :none;">
        <img v-bind:src="snsMaingImg2">
      </div>
      <div class="sns-img">
        <input type="text" v-bind:value="snsWriteNumber3"  style="display :none;">
        <img v-bind:src="snsMaingImg3">
      </div>
      <div class="sns-img">
        <input type="text" v-bind:value="snsWriteNumber4"  style="display :none;">
        <img v-bind:src="snsMaingImg4">
      </div>
      <div class="sns-img">
        <input type="text" v-bind:value="snsWriteNumber5"  style="display :none;">
        <img v-bind:src="snsMaingImg5">
      </div>
      <div class="sns-img">
        <input type="text" v-bind:value="snsWriteNumber6"  style="display :none;">
        <img v-bind:src="snsMaingImg6">
      </div>
      <div class="sns-img">
        <input type="text" v-bind:value="snsWriteNumber7"  style="display :none;">
        <img v-bind:src="snsMaingImg7">
      </div>
      <div class="sns-img">
        <input type="text" v-bind:value="snsWriteNumber8"  style="display :none;">
        <img v-bind:src="snsMaingImg8">
      </div>
      <div class="sns-img">
        <input type="text" v-bind:value="snsWriteNumber9"  style="display :none;">
        <img v-bind:src="snsMaingImg9">
      </div>
    </div>
  </div>
</template>

<script>
  import img1 from "@/assets/img/이미지1.jpg"
  import img2 from "@/assets/img/이미지2.jpg"
  import img3 from "@/assets/img/이미지3.jpg"
  import img4 from "@/assets/img/이미지4.jpg"
  import img5 from "@/assets/img/이미지5.jpg"
  import img6 from "@/assets/img/이미지6.jpg"
  import img7 from "@/assets/img/이미지7.jpg"
  import img8 from "@/assets/img/이미지8.jpg"
  import img9 from "@/assets/img/이미지9.jpg"

  export default{
    data : ()=>{
      return {     
        snsWriteNumber1 : '1111',   
        snsWriteNumber2 : '2222',
        snsWriteNumber3 : '3333',
        snsWriteNumber4 : '4444',
        snsWriteNumber5 : '5555',
        snsWriteNumber6 : '6666',  
        snsWriteNumber7 : '7777',
        snsWriteNumber8 : '8888',
        snsWriteNumber9 : '9999',  
        snsMaingImg1 : img1,
        snsMaingImg2 : img2,
        snsMaingImg3 : img3,
        snsMaingImg4 : img4,
        snsMaingImg5 : img5,
        snsMaingImg6 : img6,
        snsMaingImg7 : img7,
        snsMaingImg8 : img8,
        snsMaingImg9 : img9

        
      }
    },
    methods : {
     
    }
  }
 </script>



<style>
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