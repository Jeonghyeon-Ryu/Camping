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
            <form class="camp-detail-info-left">
                <div class="camp-detail-map">
                    <span>위치 정보</span>
                    <div class="prev">대구광역시 중구 중구</div>
                    <input type="text" name="camp_address" placeholder="" />
                    <KakaoMap :search="search" :isNotList="isNotList"></KakaoMap>
                </div>
                <div class="camp-detail-site">
                    <span>사이트 수</span>
                    <div class="prev">2개</div>
                    <input type="text" name="camp_site" value="2개" />
                </div>
                <div class="camp-detail-price">
                    <span>가격</span>
                    <div class="prev">50,000원</div>
                    <input type="text" name="camp_price" value="가격정보 없음" />
                </div>
                <div class="camp-detail-info">
                    <span>정보</span>
                    <div class="camp-detail-info-buttons">
                        <div class="row">
                            <label class="checkboxLabel">
                                <input type="checkbox" name="camp_info" value="toilet"/>
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/toilet.png" />
                                    <span>화장실</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="camp_info" value="parking"/>
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/parking.png">
                                    <span>주차장</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="camp_info" value="shower"/>
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/shower.png" />
                                    <span>샤워장</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="camp_info" value="disposal"/>
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/disposal.png" />
                                    <span>쓰레기장</span>
                                </div>
                            </label>

                            <label class="checkboxLabel">
                                <input type="checkbox" name="camp_info" value="deck"/>
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/deck.png" />
                                    <span>데크</span>
                                </div>
                            </label>

                            <label class="checkboxLabel">
                                <input type="checkbox" name="camp_info" value="bbq"/>
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/bbq.png" />
                                    <span>바비큐</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="camp_info" value="swim"/>
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/swim.png" />
                                    <span>수영장</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="camp_info" value="kitchen"/>
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/spoon.png" />
                                    <span>조리도구</span>
                                </div>
                            </label>
                            <label class="checkboxLabel">
                                <input type="checkbox" name="camp_info" value="lease"/>
                                <div class="icon-box" @click="clickCheckBox($event)">
                                    <img src="@/assets/img/Camping/lease.png" />
                                    <span>장비대여</span>
                                </div>
                            </label>
                        </div>
                    </div>
                </div>
            </form>
            <div class="camp-detail-info-right">
                <RButton :inputColor="'lightGreen'" :inputSize="'md'" :inputValue="'수정신청'" @clickBtn="confirm()"></RButton>
                <RButton :inputColor="'cream'" :inputSize="'md'" :inputValue="'취소'" @clickBtn="cancel()"></RButton>
            </div>
        </div>
        <div id="camp-detail-sns-container" class="camp-detail-sns-container">

        </div>
    </div>
</template>

<script>
import KakaoMap from "../KakaoMap.vue";
import CampDetailImage from "./CampDetailImage.vue";
import RButton from "../Admin/RButton.vue";
export default {
    props : ['prevData'],
    data : function() { 
        return {
            isNotList : true,
            search : '대구광역시 달서구 달서대로 719',
        }
    },
    components: {
    KakaoMap,
    CampDetailImage,
    RButton
},
    methods :{
        // 후기 셋팅 필요
        // 주소 카카오맵 할당 필요
        // 기타 정보 할당 필요
        // 사진 Swiper 적용하기
        clickCheckBox(e) {
            e.preventDefault();
            let checkItem = e.target.parentElement;
            while(!checkItem.classList.contains('checkboxLabel')){
                checkItem = checkItem.parentElement;
            }
            checkItem = checkItem.querySelector('input');
            
            console.log(checkItem.checked);
            if(checkItem.checked){
                checkItem.checked = false;
            } else {
                checkItem.checked = true;
            }
        },
        confirm() {
            let fetchData = {};
            new FormData(document.querySelector('.camp-detail-info-left')).forEach((value,key) => fetchData[key]=value);
            console.log(fetchData);
            for(let aa of document.querySelectorAll('input[type="checkbox"]')){
                if(aa.checked){
                    fetchData.camp_info = fetchData.camp_info + ',' + aa.value;
                }
            }
            console.log(fetchData);
            
           
        },
        cancel() {
            
        }
    }
}
</script>

<style scoped src="@/assets/css/Camping/CampDetail.css">

</style>
