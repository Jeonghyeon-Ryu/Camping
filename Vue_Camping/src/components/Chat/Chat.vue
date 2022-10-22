<template>
    <div id="modal">
        <h3>message</h3>
        <div id="content">
            <div class="deal">
                <div class="img-container">
                    <img src="">
                </div>
                <div class="desc">
                    <h2>이름</h2>
                    <p>물건이름</p>
                </div>
                <button id="dealcomplete" v-on:click="dealComplete()">거래 완료하기</button>
            </div>
            <div class="msg">
                <div class="sender">
                </div>
                <div class="receiver">
                </div>
            </div>
            <div class="send">
                <input type="text" class="sendContent">
                <input type="file" class="sendPic">
                <input type="submit" class="submit" value="보내기" @click="send()">
            </div>
        </div>
    </div>
</template>
<script>
import img1 from "@/assets/img/bg9.jpg"
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
    data() {
        return {
            // 채팅에 필요한 데이터
            email: this.$store.state.email,
            rooms: [],
            room: '',
            chatInfo: [],
            msg: {},
        }
    },
    created: function () {
        this.connect();
        this.setMsg();
        console.log(this.msg);
    },
    methods: {
        setMsg: function() {
            // let date = new Date();
            // date = date.getFullYear() +"/" + (+date.getMonth()+1) +"/"+ date.getDate()
            this.msg = {
                roomId : 1,    // 서버에서 하는게 맞을듯
                email : this.$store.state.email,
                targetEmail : [this.$store.state.email],
                content : '예제22',
                regdate : '' // 서버에서 하는게 맞을듯
            }
        },
        dealComplete: function () {
            console.log(this.chatInfo.roomNo);
            for(let info of this.chatInfo){
                console.log(info);
            }
        },
        connect() {
            const serverURL = "http://localhost:8087/java/ws";
            let socket = new SockJS(serverURL);
            this.stompClient = Stomp.over(socket);
            let component = this;
            this.stompClient.connect(
                {},
                (frame) => {
                    console.log("소켓 연결 완료", frame);

                    component.stompClient.subscribe(
                        "/queue/" + this.$store.state.email,
                        function (res) {
                            let changeContent = JSON.parse(res.body);
                            component.chatInfo = changeContent;
                            console.log("구독", frame);
                            console.log('aaaaaaaaa');
                            console.log(changeContent);
                        }
                    );
                },
                (error) => {
                    console.log("소켓 연결 실패", error);
                }
            );
        },
        send() {
            this.setMsg();
            if (this.msg.content !== '') {

                // Send 하기전에 채팅방 + 채팅참여자를 DB에 저장하는게 좋아보임
                // 그리고 연결된 사람들은 Socket 연결될때(헤더에서) 채팅방 목록을 받아와서 Vuex에 저장하는게 좋아보임
                // 
                this.stompClient.send("/send",JSON.stringify(this.msg), (result) =>{
                    console.log(result);
                })

            //     this.axios
            //         .post("/InsertMessage/", {
            //             memberId: this.memberId,
            //             roomNo: this.roomId,
            //             content: this.message,
            //         })
            //         .then(function (res) {
            //             console.log(res);
            //         })
            //         .catch(function (error) {
            //             console.log(error);
            //         });
            //     //현재 대화방에 채팅보내기
            //     this.stompClient.send("/app/send", JSON.stringify(msg), (res) => {
            //         console.log(res);
            //     });
            // }
            // this.message = "";
            // for (let i = 0; i < this.roomList.length; i++) {
            //     console.log("=================a22222222222222222222sdadsadsadsadsadsadsadsadsadsad======")
            //     console.log(this.roomList[i])
            }
        },
    }
}
</script>
<style scoped>
#modal {
    border-radius: 0.5em;
    background-color: #41b56c;
    /* color: white; */
    padding: 1em;
    height: 100%;
}

#modal h3 {
    text-align: center;
    color: #fff;
    padding: 10px;
}

#content {
    background-color: #fff;
    border-radius: 5px;
    padding: 30px;
    height: 100%;
}

.deal {
    width: 100%;
    height: 200px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #f0f0f0;
    border-radius: 10px;
    box-shadow: 1px 1px 5px 0.2px #bcbcbc;
    padding: 10px;
    min-width: 100px;
}

.deal .img-container {
    height: 200px;
    width: 200px;
    overflow: hidden;
    padding: 10px;
}

.deal img {
    width: 100%;
    height: 100%;
    flex-wrap: nowrap;
    padding: 2%;
    object-fit: fill;
}

.desc {
    min-width: 50px;
    overflow: hidden;
}

#dealcomplete {
    flex-wrap: nowrap;
    padding: 15px;
    margin: 30px;
    background-color: #41b56c;
    border: none;
    border-radius: 10px;
    color: white;
    font-weight: bold;
    cursor: pointer;
}

#modal h4 {
    padding: 30px;
    text-align: center;
}

.msg {
    width: 100%;
    padding: 5%;
    display: flex;
    flex-direction: column;
    position: relative;
}

.sender,
.receiver {
    width: 30%;
}

.sender {
    background-color: #41b56c;

}

.receiver {
    background-color: gray;
}

.send {
    display: flex;
    justify-content: center;
    width: 100%;
    padding: 5% 0 5% 0;
}

.send>.sendContent {
    width: 70%;
    padding: 2%;
    border-radius: 5px;
    border: none;
    background-color: #d2d2d2;
    outline: none;
}

.sendPic,
.submit {
    width: 70px;
    padding: 15px;
    background-color: #41b56c;
    border: none;
    border-radius: 10px;
    color: white;
    font-weight: bold;
    cursor: pointer;
}
</style>
<!-- 메시지 양옆으로 정렬 / 사진전송버튼, 거래상태변경 / 전반적으로잘모르겟삼-->