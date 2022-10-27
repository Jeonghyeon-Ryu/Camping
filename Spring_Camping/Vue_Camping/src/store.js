import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState({
      storage: window.sessionStorage
  })],
  state: {
      email: null,
      nickname: null,
      auth: null,
      currentCategory: sessionStorage.getItem('currentCategory'),
      roomList: [],
      subscribeRoomList: [],
      roomChatList: {},
  },
  mutations: {
      getUserInfo(state, info) {
          console.log('aaa', info);
          state.email = info.email;
          state.nickname = info.nickname;
          state.auth = info.auth;
      },
      delUserInfo(state) {
          sessionStorage.removeItem('email');
          sessionStorage.removeItem('nickname');
          sessionStorage.removeItem('auth');
          state.email = null;
          state.nickname = null;
          state.auth = null;
          state.connectQueue = false;
          state.roomList = [];
          state.roomChatList = [];
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
      getRoomList() {
          let component = this;
          fetch('/java/roomlist/' + this.state.email)
              .then(result => result.json())
              .then(result => {
                  console.log(this.state);
                  this.state.roomList = result;
                  if (!stompClient.connected) {
                      stompClient.connect({},
                          (frame) => { // 콜백함수
                              console.log("소켓 연결 완료", frame);
                          },
                          (error) => {
                              console.log("소켓 연결 실패", error);
                          }
                      )
                  }
                  for(let sub of this.state.subscribeRoomList){
                      console.log("----------구독취소 : ",  sub);
                      stompClient.unsubscribe(sub.subscription);
                  }
                  this.state.subscribeRoomList = [];
                  for(let room of this.state.roomList) {
                      console.log("방 이름 : " , room);
                      stompClient.subscribe("/topic/room/"+room.roomId, function(res) {
                          for(let subscribeRoom of component.state.subscribeRoomList){
                              console.log('--------------', subscribeRoom.destination);
                              console.log('--------------', res.headers.destination);
                              if(subscribeRoom.destination===res.headers.destination){
                                  stompClient.unsubscribe(res.headers.subscription);
                              }
                          }
                          component.state.subscribeRoomList.push(res.headers);
                          console.log(component.state.subscribeRoomList);
                      })
                  }
                  for(let room of this.state.roomList) {
                      console.log('-------------- 헤더정보 읽어오기');
                      stompClient.send("/topic/room/"+room.roomId, '', {});
                  }
              })
      },
      getChatList(state) {


      }
  },
})