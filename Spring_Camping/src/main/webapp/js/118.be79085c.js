"use strict";(self["webpackChunkcamping"]=self["webpackChunkcamping"]||[]).push([[118],{8118:function(s,e,t){t.r(e),t.d(e,{default:function(){return j}});var n=t(3396);const a={class:"sns-container"},c={class:"sns-searchbox"},h={class:"sns-img-container"},o=["onClick"],l={class:"sns-img"},i=["value"],r=["src"];function u(s,e,t,u,g,d){const p=(0,n.up)("SnsSearch");return(0,n.wg)(),(0,n.iD)("div",a,[(0,n._)("div",c,[(0,n.Wm)(p,{onShowHashList:d.showHashList},null,8,["onShowHashList"])]),(0,n._)("div",h,[((0,n.wg)(!0),(0,n.iD)(n.HY,null,(0,n.Ko)(s.snsImgs,(s=>((0,n.wg)(),(0,n.iD)("div",{class:"sns-img-row-container",key:s.writeNo,onClick:e=>d.getSnsDetail(s.writeNo)},[(0,n._)("div",l,[(0,n._)("input",{type:"text",value:s.writeNo,style:{display:"none"}},null,8,i),(0,n._)("img",{src:"http://localhost:8087/java/showSnsImage/"+s.snsPath+"/"+s.storedName},null,8,r)])],8,o)))),128))])])}t(7658);var g=t(9242);const d={class:"sns-container"},p={class:"sns-searchbox"},v={class:"sns-search-list-container"},k={class:"sns-search-list"},m=["onClick","value"],f={class:"sns-search-list-container"},S={class:"sns-search-nick-list"},w=["onClick","value"];function N(s,e,t,a,c,h){return(0,n.wg)(),(0,n.iD)("div",d,[(0,n._)("div",p,[(0,n.wy)((0,n._)("input",{type:"search",onKeyup:e[0]||(e[0]=s=>h.checkEnter(s)),"onUpdate:modelValue":e[1]||(e[1]=e=>s.searchText=e),placeholder:"검색어를 입력해주세요."},null,544),[[g.nr,s.searchText]])]),(0,n._)("div",v,[((0,n.wg)(!0),(0,n.iD)(n.HY,null,(0,n.Ko)(s.searchResult,(t=>((0,n.wg)(),(0,n.iD)("div",k,[(0,n.wy)((0,n._)("input",{type:"text",onClick:s=>h.getSnsHashtagList(t),value:t,onChange:e[2]||(e[2]=e=>s.search())},null,40,m),[[g.F8,this.doSearchTag]])])))),256))]),(0,n._)("div",f,[((0,n.wg)(!0),(0,n.iD)(n.HY,null,(0,n.Ko)(s.searchResultNick,(t=>((0,n.wg)(),(0,n.iD)("div",S,[(0,n.wy)((0,n._)("input",{type:"text",onClick:s=>h.getSnsNickFeed(t),value:t,onChange:e[3]||(e[3]=e=>s.search())},null,40,w),[[g.F8,this.doSearchId]])])))),256))])])}var _={created:function(){fetch("http://localhost:8087/java/hashtag").then((s=>s.text())).then((s=>s.substring(2,s.length-2))).then((s=>s.replaceAll('","'," "))).then((s=>s.split(" "))).then((s=>s.join(" "))).then((s=>{console.log(s),this.snsData.push(s)})).catch((s=>console.log(s))),fetch("http://localhost:8087/java/snsnickname").then((s=>s.text())).then((s=>s.substring(2,s.length-2))).then((s=>s.replaceAll('","'," "))).then((s=>s.split(" "))).then((s=>s.join(" "))).then((s=>{this.searchNickname.push(s),console.log(s)}))},components:{},data:()=>({searchText:"",snsData:[],searchNickname:[],searchResult:[],searchResultNick:[]}),methods:{doSearch(){console.log(this.searchText)},checkEnter(s){let e=s.target.value,t=e.substr(0,1);"#"==t?this.doSearchTag(e):this.doSearchId(e),13==s.keyCode&&this.doSearch()},doSearchTag(s){this.searchResult=[],console.log(s);for(let e of this.snsData){console.log(e);let t=e.indexOf(s);if(t>=0){let t=e.split(" "),n=t.filter((e=>e.includes(s)));console.log(n),n.forEach((s=>{this.searchResult.includes(s)||this.searchResult.push(s)}))}}console.log(this.searchResult)},doSearchId(s){this.searchResultNick=[],console.log(s);for(let e of this.searchNickname){let t=e.indexOf(s);if(t>=0){let t=e.split(" "),n=t.filter((e=>e.includes(s)));n.forEach((s=>{this.searchResultNick.includes(s)||this.searchResultNick.push(s)}))}}console.log(this.searchResultNick)},getSnsNickFeed(s){this.$router.push({name:"SnsMyFeed",params:{nickname:s}})},getSnsHashtagList(s){this.$emit("showHashList",s)}}},x=t(89);const y=(0,x.Z)(_,[["render",N],["__scopeId","data-v-dd21e700"]]);var D=y,C={data:function(){return{page:1,snsImgs:[]}},created:function(){fetch("http://localhost:8087/java/sns/"+this.page).then((s=>s.json())).then((s=>{this.snsImgs=s})).catch((s=>console.log(s)))},methods:{doSearch(){console.log(this.searchText)},checkEnter(s){13==s.keyCode&&this.doSearch()},getSnsDetail(s){this.$router.push({name:"SnsDetail",params:{writeNo:s}})},showHashList(s){s=s.substring(1,s.length),console.log("http://localhost:8087/java/hashtagList/"+s+"/"+this.page),fetch("http://localhost:8087/java/hashtagList/"+s+"/"+this.page).then((s=>s.json())).then((s=>{console.log(s),this.snsImgs=s,console.log(s)})).catch((s=>console.log(s)))}},components:{SnsSearch:D}};const R=(0,x.Z)(C,[["render",u],["__scopeId","data-v-d86a3a0e"]]);var j=R}}]);
//# sourceMappingURL=118.be79085c.js.map