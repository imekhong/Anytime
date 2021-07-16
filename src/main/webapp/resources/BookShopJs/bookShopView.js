function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};


function salesCompleted() {

	$.ajax({
		url: "/root/bookshop/delete/" + $('#bookId').val(),
		type: "DELETE",
		success: function(result) {
			alert('판매완료')
			console.log(getContextPath())
			location.href = getContextPath()+"/bookshop";
		}
	})


}