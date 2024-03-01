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
<title>流动管理管理</title>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/fullcalendar.min.css"/>
<link href="<%=baseURL %>/asset/css/style.css" rel="stylesheet">
<info:verifyField verifyFile="flow.xml" />
<script type="text/javascript">
	$(function(){
		$("#addFlowButton").click(function(){
			if(!Validator.vd()) return;
			var resourceId = $("#resourceId").val();
			if(resourceId == "" || resourceId == null){
				alert("新增成功");
				$("#addFlow").attr("action",baseURL + "/addFlow.do");
			} else {
				alert("更新成功");
				$("#addFlow").attr("action",baseURL + "/updateFlow.do");
			}
			$("#addFlow").submit();
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
			window.location.href = baseURL + "/listFlow.do";
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
  <form method="post"   id="addFlow" >
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
	                  <label class="col-sm-2 control-label text-right">人员名称</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="name" id="name" value='${pageEntity.name }'
				         		placeholder="请输入人员名称"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">联系电话</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="mobile" id="mobile" value='${pageEntity.mobile }'
				         		placeholder="请输入联系电话"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">联系地址</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="address" id="address" value='${pageEntity.address }'
				         		placeholder="请输入联系地址"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">户主名</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="hz" id="hz" value='${pageEntity.hz }'
				         		placeholder="请输入户主名"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">户主手机号</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="hzMobile" id="hzMobile" value='${pageEntity.hzMobile }'
				         		placeholder="请输入户主手机号"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">入住日期</label>
	                  <div class="col-sm-10">
	                  	
	                    <input type="text" name="flowDate"  id="flowDate" onclick="WdatePicker()" id="endDate" value='<fmt:formatDate value="${pageEntity.flowDate }" pattern="yyyy-MM-dd HH:mm:ss" />'
				         		placeholder="请输入入住日期"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">状态</label>
	                  <div class="col-sm-10">
	                  	<info:dropDown  dictionaryType="flowType" name="flowType" 
        									defaultValue='${pageEntity.flowType }' styleClass="height:40px;width:345px;border:solid 1px #ddd;color: rgb(204, 204, 204);"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">单元号</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="unitNo" id="unitNo" value='${pageEntity.unitNo }'
				         		placeholder="请输入单元号"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">备注</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="flowContext" id="flowContext" value='${pageEntity.flowContext }'
				         		placeholder="请输入备注"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
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
              <button id="addFlowButton" class="submit btn btn-danger" type="button">保存</button>
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