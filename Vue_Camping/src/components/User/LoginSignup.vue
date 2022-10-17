<template>
  <div class="login_container">
    <div class="cont_centrar">
      <div class="cont_login">
        <div class="cont_info_log_sign_up">
          <div class="col_md_login">
            <div class="cont_ba_opcitiy">
              <h2>Login</h2>
              <p>드루와드루와</p>
              <button class="btn_login" @click="click_login()">LOGIN</button>
            </div>
          </div>
          <div class="col_md_sign_up">
            <div class="cont_ba_opcitiy">
              <h2>Sign Up</h2>
              <p>캠핑 가족이 되어주세요 !</p>
              <button class="btn_sign_up" @click="click_sign_up()">SIGN UP</button>
            </div>
          </div>
        </div>
        <div class="cont_back_info">
          <div class="cont_img_back_grey">
            <img src="@/assets/img/login/login_background.jpg" alt="" />
          </div>
        </div>
        <div class="cont_forms">
          <div class="cont_img_back_">
            <img src="@/assets/img/login/login_background.jpg" alt="" />
          </div>
          <div class="cont_form_login">
            <a href="#" @render-tracked="showLoginSignup()"><i class="material-icons">&#xE5C4;</i></a>
            <h2>LOGIN</h2>
            <input type="text" placeholder="Email" />
            <input type="password" placeholder="Password" />
            <button class="btn_login" @click="clickLogin($event)">LOGIN</button>
            <button class="btn_login">KAKAO</button>
            <button class="btn_login">NAVER</button>
          </div>
          <div class="cont_form_sign_up">
            <a href="#" @click="showLoginSignup()"><i class="material-icons">&#xE5C4;</i></a>
            <h2>SIGN UP</h2>
            <input type="text" @change="isEmail($event)" @blur="isEmail($event)" name="email" placeholder="이메일"
              autocomplete="off" />
            <input type="text" name="name" placeholder="이름" autocomplete="off" />
            <input type="text" @change="isNickname($event)" name="nickname" placeholder="닉네임" autocomplete="off" />
            <input type="password" @change="isPassword($event)" name="password" placeholder="비밀번호" />
            <input type="password" @change="isPasswordConfirm($event)" name="password_confirm" placeholder="비밀번호 확인" />
            <input type="text" @change="isIdentify($event)" name="identify" placeholder="주민등록번호(900101-1)" maxlength="8"
              autocomplete="off" />
            <input type="text" @change="isPhoneNumber($event)" name="phone_number" placeholder="01012341234"
              maxlength="11" autocomplete="off" />
            <button class="btn_sign_up" @click="clickSignup($event)">SIGN UP</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Swal from 'sweetalert2';
const { IMP } = window;
export default {
  methods: {
    click_login: () => {
      document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
      document.querySelector('.cont_form_login').style.display = "block";
      document.querySelector('.cont_form_sign_up').style.opacity = "0";

      setTimeout(function () { document.querySelector('.cont_form_login').style.opacity = "1"; }, 400);

      setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
      }, 200);
    },
    click_sign_up: () => {
      document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
      document.querySelector('.cont_form_sign_up').style.display = "block";
      document.querySelector('.cont_form_login').style.opacity = "0";

      setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.opacity = "1";
      }, 100);

      setTimeout(function () {
        document.querySelector('.cont_form_login').style.display = "none";
      }, 400);
    },
    clickLogin: function (e) {
      let loginForm = e.target.parentElement;
      let member = {
        "email": loginForm.querySelector('input[type="text"]').value,
        "password": loginForm.querySelector('input[type="password"]').value
      };
      console.log(member)
      fetch('http://localhost:8087/java/login', {
        method: "POST",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(member)
      }).then(result => result.json())
        .then(result => {
          localStorage.setItem("nickname", result.nickname);
          localStorage.setItem("email", result.email);
          localStorage.setItem("auth", result.auth);
          this.$store.commit('getUserInfo');
          Swal.fire({
            icon: 'success',
            title: '로그인 성공!',
            toast: true,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
              this.$router.replace('/');
            }
          })
        }).catch(error => {
          Swal.fire({
            icon: 'error',
            title: '아이디 또는 비밀번호가 일치하지 않습니다.',
            toast: true,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
              loginForm.querySelector('input[type="password"]').value = '';
              loginForm.querySelector('input[type="text"]').focus();
            }
          })
        });
    },
    clickSignup: (e) => {
      let signupForm = e.target.parentElement;
      let identify = signupForm.querySelector('input[name="identify"]').value;
      let sex = '';
      let birth = '';

      if (identify.substr(-1, 1) % 2 == 0) {
        sex = "여";
      } else {
        sex = "남";
      }
      if (identify.substr(-1, 1) == 1 || identify.substr(-1, 1) == 2) {
        birth = '19';
      } else if (identify.substr(-1, 1) == 3 || identify.substr(-1, 1) == 4) {
        birth = '20';
      }
      birth = birth + identify.substr(0, 2) + '-' + identify.substr(2, 2) + '-' + identify.substr(4, 2);

      let signupData = {
        "email": signupForm.querySelector('input[name="email"]').value,
        "name": signupForm.querySelector('input[name="name"]').value,
        "nickname": signupForm.querySelector('input[name="nickname"]').value,
        "password": signupForm.querySelector('input[name="password"]').value,
        "birth": birth,
        "sex": sex,
        "phone_number": signupForm.querySelector('input[name="phone_number"]').value
      }

      // 본인인증 시작
      let access_token;
      let imp_uid;
      IMP.init("imp77170587");
      IMP.certification({
        merchant_uid: `${new Date().getTime()}`,
      }, function (result) {
        if (result.success) {
          console.log("성공", result.imp_uid);
          imp_uid = result.imp_uid;
          console.log(imp_uid);
          fetch('http://localhost:8087/java/auth?impUid=' + imp_uid)
            .then(result => result.json())
            .then(result => {
              if (result.name != signupForm.querySelector('input[name="name"]').value) {
                document.querySelector('input[name="name"]').value = result.name;
              }
              if (result.phoneNumber != signupForm.querySelector('input[name="phone_number"]').value) {
                document.querySelector('input[name="name"]').value = result.phoneNumber;
              }
              // 본인인증 성공 후, 회원가입 자동 요청
              fetch('http://localhost:8087/java/member', {
                method: "POST",
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify(signupData)
              }).then(result => result.text())
                .then(result => {
                  if (result == "true") {
                    this.$router.push({ path: '/', name: 'Home' });
                  } else {
                    Swal.fire({
                      icon: 'error',
                      title: '알수없는 오류로 회원가입에 실패하였습니다.',
                      toast: true,
                      showConfirmButton: false,
                      timer: 2000,
                      timerProgressBar: true,
                      didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                      }
                    })
                  }
                }).catch(err => console.log("회원가입 오류", err))
            }).catch(err => console.log("본인인증 오류", err));

        } else {
          console.log("실패", result);
        }
      })
    },
    showLoginSignup: () => {
      document.querySelector('.cont_forms').className = "cont_forms";
      document.querySelector('.cont_form_sign_up').style.opacity = "0";
      document.querySelector('.cont_form_login').style.opacity = "0";

      setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
        document.querySelector('.cont_form_login').style.display = "none";
      }, 500);
    },
    regCheck: function (regExp, asValue) {
      if (regExp.test(asValue.value)) {
        asValue.style.background = 'rgba(0,255,0,0.1)';
      } else {
        asValue.style.background = 'rgba(255,0,0,0.1)';
        Swal.fire({
          icon: 'error',
          title: '형식에 맞게 입력해주세요.',
          toast: true,
          showConfirmButton: false,
          timer: 2000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        }).then((result => {
          asValue.value = '';
          asValue.focus();
        }))
      }
    },
    isPhoneNumber: function (e) {
      let asValue = e.target;
      let regExp = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
      this.regCheck(regExp, asValue);
    },

    isEmail: function (e) {
      let asValue = e.target;
      let regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
      let url = 'http://localhost:8087/java/email?email=' + e.target.value
      fetch(url).then(result => result.text())
        .then(result => {
          if (result == "true") {
            this.regCheck(regExp, asValue);
          } else {
            asValue.style.background = 'rgba(255,0,0,0.1)';
            Swal.fire({
              icon: 'error',
              title: '이미 존재하는 이메일입니다.',
              toast: true,
              showConfirmButton: false,
              timer: 2000,
              timerProgressBar: true,
              didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
              }
            }).then((result => {
              asValue.value = '';
              asValue.focus();
            }))
          }
        })
        .catch(error => console.log(error));
    },
    isNickname: function (e) {
      let asValue = e.target;
      let url = 'http://localhost:8087/java/nickname?nickname=' + e.target.value
      fetch(url).then(result => result.text())
        .then(result => {
          if (result == "true") {
            asValue.style.background = 'rgba(0,255,0,0.1)';
          } else {
            asValue.style.background = 'rgba(255,0,0,0.2)';
            Swal.fire({
              icon: 'error',
              title: '이미 존재하는 닉네임입니다.',
              toast: true,
              showConfirmButton: false,
              timer: 2000,
              timerProgressBar: true,
              didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
              }
            }).then((result => {
              asValue.value = '';
              asValue.focus();
            }))
          }
        })
        .catch(error => console.log(error));
    },
    isPassword: function (e) {
      let asValue = e.target;
      let regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;

      this.regCheck(regExp, asValue);
    },

    isPasswordConfirm: function (e) {
      let password = e.target.previousSibling.value;
      let passwordConfirm = e.target.value;
      if (password == passwordConfirm) {
        e.target.style.background = 'rgba(0,255,0,0.2)';
      } else {
        e.target.style.background = 'rgba(255,0,0,0.2)';
        Swal.fire({
          icon: 'error',
          title: '비밀번호가 일치하지 않습니다.',
          toast: true,
          showConfirmButton: false,
          timer: 2000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        }).then((result => {
          e.target.value = '';
          e.target.focus();
        }))
      }
    },
    isIdentify: function (e) {
      let asValue = e.target;
      let regExp = /^\d{2}([0]\d|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])[-]*[1-4]$/;
      this.regCheck(regExp, asValue);
    }
  }
}
</script>

<style scoped src="@/assets/css/User/LoginSignup.css">

</style>