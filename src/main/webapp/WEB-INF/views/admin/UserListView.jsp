<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="contentBox">
 <table border="1" width="50%"height="200"cellspacing="5">
            <caption>유저 리스트</caption>
            <thead>
                <tr align="center" bgcolor="white">
                    <td></td>
                    <th>학교</th>
                    <th>닉네임</th>
                    <th>권한 상태</th>
                </tr>
            </thead>

            <tbody>
            <!--  여기를 반복시키기 -->
                <c:forEach var="userList"  items="${AlluserList }"> 
                <tr align="center" bgcolor="white">
                    <th>${ userList.email}</th>
                    <td>${userList.school}</td>
                    <td>${userList.username}</td>
                    <td><a href="changePermissions/${ userList.email}/${userList.permissions}">${userList.permissions}</a> </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <br><br>
        <a href="${pageContext.request.contextPath }/generalUser/logout">어드민로그아웃</a>
</div>
</body>
</html>