<template>
  <div id="container">
    <form class="container2" id="myform">
      <div id="used-head">
        <h4>거래후기 작성하기</h4>
        <h1>거래는 어떠셨나요?</h1>
        <div class="deal">
          <div class="img-container">
              <img v-bind:src="reviewImg">
          </div>
          <div class="desc">
              <h2>{{usedName}}</h2>
              <p>{{usedContent}}</p>
          </div>
          <button class="dealcomplete">거래 완료하기</button>
        </div>
        <div class="reviewRate">
          <p class="text-bold">별점을 선택해주세요</p>
          <input type="radio" name="reviewStar" value="1" id="rate1"><label
            for="rate1">★</label>
          <input type="radio" name="reviewStar" value="2" id="rate2"><label
            for="rate2">★</label>
          <input type="radio" name="reviewStar" value="3" id="rate3"><label
            for="rate3">★</label>
          <input type="radio" name="reviewStar" value="4" id="rate4"><label
            for="rate4">★</label>
          <input type="radio" name="reviewStar" value="5" id="rate5"><label
            for="rate5">★</label>



          <!-- <span v-bind="drawStar()" class="star">
          ★★★★★
          <span>★★★★★</span>
          <input type="range" oninput="drawStar(this)" value="1" step="1" min="0" max="10">
          </span> -->
        </div>
      <div id="used-foot">
        <div class="used-desc">
            <textarea name="used_text" class="used-text" placeholder="후기를 입력하세요"></textarea>
            <div class="submit-area">
              <button class="dealcomplete" @click.prevent="confirm()">작성 완료</button>
            </div>
          </div>
      </div>  
      </div>
    </form>
  </div>
</template>
<script>
  import img1 from "@/assets/img/bg9.jpg"

  export default{
    data(){
      return{
        reviewImg: img1,
        usedName: '4인용텐트',
        usedContent: '1회 쓰고 보관만 했습니다 상태 굿'
      }
    },
    methods: {
      drawStar: function(){
        const drawStar = (target) => {
        document.querySelector(`.star span`).style.width = `${target.value * 10}%`;
        }
      },
      confirm: function(){
        let fetchData = {};
            new FormData(document.querySelector('#container2')).forEach((value,key) => fetchData[key]=value);
            console.log(fetchData);
    }
  }
} 
</script>  
<style scoped src="@/assets/css/used/UsedReview.css" />
<style>

#myform fieldset{
    display: inline-block;
    direction: ltr;
    border:0;
}
#myform fieldset legend{
    text-align: right;
}
#myform input[type=radio]{
    display: none;
}
#myform label{
    font-size: 3em;
    color: transparent;
    text-shadow: 0 0 0 #f0f0f0;
    cursor:pointer;
}
#myform label:hover{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#myform label:hover ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#myform input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#reviewContents {
    width: 100%;
    height: 150px;
    padding: 10px;
    box-sizing: border-box;
    border: solid 1.5px #D3D3D3;
    border-radius: 5px;
    font-size: 16px;
    resize: none;
}
</style>  