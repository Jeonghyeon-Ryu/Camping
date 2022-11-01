<template>
  <div class="container">
  <h2 style="text-align:center;">📫</h2>
  <h3 style="text-align:center; padding-bottom: 20px;">{{this.$store.state.nickname}} 님의 쪽지함 </h3>

  <div class="container2">
    <div class="rcvMail">
      <div class="mail-head">
        <h4 style="padding: 5px;">📥 받은 쪽지함</h4>
        <!-- <div class="mail-more" @click="mvReceive()">더보기 > </div> -->
      </div>
      <hr style="margin: 5px; border: 0.5px solid #e6e6e6; border-collapse: none;">
      <div class="mails">
        <div class="rcvMailList">
          <div v-if="rcvMailList.length< 1" style="margin-top:30%; text-align: center; font-size: 1.2em; color:gray;"><img src="@/assets/img/used/empty.png" style="width:50px"><div style="text-align: center;">받은 쪽지가 없어요</div></div>
          <div v-for="mail in rcvMailList" :key="mail.id" class="mail-detail" @click="mailDetail(mail)">
            <ul>
              <li><div style="padding:5px 10px;display:flex;justify-content: space-between;"> <div><b>✉ {{mail.mailSender}}</b></div><div @click="deleteMail(mail)">✖</div></div></li> 
            <template v-if="mail.mailContent != null">
              <li v-if="mail.mailContent.length <30" style="padding:10px;" >{{mail.mailContent}}</li>
              <li v-if="mail.mailContent.length >=30" style="padding:10px;">{{mail.mailContent.slice(0,27)}}···<span style="margin-left: 10px; font-size: small; color:gray">더보기</span></li>
            </template>
              <div class="mail-flex" style="display:flex; justify-content: space-between; padding: 5px 8px 0 5px;">
                <li style="color:gray; font-size:small; margin: 3px 5px 0 5px;">{{mail.mailDate}}</li>
                <li style="color:#429c38; font-size:small; font-weight:bold;" v-if="mail.mailStatus === 0">읽지않음</li>
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
        <button class="sendBtn" v-on:click='sendMail()'>쪽지 보내기</button>
        <!-- <div class="mail-more" @onclick="mvSend">더보기 > </div> -->
      </div>
      <hr style="margin: 5px; border: 0.5px solid #e6e6e6; border-collapse: none;">
      <div class="mails">
        <div class="rcvMailList">
          <div v-if="sendMailList.length< 1" style="margin-top:30%; text-align: center; font-size: 1.2em; color:gray;"><img src="@/assets/img/used/empty.png" style="width:50px"><div style="text-align: center;">보낸 쪽지가 없어요</div></div>
          <div v-for="mail in sendMailList" :key="mail.id" class="mail-detail" @click="mailDetail2(mail)">
            <ul>
              <li><div style="padding:5px 10px;display:flex;justify-content: space-between;"> <div><b>✉ {{mail.mailReceiver}}</b></div><div style="width:20px;height: 20px;line-height: 27px; border-radius: 3px; text-align: center;" @click="deleteMail(mail)">✖</div></div></li> 
              <template v-if="mail.mailContent != null">
                <li v-if="mail.mailContent.length <30" style="padding:10px;">{{mail.mailContent}}</li>
                <li v-if="mail.mailContent.length >=30" style="padding:10px;">{{mail.mailContent.slice(0,27)}}···<span style="margin-left: 10px; font-size: small; color:gray">더보기</span></li>
              </template>
              <div class="mail-flex" style="display:flex; justify-content: space-between; padding: 5px 8px 0 5px;">
                <li style="color:gray; font-size:small; margin: 3px 5px 0 5px;">{{mail.mailDate}}</li>
                <li style="color:#429c38; font-size:small; font-weight:bold;" v-if="mail.mailStatus === 0">읽지않음</li>
                <li style="color:gray; font-size:small;" v-if="mail.mailStatus != 0">읽음</li>
              </div>
            </ul>

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
    rcvMailList: {},
    sendMailList: {},
  }
},
methods: {
  mailDetail: function(mail){
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
    
  },
  mailDetail2: function(mail){
    //쪽지내용확인
    Swal.fire({
        title: '<div class="mail-Title" style="font-size:0.6em; color: green;">'+mail.mailReceiver+'님에게 보낸 쪽지</div>',
        html: 
        '<div class="mail-info" style="dislay:flex; border-radius:2px; width:80%; margin: 0 auto; padding: 5px; background-color:#f7f7f7"><div class="mail-usedName"> <span style="font-size:0.8em; color:#54b06d; font-weight:bold;">상품ID </span><span style="font-size:0.9em; font-weight:bold; color: #4a4a4a">'+mail.usedId
          +'</span><span style="font-size:0.9em; font-weight:bold; color: #4a4a4a"></span></div></div>'+
          '<div class="mailContents" style="width:80%; margin: 0 auto; padding: 10px; height:100px; border: 1px solid #e6e6e6; font-size:0.9em;">'+mail.mailContent+'</div>',
        showConfirmButton: false,
        showCancelButton: true,
        cancelButtonText: '확인',
        cancelButtonColor: '#54b06d',
    })
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
  
},
sendMail: function(){
  let item = Swal.fire({
        title: '<div class="mail-Title" style="font-size:0.6em; color: green;">쪽지 보내기</div>',
        html: '<span style="font-size:0.8em; color:#54b06d; font-weight:bold; padding:10px;">보내는사람</span><input type="text" class="senderMail" value='+this.$store.state.nickname+
          ' style="padding:10px; border:none; text-align:center" readonly><br><span style="font-size:0.8em; color:#54b06d; font-weight:bold; padding:5px 15px 5px 5px;">받는사람</span><input type="text" class="receiverMail" placeholder="받는 사람의 이메일 입력" style="padding:10px; border: 1px solid #e6e6e6;" autofocus><textarea id="swal-input2" class="swal2-textarea" style="resize:none; width:80%; height: 100px; font-size:12px; overflow-y:auto;" maxlength="500" placeholder="답장할 내용을 입력하세요"></textarea>',
        focusConfirm: false,
        showCancelButton: true,
        confirmButtonText: '전송하기',
        cancelButtonText: '취소',
        confirmButtonColor: '#54b06d',
        preConfirm: () => {
          let fetchData = {
            "mailSender": this.$store.state.email,
            "mailReceiver": document.querySelector('.receiverMail').value,
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
},
deleteMail: function(mail){
  event.stopPropagation()
  Swal.fire({
        title: '쪽지를 삭제할까요?',
        focusConfirm: false,
        showCancelButton: true,
        confirmButtonText: '확인',
        cancelButtonText: '취소',
        confirmButtonColor: '#54b06d',
        preConfirm: () => {
          let fetchData = {}
          fetchData["mailId"] = mail.mailId;
          console.log(fetchData);
          fetch('http://13.125.95.210:85/java/mail/deleteMail', {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(fetchData)
          }).then(result => result.text())
            .then(result => {
              if (result == "true") {
                Swal.fire({
                  icon: 'success',
                  title: '삭제되었습니다',
                  toast: true,
                  showConfirmButton: false,
                  timer: 700,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
                })
              } else {
                Swal.fire({
                  icon: 'error',
                  title: '삭제 실패 !',
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
  .container{
    width: 80%;
    margin: 0 auto;
    margin-top: 7%;
    padding: 2% 2% 3% 2%;
    /* border: 1px solid #e6e6e6; */
    background-color: #f7f7f7;
    border-radius: 5px;
    box-shadow: 1px 1px 6px 0.2px #e6e6e6;
  }
  .container2{
    width: 85%;
    display: flex;
    margin: 0 auto;
    /* background-color:white; */
    display: flex;
    border-radius: 2px;
    justify-content: space-between;
  }
  .mail-head{
    display: flex;
    justify-content: space-between;
    padding: 10px;
  }
  .sendBtn{
    padding:0 10px 0 10px; 
    border: none; 
    background-color: #81bd7b; 
    border-radius: 5px; 
    color:white; 
    font-weight: bold;
    cursor: pointer;
  }
  .sendBtn:hover{
    background-color: #7ab475; 
  }
  .mail-more{
    font-weight: bold;
    font-size: 0.8em;
    line-height: 30px;
    background-color: #f7f7f7;
    padding:3px 10px 3px 10px;
    border-radius: 4px;
    cursor: pointer;
    margin: 0 auto;
  }
  .rcvMail, .sendMail{
    width: 47%;
    height: 550px;
    padding: 15px;
    margin: 10px;
    border: 1px solid #e6e6e6;
    border-radius: 4px;
    background-color: white;
  }
  .rcvMail:hover, .sendMail:hover{
    box-shadow: 1px 1px 6px 0.2px #e6e6e6;
  }
  .mails{
    overflow: scroll;
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
  /*
  .mails::-webkit-scrollbar, .sendMail::-webkit-scrollbar{
    width: 10px;
  }

  .mails::-webkit-scrollbar-thumb, .sendMail::-webkit-scrollbar{
    height: 30%; 
    background: #d6d6d6; 
    
    border-radius: 10px;
  }

  .mails::-webkit-scrollbar-track, .sendMail::-webkit-scrollbar{
      background: rgba(255, 255, 255, 0.1);  
  } */

</style>