<template>
  <div class="container">
  <h2 style="text-align:center;">📫</h2>
  <h3 style="text-align:center; padding-bottom: 20px;">{{this.$store.state.nickname}} 님의 쪽지함 </h3>

  <div class="container2">
    <div class="rcvMail">
      <div class="mail-head">
        <h4 style="padding: 5px;">📥 받은 쪽지함</h4>
        <div class="mail-more" @click="mvReceive()">더보기 > </div>
      </div>
      <div class="mails">
        <div class="rcvMailList">
          <div v-for="mail in rcvMailList" :key="mail.id" class="mail-detail" @click="mailDetail(mail)">
            <ul>
              <li style="padding:5px 10px"> ✉ <b>{{mail.mailSender}}</b></li>
            <template v-if="mail.mailContent != null">
              <li v-if="mail.mailContent.length <30" style="padding:10px;" >{{mail.mailContent}}</li>
              <li v-if="mail.mailContent.length >=30" style="padding:10px;" >{{mail.mailContent.slice(0,30)}} . . .더보기</li>
            </template>
              <div class="mail-flex" style="display:flex; justify-content: space-between; padding: 5px 8px 0 5px;">
                <li style="color:gray; font-size:small; margin: 3px 5px 0 5px;">{{mail.mailDate}}</li>
                <li style="color:#2e2e2e; font-size:small;" v-if="mail.mailStatus === 0">읽지않음</li>
                <li style="color:gray; font-size:small;" v-if="mail.mailStatus != 0">읽음</li>
              </div>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="sendMail">
      <div class="mail-head">
        <h4 style="padding: 5px;">📤 보낸 쪽지함</h4>
        <div class="mail-more" @onclick="mvSend">더보기 > </div>
      </div>
      <div class="mails">
        <div class="rcvMailList">
          <div v-for="mail in sendMailList" :key="mail.id" class="mail-detail" @click="mailDetail">
            <ul>
              <li> ✉ <b> {{mail.mailReceiver}}</b></li>
              <template v-if="mail.mailContent != null">
                <li v-if="mail.mailContent.length <27" >내용 : {{mail.mailContent}}</li>
                <li v-if="mail.mailContent.length >=27 " >내용 : {{mail.mailContent.slice(0,27)}}<br>…더보기</li>
              </template>
              <li style="color:gray; font-size:small; margin-left: 80%;">{{mail.mailDate}}</li>
              <li style="color:#212121; font-size:small; margin-left: 89%;" v-if="mail.mailStatus === 0">읽지않음</li>
              <li style="color:gray; font-size:small; margin-left: 93%;" v-if="mail.mailStatus != 0">읽음</li>
            </ul>

          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<button v-on:click='sendMail()'>+</button>
</template>

<script>
import Swal from 'sweetalert2';

export default {
components: {},
data(){
  return{
    rcvMailList: {},
    sendMailList: {},
  }
},
methods: {
  mailDetail: function(mail){
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
    
    //읽음처리
    let fetchData = {}
    fetchData["mailId"] = mail.mailId;
    fetch('http://13.125.95.210:85/java/mail/updateMail', {
            method: "PUT",
            // body : fetchData
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(fetchData)
          })
            .then(Response => Response.json())  //json 파싱 
            .then(data => {
              if (data >= "1") {
                // 성공
                console.log("입력되었습니다.")
              } else {
                // 실패                    
                console.log("입력 실패")
              }
            }).catch(err => console.log(err))
    
    //쪽지내용확인
    let item = Swal.fire({
        title: '<div class="mail-Title" style="font-size:0.6em; color: green;">'+mail.mailSender+'님이 보낸 쪽지</div>',
        html: 
        '<div class="mail-info" style="dislay:flex; border-radius:2px; width:80%; margin: 0 auto; padding: 5px; background-color:#f7f7f7"><div class="mail-usedName"> <span style="font-size:0.8em; color:#54b06d; font-weight:bold;">상품ID </span><span style="font-size:0.9em; font-weight:bold; color: #4a4a4a">'+mail.usedId
          +'</span></div></div>'+
          '<div class="mailContents" style="width:80%; margin: 0 auto; padding: 10px; height:100px; border: 1px solid #e6e6e6; font-size:0.9em;">'+mail.mailContent+'</div><textarea id="swal-input2" class="swal2-textarea" style="resize:none; width:80%; height: 100px; font-size:12px; overflow-y:auto;" maxlength="500" placeholder="답장할 내용을 입력하세요"></textarea>',
        focusConfirm: false,
        showCancelButton: true,
        confirmButtonText: '답장하기',
        cancelButtonText: '취소',
        confirmButtonColor: '#54b06d',
        preConfirm: () => {
          let fetchData = {
            "usedId": mail.usedId,
            "mailSender": this.$store.state.email,
            "mailReceiver": mail.mailSender,
            "mailContent": document.getElementById('swal-input2').value,
          }

          console.log(fetchData);
          fetch('http://13.125.95.210:85/java/mail/sendMail', {
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
  },
  updateMail: function(){
    let fetchData = {}
    fetchData["mailId"] = this.rcvMailList.mailId;
    fetch('http://13.125.95.210:85/java/mail/updateMail', {
            method: "PUT",
            // body : fetchData
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(fetchData)
          })
            .then(Response => Response.json())  //json 파싱 
            .then(data => {
              if (data >= "1") {
                // 성공
                console.log("입력되었습니다.")
              } else {
                // 실패                    
                console.log("입력 실패")
              }
            }).catch(err => console.log(err))
  }
},
created(){
      //받은 쪽지
      fetch('http://13.125.95.210:85/java/mail/receivedMail/'+this.$store.state.email) 
              .then(Response => Response.json())  //json 파싱 
              .then(data => { 
                  this.rcvMailList = data;  
                }).catch(err=>console.log(err))
                
               
      //보낸 쪽지
      fetch('http://13.125.95.210:85/java/mail/sendedMail/'+this.$store.state.email) 
      .then(Response => Response.json())  //json 파싱 
      .then(data => { 
          this.sendMailList = data;  
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
    margin-top: 8%;
    padding: 3.5%;
    /* border: 1px solid #e6e6e6; */
    background-color: #fcfcfc;
    border-radius: 5px;
    box-shadow: 1px 1px 6px 0.2px #e6e6e6;

  }
  .container2{
    width: 90%;
    display: flex;
    margin: 0 auto;
    /* background-color:white; */
    display: flex;
    justify-content: space-between;
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
    background-color: white;
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
  .mail-detail:hover{
    background-color: #f8f8f8;
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