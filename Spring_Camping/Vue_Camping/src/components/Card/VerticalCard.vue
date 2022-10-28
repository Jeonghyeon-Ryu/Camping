<template>
  <div class="vertical-card-container">
    <div class="card">
      <div class="card-image">
        <img src="@/assets/img/main-image/main_1.jpg" />
      </div>
      <div class="card-info">
        <div class="card-title">제목제목</div>
        <div class="card-detail">내용내용</div>
        <div class="card-footer">2022 07 18</div>
        <button class="card-button" type="button">More Info</button>
      </div>
    </div>
    <div class="card">
      <div class="card-image">
        <img src="@/assets/img/main-image/main_1.jpg" />
      </div>
      <div class="card-info">
        <div class="card-title">제목제목</div>
        <div class="card-detail">내용내용</div>
        <div class="card-footer">2022 07 18</div>
        <button class="card-button" type="button">More Info</button>
      </div>
    </div>
    <div class="card">
      <div class="card-image">
        <img src="@/assets/img/main-image/main_1.jpg" />
      </div>
      <div class="card-info">
        <div class="card-title">제목제목</div>
        <div class="card-detail">내용내용</div>
        <div class="card-footer">2022 07 18</div>
        <button class="card-button" type="button">More Info</button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: ['datas']
}
</script>
<style scoped src="./VerticalCard.css"></style>