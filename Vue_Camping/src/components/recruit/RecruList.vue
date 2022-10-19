<template>
    <div class="recru-list-container">      
        <!-- 검색 -->
        <div class="used-searchbox">
            <div>
                <input class="recru-search" type="text" placeholder="어떤 캠핑을 찾으시나요?" v-model="keyword" @keyup.enter="searchKeyword">
                <img v-bind:src="searchImg" @click="searchKeyword">
            </div>
        </div>
        <!-- 필터 -->
        <form id="recru-filter-form" class="recru-list-filter recru-row">
            <div class="recru-filter-container recru-row">
                <div class="recru-filter-box recru-col">
                    <h3>나의 정보</h3>
                    <div class="recru-search-sex ">
                        <label class="bold">나이</label>
                        <label><input type="radio" name='wishSex' v-model="filter.wishSex" value="1">남</label>
                        <label><input type="radio" name='wishSex' v-model="filter.wishSex" value="2">여</label>
                        <label><input type="radio" name='wishSex' v-model="filter.wishSex" value="0" checked>무관</label>
                    </div>
                    <div class="recru-search-age recru-col">
                        <div class=" recru-left">
                            <label class="bold">연령대</label>
                            <label><input type="checkbox" v-model="filter.wishAge" value="20대" name="test"> 20대</label>
                            <label><input type="checkbox" v-model="filter.wishAge" value="30대" name="test"> 30대</label>
                        </div>
                        <div class="recru-row ">
                            <label><input type="checkbox" v-model="filter.wishAge" value="40대" style="margin-left:100px"> 40대</label>
                            <label><input type="checkbox" v-model="filter.wishAge" value="50대이상"> 50대 이상</label>
                        </div>
                    </div>
                </div>
                <div class="recru-filter-box recru-col">
                    <h3>여행정보</h3>
                    <div class="recru-filter-trip">
                        <div class="recru-search-startP">
                            <span>출발지 </span> 
                            <select v-model="filter.regionSelect" id="recru-start-districtSelect" name='city' @change="districtChange('start')"> 
                                <option value='' disabled>시/도</option> 
                                <option value='전체'>전체</option>
                                <option value='서울'>서울특별시</option>
                                <option value='부산'>부산광역시</option>
                                <option value='대구'>대구광역시</option>
                                <option value='인천'>인천광역시</option>
                                <option value='광주'>광주광역시</option>
                                <option value='대전'>대전광역시</option>
                                <option value='울산'>울산광역시</option>
                                <option value='경기'>경기도</option>
                                <option value='강원'>강원도</option>
                                <option value='충북'>충청북도</option>
                                <option value='충남'>충청남도</option>
                                <option value='전북'>전라북도</option>
                                <option value='전남'>전라남도</option>
                                <option value='경북'>경상북도</option>
                                <option value='경남'>경상남도</option>
                                <option value='제주특별자치도'>제주도</option>
                            </select>
                            <select v-model="filter.regionSelect2" name='county' id="recru-start-citySelect" style="margin-left:3px">
                                <option value='' selected disabled>시/군/구</option>
                                <option value='전체' selected>전체</option>
                            </select>
                        </div>
                        <div class="recru-search-campP">
                            <span>여행지 </span> 
                            <select v-model="filter.citySelect" id="recru-camp-districtSelect" name='city' @change="districtChange('camp')"> 
                                <option value='' disabled>시/도</option> 
                                <option value='전체'>전체</option>
                                <option value='서울'>서울특별시</option>
                                <option value='부산'>부산광역시</option>
                                <option value='대구'>대구광역시</option>
                                <option value='인천'>인천광역시</option>
                                <option value='광주'>광주광역시</option>
                                <option value='대전'>대전광역시</option>
                                <option value='울산'>울산광역시</option>
                                <option value='경기'>경기도</option>
                                <option value='강원'>강원도</option>
                                <option value='충북'>충청북도</option>
                                <option value='충남'>충청남도</option>
                                <option value='전북'>전라북도</option>
                                <option value='전남'>전라남도</option>
                                <option value='경북'>경상북도</option>
                                <option value='경남'>경상남도</option>
                                <option value='제주특별자치도'>제주도</option>
                            </select>
                            <select v-model="filter.citySelect2" name='county' id="recru-camp-citySelect" style="margin-left:3px">
                                <option value='' selected disabled>시/군/구</option>
                                <option value='전체' selected>전체</option>
                            </select>
                        </div>
                        <div class="recru-search-day">
                            <label>출발날짜<input type="date" id="recru-go-date" v-model="filter.goDateMin"></label>
                                ~ <input type="date" id="recru-go-date" v-model="filter.goDateMax">
                        </div>
                    </div>
                </div>
                <div class="recru-filter-box recru-col">
                    <div class="recru-mygear-header">
                        <h3>갖고있어요</h3>
                    </div>
                    <div class="recru-row ">
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="텐트">텐트</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="타프">타프</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="가구">가구</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="침구">침구</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="조리도구">조리도구</label>
                    </div>
                    <div class="recru-row ">
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="조명">조명</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="수납">수납</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="공구">공구</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="냉난방">냉난방</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="기타">기타</label>
                        
                    </div>
                    <br>
                    <div class="recru-mygear-header">
                        <h3>필요해요</h3>
                    </div>
                    <div class="recru-row">
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="텐트">텐트</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="타프">타프</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="가구">가구</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="침구">침구</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="조리도구">조리도구</label>
                    </div>
                    <div class="recru-row ">    
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="조명">조명</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="수납">수납</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="공구">공구</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="냉난방">냉난방</label>
                        <label><input type="checkbox" v-model="filter.searchNeedGear" value="기타">기타</label>
                    </div>
                
                </div>
                <div class="recru-filter-btn-box recru-row">
                    <button type="submit" class="recru-filter-btn submit" @click.prevent="searchFilter">검색</button>
                    <button type="reset" class="recru-filter-btn reset" @click.prevent="resetFilter">초기화</button>
                </div>
            </div>
        </form>
        <!-- 필터 결과-->
        <div class="used-selected">
            <ul>
                <li v-if="filter.wishSex" @click="filter.wishSex=''">희망 성별 : {{filter.wishSex==0? '무관': filter.wishSex==1? '남자' : '여자'}} X</li>
                <li v-if="filter.wishAge != ''" @click="filter.wishAge=[]">희망 연령 : {{toStringList(filter.wishAge)}} X</li>
                <li v-if="filter.regionSelect != null" @click="filter.regionSelect=null;">출발지 : {{filter.regionSelect}} {{filter.regionSelect2}} X</li>
                <li v-if="filter.citySelect != null" @click="filter.citySelect=null">도착지 : {{filter.citySelect}} {{filter.citySelect2}} X</li>
                <li v-if="filter.goDateMin ||filter.goDateMin" @click="filter.goDateMin='';filter.goDateMax=''">출발날짜 : {{filter.goDateMin}} ~ {{filter.goDateMax}} X</li>
                <li v-if="filter.searchMyGear != ''" @click="filter.searchMyGear=[]">나눌 수 있어요 : {{toStringList(filter.searchMyGear)}} X</li>
                <li v-if="filter.searchNeedGear != ''" @click="filter.searchNeedGear=[]">필요해요 : {{toStringList(filter.searchNeedGear)}} X</li>
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
import { filter } from "dom7";

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
                goDateMin: '',
                goDateMax: ''
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
        toStringList : function(array){
            var stringList = '';
            //연령 및 장비: 배열 -> 문자열
            for(let i=0; i<array.length ; i++){
                if(i!=0) stringList += ', '
                stringList += array[i];
            }
            return stringList;
        },
        districtChange: function(menu){
            //출발지인지 도착지인지 menu로 구분
            //지역선택
            let sido = document.querySelector('#recru-'+menu+'-districtSelect');
            let sigu = document.querySelector('#recru-'+menu+'-citySelect');
            let sidoName = sido.value;

            if(sidoName==='전체'){
                return;
            }
            let cityArr = ["서울","부산","인천","대구","광주","대전","울산","경기","강원","충북","충남","경북","경남","전북","전남","제주특별자치도"];

            sigu.options.length=1;  //저장내역 삭제

            let cityIndex = cityArr.indexOf(sidoName);
            let cityList = district.data[cityIndex][sidoName];  //도시배열
            var opt = document.createElement("option");
            opt.value = '전체';
            opt.innerHTML = '전체';
            sigu.appendChild(opt);
            for(let i in cityList){
                        var opt = document.createElement("option");
                        opt.value = cityList[i];
                        opt.innerHTML = cityList[i];
                        sigu.appendChild(opt);
            }
        },
        searchKeyword : function(){
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
        searchFilter : function(){
            // 서버에서 전체 리스트 가져오기
            fetch("http://localhost:8087/java/recru")
            .then((response) =>response.json()) 
            .then(data => { 
                this.recruPosts = data;  
                const fil = this.filter;
                const filterList = this.recruPosts;

                //성별필터
                if(fil.wishSex!=0 && fil.wishSex != ''){
                    for(var i = 0; i < filterList.length; i++){ 
                        if (filterList[i].wishSex != fil.wishSex) { 
                            //필터의 희망성별과 다른 경우 배열에서 제거
                            filterList.splice(i, 1); 
                            i--; 
                        }
                    }
                    console.log(filterList)
                }

                //나이대 필터
                if(fil.wishAge.length>0){
                    for(var i = 0; i < filterList.length; i++){ 
                        if (!filterList[i].wishAge) { 
                            filterList.splice(i, 1); 
                            i--; 
                        }else{
                            var status = false; //기본 false
                            for(var j=0; j < fil.wishAge.length; j++){
                                //각 연령대 중 하나라도 있으면 출력
                                if (filterList[i].wishAge.indexOf(fil.wishAge[j])>=0) { 
                                    status = true; //하나라도 있으면 true
                                }
                            }
                                if(!status){
                                    filterList.splice(i, 1); //둘 다 없으면 false
                                    i--; 
                                }
                        }
                    }
                    console.log(filterList)
                }

                 //출발지필터
                const startSido = document.querySelector('#recru-start-districtSelect').value;
                const startSigu = document.querySelector('#recru-start-citySelect').value;
                if(startSido!=''&& startSido!='전체'){ 
                    //출발시 시/도
                    for(var i = 0; i < filterList.length; i++){ 
                        if(!filterList[i].startingPoint){
                            filterList.splice(i,1);
                            i--;
                            continue;
                        }
                        if (filterList[i].startingPoint.indexOf(startSido)<0) {      
                            filterList.splice(i, 1); 
                            i--; 
                        }
                    }
                    console.log(filterList)
                } 
                if(startSigu!=''&& startSigu!='전체'){ 
                    //출발시 시/구
                    for(var i = 0; i < filterList.length; i++){ 
                        if(!filterList[i].startingPoint){
                            filterList.splice(i,1);
                            i--;
                            continue;
                        }
                        if (filterList[i].startingPoint.indexOf(startSigu)<0) {      
                            filterList.splice(i, 1); 
                            i--; 
                        }
                    }
                    console.log(filterList)
                }

                //도착지(캠핑장)필터
                const campSido = document.querySelector('#recru-camp-districtSelect').value;
                const campSigu = document.querySelector('#recru-camp-citySelect').value;
                if(campSido!=''&& campSido!='전체'){ 
                    //도착지 시/도
                    for(var i = 0; i < filterList.length; i++){ 
                        if(!filterList[i].campingPoint){
                            filterList.splice(i,1);
                            i--;
                            continue;
                        }
                        if (filterList[i].campingPoint.indexOf(campSido)<0) {      
                            filterList.splice(i, 1); 
                            i--; 
                        }
                    }
                    console.log(filterList)
                } 
                if(campSigu!=''&& campSigu!='전체'){ 
                    //도착지 시/군
                    for(var i = 0; i < filterList.length; i++){ 
                        if(!filterList[i].campingPoint){
                            filterList.splice(i,1);
                            i--;
                            continue;
                        }
                        if (filterList[i].campingPoint.indexOf(campSigu)<0) {      
                            filterList.splice(i, 1); 
                            i--; 
                        }
                    }
                    console.log(filterList)
                }
                
                // 출발 날짜
                if(fil.goDateMin){
                    console.log(fil.goDateMin)
                    //범위 최소보다 작으면 아웃
                    for(var i = 0; i < filterList.length; i++){ 
                        if(!filterList[i].goDate){
                            filterList.splice(i,1);
                            i--;
                            continue;
                        }
                        if(filterList[i].goDate < fil.goDateMin) {
                            filterList.splice(i,1);
                            i--;
                        }
                    }
                    console.log(filterList)
                }
                if(fil.goDateMax){
                    console.log(fil.goDateMax)
                    //범위 최대보다 크면 아웃
                    for(var i = 0; i < filterList.length; i++){ 
                        if(!filterList[i].goDate){
                            filterList.splice(i,1);
                            i--;
                            continue;
                        }
                        if(filterList[i].goDate > fil.goDateMax) {
                            filterList.splice(i,1);
                            i--;
                        }
                    }
                    console.log(filterList)
                }

                // 보유한 필터 (->게시글의 필요장비 검색)
                if(fil.searchMyGear.length>0){
                    for(let i = 0; i < filterList.length; i++){ 
                        //게시글의 필요장비 값이 없는 경우 제거
                        if (filterList[i].needGear==null) { 
                            filterList.splice(i, 1); 
                            i--; 
                        }
                        else{
                            var status = false; //기본 false
                            for(var j=0; j < fil.searchMyGear.length; j++){
                                //각 장비 중 하나라도 있으면 true
                                if (filterList[i].needGear.indexOf(fil.searchMyGear[j])>=0) { 
                                    status = true; //하나라도 있으면 true
                                }
                            }
                            if(!status){
                                filterList.splice(i, 1); //하나도 없으면 false -> 삭제
                                i--; 
                            }
                        }
                    }
                }

                // 필요한 필터 (->게시글의 필요장비 검색)
                if(fil.searchNeedGear.length>0){
                    for(let i = 0; i < filterList.length; i++){ 
                        if (filterList[i].myGear==null) { 
                            filterList.splice(i, 1); 
                            i--; 
                        }else{
                            var status = false; //기본 false
                            for(var j=0; j < fil.searchNeedGear.length; j++){
                                //각 장비 중 하나라도 있으면 출력
                                if(!filterList[i].myGear){
                                    filterList.splice(i, 1); 
                                }
                                if (filterList[i].myGear.indexOf(fil.searchNeedGear[j])>=0) { 
                                    status = true; //하나라도 있으면 true
                                }
                            }
                            if(!status){
                                filterList.splice(i, 1); //하나도 없으면 false -> 삭제
                                i--; 
                            }
                        }
                    }
                    console.log(filterList)
                }


            }).catch(err=>console.log(err));
        },
        resetFilter (){
            //필터 초기화버튼
            this.filter.wishSex;
            this.filter.wishAge=[];
            this.filter.regionSelect=null;
            this.filter.citySelect=null;
            this.filter.goDateMin='';
            this.filter.goDateMax='';
            this.filter.searchMyGear=[];
            this.filter.searchNeedGear=[];
        },       
    }
}
</script>
<style scoped src="@/assets/css/used/UsedMain.css"/>
<style scoped src="@/assets/css/recruit/recruList.css"/>