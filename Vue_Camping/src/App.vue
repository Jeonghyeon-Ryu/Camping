<template>
  <div>
    <HeaderNav></HeaderNav>
    <router-view></router-view>
  </div>
</template>

<script>
import { RouterLink, RouterView } from 'vue-router'
import HeaderNav from './components/Main/HeaderNav.vue'

export default {
  name: 'App',
  components: {
    HeaderNav,
  }
}
</script>

<style>
  * { 
    margin: 0;
    padding: 0;
    list-style: none;
    font-style: none;
    box-sizing: border-box;
  }
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
</style>
