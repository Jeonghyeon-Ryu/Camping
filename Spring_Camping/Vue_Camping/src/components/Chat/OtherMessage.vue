<template>
    <div class="other-message-container">
        <div class="other-message-email">{{message.email}}</div>
        <div class="other-message">{{message.message}}</div>
        <div class="other-message-regdate">{{message.regdate}}</div>
    </div>
</template>
<script>
export default {
    props: ['message']
}
</script>
<style scoped src="./OtherMessage.css"></style>