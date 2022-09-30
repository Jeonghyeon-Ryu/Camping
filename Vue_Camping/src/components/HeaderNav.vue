<template>
  <div class="header-container">
    <div class="header-top-container">
      <div class="header-title">
        <img src="../assets/img/logo.png" class="header-logo">
        <!-- <span class="header-name">캠핑 가자 !</span>  -->
      </div>
      <div class="header-top-button">
        <div><img src="../assets/img/login-30.png" alt=""></div>
        <div><img src="../assets/img/chat-30.png" alt=""></div>
        <div><img src="../assets/img/bell-30.png" alt=""></div>
        <div><img src="../assets/img/menu-30.png" alt=""></div>
      </div>
    </div>
    <div class="header-middle-button" >
      <div>캠핑장</div> 
      <div>캠핑장</div> 
      <div>캠핑장</div> 
      <div>캠핑장</div> 
    </div>
  </div>
</template>

<script>
    export default {
      name: 'HeaderNav',
        props:['category'],
        
    }
</script>

<style scoped src="../assets/css/header.css"></style>