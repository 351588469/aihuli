<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎</title>
</head>
<body>
<form action="login" method="post">
	用户名：<input id="name" name="username" type="text" value=""></input><br>
	真实姓名：<input id="realname" name="realname" type="text"></input><br>
	<input type="submit">
</form>
<span>当前IP：<%=request.getRemoteAddr() %></span>
</body>
</html>
