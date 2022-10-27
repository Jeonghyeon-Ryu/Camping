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
            <img :src="'http://localhost:8087/java/profile/'+storedProfile.imagePath+'/'+storedProfile.storedName">
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
          <img :src="'http://localhost:8087/java/showSnsImage/'+snsMyTagList.snsPath+'/'+snsMyTagList.storedName">
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
    fetch('http://localhost:8087/java/member/' + this.email)
      .then(response => response.json())
      .then(result => {
        console.log(result);
        this.snsMyData = result
      })
      .catch(err => console.log(err));

    console.log(this.snsMyData);

    // 작성자의 좋아요 총 게시글 숫자 표시
    fetch('http://localhost:8087/java/memberTagCount/' + this.nickname)
      .then(response => response.json())
      .then(result => {
        console.log(result);
        this.snsMyCount = result
      })
      .catch(err => console.log(err));

    console.log(this.snsMyCount);

    // 로그인한 유저가 좋아요한 게시글목록 리스트
    fetch('http://localhost:8087/java/memberSnsTagList/' + this.nickname + "/" + this.page)
      .then(response => response.json())
      .then(result => {
        console.log(result);
        this.snsMyTagLists = result
      })
      .catch(err => console.log(err));

    console.log(this.snsMyTagLists);

    //프로필 이미지
    fetch('http://localhost:8087/java/profile/' + this.email)
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


<style scoped>
/* .sns-container{
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
    }
    .sns-image{
      
      width: 30%;
    } */

.sns-container {
  width: 100vw;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.sns-searchbox {
  width: 100vw;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin-bottom: 10px;
  margin-top: 10px;
}

.sns-searchbox input[type=text] {
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.sns-searchbox input[type=button] {
  padding: 15px;
  border: none;
  border-radius: 5px;
  background-color: rgb(255, 255, 255);
}

.sns-page-nickname-container {
  display: flex;
  justify-content: center;
}

.sns-page-nickname {
  background-color: rgba(228, 239, 231, 0.7);
  width: 56vw;
  height: 40px;
  min-width: 800px;
  border-radius: 5px;
  display: flex;
  justify-content: center;
  color: rgb(255, 255, 255);
  font-weight: bold;
}

.sns-page-nickname span {
  font-weight: bold;
  margin-top: 7px;

}

.sns-detail-page-container {
  width: 60vw;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
}

.sns-detail-container {
  width: 100%;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  margin-top: 15px;
}

.sns-write-id-container {
  width: 100%;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  margin-top: 15px;
  position: relative;
}

.sns-write-id img {
  width: 3vw;
  height: 3vw;
  border-radius: 70%;
  margin-right: 1vw;
}

.sns-write-id p {
  margin-top: 0.5vw;
  padding: 0.3vw;
  border: none;
}

.sns-img-container {
  width: 70%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.sns-img-row-container {
  width: 30%;
  height: 350px;
  cursor: pointer;
}

.sns-img {
  margin-bottom: 10px;
  margin-right: 10px;
  cursor: pointer;
  width: 100%;
  height: 100%;

}

.sns-img img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.sns-profile {
  width: 100%;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  margin-top: 15px;
  position: relative;
}

.sns-profile-email {
  width: 100%;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  margin-top: 15px;
  position: relative;

}

.sns-profile-email p {
  border: none;

}

.sns-profile-text textarea {

  margin-top: 15px;
  width: 28vw;
  height: 5vw;
  min-width: 184px;
  min-height: 20px;
  border: none;
  resize: none;
  border-radius: 5px;
}

.sns-profile-text textarea:focus {
  outline: none;
}
</style>