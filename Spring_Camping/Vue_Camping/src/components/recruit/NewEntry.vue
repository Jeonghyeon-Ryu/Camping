<template>
    <div> 
        <p v-if="isEntry" class="recru-entry">!</p>
    </div>
    
    </template>
    <script>
    export default{
        props : {recruId : Number},
        data : function(){
            return{
                isEntry: false,
                entryList :[]
            }
        },
        created (){
            this.loadSaveData();
        },
        methods:{
            loadSaveData: function () {
                const recruId = this.recruId;
                const component = this;
                fetch(`http://13.125.95.210:85/java/recru/entry/${recruId}`)
                    .then(result => result.json())
                    .then(result => {
                    component.entryList = result 
                    component.entryList.forEach(info=>{
                        if(info.entryStatus==0){
                            component.isEntry=true;
                        }
                    })
                })
                    .catch(err => console.log(err));
            },
            saveItem(e) {
                //저장하기 (=찜, 하트)
                e.preventDefault();
                const component = this;
                if (this.$store.state.email != null) {
                    fetch("http://13.125.95.210:85/java/save", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(component.save)
                    }).then(result => result.text())
                    .then(result => {
                        if (result == "true") {
                            component.isEntry = !component.isEntry;
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
                    fetch("http://13.125.95.210:85/java/save", {
                        method: "DELETE",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(component.save)
                    }).then(result => result.text())
                    .then(result => {
                        if (result == "true") {
                            component.isEntry = !component.isEntry;
                        }
                    })
                    .catch(err => console.log(err));
                }
            },
        }
    }
    </script>
    <style scoped>
    .recru-entry{
        width: 25px;
        height: 25px;
        background: crimson;
        border-radius: 50%;
        color: white;
        font-weight: 600;
    }
</style>