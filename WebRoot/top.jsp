<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/common/jsp/com.jsp" %>
<!-- start: Header -->
<nav class="navbar navbar-default header navbar-fixed-top">
  <div class="col-md-12 nav-wrapper">
    <div class="navbar-header" style="width:100%;">
      <a href="http://localhost:8080/covid/index.jsp" class="navbar-brand"> <b>大庆市疫情报备系统</b> </a>
      <ul class="nav navbar-nav navbar-right user-nav">
        <li class="user-name"><span>欢迎您!${systemName }</span></li>
        <li class="dropdown avatar-dropdown"> <img src="asset/img/avatar.jpg" class="img-circle avatar" alt="user name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"/>
          <ul class="dropdown-menu user-dropdown">
            <li><a href="<%=baseURL %>/logout.do"><span class="fa fa-power-off"></span> 退出登录</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
<!-- end: Header -->