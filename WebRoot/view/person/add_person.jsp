<%@page import="com.info.common.staticvalue.StaticValue"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/common/jsp/com.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="description" content="xxxxx">
<meta name="author" content="xxxxx">
<meta name="keyword" content="xxxxx">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>xxxxx</title>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/fullcalendar.min.css"/>
<link href="<%=baseURL %>/asset/css/style.css" rel="stylesheet">
            <info:verifyField verifyFile="person.xml" />
<script type="text/javascript">
	$(function(){
		$("#addPersonButton").click(function(){
			if(!Validator.vd()) return;
			var personId = $("#personId").val();
			if(personId == "" || personId == null){
				alert("新增成功");
				$("#addPerson").attr("action",baseURL + "/addPerson.do");
			} else {
				alert("更新成功");
				$("#addPerson").attr("action",baseURL + "/updatePerson.do");
			}
			$("#addPerson").submit();
		});
	});
</script>
</head>

<body id="mimin" class="dashboard">
<!-- start: Header -->
<jsp:include page="../../top.jsp" />
<!-- end: Header -->
<div class="container-fluid mimin-wrapper"> 
  
  <jsp:include page="../../left.jsp"/>
  <form method="post"   id="addPerson" >
  <!-- start: content -->
  <div id="content">
    <div class="panel">
      <div class="panel-body">
        <div class="col-md-12">
          <h3 class="animated fadeInLeft">详情页面</h3>
        </div>
      </div>
    </div>
    <div class="form-element">
      <div class="col-md-12 padding-0">
        <div class="col-md-12">
          <div class="panel form-element-padding">
            <div class="panel-heading">
              <h4>基本表单</h4>
            </div>
            <div class="panel-body" style="padding-bottom:30px;">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="col-sm-1 control-label text-right">登录名</label>
                  <div class="col-sm-11">
                    <input type="hidden" id="personId" name="personId" value="${person.personId }" />
	  				<input  type="text" id="personNo" placeholder="请输入登录名" class="col-xs-10 col-sm-5"  name="personNo" value="${person.personNo }">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-1 control-label text-right">密码</label>
                  <div class="col-sm-11">
                    <input  type="text" id="password" placeholder="请输入密码" class="col-xs-10 col-sm-5"  name="password" value="${person.password }">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-1 control-label text-right">姓名</label>
                  <div class="col-sm-11">
                    <input  type="text" id="personName" placeholder="请输入姓名" class="col-xs-10 col-sm-5"  name="personName" value="${person.personName }">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-1 control-label text-right">身份证</label>
                  <div class="col-sm-11">
                    <input  type="text" id="cardNo" placeholder="请输入身份证" class="col-xs-10 col-sm-5"  name="cardNo" value="${person.cardNo }">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-1 control-label text-right">电话</label>
                  <div class="col-sm-11">
                    <input  type="text" id="personMobile"  placeholder="请输入电话" class="col-xs-10 col-sm-5"  name="personMobile" value="${person.personMobile }">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-1 control-label text-right">性别</label>
                  <div class="col-sm-11">
                    <info:dropDown styleClass="height:40px;width:345px;border:solid 1px #ddd;color: rgb(204, 204, 204);" dictionaryType="sex" name="sex" defaultValue="${person.sex }"/>
                  </div>
                </div>
                
              </div>
            </div>
          </div>
          <div class="col-md-12 text-center" style="margin:20px;">
              <button id="addPersonButton" class="submit btn btn-danger" type="button">保存</button>
            </div>
        </div>
      </div>
    </div>
  </div>
  </form>
  <!-- end: content --> 
  
</div>

<!--弹出复选-->

<script src="<%=baseURL %>/asset/js/jquery.min.js"></script> 
<script src="<%=baseURL %>/asset/js/jquery.ui.min.js"></script> 
<script src="<%=baseURL %>/asset/js/bootstrap.min.js"></script> 
<script src="<%=baseURL %>/asset/js/plugins/jquery.nicescroll.js"></script> 
<script>
$("#left-menu-2").click() ;
</script>
</body>
</html>