import{g as pn,R as Oe}from"./index-2e85eed9.js";/*!
 * Font Awesome Free 6.7.2 by @fontawesome - https://fontawesome.com
 * License - https://fontawesome.com/license/free (Icons: CC BY 4.0, Fonts: SIL OFL 1.1, Code: MIT License)
 * Copyright 2024 Fonticons, Inc.
 */function gn(t,e,n){return(e=bn(e))in t?Object.defineProperty(t,e,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[e]=n,t}function Vt(t,e){var n=Object.keys(t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(t);e&&(a=a.filter(function(r){return Object.getOwnPropertyDescriptor(t,r).enumerable})),n.push.apply(n,a)}return n}function l(t){for(var e=1;e<arguments.length;e++){var n=arguments[e]!=null?arguments[e]:{};e%2?Vt(Object(n),!0).forEach(function(a){gn(t,a,n[a])}):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(n)):Vt(Object(n)).forEach(function(a){Object.defineProperty(t,a,Object.getOwnPropertyDescriptor(n,a))})}return t}function hn(t,e){if(typeof t!="object"||!t)return t;var n=t[Symbol.toPrimitive];if(n!==void 0){var a=n.call(t,e||"default");if(typeof a!="object")return a;throw new TypeError("@@toPrimitive must return a primitive value.")}return(e==="string"?String:Number)(t)}function bn(t){var e=hn(t,"string");return typeof e=="symbol"?e:e+""}const Kt=()=>{};let Rt={},Pe={},Se=null,Ee={mark:Kt,measure:Kt};try{typeof window<"u"&&(Rt=window),typeof document<"u"&&(Pe=document),typeof MutationObserver<"u"&&(Se=MutationObserver),typeof performance<"u"&&(Ee=performance)}catch{}const{userAgent:qt=""}=Rt.navigator||{},L=Rt,h=Pe,Qt=Se,Z=Ee;L.document;const _=!!h.documentElement&&!!h.head&&typeof h.addEventListener=="function"&&typeof h.createElement=="function",Ie=~qt.indexOf("MSIE")||~qt.indexOf("Trident/");var yn=/fa(s|r|l|t|d|dr|dl|dt|b|k|kd|ss|sr|sl|st|sds|sdr|sdl|sdt)?[\-\ ]/,vn=/Font ?Awesome ?([56 ]*)(Solid|Regular|Light|Thin|Duotone|Brands|Free|Pro|Sharp Duotone|Sharp|Kit)?.*/i,Ce={classic:{fa:"solid",fas:"solid","fa-solid":"solid",far:"regular","fa-regular":"regular",fal:"light","fa-light":"light",fat:"thin","fa-thin":"thin",fab:"brands","fa-brands":"brands"},duotone:{fa:"solid",fad:"solid","fa-solid":"solid","fa-duotone":"solid",fadr:"regular","fa-regular":"regular",fadl:"light","fa-light":"light",fadt:"thin","fa-thin":"thin"},sharp:{fa:"solid",fass:"solid","fa-solid":"solid",fasr:"regular","fa-regular":"regular",fasl:"light","fa-light":"light",fast:"thin","fa-thin":"thin"},"sharp-duotone":{fa:"solid",fasds:"solid","fa-solid":"solid",fasdr:"regular","fa-regular":"regular",fasdl:"light","fa-light":"light",fasdt:"thin","fa-thin":"thin"}},xn={GROUP:"duotone-group",SWAP_OPACITY:"swap-opacity",PRIMARY:"primary",SECONDARY:"secondary"},Te=["fa-classic","fa-duotone","fa-sharp","fa-sharp-duotone"],A="classic",ot="duotone",An="sharp",wn="sharp-duotone",Ne=[A,ot,An,wn],kn={classic:{900:"fas",400:"far",normal:"far",300:"fal",100:"fat"},duotone:{900:"fad",400:"fadr",300:"fadl",100:"fadt"},sharp:{900:"fass",400:"fasr",300:"fasl",100:"fast"},"sharp-duotone":{900:"fasds",400:"fasdr",300:"fasdl",100:"fasdt"}},On={"Font Awesome 6 Free":{900:"fas",400:"far"},"Font Awesome 6 Pro":{900:"fas",400:"far",normal:"far",300:"fal",100:"fat"},"Font Awesome 6 Brands":{400:"fab",normal:"fab"},"Font Awesome 6 Duotone":{900:"fad",400:"fadr",normal:"fadr",300:"fadl",100:"fadt"},"Font Awesome 6 Sharp":{900:"fass",400:"fasr",normal:"fasr",300:"fasl",100:"fast"},"Font Awesome 6 Sharp Duotone":{900:"fasds",400:"fasdr",normal:"fasdr",300:"fasdl",100:"fasdt"}},Pn=new Map([["classic",{defaultShortPrefixId:"fas",defaultStyleId:"solid",styleIds:["solid","regular","light","thin","brands"],futureStyleIds:[],defaultFontWeight:900}],["sharp",{defaultShortPrefixId:"fass",defaultStyleId:"solid",styleIds:["solid","regular","light","thin"],futureStyleIds:[],defaultFontWeight:900}],["duotone",{defaultShortPrefixId:"fad",defaultStyleId:"solid",styleIds:["solid","regular","light","thin"],futureStyleIds:[],defaultFontWeight:900}],["sharp-duotone",{defaultShortPrefixId:"fasds",defaultStyleId:"solid",styleIds:["solid","regular","light","thin"],futureStyleIds:[],defaultFontWeight:900}]]),Sn={classic:{solid:"fas",regular:"far",light:"fal",thin:"fat",brands:"fab"},duotone:{solid:"fad",regular:"fadr",light:"fadl",thin:"fadt"},sharp:{solid:"fass",regular:"fasr",light:"fasl",thin:"fast"},"sharp-duotone":{solid:"fasds",regular:"fasdr",light:"fasdl",thin:"fasdt"}},En=["fak","fa-kit","fakd","fa-kit-duotone"],Jt={kit:{fak:"kit","fa-kit":"kit"},"kit-duotone":{fakd:"kit-duotone","fa-kit-duotone":"kit-duotone"}},In=["kit"],Cn={kit:{"fa-kit":"fak"},"kit-duotone":{"fa-kit-duotone":"fakd"}},Tn=["fak","fakd"],Nn={kit:{fak:"fa-kit"},"kit-duotone":{fakd:"fa-kit-duotone"}},Zt={kit:{kit:"fak"},"kit-duotone":{"kit-duotone":"fakd"}},tt={GROUP:"duotone-group",SWAP_OPACITY:"swap-opacity",PRIMARY:"primary",SECONDARY:"secondary"},Fn=["fa-classic","fa-duotone","fa-sharp","fa-sharp-duotone"],_n=["fak","fa-kit","fakd","fa-kit-duotone"],Mn={"Font Awesome Kit":{400:"fak",normal:"fak"},"Font Awesome Kit Duotone":{400:"fakd",normal:"fakd"}},Ln={classic:{"fa-brands":"fab","fa-duotone":"fad","fa-light":"fal","fa-regular":"far","fa-solid":"fas","fa-thin":"fat"},duotone:{"fa-regular":"fadr","fa-light":"fadl","fa-thin":"fadt"},sharp:{"fa-solid":"fass","fa-regular":"fasr","fa-light":"fasl","fa-thin":"fast"},"sharp-duotone":{"fa-solid":"fasds","fa-regular":"fasdr","fa-light":"fasdl","fa-thin":"fasdt"}},Dn={classic:["fas","far","fal","fat","fad"],duotone:["fadr","fadl","fadt"],sharp:["fass","fasr","fasl","fast"],"sharp-duotone":["fasds","fasdr","fasdl","fasdt"]},yt={classic:{fab:"fa-brands",fad:"fa-duotone",fal:"fa-light",far:"fa-regular",fas:"fa-solid",fat:"fa-thin"},duotone:{fadr:"fa-regular",fadl:"fa-light",fadt:"fa-thin"},sharp:{fass:"fa-solid",fasr:"fa-regular",fasl:"fa-light",fast:"fa-thin"},"sharp-duotone":{fasds:"fa-solid",fasdr:"fa-regular",fasdl:"fa-light",fasdt:"fa-thin"}},Rn=["fa-solid","fa-regular","fa-light","fa-thin","fa-duotone","fa-brands"],vt=["fa","fas","far","fal","fat","fad","fadr","fadl","fadt","fab","fass","fasr","fasl","fast","fasds","fasdr","fasdl","fasdt",...Fn,...Rn],jn=["solid","regular","light","thin","duotone","brands"],Fe=[1,2,3,4,5,6,7,8,9,10],zn=Fe.concat([11,12,13,14,15,16,17,18,19,20]),Wn=[...Object.keys(Dn),...jn,"2xs","xs","sm","lg","xl","2xl","beat","border","fade","beat-fade","bounce","flip-both","flip-horizontal","flip-vertical","flip","fw","inverse","layers-counter","layers-text","layers","li","pull-left","pull-right","pulse","rotate-180","rotate-270","rotate-90","rotate-by","shake","spin-pulse","spin-reverse","spin","stack-1x","stack-2x","stack","ul",tt.GROUP,tt.SWAP_OPACITY,tt.PRIMARY,tt.SECONDARY].concat(Fe.map(t=>"".concat(t,"x"))).concat(zn.map(t=>"w-".concat(t))),Yn={"Font Awesome 5 Free":{900:"fas",400:"far"},"Font Awesome 5 Pro":{900:"fas",400:"far",normal:"far",300:"fal"},"Font Awesome 5 Brands":{400:"fab",normal:"fab"},"Font Awesome 5 Duotone":{900:"fad"}};const N="___FONT_AWESOME___",xt=16,_e="fa",Me="svg-inline--fa",z="data-fa-i2svg",At="data-fa-pseudo-element",Un="data-fa-pseudo-element-pending",jt="data-prefix",zt="data-icon",te="fontawesome-i2svg",Hn="async",Gn=["HTML","HEAD","STYLE","SCRIPT"],Le=(()=>{try{return!0}catch{return!1}})();function Q(t){return new Proxy(t,{get(e,n){return n in e?e[n]:e[A]}})}const De=l({},Ce);De[A]=l(l(l(l({},{"fa-duotone":"duotone"}),Ce[A]),Jt.kit),Jt["kit-duotone"]);const Xn=Q(De),wt=l({},Sn);wt[A]=l(l(l(l({},{duotone:"fad"}),wt[A]),Zt.kit),Zt["kit-duotone"]);const ee=Q(wt),kt=l({},yt);kt[A]=l(l({},kt[A]),Nn.kit);const Wt=Q(kt),Ot=l({},Ln);Ot[A]=l(l({},Ot[A]),Cn.kit);Q(Ot);const $n=yn,Re="fa-layers-text",Bn=vn,Vn=l({},kn);Q(Vn);const Kn=["class","data-prefix","data-icon","data-fa-transform","data-fa-mask"],dt=xn,qn=[...In,...Wn],B=L.FontAwesomeConfig||{};function Qn(t){var e=h.querySelector("script["+t+"]");if(e)return e.getAttribute(t)}function Jn(t){return t===""?!0:t==="false"?!1:t==="true"?!0:t}h&&typeof h.querySelector=="function"&&[["data-family-prefix","familyPrefix"],["data-css-prefix","cssPrefix"],["data-family-default","familyDefault"],["data-style-default","styleDefault"],["data-replacement-class","replacementClass"],["data-auto-replace-svg","autoReplaceSvg"],["data-auto-add-css","autoAddCss"],["data-auto-a11y","autoA11y"],["data-search-pseudo-elements","searchPseudoElements"],["data-observe-mutations","observeMutations"],["data-mutate-approach","mutateApproach"],["data-keep-original-source","keepOriginalSource"],["data-measure-performance","measurePerformance"],["data-show-missing-icons","showMissingIcons"]].forEach(e=>{let[n,a]=e;const r=Jn(Qn(n));r!=null&&(B[a]=r)});const je={styleDefault:"solid",familyDefault:A,cssPrefix:_e,replacementClass:Me,autoReplaceSvg:!0,autoAddCss:!0,autoA11y:!0,searchPseudoElements:!1,observeMutations:!0,mutateApproach:"async",keepOriginalSource:!0,measurePerformance:!1,showMissingIcons:!0};B.familyPrefix&&(B.cssPrefix=B.familyPrefix);const G=l(l({},je),B);G.autoReplaceSvg||(G.observeMutations=!1);const u={};Object.keys(je).forEach(t=>{Object.defineProperty(u,t,{enumerable:!0,set:function(e){G[t]=e,V.forEach(n=>n(u))},get:function(){return G[t]}})});Object.defineProperty(u,"familyPrefix",{enumerable:!0,set:function(t){G.cssPrefix=t,V.forEach(e=>e(u))},get:function(){return G.cssPrefix}});L.FontAwesomeConfig=u;const V=[];function Zn(t){return V.push(t),()=>{V.splice(V.indexOf(t),1)}}const M=xt,I={size:16,x:0,y:0,rotate:0,flipX:!1,flipY:!1};function ta(t){if(!t||!_)return;const e=h.createElement("style");e.setAttribute("type","text/css"),e.innerHTML=t;const n=h.head.childNodes;let a=null;for(let r=n.length-1;r>-1;r--){const i=n[r],o=(i.tagName||"").toUpperCase();["STYLE","LINK"].indexOf(o)>-1&&(a=i)}return h.head.insertBefore(e,a),t}const ea="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";function K(){let t=12,e="";for(;t-- >0;)e+=ea[Math.random()*62|0];return e}function X(t){const e=[];for(let n=(t||[]).length>>>0;n--;)e[n]=t[n];return e}function Yt(t){return t.classList?X(t.classList):(t.getAttribute("class")||"").split(" ").filter(e=>e)}function ze(t){return"".concat(t).replace(/&/g,"&amp;").replace(/"/g,"&quot;").replace(/'/g,"&#39;").replace(/</g,"&lt;").replace(/>/g,"&gt;")}function na(t){return Object.keys(t||{}).reduce((e,n)=>e+"".concat(n,'="').concat(ze(t[n]),'" '),"").trim()}function st(t){return Object.keys(t||{}).reduce((e,n)=>e+"".concat(n,": ").concat(t[n].trim(),";"),"")}function Ut(t){return t.size!==I.size||t.x!==I.x||t.y!==I.y||t.rotate!==I.rotate||t.flipX||t.flipY}function aa(t){let{transform:e,containerWidth:n,iconWidth:a}=t;const r={transform:"translate(".concat(n/2," 256)")},i="translate(".concat(e.x*32,", ").concat(e.y*32,") "),o="scale(".concat(e.size/16*(e.flipX?-1:1),", ").concat(e.size/16*(e.flipY?-1:1),") "),s="rotate(".concat(e.rotate," 0 0)"),c={transform:"".concat(i," ").concat(o," ").concat(s)},f={transform:"translate(".concat(a/2*-1," -256)")};return{outer:r,inner:c,path:f}}function ra(t){let{transform:e,width:n=xt,height:a=xt,startCentered:r=!1}=t,i="";return r&&Ie?i+="translate(".concat(e.x/M-n/2,"em, ").concat(e.y/M-a/2,"em) "):r?i+="translate(calc(-50% + ".concat(e.x/M,"em), calc(-50% + ").concat(e.y/M,"em)) "):i+="translate(".concat(e.x/M,"em, ").concat(e.y/M,"em) "),i+="scale(".concat(e.size/M*(e.flipX?-1:1),", ").concat(e.size/M*(e.flipY?-1:1),") "),i+="rotate(".concat(e.rotate,"deg) "),i}var ia=`:root, :host {
  --fa-font-solid: normal 900 1em/1 "Font Awesome 6 Free";
  --fa-font-regular: normal 400 1em/1 "Font Awesome 6 Free";
  --fa-font-light: normal 300 1em/1 "Font Awesome 6 Pro";
  --fa-font-thin: normal 100 1em/1 "Font Awesome 6 Pro";
  --fa-font-duotone: normal 900 1em/1 "Font Awesome 6 Duotone";
  --fa-font-duotone-regular: normal 400 1em/1 "Font Awesome 6 Duotone";
  --fa-font-duotone-light: normal 300 1em/1 "Font Awesome 6 Duotone";
  --fa-font-duotone-thin: normal 100 1em/1 "Font Awesome 6 Duotone";
  --fa-font-brands: normal 400 1em/1 "Font Awesome 6 Brands";
  --fa-font-sharp-solid: normal 900 1em/1 "Font Awesome 6 Sharp";
  --fa-font-sharp-regular: normal 400 1em/1 "Font Awesome 6 Sharp";
  --fa-font-sharp-light: normal 300 1em/1 "Font Awesome 6 Sharp";
  --fa-font-sharp-thin: normal 100 1em/1 "Font Awesome 6 Sharp";
  --fa-font-sharp-duotone-solid: normal 900 1em/1 "Font Awesome 6 Sharp Duotone";
  --fa-font-sharp-duotone-regular: normal 400 1em/1 "Font Awesome 6 Sharp Duotone";
  --fa-font-sharp-duotone-light: normal 300 1em/1 "Font Awesome 6 Sharp Duotone";
  --fa-font-sharp-duotone-thin: normal 100 1em/1 "Font Awesome 6 Sharp Duotone";
}

svg:not(:root).svg-inline--fa, svg:not(:host).svg-inline--fa {
  overflow: visible;
  box-sizing: content-box;
}

.svg-inline--fa {
  display: var(--fa-display, inline-block);
  height: 1em;
  overflow: visible;
  vertical-align: -0.125em;
}
.svg-inline--fa.fa-2xs {
  vertical-align: 0.1em;
}
.svg-inline--fa.fa-xs {
  vertical-align: 0em;
}
.svg-inline--fa.fa-sm {
  vertical-align: -0.0714285705em;
}
.svg-inline--fa.fa-lg {
  vertical-align: -0.2em;
}
.svg-inline--fa.fa-xl {
  vertical-align: -0.25em;
}
.svg-inline--fa.fa-2xl {
  vertical-align: -0.3125em;
}
.svg-inline--fa.fa-pull-left {
  margin-right: var(--fa-pull-margin, 0.3em);
  width: auto;
}
.svg-inline--fa.fa-pull-right {
  margin-left: var(--fa-pull-margin, 0.3em);
  width: auto;
}
.svg-inline--fa.fa-li {
  width: var(--fa-li-width, 2em);
  top: 0.25em;
}
.svg-inline--fa.fa-fw {
  width: var(--fa-fw-width, 1.25em);
}

.fa-layers svg.svg-inline--fa {
  bottom: 0;
  left: 0;
  margin: auto;
  position: absolute;
  right: 0;
  top: 0;
}

.fa-layers-counter, .fa-layers-text {
  display: inline-block;
  position: absolute;
  text-align: center;
}

.fa-layers {
  display: inline-block;
  height: 1em;
  position: relative;
  text-align: center;
  vertical-align: -0.125em;
  width: 1em;
}
.fa-layers svg.svg-inline--fa {
  transform-origin: center center;
}

.fa-layers-text {
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  transform-origin: center center;
}

.fa-layers-counter {
  background-color: var(--fa-counter-background-color, #ff253a);
  border-radius: var(--fa-counter-border-radius, 1em);
  box-sizing: border-box;
  color: var(--fa-inverse, #fff);
  line-height: var(--fa-counter-line-height, 1);
  max-width: var(--fa-counter-max-width, 5em);
  min-width: var(--fa-counter-min-width, 1.5em);
  overflow: hidden;
  padding: var(--fa-counter-padding, 0.25em 0.5em);
  right: var(--fa-right, 0);
  text-overflow: ellipsis;
  top: var(--fa-top, 0);
  transform: scale(var(--fa-counter-scale, 0.25));
  transform-origin: top right;
}

.fa-layers-bottom-right {
  bottom: var(--fa-bottom, 0);
  right: var(--fa-right, 0);
  top: auto;
  transform: scale(var(--fa-layers-scale, 0.25));
  transform-origin: bottom right;
}

.fa-layers-bottom-left {
  bottom: var(--fa-bottom, 0);
  left: var(--fa-left, 0);
  right: auto;
  top: auto;
  transform: scale(var(--fa-layers-scale, 0.25));
  transform-origin: bottom left;
}

.fa-layers-top-right {
  top: var(--fa-top, 0);
  right: var(--fa-right, 0);
  transform: scale(var(--fa-layers-scale, 0.25));
  transform-origin: top right;
}

.fa-layers-top-left {
  left: var(--fa-left, 0);
  right: auto;
  top: var(--fa-top, 0);
  transform: scale(var(--fa-layers-scale, 0.25));
  transform-origin: top left;
}

.fa-1x {
  font-size: 1em;
}

.fa-2x {
  font-size: 2em;
}

.fa-3x {
  font-size: 3em;
}

.fa-4x {
  font-size: 4em;
}

.fa-5x {
  font-size: 5em;
}

.fa-6x {
  font-size: 6em;
}

.fa-7x {
  font-size: 7em;
}

.fa-8x {
  font-size: 8em;
}

.fa-9x {
  font-size: 9em;
}

.fa-10x {
  font-size: 10em;
}

.fa-2xs {
  font-size: 0.625em;
  line-height: 0.1em;
  vertical-align: 0.225em;
}

.fa-xs {
  font-size: 0.75em;
  line-height: 0.0833333337em;
  vertical-align: 0.125em;
}

.fa-sm {
  font-size: 0.875em;
  line-height: 0.0714285718em;
  vertical-align: 0.0535714295em;
}

.fa-lg {
  font-size: 1.25em;
  line-height: 0.05em;
  vertical-align: -0.075em;
}

.fa-xl {
  font-size: 1.5em;
  line-height: 0.0416666682em;
  vertical-align: -0.125em;
}

.fa-2xl {
  font-size: 2em;
  line-height: 0.03125em;
  vertical-align: -0.1875em;
}

.fa-fw {
  text-align: center;
  width: 1.25em;
}

.fa-ul {
  list-style-type: none;
  margin-left: var(--fa-li-margin, 2.5em);
  padding-left: 0;
}
.fa-ul > li {
  position: relative;
}

.fa-li {
  left: calc(-1 * var(--fa-li-width, 2em));
  position: absolute;
  text-align: center;
  width: var(--fa-li-width, 2em);
  line-height: inherit;
}

.fa-border {
  border-color: var(--fa-border-color, #eee);
  border-radius: var(--fa-border-radius, 0.1em);
  border-style: var(--fa-border-style, solid);
  border-width: var(--fa-border-width, 0.08em);
  padding: var(--fa-border-padding, 0.2em 0.25em 0.15em);
}

.fa-pull-left {
  float: left;
  margin-right: var(--fa-pull-margin, 0.3em);
}

.fa-pull-right {
  float: right;
  margin-left: var(--fa-pull-margin, 0.3em);
}

.fa-beat {
  animation-name: fa-beat;
  animation-delay: var(--fa-animation-delay, 0s);
  animation-direction: var(--fa-animation-direction, normal);
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, ease-in-out);
}

.fa-bounce {
  animation-name: fa-bounce;
  animation-delay: var(--fa-animation-delay, 0s);
  animation-direction: var(--fa-animation-direction, normal);
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, cubic-bezier(0.28, 0.84, 0.42, 1));
}

.fa-fade {
  animation-name: fa-fade;
  animation-delay: var(--fa-animation-delay, 0s);
  animation-direction: var(--fa-animation-direction, normal);
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, cubic-bezier(0.4, 0, 0.6, 1));
}

.fa-beat-fade {
  animation-name: fa-beat-fade;
  animation-delay: var(--fa-animation-delay, 0s);
  animation-direction: var(--fa-animation-direction, normal);
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, cubic-bezier(0.4, 0, 0.6, 1));
}

.fa-flip {
  animation-name: fa-flip;
  animation-delay: var(--fa-animation-delay, 0s);
  animation-direction: var(--fa-animation-direction, normal);
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, ease-in-out);
}

.fa-shake {
  animation-name: fa-shake;
  animation-delay: var(--fa-animation-delay, 0s);
  animation-direction: var(--fa-animation-direction, normal);
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}

.fa-spin {
  animation-name: fa-spin;
  animation-delay: var(--fa-animation-delay, 0s);
  animation-direction: var(--fa-animation-direction, normal);
  animation-duration: var(--fa-animation-duration, 2s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}

.fa-spin-reverse {
  --fa-animation-direction: reverse;
}

.fa-pulse,
.fa-spin-pulse {
  animation-name: fa-spin;
  animation-direction: var(--fa-animation-direction, normal);
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, steps(8));
}

@media (prefers-reduced-motion: reduce) {
  .fa-beat,
.fa-bounce,
.fa-fade,
.fa-beat-fade,
.fa-flip,
.fa-pulse,
.fa-shake,
.fa-spin,
.fa-spin-pulse {
    animation-delay: -1ms;
    animation-duration: 1ms;
    animation-iteration-count: 1;
    transition-delay: 0s;
    transition-duration: 0s;
  }
}
@keyframes fa-beat {
  0%, 90% {
    transform: scale(1);
  }
  45% {
    transform: scale(var(--fa-beat-scale, 1.25));
  }
}
@keyframes fa-bounce {
  0% {
    transform: scale(1, 1) translateY(0);
  }
  10% {
    transform: scale(var(--fa-bounce-start-scale-x, 1.1), var(--fa-bounce-start-scale-y, 0.9)) translateY(0);
  }
  30% {
    transform: scale(var(--fa-bounce-jump-scale-x, 0.9), var(--fa-bounce-jump-scale-y, 1.1)) translateY(var(--fa-bounce-height, -0.5em));
  }
  50% {
    transform: scale(var(--fa-bounce-land-scale-x, 1.05), var(--fa-bounce-land-scale-y, 0.95)) translateY(0);
  }
  57% {
    transform: scale(1, 1) translateY(var(--fa-bounce-rebound, -0.125em));
  }
  64% {
    transform: scale(1, 1) translateY(0);
  }
  100% {
    transform: scale(1, 1) translateY(0);
  }
}
@keyframes fa-fade {
  50% {
    opacity: var(--fa-fade-opacity, 0.4);
  }
}
@keyframes fa-beat-fade {
  0%, 100% {
    opacity: var(--fa-beat-fade-opacity, 0.4);
    transform: scale(1);
  }
  50% {
    opacity: 1;
    transform: scale(var(--fa-beat-fade-scale, 1.125));
  }
}
@keyframes fa-flip {
  50% {
    transform: rotate3d(var(--fa-flip-x, 0), var(--fa-flip-y, 1), var(--fa-flip-z, 0), var(--fa-flip-angle, -180deg));
  }
}
@keyframes fa-shake {
  0% {
    transform: rotate(-15deg);
  }
  4% {
    transform: rotate(15deg);
  }
  8%, 24% {
    transform: rotate(-18deg);
  }
  12%, 28% {
    transform: rotate(18deg);
  }
  16% {
    transform: rotate(-22deg);
  }
  20% {
    transform: rotate(22deg);
  }
  32% {
    transform: rotate(-12deg);
  }
  36% {
    transform: rotate(12deg);
  }
  40%, 100% {
    transform: rotate(0deg);
  }
}
@keyframes fa-spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
.fa-rotate-90 {
  transform: rotate(90deg);
}

.fa-rotate-180 {
  transform: rotate(180deg);
}

.fa-rotate-270 {
  transform: rotate(270deg);
}

.fa-flip-horizontal {
  transform: scale(-1, 1);
}

.fa-flip-vertical {
  transform: scale(1, -1);
}

.fa-flip-both,
.fa-flip-horizontal.fa-flip-vertical {
  transform: scale(-1, -1);
}

.fa-rotate-by {
  transform: rotate(var(--fa-rotate-angle, 0));
}

.fa-stack {
  display: inline-block;
  vertical-align: middle;
  height: 2em;
  position: relative;
  width: 2.5em;
}

.fa-stack-1x,
.fa-stack-2x {
  bottom: 0;
  left: 0;
  margin: auto;
  position: absolute;
  right: 0;
  top: 0;
  z-index: var(--fa-stack-z-index, auto);
}

.svg-inline--fa.fa-stack-1x {
  height: 1em;
  width: 1.25em;
}
.svg-inline--fa.fa-stack-2x {
  height: 2em;
  width: 2.5em;
}

.fa-inverse {
  color: var(--fa-inverse, #fff);
}

.sr-only,
.fa-sr-only {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  white-space: nowrap;
  border-width: 0;
}

.sr-only-focusable:not(:focus),
.fa-sr-only-focusable:not(:focus) {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  white-space: nowrap;
  border-width: 0;
}

.svg-inline--fa .fa-primary {
  fill: var(--fa-primary-color, currentColor);
  opacity: var(--fa-primary-opacity, 1);
}

.svg-inline--fa .fa-secondary {
  fill: var(--fa-secondary-color, currentColor);
  opacity: var(--fa-secondary-opacity, 0.4);
}

.svg-inline--fa.fa-swap-opacity .fa-primary {
  opacity: var(--fa-secondary-opacity, 0.4);
}

.svg-inline--fa.fa-swap-opacity .fa-secondary {
  opacity: var(--fa-primary-opacity, 1);
}

.svg-inline--fa mask .fa-primary,
.svg-inline--fa mask .fa-secondary {
  fill: black;
}`;function We(){const t=_e,e=Me,n=u.cssPrefix,a=u.replacementClass;let r=ia;if(n!==t||a!==e){const i=new RegExp("\\.".concat(t,"\\-"),"g"),o=new RegExp("\\--".concat(t,"\\-"),"g"),s=new RegExp("\\.".concat(e),"g");r=r.replace(i,".".concat(n,"-")).replace(o,"--".concat(n,"-")).replace(s,".".concat(a))}return r}let ne=!1;function mt(){u.autoAddCss&&!ne&&(ta(We()),ne=!0)}var oa={mixout(){return{dom:{css:We,insertCss:mt}}},hooks(){return{beforeDOMElementCreation(){mt()},beforeI2svg(){mt()}}}};const F=L||{};F[N]||(F[N]={});F[N].styles||(F[N].styles={});F[N].hooks||(F[N].hooks={});F[N].shims||(F[N].shims=[]);var C=F[N];const Ye=[],Ue=function(){h.removeEventListener("DOMContentLoaded",Ue),at=1,Ye.map(t=>t())};let at=!1;_&&(at=(h.documentElement.doScroll?/^loaded|^c/:/^loaded|^i|^c/).test(h.readyState),at||h.addEventListener("DOMContentLoaded",Ue));function sa(t){_&&(at?setTimeout(t,0):Ye.push(t))}function J(t){const{tag:e,attributes:n={},children:a=[]}=t;return typeof t=="string"?ze(t):"<".concat(e," ").concat(na(n),">").concat(a.map(J).join(""),"</").concat(e,">")}function ae(t,e,n){if(t&&t[e]&&t[e][n])return{prefix:e,iconName:n,icon:t[e][n]}}var la=function(e,n){return function(a,r,i,o){return e.call(n,a,r,i,o)}},pt=function(e,n,a,r){var i=Object.keys(e),o=i.length,s=r!==void 0?la(n,r):n,c,f,d;for(a===void 0?(c=1,d=e[i[0]]):(c=0,d=a);c<o;c++)f=i[c],d=s(d,e[f],f,e);return d};function fa(t){const e=[];let n=0;const a=t.length;for(;n<a;){const r=t.charCodeAt(n++);if(r>=55296&&r<=56319&&n<a){const i=t.charCodeAt(n++);(i&64512)==56320?e.push(((r&1023)<<10)+(i&1023)+65536):(e.push(r),n--)}else e.push(r)}return e}function Pt(t){const e=fa(t);return e.length===1?e[0].toString(16):null}function ca(t,e){const n=t.length;let a=t.charCodeAt(e),r;return a>=55296&&a<=56319&&n>e+1&&(r=t.charCodeAt(e+1),r>=56320&&r<=57343)?(a-55296)*1024+r-56320+65536:a}function re(t){return Object.keys(t).reduce((e,n)=>{const a=t[n];return!!a.icon?e[a.iconName]=a.icon:e[n]=a,e},{})}function St(t,e){let n=arguments.length>2&&arguments[2]!==void 0?arguments[2]:{};const{skipHooks:a=!1}=n,r=re(e);typeof C.hooks.addPack=="function"&&!a?C.hooks.addPack(t,re(e)):C.styles[t]=l(l({},C.styles[t]||{}),r),t==="fas"&&St("fa",e)}const{styles:q,shims:ua}=C,He=Object.keys(Wt),da=He.reduce((t,e)=>(t[e]=Object.keys(Wt[e]),t),{});let Ht=null,Ge={},Xe={},$e={},Be={},Ve={};function ma(t){return~qn.indexOf(t)}function pa(t,e){const n=e.split("-"),a=n[0],r=n.slice(1).join("-");return a===t&&r!==""&&!ma(r)?r:null}const Ke=()=>{const t=a=>pt(q,(r,i,o)=>(r[o]=pt(i,a,{}),r),{});Ge=t((a,r,i)=>(r[3]&&(a[r[3]]=i),r[2]&&r[2].filter(s=>typeof s=="number").forEach(s=>{a[s.toString(16)]=i}),a)),Xe=t((a,r,i)=>(a[i]=i,r[2]&&r[2].filter(s=>typeof s=="string").forEach(s=>{a[s]=i}),a)),Ve=t((a,r,i)=>{const o=r[2];return a[i]=i,o.forEach(s=>{a[s]=i}),a});const e="far"in q||u.autoFetchSvg,n=pt(ua,(a,r)=>{const i=r[0];let o=r[1];const s=r[2];return o==="far"&&!e&&(o="fas"),typeof i=="string"&&(a.names[i]={prefix:o,iconName:s}),typeof i=="number"&&(a.unicodes[i.toString(16)]={prefix:o,iconName:s}),a},{names:{},unicodes:{}});$e=n.names,Be=n.unicodes,Ht=lt(u.styleDefault,{family:u.familyDefault})};Zn(t=>{Ht=lt(t.styleDefault,{family:u.familyDefault})});Ke();function Gt(t,e){return(Ge[t]||{})[e]}function ga(t,e){return(Xe[t]||{})[e]}function j(t,e){return(Ve[t]||{})[e]}function qe(t){return $e[t]||{prefix:null,iconName:null}}function ha(t){const e=Be[t],n=Gt("fas",t);return e||(n?{prefix:"fas",iconName:n}:null)||{prefix:null,iconName:null}}function D(){return Ht}const Qe=()=>({prefix:null,iconName:null,rest:[]});function ba(t){let e=A;const n=He.reduce((a,r)=>(a[r]="".concat(u.cssPrefix,"-").concat(r),a),{});return Ne.forEach(a=>{(t.includes(n[a])||t.some(r=>da[a].includes(r)))&&(e=a)}),e}function lt(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:{};const{family:n=A}=e,a=Xn[n][t];if(n===ot&&!t)return"fad";const r=ee[n][t]||ee[n][a],i=t in C.styles?t:null;return r||i||null}function ya(t){let e=[],n=null;return t.forEach(a=>{const r=pa(u.cssPrefix,a);r?n=r:a&&e.push(a)}),{iconName:n,rest:e}}function ie(t){return t.sort().filter((e,n,a)=>a.indexOf(e)===n)}function ft(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:{};const{skipLookups:n=!1}=e;let a=null;const r=vt.concat(_n),i=ie(t.filter(g=>r.includes(g))),o=ie(t.filter(g=>!vt.includes(g))),s=i.filter(g=>(a=g,!Te.includes(g))),[c=null]=s,f=ba(i),d=l(l({},ya(o)),{},{prefix:lt(c,{family:f})});return l(l(l({},d),wa({values:t,family:f,styles:q,config:u,canonical:d,givenPrefix:a})),va(n,a,d))}function va(t,e,n){let{prefix:a,iconName:r}=n;if(t||!a||!r)return{prefix:a,iconName:r};const i=e==="fa"?qe(r):{},o=j(a,r);return r=i.iconName||o||r,a=i.prefix||a,a==="far"&&!q.far&&q.fas&&!u.autoFetchSvg&&(a="fas"),{prefix:a,iconName:r}}const xa=Ne.filter(t=>t!==A||t!==ot),Aa=Object.keys(yt).filter(t=>t!==A).map(t=>Object.keys(yt[t])).flat();function wa(t){const{values:e,family:n,canonical:a,givenPrefix:r="",styles:i={},config:o={}}=t,s=n===ot,c=e.includes("fa-duotone")||e.includes("fad"),f=o.familyDefault==="duotone",d=a.prefix==="fad"||a.prefix==="fa-duotone";if(!s&&(c||f||d)&&(a.prefix="fad"),(e.includes("fa-brands")||e.includes("fab"))&&(a.prefix="fab"),!a.prefix&&xa.includes(n)&&(Object.keys(i).find(p=>Aa.includes(p))||o.autoFetchSvg)){const p=Pn.get(n).defaultShortPrefixId;a.prefix=p,a.iconName=j(a.prefix,a.iconName)||a.iconName}return(a.prefix==="fa"||r==="fa")&&(a.prefix=D()||"fas"),a}class ka{constructor(){this.definitions={}}add(){for(var e=arguments.length,n=new Array(e),a=0;a<e;a++)n[a]=arguments[a];const r=n.reduce(this._pullDefinitions,{});Object.keys(r).forEach(i=>{this.definitions[i]=l(l({},this.definitions[i]||{}),r[i]),St(i,r[i]);const o=Wt[A][i];o&&St(o,r[i]),Ke()})}reset(){this.definitions={}}_pullDefinitions(e,n){const a=n.prefix&&n.iconName&&n.icon?{0:n}:n;return Object.keys(a).map(r=>{const{prefix:i,iconName:o,icon:s}=a[r],c=s[2];e[i]||(e[i]={}),c.length>0&&c.forEach(f=>{typeof f=="string"&&(e[i][f]=s)}),e[i][o]=s}),e}}let oe=[],Y={};const H={},Oa=Object.keys(H);function Pa(t,e){let{mixoutsTo:n}=e;return oe=t,Y={},Object.keys(H).forEach(a=>{Oa.indexOf(a)===-1&&delete H[a]}),oe.forEach(a=>{const r=a.mixout?a.mixout():{};if(Object.keys(r).forEach(i=>{typeof r[i]=="function"&&(n[i]=r[i]),typeof r[i]=="object"&&Object.keys(r[i]).forEach(o=>{n[i]||(n[i]={}),n[i][o]=r[i][o]})}),a.hooks){const i=a.hooks();Object.keys(i).forEach(o=>{Y[o]||(Y[o]=[]),Y[o].push(i[o])})}a.provides&&a.provides(H)}),n}function Et(t,e){for(var n=arguments.length,a=new Array(n>2?n-2:0),r=2;r<n;r++)a[r-2]=arguments[r];return(Y[t]||[]).forEach(o=>{e=o.apply(null,[e,...a])}),e}function W(t){for(var e=arguments.length,n=new Array(e>1?e-1:0),a=1;a<e;a++)n[a-1]=arguments[a];(Y[t]||[]).forEach(i=>{i.apply(null,n)})}function R(){const t=arguments[0],e=Array.prototype.slice.call(arguments,1);return H[t]?H[t].apply(null,e):void 0}function It(t){t.prefix==="fa"&&(t.prefix="fas");let{iconName:e}=t;const n=t.prefix||D();if(e)return e=j(n,e)||e,ae(Je.definitions,n,e)||ae(C.styles,n,e)}const Je=new ka,Sa=()=>{u.autoReplaceSvg=!1,u.observeMutations=!1,W("noAuto")},Ea={i2svg:function(){let t=arguments.length>0&&arguments[0]!==void 0?arguments[0]:{};return _?(W("beforeI2svg",t),R("pseudoElements2svg",t),R("i2svg",t)):Promise.reject(new Error("Operation requires a DOM of some kind."))},watch:function(){let t=arguments.length>0&&arguments[0]!==void 0?arguments[0]:{};const{autoReplaceSvgRoot:e}=t;u.autoReplaceSvg===!1&&(u.autoReplaceSvg=!0),u.observeMutations=!0,sa(()=>{Ca({autoReplaceSvgRoot:e}),W("watch",t)})}},Ia={icon:t=>{if(t===null)return null;if(typeof t=="object"&&t.prefix&&t.iconName)return{prefix:t.prefix,iconName:j(t.prefix,t.iconName)||t.iconName};if(Array.isArray(t)&&t.length===2){const e=t[1].indexOf("fa-")===0?t[1].slice(3):t[1],n=lt(t[0]);return{prefix:n,iconName:j(n,e)||e}}if(typeof t=="string"&&(t.indexOf("".concat(u.cssPrefix,"-"))>-1||t.match($n))){const e=ft(t.split(" "),{skipLookups:!0});return{prefix:e.prefix||D(),iconName:j(e.prefix,e.iconName)||e.iconName}}if(typeof t=="string"){const e=D();return{prefix:e,iconName:j(e,t)||t}}}},O={noAuto:Sa,config:u,dom:Ea,parse:Ia,library:Je,findIconDefinition:It,toHtml:J},Ca=function(){let t=arguments.length>0&&arguments[0]!==void 0?arguments[0]:{};const{autoReplaceSvgRoot:e=h}=t;(Object.keys(C.styles).length>0||u.autoFetchSvg)&&_&&u.autoReplaceSvg&&O.dom.i2svg({node:e})};function ct(t,e){return Object.defineProperty(t,"abstract",{get:e}),Object.defineProperty(t,"html",{get:function(){return t.abstract.map(n=>J(n))}}),Object.defineProperty(t,"node",{get:function(){if(!_)return;const n=h.createElement("div");return n.innerHTML=t.html,n.children}}),t}function Ta(t){let{children:e,main:n,mask:a,attributes:r,styles:i,transform:o}=t;if(Ut(o)&&n.found&&!a.found){const{width:s,height:c}=n,f={x:s/c/2,y:.5};r.style=st(l(l({},i),{},{"transform-origin":"".concat(f.x+o.x/16,"em ").concat(f.y+o.y/16,"em")}))}return[{tag:"svg",attributes:r,children:e}]}function Na(t){let{prefix:e,iconName:n,children:a,attributes:r,symbol:i}=t;const o=i===!0?"".concat(e,"-").concat(u.cssPrefix,"-").concat(n):i;return[{tag:"svg",attributes:{style:"display: none;"},children:[{tag:"symbol",attributes:l(l({},r),{},{id:o}),children:a}]}]}function Xt(t){const{icons:{main:e,mask:n},prefix:a,iconName:r,transform:i,symbol:o,title:s,maskId:c,titleId:f,extra:d,watchable:g=!1}=t,{width:p,height:y}=n.found?n:e,k=Tn.includes(a),P=[u.replacementClass,r?"".concat(u.cssPrefix,"-").concat(r):""].filter(S=>d.classes.indexOf(S)===-1).filter(S=>S!==""||!!S).concat(d.classes).join(" ");let b={children:[],attributes:l(l({},d.attributes),{},{"data-prefix":a,"data-icon":r,class:P,role:d.attributes.role||"img",xmlns:"http://www.w3.org/2000/svg",viewBox:"0 0 ".concat(p," ").concat(y)})};const x=k&&!~d.classes.indexOf("fa-fw")?{width:"".concat(p/y*16*.0625,"em")}:{};g&&(b.attributes[z]=""),s&&(b.children.push({tag:"title",attributes:{id:b.attributes["aria-labelledby"]||"title-".concat(f||K())},children:[s]}),delete b.attributes.title);const v=l(l({},b),{},{prefix:a,iconName:r,main:e,mask:n,maskId:c,transform:i,symbol:o,styles:l(l({},x),d.styles)}),{children:w,attributes:T}=n.found&&e.found?R("generateAbstractMask",v)||{children:[],attributes:{}}:R("generateAbstractIcon",v)||{children:[],attributes:{}};return v.children=w,v.attributes=T,o?Na(v):Ta(v)}function se(t){const{content:e,width:n,height:a,transform:r,title:i,extra:o,watchable:s=!1}=t,c=l(l(l({},o.attributes),i?{title:i}:{}),{},{class:o.classes.join(" ")});s&&(c[z]="");const f=l({},o.styles);Ut(r)&&(f.transform=ra({transform:r,startCentered:!0,width:n,height:a}),f["-webkit-transform"]=f.transform);const d=st(f);d.length>0&&(c.style=d);const g=[];return g.push({tag:"span",attributes:c,children:[e]}),i&&g.push({tag:"span",attributes:{class:"sr-only"},children:[i]}),g}function Fa(t){const{content:e,title:n,extra:a}=t,r=l(l(l({},a.attributes),n?{title:n}:{}),{},{class:a.classes.join(" ")}),i=st(a.styles);i.length>0&&(r.style=i);const o=[];return o.push({tag:"span",attributes:r,children:[e]}),n&&o.push({tag:"span",attributes:{class:"sr-only"},children:[n]}),o}const{styles:gt}=C;function Ct(t){const e=t[0],n=t[1],[a]=t.slice(4);let r=null;return Array.isArray(a)?r={tag:"g",attributes:{class:"".concat(u.cssPrefix,"-").concat(dt.GROUP)},children:[{tag:"path",attributes:{class:"".concat(u.cssPrefix,"-").concat(dt.SECONDARY),fill:"currentColor",d:a[0]}},{tag:"path",attributes:{class:"".concat(u.cssPrefix,"-").concat(dt.PRIMARY),fill:"currentColor",d:a[1]}}]}:r={tag:"path",attributes:{fill:"currentColor",d:a}},{found:!0,width:e,height:n,icon:r}}const _a={found:!1,width:512,height:512};function Ma(t,e){!Le&&!u.showMissingIcons&&t&&console.error('Icon with name "'.concat(t,'" and prefix "').concat(e,'" is missing.'))}function Tt(t,e){let n=e;return e==="fa"&&u.styleDefault!==null&&(e=D()),new Promise((a,r)=>{if(n==="fa"){const i=qe(t)||{};t=i.iconName||t,e=i.prefix||e}if(t&&e&&gt[e]&&gt[e][t]){const i=gt[e][t];return a(Ct(i))}Ma(t,e),a(l(l({},_a),{},{icon:u.showMissingIcons&&t?R("missingIconAbstract")||{}:{}}))})}const le=()=>{},Nt=u.measurePerformance&&Z&&Z.mark&&Z.measure?Z:{mark:le,measure:le},$='FA "6.7.2"',La=t=>(Nt.mark("".concat($," ").concat(t," begins")),()=>Ze(t)),Ze=t=>{Nt.mark("".concat($," ").concat(t," ends")),Nt.measure("".concat($," ").concat(t),"".concat($," ").concat(t," begins"),"".concat($," ").concat(t," ends"))};var $t={begin:La,end:Ze};const et=()=>{};function fe(t){return typeof(t.getAttribute?t.getAttribute(z):null)=="string"}function Da(t){const e=t.getAttribute?t.getAttribute(jt):null,n=t.getAttribute?t.getAttribute(zt):null;return e&&n}function Ra(t){return t&&t.classList&&t.classList.contains&&t.classList.contains(u.replacementClass)}function ja(){return u.autoReplaceSvg===!0?nt.replace:nt[u.autoReplaceSvg]||nt.replace}function za(t){return h.createElementNS("http://www.w3.org/2000/svg",t)}function Wa(t){return h.createElement(t)}function tn(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:{};const{ceFn:n=t.tag==="svg"?za:Wa}=e;if(typeof t=="string")return h.createTextNode(t);const a=n(t.tag);return Object.keys(t.attributes||[]).forEach(function(i){a.setAttribute(i,t.attributes[i])}),(t.children||[]).forEach(function(i){a.appendChild(tn(i,{ceFn:n}))}),a}function Ya(t){let e=" ".concat(t.outerHTML," ");return e="".concat(e,"Font Awesome fontawesome.com "),e}const nt={replace:function(t){const e=t[0];if(e.parentNode)if(t[1].forEach(n=>{e.parentNode.insertBefore(tn(n),e)}),e.getAttribute(z)===null&&u.keepOriginalSource){let n=h.createComment(Ya(e));e.parentNode.replaceChild(n,e)}else e.remove()},nest:function(t){const e=t[0],n=t[1];if(~Yt(e).indexOf(u.replacementClass))return nt.replace(t);const a=new RegExp("".concat(u.cssPrefix,"-.*"));if(delete n[0].attributes.id,n[0].attributes.class){const i=n[0].attributes.class.split(" ").reduce((o,s)=>(s===u.replacementClass||s.match(a)?o.toSvg.push(s):o.toNode.push(s),o),{toNode:[],toSvg:[]});n[0].attributes.class=i.toSvg.join(" "),i.toNode.length===0?e.removeAttribute("class"):e.setAttribute("class",i.toNode.join(" "))}const r=n.map(i=>J(i)).join(`
`);e.setAttribute(z,""),e.innerHTML=r}};function ce(t){t()}function en(t,e){const n=typeof e=="function"?e:et;if(t.length===0)n();else{let a=ce;u.mutateApproach===Hn&&(a=L.requestAnimationFrame||ce),a(()=>{const r=ja(),i=$t.begin("mutate");t.map(r),i(),n()})}}let Bt=!1;function nn(){Bt=!0}function Ft(){Bt=!1}let rt=null;function ue(t){if(!Qt||!u.observeMutations)return;const{treeCallback:e=et,nodeCallback:n=et,pseudoElementsCallback:a=et,observeMutationsRoot:r=h}=t;rt=new Qt(i=>{if(Bt)return;const o=D();X(i).forEach(s=>{if(s.type==="childList"&&s.addedNodes.length>0&&!fe(s.addedNodes[0])&&(u.searchPseudoElements&&a(s.target),e(s.target)),s.type==="attributes"&&s.target.parentNode&&u.searchPseudoElements&&a(s.target.parentNode),s.type==="attributes"&&fe(s.target)&&~Kn.indexOf(s.attributeName))if(s.attributeName==="class"&&Da(s.target)){const{prefix:c,iconName:f}=ft(Yt(s.target));s.target.setAttribute(jt,c||o),f&&s.target.setAttribute(zt,f)}else Ra(s.target)&&n(s.target)})}),_&&rt.observe(r,{childList:!0,attributes:!0,characterData:!0,subtree:!0})}function Ua(){rt&&rt.disconnect()}function Ha(t){const e=t.getAttribute("style");let n=[];return e&&(n=e.split(";").reduce((a,r)=>{const i=r.split(":"),o=i[0],s=i.slice(1);return o&&s.length>0&&(a[o]=s.join(":").trim()),a},{})),n}function Ga(t){const e=t.getAttribute("data-prefix"),n=t.getAttribute("data-icon"),a=t.innerText!==void 0?t.innerText.trim():"";let r=ft(Yt(t));return r.prefix||(r.prefix=D()),e&&n&&(r.prefix=e,r.iconName=n),r.iconName&&r.prefix||(r.prefix&&a.length>0&&(r.iconName=ga(r.prefix,t.innerText)||Gt(r.prefix,Pt(t.innerText))),!r.iconName&&u.autoFetchSvg&&t.firstChild&&t.firstChild.nodeType===Node.TEXT_NODE&&(r.iconName=t.firstChild.data)),r}function Xa(t){const e=X(t.attributes).reduce((r,i)=>(r.name!=="class"&&r.name!=="style"&&(r[i.name]=i.value),r),{}),n=t.getAttribute("title"),a=t.getAttribute("data-fa-title-id");return u.autoA11y&&(n?e["aria-labelledby"]="".concat(u.replacementClass,"-title-").concat(a||K()):(e["aria-hidden"]="true",e.focusable="false")),e}function $a(){return{iconName:null,title:null,titleId:null,prefix:null,transform:I,symbol:!1,mask:{iconName:null,prefix:null,rest:[]},maskId:null,extra:{classes:[],styles:{},attributes:{}}}}function de(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:{styleParser:!0};const{iconName:n,prefix:a,rest:r}=Ga(t),i=Xa(t),o=Et("parseNodeAttributes",{},t);let s=e.styleParser?Ha(t):[];return l({iconName:n,title:t.getAttribute("title"),titleId:t.getAttribute("data-fa-title-id"),prefix:a,transform:I,mask:{iconName:null,prefix:null,rest:[]},maskId:null,symbol:!1,extra:{classes:r,styles:s,attributes:i}},o)}const{styles:Ba}=C;function an(t){const e=u.autoReplaceSvg==="nest"?de(t,{styleParser:!1}):de(t);return~e.extra.classes.indexOf(Re)?R("generateLayersText",t,e):R("generateSvgReplacementMutation",t,e)}function Va(){return[...En,...vt]}function me(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:null;if(!_)return Promise.resolve();const n=h.documentElement.classList,a=d=>n.add("".concat(te,"-").concat(d)),r=d=>n.remove("".concat(te,"-").concat(d)),i=u.autoFetchSvg?Va():Te.concat(Object.keys(Ba));i.includes("fa")||i.push("fa");const o=[".".concat(Re,":not([").concat(z,"])")].concat(i.map(d=>".".concat(d,":not([").concat(z,"])"))).join(", ");if(o.length===0)return Promise.resolve();let s=[];try{s=X(t.querySelectorAll(o))}catch{}if(s.length>0)a("pending"),r("complete");else return Promise.resolve();const c=$t.begin("onTree"),f=s.reduce((d,g)=>{try{const p=an(g);p&&d.push(p)}catch(p){Le||p.name==="MissingIcon"&&console.error(p)}return d},[]);return new Promise((d,g)=>{Promise.all(f).then(p=>{en(p,()=>{a("active"),a("complete"),r("pending"),typeof e=="function"&&e(),c(),d()})}).catch(p=>{c(),g(p)})})}function Ka(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:null;an(t).then(n=>{n&&en([n],e)})}function qa(t){return function(e){let n=arguments.length>1&&arguments[1]!==void 0?arguments[1]:{};const a=(e||{}).icon?e:It(e||{});let{mask:r}=n;return r&&(r=(r||{}).icon?r:It(r||{})),t(a,l(l({},n),{},{mask:r}))}}const Qa=function(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:{};const{transform:n=I,symbol:a=!1,mask:r=null,maskId:i=null,title:o=null,titleId:s=null,classes:c=[],attributes:f={},styles:d={}}=e;if(!t)return;const{prefix:g,iconName:p,icon:y}=t;return ct(l({type:"icon"},t),()=>(W("beforeDOMElementCreation",{iconDefinition:t,params:e}),u.autoA11y&&(o?f["aria-labelledby"]="".concat(u.replacementClass,"-title-").concat(s||K()):(f["aria-hidden"]="true",f.focusable="false")),Xt({icons:{main:Ct(y),mask:r?Ct(r.icon):{found:!1,width:null,height:null,icon:{}}},prefix:g,iconName:p,transform:l(l({},I),n),symbol:a,title:o,maskId:i,titleId:s,extra:{attributes:f,styles:d,classes:c}})))};var Ja={mixout(){return{icon:qa(Qa)}},hooks(){return{mutationObserverCallbacks(t){return t.treeCallback=me,t.nodeCallback=Ka,t}}},provides(t){t.i2svg=function(e){const{node:n=h,callback:a=()=>{}}=e;return me(n,a)},t.generateSvgReplacementMutation=function(e,n){const{iconName:a,title:r,titleId:i,prefix:o,transform:s,symbol:c,mask:f,maskId:d,extra:g}=n;return new Promise((p,y)=>{Promise.all([Tt(a,o),f.iconName?Tt(f.iconName,f.prefix):Promise.resolve({found:!1,width:512,height:512,icon:{}})]).then(k=>{let[P,b]=k;p([e,Xt({icons:{main:P,mask:b},prefix:o,iconName:a,transform:s,symbol:c,maskId:d,title:r,titleId:i,extra:g,watchable:!0})])}).catch(y)})},t.generateAbstractIcon=function(e){let{children:n,attributes:a,main:r,transform:i,styles:o}=e;const s=st(o);s.length>0&&(a.style=s);let c;return Ut(i)&&(c=R("generateAbstractTransformGrouping",{main:r,transform:i,containerWidth:r.width,iconWidth:r.width})),n.push(c||r.icon),{children:n,attributes:a}}}},Za={mixout(){return{layer(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:{};const{classes:n=[]}=e;return ct({type:"layer"},()=>{W("beforeDOMElementCreation",{assembler:t,params:e});let a=[];return t(r=>{Array.isArray(r)?r.map(i=>{a=a.concat(i.abstract)}):a=a.concat(r.abstract)}),[{tag:"span",attributes:{class:["".concat(u.cssPrefix,"-layers"),...n].join(" ")},children:a}]})}}}},tr={mixout(){return{counter(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:{};const{title:n=null,classes:a=[],attributes:r={},styles:i={}}=e;return ct({type:"counter",content:t},()=>(W("beforeDOMElementCreation",{content:t,params:e}),Fa({content:t.toString(),title:n,extra:{attributes:r,styles:i,classes:["".concat(u.cssPrefix,"-layers-counter"),...a]}})))}}}},er={mixout(){return{text(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:{};const{transform:n=I,title:a=null,classes:r=[],attributes:i={},styles:o={}}=e;return ct({type:"text",content:t},()=>(W("beforeDOMElementCreation",{content:t,params:e}),se({content:t,transform:l(l({},I),n),title:a,extra:{attributes:i,styles:o,classes:["".concat(u.cssPrefix,"-layers-text"),...r]}})))}}},provides(t){t.generateLayersText=function(e,n){const{title:a,transform:r,extra:i}=n;let o=null,s=null;if(Ie){const c=parseInt(getComputedStyle(e).fontSize,10),f=e.getBoundingClientRect();o=f.width/c,s=f.height/c}return u.autoA11y&&!a&&(i.attributes["aria-hidden"]="true"),Promise.resolve([e,se({content:e.innerHTML,width:o,height:s,transform:r,title:a,extra:i,watchable:!0})])}}};const nr=new RegExp('"',"ug"),pe=[1105920,1112319],ge=l(l(l(l({},{FontAwesome:{normal:"fas",400:"fas"}}),On),Yn),Mn),_t=Object.keys(ge).reduce((t,e)=>(t[e.toLowerCase()]=ge[e],t),{}),ar=Object.keys(_t).reduce((t,e)=>{const n=_t[e];return t[e]=n[900]||[...Object.entries(n)][0][1],t},{});function rr(t){const e=t.replace(nr,""),n=ca(e,0),a=n>=pe[0]&&n<=pe[1],r=e.length===2?e[0]===e[1]:!1;return{value:Pt(r?e[0]:e),isSecondary:a||r}}function ir(t,e){const n=t.replace(/^['"]|['"]$/g,"").toLowerCase(),a=parseInt(e),r=isNaN(a)?"normal":a;return(_t[n]||{})[r]||ar[n]}function he(t,e){const n="".concat(Un).concat(e.replace(":","-"));return new Promise((a,r)=>{if(t.getAttribute(n)!==null)return a();const o=X(t.children).filter(p=>p.getAttribute(At)===e)[0],s=L.getComputedStyle(t,e),c=s.getPropertyValue("font-family"),f=c.match(Bn),d=s.getPropertyValue("font-weight"),g=s.getPropertyValue("content");if(o&&!f)return t.removeChild(o),a();if(f&&g!=="none"&&g!==""){const p=s.getPropertyValue("content");let y=ir(c,d);const{value:k,isSecondary:P}=rr(p),b=f[0].startsWith("FontAwesome");let x=Gt(y,k),v=x;if(b){const w=ha(k);w.iconName&&w.prefix&&(x=w.iconName,y=w.prefix)}if(x&&!P&&(!o||o.getAttribute(jt)!==y||o.getAttribute(zt)!==v)){t.setAttribute(n,v),o&&t.removeChild(o);const w=$a(),{extra:T}=w;T.attributes[At]=e,Tt(x,y).then(S=>{const dn=Xt(l(l({},w),{},{icons:{main:S,mask:Qe()},prefix:y,iconName:v,extra:T,watchable:!0})),ut=h.createElementNS("http://www.w3.org/2000/svg","svg");e==="::before"?t.insertBefore(ut,t.firstChild):t.appendChild(ut),ut.outerHTML=dn.map(mn=>J(mn)).join(`
`),t.removeAttribute(n),a()}).catch(r)}else a()}else a()})}function or(t){return Promise.all([he(t,"::before"),he(t,"::after")])}function sr(t){return t.parentNode!==document.head&&!~Gn.indexOf(t.tagName.toUpperCase())&&!t.getAttribute(At)&&(!t.parentNode||t.parentNode.tagName!=="svg")}function be(t){if(_)return new Promise((e,n)=>{const a=X(t.querySelectorAll("*")).filter(sr).map(or),r=$t.begin("searchPseudoElements");nn(),Promise.all(a).then(()=>{r(),Ft(),e()}).catch(()=>{r(),Ft(),n()})})}var lr={hooks(){return{mutationObserverCallbacks(t){return t.pseudoElementsCallback=be,t}}},provides(t){t.pseudoElements2svg=function(e){const{node:n=h}=e;u.searchPseudoElements&&be(n)}}};let ye=!1;var fr={mixout(){return{dom:{unwatch(){nn(),ye=!0}}}},hooks(){return{bootstrap(){ue(Et("mutationObserverCallbacks",{}))},noAuto(){Ua()},watch(t){const{observeMutationsRoot:e}=t;ye?Ft():ue(Et("mutationObserverCallbacks",{observeMutationsRoot:e}))}}}};const ve=t=>{let e={size:16,x:0,y:0,flipX:!1,flipY:!1,rotate:0};return t.toLowerCase().split(" ").reduce((n,a)=>{const r=a.toLowerCase().split("-"),i=r[0];let o=r.slice(1).join("-");if(i&&o==="h")return n.flipX=!0,n;if(i&&o==="v")return n.flipY=!0,n;if(o=parseFloat(o),isNaN(o))return n;switch(i){case"grow":n.size=n.size+o;break;case"shrink":n.size=n.size-o;break;case"left":n.x=n.x-o;break;case"right":n.x=n.x+o;break;case"up":n.y=n.y-o;break;case"down":n.y=n.y+o;break;case"rotate":n.rotate=n.rotate+o;break}return n},e)};var cr={mixout(){return{parse:{transform:t=>ve(t)}}},hooks(){return{parseNodeAttributes(t,e){const n=e.getAttribute("data-fa-transform");return n&&(t.transform=ve(n)),t}}},provides(t){t.generateAbstractTransformGrouping=function(e){let{main:n,transform:a,containerWidth:r,iconWidth:i}=e;const o={transform:"translate(".concat(r/2," 256)")},s="translate(".concat(a.x*32,", ").concat(a.y*32,") "),c="scale(".concat(a.size/16*(a.flipX?-1:1),", ").concat(a.size/16*(a.flipY?-1:1),") "),f="rotate(".concat(a.rotate," 0 0)"),d={transform:"".concat(s," ").concat(c," ").concat(f)},g={transform:"translate(".concat(i/2*-1," -256)")},p={outer:o,inner:d,path:g};return{tag:"g",attributes:l({},p.outer),children:[{tag:"g",attributes:l({},p.inner),children:[{tag:n.icon.tag,children:n.icon.children,attributes:l(l({},n.icon.attributes),p.path)}]}]}}}};const ht={x:0,y:0,width:"100%",height:"100%"};function xe(t){let e=arguments.length>1&&arguments[1]!==void 0?arguments[1]:!0;return t.attributes&&(t.attributes.fill||e)&&(t.attributes.fill="black"),t}function ur(t){return t.tag==="g"?t.children:[t]}var dr={hooks(){return{parseNodeAttributes(t,e){const n=e.getAttribute("data-fa-mask"),a=n?ft(n.split(" ").map(r=>r.trim())):Qe();return a.prefix||(a.prefix=D()),t.mask=a,t.maskId=e.getAttribute("data-fa-mask-id"),t}}},provides(t){t.generateAbstractMask=function(e){let{children:n,attributes:a,main:r,mask:i,maskId:o,transform:s}=e;const{width:c,icon:f}=r,{width:d,icon:g}=i,p=aa({transform:s,containerWidth:d,iconWidth:c}),y={tag:"rect",attributes:l(l({},ht),{},{fill:"white"})},k=f.children?{children:f.children.map(xe)}:{},P={tag:"g",attributes:l({},p.inner),children:[xe(l({tag:f.tag,attributes:l(l({},f.attributes),p.path)},k))]},b={tag:"g",attributes:l({},p.outer),children:[P]},x="mask-".concat(o||K()),v="clip-".concat(o||K()),w={tag:"mask",attributes:l(l({},ht),{},{id:x,maskUnits:"userSpaceOnUse",maskContentUnits:"userSpaceOnUse"}),children:[y,b]},T={tag:"defs",children:[{tag:"clipPath",attributes:{id:v},children:ur(g)},w]};return n.push(T,{tag:"rect",attributes:l({fill:"currentColor","clip-path":"url(#".concat(v,")"),mask:"url(#".concat(x,")")},ht)}),{children:n,attributes:a}}}},mr={provides(t){let e=!1;L.matchMedia&&(e=L.matchMedia("(prefers-reduced-motion: reduce)").matches),t.missingIconAbstract=function(){const n=[],a={fill:"currentColor"},r={attributeType:"XML",repeatCount:"indefinite",dur:"2s"};n.push({tag:"path",attributes:l(l({},a),{},{d:"M156.5,447.7l-12.6,29.5c-18.7-9.5-35.9-21.2-51.5-34.9l22.7-22.7C127.6,430.5,141.5,440,156.5,447.7z M40.6,272H8.5 c1.4,21.2,5.4,41.7,11.7,61.1L50,321.2C45.1,305.5,41.8,289,40.6,272z M40.6,240c1.4-18.8,5.2-37,11.1-54.1l-29.5-12.6 C14.7,194.3,10,216.7,8.5,240H40.6z M64.3,156.5c7.8-14.9,17.2-28.8,28.1-41.5L69.7,92.3c-13.7,15.6-25.5,32.8-34.9,51.5 L64.3,156.5z M397,419.6c-13.9,12-29.4,22.3-46.1,30.4l11.9,29.8c20.7-9.9,39.8-22.6,56.9-37.6L397,419.6z M115,92.4 c13.9-12,29.4-22.3,46.1-30.4l-11.9-29.8c-20.7,9.9-39.8,22.6-56.8,37.6L115,92.4z M447.7,355.5c-7.8,14.9-17.2,28.8-28.1,41.5 l22.7,22.7c13.7-15.6,25.5-32.9,34.9-51.5L447.7,355.5z M471.4,272c-1.4,18.8-5.2,37-11.1,54.1l29.5,12.6 c7.5-21.1,12.2-43.5,13.6-66.8H471.4z M321.2,462c-15.7,5-32.2,8.2-49.2,9.4v32.1c21.2-1.4,41.7-5.4,61.1-11.7L321.2,462z M240,471.4c-18.8-1.4-37-5.2-54.1-11.1l-12.6,29.5c21.1,7.5,43.5,12.2,66.8,13.6V471.4z M462,190.8c5,15.7,8.2,32.2,9.4,49.2h32.1 c-1.4-21.2-5.4-41.7-11.7-61.1L462,190.8z M92.4,397c-12-13.9-22.3-29.4-30.4-46.1l-29.8,11.9c9.9,20.7,22.6,39.8,37.6,56.9 L92.4,397z M272,40.6c18.8,1.4,36.9,5.2,54.1,11.1l12.6-29.5C317.7,14.7,295.3,10,272,8.5V40.6z M190.8,50 c15.7-5,32.2-8.2,49.2-9.4V8.5c-21.2,1.4-41.7,5.4-61.1,11.7L190.8,50z M442.3,92.3L419.6,115c12,13.9,22.3,29.4,30.5,46.1 l29.8-11.9C470,128.5,457.3,109.4,442.3,92.3z M397,92.4l22.7-22.7c-15.6-13.7-32.8-25.5-51.5-34.9l-12.6,29.5 C370.4,72.1,384.4,81.5,397,92.4z"})});const i=l(l({},r),{},{attributeName:"opacity"}),o={tag:"circle",attributes:l(l({},a),{},{cx:"256",cy:"364",r:"28"}),children:[]};return e||o.children.push({tag:"animate",attributes:l(l({},r),{},{attributeName:"r",values:"28;14;28;28;14;28;"})},{tag:"animate",attributes:l(l({},i),{},{values:"1;0;1;1;0;1;"})}),n.push(o),n.push({tag:"path",attributes:l(l({},a),{},{opacity:"1",d:"M263.7,312h-16c-6.6,0-12-5.4-12-12c0-71,77.4-63.9,77.4-107.8c0-20-17.8-40.2-57.4-40.2c-29.1,0-44.3,9.6-59.2,28.7 c-3.9,5-11.1,6-16.2,2.4l-13.1-9.2c-5.6-3.9-6.9-11.8-2.6-17.2c21.2-27.2,46.4-44.7,91.2-44.7c52.3,0,97.4,29.8,97.4,80.2 c0,67.6-77.4,63.5-77.4,107.8C275.7,306.6,270.3,312,263.7,312z"}),children:e?[]:[{tag:"animate",attributes:l(l({},i),{},{values:"1;0;0;0;0;1;"})}]}),e||n.push({tag:"path",attributes:l(l({},a),{},{opacity:"0",d:"M232.5,134.5l7,168c0.3,6.4,5.6,11.5,12,11.5h9c6.4,0,11.7-5.1,12-11.5l7-168c0.3-6.8-5.2-12.5-12-12.5h-23 C237.7,122,232.2,127.7,232.5,134.5z"}),children:[{tag:"animate",attributes:l(l({},i),{},{values:"0;0;1;1;0;0;"})}]}),{tag:"g",attributes:{class:"missing"},children:n}}}},pr={hooks(){return{parseNodeAttributes(t,e){const n=e.getAttribute("data-fa-symbol"),a=n===null?!1:n===""?!0:n;return t.symbol=a,t}}}},gr=[oa,Ja,Za,tr,er,lr,fr,cr,dr,mr,pr];Pa(gr,{mixoutsTo:O});O.noAuto;O.config;O.library;O.dom;const Mt=O.parse;O.findIconDefinition;O.toHtml;const hr=O.icon;O.layer;O.text;O.counter;var rn={exports:{}},br="SECRET_DO_NOT_PASS_THIS_OR_YOU_WILL_BE_FIRED",yr=br,vr=yr;function on(){}function sn(){}sn.resetWarningCache=on;var xr=function(){function t(a,r,i,o,s,c){if(c!==vr){var f=new Error("Calling PropTypes validators directly is not supported by the `prop-types` package. Use PropTypes.checkPropTypes() to call them. Read more at http://fb.me/use-check-prop-types");throw f.name="Invariant Violation",f}}t.isRequired=t;function e(){return t}var n={array:t,bigint:t,bool:t,func:t,number:t,object:t,string:t,symbol:t,any:t,arrayOf:e,element:t,elementType:t,instanceOf:e,node:t,objectOf:e,oneOf:e,oneOfType:e,shape:e,exact:e,checkPropTypes:sn,resetWarningCache:on};return n.PropTypes=n,n};rn.exports=xr();var Ar=rn.exports;const m=pn(Ar);function Ae(t,e){var n=Object.keys(t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(t);e&&(a=a.filter(function(r){return Object.getOwnPropertyDescriptor(t,r).enumerable})),n.push.apply(n,a)}return n}function E(t){for(var e=1;e<arguments.length;e++){var n=arguments[e]!=null?arguments[e]:{};e%2?Ae(Object(n),!0).forEach(function(a){U(t,a,n[a])}):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(n)):Ae(Object(n)).forEach(function(a){Object.defineProperty(t,a,Object.getOwnPropertyDescriptor(n,a))})}return t}function it(t){"@babel/helpers - typeof";return it=typeof Symbol=="function"&&typeof Symbol.iterator=="symbol"?function(e){return typeof e}:function(e){return e&&typeof Symbol=="function"&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e},it(t)}function U(t,e,n){return e in t?Object.defineProperty(t,e,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[e]=n,t}function wr(t,e){if(t==null)return{};var n={},a=Object.keys(t),r,i;for(i=0;i<a.length;i++)r=a[i],!(e.indexOf(r)>=0)&&(n[r]=t[r]);return n}function kr(t,e){if(t==null)return{};var n=wr(t,e),a,r;if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(t);for(r=0;r<i.length;r++)a=i[r],!(e.indexOf(a)>=0)&&Object.prototype.propertyIsEnumerable.call(t,a)&&(n[a]=t[a])}return n}function Lt(t){return Or(t)||Pr(t)||Sr(t)||Er()}function Or(t){if(Array.isArray(t))return Dt(t)}function Pr(t){if(typeof Symbol<"u"&&t[Symbol.iterator]!=null||t["@@iterator"]!=null)return Array.from(t)}function Sr(t,e){if(t){if(typeof t=="string")return Dt(t,e);var n=Object.prototype.toString.call(t).slice(8,-1);if(n==="Object"&&t.constructor&&(n=t.constructor.name),n==="Map"||n==="Set")return Array.from(t);if(n==="Arguments"||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n))return Dt(t,e)}}function Dt(t,e){(e==null||e>t.length)&&(e=t.length);for(var n=0,a=new Array(e);n<e;n++)a[n]=t[n];return a}function Er(){throw new TypeError(`Invalid attempt to spread non-iterable instance.
In order to be iterable, non-array objects must have a [Symbol.iterator]() method.`)}function Ir(t){var e,n=t.beat,a=t.fade,r=t.beatFade,i=t.bounce,o=t.shake,s=t.flash,c=t.spin,f=t.spinPulse,d=t.spinReverse,g=t.pulse,p=t.fixedWidth,y=t.inverse,k=t.border,P=t.listItem,b=t.flip,x=t.size,v=t.rotation,w=t.pull,T=(e={"fa-beat":n,"fa-fade":a,"fa-beat-fade":r,"fa-bounce":i,"fa-shake":o,"fa-flash":s,"fa-spin":c,"fa-spin-reverse":d,"fa-spin-pulse":f,"fa-pulse":g,"fa-fw":p,"fa-inverse":y,"fa-border":k,"fa-li":P,"fa-flip":b===!0,"fa-flip-horizontal":b==="horizontal"||b==="both","fa-flip-vertical":b==="vertical"||b==="both"},U(e,"fa-".concat(x),typeof x<"u"&&x!==null),U(e,"fa-rotate-".concat(v),typeof v<"u"&&v!==null&&v!==0),U(e,"fa-pull-".concat(w),typeof w<"u"&&w!==null),U(e,"fa-swap-opacity",t.swapOpacity),e);return Object.keys(T).map(function(S){return T[S]?S:null}).filter(function(S){return S})}function Cr(t){return t=t-0,t===t}function ln(t){return Cr(t)?t:(t=t.replace(/[\-_\s]+(.)?/g,function(e,n){return n?n.toUpperCase():""}),t.substr(0,1).toLowerCase()+t.substr(1))}var Tr=["style"];function Nr(t){return t.charAt(0).toUpperCase()+t.slice(1)}function Fr(t){return t.split(";").map(function(e){return e.trim()}).filter(function(e){return e}).reduce(function(e,n){var a=n.indexOf(":"),r=ln(n.slice(0,a)),i=n.slice(a+1).trim();return r.startsWith("webkit")?e[Nr(r)]=i:e[r]=i,e},{})}function fn(t,e){var n=arguments.length>2&&arguments[2]!==void 0?arguments[2]:{};if(typeof e=="string")return e;var a=(e.children||[]).map(function(c){return fn(t,c)}),r=Object.keys(e.attributes||{}).reduce(function(c,f){var d=e.attributes[f];switch(f){case"class":c.attrs.className=d,delete e.attributes.class;break;case"style":c.attrs.style=Fr(d);break;default:f.indexOf("aria-")===0||f.indexOf("data-")===0?c.attrs[f.toLowerCase()]=d:c.attrs[ln(f)]=d}return c},{attrs:{}}),i=n.style,o=i===void 0?{}:i,s=kr(n,Tr);return r.attrs.style=E(E({},r.attrs.style),o),t.apply(void 0,[e.tag,E(E({},r.attrs),s)].concat(Lt(a)))}var cn=!1;try{cn=!0}catch{}function _r(){if(!cn&&console&&typeof console.error=="function"){var t;(t=console).error.apply(t,arguments)}}function we(t){if(t&&it(t)==="object"&&t.prefix&&t.iconName&&t.icon)return t;if(Mt.icon)return Mt.icon(t);if(t===null)return null;if(t&&it(t)==="object"&&t.prefix&&t.iconName)return t;if(Array.isArray(t)&&t.length===2)return{prefix:t[0],iconName:t[1]};if(typeof t=="string")return{prefix:"fas",iconName:t}}function bt(t,e){return Array.isArray(e)&&e.length>0||!Array.isArray(e)&&e?U({},t,e):{}}var ke={border:!1,className:"",mask:null,maskId:null,fixedWidth:!1,inverse:!1,flip:!1,icon:null,listItem:!1,pull:null,pulse:!1,rotation:null,size:null,spin:!1,spinPulse:!1,spinReverse:!1,beat:!1,fade:!1,beatFade:!1,bounce:!1,shake:!1,symbol:!1,title:"",titleId:null,transform:null,swapOpacity:!1},un=Oe.forwardRef(function(t,e){var n=E(E({},ke),t),a=n.icon,r=n.mask,i=n.symbol,o=n.className,s=n.title,c=n.titleId,f=n.maskId,d=we(a),g=bt("classes",[].concat(Lt(Ir(n)),Lt((o||"").split(" ")))),p=bt("transform",typeof n.transform=="string"?Mt.transform(n.transform):n.transform),y=bt("mask",we(r)),k=hr(d,E(E(E(E({},g),p),y),{},{symbol:i,title:s,titleId:c,maskId:f}));if(!k)return _r("Could not find icon",d),null;var P=k.abstract,b={ref:e};return Object.keys(n).forEach(function(x){ke.hasOwnProperty(x)||(b[x]=n[x])}),Mr(P[0],b)});un.displayName="FontAwesomeIcon";un.propTypes={beat:m.bool,border:m.bool,beatFade:m.bool,bounce:m.bool,className:m.string,fade:m.bool,flash:m.bool,mask:m.oneOfType([m.object,m.array,m.string]),maskId:m.string,fixedWidth:m.bool,inverse:m.bool,flip:m.oneOf([!0,!1,"horizontal","vertical","both"]),icon:m.oneOfType([m.object,m.array,m.string]),listItem:m.bool,pull:m.oneOf(["right","left"]),pulse:m.bool,rotation:m.oneOf([0,90,180,270]),shake:m.bool,size:m.oneOf(["2xs","xs","sm","lg","xl","2xl","1x","2x","3x","4x","5x","6x","7x","8x","9x","10x"]),spin:m.bool,spinPulse:m.bool,spinReverse:m.bool,symbol:m.oneOfType([m.bool,m.string]),title:m.string,titleId:m.string,transform:m.oneOfType([m.string,m.object]),swapOpacity:m.bool};var Mr=fn.bind(null,Oe.createElement);export{un as F};
