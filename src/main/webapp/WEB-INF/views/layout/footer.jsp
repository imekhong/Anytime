<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.wrap {
	width: 960px;
	margin: auto;
}
.boardmenu a:hover {
	font-size: 11pt;
	color:red;
}
</style>
</head>
<body>
<div class="wrap">
<div style="color:#666; font-size:12px; font-family:tahoma;">
<div class="boardmenu">
			<nav style= "list-style:none; ">
			<ul style="display:flex;" >
			<li><a href="${contextPath }/rule/servicerule">이용약관</a></li>
			<li><a href="${contextPath }/rule/servicerule2">개인정보처리방침</a></li>
			<li><a href="${contextPath }/rule/servicerule3">커뮤니티이용규칙</a></li>	
			<li><a href="mailto:imj1363@naver.com" title="문의메일보내기">문의하기</a></li>									
			<li>Copyright &copy; 애니타임</li>
			</ul>
			</nav>
			
</div>
</div>
</div>

</body>
</html>