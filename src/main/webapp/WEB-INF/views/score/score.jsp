<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }/score"/>
<!DOCTYPE html>
<html>
<head>
<title>애니타임</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://kit.fontawesome.com/46c1a740db.js" crossorigin="anonymous"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<style type="text/css">
ol{
	display: flex;
}
li{
	display: block;
}

#delete_line {
	border-right-style: hidden;
	border-top-style: hidden;
	border-bottom-style: hidden;
}
</style>
<script type="text/javascript">

	$(function() {
		$(".navdiv").hide();
		
		$("#viewMode").change(function(){
			var go = "http://localhost:8000/root/score/${semester }/";
			var to = $("select option:selected").val();
			go += to;
			location.href=go;
		});
		
		$("#plus_btn").click(function(){
			var count = $("#score_tbody tr").length+1;
			var str = '<tr id="'+count+'">';
			str += '<td><input type="hidden" class="itemNo" value="-1">';
			str += '<input type="text" class="form-control subject"></td>';
			str += '<td><input type="text" class="form-control scoree" placeholder="0"></td>';
			str += '<td><select class="form-control type">';
			if('${selc}' == 'all'){
				str += '<option value="middle">중간고사</option>';
				str += '<option value="final">기말고사</option>';
				str += '<option value="march">모의고사 3월</option>';
				str += '<option value="june">모의고사 6월</option>';
				str += '<option value="september">모의고사 9월</option>';
			}else if('${selc}' == 'term'){
				str += '<option value="middle">중간고사</option>';
				str += '<option value="final">기말고사</option>';
			}else if('${selc}' == 'mock'){
				str += '<option value="march">모의고사 3월</option>';
				str += '<option value="june">모의고사 6월</option>';
				str += '<option value="september">모의고사 9월</option>';
			}else{
				str += '<option value="${selc}">'+getTypeName('${selc}')+'</option>';
			}
			str += '</select></td>';
			str += '<td id="delete_line"><i class="far fa-minus-square" style="padding-top:13px;" onClick="minus_btn('+count+')"></i></td></tr>';
			$("#score_tbody").append(str);
		});
		
		$("#save_btn").click(function(){
			var arr = new Array();
			var count = $("#score_tbody tr").length;
			var change = 0;
			for(var i=1; i<=count; i++){
				var flag = false;
				var data = new Object();
				var idis = "#"+i;
				var item = $(idis+" .itemNo").val();
				var typ = $(idis+" .type").val();
				var sub = $(idis+" .subject").val();
				var scor = $(idis+" .scoree").val();
				if(item == -1){
					if(sub != "" && scor != ""){
						data.itemNo = -1;
						data.type = typ;
						data.subject = sub;
						data.score = scor;
						++change;
						flag = true;
					}else{
						alert('미완성된 항목이 있습니다.');
						return;
					}
				}else{
					if(sub != $(idis+" .ori_subject").val() || scor != $(idis+" .ori_scoree").val()
							|| typ != $(idis+" .ori_type").val()){
						if(change != 0) jsonData += ',';
						data.itemNo = item;
						data.type = typ;
						data.subject = sub;
						data.score = scor;
						flag = true;
						++change;
					}
				}
				if(flag == true) arr.push(data);
			}
			
			if(change != 0){
				$.ajax({
	    			url: "${contextPath}/save",
	                type: "POST",
	                data: {
	                	id: '${userId}',
	                	semester: '${semester}',
	                	arr: JSON.stringify(arr)
	                },
	                success: function () {
	                	alert("저장 완료");
	                	location.reload();
	                },
	    		})
			}else{
				alert('변경사항이 없습니다.');
			}
			
		});
		
		$("#reset_btn").click(function(){
			$.ajax({
    			url: "${contextPath}/reset",
                type: "POST",
                data: {
                	id: '${userId}',
                	semester: '${semester}',
                	selc: '${selc}'
                },
                success: function () {
                	alert("초기화 완료");
                	location.reload();
                }
			})
		});
		
		function show(){
			var sum = 0;
			var avg = 0;
			var list = JSON.parse('${scoreList}');
			if('${selc}' == 'all' && '${semester}' == 'sem1') drawChart();
			$("#viewMode").val('${selc}').prop("selected", true);
			$("#title").text(getTitleName());
			if( list.length == 0 ){
				var str = '<tr id="1">';
				str += '<td><input type="hidden" class="itemNo" value="-1">';
				str += '<input type="text" class="form-control subject"></td>';
				str += '<td><input type="text" class="form-control scoree" placeholder="0"></td>';
				str += '<td><select class="form-control type">';
				if('${selc}' == 'all'){
					str += '<option value="middle">중간고사</option>';
					str += '<option value="final">기말고사</option>';
					str += '<option value="march">모의고사 3월</option>';
					str += '<option value="june">모의고사 6월</option>';
					str += '<option value="september">모의고사 9월</option>';
				}else if('${selc}' == 'term'){
					str += '<option value="middle">중간고사</option>';
					str += '<option value="final">기말고사</option>';
				}else if('${selc}' == 'mock'){
					str += '<option value="march">모의고사 3월</option>';
					str += '<option value="june">모의고사 6월</option>';
					str += '<option value="september">모의고사 9월</option>';
				}else{
					str += '<option value="${selc}">'+getTypeName('${selc}')+'</option>';
				}
				str += '</select></td>';
				str += '<td id="delete_line"><i class="far fa-minus-square" style="padding-top:13px;" onClick="minus_btn(1)"></i></td></tr>';
				$("#score_tbody").append(str);
			}else{
				for(var i=0; i<list.length; i++){
					sum += list[i].score;
					var str = '<tr id="'+(i+1)+'">';
					str += '<td><input type="hidden" class="itemNo" value="'+list[i].itemNo+'">';
					str += '<input type="hidden" class="ori_subject" value="'+list[i].subject+'">';
					str += '<input type="hidden" class="ori_scoree" value="'+list[i].score+'">';
					str += '<input type="hidden" class="ori_type" value="'+list[i].type+'">';
					str += '<input type="text" class="form-control subject" value="'+list[i].subject+'"></td>';
					str += '<td><input type="text" class="form-control scoree" placeholder="0" value="'+list[i].score+'"></td>';
					str += '<td><select class="form-control type">';
					if('${selc}' == 'all'){
						str += '<option value="middle">중간고사</option>';
						str += '<option value="final">기말고사</option>';
						str += '<option value="march">모의고사 3월</option>';
						str += '<option value="june">모의고사 6월</option>';
						str += '<option value="september">모의고사 9월</option>';
					}else if('${selc}' == 'term'){
						str += '<option value="middle">중간고사</option>';
						str += '<option value="final">기말고사</option>';
					}else if('${selc}' == 'mock'){
						str += '<option value="march">모의고사 3월</option>';
						str += '<option value="june">모의고사 6월</option>';
						str += '<option value="september">모의고사 9월</option>';
					}else{
						str += '<option value="${selc}">'+getTypeName('${selc}')+'</option>';
					}
					str += '</select></td>';
					str += '<td id="delete_line"><i class="far fa-minus-square" style="padding-top:13px;" onClick="minus_btn('+(i+1)+')"></i></td></tr>';
					$("#score_tbody").append(str);
					$("#"+(i+1)+" .type").val(list[i].type).prop("selected", true);
				}
				avg = sum / list.length;
				avg = Math.round(avg*100)/100;
			}
			$("#subCount").text(list.length);
			$("#total").text(sum);
			$("#average").text(avg);
		};
		
		show();
	});
	
	function getTypeName(data){
		var result = "";
		if(data == "middle"){
			result = "중간고사";
		}else if(data == "final"){
			result = "기말고사";
		}else if(data == "march"){
			result = "모의고사 3월";
		}else if(data == "june"){
			result = "모의고사 6월";
		}else if(data == "september"){
			result = "모의고사 9월";
		}
		return result;
	}
	
	function getTitleName(){
		var data = '${semester}';
		var result = "";
		if(data == "sem1"){
			result = "1학년 1학기";
		}else if(data == "sem2"){
			result = "1학년 2학기";
		}else if(data == "sem3"){
			result = "2학년 1학기";
		}else if(data == "sem4"){
			result = "2학년 2학기";
		}else if(data == "sem5"){
			result = "3학년 1학기";
		}else if(data == "sem6"){
			result = "3학년 2학기";
		}
		return result;
	}
	
	function minus_btn(data){
		//var removeId = "#";
		//removeId += $(this).closest("tr").attr('id');
		var item = "#"+data+" .itemNo";
		if($(item).val() == -1){
			$("#"+data).remove();
		}else{
			$.ajax({
    			url: "${contextPath}/delete",
                type: "POST",
                data: {
                	id: '${userId}',
                	semester: '${semester}',
                	itemNo: $(item).val()
                },
                success: function () {
                	alert("삭제 완료");
                	$(item).remove();
                	location.reload();
                }
    		})	
		}
	};
	
	function drawChart(){
		var subjectList = JSON.parse('${subjectList}');
		var mid = JSON.parse('${mid}');
		var fin = JSON.parse('${fin}');
		var mar = JSON.parse('${mar}');
		var jun = JSON.parse('${jun}');
		var sep = JSON.parse('${sep}');
		
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawVisualization);
		function drawVisualization() {
			var data = new google.visualization.DataTable();
			data.addColumn('string' , '과목');
			for(var i=0; i<subjectList.length; i++){
				data.addColumn('number' , subjectList[i]);
			}
			
			var dataRow = [];
			var arr =['중간고사'];
			dataRow = arr.concat(mid);
			data.addRow(dataRow);
			
			arr =['기말고사'];
			dataRow = arr.concat(fin);
			data.addRow(dataRow);
			
			arr =['모의3월'];
			dataRow = arr.concat(mar);
			data.addRow(dataRow);
			
			arr =['모의6월'];
			dataRow = arr.concat(jun);
			data.addRow(dataRow);
			
			arr =['모의9월'];
			dataRow = arr.concat(sep);
			data.addRow(dataRow);
			

			var name = getTitleName();
			name += ' 성적 분포';
			var options = {
				title : name,
				vAxis: {title: '점수'},
				hAxis: {title: '시험'},
		};

		var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
		chart.draw(data, options);
		}
	}
</script>
</head>
<body>
<div class="contentBox">
<nav>
	<ol>
		<li><a class="w3-button" href="${contextPath }/sem1/all">1학년 1학기</a></li>
		<li><a class="w3-button" href="${contextPath }/sem2/all">1학년 2학기</a></li>
		<li><a class="w3-button" href="${contextPath }/sem3/all">2학년 1학기</a></li>
		<li><a class="w3-button" href="${contextPath }/sem4/all">2학년 2학기</a></li>
		<li><a class="w3-button" href="${contextPath }/sem5/all">3학년 1학기</a></li>
		<li><a class="w3-button" href="${contextPath }/sem6/all">3학년 2학기</a></li>
	</ol>
</nav>
<div id="chart_div"></div>
<div>
<table class="table table-bordered">
	<thead>
		<tr>
			<th colspan="3">
				<strong style="font-size:40px; color: orange;" id="title">1학년 1학기</strong><br>
				<strong id="subCount"></strong>&nbsp;<em>과목</em>&nbsp;&nbsp;&nbsp;
				<em>총점</em>&nbsp;<strong id="total"></strong>&nbsp;&nbsp;&nbsp;
				<em>평점</em>&nbsp;<strong id="average"></strong>
				<div class="w3-right">
					<input type="button" class="w3-button" id="save_btn" value="저장">
				</div>
				<div class="w3-right">
					<select class="w3-select" name="option" id="viewMode">
						<option value="all">전체</option>
						<option value="middle">중간고사</option>
						<option value="final">기말고사</option>
						<option value="march">모의고사 3월</option>
						<option value="june">모의고사 6월</option>
						<option value="september">모의고사 9월</option>
						<option value="term">중간,기말고사</option>
						<option value="mock">모의고사3,6,9월</option>
					</select>
				</div>
			</th>
			<th id="delete_line"></th>
		</tr>
		<tr>
			<th style="width:69%">과목명</th>
			<th style="width:10%">점수</th>
			<th style="width:20%">시험유형</th>
			<th style="width:1%;" id="delete_line"><i class="far fa-plus-square" style="padding-top:13px;" id="plus_btn"></i></th>
      </tr>
	</thead>
	<tbody id="score_tbody"></tbody>
</table>
<input type="button" class="w3-button" id="reset_btn" value="초기화">
</div>
</div>
</body>
</html>