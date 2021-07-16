<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }/board/common"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>애니타임</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/46c1a740db.js" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
		// 추천버튼 클릭시(추천 추가 또는 추천 제거)
		$("#like_update").click(function(){
			$.ajax({
				url: "${contextPath}/likeUpdate",
	            type: "POST",
	            data: {
	                no: ${board.postNo},
	                id: '${userId}'
	            },
	            success: function () {
			        likeCount();
	            }
			})
		});
		
		// 게시글 추천수
	    function likeCount() {
			$.ajax({
				url: "${contextPath}/likeCount",
	            type: "POST",
	            data: {
	                no: ${board.postNo}
	            },
	            success: function (data) {
	            	$(".like_count").html(data.count);
	            }
			})
	    };
  		likeCount(); // 처음 시작했을 때 실행되도록 해당 함수 호출
  		
 		// 댓글 입력
	    $("#reply_btn").click(function(){
	    	if($("#replyContent").val().trim() === ""){
	    		alert("댓글을 입력하세요.");
	    		$("#replyContent").val("").focus();
	    	}else{
	    		var nick;
	    		if($('input:checkbox[id="unknown"]').is(':checked') == true){
	    			nick = '익명이';
	    		}else{
	    			nick = $("#nickname").val();
	    		}
	    		$.ajax({
	    			url: "${contextPath}/reply",
	                type: "POST",
	                data: {
	                	postNo : $("#postNo").val(),
	                	writerId : $("#writerId").val(),
	                	nickname : nick,
	                	school : $("#school").val(),
	                    replyContent : $("#replyContent").val(),
	                },
	                success: function () {
	                	alert("댓글 등록 완료");
	                	$("#replyContent").val("");
	                	location.reload();
	                },
	    		})
	    	}
	    });
  		
  		// 댓글 쓰기 부분
  		$("#showReplyForm").on("click",function(){
  			$("#replyForm").slideToggle(100);
  		});
  		$("#replyForm").hide();
  		
	});
	
	// 대댓글 쓰기
	function showReReply(data1, data2, data3){
		var id = "#reReplyForm_"+data1;
		var flag = ($(id).html() == "");
		$(".reReplyForm").html("");
		if(flag === true){
			var make = "<div class='w3-border w3-padding'>";
	    	make += "<span class='w3-left'><i class='fa fa-user w3-padding-16'></i>${userNickname }</span>";
	    	make += "<form><span class='form-group form-check w3-right'><label class='form-check-label'>";
	    	make += "<input type='checkbox' class='form-check-input' name='unknown' id='unknown'>&nbsp;익명이</label></span>";
	    	make += "<input type='hidden' name='postNo' id='postNo' value='${board.postNo }'>";
	    	make += "<input type='hidden' name='writerId' id='writerId' value='${userId }'>";
	    	make += "<input type='hidden' name='nickname' id='nickname' value='${userNickname }'>";
	    	make += "<input type='hidden' name='school' id='school' value='${userschoolname }'>";
	    	make += "<input type='hidden' id='replyNo' value='"+data1+"'>";
			make += "<input type='hidden' id='parentNo' value='"+data2+"'>";
			make += "<input type='hidden' id='depth' value='"+data3+"'>";
	    	make += "<textarea rows='5' cols='20' class='w3-input w3-border' placeholder='댓글 작성' name='replyContent' id='reReplyContent'></textarea>";
			make += "<input type='button' class='w3-button w3-border' onClick='reReReply_btn()' value='댓글 등록'></form></div>";
			$(id).html(make);
		}else if(flag === false){
			$(id).html("");
		}
	}
	
	function reReReply_btn(){
		if($("#reReplyContent").val().trim() === ""){
    		alert("댓글을 입력하세요.");
    		$("#reReplyContent").val("").focus();
    	}else{
    		var nick;
    		if($('input:checkbox[id="unknown"]').is(':checked') == true){
    			nick = '익명이';
    		}else{
    			nick = $("#nickname").val();
    		}
    		$.ajax({
    			url: "${contextPath}/reReply",
                type: "POST",
                data: {
                	postNo : $("#postNo").val(),
                	replyNo : $("#replyNo").val(),
                	parentNo : $("#parentNo").val(),
                	depth : $("#depth").val(),
                	writerId : $("#writerId").val(),
                	nickname : nick,
                    replyContent : $("#reReplyContent").val(),
                    school : $("#school").val()
                },
                success: function () {
                	alert("댓글 등록 완료");
                	$("#reReplyContent").val("");
                	location.reload();
                }
    		})
    	}
	}
	
	//댓글 수정
	function modifyReply(data1, data2){
		$("#modify").attr('disabled', true);
		$("#replyContent_"+data1).html("");
		var id = "#reReplyForm_"+data1;
		if($(id).html() == ""){
			var make = "<form>";
	    	make += "<input type='hidden' name='nickname' id='nickname' value='${userNickname }'>";
	    	make += "<input type='hidden' id='replyNo' value='"+data1+"'>";
	    	make += "<textarea rows='5' cols='20' class='w3-input w3-border' name='replyContent' id='modifyContent'>"+data2+"</textarea>";
	    	make += "<span class='form-group form-check'><label class='form-check-label'>";
	    	make += "<input type='checkbox' class='form-check-input' name='unknown' id='unknown'>&nbsp;익명이</label></span>";
			make += "<input type='button' class='w3-button w3-border' onClick='modifyReply_btn()' value='댓글 등록'>";
			make += "<input type='button' class='w3-button w3-border' id='modifyCancel' value='취소'></form>";
			$(id).html(make);
		}else{
			$(id).html("");
		}
		$("#modifyCancel").on("click", function(){
			$(id).html("");
			$("#replyContent_"+data1).html(data2);
			$("#modify").attr('disabled', false);
		});
	}
	
	function modifyReply_btn(){
		if($("#modifyContent").val().trim() === ""){
    		alert("댓글을 입력하세요.");
    		$("#modifyContent").val("").focus();
    	}else{
    		var nick;
    		if($('input:checkbox[id="unknown"]').is(':checked') == true){
    			nick = '익명이';
    		}else{
    			nick = $("#nickname").val();
    		}
    		$.ajax({
    			url: "${contextPath}/modifyReply",
                type: "POST",
                data: {
                	replyNo : $("#replyNo").val(),
                	writerId : $("#writerId").val(),
                	nickname : nick,
                    replyContent : $("#modifyContent").val()
                },
                success: function () {
                	alert("수정 완료");
                	location.reload();
                }
    		})
    	}
	}
	
	// 댓글 삭제
	function deleteReply(data1, data2){
		$.ajax({
			url: "${contextPath}/deleteReply",
            type: "GET",
            data: {
            	replyNo : data1,
            	depth : data2
            },
            success: function () {
            	alert("삭제 완료");
            	location.reload();
            }
		})
	}
</script>
</head>
<body>
<div class="contentBox">
<h1 style="color:skyblue;">자유게시판 글 보기</h1><br>
	<div class="w3-main w3-margin-bottom">
		<div class="w3-bar">
			<button type="button" class="w3-bar-item w3-button w3-border" onclick="history.back();">
				<i class="fa fa-bars"></i> 뒤로
			</button>
			<button type="button" class="w3-bar-item w3-button w3-border" onclick="location.href='${contextPath }/list'">
				<i class="fa fa-bars"></i> 글 목록
			</button>
			<!-- 로그인 하였을때 -->
			<c:if test="${ userId != null && loginuserAuth != 'admin' }">
				<button type="button" class="w3-bar-item w3-button w3-border" onClick="location.href='${contextPath }/write'">
					<i class="fa fa-pencil-square-o"></i> 새 글 쓰기
				</button>
			</c:if>
			<!--작성자 일때  -->
			<c:if test="${ board.writerId == userId }">
				<button type="button" class="w3-bar-item w3-button w3-border" onClick="location.href='${contextPath }/modify?no=${board.postNo}'">
					<i class="fa fa-exchange"></i> 글 수정
				</button>
				<button type="button" class="w3-bar-item w3-button w3-border" onClick="location.href='${contextPath }/delete?no=${board.postNo}'">
					<i class="fa fa-remove"></i> 글 삭제
				</button>
			</c:if>
			<c:if test="${loginuserAuth == 'admin' }">
				<button type="button" class="w3-bar-item w3-button w3-border" onClick="location.href='${contextPath }/delete?no=${board.postNo}'">
					<i class="fa fa-remove"></i> 글 삭제
				</button>
			</c:if>
		</div>
		<!-- 게시글 내용(작성자, 작성일, 조회수, 번호, 제목, 내용) -->
		<div class="w3-article">
			<div class="w3-border w3-padding">
				No.${ board.postNo }&nbsp;&nbsp;<span class="w3-center w3-xlarge w3-text-blue">${ board.subject }</span>
				<div class="w3-right">
					<c:if test="${board.writerId != userId && loginuserAuth != 'admin'}">
						<button class="w3-button">쪽지</button>
						<button class="w3-button" id="like_update">
							<i class="fa fa-heart" style="font-size:16px;color:red"></i>
							&nbsp;<span class="like_count"></span>
						</button>
					</c:if>
					<c:if test="${board.writerId == userId || loginuserAuth == 'admin'}">
							<i class="fa fa-heart" style="font-size:16px;color:red"></i>
							&nbsp;<span class="like_count"></span>
					</c:if>
				</div><br>
				<c:if test="${tagList != null }">
					<c:forEach var="tg" items="${tagList }">
						<span><i class="fas fa-hashtag"></i>${tg}</span>
					</c:forEach>
				</c:if>
			</div>
			<div class="w3-border w3-padding">
				<i class="fa fa-user"></i>&nbsp;<strong>${board.nickname }</strong>&nbsp;&nbsp;
				<i class="fas fa-school"></i>&nbsp;<i>${board.school }고등학교</i>&nbsp;&nbsp;
				<i class="fa fa-calendar"></i>&nbsp;${board.writeDate }
				<div class="w3-right">
					<span><i class="fa fa-eye"></i>&nbsp;${ board.views }</span>&nbsp;&nbsp;
					<span><i class="far fa-comment-dots"></i>&nbsp;${replyCount }</span>&nbsp;
				</div>
			</div>
			<article class="w3-border w3-large w3-padding" style="height:500px;">${ board.content }</article>
		</div>
		<!-- 댓글 -->
		<c:forEach var="list" items="${replyList }">
			<div class="w3-border w3-padding">
				<c:if test="${list.depth == 0 }">
					<i class="fa fa-user"></i>&nbsp;
					<c:if test="${list.writerId == board.writerId }"><b style="color:orange;">${list.nickname }(글쓴이)</b></c:if>
					<c:if test="${list.writerId != board.writerId }">${list.nickname }</c:if>&nbsp;&nbsp;
					<i class="fa fa-calendar">&nbsp;</i>${list.writeDate }
					<div class="w3-right">
						<c:if test="${loginuserAuth == 'admin' }">
							<input type="button" class="w3-button" onclick="deleteReply(${list.replyNo}, ${list.depth})" value="삭제">						
						</c:if>
						<c:if test="${loginuserAuth != 'admin' }">
							<input type="button" class="w3-button" onclick="showReReply(${list.replyNo}, ${list.parentNo }, ${list.depth+1 })" value="대댓글">
							&nbsp;&nbsp;						
						</c:if>
						<c:if test="${list.writerId == userId }">
							<input type="button" class="w3-button" onclick="modifyReply(${list.replyNo}, '${list.replyContent}')" value="수정" id="modify">
							&nbsp;&nbsp;
							<input type="button" class="w3-button" onclick="deleteReply(${list.replyNo}, ${list.depth})" value="삭제">
						</c:if>
						<c:if test="${list.writerId != userId && loginuserAuth != 'admin'}">
							&nbsp;&nbsp;<button class="w3-button">쪽지</button>
						</c:if>
					</div>
					<pre id="replyContent_${list.replyNo}">${list.replyContent}</pre> 
					<div class="reReplyForm" id="reReplyForm_${list.replyNo}"></div>
				</c:if>
				<c:if test="${list.depth != 0 }">
					<div class="w3-padding">
						<i class="fas fa-angle-right"></i>&nbsp;<i class="fa fa-user"></i>&nbsp;
						<c:if test="${list.writerId == board.writerId }"><b style="color:orange;">${list.nickname }(글쓴이)</b></c:if>
						<c:if test="${list.writerId != board.writerId }">${list.nickname }</c:if>&nbsp;&nbsp;
						<i class="fa fa-calendar">&nbsp;</i>${list.writeDate }
						<div class="w3-right">
							<c:if test="${loginuserAuth == 'admin' }">
								<input type="button" class="w3-button" onclick="deleteReply(${list.replyNo}, ${list.depth})" value="삭제">						
							</c:if>
							<c:if test="${list.writerId == userId }">
								<input type="button" class="w3-button" onclick="modifyReply(${list.replyNo}, '${list.replyContent}')" value="수정" id="modify">
								&nbsp;&nbsp;
								<input type="button" class="w3-button" onclick="deleteReply(${list.replyNo}, ${list.depth})" value="삭제">
							</c:if>
							<c:if test="${list.writerId != userId && loginuserAuth != 'admin'}">
								&nbsp;&nbsp;<button class="w3-button">쪽지</button>
							</c:if>
						</div>
						<pre>${list.replyContent}</pre>
					</div>
				</c:if>
			</div>
		</c:forEach>
		<c:if test="${loginuserAuth != 'admin' }">
			<div class="w3-border w3-padding" id="showReplyForm">댓글쓰기</div>
		</c:if>
		<div class="w3-border w3-padding" id="replyForm">
			<span class="w3-left"><i class="fa fa-user w3-padding-16"></i> ${userNickname }</span>
			<form>
				<span class="form-group form-check w3-right">
	    			<label class="form-check-label">
						<input type="checkbox" class="form-check-input" name="unknown" id="unknown">&nbsp;익명이
					</label>
				</span>
				<input type="hidden" name="postNo" id="postNo" value="${board.postNo }"> 
				<input type="hidden" name="writerId" id="writerId" value="${userId }">
				<input type="hidden" name="nickname" id="nickname" value="${userNickname }"> 
				<input type="hidden" name="school" id="school" value="${userschoolname }">
				<textarea rows="5" cols="20" class="w3-input w3-border" placeholder="댓글 작성" name="replyContent" id="replyContent"></textarea>
				<input type="button" class="w3-button w3-border" id="reply_btn" value="댓글 등록">
			</form>
		</div>
	</div>
</div>
</body>
</html>