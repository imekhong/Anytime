$(document).ready(function() {
	setDateBox();
});
function setDateBox() {
	var dt = new Date();
	var year = "";
	var com_year = dt.getFullYear();

	for (var y = (com_year - 50); y <= (com_year + 1); y++) {
		$("#year").append(
			"<option value='" + y + "'>" + y + " 년" + "</option>");
	}
}


function handleOnChange(e) {
	// 선택된 데이터의 텍스트값 가져오기
	const text = e.options[e.selectedIndex].text;

	console.log(e.options);
	console.log(text);

	// 선택한 텍스트 출력
	document.getElementById("enterYear").value = text;
}









