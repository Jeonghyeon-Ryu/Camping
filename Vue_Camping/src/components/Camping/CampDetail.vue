<template>
    <div class="camp-detail-container">
        <div class="camp-detail-title-container">
            <input :value="camp.campName" class="camp-detail-title" readonly />
            <div class="camp-detail-title-info">
                <div class="camp-detail-review-count"><a href="#camp-detail-sns-container">후기(30)</a></div>
                <div class="camp-detail-address"><a href="#" v-text="camp.campAddress"></a></div>
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
                <button type="button" @click="saveItem()">저장하기</button>
                <button type="button" @click="getCompanion()">동행자 구하기</button>
                <button type="button" @click="modifyItem()">수정하기</button>
                <button type="button" @click="reportItem()">신고하기</button>
            </div>
        </div>
        <div id="camp-detail-sns-container" class="camp-detail-sns-container">

        </div>
    </div>
</template>

<script>
import KakaoMap from "../KakaoMap.vue";
import CampDetailImage from "./CampDetailImage.vue";
import Swal from 'sweetalert2';
export default {
    data: function () {
        return {
            isNotList: true,
            search: '대구광역시 달서구 달서대로 719',
            campId: this.$route.params.campId,
            camp: {},
        }
    },
    created: function () {
        fetch('http://localhost:8087/java/campDetail/' + this.campId)
            .then(result => result.json())
            .then(result => {
                result.campInfo = result.campInfo.split(" ");
                this.camp = result;
            })
    },
    methods: {
        // 후기 셋팅 필요
        // 주소 카카오맵 할당 필요
        // 기타 정보 할당 필요
        // 사진 Swiper 적용하기
        saveItem() {

        },
        getCompanion() {

        },
        modifyItem() {
            if (this.$store.state.email != null) {
                fetch('http://localhost:8087/java/campModify/' + this.campId)
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
        reportItem() {
            let item = Swal.fire({
                title: '신고',
                html:
                    '<select id="swal-input1" class="swal2-select">' +
                        '<option value="" disabled="">신고 분류</option>'+
                        '<option value="잘못된 정보">잘못된 정보</option>'+
                        '<option value="게시글 규정 위반">게시글 규정 위반</option>'+
                        '<option value="기타">기타</option>'+
                    '</select>'+
                    '<textarea id="swal-input2" class="swal2-textarea" style="resize:none"></textarea>',
                focusConfirm: false,
                showCancelButton: true,
                confirmButtonText:'신고',
                cancelButtonText:'취소',
                confirmButtonColor: 'rgba(6,68,32,0.8)',
                preConfirm: () => {
                    document.getElementById('swal-input1').value;
                    document.getElementById('swal-input2').value;

                    return false;
                }
            })
            console.log(item);
        }
    },
    components: { KakaoMap, CampDetailImage },
}
</script>

<style scoped src="@/assets/css/Camping/CampDetail.css">

</style>
