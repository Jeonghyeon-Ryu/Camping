<template>
  <div>
    <div class="header-container">
      <div class="header-top-container">
        <router-link to="/" tag="div" class="header-title">
          <img src="@/assets/img/logo.png" class="header-logo">
          <!-- <span class="header-name">캠핑 가자 !</span>  -->
        </router-link>
        <div class="header-top-button">
          <router-link to="/login" tag="div" class="header-button"><img src="@/assets/img/login-30.png" alt=""></router-link>
          <!-- <div @click="showLoginForm()"><img src="../assets/img/login-30.png" alt=""></div> -->
          <router-link to="/" tag="div" class="header-button"><img src="@/assets/img/chat-30.png" alt=""></router-link>
          <router-link to="/" tag="div" class="header-button"><img src="@/assets/img/bell-30.png" alt=""></router-link>
          <div class="header-button" @click="showMenuForm()"><img src="@/assets/img/menu-30.png" alt=""></div>
        </div>
      </div>
      <div class="aside-right-container">
        <AsideRight></AsideRight>
      </div>
      <div class="header-middle-container">
        <!-- <NavbarDefault dark transparent></NavbarDefault> -->
        <div class="header-middle-button" >
          <div>캠핑장</div> 
          <div>캠핑장</div> 
          <div>캠핑장</div> 
          <div>캠핑장</div> 
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import AsideRight from './AsideRight.vue'
// import NavbarDefault from '@/examples/navbars/NavbarDefault.vue';
    export default {
    name: "HeaderNav",
    props: ["category"],
    methods: {
        showLoginForm: () => {
          this.$router.push({ name: "LoginSignup" });
        },
        showMenuForm: () => {
          document.querySelector('.aside-right-container').style.top = (document.scrollingElement.scrollTop+60) + 'px';
          document.querySelector('.aside-right-container').classList.toggle('active');
        }
    },
    components: { AsideRight }
}
</script>

<style scoped src="@/assets/css/header.css"></style>