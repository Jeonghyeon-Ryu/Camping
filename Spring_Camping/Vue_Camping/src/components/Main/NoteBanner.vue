<template>
  <div class="note-banner-container" @click="clickBanner">
    <img src="@/assets/img/main-note.jpg"/>
  </div>
</template>
<script>
export default{
  methods :{
    clickBanner() {
      this.$router.push({ name: 'MynoteList'});
    }
  }
}
</script>
<style scoped>
  .note-banner-container{
    width:100%;
    height:100%;
    padding:0;
    cursor: pointer;
  }
  .note-banner-container img{
    width: 100%;
    height:150px;
  }
</style>