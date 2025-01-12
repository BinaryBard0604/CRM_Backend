import{r as l,a as h,j as e,c as de,B as g,_ as F}from"./index-2e85eed9.js";import{l as ie,C as ce}from"./CMSPagination-8551283f.js";import{F as me}from"./index.es-55149f84.js";import{p as ue}from"./index-fb770e0d.js";import{T as pe}from"./index-9c37b3d3.js";const fe=e.jsx(me,{icon:ue,style:{color:"green"}}),xe=({searchTerm:u})=>{const p=sessionStorage.getItem("branch"),b=sessionStorage.getItem("role"),w=l.useRef(null),N="https://fleetp.com";l.useEffect(()=>{M()},[]);const[I,O]=l.useState(!0),[R,k]=l.useState(!1),[c,$]=l.useState([]),M=async()=>{const t=`${g}/api/staff`;h({method:"post",url:`${t}`,data:{branch:p,user_role:b,status:1,type:"leave_list"}}).then(a=>{$(a.data.result),v(a.data.result),O(!1)}).catch(a=>console.log(a))},S=l.useRef(null),[he,B]=l.useState(!1),[ge,U]=l.useState(!1),[L,v]=l.useState([]),[f,be]=l.useState([]),[d,K]=l.useState(),[q,y]=l.useState(!1),[n,H]=l.useState("asc"),[C,z]=l.useState(1),j=10,[s,i]=l.useState({id:"",leave_from:"",leave_to:"",notes:"",leave_type:"",staff_id:"",leave_form:"",settlement_form:"",appoval_status:""}),[x,J]=l.useState({staff_name:"",change_date:"",passport_number:"",date_employed:""}),[W,G]=l.useState(""),[Q,X]=l.useState(""),[_,T]=l.useState({filedetails:{settlement:null,leave_form:null}});l.useEffect(()=>{const t=c.filter(a=>{const o=u.toLowerCase();return a.full_name.toLowerCase().includes(o)||a.branch.toLowerCase().includes(o)||a.phone.toLowerCase().includes(o)||a.date_employed.toLowerCase().includes(o)||a.leave_approval.toLowerCase().includes(o)||a.job_sts.toLowerCase().includes(o)});v(t)},[u]),l.useLayoutEffect(()=>{const t=f.length>0&&f.length<c.length;B(f.length===c.length),U(t),S.current&&(S.current.indeterminate=t)},[f,c.length]);const Y=t=>{z(t)},Z=(t,a,o)=>t.sort((r,ne)=>{const E=r[a],P=ne[a];return E<P?o==="asc"?-1:1:E>P?o==="asc"?1:-1:0}),m=t=>{const a=d===t&&n==="asc"?"desc":"asc";K(t),H(a);const o=Z([...c],t,n);v(o)};function ee(t){h({method:"post",url:`${g}/api/staff`,data:{val:t,type:"search"}}).then(a=>{if(a.data.vacationdetails[0]!=null){if(i(a.data.vacationdetails[0]),a.data.vacationdetails[0].leave_form!==""&&a.data.vacationdetails[0].leave_form){const o=a.data.vacationdetails[0].leave_form.split("uploads/");G(`${N}/uploads/${o[1]}`)}if(a.data.vacationdetails[0].settlement_form!==""&&a.data.vacationdetails[0].settlement_form){const o=a.data.vacationdetails[0].settlement_form.split("uploads/");X(`${N}/uploads/${o[1]}`)}}else i({...s,leave_from:"",leave_to:"",notes:"",leave_type:"",staff_id:"",leave_form:""});a.data.joindetails!=null&&J(a.data.joindetails[0]),k(!0)}).catch(a=>console.log(a.message))}function D(){k(!1)}function V(t,a,o){const r={..._.filedetails};o=="settlement"?T({filedetails:{...r,settlement:t}}):o=="leave_form"&&T({filedetails:{...r,leave_form:t}})}function te(t){y(!0),s&&s.appoval_status?h({method:"post",url:`${g}/api/leaves`,data:{val:t,approval_status:s.appoval_status,notes:s.notes,type:"update_staff_leave"}}).then(a=>{a.data[0].result>1&&ae(a.data[0].result)}).catch(a=>console.log(a.message)):F.error("Please enter all the required fields")}function ae(t){const a=new FormData;a.append("leave_form",_.filedetails.leave_form||new Blob),a.append("settlement",_.filedetails.settlement||new Blob),a.append("vacationid",t),a.append("passportno",x.passport_number),a.append("leaveform2",s.leave_form),a.append("settlementform2",s.settlement_form);let o=`${g}/api/staffVacationFileUpload`;h.post(o,a,{}).then(r=>{console.log("heree",r.data),F.success(r.data),y(!1),setTimeout(()=>{window.location.reload()},2e3)},r=>{alert(r),y(!1)})}const A=C*j,se=A-j,le=L.slice(se,A),oe=Math.ceil(L.length/j),re=c.length;return e.jsx(e.Fragment,{children:I?e.jsx(de,{}):e.jsxs("div",{className:"flex-1 flex justify-between flex-col rounded-sm border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1 min-w-[900px]",children:[R&&e.jsx("div",{className:"fixed inset-0 z-50 flex items-center justify-center h-full bg-black/90 px-4 py-5",children:e.jsxs("div",{className:"bg-white rounded-lg shadow-lg w-full max-w-3xl overflow-y-auto max-h-[80vh]",children:[e.jsxs("div",{className:"modal-header flex justify-between items-center p-4 border-b",children:[e.jsx("h4",{className:"text-lg font-semibold text-gray-800",children:"Apply Vacation / Leave"}),e.jsx("button",{type:"button",onClick:D,className:"text-gray-500 hover:text-gray-700 transition duration-200",children:"×"})]}),e.jsx("div",{className:"modal-body p-6",children:e.jsxs("form",{id:"myform",encType:"multipart/form-data",children:[e.jsxs("div",{className:"grid grid-cols-1 md:grid-cols-2 gap-6",children:[e.jsxs("div",{children:[e.jsx("label",{className:"block text-sm font-medium text-gray-700",children:"Driver"}),e.jsx("input",{type:"text",id:"driver",name:"driver",defaultValue:x.staff_name,className:"mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50 cursor-not-allowed bg-gray-100",disabled:!0}),e.jsx("input",{type:"hidden",id:"driver_id",name:"driver_id",disabled:!0})]}),e.jsxs("div",{children:[e.jsx("label",{className:"block text-sm font-medium text-gray-700",children:"Leave Type"}),e.jsxs("select",{id:"leave_type",name:"leave_type",onChange:t=>i({...s,leave_type:t.target.value}),defaultValue:s.leave_type||"",className:"mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50",children:[e.jsx("option",{children:"Select Leave Type"}),e.jsx("option",{value:"Sick Leave",children:"Sick Leave"}),e.jsx("option",{value:"Emergency Leave",children:"Emergency Leave"}),e.jsx("option",{value:"Annual Leave",children:"Annual Leave"}),e.jsx("option",{value:"Local Leave",children:"Local Leave"}),e.jsx("option",{value:"Maternity Leave",children:"Maternity Leave"})]})]})]}),e.jsxs("div",{className:"grid grid-cols-1 md:grid-cols-2 gap-6 mt-4",children:[e.jsxs("div",{children:[e.jsxs("label",{className:"block text-sm font-medium text-gray-700",children:["Leave From ",e.jsx("span",{style:{color:"red"},children:"*"})]}),e.jsx("input",{type:"date",className:"mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50",onChange:t=>i({...s,leave_from:t.target.value}),defaultValue:s.leave_from,id:"start",name:"start",onKeyDown:t=>t.preventDefault()})]}),e.jsxs("div",{children:[e.jsxs("label",{className:"block text-sm font-medium text-gray-700",children:["Leave To ",e.jsx("span",{style:{color:"red"},children:"*"})]}),e.jsx("input",{type:"date",className:"mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50",id:"end",onChange:t=>i({...s,leave_to:t.target.value}),defaultValue:s.leave_to,name:"end",onKeyDown:t=>t.preventDefault()})]})]}),e.jsxs("div",{className:"grid grid-cols-1 md:grid-cols-2 gap-6 mt-4",children:[e.jsxs("div",{children:[e.jsx("label",{className:"block text-sm font-medium text-gray-700",children:"Joined Date"}),e.jsx("input",{type:"date",className:"mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50 cursor-not-allowed bg-gray-100",id:"joined_date",defaultValue:x.date_employed,name:"joined_date",disabled:!0})]}),e.jsxs("div",{children:[e.jsx("label",{className:"block text-sm font-medium text-gray-700",children:"Change Status Date"}),e.jsx("input",{type:"date",className:"mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50 cursor-not-allowed bg-gray-100",id:"change_date",defaultValue:x.change_date,name:"change_date",disabled:!0})]})]}),e.jsxs("div",{className:"mt-4",children:[e.jsx("label",{className:"block text-sm font-medium text-gray-700",children:"Special Notes"}),e.jsx("textarea",{className:"mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50",rows:3,onChange:t=>i({...s,notes:t.target.value}),name:"notes",defaultValue:s.notes,id:"notes"})]}),e.jsxs("div",{className:"grid grid-cols-1 md:grid-cols-2 gap-6 mt-4",children:[e.jsxs("div",{children:[e.jsx("label",{className:"block text-sm font-medium text-gray-700",children:"Approval Status"}),e.jsxs("select",{id:"leave_type",name:"leave_type",onChange:t=>i({...s,appoval_status:t.target.value}),defaultValue:s.appoval_status||"",className:"mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50",children:[e.jsx("option",{children:"Select Status"}),e.jsx("option",{value:"0",children:"Pending"}),e.jsx("option",{value:"1",children:"Approved"}),e.jsx("option",{value:"2",children:"Rejected"})]})]}),e.jsxs("div",{children:[e.jsxs("label",{className:"block text-sm font-medium text-gray-700",children:["Leave Form ",e.jsx("span",{className:"text-red-500",children:"*"})]}),e.jsx("input",{type:"file",className:"mt-1 block w-full border border-gray-300 rounded-md",onChange:t=>{const a=t.target.files?t.target.files[0]:null;V(a,1,"leave_form")}}),s.leave_form&&s.leave_form!==""?e.jsx("a",{href:W,className:"text-blue-500 hover:underline mt-2 block",target:"_blank",rel:"noopener noreferrer",children:"View Form"}):null]})]}),e.jsxs("div",{children:[e.jsx("label",{className:"block text-sm font-medium text-gray-700",children:"Settlement Form"}),e.jsx("input",{type:"file",className:"mt-1 block w-full border border-gray-300 rounded-md bg-gray-100",onChange:t=>{const a=t.target.files?t.target.files[0]:null;V(a,1,"settlement")}}),s.settlement_form&&s.settlement_form!==""?e.jsx("a",{href:Q,target:"_blank",className:"hover:underline mt-2 block",rel:"noopener noreferrer",children:"View Form"}):null]})]})}),e.jsxs("div",{className:"modal-footer p-4 border-t flex justify-between",children:[q?e.jsxs("div",{className:"flex items-center",children:[e.jsx("div",{className:"w-5 h-5 border-4 border-transparent border-t-white animate-spin rounded-full"}),e.jsx("span",{className:"ml-2",children:"Updating..."})," "]}):e.jsx("button",{type:"button",className:"bg-blue-500 font-bold py-2 px-4 rounded hover:bg-blue-600 transition duration-200",onClick:t=>te(s.id),children:"Update Leave"}),e.jsx("button",{type:"button",className:"bg-gray-300 text-gray-700 font-bold py-2 px-4 rounded hover:bg-gray-400 transition duration-200",onClick:D,children:"Close"})]})]})}),e.jsxs("div",{className:"relative overflow-x-scroll",children:[e.jsx(ie.DownloadTableExcel,{filename:"User List",sheet:"vehicles",currentTableRef:w.current,children:e.jsx("button",{className:"bg-blue-500 font-bold py-2 px-4 rounded hover:bg-blue-700 transition duration-300",children:"Export Excel"})}),e.jsxs("table",{ref:w,className:"min-w-full table-fixed divide-y divide-gray-300",children:[e.jsx("thead",{children:e.jsxs("tr",{className:"bg-gray-2 text-left dark:bg-meta-4 border-[#aaaaaa] border-b border-t",children:[e.jsxs("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",onClick:()=>m("full_name"),children:["Full name ",d==="full_name"?n==="asc"?"↑":"↓":""]}),e.jsxs("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",onClick:()=>m("branch"),children:["Branch ",d==="branch"?n==="asc"?"↑":"↓":""]}),e.jsxs("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",onClick:()=>m("phone"),children:["Mobile ",d==="phone"?n==="asc"?"↑":"↓":""]}),e.jsxs("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",onClick:()=>m("date_employed"),children:["Date Employed ",d==="date_employed"?n==="asc"?"↑":"↓":""]}),e.jsxs("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",onClick:()=>m("leave_approval"),children:["Leave Request ",d==="leave_approval"?n==="asc"?"↑":"↓":""]}),e.jsxs("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",onClick:()=>m("job_sts"),children:["Status ",d==="job_sts"?n==="asc"?"↑":"↓":""]}),e.jsx("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900",children:"Actions"})]})}),e.jsx("tbody",{className:"divide-y divide-gray-200 bg-white",children:le.map((t,a)=>e.jsxs("tr",{className:`${a%2===1?"bg-gray-2 dark:bg-meta-4":"dark:bg-boxdark"} hover:cursor-pointer border-b border-[#aaaaaa]`,children:[e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:t.full_name}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:t.branch}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:t.phone}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:t.date_employed}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:t.leave_approval}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:e.jsxs("div",{children:[e.jsx("p",{style:{textAlign:"left",color:"green",fontWeight:"bold"},children:t.job_sts}),t.assignment_sts==""?null:e.jsx("p",{style:{textAlign:"left",color:"red"},children:t.assignment_sts})]})}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:e.jsx("a",{title:"Apply Leave",id:t.id,onClick:()=>ee(t.id),className:"",children:fe})})]},a))})]})]}),e.jsx(ce,{currentPage:C,totalPages:oe,totalNumbers:re,perNumberPage:10,onPageChange:Y})]})})},Ne=()=>{const u=[],[p,b]=l.useState("");return e.jsxs("div",{className:"w-full h-full flex flex-col gap-4 ",children:[e.jsx(pe,{title:"",btns:u,searchTerm:p,setSearchTerm:b}),e.jsx(xe,{searchTerm:p})]})};export{Ne as default};
