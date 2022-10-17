import { createApp } from 'vue'
import App from './App.vue'
import router from "./router"
import { createStore } from 'vuex'

const store = createStore({
    state: {
        email: localStorage.getItem('email'),
        nickname : localStorage.getItem('nickname'),
        auth : localStorage.getItem('auth')
    },
    mutations : {
        getUserInfo(state) {
            state.email = localStorage.getItem('email');
            state.nickname = localStorage.getItem('nickname');
            state.auth = localStorage.getItem('auth');
        },
        delUserInfo(state) {
            localStorage.removeItem('email');
            localStorage.removeItem('nickname');
            localStorage.removeItem('auth');
            state.email = null;
            state.nickname = null;
            state.auth = null;
        }
    },
    actions: {

    }
})

const app = createApp(App);
app.use(router);
app.use(store);
app.mount('#app');
