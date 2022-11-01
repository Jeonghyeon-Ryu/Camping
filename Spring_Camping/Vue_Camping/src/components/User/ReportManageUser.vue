<template>
    <div class="manage-container">
        <div class="manage-title">
            <h2>나의 신고</h2>
        </div>
        <div class="manage-table">
            <ReportTable v-model:reportData="reportData" :perPage="perPage"></ReportTable>
        </div>
    </div>
</template>
<!-- -------------------------------------- -->
<script>
import ReportTable from '../DataGrid/Table/ReportTable.vue';
// import user from './users'
export default {
    data: function () {
        return {
            reportData: [],
            perPage: 20
        }
    },
    created : function(){
        fetch('http://localhost:8087/java/report/'+this.$store.state.email)
            .then(result => result.json())
            .then(result => {
                console.log(result);
                this.reportData = result;
            }).catch(err => console.log(err))
    },
    components: { ReportTable }
}
</script>
<!-- -------------------------------------- -->
<style scoped>
.manage-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 150px;
    width: 100%;
    height: calc(100vh - 150px)
}

.manage-container h2 {
    padding: 20px;
}

.manage-table {
    width: 90%;
    height: 100%;
}
</style>