<template>
    <div class="camp-detail-container">
        <div class="camp-detail-title-container">
            <input :value="camp.campName" class="camp-detail-title" readonly />
            <div class="camp-detail-title-info">
                <div class="camp-detail-review-count"><a href="#camp-detail-sns-container">후기({{ snsImgs.length }})</a>
                </div>
                <div class="camp-detail-address"><a v-text="camp.campAddress"></a></div>
            </div>
        </div>
        <div class="camp-detail-image-container">
            <CampDetailImage :campId="campId"></CampDetailImage>
        </div>
        <div class="camp-detail-info-container">
            <div class="camp-detail-info-left">
                <div class="camp-detail-map">
                    <span>위치 정보</span>
                    <KakaoMap :search="camp.campAddress" :isNotList="isNotList"></KakaoMap>
                </div>
                <div class="camp-detail-site">
                    <span>사이트 수</span>
                    <input type="text" :value="camp.campSite" readonly />
                </div>
                <div class="camp-detail-price">
                    <span>가격</span>
                    <input type="text" :value="camp.campPrice" readonly />
                </div>
                <div class="camp-detail-info">
                    <span>정보</span>
                    <div class="camp-detail-info-buttons">
                        <div class="row">
                            <label>
                                <input type="checkbox" name="toilet" value="toilet" v-model="camp.campInfo" disabled />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/toilet.png" />
                                    <span>화장실</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="parking" value="parking" v-model="camp.campInfo"
                                    disabled />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/parking.png">
                                    <span>주차장</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="shower" value="shower" v-model="camp.campInfo" disabled />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/shower.png" />
                                    <span>샤워장</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="disposal" value="disposal" v-model="camp.campInfo"
                                    disabled />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/disposal.png" />
                                    <span>쓰레기장</span>
                                </div>
                            </label>

                            <label>
                                <input type="checkbox" name="deck" value="deck" v-model="camp.campInfo" disabled />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/deck.png" />
                                    <span>데크</span>
                                </div>
                            </label>

                            <label>
                                <input type="checkbox" name="bbq" value="bbq" v-model="camp.campInfo" disabled />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/bbq.png" />
                                    <span>바비큐</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="swim" value="swim" v-model="camp.campInfo" disabled />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/swim.png" />
                                    <span>수영장</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="spoon" value="spoon" v-model="camp.campInfo" disabled />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/spoon.png" />
                                    <span>조리도구</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="lease" value="lease" v-model="camp.campInfo" disabled />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/lease.png" />
                                    <span>장비대여</span>
                                </div>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="camp-detail-info-right">
                <button v-if="!isSaved" type="button" @click="saveItem($event)">저장하기</button>
                <button v-if="isSaved" type="button" @click="noSaveItem($event)"
                    style="background:lavenderblush">저장취소</button>
                <button v-if="$store.state.auth != 0" type="button" @click="getCompanion()">동행자 구하기</button>
                <button v-if="$store.state.auth != 0" type="button" @click="modifyItemByUser()">수정신청</button>
                <button v-if="$store.state.auth == 0" type="button" @click="modifyItemByAdmin()">수정하기</button>
                <button v-if="$store.state.auth != 0" type="button" @click="reportItem()">신고하기</button>
            </div>
        </div>
        <div class="camp-detail-sns-container-title">후기</div>
        <div v-if="snsImgs.length > 0" id="camp-detail-sns-container" class="camp-detail-sns-container">
            <CampSns :snsImgs="snsImgs"></CampSns>
        </div>
    </div>
</template>

<script>
import KakaoMap from "../KakaoMap.vue";
import CampDetailImage from "./CampDetailImage.vue";
import Swal from 'sweetalert2';
import CampSns from "./CampSns.vue";
export default {
    data: function () {
        return {
            isNotList: true,
            search: '대구광역시 달서구 달서대로 719',
            campId: this.$route.params.campId,
            camp: {},
            isSaved: false,
            snsImgs: [],
            page: 1,
        }
    },
    created: function () {
        fetch('http://13.125.95.210:85/java/campDetail/' + this.campId)
            .then(result => result.json())
            .then(result => {
                result.campInfo = result.campInfo.split(" ");
                this.camp = result;
                fetch("http://13.125.95.210:85/java/hashtagList/" + this.camp.campName.substring(0,this.camp.campName.length-4) + "/" + this.page)
                    .then(result => result.json())
                    .then(result => {
                        this.snsImgs = result;
                    })
                    .catch(err => console.log(err));
            }).catch(err => console.log(err));
        if (this.$store.state.email != null) {
            fetch('http://13.125.95.210:85/java/save?boardId=' + this.campId + '&email=' + this.$store.state.email)
                .then(result => result.text())
                .then(result => {
                    if (result == 'true') {
                        this.isSaved = true;
                    }
                })
                .catch(err => console.log(err));
        }

    },
    methods: {
        // 후기 셋팅 필요
        // 주소 카카오맵 할당 필요
        // 기타 정보 할당 필요
        // 사진 Swiper 적용하기
        saveItem(e) {
            let save = {
                boardId: this.campId,
                boardDivision: 0,
                email: this.$store.state.email
            }
            if (this.$store.state.email != null) {
                fetch('http://13.125.95.210:85/java/save', {
                    method: 'POST',
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(save)
                }).then(result => result.text())
                    .then(result => {
                        if (result == 'true') {
                            this.isSaved = true;
                        }
                    })
                    .catch(err => console.log(err));
            }
        },
        noSaveItem(e) {
            let save = {
                'boardId': this.campId,
                'boardDivision': 0,
                'email': this.$store.state.email
            }
            fetch('http://13.125.95.210:85/java/save', {
                method: 'DELETE',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(save)
            }).then(result => result.text())
                .then(result => {
                    if (result == 'true') {
                        this.isSaved = false;
                    }
                })
                .catch(err => console.log(err));
        },
        getCompanion() {
            this.$router.push({name : 'RecruInsertByCamp', params: {campAddress:this.camp.campAddress, campName:this.camp.campName }})
        },
        modifyItemByUser() {
            if (this.$store.state.email != null) {
                fetch('http://13.125.95.210:85/java/campModify/' + this.campId)
                    .then(result => result.text())
                    .then(result => {
                        if (result == 'true')    // 수정중인게 있을때
                            Swal.fire({
                                icon: 'warning',
                                title: '수정중인 신청내역이 있습니다.',
                                text: '해당 캠핑장의 수정 신청이 검토중에 있습니다.',
                                toast: true,
                                showConfirmButton: false,
                                timer: 1500,
                                timerProgressBar: true,
                                didOpen: (toast) => {
                                    toast.addEventListener('mouseenter', Swal.stopTimer)
                                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                                }
                            })
                        else {  // 수정중인게 없을때
                            this.$router.push({ name: "CampModify", params: { campId: this.campId } });
                        }
                    })

            } else {
                Swal.fire({
                    icon: 'warning',
                    title: '로그인을 해주세요',
                    toast: true,
                    showConfirmButton: false,
                    timer: 1500,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                        this.$router.push({ name: "LoginSignup" });
                    }
                })
            }
        },
        modifyItemByAdmin() {
            fetch('http://13.125.95.210:85/java/campModify/' + this.campId)
                .then(result => result.text())
                .then(result => {
                    if (result == 'true')    // 수정중인게 있을때
                        Swal.fire({
                            icon: 'warning',
                            title: '수정중인 신청내역이 있습니다.',
                            text: '해당 캠핑장의 수정 신청이 검토중에 있습니다.',
                            toast: true,
                            showConfirmButton: false,
                            timer: 1500,
                            timerProgressBar: true,
                            didOpen: (toast) => {
                                toast.addEventListener('mouseenter', Swal.stopTimer)
                                toast.addEventListener('mouseleave', Swal.resumeTimer)
                            }
                        })
                    this.$router.push({ name: "CampModify", params: { campId: this.campId } });
                })
        },
        reportItem() {
            let item = Swal.fire({
                title: '신고',
                html:
                    '<select id="swal-input1" class="swal2-select" style="font-size:13px;">' +
                    '<option value="" disabled="">신고 분류</option>' +
                    '<option value="잘못된 정보">잘못된 정보</option>' +
                    '<option value="게시글 규정 위반">게시글 규정 위반</option>' +
                    '<option value="기타">기타</option>' +
                    '</select>' +
                    '<textarea id="swal-input2" class="swal2-textarea" style="resize:none; width:80%; font-size:12px;" maxlength="450" placeholder="신고 사유를 입력하세요"></textarea>',
                focusConfirm: false,
                showCancelButton: true,
                confirmButtonText: '신고',
                cancelButtonText: '취소',
                confirmButtonColor: 'rgba(6,68,32,0.8)',
                preConfirm: () => {
                    let fetchData = {
                        "boardId": this.campId,
                        "boardDivision": 0,
                        "reportDivision": document.getElementById('swal-input1').value,
                        "reportContent": document.getElementById('swal-input2').value,
                        "email": this.$store.state.email
                    }

                    console.log(fetchData);
                    fetch('http://13.125.95.210:85/java/report', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(fetchData)
                    }).then(result => result.text())
                        .then(result => {
                            if (result == "true") {
                                Swal.fire({
                                    icon: 'success',
                                    title: '신고 완료 !',
                                    toast: true,
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                        this.$router.push({ path: '/CampModify/' + this.campId, });
                                    }
                                })
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: '신고 실패 !',
                                    text: '계속 실패하면 고객센터에 문의해주세요.',
                                    toast: true,
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                    }
                                })
                            }
                            console.log(result);
                        })
                    return false;
                }
            })
            console.log(item);
        },
    },
    components: { KakaoMap, CampDetailImage, CampSns },
}
</script>

<style scoped src="@/assets/css/Camping/CampDetail.css">

</style>
