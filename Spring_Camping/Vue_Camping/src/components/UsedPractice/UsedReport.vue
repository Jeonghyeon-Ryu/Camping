<template>
  <div class="report-container">
    <div class="report">
      <div class="report-default-text">
        <textarea v-model="snsReportDefault"></textarea>
        <!-- <textarea v-model="snsWriteText" placeholder="내용을 입력하세요"></textarea> -->
      </div>
      <div class="report-reason">
        <select v-model="snsReportReason" name="choose-report-reason" value="신고사유">
          <option value="거짓정보">거짓정보</option>
          <option value="게시글 규정 위반">게시글 규정 위반</option>
          <option value="기타">기타</option>
        </select>
      </div>
      <div class="report-text">
        <textarea v-model="snsReportText" placeholder="신고 내용을 입력하세요."></textarea>
      </div>
      <div class="report-button">
        <input type="button" @click="doReport" value="신고">
        <input type="button" @click="doEsc" value="취소">

      </div>
    </div>
  </div>

</template>

<script>
export default {
  components: {},
  data: () => {
    return {
      snsReportDefault: '신고\n \n게시물을 신고하는 이유\n \n지식재산권 침해를 신고하는 경우를 제외하고 회원님의 신고는 익명으로 처리됩니다. 누군가 위급한 상황에 있다고 생각된다면 즉시 현지 응급 서비스 기관에 연락하시기 바랍니다.',
      snsReportReason: '게시글 규정 위반',
      snsReportText: '',
    }
  },
  //검색
  methods: {
    doReport() {
      console.log(this.snsReportReason);
      console.log(this.snsReportText);
      this.$emit('confirmModify')
    },
    doEsc() {
      this.$emit('cancelModify')
    }
  }
}
</script>

<style scoped src="./Report.css" />