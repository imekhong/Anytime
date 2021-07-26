<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath }/resources/js/login.js"></script>
<link
	href="${pageContext.request.contextPath }/resources/css/generalLoginView.css"
	rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="contentBox">
<div style="display: inline-block; text-align: center; align-content: center;margin-left: 30%; margin-top: 20% ">
<form action="GeneralLogin" method="get">
<label>이메일 : </label>  <input type="email" id="email" name="email" placeholder="email"><br><br>
<label>비밀번호: </label> <input type="password" id="pwd"  name="pwd" placeholder="password"><br><br>
<input type="submit" value="로그인">
</form>
</div>
</div>
</body>
</html>