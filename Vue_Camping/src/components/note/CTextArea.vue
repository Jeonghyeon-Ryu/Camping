<template>
  <div v-if="type=='textBox'">
    <textarea class="write_place" v-on:keyup.shift="shiftfUp($event)" v-on:keydown.shift="shiftfDown($event)"
      v-on:keydown.enter="creTextarea($event)" :value="data">
    </textarea>
  </div>
</template>
<script>
export default {
  props: ['data'],
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
  }
}
</script>

<style scoped>
.textbox {
    background-color: beige;
    border: 3px solid beige;
    line-height: 10px;
}
.write_place {
    display: inline-block;
    height: fit-content;
    width: 90%; 
    border : none;
}
</style>