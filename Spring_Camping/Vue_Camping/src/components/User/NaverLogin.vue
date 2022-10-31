<template>
    <div @click="kakaoLogin" class="naver-login-container">
        <img src="@/assets/img/login/naver_button.png" />
    </div>
</template>
<script>
export default {
    data: function() {
        return {
            REDIRECT_URL : "http://localhost:8081/Login",
            AUTH_URL : "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=b0f34304cdd8f262ee81a86644e1a33e&redirect_uri="
        };
    },
    methods: {
        kakaoLogin() {
            // window.location.replace(this.AUTH_URL+this.REDIRECT_URL);
        },  
    }
}
</script>
<style scoped>
.naver-login-container {
    width:100%;
    height:fit-content;
}
.naver-login-container img {
    width: 200px;
    height:35px;
    object-fit: fill;
}
</style>