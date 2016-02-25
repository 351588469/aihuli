<%@ page contentType="text/html; charset=gb2312" language="java" isELIgnored="false" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>恰恰广场舞管理平台</title>
<link href="css/public.css" rel="stylesheet" type="text/css"/>
<script src="js/md5.js" type="text/javascript"></script>
<script src="js/jQuery.js" type="text/javascript"></script>
<%@ include file="limits/commons.jsp" %>
<style type="text/css">
body {
	margin:0 auto;
	padding:0;
	text-align:center;
	background: #fafafa;
}
#login {
	position:absolute;
	width:400px;
	top:30%;
	left:50%;
	margin-left:-200px;
}
.imgCode{
	cursor:pointer
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#userVo\\.userName").focus();
	
	$("#check").blur(function(){
		var check = $("#check").val();
		if(isNaN(check) || /\s+/g.test(check)){
			$("#error").html("验证码请输入数字！");
		}else if(check == ""){
			$("#error").html("请输入验证码！");
		}
	});
	
	$("#loginBut").click(function(){
		var check = $("#check").val();
		if(isNaN(check) || check == "" || /\s+/g.test(check)){
			return false;
		}
		var password = $("#userVo\\.userPwd").val();
		if(password.length < 6){
			$("#error").html("密码输入过短！");
			return false;
		}
		$("#userVo\\.userPwd").val(MD5(password));
	});
	
	$("#imgCheck").click(function(){
		$(this).attr("src","imgCode.servlet?"+Math.random().toString());
	});
	
	if(top.location != self.location){
		top.location=self.location;
	}
});
</script>
</head>

<body >
<form method="post" action="login.action">
<div id="login">
<table >
  <tr >
  	<td rowspan="5" width="25%">
  	<img src="imgs/logo.png" style="float:left;padding-top:7px;padding-left:10px"/></td>
  </tr>
  <tr height="30">
  	<td colspan="2"><font color="red"><div id="error"><s:property value="message"/></div></font></td>
  </tr>
  <tr>
  	<td width="25%">用户名:</td>
	<td width="67%" align="left"><input type="text" class="input" id="userVo.userName" name="userVo.userName"/></td>
  </tr>
  <tr>
  	<td>密&nbsp;&nbsp;码:</td>
	<td align="left"><input type="password" class="input" id="userVo.userPwd" name="userVo.userPwd"/></td>
  </tr>
 <!--  <tr>
  	<td>验证码:</td>
	<td align="left"><input type="text" class="input" id="check" style="width:50px;" name="userVo.checking"/>
	&nbsp;<img id="imgCheck" class="imgCode" src="imgCode.servlet"/>
	</td>
  </tr> -->
  <input type="hidden" value="1234" class="input" id="check" style="width:50px;" name="userVo.checking"/>
  <tr>
  	<td colspan="2" style="height:30px;line-height:30px;padding-top:12px;">
		<input class="button" type="submit" id="loginBut" style="width:45px;height:22px" value="登&nbsp;录"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<!-- <input class="button" type="button" style="width:45px;height:22px" value="取&nbsp;消"/> -->
	</td>
  </tr>
</table>
</div>
</form>
</body>
</html>
