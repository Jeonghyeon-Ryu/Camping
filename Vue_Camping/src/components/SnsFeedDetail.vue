<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="text" placeholder="검색어를 입력해주세요.">
      <input type="button" value="X">
    </div>
      <div class="sns-page-id-container">
    <div class="sns-page-id">
      <input type="text" value="게시물">
    </div>
    </div>
    <div class="sns-detail-page-container">
      <div class="sns-write-id-container">
        <div class="sns-write-id-img">
          <img src="이미지6.jfif">
        </div>
        <div class="sns-write-id">
          <input type="text" value="작성자id">
        </div>
      </div>
      
    <div class="sns-detail-container">
      <div class="sns-img-container">
        <div class="sns-write-number" style="display :none;">1111</div>
        <img src="이미지1.jfif">
      </div>
      <div class="sns-write-all-form-container">
        <div class="sns-write-context">
          
        </div>
        <div class="sns-push-button-container">
        <div class="sns-write-like-button">
          <input type="text" value="♥">
        </div>
        <div class="sns-write-comment-button">
          <input type="text" value="🗨">
        </div>
        </div>
        <div class="sns-notification">

        </div>
        <div class="sns-detail-comment-form-container">
          <div class="sns-detail-comment-form">
            <div class="sns-comment-write-id-img">
              <img src="../assets/img/투샷.jpg">
            </div>
            <div class="sns-comment-write-id">
              <input type="text" value="댓글작성자id">
            </div>
            <div class="sns-comment-container">
              <div class="sns-comment">

              </div>
              <div class="sns-comment-date">

              </div>
            </div>
          </div>


          <div class="sns-detail-comment-form">
            <div class="sns-comment-write-id-img">
              <img src="../assets/img/본체현민.jpg">
            </div>
            <div class="sns-comment-write-id">
              <input type="text" value="댓글작성자id">
            </div>
            <div class="sns-comment-container">
              <div class="sns-comment">

              </div>
              <div class="sns-comment-date">
                <input type="text" value="2022/09/30">
              </div>
            </div>
          </div>

        </div>
        
      </div>  
    </div>
    </div>
    </div>
</template>

<script>
    export default{
  data : function(){

  }  
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
    /* 공통 부분 */
    * {
      margin: 0;
      padding: 0;
      list-style: none;
      font-style: none;
      box-sizing: border-box;
    }

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

    .sns-page-id-container{
      display: flex;
      justify-content: center;
    }
    .sns-page-id{
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

    .sns-page-id input {
      position: absolute;
      background-color: rgba(228, 239, 231, 0.7);
      border: none;
      color: rgb(255, 255, 255);
      font-weight: bold;
      margin-top: 7px;
    }

    .sns-profile-container {

      display: flex;
      flex-wrap: wrap;
      justify-content: left;
      }
    .sns-write-id img {
      width: 3vw;
      height: 3vw;
      border-radius: 70%;
      margin-right: 1vw;
    }

    .sns-write-total{
      margin-top: 5px;
    }

    .sns-write-total input{
      border: none;
    }

    .sns-write-nickname input{
      border: none;
    }
    
    .sns-write-profile-introduction input{
      border: none;
    }
    .sns-img-container {
      width: 70vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
    }

    .sns-img {
      margin-bottom: 10px;
      margin-right: 10px;
      cursor: pointer;
    }
  </style>
