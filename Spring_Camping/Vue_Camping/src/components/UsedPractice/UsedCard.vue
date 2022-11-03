<template>
  <!-- 카드 -->
  <div v-on:click='cardDetail' class="card">
    <!-- 카드 좋아요 -->
    <div class="like">
      <img v-if="liked" v-on:click.stop='hearted()' v-bind:src="heartImg">
      <img v-if="!liked" v-on:click.stop="hearted()" v-bind:src="heartImg2">
    </div>
    <!-- 카드사진 -->
    <div class="card-pic">
      <!-- <img v-bind:src="cardImg"> -->
      <div v-for="usedImage of images"><img
          :src="'http://13.125.95.210:85/java/used/showImage/'+usedImage.usedPath+'/'+usedImage.usedStoredName"></div>
    </div>
    <!-- 카드정보  -->
    <div class="card-info">
      <div class="card-top">
        <ul class="card-info-l">
          <div v-if="usedCard.usedName.length > 12">
            <li class="blue-btn">
              <a class="first-link" href=""><h3>{{usedCard.usedName}}</h3></a>
              <input class="used-id" type="hidden" :value="usedCard.usedId">
            </li>
          </div>
          <div v-if="usedCard.usedName.length < 12">
            <li class="blue-btnn">
              <a class="first-linkk" href=""><h3>{{usedCard.usedName}}</h3></a>
              <input class="used-id" type="hidden" :value="usedCard.usedId">
            </li>
          </div>
          <li>
            <h4><span class="font-gray">￦ {{usedCard.usedPrice.toLocaleString('ko-KR')}}</span></h4>
          </li>
        </ul>
        <ul class="card-info-r">
          <li>
            <div v-if="usedCard.dealStatus==0 && usedCard.usedStatus === 0" class="dealGreen">거래가능</div>
            <div v-if="usedCard.dealStatus==1 && usedCard.usedStatus === 0" class="dealRed">거래중</div>
            <div v-if="usedCard.dealStatus==2 && usedCard.usedStatus === 0" class="dealGray">거래완료</div>
            <div v-if="usedCard.usedStatus != 0" class="statusBlack">접근제한</div>
          </li>
        </ul>
      </div>
      <div class="card-bottom">
        <div class="card-place">{{usedCard.usedPlace}}</div>
        <div class="card-write"><span class="font-gray">{{usedCard.usedWrite}}</span></div>
      </div>
    </div>
  </div>
</template>
<script>
import img1 from "@/assets/img/bg9.jpg"
import img2 from "@/assets/img/used/heart.png"
import img3 from "@/assets/img/used/heart2.png"
import Swal from 'sweetalert2'

export default {
  name: "UsedCard",
  props: ['usedCard'],
  data() {
    return {
      cardImg: img1,
      heartImg: img2,
      heartImg2: img3,
      liked: true,
      images: [],
    }
  },
  mounted() {
  },
  methods: {
    cardDetail: function (e) {
      this.$router.push({ name: 'usedDetail' })
    },

    hearted: function () {

      event.preventDefault();
      if (this.$store.state.email === null) {
        Swal.fire({
          icon: 'warning',
          title: '로그인 후에 찜해주세요',
          toast: true,
          showConfirmButton: false,
          timer: 850,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })
      } else if (this.$store.state.email === this.usedCard.email) {
        Swal.fire({
          icon: 'warning',
          title: '내가 쓴 글은 찜할 수 없어요',
          toast: true,
          showConfirmButton: false,
          timer: 900,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })
      } else {
        this.liked = !this.liked

        let save = {
          //boardId: document.querySelector('.used-id').value,
          boardId: this.usedCard.usedId,
          boardDivision: 2,
          email: this.$store.state.email
        }


        console.log(save);
        console.log(save.boardId);

        if (this.liked === true) {
          fetch('http://13.125.95.210:85/java/save', {
            method: 'DELETE',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(save)
          })
            .then(Response => Response.text())  //json 파싱 
            .then(data => {
              if (data >= "1") {
                // 성공
                console.log("삭제되었습니다.")

                Swal.fire({
                  icon: 'error',
                  title: '찜 목록에서 삭제되었어요',
                  toast: true,
                  showConfirmButton: false,
                  timer: 1000,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
                })

              }
            }).catch(err => console.log(err))


        } else if (this.liked === false) {
          fetch('http://13.125.95.210:85/java/save', {
            method: 'POST',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(save)
          })
            .then(Response => Response.text())  //json 파싱 
            .then(data => {
              if (data >= "1") {
                // 성공
                console.log("입력되었습니다.")

                Swal.fire({
                  icon: 'success',
                  title: '찜 목록에 저장되었어요',
                  toast: true,
                  showConfirmButton: false,
                  timer: 1000,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
                })

                //업뎅이트
                this.updateLike();
                // this.$router.push({name : 'usedMain'})
              }
            }).catch(err => console.log(err))

        } else {
          console.log("좋아요실패??")
        }
      }
    },

    updateLike: function () {
      let fetchData = this.usedCard.usedId;

      fetch('http://13.125.95.210:85/java/used/updateLike', {
        method: 'PUT',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(fetchData)
      })

    }
  },
  created() {
    fetch('http://13.125.95.210:85/java/used/usedImage/' + this.usedCard.usedId)
      .then(result => result.json())
      .then(result => {
        this.images = result;
      })
      .catch(err => console.log(err))

    if (this.$store.state.email != null) {
      fetch('http://13.125.95.210:85/java/save?boardId=' + this.usedCard.usedId + '&email=' + this.$store.state.email + '&boardDivision=' + 2)
        .then(result => result.text())
        .then(result => {
          if (result == 'true') {
            this.liked = false;
          }
        })
        .catch(err => console.log(err));
    }
  },
  watch : {
    usedCard : function() {
      fetch('http://13.125.95.210:85/java/used/usedImage/' + this.usedCard.usedId)
      .then(result => result.json())
      .then(result => {
        this.images = result;
      })
      .catch(err => console.log(err))

    if (this.$store.state.email != null) {
      fetch('http://13.125.95.210:85/java/save?boardId=' + this.usedCard.usedId + '&email=' + this.$store.state.email + '&boardDivision=' + 2)
        .then(result => result.text())
        .then(result => {
          if (result == 'true') {
            this.liked = false;
          }
        })
        .catch(err => console.log(err));
    }
    }
  }
}
</script>
<style scoped src="@/assets/css/used/UsedMain.css" />
<style scoped>

.blue-btn{
  width: 190px  !important;
  height: 30px  !important;
}
.blue-btn a{
  color: rgb(34, 34, 34) !important;
  text-decoration:none !important;
  /* margin-top: 0em; */
  text-align: center !important;
  display:inline-block !important; /* important */
  white-space: nowrap !important;
  overflow: hidden !important;
  text-overflow: ellipsis !important;
}

.blue-btn, .first-link{
  -webkit-transition: 3.3s !important;
  -moz-transition: 3.3s !important;
  transition: 3.3s !important;     
  -webkit-transition-timing-function: linear !important;
  -moz-transition-timing-function: linear !important;
  transition-timing-function: linear !important;
}


.blue-btn{
  height: 25px !important;
  overflow: hidden !important;
  width: 190px !important;
  background-color: #ffffff !important;
}

.blue-btn:hover{
   background-color: #ffffff !important;
}

.blue-btn a:hover{
  text-decoration: none !important;
}

.first-link{
  margin-left: 0em !important;   
}

.blue-btn:hover .first-link{
  margin-left: -210px !important;
}

</style>