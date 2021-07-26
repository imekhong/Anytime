<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"  src="${pageContext.request.contextPath }/resources/BookShopJs/bookShopView.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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
			
			<table class="table table-hover table-bordered">
    <thead>
      <tr>
        <th>목록,상태</th>
        <th>    </th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>제목</td>
        <td>${ BookShop.booktitle}</td>
      </tr>
      <tr>
        <td>bookisbn</td>
        <td>${ BookShop.bookisbn}</td>
      </tr>
      <tr>
        <td>밑줄</td>
        <c:choose>
			<c:when test="${ BookShop.underline == 'non'}">
			 <td>없음</td>
			</c:when>
			<c:when test="${ BookShop.underline == 'sharpPencil'}">
			<td>샤프, 펜슬</td>
			</c:when>
			<c:when test="${ BookShop.underline == 'highliterBolpen'}">
			<td>볼펜</td>
			</c:when>
			<c:otherwise>
			<td>샤프,펜슬,볼펜</td>
			</c:otherwise>
			</c:choose>
			 </tr>
			 
			 <tr>
			 <td>필기</td>
        <c:choose>
			<c:when test="${ BookShop.handwrite == 'non'}">
			<td>없음</td>
			</c:when>
			<c:when test="${ BookShop.handwrite == 'sharpPencil'}">
			<td>샤프, 펜슬</td>
			</c:when>
			<c:when test="${BookShop.handwrite == 'highliterBolpen'}">
			<td>볼펜</td>
			</c:when>
			<c:otherwise>
			<td>샤프,펜슬,볼펜</td>
			</c:otherwise>
			</c:choose>
       </tr>
       <tr>
       <td>책표지 상태</td>
       <c:choose>
			<c:when test="${ BookShop.cover == 'clean'}">
			 <td>깨끗함</td>
			</c:when>
			<c:otherwise>
			<td>더러움</td>
			 
			</c:otherwise>
			</c:choose>
       </tr>
      <tr>
      <td>이름 표기 유무</td>
      
      <c:choose>
			<c:when test="${ BookShop.nameWrite == 'non'}">
			 <td>없음</td>
			</c:when>
			<c:otherwise>
			<td>썼다</td>
			</c:otherwise>
			</c:choose>
      </tr>
      <tr>
      <td>페이지 상태</td>
      <c:choose>
			<c:when test="${ BookShop.page == 'non'}">
			<td>없음</td>
			</c:when>
			<c:when test="${ BookShop.page == 'blank'}">
			<td>반색</td>
			</c:when>
			<c:when test="${ BookShop.page == 'damage'}">
			<td>훼손</td>
			</c:when>
			<c:otherwise>
			<td>반색과 회손</td>
			</c:otherwise>
			</c:choose>
      </tr>
      <tr>
      <td>거래 형태 </td>
      <c:choose>
			<c:when test="${ BookShop.meansOftransaction == 'parcelService'}">
			<td>택배만 </td>
			</c:when>
			<c:when test="${BookShop.meansOftransaction == 'directTransaction'}">
			<td>직거래만 </td>
			</c:when>
			<c:otherwise>
			<td>택배와 직거래 둘다가능 </td>
			</c:otherwise>
			</c:choose>
      
      </tr>
      <tr>
      <td>학교와 위치</td>
      <td><label>${BookShop.school}</label></td>
       
      
      </tr>
      <tr>
      <td>가격</td>
      <td><label>${BookShop.price}원</label></td>
       
      
      </tr>
      
      
      
      
      
      
    </tbody>
  </table>
<br><br><br>
			
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
			<a class="btn-warning btn-sm"  href="/root/bookshop/modifyView/${ BookShop.boardId}">수정하기</a>
			<button onclick="salesCompleted()" class="btn-success btn-sm" >판매완료</button>
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