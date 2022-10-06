<template>
<div class="sns-container">
    
    <div class="sns-write-container">
      <div class="sns-write-button">
        <span class="sns-button-background">새 게시물 작성</span>
        <input v-on:click="snsWriteButton" type="button" value="게시글 공유">
      </div>
      <div class="sns-write-form-container">
        <div class="sns-write-form">
          <div class="sns-upload-img">


            <div class="sns-image-preview">
              <div class="sns-img-upload-button">
              <div id='att_zone' data-placeholder='파일을 첨부 하려면 파일 선택 버튼을 클릭하거나 파일을 드래그앤드롭 하세요'></div>
            </div>
            <div class="sns-img-upload-button">
              <label for="btnAtt">업로드</label>
              <input type='file' id='btnAtt' multiple='multiple' />
            </div>
          </div>
        </div>
      </div>

      <div class="sns-write-form">
        <div class="sns-write-form-id-form">
          <div class="sns-write-form-id">
            <div class="sns-write-id">
              <img src="이미지8.jpg">
            </div>
          </div>
          <div class="sns-write-form-id">
            <div class="sns-write-id">
              <input type="text" {{ snsWriteId }}>
            </div>
          </div>
        </div>
        <div class="sns-write-context">
          <textarea placeholder={{snsWriteText}}></textarea>

        </div>
        <div class="sns-write-location">
          <input type="button" value="위치추가">
          <p class="result"></p>
        </div>
      </div>
    </div>
  </div>
  </div>


</template>

<script>
  export default{
    components : {},
    data : ()=>{
      return {        
        snsWriteId : '',
        snsWriteText : ''

        
      }
    },
    methods : {
     
    }
  }
  </script>