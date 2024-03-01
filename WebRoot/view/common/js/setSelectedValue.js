//下拉框
function setSelectedValue(objId,objValue){
	var select = document.getElementById(objId); 
	for(var i=0; i<select.options.length; i++){  
        if(select.options[i].innerHTML == objValue){ 
            select.options[i].selected = true;  
            break;  
        }  
    } 
}

function setSelectedValue1(objId,objValue){
	var select = document.getElementById(objId); 
	for(var i=0; i<select.options.length; i++){  
        if(select.options[i].value == objValue){ 
            select.options[i].selected = true;  
            break;  
        }  
    } 
}

//单选按钮
function setCheckRadioSelected(objName,objValue){
	var obj = document.getElementsByName(objName);
	for(a=0; a<obj.length; a++){//遍历单选框
	   if(obj[a].value == objValue){
	   		obj[a].checked = "checked";
	   }
	}
}

//复选框
function setCheckSelected(objId,objValue) {
	var obj = document.getElementsByName(objId);
	for(var a=0;a<objValue.length;a++){
		var value = objValue[a];
		for(var b=0;b<obj.length;b++){
			if(obj[b].value == value){
				obj[b].checked = "checked";
			}
		}
	} 
}
