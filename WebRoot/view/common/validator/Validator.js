/*************************************************
 	author 广东数据通信网络有限公司-研发中心-平台产品部-何钦锰
 	version 1.0,CreateTime:2008-06-14

Validator目前可实现的验证类型有：
1.Require  		：  必填,当status=='enable'时会自动在节点后面加上红色*号
2.Chinese  		：  中文
3.English  		：  英文
4.Number   		：  正整数
5.Integer  		：  整数
6.Double   		：  浮点数   可选配置 precise(精确数，如：##.## 只允许类似为35.09、5.09这样的数)
7.Email    		：  邮件
8.Url      		：  使用HTTP协议的网址
9.Phone   		：  电话号码
10.Mobile  		：  手机号码
11.Currency		：  货币
13.Zip     		：  邮政编码
14.IdCard  		：  身份证号码
15.QQ     		：  QQ号码
16.Time    		：  时间    可选配置 format(时间格式，支持 支持"HH时mm分ss秒"，默认为"HH:mm:ss"),theValue(指定的时间,填new Date()表示当前时间), opt(比较动作)：eq(默认等于),neq(不等于),gt(大于),gteq(大于等于),lt(小于),lteq(小于等于)
17.Date    		：  日期    可选配置 format(日期格式，支持 "yyyy/MM/dd"、"yyyy.MM.dd"、"yyyy年MM月dd日"，默认为"yyyy-MM-dd"),theValue(指定的日期,填new Date()表示当前时间), opt(比较动作)：eq(默认等于),neq(不等于),gt(大于),gteq(大于等于),lt(小于),lteq(小于等于)
18.DateTime    	：  日期时间    可选配置 format(日期时间格式，支持 "yyyy/MM/dd HH:mm:ss"、"yyyy.MM.dd HH:mm:ss"、"yyyy年MM月dd日 HH时mm分ss秒"，默认为"yyyy-MM-dd HH:mm:ss"),theValue(指定的日期时间,填new Date()表示当前时间), opt(比较动作)：eq(默认等于),neq(不等于),gt(大于),gteq(大于等于),lt(小于),lteq(小于等于)
19.Safe         ：  符合安全规则的字符串
20.Repeat       ：  某项的重复值  必要配置 to(另一个输入值的name)
21.Compare      ：  两数的关系比较  必要配置 theValue(指定的数字) 可选配置 opt(比较动作)：eq(默认等于),neq(不等于),gt(大于),gteq(大于等于),lt(小于),lteq(小于等于)
22.Range        ：  判断输入值是否在(n, m)区间 必要配置 min(n的值) max(m的值)
23.Limit        ：  输入字符长度限制  可选配置 min(n的值，默认0) max(m的值，默认Number.MAX_VALUE)
24.LimitB   	：  双字节字符长度限制 可选配置 min(n的值，默认0) max(m的值，默认Number.MAX_VALUE)
25.Group        ：  对于具有相同名称的单选按钮的选中判断。限制具有相同名称的多选按钮的选中数目 可选配置 min(n的值，默认0) max(m的值，默认Number.MAX_VALUE)
26.Filter       ：  文件上传格式过滤  必要配置 accept(文件后缀名,如：jpg, gif, png)
27.Custom       ：  自定义的正则表达式验证 必要配置 regexp(自定义的正则表达式)

支持组合验证，以","号分隔，例如配置validateType=Require,Chinese,LimitB 表示需要必填、中文、且有字节长度限制
支持前缀验证，如配置 prefix=true  表示对于具有相同前缀的名字节点的统一配置，此时name值将作为前缀名
支持预注册，即可以先注册还没有出现的html节点

配置方式：
var obj = {
	name:'Card',					//必要配置，需要被配置的节点的name,不是id
	text:'身份证',					//字段对应的label，一般用于提示作用
	validateType:'IdCard,Require',		//必要配置，验证的类型，可配置多个
	enableInfoTip:true,					//可选配置，默认为false，当节点获得焦点时显示提示信息
	infoTip:'请输入正确的18位身份证号码！', //可选配置，默认为'没有提示信息,请检查。如果不需要提示信息,请配置为不启用！',必须enableInfoTip=true
	enableErrorTip:true,		//可选配置，默认为true 表示启用错误提示
	errorTip:'身份证号码不正确', 	//可选配置，不选有个默认值，用于错误提示，必须enableErrorTip=true
	prefix:false,               //是否采用前缀验证，表示对于具有相同前缀的名字节点的统一配置，此时name值将作为前缀名，默认为false
	status:'enable'              //状态 'disabled'(不可编辑)/'enable'(可编辑，默认)/'hidden'(隐藏)
}
Validator.add(obj,theForm);//注册到验证类中 theForm可不传，默认为 document.forms[0]
Validator.vd(document.getElementById(obj.name));//验证指定的节点
Validator.remove(document.getElementById(obj.name));//销毁指定节点的验证

Validator.add(objArray,theForm);//一次性全部注册
Validator.vd();//全部验证,有错误会弹出alert
Validator.remove();//销毁全部验证

运行环境(客户端)：
在Windows XP下用IE6.0+SP2和Mozilla Firefox 2.0.0.14测试通过


修改日志：
1、2008-08-22 增加 Validator.valid(dom,validateType);//手动指定验证，用于其他触发事件中
2、2008-08-25 增加 如果指定了 Limit 类型验证，则自动验证 LimitB 的验证 max(LimitB) = max(Limit)
3、2008-08-26 增加 Time、DateTime 类型验证
4、2008-10-16 增加 对Time、Date和DateTime类型指定值的比较
5、2008-12-04 增加 对放置星号位置的判断。如果有label_obj.name的标签，则将星号放置在这里
6、2009-07-07 更改 对text和textarea的状态从disabled改为readonly


//TODO:考虑用onpropertychange(IE)、oninput(FF,需要用addEventListener来注册) 替换掉onfocus和onblur

*************************************************/

Validator = {
 	AutoVd : false, //是否自动提交验证，实际就是在form的onsubmit方法中自动调用Validator.vd()方法
 	noEdit : 'readOnly',//不可编辑状态：readOnly=只读、disabled=失效、变灰
	Require : /.+/,
	Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	Phone : /^((\(\d{2,3}\-\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3})?[1-9]\d{6,7}(\-\d{1,4})?$/,
	Mobile : /^1[3|4|5|8|7][0-9]\d{8}$/,//手机号码验证（新）
	oldMobile : /^((\(\d{2,3}\))|(\d{3}\-))?1\d{10}$/,//以前的手机号码验证
	Url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
	IdCard : "Validator.isIdCard(node.value)",
	Currency : /^\d+(\.\d+)?$/,
	Number : /^\d+$/,
	Zip : /^[1-9]\d{5}$/,
	QQ : /^[1-9]\d{4,11}$/,
	Integer : /^[-\+]?\d+$/,
	Double : "Validator.isDouble(node.value,obj.precise)",
	English : /^[A-Za-z]+$/,
	Chinese :  /^[\u0391-\uFFE5]+$/,
	Username : /^[a-z]\w{3,}$/i,
	UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
	IsSafe : function(str){return !Validator.UnSafe.test(str);},
	Safe : "Validator.IsSafe(node.value)",
	Filter : "Validator.doFilter(node.value, obj.accept)",
	Limit : "Validator.limit(node.value.length,obj.min,  obj.max)",
	LimitB : "Validator.limit(Validator.lenB(node.value), obj.min,obj.max)",
	Time : "Validator.isTime(node.value, obj.format,obj.opt,obj.theValue)",
	Date : "Validator.isDate(node.value, obj.format,obj.opt,obj.theValue)",
	DateTime : "Validator.isDateTime(node.value, obj.format,obj.opt,obj.theValue)",
	Repeat : "node.value == document.getElementsByName(obj.to)[0].value",
	Range : "obj.min <= parseFloat(node.value) && parseFloat(node.value) <= obj.max",
	Compare : "Validator.compare(node.value,obj.opt,obj.theValue)",
	Custom : "Validator.exec(node.value, obj.regexp)",
	Group : "Validator.mustChecked(node.getAttribute('name'), obj.min, obj.max)",
	TheForm : null,
	Cache : [],
	IsLoad : false,
	DefaultErrorMsg:[],
	InfoTip : null,
	ErrorTip : null,
	ErrorItem : [],
	ErrorMessage : [],
	events : {},//回调事件集合loadBefore,loadAfter
	load : function(theForm){//theForm参数可以为form对象、form的id
		if(!Validator.IsLoad){
			var theForm = theForm||document.forms[0];//取页面中的第一个表单
			if(typeof theForm == 'undefined'){
				alert('当前页面寻找不到form对象，请检查！');
				return false;
			}
			//回调加载前事件
			if(this.fireEvent('loadBefore')===false)return false;

			Validator.TheForm = theForm;
			Validator.ErrorTip = document.createElement("div");
			Validator.ErrorTip.className = "v-form-invalid-tip";
			document.body.appendChild(Validator.ErrorTip);
			Validator.InfoTip = document.createElement("div");
			Validator.InfoTip.className = "v-form-info-tip";
			document.body.appendChild(Validator.InfoTip);
			Validator.DefaultErrorMsg['Require'] = '必填!';
			Validator.DefaultErrorMsg['Username'] = '用户名不符合规则!';
			Validator.DefaultErrorMsg['Chinese'] = '必须为中文！';
			Validator.DefaultErrorMsg['LimitB'] = '内容不符合大小限制!';
			Validator.DefaultErrorMsg['English'] = '必须为英文!';
			Validator.DefaultErrorMsg['Number'] = '必须为正整数!';
			Validator.DefaultErrorMsg['Integer'] = '必须为整数!';
			Validator.DefaultErrorMsg['Double'] = '必须为浮点数!';
			Validator.DefaultErrorMsg['Email'] = '内容不符合Email规则!';
			Validator.DefaultErrorMsg['Url'] = '内容不符合Url规则!';
			Validator.DefaultErrorMsg['Phone'] = '不是一个正确的电话号码!';
			Validator.DefaultErrorMsg['Currency'] = '不是一个正确的货币!';
			Validator.DefaultErrorMsg['Mobile'] = '不是一个正确的手机号码!';
			Validator.DefaultErrorMsg['Zip'] = '不是一个正确的邮政编码!';
			Validator.DefaultErrorMsg['IdCard'] = '不是一个正确的身份证号码!';
			Validator.DefaultErrorMsg['QQ'] = '不是一个正确的QQ号码!';
			Validator.DefaultErrorMsg['Date'] = '不是一个正确的日期格式!';
			Validator.DefaultErrorMsg['Safe'] = '安全等级不够!';
			Validator.DefaultErrorMsg['Repeat'] = '重新输入的内容不正确!';
			Validator.DefaultErrorMsg['Compare'] = '与指定值的对比不符合期望结果!';
			Validator.DefaultErrorMsg['Range'] = '内容不符合范围限制!';
			Validator.DefaultErrorMsg['Limit'] = '内容不符合大小限制!';
			Validator.DefaultErrorMsg['Group'] = '选择错误!';
			Validator.DefaultErrorMsg['Custom'] = '内容不符合所定义的规则!';
			Validator.DefaultErrorMsg['Filter'] = '文件格式不正确!';
			for(var n=0;n<Validator.Cache.length;n++){
				var obj = Validator.Cache[n];
				Validator.loading(obj);
			}
			if(Validator.AutoVd===true){
				var oldOnSubmit = theForm.onsubmit;
				if(oldOnSubmit){
					theForm.onsubmit = function(){oldOnSubmit();return Validator.vd();};
				}else{
					theForm.onsubmit = function(){return Validator.vd();};
				}
			}
			this.fireEvent('loadAfter');//回调加载后事件
			Validator.IsLoad = true;
		}
	},
	fireEvent : function(evtName){
		var fn = this.events[evtName];
		if(fn){
			var args = Array.prototype.slice.call(arguments,1);
			if(fn.apply(this,args)===false){
				return false;
			}
		}
		return true;
	},
	addMyEvent : function(evtName,fn){
		this.events[evtName] = fn;
	},
	loading : function(obj){
		if(typeof obj != 'object'&&typeof obj != 'string')return false;
		if(typeof obj == 'string')
			obj = Validator.getCache(obj);
		if(typeof obj != 'object' || obj == null)return false;
		var eles = [];
		if(obj.prefix===true){
			var es = Validator.TheForm.elements;
			for(var i = 0;i<es.length;i++){
				if((es[i].getAttribute('name')&&es[i].getAttribute('name').indexOf(obj.name)==0)||(es[i].getAttribute('id')&&es[i].getAttribute('id').indexOf(obj.name)==0))
					eles[eles.length]=es[i];
			}
		}else{
			eles = document.getElementsByName(obj.name);
		}
		if(eles){
			for(var k=0;k<eles.length;k++){
				var ele = eles[k];
				if(ele.getAttribute('validate')=='true')return false;
				var xy = Validator.getXY(ele,ele.parentNode);
				this.addXH(ele);
				if(ele.className)
					ele.setAttribute('oldClass',ele.className);
				if(obj.status=='disabled'){
					Validator.disable(ele);
				}else if(obj.status=='hidden'){
					ele.style.display = 'none';
				}else if(obj.status=='enable'){
					ele.disabled = false;
					if(obj.enableInfoTip)
						Validator.addEvent(ele,"focus",Validator.showInfoTip);
					Validator.addEvent(ele,"blur",Validator.hideInfoTip);
				}
				ele.setAttribute('validate','true');
			}
		}
	},
	disable : function(elem){//设置不可编辑状态
		if(elem&&elem.nodeType==1){
			if(Validator.noEdit=='disabled'){//变灰的不可编辑状态
				elem.disabled = true;
			}else{//默认只读的不可编辑状态
				var nodeName = elem.nodeName.toUpperCase();//节点名
				/**/
				elem.onclick=elem.onblur=elem.onchange=elem.ondblclick=elem.onfocus=
					elem.onmousedown=elem.onmouseout=elem.onmouseover
						=elem.onmouseup= function(){return false;};

				if('INPUT'==nodeName){
					var type = elem.type.toLowerCase();//类型 text、password、checkbox、radio、submit、reset、file、hidden、image、button
					if('text'==type||'password'==type){
						elem.readOnly=true;
						elem.className = 'v-form-readonly';
						//elem.style.backgroundColor='#F3F3EC';//底色变灰
					}else if('radio'==type){
						elem.onmousedown = function(){
							var rs = document.getElementsByName(this.name||this.id);
							for(var i=0,length=rs.length;i<length;i++){
								rs[i].oldChecked = rs[i].checked;
							}
						}
						elem.onclick = function(){
							var rs = document.getElementsByName(this.name||this.id);
							for(var i=0,length=rs.length;i<length;i++){
								rs[i].checked = !!rs[i].oldChecked;
							}
						}
					}else if('checkbox'==type){
						elem.onclick = function(){return false;}
					}else if('hidden'==type){
						//do nothing
					}else{
						elem.disabled = true;
					}
				}else if('TEXTAREA'==nodeName){
					elem.readOnly=true;
					elem.className = 'v-form-readonly';
					//elem.style.backgroundColor='#F3F3EC';//底色变灰
				}else if('SELECT'==nodeName){
					elem.onclick = function(){this.oldSelectedIndex = this.selectedIndex;};
					elem.onchange=function(){this.selectedIndex=this.oldSelectedIndex;};
					elem.className = 'v-form-readonly';
					//elem.style.backgroundColor='#F3F3EC';//底色变灰
				}else if('A'==nodeName)
					elem.onclick = function(){return false;}
				else if('LABEL'==nodeName)
					;//do nothing
				else
					elem.disabled = true;

				elem.title='不可编辑';
			}
		}
	},
	isCache : function(name){//判断是否注册验证
		return Validator.getCache(name)!=null;
	},
	getCache : function(name){//获取注册对象，关键处理前缀模式
		for(var i=0;i<Validator.Cache.length;i++){
			if(Validator.Cache[i].name==name||(name.indexOf(Validator.Cache[i].name)==0&&Validator.Cache[i].prefix==true))
				return Validator.Cache[i];
		}
		return null;
	},
	removeCache : function(name){
		var index = -1;
		for(var i=0;i<Validator.Cache.length;i++){
			if(Validator.Cache[i].name==name||(name.indexOf(Validator.Cache[i].name)==0&&Validator.Cache[i].prefix==true)){
				index = i;
				break;
			}
		}
		if(index!=-1){
			Validator.Cache.splice(index,1);
		}
	},
	vd : function(node){
		if(node){
			if(!Validator.isCache(node.name||node.id))return true;//没有注册验证的不验证
			var obj = Validator.getCache(node.name||node.id);
			if(obj.status == 'disabled')return true;//不可编辑的不验证
			var validateType = obj.validateType;//得到验证类型
			if(!(/Require/.test(validateType))&&!node.value)return true;//内容为空，并且不是必填的话，不验证
			Validator.clearState(node);
			var va = validateType.split(',');
			for(var k=0;k<va.length;k++){
				var v = va[k];
				if(typeof(Validator[v]) === "undefined")continue;//不支持的验证类型，跳过
				switch(v){
					case "IdCard"  :
					case "Time"    :
					case "Date"    :
					case "DateTime":
					case "Repeat"  :
					case "Range"   :
					case "Compare" :
					case "Custom"  :
					case "Group"   :
					case "Limit"   :
						if(!eval(Validator[v]))obj.errorItem.push(v);
						break;
					case "LimitB"  :
						if(!eval(Validator[v]))obj.errorItem.push(v);
						break;
					case "Safe"    :
					case "Filter"  :
					case "Double"  :
						if(!eval(Validator[v]))obj.errorItem.push(v);
						break;
					default :
						if(!Validator[v].test(node.value))obj.errorItem.push(v);
						break;
				}
			}
			if(obj.errorItem.length>0){
				var errorTip = obj.errorTip;
				if(errorTip==''){
					for(var l=0;l<obj.errorItem.length;l++){
						var cNum = obj.errorItem.length==1 ? '' : Validator.getCNum(l)+'.';
						errorTip += cNum +Validator.getErrorMsg(obj,obj.errorItem[l]) + '<br>';
					}
					obj.defaultErrorTip = errorTip;
				}
				Validator.addError(node);
				return false;
			}
			return true;
		}else{
			var theForm = Validator.TheForm;
			Validator.ErrorItem.length=0;
			Validator.ErrorMessage.length=0;
			var count = theForm.elements.length;
			for(var i=0;i<count;i++){
				Validator.vd(theForm.elements[i]);
			}
			if(Validator.ErrorMessage.length > 0){
				alert("以下原因导致提交失败：\t\t\n"+Validator.ErrorMessage.join("\n").replace(/<br>/gi,' '));
				try{
					Validator.ErrorItem[0].focus();
				}catch(e){}
				return false;
			}
			if(window.CustomVd)//如果回调函数存在，则回调
				return CustomVd()===true?true:false;
			return true;
		}
	},
	
	vdForm : function(ele){
		var theForm = typeof ele == "string" ? document.getElementById(ele) : ele;
		Validator.ErrorItem.length=0;
		Validator.ErrorMessage.length=0;
		var count = theForm.elements.length;
		for(var i=0;i<count;i++){
			Validator.vd(theForm.elements[i]);
		}
		if(Validator.ErrorMessage.length > 0){
			alert("以下原因导致提交失败：\t\t\n"+Validator.ErrorMessage.join("\n").replace(/<br>/gi,' '));
			try{
				Validator.ErrorItem[0].focus();
			}catch(e){}
			return false;
		}
		if(window.CustomVd)//如果回调函数存在，则回调
			return CustomVd()===true?true:false;
		return true;
	},
	
	valid : function(dom,validateType){
		if(!dom)return false;
		var obj = {
			name : dom.name||dom.id,
			validateType : validateType
		}
		var oldObj = this.getCache(obj.name);
		if(oldObj){//已经存在
			var vs = validateType.split(',');
			var xh1 = /Require/.test(oldObj.validateType);
			for(var i=0;i<vs.length;i++){
				var v = vs[i];
				if(oldObj.validateType.indexOf(v)==-1){
					oldObj.validateType += ','+v;
				}
			}
			var xh2 = /Require/.test(oldObj.validateType);
			if(!xh1&&xh2){//如果原来没必填，增加有必填，需要加上星号
				this.addXH(dom);
			}
		}else{
			this.add(obj);
			this.vd(dom);
		}
	},
	add : function(obj){
		if(typeof obj === 'object'){
			if(typeof obj.pop === 'function'){
				for(var i=0;i<obj.length;i++){
					var o = obj[i];
					Validator.add(o);
				}
				return true;
			}
		}else if(typeof obj === 'undefined' || typeof obj.name != 'string' || typeof obj.validateType != 'string'){
			alert('验证对象配置错误，请检查！');
			return false;
		}

		if(typeof obj.errorTip!='string'){
			obj.errorTip = '';
		}
		obj.infoTip = obj.infoTip||'没有提示信息,请检查。如果不需要提示信息,请配置为不启用！';//正常提示信息
		obj.enableInfoTip = (obj.enableInfoTip=='Y'||obj.enableInfoTip=='true'||obj.enableInfoTip==true)?true:false;//是否启用正常提示信息
		obj.enableErrorTip = (obj.enableErrorTip=='N'||obj.enableErrorTip=='false'||obj.enableErrorTip==false)?false:true;//是否启用错误提示信息
		obj.prefix = (obj.prefix == 'Y'||obj.prefix == 'true'||obj.prefix == true)?true:false;//是否前缀
		obj.status = obj.status=='disabled'?'disabled':obj.status=='hidden'?'hidden':'enable';//是否可编辑
		if(obj.disabled==true)obj.status='disabled';//兼容旧写法
		obj.errorItem = [];//当前验证错误项
		obj.defaultErrorTip = '';
		Validator.Cache.push(obj);//先放到缓存集合中
		if(Validator.IsLoad==true)
			Validator.loading(obj);
		return true;
	},
	remove : function(obj){
		if(!Validator.IsLoad)
			return;
		if(obj){
			try{
				if(typeof obj == 'string')obj = document.getElementById(obj);
				var name = obj.name||obj.id;
				var c = Validator.getCache(obj.name||obj.id);
				if(c)name = c.name;
				var objs = document.getElementsByName(name);
				for(var i =0;i<objs.length;i++){
					var o = objs[i];
					Validator.clearState(o);
					Validator.removeEvent(o,"focus",Validator.showInfoTip);
					Validator.removeEvent(o,"blur",Validator.hideInfoTip);
					var next = o.nextSibling;
					if(next&&next.id&&next.id.indexOf('_xh_span')!=-1){
						if(window.attachEvent){//ie下的销毁方式
							var div = document.createElement('div');
							div.appendChild(next);
			                div.innerHTML = '';
						}else{
							o.parentNode.removeChild(next);
						}
					}
					o.setAttribute('validate','false');
				}
				if(c)
					Validator.removeCache(obj.name||obj.id);
				//obj.disabled = false;
				//obj.style.display='';
			}catch(e){}
		}else{
			var theForm = Validator.TheForm;
			if(theForm){
				for(var i=0;i<theForm.elements.length;i++){
					Validator.remove(theForm.elements[i]);
				}
			}
			if(window.attachEvent){//ie下的销毁方式
				var div = document.createElement('div');
				div.appendChild(Validator.InfoTip);
				div.appendChild(Validator.ErrorTip);
                div.innerHTML = '';
			}else{
				Validator.InfoTip.parentNode.removeChild(Validator.InfoTip);
				Validator.ErrorTip.parentNode.removeChild(Validator.ErrorTip);
			}
			Validator.InfoTip = null;
			Validator.ErrorTip = null;
			Validator.IsLoad = false;
		}
	},
	removeV : function(v,obj){//销毁指定验证类型的验证，v验证类型可以放置多个，以","号分隔
		if(obj){
			try{
				if(!Validator.isCache(obj.name||obj.id))return true;//没有注册验证,不处理
				Validator.clearState(obj);
				var c = Validator.getCache(obj.name||obj.id);
				if(/Require/.test(v)){//必填项，要去掉星号
					var next = obj.nextSibling;
					if(next&&next.id==c.name+'_xh_span'){
						if(window.attachEvent){//ie下的销毁方式
							var div = document.createElement('div');
							div.appendChild(next);
			                div.innerHTML = '';
						}else{
							obj.parentNode.removeChild(next);
						}
					}
				}
				c.validateType = c.validateType.replace(','+v,'').replace(','+v,'').replace(v,'').replace(',,',',');
				if(c.validateType.length==0){//如果验证类型已经为空，则说明已经无验证了
					Validator.removeEvent(obj,"focus",Validator.showInfoTip);
					Validator.removeEvent(obj,"blur",Validator.hideInfoTip);
					Validator.removeCache(obj.name||obj.id);
				}

			}catch(e){}
		}else{
			var theForm = Validator.TheForm;
			if(theForm){
				for(var i=0;i<theForm.elements.length;i++){
					Validator.removeV(v,theForm.elements[i]);
				}
			}
		}
	},
	clearState : function(node){//清除异常状态
		var obj = Validator.getCache(node.name||node.id);
		var name = node.name||node.id;
		if(obj){
			obj.errorItem.length=0;//清空错误项
			obj.defaultErrorTip = '';//清楚错误提示
			name = obj.name
		}
		if(node.getAttribute('validate')!=='true')return true;
		var oldClass = node.getAttribute('oldClass');//原样式
		node.className = typeof oldClass !='undefined'&& oldClass!='v-form-invalid' ? oldClass:null;
		var eles = document.getElementsByName(name+"__ErrorMessagePanel");
		if(!eles)return true;
		for(var i=0;i<eles.length;i++){
			try{
				if(window.attachEvent){//ie下的销毁方式
					var div = document.createElement('div');
					div.appendChild(eles[i]);
	                div.innerHTML = '';
				}else{
					eles[i].parentNode.removeChild(eles[i]);
				}
			}catch(e){}
		}
	},
	addXH : function(node){
		if(!Validator.isCache(node.name||node.id))return false;
		var obj = Validator.getCache(node.name||node.id);
		var validateType = obj.validateType;
		if(/Require/.test(validateType)&&obj.status=='enable'&&node.style.display!='none'&&(node.tagName!='INPUT'||node.type!='hidden')){//必填自动加上红色*
			if(!(/Group/.test(validateType))){
				var xh = document.getElementById('label_'+obj.name);//判断是否有指定放置星号的标签
				if(xh){
					xh.style.width='2px';
					xh.innerHTML="<FONT COLOR='RED' style='font-size:12px'>*</FONT>";
				}else{
					var next = node.nextSibling;
					if(!next||!next.innerHTML||next.innerHTML.indexOf('*')<0){
						/*//改用绝对位置的方式*/
						xh = document.createElement("label");
						xh.innerHTML="<FONT COLOR='RED' style='font-size:12px'>*</FONT>";
						xh.name =xh.id= obj.name+"_xh_span";
						if(node.style.position=='absolute'){
							var xy = Validator.getXY(node,node.parentNode);//节点的绝对位置
							xh.className = "v-form-invalid-xh";
							node.parentNode.appendChild(xh);
							xh.style.left=xy.x+node.offsetWidth;
							xh.style.top=xy.y+node.offsetHeight-xh.offsetHeight;
						}else{
							if(next)node.parentNode.insertBefore(xh,next);
							else node.parentNode.appendChild(xh);
						}
					}
				}
			}
		}
	},
	addError : function(node){
		if(!Validator.isCache(node.name||node.id))return true;
		var obj = Validator.getCache(node.name||node.id);
		var enableErrorTip = obj.enableErrorTip;
		var errorTip = obj.errorTip==''?obj.defaultErrorTip:obj.errorTip;
		var validateType = obj.validateType;
		var showError = function(n){
			var xy = Validator.getXY(n,n.parentNode);//节点的绝对位置
			try{
				var oldWidth = n.offsetWidth;//保留原有宽
				var oldHeight = n.offsetHeight;//保留原有高
				//n.className = 'v-form-invalid';
				
				//验证不通过时，不需要增大宽度和高度     update 陈廷峰
				var _tempOldClass = n.className; //原来的样式
				_tempOldClass = typeof _tempOldClass !='undefined'&& _tempOldClass!='v-form-invalid' ? " "+_tempOldClass:"";
				n.className = 'v-form-invalid'+_tempOldClass;
//				if(oldWidth>0)
//					n.style.width = oldWidth;
//				if(oldHeight>0)
//					n.style.height = oldHeight;
				
				var validate = n.getAttribute('validate');
				if(validate!='true'){
					if(obj.enableInfoTip){
						Validator.addEvent(n,"focus",Validator.showInfoTip);
					}
					Validator.addEvent(n,"blur",Validator.hideInfoTip);
					this.addXH(n);
					n.setAttribute('validate','true');
				}
				if(enableErrorTip&&n.style.display!='none'){
					/*//改用绝对位置的方式*/
					var img = document.createElement("label");
					img.id = img.name = obj.name+"__ErrorMessagePanel";
					img.setAttribute("errorTip",errorTip);
					if(n.style.position=='absolute'){
						img.className = 'v-form-invalid-img-p';
						n.parentNode.appendChild(img);
						img.style.left=xy.x+n.offsetWidth;
						img.style.top=xy.y+n.offsetHeight-img.offsetHeight;
					}else{
						img.className = 'v-form-invalid-img';
						var next = n.nextSibling;
						if(next)n.parentNode.insertBefore(img,next);
						else n.parentNode.appendChild(img);
					}
					Validator.addEvent(img,"mouseover",Validator.showErrorTip);
					Validator.addEvent(img,"mouseout",Validator.hideErrorTip);
				}
			}catch(e){}
		}
		if(/Group/.test(validateType)){
			var eles = document.getElementsByName(node.getAttribute('name'));
			for(var i=0;i<eles.length;i++){
				showError(eles[i]);
			}
		}else{
			showError(node);
		}
		Validator.ErrorItem[Validator.ErrorItem.length] = node;
		var text = '';
		if(obj.text)text = obj.text + ':';//兼容没有写text的写法
		Validator.ErrorMessage[Validator.ErrorMessage.length] = (Validator.ErrorMessage.length+1) + "." + text + errorTip;
	},
	addEvent : function(obj,event,fn){
		if(window.attachEvent){
			obj.attachEvent("on"+event,fn);
		}else{
			obj.addEventListener(event,fn,false);
		}
	},
	removeEvent : function(obj,event,fn){
		if(window.detachEvent){
			obj.detachEvent("on"+event,fn);
		}else{
			obj.removeEventListener(event,fn,false);
		}
	},
	getXY : function(node,tp){
		var x = node.offsetLeft;
		var y = node.offsetTop;
		var parent = node.offsetParent;
		while (parent != null&&(!tp||(parent!=tp))){
			x += parent.offsetLeft;
			y += parent.offsetTop;
			parent = parent.offsetParent;
		}
		return {x: x, y: y};
	},
	getCNum : function(n){
		if(!Validator.cNum)
			Validator.cNum = ['①','②','③','④','⑤','⑥','⑦','⑧','⑨','⑩','⑾','⑿','⒀','⒁','⒂','⒃','⒄','⒅','⒆','⒇'];
		if(typeof n != 'number' || n > Validator.cNum.length-1)n=0;
		return Validator.cNum[n];
	},
	getErrorMsg : function(obj,v){
		var msg = '';
		switch(v){
			case 'Time' :
				var format = obj.format;
				format = typeof format != 'string' ? "HH:mm:ss" : format;
				if(obj.theValue){
					var opt = obj.opt ? obj.opt : 'eq';
					var eqs = {eq:'=',neq:'!=',gt:'>',gteq:'>=',lt:'<',lteq:'<='};
					msg = '必须为 '+ format +' 格式的时间,<br>并且必须 ' + eqs[opt] + ' ' + obj.theValue.replace('new Date()','当前时间') + ' !';
				}else{
					msg = '必须为 '+ format +' 格式的时间!';
				}
				break;
			case 'Date' :
				var format = obj.format;
				format = typeof format != 'string' ? "yyyy-MM-dd" : format;
				if(obj.theValue){
					var opt = obj.opt ? obj.opt : 'eq';
					var eqs = {eq:'=',neq:'!=',gt:'>',gteq:'>=',lt:'<',lteq:'<='};
					msg = '必须为 '+ format +' 格式的日期,<br>并且必须 ' + eqs[opt] + ' ' + obj.theValue.replace('new Date()','当前时间') + ' !';
				}else{
					msg = '必须为 '+ format +' 格式的日期!';
				}
				break;
			case 'DateTime' :
				var format = obj.format;
				format = typeof format != 'string' ? "yyyy-MM-dd HH:mm:ss" : format;
				if(obj.theValue){
					var opt = obj.opt ? obj.opt : 'eq';
					var eqs = {eq:'=',neq:'!=',gt:'>',gteq:'>=',lt:'<',lteq:'<='};
					msg = '必须为 '+ format +' 格式的日期时间,<br>并且必须 ' + eqs[opt] + ' ' + obj.theValue.replace('new Date()','当前时间') + ' !';
				}else{
					msg = '必须为 '+ format +' 格式的日期时间!';
				}
				break;
			case 'Double' :
				if(obj.precise){
					msg = '必须为 '+ obj.precise +' 格式的浮点数!';
					break;
				}else{
					msg = Validator.DefaultErrorMsg[v];
					break;
				}
			case 'Compare' :
				if(obj.theValue){
					var opt = obj.opt ? obj.opt : 'eq';
					var eqs = {eq:'=',neq:'!=',gt:'>',gteq:'>=',lt:'<',lteq:'<='};
					msg = '输入的值必须 '+ eqs[opt] +' ' + obj.theValue + '!';
					break;
				}else{
					msg = Validator.DefaultErrorMsg[v];
					break;
				}
			case 'Range' :
				if(obj.max){
					var min = obj.min ? obj.min:0;
					msg = '输入的值必须在 ['+ min +','+obj.max+'] 区间!';
					break;
				}else{
					msg = Validator.DefaultErrorMsg[v];
					break;
				}
			case 'Limit' :
				if(obj.max){
					msg = '输入的内容不能超过 '+obj.max+' 个字符!';
					break;
				}else{
					msg = Validator.DefaultErrorMsg[v];
					break;
				}
			case 'LimitB' :
				if(obj.max){
					msg = '输入的内容不能超过 '+obj.max+' 个字节!';
					break;
				}else{
					msg = Validator.DefaultErrorMsg[v];
					break;
				}
			case 'Filter' :
				if(obj.accept){
					msg = '所选文件必须为 '+obj.accept+' 的格式!';
					break;
				}else{
					msg = Validator.DefaultErrorMsg[v];
					break;
				}
			default:
				msg = Validator.DefaultErrorMsg[v];
				break;
		}
		return msg;
	},
	showErrorTip : function(node){
		node = window.event ? window.event.srcElement : node.target;
		var xy = Validator.getXY(node);
		Validator.ErrorTip.innerHTML = node.getAttribute('errorTip');
		Validator.ErrorTip.style.display = 'block';
		if(document.body.offsetWidth<xy.x+Validator.ErrorTip.offsetWidth+100)
			Validator.ErrorTip.style.left=xy.x - Validator.ErrorTip.offsetWidth -20;
		else
			Validator.ErrorTip.style.left=xy.x+20;
		if(document.body.offsetHeight<xy.y+Validator.ErrorTip.offsetHeight+100)
			Validator.ErrorTip.style.top=xy.y - Validator.ErrorTip.offsetHeight -20;
		else
			Validator.ErrorTip.style.top=xy.y+20;
		//alert("页面宽度："+document.body.offsetWidth+"，提示位置left:"+Validator.ErrorTip.style.left+"，提示宽度："+Validator.ErrorTip.offsetWidth+"\n"+"页面高度："+document.body.offsetHeight+"，提示位置top:"+Validator.ErrorTip.style.top+"，提示高度："+Validator.ErrorTip.offsetHeight);
	},
	hideErrorTip : function(){
 		Validator.ErrorTip.style.display = 'none';
 	},
 	showInfoTip : function(node){
		node = window.event ? window.event.srcElement : node.target;
		var xy = Validator.getXY(node);
		Validator.InfoTip.innerHTML = Validator.getCache(node.name||node.id).infoTip;
		Validator.InfoTip.style.display = 'block';
		Validator.InfoTip.style.left=xy.x;
		Validator.InfoTip.style.top=xy.y-Validator.InfoTip.offsetHeight;
	},
	hideInfoTip : function(node){
 		Validator.InfoTip.style.display = 'none';
 		Validator.vd(window.event ? window.event.srcElement : node.target);
 	},
	isDouble : function(input,precise){
		if(typeof precise == "undefined" || !((/^[#]+\.[#]+$/).test(precise)))
			return (/^[-\+]?\d+(\.\d+)?$/).test(input);
		var n = precise.split(".")[0].length;
		var m = precise.split(".")[1].length;
		return new RegExp("^[-\\+]?\\d{1,"+n+"}(\\.\\d{1,"+m+"})?$").test(input);
	},
	limit : function(len,min, max){
		min = typeof min == 'undefined' || min == 'undefined' ? 0 : parseInt(min,10);
		max = typeof max == 'undefined' || max == 'undefined' ? Number.MAX_VALUE : parseInt(max,10);
		return min <= len && len <= max;
	},
	lenB : function(str){
		return str.replace(/[^\x00-\xff]/g,"**").length;
	},
	exec : function(op, reg){
		if(typeof reg === 'undefined')
			return true;
		return new RegExp(reg,"g").test(op);
	},
	compare : function(op1,opt,op2){
		if(Validator.isDouble(op1)&&Validator.isDouble(op2)){
			var eqs = {eq:'==',neq:'!=',gt:'>',gteq:'>=',lt:'<',lteq:'<='};
			opt = eqs[opt]||'==';
			return eval("parseFloat("+op1+",10) " + opt + " parseFloat("+op2+",10)");
		}else{
			return false;
		}
	},
	mustChecked : function(name, min, max){
		var groups = document.getElementsByName(name);
		var hasChecked = 0;
		min = typeof min == 'undefined' || min == 'undefined' ? 1 : parseInt(min,10);
		max = typeof max == 'undefined' || max == 'undefined' ? groups.length : parseInt(max,10);
		for(var i=groups.length-1;i>=0;i--)
			if(groups[i].checked) hasChecked++;
		return min <= hasChecked && hasChecked <= max;
	},
	doFilter : function(input, filter){
		if(typeof filter === 'undefined')
			return true;
		return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, filter.split(/\s*,\s*/).join("|")), "gi").test(input);
	},
	isIdCard : function(number){
		var date, Ai;
		var verify = "10X98765432";
		var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
		var area = ['','','','','','','','','','','','北京','天津','河北','山西','内蒙古','','','','','','辽宁','吉林','黑龙江','','','','','','','','上海','江苏','浙江','安微','福建','江西','山东','','','','河南','湖北','湖南','广东','广西','海南','','','','重庆','四川','贵州','云南','西藏','','','','','','','陕西','甘肃','青海','宁夏','新疆','','','','','','台湾','','','','','','','','','','香港','澳门','','','','','','','','','国外'];
		var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[X\d])))$/i);
		if(re == null) return false;
		if(re[1] >= area.length || area[re[1]] == "") return false;
		if(re[2].length == 12){
			Ai = number.substr(0, 17);
			date = [re[9], re[10], re[11]].join("-");
		}
		else{
			Ai = number.substr(0, 6) + "19" + number.substr(6);
			date = ["19" + re[4], re[5], re[6]].join("-");
		}
		if(!Validator.isDate(date, "yyyy-MM-dd")) return false;
		var sum = 0;
		for(var i = 0;i<=16;i++){
			sum += Ai.charAt(i) * Wi[i];
		}
		number = number.substr(0,number.length-1) + number.substr(number.length-1,number.length).toUpperCase();
		Ai +=  verify.charAt(sum%11).toUpperCase();
		return (number.length ==15 || number.length == 18 && number == Ai);
	},
	isTime : function(op, format,opt,theValue){//format支持"HH时mm分ss秒"，默认为"HH:mm:ss"
		var time = Validator.parseTime(op,format);
		if(time==null)return false;
		var h = time.h;
		var m = time.m;
		var s = time.s;
		var b = ((h>=0&&h<24)&&(m>=0&&m<60)&&(s>=0&&s<60))||(h==24&&m==0&&s==0);
		if(b&&theValue&&opt){//如果是正确的时间格式，并且需要比较时间大小
			theValue = theValue == 'new Date()' ? Validator.formatDate(new Date(),format) : theValue;
			var theTime = Validator.parseTime(theValue,format);//指定值的时间
			if(theTime==null){
				b = false;
			}else{
				var theH = theTime.h;
				var theM = theTime.m;
				var theS = theTime.s;
				var theDate = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDay(),theH,theM,theS);
				var date = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDay(),h,m,s);
				var eqs = {eq:'==',neq:'!=',gt:'>',gteq:'>=',lt:'<',lteq:'<='};
				opt = eqs[opt]||'==';
				b = eval(date.getTime()+opt+theDate.getTime());
			}
		}
        return b;
	},
	parseTime : function(op,format){//返回分隔的时间，format支持"HH时mm分ss秒"，默认为"HH:mm:ss"
		format = typeof format != 'string' ? "HH:mm:ss" : format;
		var o = null, h, m, s;
		switch(format){
			case "HH:mm:ss" :
				if(op.length==="HH:mm".length)op+=":00";
				o = op.match(new RegExp("^(\\d{1,2}):(\\d{1,2}):(\\d{1,2})$"));
				break;
			case "HH时mm分ss秒" :
				if(op.length==="HH时mm分".length)op+="00秒";
				o = op.match(new RegExp("^(\\d{1,2})时(\\d{1,2})分(\\d{1,2})秒$"));
				break;
			default :
				break;
		}
		if(o == null ) return null;
		h = parseInt(o[1],10);
		m = parseInt(o[2],10);
		s = parseInt(o[3],10);
		return {h:h,m:m,s:s};
	},
	isDate : function(op, format,opt,theValue){//format支持"yyyy/MM/dd"、"yyyy.MM.dd"、"yyyy年MM月dd日"，默认为"yyyy-MM-dd"
		var date = Validator.parseDate(op,format);
		if(date==null)return false;
		var day = date.day;
		var month = date.month;
		var year =  date.year;
		date = new Date(year, month-1, day);
		var b = (typeof(date) == "object" && year == date.getFullYear() && month == (date.getMonth()+1) && day == date.getDate());
		if(b&&theValue&&opt){//如果是正确的日期格式，并且需要比较日期大小
			theValue = theValue == 'new Date()' ? Validator.formatDate(new Date(),format) : theValue;
			var theDate = Validator.parseDate(theValue,format);//指定值的日期
			if(theDate==null){
				b = false;
			}else{
				var theDay = theDate.day;
				var theMonth = theDate.month;
				var theYear =  theDate.year;
				theDate = new Date(theYear, theMonth-1, theDay);
				var eqs = {eq:'==',neq:'!=',gt:'>',gteq:'>=',lt:'<',lteq:'<='};
				opt = eqs[opt]||'==';
				b = eval(date.getTime()+opt+theDate.getTime());
			}
		}
        return b;
	},
	parseDate : function(op,format){//返回分隔的日期，format支持"yyyy/MM/dd"、"yyyy.MM.dd"、"yyyy年MM月dd日"，默认为"yyyy-MM-dd"
		format = typeof format != 'string' ? "yyyy-MM-dd" : format;
		var m = null, year, month, day;
		switch(format){
			case "yyyy-MM-dd" :
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))-(\\d{1,2})-(\\d{1,2})$"));
				break;
			case "yyyy/MM/dd" :
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))/(\\d{1,2})/(\\d{1,2})$"));
				break;
			case "yyyy.MM.dd" :
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))\\.(\\d{1,2})\\.(\\d{1,2})$"));
				break;
			case "yyyy年MM月dd日" :
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))年(\\d{1,2})月(\\d{1,2})日$"));
				break;
			default :
				break;
		}
		if(m == null ) return null;
		day = parseInt(m[5],10);
		month = parseInt(m[4],10);
		year =  parseInt((m[1].length == 4) ? m[1] : GetFullYear(parseInt(m[1], 10)),10);
		month = month==0 ?12:month;
		return {year:year,month:month,day:day};
		function GetFullYear(y){return ((y<30 ? "20" : "19") + y)|0;}
	},
	isDateTime: function(op, format,opt,theValue){//format支持"yyyy/MM/dd HH:mm:ss"、"yyyy.MM.dd HH:mm:ss"、"yyyy年MM月dd日 HH时mm分ss秒"，默认为"yyyy-MM-dd HH:mm:ss"
		format = typeof format != 'string' ? "yyyy-MM-dd HH:mm:ss" : format;
		var opArr = op.split(' ');
		var formatArr = format.split(' ');
		if(opArr.length != 2 || formatArr.length != 2)return false;
		var b = false;
		if(theValue&&opt){
			try{
				theValue = theValue == 'new Date()' ? Validator.formatDate(new Date(),format) : theValue;
				var theValueArr = theValue.split(' ');
				if(theValueArr.length != 2){
					b = false;
				}else{
					var date = Validator.parseDate(opArr[0],formatArr[0]);
					var day = date.day;
					var month = date.month;
					var year =  date.year;
					var time = Validator.parseTime(opArr[1],formatArr[1]);
					var h = time.h;
					var m = time.m;
					var s = time.s;
					var dateTime = new Date(year, month-1, day,h,m,s);

					var theDate = Validator.parseDate(theValueArr[0],formatArr[0]);
					var theDay = theDate.day;
					var theMonth = theDate.month;
					var theYear =  theDate.year;
					var theTime = Validator.parseTime(theValueArr[1],formatArr[1]);
					var theH = theTime.h;
					var theM = theTime.m;
					var theS = theTime.s;
					var theDateTime = new Date(theYear, theMonth-1, theDay,theH,theM,theS);

					var eqs = {eq:'==',neq:'!=',gt:'>',gteq:'>=',lt:'<',lteq:'<='};
					opt = eqs[opt]||'==';
					b = eval(dateTime.getTime()+opt+theDateTime.getTime());
				}
			}catch(ex){b = false;}
		}else{
			b = Validator.isDate(opArr[0],formatArr[0])&& Validator.isTime(opArr[1],formatArr[1]);
		}
		return b;
	},
	formatDate : function(date,format){
		format = typeof format != 'string' ? "yyyy-MM-dd HH:mm:ss" : format;
	   	var o = {
			"M+" : date.getMonth()+1, //month
			"d+" : date.getDate(),    //day
			"H+" : date.getHours(),   //hour
			"m+" : date.getMinutes(), //minute
			"s+" : date.getSeconds(), //second
			"q+" : Math.floor((date.getMonth()+3)/3), //quarter
			"S" : date.getMilliseconds() //millisecond
	   	}
	   	if(/(y+)/.test(format))
	   		format=format.replace(RegExp.$1,(date.getFullYear()+"").substr(4 - RegExp.$1.length));
	   	for(var k in o)
	   		if(new RegExp("("+ k +")").test(format))
	    		format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] :  ("00"+ o[k]).substr((""+ o[k]).length));
	   	return format;
	}
 }
