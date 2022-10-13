<template>
    <div class="camp-detail-container">
        <div class="camp-detail-title-container">
            <div class="camp-detail-title">캠핑가자 캠핑장</div>
            <div class="camp-detail-title-info">
                <div class="camp-detail-review-count"><a href="#camp-detail-sns-container">후기(30)</a></div>
                <div class="camp-detail-address"><a href="#">대구광역시 중구 중구</a></div>
            </div>
        </div>
        <div class="camp-detail-image-container">
            <CampDetailImage></CampDetailImage>
        </div>
        <div class="camp-detail-info-container">
            <div class="camp-detail-info-left">
                <div class="camp-detail-map">
                    <span>위치 정보</span>
                    <KakaoMap :search="search" :isNotList="isNotList"></KakaoMap>
                </div>
                <div class="camp-detail-site">
                    <span>사이트 수</span>
                    <input type="text" value="2개" disabled />
                </div>
                <div class="camp-detail-price">
                    <span>가격</span>
                    <input type="text" value="가격정보 없음" disabled />
                </div>
                <div class="camp-detail-info">
                    <span>정보</span>
                    <div class="camp-detail-info-buttons">
                        <div class="row">
                            <label>
                                <input type="checkbox" name="toilet" />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/toilet.png" />
                                    <span>화장실</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="parking" />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/parking.png">
                                    <span>주차장</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="shower" />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/shower.png" />
                                    <span>샤워장</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="disposal" />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/disposal.png" />
                                    <span>쓰레기장</span>
                                </div>
                            </label>

                            <label>
                                <input type="checkbox" name="deck" />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/deck.png" />
                                    <span>데크</span>
                                </div>
                            </label>

                            <label>
                                <input type="checkbox" name="bbq" />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/bbq.png" />
                                    <span>바비큐</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="pool" />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/swim.png" />
                                    <span>수영장</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="cooking" />
                                <div class="icon-box">
                                    <img src="@/assets/img/Camping/spoon.png" />
                                    <span>조리도구</span>
                                </div>
                            </label>
                            <label>
                                <input type="checkbox" name="lease" />
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
export default {
    data : function() { 
        return {
            isNotList : true,
            search : '대구광역시 달서구 달서대로 719'
        }
    },
    components: {
    KakaoMap,
    CampDetailImage
},
    methods :{
        // 후기 셋팅 필요
        // 주소 카카오맵 할당 필요
        // 기타 정보 할당 필요
        // 사진 Swiper 적용하기
        saveItem() {

        },
        getCompanion() {
            
        },
        modifyItem() {

        },
        reportItem() {

        }
    }
}
</script>

<style scoped src="@/assets/css/Camping/CampDetail.css">

</style>
