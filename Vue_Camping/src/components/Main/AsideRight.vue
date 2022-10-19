<template>
    <div class="aside-right">
        <router-link v-if="$store.state.email == null" to="/login" tag="div" class="aside-right-button" @click="clickMenu">로그인 / 회원가입</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/" tag="div" class="aside-right-button" @click="clickMenu" style="font-weight:bold">나의 캠핑</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/used/myUsed" tag="div" class="aside-right-button" @click="clickMenu" style="font-weight:bold">나의 중고장터</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/" tag="div" class="aside-right-button" @click="clickMenu" style="font-weight:bold">나의 피드</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/" tag="div" class="aside-right-button" @click="clickMenu" style="font-weight:bold">나의 노트</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/" tag="div" class="aside-right-button" @click="clickMenu" style="font-weight:bold">저장 목록</router-link>
        <hr v-if="($store.state.email != null)&&($store.state.auth == 1)">
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/" tag="div" class="aside-right-button" @click="clickMenu">나의 신고</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/MyInfo" tag="div" class="aside-right-button" @click="clickMenu">나의 정보</router-link>
        <router-link to="/MyInfo" tag="div" class="aside-right-button" @click="clickMenu">나의 정보</router-link>
        <router-link v-if="($store.state.email == 'admin')&&($store.state.auth == 0)" to="/Manage/User" tag="div" class="aside-right-button" @click="clickMenu">회원 관리</router-link>
        <router-link to="/Manage/User" tag="div" class="aside-right-button" @click="clickMenu">회원 관리</router-link>
        <router-link v-if="($store.state.email == 'admin')&&($store.state.auth == 0)" to="/" tag="div" class="aside-right-button" @click="clickMenu">캠핑 관리</router-link>
        <router-link v-if="($store.state.email == 'admin')&&($store.state.auth == 0)" to="/" tag="div" class="aside-right-button" @click="clickMenu">모집 관리</router-link>
        <router-link v-if="($store.state.email == 'admin')&&($store.state.auth == 0)" to="/" tag="div" class="aside-right-button" @click="clickMenu">중고 관리</router-link>
        <router-link v-if="($store.state.email == 'admin')&&($store.state.auth == 0)" to="/" tag="div" class="aside-right-button" @click="clickMenu">피드 관리</router-link>
        <router-link v-if="$store.state.email != null" to="/" tag="div" class="aside-right-button" @click="logout">로그아웃</router-link>
    
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                email : sessionStorage.getItem("email"),
            }
        },  
        methods : {
            logout(){
                sessionStorage.removeItem("email");
                sessionStorage.removeItem("nickname");
            },
            clickMenu() {
                this.$emit("hideMenu");
            }
        }
    }
</script>

<style scoped src="@/assets/css/Main/AsideRight.css"> </style>