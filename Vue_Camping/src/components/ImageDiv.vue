<template>
  <div>
    <img :src="file.src"></img>
    <input type="button" value="X" :delFile="file.name" @click="deleteFile($event)"></input>
  </div>
</template>
<script>
  export default {
    data : () => {
      return {
        file : '',
      }
    },
    methods : {
      deleteFile : function(ev){
        let ele = ev.srcElement;
        let delFile = ele.getAttribute('delFile');
        console.log(this.images);
        console.log(this.images.length);
        for(let i=0 ;i<this.images.length; i++){
          if(delFile== this.images[i].name){
            this.images.splice(i, 1);      
          }
        }
        
        let dt = new DataTransfer();
        for(let f in this.images) {
          let file = this.images[f];
          dt.items.add(file);
        }
        btnAtt.files = dt.files;
        let p = ele.parentNode;
        attZone.removeChild(p)
      }
    }
  }

</script>

