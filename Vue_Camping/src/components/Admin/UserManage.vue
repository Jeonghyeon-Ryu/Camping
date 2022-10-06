<template>
    <div class="user-manage-container">
        <div class="user-manage-title">
            <h2>유저 관리 페이지</h2>
        </div>
        <div class="user-manage-table">
            <UserTable :userData="userData" :perPage="perPage"></UserTable>
        </div>
    </div>
</template>
<!-- -------------------------------------- -->
<script>
import UserTable from './UserTable.vue';
import users from './users';
export default {
    data: function () {
        return {
            userData: users,
            perPage: 20
        }
    },
    components: {
        UserTable
    }
}
</script>
<!-- -------------------------------------- -->
<style scoped>
.user-manage-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 150px;
    width: 100%;
    height: calc(100vh - 150px)
}

.user-manage-container h2 {
    padding: 20px;
}

.user-manage-table {
    width: 90%;
    height: 100%;
}
</style>