<template>
    <div id="container2">
      <div id="used-head">
        <h4>거래후기 작성하기</h4>
        <h1>거래는 어떠셨나요?</h1>
        <div class="deal">
          <div class="img-container">
              <img src="image/prod.jpg">
          </div>
          <div class="desc">
              <h2>4인용 텐트</h2>
              <p>1회 쓰고 보관만 했습니다 상태 굿</p>
          </div>
          <button id="dealcomplete">거래 완료하기</button>
        </div>
      <div id="used-foot">
        <form class="used-desc">
            <textarea class="used-text" placeholder="후기를 입력하세요"></textarea>
        </form>
        <div class="submit-area">
          <button class="btn bg-gradient-success btn-md false false w-auto me-2" type="submit">작성 완료</button>
        </div>
      </div>  
      </div>
    </div>
</template>
<script>
  
</script>  
<style>
        #container2{
          margin: 0 auto;
          width: 70%;
        }
        h1{
          text-align: center;
          padding-top: 2%;
          padding: 40px;
        }
        .deal{
            margin: 0 auto;
            width: 700px;
            min-width: 700px;
            height: 200px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 1px 1px 6px 0.2px #e6e6e6;
            padding:10px;
            min-width: 100px;
        }
        .deal .img-container {
            height:200px;
            width:200px;
            overflow: hidden;
            padding: 10px;
        }
        .deal img{
            width:100%;
            height:100%;
            flex-wrap: nowrap;
            padding: 2%;
            object-fit: fill;
        }
        .desc{
            min-width: 50px;
            overflow: hidden;
        }
        #dealcomplete{
            flex-wrap: nowrap;
            padding: 15px;
            margin: 30px;
            background-color: #41b56c;
            border: none;
            border-radius: 10px;
            color:white;
            font-weight: bold;
            cursor:pointer;
        }
        .used-foot{
          margin: 0 auto;
        }
        .used-desc {
          text-align: center;
        }
        .used-text {
          width: 50%;
          height: 170px;
          padding: 2%;
          border: none;
          background-color: #f5f5f5;
          resize: none;
          margin-top: 5%;
          font-size: 1.5em;
        }
        .submit-area {
          padding: 5%;
          text-align: center;
        }
</style>  