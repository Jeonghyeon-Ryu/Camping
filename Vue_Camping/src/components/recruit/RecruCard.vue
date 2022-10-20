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
          <img :src="'http://localhost:8087/java/recruImg/'+image.imgPath+'/'+image.storedName" alt="캠핑도구 사진"/>
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
    <RecruSaveHeart :recruId="recruCard.recruId"></RecruSaveHeart>
  </div>
</div>  
</template>
<script>
import RecruSaveHeart from './RecruSaveHeart.vue';
export default{
    name: "RecruCard",
    props: { recruCard: Object },
    data: function () {
        return {
            recruStatus: "",
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
            fetch("http://localhost:8087/java/recruImg/" + recruId)
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
    components: { RecruSaveHeart }
}
</script>

<style scoped src="@/assets/css/recruit/recruCard.css"/>