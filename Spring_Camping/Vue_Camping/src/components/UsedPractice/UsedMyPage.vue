<template>
  <!--상단(필터, 검색)-->
  <div id="container">
    <div id="container2">
      <form onsubmit="return false">
      <div class="used-headd">
        <div class="used-title">
          <h2 v-if="this.usedList[0] != null">중고장터 {{this.usedList[0].nickName}} 님이 쓴 글</h2>
          
        </div>
      </div>

      <!--본문-->
      <div class="used-body">

        <h2>{{recruMsg}}</h2>
        <div class="cards">
          <div v-for="card in usedList" :key="card.id">
            <router-link tag="div" v-bind:to="{name:'usedDetail',params : {usedId : card.usedId}}">
              <UsedCard v-bind:usedCard="card"></UsedCard>
            </router-link>
          </div>
        </div>
      </div>
    </form>
      <!--하단-->
      <div class="used-foote">
        <!-- <router-link tag="div" v-bind:to="{name:'usedInsert'}">
          <button>+</button> 
        </router-link> -->
        <button v-on:click='usedInsert'>+</button>
                <!--<button v-on:click='usedInsert'>♥</button> -->
      </div>
    </div>
  </div>
</template>
<script>
  import UsedCard from "@/components/UsedPractice/UsedCard.vue"
  import Swal from 'sweetalert2';

  export default{
    props:[
      'email'
    ],
    components:{
    UsedCard,
    },
    data(){
      return{
        usedList: [],
        myGearType: '',
        regionSelect: '',
        regionSelect2: '',
        minPrice: '',
        maxPrice: '',
      }
    },
    methods : {
      cardDetail: function(){
        this.$router.push({name : 'usedDetail'})
      },
      usedInsert: function(){
        if(this.$store.state.email != null){
          this.$router.push({name : 'usedInsert'})
        }else{
          Swal.fire({
                    icon: 'warning',
                    title: '로그인을 해주세요',
                    toast: true,
                    showConfirmButton: false,
                    timer: 1500,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                        this.$router.push({name:"LoginSignup"});
                    }
                })
        }
      },
      usedDetail: function(usedId){
        //클릭하면은 디테일 페이지로 넘겨 넘길때, 번호를 넘겨야해
        this.$router.push({name : 'usedDetail', params: {usedId:usedId }})
      },
    },
    //created-페이지 열자마자 실행
    created(){
      // const email = this.usedList.email;
      let email;
      if(this.email == 'myPage'){
        email = this.$store.state.email;
      }else{
        email = this.email;
      }
      //내가쓴글전체조회
      fetch("http://13.125.95.210:85/java/used/myUsed/"+email,{
        method: "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(email)
      }) 
      .then((Response) => Response.json())  //json 파싱 
      .then(data => { 
        console.log(data)
        this.usedList = data;
        if(this.usedList.length<1){
          this.recruMsg="중고거래 게시물이 없습니다"
        }else{
          this.recruMsg="";
        }
      }).catch(err=>console.log(err));
      
      console.log(this.usedList.nickName)
    }
  }

</script>
<style scoped src="@/assets/css/used/UsedMain.css" />