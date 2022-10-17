<template>
  <div>
    <div class="header-container">
      <div class="header-top-container">
        <router-link to="/" tag="div" class="header-title" @click="clickTopCategory('메인')">
          <img src="@/assets/img/logo.png" class="header-logo">
        </router-link>
        <div class="header-top-category">
          <router-link v-for="(category,key) of topCategory" :to="category" @click="clickTopCategory(key)">{{key}}</router-link>
        </div>
        <div class="header-top-button">
          <router-link v-if="$store.state.email == null" to="/login" tag="div" class="header-button"><img src="@/assets/img/icons/login.png" alt="" @click="clickTopCategory('로그인')"></router-link>
          <router-link v-if="$store.state.email != null" to="/" tag="div" class="header-button"><img src="@/assets/img/icons/logout.png" alt="" @click="clickTopCategory('로그아웃')"></router-link>
          <!-- <div @click="showLoginForm()"><img src="../assets/img/login-30.png" alt=""></div> -->
          <router-link to="/" tag="div" class="header-button"><img src="@/assets/img/icons/chat.png" alt="" @click="clickTopCategory('채팅')"></router-link>
          <router-link to="/" tag="div" class="header-button"><img src="@/assets/img/icons/alarm.png" alt="" @click="clickTopCategory('알람')"></router-link>
          <div class="header-button" @click="showMenuForm()"><img src="@/assets/img/icons/menu.png" alt=""></div>
        </div>
      </div>
      <div class="aside-right-container">
        <AsideRight @hideMenu="hideMenuForm"></AsideRight>
      </div>
      <div class="header-middle-container">
        <!-- <NavbarDefault dark transparent></NavbarDefault> -->
        <div v-if="currentCategory!=0" class="header-middle-button">
          <div v-for="(info,key) of middleCategory[currentCategory-1]">
            <router-link :to="info" tag="div">{{key}}</router-link>
          </div>
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
      email : localStorage.getItem("email"),
      currentCategory: 0,
      topCategory: {
        "어디갈래?" : "/CampList",
        "같이갈래?" : "/RecruList",
        "중고장터" : "/used/usedMain",
        "후기피드" : "/sns",
        "나의노트" : "/MynoteList"
      },
      middleCategory: [
        {
          "캠핑장 리스트": "/CampList",
          "캠핑장 등록": "/CampRegister",
          "저장 목록": "/StoredCampList"
        },
        {
          "같이 갈래?": "/RecruList",
          "같이 가자!": "/recru/RecruInsert",
          "나의 동행 모집글": "/recru/RecruMypage",
          "나의 신청 목록": "/recru/EntryMypage",
          "보증금 관리": "/recru/DepositMypage"
        },
        {
          "게시글": "/sns",
          "글쓰기": "/sns/write",
          "좋아요": "/sns",
          "나의피드": "/sns/myFeed/:nickname",
        },
        {
          "이용방법": "/MynoteList",
          "내가 작성한 노트": "/MynoteList",
          "내가 초대받은 노트": "/InvitedList",
          "노트 작성하기": "/WriteNote"
        },
        {
          "물건 사기":"/used/UsedMain",
          "물건 팔기":"/used/UsedInsert"
        }
      ],
    }
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
      if(key == "어디갈래?") {
        this.currentCategory = 1;
      } else if (key == "같이갈래?") {
        this.currentCategory = 2;
      } else if (key == "중고장터") {
        this.currentCategory = 5;
      } else if (key == "후기피드") {
        this.currentCategory = 3;
      } else if (key == "나의노트") {
        this.currentCategory = 4;
      } else if (key == "메인") {
        this.currentCategory = 0;
      } else if (key == "로그인") {
        this.currentCategory = 0;
      } else if (key == "로그아웃") {
        this.currentCategory = 0;
        this.logout();
      } else if (key == "채팅") {
        this.currentCategory = 0;
      } else if (key == "알람") {
        this.currentCategory = 0;
      }
    },
    logout() {
      this.$store.commit('delUserInfo');
      this.$router.push({name:"Home"});
    }
  },
  components: { AsideRight }
}
</script>

<style scoped src="@/assets/css/Main/HeaderNav.css">

</style>