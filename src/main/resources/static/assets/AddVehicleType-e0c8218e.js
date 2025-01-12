import{u as m,r as n,j as e,a as d,B as u,_ as t}from"./index-2e85eed9.js";import{I as x,a as f,E as h}from"./index-497b2833.js";const y=()=>{sessionStorage.getItem("branch"),sessionStorage.getItem("role");const i=sessionStorage.getItem("userid");m();const[l,s]=n.useState({veh_type:"",fuel_allowance:"",km_allowance:""}),[r,o]=n.useState(!1);function c(){l.veh_type!=""&&l.fuel_allowance!=""&&l.km_allowance!=""?(o(!0),d({method:"post",url:`${u}/api/vehicleType`,data:{user:i,veh_type:l.veh_type,km_allowance:l.km_allowance,fuel_allowance:l.fuel_allowance,type:"add"}}).then(a=>{a.data[0].allowed==0?t.success(a.data[0].message):t.error(a.data[0].message),o(!1)}).catch(a=>console.log(a))):t.error("Please fill all the required fields")}return e.jsx(e.Fragment,{children:e.jsxs("div",{className:"flex flex-col gap-4 flex-1 h-full",children:[e.jsxs("div",{className:"flex flex-row justify-between",children:[e.jsx("div",{className:"flex flex-row gap-6 items-center",children:e.jsxs("div",{className:"flex flex-row text-center items-center gap-2 font-bold",children:["New Vehicle Type",e.jsx(x,{})]})}),e.jsx("div",{className:"flex flex-row items-center",children:e.jsx(f,{})})]}),e.jsxs("div",{className:"rounded-sm border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1 flex-1",children:[e.jsx("div",{className:"flex gap-2 mb-5",children:e.jsx("button",{type:"button",onClick:c,className:"flex items-center justify-center block px-5 rounded border border-primary bg-primary p-2 text-center font-medium text-white transition hover:bg-opacity-90",children:r?e.jsxs("div",{className:"flex items-center",children:[e.jsx("div",{className:"w-5 h-5 border-4 border-transparent border-t-white animate-spin rounded-full"}),e.jsx("span",{className:"ml-2",children:"Saving..."})," "]}):e.jsx("p",{children:"Save"})})}),e.jsxs("div",{className:"flex flex-row gap-5 items-center h-10 mb-5",children:[e.jsx(h,{}),e.jsx("h1",{className:"text-3xl font-semibold",children:"New Vehicle Type"})]}),e.jsx("div",{className:"flex md:flex-row gap-5 flex-col",children:e.jsxs("div",{className:"grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6 w-full",children:[e.jsxs("div",{className:"sm:col-span-2",children:[e.jsx("label",{htmlFor:"first-name",className:"block text-sm/6 font-medium text-gray-900",children:"Vehicle Type"}),e.jsx("div",{className:"mt-2",children:e.jsx("input",{name:"vehicle_type",value:l.veh_type,onChange:a=>s({...l,veh_type:a.target.value}),autoComplete:"given-name",className:"block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6"})})]}),e.jsxs("div",{className:"sm:col-span-2",children:[e.jsx("label",{htmlFor:"last-name",className:"block text-sm/6 font-medium text-gray-900",children:"Fuel Allowance / Month"}),e.jsx("div",{className:"mt-2",children:e.jsx("input",{name:"fuel_allowance",value:l.fuel_allowance,onChange:a=>s({...l,fuel_allowance:a.target.value}),type:"text",autoComplete:"family-name",className:"block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6"})})]}),e.jsxs("div",{className:"sm:col-span-2",children:[e.jsx("label",{htmlFor:"last-name",className:"block text-sm/6 font-medium text-gray-900",children:"KM Allowance / Month"}),e.jsx("div",{className:"mt-2",children:e.jsx("input",{name:"km_allowance",value:l.km_allowance,onChange:a=>s({...l,km_allowance:a.target.value}),type:"text",autoComplete:"family-name",className:"block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6"})})]})]})})]})]})})};export{y as default};
