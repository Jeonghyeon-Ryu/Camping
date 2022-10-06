<template>
    <div class="table">
        <ul>
            <li class="table-header row">
                <input @click="checkAll($event)" type="checkbox" name="checkedUser" value="" />
                <div class="table-column" v-for="column of columns">{{column.name}}
                    <Sort v-if="column.sortable" :column="column.prop" @sort="getSortData"></Sort>
                    <Filtering :datas="userData" :column="column.prop" :type="column.type"></Filtering>
                </div>
            </li>
            <li v-for="data of rows" class="table-body row">
                <input type="checkbox" name="checkedUser" value="" />
                <div class="table-column" v-for="column of columns">{{data[column.prop]}}</div>
                <TableButton v-if="modifybtn" :type="'modify'" @modify="modify(data)"></TableButton>
                <TableButton v-if="removebtn" :type="'remove'" @remove="remove(data)"></TableButton>
                <!-- v-if="data.status == 0 ? 1? 판별해서 limit active 둘중 하나만 띄우는거 필요" -->
                <TableButton :type="'limit'" @limit="limit(data)"></TableButton>
                <TableButton :type="'active'" @active="active(data)"></TableButton>
            </li>
        </ul>
        <Pagination :startPage="startPage" :endPage="endPage" :totalPage="totalPage" 
            @changePage="changePage">
        </Pagination>
        <ModifyModal v-if="isModify" 
            :columns="columns" 
            :modifyData="modifyData" 
            @cancelModify="cancelModify"
            @confirmModify="confirmModify">
        </ModifyModal>
    </div>
</template>

<script>
import Pagination from "./Pagination.vue";
import Sort from "./Sort.vue";
import Filtering from "./Filtering.vue";
import TableButton from "./TableButton.vue";
import Swal from 'sweetalert2';
import ModifyModal from "./ModifyModal.vue";

export default {
    props: ["userData", "perPage"],
    data: function () {
        return {
            columns: [
                {
                    name: "이름",
                    prop: "name",
                    sortable: true,
                    size: "200px",
                    type: String
                },
                {
                    name: "이메일",
                    prop: "email",
                    sortable: true,
                    type: String
                },
                {
                    name: "성별",
                    prop: "gender",
                    type: String
                },
                {
                    name: "메일확인",
                    prop: "email_verified_at",
                    type: Date
                }
            ],
            rows: [],
            totalPage: 0,
            currentPage: 1,
            modifybtn: true,
            removebtn: true,
            isModify:false,
            modifyData: {}
        };
    },
    created: function () {
        let total = Object.keys(this.userData).length;
        this.totalPage = Math.ceil(total / this.perPage);
        for (let i = 0; i < this.perPage; i++) {
            this.rows.push(this.userData[i]);
        }
    },
    mounted: function () {
        this.changeColumnSize();
    },
    computed: {
        endPage: function () {

            if ((Math.trunc(((this.currentPage - 1) / 10)) + 1) * 10 > this.totalPage) {
                return this.totalPage;
            } else {
                return (Math.trunc((this.currentPage - 1) / 10) + 1) * 10;
            }
        },
        startPage: function () {
            if (this.endPage == this.totalPage) {
                return (this.endPage - this.endPage % 10)
            } else {
                return this.endPage - 10;
            }
        }
    },
    // 페이징 함수 rows로 보여줌,
    // 페이징 번호 저장 필요
    methods: {
        getSortData: function (column, type) {
            console.log(column, type);
            this.sortJSON(this.rows, column, type);
        },
        // JSON데이터 / 키값 / asc 또는 desc
        sortJSON: function (data, key, type) {
            if (type == undefined) {
                type = "asc";
            }
            return data.sort(function (a, b) {
                var x = a[key];
                var y = b[key];
                if (type == "desc") {
                    return x > y ? -1 : x < y ? 1 : 0;
                }
                else if (type == "asc") {
                    return x < y ? -1 : x > y ? 1 : 0;
                }
            });
        },
        test: function () {
            console.log(this.sortJSON(this.userData, "name", "asc"));
        },
        checkAll: function (e) {
            if (e.target.checked) {
                for (let checkBoxItem of document.querySelectorAll('input[type="checkbox"]')) {
                    checkBoxItem.checked = true;
                }
            } else {
                for (let checkBoxItem of document.querySelectorAll('input[type="checkbox"]')) {
                    checkBoxItem.checked = false;
                }
            }
        },
        changePage: function (pageNum) {
            this.currentPage = pageNum;
        },
        changeColumnSize: function () {
            let styleResult = '40px';
            for (let column of this.columns) {
                if (Object.keys(column).includes('size')) {
                    styleResult = styleResult + ' ' + column.size;
                } else {
                    styleResult = styleResult + ' 1fr';
                }
            }
            if (this.modifybtn) {
                styleResult = styleResult + ' 25px';
            }
            if (this.removebtn) {
                styleResult = styleResult + ' 25px';
            }
            // 나중에 수정 필요 ( 한개만 필요함 )
            styleResult = styleResult + ' 25px 25px';
            document.querySelector('.table').style.setProperty('--gridTemplate', styleResult);
        },
        modify: function (data) {
            this.modifyData = data;
            this.isModify = true;
        },
        remove: function (data) {
            Swal.fire({
                title: '회원정보를 삭제하시겠습니까?',
                text: '회원정보가 영구히 삭제됩니다.',
                showCancelButton: true,
                confirmButtonText: '삭제',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('삭제 취소 !', '', 'success')
                }
            })
        },
        limit: function(data) {
            Swal.fire({
                title: '회원 접근을 제한하시겠습니까?',
                text: '회원의 접근이 제한됩니다.',
                showCancelButton: true,
                confirmButtonText: '제한',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('접근제한 완료 !', '', 'success')
                }
            })
        },
        active: function(data) {
            Swal.fire({
                title: '회원 접근을 허용하시겠습니까?',
                text: '회원의 접근제한이 해제됩니다.',
                showCancelButton: true,
                confirmButtonText: '해제',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire('접근제한 해제 완료 !', '', 'success')
                }
            })
        },
        cancelModify: function() {
            this.modifyData = [];
            this.isModify = false;
        }
    },
    watch: {
        currentPage: function () {
            for (let tableBody of document.querySelectorAll('.table-body')) {
                tableBody.remove();
            }
            for (let i = (this.currentPage * this.perPage); i < (this.currentPage * this.perPage + this.perPage); i++) {
                this.rows.push(this.userData[i]);
            }
        }
    },
    components: { Pagination, Sort, Filtering, TableButton, ModifyModal }
}
</script>

<style scoped src="./UserTable.css">

</style>
