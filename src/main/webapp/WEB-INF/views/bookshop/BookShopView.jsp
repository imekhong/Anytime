<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath }/resources/BookShopJs/bookShopView.js"></script>
<link
	href="${pageContext.request.contextPath }/resources/css/BookShopView.css"
	rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="MainBox">
		<div class="BoardList">
			<div id="BoardCard" class="BoardCard">
			<!--  bookId로 삭제나 수정해야하니 숨겨놓음 -->
			<input type="hidden" id="bookId" value="${ BookShop.boardId}">
			
			제목 : ${ BookShop.booktitle} <br>
			bookisbn : ${ BookShop.bookisbn} <br>
			
			
			<c:choose>
			<c:when test="${ BookShop.underline == 'non'}">
			밑줄 : 없음<br>
			</c:when>
			<c:when test="${ BookShop.underline == 'sharpPencil'}">
			밑줄 : 샤프, 펜슬<br>
			</c:when>
			<c:when test="${ BookShop.underline == 'highliterBolpen'}">
			밑줄 : 볼펜<br>
			</c:when>
			<c:otherwise>
			밑줄 : 샤프,펜슬,볼펜<br>
			</c:otherwise>
			</c:choose>
			
			<c:choose>
			<c:when test="${ BookShop.handwrite == 'non'}">
			필기 : 없음<br>
			</c:when>
			<c:when test="${ BookShop.handwrite == 'sharpPencil'}">
			필기 : 샤프, 펜슬<br>
			</c:when>
			<c:when test="${BookShop.handwrite == 'highliterBolpen'}">
			필기 : 볼펜<br>
			</c:when>
			<c:otherwise>
			필기 : 샤프,펜슬,볼펜<br>
			</c:otherwise>
			</c:choose>
			
			<c:choose>
			<c:when test="${ BookShop.cover == 'clean'}">
			책표지 상태 : 깨끗함<br>
			</c:when>
			<c:otherwise>
			책표지 상태 : 더러움<br>
			</c:otherwise>
			</c:choose>
			
			<c:choose>
			<c:when test="${ BookShop.nameWrite == 'non'}">
			이름 표기 유무 : 없음<br>
			</c:when>
			<c:otherwise>
			이름 표기 유무 : 썼다<br>
			</c:otherwise>
			</c:choose>
			
			<c:choose>
			<c:when test="${ BookShop.page == 'non'}">
			페이지 상태 : 없음<br>
			</c:when>
			<c:when test="${ BookShop.page == 'blank'}">
			페이지 상태 : 반색<br>
			</c:when>
			<c:when test="${ BookShop.page == 'damage'}">
			페이지 상태 : 훼손<br>
			</c:when>
			<c:otherwise>
			페이지 상태 : 반색과 회손<br>
			</c:otherwise>
			</c:choose>
			
			<c:choose>
			<c:when test="${ BookShop.meansOftransaction == 'parcelService'}">
			거래 형태 : 택배만<br>
			</c:when>
			<c:when test="${BookShop.meansOftransaction == 'directTransaction'}">
			거래 형태 : 직거래만<br>
			</c:when>
			<c:otherwise>
			거래 형태 : 택배와 직거래 둘다가능<br>
			</c:otherwise>
			</c:choose>
			<br>
			
			학교와 위치 : <label>${ BookShop.school}</label><br>
			
			
			
			가격 :  ${BookShop.price}원<br><br><br>
			
			<c:if test="${BookShopPhoto.photo1 != 'null'}">
			<img src="${BookShopPhoto.photo1 }" id="numPicture0" width="300px" height="200px">
			</c:if>
			<c:if test="${BookShopPhoto.photo2 != 'null'}">
			<img src="${BookShopPhoto.photo2 }" id="numPicture1" width="300px" height="200px">
			</c:if>
			<c:if test="${BookShopPhoto.photo3 != 'null'}">
			<img src="${BookShopPhoto.photo3 }" id="numPicture2" width="300px" height="200px">
			</c:if>
			<c:if test="${BookShopPhoto.photo4 != 'null'}">
			<img src="${BookShopPhoto.photo4 }" id="numPicture3" width="300px" height="200px">
			</c:if>
			<c:if test="${BookShopPhoto.photo5 != 'null'}">
			<img src="${BookShopPhoto.photo5 }" id="numPicture4" width="300px" height="200px">
			</c:if>
			
			
			<br>
			<c:if test="${BookShop.writer ==  userId}">
			<a href="/root/bookshop/modifyView/${ BookShop.boardId}">수정하기</a>
			<button onclick="salesCompleted()">판매완료</button>
			</c:if>
			<c:if test="${BookShop.writer !=  userId}">
			
			<form action="/root/writeChatView" method="POST">
			<input type="hidden" id="writer" name="writer" value="${ BookShop.writer}" >
			<input type="submit" value="판매자에게 쪽지보내기">
			</form>
			
			
			
			</c:if>
			
			</div>
		</div>
	</div>

	
</body>



</html>