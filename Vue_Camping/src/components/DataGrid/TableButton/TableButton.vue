<template>
  <div :type="type" @click="clickButton(type)" class="button-container">
    <img v-bind:src="source" />
  </div>
</template>

<script>
import deleteBtn from '@/assets/img/Table/delete.png';
import modifyBtn from '@/assets/img/Table/edit.png';
import confirmBtn from '@/assets/img/Table/accept.png';
import limit from '@/assets/img/Table/limit.png';
import active from '@/assets/img/Table/active.png';

export default {
  props:['type'],
  data: function() {
    return {
      source : ''
    }
  },
  mounted: function() {
    this.setButtonImage();
  },
  methods: {
    setButtonImage : function() {
      if(this.type == 'modify') {
        this.source = modifyBtn;
      } else if(this.type =='remove'){
        this.source = deleteBtn;
      } else if(this.type =='limit'){
        this.source = limit;
      } else if(this.type =='active'){
        this.source = active;
      }
    },
    clickButton(type) {
      if(type=="modify") {
        this.$emit('modify');
      } else if(type=="remove"){
        this.$emit('remove');
      } else if(type=='limit'){
        this.$emit('limit');
      } else if(type=='active'){
        this.$emit('active');
      }
    }
  }
}
</script>
<style scoped>
  .button-container {
    height:20px;
    width:auto;
    display:flex;
    justify-content: center;
    cursor: pointer;
  }
  .button-container img {
    height:100%;
    width:auto;
  }
</style>