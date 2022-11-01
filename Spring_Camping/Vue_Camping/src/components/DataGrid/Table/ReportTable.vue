<template>
  <div class="table">
    <ul>
      <li class="table-header row">
        <input @click="checkAll($event)" type="checkbox" name="checkedUser" value="" />
        <div class="table-column" v-for="column of columns">{{ column.name }}
          <Sort v-if="column.sortable" :column="column.prop" @sort="getSortData"></Sort>
          <Filtering :datas="reportData" :column="column.prop" :type="column.type"></Filtering>
        </div>
        <div class="excel-export-container">
          <ExcelExport :inputData="reportData"></ExcelExport>
        </div>
      </li>
      <li v-for="(data, index) of rows" class="table-body row">
        <input type="checkbox" name="checkedUser" value="" />
        <div class="table-column" v-for="column of columns">
          <div v-if="column.type != Date && column.prop != 'boardDivision' && column.prop != 'status'">{{
              data[column.prop]
          }}
          </div>
          <div v-if="column.type != Date && column.prop == 'boardDivision' && column.prop != 'status'">{{
              $filters.formatBoardDivision(data[column.prop])
          }}</div>
          <div v-if="column.type != Date && column.prop != 'boardDivision' && column.prop == 'status'">{{
              $filters.formatReportStatus(data[column.prop])
          }}</div>
          <div v-if="column.type == Date">{{ $filters.formatDate(data[column.prop]) }}</div>
        </div>
        <TableButton v-if="modifybtn" :type="'modify'" @modify="modify(data, index)"></TableButton>
        <TableButton v-if="removebtn && $store.state.auth==0" :type="'remove'" @remove="remove(data, index)"></TableButton>
        <!-- v-if="data.status == 0 ? 1? 판별해서 limit active 둘중 하나만 띄우는거 필요" -->
        <TableButton v-if="$store.state.auth==0" :type="'limit'" @limit="limit(data, index)"></TableButton>
        <TableButton v-if="$store.state.auth==0" :type="'active'" @active="active(data, index)"></TableButton>
      </li>
    </ul>
    <Pagination :startPage="startPage" :endPage="endPage" :totalPage="totalPage" @changePage="changePage">
    </Pagination>
    <ModifyModalReport v-if="isModify" :columns="columns" :modifyData="modifyData" @cancelModify="cancelModify"
      @confirmModify="confirmModify">
    </ModifyModalReport>

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
import ModifyModalReport from "../Modal/ModifyModalReport.vue";

export default {
  props: ["reportData", "perPage"],
  data: function () {
    return {
      columns: [
        {
          name: "캠핑장 번호",
          prop: "boardId",
          sortable: true,
          type: Number
        },
        {
          name: "구분",
          prop: "boardDivision",
          sortable: true,
          type: Number
        },
        {
          name: "대분류",
          prop: "reportDivision",
          type: String
        },
        {
          name: "신고자",
          prop: "email",
          type: String
        },
        {
          name: "신고일자",
          prop: "regdate",
          type: Date
        },
        {
          name: "상태",
          prop: "status",
          sortable: true,
          type: Number
        }
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
    let total = Object.keys(this.reportData).length;
    if (total <= 20) {
      this.totalPage = 1;
    } else {
      this.totalPage = Math.ceil(total / this.perPage);
    }
    for (let i = 0; i < this.perPage; i++) {
      if (Object.keys(this.reportData).length <= i) {
        break;
      }
      console.log(i);
      this.rows.push(this.reportData[i]);
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
    },
    startPage: function () {
      // currentPage - currentPage % 10 + 1 을
      if (this.currentPage <= 10) {
        return 1;
      } else {
        return (this.currentPage - (this.currentPage % 10) + 1);
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
    modify: function (data, index) {
      this.modifyData = data;
      this.isModify = true;
    },
    remove: function (data, index) {
      Swal.fire({
        title: '신고를 미처리로 변경하시겠습니까?',
        showCancelButton: true,
        confirmButtonText: '미처리',
        cancelButtonText: '취소'
      }).then((result) => {
        if (result.isConfirmed) {
          fetch('http://13.125.95.210:85/java/report', {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              reportId: data.reportId,
              status: 0
            })
          }).then(result => result.text())
            .then(result => {
              if (result == "true") {
                this.rows[index].status = 0;
                Swal.fire('미처리 변경 !', '', 'success')
              }
            })
        }
      })
    },
    limit: function (data, index) {
      Swal.fire({
        title: '신고 처리를 반려하시겠습니까?',
        showCancelButton: true,
        confirmButtonText: '반려',
        cancelButtonText: '취소'
      }).then((result) => {
        if (result.isConfirmed) {
          fetch('http://13.125.95.210:85/java/report', {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              reportId: data.reportId,
              status: 2
            })
          }).then(result => result.text())
            .then(result => {
              if (result == "true") {
                this.rows[index].status = 2;
                Swal.fire('반려 완료 !', '', 'success');
              }
            })
        }
      })
    },
    active: function (data, index) {
      Swal.fire({
        title: '신고 처리 완료 하시겠습니까?',
        text: '신고 내용이 정상적으로 처리되었음을 알립니다.',
        showCancelButton: true,
        confirmButtonText: '완료',
        cancelButtonText: '취소'
      }).then((result) => {
        if (result.isConfirmed) {
          fetch('http://13.125.95.210:85/java/report', {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              reportId: data.reportId,
              status: 1
            })
          }).then(result => result.text())
            .then(result => {
              if (result == "true") {
                this.rows[index].status = 1;
                Swal.fire('처리 완료 !', '', 'success')
              }
            })
        }
      })
    },
    cancelModify: function () {
      this.modifyData = [];
      this.isModify = false;
    },
    confirmModify: function (modifyData) {
      if (modifyData.boardDivision == 0) {
        this.$router.push({ name: 'CampDetail', params: { campId: modifyData.boardId } });
      } else if (modifyData.boardDivision == 1) {
        this.$router.push({ name: 'recruDetail', params: { recruId: modifyData.boardId } });
      } else if (modifyData.boardDivision == 2) {
        this.$router.push({ name: 'usedDetail', params: { writeNo: modifyData.boardId } });
      } else if (modifyData.boardDivision == 3) {
        this.$router.push({ name: 'SnsDetail', params: { writeNo: modifyData.boardId } });
      }
    },
  },
  watch: {
    currentPage: function () {
      // 현재 페이지 변경될때 현재 데이터 제거 > 새로운 데이터 푸시
      this.rows = [];
      for (let i = ((this.currentPage - 1) * this.perPage); i < ((this.currentPage - 1) * this.perPage + this.perPage); i++) {
        if (i >= this.reportData.length) {
          break;
        } else {
          this.rows.push(this.reportData[i]);
        }
      }


    },
    reportData: function () {
      // 데이터 변경 있을때 전체 페이지, 전체 수 계산, 화면 표시되는 데이터
      let total = Object.keys(this.reportData).length;
      if (total <= 20) {
        this.totalPage = 1;
      } else {
        this.totalPage = Math.ceil(total / this.perPage);
      }
      for (let i = 0; i < this.perPage; i++) {
        if (Object.keys(this.reportData).length <= i) {
          break;
        }
        console.log(i);
        this.rows.push(this.reportData[i]);
      }
      this.$forceUpdate();
    }
  },
  components: { Pagination, Sort, Filtering, TableButton, ModifyModal, ExcelExport, ModifyModalReport }
}
</script>

<style scoped src="./Table.css">

</style>
