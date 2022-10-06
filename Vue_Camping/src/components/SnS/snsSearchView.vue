<template>
<div class="sns-searchbox">
  <input type="text" v-model="snsSearchboxText" placeholder="검색어를 입력해주세요.">
  <input v-on:click="snsSearchboxButton" type="button" value="X">
</div>
</template>

<script>
  export default{
    components : {},
    data : ()=>{
      return {
        snsSearchboxText : ''
        
      }
    },
    methods : {
      snsSearchboxButton(){
        console.log()
      }
    }
  }
  </script>