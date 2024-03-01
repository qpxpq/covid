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
<title>用户管理管理</title>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/fullcalendar.min.css"/>
<link href="<%=baseURL %>/asset/css/style.css" rel="stylesheet">
<info:verifyField verifyFile="user.xml" />
<script type="text/javascript">
	$(function(){
		$("#addUserButton").click(function(){
			if(!Validator.vd()) return;
			var resourceId = $("#resourceId").val();
			if(resourceId == "" || resourceId == null){
				alert("新增成功");
				$("#addUser").attr("action",baseURL + "/addUser.do");
			} else {
				alert("更新成功");
				$("#addUser").attr("action",baseURL + "/updateUser.do");
			}
			$("#addUser").submit();
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
			window.location.href = baseURL + "/listUser.do";
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
  <form method="post"   id="addUser" >
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
	                  <label class="col-sm-2 control-label text-right">账号</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="userNo" id="userNo" value='${pageEntity.userNo }'
				         		placeholder="请输入账号"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">密码</label>
	                  <div class="col-sm-10">
	                    <input type="password" name="password" id="password" value='${pageEntity.password }'
				         		placeholder="请输入密码"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">姓名</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="userName" id="userName" value='${pageEntity.userName }'
				         		placeholder="请输入姓名"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">性别</label>
	                  <div class="col-sm-10">
	                    <info:dropDown  dictionaryType="sex" name="sex" 
        									defaultValue='${pageEntity.sex }' styleClass="height:40px;width:345px;border:solid 1px #ddd;color: rgb(204, 204, 204);"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">电话</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="mobile" id="mobile" value='${pageEntity.mobile }'
				         		placeholder="请输入电话"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">地址</label>
	                  <div class="col-sm-10">
	                    <input type="text" name="address" id="address" value='${pageEntity.address }'
				         		placeholder="请输入地址"  class="col-xs-10 col-sm-5"  />
	                  </div>
	                </div>
					<div class="form-group">
	                  <label class="col-sm-2 control-label text-right">居民状态</label>
	                  <div class="col-sm-10">
	                    <info:dropDown  dictionaryType="userType" name="userType" 
        									defaultValue='${pageEntity.userType }' styleClass="height:40px;width:345px;border:solid 1px #ddd;color: rgb(204, 204, 204);"  />
	                  </div>
	                </div>
	                    <input type="hidden" name="oneDate" id="oneDate" value='${pageEntity.oneDate }'
				         		placeholder="请输入第一针"  class="col-xs-10 col-sm-5"  />
	                    <input type="hidden" name="oneType" id="oneType" value='${pageEntity.oneType }'
				         		placeholder="请输入一状态"  class="col-xs-10 col-sm-5"  />
	                    <input type="hidden" name="oneTime" id="oneTime" value='${pageEntity.oneTime }'
				         		placeholder="请输入一间隔"  class="col-xs-10 col-sm-5"  />
	                    <input type="hidden" name="twoDate" id="twoDate" value='${pageEntity.twoDate }'
				         		placeholder="请输入第二针"  class="col-xs-10 col-sm-5"  />
	                    <input type="hidden" name="twoType" id="twoType" value='${pageEntity.twoType }'
				         		placeholder="请输入二状态"  class="col-xs-10 col-sm-5"  />
	                    <input type="hidden" name="twoTime" id="twoTime" value='${pageEntity.twoTime }'
				         		placeholder="请输入二间隔"  class="col-xs-10 col-sm-5"  />
	                    <input type="hidden" name="threeDate" id="threeDate" value='${pageEntity.threeDate }'
				         		placeholder="请输入第三针"  class="col-xs-10 col-sm-5"  />
	                    <input type="hidden" name="threeType" id="threeType" value='${pageEntity.threeType }'
				         		placeholder="请输入三状态"  class="col-xs-10 col-sm-5"  />
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
              <button id="addUserButton" class="submit btn btn-danger" type="button">保存</button>
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