<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>애니타임</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/46c1a740db.js" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function() {
		$(".navdiv").show();
	})
</script>
</head>
<body>
<c:set var="page" value="1" />
<c:if test="${param.page != null && param.page != ''}">
	<c:set var="page" value="${param.page }" />
</c:if>
<div class="contentBox">
<h1 style="color:skyblue;">학교게시판 내 게시글</h1><br>
	<div class="w3-bar">
		<button type="button" class="w3-bar-item w3-button w3-border" onclick="location.href='${contextPath }/my/cboard?page=1'">
			<i class="fa fa-bars"></i> 자유게시판 글 목록
		</button>
		<button type="button" class="w3-bar-item w3-button w3-border w3-blue" onclick="location.href='${contextPath }/my/sboard?page=1'">
			<i class="fa fa-bars"></i> 학교게시판 글 목록
		</button>
	</div>
	<div>
		<c:if test="${boardList.size() == 0}">
			<div class="w3-container w3-border">게시물이 없습니다.</div>
		</c:if>
		<c:if test="${boardList.size() != 0 }">
			<c:forEach var="list" items="${boardList }">
				<div class="w3-container w3-border w3-hover-border-red" style="margin: 0px">
					<span class="w3-xlarge w3-text-indigo w3-hover-text-white"  style="text-align:left;"> 
					<a href="${contextPath }/board/school/p/${list.section}/${list.postNo}" style="text-decoration: none;">${list.subject }</a>
					</span><br>
					${list.writeDate}&nbsp; ${list.nickname }&nbsp;
					<div class="w3-right">
						<span><i class="fa fa-eye"></i>&nbsp; ${ list.views }</span>&nbsp; 
						<span><i class="fa fa-heart"></i></span>&nbsp;
						<c:if test="${list.like != null }"> ${list.like }</c:if>
						<c:if test="${list.like == null }"> 0</c:if>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<br>
		<div style="display: flex; justify-content:center;">
			<ul class="pagination">
				<c:if test="${page != 1 }">
					<li class="page-item"><a class="page-link" href="${contextPath }/my/sboard?page=${page-1 }">Previous</a></li>
				</c:if>
				<c:forEach var="num" begin="1" end="${pageCount }" step="1">
					<c:if test="${num == page }">
						<li class="page-item active"><a class="page-link" href="">${num }</a></li>
					</c:if>
					<c:if test="${num != page }">
						<li class="page-item"><a class="page-link" href="${contextPath }/my/sboard?page=${num }">${num }</a></li>
					</c:if>
				</c:forEach>
				<c:if test="${page != pageCount }">
					<li class="page-item"><a class="page-link" href="${contextPath }/my/sboard?page=${page+1 }">Next</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
</body>
</html>