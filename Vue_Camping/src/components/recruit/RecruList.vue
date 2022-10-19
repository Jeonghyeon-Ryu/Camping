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
        <form id="recru-filter-form" class="recru-list-filter recru-row">
            <div class="recru-filter-container recru-row">
                <div class="recru-filter-box recru-col">
                    <h3>희망 동행자</h3>
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
                            <span style="margin-left:6px">출발지 </span> 
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
                            <span style="margin-left:6px">여행지 </span> 
                            <select v-model="filter.regionSelect" id="recru-camp-districtSelect" name='city' @change="districtChange('camp')"> 
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
                            <select v-model="filter.regionSelect2" name='county' id="recru-camp-citySelect" style="margin-left:3px">
                                <option value='' selected disabled>시/군/구</option>
                                <option value='전체' selected>전체</option>
                            </select>
                        </div>
                        <div class="recru-search-day">
                            <label>출발날짜<input type="date" id="recru-go-date" v-model="filter.goDate"></label>
                        </div>
                    </div>
                </div>
                <div class="recru-filter-box recru-col">
                    <div class="recru-mygear-header">
                        <h3>보유한 장비</h3>
                    </div>
                    <div class="recru-row recru-left">
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="텐트">텐트</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="타프">타프</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="가구">가구</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="침구">침구</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="조리도구">조리도구</label>
                    </div>
                    <div class="recru-row recru-left">
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="조명">조명</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="수납">수납</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="공구">공구</label>
                        <label><input type="checkbox" v-model="filter.searchMyGear" value="냉난방">냉난방</label>
                        
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
                    </div>
                
                </div>
                <div class="recru-filter-btn-box recru-row">
                    <button type="submit" class="recru-filter-btn submit" @click.prevent="searchFileter">검색</button>
                    <button type="reset" class="recru-filter-btn reset">초기화</button>
                </div>
            </div>
        </form>
        <!-- 필터 결과-->
        <div class="used-selected">
            <ul>
                <li v-if="filter.wishSex != ''" @click="filter.wishSex=''">희망 성별 : {{filter.wishSex}} X</li>
                <li v-if="filter.wishAge != ''" @click="filter.wishAge=[]">희망 연령 : {{filter.wishAge}} X</li>
                <li v-if="filter.regionSelect != null" @click="filter.regionSelect=null;">출발지 : {{filter.regionSelect}} {{filter.regionSelect2}} X</li>
                <li v-if="filter.campingSpot != ''" @click="filter.campingSpot=''">도착지 : {{filter.campingSpot}} X</li>
                <li v-if="filter.goDate != ''" @click="filter.goDate=''">출발날짜 : {{filter.goDate}} X</li>
                <li v-if="filter.searchMyGear != ''" @click="filter.searchMyGear=[]">나눌 수 있어요 : {{filter.searchMyGear}} X</li>
                <li v-if="filter.searchNeedGear != ''" @click="filter.searchNeedGear=[]">필요해요 : {{filter.searchNeedGear}} X</li>
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
        searchFileter : function(){
            //전체 값 받기
            fetch("http://localhost:8087/java/recru")
            .then((response) =>response.json()) 
            .then(data => { 
                console.log(data);
                this.recruPosts = data;  
                const fil = this.filter;
                const filterList = this.recruPosts;
                //성별필터
                if(fil.wishSex!=0 && fil.wishSex != null){
                    for(var i = 0; i < filterList.length; i++){ 
                        if (filterList[i].wishSex != fil.wishSex) { 
                            //필터의 희망성별과 다른 경우 배열에서 제거
                            filterList.splice(i, 1); 
                            i--; 
                        }
                    }
                }
                //나이대 필터
                if(fil.wishAge.length>0){
                    var list=[];
                    for(var i = 0; i < filterList.length; i++){ 
                        if (filterList[i].wishAge==null) { 
                            filterList.splice(i, 1); 
                            i--; 
                        }else{
                            var status = false; //기본 false
                            for(var j=0; j < fil.wishAge.length; j++){
                                //각 연령대 중 하나라도 있으면 출력
                                if(!filterList[i].wishAge){
                                    filterList.splice(i, 1); 
                                }
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
                }
                //출발지필터
                const startSido = document.querySelector('#recru-start-districtSelect').value;
                const startSigu = document.querySelector('#recru-start-citySelect').value;
                console.log(startSido);
                console.log(startSigu);
                const startRegion='';
                if(startSigu && startSigu!='전체'){
                    startRegion += startSido+' '+startSigu;
                }
                if(startRegion!=null&& startRegion!='전체'){ 
                    for(var i = 0; i < filterList.length; i++){ 
                        console.log(filterList[i].startingPoint)
                        console.log(filterList[i].startingPoint.indexOf(startRegion))
                        if(!filterList[i].startingPoint){
                            filterList.splice(i,1);
                            i--;
                            continue;
                        }
                        if (filterList[i].startingPoint.indexOf(startRegion)<0) {      
                            filterList.splice(i, 1); 
                            i--; 
                        }
                    }
                }
                //도착지필터
                const camp = fil.regionSelect
                if(fil.regionSelect2 && fil.regionSelect2!='전체'){
                    start += fil.regionSelect2
                }
                if(start!=null&& start!='전체'){ 
                    for(var i = 0; i < filterList.length; i++){ 
                        console.log(filterList[i].startingPoint)
                        console.log(filterList[i].startingPoint.indexOf(start))
                        if(!filterList[i].startingPoint){
                            filterList.splice(i,1);
                            i--;
                            continue;
                        }
                        if (filterList[i].startingPoint.indexOf(start)<0) {      
                            filterList.splice(i, 1); 
                            i--; 
                        }
                    }
                }
                console.log(filterList)
                
                // wishAge :[],
                // searchMyGear :[],
                // searchNeedGear: [],

                // campingSpot: '',
                // goDate: ''
            }).catch(err=>console.log(err));
        }
        
        
    }
}
</script>
<style scoped src="@/assets/css/used/UsedMain.css"/>
<style scoped src="@/assets/css/recruit/recruList.css"/>