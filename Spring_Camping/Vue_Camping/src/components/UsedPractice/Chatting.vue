<template>
    <div class="container">
    <div class="container-l">
      <div v-for="imgs in images" class="chatroom-list">
        <div class="chat-profile-pic">
          <img src="@/assets/img/used/heart2.png">
        </div>
        <div class="chat-content">
          <div class="chat-user"><b>sender</b></div>
          <div class="chat-content">sender.messages...</div>
          <div class="chat-date">sendDate(2022-10-21)</div>
        </div>
        <div class="chat-notification">
          1
        </div>
      </div>

    </div>
    <div class="container-r">
      <div class="chat-deal">
        <div class="deal-pic">
          <img src="@/assets/img/used/heart2.png">
        </div>
        <div class="deal-content">

          <div class="deal-name"><b>sender</b></div>
          <div class="deal-desc">sender.messages...</div>

        </div>
        <div class="deal-btns">
          <!-- v-if? -->
          <input type="button" value="거래완료" @click="">
          <input type="button" value="후기작성" @click="">
        </div>
      </div>
      <div v-for="imgs in images" class="chat-messages">
        메 세 지 들
        <div class="sender-msg">안녕</div>
        <div class="rece-msg">하세요</div>
      </div>
      <div class="chat-send">
        <input type="text" class="chat-send-input">
        <input type="button" value="전송">
      </div>
    </div>


  </div>
</template>
<script>
</script>
<style scoped>
     * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      text-align: left;
    }

    .container {
      width: 1000px;
      height: 600px;
      border: 1px solid #e6e6e6;
      margin: 0 auto;
      margin-top: 5%;
      display: flex;

    }

    .container-l {
      width: 50%;
      border: 1px solid #e6e6e6;
      overflow-y: scroll;
    }

    .container-r {
      width: 50%;
      border: 1px solid #e6e6e6;
    }

    .chatroom-list {
      width: 100%;
      height: 90px;
      padding: 2%;
      border: 1px solid #e6e6e6;
      cursor: pointer;
      display: flex;
      justify-content: space-between;
    }

    .chatroom-list:active {
      width: 100%;
      padding: 10px;
      border: 1px solid #e6e6e6;
      background-color: #e6e6e6;
    }
    .chat-profile-pic{
      width: 70px;
      margin:0 5% 0 2%;
      border: 1px solid #e6e6e6;
      border-radius: 100%;
    }
    .chat-profile-pic img{
      width: 100%;
      object-fit: cover;
    }
    .chat-notification{
      background-color: red;
      padding: 5px;
      border-radius: 100%;
    }
    .chat-deal{
      width: 95%;
      height: 100px;
      margin: 0 auto;
      margin-top: 2%;
      padding: 2%;
      border: 1px solid #e6e6e6;
      border-radius: 5px;
      background-color: rgba(242, 242, 242, 0.7);
      display: flex;
      justify-content: space-between;
    }
    .deal-btns{
      display: flex;
      flex-direction: column;
    }
    .chat-messages{
      padding:5%;
      position: relative;
    }
    .chat-send{
      width: 12%;
      padding: 2%;
      position: absolute;
      bottom: 1;
    }
    .sender-msg{
      float: left;
    }
    .rece-msg{
      float: right;
    }
    .chat-send-input{
    }
</style>