<template>
    <div class="all_container">
        <!-- 짭 모달 -->
        <div class="black-bg" v-if="modalOpen == true">
            <div class="white-bg">
                <div class="modal_text">삭제 하시겠습니까?</div>
                <hr>
                <div class="modal_btn_container">
                    <button class="modal_delbtn" >ㅇㅋ</button>
                    <button class="modal_cancel" @click="modalOpen = false">ㄴㄴ</button>
                </div>
            </div>
        </div>
        <div class="black-bg" v-if="inviteModalOpen == true">
            <div class="invite-white-bg">
                <div class="modal_info">초대할 유저의 닉네임을 입력해주세요</div>
                <hr>
                <div class="input_container">      
                    <input class="input" placeholder="닉네임을 입력해주세요">
                </div>
                <div class="invite_btn_container">
                    <button class="success" >초대</button>
                    <button class="modal_cancel" @click="inviteModalOpen = false">ㄴㄴ</button>
                </div>
            </div>
        </div>
        <div class="black-bg" v-if="inviteCancleModalOpen == true">
            <div class="invite-cancel-white-bg">
                <div class="modal_info">공유를 중지할 유저를 선택해주세요</div>
                <hr>
                <div class="block_user_container" >      
                    <label for="select_user">
                    <input type="checkbox" id="select_user" class="check_block_user" :value="checked">
                    {{nickName}}
                    </label>
                </div>
                <div class="invite_btn_container">
                    <button class="success" >초대</button>
                    <button class="modal_cancel" @click="inviteCancleModalOpen = false">ㄴㄴ</button>
                </div>
            </div>
        </div>
        <!-- 화면 -->
        <div class="mynote_container">
            <nav class="mynote_menu">
               <div class="use_method">이용 방법</div>
               <div class="note_mine">내가 작성한 노트</div>
               <div class="invited_note">내가 초대받은 노트</div>
               <div class="write_note">노트 작성하기</div>
           </nav>
           <div class="select_container">
               <div class="fn_btn_container">
                   <div class="mynote_btn">
                       <button id="select_btn" @click="showCheckBox">선택</button>
                       <button id="delete_btn" @click="modalOpen = true">삭제</button>
                   </div>   
               </div>
               <div class="select_note">
                   <select class="select">
                       <option value="orderby_current">최신순</option>
                       <option value="orderby_order">오래된 순</option>
                       <option vlaue="orderby_shared">공유중</option>
                   </select> 
               </div>
           </div>
           <div class="container-fluid">    
               <div class="row">            
                    <div :class=cardClass[i-1] v-for="i in 5" :key="i">          
                        <div class="card-header" >
                            <div class="note_state">{{noteState}}</div>
                            <div class="noteId">{{noteId[i-1]}}</div>
                            <div class="checkbox">
                                <input type="checkbox" name="check_one" v-if="show">
                                <label for="select_card"></label>
                            </div>
                            <div class="header_button">
                                <button id="delete_btn"  @click="modalOpen = true">삭제</button>
                                <button id="invite_btn"  @click="inviteModalOpen = true">초대</button>
                                <button id="block_btn"  @click="inviteCancleModalOpen = true">공유취소</button>
                            </div>
                        </div>          
                        <div class="card-body">         
                            <div class="card-text">{{writeDate}}</div>
                            <hr>
                            <div class="card-title">{{title}}</div>                    
                        </div>        
                    </div>              
                    
               </div>      
           </div>
       </div>
    </div>    
    </template>
       
    <script>
    import "bootstrap"
    import "bootstrap/dist/css/bootstrap.min.css"
   
    
    export default{
        name: "MynoteListTest",
        data() {
            return {
    
                noteId: [1,2,3,4,5],
                writeDate: "2022-10-10",
                noteState: "공유중",
                title: "포항 도구해수욕장 캠크닉",
                nickName: ['치킨','참치','고구마','피자'],
                
                cardClass: [
                    "card text-white bg-secondary mb-3",
                    "card text-white bg-success mb-3",
                    "card text-white bg-dark mb-3",
                    "card text-dark bg-info mb-3",
                    "card text-white bg-danger mb-3"
                ],
                /*체크박스 상태 */
                show: false,
                /*모달창 상태*/
                modalOpen: false,
                inviteModalOpen: false,
                inviteCancleModalOpen: false,
            };
        },
       
        methods: {
            showCheckBox(e) {
                this.show = !this.show;
                console.log(this.show);
            },
            deleteNote() {
                this.visible = !this.visible;
            },
            inviteNote() {
                this.visible = !this.visible;
            },
            deleteInvite() {
                this.visible = !this.visible;
            },
            changeCardColor(){ 
                if(this.noteState === "공유중"){
                    this.cardClass = "card text-white bg-dark mb-3";
                } else {
                    this.cardClass = "card text-white bg-success mb-3";
                }     
            },
        },
    };
    </script>
       
    <style scoped>
        /*버튼임*/
        @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
    
        :root {
        --button-color: rgba(228,239,231,0.7);
        --button-bg-color: #0d6efd;
        --button-hover-bg-color: #025ce2;
        }
    
        button {
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        
        background: var(--button-bg-color);
        color: var(--button-color);
        
        margin: 0;
        padding: 0.5rem 1rem;
        
        font-family: 'Noto Sans KR', sans-serif;
        font-size: 1rem;
        font-weight: 400;
        text-align: center;
        text-decoration: none;
        
        border: none;
        border-radius: 4px;
        
        display: inline-block;
        width: auto;
        
        box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
        
        cursor: pointer;
        
        transition: 0.5s;
        }
    
        button.success {
        --button-bg-color: #28a745;
        --button-hover-bg-color: #218838;
        }
        button.modal_delbtn {
        --button-bg-color: #c72939;
        --button-hover-bg-color: #e04958;
        }
        button.warning {
        --button-color: #212529;
        --button-bg-color: #ffc107;
        --button-hover-bg-color: #e0a800;
        }
    
        button:active,
        button:hover,
        button:focus {
        background: var(--button-hover-bg-color);
        outline: 0;
        }
        button:disabled {
        opacity: 0.5;
        }
        /* 모달임 */
        .black-bg { 
            width : 100%;
            height: 100%;
            background-color: rgba(230, 225, 225, 0.905);
            position: fixed;
            padding: 20px;
            z-index: 100;
            align-items: center;
            border : 1px solid rgb(59, 155, 59);
        }
        .white-bg{ 
            width: 400px;
            height: 200px;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            margin: 15% 30% 15% 40%;  
        }
        .modal_text { 
            margin: 20px 0 20px 0;
            font-size: 20px;
            font-weight: bold;
        }
        .modal_btn_container{
            margin-top: 30px;
            align-items: center;
        }
        .modal_btn_container>button{ 
            margin-right: 20px;
        }
        .modal_delbtn{ 
            color: #fff;
        }
        /* 초대모달임 */
       .invite-white-bg{ 
            width: 500px;
            height: 250px;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            margin: 10% 30% 15% 40%; 
       }
        .modal_info{ 
            font-size: 25px;
            font-weight: bolder;
            margin: 10px 0 15px 0;
        }
          .input {
            width: 400px;
            margin: 30px 0;
            font-size: 20px;
            color: black;
          }
          .input:focus {
            animation-name: border-focus;
            animation-duration: 1s;
            animation-fill-mode: forwards;
          }
          @keyframes border-focus {
            from {
              border-radius: 0;
            }
            to {
              border-radius: 15px;
            }
          }
        .invite_btn_container{ 
            margin-top : 15px;
        }
        .invite_btn_container>button{ 
            margin-right: 10px;
        }
        /* 공유끊기 모달임*/
        .invite-cancel-white-bg{ 
            width: 35%;
            height: fit-content;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            margin: 5% 10% 10% 30%; 
        }
        .block_user_container{ 
            margin-top: 10px;
            
        }
    
        .textbox input[type="text"],
        .textbox input[type="password"] {
            width: 100%;  /* 원하는 너비 설정 */ 
            height: auto;  /* 높이값 초기화 */
            line-height : normal;  /* line-height 초기화 */
            padding: .8em .5em; /* 원하는 여백 설정, 상하단 여백으로 높이를 조절 */
            font-family: inherit;  /* 폰트 상속 */
            border: 1px solid #999;
            border-radius: 0;  /* iSO 둥근모서리 제거 */
            outline-style: none;  /* 포커스시 발생하는 효과 제거를 원한다면 */
            -webkit-appearance: none;  /* 브라우저별 기본 스타일링 제거 */
            -moz-appearance: none;
            appearance: none;
        }
    
        
        * {
            margin: 0;
            padding: 0;
            list-style: none;
            font-style: none;
            box-sizing: border-box;
        }
        .row{ 
            width: 100%;
        }
        /* page_section*/
        .mynote_container {
            width: 100%;
            min-width: 650px;
            margin-top: 150px;
            position: relative;
        }
        .mynote_menu {
            width: 100%;
            border: 1px solid #ddd;
            padding: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            min-width: 200px;
        }
        .mynote_menu>div {
            width: 20%;
            border: 1px solid lightgray;
            padding: 10px;
            margin: 15px;
            text-align: center;
            cursor: pointer;
        }
        .mynote_menu>div:hover {
            background-color: rgba(228, 239, 231, 0.7);
        }
        .howtouse_region {
            margin-top: 5px;
            width: 100%;
            border: 1px solid lightgray;
        }
        .mynote_list {
            display: flex;
            flex-direction: row;
        }
        .mynote_list>div {
            padding: 5px 0px 5px 0px;
        }
        .mynote_region {
            margin-top: 5px;
            padding: 10px;
            width: 100%;
            height: 600px;
            vertical-align: left;
            display: flex;
            flex-direction: row; 
        }
        .select_note{
            margin: 20px 0 10px 88%; 
            width: 140px;
            height: 35px;
            border-radius: 4px;
            border: 2px solid rgb(6,68,32);
    
        }
        .select_note .select{ 
            width: inherit;
            height: inherit;
            background: transparent;
            border: 0 none;
            outline: 0 none;
            position: relative;
            padding: 0 5px; 
        }
        .select_note .select option {
            background: rgb(6,68,32);
            color: #fff;
            padding: 3px 0;
            font-size: 16px;
        }
    
        /* card_section css */ 
        .column{    
            padding-top: 10px;    
            padding-bottom: 10px;
            margin: 0 20px 0 0;
            width: 440px;  
        }  
        .container-fluid{    
            margin-top: 20px;
        
        }  
        .container-fluid .card{    
            margin-left:auto;    
            margin-right:auto;
            margin-bottom: 50px; 
            width: 30%;
            height: 200px;
            cursor: pointer;  
        }
        input { 
        background-color: transparent;
        border: none;
        color: white;
        font-weight: bold;
        font-size: 20px;
        }
    
        /* 이동 타켓 */  
        .card-placeholder {    
            border: 1px dotted black;    
            margin: 0 1em 1em 0;    
            height: 50px;    
            margin-left:auto;    
            margin-right:auto;    
            /* 선택한 카드 이동할 때 원래 자리 표시 */    
            background-color: skyblue;  
        }  
        /* 마우스 포인터을 손가락으로 변경 */  
        .card:not(.no-move) .card-header{    
            cursor: pointer;  
        }
        .card-header{ 
            display : flex;
            position: relative;
            padding: 20px 0 20px 0; 
        }
        .header_button{ 
        position : absolute;
        right: 45px;
        top : 15px; 
        }
        
        .checkbox {
            position: absolute;
            right: 10px;
            transform: scale(2);
        }
        .select_container{ 
            position: relative;
        }
        .card-body{ 
        padding: 20px 0 20px 0;
        text-align: justify;
        }
        
        .card-title{ 
        margin-top: 10px;
        }
        /* fn_btn */
        .mynote_btn{ 
            padding : 5px 0 5px 0;
            justify-content: right;
            position: absolute;
            right: 230px;          
        }
    
        .header_button>button{ 
            /*opacity: 0;*/
            transition-duration: 0.5s;
            margin-left: 5px;
            width: fit-content;
        }
        .mynote_btn>button{
            margin-right: 10px;
        }
        .btn_active {
            opacity: 1 !important;
        }
       </style>