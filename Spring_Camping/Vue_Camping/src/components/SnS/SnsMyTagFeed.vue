<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <SnsSearch @showHashList="showHashList"></SnsSearch>
    </div>
    <div class="sns-page-nickname-container">
      <div class="sns-page-nickname">
        <span>{{snsMyData.nickname}}</span>
      </div>
    </div>


    <!--  -->
    <div class="sns-detail-page-container">
      <div class="sns-write-id-container">
        <div class="sns-write-form-id">
          <div class="sns-write-id">
            <img :src="'http://13.125.95.210:85/java/profile/'+storedProfile.imagePath+'/'+storedProfile.storedName">
          </div>
        </div>
        <div class="sns-write-form-id">
          <div class="sns-write-id">
            <p>댓글 태그된 게시글 수 : {{this.snsMyCount}}</p>
          </div>
        </div>
      </div>
      <div class="sns-profile">
        <div class="sns-profile-email">
          <p>{{this.email}}</p>
        </div>
        <div class="sns-profile-text">
          <textarea :value="snsMyData.profileInfo" readonly></textarea>
          <!-- / <textarea v-model="snsWriteText" placeholder="내용을 입력하세요"></textarea> -->
        </div>
      </div>
    </div>
    <!--  -->

    <div class="sns-img-container">
      <div class="sns-img-row-container" :key="snsMyTagList.writeNo" v-for="snsMyTagList in snsMyTagLists"
        @click="getSnsDetail(snsMyTagList.writeNo)">
        <div class="sns-img">
          <input type="text" :value="snsMyTagList.writeNo" style="display :none;" readonly>
          <img :src="'http://13.125.95.210:85/java/showSnsImage/'+snsMyTagList.snsPath+'/'+snsMyTagList.storedName">
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import SnsSearch from './SnsSearch.vue';

export default {
  created: function () {
    // 이메일 + 프로필소개글 + 닉네임 가져오기
    fetch('http://13.125.95.210:85/java/member/' + this.email)
      .then(response => response.json())
      .then(result => {
        console.log(result);
        this.snsMyData = result
      })
      .catch(err => console.log(err));

    console.log(this.snsMyData);

    // 작성자의 좋아요 총 게시글 숫자 표시
    fetch('http://13.125.95.210:85/java/memberTagCount/' + this.nickname)
      .then(response => response.json())
      .then(result => {
        console.log(result);
        this.snsMyCount = result
      })
      .catch(err => console.log(err));

    console.log(this.snsMyCount);

    // 로그인한 유저가 좋아요한 게시글목록 리스트
    fetch('http://13.125.95.210:85/java/memberSnsTagList/' + this.nickname + "/" + this.page)
      .then(response => response.json())
      .then(result => {
        console.log(result);
        this.snsMyTagLists = result
      })
      .catch(err => console.log(err));

    console.log(this.snsMyTagLists);

    //프로필 이미지
    fetch('http://13.125.95.210:85/java/profile/' + this.email)
      .then(result => result.json())
      .then(result => {
        this.storedProfile = result;
      }).catch(err => console.log(err));
  },
  data: function () {
    return {
      snsSearch: '검색어를 입력하세요.',
      searchText: '',
      //마이페이지 정보 불러오기
      //닉네임 불러오기
      //닉네임, 
      //로그인하며 가져오는 것 목록 : email, nickname, auth
      email: this.$store.state.email,
      nickname : this.$store.state.nickname,
      // profileInfo : this.$store.state.profileInfo,
      snsMyData: {},
      //총게시글수
      snsMyCount: "",
      //내가 태그당한 게시글 리스트
      snsMyTagLists: [],
      //프로필 이미지
      storedProfile: '',
      //페이지
      page: 1,
    }
  },
  //검색
  methods: {
    doSearch() {
      console.log(this.searchText)
    },
    checkEnter(event) {
      if (event.keyCode == 13) {
        this.doSearch()
      }
    },
    //상세페이지
    getSnsDetail(writeNo) {
      this.$router.push({ name: 'SnsDetail', params: { writeNo: writeNo } });
    }
  },
  components: {
    SnsSearch
  },
}
</script>

<style scoped src="@/assets/css/sns/SnsMyFeed.css">
</style>