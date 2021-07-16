<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath }/resources/BookShopJs/WriteBookShop.js"></script>
<link href="${pageContext.request.contextPath }/resources/css/WriteBookShop.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>BookWrite</title>
</head>
<body>
<div class="MainBox">
<div class="contentBox">
<input id="SearchCode" type="text"  placeholder="책 제목,isbn,작가를 입력해주세요"><button id="SearchCodeBtn" onclick="GowriteBook()">검색</button><br> <!--  책 검색 API 사용 -->
<div id="bookSearchResultTableBox"> 
<table>
  <thead>
    <th>사진</th>
    <th>책 이름</th>
    <th>책 ISBN</th>
    <th>작가</th>
  </thead>
  <tbody id="my-book-tbody"></tbody>
</table>
</div>
<div id="bookList" >

<details id="underlineDetails">
   <summary>밑줄을 사용하셨습니까?</summary>
   <p>details, summary 같이 적용</p>
</details>

<details id="handwriteDetails">
   <summary>필기를 하셨나요?</summary>
   <p>details, summary 같이 적용</p>
</details>

<details id="coverDetails">
   <summary>책표지상태는 어떤가요?</summary>
   <p>details, summary 같이 적용</p>
</details>

<details id="namewriteDetails">
   <summary>책에 이름을 쓰셨나요?</summary>
   <p>details, summary 같이 적용</p>
</details>

<details id="pageDetails">
   <summary>책의 페이지 상태는 어떤가요?</summary>
   <p>details, summary 같이 적용</p>
</details>

<details id="meansOftransactionDetails">
   <summary>거래는 어떻게 진행하시나요?</summary>
   <p>details, summary 같이 적용</p>
</details>



</div>

<input type="file" name="uploadFile"  id="uploadFile"  multiple>
<div id="preview" ></div>

</div>
</div>
</body>
<script type="text/javascript"  src="${pageContext.request.contextPath }/resources/BookShopJs/WriteBookShopCallBack.js"></script>
</html>