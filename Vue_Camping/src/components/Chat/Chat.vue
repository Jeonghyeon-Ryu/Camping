<template>
    <div id="modal">
        <h3>message</h3>
        <div id="content">
            <div class="deal">
                <div class="img-container">
                    <img v-bind:src="prodImg">
                </div>
                <div class="desc">
                    <h2>{{used_name}}</h2>
                    <p>{{used_content}}</p>
                </div>
                <button v-if="dealComplete" id="dealcomplete" v-on:click="dealComplete()">거래 완료하기</button>
                <button v-if="!dealComplete" id="dealcomplete" v-on:click="dealComplete()">후기 작성하기</button>
            </div>
            <div class="msg">
                <div class="sender">
                    {{sender_msg}}
                </div>
                <div class="receiver">
                    {{receiver_msg}}
                </div>
            </div>
            <div class="send">
                <input type="text" class="sendContent">
                <input type="file" class="sendPic">
                <input type="submit" class="submit">
            </div>
        </div>
    </div>
</template>
<script>
import img1 from "@/assets/img/bg9.jpg"
import { change } from "dom7";

export default {
    data() {
        return {
            // 채팅에 필요한 데이터
            email : this.$store.state.email,

            dealComplete: true,
            prodImg: img1,
            used_name: '4인용 텐트',
            used_content: '상태좋습니다',
            sender_msg: '안녕하세요 텐트 팔렸나요?',
            receiver_msg: '아직안팔렸어용^^'
        }
    },
    methods: {
        dealComplete: function () {
            this.dealComplete = !this.dealComplete;
        },
        connect() {
            const serverURL = "http://localhost:8088/java/sock";
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
                            
                            console.log("구독", frame);
                            console.log(changeContent);
                        }
                    );
                },
                (error) => {
                    console.log("소켓 연결 실패", error);
                }
            );
        }
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