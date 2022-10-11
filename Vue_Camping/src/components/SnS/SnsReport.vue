<template>
  <div class="sns-report-container">
    <div class="sns-report">
    <div class="sns-report-default-text">
      <textarea v-model="snsReportDefault"></textarea>
      <!-- <textarea v-model="snsWriteText" placeholder="내용을 입력하세요"></textarea> -->
    </div>
    <div class="sns-report-reason">
      <select v-model="snsReportReason" name="choose-report-reason" value="신고사유">
        <option value="거짓정보">거짓정보</option>
        <option value="게시글 규정 위반">게시글 규정 위반</option>
        <option value="기타">기타</option>
      </select>
    </div>
    <div class="sns-report-text">
      <textarea v-model="snsReportText" placeholder="신고 내용을 입력하세요."></textarea>
    </div>
    <div class="sns-report-button">
      <input type="button" @click="doReport" value="신고">
      <input type="button" @click="doEsc" value="취소">
      
    </div>
  </div>
  </div>

</template>

<script>
export default{
    components : {},
    data : ()=>{
      return {        
        snsReportDefault : '신고\n \n게시물을 신고하는 이유\n \n지식재산권 침해를 신고하는 경우를 제외하고 회원님의 신고는 익명으로 처리됩니다. 누군가 위급한 상황에 있다고 생각된다면 즉시 현지 응급 서비스 기관에 연락하시기 바랍니다.',
        snsReportReason : '게시글 규정 위반',
        snsReportText : '',        
      }
    },
    //검색
    methods : {
      doReport(){
        console.log(this.snsReportReason);
        console.log(this.snsReportText);
        this.$emit('confirmModify')
      },
      doEsc(){
        this.$emit('cancelModify')
      }
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

  .sns-report-container{

    font-family: "Noto Sansf KR", sans-serif;

    width: 100vw;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }

  .sns-report{
    width: 40%;
    max-width: 400px;
    border: 0.5px solid #aaa;
    border-radius: 5px;
  }

  .sns-report-default-text{
    height: 180px;
  }

  .sns-report-default-text textarea{
    font-family: "Noto Sansf KR", sans-serif;

    margin-top: 5px;
    width: 90%;
    height: 10%;
    min-height: 200px;
    min-width: 200px;
    margin-top: 10px;

    resize: none;
    border: none;

}

.sns-report-reason select {
  -moz-appearance: none;
	-webkit-appearance: none;
	appearance: none;
  font-family: "Noto Sansf KR", sans-serif;
  /* font-size: 1rem; */
  font-weight: 400;
  line-height: 1.5;

  color: #444;
  background-color: #fff;

  width: 90%;
  padding: 0.6em 1.4em 0.5em 0.8em;
  margin: 0;

  border: 1px solid #aaa;
  border-radius: 0.5em;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, 0.04);
}
select:hover {
  border-color: #888;
}

select:focus {
  border-color: #aaa;
  box-shadow: 0 0 1px 3px rgba(59, 153, 252, 0.7);
  box-shadow: 0 0 0 3px -moz-mac-focusring;
  color: #222;
  outline: none;
}

select:disabled {
  opacity: 0.5;
}

.sns-report-text textarea{
  
  width: 90%;
  height: 70px;
  margin-top: 10px;
  margin-bottom: 10px;
  border: 0.5px solid #aaa;
  border-radius: 5px;
  resize: none;
}

.sns-report-button input[type=button]{
  padding: 2px 15px 2px 15px;
  margin-right: 10px;
  margin-bottom: 10px;
  background-color: rgb(228, 225, 225);
  border: none;
  border-radius: 5px;
  font-weight: bold;
  color: #888;
  cursor: pointer;
}
</style>