<template>
  <CTextArea :data="data"></CTextArea>
</template>
<script>
import CTextArea from './CTextArea.vue';

export default {
  props:['type','data'],
  created() {
    console.log('type : ', this.type)
    console.log('data : ', this.data)
  }, 
  components: { CTextArea }
}
</script>
<style scoped>

</style>