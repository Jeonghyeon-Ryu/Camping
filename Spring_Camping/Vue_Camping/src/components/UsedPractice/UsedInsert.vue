<template>
  <div id="container">
    <form id="container2" method="post" encrypt="multipart/form-data">
      <!-- <h3>상품 등록</h3> -->
        <div class="used-heads">
          <!-- 사진 -->
          <div class="used-insert-image-container">
            <h3>상품 사진</h3>
              <div class='used-insert-image-preview'>
                <ImagePreview :images="images" :isNotList="false" @deleteImages="deleteImages"></ImagePreview>
              </div>
              <div id="camp-register-image-form" encrypt="multipart/form-data" style="padding:20px;">
                <label>사진등록(정사각형 권장)
                  <input @change="changeImage($event)" @dragenter.prevent @dragover.prevent
                    @drop.prevent="dropImage($event)" type="file" multiple style="display:none;">
                </label>
              </div>
          </div>
          <!-- 상품 -->
          <div class="used-info">
            <ul>
              <li>
                <input type="hidden" name="email" :value="$store.state.email">
                <label for="inputWriter">작성자</label>
                <input type="text" name="nickName" id="inputemail" :value="$store.state.nickname" readonly>
              </li>
              <li>
                <label for="inputName">상품명<span class="essential">*</span></label>
                <input type="text" name="usedName" id="inputName" placeholder="상품명을 등록해주세요(최대 20자)" maxlength="20" value='' autofocus>
              </li>
              <li>
                <label for="inputPrice">가격<span class="essential">*</span></label>
                <input type="number" name="usedPrice" id="inputPrice" placeholder="가격을 숫자로 입력하세요">
              </li>
              <hr>
              <li>
                <label for="inputCate">카테고리<span class="essential">*</span></label>
                <select name="usedCategory" id="used_cate" v-model="myGearType">
                  <option value='' disabled>카테고리 선택</option>
                  <option value="텐트">텐트</option>
                  <option value="타프">타프</option>
                  <option value="가구">가구</option>
                  <option value="침구">침구</option>
                  <option value="조리도구">조리도구</option>
                  <option value="조명">조명</option>
                  <option value="수납">수납</option>
                  <option value="공구">공구</option>
                  <option value="냉난방">냉난방</option>
                  <option value="기타">기타</option>
              </select>
              </li>
                <li>
                  <label for="inputPlace">지역<span class="essential">*</span></label>
                    <div class="usedPlace">
                    <select name="usedPlace" v-model="regionSelect" id="districtSelect" @change="districtChange">
                      <option value='' disabled>시/도</option> 
                      <option value='전체'>전체</option>
                      <option value='서울'>서울특별시</option>
                      <option value='부산'>부산광역시</option>
                      <option value='대구'>대구광역시</option>
                      <option value='인천'>인천광역시</option>
                      <option value='광주'>광주광역시</option>
                      <option value='대전'>대전광역시</option>
                      <option value='울산'>울산광역시</option>
                      <option value='경기'>경기도</option>
                      <option value='강원'>강원도</option>
                      <option value='충북'>충청북도</option>
                      <option value='충남'>충청남도</option>
                      <option value='전북'>전라북도</option>
                      <option value='전남'>전라남도</option>
                      <option value='경북'>경상북도</option>
                      <option value='경남'>경상남도</option>
                      <option value='제주특별자치도'>제주도</option>
                    </select>
                    <select v-model="regionSelect2" name="usedPlace" id="citySelect">
                      <option value disabled>시/군/구</option>
                      <option value='전체'>전체</option>
                    </select>
                  </div>
              </li>
              <li>
                  <label for="align">상태</label>
                  <div id="align">
                  <input type="radio" id="radio1" name="usedCondition" value=0 checked>
                  <label for="radio1">상</label>
                  <input type="radio" id="radio2" name="usedCondition" value=1>
                  <label for="radio2">중</label>
                  <input type="radio" id="radio3" name="usedCondition" value=2>
                  <label for="radio3">하</label>
                  </div>
                </li>
              <hr>
              <li id="textbox">
                <label for="used_content">상품설명<span class="essential">*</span></label>
                <div class="used-desc">
                  <textarea name="usedContent" class="used_content" maxlength="500" placeholder="여러 장의 상품 사진과 구입 연도, 브랜드, 사용감, 하자 유무 등 구매자에게 필요한 정보를 꼭 포함해 주세요. (10자 이상 500자 이하)"></textarea>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <!-- 작성버튼 -->
        <div class="used-foot">
              <button @click.prevent="confirm()">작성 완료</button>
        </div>
      </form>
  </div>
</template>
<script>
  import district from "@/assets/district.js"
  import Swal from 'sweetalert2';
  import ImagePreview from '../ImagePreview.vue';


  export default {
    data(){
      return{
        myGearType: '',
        used_writer: 'campingGo',
        regionSelect: '',
        regionSelect2: '',
        nickName : this.$store.state.nickname,
        images: [],
        imagesUrl: [],
        search: ''
      }
    },
    created(){
      console.log(this.nickName)
      // let nick = document.getElementById('inputemail');
      // nick.value = this.$store.state.nickname;
      // console.log(nick)
    },
    components:{
      ImagePreview
    },
    methods: {
      // upload : function(){
      //   const picUpload = document.querySelector('.pic-upload');
      //   const upload = document.querySelector('.uploadarea');
      //   picUpload.click();
      // },
      // numOnly: function(){
      //   let price = document.getElementById('inputPrice').value;
      //   let st = /[^0-9]/g
      //   if(st.test(price.value)){
      //     console.log(price.value)
      //     price.replace(st, "")
      //   }
      // },
      deleteImages(updatedImages) {
        this.images = updatedImages;
        document.querySelector('.used-insert-image-container input[type="file"]').files = updatedImages;
      },
      changeImage(e) {
        let dt = new DataTransfer();
      for(let i = 0; i < this.images.length; i++){
        dt.items.add(this.images[i]);
      }
      for (let i = 0; i < e.target.files.length; i++) {
        dt.items.add(e.target.files[i]);
      }
      this.images = dt.files;
      e.target.files = dt.files;
      console.log('aaa', this.images)
      },
      confirm: function(){
        const form = document.forms.namedItem('#container2')
        let place = document.querySelector('#districtSelect'+'#citySelect')
        let fetchData = [];
        fetchData = new FormData(document.querySelector('#container2'))
        for (let image of this.images) {
        fetchData.append("files", image);
      }

        let name = document.getElementById('inputName').value;
        let price = document.getElementById('inputPrice').value;
        let category = document.getElementById('used_cate').value;
        let content = document.querySelector('.used_content').value;
        let placeVal = document.querySelector('#districtSelect').value;

        // fetchData.append('nickName', this.nickName)
        fetchData.forEach((value,key)=>{
          console.log(key, ":", value);
        })

        if(name === null || name.trim() === ""){
          this.swName();
        }else if(price == null || price.trim() === ""){
          this.swPrice();
        }else if(category == null || category.trim() === ""){
          this.swCategory();
        }else if(placeVal == null || placeVal.trim() === ""){
          this.swPlace();
        }else if(content == null || content.trim() === "" ||content.length < 10){
          this.swContent();
        }else{
            fetch('http://13.125.95.210:85/java/used/usedInsert',{
                    method : "POST",
                    headers : { },
                    body : fetchData
                }) 
                .then(Response => Response.text())
                .then(data => { 
                    if(data>="1"){
                      // 성공
                      console.log("입력되었습니다.")
                      this.$router.push({name : 'usedMain'})
                    } else {
                      // 실패                    
                      console.log("입력 실패")  
                    }
                }).catch(err=>console.log(err))
              
                console.log(fetchData)
        }
      },
      clickCheckBox(e) {
        e.preventDefault();
        let checkItem = e.target.parentElement;
        while (!checkItem.classList.contains('checkboxLabel')) {
          checkItem = checkItem.parentElement;
        }
        checkItem = checkItem.querySelector('input');

        console.log(checkItem.checked);
        if (checkItem.checked) {
          checkItem.checked = false;
        } else {
          checkItem.checked = true;
        }
      },
      //지역선택
      districtChange: function(){
        let sido = document.querySelector('#districtSelect');
        let sigu = document.querySelector('#citySelect');
        let sidoName = sido.value;
        let cityArr = ["서울","부산","인천","대구","광주","대전","울산","경기","강원","충북","충남","경북","경남","전북","전남","제주특별자치도"];

        sigu.options.length=1;  //저장내역 삭제

        let cityIndex = cityArr.indexOf(sidoName);

        let cityList = district.data[cityIndex][sidoName];  //도시배열
        for(let i in cityList){
                    var opt = document.createElement("option");
                    opt.value = cityList[i];
                    opt.innerHTML = cityList[i];
                    sigu.appendChild(opt);
       }
      },
      swName: function(){
        Swal.fire({
          title: '',
          text: '상품명은 필수 입력 사항입니다',
          icon: 'warning',
          
          confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
          cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
          confirmButtonText: '확인', // confirm 버튼 텍스트 지정
          
          reverseButtons: true, // 버튼 순서 거꾸로
          
        })
      },
      swPrice: function(){
        Swal.fire({
          title: '',
          text: '가격은 필수 입력 사항입니다',
          icon: 'warning',
          
          confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
          cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
          confirmButtonText: '확인', // confirm 버튼 텍스트 지정
          
          reverseButtons: true, // 버튼 순서 거꾸로
          
        })
      },
      swCategory: function(){
        Swal.fire({
          title: '',
          text: '카테고리는 필수 선택 사항입니다',
          icon: 'warning',
          
          confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
          cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
          confirmButtonText: '확인', // confirm 버튼 텍스트 지정
          
          reverseButtons: true, // 버튼 순서 거꾸로
          
        })
      },
      swPlace: function(){
        Swal.fire({
          title: '',
          text: '지역은 필수 선택 사항입니다',
          icon: 'warning',
          
          confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
          cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
          confirmButtonText: '확인', // confirm 버튼 텍스트 지정
          
          reverseButtons: true, // 버튼 순서 거꾸로
          
        })
      },
      swPlace: function(){
        Swal.fire({
          title: '',
          text: '거래지역은 필수 선택 사항입니다',
          icon: 'warning',
          
          confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
          cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
          confirmButtonText: '확인', // confirm 버튼 텍스트 지정
          
          reverseButtons: true, // 버튼 순서 거꾸로
          
        })
      },
      swContent: function(){
        Swal.fire({
          title: '',
          text: '상품설명을 10자 이상 작성해주세요',
          icon: 'warning',
          
          confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
          cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
          confirmButtonText: '확인', // confirm 버튼 텍스트 지정
          
          reverseButtons: true, // 버튼 순서 거꾸로
          
        })
      }
    },

  }
</script>
<style scoped src="@/assets/css/used/UsedInsert.css" />

