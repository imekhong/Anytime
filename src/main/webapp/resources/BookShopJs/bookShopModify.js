function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

function changeunderline(value) {
	$('#Question0').val(value)
	$('#label0').text(value)
}

function changehandwrite(value) {
	$('#Question1').val(value)
	$('#label1').text(value)
}
function changecover(value) {
	$('#Question2').val(value)
	$('#label2').text(value)
}

function changenameWrite(value) {
	$('#Question3').val(value)
	$('#label3').text(value)
}

function changepage(value) {
	$('#Question4').val(value)
	$('#label4').text(value)
}

function changemeansOftransaction(value) {
	$('#Question5').val(value)
	$('#label5').text(value)
}

function bookShopmodify() {
	bookId = $('#boardId').val()
	
	//최대 다섯장이니깐 다섯 장 다 받아와지는 지 확인
	var img = []
	var totalPicture = ""
	for (i = 0; i < 5; i++) {
		var resultPictureSrc = $('#numPicture' + i).attr("src")

		if (resultPictureSrc == null) {
			break
		} else {
			img[i] = resultPictureSrc + '#' //요걸넣어줘야 구분가능함 자바 서버단에서 split할예정
		}
	}
	console.log("이미지 갯수만큼의 길이가 나와야함 : " + img.length)
	for (i = 0; i < img.length; i++) {
		console.log("이미지배열의 값인데" + img[i])
		totalPicture += img[i]
	}

	fom = {
		'booktitle': $('#booktitle').text(),
		'bookisbn': $('#bookisbn').text(),
		'underline': $('#Question0').val(),
		'handwrite': $('#Question1').val(),
		'cover': $('#Question2').val(),
		'nameWrite': $('#Question3').val(),
		'page': $('#Question4').val(),
		'meansOftransaction': $('#Question5').val(),
		'price': $('#price').val(),
		'bookId' : bookId,
		'photo': totalPicture
	}
	$.ajax({
		url: "/root/bookShop/modify/"+bookId,
		type: "PUT",
		dataType: 'json', // 보낼 타입
		data: JSON.stringify(fom),
		contentType: "application/json; charset=utf-8",
		success: function(result) {
			alert('글 수정 성공 : ' + result.result)
			location.href = getContextPath() + "/bookshop"
		}
	})




}


