<template>
    <div @click="clickSort" class="sort-container">
        <img v-bind:src="source"/>
    </div>
</template>

<script>
    import asc from '@/assets/img/Table/asc.png'
    import desc from '@/assets/img/Table/desc.png'

    export default {
        props : ['column'],
        data: function () {
            return {
                type : 'asc'
            }
        },
        methods: {
            clickSort : function () {
                if(this.type == 'asc') {
                    this.type = 'desc';
                } else {
                    this.type = 'asc';
                }
                this.$emit('sort', this.column,this.type)
            }
        },
        computed: {
            source : function() {
                if(this.type == 'asc'){
                    return asc;
                } else {
                    return desc;
                }
            }
        }
    }
</script>

<style scoped>
    .sort-container {
        width: 21px;
        height: 21px;
        overflow: hidden;
        margin-left:10px;
    }
    .sort-container img {
        height: 100%;
        object-fit: cover;
    }
</style>