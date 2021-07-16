<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath }/resources/js/chat.js"></script>
<meta charset="UTF-8">
<title>ChatSend</title>
</head>
<body>
<!--  보낸사람이 send ,받는사람이 receive -->
<div class="contentBox">

<input id="receiveUser" type="hidden" value="${receiveUser }"><br> <!-- 지금전송받을 유저의 사람 값을 숨겨놓는 인풋태그 -->
<input id="sendUser" value="${userId}"><br><!-- 전송할 유저가 보내는걸 알기위해-->
<input id="chatcontent" type="text" ><br><!-- 쪽지 내용 -->
<button onclick="sendChat()">작성</button><br>

</div>

</body>
</html>