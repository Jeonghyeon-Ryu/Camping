<template>
  <!-- 카드 -->
  <div class="recru-card">
    <!-- 카드 헤더 -->
    <div class="recru-card-header" >
        <div class="card-header-img">
          <div v-if="image.imageId==0">
            <img src="@/assets/img/bg9.jpg" alt="camping gear">
          </div>
          <div v-if="image.imageId>0">
            <img :src="'http://13.125.95.210:85/java/recruImg/'+image.imgPath+'/'+image.storedName" alt="캠핑도구 사진"/>
          </div>
        </div>
    </div>
   
    <!--  카드 바디 -->
    <div class="recru-card-body">
      <div class="card-body-title">
        <h1>{{recruCard.recruTitle}}</h1>
      </div>
      <!--  카드 바디 내용 -->
      <div class="card-body-content">
        <p class="card-body-my"><span>갖고있어요 : </span> {{gearList(recruCard.myGear)}} </p>
        <p class="card-body-need"><span>필요해요 : </span>{{gearList(recruCard.needGear)}}</p>
        <hr >
        <p class="card-body-go">{{recruCard.goDate}}</p>
        <p class="card-body-spot">{{recruCard.campingPoint}}</p>
      </div>
    </div>
  </div>  
  </template>
  <script>
  
  export default{
      name: "RecruCard",
      props: { recruCard: Object },
      data: function () {
          return {
              myClass: "",
              image: "",
          };
      },
      created() {
          this.loadImgs();
      },
      methods: {
          loadImgs: function () {
              const recruId = this.recruCard.recruId;
              const component = this;
              fetch("http://13.125.95.210:85/java/recruImg/" + recruId)
                  .then(result => result.json())
                  .then(result => {
                  component.image = result[0];
              })
                  .catch(err => console.log(err));
          },
          gearList(gears) {
              //필요한 장비 배열 정리
              if (gears) {
                  let gearList = gears.split(",");
                  let str = "";
                  for (let i = 0; i < gearList.length; i += 3) {
                      if (i != 0)
                          str += ", ";
                      str += gearList[i] + "(" + gearList[i + 1] + ") " + gearList[i + 2] + "개";
                  }
                  return str;
              }
          }
      },
  
  }
  </script>
  
  <style scoped src="@/assets/css/recruit/recruCard.css"/>