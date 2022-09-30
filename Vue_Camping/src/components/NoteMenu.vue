<template>
  <div class="note-menu-container">
    <div v-click="noteMethodClick" class="note-method">이용방법</div>
    <div v-click="noteMineClick" class="note-mine">나의 노트</div>
    <div v-click="noteInvitedClick" class="note-invited">초대 노트</div>
    <div v-click="noteWriteClick" class="note-write">노트 작성</div>
  </div>
</template>
<script>
  export default {
    methods :{
      noteMethodClick(event){
      },
      noteMineClick(event){
      },
      noteInvitedClick(event){
      },
      noteWriteClick(event){
      },
    }
  }
</script>

<style>
  .note-menu-container {
    min-width: 150px;
    width:25%;
    display:flex;
    flex-direction: column;
    padding: 0 10px 0 10px;
  }
  .note-menu-container>div{
    text-align: center;
    padding:10px;
    margin:10px;
    cursor:pointer;
    border:1px solid rgb(230,230,230);
  }
  .note-menu-container>div:hover {
    background: rgba(228,239,231,0.7);
    box-shadow: 1px 1px 2px gray;
  }
</style>