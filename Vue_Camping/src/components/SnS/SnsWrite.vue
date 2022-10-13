<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="search" @keyup="checkEnter($event)" v-model="searchText" placeholder="검색어를 입력해주세요.">
      <button @click="doSearch" style="display: none;">조회</button>
      <!-- <input type="button" @click="doClear" value="X"> -->
    </div>
    <div class="sns-write-container">
      <div class="sns-write-button">
        <span class="sns-button-background">새 게시물 작성</span>
        <input type="button" @click="doPost" value="게시글 공유">
      </div>
      <div class="sns-write-form-container">
        <div class="sns-write-form">

          <div class="sns-upload-image-container">
            <div class="sns-upload-image-preview">


              <!-- <div class="sns-img-preview"> -->
                
              <label>업로드
                <input type="file" multiple @change="changeImage($event)" @dragenter.prevent @dragover.prevent
                  @drop.prevent="dropImage($event)" style="display:none;">
                <div class="sss" data-placeholder='파일을 첨부 하려면 파일 선택 버튼을 클릭하거나 파일을 드래그앤드롭 하세요'>

                  <div v-for="(img,index) of imagesUrl" :id="index" class="sns-image-preview">
                    <img :src="img" />
                    <input type="button" value="X" @click="deletePreview($event)"
                      class="sns-image-preview-delete-button" />
                  </div>
                </div>
              </label>

              <!-- </div> -->



              <div class="insert" id="snsImgForm">
    <form method="POST" onsubmit="return false;" enctype="multipart/form-data">
        <input type="file" onchange="addFile(this);" multiple />
        <div class="file-list"></div>
    </form>
</div>



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
                <input type="text" value="작성자 닉네임" name="nickname">
              </div>
            </div>
          </div>
          <div class="sns-write-context">
            <textarea v-model="snsWriteText" placeholder="내용을 입력하세요" name="content"></textarea>

          </div>
          <div class="sns-write-hashtag">
            <textarea v-model="snsWriteHashtag" placeholder="해시태그" name="hashtag"></textarea>
          </div>
          <div class="sns-write-place">
            <input v-model="snsWritePlace" type="text" name="place">

            <p class="result"></p>

          </div>
          <div class="sns-write-location">
            <input v-model="snsWriteLocation" type="text" name="location">
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
  created : function(){
    
  },
  
  data: function(){
    return {
      snsWriteId: '작성자id',
      snsWriteText: '내용',
      snsWriteHashtag: '#해시태그',
      snsWritePlace: '장소',
      snsWriteLocation: '위치',
      snsWriteIdImg: img1,
      searchText: '',
      images: [],
      imagesUrl: [],
      
      //
      fileNo : '',
      filesArr : []



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
      new FormData(document.querySelector('#snsForm')).forEach((value,key) => snsInfo[key]=value);
            console.log(snsInfo);

    fetch('http://localhost:8087/java/sns', {
    method : 'POST',
    headers : {
      'Content-Type' : 'application/json'

    },
    body : JSON.stringify(snsInfo)
  }).then(result => result.text())
  .then(result => console.log(result))
  .catch(err => console.log(err))
      

  //이미지를..어케..해보려함..
      let snsImg = {};
      // console.log(new FormData(document.querySelector('#snsImgForm')));
      // new FormData(document.querySelector('#snsImgForm')).forEach((value,key) => snsImg[key]=value);
      //       console.log(snsImg);


    // 폼데이터 담기
    var form = document.querySelector("form");
    var formData = new FormData(form);
    for (var i = 0; i < filesArr.length; i++) {
        // 삭제되지 않은 파일만 폼데이터에 담기
        if (!filesArr[i].is_delete) {
            formData.append("attach_file", filesArr[i]);
        }
    }

      fetch('http://localhost:8087/java/snsImg',{
        method: 'POST',
        headers : {},
        body : formData
      }).then(result => console.log(result))
    },



    //-사진
    changeImage(e) {
      this.images = e.target.files;
      for (let image of this.images) {
        this.imageLoader(image);
      }
    },
    
    imageLoader(image) {
      this.imagesUrl.push(URL.createObjectURL(image));
    },
    dropImage(e) {
      let files = {};
      e.preventDefault();
      e.stopPropagation();
      let dt = e.dataTransfer;
      files = dt.files;
      for(let f of files) {
        this.imageLoader(f);
      }
    },
    deletePreview(e) {
      let parentDiv = e.target.parentElement;
      let tempimages = [];
      let tempimagesUrl = [];

      for(let i=0; i<this.images.length; i++){
        if(i != parentDiv.getAttribute('id')){
          tempimages.push(this.images[i]);
          tempimagesUrl.push(this.imagesUrl[i]);
        }
      }
      this.images = tempimages;
      this.imagesUrl = tempimagesUrl;
    },





    //
    

/* 첨부파일 추가 */
    addFile(obj){
    var maxFileCnt = 5;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수

    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
    }

    for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) {

        const file = obj.files[i];

        // 첨부파일 검증
        if (validation(file)) {
            // 파일 배열에 담기
            var reader = new FileReader();
            reader.onload = function () {
                filesArr.push(file);
            };
            reader.readAsDataURL(file)

            // 목록 추가
            let htmlData = '';
            htmlData += '<div id="file' + fileNo + '" class="filebox">';
            htmlData += '   <p class="name">' + file.name + '</p>';
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');"><i class="far fa-minus-square"></i></a>';
            htmlData += '</div>';
            $('.file-list').append(htmlData);
            fileNo++;
        } else {
            continue;
        }
    }
    // 초기화
    document.querySelector("input[type=file]").value = "";
},

/* 첨부파일 검증 */
    validation(obj){
    const fileTypes = ['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'application/haansofthwp', 'application/x-hwp'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (100 * 1024 * 1024)) {
        alert("최대 파일 용량인 100MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
},

/* 첨부파일 삭제 */
    deleteFile(num) {
    document.querySelector("#file" + num).remove();
    filesArr[num].is_delete = true;
},

/* 폼 전송 */
    submitForm() {


    $.ajax({
        method: 'POST',
        url: '/register',
        dataType: 'json',
        data: formData,
        async: true,
        timeout: 30000,
        cache: false,
        headers: {'cache-control': 'no-cache', 'pragma': 'no-cache'},
        success: function () {
            alert("파일업로드 성공");
        },
        error: function (xhr, desc, err) {
            alert('에러가 발생 하였습니다.');
            return;
        }
    })

  }
}
}


</script>

<style scoped src="@/assets/css/sns/SnsWrite.css"></style>