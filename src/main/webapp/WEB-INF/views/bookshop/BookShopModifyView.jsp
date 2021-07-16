<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath }/resources/BookShopJs/bookShopModify.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath }/resources/BookShopJs/bookShopModifyCallBack.js"></script>
<link href="${pageContext.request.contextPath }/resources/css/WriteBookShop.css" rel="stylesheet" type="text/css">

<meta charset="UTF-8">
<title>modify</title>
</head>
<body>



<div class="contentBox">
<input type="hidden" id="boardId" value="${BookShop.boardId}">

<label id="booktitle">${BookShop.booktitle}</label><br>

<label id="bookisbn">${BookShop.bookisbn}</label><br>

<label>밑줄 상태</label><br>
없음<input type="radio"  name="underline" onclick="changeunderline(this.value)" value="non">
연필/샤프<input type="radio"  name="underline" onclick="changeunderline(this.value)" value="sharpPencil">
형광펜/볼펜<input type="radio"  name="underline" onclick="changeunderline(this.value)" value="highliterBolpen">
전부다<input type="radio"  name="underline" onclick="changeunderline(this.value)" value="all"><br>
<input type="hidden" id="Question0" value="${BookShop.underline}">
<label id="label0">${BookShop.underline}</label><br>
<br>
<label>필기 상태</label><br>
없음<input type="radio"  name="handwrite"  onclick="changehandwrite(this.value)" value="non">
연필/샤프<input type="radio"  name="handwrite"  onclick="changehandwrite(this.value)" value="sharpPencil">
형광펜/볼펜<input type="radio"  name="handwrite" onclick="changehandwrite(this.value)" value="highliterBolpen">
전부다<input type="radio"  name="handwrite" onclick="changehandwrite(this.value)" value="all"><br>
<input type="hidden" id="Question1" value="${BookShop.handwrite}">
<label id="label1" >${BookShop.handwrite}</label><br>

<br>
<label>책 커버 상태</label><br>
깨끗함<input type="radio"  name="cover " onclick="changecover(this.value)" value="clean">
더러움<input type="radio"  name="cover " onclick="changecover(this.value)" value="dirty"><br>
<input type="hidden" id="Question2" value="${BookShop.cover}">
<label id="label2">${BookShop.cover}</label><br>
<br>
<label>책의 이름 표기 유무</label><br>
없음<input type="radio"  name="nameWrite " onclick="changenameWrite(this.value)" value="non">
이름을 썼습니다.<input type="radio"  name="nameWrite "   onclick="changenameWrite(this.value)"  value="wrote"><br>
<input type="hidden" id="Question3" value="${BookShop.nameWrite}">
<label id="label3">${BookShop.nameWrite}</label><br>
<br>
<label>페이지 상태</label><br>
없음<input type="radio"  name="page " onclick="changepage(this.value)" value="non">
반색<input type="radio"  name="page " onclick="changepage(this.value)" value="blank">
회손<input type="radio"  name="page " onclick="changepage(this.value)" value="damage">
둘다<input type="radio"  name="page " onclick="changepage(this.value)" value="all"><br>
<input type="hidden" id="Question4" value="${BookShop.page}">
<label id="label4">${BookShop.page}</label><br>
<br>
<label>거래 선택</label><br>
택배만<input type="radio"  name="meansOftransaction " onclick="changemeansOftransaction(this.value)" value="parcelService">
직거래만<input type="radio"  name="meansOftransaction " onclick="changemeansOftransaction(this.value)"  value="directTransaction">
둘다가능<input type="radio"  name="meansOftransaction " onclick="changemeansOftransaction(this.value)"  value="all"><br>
<input type="hidden" id="Question5" value="${BookShop.meansOftransaction}">
<label id="label5">${BookShop.meansOftransaction}</label><br>

<br>
<label>가격 : </label><input id="price" type="text" value="${BookShop.price}">

<br><br>

<input type="file" name="uploadFile"  id="uploadFile"  multiple><br>
<div id="preview">
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

</div>

<br><br>
<button onclick=" bookShopmodify()"> 수정하기</button><br>



</div>




</body>
</html>