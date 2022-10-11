<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="search" @keyup="checkEnter($event)" v-model="searchText" placeholder="검색어를 입력해주세요.">
      <button @click="doSearch" style="display: none;">조회</button>
      <!-- <input type="button" @click="doClear" value="X"> -->
    </div>
    
    <div class="sns-img-container">
      <div class="sns-img-row-container" :key="snsImg.No" v-for="snsImg in snsImgs">
      <div class="sns-img">
        <input type="text" :value="snsImg.No" style="display :none;">
        <img v-bind:src="snsImg.Img">
      </div> 
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

        searchText : '',

        snsImgs : [
          {Img : img1, Name : 'snsMaingImg1', No : '1111'},
          {Img : img2, Name : 'snsMaingImg2', No : '2222'},
          {Img : img3, Name : 'snsMaingImg3', No : '3333'},
          {Img : img4, Name : 'snsMaingImg4', No : '4444'},
          {Img : img5, Name : 'snsMaingImg5', No : '5555'},
          {Img : img6, Name : 'snsMaingImg6', No : '6666'},
          {Img : img7, Name : 'snsMaingImg7', No : '7777'},
          {Img : img8, Name : 'snsMaingImg8', No : '8888'},
          {Img : img9, Name : 'snsMaingImg9', No : '9999'},
        
      ]
        
      }
    },
    //검색
    methods : {
      doSearch(){
        console.log(this.searchText)
      },
      checkEnter(event){
        if(event.keyCode == 13) {
          this.doSearch()
        }
      },
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

  .sns-searchbox input[type=search] {
    padding: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

  /* .sns-searchbox input[type=button] {
    padding: 15px;
    border: none;
    border-radius: 5px;
    background-color: rgb(255, 255, 255);
  } */

  .sns-img-container {
    width: 70%;
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