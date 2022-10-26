<template>
     <div class="black-bg"  id="del_modal">
        <div class="white-bg">
            <div class="modal_text">삭제 하시겠습니까?</div>
            <hr>
            <div class="modal_btn_container">
                <button class="modal_delbtn" @click="delNote($event)" >ㅇㅋ</button>
                <button class="modal_cancel" @click="$emit('hide_modal')">ㄴㄴ</button>
            </div>
        </div>
    </div>
</template>

<script>

export default {

  props: {
    noteId: Array,
    
    title: {
      type: String,
      require: false,
    },
  },
  methods: { 
    delNote(e) {
        let noteIdObj = this.noteId;
        let noteIds = []; //new Array();
        for(let i=0; i<noteIdObj.length; i++){
            noteIds.push(noteIdObj[i]);
        }
            
        let fetchData = {
            "noteIds" : noteIds
        }
        
        fetch(`http://localhost:8087/java/MyNoteList`,{
            method : "DELETE",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(fetchData)
        })
        .then((response) =>{
            console.log("asdasdasdasd");
            
            this.$emit("goList");
        }).catch(err=>{
            console.log(err)
        });
        },  
  }
}
</script>
 
<style scoped src="@/assets/css/note/MynoteList.css">



</style>