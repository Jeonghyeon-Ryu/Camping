<template>
    <div class="aside-right">
        <router-link to="/login" tag="div" class="aside-right-button">로그인 / 회원가입</router-link>
        <hr>
        <router-link to="/" tag="div" class="aside-right-button" style="font-weight:bold">나의 캠핑</router-link>
        <router-link to="/" tag="div" class="aside-right-button" style="font-weight:bold">나의 중고장터</router-link>
        <router-link to="/" tag="div" class="aside-right-button" style="font-weight:bold">나의 피드</router-link>
        <router-link to="/" tag="div" class="aside-right-button" style="font-weight:bold">나의 노트</router-link>
        <router-link to="/" tag="div" class="aside-right-button" style="font-weight:bold">저장 목록</router-link>
        <hr>
        <router-link to="/" tag="div" class="aside-right-button">나의 신고</router-link>
        <router-link to="/MyInfo" tag="div" class="aside-right-button">나의 정보</router-link>
        <router-link to="/" tag="div" class="aside-right-button">로그아웃</router-link>
    </div>
</template>

<script>
    export default {
        methods : {

        }
    }
</script>

<style scoped>
    .aside-right {
        position: absolute;
        top:0;
        z-index: 1002;
        background: #fff;
        width:200px;
        border: 1px solid rgb(230,230,230);
        border-radius: 15px;
        display:flex;
        flex-direction: column;
    }
    .aside-right hr {
        border:none;
        border-bottom : 0.5px solid rgb(230,230,230);
    }
    .aside-right .aside-right-button {
        text-decoration: none;
        margin:5px 0 5px 0;
        padding:10px;
        font-size:0.8rem;
        color:gray;
        cursor:pointer;
    }
    .aside-right .aside-right-button:hover {
        color:#000;
        font-weight: bold;
    }
</style>