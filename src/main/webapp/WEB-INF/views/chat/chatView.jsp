<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="contentBox">

<table border="1" width="50%"height="200"cellspacing="5">
            <caption>쪽지함</caption>
            <thead>
                <tr align="center" bgcolor="white">
                    <th>쪽지내용</th>
                    <th>보낸사람</th>
                    <th>받은 날짜</th>
                    <th>답장</th>
                    <th>삭제</th>
                </tr>
            </thead>

            <tbody>
            <!--  여기를 반복시키기 -->
                  <c:forEach var="userList"  items="${selectChatList }"> 
                <tr align="center" bgcolor="white">
                    <td>${userList.chatcontent}</td>
                    <td>익명이</td>
                    <td>${ userList.createdate}</td>
                    <td><form action="${pageContext.request.contextPath }/reSendView" method="POST"><input type="hidden" name="sendUser" value="${ userList.sendUser}"><input type="submit"value="답장"> </form> </td>
                    <td><a href="${pageContext.request.contextPath }/ChatDelete/${userList.chatId}">삭제</a> </td>
                </tr>
               </c:forEach>
            </tbody>
        </table>
        <form action="${pageContext.request.contextPath }/ChatAllDelete" method="POST"><input type="hidden" id="nowUser" name="nowUser" value="${userId}"><input type="submit"value="삭제"> </form>

</div>
</body>
</html>