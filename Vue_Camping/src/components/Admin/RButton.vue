<template>
  <button v-text="value" ref="btn" class="btn" @click="clickBtn" @mouseover="hoverEvent" @mouseout="outEvent"></button>
</template>
<script>
export default {
  props: ['inputColor', 'inputSize', 'inputValue'],
  data: function () {
    return {
      color: this.inputColor,
      value: this.inputValue,
      size: this.inputSize
    }
  },
  mounted: function () {
      if (this.inputSize == 'lg') {
        this.$refs.btn.style.padding = 'var(--lg)'
        this.$refs.btn.style.width = 'var(--lgWidth)'
      } else if (this.inputSize == 'md') {
        this.$refs.btn.style.padding = 'var(--md)';
        this.$refs.btn.style.width = 'var(--mdWidth)'
      } else {
        this.$refs.btn.style.padding = 'var(--sm)';
        this.$refs.btn.style.width = 'var(--smWidth)'
      }
      if (this.color == 'lightCream') {
        this.$refs.btn.style.background = 'var(--lightCream)';
        this.$refs.btn.style.color='var(--green)';
      } else if (this.color == 'cream') {
        this.$refs.btn.style.background = 'var(--cream)';
        this.$refs.btn.style.color='var(--green)';
      } else if (this.color == 'lightGreen') {
        this.$refs.btn.style.background = 'var(--lightGreen)';
      } else if (this.color == 'green') {
        this.$refs.btn.style.background = 'var(--green)';
        this.$refs.btn.style.color='var(--white)';
      } else if (this.color == 'white') {
        this.$refs.btn.style.background = 'var(--white)';
      }
  },
  methods: {
    clickBtn: function () {
      this.$emit('clickBtn');
    },
    hoverEvent: function() {
      if (this.color == 'lightCream') {
        this.$refs.btn.style.background = 'var(--lightGreen)';
        this.$refs.btn.style.boxShadow = '2px 2px 2px gray';
      } else if (this.color == 'cream') {
        this.$refs.btn.style.background = 'var(--green)';
        this.$refs.btn.style.color='var(--cream)';
        this.$refs.btn.style.boxShadow = '2px 2px 2px gray';
      } else if (this.color == 'lightGreen') {
        this.$refs.btn.style.background = 'var(--lightCream)';
        this.$refs.btn.style.color='var(--green)';
        this.$refs.btn.style.boxShadow = '2px 2px 2px gray';
      } else if (this.color == 'green') {
        this.$refs.btn.style.background = 'var(--cream)';
        this.$refs.btn.style.color='var(--green)';
        this.$refs.btn.style.boxShadow = '2px 2px 2px gray';
      } else if (this.color == 'white') {
        this.$refs.btn.style.background = 'rgb(220,220,220)';
        this.$refs.btn.style.boxShadow = '2px 2px 2px gray';
      }
    },
    outEvent: function() {
      if (this.color == 'lightCream') {
        this.$refs.btn.style.boxShadow = 'none';
        this.$refs.btn.style.background = 'var(--lightCream)';
      } else if (this.color == 'cream') {
        this.$refs.btn.style.boxShadow = 'none';
        this.$refs.btn.style.background = 'var(--cream)';
        this.$refs.btn.style.color='var(--green)';
      } else if (this.color == 'lightGreen') {
        this.$refs.btn.style.boxShadow = 'none';
        this.$refs.btn.style.background = 'var(--lightGreen)';
      } else if (this.color == 'green') {
        this.$refs.btn.style.boxShadow = 'none';
        this.$refs.btn.style.background = 'var(--green)';
        this.$refs.btn.style.color='var(--white)';
      } else if (this.color == 'white') {
        this.$refs.btn.style.boxShadow = 'none';
        this.$refs.btn.style.background = 'var(--white)';
      }
    }
  }
}
</script>
<style scoped>
.btn {
  --lgWidth: 300px;
  --mdWidth: 200px;
  --smWidth: 100px;
  --lg: 5px 20% 5px 20%;
  --md: 5px 10% 5px 10%;
  --sm: 5px 5% 5px 5%;
  --lightCream: rgb(253, 250, 246);
  --cream: rgb(250, 241, 230);
  --lightGreen: rgb(228, 239, 231);
  --green: rgb(6, 68, 32);
  --white: rgb(255, 255, 255);
  --hoverBg: ;
  --hoverColor: ;
  border:1px solid rgb(220,220,220);
  border-radius: 10px;
  cursor: pointer;
}
</style>