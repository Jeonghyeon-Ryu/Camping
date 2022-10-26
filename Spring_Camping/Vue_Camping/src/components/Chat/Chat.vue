<template>
    <div class="chat-container">
        <div class="chat-header">
            <div class="chat-title">제목제목</div>
            <div class="chat-close-icon"><img src="@/assets/img/icons/close.png"></div>
        </div>
        <div class="chat-body">
            <!-- <MyMessage></MyMessage>
            <OtherMessage></OtherMessage> -->
        </div>
        <div class="chat-footer">
            <input type="text" class="chat-input" placeholder="채팅 내용을 입력해주세요." @keyup.enter="chatEnter"/>
            <div class="chat-input-icon"><img src="@/assets/img/icons/send.png"></div>
        </div>
    </div>
</template>
<script>
import MyMessage from "./MyMessage.vue";
import OtherMessage from "./OtherMessage.vue";
export default{ 
    components: { MyMessage, OtherMessage }
}
</script>
<style scoped src="./Chat.css"></style>