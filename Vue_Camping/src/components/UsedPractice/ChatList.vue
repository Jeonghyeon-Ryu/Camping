<template>
       <div id="modal">
        <h3>message</h3>
        <div id="content">
            <div class="chat">
                <div class="img-container">
                    <img src="image/user.png" alt="프로필사진">
                </div>
            </div>
            <table class="chat">
                <div class="chatroom">
                    <tr>
                        <td rowspan="2" style="width:20%"><img src="image/user.png" width="30%"></td>
                        <td>joha</td>
                        <td style="width:10%">5</td>
                    </tr>
                    <tr>
                        <td>두류역 18번 출구에서 뵙겠습니다~</td>
                        <td>나가기</td>
                    </tr>
                </div>
            </table>

            <table class="chat">
                <div class="chatroom">
                    <tr>
                        <td rowspan="2" style="width:20%"><img src="image/user.png" width="30%"></td>
                        <td>yul</td>
                        <td style="width:10%">2</td>
                    </tr>
                    <tr>
                        <td>침낭 팔렸나요??</td>
                        <td>나가기</td>
                    </tr>
                </div>
            </table>
            <table class="chat">
                <div class="chatroom">
                    <tr>
                        <td rowspan="2" style="width:20%"><img src="image/user.png" width="30%"></td>
                        <td>냔냐냠</td>
                        <td style="width:10%">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>냔냐냐냔냐냐냔</td>
                        <td>나가기</td>
                    </tr>
                </div>
            </table>
            <table class="chat">
                <div class="chatroom">
                    <tr>
                        <td rowspan="2" style="width:20%"><img src="image/user.png" width="30%"></td>
                        <td>침낭삽니다</td>
                        <td style="width:10%">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>네~!^^</td>
                        <td>나가기</td>
                    </tr>
                </div>
            </table>
        </div>
    </div>
</template>
<script>

    export default{
    data(){
    return{
        dealComplete: true,
        prodImg: img1, 
        used_name: '4인용 텐트',
        used_content: '상태좋습니다',
        sender_msg: '안녕하세요 텐트 팔렸나요?',
        receiver_msg: '아직안팔렸어용^^'
    }
    },
    methods:{
        dealComplete: function(){
            this.dealComplete = !this.dealComplete;
        }
    }
    }
</script>
<style>
                 *{
            margin:0;
            padding:0;
            box-sizing: border-box;
        }
        #modal{
            border-radius: 0.5em;
            background-color: #41b56c;
            /* color: white; */
            padding: 1em;
            height: 100%;
        }
        #modal h3{
            text-align: center;
            color: #fff;
            padding: 10px;
        }
        #content{
            background-color: #fff;
            border-radius: 5px;
            padding: 30px;
            height: 100%;
        }
        .chat{
            width: 100%;
            border-bottom: 1px solid #e6e6e6;
            table-layout: fixed;
        }
        .img-container{
            height:200px;
            width:200px;
            overflow: hidden;
            padding: 10px;
        }
        chat .img{
            width:100%;
            height:100%;
            flex-wrap: nowrap;
            padding: 2%;
            object-fit: fill;
        }
        .chatroom {
            width:100%;
        }
        .chatroom tr {
            width: 50%;
        }
        .chatroom th,tr,td {
            border: 1px solid black;
        }
        
</style>