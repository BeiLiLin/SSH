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

		<title>My JSP 'index.jsp' starting page</title>
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
	background-color:#e5ebef;
}

#contain {
	margin: 0px;
	padding: 0px;
}

#top {
	
}

#left {
	float: left;
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
	padding-top: 50px;
	padding-left: -10px;
}

#footer {
	
}

#user {
	position: absolute;
	top: 30px;
	right: 30px;
}

#black {
	position:absolute;
	bottom:-45px;
	right:0px;
}
#tishi{
		position:absolute;
		right:400px;
		top:285px;
	}
</style>
<!-- 
<script type="text/javascript">
function hint(v) {
	if (v.value != "") {
		check();
	}
}
function change(v) {
	if (v.value == "") {
		var show = "格式:yyyy-MM-dd";
		document.getElementById("tishi").innerHTML = show;
	}
}
function check() {
	var bo = document.getElementById("birth").value;


	var r = /^([1]\d{3}|[2][0][0-1][0-7])-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
	if ((bo!= "")) {
		if ((!r.test(bo))) {
			var show = "<font color='red'>*格式错误！</font>";
			document.getElementById("tishi").innerHTML = show;

		} else if (r.test(bo)) {

			var show = "<font color='green'>*格式正确！</font>";
			document.getElementById("tishi").innerHTML = show;

		}
	}
}
</script> -->
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
</script>
	</head>

	<body>
		<div id="contain">
			<div id="top">
				<img src="image/top.jpg">
				<div id="user">
					<span><font color="white">用户名：</font>
					</span>
					<span><font color="white" size="6px">${sessionScope.user }</font>
					</span>
					
					<a href="http://localhost:8888/Demo1/users/LoginRegist.jsp"><font color="red" id="black">退出登录</font>
					
					</a>
				</div>
			</div>
			<div id="left">
				<ul>
			<li><a href="${pageContext.request.contextPath}/students/Students_pagecount.action">数据表</a></li>
				<li><a href="${pageContext.request.contextPath}/links/Links_search.action">查询</a></li>
				<li><a href="${pageContext.request.contextPath}/links/Links_add.action">添加</a></li>
			</ul>
			</div>
			<div id="main">
				<h4 align="center">
					添加学生信息
				</h4>
				<form name="addForm" action="<%=path%>/students/Students_add.action" method="post">
				
					<table align="center" cellpadding="10px">												
							<tr>
							<td>
								姓名：
								<input type="text" name="stuname" style="width:150px;padding:3px 10px;border:1px solid #ccc;">
							</td>
						</tr>
						<tr>
							<td>
								生日：
								<input type="date" name="birthday"  id="birth"
								value="" 
								style="width:150px;padding:3px 10px;border:1px solid #ccc;">
							</td>
							
							</tr>													
							<tr>
							<td>
								性别：
								<input type="radio" name="sex" value="男" >
								男

								<input type="radio" name="sex" value="女">
								女

								<input type="radio" name="sex" value="不限" checked>
								不限
							</td>
						</tr>

						<tr>
							<td colspan="2">
								<input id="Botton" type="submit" value="提交" style="border-radius: 10px;width: 60px;height: 30px;cursor:pointer;
								background-color: #65809a;color: white;border: none;outline: none;"
								onclick="ChangeColor()" onmouseover="ChangeFontColor()" onmouseout="RevertFontColor()">
							</td>
						</tr>
					</table>
					<span id="tishi"></span>
				</form>
				
			</div>
			<div id="right"></div>
			<div id="footer"></div>
		</div>


	</body>
</html>
