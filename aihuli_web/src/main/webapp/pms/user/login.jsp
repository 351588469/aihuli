<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱护理</title>
<link href="css/public.css" rel="stylesheet" type="text/css"/>
<script src="js/md5.js" type="text/javascript"></script>
<script src="js/jQuery.js" type="text/javascript"></script>
</head>
<body>
<form action="login" method="post">
	用户名：<input id="name" name="username" type="text"></input><br>
	密&nbsp;&nbsp;&nbsp;&nbsp;码：<input id="password" name="password" type="password"></input><br>
	<input type="submit">
</form>
<span>当前IP：<%=request.getRemoteAddr() %></span>
</body>
</html>
