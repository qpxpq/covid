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
<title>集中隔离管理</title>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/fullcalendar.min.css"/>
<link href="<%=baseURL %>/asset/css/style.css" rel="stylesheet">
<info:verifyField verifyFile="disinfect.xml" />
<script type="text/javascript">
	$(function(){
		$("#addDisinfectButton").click(function(){
			if(!Validator.vd()) return;
			var resourceId = $("#resourceId").val();
			if(resourceId == "" || resourceId == null){
				alert("新增成功");
				$("#addDisinfect").attr("action",baseURL + "/addDisinfect.do");
			} else {
				alert("更新成功");
				$("#addDisinfect").attr("action",baseURL + "/updateDisinfect.do");
			}
			$("#addDisinfect").submit();
		});
		
		
		//调用选择页面
		$("#selectStudent").click(function(){
			var returnValue = window.showModalDialog("<%=baseURL%>/listSelectStudent.do","","dialogHeight:600px;dialogWidth:1200px");
			if (returnValue!=null) {
				$("#resourceId").val(returnValue.resourceId);
				$("#resourceId").val(returnValue.resourceId);
		 	}
		});
		
		$("#returnListButton").click(function(){
			window.location.href = baseURL + "/listDisinfect.do";
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
  <form method="post"   id="addDisinfect" >
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
	                  <label class="col-sm-2 control-label text-right">手机号</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="disinfectName" id="disinfectName" value='${pageEntity.disinfectName }'
				         		placeholder="请输入手机号" readonly="readonly"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">身份证号</label>
	                  <div class="col-sm-10">
						 <textarea readonly="readonly" style="height:80px;width:310px;border:solid 1px #ddd;color: rgb(204, 204, 204);" id="disinfectContext" name="disinfectContext">${pageEntity.disinfectContext }</textarea>
	                  </div>
	                </div>
						<input type="hidden" name="userId" id="userId" value='${pageEntity.userId }' />
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">姓名</label>
	                  <div class="col-sm-10">
	                    <input type="text" readonly="readonly" name="userName" id="userName" value='${pageEntity.userName }'
				         		placeholder="请输入姓名"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
						<input type="hidden" name="authorId" id="authorId" value='${workSession.resourceId }' />
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">审核人</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="authorName" id="authorName" value='${workSession.workName }'
				         		placeholder="请输入审核人"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">回复内容</label>
	                  <div class="col-sm-10">
						 <textarea style="height:80px;width:310px;border:solid 1px #ddd;color: rgb(204, 204, 204);" id="authorContext" name="authorContext">${pageEntity.authorContext }</textarea>
	                  </div>
	                </div>
						
						<input type="hidden" name="disinfectType" id="disinfectType" value='已处理' />
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
              <button id="addDisinfectButton" class="submit btn btn-danger" type="button">保存</button>
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
