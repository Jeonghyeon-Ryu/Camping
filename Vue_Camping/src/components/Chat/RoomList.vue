<template>
    <div class="roomlist-container">
        <div class="roomlist-header">
            <div class="roomlist-name">Camp Chat</div>
            <div class="roomlist-invite"></div>
        </div>
        <div class="roomlist-body">
            <div class="room">
                <div class="roomImage"><img src=""></div>
                <div class="roomTitle">제목제목</div>
                <div class="roomUpdated">1분전</div>
            </div>
            <div class="room">
                <div class="roomImage"><img src=""></div>
                <div class="roomTitle">제목제목</div>
                <div class="roomUpdated">1분전</div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    props: ['roomList'],
}
</script>
<style scoped src="./RoomList.css"></style>