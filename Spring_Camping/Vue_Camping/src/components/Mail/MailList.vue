<template>
  <div class="container">
  <h2 style="text-align:center;">π«</h2>
  <h3 style="text-align:center; padding-bottom: 20px;">{{this.$store.state.nickname}} λμ μͺ½μ§ν¨ </h3>

  <div class="container2">
    <div class="rcvMail">
      <div class="mail-head">
        <h4 style="padding: 5px;">π₯ λ°μ μͺ½μ§ν¨</h4>
        <!-- <div class="mail-more" @click="mvReceive()">λλ³΄κΈ° > </div> -->
      </div>
      <hr style="margin: 5px; border: 0.5px solid #e6e6e6; border-collapse: none;">
      <div class="mails">
        <div class="rcvMailList">
          <div v-if="rcvMailList.length< 1" style="margin-top:30%; text-align: center; font-size: 1.2em; color:gray;"><img src="@/assets/img/used/empty.png" style="width:50px"><div style="text-align: center;">λ°μ μͺ½μ§κ° μμ΄μ</div></div>
          <div v-for="mail in rcvMailList" :key="mail.id" class="mail-detail" @click="mailDetail(mail)">
            <ul>
              <li><div style="padding:5px 10px;display:flex;justify-content: space-between;"> <div><b>β {{mail.mailSender}}</b></div><div @click="deleteMail(mail)">β</div></div></li> 
            <template v-if="mail.mailContent != null">
              <li v-if="mail.mailContent.length <30" style="padding:10px;" >{{mail.mailContent}}</li>
              <li v-if="mail.mailContent.length >=30" style="padding:10px;">{{mail.mailContent.slice(0,27)}}Β·Β·Β·<span style="margin-left: 10px; font-size: small; color:gray">λλ³΄κΈ°</span></li>
            </template>
              <div class="mail-flex" style="display:flex; justify-content: space-between; padding: 5px 8px 0 5px;">
                <li style="color:gray; font-size:small; margin: 3px 5px 0 5px;">{{mail.mailDate}}</li>
                <li style="color:#429c38; font-size:small; font-weight:bold;" v-if="mail.mailStatus === 0">μ½μ§μμ</li>
                <li style="color:gray; font-size:small;" v-if="mail.mailStatus != 0">μ½μ</li>
              </div>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="sendMail">
      <div class="mail-head">
        <h4 style="padding: 5px;">π€ λ³΄λΈ μͺ½μ§ν¨</h4> 
        <button class="sendBtn" v-on:click='sendMail()'>μͺ½μ§ λ³΄λ΄κΈ°</button>
        <!-- <div class="mail-more" @onclick="mvSend">λλ³΄κΈ° > </div> -->
      </div>
      <hr style="margin: 5px; border: 0.5px solid #e6e6e6; border-collapse: none;">
      <div class="mails">
        <div class="rcvMailList">
          <div v-if="sendMailList.length< 1" style="margin-top:30%; text-align: center; font-size: 1.2em; color:gray;"><img src="@/assets/img/used/empty.png" style="width:50px"><div style="text-align: center;">λ³΄λΈ μͺ½μ§κ° μμ΄μ</div></div>
          <div v-for="mail in sendMailList" :key="mail.id" class="mail-detail" @click="mailDetail2(mail)">
            <ul>
              <li><div style="padding:5px 10px;display:flex;justify-content: space-between;"> <div><b>β {{mail.mailReceiver}}</b></div><div style="width:20px;height: 20px;line-height: 27px; border-radius: 3px; text-align: center;" @click="deleteMail(mail)">β</div></div></li> 
              <template v-if="mail.mailContent != null">
                <li v-if="mail.mailContent.length <30" style="padding:10px;">{{mail.mailContent}}</li>
                <li v-if="mail.mailContent.length >=30" style="padding:10px;">{{mail.mailContent.slice(0,27)}}Β·Β·Β·<span style="margin-left: 10px; font-size: small; color:gray">λλ³΄κΈ°</span></li>
              </template>
              <div class="mail-flex" style="display:flex; justify-content: space-between; padding: 5px 8px 0 5px;">
                <li style="color:gray; font-size:small; margin: 3px 5px 0 5px;">{{mail.mailDate}}</li>
                <li style="color:#429c38; font-size:small; font-weight:bold;" v-if="mail.mailStatus === 0">μ½μ§μμ</li>
                <li style="color:gray; font-size:small;" v-if="mail.mailStatus != 0">μ½μ</li>
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
    //μ½μμ²λ¦¬
    let fetchData = {}
    fetchData["mailId"] = mail.mailId;
    fetch('http://13.125.95.210:85/java/mail/updateMail', {
            method: "PUT",
            // body : fetchData
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(fetchData)
          })
            .then(Response => Response.json())  //json νμ± 
            .then(data => {
              if (data >= "1") {
                // μ±κ³΅
                console.log("μλ ₯λμμ΅λλ€.")
              } else {
                // μ€ν¨                    
                console.log("μλ ₯ μ€ν¨")
              }
            }).catch(err => console.log(err))
    
    //μͺ½μ§λ΄μ©νμΈ
    let item = Swal.fire({
        title: '<div class="mail-Title" style="font-size:0.6em; color: green;">'+mail.mailSender+'λμ΄ λ³΄λΈ μͺ½μ§</div>',
        html: 
        '<div class="mail-info" style="dislay:flex; border-radius:2px; width:80%; margin: 0 auto; padding: 5px; background-color:#f7f7f7"><div class="mail-usedName"> <span style="font-size:0.8em; color:#54b06d; font-weight:bold;">μνID </span><span style="font-size:0.9em; font-weight:bold; color: #4a4a4a">'+mail.usedId
          +'</span></div></div>'+
          '<div class="mailContents" style="width:80%; margin: 0 auto; padding: 10px; height:100px; border: 1px solid #e6e6e6; font-size:0.9em;">'+mail.mailContent+'</div><textarea id="swal-input2" class="swal2-textarea" style="resize:none; width:80%; height: 100px; font-size:12px; overflow-y:auto;" maxlength="500" placeholder="λ΅μ₯ν  λ΄μ©μ μλ ₯νμΈμ"></textarea>',
        focusConfirm: false,
        showCancelButton: true,
        confirmButtonText: 'λ΅μ₯νκΈ°',
        cancelButtonText: 'μ·¨μ',
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
                  title: 'μ μ‘λμμ΅λλ€',
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
                  title: 'μ μ‘ μ€ν¨ !',
                  text: 'κ³μ μ€ν¨νλ©΄ κ³ κ°μΌν°μ λ¬Έμν΄μ£ΌμΈμ.',
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
    //μͺ½μ§λ΄μ©νμΈ
    Swal.fire({
        title: '<div class="mail-Title" style="font-size:0.6em; color: green;">'+mail.mailReceiver+'λμκ² λ³΄λΈ μͺ½μ§</div>',
        html: 
        '<div class="mail-info" style="dislay:flex; border-radius:2px; width:80%; margin: 0 auto; padding: 5px; background-color:#f7f7f7"><div class="mail-usedName"> <span style="font-size:0.8em; color:#54b06d; font-weight:bold;">μνID </span><span style="font-size:0.9em; font-weight:bold; color: #4a4a4a">'+mail.usedId
          +'</span><span style="font-size:0.9em; font-weight:bold; color: #4a4a4a"></span></div></div>'+
          '<div class="mailContents" style="width:80%; margin: 0 auto; padding: 10px; height:100px; border: 1px solid #e6e6e6; font-size:0.9em;">'+mail.mailContent+'</div>',
        showConfirmButton: false,
        showCancelButton: true,
        cancelButtonText: 'νμΈ',
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
            .then(Response => Response.json())  //json νμ± 
            .then(data => {
              if (data >= "1") {
                // μ±κ³΅
                console.log("μλ ₯λμμ΅λλ€.")
              } else {
                // μ€ν¨                    
                console.log("μλ ₯ μ€ν¨")
              }
            }).catch(err => console.log(err))
  
},
sendMail: function(){
  let item = Swal.fire({
        title: '<div class="mail-Title" style="font-size:0.6em; color: green;">μͺ½μ§ λ³΄λ΄κΈ°</div>',
        html: '<span style="font-size:0.8em; color:#54b06d; font-weight:bold; padding:10px;">λ³΄λ΄λμ¬λ</span><input type="text" class="senderMail" value='+this.$store.state.nickname+
          ' style="padding:10px; border:none; text-align:center" readonly><br><span style="font-size:0.8em; color:#54b06d; font-weight:bold; padding:5px 15px 5px 5px;">λ°λμ¬λ</span><input type="text" class="receiverMail" placeholder="λ°λ μ¬λμ μ΄λ©μΌ μλ ₯" style="padding:10px; border: 1px solid #e6e6e6;" autofocus><textarea id="swal-input2" class="swal2-textarea" style="resize:none; width:80%; height: 100px; font-size:12px; overflow-y:auto;" maxlength="500" placeholder="λ΅μ₯ν  λ΄μ©μ μλ ₯νμΈμ"></textarea>',
        focusConfirm: false,
        showCancelButton: true,
        confirmButtonText: 'μ μ‘νκΈ°',
        cancelButtonText: 'μ·¨μ',
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
                  title: 'μ μ‘λμμ΅λλ€',
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
                  title: 'μ μ‘ μ€ν¨ !',
                  text: 'κ³μ μ€ν¨νλ©΄ κ³ κ°μΌν°μ λ¬Έμν΄μ£ΌμΈμ.',
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
        title: 'μͺ½μ§λ₯Ό μ­μ ν κΉμ?',
        focusConfirm: false,
        showCancelButton: true,
        confirmButtonText: 'νμΈ',
        cancelButtonText: 'μ·¨μ',
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
                  title: 'μ­μ λμμ΅λλ€',
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
                  title: 'μ­μ  μ€ν¨ !',
                  text: 'κ³μ μ€ν¨νλ©΄ κ³ κ°μΌν°μ λ¬Έμν΄μ£ΌμΈμ.',
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
      //λ°μ μͺ½μ§
      fetch('http://13.125.95.210:85/java/mail/receivedMail/'+this.$store.state.email) 
              .then(Response => Response.json())  //json νμ± 
              .then(data => { 
                  this.rcvMailList = data;  
                }).catch(err=>console.log(err))
                
               
      //λ³΄λΈ μͺ½μ§
      fetch('http://13.125.95.210:85/java/mail/sendedMail/'+this.$store.state.email) 
      .then(Response => Response.json())  //json νμ± 
      .then(data => { 
          this.sendMailList = data;  
      }).catch(err=>console.log(err))
}
}


</script>

<style scoped>
  li{
    text-align: left;
  }
  .container{
    width: 80%;
    margin: 0 auto;
    margin-top: 7%;
    margin-bottom: 6%;
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