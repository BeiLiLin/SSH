<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登录界面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/modal.js"></script>
<script type="text/javascript">	
	$(document).ready(function() {

		$("a.forgot").click(function() {
			$("#login-modal").modal("hide");
			$("#forgetform").modal({
				show : !0
			})
		});

		$("#signup-modal").modal("hide");
		$("#activation-modal").modal("hide");
		$("#setpassword-modal").modal("hide");

	});
	window.onload = function() {
		var config = {
			vx : 4,
			vy : 4,
			height : 2,
			width : 2,
			count : 100,
			color : "121, 162, 185",
			stroke : "100, 200, 180",
			dist : 6000,
			e_dist : 20000,
			max_conn : 10
		}
		CanvasParticle(config);
	}
	function Change()
	{
		document.getElementById('registbutton').style.backgroundColor="#337636";
	}
	function ChangeFontColor(){

		document.getElementById('registbutton').style.color="#cccccc";
	}
	function RevertFontColor(){

		document.getElementById('registbutton').style.color="white";
	}
/*	function Revert()
	{
		document.getElementById('registbutton').style.backgroundColor="#45B549";
	}*/
	function rChange()
	{
	document.getElementById('r').size="5px";
	}
	function rRevert()
	{
		document.getElementById('r').size="2px";
	}
</script>
<script type="text/javascript" src="js/canvas-particle.js"></script>
</head>
<body >
	<div class="page">
		<div class="loginwarrp">
			<div class="logo">登录界面</div>
		   <div class="login_form">
			<form  name="LoginForm" action="<%=path%>/users/Users_login.action" method="post">
				
				<li class="login-item"><span>用户名：</span> <input type="text"
					id="username" name="username" class="login_input"
							<% 
						if(request.getAttribute("ruser")!=null)
						{
							%>
							value="<%=request.getAttribute("ruser") %>"						
							<%
							}
							%>
					> <span
					id="count-msg" class="error"></span></li>

				<li class="login-item"><span>密 码：</span> <input type="password"
					id="password" name="password" class="login_input"
					<%
						if(request.getAttribute("rpass")!=null)
						{
							%>
							value="<%=request.getAttribute("rpass") %>"						
							<%
							}
							%>
					> <span
					id="password-msg" class="error"></span></li>

				<div class="clearfix"></div>

				<li class="login-sub"><input id="" type="submit" name="Submit"
					value="登录"  onclick="ChangeColor()"/> <input
					type="reset" name="Reset" value="重置" onclick="reset();" />
					</li>
					<div id="ad"><a data-toggle="modal" href="#signup-modal" onmouseover="rChange()" onmouseout="rRevert()"><font color="#666666" size="2px" id="r">点击此处注册</font></a></div> 
					
				

			</form>


		</div>
	</div>
	</div>
	<div class="modal" id="signup-modal">
		<a class="close" data-dismiss="modal">×</a>
		<h1>注册</h1>
		 <div class="regist_form">
		<form name="RegistForm"  action="<%=path%>/users/Users_regist.action" method="login">
			<table border-spacing:  0px 10px;>
			<tr class="trs">
			<td>
			<li style="border:1px solid #dedede;width:300px;line-height:30px;border-radius: 6px;" >				
				用户名:<input type="text" name="username" id="username" style="padding: 5px 10px;width: 200px;outline: none;border: none;"/>
			</li>
			</td>
			</tr>
			<tr class="trs">
			<td>
			<li style="border:1px solid #dedede;width:300px;line-height: 30px;border-radius: 6px;">
				密码： <input type="password" name="password" class="password" style="padding: 5px 10px;width:200px;outline: none;border:none;"/>
			</li>
			</td>
			</tr>
			<tr class="trs">
			<td>
				<input id="registbutton" type="submit" value="注册" style="color: #FFFFFF;background-color: #45B549;cursor: pointer;width: 60px;height:30px;border-radius: 8px;outline: none;border: none;"
				 onclick="Change()" onmouseover="ChangeFontColor()" onmouseout="RevertFontColor()"/>
			</td>
			
			</tr>
			</table>
			
		
			
		</form>
			
	


		</div>

	</div>

</body>
</html>