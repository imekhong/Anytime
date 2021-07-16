<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Join</title>
</head>
<body>

<div class="contentBox">
	<div style="display: inline-block; text-align: center; align-content: center;margin-left: 30%; margin-top: 20% ">
	<img src="${pageContext.request.contextPath }/resources/img/LoginJoinTitle.png"><br><br>
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=a924c282a86092b8472e6c2885aafe4a&redirect_uri=http://localhost:8000/root/auth/kakao/callback&response_type=code"><img style="border-radius: 15px;" src="${pageContext.request.contextPath }/resources/img/kakaoJoinNew.png" ></a><br><br>
	<a href="generaljoin" style="background-color: #437eb4; color: black;border-radius: 15px; display:inline-block;  width: 181px;height: 34px; font-weight: bold; text-align: center; text-decoration-line : none;"><label style="margin-top: 5px;">일반회원으로 회원가입</a><br>
	</div>
</div>


</body>
</html>