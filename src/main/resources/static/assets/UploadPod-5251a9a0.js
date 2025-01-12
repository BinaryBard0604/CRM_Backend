import{r as o,a as i,j as e,B as c,_ as d}from"./index-2e85eed9.js";import{I as _,a as k,E as P}from"./index-497b2833.js";const F=()=>{const x=sessionStorage.getItem("role"),u=sessionStorage.getItem("userid"),[h,A]=o.useState(!1),[m,g]=o.useState(),[p,b]=o.useState(),[a,n]=o.useState({branch:"39",manager:u,updated_on:"",file:null});o.useEffect(()=>{y(),j()},[]);const y=async()=>{const s=`${c}/api/pod`,l={type:"getbranch1",user:u,role:x};try{const t=await i.post(s,l);g(t.data)}catch(t){console.log(t)}},j=async()=>{const s=`${c}/api/pod`,l={type:"getuserlist",user:u,role:x};try{const t=await i.post(s,l);console.log("???????????",t.data),b(t.data)}catch(t){console.log(t)}},v=s=>{s.target.files&&n({...a,file:s.target.files})},w=async()=>{const s=`${c}/api/pod`;if(!a.file||a.updated_on=="")return d.error("Please select a file!"),!1;{const l={type:"uploadPOD",branch:a.branch,manager:a.manager,updated_on:a.updated_on};try{const t=await i.post(s,l);t.data[0].status==="1"?(d.success(t.data[0].message),N(t.data[0].id)):d.error(t.data[0].message)}catch(t){console.log(t)}}},N=async s=>{var f;const l=`${c}/api/podUpload`,t=new FormData;console.log("``````",(f=a.file)==null?void 0:f.length,s),a.file&&a.file.length>0&&t.append("filelist",a.file[0]),t.append("pod_id",s),t.append("type","uploadFILES");try{const r=await i.post(l,t);d.success(r.data.message),r.data.status==="1"&&window.location.reload()}catch(r){console.log(r)}};return e.jsx(e.Fragment,{children:e.jsxs("div",{className:"flex flex-col gap-4 flex-1 h-full",children:[e.jsxs("div",{className:"flex flex-row justify-between",children:[e.jsx("div",{className:"flex flex-row gap-6 items-center",children:e.jsxs("div",{className:"flex flex-row text-center items-center gap-2 font-bold",children:["Complete vehicle profile",e.jsx(_,{})]})}),e.jsx("div",{className:"flex flex-row items-center",children:e.jsx(k,{})})]}),e.jsxs("div",{className:"rounded-sm border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1 flex-1",children:[e.jsx("div",{className:"flex gap-2 mb-5",children:e.jsx("button",{type:"button",onClick:w,className:"flex items-center justify-center block px-5 rounded border border-primary bg-primary p-2 text-center font-medium text-white transition hover:bg-opacity-90",children:h?e.jsxs("div",{className:"flex items-center",children:[e.jsx("div",{className:"w-5 h-5 border-4 border-transparent border-t-white animate-spin rounded-full"}),e.jsx("span",{className:"ml-2",children:"Saving..."})," "]}):e.jsx("p",{children:"Save"})})}),e.jsxs("div",{className:"flex flex-row gap-5 items-center h-10 mb-5",children:[e.jsx(P,{}),e.jsx("h1",{className:"text-3xl font-semibold",children:"Complete vehicle profile"})]}),e.jsx("div",{className:"flex md:flex-row gap-5 flex-col",children:e.jsxs("div",{className:"grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6 w-full",children:[e.jsxs("div",{className:"sm:col-span-3",children:[e.jsx("label",{htmlFor:"first-name",className:"block text-sm/6 font-medium text-gray-900",children:"Account Name"}),e.jsxs("select",{id:"branch1",name:"branch1",onChange:s=>n({...a,branch:s.target.value}),className:"cursor-not-allowed block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6","data-live-search":"true",disabled:!0,children:[m&&m!=""?e.jsx(e.Fragment,{children:m.map(s=>e.jsx("option",{value:s.id,selected:s.id==a.branch,children:s.branch}))}):null,e.jsx("option",{value:"DXB",children:"DXB"})]})]}),e.jsxs("div",{className:"sm:col-span-3",children:[e.jsx("label",{htmlFor:"last-name",className:"block text-sm/6 font-medium text-gray-900",children:"Account Name"}),e.jsx("select",{id:"manager1",name:"manager1",onChange:s=>n({...a,manager:s.target.value}),className:"cursor-not-allowed block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6","data-live-search":"true",disabled:!0,children:p&&p!=""?e.jsx(e.Fragment,{children:p.map(s=>e.jsx("option",{value:s.id,selected:s.id==a.manager,children:s.user}))}):null})]}),e.jsxs("div",{className:"sm:col-span-3",children:[e.jsxs("label",{className:"block text-sm/6 font-medium text-gray-900",children:["AWB Date ",e.jsx("span",{style:{color:"red"},children:"*"})]}),e.jsx("input",{className:"block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6",type:"date",id:"updated_on",name:"updated_on",value:a.updated_on,onChange:s=>n({...a,updated_on:s.target.value})})]}),e.jsxs("div",{className:"sm:col-span-3",children:[e.jsxs("label",{className:"block text-sm/6 font-medium text-gray-900",children:["Upload File ",e.jsx("span",{style:{color:"red"},children:"*"}),"    "]}),e.jsx("input",{className:"block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6",type:"file",id:"pod",onChange:s=>v(s),multiple:!0})]})]})})]})]})})};export{F as default};
