package com.anytime.root.board.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.anytime.root.board.dto.CommonLikeBoard;
import com.anytime.root.board.dto.Reply;
import com.anytime.root.board.dto.SchoolLikeBoard;
import com.anytime.root.bookshop.dto.BookShopDTO;
import com.anytime.root.bookshop.dto.BookShopPhotoDTO;

public interface MainBoardRepository {
	public ArrayList<CommonLikeBoard> getCmnBoardByDate();
	public ArrayList<SchoolLikeBoard> getSchoolBoardByDate(@Param("section")String section, @Param("school")String school);
	public ArrayList<CommonLikeBoard> getCmnBoardByLike();
	public ArrayList<SchoolLikeBoard> getSchoolBoardByLike(String school);
	public int cmnListCount(String id);
	public int schoolListCount(String id);
	public ArrayList<CommonLikeBoard> getMyCmnBoard(@Param("id")String id, @Param("start")int start, @Param("end")int end);
	public ArrayList<SchoolLikeBoard> getMySchoolBoard(@Param("id")String id, @Param("start")int start, @Param("end")int end);
	public ArrayList<Reply> getMycreplyList(String id);
	public ArrayList<Reply> getMysreplyList(String id);
	public String getSection(int postNo);
	public ArrayList<CommonLikeBoard> getCmnBoardForAdmin();
	public ArrayList<SchoolLikeBoard> getSchoolBoardForAdmin(String section);
	
	// 추가 부분
	public ArrayList<BookShopDTO> PreViewBookShop();
	public ArrayList<BookShopPhotoDTO> PreViewBookShopPhoto();
}
