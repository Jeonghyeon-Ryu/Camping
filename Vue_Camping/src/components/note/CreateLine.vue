<template>
  <CTextArea v-if="type==0" :data="data" ></CTextArea>
  <CTableArea v-if="type==1" :data="data"></CTableArea>
  <CCheckList v-if="type==2" :data="data"></CCheckList>
</template>
<script>
import CTextArea from './CTextArea.vue';
import CTableArea from './CTableArea.vue';
import CCheckList from './CCheckList.vue';

export default {
  props: ['type', 'data'],
  components: { CTextArea, CTableArea, CCheckList }
}
</script>
<style scoped src="@/assets/css/note/WriteNote.css">

</style>