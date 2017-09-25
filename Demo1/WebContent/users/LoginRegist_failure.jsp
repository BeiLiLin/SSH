<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login_Failure.jsp' starting page</title>
    
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
  </head>
  
  <body>
  <h3><%=request.getAttribute("tip") %></h3>
    <a href="http://localhost:8888/Demo1/users/LoginRegist.jsp">点击此处返回登陆界面</a>
    <%
    	response.setHeader("Refresh","1;URL=http://localhost:8888/Demo1/users/LoginRegist.jsp");
    %>
  </body>
</html>
