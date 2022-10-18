<template>
    <div class="all_container">  
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
       
       <style scoped src="@/assets/css/note/InvitedList.css">
        
       </style>