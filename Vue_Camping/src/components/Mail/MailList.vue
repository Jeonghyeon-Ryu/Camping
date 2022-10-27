<template>
    <div class="container">
    <h3 style="text-align:center">쪽지함</h3>
    <div class="container2">
      <div class="rcvMail">
        <div class="mail-head">
          <h4 style="padding: 5px;">받은 쪽지함</h4>
          <div class="mail-more" @click="mvReceive()">더보기 > </div>
        </div>
        <div class="mails">
          <div class="rcvMailList">
            <div v-for="mail in rcvMailList.slice(0,5)" :key="mail.id" class="mail-detail" @click="mailDetail()">
              <ul>
                <li>보낸사람 : <b>{{mail.mailSender}}</b></li>
                <li>내용 : {{mail.mailContent}}</li>
                <li style="color:gray; font-size:small; margin-left: 80%;">{{mail.mailDate}}</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="sendMail">
        <div class="mail-head">
          <h4 style="padding: 5px;">보낸 쪽지함</h4>
          <div class="mail-more" @onclick="mvSend">더보기 > </div>
        </div>
        <div class="mails">
          <div class="rcvMailList">
            <div v-for="mail in sendMailList.slice(0,5)" :key="mail.id" class="mail-detail" @click="mailDetail()">
              받는사람 : {{mail.mailReceiver}}<br>
              내용 : {{mail.mailContent}}<br>
              보낸시간 : {{mail.mailDate}}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2';

export default {
  components: {},
  data(){
    return{
      rcvMailList: [],
      sendMailList: [],
    }
  },
  methods: {
    mailDetail: function(){
      if(this.$store.state.email === null){
        Swal.fire({
          icon: 'warning',
          title: '로그인 후에 쪽지할 수 있어요',
          toast: true,
          showConfirmButton: false,
          timer: 1200,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
            // this.$router.push({ name: "LoginSignup" });
          }
        })
      }else{
      let item = Swal.fire({
          title: '<div class="mail-Title" style="font-size:0.6em; color: green;">'+this.rcvMailList.mailSender+'님이 보낸 쪽지</div>',
          html: 
          '<div class="mail-info" style="dislay:flex; border-radius:2px; width:80%; margin: 0 auto; padding: 5px; background-color:#f7f7f7"><div class="mail-usedName"> <span style="font-size:0.8em; color:#54b06d; font-weight:bold;">상품명 </span><span style="font-size:0.9em; font-weight:bold; color: #4a4a4a">'
            +'</span></div>'+
            '<div class="mail-usedPrice"> <span style="font-size:0.8em; color:#54b06d; font-weight:bold;">상품가격 </span><span style="font-size:0.9em; font-weight:bold; color: #4a4a4a">'+'</span><span style="font-size:0.8em; font-weight:bold;">원<span></div></div>'+
            '<textarea id="swal-input2" class="swal2-textarea" style="resize:none; width:80%; height: 200px; font-size:12px;" maxlength="500" placeholder="판매자에게 보낼 내용을 입력하세요"></textarea>',
          focusConfirm: false,
          showCancelButton: true,
          confirmButtonText: '전송',
          cancelButtonText: '취소',
          confirmButtonColor: '#54b06d',
          preConfirm: () => {
            let fetchData = {
              "usedId": this.usedId,
              "mailSender": this.$store.state.email,
              "mailReceiver": this.usedList.email,
              "mailContent": document.getElementById('swal-input2').value,
            }

            console.log(fetchData);
            fetch('http://localhost:8087/java/mail/sendMail', {
              method: 'POST',
              headers: {'Content-Type': 'application/json'},
              body: JSON.stringify(fetchData)
            }).then(result => result.text())
              .then(result => {
                if (result == "true") {
                  Swal.fire({
                    icon: 'success',
                    title: '전송되었습니다',
                    toast: true,
                    showConfirmButton: false,
                    timer: 900,
                    didOpen: (toast) => {
                      toast.addEventListener('mouseenter', Swal.stopTimer)
                      toast.addEventListener('mouseleave', Swal.resumeTimer)
                      this.$router.push({ path: '/used/UsedDetail/' + this.usedId, });
                    }
                  })
                } else {
                  Swal.fire({
                    icon: 'error',
                    title: '전송 실패 !',
                    text: '계속 실패하면 고객센터에 문의해주세요.',
                    toast: true,
                    showConfirmButton: false,
                    timer: 900,
                    didOpen: (toast) => {
                      toast.addEventListener('mouseenter', Swal.stopTimer)
                      toast.addEventListener('mouseleave', Swal.resumeTimer)
                    }
                  })
                }
                console.log(result);
              })

            return false;
          }
        })
        console.log(item);
      }
    }
  },
  created(){
        //받은 쪽지
        fetch('http://localhost:8087/java/mail/receivedMail/'+this.$store.state.email) 
                .then(Response => Response.json())  //json 파싱 
                .then(data => { 
                    for(let key in data){
                      this.rcvMailList.push(data[key]);  
                    }
                    console.log('쪽지')
                    console.log(data);
                }).catch(err=>console.log(err))
        
        //보낸 쪽지
        fetch('http://localhost:8087/java/mail/sendedMail/'+this.$store.state.email) 
        .then(Response => Response.json())  //json 파싱 
        .then(data => { 
            for(let key in data){
              this.sendMailList.push(data[key]);  
            }
            console.log('쪽지')
            console.log(data);
        }).catch(err=>console.log(err))
  }
}
</script>

<style scoped>
      *{
      margin:0;
      padding:0;
      box-sizing: border-box;
      text-align: left;
    }
    .container{
      width: 80%;
      margin: 0 auto;
      margin-top: 10%;
      padding: 2%;
      border: 1px solid #e6e6e6;
    }
    .container2{
      display: flex;
      margin: 0 auto;
    }
    .mail-head{
      display: flex;
      justify-content: space-between;
      padding: 10px;
    }
    .mail-more{
      font-weight: bold;
      font-size: 0.8em;
      line-height: 30px;
      background-color: #f7f7f7;
      padding:3px 10px 3px 10px;
      border-radius: 4px;
      cursor: pointer;
    }
    .rcvMail, .sendMail{
      width: 47%;
      height: 550px;
      padding: 15px;
      margin: 10px;
      border: 1px solid #e6e6e6;
    }
    .mails{
      overflow-y: scroll;
      height:450px;
    }
    .mail-detail{
      border: 1px solid #e6e6e6;
      margin: 5px;
      padding: 10px;
      border-radius: 3px;
      cursor: pointer;
      min-width: 360px;
    }  
    .mails::-webkit-scrollbar, .sendMail::-webkit-scrollbar{
    width: 10px;  /* 스크롤바의 너비 */
    }

    .mails::-webkit-scrollbar-thumb, .sendMail::-webkit-scrollbar{
        height: 30%; /* 스크롤바의 길이 */
        background: #eeeeee; /* 스크롤바의 색상 */
        
        border-radius: 10px;
    }

    .mails::-webkit-scrollbar-track, .sendMail::-webkit-scrollbar{
        background: rgba(255, 255, 255, 0.1);  /*스크롤바 뒷 배경 색상*/
    }

</style>