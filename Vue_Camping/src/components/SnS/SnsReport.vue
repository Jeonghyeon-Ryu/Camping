<template>
  <div class="sns-report-container">
    <div class="sns-report-default-text">
      <input type="text" value="신고\n\n이 게시물을 신고하는 이유\n지식재산권 침해를 신고하는 경우를 제외하고 회읜님의 신고는 익명으로 처리됩니다. 누군가 위급한 상황에 있다고 생각된다면 즉시 현지 응급 서비스 기관에 연락하시기 바랍니다.">
    </div>
    <div class="sns-report-reason">
      <select name="choose-report-reason" value="신고사유">
        <option value="거짓정보">거짓정보</option>
        <option value="게시글 규정 위반">게시글 규정 위반</option>
        <option value="기타">기타</option>
      </select>
    </div>
    <div class="sns-report-text">
      <input type="text" placeholder="신고 내용을 입력하세요.">
    </div>

  </div>

</template>

<script>

</script>