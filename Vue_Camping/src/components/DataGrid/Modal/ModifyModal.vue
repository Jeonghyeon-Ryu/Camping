<template>
  <div class="modify-container">
    <h2>정보 수정</h2>
    <div v-for="column of columns" class="modify-row">
      <span>{{column.name}}</span>
      <input type="text" :value="modifiedData[column.prop]"/>
    </div>
    <div class="modify-button-container">
      <RButton :inputColor="'lightGreen'" :inputSize="'lg'" :inputValue="'수정'" @clickBtn="confirm()"></RButton>
      <RButton :inputColor="'cream'" :inputSize="'lg'" :inputValue="'취소'" @clickBtn="cancel()"></RButton>
    </div>
  </div>
</template>
<script>
import RButton from '../../Admin/RButton.vue';
export default {
    props: ["columns", "modifyData"],
    data: function () {
        return {
            modifiedData: this.modifyData,
        };
    },
    methods: {
      cancel: function() {
        this.$emit('cancelModify');
      },
      confirm: function() {
        this.$emit('confirmModify')
      }
    }, 
    components: { RButton }
}
</script>
<style scoped>
  .modify-container{
    position:absolute;
    top:150px;
    left: calc(50% - 200px);
    width:400px;
    background: #fff;
    border:1px solid rgb(220,220,220);
    padding:10px;
    border-radius: 30px;
  }
  .modify-container h2{
    padding:10px;
    text-align: center;
  }
  .modify-row{
    padding:5px 10px 5px 10px;
    display:flex;
    justify-content: space-between;
  }
  .modify-row input {
    width:70%;
    padding:5px;
    border:1px solid rgb(220,220,220);
    border-radius: 5px;
  }
  .modify-button-container {
    display:flex;
    justify-content: space-around;
  }
</style>