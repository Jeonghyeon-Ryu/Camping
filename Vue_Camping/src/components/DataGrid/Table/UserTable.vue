<template>
    <div class="table">
        <ul>
            <li class="table-header row">
                <input @click="checkAll($event)" type="checkbox" name="checkedUser" value="" />
                <div class="table-column" v-for="column of columns">{{column.name}}
                    <Sort v-if="column.sortable" :column="column.prop" @sort="getSortData"></Sort>
                    <Filtering :datas="userData" :column="column.prop" :type="column.type"></Filtering>
                </div>
                <div class="excel-export-container">
                    <ExcelExport :inputData="userData"></ExcelExport>
                </div>
            </li>
            <li v-for="(data,index) of rows" class="table-body row">
                <input type="checkbox" name="checkedUser" value="" />
                <div class="table-column" v-for="column of columns">
                    <div v-if="column.type!=Date && column.prop!='status'">{{data[column.prop]}}</div>
                    <div v-if="column.type==Date && column.prop!='status'">{{ $filters.formatDate(data[column.prop]) }}
                    </div>
                    <div v-if="column.type!=Date && column.prop=='status'">{{
                    $filters.formatMemberStatus(data[column.prop]) }}</div>
                </div>
                <TableButton v-if="modifybtn" :type="'modify'" @modify="modify(data,index)"></TableButton>
                <TableButton v-if="removebtn" :type="'remove'" @remove="remove(data,index)"></TableButton>
                <!-- v-if="data.status == 0 ? 1? 판별해서 limit active 둘중 하나만 띄우는거 필요" -->
                <TableButton :type="'limit'" @limit="limit(data,index)"></TableButton>
                <TableButton :type="'active'" @active="active(data,index)"></TableButton>
            </li>
        </ul>
        <Pagination :startPage="startPage" :endPage="endPage" :totalPage="totalPage" @changePage="changePage">
        </Pagination>
        <ModifyModal v-if="isModify" :columns="columns" :modifyData="modifyData" @cancelModify="cancelModify"
            @confirmModify="confirmModify">
        </ModifyModal>

    </div>
</template>

<script>
import Pagination from "../Pagination/Pagination.vue";
import Sort from "../Sort/Sort.vue";
import Filtering from "../Filtering/Filtering.vue";
import TableButton from "../TableButton/TableButton.vue";
import Swal from 'sweetalert2';
import ModifyModal from "../Modal/ModifyModal.vue";
import ExcelExport from "../Export/ExcelExport.vue";

export default {
    props: ["userData", "perPage"],
    data: function () {
        return {
            columns: [
                {
                    name: "닉네임",
                    prop: "nickname",
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
                    prop: "sex",
                    type: String
                },
                {
                    name: "연락처",
                    prop: "phoneNumber",
                    type: String
                },
                {
                    name: "가입일",
                    prop: "regdate",
                    type: Date
                },
                {
                    name: "상태",
                    prop: "status",
                    type: Number
                },
            ],
            rows: [],
            totalPage: 0,
            currentPage: 1,
            modifybtn: true,
            removebtn: true,
            isModify: false,
            modifyData: {}
        };
    },
    created: function () {
        let total = Object.keys(this.userData).length;
        if (total <= 20) {
            this.totalPage = 1;
        } else {
            this.totalPage = Math.ceil(total / this.perPage);
        }
        for (let i = 0; i < this.perPage; i++) {
            if (Object.keys(this.userData).length <= i) {
                break;
            }
            console.log(i);
            this.rows.push(this.userData[i]);
        }
    },
    mounted: function () {
        this.changeColumnSize();
    },
    computed: {
        // 현제 페이지 변경 시 끝페이지 계산
        endPage: function () {
            // 만약 this.totalPage 보다 startPage + 9 가 크면 ? 끝페이지는 this.totalPage % 10 그게 아니면 endPage = 10;
            if (this.totalPage < this.startPage + 9) {
                return this.totalPage % 10;
            } else {
                return 10;
            }
            // if ((Math.trunc(((this.currentPage - 1) / 10)) + 1) * 10 > this.totalPage) {
            //     return this.totalPage;
            // } else {
            //     return (Math.trunc((this.currentPage - 1) / 10) + 1) * 10;
            // }
        },
        startPage: function () {
            // currentPage - currentPage % 10 + 1 을
            if (this.currentPage <= 10) {
                return 1;
            } else {
                return (this.currentPage - (this.currentPage % 10) + 1);
            }

            // if ((this.endPage == this.totalPage) && (this.endPage > 10)) {
            //     return (this.endPage - this.endPage % 10);
            // } else if ((this.endPage == this.totalPage) && (this.endPage <= 10)) {
            //     return this.endPage;
            // } else {
            //     return this.endPage - 10;
            // }
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
                    styleResult = styleResult + ' minmax(200px,1fr)';
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
        modify: function (data, index) {   // 업데이트 필요함
            this.modifyData = data;
            this.isModify = true;
        },
        remove: function (data, index) {
            Swal.fire({
                title: '회원정보를 삭제하시겠습니까?',
                text: '회원정보가 영구히 삭제됩니다.',
                showCancelButton: true,
                confirmButtonText: '삭제',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    fetch('http://localhost:8087/java/member', {
                        method: 'PUT',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({
                            email: data.email,
                            auth: 1,
                            status: 0   // 접속 제한
                        })
                    }).then(result => result.text())
                        .then(result => {
                            if (result == 'true') {
                                Swal.fire({
                                    icon: 'success',
                                    title: '접속 제한 적용 !',
                                    toast: true,
                                    position: 'center-center',
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                        this.userData[index].status = 1;
                                    }
                                })
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: '접속 제한 실패 !',
                                    text: '알수없는 오류로 접속제한이 실패하였습니다.',
                                    toast: true,
                                    position: 'center-center',
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                    }
                                })
                            }
                        })
                }
            })
        },
        limit: function (data, index) {
            Swal.fire({
                title: '회원 접근을 제한하시겠습니까?',
                text: '회원의 접근이 제한됩니다.',
                showCancelButton: true,
                confirmButtonText: '제한',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    fetch('http://localhost:8087/java/member', {
                        method: 'PUT',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({
                            email: data.email,
                            auth: 1,
                            status: 1   // 접속 제한
                        })
                    }).then(result => result.text())
                        .then(result => {
                            if (result == 'true') {
                                Swal.fire({
                                    icon: 'success',
                                    title: '접속 제한 적용 !',
                                    toast: true,
                                    position: 'center-center',
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                        this.userData[index].status = 1;
                                    }
                                })
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: '접속 제한 실패 !',
                                    text: '알수없는 오류로 접속제한이 실패하였습니다.',
                                    toast: true,
                                    position: 'center-center',
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                    }
                                })
                            }
                        })
                }
            })
        },
        active: function (data, index) {
            Swal.fire({
                title: '회원 접근을 허용하시겠습니까?',
                text: '회원의 접근제한이 해제됩니다.',
                showCancelButton: true,
                confirmButtonText: '해제',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    fetch('http://localhost:8087/java/member', {
                        method: 'PUT',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({
                            email: data.email,
                            auth: 1,
                            status: 2   // 접속 허용
                        })
                    }).then(result => result.text())
                        .then(result => {
                            if (result == 'true') {
                                Swal.fire({
                                    icon: 'success',
                                    title: '접속 제한 해제 !',
                                    toast: true,
                                    position: 'center-center',
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                        this.userData[index].status = 2;
                                    }
                                })
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: '접속 제한 해제 실패 !',
                                    text: '알수없는 오류로 접속제한이 실패하였습니다.',
                                    toast: true,
                                    position: 'center-center',
                                    showConfirmButton: false,
                                    timer: 1500,
                                    timerProgressBar: true,
                                    didOpen: (toast) => {
                                        toast.addEventListener('mouseenter', Swal.stopTimer)
                                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                                    }
                                })
                            }
                        })
                }
            })
        },
        cancelModify: function () {
            this.modifyData = [];
            this.isModify = false;
        }
    },
    watch: {
        currentPage: function () {
            // 현재 페이지 변경될때 현재 데이터 제거 > 새로운 데이터 푸시
            this.rows = [];
            for (let i = ((this.currentPage - 1) * this.perPage); i < ((this.currentPage - 1) * this.perPage + this.perPage); i++) {
                if (i >= this.userData.length) {
                    break;
                } else {
                    this.rows.push(this.userData[i]);
                }
            }


        },
        userData: function () {
            // 데이터 변경 있을때 전체 페이지, 전체 수 계산, 화면 표시되는 데이터
            let total = Object.keys(this.userData).length;
            if (total <= 20) {
                this.totalPage = 1;
            } else {
                this.totalPage = Math.ceil(total / this.perPage);
            }
            for (let i = 0; i < this.perPage; i++) {
                if (Object.keys(this.userData).length <= i) {
                    break;
                }
                console.log(i);
                this.rows.push(this.userData[i]);
            }
            this.$forceUpdate();
        }
    },
    components: { Pagination, Sort, Filtering, TableButton, ModifyModal, ExcelExport }
}
</script>

<style scoped src="./Table.css">

</style>
