<template>
    <div class="entry-card-box ">
        <div class="entry-card-row row">
            <div class="entry-card-img col">
              <img :src="'http://13.125.95.210:85/java/profile/'+storedProfile.imagePath+'/'+storedProfile.storedName">
            </div>
            <div class="entry-card-info col">
                <div class="entry-card-contents">
                    <h4>{{user.nickname}}</h4>
                    <p>{{user.sex}}, {{userage}}, 차 {{entryCard.entryCar==1?'있음':'없음'}}</p>
                    <p><span>보유장비 : </span> {{gearList}}</p>
                </div>
            </div>
        </div>
    </div>
  </template>
  <script>
      export default{
          props:{
              entryCard : Object
          },
          data: function(){
              return {
                  user :{},
                  storedProfile : ''
              }
          },
          created(){
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
                  //맴버 정보 받아서 성별과, 생일에서 연령대 추출
          },
          computed :{
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
      }
      
  </script>
  <style scoped src="@/assets/css/recruit/entryCard.css" />
  <style scoped>
  
  
  </style>