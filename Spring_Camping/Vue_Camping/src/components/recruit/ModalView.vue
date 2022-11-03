<template>
    <div class="recru-modal">
        <div class="recru-overlay" @click="$emit('close-modal')"> </div>
        <div class="recru-modal-card">
            <slot/>
        </div>
    </div>
</template>

<script>
export default{}
</script>
<style scoped>
/* Modal 컴포넌트를 감싸고 있는 부모 */
.recru-modal{
    z-index: 1000;
}
.recru-modal, .recru-overlay{
    width: 100%;
    height: 100%;
    position: fixed;
    left: 0;
    top: 0;  
}
/* 컴포넌트 영역과 같은 크기를 가진 배경 */
.recru-overlay{
    opacity: 0.5;
    background-color: black;
}
/* 내용이 보이는 부분 */
.recru-modal-card{
    position: relative;
    max-width: 50%;
    margin: auto;
    margin-top: 150px;
    padding: 20px;
    /* background-color: white; */
    min-height:100px;
    z-index: 10;
    opacity: 1;
    display: flex;
    justify-content: center;
}
</style>