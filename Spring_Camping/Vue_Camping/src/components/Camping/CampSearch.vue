<template>
    <div class="search-container">
        <div class="main-title">오늘은 어느 캠핑장으로 떠나볼까?</div>
        <div class="search-top">
            <div class="select-container">
                <select class="search-select">
                    <option disabled selected>검색 조건</option>
                    <option>이름</option>
                    <option>주소</option>
                </select>
                <img src="@/assets/img/arrow-down.svg">
            </div>
            <input type="text" class="main-search" @keyup.enter="search()">
            <img class="search-icon" src="@/assets/img/search.png" @click="search()">
        </div>
        <div class="hashtag">
            <span @click="hashtag($event)">#서울</span>
            <span @click="hashtag($event)">#대구</span>
            <span @click="hashtag($event)">#대전</span>
            <span @click="hashtag($event)">#인천</span>
            <span @click="hashtag($event)">#부산</span>
            <span @click="hashtag($event)">#광주</span>
        </div>
    </div>
</template>
<script>
import Swal from 'sweetalert2';
export default {
    data() {
        return {
            resultItem:[]
        }
    },
    methods: {
        search: function () {
            let select = document.querySelector('.search-select').value;
            let search = document.querySelector('.main-search').value;
            console.log(select);
            console.log(search);
            this.resultItem=[];
            if (select == '이름') {
                // location.href = "http://localhost:8082/used/usedMain?search=" + search
                fetch('http://localhost:8087/java/camp/name?campName=' + search)
                    .then(result => result.json())
                    .then(result => {
                        this.$emit("search", result);
                    })
            } else if (select == '주소') {
                // location.href = "http://localhost:8082/CampList?search=" + search
                fetch('http://localhost:8087/java/camp/address?campAddress=' + search)
                    .then(result => result.json())
                    .then(result => {
                        console.log(result);
                        this.$emit("search", result);
                    })
            } else {
                if (select == "검색 조건") {
                    Swal.fire({
                        icon: 'warning',
                        title: '검색 카테고리를 선택해주세요',
                        toast: true,
                        showConfirmButton: false,
                        timer: 1000,
                        timerProgressBar: true,
                        didOpen: (toast) => {
                            toast.addEventListener('mouseenter', Swal.stopTimer)
                            toast.addEventListener('mouseleave', Swal.resumeTimer)
                            document.querySelector('.search-select').focus();
                        }
                    })
                } else if (search == '') {
                    Swal.fire({
                        icon: 'warning',
                        title: '검색 내용을 입력해주세요',
                        toast: true,
                        showConfirmButton: false,
                        timer: 1000,
                        timerProgressBar: true,
                        didOpen: (toast) => {
                            toast.addEventListener('mouseenter', Swal.stopTimer)
                            toast.addEventListener('mouseleave', Swal.resumeTimer)
                            document.querySelector('.main-search').focus();
                        }
                    })
                }
            }
            console.log(this.resultItem)
            if(this.resultItem.length != 0) {
                this.$emit("search", this.resultItem);
            }
        },
        hashtag: function (e) {
            document.querySelector('.search-select').value = "주소";
            document.querySelector('.main-search').value = e.target.innerText.substring(1, e.target.innerText.length);
        }

    }
}
</script>
<style scoped>
.main-title {
    color: white;
    font-weight: bold;
    font-size: 1.2em;
    margin-left: 7rem;
    padding: 0.8em 0.8em 0.4em 0.8em;
    letter-spacing: 4px;
}

.select-container {
    width: 8rem;
    height: 3.5rem;
    padding: 0.8rem;
    border-radius: 40px;
    margin-right: 1rem;
    background-color: #0a5326e9;
    position: relative;
    cursor: pointer;
}

.select-container img {
    position: absolute;
    width: 10px;
    height: 10px;
    right: 15px;
    top: calc(50% - 5px);
}

select option {
    color: black;
}

.search-container {
    width: 100%;
    display: flex;
    flex-direction: column;
}

.search-top {
    display: flex;
    align-items: center;
    position: relative;
}

.search-select {
    width: 100%;
    height: 100%;
    padding-right: 10px;
    background: none;
    border: none;
    color: rgb(255, 255, 255);
    font-weight: bold;
    text-align: center;
    -moz-appearance: none;
    -webkit-appearance: none;
    appearance: none;
    cursor: pointer;
}

select::-ms-expand {
    display: none;
}

.main-search {
    width: calc(100% - 8rem);
    height: 3.5rem;
    padding: 0.8rem 5rem 0.8rem 3rem;
    border-radius: 90px;
    background-color: rgba(255, 255, 255, 0.9);
    border: 1px solid gray;
    font-weight: bold;
    font-size: 1.1em;
    color: #353535;
}

.search-icon {
    width: 50px;
    height: 50px;
    position: absolute;
    right: 1rem;
}

.hashtag {
    display: flex;
    margin-left: 8rem;
    justify-content: space-evenly;
}

.hashtag span {
    padding: 10px;
    border-radius: 10px;
    color: rgb(176, 176, 176);
    font-weight: bold;
    font-size: 0.9em;
}

select:focus,
select:hover {
    outline: none;
}

input:focus {
    outline: none;
    border: 3px solid #0a5326e9;
}

input:hover {
    background-color: #ffffff;
}
</style>