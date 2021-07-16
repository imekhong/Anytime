<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath }/resources/BookShopJs/bookShopMain.js"></script>
<link
	href="${pageContext.request.contextPath }/resources/css/bookshop.css"
	rel="stylesheet" type="text/css">
	
<meta charset="UTF-8">

<title>BookShop</title>
</head>
<body>
	<div class="MainBox">
		<div class="searchBook">
		<input type="text" placeholder="찾을 책을 입력해주세요"> 
			&emsp; <input type="button" value="검색"><br><br>
			<c:choose>
			<c:when test="${userId  != null}">
			
			<a class="btn sell" href="${pageContext.request.contextPath }/bookShop/write"><label>책팔기</label> </a><br><br>
			<a class="btn sell" href="${pageContext.request.contextPath }/bookShop/myboardLsit"><label>내가 쓴 글보기</label></a><br><br>
			<a class="btn sell" href="${pageContext.request.contextPath }/chatView/${userId}">쪽지함</a><br>
			</c:when>
			<c:otherwise>
			
			</c:otherwise>
			</c:choose>
			
		</div>
		
		
		
		<!--  반복문 c태그 넣기 -->
		<div class="BoardList">
		<c:forEach var="BoardListDTO" items="${PageboardList }">
		<a href="/root/bookshop/${nowPage }/${BoardListDTO.boardId }" >
		    <div class="BoardCardSet">
		    <div class="BoardCardImg">
		    <img src="${AllBookPhotoList[BoardListDTO.boardId].photo1}" width="200px" height="200px">
		    </div>
		    <div id="BoardCard" class="BoardCard">
			${ BoardListDTO.booktitle}<br>
			ISBN : <label>${ BoardListDTO.bookisbn} </label><br>
			학교와 위치 : <label>${ BoardListDTO.school}</label><br>
			<label class="priceLabel">${ BoardListDTO.price}원</label>
			<!--  중요 반드시 DTO의 변수값을 따지니 대소문자 틀리지않게 하세요 -->
			</div>
		    </div>
		    </a>
		</c:forEach>
		<br><br>
		<ul>
		<c:forEach var="listNum" items="${allPageCount }">
		<li><a href="/root/bookshop/${listNum +1}"><label>${listNum +1}</label>&nbsp;&nbsp; </a></li>
		</c:forEach>
		</ul>
		</div>
	</div>
</body>
</html>