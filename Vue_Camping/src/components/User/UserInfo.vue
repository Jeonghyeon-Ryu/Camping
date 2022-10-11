<template>
  <div class="my-info">
    <div class="my-info-left">
      <div class="my-info-left-top">
        <div class="profile-image">
          <img :src="profile-image" />
        </div>
        <div class="nickname" v-text="nickname"></div>
        <div class="profile-intro" v-text="profile_intro">
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
            <ImagePreview :images="profile_image"></ImagePreview>
          </div>
          <label>사진변경
            <input @change="changeImage($event)" type="file" style="display:none;">
          </label>
        </div>
        <div class="profile-info-modify">
          <div>
            <input type="text" v-bind:value="email" placeholder="이메일" name="email" />
            <input type="text" v-bind:value="nickname" placeholder="닉네임" name="nickname" />
            <input type="button" value="비밀번호 변경" name="nickname" />
          </div>
          <div>
            <input type="text" v-bind:value="member_name" placeholder="이름" name="member_name" />
            <input type="text" v-bind:value="identification" placeholder="주민등록번호(900101-1)" name="" />
            <input type="text" v-bind:value="phone_number" placeholder="연락처" name="phone_number" />
          </div>
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
  export default {
    data: function () {
        return {
            nickname: "닉네임",
            profile_image: [],
            profile_intro: "저의 프로필 인트로 글입니다. 저의 프로필 인트로 글입니다. 저의 프로필 인트로 글입니다.",
            email: "vxxv@naver.com",
            member_name: "류정현",
            identification: "900000-1",
            phone_number: "010-0000-0000"
        };
    },
    components: { ImagePreview },
    methods: {
      changeImage : function(e) {
        this.profile_image = e.target.files;
      },
      user_modify_confirm : function(){
        console.log(new FormData(document.querySelector('.user-info-form')).get('member_name') );
      },
      user_modify_cancel : function() {
        Swal.fire({
          title: '등록을 취소하시겠습니까?',
          text:'입력한 내용이 삭제됩니다.',
          showCancelButton: true,
          confirmButtonText: '취소',
          cancelButtonText : '계속 작성'
        }).then((result) => {
          /* Read more about isConfirmed, isDenied below */
          if (result.isConfirmed) {
            Swal.fire('취소 !', '', 'success')
          }
        })
      }
    }
}
</script>
<style scoped src="@/assets/css/User/UserInfo.css"></style>