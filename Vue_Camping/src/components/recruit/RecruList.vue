<template>
    <div class="recru-list-container">      
        <!-- 검색 -->
        <div class="used-searchbox">
            <div>
                <input class="recru-search" type="text" placeholder="어떤 캠핑을 찾으시나요?" v-model="keyword" @keyup.enter="searchList">
                <img v-bind:src="searchImg" @click="searchList">
            </div>
        </div>
        <!-- 필터 -->
        <form id="recru-filter-form" class="recru-list-filter row">
            <div class="recru-filter-container row">
                <div class="recru-filter-box col">
                    <h3>희망 동행자</h3>
                    <div class="recru-search-sex ">
                        <label class="bold">나이</label>
                        <label><input type="radio" name='wishSex' v-model="filter.wishSex" value="1">남</label>
                        <label><input type="radio" name='wishSex' v-model="filter.wishSex" value="2">여</label>
                        <label><input type="radio" name='wishSex' v-model="filter.wishSex" value="0" checked>무관</label>
                    </div>
                    <div class="recru-search-age col">
                        <div class=" left">
                            <label class="bold">연령대</label>
                            <label><input type="checkbox" v-model="filter.wishAge" value="20대" name="test">20대</label>
                            <label><input type="checkbox" v-model="filter.wishAge" value="30대" name="test">30대</label>
                        </div>
                        <div class="row left">
                            <label><input type="checkbox" v-model="filter.wishAge" value="40대">40대</label>
                            <label><input type="checkbox" v-model="filter.wishAge" value="50대이상">50대 이상</label>
                        </div>
                    </div>
                </div>
                <div class="recru-filter-box col">
                    <h3>여행정보</h3>
                    <div class="recru-filter-trip">
                        <div class="recru-search-startP">
                            <span style="margin-left:6px">출발지 </span> 
                            <select v-model="filter.regionSelect" id="recru-districtSelect" name='city' @change="districtChange"> 
                                <option value='' disabled>시/도</option> 
                                <option value='전체'>전체</option>
                                <option value='서울특별시'>서울특별시</option>
                                <option value='부산광역시'>부산광역시</option>
                                <option value='대구광역시'>대구광역시</option>
                                <option value='인천광역시'>인천광역시</option>
                                <option value='광주광역시'>광주광역시</option>
                                <option value='대전광역시'>대전광역시</option>
                                <option value='울산광역시'>울산광역시</option>
                                <option value='경기도'>경기도</option>
                                <option value='강원도'>강원도</option>
                                <option value='충청북도'>충청북도</option>
                                <option value='충청남도'>충청남도</option>
                                <option value='전라북도'>전라북도</option>
                                <option value='전라남도'>전라남도</option>
                                <option value='경상북도'>경상북도</option>
                                <option value='경상남도'>경상남도</option>
                                <option value='제주도'>제주도</option>
                            </select>
                            <select v-model="filter.regionSelect2" name='county' id="recru-citySelect" style="margin-left:3px">
                                <option value='' selected disabled>시/군/구</option>
                                <option value='전체'>전체</option>
                            </select>
                        </div>
                        <div class="recru-search-campP">
                            <label>도착지<input type="text" v-model="filter.campingSpot" @click="searchCamp">
                            <img v-bind:src="searchImg" style="width:20px;margin:auto 0"></label>
                        </div>
                        <div class="recru-search-day">
                            <label>출발날짜<input type="date" v-model="filter.goDate"></label>
                        </div>
                    </div>
                </div>
                <div class="recru-filter-box col">
                    <div class="recru-mygear-header">
                        <h3>보유한 장비</h3>
                    </div>
                    <div class="row left">
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="텐트">텐트</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="타프">타프</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="가구">가구</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="침구">침구</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="조리도구">조리도구</label>
                    </div>
                    <div class="row left">
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="조명">조명</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="수납">수납</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="공구">공구</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="냉난방">냉난방</label>
                        
                    </div>
                    <br>
                    <div class="recru-mygear-header">
                        <h3>필요해요</h3>
                    </div>
                    <div class="row left">
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="텐트">텐트</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="타프">타프</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="가구">가구</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="침구">침구</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="조리도구">조리도구</label>
                    </div>
                    <div class="row left">    
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="조명">조명</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="수납">수납</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="공구">공구</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="냉난방">냉난방</label>
                    </div>
                
                </div>
                <div class="recru-filter-btn-box row">
                    <button type="submit" class="recru-filter-btn submit" @click.prevent="searchFileter">검색</button>
                    <button type="reset" class="recru-filter-btn reset">초기화</button>
                </div>
            </div>
        </form>
        <!-- 필터 결과-->
        <div class="used-selected">
            <ul>
                <li v-if="filter.wishSex != ''" @click="deleteFilter">희망 성별 : {{filter.wishSex}} X</li>
                <li v-if="filter.wishAge != ''" @click="deleteFilter">희망 연령 : {{filter.wishAge}}{{ischeck}} X</li>
                <li v-if="filter.regionSelect != null" @click="deleteFilter">출발지 : {{filter.regionSelect}} {{filter.regionSelect2}} X</li>
                <li v-if="filter.campingSpot != ''" @click="deleteFilter">도착지 : {{filter.campingSpot}} X</li>
                <li v-if="filter.goDate != ''" @click="deleteFilter">출발날짜 : {{filter.goDate}} X</li>
                <li v-if="filter.searchMyGear != ''" @click="deleteFilter">나눌 수 있어요 : {{filter.searchMyGear}} X</li>
                <li v-if="filter.searchNeedGear != ''" @click="deleteFilter">필요해요 : {{filter.searchNeedGear}} X</li>
            </ul>
        </div>

        <!-- 리스트 -->
        <div class="container">
            <!-- 카드 -->
            <h2>{{recruMsg}}</h2>
            <div class="recru-card-box">
                <div v-for="recruInfo in recruPosts" :key="recruInfo.recruId">
                    <router-link tag="div" v-bind:to="{name:'recruDetail',params : {recruId : recruInfo.recruId}}">
                        <RecruCard v-bind:recruCard="recruInfo"></RecruCard>
                    </router-link>
                </div>
            </div>      
        </div>
    </div>
</template>
<script>
import RecruCard from "@/components/recruit/RecruCard.vue";
import img2 from "@/assets/img/search.png"
import district from "@/assets/district.js"

export default{
    components: {
        RecruCard
    },
    data : function(){
        return{
            keyword : '',
            filter: {
                wishSex :'',
                wishAge :[],
                searchMyGear :[],
                searchNeedGear: [],
                startingSpot: '',
                campingSpot: '',
                goDate: ''
            },
            recruPosts : [ ],
            searchImg : img2,
            recruMsg : ''
        }
    },
    created(){
        this.loadData();
    },
    methods: {
        loadData : function(){
            // 서버에서 전체 리스트 가져오기
            fetch("http://localhost:8087/java/recru")
            .then((response) =>response.json()) 
            .then(data => { 
                console.log(data);
                this.recruPosts = data;  

            }).catch(err=>console.log(err));
        },
        districtChange: function(){
            //지역선택
            let sido = document.querySelector('#recru-districtSelect');
            let sigu = document.querySelector('#recru-citySelect');
            let sidoName = sido.value;
            let cityArr = ["서울특별시","부산광역시","인천광역시","대구광역시","광주광역시","대전광역시","울산광역시","경기도","강원도","충청북도","충청남도","경상북도","경상남도","전라북도","전라남도","제주도"];

            sigu.options.length=1;  //저장내역 삭제

            let cityIndex = cityArr.indexOf(sidoName);

            let cityList = district.data[cityIndex][sidoName];  //도시배열
            for(let i in cityList){
                        var opt = document.createElement("option");
                        opt.value = cityList[i];
                        opt.innerHTML = cityList[i];
                        sigu.appendChild(opt);
            }
        },
        searchList : function(){
            //키워드 검색 결과 받아오기
            const keyword = this.keyword;
            fetch("http://localhost:8087/java/recru/search/"+keyword,{
                method : "POST",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify(keyword)
            })
            .then((response) =>response.json()) 
            .then(data => { 
                console.log(data);
                this.recruPosts = data;  
                if(this.recruPosts.length<1){
                    this.recruMsg="검색 결과가 없습니다."
                }else{
                    this.recruMsg="";
                }
            }).catch(err=>console.log(err));
        },
        deleteFilter: function(e){
            console.log(e.target)
        },
        
        
    }
}
</script>
<style scoped src="@/assets/css/used/UsedMain.css"/>
<style scoped src="@/assets/css/recruit/recruList.css"/>