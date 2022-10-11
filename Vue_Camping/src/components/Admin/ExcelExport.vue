<template>
  <button class="excel-export"></button>
</template>
<script>
import { XLSX } from "xlsx"
export default {
  props: ['inputData'],
  methods: {
    makeExcelFile5: function () {
      const workBook = XLSX.utils.book_new()    // 새 시트 생성 
      const workSheet = XLSX.utils.json_to_sheet(this.inputData)
      XLSX.utils.book_append_sheet(workBook, workSheet, 'Camp') // 시트 명명, 데이터 지정
      XLSX.writeFile(workBook, 'Camp.xlsx')     // 엑셀파일 만듬
    },
    downloadExcel() {
      var workBook = XLSX.utils.book_new(); 
      var workSheet = XLSX.utils.table_to_sheet(document.getElementById('')); // 테이블 선택자
      XLSX.utils.book_append_sheet(workBook, workSheet, 'Camp');  
      XLSX.writeFile(workBook, 'Camp.xlsx'); 
    }
  }
}
</script>