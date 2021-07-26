// bookshop/bookshopwrite/user
// 적어도 누가 쓰는지는 알아야하니까
// bookList 는 꼭 남겨놓기
// 꼭 div 만들때 height 지정해줘야할듯
// 밑줄, 손글씨, 표지상태 , 이름 유무, 페이지 상태, 거래 상태

var resultLength
var Bookresult // 책의 결과값을 넣는 전역변수 입니다
/* ------------------- 위는 검색api결과값 리스트관한것------------------------- */

var choicenum = 0 // 이녀석이 총 6번 반복하면 알아서 멈추게 만들껍니다 (if문을 통해)
var underline = ['non', 'sharpPencil', 'highliterBolpen', 'all']
var handwrite = ['non', 'sharpPencil', 'highliterBolpen', 'all']
var Kounderhand = ['없음', '샤프,연필', '형광펜,볼펜', '전부다']
var cover = ['clean', 'dirty']
var Kocover = ['깨끗함', '더러움']
var nameWrite = ['non', 'wrote']
var KoNameWrite = ['없음', '썼다.']
var Page = ['non', 'blank', 'damage', 'all']
var KoPage = ['없음', '반색', '회손', '둘다']
var meansOftransaction = ['parcelService', 'directTransaction', 'all']
var KomeansOftransaction = ['택배만', '직거래만', '둘다가능']
var Koask = ['밑줄이 있습니까?', '필기가 있습니까?', '표지는 깨끗하나요?', '책에 이름을 썼습니까?', '종이상태는 어떤가요?', '거래는 어떤게 가능한가요?']
var Koaskask = ['밑줄', '필기', '표지', '책의 이름 유무', '종이상태', '거래(택배/직거래)']
// 리스트를 모두 담은것 즉 접근법은 totalSelectList[i].Page[i]  , ? 
var totalSelectList = [underline, handwrite, cover, nameWrite, Page, meansOftransaction]
var KototalSelectList = [Kounderhand, Kounderhand, Kocover, KoNameWrite, KoPage, KomeansOftransaction]


// ------------------------해당 radio에 관한것------------------------------------
var selectName = [] // 해당값은 user UI를 위한 한글값을 위해 넣은것
var selectValue = [] // selectValue 데베에 담기위한 영어값


function roop() {
	//check시 넘어가는 걸로

}

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

function clickBook(i) {
	console.log('책 결과값은 : ' + Bookresult.documents[i].title)
	console.log('책 isbn : ' + Bookresult.documents[i].isbn)
	// 꼭 다 삭제 전에 값을 만들어 놓고 삭제해야한다
	//한줄 즉 그 제목만 남겨놓고 전부 다 삭제해주는거임
	// 자바스크립트는 매개변수를 지정해놔도 받는 매개변수가 없어도 작동한다

	allDelElement('SearchCode', 'SearchCodeBtn')
	//bookList 는 꼭 남겨놓기

	// 제목 isbn 저장 ( label 형식으로)
	var BookListDiv = document.getElementById('bookSearchResultTableBox')
	BookListDiv.innerHTML += "<div style=\"margin-top: 10px;\">" +
		"제목 :<label id=\"booktitle\">" + Bookresult.documents[i].title + "</label><br>" +
		"isbn : <label id=\"bookisbn\">" + Bookresult.documents[i].isbn + "</label><br></div>"
	//BookListDiv.appendChild(BookListDiv);

	// 여기서 새로 다음으로 넘어갈 로직짜야함 함수 생성 요망

	makeChoiceRadio(totalSelectList[choicenum]) //
}

function ResultlistBook(result) {
	//allDelElement()

	// 얘 무조건 10개씩 준다
	if (result.documents[0] != null) {
		var my_tbody = document.getElementById('my-book-tbody');
		// var row = my_tbody.insertRow(0); // 상단에 추가
		
		for (i = 0; i < result.documents.length; i++) {
			// 책 검색 api의 전체 결과값을 담아내는 변수 두개로 꼭 넣어놔야함
			Bookresult = result
			resultLength = result.documents.length
			
			var row = my_tbody.insertRow(my_tbody.rows.length); // 하단에 추가
		    var cell1 = row.insertCell(0);
		    var cell2 = row.insertCell(1);
		    var cell3 = row.insertCell(2);
		    var cell4 = row.insertCell(3);
			
			cell1.innerHTML = "<img src=\"" + result.documents[i].thumbnail + "\">"
		    cell2.innerHTML = "<button onclick = \"clickBook( "+i+")\"style=\"border:none;  outline :none; background-color:white;\">" + result.documents[i].title + "</button>"
			cell3.innerHTML = "<label> " + result.documents[i].isbn + "</label>"
			cell4.innerHTML =  "<label> " + result.documents[i].authors + "</label>"
		}
		//console.log('ajax통신확인' + result.documents[0].authors)
		// 반복문으로 모두 출력
		/*	console.log(result.documents.length)
			for (i = 0; i < result.documents.length; i++) {
				var BookListDiv = document.getElementById('bookList')
				Bookresult = result
				resultLength = result.documents.length
				BookListDiv.removeChild(BookListDiv.firstChild)
				BookListDiv.innerHTML += "<div name=\"BookDiv\" id=\"BookDiv\"> <img src=\"" + result.documents[i].thumbnail + "\"><br>" +
					"<label>제목 : " + result.documents[i].title + "</label><br>" + "<label>isbn : " + result.documents[i].isbn + "</label><br>" + "<label>작가 : " + result.documents[i].authors + "</label><br>"
					+ "<input type=\"radio\" onclick=\"clickBook(" + i + ")\" id=\"book\" name=\"book\" value=\"" + result.documents[i].title + "\">" + "</div><br>"
				document.body.appendChild(BookListDiv);
			}*/
		// 자바스크립트로 페이징 처리해야함 현재 10개씩만 받아오게 해놓음
	} else {
		alert('검색된 책이 없습니다.')
	}
}

// api값을 가져와줌
function GowriteBook() {
	// console.log('잘돌아가는지 확인')
	let SearchCode = $("#SearchCode").val()
	//console.log(SearchCode)

	// 서버단으로 유저 세션 값 전달, 그럴필요가 있나? 세션은 서버단에 저장했으니깐 가져오면대자나?
	// 일단 해보자 RESTFUL하게 만들어보자

	$.ajax({
		url: 'search/' + SearchCode,
		type: 'GET',
		contentType: "application/json;charset=utf-8",
		success: function(result) {
			ResultlistBook(result)
		}

	})

}
// 모든 element를 삭제해주는 함수
function allDelElement(elemid1, elemid2) {
	$('table').remove();
	/*$('label').remove();
	$('img').remove();
	$('input[type=radio][name=book]').remove();
	$('#' + elemid1).remove();
	$('#' + elemid2).remove();
	for (i = 0; i < resultLength; i++) {
		$('br').remove();
		$('#BookDiv').remove();
		// 반복문으로 넣어줘야함, BookDiv 아이디가 하나만 삭제하는 거같음

	}*/
}

// 총 3개 , 선택지 갯수
//  각각 선택지 이름

// 굳이 이렇게 따로 함수를 둔 이유는 쓸데없는 코드가 반복함을 방지하기 위함
// value는 말그대로 데베에 저장을위한 값 name은 유저가 보기위함 값
// 공통 선택이 가능해야함
// choiceName = > 총 선택한 ID값 (나중에 지우기위해둔건데 꼭 반복문으로 갯수만큼 삭제해야함)
function makeChoiceRadio(choiceName) {
	var BookListDiv = document.getElementById('bookList')
	// 아래처럼 접근이 가능함
	/*console.log(totalSelectList[0][3])
	console.log(KototalSelectList[2][1])*/
	BookListDiv.innerHTML += "<label id=\"koask\">" + Koask[choicenum] + "</label><br>"
	if (choicenum < 6) {
		for (i = 0; i < totalSelectList[choicenum].length; i++) {
			BookListDiv.innerHTML += "<div id=\"selRadioDIV\" style=\"margin-top: 10px;\">" +
				"<label id=\"" + totalSelectList[choicenum][i] + "\">" + KototalSelectList[choicenum][i] + "</label>" +
				"<input type=\"radio\" onclick=\"makeDecide(\'" + totalSelectList[choicenum][i] + "\' , \'" + choiceName + "\', \'" + KototalSelectList[choicenum][i] + "\')\" id=\"radiosel\" name=\"radiosel\" value=\"" + totalSelectList[choicenum][i] + "\"><br>" +
				"</div>"
		}
		//document.body.appendChild(BookListDiv);

	}
}

//  Decide = > 결정하다, 결정한 값들을 label로 만들어주는 함수
//값들은 전부 삭제해야함 해당 div값들을 // inchoicName =>아이디삭제
// inchoiceName 예를 들어 underline
// 전역변수랑 이름이 같지 않아야한다
function makeDecide(inselectValue, inchoiceName, koreananswer) {
	// 여기서 재귀가 일어나야함

	console.log(choicenum)
	console.log(inselectValue)
	console.log(inchoiceName)
	for (i = 0; i < totalSelectList[choicenum].length; i++) {
		console.log('반복 : ' + i)
		$('#selRadioDIV').remove();
		/*$('#' + inchoiceName).remove();
		$('#radiosel').remove();*/
	}

	// 여기 생각종 (재귀함수? )
	// 값을 받아와서 ( label 형식으로)
	var BookListDiv = document.getElementById('bookList')
	BookListDiv.innerHTML += "<div style=\"margin-top: 10px;\">" +
		Koaskask[choicenum] + " : " +
		"<label>" + koreananswer + "</label><br>" +
		"<input type=\"hidden\" id=\"Question" + choicenum + "\" value=\"" + inselectValue + "\"><br></div>"
	//document.body.appendChild(BookListDiv);
	// 여기서 새로 다음으로 넘어갈 로직짜야함 함수 생성 요망
	// 전역 리스트 하나를 두고 그냥 다 담아놓자 그리고 수가 4번 반복이 완료되면 멈추도록
	choicenum += 1
	if (choicenum < 6) {
		makeChoiceRadio(totalSelectList[choicenum]) //
	} else {
		makePhotoPrice()
	}
}

// 가격과 사진 부분, 동시에 띄우고 마지막 전송버튼 쓰는게 나을듯
function makePhotoPrice() {
	var BookListDiv = document.getElementById('bookList')
	BookListDiv.innerHTML += "<label>판매 가격 : </label>&ensp;<input type=\"text\" id=\"price\">"

	BookListDiv.innerHTML += "<div id=\"finalDiv\" style=\"margin-top: 10px;\">" +
		"<button onclick=\"WriteButton()\">작성</button><br>" +
		"</div>"
	//document.body.appendChild(BookListDiv);

	$("#uploadFile").show()
	$("#preview").show()

	// 이미지 올리는 부분이 위에 생성되는 부분을 임시 해결위해 스크롤 시키도록 만들어놓음 무조건 수정요망
	var offset = $("#uploadFile").offset();
	$('html, body').animate({ scrollTop: offset.top }, 400);
}

function WriteButton() {
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
		'photo': totalPicture
	}
	$.ajax({
		url: "/root/bookShop/write",
		type: "POST",
		dataType: 'json', // 보낼 타입
		data: JSON.stringify(fom),
		contentType: "application/json; charset=utf-8",
		success: function(result) {
			alert('글쓰기성공 : ' + result.result)
			location.href = getContextPath() + "/bookshop"
		}
	})
}
