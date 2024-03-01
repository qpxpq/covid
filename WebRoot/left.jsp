<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/view/common/jsp/com.jsp" %>
<!-- start:Left Menu -->
  <div id="left-menu">
    <div class="sub-left-menu scroll">
      <ul class="nav nav-list">
        <li>
          <div class="left-bg"></div>
        </li>
        <li> <a class="tree-toggle nav-header"> <span class="fa-diamond fa"></span>菜单导航 <span class="fa-angle-right fa right-arrow text-right"></span> </a>
          <ul class="nav nav-list tree" style="display: block;height: 730px;overflow-y:scroll;">
            <c:if test="${role eq 'super' }">
            	<li><a href="<%=baseURL %>/listPerson.do">管理员管理</a></li>
            </c:if>
            <c:if test="${role eq 'admin' }">
            <li><a href="<%=baseURL %>/findPerson.do?id=${personSession.personId }">个人信息</a></li>
            <li><a href="<%=baseURL %>/listUser.do">用户信息</a></li>
            <li><a href="<%=baseURL %>/listWork.do">工作人员信息</a></li>
            <li><a href="<%=baseURL %>/view/user/list_user_report.jsp">住户新增统计</a></li>
            <li><a href="<%=baseURL %>/listLog.do">日志管理</a></li>
            <li><a href="<%=baseURL %>/listLeadership.do">入庆报备管理</a></li>
            </c:if>
            <c:if test="${role eq 'user' }">
            <li><a href="<%=baseURL %>/findUser.do?id=${userSession.resourceId }">个人信息</a></li>
            <li><a href="<%=baseURL %>/listLeadership.do">入庆报备</a></li>
            <li><a href="<%=baseURL %>/listAccusation.do">落地核酸填报</a></li>
            <li><a href="<%=baseURL %>/listMessage.do">居家隔离填报</a></li>
            <li><a href="<%=baseURL %>/listDisinfect.do">集中隔离填报</a></li>
            <li><a href="<%=baseURL %>/listHealth.do">我的健康登记</a></li>
            <li><a href="<%=baseURL %>/listNews.do">我的消息</a></li>
            </c:if>
            <c:if test="${role eq 'work' }">
            <li><a href="<%=baseURL %>/findWork.do?id=${workSession.resourceId }">个人信息</a></li>
            <li><a href="<%=baseURL %>/listHealth.do">健康登记</a></li>
            <li><a href="<%=baseURL %>/listAccusation.do">落地核酸管理</a></li>
            <li><a href="<%=baseURL %>/listMessage.do">居家隔离管理</a></li>
            <li><a href="<%=baseURL %>/listDisinfect.do">集中隔离管理</a></li>
            <li><a href="<%=baseURL %>/listLeadership.do">上传管理</a></li>
            </c:if>
          </ul>
        </li>
      </ul>
    </div>
  </div>
  <!-- end: Left Menu --> 