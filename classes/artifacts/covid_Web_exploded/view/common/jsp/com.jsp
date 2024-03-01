<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="info" uri="http://www.info.com" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String baseAddress=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	String baseURL=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath();
	String requestURL = request.getRequestURL().toString();
	String[] tempURL = requestURL.split("/", 4);
	String currentHost = tempURL[0] + "/" + "/" + tempURL[1] + tempURL[2]; 
%>
<link rel="stylesheet" href="<%=baseURL%>/view/common/validator/css/Validator.css" type="text/css"></link>
<script type="text/javascript" src="<%=baseURL %>/view/common/js/jquery.js"></script>
<script src="<%=baseURL %>/view/common/js/verificationNumbers.js"></script>
<script type="text/javascript" src="<%=baseURL%>/view/common/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=baseURL%>/view/common/validator/Validator.js"></script>
<script type="text/javascript">
	var baseURL = "<%=baseURL %>";
</script>
 
