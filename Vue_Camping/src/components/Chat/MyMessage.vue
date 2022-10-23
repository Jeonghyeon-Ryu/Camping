<template>
    <div class="my-message-container">
        <div class="my-message">{{message.message}}</div>
        <div class="my-message-regdate">{{message.regdate}}</div>
    </div>
</template>
<script>
export default {
    props: ['message']
}
</script>
<style scoped src="./MyMessage.css"></style>