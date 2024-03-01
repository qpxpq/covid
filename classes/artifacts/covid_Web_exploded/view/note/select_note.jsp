<%@page import="com.info.common.staticvalue.StaticValue"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/common/jsp/com.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<base target="_self" />
<meta charset="utf-8">
<meta name="description" content="xxxxx">
<meta name="author" content="xxxxx">
<meta name="keyword" content="xxxxx">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>帖子管理管理</title>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/fullcalendar.min.css"/>
<link href="<%=baseURL %>/asset/css/style.css" rel="stylesheet">
<script type="text/javascript">
	function submitSplitPageForm(){
		document.getElementById("splitPageForm").submit();
	}

	function seracherList(){
		$("#splitPageForm").submit();	
	}
	
	function selectNote(){
		var obj = $('input[name="selectNoteRadio"]:checked');
		var json = {};
			json["noteName"] = $(obj).attr("noteName");
			json["noteContext"] = $(obj).attr("noteContext");
			json["resourceId"] = $(obj).attr("resourceId");
			json["creatorId"] = $(obj).attr("creatorId");
			json["creatorName"] = $(obj).attr("creatorName");
			json["updaterId"] = $(obj).attr("updaterId");
			json["updaterName"] = $(obj).attr("updaterName");
			json["updateTime"] = $(obj).attr("updateTime");
			json["createTime"] = $(obj).attr("createTime");
		window.returnValue = json;
		window.close();
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
  <form  action="listNote.do" method="post" id="splitPageForm">
  <div id="content">
    <div class="panel">
      <div class="panel-body">
        <div class="col-md-12">
          <h3 class="animated fadeInLeft">帖子管理管理</h3>
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
			 <label class="col-sm-1 control-label text-right">帖子标题：</label>
			 <div class="col-sm-2">
			 	<input type="text" id="noteName" name="noteName" value="${searchList.noteName }" 
          	 			class="text_add" style=" width:120px"  />
             </div>
                  
                  
                  <div class="col-sm-4">
                    <input class="submit btn btn-danger" type="submit" value="查询" />
                    <button type="button"  onclick="selectNote();" class="btn_search">确认选择</button>
                  </div>
                  
                </div>
            </div>
          </div>
        <div class="panel">
          <div class="panel-heading">
            <h3>帖子管理列表</h3>
          </div>
          <div class="panel-body">
            <div class="responsive-table">
              <div id="datatables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="row">
                  <div class="col-sm-12">
                    <table class="table table-striped table-bordered dataTable no-footer" width="100%" cellspacing="0"  style="width: 100%;">
                      <thead>
                        <tr role="row">
                          <th class="sorting" width="5%">选择</th>
						  <th  class="sorting" width="10%">帖子标题</th>
						  <th  class="sorting" width="10%">帖子内容</th>
						  <th  class="sorting" width="10%">创建人</th>
						  <th  class="sorting" width="10%">创建时间</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${pagerList.pageList }" varStatus="status" var="listObject" >
					       <tr role="row" class="even">
					        	<td class="center">
					        		<input type="radio" name="selectNoteRadio" 
					          	  			noteName='${listObject.noteName }'
					          	  			noteContext='${listObject.noteContext }'
					          	  			resourceId='${listObject.resourceId }'
					          	  			creatorId='${listObject.creatorId }'
					          	  			creatorName='${listObject.creatorName }'
					          	  			updaterId='${listObject.updaterId }'
					          	  			updaterName='${listObject.updaterName }'
					          	  			updateTime='${listObject.updateTime }'
					          	  			createTime='${listObject.createTime }'
					  	 			 />
					        	
					        	</td>
				        		<td class="center">${listObject.noteName }</td>
				        		<td class="center">${listObject.noteContext }</td>
				        		<td class="center">${listObject.creatorName }</td>
				    			<td class="center"><fmt:formatDate value="${listObject.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
</body>
</html>