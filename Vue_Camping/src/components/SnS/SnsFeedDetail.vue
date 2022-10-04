<template>
  <div class="sns-container">
    <div class="sns-searchbox">
      <input type="text" v-model="snsSearch">
      <input type="button" value="X">
    </div>
      <div class="sns-page-id-container">
    <div class="sns-page-id">
      <span>게시물</span>
    </div>
    </div>
    <div class="sns-detail-page-container">
      <div class="sns-write-id-container">
        <div class="sns-write-form-id">
        <div class="sns-write-id">
          <img v-bind:src="snsMaingImg5">
        </div>
      </div>
      <div class="sns-write-form-id">
        <div class="sns-write-id">
          <input type="text" value="작성자id">
        </div>
      </div>
      </div>
      
    <div class="sns-detail-container">
      <div class="sns-detail-form1">
      <div class="sns-img-container">
        <input type="text" v-bind:value="snsWriteNumber1"  style="display :none;">
        <img v-bind:src="snsMaingImg6">
      </div>
    </div>

    <div class="sns-detail-form1">
      <div class="sns-write-all-form-container">
        
        <div class="sns-write-context">
          <textarea v-model="snsWriteText"></textarea>
        </div>
      </div>
     
        <div class="sns-push-button-container">
        <div class="sns-push-button-container1">
        <div class="sns-write-like-button">
          <input type="text" value="♥">
        </div>
        <div class="sns-write-comment-button">
          <input type="text" value="🗨">
        </div>
        </div>
        <div class="sns-push-button-container1">
        <div class="sns-notification">
          <input type="text" value="신고">
        </div>
      </div>
    </div>

        <div class="sns-detail-comment-form-container">         
          <div class="sns-detail-comment-form">
            <div class="sns-comment-write-id-container">
            <div class="sns-comment-write-id">
              <img v-bind:src="snsMaingImg3">
            </div>
            <div class="sns-comment-write-id">
              <input type="text" value="댓글작성자id">
            </div>
          </div>
            <div class="sns-comment-container">
              <div class="sns-comment">
                <div class="sns-comment-write-context">
          <textarea v-model="snsCommentWriteText"></textarea>

        </div>
              </div>
              <div class="sns-comment-date">
                <input type="text" value="2022/09/30">
              </div>
            </div>
          </div>


          <div class="sns-detail-comment-form">
            <div class="sns-comment-write-id-container">
            <div class="sns-comment-write-id">
              <img v-bind:src="snsMaingImg4">
            </div>
            <div class="sns-comment-write-id">
              <input type="text" value="댓글작성자id">
            </div>
          </div>
            <div class="sns-comment-container">
              <div class="sns-comment">
                <div class="sns-comment-write-context">
          <textarea v-model="snsCommentWriteText"></textarea>

        </div>
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
  import img1 from "@/assets/img/이미지1.jpg"
  import img2 from "@/assets/img/이미지2.jpg"
  import img3 from "@/assets/img/이미지3.jpg"
  import img4 from "@/assets/img/이미지4.jpg"
  import img5 from "@/assets/img/이미지5.jpg"
  import img6 from "@/assets/img/이미지6.jpg"
  import img7 from "@/assets/img/투샷.jpg"
  import img8 from "@/assets/img/본체현민.jpg"
  
  export default{
    data : ()=>{
      return {    
        snsSearch : '검색어를 입력하세요.', 
        snsWriteText : '글내용 글내용 #태그',
        snsCommentWriteText : '댓글을 입력하세요',
        snsWriteNumber1 : '1111',   
        snsWriteNumber2 : '2222',
        snsWriteNumber3 : '3333',
        snsWriteNumber4 : '4444',
        snsWriteNumber5 : '5555',
        snsWriteNumber6 : '6666', 

        snsMaingImg1 : img1,
        snsMaingImg2 : img2,
        snsMaingImg3 : img3,
        snsMaingImg4 : img4,
        snsMaingImg5 : img5,
        snsMaingImg6 : img6,
        snsMaingImg7 : img7,
        snsMaingImg8 : img8
        
      }
    },
    methods : {
     
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

    .sns-page-id span {
      font-weight: bold;
      margin-top: 7px;
    }

    .sns-detail-page-container {
      width: 60vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: left;
      }

      .sns-detail-container{
        width: 60vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: left;
      }

      .sns-write-id-container {
width: 100%;
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

.sns-write-id input {
margin-top: 0.5vw;
padding: 0.3vw;
border: none;
}


.sns-detail-container{
display: flex;
flex-wrap: wrap;
justify-content: left;
width: 100vw;
}

.sns-detail-form1{
  width: 50%;
}

.sns-img-container{
  width : 100%;
}
.sns-img-container img {
  width: 100%;
}
.sns-detail-comment-form-container{

flex-wrap: wrap;
justify-content: left;
}

.sns-comment-write-id-container{
  display: flex;
      flex-wrap: wrap;
      justify-content: left;
}

.sns-comment-write-id{
  width: 50%;
}
.sns-detail-comment-form{

flex-wrap: wrap;
justify-content: left;
}


    .sns-comment-write-id-img img {
      width: 3vw;
      height: 3vw;
      border-radius: 70%;
      margin-right: 1vw;
    }


    .sns-push-button-container{
      display: flex;
      flex-wrap: wrap;
      justify-content: left;

    }
    .sns-push-button-container1{
      width : 50%;

    }

    .sns-write-like-button input{
      border: none;

    }
    .sns-write-comment-button input{
      border: none;
    }
    .sns-notification{
    justify-content: rigth;
    }
    
    .sns-notification input{
      border: none;
    }

    .sns-detail-comment-form-container{
      display: flex;
      flex-wrap: wrap;
      justify-content: left;
      width: 100%;
    }

    .sns-detail-comment-form{

    }

    .sns-comment-write-id img{
      width: 3vw;
      height: 3vw;
      border-radius: 70%;
      margin-right: 1vw;
    }
    .sns-comment-write-id input{
      margin-top: 0.5vw;
      padding: 0.3vw;
      border: none;
    }

    .sns-comment-write-context textarea{
      margin-top: 5px;
      width: 24vw;
      height: 30px;
      border: 1px solid;
      resize: none;
      border-radius: 5px;
      margin-left: 15px;
    }

    
  </style>
