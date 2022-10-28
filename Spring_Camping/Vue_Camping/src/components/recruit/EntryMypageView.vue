<template>
    <div class="entry-mypage-container">
          <div class="entry-mypage-box">
            <div class="entry-mypage-title">
                <h2>여기 갈래</h2>
            </div>
            <div v-for="entryPost in EntryList" :key="entryPost.entryId" class="entry-mypage-mini-box" >
                <div class="entry-mypage-status">
                    <p class="entry-recru-status status-box">{{recruStatus(entryPost.recruStatus)}}</p>
                    <p class="entry-status status-box">{{eStatus(entryPost.entryStatus)}}</p>
                </div>
                <div class="row">
                    <EntryMypageCard v-bind:recruId="entryPost.recruId" @goRecruDetail="goRecruDetail"></EntryMypageCard>
                    <div class="entry-mypage-btn">
                            <button v-if="entryPost.recruStatus==0 && (entryPost.entryStatus==0||entryPost.entryStatus==1)" 
                                    @click="cencelEntry(entryPost.entryId)" class="entry-review-btn">참가 취소</button>
                            <!-- <button v-if="entryPost.recruStatus==1 && entryPost.entryStatus==1"
                                    @click="deleteEntry(entryPost.entryId)" class="entry-review-btn">취소 신청</button> -->
                            <button v-if="entryPost.recruStatus==3" @click="recruReview(entryPost.recruId)" class="entry-review-btn">후기 등록</button>
                            <button v-if="entryPost.recruStatus==3" class="entry-status-btn" disabled>여행완료</button>
                    </div>
                </div>
                <div class="entry-mypage-bar"></div>
            </div>
            <div id="bottomSensor"></div>
          </div>
      </div>
    </template>
    <script>
    import EntryMypageCard from "@/components/recruit/EntryMypageCard.vue";
    import Swal from 'sweetalert2';
    export default{
        components: {
            EntryMypageCard
        },
        data : function(){
            return{
                memberId : this.$store.state.email,//세션에서 받을 로그인 정보
                pageNum : 1,
                EntryList : [],
                rStatus : '',
                tStatus : false
            }
        },    
        created(){
            this.loadDataPage();
        },
        mounted(){
            if(this.$store.state.email==null){
                Swal.fire({
                    title: '로그인이 필요한 서비스입니다.',
                    text: "로그인 페이지로 이동하겠습니까?",
                    icon: 'warning',
                    showCancelButton: true,
                    // confirmButtonColor: '#3085d6',
                            // cancelButtonColor: '#d33',
                            confirmButtonText: '네',
                            cancelButtonText : '아니오'
                        }).then((result) => {
                        if (result.isConfirmed) {
                            this.$router.push({ name: 'LoginSignup'})
                        }else{
                            this.$router.go(-1);
                        }
                    })
                }
                this.addScrollWatcher();       
        },
        methods : {
            loadDataPage : function(){
                //로그인한 유저의 아이디로 신청정보를 받아온다
                const component = this;
                fetch(`http://localhost:8087/java/recru/entry/mypage/${this.memberId}/${this.pageNum}`)
                .then((response) =>response.json()) 
                .then(data => { 
                    for(let key in data){
                        component.EntryList.push(data[key]);  
                    }
                    console.log(component.EntryList);
                }).catch(err=>console.log(err));
            },
            addScrollWatcher: function () {
                const bottomSensor = document.querySelector("#bottomSensor")
                const watcher = scrollMonitor.create(bottomSensor)
                watcher.enterViewport(() => {
                    // 서버 과부하를 막기 위한 딜레이
                    setTimeout(() => {
                      this.pageNum = this.pageNum+1;
                        this.loadDataPage();          
                    },300)
                })
            },
            recruStatus: function(status){
                if (status==0){
                    return '모집중'
                }else if(status==1){
                    return '모집완료'
                }else{
                    return '모집취소'
                }
            },
            eStatus : function(status){
                if (status==0){
                        return '수락 대기'
                    }else if(status==1){
                        return '신청완료'
                    }else if(status==2){
                        return '거절됨'
                    }else if(status==3){
                        return '취소대기'
                    }else{
                        return '취소'
                    }
            },
            goRecruDetail(recruId){
                //디테일 페이지로 이동
                this.$router.push({name:'recruDetail',params : {recruId : recruId}})
            },
            recruReview : function(recruId){
                this.$router.push({name : 'RecruReview',params : {recruId : recruId}})
            },
            cencelEntry : function(entryId){
                var updateInfo = {
                    entryStatus : 2,    // 신청상태 : 0신청중, 1수락, 2거절, 3취소대기, 4취소
                    entryId : entryId
                }
                Swal.fire({
                        icon: 'warning',
                        title: '신청을 취소하시겠습니까?',
                        position: 'center-center',
                        showCancelButton: true,              
                    }).then(result => {
                        if(result.isConfirmed){
                             //등록상태변화
                             fetch("http://localhost:8087/java/recru/entry",{
                                method : "PUT",
                                headers : {"Content-Type" : "application/json"},
                                body : JSON.stringify(updateInfo)
                            })
                            .then(Response => Response.json())  
                            .then(data => { 
                                console.log(data)
                                Swal.fire('동행을 거절하였습니다.','다음에 함께해요','');
                            }).catch(err=>console.log(err))
                            this.$router.go(0) ;
                        }
                    })
            },
            deleteEntry: function(entryId){
                var updateInfo = {
                    entryStatus : 3,    // 신청상태 : 0신청중, 1수락, 2거절, 3취소대기, 4취소
                    entryId : entryId
                }
                Swal.fire({
                        icon: 'warning',
                        title: '모집이 완료된 글입니다. 취소신청 수락시 취소가 진행됩니다',
                        position: 'center-center',
                        showCancelButton: true,              
                    }).then(result => {
                        if(result.isConfirmed){
                             //등록상태변화
                             fetch("http://localhost:8087/java/recru/entry",{
                                method : "PUT",
                                headers : {"Content-Type" : "application/json"},
                                body : JSON.stringify(updateInfo)
                            })
                            .then(Response => Response.json())  
                            .then(data => { 
                                console.log(data)
                                Swal.fire('동행을 거절하였습니다.','다음에 함께해요','');
                            }).catch(err=>console.log(err))
                            this.$router.go(0) ;
                        }
                    })
            },
        }
    }
    </script>
    <style scoped src="@/assets/css/recruit/myPage.css" />