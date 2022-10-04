<template>

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

<style scoped>
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

    .sns-write-container {
      width: 100vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
    }

    .sns-write-button {
      position: relative;
      width: 56vw;
      min-width: 800px;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      margin-bottom: 10px;
    }

    .sns-button-background {
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

    .sns-write-button input {
      position: absolute;
      background-color: rgba(228, 239, 231, 0.7);
      /* border: none; */
      right: 10px;
      margin-right: 10px;
      color: rgb(255, 255, 255);
      font-weight: bold;
      cursor: pointer;
      margin-top: 7px;
    }

    .sns-write-form-container {
      width: 100vw;
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
    }

    .sns-upload-img {
      margin-right: 2vw;
    }

    .sns-write-form-id-form {

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

    .sns-write-context textarea {
      margin-top: 5px;
      width: 28vw;
      height: 13vw;
      min-height: 250px;
      min-width: 184px;
      border: 1px solid;
      resize: none;
      border-radius: 5px;
    }

    .sns-write-location input {
      width: 28vw;
      height: 40px;
      background-color: rgb(223, 223, 223);
      border-radius: 5px;
      border: none;
      cursor: pointer;
      color: rgb(87, 87, 87);
      font-weight: bold;
    }



    /* 시도3 */
    #att_zone {
      margin-top: 14px;
      width: 503px;
      min-height: 135px;
      padding: 10px;
      border: 1px solid #999;
      border-radius: 5px;

    }

    #att_zone:empty:before {
      content: attr(data-placeholder);
      color: #999;
      font-size: .9em;
    }




    /* 파일첨부꾸미기 */
    .sns-image-preview input[type="file"] {
      position: absolute;
      width: 0;
      height: 0;
      padding: 0;
      overflow: hidden;
      border: 0;
    }

    .sns-image-preview label {
      display: inline-block;
      padding: 10px 20px;
      color: #fff;
      vertical-align: middle;
      background-color: #337ab7;
      cursor: pointer;
      border: 1px solid #2e6da4;
      border-radius: 5px;
    }

    /* named upload */
    .sns-image-preview #att_zone {
      display: inline-block;
      height: 100%;
      width: 26.5vw;
      min-width: 345px;
      font-size: 18px;
      padding: 0 10px;
      vertical-align: middle;
      background-color: #f5f5f5;
      border: 1px solid #ebebeb;
      border-radius: 5px;

    }


    /* 시도2 */
    /* #multiple-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
}

.image {
    display: block;
    width: 100%;
}
.image-label {
    position: relative;
    bottom: 22px;
    left: 5px;
    color: white;
    text-shadow: 2px 2px 2px black;
} */
  </style>