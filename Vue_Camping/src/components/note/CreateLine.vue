<template>
  <CTextArea v-if="type==0" :data="data" ></CTextArea>
  <CTableArea v-if="type==1" :data="data"></CTableArea>
</template>
<script>
import CTextArea from './CTextArea.vue';
import CTableArea from './CTableArea.vue';

export default {
  props: ['type', 'data'],
  components: { CTextArea, CTableArea }
}
</script>
<style scoped>

</style>