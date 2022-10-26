<template>
    <div class="myNoteList">
        <div class="all_container">
            <div class="black-bg" v-if="inviteCancleModalOpen == true">
                <div class="invite-cancel-white-bg">
                    <div class="modal_info">공유를 중지할 유저를 선택해주세요</div>
                    <hr>
                    <div class="block_user_container">
                        <label for="select_user">
                            <input type="checkbox" id="select_user" class="check_block_user" :value="checked">
                            {{nickName}}
                        </label>
                    </div>
                    <div class="invite_btn_container">
                        <button class="success" @click="inviteCancleModalOpen = true">초대</button>
                        <button class="modal_cancel" @click="inviteCancleModalOpen = false">ㄴㄴ</button>
                    </div>
                </div>
            </div>
            <!-- 화면 -->
            <div class="mynote_container">
                <div class="select_container">
                    <div class="fn_btn_container">
                        <div class="mynote_btn">
                            <button id="select_btn" @click="showCheckBox">선택</button>
                            <button id="delete_btn" @click="[modalOpen = true, checkedInfo($event)]">삭제</button>
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
                        <div class="card text-white bg-success mb-3" v-for="info in listInfo" :key="info.noteId">
                            <div class="card-header">
                                <div class="noteId">{{info.noteId}}</div>
                                <div class="note_state">{{info.noteState}}</div>
                                <div class="checkbox">
                                    <input type="checkbox" v-bind:id="info.noteId" name="check_one" v-if="show">
                                    <label for="select_card"></label>
                                </div>
                                <div class="header_button">
                                    <button id="delete_btn" @click="findId($event)">삭제</button>
                                    <button id="invite_btn"
                                        @click="[inviteModalOpen = true, inviteForm($event)]">초대</button>
                                    <button id="block_btn" @click="inviteCancleModalOpen == true">공유취소</button>
                                </div>
                            </div>
                            <div class="card-body" @click="goMynote($event)">
                                <div class="card-text">{{info.writeDate}}</div>
                                <hr>
                                <div class="card-title">{{info.title}}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="all_container">
                    <div class="black-bg" v-if="inviteModalOpen == true">
                        <div class="invite-white-bg">
                            <div class="modal_info">초대할 유저의 닉네임을 입력해주세요</div>
                            <hr>
                            <div class="input_container">
                                <input class="input_email" placeholder="닉네임을 입력해주세요">
                                <input class="input_email" placeholder="닉네임을 입력해주세요">
                                <input class="input_email" placeholder="닉네임을 입력해주세요">
                                <input class="input_email" placeholder="닉네임을 입력해주세요">
                                <input class="input_email" placeholder="닉네임을 입력해주세요">
                            </div>
                            <div class="invitedUser_list">
                            </div>
                            <div class="invite_btn_container">
                                <button class="success" @click="inviteUser($event)">초대</button>
                                <button class="modal_cancel" @click="inviteModalOpen = false">ㄴㄴ</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
       
<script>
import Modal from "./modal.vue"
import Swal from 'sweetalert2';

export default {
    name: "MynoteList",
    data() {
        return {
            noteId: [],
            myNoteId: this.$route.params.noteId,
            listInfo: [],
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
    created() {
        this.getMyListInfo();
    },
    methods: {
        showCheckBox(e) {
            this.show = !this.show;
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
        changeCardColor() {
            if (this.noteState === "공유중") {
                this.cardClass = "card text-white bg-dark mb-3";
            }
            else {
                this.cardClass = "card text-white bg-success mb-3";
            }
        },
        //card에 들어갈 info
        getMyListInfo() {
            const email = sessionStorage.getItem("email");
            fetch(`http://localhost:8087/java/MyNoteList/${email}`, {
                method: "GET",
                headers: { "Content-Type": "application/json" },
            })
                .then((response) => response.json())
                .then(data => {

                    this.listInfo = data;

                }).catch(err => console.log(err));
        },
        findId(e) {
            let findId = e.target.parentElement.parentElement.firstChild.innerText;
            this.noteId.push(findId);
            this.delNote();
        },
        checkedInfo(e) {
            let checkBoxes = document.querySelectorAll('input[type="checkbox"]');
            let checkedId = [];
            let checkedIds = {};
            for (let i = 0; i < checkBoxes.length; i++) {

                if (checkBoxes[i].checked == true) {
                    this.noteId.push(checkBoxes[i].id);
                }
            }
            this.delNote();
        },
        goList() {
            this.$router.push({ name: "MynoteList" });
        },
        delNote() {
            Swal.fire({
                title: '삭제하시겠습니까?',
                text: '입력한 내용이 삭제됩니다.',
                showCancelButton: true,
                confirmButtonText: '삭제',
                cancelButtonText: '취소'
            }).then((result) => {
                /* Read more about isConfirmed, isDenied below */
                if (result.isConfirmed) {
                    let noteIdObj = this.noteId;
                    let noteIds = []; //new Array();
                    for (let i = 0; i < noteIdObj.length; i++) {
                        noteIds.push(noteIdObj[i]);
                    }
                    let fetchData = {
                        "noteIds": noteIds
                    }
                    fetch(`http://localhost:8087/java/MyNoteList`, {
                        method: "DELETE",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(fetchData)
                    })
                        .then((response) => {
                            this.$router.go(0)
                        }).catch(err => {
                            console.log(err)
                        });
                }
            })
        },
        goMynote(e) {
            let target = e.target.parentElement;
            while (!target.classList.contains('card')) {
                target = target.parentElement
            }
            let noteId = target.querySelector('.noteId').innerText;
            this.myNoteId = noteId;

            this.$router.push({
                name: "MyNoteInfo",
                params: {
                    noteId: this.myNoteId
                }
            })
        },
        //초대하기
        inviteForm(e) {
            let target = e.target;
            while (!target.classList.contains('card')) {
                target = target.parentElement;
            }
            let noteId = target.querySelector('.noteId').innerText;
            this.noteId.push(noteId);
        },
        inviteUser(e) {
            //노트id가져오기
            let noteId = this.noteId[0];
            console.log(noteId);
            let userEmails = [];
            //email입력
        
            let userEmail = document.querySelectorAll('.input_email');
            for(let i=0; i<userEmail.length; i++){
                userEmails.push(userEmail[i].value);
                console.log("sssssssssssssss")
            console.log(userEmails)
            }
            
            //let inviteUserObj = this.inviteUserEmail;

            /*for (let i = 0; i < inviteUserObj.length; i++) {
                invitedUser.push(inviteUserObj[i])
            }*/
            let fetchData = {
                "noteId": noteId,
                "invitedMember": userEmails
            }
            console.log("-----------fetch")
            console.log(fetchData)
            fetch('http://localhost:8087/java/inviteUser', {
                method: 'PUT',
                //mode: 'cors',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(fetchData)
            })
                .then((response) => {
                    this.$router.go(0)
                }).catch(err => {
                    console.log(err)
                });
        },
    },
    components: { Modal }
};
</script>
    
<style scoped src="@/assets/css/note/MynoteList.css">

</style>