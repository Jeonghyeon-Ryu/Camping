<template>
  <div class="my-info">
    <div class="my-info-left">
      <div class="my-info-left-top">
        <div class="profile-image">
          <img :src="'http://localhost:8087/java/profile/' + storedProfile.imagePath + '/' + storedProfile.storedName">
          <!-- <ImagePreviewOne :image="profile_image"></ImagePreviewOne> -->
        </div>
        <div class="nickname" v-text="user.nickname"></div>
        <div class="profile-intro" v-text="user.profileInfo">
        </div>
      </div>
      <div class="my-info-left-bottom">
        <div class="my-info-left-button" @click="user_modify_toggle">기본정보 변경</div>
        <div class="my-info-left-button" @click="user_modify_password">비밀번호 변경</div>
        <div class="my-info-left-button" @click="user_delete">회원탈퇴</div>
      </div>
    </div>
    <div class="my-info-right">
      <h2>계정 정보</h2>
      <form class="user-info-form">
        <div class="profile-image-modify">
          <div class="profile-image-preview">
            <img v-if="imgUrl != ''" :src="imgUrl">
            <img v-if="imgUrl == ''"
              :src="'http://localhost:8087/java/profile/' + storedProfile.imagePath + '/' + storedProfile.storedName">
            <!-- <ImagePreviewOne ref="previewOne" v-if="profile-image!=[]" :image="profile_image"></ImagePreviewOne> -->
          </div>
          <label>사진변경
            <input @change="changeImage($event)" type="file" name="file" style="display:none;">
          </label>
        </div>
        <div class="profile-info-modify">
          <div>
            <input type="text" v-bind:value="user.email" placeholder="이메일" name="email" readonly />
            <input type="text" v-bind:value="user.nickname" placeholder="닉네임" name="nickname" disabled />
            <input type="button" value="비밀번호 변경" @click="user_modify_password" />
          </div>
          <div>
            <input type="text" v-bind:value="user.name" placeholder="이름" name="name" disabled />
            <input type="text" v-bind:value="user.birth" placeholder="생년월일" name="birth" disabled />
            <input type="text" v-bind:value="user.phoneNumber" placeholder="연락처" name="phoneNumber" disabled />
          </div>
        </div>
        <div>
          <textarea :value="user.profileInfo" name="profileInfo" disabled></textarea>
        </div>
        <div class="btn-modify-container">
          <button v-if="isModify" type="button" class="btn-modify" @click="user_modify_confirm()">수정완료</button>
          <button v-if="isModify" type="button" class="btn-modify" @click="user_modify_cancel()">취소</button>
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
      storedProfile: '',
      isModify: false
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
    user_modify_toggle: function () {
      if (!this.isModify) {
        document.querySelector('input[name="nickname"]').disabled = false;
        document.querySelector('input[name="name"]').disabled = false;
        document.querySelector('input[name="birth"]').disabled = false;
        document.querySelector('input[name="phoneNumber"]').disabled = false;
        document.querySelector('textarea').disabled = false;
        this.isModify = true;
      } else {
        document.querySelector('input[name="nickname"]').disabled = true;
        document.querySelector('input[name="name"]').disabled = true;
        document.querySelector('input[name="birth"]').disabled = true;
        document.querySelector('input[name="phoneNumber"]').disabled = true;
        document.querySelector('textarea').disabled = true;
        this.isModify = false;
      }
    },
    user_modify_confirm: function () {
      let fetchData = new FormData(document.querySelector('.user-info-form'));
      fetchData.append("status", 2);
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
          document.querySelector('input[name="email"]').value = this.user.email;
          document.querySelector('input[name="nickname"]').value = this.user.nickname;
          document.querySelector('input[name="name"]').value = this.user.name;
          document.querySelector('input[name="birth"]').value = this.user.birth;
          document.querySelector('input[name="phoneNumber"]').value = this.user.phoneNumber;
          document.querySelector('textarea').value = this.user.profileInfo;
          this.user_modify_toggle();
        }
      })
    },

    user_modify_password: function () {
      Swal.fire({
        title: '비밀번호 변경',
        html:
          '<input id="swal-input1" class="swal2-input" placeholder="이전 비밀번호">' +
          '<input id="swal-input2" class="swal2-input" placeholder="변경할 비밀번호">' +
          '<input id="swal-input3" class="swal2-input" placeholder="비밀번호 확인">',
        focusConfirm: false,
        confirmButtonText: '변경완료',
        showCancelButton: true,
        cancelButtonText: '변경취소',
        preConfirm: () => {
          let prevPw = document.getElementById('swal-input1');
          let nextPw1 = document.getElementById('swal-input2');
          let nextPw2 = document.getElementById('swal-input3');

          if (!this.isPassword(nextPw1)) {
            return false;
          } else if (prevPw.value == '') {
            Swal.showValidationMessage(`이전 비밀번호를 입력해주세요.`);
            prevPw.focus();
          } else if (nextPw1.value == '') {
            Swal.showValidationMessage(`변경할 비밀번호를 입력해주세요.`);
            nextPw1.focus();
          } else if (nextPw2.value == '') {
            Swal.showValidationMessage(`비밀번호 확인을 해주세요.`);
            nextPw2.focus();
          } else if (nextPw1.value != nextPw2.value) {
            Swal.showValidationMessage(`변경할 비밀번호가 일치하지 않습니다.`);
            nextPw2.value = '';
            nextPw2.focus();
          } else {
            fetch('http://localhost:8087/java/member/' + this.$store.state.email + '/' + prevPw.value)
              .then(result => result.text())
              .then(result => {
                console.log('이전 비밀번호와 비교', result);
                if (result == "true") {
                  fetch('http://localhost:8087/java/member', {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                      email: this.$store.state.email,
                      password: nextPw1.value,
                      auth: 1,
                      status: 2
                    })
                  }).then(result => result.text())
                    .then(result => {
                      if (result == 'true') {
                        Swal.fire({
                          icon: 'success',
                          title: '비밀번호가 변경되었습니다 !',
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
                    })
                } else {

                }
              })
              .catch(err => console.log(err))
          }


          return false;
        }
      }).then(result => {
        if (result.isConfirmed) {
        } else {
          // 닫기
        }
      })
    },
    regCheck: function (regExp, asValue) {
      if (regExp.test(asValue.value)) {
        asValue.style.background = 'rgba(0,255,0,0.1)';
        return true;
      } else {
        asValue.style.background = 'rgba(255,0,0,0.1)';
        Swal.fire({
          icon: 'error',
          title: '형식에 맞게 입력해주세요.',
          text: '8~16자리 특수문자 포함',
          toast: true,
          showConfirmButton: false,
          timer: 1500,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        }).then((result => {
          asValue.value = '';
          asValue.focus();
          return false;
        }))
      }
    },
    isPassword: function (target) {
      let asValue = target;
      let regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*^?&\\(\\)\-_=+]).{8,16}$/;

      return this.regCheck(regExp, asValue);
    },
    user_delete: function () {
      Swal.fire({
        title: '비밀번호 변경',
        html: '<input id="swal-input1" class="swal2-input" placeholder="이전 비밀번호">',
        focusConfirm: false,
        confirmButtonText: '탈퇴',
        showCancelButton: true,
        cancelButtonText: '취소',
        preConfirm: () => {
          let prevPw = document.getElementById('swal-input1');
          let fetchData = new FormData();

          fetchData.append('email', this.$store.state.email);
          fetchData.append('status', 0);

          if (prevPw.value == '') {
            Swal.showValidationMessage(`비밀번호를 입력해주세요.`);
            prevPw.focus();
          } else {
            fetch('http://localhost:8087/java/member/' + this.$store.state.email + '/' + prevPw.value)
              .then(result => result.text())
              .then(result => {
                console.log('이전 비밀번호와 비교', result);
                if (result == "true") {
                  fetch('http://localhost:8087/java/member/modify', {
                    method: 'POST',
                    headers: {},
                    body: fetchData
                  }).then(result => result.text())
                    .then(result => {
                      if (result == 'true') {
                        Swal.fire({
                          icon: 'success',
                          title: '회원탈퇴 성공 !',
                          toast: true,
                          position: 'center-center',
                          showConfirmButton: false,
                          timer: 1500,
                          timerProgressBar: true,
                          didOpen: (toast) => {
                            toast.addEventListener('mouseenter', Swal.stopTimer)
                            toast.addEventListener('mouseleave', Swal.resumeTimer)
                            this.$store.commit('delUserInfo');
                            this.$router.push('/');
                          }
                        })
                      } else {
                        Swal.fire({
                          icon: 'error',
                          title: '회원탈퇴가 되지 않았습니다 !',
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
                    })
                } else {
                  Swal.fire({
                    icon: 'error',
                    title: '비밀번호가 일치하지 않습니다.',
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
              })
              .catch(err => console.log(err))
          }
          return false;
        }
      })
    },
  }
}
</script>
<style scoped src="@/assets/css/User/UserInfo.css">

</style>