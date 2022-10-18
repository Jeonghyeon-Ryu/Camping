<template>
  <div class="my-info">
    <div class="my-info-left">
      <div class="my-info-left-top">
        <div class="profile-image">
          <img :src="'http://localhost:8087/java/profile/'+storedProfile.imagePath+'/'+storedProfile.storedName">
          <!-- <ImagePreviewOne :image="profile_image"></ImagePreviewOne> -->
        </div>
        <div class="nickname" v-text="user.nickname"></div>
        <div class="profile-intro" v-text="user.profileInfo">
        </div>
      </div>
      <div class="my-info-left-bottom">
        <div class="my-info-left-button">기본정보 변경</div>
        <div class="my-info-left-button">비밀번호 변경</div>
      </div>
    </div>
    <div class="my-info-right">
      <h2>Account Settings</h2>
      <form class="user-info-form">
        <div class="profile-image-modify">
          <div class="profile-image-preview">
            <img v-if="imgUrl != ''" :src="imgUrl">
            <img v-if="imgUrl == ''"
              :src="'http://localhost:8087/java/profile/'+storedProfile.imagePath+'/'+storedProfile.storedName">
            <!-- <ImagePreviewOne ref="previewOne" v-if="profile-image!=[]" :image="profile_image"></ImagePreviewOne> -->
          </div>
          <label>사진변경
            <input @change="changeImage($event)" type="file" name="file" style="display:none;">
          </label>
        </div>
        <div class="profile-info-modify">
          <div>
            <input type="text" v-bind:value="user.email" placeholder="이메일" name="email" readonly />
            <input type="text" v-bind:value="user.nickname" placeholder="닉네임" name="nickname" />
            <input type="button" value="비밀번호 변경" name="nickname" @click="user_modify_password" />
          </div>
          <div>
            <input type="text" v-bind:value="user.name" placeholder="이름" name="name" />
            <input type="text" v-bind:value="user.birth" placeholder="생년월일" name="birth" />
            <input type="text" v-bind:value="user.phoneNumber" placeholder="연락처" name="phoneNumber" />
          </div>
        </div>
        <div>
          <textarea :value="user.profileInfo" name="profileInfo"></textarea>
        </div>
        <div class="btn-modify-container">
          <button type="button" class="btn-modify" @click="user_modify_confirm()">수정완료</button>
          <button type="button" class="btn-modify" @click="user_modify_cancel()">취소</button>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import { functionTypeAnnotation } from '@babel/types';
import ImagePreview from '../ImagePreview.vue';
import Swal from 'sweetalert2';
import ImagePreviewOne from '../ImagePreviewOne.vue';
export default {
  data: function () {
    return {
      user: {},
      imgUrl: '',
      storedProfile: ''
    };
  },
  created: function () {
    fetch('http://localhost:8087/java/member/' + this.$store.state.email)
      .then(result => result.json())
      .then(result => {
        this.user = result;
      }).catch(err => console.log(err));

    fetch('http://localhost:8087/java/profile/' + this.$store.state.email)
      .then(result => result.json())
      .then(result => {
        this.storedProfile = result;
      }).catch(err => console.log(err));
  },
  components: { ImagePreview, ImagePreviewOne },
  methods: {
    changeImage: function (e) {
      this.imgUrl = URL.createObjectURL(e.target.files[0]);
    },
    user_modify_confirm: function () {
      let fetchData = new FormData(document.querySelector('.user-info-form'));
      fetchData.forEach((value, key) => {
        console.log(value)
      })
      Swal.fire({
        title: '정말 수정하시겠습니까?',
        text: '회원 정보가 수정됩니다..',
        showCancelButton: true,
        confirmButtonText: '취소',
        cancelButtonText: '수정'
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
          Swal.fire('취소 !', '', 'success')
        } else {
          fetch('http://localhost:8087/java/member/modify', {
            method: 'POST',
            headers: {},
            body: fetchData
          }).then(result => result.text())
            .then(result => {
              if (result == "true") {
                Swal.fire({
                  icon: 'success',
                  title: '회원정보가 수정되었습니다 !',
                  toast: true,
                  position: 'center-center',
                  showConfirmButton: false,
                  timer: 1500,
                  timerProgressBar: true,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                    this.$router.go(0);
                  }
                })
              } else {
                Swal.fire({
                  icon: 'error',
                  title: '회원정보가 수정 되지 않았습니다 !',
                  text: '계속 오류가 발생하면 고객센터로 문의해주세요.',
                  toast: true,
                  position: 'center-center',
                  showConfirmButton: false,
                  timer: 1500,
                  timerProgressBar: true,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
                })
              }
            });
        }
      })
    },
    user_modify_cancel: function () {
      Swal.fire({
        title: '등록을 취소하시겠습니까?',
        text: '입력한 내용이 삭제됩니다.',
        showCancelButton: true,
        confirmButtonText: '취소',
        cancelButtonText: '계속 작성'
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
          Swal.fire('취소 !', '', 'success')
        }
      })
    },

    user_modify_password: function () {
      const { value: password } = Swal.fire({
        title: 'Enter your password',
        input: 'password',
        inputLabel: 'Password',
        inputPlaceholder: 'Enter your password',
        inputAttributes: {
          maxlength: 10,
          autocapitalize: 'off',
          autocorrect: 'off'
        }
      })
    }
  }
}
</script>
<style scoped src="@/assets/css/User/UserInfo.css">

</style>