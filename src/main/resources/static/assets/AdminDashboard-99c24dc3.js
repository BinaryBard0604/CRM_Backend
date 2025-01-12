import{r as t,a as b,j as e,B as $}from"./index-2e85eed9.js";import{F as g}from"./index.es-55149f84.js";import{b as I,k as C}from"./index-fb770e0d.js";const A=e.jsx(g,{icon:I}),D=e.jsx(g,{icon:C});function P(){const n=sessionStorage.getItem("userid"),i=sessionStorage.getItem("branch"),c=sessionStorage.getItem("role"),[s,y]=t.useState(void 0),[f,v]=t.useState([]),[u,k]=t.useState([]),[j,w]=t.useState([]),[N,_]=t.useState([]),[S,E]=t.useState([]),[x,m]=t.useState(!1),[p,L]=t.useState(!1),h=`${$}/api/staffDashboard`,o=new Date,l=o.toISOString().split("T")[0],F=`${o.getMonth()+1} - ${o.getFullYear()}`;t.useEffect(()=>{b.post(h,{user:n,branch:i,user_role:c,type:"basic"}).then(a=>{console.log(">>>>>>>>>>>>>",a.data.staffcount.length),y({totalStaffs:a.data.staffcount.length,onLeaves:a.data.staffleavescount.length})}).catch(a=>console.log(a.message))},[]),t.useEffect(()=>{b.post(h,{user:n,branch:i,user_role:c,type:"notification"}).then(a=>{v(a.data.passportExpiry),k(a.data.visaExpiry),w(a.data.visaExpired),_(a.data.leaveOverdue),E(a.data.newJoinees),m(!0)}).catch(a=>console.log(a.message))},[]);function d(a){m(a==="staff"),L(a==="leave")}return e.jsxs("div",{children:[e.jsxs("ul",{role:"list",className:"mt-3 grid grid-cols-1 gap-5 sm:grid-cols-2 sm:gap-6 lg:grid-cols-4",children:[e.jsxs("li",{className:"col-span-1 flex rounded-md shadow-sm",children:[e.jsx("div",{className:"flex w-16 shrink-0 items-center justify-center rounded-l-md text-sm font-medium text-white",style:{backgroundColor:"#F79F00"},children:A}),e.jsxs("div",{className:"flex flex-1 items-center justify-between truncate rounded-r-md border-b border-r border-t border-gray-200 bg-white",children:[e.jsxs("div",{className:"flex-1 truncate px-4 py-2 text-sm",children:[e.jsx("a",{href:"/drivers/driver-list",className:"font-medium text-gray-900 hover:text-gray-600",children:"Total Staffs"}),e.jsx("p",{className:"text-gray-500",children:s?s.totalStaffs:0})]}),e.jsx("div",{className:"shrink-0 pr-2"})]})]}),e.jsxs("li",{className:"col-span-1 flex rounded-md shadow-sm",children:[e.jsx("div",{className:"bg-yellow-500 flex w-16 shrink-0 items-center justify-center rounded-l-md text-sm font-medium text-white",style:{backgroundColor:"#e44e48"},children:D}),e.jsxs("div",{className:"flex flex-1 items-center justify-between truncate rounded-r-md border-b border-r border-t border-gray-200 bg-white",children:[e.jsxs("div",{className:"flex-1 truncate px-4 py-2 text-sm",children:[e.jsx("a",{href:"/drivers/driver-list",className:"font-medium text-gray-900 hover:text-gray-600",children:"On Leave"}),e.jsx("p",{className:"text-gray-500",children:s?s.onLeaves:0})]}),e.jsx("div",{className:"shrink-0 pr-2"})]})]})]}),e.jsxs("div",{className:"flex gap-10 py-10 items-start",children:[e.jsxs("table",{className:"table-fixed divide-y divide-gray-300 col-lg-6 col-md-12 w-[50%]",children:[e.jsx("thead",{children:e.jsx("tr",{className:"bg-gray-2 text-left dark:bg-meta-4 border-[#aaaaaa] border-b border-t",children:e.jsx("div",{className:"hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 w-full md:block md:w-auto",id:"navbar-default",children:e.jsxs("ul",{className:"font-medium flex flex-col p-4 md:p-0 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 rtl:space-x-reverse md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700",children:[e.jsx("li",{children:e.jsx("p",{className:"block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent",children:"Tasks: "})}),e.jsx("li",{children:x?e.jsx("a",{className:"block py-2 px-3 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent",style:{cursor:"pointer",color:"blue"},onClick:()=>d("staff"),children:"Staffs"}):e.jsx("a",{className:"block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent",style:{cursor:"pointer"},onClick:()=>d("staff"),children:"Staffs"})}),e.jsx("li",{children:p?e.jsx("a",{className:"block py-2 px-3 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent",style:{cursor:"pointer",color:"blue"},onClick:()=>d("leave"),children:"Leaves"}):e.jsx("a",{className:"block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent",style:{cursor:"pointer"},onClick:()=>d("leave"),children:"Leaves"})})]})})})}),e.jsxs("tbody",{className:"divide-y divide-gray-200 bg-white",children:[x&&e.jsxs(e.Fragment,{children:[u.map((a,r)=>e.jsx("tr",{className:`${r%2===1?"bg-gray-2 dark:bg-meta-4":"dark:bg-boxdark"} border-b border-[#aaaaaa]`,children:e.jsxs("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:[a.first_name," ",a.last_name," visa expiry on ",a.work_permit_expiry]})},r)),j.map((a,r)=>e.jsx("tr",{className:`${r%2===1?"bg-gray-2 dark:bg-meta-4":"dark:bg-boxdark"} border-b border-[#aaaaaa]`,children:e.jsxs("td",{className:"whitespace-nowrap px-3 py-4 text-sm",style:{color:"red"},children:[a.first_name," ",a.last_name," visa expired  on ",a.work_permit_expiry]})},r)),f.map((a,r)=>e.jsx("tr",{className:`${r%2===1?"bg-gray-2 dark:bg-meta-4":"dark:bg-boxdark"} border-b border-[#aaaaaa]`,children:e.jsxs("td",{className:"whitespace-nowrap px-3 py-4 text-sm",style:{color:l>=a.pdrp_expiry_date?"red":"#374151"},children:[a.first_name," ",a.last_name," ",l>=a.pdrp_expiry_date?"passport expired on":"passport expiry on"," ",a.pdrp_expiry_date]})},r))]}),p&&e.jsx(e.Fragment,{children:N.map((a,r)=>e.jsx("tr",{className:`${r%2===1?"bg-gray-2 dark:bg-meta-4":"dark:bg-boxdark"} border-b border-[#aaaaaa]`,children:e.jsxs("td",{className:"whitespace-nowrap px-3 py-4 text-sm",style:{color:l>=a.leave_to?"red":"#374151"},children:[a.last_name," was expected to rejoin by ",a.leave_to]})},r))})]})]}),e.jsxs("div",{className:"w-[50%] flex items-center flex-col gap-5",children:[e.jsxs("h1",{children:["Employees Status(New employees on ",F,")"]}),e.jsxs("table",{className:"min-w-full table-fixed divide-y divide-gray-300",children:[e.jsx("thead",{children:e.jsxs("tr",{className:"bg-gray-2 text-left dark:bg-meta-4 border-[#aaaaaa] border-b border-t",children:[e.jsx("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900 cursor-pointer",children:"ID"}),e.jsx("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900 cursor-pointer",children:"Name"}),e.jsx("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900 cursor-pointer",children:"Nationality"}),e.jsx("th",{scope:"col",className:"px-3 py-3.5 text-left text-sm font-semibold text-gray-900 cursor-pointer",children:"Joined date"})]})}),e.jsx("tbody",{className:"divide-y divide-gray-200 bg-white",children:S.map((a,r)=>e.jsxs("tr",{className:`${r%2===1?"bg-gray-2 dark:bg-meta-4":"dark:bg-boxdark"} border-b border-[#aaaaaa]`,children:[e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:r+1}),e.jsxs("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:[a.first_name," ",a.last_name]}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:a.citizenship}),e.jsx("td",{className:"whitespace-nowrap px-3 py-4 text-sm text-gray-500",children:a.date_employed})]},r))})]})]})]})]})}export{P as default};
