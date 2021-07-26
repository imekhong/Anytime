<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }/board/common"/>
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
		
		function setSearch(){
			if('${param.keyword}' != ''){
				$("#searchType").val('${param.search_type}');
				$("#keyword").val('${param.keyword}');
			}
		}
		setSearch();
	})
</script>
</head>
<body>
<c:set var="page" value="1" />
<c:if test="${param.page != null && param.page != ''}">
	<c:set var="page" value="${param.page }" />
</c:if>
<div class="contentBox">
<h1 style="color:skyblue;">자유게시판</h1><br>
	<div>
		<button type="button" onclick="location.href='${contextPath }/write'" class="btn btn-outline-dark" style="width:960px">글쓰기</button>
	</div>
	<br>
	<div>
		<c:if test="${commonBoardList.size() == 0 && param.keyword == null}">
			<div class="w3-container w3-border">게시물이 없습니다.</div>
		</c:if>
		<c:if test="${commonBoardList.size() == 0 && param.keyword != null}">
			<div class="w3-container w3-border">검색 결과가 없습니다.</div>
		</c:if>
		<c:if test="${commonBoardList.size() != 0 }">
			<c:forEach var="list" items="${commonBoardList }">
				<div class="w3-container w3-border w3-hover-border-red" style="margin: 0px">
					<span class="w3-xlarge w3-text-indigo w3-hover-text-white"  style="text-align:left;"> 
					<a href="${contextPath }/p/${list.postNo}" style="text-decoration: none;">${list.subject }</a>
					</span><br>
					${list.writeDate}&nbsp; ${list.nickname }&nbsp;<small style="color:gray;">${ list.school }고등학교</small>
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
		<div>
			<div class="form-group">
				<form>
					<span>
					<select class="form-control" name="search_type" id="searchType" style="width:150px">
				    	<option value="all">제목+내용</option>
				    	<option value="subject">글 제목</option>
				    	<option value="content">글 내용</option>
				    	<option value="tag">해시태그</option>
					</select>
					</span>
					<span>
					<input type="text" name="keyword" class="form-control" id="keyword" placeholder="검색어를 입력하세요." style="width:200px">
					</span>
				</form>
			</div>
		</div>
		<div style="display: flex; justify-content:center;">
			<ul class="pagination">
				<c:if test="${page != 1 }">
					<li class="page-item"><a class="page-link" href="${contextPath }/list?page=${page-1 }">Previous</a></li>
				</c:if>
				<c:forEach var="num" begin="1" end="${pageCount }" step="1">
					<c:if test="${num == page }">
						<li class="page-item active"><a class="page-link" href="">${num }</a></li>
					</c:if>
					<c:if test="${num != page }">
						<li class="page-item"><a class="page-link" href="${contextPath }/list?page=${num }">${num }</a></li>
					</c:if>
				</c:forEach>
				<c:if test="${page != pageCount }">
					<li class="page-item"><a class="page-link" href="${contextPath }/list?page=${page+1 }">Next</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
</body>
</html>