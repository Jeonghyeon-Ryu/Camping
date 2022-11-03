<template>
  <div class="write_fn" @mouseenter="show($event)" @mouseleave="hide($event)">
    <div class="left_container">
      <div class="btn_container">
        <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
        <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
      </div>
    </div>
    <div class="textarea_container">
        <textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" v-on:keydown.shift="shiftfDown($event)"
        v-on:keydown.enter="creTextarea($event)" :value="data"  @input="resize"></textarea>
    </div>
  </div>
</template>

<script>
export default {
  props: ['data'],
  data() {
    return {
      shiftSatus: false
    }
  },
  
  methods: {
    
    shiftfUp: function (e) {
      this.shiftSatus = false;
    },
    shiftfDown: function (e) {
      this.shiftSatus = true;
    },
    show(e){
      let target = e.target;
      let showTarget = target.querySelector('.btn_container');
      showTarget.setAttribute('style','opacity:0.6');
    },
    hide(e){
      let target = e.target;
      let showTarget = target.querySelector('.btn_container');
      showTarget.setAttribute('style','opacity:0');
    },
    
    creTextarea: function (e) {
      if (!this.shiftSatus) {
        e.preventDefault();
        
        this.$emit('creArea');
      }
    },
    
  },
}
</script>

<style scoped src="@/assets/css/note/CTextArea.css">

</style>