<template>
    <div class="col-lg-4 mx-auto">
    <div class="input-group input-group-dynamic mb-4">
        <input type="text" class="form-control form-control-md" 
            @keyup.enter="search(srhKeyword)" 
            v-model="srhKeyword"
            placeholder="검색">
        <span class="input-group-text" v-on:click="search(srhKeyword)"><i class="fas fa-search" aria-hidden="true" ></i></span>
    </div>
</div>
</template>

<script>
export default{
    data : function(){
        return{
            srhKeyword : ''
        }
    },
    methods : {
        search : function(srhKeyword){
            alert(srhKeyword)
            this.$parent.loadPage(srhKeyword)
        }
    }
}
</script>