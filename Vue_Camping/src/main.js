import { createApp } from 'vue'
import App from './App.vue'
import router from "./router"
import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate';
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

const store = createStore({
    plugins: [createPersistedState({
        storage: window.sessionStorage
    })],
    state: {
        email: sessionStorage.getItem('email'),
        nickname: sessionStorage.getItem('nickname'),
        auth: sessionStorage.getItem('auth'),
        currentCategory: sessionStorage.getItem('currentCategory'),
        roomList:[],
        subscribeRoomList: [],
        roomChatList:{},
    },
    mutations: {
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
            state.connectQueue = false;
            roomList = [];
            roomChatList = [];
            stompClient.disconnect();
        },
        setCurrentCategory(state) {
            state.currentCategory = sessionStorage.getItem('currentCategory');
        },
        delCurrentCategory(state) {
            sessionStorage.removeItem('currentCategory');
            state.currentCategory = null;
        },
    },
    actions: {
        getRoomList(state) {

        },
        getChatList(state) {

        }
    }
})

const serverURL = "http://localhost:8087/java/ws";
const socket = new SockJS(serverURL);
const stompClient = Stomp.over(socket);

stompClient.connect(
    {},
    (frame) => {    // 콜백함수
        console.log("소켓 연결 완료", frame);
    },
    (error) => {
        console.log("소켓 연결 실패", error);
    }
);
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
    },
    formatMemberStatus(value) {
        if (value == 0) {
            return "탈퇴";
        } else if (value == 1) {
            return "제한";
        } else if (value == 2) {
            return "활성";
        } else {
            return "오류";
        }
    },
}
app.use(router);
app.use(store);
app.mount('#app');
