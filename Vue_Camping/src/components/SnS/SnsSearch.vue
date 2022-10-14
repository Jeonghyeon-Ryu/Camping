<template>
  <div class="sns-container">



    <div class="sns-searchbox">
      <input type="search" @keyup="checkEnter($event)" v-model="searchText" placeholder="검색어를 입력해주세요.">
      <!-- <button @click="doSearch" style="display: none;">조회</button> -->
      <!-- <input type="button" @click="doClear" value="X"> -->
    </div>


    <div class="sns-search-list-container">
      <div class="sns-search-list" v-for="searchHashTagKeyword of searchResult" >
        <input v-show="this.doSearchTag" type="text" @click="" v-bind:value="searchHashTagKeyword" v-on:change="search()">
      </div>
    </div>
    <div class="sns-search-list-container">
      <div class="sns-search-id-list" v-for="searchMemberId of searchResultNick">
        <input v-show="this.doSearchId" type="text" @click="" v-bind:value="searchMemberId" v-on:change="search()">
      </div>
    </div>

  </div>
</template>

<script>
// import sns from "@/components/SnS/snsMember.js"

export default {

  //DB연결
  created : function(){
    fetch('http://localhost:8087/java/hashtag')
    
    .then(result => result.text())
    .then(result => result.substring(2, result.length-2))
    .then(result => result.replaceAll('","', ' '))
    // .then(result => {
    //   result = result.split('","')
    // console.log(result)})
    // .then(result => result.text())
    .then(result => result.split(' '))
    .then(result => result.join(' '))
    
    .then(result => {
      console.log(result)
      this.snsData.push(result)
    })
    .catch(err => console.log(err));


    //닉네임 검색... 왜안되ㅈ는지....그지같음...
    fetch('http://localhost:8087/java/snsnickname')
    .then(result => result.text())
    
    
    .then(result => result.substring(2, result.length-2))
    .then(result => result.replaceAll('","',' '))
    .then(result => result.split(' '))
    .then(result => result.join(' '))
    
    .then(result => {
        this.searchNickname.push(result)
        console.log(result)
      })
      // .then(result => console.log(result))
      // .catch(err => console.log(err));
    
  },

  components: {},
  data: () => {
    return {
      searchText: '',
      //해시태그모음
      snsData: [],
      //닉네임모음
      searchNickname : [],
      //해시태그결과값
      searchResult: [],
      //닉네임결과값
      searchResultNick : [],

    }
  },
  //검색
  methods: {
    // search: function(){
    //   this.hashNick = !this.hashNick;
    // }
    // ,
    doSearch() {
      console.log(this.searchText)
    },
    checkEnter(event) {
      // console.log(event.target.value);
      let searchValue = event.target.value;
      // searchValue 에서 맨앞이 #인지 확인
      let result1 = searchValue.substr(0, 1);
      // #이면 -> 태그검색 함수로 function doSearchTag
      if (result1 == '#') {
        this.doSearchTag(searchValue); //searchVal의 흐름 2. 여기서 searchValue  =  searchVal인 것을 인지시켜줌
        // #이 없으면 -> 아이디검색 함수로 function doSearchId
      } else {
        this.doSearchId(searchValue);
      }

      if (event.keyCode == 13) {
        this.doSearch()
      }
    },
    // 태그검색 함수
    doSearchTag(searchVal) { //searchVal의 흐름 1. 여기서 searchValue의 공간을 만들어주고
      this.searchResult = [];
      console.log(searchVal);
      for (let snsList of this.snsData) {
        // console.log(snsList.hashtag);
        //result2 = hashtag와 searchVal이 일치하는게있으면 0보다 같,크고 아니면 -1
        console.log(snsList);
        let result2 = snsList.indexOf(searchVal);
        // console.log(result2);
        if (result2 >= 0) {
          // console.log(snsList);
          //result3 = 나열된 해시태그들의 문자를 공백기준으로 각각 자른다
          let results3 = snsList.split(' ');
          // console.log(results3);


          let result5 = results3.filter(results4 => results4.includes(searchVal));
          console.log(result5)
          // this.searchResult.concat(result5);

          result5.forEach((element) => {
            if (!this.searchResult.includes(element)) {
              this.searchResult.push(element);
            }
          });
        }
      }
      console.log(this.searchResult);
      // console.log(this.searchResult[0]);
    },
    // snsData -> for문 HashTag를 하나씩 읽으면서
    // searchValue 가 있는지 확인
    // 있으면 화면에 출력 

    /////////////////////////////////////////////////////

    // 사용자 Id(숫자) 검색 함수
    doSearchId(searchVal) {//searchValue의 공간만들어주기
      this.searchResultNick = [];
      console.log(searchVal); // 내가 입력한거
      for (let snsList of this.searchNickname) { //
        // console.log(snsList.member_id);
        // let searchIdList = snsList.member_id;
        // console.log(searchId);
        let result2=snsList.indexOf(searchVal);
        if (result2 >= 0) {
          let results3 = snsList.split(' ');
          let result5 = results3.filter(results4 => results4.includes(searchVal));
          // console.log(this.searchId);
          result5.forEach((element) => {
            if (!this.searchResultNick.includes(element)) {
              this.searchResultNick.push(element);
            }
          });
        }
      }
      console.log(this.searchResultNick);
    }
  }
}

</script>

<style>
.sns-container {
  margin-top: 150px;
}

.sns-search-list input {
  cursor: pointer;
}
</style>