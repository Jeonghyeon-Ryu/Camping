"use strict";(self["webpackChunkcamping"]=self["webpackChunkcamping"]||[]).push([[810],{8810:function(e,t,n){n.r(t),n.d(t,{default:function(){return H}});var s=n(3396),i=n(7139);const o=e=>((0,s.dD)("data-v-b35cad9e"),e=e(),(0,s.Cn)(),e),l={class:"myNoteList"},a={class:"all_container"},c={key:0,class:"black-bg"},d={class:"invite-cancel-white-bg"},r=o((()=>(0,s._)("div",{class:"modal_info"},"공유를 중지할 유저를 선택해주세요",-1))),h=o((()=>(0,s._)("hr",null,null,-1))),_={class:"block_user_container"},v={for:"select_user"},u=["value"],b={class:"invite_btn_container"},p={class:"mynote_container"},m={class:"select_container"},g={class:"fn_btn_container"},k={class:"mynote_btn"},f=o((()=>(0,s._)("div",{class:"select_note"},[(0,s._)("select",{class:"select"},[(0,s._)("option",{value:"orderby_current"},"최신순"),(0,s._)("option",{value:"orderby_order"},"오래된 순"),(0,s._)("option",{vlaue:"orderby_shared"},"공유중")])],-1))),y={class:"container-fluid"},w={class:"row"},C={class:"card-header"},I={class:"noteId"},x={class:"note_state"},M={class:"checkbox"},N=["id"],L=o((()=>(0,s._)("label",{for:"select_card"},null,-1))),O={class:"header_button"},E={class:"card-text"},T=o((()=>(0,s._)("hr",null,null,-1))),D={class:"card-title"},S={class:"all_container"},j={key:0,class:"black-bg"},q={class:"invite-white-bg"},$=(0,s.uE)('<div class="modal_info" data-v-b35cad9e>초대할 유저의 닉네임을 입력해주세요</div><hr data-v-b35cad9e><div class="input_container" data-v-b35cad9e><input class="input_email" placeholder="닉네임을 입력해주세요" data-v-b35cad9e><input class="input_email" placeholder="닉네임을 입력해주세요" data-v-b35cad9e><input class="input_email" placeholder="닉네임을 입력해주세요" data-v-b35cad9e><input class="input_email" placeholder="닉네임을 입력해주세요" data-v-b35cad9e><input class="input_email" placeholder="닉네임을 입력해주세요" data-v-b35cad9e></div><div class="invitedUser_list" data-v-b35cad9e></div>',4),B={class:"invite_btn_container"};function U(e,t,n,o,U,z){return(0,s.wg)(),(0,s.iD)("div",l,[(0,s._)("div",a,[1==U.inviteCancleModalOpen?((0,s.wg)(),(0,s.iD)("div",c,[(0,s._)("div",d,[r,h,(0,s._)("div",_,[(0,s._)("label",v,[(0,s._)("input",{type:"checkbox",id:"select_user",class:"check_block_user",value:e.checked},null,8,u),(0,s.Uk)(" "+(0,i.zw)(e.nickName),1)])]),(0,s._)("div",b,[(0,s._)("button",{class:"success",onClick:t[0]||(t[0]=e=>U.inviteCancleModalOpen=!0)},"초대"),(0,s._)("button",{class:"modal_cancel",onClick:t[1]||(t[1]=e=>U.inviteCancleModalOpen=!1)},"ㄴㄴ")])])])):(0,s.kq)("",!0),(0,s._)("div",p,[(0,s._)("div",m,[(0,s._)("div",g,[(0,s._)("div",k,[(0,s._)("button",{id:"select_btn",onClick:t[2]||(t[2]=(...e)=>z.showCheckBox&&z.showCheckBox(...e))},"선택"),(0,s._)("button",{id:"delete_btn",onClick:t[3]||(t[3]=e=>[U.modalOpen=!0,z.checkedInfo(e)])},"삭제")])]),f]),(0,s._)("div",y,[(0,s._)("div",w,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(U.listInfo,(e=>((0,s.wg)(),(0,s.iD)("div",{class:"card text-white bg-success mb-3",key:e.noteId},[(0,s._)("div",C,[(0,s._)("div",I,(0,i.zw)(e.noteId),1),(0,s._)("div",x,(0,i.zw)(e.noteState),1),(0,s._)("div",M,[U.show?((0,s.wg)(),(0,s.iD)("input",{key:0,type:"checkbox",id:e.noteId,name:"check_one"},null,8,N)):(0,s.kq)("",!0),L]),(0,s._)("div",O,[(0,s._)("button",{id:"delete_btn",onClick:t[4]||(t[4]=e=>z.findId(e))},"삭제"),(0,s._)("button",{id:"invite_btn",onClick:t[5]||(t[5]=e=>[U.inviteModalOpen=!0,z.inviteForm(e)])},"초대"),(0,s._)("button",{id:"block_btn",onClick:t[6]||(t[6]=e=>1==U.inviteCancleModalOpen)},"공유취소")])]),(0,s._)("div",{class:"card-body",onClick:t[7]||(t[7]=e=>z.goMynote(e))},[(0,s._)("div",E,(0,i.zw)(e.writeDate),1),T,(0,s._)("div",D,(0,i.zw)(e.title),1)])])))),128))])]),(0,s._)("div",S,[1==U.inviteModalOpen?((0,s.wg)(),(0,s.iD)("div",j,[(0,s._)("div",q,[$,(0,s._)("div",B,[(0,s._)("button",{class:"success",onClick:t[8]||(t[8]=e=>z.inviteUser(e))},"초대"),(0,s._)("button",{class:"modal_cancel",onClick:t[9]||(t[9]=e=>U.inviteModalOpen=!1)},"ㄴㄴ")])])])):(0,s.kq)("",!0)])])])])}n(7658);var z=n(7273),Z=n(2492),A=n.n(Z),J={name:"MynoteList",data(){return{noteId:[],myNoteId:this.$route.params.noteId,listInfo:[],cardClass:["card text-white bg-secondary mb-3","card text-white bg-success mb-3","card text-white bg-dark mb-3","card text-dark bg-info mb-3","card text-white bg-danger mb-3"],show:!1,modalOpen:!1,inviteModalOpen:!1,inviteCancleModalOpen:!1}},created(){this.getMyListInfo()},methods:{showCheckBox(e){this.show=!this.show},deleteNote(){this.visible=!this.visible},inviteNote(){this.visible=!this.visible},deleteInvite(){this.visible=!this.visible},changeCardColor(){"공유중"===this.noteState?this.cardClass="card text-white bg-dark mb-3":this.cardClass="card text-white bg-success mb-3"},getMyListInfo(){const e=sessionStorage.getItem("email");fetch(`http://localhost:8087/java/MyNoteList/${e}`,{method:"GET",headers:{"Content-Type":"application/json"}}).then((e=>e.json())).then((e=>{this.listInfo=e})).catch((e=>console.log(e)))},findId(e){let t=e.target.parentElement.parentElement.firstChild.innerText;this.noteId.push(t),this.delNote()},checkedInfo(e){let t=document.querySelectorAll('input[type="checkbox"]');for(let n=0;n<t.length;n++)1==t[n].checked&&this.noteId.push(t[n].id);this.delNote()},goList(){this.$router.push({name:"MynoteList"})},delNote(){A().fire({title:"삭제하시겠습니까?",text:"입력한 내용이 삭제됩니다.",showCancelButton:!0,confirmButtonText:"삭제",cancelButtonText:"취소"}).then((e=>{if(e.isConfirmed){let e=this.noteId,t=[];for(let s=0;s<e.length;s++)t.push(e[s]);let n={noteIds:t};fetch("http://localhost:8087/java/MyNoteList",{method:"DELETE",headers:{"Content-Type":"application/json"},body:JSON.stringify(n)}).then((e=>{this.$router.go(0)})).catch((e=>{console.log(e)}))}}))},goMynote(e){let t=e.target.parentElement;while(!t.classList.contains("card"))t=t.parentElement;let n=t.querySelector(".noteId").innerText;this.myNoteId=n,this.$router.push({name:"MyNoteInfo",params:{noteId:this.myNoteId}})},inviteForm(e){let t=e.target;while(!t.classList.contains("card"))t=t.parentElement;let n=t.querySelector(".noteId").innerText;this.noteId.push(n)},inviteUser(e){let t=this.noteId[0];console.log(t);let n=[],s=document.querySelectorAll(".input_email");for(let o=0;o<s.length;o++)n.push(s[o].value),console.log("sssssssssssssss"),console.log(n);let i={noteId:t,invitedMember:n};console.log("-----------fetch"),console.log(i),fetch("http://localhost:8087/java/inviteUser",{method:"PUT",headers:{"Content-Type":"application/json"},body:JSON.stringify(i)}).then((e=>{this.$router.go(0)})).catch((e=>{console.log(e)}))}},components:{Modal:z.Z}},F=n(89);const G=(0,F.Z)(J,[["render",U],["__scopeId","data-v-b35cad9e"]]);var H=G},7273:function(e,t,n){n.d(t,{Z:function(){return u}});var s=n(3396);const i=e=>((0,s.dD)("data-v-068e05fd"),e=e(),(0,s.Cn)(),e),o={class:"black-bg",id:"del_modal"},l={class:"white-bg"},a=i((()=>(0,s._)("div",{class:"modal_text"},"삭제 하시겠습니까?",-1))),c=i((()=>(0,s._)("hr",null,null,-1))),d={class:"modal_btn_container"};function r(e,t,n,i,r,h){return(0,s.wg)(),(0,s.iD)("div",o,[(0,s._)("div",l,[a,c,(0,s._)("div",d,[(0,s._)("button",{class:"modal_delbtn",onClick:t[0]||(t[0]=e=>h.delNote(e))},"ㅇㅋ"),(0,s._)("button",{class:"modal_cancel",onClick:t[1]||(t[1]=t=>e.$emit("hide_modal"))},"ㄴㄴ")])])])}n(7658);var h={props:{noteId:Array,title:{type:String,require:!1}},methods:{delNote(e){let t=this.noteId,n=[];for(let i=0;i<t.length;i++)n.push(t[i]);let s={noteIds:n};fetch("http://localhost:8087/java/MyNoteList",{method:"DELETE",headers:{"Content-Type":"application/json"},body:JSON.stringify(s)}).then((e=>{console.log("asdasdasdasd"),this.$emit("goList")})).catch((e=>{console.log(e)}))}}},_=n(89);const v=(0,_.Z)(h,[["render",r],["__scopeId","data-v-068e05fd"]]);var u=v}}]);
//# sourceMappingURL=810.0694438f.js.map