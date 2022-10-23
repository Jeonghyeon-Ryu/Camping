<template>
    <div class="chatview-container">
        <RoomList></RoomList>
        <Chat></Chat>
    </div>
</template>
<script>
import RoomList from "./RoomList.vue";
import Chat from "./Chat.vue";
export default {
    components: { RoomList, Chat }
}
</script>
<style scoped>
    .chatview-container {
        padding: 150px 20px 20px 20px;
        width:100%;
        height:100vh;
        display: flex;
        justify-content: center;
    }
</style>