<template>
    <form class="camp-detail-container" onsubmit="return false">
        <div class="camp-detail-title-container">
            <input :value="camp.campName" class="camp-detail-title" readonly />
            <div class="camp-detail-title-info">
                <div class="camp-detail-review-count"><a href="#camp-detail-sns-container">후기(30)</a></div>
                <div class="camp-detail-address"><a href="#" v-text="camp.campAddress"></a></div>
            </div>
        </div>
        <div class="camp-detail-image-container">
            <CampDetailImage :campId="campId" :addImage="images"></CampDetailImage>
            <label>사진등록
                <input @change="changeImage($event)" type="file" name="files" multiple style="display:none;">
            </label>
        </div>
        <div class="camp-detail-info-container">
            <div class="camp-detail-info-left">
                <input type="text" name="campId" :value="campId" style="display:none;" />
                <div class="camp-detail-map">
                    <span>위치 정보</span>
                    <div class="prev" v-text="camp.campAddress"></div>
                    <input type="text" name="campAddress" placeholder="수정할 주소를 입력해주세요." />
                    <KakaoMap :search="camp.campAddress" :isNotList="isNotList"></KakaoMap>
                </div>
                <div class="camp-detail-site">
                    <span>사이트 수</span>
                    <div class="prev" v-text="camp.campSite"></div>
                    <input type="text" name="campSite" placeholder="수정할 사이트 수를 입력해주세요." />
                </div>
                <div class="camp-detail-price">
                    <span>가격</span>
                    <div class="prev" v-text="camp.campPrice"></div>
                    <input type="text" name="campPrice" placeholder="수정할 가격을 입력해주세요." />
                </div>
                <div class="camp-detail-info">
                    <span>정보</span>
                    <div class="camp-detail-info-buttons">
                        <div class="row">
                            <label class="checkboxLabel">
                                <input type="checkbox" name="campInfo" value="toilet" v-model="camp.campInfo" />
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/toilet.png" />
                                    <span>화장실</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="campInfo" value="parking" v-model="camp.campInfo" />
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/parking.png">
                                    <span>주차장</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="campInfo" value="shower" v-model="camp.campInfo" />
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/shower.png" />
                                    <span>샤워장</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="campInfo" value="disposal" v-model="camp.campInfo" />
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/disposal.png" />
                                    <span>쓰레기장</span>
                                </div>
                            </label>

                            <label class="checkboxLabel">
                                <input type="checkbox" name="campInfo" value="deck" v-model="camp.campInfo" />
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/deck.png" />
                                    <span>데크</span>
                                </div>
                            </label>

                            <label class="checkboxLabel">
                                <input type="checkbox" name="campInfo" value="bbq" v-model="camp.campInfo" />
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/bbq.png" />
                                    <span>바비큐</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="campInfo" value="swim" v-model="camp.campInfo" />
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/swim.png" />
                                    <span>수영장</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="campInfo" value="spoon" v-model="camp.campInfo" />
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/spoon.png" />
                                    <span>조리도구</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="campInfo" value="lease" v-model="camp.campInfo" />
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/lease.png" />
                                    <span>장비대여</span>
                                </div>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="camp-detail-info-right">
                <RButton :inputColor="'lightGreen'" :inputSize="'md'" :inputValue="'수정신청'" @clickBtn="confirm($event)">
                </RButton>
                <RButton :inputColor="'cream'" :inputSize="'md'" :inputValue="'취소'" @clickBtn="cancel($event)">
                </RButton>
            </div>
        </div>
        <div id="camp-detail-sns-container" class="camp-detail-sns-container">

        </div>
    </form>
</template>

<script>
import KakaoMap from "../KakaoMap.vue";
import CampDetailImage from "./CampDetailImage.vue";
import RButton from "../Admin/RButton.vue";
import Swal from 'sweetalert2';

export default {
    data: function () {
        return {
            isNotList: true,
            search: '대구광역시 달서구 달서대로 719',
            campId: this.$route.params.campId,
            camp: [],
            images: []
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
    components: { KakaoMap, CampDetailImage, RButton },
    methods: {
        clickCheckBox(e) {
            e.preventDefault();
            let checkItem = e.target.parentElement;
            while (!checkItem.classList.contains('checkboxLabel')) {
                checkItem = checkItem.parentElement;
            }
            checkItem = checkItem.querySelector('input');
            if (checkItem.checked) {
                checkItem.checked = false;
            } else {
                checkItem.checked = true;
            }
        },
        changeImage(e) {
            let dt = new DataTransfer();
            for (let i = 0; i < e.target.files.length; i++) {
                dt.items.add(e.target.files[i]);
            }
            this.images = dt.files;
            e.target.files = dt.files;
        },
        confirm(e) {
            let fetchData = new FormData(document.querySelector('.camp-detail-container'));
            fetchData.append("email", this.$store.state.email);
            fetchData.append("campName", this.camp.campName);
            // 데이터 변경이 없는 것을 초기화 시켜줌
            let countModifyData = 0;
            fetchData.forEach((value, key) => {
                if (key == 'campAddress' && value == '') {
                    fetchData.set('campAddress', this.camp.campAddress);
                } else if (key == 'campSite' && value == '') {
                    fetchData.set('campSite', this.camp.campSite);
                } else if (key == 'campPrice' && value == '') {
                    fetchData.set('campPrice', this.camp.campPrice);
                }
            })
            Swal.fire({
                title: '수정 신청을 하시겠습니까?',
                text: '관리자의 확인 후 수정이 됩니다.',
                showCancelButton: true,
                confirmButtonText: '취소',
                cancelButtonText: '수정'
            }).then((result) => {
                /* Read more about isConfirmed, isDenied below */
                if (result.isConfirmed) {
                    Swal.fire('취소 !', '', 'success')
                } else {
                    fetch('http://localhost:8087/java/campModify', {
                        method: 'POST',
                        headers: {},
                        body: fetchData
                    }).then(result => result.text())
                        .then(result => {
                            if (result == 'true') {
                                Swal.fire({
                                    icon: 'success',
                                    title: '수정신청 완료 !',
                                    toast: true,
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                        this.$router.push({path: '/Camp/'+this.campId})
                                    }
                                })
                            }
                        });
                }
            })


        },
        cancel(e) {
            this.$router.push({ name: "CampDetail", params: { campId: this.campId } })
        }
    }
}
</script>

<style scoped src="@/assets/css/Camping/CampDetail.css">

</style>
