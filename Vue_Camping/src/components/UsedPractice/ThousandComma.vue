<template>

</template>
<script>
export default{

  methods:{
  
    inputNumberFormat: function(obj) {
        obj.value = comma(uncomma(obj.value));
    },
    comma : function(str) {
        str = String(str);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    },
    uncomma: function(str) {
        str = String(str);
        return str.replace(/[^\d]+/g, '');
    }
  }
}
</script>
<style>
</style>