<template>
    <div> 
        <img v-if="isHeart" class="recru-heart" src='@/assets/img/used/heart2.png' @click.stop="noSaveItem" alt="찜한 카드">
        <img v-if="!isHeart" class="recru-heart" src='@/assets/img/noheart.png' @click.stop="saveItem" alt="카드">
    </div>
    
    </template>
    <script>
    export default{
        props : {recruId : Number},
        data : function(){
            return{
                isHeart: false,
                save : {
                    boardId: this.recruId,
                    boardDivision: 1,
                    email: this.$store.state.email
                }
            }
        },
        created (){
            this.loadSaveData();
        },
        methods:{
            loadSaveData: function () {
                const recruId = this.recruId;
                const component = this;
                fetch(`http://localhost:8087/java/recru/save/${this.save.email}/${this.save.boardDivision}/${this.save.boardId}`)
                    .then(result => result.text())
                    .then(result => {
                    component.isHeart = result == "true" ? true : false;
                })
                    .catch(err => console.log(err));
            },
            saveItem(e) {
                //저장하기 (=찜, 하트)
                e.preventDefault();
                const component = this;
                if (this.$store.state.email != null) {
                    fetch("http://localhost:8087/java/save", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(component.save)
                    }).then(result => result.text())
                    .then(result => {
                        if (result == "true") {
                            component.isHeart = !component.isHeart;
                        }
                    })
                    .catch(err => console.log(err));
                }
            },
            noSaveItem(e) {
                //저장 해체
                e.preventDefault();
                const component = this;
                if (this.$store.state.email != null) {
                    fetch("http://localhost:8087/java/save", {
                        method: "DELETE",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(component.save)
                    }).then(result => result.text())
                    .then(result => {
                        if (result == "true") {
                            component.isHeart = !component.isHeart;
                        }
                    })
                    .catch(err => console.log(err));
                }
            },
        }
    }
    </script>
    <style scoped src="@/assets/css/recruit/recruCard.css" />