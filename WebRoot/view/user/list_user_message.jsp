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
<script type="text/javascript">
	function delteUser(id){
		alert("删除成功");
		var url = baseURL + "/delteUser.do?id=" + id;
		window.location.href = url;
	}
	
	//模板下载
	function download(){
		var fileName = encodeURI(encodeURI("学生导入模板.xls"));
		window.open(baseURL+'/downloadModel.do?file='+fileName+'&folder=file');
	}
	
	function importExcl(){
		var url = baseURL + "/view/user/import_user.jsp"
		window.open(url, "_blank","height=200, width=600, top=200, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	}
	
	function addPage(){
		var url = baseURL + "/view/user/add_user.jsp";
		window.location.href = url;
	}
</script>
</head>

<body id="mimin" class="dashboard">
<!-- start: Header -->
<jsp:include page="../../top.jsp"/>
<!-- end: Header -->
<div class="container-fluid mimin-wrapper"> 
  <!-- start:Left Menu -->
  <jsp:include page="../../left.jsp"/>

  <!-- end: Left Menu --> 
  
  <!-- start: content -->
  <form  action="listUserMessage.do" method="post" id="splitPageForm">
  <div id="content">
    <div class="panel">
      <div class="panel-body">
        <div class="col-md-12">
          <h3 class="animated fadeInLeft">用户管理管理</h3>
        </div>
      </div>
    </div>
    <div class="col-md-12 padding-0 form-element">
      <div class="col-md-12">
       <div class="panel form-element-padding">
            <div class="panel-heading">
              <h4>查询条件</h4>
            </div>
            <div class="panel-body" style="padding-bottom:30px;" id="searchTable">
                <div class="form-group">
			 <label class="col-sm-1 control-label text-right">姓名：</label>
			 <div class="col-sm-2">
			 	<input type="text" id="userName" name="userName" value="${searchList.userName }" 
          	 			class="text_add" style=" width:120px"  />
             </div>
	      <label class="col-sm-1 control-label text-right">性别：</label>
	      <div class="col-sm-2">
	      <info:dropDown dictionaryType="sex" name="sex"  defaultValue="${searchList.sex }" 
	         	styleClass="height:40px;width:120px;border:solid 1px #ddd;color: rgb(204, 204, 204);"/>
	  	  </div>
	      <label class="col-sm-2 control-label text-right">居民状态：</label>
	      <div class="col-sm-2">
	      <info:dropDown dictionaryType="userType" name="userType"  defaultValue="${searchList.userType }" 
	         	styleClass="height:40px;width:120px;border:solid 1px #ddd;color: rgb(204, 204, 204);"/>
	  	  </div>
                  
                  
                  <div class="col-sm-6">
                    <input class="submit btn btn-danger" type="submit" value="查询" />
                  </div>
                  
                  <!--
    <input type="button" onclick="download()" value="模板下载" class="submit btn btn-danger"/>
    <input type="button" onclick="ev_export()" value="导出" class="submit btn btn-danger"/>
    <input type="button" onclick="importExcl()" value="导入" class="submit btn btn-danger"/>   -->
                  
                </div>
            </div>
          </div>
        <div class="panel">
          <div class="panel-heading">
            <h3>用户管理列表</h3>
          </div>
          <div class="panel-body">
            <div class="responsive-table">
              <div id="datatables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="row">
                  <div class="col-sm-12">
                    <table style="font-size: 10px;" class="table table-striped table-bordered dataTable no-footer" width="100%" cellspacing="0"  style ="width: 100%;">
                      <thead>
                        <tr role="row">
                          <th  class="sorting" width="2%">序号</th>
						  <th  class="sorting" width="4%">姓名</th>
						  <th  class="sorting" width="7%">电话</th>
						  <th  class="sorting" width="7%">第一针接种时间</th>
						  <th  class="sorting" width="7%">第一针接种状态</th>
						  <th  class="sorting" width="7%">第二针剩余时间</th>
						  <th  class="sorting" width="7%">第二针接种时间</th>
						  <th  class="sorting" width="7%">第二针接种状态</th>
						  <th  class="sorting" width="7%">第三针剩余时间</th>
						  <th  class="sorting" width="7%">第三针接种时间</th>
						  <th  class="sorting" width="7%">第三针接种状态</th>
						  <th  class="sorting" width="7%">操作</th>
s
						  <th  class="sorting" width="8%">操作</th>
						  <th  class="sorting" width="8%">操作</th>
						  <th  class="sorting" width="8%">操作</th>

                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${pagerList.pageList }" varStatus="status" var="listObject" >
					       <tr role="row" class="even">
					        	<td class="center">${status.index + 1 }</td>
 								<td class="center">${listObject.userName }</td> 
				        		
				        		
				        		
				        		
				        		<td class="center">${listObject.mobile }</td>
				        		<!-- <td class="center">${listObject.resourceId }</td> -->
				        		
				        		<td class="center"><fmt:formatDate value="${listObject.oneDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				        		<td class="center">${listObject.oneType }</td>
				        		<td class="center">${listObject.oneTime }</td>
				        		<td class="center"><fmt:formatDate value="${listObject.twoDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				        		<td class="center">${listObject.twoType }</td>
				        		<td class="center">${listObject.twoTime }</td>
				        		<td class="center"><fmt:formatDate value="${listObject.threeDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				        		<td class="center">${listObject.threeType }</td>
				        		 
				        		
 
				        		<td class="center"><a href="<%=baseURL %>/addNews.do?userId=${listObject.resourceId}&id=a" title="发送消息" class="link_icon">正常通行</a></td>
				        		<td class="center"><a href="<%=baseURL %>/addNews.do?userId=${listObject.resourceId}&id=b" title="发送消息" class="link_icon">落地核酸</a></td>
				        		<td class="center"><a href="<%=baseURL %>/addNews.do?userId=${listObject.resourceId}&id=c" title="发送消息" class="link_icon">居家隔离</a></td>
				        		<td class="center"><a href="<%=baseURL %>/addNews.do?userId=${listObject.resourceId}&id=d" title="发送消息" class="link_icon">集中隔离</a></td>
					            
					       </tr>
					      </c:forEach>
					      <tr>
					      	<td colspan="20" class="center"> <info:splitPage pageIndex='${pagerList.pageIndex }' pageSize="<%=StaticValue.pageSize %>" pageCount='${pagerList.totalRecord }' formName="splitPageForm" /></td>
					      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </form>
  <!-- end: content --> 
</div>
<script src="<%=baseURL %>/asset/js/jquery.min.js"></script> 
<script src="<%=baseURL %>/asset/js/jquery.ui.min.js"></script> 
<script src="<%=baseURL %>/asset/js/bootstrap.min.js"></script> 
<script src="<%=baseURL %>/asset/js/plugins/jquery.nicescroll.js"></script> 
<script>
$("#left-menu-2").click() ;
</script>
<script type="text/javascript">
function ev_export(){
	var action = baseURL+"/exportUser.do";
    var form = $("<form></form>");
    form.attr("method","post");
    form.attr("id","exprotExclForm");
    form.attr("action",action);
    $("#searchTable").find('input').each(function(){
		if(this.type.toUpperCase() != 'BUTTON' && this.type.toUpperCase() != 'RADIO'){
			var name = (this.name||this.id);
			if(name != null && name != ""){
				var input1 = $("<input type='hidden' name='"+ name +"' />");
				input1.attr("value",this.value);
				form.append(input1);
			}
		}
	});
	
    $("#searchTable").find('select').each(function(){
		if(this.type.toUpperCase() != 'BUTTON') {
			var name = (this.name||this.id);
			if(name != null && name != ""){
				var input1 = $("<input type='hidden' name='"+ name +"' />");
				input1.attr("value",this.value);
				form.append(input1);
				//select下拉框多拼接一个参数给查询条件展示。name的规格为    name_dicValue
				if(this.value != null && this.value != ""){//若选择值为null "" 。说明该选项未选值，则不需要生成字段选项值。
					var input2 = $("<input type='hidden' name='"+ name +"_dicValue' />");
					var value = $("#"+ name +"").find("option:selected").text();
					input2.attr("value",value);
					form.append(input2);
				}
				
			}
		}
	});
	var input1 = $("<input type='hidden' name='appType' />");
	input1.attr("value",obj);//传入对应的业务类型用于查找对应的打印
	form.append(input1);
	
    form.appendTo("body");
    form.css("display","none");
    form.submit();
}
</script>
</body>
</html>