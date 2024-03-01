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
<title>疫苗预约管理</title>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/fullcalendar.min.css"/>
<link href="<%=baseURL %>/asset/css/style.css" rel="stylesheet">
<info:verifyField verifyFile="bespoke.xml" />
<script type="text/javascript">
	$(function(){
		$("#addBespokeButton").click(function(){
			if(!Validator.vd()) return;
			var resourceId = $("#resourceId").val();
			if(resourceId == "" || resourceId == null){
				alert("新增成功");
				$("#addBespoke").attr("action",baseURL + "/addBespoke.do");
			} else {
				alert("更新成功");
				$("#addBespoke").attr("action",baseURL + "/updateBespoke.do");
			}
			$("#addBespoke").submit();
		});
		
		
		//调用选择页面
		$("#selectVaccinum").click(function(){
			var returnValue = window.showModalDialog("<%=baseURL%>/listSelectVaccinum.do","","dialogHeight:600px;dialogWidth:1200px");
			if (returnValue!=null) {
				$("#vaccinumId").val(returnValue.resourceId);
				$("#vaccinumName").val(returnValue.vaccinumName);
				$("#vaccinumType").val(returnValue.vaccinumType);
		 	}
		});
		
		$("#returnListButton").click(function(){
			window.location.href = baseURL + "/listBespoke.do";
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
  <form method="post"   id="addBespoke" >
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
						<input type="hidden" name="vaccinumId" id="vaccinumId" value='${pageEntity.vaccinumId }' />
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">疫苗名称</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="vaccinumName" readonly="readonly" id="vaccinumName" value='${pageEntity.vaccinumName }'
				         		placeholder="请输入疫苗名称"  class="col-xs-10 col-sm-5"  />
				         		<button id="selectVaccinum" class="submit btn btn-danger" type="button">选择需要预约的疫苗</button>
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">疫苗针次</label>
	                  <div class="col-sm-10">
	                  	<input type="text" placeholder="请输入疫苗针次"  class="col-xs-10 col-sm-5"  name="vaccinumType" id="vaccinumType" value='${pageEntity.vaccinumType }' />
	                  </div>
	                </div>
						<input type="hidden" name="userId" id="userId" value='${userSession.resourceId }' />
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">用户名称</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="userName" readonly="readonly" id="userName" value='${userSession.userName }'
				         		placeholder="请输入用户名称"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
						
						<input type="hidden" name="accusationType" id="accusationType" value='预约中' />
						<input type="hidden" name="resourceId" id="resourceId" value='${pageEntity.resourceId }' />
						<input type="hidden" name="creatorId" id="creatorId" value='${pageEntity.creatorId }' />
						<input type="hidden" name="creatorName" id="creatorName" value='${pageEntity.creatorName }' />
						<input type="hidden" name="updaterId" id="updaterId" value='${pageEntity.updaterId }' />
						<input type="hidden" name="updaterName" id="updaterName" value='${pageEntity.updaterName }' />
						<input type="hidden" id="updateTime" name="updateTime" value="<fmt:formatDate value="${pageEntity.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" />" />
						<input type="hidden" id="createTime" name="createTime" value="<fmt:formatDate value="${pageEntity.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />" />
              </div>
            </div>
          </div>
          <div class="col-md-12 text-center" style="margin:20px;">
              <button id="addBespokeButton" class="submit btn btn-danger" type="button">保存</button>
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