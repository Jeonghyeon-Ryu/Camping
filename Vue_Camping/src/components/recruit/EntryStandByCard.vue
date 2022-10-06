<template>
  <div class="entry-card-box ">
      <div class="entry-card-row row">
          <div class="entry-card-img col">
              <img class="entry-profile-img" src="@/assets/img/camping_gear01.jpg" alt="profile img">
          </div>
          <div class="entry-card-info col">
              <div class="entry-card-contents">
                  <h3>{{entryCard.member_id}}</h3>
                  <p class="entry-date">{{entryCard.entry_date}}</p>
                  <p>{{entrySex}}, {{entryAge}}, {{entryCard.entry_car}}</p>
                  <p><span>보유장비 : </span> {{entryCard.entry_gear}}</p>
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
                entrySex : '',
                entryAge : 0
            }
        },
        created(){
            //맴버 정보 받아서 성별과, 생일에서 연령대 추출
                this.entrySex = '남';
                this.entryAge = 20;
                this.entryCard.entry_date = this.entryCard.entry_date.toLocaleString();
        },
        methods : {
            entryAccept (){
                Swal.fire({
                    icon: 'warning',
                    title: '신청을 승인하시겠습니까?',
                    text: '동행을 수락합니다',
                    position: 'center-center',
                    showCancelButton: true,              
                }).then(result => {
                    if(result.isConfirmed){
                        Swal.fire('동행을 수락하였습니다.','함께 여행을 떠나요!','success');
                    }
                })
                //등록상태변화
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
                        Swal.fire('동행을 거절하였습니다.','다음에 함께해요','');
                    }
                })
            }
        }
    }
    
</script>
<style scoped src="@/assets/css/recruit/entryCard.css" />
