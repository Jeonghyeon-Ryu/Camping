<template>
  <form class="modify-container" @submit.prevent>
    <h2>정보 수정</h2>
    <div v-for="(val,key) of modifyData" class="modify-row">
      <span>{{key}}</span>
      <input v-if="key != 'password'" type="text" v-model="modifyData[key]"/>
      <input v-if="key == 'password'" type="password" v-model="modifyData[key]"/>
    </div>
    <div class="modify-button-container">
      <RButton :inputColor="'lightGreen'" :inputSize="'md'" :inputValue="'확인'" @clickBtn="confirm()"></RButton>
      <RButton :inputColor="'cream'" :inputSize="'md'" :inputValue="'취소'" @clickBtn="cancel()"></RButton>
    </div>
  </form>
</template>
<script>
import RButton from '../../Admin/RButton.vue';
export default {
    props: ["columns", "modifyData"],
    created : function() {
      console.log('aa', this.columns);
      console.log('aa', this.modifyData);
    },
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
        let data = new FormData(document.querySelector('.modify-container'));
        data.forEach((value,key)=>{
          console.log(value);
        })
        console.log(this.modifiedData);
        this.$emit('confirmModify', this.modifiedData);
      }
    }, 
    components: { RButton }
}
</script>
<style scoped>
  .modify-container{
    position:absolute;
    top:30px;
    left: calc(50% - 200px);
    width:400px;
    background: #fff;
    border:1px solid rgb(220,220,220);
    padding:10px;
    border-radius: 10px;
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