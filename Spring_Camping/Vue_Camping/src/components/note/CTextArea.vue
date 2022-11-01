<template>
  <div class="write_fn">
    <div class="left_container">
      <div class="btn_container" @mouseover="show = true" @mouseleave="show = false">
        <div class="drag_btn" ><img src="@/assets/img/note/drag.png"></div>
        <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
      </div>
    </div>
    <div class="textarea_container">
        <textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" v-on:keydown.shift="shiftfDown($event)"
        v-on:keydown.enter="creTextarea($event)" ></textarea>
    </div>
  </div>
</template>

<script>
export default {
  props: ['data'],
  data() {
    return {
      shiftSatus: false,
      show : false
    }
  },
  
  methods: {
    
    shiftfUp: function (e) {
      this.shiftSatus = false;
    },
    shiftfDown: function (e) {
      this.shiftSatus = true;
    },
    creTextarea: function (e) {
      if (!this.shiftSatus) {
        e.preventDefault();
        
        this.$emit('creArea');
      }
    },
    /*resize() {
      let element = this.$refs["textarea"];

      element.style.height = "18px";
      element.style.height = element.scrollHeight + "px";
    },*/
    
  },
}
</script>

<style scoped src="@/assets/css/note/CTextArea.css">
/*@import url('https://fonts.googleapis.com/css2?family=Inconsolata&display=swap');*/

</style>