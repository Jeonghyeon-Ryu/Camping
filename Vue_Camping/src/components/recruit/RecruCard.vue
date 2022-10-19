<template>
<!-- 카드 -->
<div class="recru-card">
  <!-- 카드 헤더 -->
  <div class="recru-card-header" >
      <div class="card-header-img">
        <div v-if="!image">
          <img src="@/assets/img/bg9.jpg" alt="camping gear">
        </div>
        <div v-if="image">
          <img :src="'http://localhost:8087/java/recruImg/'+image.imgPath+'/'+image.storedName"/>
        </div>
      </div>
      <div class = "card-header-text" :class="myClass" > {{recruStatus}} </div >
  </div>
 
  <!--  카드 바디 -->
  <div class="recru-card-body">
    <div class="card-body-title">
      <h1>{{recruCard.recruTitle}}</h1>
    </div>
    <!--  카드 바디 내용 -->
    <p class="card-body-my"><span>갖고있어요 : </span>{{gearList(recruCard.myGear)}} </p>
    <p class="card-body-need"><span>필요해요 : </span>{{gearList(recruCard.needGear)}}</p>
    <hr style="margin:5px;">
    <p class="card-body-go"><span>여행 날짜 : </span>{{recruCard.goDate}}</p>
    <p class="card-body-spot"><span>캠핑지 : </span> {{recruCard.campingPoint}}</p>
  </div>
  <div class = "recru-card-wish" > 
    <img v-if="isHeart" class="recru-heart" src='@/assets/img/used/heart2.png' @click.stop="changeHeart" alt="찜한 카드">
    <img v-if="!isHeart" class="recru-heart" src='@/assets/img/noheart.png' @click.stop="changeHeart" alt="카드">
  </div>
</div>  
</template>
<script>
export default{
    name : "RecruCard",
    props : {recruCard : Object},
    data : function(){
      return {
        isHeart : false,
        recruStatus : '',
        myClass : '',
        image : ''
      }
    },
    created (){
      const recruId = this.recruCard.recruId;
      const component = this;
      fetch('http://localhost:8087/java/recruImg/'+recruId)
      .then(result => result.json())
      .then(result => {
        component.image = result[0];
      })
      .catch(err => console.log(err))
    },
    watch : {
      //원본데이터 변화를 감지하면
      isHeart(){
        console.log('하트 변경')
        let save = {
            boardId: this.recruCard.recruId,
            boardDivision: 1,
            email: this.$store.state.email
        }
        console.log(save)
        if (save.email) {
          console.log(save.email)
          this.isHeart ? this.saveItem(save) : this.noSaveItem(save);
        }
      }
    },
    methods: {
      changeHeart : function(e){
        e.preventDefault();
        this.isHeart=!this.isHeart;
      },
      gearList(gears){
            //필요한 장비 배열 정리
            if(gears){
                let gearList = gears.split(',');
                let str = '';
                for(let i=0; i<gearList.length; i+=3){
                    if(i!=0) str+= ', ';
                    str += gearList[i]+'('+gearList[i+1]+') '+gearList[i+2]+'개';
                }
                return str;
            }
      },
      saveItem(save) {
        //저장하기 (=찜, 하트)
        console.log(save);
        fetch('http://localhost:8087/java/save', {
            method: 'POST',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(save)
        }).then(result => result.text())
          .then(result => {
            if (result == 'true') {
              alert('하트뿅!')
            }
          })
          .catch(err => console.log(err));
      },
      noSaveItem(save) {
        //저장 해체
        console.log(save);
        fetch('http://localhost:8087/java/save', {
          method: 'DELETE',
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(save)
        }).then(result => result.text())
            .then(result => {
                if (result == 'true') {
                  alert('저장해제')
                }
            })
            .catch(err => console.log(err));
        },
    }
}
</script>

<style scoped src="@/assets/css/recruit/recruCard.css"/>