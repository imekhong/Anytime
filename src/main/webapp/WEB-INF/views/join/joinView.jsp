<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="${pageContext.request.contextPath }/resources/js/JoinView.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/Join.js"></script>
<link
	href="${pageContext.request.contextPath }/resources/css/JoinView.css"
	rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>AnyTime JoinView</title>
<script type="text/javascript">
var authCode = "";

function sendSms(){
	console.log("clicked sendSms");
	
	if($('#phoneNumber').val() === "" ){
		alert('휴대폰번호를 입력해주세요');
	}else{
		$('#sendSmsBtn').prop('disabled', true);
		$.ajax({
			url: getContextPath() + '/member/auth/sendsms',
		    type: "POST",
		    data: {num: $('#phoneNumber').val()},
		    success: function (result) {
				alert('문자를 확인해주세요. '+result.code);
				authCode = result.code;
			}      
		})
	}
}

function authSms(){
	alert('clicked authSms: '+authCode);
	
	var userCode = $('#smsAuthNumber').val();
	if(userCode === "" ){
		alert('인증번호를 입력해주세요');
	}else if(userCode != authCode){
		alert('인증번호가 틀렸습니다.');
		$('#sendSmsBtn').prop('disabled', false);
	}else{
		alert('인증되었습니다.');
		$.ajax({
			url: getContextPath() + '/member/auth/sms',
		    type: "GET",
		    success: function () {
		    	$('#authSmsBtn').prop('disabled', true);
		    	$('#authEmailBtn').prop('disabled', true);
			}      
		})
	}
}
</script>
</head>
<body>


	<div style="margin-left: 30%" class="joinForm"  >
	
	<form id="joinfrm" >
	<h1>애니타임 회원가입</h1>
		<br>
		<h4>언제, 어디서나 학생들과 함께.......</h4>
		<br> 
		<span class="select"> 
			<select id="year" title="년도" onchange="handleOnChange(this)"></select><br>
		</span> <br>
		<label> 입학년도 : </label> <input id="enterYear"  name="enterYear"  type="text" readonly="readonly" value="입학년도"><br><br> 
		<input type="button" readonly="readonly" onclick="showPopup()" value="학교 검색하기"><br>
		
		
		<script type="text/javascript">
		
		</script>
		<script src="${pageContext.request.contextPath }/resources/js/SearchSchool.js"></script>
		
		<input type="text" id="parSchoolName"  name="parSchoolName"  placeholder="학교이름"><br>
		<input type="text"  id="parSchooladd"  name="parSchooladd" placeholder="학교주소"><br><br>
		 
		
		<c:choose>
			<c:when test="${UserJoinAuth == 'general'}">
				<c:if test="${emailauth == null && smsauth == null }">
				<!-- 
					<button class="w3-button" onclick="showEmail()">이메일로 인증하기</button>
					<button class="w3-button" onclick="showSms()">문자로 인증하기</button>
				-->
					<div id="emailAuthBox">
						<input type="text"  id="generalEmail"  name="generalEmail" placeholder="email@example.com">
						<input type="button" onclick="sendEmail()" id="authEmailBtn" value="인증메일 보내기">
					</div>
					<br>
					<div id="smsSendBox">
						<input type="text"  id="phoneNumber"  name="phoneNumber" placeholder="01012345678">
						<input type="button" onclick="sendSms()" id="sendSmsBtn" value="인증문자 보내기">
					</div>
					<div id="smsAuthBox">
						<input type="text"  id="smsAuthNumber"  name="smsAuthNumber" placeholder="인증번호 입력">
						<input type="button" onclick="authSms()" id="authSmsBtn" value="인증하기">
					</div>
				</c:if>
				<br>
				<c:if test="${emailauth =='emailauth'}">
					<input type="email"  id="email"  name="email" value="${OKemail }"><br><br>
				</c:if>
			<!-- 일반회원일때는 비밀번호를 받아야하니깐 -->
				<label> 비밀번호 : </label> <input type="password" id="pwd" name="pwd"  placeholder="password"><br>
			</c:when>
			<c:otherwise>
				<label>이메일 : </label> <input type="email" id="email" name="email" value="${email}"><br>
			</c:otherwise>
		</c:choose>
		
		<label>이름 : </label> <input type="text" id="nickname" name="nickname" value="${nickname}"><br>
		<label>나이 : </label> <input type="text"  id="age"  name="age"  placeholder="나이를 입력해주세요"><br>
		<c:choose>
			<c:when test="${UserJoinAuth == 'general'}">
				<label>연령대 :</label>  <input type="text"  id="ageGroup"  name="ageGroup"  value="${agerange}" ><br>
			</c:when>
			<c:otherwise>
				<label>연령대 :</label>  <input type="text"  id="ageGroup"  name="ageGroup"  value="${agerange}" readonly="readonly"><br>
			</c:otherwise>
		</c:choose>
		<label>현재학년 :</label> <input type="text"  id="grade"  name="grade"  placeholder="현재학년"><br>
		<input type="text"  id="UserJoinAuth"  name="UserJoinAuth"  value="${UserJoinAuth}" hidden="hidden"><br>
		<br>
		
		
	<c:choose>
	<c:when test="${UserJoinAuth == 'general'}">
	<c:if test="${ emailauth != null || smsauth != null}">
	<!--  임시방편 나중에 카카오 로그인 버튼으로 대체할생각 -->
		<input type="button"  style="width: 300px; height: 80px;" value="일반회원가입하기" onclick="SaveUser()" ><br>
	</c:if>
	</c:when>
	<c:when test="${UserJoinAuth == 'naverJoin'}">
	<input type="button"  style="width: 300px; height: 80px;" value="네이버로회원가입하기" onclick="SaveUser()" ><br>
	</c:when>
	<c:otherwise>
	<!--  임시방편 나중에 카카오 로그인 버튼으로 대체할생각 -->
	<c:if test="${UserJoinAuth == 'kakaoJoin' }">
		<input type="button"  style="width: 300px; height: 80px;" value="카카오로회원가입하기" onclick="SaveUser()" ><br>
	</c:if>
	</c:otherwise>
	
	
	</c:choose>
	
	
	</form>
		
	</div>

</body>

</html>