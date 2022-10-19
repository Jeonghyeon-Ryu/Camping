<template>
  <div class="write_fn">
    <div class="left_container">
      <div class="btn_container">
        <div class="drag_btn"><img src="@/assets/img/note/drag.png"> </div>
        <div class="del_line"><img src="@/assets/img/note/trash.png" @click="delLine($event)"></div>
      </div>
    </div>
    <div>
      <textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" v-on:keydown.shift="shiftfDown($event)"
        v-on:keydown.enter="creTextarea($event)" :value="data">
    </textarea>
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
    creTextarea: function (e) {
      if (!this.shiftSatus) {
        //e.preventDefault();
        
        this.$emit('creArea');
      }
    },
  }
}
</script>

<style scoped src="@/assets/css/note/WriteNote.css">
.textbox {
  background-color: beige;
  border: 3px solid beige;
  line-height: 10px;
}

.write_place {
  display: inline-block;
  height: fit-content;
  width: 90%;
  border: none;
}
</style>