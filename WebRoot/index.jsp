<%@page import="com.info.common.staticvalue.StaticValue"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/common/jsp/com.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="description" content="12">
<meta name="author" content="12">
<meta name="keyword" content="12">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>大庆市疫情报备系统</title>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/fullcalendar.min.css"/>
<link href="<%=baseURL %>/asset/css/style.css" rel="stylesheet">
</head>

<body id="mimin" class="dashboard">

<jsp:include page="top.jsp"/>

<div class="container-fluid mimin-wrapper"> 
  <jsp:include page="left.jsp"/>
  
  <!-- start: content -->
  <div id="content">
    <div class="panel">
      <div class="panel-body">
        <div class="col-md-6 col-sm-12">
          <h3 class="animated fadeInLeft">欢迎您：${systemName }</h3>
        </div>
      </div>
    </div>
    <div class="col-md-12" style="padding:20px;">
      <div class="col-md-12 padding-0">
        <div class="col-md-8 padding-0">
          <div class="col-md-12 padding-0" style="font-size: 40px;text-align: center;">
            	欢迎您登录大庆市疫情报备系统!
          </div>
        </div>
      </div>
    </div>
  </div>
  
</div>

<script src="<%=baseURL %>/asset/js/jquery.min.js"></script> 
<script src="<%=baseURL %>/asset/js/jquery.ui.min.js"></script> 
<script src="<%=baseURL %>/asset/js/bootstrap.min.js"></script> 
<script src="<%=baseURL %>/asset/js/plugins/jquery.nicescroll.js"></script> 
<%--<script src="<%=baseURL %>/asset/js/main.js"></script> 

--%></body>
</html>