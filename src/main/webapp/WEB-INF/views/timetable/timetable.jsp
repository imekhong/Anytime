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
	
	function PrintDiv(){
		$(".fas").hide();
		html2canvas(document.getElementById("my_table")).then(function(canvas){
			var myImage = canvas.toDataURL();
			downloadURI(myImage, "애니타임_시간표.png") 
		});
		$(".fas").show();
	}
	
	function downloadURI(uri, name){
		var link = document.createElement("a")
		link.download = name;
		link.href = uri;
		document.body.appendChild(link);
		link.click();
	}
	
	$(function(){		
		$("#add_schedule").click(function(){
			$("#add_wrap").show();
		});
		$("#close").click(function(){
			$("#subject").val("");
			$("input:radio[name='day']").prop("checked",false);
			$("#start_time").val("시작시간");
			$("#start_minute").val("시작분");
			$("#end_time").val("끝 시간");
			$("#end_minute").val("끝 분");
			$("#add_wrap").hide();
		});
		$("#mod_close").click(function(){
			$("#mod_subject").val("");
			$("input:radio[name='mod_day']").prop("checked",false);
			$("#mod_start_time").val("");
			$("#mod_start_minute").val("");
			$("#mod_end_time").val("");
			$("#mod_end_minute").val("");
			$("#mod_itemNo").val("");
			$("#mod_wrap").hide();
		});
		$("#set_close").click(function(){
			$("#set_wrap").hide();
		});
		
		$("#add_btn").click(function(){
			var radio = $('input[name="day"]:checked').val();
			var st = $("#start_time").val();
			var sm = $("#start_minute").val();
			var et = $("#end_time").val();
			var em = $("#end_minute").val();
			var stm = st*60+sm*1;
			var etm = et*60+em*1;
			if($("#subject").val()===""){
				alert("과목명을 입력해주세요.");
			}else if(radio == null){
				alert("요일을 골라주세요.");
			}else if(st === "시작시간"){
				alert("시작시간을 골라주세요.");
			}else if(sm === "시작분"){
				alert("시작분을 골라주세요.");
			}else if(et === "끝 시간"){
				alert("끝시간을 골라주세요.");
			}else if(em === "끝 분"){
				alert("끝분을 골라주세요.");
			}else if( stm >= etm ){			//입력시간 체크
				alert("끝시간이 시작시간보다 빠르거나 같습니다.");
			}else if( checkOverlap(st, sm, et, em, radio) == false ){	//중복확인
				alert("시간표가 중복 됩니다.");
			}else{
				$.ajax({
	    			url: "${contextPath}/add",
	                type: "POST",
	                data: {
	                	id : '${userId}',
	                	semester : '${semester}',
	                	subject : $("#subject").val(),
	                	day : radio,
	                	start_time : $("#start_time").val(),
	                	start_minute : $("#start_minute").val(),
	                	end_time : $("#end_time").val(),
	                	end_minute : $("#end_minute").val()
	                },
	                success: function () {
	                	alert("등록완료");
	                	$("#add_wrap").hide();
	                	location.reload();
	                },
	    		})
			}
		});
		
		$("#modify_btn").click(function(){
			var radio = $('input[name="mod_day"]:checked').val();
			var st = $("#mod_start_time").val();
			var sm = $("#mod_start_minute").val();
			var et = $("#mod_end_time").val();
			var em = $("#mod_end_minute").val();
			var stm = st*60+sm*1;
			var etm = et*60+em*1;
			if($("#mod_subject").val()===""){
				alert("과목명을 입력해주세요.");
			}else if(radio==null){
				alert("요일을 골라주세요.");
			}else if(st === "시작시간"){
				alert("시작시간을 골라주세요.");
			}else if(sm === "시작분"){
				alert("시작분을 골라주세요.");
			}else if(et === "끝 시간"){
				alert("끝시간을 골라주세요.");
			}else if(em === "끝 분"){
				alert("끝분을 골라주세요.");
			}else if( stm >= etm ){			//입력시간 체크
				alert("끝시간이 시작시간보다 빠르거나 같습니다.");
			}else if( checkOverlap(st, sm, et, em, radio) == false ){	//중복확인
				alert("시간표가 중복 됩니다.");
			}else{
				$.ajax({
	    			url: "${contextPath}/modify",
	                type: "POST",
	                data: {
	                	itemNo: $("#mod_itemNo").val(),
	                	id : '${userId}',
	                	semester : '${semester}',
	                	subject : $("#mod_subject").val(),
	                	day : radio,
	                	start_time : $("#mod_start_time").val(),
	                	start_minute : $("#mod_start_minute").val(),
	                	end_time : $("#mod_end_time").val(),
	                	end_minute : $("#mod_end_minute").val()
	                },
	                success: function () {
	                	alert("수정완료");
	                	$("#mod_wrap").hide();
	                	location.reload();
	                },
	    		})
			}
		});
		
		$("#set_btn").click(function(){
			$.ajax({
    			url: "${contextPath}/setting",
                type: "POST",
                data: {
                	id: '${userId}',
                	//scope: $('input[name="openScope"]:checked').val(),
                	scope: 'public',
                	main : $("#setMain").is(":checked"),
                	semester: '${semester}'
                },
                success: function () {
                	alert("저장완료");
                	$("#set_wrap").hide();
                	location.reload();
                },
    		})
		});

		$("#reset_btn").click(function(){
			$.ajax({
    			url: "${contextPath}/reset",
                type: "POST",
                data: {
                	id: '${userId}',
                	semester: '${semester}'
                },
                success: function () {
                	alert("초기화 완료");
                	$("#set_wrap").hide();
                	location.reload();
                },
    		})
		});
		
		function getRandomColor(){
			return "#" + Math.floor(Math.random() * 16777213 + 1).toString(16); //0x000001이상 0xffffff미만
		}
		
		// 시간표 중복 체크
		function checkOverlap(st, sm, et, em, day){
			var flag = true;
			var list = JSON.parse('${timetableList}');
			for(var i=0; i<list.length; i++){
				if(day == list[i].day){
					var stm = st*60+sm*1;
					var etm = et*60+em*1;
					var e_stm = list[i].start_time*60 + list[i].start_minute;
					var e_etm = list[i].end_time*60 + list[i].end_minute;
					if( (stm>e_stm && stm<e_etm) || (etm>e_stm && etm<e_etm) ){
						flag = false;
						return flag;
					}
				}
			}
			return flag;			
		}
		
		function showTable(){
			var setTitle;
			if('${semester}' === 'sem1'){
				setTitle = '1학년 1학기';
			}else if('${semester}' === 'sem2'){
				setTitle = '1학년 2학기';
			}else if('${semester}' === 'sem3'){
				setTitle = '2학년 1학기';
			}else if('${semester}' === 'sem4'){
				setTitle = '2학년 2학기';
			}else if('${semester}' === 'sem5'){
				setTitle = '3학년 1학기';
			}else if('${semester}' === 'sem6'){
				setTitle = '3학년 2학기';
			}
			setTitle += '<br>시간표';
			$("#title").html(setTitle);
			
			var list = JSON.parse('${timetableList}');
			for(var i=0; i<list.length; i++){
				var day = "#cols_"+list[i].day;
				var topPosi = 84+(list[i].start_time * 60)+list[i].start_minute;
				var heightSize = (list[i].end_time - list[i].start_time)*60 + (list[i].end_minute-list[i].start_minute);
				var colour = getRandomColor();
				var str = "<div style='position:absolute;width:123px;height:"+heightSize+"px;top:"+topPosi+"px;";
				str += "background-color:"+colour+";'>";
				str += "<strong>"+list[i].subject;
				str += "</strong>&nbsp;<i class='fas fa-edit' onclick='modifyShow("+JSON.stringify(list[i])+")'></i>&nbsp;";
				str += "<i class='fas fa-times' onclick='deleteItem("+list[i].itemNo+")'></i><br>";
				str += "<small>"+(list[i].start_time+8)+":"+list[i].start_minute+"~"+(list[i].end_time+8)+":"+list[i].end_minute+"</small></div>";
				var setting = $(str).addClass('subject');
				$(day).append(setting);
			}
		}
		showTable();
	});
	
	function deleteItem(data){
		$.ajax({
			url: "${contextPath}/delete/${userId}/"+data,
            type: "POST",
            success: function () {
            	alert("삭제완료");
            	location.reload();
            },
		})
	}
	
	function modifyShow(data){
		$("#mod_subject").val(data.subject);
		$("input:radio[name='mod_day']:input[value='"+data.day+"']").attr("checked", true);
		$("#mod_start_time").val(data.start_time);
		$("#mod_start_minute").val(data.start_minute);
		$("#mod_end_time").val(data.end_time);
		$("#mod_end_minute").val(data.end_minute);
		$("#mod_itemNo").val(data.itemNo);
		$("#mod_wrap").show();	
	}
	
	function showSet() {
		//$("input:radio[name='openScope']:input[value='${scope}']").attr("checked", true);
		if('${setMain}' === 'true'){
			$("input:checkbox[id='setMain']").attr('checked', true);
		}
		$("#set_wrap").show();
	}
</script>
<style type="text/css">
.contentBox{
	display: flex;
	justify-content: column; 
}
li{
	display: block;
}
#add_wrap, #mod_wrap, #set_wrap{
	display: none;
	position: fixed; z-index: 9;
	margin: 0 auto;
	top: 0; left: 0; right: 0;
	width: 100%; height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
}
#add_table, #mod_table, #set_table{
	display: flex;
	margin: 0 auto;
	margin-top: 250px;
	z-index: 10;
	flex-direction: column;
	justify-content: flex-start;
	align-items: center;
}

.item1{
	display: flex;
	width: 500px;
	height: 400px;
	background-color: white;
}

.item2{
	width: 96%;
	padding: 30px;
	align-items: center;
}
</style>
</head>
<body>
<div class="contentBox">
	<aside class="box" style="width:20%;">
		<div class="title">
			<h4 id="title" style="text-align:center; background-color:beige;"></h4>
			<ol class="button">
				<li class="save_btn"><a class="w3-button" onclick="PrintDiv();">이미지저장</a></li>
				<li><input type="button" class="w3-button" onclick="showSet();" value="설정"></li>
				<li><a class="w3-button" id="add_schedule">새 수업 추가</a></li>
			</ol>
			<hr>
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
						<td>
							<div class="cols" id="cols_mon" style="width:123px;"></div>
						</td>
						<td>
							<div class="cols" id="cols_tue" style="width:123px;"></div>
						</td>
						<td>
							<div class="cols" id="cols_wed" style="width:123px;"></div>
						</td>
						<td>
							<div class="cols" id="cols_thu" style="width:123px;"></div>
						</td>
						<td>
							<div class="cols" id="cols_fri" style="width:123px;"></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="add_wrap">
		<div id="add_table">
			<div class="item1">
				<div class="item2">
					<b style="color:fuchsia;">새 수업 추가</b><br><hr>
					<form>
						<div class="form-group">
					    	<label>과목명(필수):</label>
					    	<input type="text" class="form-control" placeholder="예)영어" id="subject">
					  	</div>
					  	<hr>
					  	<label>요일:</label><br>
					  	<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" id="monday" name="day" value="mon">
							<label class="custom-control-label" for="monday">MON</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" class="custom-control-input" id="tuesday" name="day" value="tue">
						  <label class="custom-control-label" for="tuesday">TUE</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" class="custom-control-input" id="wednesday" name="day" value="wed">
						  <label class="custom-control-label" for="wednesday">WED</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" class="custom-control-input" id="thursday" name="day" value="thu">
						  <label class="custom-control-label" for="thursday">THU</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" class="custom-control-input" id="friday" name="day" value="fri">
						  <label class="custom-control-label" for="friday">FRI</label>
						</div>
						<hr>
						<label>시간:</label><br>
						<select id="start_time" class="custom-select" style="width:100px;">
							<option selected>시작시간</option>
							<option value="0">오전8시</option>
							<option value="1">오전9시</option>
							<option value="2">오전10시</option>
							<option value="3">오전11시</option>
							<option value="4">오후12시</option>
							<option value="5">오후1시</option>
							<option value="6">오후2시</option>
							<option value="7">오후3시</option>
							<option value="8">오후4시</option>
							<option value="9">오후5시</option>
							<option value="10">오후6시</option>
							<option value="11">오후7시</option>
							<option value="12">오후8시</option>
							<option value="13">오후9시</option>
							<option value="14">오후10시</option>
						</select>
						<select id="start_minute" class="custom-select" style="width:90px;">
							<option selected>시작분</option>
							<option value="0">0분</option>
							<option value="5">5분</option>
							<option value="10">10분</option>
							<option value="15">15분</option>
							<option value="20">20분</option>
							<option value="25">25분</option>
							<option value="30">30분</option>
							<option value="35">35분</option>
							<option value="40">40분</option>
							<option value="45">45분</option>
							<option value="50">50분</option>
							<option value="55">55분</option>
						</select>
						<b> ~ </b>
						<select id="end_time" class="custom-select" style="width:100px;">
							<option selected>끝 시간</option>
							<option value="0">오전8시</option>
							<option value="1">오전9시</option>
							<option value="2">오전10시</option>
							<option value="3">오전11시</option>
							<option value="4">오후12시</option>
							<option value="5">오후1시</option>
							<option value="6">오후2시</option>
							<option value="7">오후3시</option>
							<option value="8">오후4시</option>
							<option value="9">오후5시</option>
							<option value="10">오후6시</option>
							<option value="11">오후7시</option>
							<option value="12">오후8시</option>
							<option value="13">오후9시</option>
							<option value="14">오후10시</option>
						</select>
						<select id="end_minute" class="custom-select" style="width:90px;">
							<option selected>끝 분</option>
							<option value="0">0분</option>
							<option value="5">5분</option>
							<option value="10">10분</option>
							<option value="15">15분</option>
							<option value="20">20분</option>
							<option value="25">25분</option>
							<option value="30">30분</option>
							<option value="35">35분</option>
							<option value="40">40분</option>
							<option value="45">45분</option>
							<option value="50">50분</option>
							<option value="55">55분</option>
						</select>
						<br><hr>
						<input type="button" class="btn btn-primary" id="add_btn" value="저장">
					</form>
				</div>
				<div class="item3">
					<i id="close" class="fas fa-times-circle"></i>
				</div>
			</div>
		</div>
	</div>
	<div id="mod_wrap">
		<div id="mod_table">
			<div class="item1">
				<div class="item2">
					<b style="color:fuchsia;">수업 수정</b><br><hr>
					<form>
						<div class="form-group">
					    	<label>과목명(필수):</label>
					    	<input type="text" class="form-control" id="mod_subject">
					  	</div>
					  	<hr>
					  	<label>요일:</label><br>
					  	<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" id="mod_monday" name="mod_day" value="mon">
							<label class="custom-control-label" for="mod_monday">MON</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" class="custom-control-input" id="mod_tuesday" name="mod_day" value="tue">
						  <label class="custom-control-label" for="mod_tuesday">TUE</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" class="custom-control-input" id="mod_wednesday" name="mod_day" value="wed">
						  <label class="custom-control-label" for="mod_wednesday">WED</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" class="custom-control-input" id="mod_thursday" name="mod_day" value="thu">
						  <label class="custom-control-label" for="mod_thursday">THU</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" class="custom-control-input" id="mod_friday" name="mod_day" value="fri">
						  <label class="custom-control-label" for="mod_friday">FRI</label>
						</div>
						<hr>
						<label>시간:</label><br>
						<select id="mod_start_time" class="custom-select" style="width:100px;">
							<option value="시작시간">시작시간</option>
							<option value="0">오전8시</option>
							<option value="1">오전9시</option>
							<option value="2">오전10시</option>
							<option value="3">오전11시</option>
							<option value="4">오후12시</option>
							<option value="5">오후1시</option>
							<option value="6">오후2시</option>
							<option value="7">오후3시</option>
							<option value="8">오후4시</option>
							<option value="9">오후5시</option>
							<option value="10">오후6시</option>
							<option value="11">오후7시</option>
							<option value="12">오후8시</option>
							<option value="13">오후9시</option>
							<option value="14">오후10시</option>
						</select>
						<select id="mod_start_minute" class="custom-select" style="width:90px;">
							<option value="시작분">시작분</option>
							<option value="0">0분</option>
							<option value="5">5분</option>
							<option value="10">10분</option>
							<option value="15">15분</option>
							<option value="20">20분</option>
							<option value="25">25분</option>
							<option value="30">30분</option>
							<option value="35">35분</option>
							<option value="40">40분</option>
							<option value="45">45분</option>
							<option value="50">50분</option>
							<option value="55">55분</option>
						</select>
						<b> ~ </b>
						<select id="mod_end_time" class="custom-select" style="width:100px;">
							<option value="끝 시간">끝 시간</option>
							<option value="0">오전8시</option>
							<option value="1">오전9시</option>
							<option value="2">오전10시</option>
							<option value="3">오전11시</option>
							<option value="4">오후12시</option>
							<option value="5">오후1시</option>
							<option value="6">오후2시</option>
							<option value="7">오후3시</option>
							<option value="8">오후4시</option>
							<option value="9">오후5시</option>
							<option value="10">오후6시</option>
							<option value="11">오후7시</option>
							<option value="12">오후8시</option>
							<option value="13">오후9시</option>
							<option value="14">오후10시</option>
						</select>
						<select id="mod_end_minute" class="custom-select" style="width:90px;">
							<option value="끝 분">끝 분</option>
							<option value="0">0분</option>
							<option value="5">5분</option>
							<option value="10">10분</option>
							<option value="15">15분</option>
							<option value="20">20분</option>
							<option value="25">25분</option>
							<option value="30">30분</option>
							<option value="35">35분</option>
							<option value="40">40분</option>
							<option value="45">45분</option>
							<option value="50">50분</option>
							<option value="55">55분</option>
						</select>
						<br><hr>
						<input type="hidden" id="mod_itemNo">
						<input type="button" class="btn btn-primary" id="modify_btn" value="수정">
					</form>
				</div>
				<div class="item3">
					<i id="mod_close" class="fas fa-times-circle"></i>
				</div>
			</div>
		</div>
	</div>
	<div id="set_wrap">
		<div id="set_table">
			<div class="item1" style="height: 250px;">
				<div class="item2">
					<b style="color:fuchsia;">시간표 설정</b>
					<div class="w3-right">
						<input type="button" class="btn btn-light" id="reset_btn" value="시간표 전체 초기화">
					</div>
					<br><hr>
					<form>
						<!-- 
						<label>공개범위: </label><br>
						<div class="form-check-inline">
							<label class="form-check-label">
								<input type="radio" class="form-check-input" id="public" name="openScope" value="public">전체공개
							</label>
						</div>
						<div class="form-check-inline">
							<label class="form-check-label">
								<input type="radio" class="form-check-input" id="secret" name="openScope" value="secret">친구에게만 공개
							</label>
						</div>
						<div class="form-check-inline">
							<label class="form-check-label">
								<input type="radio" class="form-check-input" id="private" name="openScope" value="private">비공개
							</label>
						</div>
						<hr>
						 -->
						<div class="form-check">
							<label class="form-check-label">
								<input type="checkbox" class="form-check-input" id="setMain">기본시간표 설정
							</label>
						</div>
						<input type="button" class="btn btn-primary w3-right" id="set_btn" value="저장">
					</form>
				</div>
				<div class="item3">
					<i id="set_close" class="fas fa-times-circle"></i>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>