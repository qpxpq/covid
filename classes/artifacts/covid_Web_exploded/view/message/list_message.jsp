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
<title>核酸检测咨询管理</title>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/fullcalendar.min.css"/>
<link href="<%=baseURL %>/asset/css/style.css" rel="stylesheet">
<script type="text/javascript">
	function delteMessage(id){
		alert("删除成功");
		var url = baseURL + "/delteMessage.do?id=" + id;
		window.location.href = url;
	}
	
	//模板下载
	function download(){
		var fileName = encodeURI(encodeURI("学生导入模板.xls"));
		window.open(baseURL+'/downloadModel.do?file='+fileName+'&folder=file');
	}
	
	function importExcl(){
		var url = baseURL + "/view/message/import_message.jsp"
		window.open(url, "_blank","height=200, width=600, top=200, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	}
	
	function addPage(){
		var url = baseURL + "/view/message/add_message.jsp";
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
  <form  action="listMessage.do" method="post" id="splitPageForm">
  <div id="content">
    <div class="panel">
      <div class="panel-body">
        <div class="col-md-12">
          <h3 class="animated fadeInLeft">核酸检测咨询管理</h3>
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
			 <label class="col-sm-2 control-label text-right">咨询标题：</label>
			 <div class="col-sm-2">
			 	<input type="text" id="messageName" name="messageName" value="${searchList.messageName }" 
          	 			class="text_add" style=" width:120px"  />
             </div>
			 <label class="col-sm-1 control-label text-right">咨询人：</label>
			 <div class="col-sm-2">
			 	<input type="text" id="userName" name="userName" value="${searchList.userName }" 
          	 			class="text_add" style=" width:120px"  />
             </div>
			 <label class="col-sm-1 control-label text-right">审核人：</label>
			 <div class="col-sm-2">
			 	<input type="text" id="authorName" name="authorName" value="${searchList.authorName }" 
          	 			class="text_add" style=" width:120px"  />
             </div>
             </div>
             <div class="form-group">
	      <label class="col-sm-1 control-label text-right">状态：</label>
	      <div class="col-sm-2">
	      <info:dropDown dictionaryType="messageType" name="messageType"  defaultValue="${searchList.messageType }" 
	         	styleClass="height:40px;width:100px;border:solid 1px #ddd;color: rgb(204, 204, 204);"/>
	  	  </div>
                  
                  
                  <div class="col-sm-4">
                    <input class="submit btn btn-danger" type="submit" value="查询" />
                    <c:if test="${role eq 'user' }">
                    <button type="button" onclick="addPage()" class="submit btn btn-danger">添加</button></c:if>
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
            <h3>咨询列表</h3>
          </div>
          <div class="panel-body">
            <div class="responsive-table">
              <div id="datatables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="row">
                  <div class="col-sm-12">
                    <table class="table table-striped table-bordered dataTable no-footer" width="100%" cellspacing="0"  style="width: 100%;">
                      <thead>
                        <tr role="row">
                          <th class="sorting" width="5%">序号</th>
						  <th  class="sorting" width="10%">咨询标题</th>
						  <th  class="sorting" width="20%">咨询内容</th>
						  <th  class="sorting" width="10%">咨询人</th>
						  <th  class="sorting" width="10%">审核人</th>
						  <th  class="sorting" width="20%">回复内容</th>
						  <th  class="sorting" width="10%">状态</th>
						  <th class="sorting" width="10%">操作</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${pagerList.pageList }" varStatus="status" var="listObject" >
					       <tr role="row" class="even">
					        	<td class="center">${status.index + 1 }</td>
				        		<td class="center">${listObject.messageName }</td>
				        		<td class="center">${listObject.messageContext }</td>
				        		<td class="center">${listObject.userName }</td>
				        		<td class="center">${listObject.authorName }</td>
				        		<td class="center">${listObject.authorContext }</td>
				        		<td class="center">${listObject.messageType }</td>
					        	<td class="center">
					         		
					         		<c:if test="${role eq 'user' && listObject.messageType eq '未回复'}">
					         		<a href="<%=baseURL %>/findMessage.do?id=${listObject.resourceId}" title="编辑" class="link_icon">编辑</a>
         		|
         		<a href="javascript:delteMessage('${listObject.resourceId}');" title="删除" class="link_icon">删除</a>
					        	</c:if>
					        	<c:if test="${role eq 'work' && listObject.messageType eq '未回复'}">
					         		<a href="<%=baseURL %>/findMessage.do?id=${listObject.resourceId}&type=author" title="处理" class="link_icon">处理</a>
					        	</c:if>
					        	</td>
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
	var action = baseURL+"/exportMessage.do";
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
