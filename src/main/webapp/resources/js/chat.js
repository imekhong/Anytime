function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

function sendChat(){
	form ={
		'receiveUser' : $('#receiveUser').val(),
		'sendUser' : $('#sendUser').val(),
		'chatcontent' : $('#chatcontent').val()
		
	}
	
	$.ajax({
		url: "/root/sendChat",
		type: "POST",
		dataType: 'json', // 보낼 타입
		data: JSON.stringify(form),
		contentType: "application/json; charset=utf-8",
		success: function(result) {
			alert('전송성공 : ' + result.result)
			location.href = getContextPath() + "/chatView"
		}
	})
	
	
}