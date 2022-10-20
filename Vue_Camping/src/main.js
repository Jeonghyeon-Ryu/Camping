import { createApp } from 'vue'
import App from './App.vue'
import router from "./router"
import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate';

const store = createStore({
    plugins : [createPersistedState({
        storage: window.sessionStorage
      })],
    state: {
        email: sessionStorage.getItem('email'),
        nickname : sessionStorage.getItem('nickname'),
        auth : sessionStorage.getItem('auth'),
        currentCategory : sessionStorage.getItem('currentCategory')
    },
    mutations : {
        getUserInfo(state) {
            state.email = sessionStorage.getItem('email');
            state.nickname = sessionStorage.getItem('nickname');
            state.auth = sessionStorage.getItem('auth');
        },
        delUserInfo(state) {
            sessionStorage.removeItem('email');
            sessionStorage.removeItem('nickname');
            sessionStorage.removeItem('auth');
            state.email = null;
            state.nickname = null;
            state.auth = null;
        },
        setCurrentCategory(state){
            state.currentCategory = sessionStorage.getItem('currentCategory');
        },
        delCurrentCategory(state){
            sessionStorage.removeItem('currentCategory');
            state.currentCategory = null;
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
