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
<title>来访管理管理</title>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/fullcalendar.min.css"/>
<link href="<%=baseURL %>/asset/css/style.css" rel="stylesheet">
<script type="text/javascript">
	function delteVisit(id){
		alert("删除成功");
		var url = baseURL + "/delteVisit.do?id=" + id;
		window.location.href = url;
	}
	
	//模板下载
	function download(){
		var fileName = encodeURI(encodeURI("学生导入模板.xls"));
		window.open(baseURL+'/downloadModel.do?file='+fileName+'&folder=file');
	}
	
	function importExcl(){
		var url = baseURL + "/view/visit/import_visit.jsp"
		window.open(url, "_blank","height=200, width=600, top=200, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	}
	
	function addPage(){
		var url = baseURL + "/view/visit/add_visit.jsp";
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
  <form  action="listVisit.do" method="post" id="splitPageForm">
  <div id="content">
    <div class="panel">
      <div class="panel-body">
        <div class="col-md-12">
          <h3 class="animated fadeInLeft">来访管理报表</h3>
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
			 <label class="col-sm-2 control-label text-right">统计方式：</label>
			 <div class="col-sm-2">
				      <select id="type" name="type" onchange="changeSelectButton()">
				      <option value="year">年</option>
				      <option value="mouth">月</option>
				      	<option value="day">日</option>

				      </select>
             </div>
                  <!--
    <input type="button" onclick="download()" value="模板下载" class="submit btn btn-danger"/>
    <input type="button" onclick="ev_export()" value="导出" class="submit btn btn-danger"/>
    <input type="button" onclick="importExcl()" value="导入" class="submit btn btn-danger"/>   -->
                  
            </div>
          </div>
        <div class="panel">
          <div class="panel-body">
            <div class="responsive-table">
              <div id="datatables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                <div class="row">
                  <div class="col-sm-12">
                    <div id="reportDiv" style="width: 100%;height: 500px;">
                    </div>
                    <script type="text/javascript" src="<%=baseURL %>/view/common/js/echarts.min.js"></script>
                    <script type="text/javascript">
                    var myChart = echarts.init(document.getElementById('reportDiv'));
                    function ajaxOption(type){
                    	$.ajax({
                    		type: "post",
                    		dataType:"text",
                    		url: baseURL+"/visitReport.do?ajax=ajax&type="+type,
                    		success: function(data){
                    			var option =  eval("(" + data + ")");
                    			// 使用刚指定的配置项和数据显示图表。
                    	        myChart.setOption(option);
                    		},
                    		complete: function(XMLHttpRequest, textStatus){
                    		},
                    		error: function(){
                    		}
                    	});
                    }
                    
                    function changeSelectButton(){
                    	var selectData = $("#type").val();
                    	ajaxOption(selectData);
                    }
                    $("#type").val("day");
                    ajaxOption("day");
                    </script>
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
	var action = baseURL+"/exportVisit.do";
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