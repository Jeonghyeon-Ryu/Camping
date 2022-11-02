<template>
  <div class="filter-container">
    <div @click="clickFilter" class="filter-button">
      <img v-bind:src="filterImg" />
    </div>
    <div v-if="isFilter" class="filter-modal">
      <div class="filter-option-container">
        <button class="selected-option" @click="showOptions($event)">필터<img src="@/assets/img/arrow-down.svg"></button>
        <ul>
          <li v-for="option in filterOption"><button class="option-button"
              @click="selectOption($event)">{{option}}</button></li>
        </ul>
      </div>
      <div class="filter-text">
        <input v-if="!isDate" type="text" @keyup.enter="onFilter($event)" placeholder="검색" /> 
        <input v-if="isDate" type="date" @keyup.enter="onFilter($event)" placeholder="검색" />
        <img src="@/assets/img/icons/search-20.png" @click="onFilter($event)" />
      </div>
    </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2';
import filter from '@/assets/img/Table/filter.png'
export default {
  props: [
    'datas',    // 검색할 모든 데이터
    'column',   // 검색할 컬럼
    'type'      // 검색할 컬럼 타입
  ],
  data: function () {
    return {
      filterImg: filter,
      isFilter: false,
      isFiltered: false,
      filterOptions: [
        ['Equal', 'Contain', 'Start', 'End'],
        ['Equal', '>=', '<=', '>', '<'],
        ['Equal', 'Before', 'After']
      ],
      filterOption: [],
      filterDatas: [],
      isDate: (this.type==Date)?true:false
    }
  },
  created: function () {
    if (this.type == String) {
      this.filterOption = this.filterOptions[0];
    } else if (this.type == Number) {
      this.filterOption = this.filterOptions[1];
    } else if (this.type == Date) {
      this.filterOption = this.filterOptions[2];
    }
  },
  methods: {
    clickFilter: function () {  // 필터 모달 열기
      if (this.isFilter) {
        this.isFilter = false;
      } else {
        this.isFilter = true;
      }
    },
    showOptions: function (e) {  // 옵션 보기 ( 필터 Select Box 클릭 )
      let par = e.target.parentElement;
      while (!par.classList.contains('filter-option-container')) {
        par = par.parentElement;
      }
      par.classList.toggle('active-selected-option');
    },
    selectOption: function (e) { // Select Box 옵션 선택 
      let par = e.target.parentElement;
      while (!par.classList.contains('filter-option-container')) {
        par = par.parentElement;
      }
      par.querySelector('.selected-option').innerText = e.target.innerText;
      par.classList.toggle('active-selected-option');
    },
    onFilter: function (e) {
      let par = e.target.parentElement.parentElement;
      let filterOption = par.querySelector('.selected-option').innerText;
      let filterValue = par.querySelector('input').value;

      if (filterOption == '필터') {
        Swal.fire({
          icon: 'warning',
          title: '필터 옵션을 선택하세요.',
          toast: true,
          position: 'center-center',
          showConfirmButton: false,
          timer: 1500,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        }).finally(()=>{
          par.querySelector('.selected-option').click();
        })
      } else if (filterValue == '') {
        Swal.fire({
          icon: 'warning',
          title: '검색할 값을 입력하세요.',
          toast: true,
          position: 'center-center',
          showConfirmButton: false,
          timer: 1500,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        }).finally(()=>{
          par.querySelector('input').focus();
        })
      } else {  // 실제 이벤트 분기
        if(filterOption=='Equal'){
          this.Equal(filterValue);
        } else if(filterOption=='Contain'){
          this.Contain(filterValue);
        } else if(filterOption=='Start'){
          this.Start(filterValue);
        } else if(filterOption=='End'){
          this.End(filterValue);
        } else if(filterOption=='>='){
          this.ge(filterValue);
        } else if(filterOption=='<='){
          this.le(filerValue);
        } else if(filterOption=='<' || filterOption=='Before'){
          this.lt(filterValue);
        } else if(filterOption=='>' || filterOption=='After'){
          this.gt(filterValue);
        }
        this.executeFilter();
        this.isFilter = false;
      }
    },
    Equal : function(filterValue){
      this.filterDatas = [];
      for(let data of this.datas){
        if(data[this.column]==filterValue){
          this.filterDatas.push(data);
        }
      }
      console.log(this.filterDatas);
    },
    Contain : function(filterValue){
      this.filterDatas = [];
      for(let data of this.datas){
        if(data[this.column].indexOf(filterValue)>0){
          this.filterDatas.push(data);
        }
      }
      console.log(this.filterDatas);
    },
    Start: function(filterValue) {
      this.filterDatas = [];
      for(let data of this.datas){
        if(data[this.column].indexOf(filterValue)==0){
          this.filterDatas.push(data);
        }
      }
      console.log(this.filterDatas);
    },
    End : function(filterValue) {
      this.filterDatas = [];
      for(let data of this.datas){
        if(data[this.column].lastIndexOf(filterValue,data[this.column].length)==(data[this.column].length - filterValue.length)){
          this.filterDatas.push(data);
        }
      }
      console.log(this.filterDatas);
    },
    ge: function(filterValue) {  // >=
      this.filterDatas = [];
      for(let data of this.datas){
        if(data[this.column] >= filterValue){
          this.filterDatas.push(data);
        }
      }
      console.log(this.filterDatas);
    },
    le: function(filterValue) {  // <=
      this.filterDatas = [];
      for(let data of this.datas){
        if(data[this.column] <= filterValue){
          this.filterDatas.push(data);
        }
      }
      console.log(this.filterDatas);
    },
    gt: function(filterValue) {  // > , after
      this.filterDatas = [];
      for(let data of this.datas){
        if(data[this.column] > filterValue){
          this.filterDatas.push(data);
        }
      }
      console.log(this.filterDatas);
    },
    lt: function(filterValue) {  // < , Before
      this.filterDatas = [];
      for(let data of this.datas){
        if(data[this.column] < filterValue){
          this.filterDatas.push(data);
        }
      }
      console.log(this.filterDatas);
    },
    executeFilter: function() {
      this.$emit('filterData', this.filterDatas);
      this.isFiltered = true;
    }
  }
}
</script>

<style scoped src="./Filtering.css"></style>