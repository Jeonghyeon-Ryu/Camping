<template>
    <div class="entry-card-box ">
        <div class="entry-card-row row">
            <div class="entry-card-img col">
              <img :src="'http://13.125.95.210:85/java/profile/'+storedProfile.imagePath+'/'+storedProfile.storedName">
            </div>
            <div class="entry-card-info col">
                <div class="entry-card-contents">
                    <h4>{{user.nickname}}</h4>
                    <p class="entry-date">{{entryCard.entryDate}}</p>
                    <p>{{user.sex}}, {{userage}}, 차 {{entryCard.entryCar==1?'있음' :'없음'}}</p>
                    <p><span>보유장비 : </span> {{gearList}}</p>
                </div>
                <div class="entry-yesno-btn row">
                    <button type="button" class="accept-btn" @click="entryAccept">수락</button>
                    <button type="button" class="refuse-btn" @click="entryRefusal">거절</button>
                </div>
            </div>
        </div>
    </div>
  </template>
  <script>
      import Swal from 'sweetalert2';
      export default{
          props:{
              entryCard : Object
          },
          data: function(){
              return {
                  user :{},
                  storedProfile : '',
                  updateStatus : {
                      entryStatus : 0,    // 신청상태 : 0신청중, 1수락, 2거절, 3취소대기, 4취소
                      entryId : 0
                  }
              }
          },
          created(){
              this.entryCard.entryDate = this.entryCard.entryDate.toLocaleString('en-US');
              var email = this.entryCard.memberId;
              fetch('http://13.125.95.210:85/java/member/' +email)
                  .then(result => result.json())
                  .then(result => {
                      this.user = result;
                  }).catch(err => console.log(err));
  
                  fetch('http://13.125.95.210:85/java/profile/' + email)
                  .then(result => result.json())
                  .then(result => {
                      this.storedProfile = result;
                  }).catch(err => console.log(err));
          },
          computed : {
              gearList(){
                  //필요한 장비 배열 정리
                  let gearList = this.entryCard.entryGear.split(',');
                  let str = gearList[0]+'('+gearList[1]+') '+gearList[2]+'개'
                  for(let i=3; i<gearList.length; i+=3){
                      str = str+', '+gearList[i]+'('+gearList[i+1]+') '+gearList[i+2]+'개';
                  }
                  return str;
              },
              userage(){
                  //연령대 계산
                  const today = new Date();          
                  let birth = this.user.birth;
                  birth = typeof birth === 'string' ? birth.substring(0, 4) : '';
                  let age = today.getFullYear() - birth + 1;
                  
                  if(age<30){
                      return '20대'
                  }else if(age<40){
                      return '30대'
                  }else if(age<50){
                      return '40대'
                  }else{
                      return '50대 이상'
                  }
              },
          },
          methods : {
              entryAccept (){
                  //신청 수락
                  Swal.fire({
                      icon: 'warning',
                      title: '신청을 승인하시겠습니까?',
                      text: '동행을 수락합니다',
                      position: 'center-center',
                      showCancelButton: true,              
                  }).then(result => {
                      const component = this;
                      if(result.isConfirmed){
                          this.updateStatus.entryStatus = 1;
                          this.updateStatus.entryId = this.entryCard.entryId;
                          let updateInfo = this.updateStatus;
                          //등록상태변화
                          fetch("http://13.125.95.210:85/java/recru/entry",{
                              method : "PUT",
                              headers : {"Content-Type" : "application/json"},
                              body : JSON.stringify(updateInfo)
                          })
                          .then(Response => Response.json())  
                          .then(data => { 
                              console.log(data)
                              Swal.fire('동행을 수락하였습니다.','함께 여행을 떠나요!','success');
                          }).catch(err=>console.log(err))
                          component.$router.go(0) ;
                      }
                  })
              },
              entryRefusal(){
                  Swal.fire({
                      icon: 'warning',
                      title: '신청을 거절하시겠습니까?',
                      text: '함께 할 수 없습니다.',
                      position: 'center-center',
                      showCancelButton: true,              
                  }).then(result => {
                      if(result.isConfirmed){
                          this.updateStatus.entryStatus = 2;
                          this.updateStatus.entryId = this.entryCard.entryId;
                          let updateInfo = this.updateStatus;
                          let component = this;
                           //등록상태변화
                           fetch("http://13.125.95.210:85/java/recru/entry",{
                              method : "PUT",
                              headers : {"Content-Type" : "application/json"},
                              body : JSON.stringify(updateInfo)
                          })
                          .then(Response => Response.json())  
                          .then(data => { 
                              console.log(data)
                              Swal.fire('동행을 거절하였습니다.','다음에 함께해요','');
                          }).catch(err=>console.log(err))
                          component.$router.go(0) ;
                      }
                  })
              },
          }
      }
      
  </script>
  <style scoped src="@/assets/css/recruit/entryCard.css" />
  