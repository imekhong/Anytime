package com.anytime.root.bookshop.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.anytime.root.bookshop.dto.BookShopDTO;
import com.anytime.root.bookshop.dto.BookShopPhotoDTO;

public interface BookShopDAO {
	
	public void SaveBookShop(BookShopDTO dto);
	
	//사진이랑 같이 저장하는법
	public void SaveBookShopPhoto(BookShopPhotoDTO dto);
	
	// 게시판 검색
	public BookShopDTO selectBook(String bookisbn);
	
	// 아이디 값 단 하나만 가져오는것
	public BookShopPhotoDTO selectBookPhoto(int id);
	
	// 책 아이디 검색해  select해오는 쿼리문
	public BookShopDTO selectBookId(int boardId);
	
	// 책과 사진 관련된 글 전부다 select해오는 문 조건없이 다가져옴
	public ArrayList<BookShopDTO> AllselectBookShop();
	public ArrayList<BookShopPhotoDTO> AllSelectBookShopPhoto();
	
	// 책 게시글 삭제 쿼리문
	public void deleteBookShopBoard(int boardId);
	
	// PageSelectBookShop();
	public ArrayList<BookShopDTO> PageSelectBookShop(@Param("strBoardNum") int strBoardNum,
			@Param("endBoardNum") int endBoardNum);
	
	// 페이징을 위한 카운티 메소드
	public int countingBookShop();
	
	// 북 본글 수정
	public void modifyBookShop(BookShopDTO dto);
	
	// 북 포토 테이블 수정
	public void modifyBookShopPhoto(BookShopPhotoDTO dto);
	
}
