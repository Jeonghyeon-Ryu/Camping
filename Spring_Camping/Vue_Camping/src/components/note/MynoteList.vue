<template>
    <div class="myNoteList">
        <div class="all_container">
            <!-- 화면 -->
            <div class="mynote_container">
                <div class="select_container">
                    <div class="fn_btn_container">
                        <div class="mynote_btn">
                            <button id="select_btn" @click="showCheckBox">선택</button>
                            <button id="delete_btn" @click="[modalOpen = true, checkedInfo($event)]">삭제</button>
                        </div>
                    </div>
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="card_container" v-for="info in listInfo" :key="info.noteId">
                            <div class="card_header">
                                <div class="noteId" style="opacity : 0">{{ info.noteId }}</div>
                                <div class="note_state">{{ info.noteState }}</div>
                                <div class="checkbox">
                                    <input type="checkbox" v-bind:id="info.noteId" name="check_one" v-if="show" class="check_note">
                                    <label for="select_card"></label>
                                </div>
                                <div class="header_button">
                                    <button id="delete_btn" @click="findId($event)">삭제</button>
                                    <button id="invite_btn"
                                        @click="[inviteModalOpen = true, inviteForm($event)]">초대</button>
                                    <button id="block_btn"
                                        @click="[inviteCancleModalOpen = true, inviteCancleForm($event)]">공유취소</button>
                                </div>
                            </div>
                            <div class="card_body" @click="goMynote($event)">
                                <div class="card-text">{{ info.writeDate }}</div>
                                <hr>
                                <div class="card-title">{{ info.title }}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="black-bg" v-if="inviteModalOpen == true">
                    <div class="invite-white-bg">
                        <div class="modal_info">초대할 유저의 메일(id)을 입력해주세요</div>
                        <hr>
                        <div class="input_container">
                            <div class="input_box">
                                <input class="input_email" placeholder="메일(id)을 입력해주세요">
                                <button class="add_inviteMember" @click="addInviteMember($event)">추가</button>
                                <button class="add_inviteMember" @click="delInviteMember($event)">삭제</button>
                            </div>
                        </div>
                        <div class="invite_btn_container">
                            <button class="success" @click="inviteUser($event)">초대</button>
                            <button class="modal_cancel" @click="inviteModalOpen = false">취소</button>
                        </div>
                    </div>
                </div>
                <div class="black-bg" v-if="inviteCancleModalOpen == true">
                    <div class="invite-cancel-white-bg">
                        <div class="modal_info">공유를 중지할 유저를 선택해주세요</div>
                        <hr>
                        <div class="block_user_container">
                            <div class="invited_list_info" v-for="(member, i) in InvitedmemberList" :key="i">
                                <input type="checkbox" class="check_block_user">
                                <div class="invitedMemberInfo">{{ member }} </div>
                            </div>
                        </div>
                        <div class="invite_btn_container">
                            <button class="success" @click="[blockMember($event), delMemberList($event)]">공유끊기</button>
                            <button class="modal_cancel"
                                @click="[inviteCancleModalOpen = false, delMemberList($event)]">취소</button>
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
            oneNoteId: '',
            noteId: [],
            blockMeber: [],
            myNoteId: this.$route.params.noteId,
            status: '',
            listInfo: [],
            InvitedmemberList: [],
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
        
        //card에 들어갈 info
        getMyListInfo() {
            const email = this.$store.state.email;
            fetch(`http://13.125.95.210:85/java/MyNoteList/${email}`, {
                method: "GET",
                headers: { "Content-Type": "application/json" },
            })
                .then((response) => response.json())
                .then(data => {
                    this.listInfo = data; 
                    console.log(this.status);
                    console.log(this.listInfo);
                }).catch(err => console.log(err));
        },
        findId(e) {
            let findId = e.target.parentElement.parentElement.firstChild.innerText;
            this.noteId.push(findId);
            this.delNote();
        },
        checkedInfo(e) {
            let checkBoxes = document.querySelectorAll('input[type="checkbox"]');
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
                    fetch(`http://13.125.95.210:85/java/MyNoteList`, {
                        method: "DELETE",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(fetchData)
                    })
                        .then((response) => {
                            this.$router.go(0);
                        }).catch(err => {
                            console.log(err)
                        });
                }
            })
        },
        goMynote(e) {
            let target = e.target.parentElement;
            while (!target.classList.contains('card_container')) {
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
        //초대하기(폼열기)
        inviteForm(e) {
            let target = e.target;
            while (!target.classList.contains('card_container')) {
                target = target.parentElement;
            }
            let noteId = target.querySelector('.noteId').innerText;
            this.noteId.push(noteId);
        },
        //초대하기(유저초대)
        inviteUser(e) {
            //노트id가져오기
            let noteId = this.noteId[0];
            let userEmails = [];
            //email입력
            let userEmail = document.querySelectorAll('.input_email');

            for (let i = 0; i < userEmail.length; i++) {
                if (userEmail[i] != null) {
                    userEmails.push(',' + userEmail[i].value + ',');
                }
                console.log("userEmail");
                console.log(userEmail[i].value)
            }
            let fetchData = {
                "noteId": noteId,
                "invitedMember": userEmails
            }
            fetch('http://13.125.95.210:85/java/inviteUser', {
                method: 'PUT',
                //mode: 'cors',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(fetchData)
            })
                .then((response) => {
                    Swal.fire({
                        title: '초대완료!',
                        text: '초대받은 유저는 내용을 수정할 수 있습니다!!',
                        cancelButtonText: '돌아가기'
                    })
                    this.inviteModalOpen = false;
                }).catch(err => {
                    console.log(err)
                });
        },
        //초대하기(초대인원추가)
        addInviteMember(e) {
            let inputContainer = e.target.parentElement.parentElement;
            console.log(inputContainer);
            inputContainer.setAttribute('style', 'display:flex; flex-direction:column;')
            let inputEmail = document.createElement('input');
            inputEmail.setAttribute('class', 'input_email');
            inputEmail.setAttribute('style', 'width: 450px; margin: 3% 0 0 12.5%; border:none; height: 50px; outline: none !important; text-align: center; box-shadow: 0 0 10px #26ce42; font-size: 20px; color:black;')

            inputContainer.append(inputEmail);
        },
        delInviteMember(e) {
            let parent = e.target.parentElement.parentElement;
            let delList = parent.querySelectorAll('.input_email');
            console.log(parent.lastChild);
            if (delList.length > 1) {
                parent.lastChild.remove();
            }
            console.log(delList);
        },
        //폼 닫기
        inviteCancleForm(e) {
            let target = e.target;
            while (!target.classList.contains('card_container')) {
                target = target.parentElement;
            }
            let noteId = target.querySelector('.noteId').innerText;
            this.oneNoteId = noteId;
            fetch(`http://13.125.95.210:85/java/showBlockMember/${noteId}`, {
                method: "GET",
                headers: { "Content-Type": "application/json" },
            })
            .then((response) => response.text())
            .then(data => {
                let invitedMember = [];
                invitedMember = data.split(',')
                invitedMember = invitedMember.filter((element) => {
                    return element !== '' && element !== null;
                });
                for (let i = 0; i < invitedMember.length; i++) {
                    this.InvitedmemberList.push(invitedMember[i]);
                }
            }).catch(err => console.log(err));
        },
        //공유 끊기
        blockMember(e) {
            let noteId = this.oneNoteId;

            let checkBoxes = document.querySelectorAll('input[type="checkbox"]');

            for (let i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i].checked == true) {
                    //  console.log(document.querySelectorAll('.invitedMemberInfo'))
                    let blockMember = document.querySelectorAll('.invitedMemberInfo')[i].innerHTML;
                    this.blockMeber.push(blockMember);
                }
            }
            let fetchData = {
                "invitedMember": this.blockMeber,
                "noteId": noteId
            }
            console.log("fetchData")
            console.log(fetchData);


            fetch(`http://13.125.95.210:85/java/blockMember`, {
                method: "DELETE",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(fetchData)
            })
                .then((response) => {
                    Swal.fire({
                        title: '성공!',
                        cancelButtonText: '돌아가기'
                    })
                    let removeTarget = '';
                    let parent = '';
                    let listAll = document.querySelectorAll('.invitedMemberInfo');

                    if (response.isConfirmed) {
                        for (let i = 0; i < listAll.length; i++) {
                            if (listAll[i].innerHTML == null) {
                                removeTarget = listAll[i].parentElement;

                                parent = removeTarget.parentElement;

                                parent.removeChild(removeTarget);
                            }
                        }

                    }
                    this.inviteCancleModalOpen = false

                }).catch(err => {
                    console.log(err)
                });
        },
        delMemberList(e) {
            this.InvitedmemberList = [];
        }
    },
    components: { Modal }
};
</script>
    
<style scoped src="@/assets/css/note/MynoteList.css">

</style>