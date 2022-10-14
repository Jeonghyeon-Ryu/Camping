<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="search" @keyup="checkEnter($event)" v-model="searchText" placeholder="검색어를 입력해주세요.">
      <button @click="doSearch" style="display: none;">조회</button>
      <!-- <input type="button" @click="doClear" value="X"> -->
    </div>
    
    <div class="sns-img-container">
      <div class="sns-img-row-container" :key="snsImg.no" v-for="snsImg in snsImgs" @click="getSnsDetail(snsImg.no)">
      <div class="sns-img">
        <input type="text" :value="snsImg.no" style="display :none;">
        <img src="">
        <!-- <img v-bind:src="snsImg.Img"> -->
      </div> 
    </div>     
    </div>
  </div>
</template>

<script>
  import img1 from "@/assets/img/sns/이미지1.jpg"
  import img2 from "@/assets/img/sns/이미지2.jpg"
  import img3 from "@/assets/img/sns/이미지3.jpg"
  import img4 from "@/assets/img/sns/이미지4.jpg"
  import img5 from "@/assets/img/sns/이미지5.jpg"
  import img6 from "@/assets/img/sns/이미지6.jpg"
  import img7 from "@/assets/img/sns/이미지7.jpg"
  import img8 from "@/assets/img/sns/이미지8.jpg"
  import img9 from "@/assets/img/sns/이미지9.jpg"

  export default{
    data : ()=>{
      return {     

        searchText : '',

        snsImgs : [
          {img : img1, name : 'snsMaingImg1', no : 1},
          {img : img2, name : 'snsMaingImg2', no : 11},
          {img : img3, name : 'snsMaingImg3', no : 12},
          {img : img4, name : 'snsMaingImg4', no : 13},
          {img : img5, name : 'snsMaingImg5', no : 14},
          {img : img6, name : 'snsMaingImg6', no : 9},
          {img : img7, name : 'snsMaingImg7', no : 1},
          {img : img8, name : 'snsMaingImg8', no : 1},
          {img : img9, name : 'snsMaingImg9', no : 1},
        
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
      getSnsDetail(writeNo) {
        this.$router.push({name:'SnsDetail', params:{writeNo:writeNo}});
      }
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
  .sns-img-row-container {
    width:30%;
    height: 300px;  
    cursor: pointer;
    border:1px solid black;
  }
  .sns-img {
    margin-bottom: 10px;
    margin-right: 10px;
    cursor: pointer;
  }
</style>