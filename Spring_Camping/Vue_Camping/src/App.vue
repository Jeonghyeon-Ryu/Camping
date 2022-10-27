<template>
  <div>
    <HeaderNav ref="headerNav"></HeaderNav>
    <router-view @loginout="loginout" @send="send" @scroll="onScroll($event)"></router-view>
  </div>
</template>

<script>
import { RouterLink, RouterView } from 'vue-router'
import HeaderNav from './components/Main/HeaderNav.vue'
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
  name: 'App',
  components: {
    HeaderNav,
  },
  data() {
    return {
      stompClient: '',
      chatInfo: {},
      roomList: [],
    }
  },
  methods: {
    loginout() {
      // this.connect();
      this.$refs.headerNav.setInfo();
    },
    connect() { // 소켓연결
      const serverURL = "/java/ws";
      let component = this;
      let socket = new SockJS(serverURL);
      if (this.$store.state.email != null) {
        this.stompClient = Stomp.over(socket);
        this.stompClient.connect(
          {},
          (frame) => {    // 콜백함수
            console.log("STOMP 연결 완료", frame);
            component.isConnectQueue(); // 큐 구독
            // 기존에 연결되어있는 방 조회 -> 읽지않은 채팅내역 확인( 방번호 + 갯수만 ) -> 표시
            // 전체 내용 가져오기
            // 구독하기 
          },
          (error) => {
            console.log("소켓 연결 실패", error);
          }
        );
      }
    },
    isConnectQueue() {
      let component = this;
      // Queue 연결 조회
      fetch('/java/room/' + this.$store.state.email)
        .then(result => result.text())
        .then(result => {
          console.log('isConnectQueue Result : ', result);
          console.log('이메일 : ', component.$store.state.email);
          component.stompClient.subscribe(    // 연결 후 바로 아이디로 구독
            "/queue/" + component.$store.state.email,
            function (result) {
              // 사이트 접속시 바로 소켓연결 후 email 로 구독
              // queue 로 오는 정보는 새로운 방을 만든 정보.
              let content = JSON.parse(result.body);
              console.log("연결 요청 정보 전부 : ", content)
              console.log("타입 확인 : ", Array.isArray(content));
              if (Array.isArray(content)) { // 채팅 입장 시 리스트
                let roomId = content[0].roomId;
                component.chatInfo.roomId = content;
                console.log(component.chatInfo);
              } else {  // 채팅방 입장 요청
                // 현재 세션에서 구독한 내역이 있으면 구독 재요청 안함.
                if (component.roomList.find(value => value === content.roomId) == undefined) {
                  component.roomList.push(content.roomId);
                  component.stompClient.subscribe("/topic/room/" + content.roomId,
                    function (res) {
                      let roomContent = JSON.parse(res.body);
                      console.log("채팅 들어온 방번호 : ", roomContent);
                    }
                  )
                } else {
                  console.log(content.roomId + "는 존재하는 방입니다.")
                }
              }
            }
          );
          // Queue 연결 DB저장
          if (+result == 0) {
            fetch('/java/room/' + this.$store.state.email, {
              method: 'POST'
            }).then(result => result.text())
              .then(result => {
                if (+result == 1) {
                  console.log("Queue 저장 완료");
                }
              })
          }
        })
    },
    send(message) {
      // 연결 없을때 재연결
      if (this.stompClient == '' && this.$store.state.email != null) {
        this.connect();
      }
      // 채팅 연결 메세지 보내기
      // 돌아오는 Room 번호로 구독하기.
      // 연결여부 확인 연결 안되있을때 연결하기 ( 새로고침때문 )
      // 구독 여부 확인이 중요.
      // Socket 연결 여부 사전 확인 필요.
      // 방생성 -> Queue 사전 구독 필요
      // 채팅방 접속 -> Queue 구독 + Room 구독 필요( Room 구독은 Chatlog로 확인 ).
      // 방나가기 -> Room 구독 취소 필요

      // if (this.stompClient == '') {  // 연결되지 않았을때.
      //   this.connect();
      // }
      if (message.type == 0) {
        this.createRoom(message);
      } else {
        this.stompClient.send("/app/send", JSON.stringify(message), (result) => {
          console.log('결과 ', result);
        })
      }
    },
    createRoom(message) {  // 기존 연결되어있는 방 없으면 방 만들기 / 
      fetch('/java/room', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(message)
      }).then(result => result.text())
        .then(result => {
          console.log('Result !!! 방있나 ? ', result, ' ', result == '1')
          message.roomId = +result;
          if (message.roomId === 0) { // 방 없을때
            message.type = 0;
            this.stompClient.send("/app/send", JSON.stringify(message), (result) => {
              console.log('결과 ', result);
            })
          } else {                    // 방 있을때
            message.type = 1;
            console.log('메세지다 ', message);
            // 해당 방으로 이동
            this.stompClient.send("/app/send", JSON.stringify(message), (result) => {
              console.log('결과 ', result);
            })
            // this.getChatAll(message);
          }
        }).catch(err => console.log(err))
    },
    getNotReadChatCount() { // 읽지않은 채팅 갯수 가져오기

    },
    getChatAll() {    // 모든 채팅 내용 가져오기
      // 모든채팅 읽고 -> 채팅방 이동( 채팅들고 )
      // fetch('/java/chat')
    },
    setSubscribe() {  // getExistRoom 에서 조회된 방으로 구독하기

    },
  },
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Open+Sans&display=swap'); 
* {
  margin: 0;
  padding: 0;
  list-style: none;
  font-style: none;
  box-sizing: border-box;
}
@font-face {
  font-family: 'Noto Sans KR';
  src :url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Open+Sans&display=swap');
}
@font-face {
  font-family: 'Open Sans';
  src :url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Open+Sans&display=swap');
  unicode-range: U+0041-005A, U+0061-007A;
}
body {
  overflow-x: hidden;
  overflow-y: hidden;
}

#app {
  font-family: 'Noto Sans KR', 'Open Sans';
  /* -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; */
  text-align: center;
  color: #2c3e50;
  overflow-y: auto;
}

/* total width */
*::-webkit-scrollbar {
    background-color:#fff;
    width:16px
}

/* background of the scrollbar except button or resizer */
*::-webkit-scrollbar-track {
    background-color:#fff
}
*::-webkit-scrollbar-track:hover {
    background-color:#f4f4f4
}

/* scrollbar itself */
*::-webkit-scrollbar-thumb {
    background-color:rgb(228,239,231) ;
    border-radius:16px;
    border:5px solid #fff
}
*::-webkit-scrollbar-thumb:hover {
    background-color:rgb(6,68,32);
    border:4px solid #f4f4f4
}

/* set button(top and bottom of the scrollbar) */
*::-webkit-scrollbar-button {display:none}

</style>
