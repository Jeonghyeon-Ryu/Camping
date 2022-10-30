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
            <p>{{this.snsMyCount}}</p>
          </div>
        </div>
      </div>
      <div class="sns-profile">
        <div class="sns-profile-email">
          <p>{{this.snsMyData.email}}</p>
        </div>
        <div class="sns-profile-text">
          <textarea :value="snsMyData.profileInfo" readonly></textarea>
          <!-- / <textarea v-model="snsWriteText" placeholder="내용을 입력하세요"></textarea> -->
        </div>
      </div>
    </div>
    <!--  -->

    <div class="sns-img-container">
      <div class="sns-img-row-container" :key="snsMyList.writeNo" v-for="snsMyList in snsMyLists"
        @click="getSnsDetail(snsMyList.writeNo)">
        <div class="sns-img">
          <input type="text" :value="snsMyList.writeNo" style="display :none;" readonly>
          <img :src="'http://13.125.95.210:85/java/showSnsImage/'+snsMyList.snsPath+'/'+snsMyList.storedName">
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
    var nick = this.$route.params.nickname

    //닉네임을 이용해서 닉네임, 이메일, 프로필 소개글을 가져오는걸로 바꿈
    //왜냐면 헤더네비에서 눌렀을때는 store가 맞는데 검색창으로 했을땐 누는 사람의 닉네임에 맞게 들어가줘야하니까 엄청 헷갈림.. 내용정리필요
    fetch('http://13.125.95.210:85/java/emailByNickname/' + nick)
      .then(response => response.json())
      .then(result => {
        console.log(result);
        this.snsMyData = result

        //위의 패치문이 실행되고 난 이후에 실행되어야 하는 애들
        // 작성자의 총 게시글 숫자 표시
        fetch('http://13.125.95.210:85/java/memberCount/' + this.snsMyData.email)
          .then(response => response.json())
          .then(result => {
            console.log(result);
            this.snsMyCount = result
          })
          .catch(err => console.log(err));

        console.log(this.snsMyCount);

        // 해당 유저가 작성한 게시글 리스트
        // 페이징
        fetch('http://13.125.95.210:85/java/memberSnsList/' + this.snsMyData.email + "/" + this.page)
          .then(response => response.json())
          .then(result => {
            console.log(result);
            this.snsMyLists = result
          })

          .catch(err => console.log(err));

        console.log(this.snsMyList);


        //프로필 이미지
        fetch('http://13.125.95.210:85/java/profile/' + this.snsMyData.email)
          .then(result => result.json())
          .then(result => {
            this.storedProfile = result;
          }).catch(err => console.log(err));

      })
      .catch(err => console.log(err));

    console.log(this.snsMyData);
  },
  data: function () {
    return {
      snsSearch: '검색어를 입력하세요.',
      searchText: '',
      //마이페이지 정보 불러오기
      //닉네임 불러오기
      //닉네임, 
      //로그인하며 가져오는 것 목록 : email, nickname, auth
      nickname: this.$route.params.nikcname,
      email: this.$store.state.email,
      // profileInfo : this.$store.state.profileInfo,
      snsMyData: {},
      //총게시글수
      snsMyCount: "",
      //나의 게시글 리스트
      snsMyLists: [],
      //프로필 이미지
      storedProfile: '',
      //페이지
      page: 1,

      snsProfileText: '프로필 소개글 안냐세용 게시글 소개글입니다 그만하고싶고요 힘들고요 저는 그냥 말하는 감자고요 아무것도 안됩니다 누르지마세요',
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
    },
    
  },
  components: {
    SnsSearch
  },
}
</script>


<style scoped src="@/assets/css/sns/SnsMyFeed.css">
</style>