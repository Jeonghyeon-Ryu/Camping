<!-- 게시글에담긴정보랑연계하기......어케함 -->
<template>
  <div id="container">
    <form class="container2" id="myform" method="post">
      <div id="used-head">
        <h1>여행은 즐거우셨나요?</h1>
        <div class="deal">
          <EntryMypageCard v-bind:recruId="recruId" @goRecruDetail="this.$router.push({name:'recruDetail',params : {recruId : recruId}})"></EntryMypageCard>
        </div>
        <div>
          <select name="review-member" id="">
            <option value="기타" selected disabled>동행자 선택</option>
           
              <option v-for="member in recruMember" :value="member.nickname">{{member.nickname}}</option>
        
          </select>
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
          </fieldset>
        </div>
      <div id="used-foot">
        <div class="used-desc">
            <input type="hidden" name="email" :value="$store.state.email">
            <textarea name="reviewContent" class="reviewContent" placeholder="후기를 10자 이상 입력하세요"></textarea>
            <div class="submit-area">
              <button class="dealcomplete" @click.prevent="confirm">작성 완료</button>
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
  import RecruCard from '@/components/recruit/RecruCard.vue'
  import EntryMypageCard from '@/components/recruit/EntryMypageCard.vue'
  export default{
    props : {
       recruId : String
    },
    data(){
      return{
        recruInfo : {},
        recruMember : []
      }
    },
    created (){
      this.loadData();
    },  
    methods: {
      loadData : function(){
        var recruId = this.recruId;
        //게시글 정보 
        fetch(`/java/recru/${recruId}`)
        .then((response) =>response.json()) 
        .then(data => { 
            console.log(data);
            this.recruInfo = data;  
        }).catch(err=>console.log(err));
        //모임 참여자 정보
        fetch(`/java/recru/members/${recruId}`)
        .then((response) =>response.json()) 
        .then(data => { 
            this.recruMember = data;  
            //this.recruMember.forEach(member=>{})
        }).catch(err=>console.log(err));
          
      },
      confirm: function(){

        // const form = document.forms.namedItem('#container2')
        let fetchData = [];

        let grade = document.querySelector("[name='reviewGrade']:checked")
        fetchData = new FormData(document.querySelector('.container2'))

        fetchData.forEach((value,key)=>{
          console.log(key, ":", value);
        })

        if(grade === null){
          Swal.fire({
          title: '',
          text: '별점을 선택해주세요',
          icon: 'warning',
          confirmButtonColor: '#3085d6', // confirm 버튼 색깔 지정
          confirmButtonText: '확인', // confirm 버튼 텍스트 지정
          })
        }else{

            fetch('/java/recru/review',{
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
  },
  components : {
    RecruCard,
    EntryMypageCard
  }
} 
</script>  
<style scoped src="@/assets/css/used/UsedReview.css" />
<style scoped>
#container{
  margin-top: 150px;
}
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