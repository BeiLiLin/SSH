<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<base href="<%=basePath%>">
		<title>修改成功界面</title>
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}

#contain {
	margin: 0px;
	padding: 0px;
	float: left;
}

#top {
	
}

#left {
	float: left;
	width: 200px;
}

#left ul {
	list-style-type: none;
	text-align: center;
	margin: 0px;
	padding: 0px;
	padding-top: 50px;
}

#left li {
	margin: 0px;
	padding-top: 20px;
	padding-right: 0px;
	padding-bottom: 20px;
	padding-left: 80px;
	list-style-type: none;
	border-bottom-style: none;
	border-bottom-width: 1px;
}

a:link {
	color: #766960;
	text-decoration: none;
}

a:visited {
	text-decoration: none;
	color: #766960;
}

a:hover {
	text-decoration: none;
	color: #333333;
}

a:active {
	text-decoration: none;
	color: #000000;
}

#main {
	margin: 0px;
	padding-left: 150px;
	padding-top: 20px;
	width: 650px;
	float: left;
}

#footer {
	
}

#user {
	position: absolute;
	top: 30px;
	right: 30px;
}

#black {
	position: absolute;
	bottom: -45px;
	right: 0px;
}
#juzhong{
	position: absolute;
	top: 280px;
	right: 500px;
	}
</style>

<script type="text/javascript">
	function ChangeColor()
	{
		document.getElementById('Botton').style.backgroundColor="#3d4349"
	}
	function ChangeFontColor(){

		document.getElementById('Botton').style.color="#cccccc";
	}
	function RevertFontColor(){

		document.getElementById('Botton').style.color="white";
	}
	var time=4;
	setInterval("returnUrlByTime()",1000);
	function returnUrlByTime()
	{	
		
		//window.setTimeout('returnUrlByTime()',1000);
		time--;
		document.getElementById("layer").innerHTML=time;	
	/*	if(time==0)
		{
			location.assign("http://localhost:8888/Demo1/students/Students_query.action");
		}*/
	}
	
	function back()
	{
		window.history.back();
	}
</script>
	</head>

	<body onload="returnUrlByTime()">
		<div id="contain">
			<div id="top">
				<img src="image/top.jpg">
				<div id="user">
					<span><font color="white">用户名：</font> </span>
					<span><font color="white" size="6px">${sessionScope.user }</font>
					</span>

					<a href="http://localhost:8888/Demo1/users/LoginRegist.jsp"><font color="red" id="black">退出登录</font> </a>
				</div>
			</div>
			<div id="left">
				<ul>
					<li><a href="${pageContext.request.contextPath}/students/Students_pagecount?confirm=1.action">数据表</a></li>
				<li><a href="${pageContext.request.contextPath}/links/Links_search.action">查询</a></li>
				<li><a href="${pageContext.request.contextPath}/links/Links_add.action">添加</a></li>		
				</ul>
			</div>
			<div id="main">

				<div id="juzhong">
				<a>修改成功！</a><br>
				<s:if test="null==#session.searchupdate||#session.searchupdate.isEmpty()">
				<a href="${pageContext.request.contextPath}/students/Students_query.action"><span id="layer" style="color: red">3</span>秒后跳转到主界面,点击此处立刻跳转！</a><br>
				<%
					//转向语句
						response.setHeader("refresh", "3,URL=http://localhost:8888/Demo1/students/Students_query.action");
					
				%>
				</s:if>
				<s:else>
				<a href="${pageContext.request.contextPath}/students/Students_search.action"><span id="layer" style="color: red">3</span>秒后跳转到主界面,点击此处立刻跳转！</a><br>					
				<%
					//转向语句
						response.setHeader("refresh", "3,URL=http://localhost:8888/Demo1/students/Students_search.action");
					
				%>
				</s:else>
				<a href="javascript:back()" style="color: red"><input id="Botton" type="submit" value="返回" style="border-radius: 10px;width: 60px;height: 30px;
				background-color: #65809a;color: white;border: none;outline: none; cursor: pointer;"
								onclick="ChangeColor()" onmouseover="ChangeFontColor()" onmouseout="RevertFontColor()"/></a>			
				
			
				</div>
			</div>

			<div id="right"></div>
			<div id="footer"></div>
		</div>


	</body>
</html>
