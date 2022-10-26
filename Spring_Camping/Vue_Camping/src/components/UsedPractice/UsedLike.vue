<template>
    <!--상단(필터, 검색)-->
    <div id="container">
      <div id="container2">
        <!--본문-->
        <div class="used-body">
          <div class="used-title">
            <h2>중고거래 찜/내가 쓴 글</h2>
          </div>
          <div class="cards">
            <div v-for="card in usedCards" :key="card.id">
              <UsedCard v-bind:usedCard="card"></UsedCard>
            </div>
          </div>
        </div>
  
        <!--하단-->
        <div class="used-foote">
          <button v-on:click='usedInsert()'>+</button>
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
      UsedCard,
      },
      data(){
        return{
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
            used_name : '5인용 텐트',
            used_place : '대구광역시 서구',
            used_price : '￦100,000',
            used_status : '거래중'
          },
          {
            used_img : '',
            used_name : '6인용 텐트',
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

      }
    }
  
  </script>
  <style scoped src="@/assets/css/used/UsedMain.css" />
