<template>
  <CTextArea v-if="type==0" :data="data" ></CTextArea>
  <CTableArea v-if="type==1" :data="data"></CTableArea>
  <CCheckList v-if="type==2" :data="data"></CCheckList>
  <Cimg v-if="type==3" :data="data" :storedImages="storedImages"></Cimg>
</template>

<script>
import CTextArea from './CTextArea.vue';
import CTableArea from './CTableArea.vue';
import CCheckList from './CCheckList.vue';
import Cimg from './Cimg.vue';

export default {
  props: ['type', 'data', 'storedImages'],
  components: { CTextArea, CTableArea, CCheckList, Cimg }
}
</script>
<style scoped src="@/assets/css/note/WriteNote.css">

</style>