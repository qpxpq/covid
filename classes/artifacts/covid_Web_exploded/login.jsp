<%@page import="com.info.common.staticvalue.StaticValue" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/view/common/jsp/com.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="description" content="xxxxx">
    <meta name="author" content="xxxxx">
    <meta name="keyword" content="xxxxx">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>疫情防控管理系统</title>
    <link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/simple-line-icons.css"/>
    <link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/animate.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=baseURL %>/asset/css/plugins/icheck/skins/flat/aero.css"/>
    <link href="<%=baseURL %>/asset/css/style.css" rel="stylesheet">
    <script type="text/javascript">
        var msg = "${msg }";
        if (msg != null && msg != "") {
            alert(msg);
        }

    </script>
</head>

<body id="mimin" class="dashboard form-signin-wrapper">
<div class="container">
    <form action="<%=baseURL %>/login.do" id="loginForm" class="form-signin" name="loginForm" method="post">
        <div class="panel periodic-login">
            <div class="panel-body text-center">
                <p class="atomic-mass">疫情防控管理系统</p>
                <div class="form-group form-animate-text" style="margin-top:40px !important;">
                    <label>账号</label></br> <span class="bar"></span>
                    <input type="text" class="form-text" id="username" name="username" required
                           style="margin-top: 20px">
                </div>
                <div class="form-group form-animate-text" style="margin-top:25px !important;">
                    <label>密码</label></br>
                    <input type="password" id="password" name="password" class="form-text" required
                           style="margin-top: 20px">


                </div>
                <div class="form-group form-animate-text" style="margin-top:25px !important;">
                    <label>用户类型</label></br> <span class="bar"></span>
                    <select id="operate" name="operate" required class="login_txtbx"
                            style="width: 100%;height:35px;color: black;margin-top: 20px">
                        <option value="">请选择登录权限</option>
                        <option value="super">超级管理员</option>
                        <option value="admin">管理员</option>
                        <option value="user">居民</option>
                        <option value="work">工作人员</option>
                    </select>
                    <span class="bar"></span>
                </div>

                <input type="submit" class="btn col-md-12" value="立即登录"/>
            </div>
            <%--<div class="text-center" style="padding:5px;">您还没有本网站的账号?请点击<a>立即注册</a> </div>
          --%></div>
    </form>
</div>

<script src="asset/js/main.js"></script>

</body>
</html>