<!-- 게시글에담긴정보랑연계하기......어케함? -->
<template>
  <div id="container">
    <form class="container2" id="myform" method="post">
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
        </div>
        <div class="reviewRate">
          <p class="text-bold">별점을 선택해주세요</p>
          <fieldset>
            <input type="radio" name="reviewGrade" value=1 id="rate1"><label
              for="rate1">★</label>
            <input type="radio" name="reviewGrade" value=2 id="rate2"><label
              for="rate2">★</label>
            <input type="radio" name="reviewGrade" value=3 id="rate3"><label
              for="rate3">★</label>
            <input type="radio" name="reviewGrade" value=4 id="rate4"><label
              for="rate4">★</label>
            <input type="radio" name="reviewGrade" value=5 id="rate5"><label
              for="rate5">★</label>
            <div class="review-grade">{{reviewGrade}}</div>
          </fieldset>
        </div>
      <div id="used-foot">
        <div class="used-desc">
            <input type="hidden" name="email" :value="$store.state.email">
            <textarea name="reviewContent" class="reviewContent" placeholder="후기를 10자 이상 입력하세요"></textarea>
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
  import img1 from "@/assets/img/bg9.jpg";
  import Swal from 'sweetalert2';


  export default{
    data(){
      return{
        reviewImg: img1,
        usedName: '4인용텐트',
        usedContent: '1회 쓰고 보관만 했습니다 상태 굿',
        reviewGrade: '0 - 선택할때마다이값이바뀌었으면좋겠음'
      }
    },
    methods: {
      confirm: function(){

        // const form = document.forms.namedItem('#container2')
        let fetchData = [];

        let grade = document.querySelector("[name='reviewGrade']:checked")
        let content = document.querySelector(".reviewContent").value
        fetchData = new FormData(document.querySelector('.container2'))

        fetchData.forEach((value,key)=>{
          console.log(key, ":", value);
        })

        if(grade === null){
          Swal.fire({
                  icon: 'warning',
                  title: '별점을 선택해주세요',
                  toast: true,
                  showConfirmButton: false,
                  timer: 1200,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
                })
        }else if(content === null || content.trim() === '' || content.length < 10){
          Swal.fire({
                  icon: 'warning',
                  title: '후기를 10자 이상 입력하세요', 
                  toast: true,
                  showConfirmButton: false,
                  timer: 1200,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
                })
        }else{

            fetch('http://localhost:8087/java/used/usedReview',{
                    method : "POST",
                    headers : { },
                    body : fetchData
                }) 
                .then(Response => Response.text())  //json 파싱 
                .then(data => { 
                    if(data>="1"){
                      // 성공
                      console.log("입력되었습니다.")
                      this.$router.push({name : 'usedMain'})
                      let component = this;
                    } else {
                      // 실패                    
                      console.log("입력 실패")  
                    }
                }).catch(err=>console.log(err))
              
                console.log(fetchData)

              }
    }
  }
} 
</script>  
<style scoped src="@/assets/css/used/UsedReview.css" />
<style scoped>

#myform fieldset{
    display: inline-block;
    direction: rtl;
    border:0;
}
#myform fieldset legend{
    text-align: left;
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