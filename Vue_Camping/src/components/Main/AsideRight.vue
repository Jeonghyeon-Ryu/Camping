<template>
    <div class="aside-right">
        <router-link v-if="$store.state.email == null" to="/login" tag="div" class="aside-right-button"
            @click="clickMenu">로그인 / 회원가입</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/recru/RecruMypage" tag="div"
            class="aside-right-button" @click="clickMenu" style="font-weight:bold">나의 캠핑</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" v-bind:to="{name:'myUsed', params:{email : $store.state.email}}" tag="div"
            class="aside-right-button" @click="clickMenu" style="font-weight:bold">나의 중고장터</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/" tag="div"
            class="aside-right-button" @click="clickMenu" style="font-weight:bold">나의 피드</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/" tag="div"
            class="aside-right-button" @click="clickMenu" style="font-weight:bold">나의 노트</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/" tag="div"
            class="aside-right-button" @click="clickMenu" style="font-weight:bold">저장 목록</router-link>
        <hr v-if="($store.state.email != null)&&($store.state.auth == 1)">
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/User/Report" tag="div"
            class="aside-right-button" @click="clickMenu">나의 신고</router-link>
        <router-link v-if="($store.state.email != null)&&($store.state.auth == 1)" to="/MyInfo" tag="div"
            class="aside-right-button" @click="clickMenu">나의 정보</router-link>
        <router-link v-if="($store.state.email == 'admin')&&($store.state.auth == 0)" to="/Manage/User" tag="div"
            class="aside-right-button" @click="clickMenu">회원 관리</router-link>
        <router-link v-if="($store.state.email == 'admin')&&($store.state.auth == 0)" to="/Manage/Report" tag="div"
            class="aside-right-button" @click="clickMenu">신고 관리</router-link>
        <router-link v-if="$store.state.email != null" to="/" tag="div" class="aside-right-button" @click="logout()">로그아웃</router-link>
    </div>
</template>

<script>
export default {
    methods: {
        logout() {
            this.$emit("hideMenu");
            this.$store.commit('delUserInfo');
            this.$router.push({ name: "Home" });
        },
        clickMenu() {
            this.$emit("hideMenu");
        }
    }
}
</script>

<style scoped src="@/assets/css/Main/AsideRight.css">

</style>