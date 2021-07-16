package com.anytime.root.bookshop.service;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.anytime.root.board.repository.MainBoardRepository;
import com.anytime.root.bookshop.StatusMeans;
import com.anytime.root.bookshop.StatusMeans.handwrite;
import com.anytime.root.bookshop.StatusMeans.underline;
import com.anytime.root.bookshop.dao.BookShopDAO;
import com.anytime.root.bookshop.dto.BookShopDTO;
import com.anytime.root.bookshop.dto.BookShopPhotoDTO;

@Service
public class BookShopServiceImpl implements BookShopService {
	private String requestUrl = "https://dapi.kakao.com/v3/search/book" + "?sort=accuracy&page=1&size=10&query=";
	// 쿼리문 다음에 검색 문 String넣으면 됨

	private String RestAPIKey = "a924c282a86092b8472e6c2885aafe4a";

	@Autowired
	StatusMeans statusM;

	@Autowired
	BookShopDAO mapper;
	
	// 메인화면에 미리보기를 위한 매퍼 가져왔습니다.
	@Autowired
	MainBoardRepository previewMapper;

	int onePageboardCount = 5; // 한페이지에 몇개의 글을 보여줄지 정해주는 부분
	int allpageNum = 0; // 모든 페이지의 수 총 몇개의 페이지가 있는지 정해줌,
	
	
	@Override
	public ResponseEntity<String> responseBookSearch(String searchCode) {
		RestTemplate rt = new RestTemplate();

		// 헤더값
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + RestAPIKey);

		HttpEntity<MultiValueMap<String, String>> BookResquestEntity = new HttpEntity<>(headers);

		// 헤더의 Authorization 값 을 넣어서 하나에 담은후에 아래 줄과 같이 요청한다
		ResponseEntity<String> response = rt.exchange(requestUrl + searchCode, HttpMethod.GET, BookResquestEntity,
				String.class);

		return response;
	}

	@Override
	public int SaveBookShop(Map<String, Object> map, HttpSession session) {
		BookShopDTO dto = new BookShopDTO();

		String getIdSession = (String) session.getAttribute("userId");
		String getSchoolSession = (String) session.getAttribute("userSchool");

		dto.setBooktitle((String) map.get("booktitle"));
		dto.setBookisbn((String) map.get("bookisbn"));
		dto.setWriter(getIdSession);
		dto.setSchool(getSchoolSession);
		dto.setUnderline((String) map.get("underline"));
		dto.setHandwrite((String) map.get("handwrite"));
		dto.setCover((String) map.get("cover"));
		dto.setNameWrite((String) map.get("nameWrite"));
		dto.setPage((String) map.get("page"));
		dto.setMeansOftransaction((String) map.get("meansOftransaction"));
		dto.setPrice(Integer.parseInt((String) map.get("price")));

		// dto.setPhoto((String)map.get("photo"));
		// 이게먼저 저장되어야지 올바르게 돌아가지
		mapper.SaveBookShop(dto);
		SaveBookShopPhoto(BookShopPhtoSplit((String) map.get("photo")));

		// 여기서 스프릿들어가고 각각 반복문으로 만들어버리면 될듯
		return 1;
	}

	@Override
	public int SaveBookShopPhoto(ArrayList<String> photoList) {
		BookShopPhotoDTO dto = new BookShopPhotoDTO();
		int ListSize = photoList.size();

		// 낙수효과처럼 스위치문은 해당 조건을 만족하는순간 break문이 없다면 계속 실행된다, 그걸 이용함
		switch (ListSize) {
		case 5:

			dto.setPhoto5(photoList.get(4));
		case 4:

			dto.setPhoto4(photoList.get(3));
		case 3:

			dto.setPhoto3(photoList.get(2));
		case 2:

			dto.setPhoto2(photoList.get(1));
		case 1:
			// 저장공간에 가져올때 "" 자바스크립트 문에서 빈값 가져와서 일단 무조건 길이 1임 그래서 아래 예외처리조치함
			if (photoList.get(0) != null) {
				dto.setPhoto1(photoList.get(0));
			}
			// 좀더 깔끔한 방법 구상중

		default:
			if (dto.getPhoto1() == null) {
				dto.setPhoto1("null");
			}
			if (dto.getPhoto2() == null) {
				dto.setPhoto2("null");
			}
			if (dto.getPhoto3() == null) {
				dto.setPhoto3("null");
			}
			if (dto.getPhoto4() == null) {
				dto.setPhoto4("null");
			}
			if (dto.getPhoto5() == null) {
				dto.setPhoto5("null");
			}
			mapper.SaveBookShopPhoto(dto);
			return 1;

		}

	}

	@Override
	public BookShopDTO SelectBook(String isbn) {
		BookShopDTO dto = new BookShopDTO();

		// 여기서 사진만 가져오자(올바르게 담긴 값)
		dto = mapper.selectBook(isbn);

		// split으로 data순? 으로 잘라넣어서 미리보기 해야함 ';'을 이용해서 붙여넣으면될듯(최대 다섯개)
		// 이게 웃긴게 리스트 형식으로 담아서 또 반환해야하는데 DTO 구조를 좀 변경해야하나

		return dto;
		// return null;
	}

	@Override
	public ArrayList<BookShopDTO> ListBookBoard(String isbn) {

		return null;
	}

	// split과 동시에 trim도 함
	@Override
	public ArrayList<String> BookShopPhtoSplit(String imgString) {
		ArrayList<String> splitStringList = new ArrayList<String>();
		String[] splitStr = imgString.split("#");

		// 하나의 이미지당 두개가 담김 data형식과 data값
		// 사용할때는 두개씩 붙여 사용
		for (int i = 0; i < splitStr.length; i++) {
			// 공백 모두 제거
			// String tempStr = splitStr[i].replaceAll("\\p{Z}", "");
			splitStringList.add(splitStr[i].trim());
		}
		System.out.println("trim 확인 : " + splitStringList.get(0));

		return splitStringList;
	}

	@Override
	public ArrayList<BookShopDTO> AllListBookBoard() {
		return mapper.AllselectBookShop();
	}

	@Override
	public Map<Integer, BookShopPhotoDTO> AllListBookBoardPhoto() {
		ArrayList<BookShopPhotoDTO> AllphotoList = new ArrayList<BookShopPhotoDTO>();
		AllphotoList = mapper.AllSelectBookShopPhoto();

		Map<Integer, BookShopPhotoDTO> AllBookPhotoList = new HashMap<Integer, BookShopPhotoDTO>();

		for (int i = 0; i < AllphotoList.size(); i++) {
			AllBookPhotoList.put(AllphotoList.get(i).getId(), AllphotoList.get(i));
		}

		return AllBookPhotoList;
	}

	@Override
	public ArrayList getBoardListCount() {
		ArrayList countList = new ArrayList();
		
		
		for (int i = 0; i < allpageNum; i++) {
			countList.add(i);
		}

		return countList;
	}

	@Override
	public ArrayList<BookShopDTO> PageselectBookShop(int nowPage) {
		int totalBoardCount = mapper.countingBookShop(); // 현재 글이 몇개있는지 파악
		

		int lastBoardNum = 1; // 마지막 보드 아이디 넘버
		int startBoardNum = 0; // 첫번째 보드 아이디 넘버


		// 1. 먼저 페이지의 수를 구한다 전체 글 /한페지의 게시글 = > 페이지수
		allpageNum = totalBoardCount / onePageboardCount;
		lastBoardNum = onePageboardCount; // 즉 한페이지의 해결이니깐 처음엔 무조건 위의 설정한만큼
		// 2. 나누고 남는다는 뜻 자체가 한페이지 더 처리해줘야 한다는 뜻
		if (totalBoardCount % onePageboardCount >= 1) {
			allpageNum += 1;
		}
		System.out.println("모든 페이지 수 :" + allpageNum);
		// 첫번째 장이라는 뜻
		if (nowPage == 1) {
			// 할께 없음
		} else if (allpageNum == nowPage) {
			// 모든 페이지의 넘버와 지금 클릭한 페이지 수가 같다는 것은 즉 마지막 페이지 라는뜻

			startBoardNum = ((nowPage - 1) * onePageboardCount)+1;
			int ramainNum = (nowPage * onePageboardCount) - totalBoardCount;

			lastBoardNum = (nowPage * onePageboardCount) - ramainNum; // 마지막 번호라는뜻은 즉 끝번호는 무조건 총 게시판의 수
		} else {
			startBoardNum = ((nowPage - 1) * onePageboardCount)+1;
			lastBoardNum = (nowPage * onePageboardCount);
		}
		System.out.println(" 시작 게시판 번호 :" + startBoardNum);
		System.out.println(" 끝 게시판 번호 :" + lastBoardNum);
		return mapper.PageSelectBookShop(startBoardNum, lastBoardNum);
	}

	@Override
	public BookShopPhotoDTO SearchSelectPhotoBookShop(int id) {
		BookShopPhotoDTO dto = new BookShopPhotoDTO();
		dto = mapper.selectBookPhoto(id);

		return dto;
	}
	//분류해주는 메소드
	

	@Override
	public BookShopDTO SearchbookshopId(int bookId) {
		BookShopDTO dto = new BookShopDTO();
		
		// 해당 id만을 검색해 결과를 보내준다
		dto = mapper.selectBookId(bookId);
		
		return dto;
	}
	
	//글 삭제 메소드
	@Override
	public void DeleteBookShopDelete(int boarId) {
		mapper.deleteBookShopBoard(boarId);
		
	}

	@Override
	public int ModifyBookShop(Map<String, Object> map) {
		BookShopDTO dto = new BookShopDTO();
		int price = Integer.parseInt((String)map.get("price"));
		int boardId = Integer.parseInt((String)map.get("bookId"));
		
		dto.setUnderline((String)map.get("underline"));
		dto.setHandwrite((String)map.get("handwrite"));
		dto.setCover((String)map.get("cover"));
		dto.setNameWrite((String)map.get("nameWrite"));
		dto.setPage((String)map.get("page"));
		dto.setMeansOftransaction((String)map.get("meansOftransaction"));
		dto.setPrice(price);
		
		dto.setBoardId(boardId);
		
		// 수정부분
		mapper.modifyBookShop(dto);
		
		// 사진을 수정하는 부분, 해당 보드의 아이디를 받아야함
		ModifyBookShopPhoto(BookShopPhtoSplit((String)map.get("photo")), boardId);
		
		return 1;
	}
	
	// 사진 수정은 아이디가 필요하니 넣었습니다
	@Override
	public int ModifyBookShopPhoto(ArrayList<String> photoList , int boardId) {
		BookShopPhotoDTO dto = new BookShopPhotoDTO();
		int ListSize = photoList.size();

		// 낙수효과처럼 스위치문은 해당 조건을 만족하는순간 break문이 없다면 계속 실행된다, 그걸 이용함
		switch (ListSize) {
		case 5:

			dto.setPhoto5(photoList.get(4));
		case 4:

			dto.setPhoto4(photoList.get(3));
		case 3:

			dto.setPhoto3(photoList.get(2));
		case 2:

			dto.setPhoto2(photoList.get(1));
		case 1:
			// 저장공간에 가져올때 "" 자바스크립트 문에서 빈값 가져와서 일단 무조건 길이 1임 그래서 아래 예외처리조치함
			if (photoList.get(0) != null) {
				dto.setPhoto1(photoList.get(0));
			}
			// 좀더 깔끔한 방법 구상중

		default:
			if (dto.getPhoto1() == null) {
				dto.setPhoto1("null");
			}
			if (dto.getPhoto2() == null) {
				dto.setPhoto2("null");
			}
			if (dto.getPhoto3() == null) {
				dto.setPhoto3("null");
			}
			if (dto.getPhoto4() == null) {
				dto.setPhoto4("null");
			}
			if (dto.getPhoto5() == null) {
				dto.setPhoto5("null");
			}
		}
		dto.setId(boardId);
		
		mapper.modifyBookShopPhoto(dto);
		
		
		
		return 1;
	}

	@Override
	public ArrayList<BookShopDTO> preViewBookShop() {
		ArrayList<BookShopDTO> listDto = new ArrayList<BookShopDTO>();
		listDto = previewMapper.PreViewBookShop(); // 1번부터 4번까지 값 가져옴
		
		return listDto;
	}

	@Override
	public ArrayList<BookShopPhotoDTO> preViewBookShopPhoto() {
		ArrayList<BookShopPhotoDTO> listdto = new ArrayList<BookShopPhotoDTO>();
		
		listdto = previewMapper.PreViewBookShopPhoto();
		
		
		return listdto;
	}
	
	
	
	

}
