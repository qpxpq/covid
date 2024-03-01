<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.info.common.staticvalue.StaticValue"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/common/jsp/com.jsp" %>

<html>
	<head>
		<title>导入文件</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
		<script type="text/javascript" src="<%=baseURL%>/view/common/js/ajaxfileupload.js"></script>
		
	</head>

	<script type="text/javascript" language="javascript">
	
	$(function(){
	});
	
	// 判断上传文件格式
	function checkFile(file,type){
		var path = file[0].value;
		var suffix="";
		if(path.indexOf(".")!=-1){
			suffix =path.substring(path.lastIndexOf(".")+1);//获得后缀
		}
		type=type.toLowerCase();
		if(type!=suffix.toLowerCase()){//长度为0 或者 后缀不是pdf
			parent.parent.alert('请选择'+type+'文件');
	          return false;
		}
		return true;
	}
	
	function uploadFile(){
	   var file = $("#file");
	   var myurl=baseURL+"/importLeadership.do";
	   if(checkFile(file,'xls')){
		   jQuery.ajaxFileUpload({ 
			    url:myurl,           
				secureuri:false, 
				fileElementId:'file',// --上传文件对应的文本域的id
				dataType: 'json',      
				success: function (data){
					if(data.result){
						alert(data.msg);
						window.opener.location.reload();
						window.close();
					}else{
						alert(data.msg);
					}
				}
		   })
	   }
	}
	
	function closeWin(){
		window.close();
	}
	</script>


<body>

<!-- <div id="demo"> -->
<form method="post" action="<%=baseURL%>/uploadPdfToService.do" enctype="multipart/form-data">
	<TABLE width="100%" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
		<TR>
			<TD colspan="2" bgcolor="#FFFFFF" align="center" style="HEIGHT: 25px">Excl导入</TD>
		</TR>
		<TR>
				<TD class="gridtitle" bgcolor="#FFFFFF" style="HEIGHT: 25px">选择文件：</TD>
				<TD class="gridbody" bgcolor="#FFFFFF" style="HEIGHT: 25px">
					<input type="file" name="file" size="35"  id="file"/>  
				</TD>
		</TR>		
		<TR>
			<TD colspan="2" class="gridbody" bgcolor="#FFFFFF" style="HEIGHT: 35px">
			<center>
					<input type="button"  value="上传"  onclick="javascript:uploadFile()" class="gdcn-form-button-E2" style="height: 25px;"></input>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button"  value="取消"  onclick="javascript:closeWin()" class="gdcn-form-button-E2" style="height: 25px;"</input>
			</center>
			</TD>
		</TR>
	</TABLE>
</form>


</body>

</html>
