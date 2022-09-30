<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="text" placeholder="검색">
    </div>
    <div class="sns-card-container">
      <div v-for="info in cardInfo" class="sns-card">
        <div class="sns-card-id" style="display:none;" v-text="info.id"></div>
        <img v-attr:src="info.imgUrl">
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data: function() {
      return {
        info : [
          {
            id : 'aaaaaa',
            imgUrl :'aaaaaaaaaaa.jpg'
          },{
            id : 'aaaaaa',
            imgUrl :'aaaaaaaaaaa.jpg'
          },{
            id : 'aaaaaa',
            imgUrl :'aaaaaaaaaaa.jpg'
          },{
            id : 'aaaaaa',
            imgUrl :'aaaaaaaaaaa.jpg'
          },{
            id : 'aaaaaa',
            imgUrl :'aaaaaaaaaaa.jpg'
          }
        ]
      }
    }

  }
</script>
<style scoped>
  .sns-container{
    width:100%;
    display:flex;
    flex-wrap :wrap;
    justify-content: center;
  }
  .sns-searchbox {
    display:block;
    margin-bottom: 15px;
  }
  .sns-searchbox input {
    padding:10px;
    border: 1px solid rgb(228, 228, 228);
    border-radius: 5px;
  }
  .sns-card-container {
    width:100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }
  .sns-card{
    width:30vw;
    height: 30vw;
    margin-bottom: 5px;
    margin-right: 5px;
    cursor: pointer;
  }
  img {
    width:100%;
    height:100%;
    object-fit: cover;
  }
</style>