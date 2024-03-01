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
          <ul class="nav nav-list tree" style="display: block;height: 480px;overflow-y:scroll;">
            <c:if test="${role eq 'super' }">
            	<li><a href="<%=baseURL %>/listPerson.do">管理员管理</a></li>
            </c:if>
            
            <c:if test="${role eq 'admin' }">
            <li><a href="<%=baseURL %>/findPerson.do?id=${personSession.personId }">个人信息</a></li>
            <li><a href="<%=baseURL %>/listUser.do">用户信息</a></li>
            <li><a href="<%=baseURL %>/listWork.do">工作人员信息</a></li>
            <li><a href="<%=baseURL %>/listLog.do">日志管理</a></li>
            <li><a href="<%=baseURL %>/view/visit/list_visit_report.jsp">访客统计</a></li>
            <li><a href="<%=baseURL %>/view/user/list_user_report.jsp">住户新增统计</a></li>
            <li><a href="<%=baseURL %>/listUserMessage.do">人员疫苗统计</a></li>
            <li><a href="<%=baseURL %>/listAppriseNote.do">评论信息管理</a></li>
            </c:if>
            
            <c:if test="${role eq 'user' }">
            <li><a href="<%=baseURL %>/findUser.do?id=${userSession.resourceId }">个人信息</a></li>
            <li><a href="<%=baseURL %>/listAntiepidemic.do">防疫信息</a></li>
            <li><a href="<%=baseURL %>/listRegister.do">人员信息登记</a></li>
            <li><a href="<%=baseURL %>/listNotice.do">公告管理</a></li>
            <li><a href="<%=baseURL %>/listCovid.do">疫情信息</a></li>
            <li><a href="<%=baseURL %>/listConduct.do">宣传管理</a></li>
            <li><a href="<%=baseURL %>/listHealth.do">我的健康登记</a></li>
            <li><a href="<%=baseURL %>/listVisit.do">来访信息</a></li>
            <li><a href="<%=baseURL %>/listNote.do">帖子管理</a></li>
            <li><a href="<%=baseURL %>/listRisk.do">风险地区管理</a></li>
            <li><a href="<%=baseURL %>/listVaccinum.do">疫苗管理</a></li>
            <li><a href="<%=baseURL %>/listBespoke.do">预约管理</a></li>
            <li><a href="<%=baseURL %>/listNews.do">我的消息</a></li>
            <li><a href="<%=baseURL %>/listLeadership.do">我的上传管理</a></li>
            <li><a href="<%=baseURL %>/listAccusation.do">我的核酸检测管理</a></li>
            <li><a href="<%=baseURL %>/listMessage.do">疑问咨询</a></li>
            <li><a href="<%=baseURL %>/listDisinfect.do">消毒申请</a></li>
            <li><a href="<%=baseURL %>/listApply.do">核酸检测申请</a></li>
            </c:if>
            
            <c:if test="${role eq 'work' }">
            <li><a href="<%=baseURL %>/findWork.do?id=${workSession.resourceId }">个人信息</a></li>
            <li><a href="<%=baseURL %>/listAntiepidemic.do">防疫信息</a></li>
            <li><a href="<%=baseURL %>/listNotice.do">公告管理</a></li>
            <li><a href="<%=baseURL %>/listCovid.do">疫情信息</a></li>
            <li><a href="<%=baseURL %>/listConduct.do">宣传管理</a></li>
            <li><a href="<%=baseURL %>/listHealth.do">健康登记</a></li>
            <li><a href="<%=baseURL %>/listDivergence.do">出入登记</a></li>
            <li><a href="<%=baseURL %>/listNote.do">帖子管理</a></li>
            <li><a href="<%=baseURL %>/listStack.do">健康码记录</a></li>
            <li><a href="<%=baseURL %>/listLeadership.do">上传管理</a></li>
            <li><a href="<%=baseURL %>/listAccusation.do">核酸检测管理</a></li>
            <li><a href="<%=baseURL %>/listRisk.do">风险地区管理</a></li>
            <li><a href="<%=baseURL %>/listFlow.do">人员流动管理</a></li>
            <li><a href="<%=baseURL %>/listVaccinum.do">疫苗管理</a></li>
            <li><a href="<%=baseURL %>/listBespoke.do">预约管理</a></li>
            <li><a href="<%=baseURL %>/listMessage.do">疑问咨询</a></li>
            <li><a href="<%=baseURL %>/listDisinfect.do">消毒申请</a></li>
            <li><a href="<%=baseURL %>/listApply.do">核酸检测申请</a></li>
            
            </c:if>
            
            
            
            
           
            
            
          </ul>
        </li>
      </ul>
    </div>
  </div>
  <!-- end: Left Menu --> 