<template>
  <div id="container">
    <form id="container2" method="post">
      <!-- <h3>상품 등록</h3> -->
        <div class="used-heads">
          <div class="usedId" name="usedId"></div>
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
                <label for="inputWriter">작성자</label>
                <p>{{usedList.nickName}}</p>
              </li>
              <li>
                <label for="inputName">상품명<span class="essential">*</span></label>
                <input type="text" name="usedName" id="inputName" placeholder="상품명을 등록해주세요(최대 20자)" maxlength="20" autofocus :value="usedList.usedName">
              </li>
              <li>
                <label for="inputPrice">가격<span class="essential">*</span></label>
                <input type="number" name="usedPrice" id="inputPrice" placeholder="가격을 숫자로 입력하세요" :value="usedList.usedPrice">
              </li>
              <hr>
              <li>
                <label for="inputCate">카테고리<span class="essential">*</span></label>
                <select name="usedCategory" id="used_cate" :value="usedList.usedCategory">
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
                    <select name="usedPlace" id="districtSelect" @change="districtChange" :value="usedList.usedPlace" >
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
                    <select name="usedPlace" id="citySelect">
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
                  <textarea name="usedContent" class="used_content" placeholder="여러 장의 상품 사진과 구입 연도, 브랜드, 사용감, 하자 유무 등 구매자에게 필요한 정보를 꼭 포함해 주세요. (10자 이상)" :value="usedList.usedContent"></textarea>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <!-- 작성버튼 -->
        <div class="used-foot">
              <button @click.prevent="confirm()">수정 완료</button>
        </div>
      </form>
  </div>
</template>
<script>
  import district from "@/assets/district.js"
  import Swal from 'sweetalert2';
  import ImagePreview from '../ImagePreview.vue';


  export default {
    created(){
      //created-페이지 열자마자 실행
      fetch('http://13.125.95.210:85/java/used/usedDetail/'+this.usedId) 
                .then(Response => Response.json())  //json 파싱 
                .then(data => { 
                    console.log(data)
                    this.usedList = data;

                    //const el = document.getElementById('fruit');  //select box
                    //const len = el.options.length; //select box의 option 갯수
                    //const str = document.getElementById('searchValue').value; //입력 받은 value 값
                    //select box의 option 갯수만큼 for문 돌림
                    //for (let i=0; i<len; i++){  
                      //select box의 option value가 입력 받은 value의 값과 일치할 경우 selected
                      //if(el.options[i].value == str){
                        //el.options[i].selected = true;
                      //}
                    //}  



                    // if(data.usedPlace != '전체' && data.usedPlace != null){
                    //   let places = data.usedPlace.split(' ')
                    //   console.log(places)
                    //   document.querySelector('#districtSelect').value = places[0]
                    //   document.querySelector('#citySelect').value = places[1]
                    // }
                }).catch(err=>console.log(err))


    },
    components:{
      ImagePreview
    },
    data(){
      return{
        usedList : [],    
        usedId : this.$route.params.usedId,
        images: [],
        imagesUrl: [],
      }
    },
    methods: {
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
        let fetchData = {};

        let name = document.getElementById('inputName').value;
        let price = document.getElementById('inputPrice').value;
        let category = document.getElementById('used_cate').value;
        let content = document.querySelector('.used_content').value;

        console.log();
            new FormData(document.querySelector('#container2')).forEach((value,key) => fetchData[key]=value);
            // fetchData.append("usedPlace":place,"":)
            // console.log(fetchData);
            // console.log(JSON.stringify(fetchData));
        fetchData["usedId"] = this.usedId;
        if(name === null || name.trim() === ""){
          this.swName();
        }else if(price == null || price.trim() === ""){
          this.swPrice();
        }else if(category == null || category.trim() === ""){
          this.swCategory();
        }else if(content == null || content.trim() === "" ||content.length < 10){
          this.swContent();
        }else{

          Swal.fire({
          title: '',
          text: '수정하시겠습니까?',
          icon: 'warning',
          
          confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
          cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
          confirmButtonText: '확인', // confirm 버튼 텍스트 지정
          cancelButtonText: '취소', // cancel 버튼 텍스트 지정
          
          reverseButtons: true, // 버튼 순서 거꾸로
          
        }).then(result => {
          // 만약 Promise리턴을 받으면,

          fetch('http://13.125.95.210:85/java/used/usedUpdate',{
                    method : "PUT",
                    // body : fetchData
                    headers : {"Content-Type" : "application/json"},
                    body : JSON.stringify(fetchData)
                }) 
                .then(Response => Response.text())  //json 파싱 
                .then(data => { 
                    console.log(data)

                }).catch(err=>console.log(err))
                .finally(this.$router.push({name : 'usedDetail'}))
         }
        );
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
        })
      }
    }

  }
</script>
<style scoped src="@/assets/css/used/UsedInsert.css" />

