<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="search" @keyup="checkEnter($event)" v-model="searchText" placeholder="검색어를 입력해주세요.">
      <button @click="doSearch" style="display: none;">조회</button>
      <!-- <input type="button" @click="doClear" value="X"> -->
    </div>
    <div class="sns-write-container">
      <div class="sns-write-button">
        <span class="sns-button-background">게시물 수정</span>
        <input type="button" @click="doPost" value="게시글 공유">
      </div>
      <div class="sns-write-form-container">
        <div class="sns-write-form">

          <div class="sns-upload-image-container">
            <div class="sns-upload-image-preview">
            </div>
          </div>
        </div>


        <form class="sns-write-form" id="snsForm">
          <div class="sns-write-form-id-form">
            <div class="sns-write-form-id">
              <div class="sns-write-id">
                <img :src='snsWriteIdImg' alt=" ">
              </div>
            </div>
            <div class="sns-write-form-id">
              <div class="sns-write-id">
                <input type="text" :value="snsItem.nickname">
              </div>
            </div>
          </div>
          <div class="sns-write-context">
            <textarea :value="snsItem.content" name="content"></textarea>
            <input type="text" :value="snsItem.writeNo" style="display :none;" name="writeNo">
          </div>
          <div class="sns-write-hashtag">
            <textarea :value="snsItem.hashtag" name="hashtag"></textarea>
          </div>
          <div class="sns-write-place">
            <input :value="snsItem.place" type="text" name="place">

            <p class="result"></p>
            

          </div>
          <div class="sns-write-location">
            <input :value="snsItem.location" type="text" name="location">
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import img1 from "@/assets/img/sns/이미지1.jpg";
export default {
  //DB연결
  created: function () {
    fetch('http://localhost:8087/java/snsGet/' + this.writeNo)
      .then(response => response.json())
      .then(result => {
        this.snsItem = result
      })
      .catch(err => console.log(err));
      //서버에서 제대로 받아왔는지 확인.
      console.log(this.snsItem);
  },

  data: function () {
    return {
      // snsWriteId: '작성자id',
      // snsWriteText: '내용',
      // snsWriteHashtag: '#해시태그',
      // snsWritePlace: '장소',
      // snsWriteLocation: '위치',
      writeNo: this.$route.params.writeNo,
      snsItem : {},  
      snsWriteIdImg: img1,
      searchText: '',
      images: [],
      imagesUrl: [],

      //
      fileNo: '',
      filesArr: []



    };
  },
  //검색
  methods: {
    doSearch() {
      console.log(this.searchText);
    },
    checkEnter(event) {
      if (event.keyCode == 13) {
        this.doSearch();
      }
    },
    //게시글 공유
    //-텍스트
    doPost() {


      let snsInfo = {};
      console.log(new FormData(document.querySelector('#snsForm')));
      new FormData(document.querySelector('#snsForm')).forEach((value, key) => snsInfo[key] = value);
      console.log(snsInfo);

      fetch('http://localhost:8087/java/snsUpdate', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'

        },
        body: JSON.stringify(snsInfo)
      }).then(result => result.text())
        .then(result => console.log(result))
        .catch(err => console.log(err))

        // window.alert("수정이 완료되었습니다.")
        if(confirm("수정하시겠습니까?")) {
        window.location.href = ("/sns/detail/" + this.writeNo)
}
    },
  }
}


</script>

<style scoped src="@/assets/css/sns/SnsWrite.css">

</style>