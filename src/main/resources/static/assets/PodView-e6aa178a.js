import{r as s,a as $,j as e,c as B,B as U}from"./index-2e85eed9.js";import"./index.es-55149f84.js";import{l as H,C as K}from"./CMSPagination-8551283f.js";import{T as M}from"./index-9c37b3d3.js";const q="/assets/fileicon-075678f2.png",z=({searchTerm:c})=>{const d="https://fleetp.com",i=s.useRef(null),j=window.location.href.split("/").pop();s.useEffect(()=>{E()},[]);const[N,S]=s.useState(!0),[o,P]=s.useState([]),E=async()=>{const t=`${U}/api/pod`,a={type:"podview",manager:j};await $.post(t,a).then(r=>{P(r.data),u(r.data[0].list),S(!1)}).catch(r=>{console.log(r)})},m=s.useRef(null),[J,C]=s.useState(!1),[Q,k]=s.useState(!1),[f,u]=s.useState([]),[x,W]=s.useState([]),[l,v]=s.useState(),[n,L]=s.useState("asc"),[g,T]=s.useState(1),h=10;s.useEffect(()=>{const t=o.filter(a=>{const r=c.toLowerCase();return a.count.toLowerCase().includes(r)||a.id.toLowerCase().includes(r)||a.filename.toLowerCase().includes(r)||a.updated_on.toLowerCase().includes(r)});u(t)},[c]),s.useLayoutEffect(()=>{const t=x.length>0&&x.length<o.length;C(x.length===o.length),k(t),m.current&&(m.current.indeterminate=t)},[x,o.length]);const D=t=>{T(t)},O=(t,a,r)=>t.sort((V,F)=>{const w=V[a],y=F[a];return w<y?r==="asc"?-1:1:w>y?r==="asc"?1:-1:0}),p=t=>{const a=l===t&&n==="asc"?"desc":"asc";v(t),L(a);const r=O([...o],t,n);u(r)},b=g*h,R=b-h,_=f.slice(R,b),I=Math.ceil(f.length/h),A=o.length;return e.jsx(e.Fragment,{children:N?e.jsx(B,{}):e.jsxs("div",{className:"flex-1 flex justify-between flex-col rounded-sm border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1 min-w-[900px]",children:[e.jsxs("div",{className:"relative overflow-x-scroll",children:[e.jsx(H.DownloadTableExcel,{filename:"User List",sheet:"vehicles",currentTableRef:i.current,children:e.jsx("button",{className:"bg-blue-500 font-bold py-2 px-4 rounded hover:bg-blue-700 transition duration-300",children:"Export Excel"})}),e.jsxs("table",{ref:i,className:"min-w-full table-fixed divide-y divide-gray-300",children:[e.jsx("thead",{children:e.jsxs("tr",{className:"bg-gray-2 text-left dark:bg-meta-4 border-[#aaaaaa] border-b border-t",children:[e.jsxs("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",onClick:()=>p("count"),children:["Sl.NO ",l==="count"?n==="asc"?"↑":"↓":""]}),e.jsxs("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",onClick:()=>p("id"),children:["ID ",l==="id"?n==="asc"?"↑":"↓":""]}),e.jsx("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",children:"POD FILE"}),e.jsxs("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",onClick:()=>p("filename"),children:["POD REFERENCE ",l==="filename"?n==="asc"?"↑":"↓":""]}),e.jsxs("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",onClick:()=>p("updated_on"),children:["POD DATE ",l==="updated_on"?n==="asc"?"↑":"↓":""]})]})}),e.jsx("tbody",{className:"divide-y divide-gray-200 bg-white",children:_.map((t,a)=>e.jsxs("tr",{className:`${a%2===1?"bg-gray-2 dark:bg-meta-4":"dark:bg-boxdark"} hover:cursor-pointer border-b border-[#aaaaaa]`,children:[e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:t.count}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:t.id}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:e.jsx("a",{href:`${d}/uploads/POD/${t.filename}`,target:"_blank",style:{textAlign:"left"},children:e.jsx("img",{src:q,style:{width:"20px"}})})}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:t.filename}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:t.updated_on})]},a))})]})]}),e.jsx(K,{currentPage:g,totalPages:I,totalNumbers:A,perNumberPage:10,onPageChange:D})]})})},te=()=>{const c=[{label:"New",path:"/users/add-user"}],[d,i]=s.useState("");return e.jsxs("div",{className:"w-full h-full flex flex-col gap-4 ",children:[e.jsx(M,{title:"",btns:c,searchTerm:d,setSearchTerm:i}),e.jsx(z,{searchTerm:d})]})};export{te as default};
