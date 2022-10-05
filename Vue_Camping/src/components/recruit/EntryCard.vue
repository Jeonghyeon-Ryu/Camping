<template>
  <div class="entry-card-box ">
      <div class="entry-card-row row">
          <div class="entry-card-img col">
              <img class="entry-profile-img" src="@/assets/img/camping_gear01.jpg" alt="profile img">
          </div>
          <div class="entry-card-info col">
              <div class="entry-card-contents">
                  <h3>{{entryCard.member_id}}</h3>
                  <p>{{entrySex}}, {{entryAge}}, {{entryCard.entry_car}}</p>
                  <p><span>보유장비 : </span> {{entryCard.entry_gear}}</p>
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
                entrySex : '',
                entryAge : 0
            }
        },
        created(){
            //맴버 정보 받아서 성별과, 생일에서 연령대 추출
                this.entrySex = '남';
                this.entryAge = 20;
                this.entryCard.entry_date = this.entryCard.entry_date.toLocaleString();
        }
    }
    
</script>
<style scoped src="@/assets/css/recruit/entryCard.css" />
<style scoped>

  .entry-card-contents{
    max-height:105px;
  }
</style>