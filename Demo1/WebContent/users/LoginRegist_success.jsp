<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>请勿重复提交你的信息，谢谢！</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{
		background-color:#e5ebef;
		}
	</style>
	<script type="text/javascript">
	var time=4;
	
	function returnUrlByTime()
	{
		
		window.setTimeout('returnUrlByTime()',1000);
		
		time=time-1;
		
		document.getElementById("layer").innerHTML = time;
	}
	</script>
	</head>
	
	<body onload="returnUrlByTime()">
		<%
			request.setCharacterEncoding("utf-8");
		%>
		用户：<font color="red"><%=session.getAttribute("user") %></font>
		
			<font >登录成功</font>
		<br>
		<a href="${pageContext.request.contextPath}/students/Students_pagecount.action"><span id="layer" style="color: red">3</span>秒后。将自动转入管理界面</a>
		<%
			//转向语句
			response.setHeader("Refresh","3;URL=http://localhost:8888/Demo1/students/Students_pagecount.action");
		 %>
	</body>
</html>
