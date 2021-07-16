<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }/timetable"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>애니타임</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://kit.fontawesome.com/46c1a740db.js" crossorigin="anonymous"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>
<script type="text/javascript">
	$(".navdiv").hide();
</script>
<style type="text/css">
.contentBox{
	display: flex;
	justify-content: column; 
}
li{
	display: block;
}
</style>
</head>
<body>
<div class="contentBox">
	<aside class="box" style="width:20%;">
		<div class="title">
			<h4 style="text-align:center; background-color:beige;">기본<br>시간표</h4>
			<ol class="button">
			</ol>
		</div>
		<div class="select">
			<ol>
				<li><a class="w3-button" href="${contextPath }/sem1">1학년 1학기</a></li>
				<li><a class="w3-button" href="${contextPath }/sem2">1학년 2학기</a></li>
				<li><a class="w3-button" href="${contextPath }/sem3">2학년 1학기</a></li>
				<li><a class="w3-button" href="${contextPath }/sem4">2학년 2학기</a></li>
				<li><a class="w3-button" href="${contextPath }/sem5">3학년 1학기</a></li>
				<li><a class="w3-button" href="${contextPath }/sem6">3학년 2학기</a></li>
			</ol>
		</div>
	</aside>
	<div id="box" style="width:80%;">
		<div id="my_table">
			<table class="tablehead" border="1">
				<thead>
					<tr style="text-align:center;">
						<th></th>
						<td style="width:120px;">월</td>
						<td style="width:120px;">화</td>
						<td style="width:120px;">수</td>
						<td style="width:120px;">목</td>
						<td style="width:120px;">금</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<div class="times">
								<div class="time" style="height:60px;top:400px;">오전 8시</div>
								<div class="time" style="height:60px;top:460px;">오전 9시</div>
								<div class="time" style="height:60px;top:520px;">오전 10시</div>
								<div class="time" style="height:60px;top:580px;">오전 11시</div>
								<div class="time" style="height:60px;top:640px;">오후 12시</div>
								<div class="time" style="height:60px;top:700px;">오후 1시</div>
								<div class="time" style="height:60px;top:760px;">오후 2시</div>
								<div class="time" style="height:60px;top:820px;">오후 3시</div>
								<div class="time" style="height:60px;top:880px;">오후 4시</div>
								<div class="time" style="height:60px;top:940px;">오후 5시</div>
								<div class="time" style="height:60px;top:1000px;">오후 6시</div>
								<div class="time" style="height:60px;top:1060px;">오후 7시</div>
								<div class="time" style="height:60px;top:1120px;">오후 8시</div>
								<div class="time" style="height:60px;top:1180px;">오후 9시</div>
								<div class="time" style="top:1240px;">오후 10시</div>
							</div>
						</th>
						<td colspan="5">
							기본 시간표가 설정되어 있지 않습니다. 기본시간표를 설정해주세요.
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>