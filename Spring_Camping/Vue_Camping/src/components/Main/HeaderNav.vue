<template>
  <div>
    <div class="header-container">
      <div class="header-top-container">
        <router-link to="/" tag="div" class="header-title" @click="clickTopCategory('메인')">
          <img src="@/assets/img/logo.png" class="header-logo">
        </router-link>
        <div class="header-top-category">
          <router-link v-for="(category, key) of topCategory" :to="category" @click="clickTopCategory(key)">{{ key }}
          </router-link>
        </div>
        <div class="header-top-button">
          <router-link v-if="$store.state.email == null" to="/login" tag="div" class="header-button"><img
              src="@/assets/img/icons/login.png" alt="" @click="clickTopCategory('로그인')"></router-link>
          <router-link v-if="$store.state.email != null" to="/" tag="div" class="header-button"><img
              src="@/assets/img/icons/logout.png" alt="" @click="clickTopCategory('로그아웃')"></router-link>
          <!-- <div @click="showLoginForm()"><img src="../assets/img/login-30.png" alt=""></div> -->
          <router-link v-if="isLogin" to="/mail/mailList" tag="div" class="header-button"><img
              src="@/assets/img/icons/chat.png" alt="" @click="clickTopCategory('채팅')"></router-link>
          <!-- <router-link to="/" tag="div" class="header-button"><img src="@/assets/img/icons/alarm.png" alt=""
              @click="clickTopCategory('알람')"></router-link> -->
          <div class="header-button" @click="showMenuForm()"><img src="@/assets/img/icons/menu.png" alt=""></div>
        </div>
      </div>
      <div class="aside-right-container">
        <AsideRight @hideMenu="hideMenuForm" @loginout="setInfo"></AsideRight>
      </div>
      <div class="header-middle-container">
        <!-- <NavbarDefault dark transparent></NavbarDefault> -->
        <div v-if="($store.state.currentCategory != '0') && ($store.state.currentCategory != null)"
          class="header-middle-button">{{ auth }}
          <template v-for="(info, key) of middleCategory[$store.state.currentCategory - 1]">
            <div v-show="info[1]">
              <router-link :to="info[0]" tag="div">{{ key }}</router-link>
            </div>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AsideRight from './AsideRight.vue'
export default {
  name: "HeaderNav",
  data: function () {
    return {
      currentCategory: 0,
      isLogin: false,
      email: '',
      topCategory: {
        "어디갈래?": "/CampList",
        "같이갈래?": "/RecruList",
        "중고장터": "/used/usedMain",
        "후기피드": "/sns/" + undefined,
        "나의노트": "/MynoteList",
      },
      middleCategory: [
        {
          "캠핑장 리스트": ["/CampList", true],
          "캠핑장 등록": ["/CampRegister", false],
          "저장 목록": ["/StoredCampList", false],
        },
        {
          "같이 갈래?": ["/RecruList", true],
          "같이 가자!": ["/recru/RecruInsert", true],
          "나의 동행 모집글": ["/recru/RecruMypage", false],
          "나의 신청 목록": ["/recru/EntryMypage", false],
        },
        {
          "물건 사기": ["/used/UsedMain", true],
          "물건 팔기": ["/used/UsedInsert", false],
        },
        {
          "게시글": ["/sns/" + undefined, true],
          "글쓰기": ["/sns/write", false],
          "좋아요": ["/sns/myLikeFeed/" + this.$store.state.nickname, false],
          "태그피드": ["/sns/myTagFeed/" + this.$store.state.nickname, false],
          "나의피드": ["/sns/myFeed/" + this.$store.state.nickname, false],
        },
        {
          "이용방법": ["/MynoteList", true],
          "내가 작성한 노트": ["/MynoteList", false],
          "내가 초대받은 노트": ["/InvitedList", false],
          "노트 작성하기": ["/WriteNote", false],
        },
      ],
      responsiveFlag: false,
    }
  },
  beforeUpdate: function() {
    this.setInfo();
  },
  methods: {
    showLoginForm() {
      this.$router.push({ name: "LoginSignup" });
    },
    showMenuForm() {
      document.querySelector('.aside-right-container').style.top = (document.scrollingElement.scrollTop + 60) + 'px';
      document.querySelector('.aside-right-container').classList.toggle('active');
    },
    hideMenuForm() {
      document.querySelector('.aside-right-container').style.top = (document.scrollingElement.scrollTop + 60) + 'px';
      document.querySelector('.aside-right-container').classList.remove('active');
    },
    clickTopCategory(key) {
      if (key == "어디갈래?") {
        sessionStorage.setItem('currentCategory', '1');
        // this.$store.state.currentCategory = 1;
      } else if (key == "같이갈래?") {
        // this.$store.state.currentCategory = 2;
        sessionStorage.setItem('currentCategory', '2');
      } else if (key == "중고장터") {
        // this.$store.state.currentCategory = 5;
        sessionStorage.setItem('currentCategory', '3');
      } else if (key == "후기피드") {
        // this.$store.state.currentCategory = 3;
        sessionStorage.setItem('currentCategory', '4');
      } else if (key == "나의노트") {
        // this.$store.state.currentCategory = 4;
        sessionStorage.setItem('currentCategory', '5');
      } else if (key == "메인") {
        // this.$store.state.currentCategory = 0;
        sessionStorage.setItem('currentCategory', '0');
      } else if (key == "로그인") {
        // this.$store.state.currentCategory = 0;
        sessionStorage.setItem('currentCategory', '0');
      } else if (key == "로그아웃") {
        this.$store.state.currentCategory = 0;
        sessionStorage.setItem('currentCategory', '0');
        this.logout();
      } else if (key == "채팅") {
        // this.$store.state.currentCategory = 0;
        sessionStorage.setItem('currentCategory', '0');
      } else if (key == "알람") {
        // this.$store.state.currentCategory = 0;
        sessionStorage.setItem('currentCategory', '0');
      }
      this.$store.commit('setCurrentCategory');
    },
    logout() {
      this.$store.commit('delUserInfo');
      this.isLogin = false;
      this.$router.push({ name: "Home" });
    },
    setInfo() {
      if (this.$store.state.email != null) {
        this.isLogin = true;
      } else {
        this.isLogin = false;
      }
    },
  },
  watch: {
    isLogin: function () {
      if (!this.isLogin) {
        this.middleCategory = [
          {
            "캠핑장 리스트": ["/CampList", true],
            "캠핑장 등록": ["/CampRegister", false],
            "저장 목록": ["/StoredCampList", false],
          },
          {
            "같이 갈래?": ["/RecruList", true],
            "같이 가자!": ["/recru/RecruInsert", true],
            "나의 동행 모집글": ["/recru/RecruMypage", false],
            "나의 신청 목록": ["/recru/EntryMypage", false],
          },
          {
            "물건 사기": ["/used/UsedMain", true],
            "물건 팔기": ["/used/UsedInsert", false],
          },
          {
            "게시글": ["/sns/" + undefined, true],
            "글쓰기": ["/sns/write", false],
            "좋아요": ["/sns/myLikeFeed/" + this.$store.state.nickname, false],
            "태그피드": ["/sns/myTagFeed/" + this.$store.state.nickname, false],
            "나의피드": ["/sns/myFeed/" + this.$store.state.nickname, false],
          },
          {
            "이용방법": ["/MynoteList", true],
            "내가 작성한 노트": ["/MynoteList", false],
            "내가 초대받은 노트": ["/InvitedList", false],
            "노트 작성하기": ["/WriteNote", false],
          },
        ]
      } else {
        this.middleCategory = [
          {
            "캠핑장 리스트": ["/CampList", true],
            "캠핑장 등록": ["/CampRegister", true],
            "저장 목록": ["/StoredCampList", true],
          },
          {
            "같이 갈래?": ["/RecruList", true],
            "같이 가자!": ["/recru/RecruInsert", true],
            "나의 동행 모집글": ["/recru/RecruMypage", true],
            "나의 신청 목록": ["/recru/EntryMypage", true],
          },
          {
            "물건 사기": ["/used/UsedMain", true],
            "물건 팔기": ["/used/UsedInsert", true],
          },
          {
            "게시글": ["/sns/" + undefined, true],
            "글쓰기": ["/sns/write", true],
            "좋아요": ["/sns/myLikeFeed/" + this.$store.state.nickname, true],
            "태그피드": ["/sns/myTagFeed/" + this.$store.state.nickname, true],
            "나의피드": ["/sns/myFeed/" + this.$store.state.nickname, true],
          },
          {
            "이용방법": ["/MynoteList", true],
            "내가 작성한 노트": ["/MynoteList", true],
            "내가 초대받은 노트": ["/InvitedList", true],
            "노트 작성하기": ["/WriteNote", true],
          },
        ]
      }
    },
  },
  components: { AsideRight }
}
</script>

<style scoped src="@/assets/css/Main/HeaderNav.css">

</style>