<template>
    <a :href="AUTH_URL+REDIRECT_URL" class="kakao-login-container">
        <img src="@/assets/img/Login/kakao_button.png" />
    </a>
</template>
<script>
export default {
    data: function() {
        return {
            REDIRECT_URL : "http://localhost:8080/Login",
            AUTH_URL : "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=b0f34304cdd8f262ee81a86644e1a33e&redirect_uri="
        };
    },
    methods: {
        kakaoLogin() {
            window.open(this.AUTH_URL+this.REDIRECT_URL,"","menubart=no, status=no");
        },  
    }
}
</script>
<style scoped>
.kakao-login-container {
    width:100%;
    height:35px;
}
.kakao-login-container img {
    width: 200px;
    height:35px;
    object-fit: fill;
}
</style>