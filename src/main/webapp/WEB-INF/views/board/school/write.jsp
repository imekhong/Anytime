<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }/board/school"/>
<!DOCTYPE html>
<html>
<head>
<title>애니타임</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/46c1a740db.js" crossorigin="anonymous"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script type="text/javascript">
	$(function(){
		function setBoardName(){
			var str;
			if('${section}' === 's'){
				str = "비밀";
			}else if('${section}' === 'c'){
				str = "동아리";
			}else if('${section}' === 'i'){
				str = "학교정보";
			}else if('${section}' === 'f'){
				str = "진학진로";
			}
			str += "게시판";
			$("#boardName").html(str);
		}
		setBoardName();
	});
</script>
</head>
<body>
<div class="contentBox">
<h1 id="boardName" style="color:skyblue;"></h1><br>
	<div class="form-group">
		<form action="${contextPath }/write_save" method="post">
			<input type="hidden" name="writerId" value="${userId }">
			<input type="hidden" name="nickname" value="${userNickname }">
			<input type="hidden" name="school" value="${userSchool }">
			<div class="form-group" style="width:600px;">
				<input type="text" name="subject" class="form-control" placeholder="제목">
			</div>
			<input type="hidden" name="section" value="${section}">			
			<script>
				$(document).ready(function() {
					$('#summernote').summernote({
						lang: 'ko-KR',
						width: 600,
						height: 300,                 // set editor height
						minHeight: null,             // set minimum height of editor
						maxHeight: null,             // set maximum height of editor
						focus: true,                 // set focus to editable area after initializing summernote
						placeholder: '내용을 입력하세요.',
						toolbar: [
						    // [groupName, [list of button]]
						    ['fontname', ['fontname']],
						    ['fontsize', ['fontsize']],
						    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
						    ['color', ['color']],
						    ['table', ['table']],
						    ['para', ['ul', 'ol', 'paragraph']],
						    ['insert',['picture','link']],
						    ['view', ['help']]
						],
						fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
						fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','60']
				     });		
				});
	    	</script>
			<textarea id="summernote" name="content"></textarea>
			<div class="form-group" style="width:600px;">
				<input type="text" name="tag" class="form-control" placeholder="#태그를 입력하세요. #으로 구분하여 입력.(최대 5개)">
			</div>
			<div class="form-group form-check">
    			<label class="form-check-label">
					<input type="checkbox" class="form-check-input" name="unknown"> &nbsp;&nbsp;&nbsp;&nbsp;익명이
				</label>
			</div>
			<div class="w3-bar">
				<button type="button" class="w3-bar-item w3-button w3-border" onclick="location.href='${contextPath}/list/${section }'">
					<i class="fa fa-bars"></i> 글 목록
				</button>
				<button type="submit" class="w3-bar-item w3-button w3-border">
						<i class="far fa-save"></i> 저장
				</button>
				<button type="button" class="w3-bar-item w3-button w3-border" onclick="history.back();">
					<i class="fa fa-remove"></i> 취소
				</button>
			</div>
		</form>
    </div>
</div>
</body>
</html>