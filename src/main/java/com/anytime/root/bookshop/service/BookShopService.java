package com.anytime.root.bookshop.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import com.anytime.root.bookshop.dto.BookShopDTO;
import com.anytime.root.bookshop.dto.BookShopPhotoDTO;



public interface BookShopService {
	public ResponseEntity<String> responseBookSearch(String searchCode);
	
	public int SaveBookShop(Map<String, Object>map,HttpSession session);
	
	public int SaveBookShopPhoto(ArrayList<String> photoList);
	
	public BookShopDTO SelectBook(String isbn);
	
	// 받아온 photo값 split해주는 메소드
	public ArrayList<String> BookShopPhtoSplit(String imgString);
	
	
	
	public ArrayList<BookShopDTO> ListBookBoard(String isbn); //이게 검색시 리스트형식으로 반환해줌
	
	public ArrayList<BookShopDTO> AllListBookBoard(); // 그냥 전부 가져와줌(게싯글을)
	public Map<Integer,BookShopPhotoDTO> AllListBookBoardPhoto(); // 그냥 전부 가져와줌(사진값을)
	public ArrayList getBoardListCount(); // 글이 총 몇개 있는지 파악하는 카운팅 메소드
	
	public ArrayList<BookShopDTO> PageselectBookShop(int nowPage);
	
	public BookShopPhotoDTO SearchSelectPhotoBookShop(int id);
	
	// 북 샵 글 id로 검색하는 것
	public BookShopDTO SearchbookshopId(int bookId);
	
	// 북샵 글 삭제
	public void DeleteBookShopDelete(int boarId);
	
	// 글수정
	public int ModifyBookShop(Map<String, Object> map);
	
	// 글의 사진 수정
	public int ModifyBookShopPhoto(ArrayList<String> photoList , int boardId);
	
	//미리보기 서비스
	public ArrayList<BookShopDTO> preViewBookShop();
	public ArrayList<BookShopPhotoDTO> preViewBookShopPhoto();
	
}
