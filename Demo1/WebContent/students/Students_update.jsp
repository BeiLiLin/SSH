<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改界面</title>
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
	position: absolute;
	bottom: -45px;
	right: 0px;
}

#tishi {
	position: absolute;
	right: 400px;
	top: 285px;
}

</style>
<script type="text/javascript">
/*	function check() {

		var s = document.getElementById("birth").value;
		var r = /^([1]\d{3}|[2][0][0-1][0-7])-(0[1-9]|1[0-2])-([0-2][1-9]|3[0-1])$/;
		if (s != "格式:yyyy-MM-dd") {
			if (!r.test(s)) {
				var show = "<font color='red'>*格式错误！</font>";
				document.getElementById("tishi").innerHTML = show;
			} else {
				var show = "<font color='green'>*格式正确！</font>";
				document.getElementById("tishi").innerHTML = show;
			}
		}
	}
	function hint(v) {
		if (v.value == "") {
			v.value = "格式:yyyy-MM-dd";
		}

	}*/
	function change(v) {
		if (v.value == "格式:yyyy-MM-dd") {
			v.value = "";
		}
	}
	function ChangeFontColor(){

		document.getElementById('Botton').style.color="#cccccc";
	}
	function RevertFontColor(){

		document.getElementById('Botton').style.color="white";
	}
	function RevertColor(){
	
		document.getElementById('Botton').style.backgroundColor="#65809a"
	}
</script>
</head>

<body>
	<div id="contain">
		<div id="top">
			<img src="image/top.jpg">
			<div id="user">
				<span><font color="white">用户名：</font> </span> <span><font
					color="white" size="6px">${sessionScope.user }</font> </span> <a
					href="http://localhost:8888/Demo1/users/LoginRegist.jsp"><font
					color="red" id="black">退出登录</font> </a>
			</div>
		</div>
		<div id="left">
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/students/Students_pagecount.action">数据表</a></li>
				<li><a
					href="${pageContext.request.contextPath}/links/Links_search.action">查询</a></li>
				<li><a
					href="${pageContext.request.contextPath}/links/Links_add.action">添加</a></li>			

			</ul>
		</div>
		<div id="main">
			<h4 align="center">修改学生信息</h4>
			<form name="updateForm" action="<%=path%>/students/Students_update.action" method="post">
				<table align="center" cellpadding="10px">
					<tr>
						<td>编号： <input type="text" name="stuid"		readOnly="true"
							 style="background-color: #cad6de"	
							 value='<s:property value="#session.modify_students.stuID"/>'
							>
						</td>
					</tr>
					<tr>
						<td>生日： <input type="text" name="birthday"
							value='<s:date name="#session.modify_students.birthday" format="yyyy-MM-dd"/>' id="birth">
						</td>
					</tr>
					<tr>
						<td>姓名： <input type="text" name="stuname" value='<s:property value="#session.modify_students.stuName" />'>
						</td>
					</tr>
					<tr>
						<s:if test='%{#session.modify_students.sex=="男"}'>
							<td>性别： <input type="radio" name="sex" value="男" checked="checked"> 男 <input
							type="radio" name="sex" value="女"> 女 
						</td>
						</s:if>
						<s:else>
						<td>性别： <input type="radio" name="sex" value="男"> 男 <input
							type="radio" name="sex" value="女" checked="checked"> 女 
						</td>
						</s:else>
					</tr>

					<tr>

						<td colspan="2"><input id="Botton" type="submit" value="修改" style="border-radius: 10px;width: 60px;
						height: 30px;background-color: #65809a;color: white;border: none;outline: none;cursor: pointer;"
								onclick="ChangeColor()" onmouseover="ChangeFontColor()" onmouseout="RevertFontColor()"/></td>

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
