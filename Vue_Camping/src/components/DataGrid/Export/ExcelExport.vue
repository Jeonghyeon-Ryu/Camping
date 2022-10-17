<template>
  <RButton :inputColor="'green'" :inputSize="'sm'" :inputValue="'엑셀저장'" @clickBtn="makeExcelFile()"></RButton>
</template>
<script>
import * as Xlsx from "xlsx"
import RButton from "../../Admin/RButton.vue"
export default {
  props: ["inputData"],
  methods: {
    makeExcelFile: function () {
      const workBook = Xlsx.utils.book_new(); // 새 시트 생성 
      const workSheet = Xlsx.utils.json_to_sheet(this.inputData);
      Xlsx.utils.book_append_sheet(workBook, workSheet, "Camp"); // 시트 명명, 데이터 지정
      Xlsx.writeFile(workBook, "Camp.xlsx"); // 엑셀파일 만듬
    },
    downloadExcel() {
      var workBook = Xlsx.utils.book_new();
      var workSheet = Xlsx.utils.table_to_sheet(document.getElementById("")); // 테이블 선택자
      Xlsx.utils.book_append_sheet(workBook, workSheet, "Camp");
      Xlsx.writeFile(workBook, "Camp.xlsx");
    }
  },
  components: { RButton }
}
</script>
