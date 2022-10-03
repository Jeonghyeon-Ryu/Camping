<template>
    <div >
        <div class="recru-search-box">
            <span>희망 동행자</span>
            <div class="recru-search-sex">
                <label><input type="radio" v-model="wishSex" value="0">남</label>
                <label><input type="radio" v-model="wishSex" value="1">여</label>
                <label><input type="radio" v-model="wishSex" value="2" checked>무관</label>
            </div>
            <div class="recru-search-age">
                <label><input type="checkbox" v-model="wishAge" value="2">20대</label>
                <label><input type="checkbox" v-model="wishAge" value="3">30대</label>
                <label><input type="checkbox" v-model="wishAge" value="4">40대</label>
                <label><input type="checkbox" v-model="wishAge" value="5">50대 이상</label>
            </div>
        </div>

        <div class="recru-search-box">
            <div class="recru-mygear-header">
                <span>나 이거 있어요</span>
            </div>
            <div>
                <label><input type="checkbox" v-model="searchMyGear" value="tent">텐트</label>
                <label><input type="checkbox" v-model="searchMyGear" value="tarp">타프</label>
                <label><input type="checkbox" v-model="searchMyGear" value="furniture">가구</label>
                <label><input type="checkbox" v-model="searchMyGear" value="bedding">침구</label>
                <label><input type="checkbox" v-model="searchMyGear" value="kitchenTool">조리도구</label>
                <label><input type="checkbox" v-model="searchMyGear" value="lamp">조명</label>
                <label><input type="checkbox" v-model="searchMyGear" value="storage">수납</label>
                <label><input type="checkbox" v-model="searchMyGear" value="tool">공구</label>
                <label><input type="checkbox" v-model="searchMyGear" value="airCon">냉난방</label>
            </div>
        </div>
    
        <div class="recru-search-box">
            <div class="recru-mygear-header">
                <span>필요해요</span>
            </div>
            <div>
                <label><input type="checkbox" v-model="searchNeedGear" value="tarp">텐트</label>
                <label><input type="checkbox" v-model="searchNeedGear" value="tarp">타프</label>
                <label><input type="checkbox" v-model="searchNeedGear" value="furniture">가구</label>
                <label><input type="checkbox" v-model="searchNeedGear" value="bedding">침구</label>
                <label><input type="checkbox" v-model="searchNeedGear" value="kitchenTool">조리도구</label>
                <label><input type="checkbox" v-model="searchNeedGear" value="lamp">조명</label>
                <label><input type="checkbox" v-model="searchNeedGear" value="storage">수납</label>
                <label><input type="checkbox" v-model="searchNeedGear" value="tool">공구</label>
                <label><input type="checkbox" v-model="searchNeedGear" value="airCon">냉난방</label>
            </div>
        </div>         
        
        <div class="recru-search-box">
            <span>여행정보</span>
            <ul class="recru-box-name">
                <li class="recru-search-startP">
                    <label>출발지<input type="text" v-model="startingSpot"></label>
                </li>
                <li class="recru-search-campP">
                    <label>도착지<input type="text" v-model="campingSpot"></label>
                </li>
                <li class="recru-search-day">
                    <label>출발날짜<input type="date" v-model="campingSpot"></label>
                </li>
            </ul>
        </div>
        <div style="text-align: center;">
            <button type="button" class="btn bg-gradient-light btn-md false"
                    @click="sendFileter">검색</button>
        </div>
    </div>
</template>
<script>
    
    export default{
        data: function(){
            return {
                wishSex :[],
                wishAge :[],
                searchMyGear :[],
                searchNeedGear: [],
                startingSpot: '',
                campingSpot: '',
                goDate: '',
                date : new Date()
            }
        },
        methods : {
            sendFileter : function(){
                alert(this.wishSex+', '+this.wishAge+', '+this.searchMyGear)
            }
        }  
    }
</script>