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
app.config.globalProperties.$filters = {
    formatDate(value) {
        const date = new Date(value);
        const year = date.getFullYear();
        let month = date.getMonth() + 1;
        month = month > 9 ? month : `0${month}`;
        let day = date.getDate();
        day = day > 9 ? day : `0${day}`;
        let hours = date.getHours();
        hours = hours > 9 ? hours : `0${hours}`;
        const minutes = date.getMinutes();
        return `${year}-${month}-${day} ${hours}:${minutes}`;
    },
    formatWon(value) {
        return value + '원';
    }
}
app.use(router);
app.use(store);
app.mount('#app');
