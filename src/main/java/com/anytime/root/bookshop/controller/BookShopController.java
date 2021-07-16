package com.anytime.root.bookshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anytime.root.bookshop.dao.BookShopDAO;
import com.anytime.root.bookshop.dto.BookShopDTO;
import com.anytime.root.bookshop.dto.BookShopPhotoDTO;
import com.anytime.root.bookshop.service.BookShopService;
import com.anytime.root.script.ScriptUtils;

// REST쪽이 아니니깐 view와 ATTRibute를 반환하자

@Controller
public class BookShopController {
	@Autowired
	BookShopService Bs;
	int nowPage;

	@GetMapping(value = { "bookshop/{pageNum}", "bookshop" })
	public String showBookShop(Model model, @PathVariable(value = "pageNum", required = false) Integer pageNum) {
		// default 값이 1로 하게 만들기위해서
		if (pageNum == null) {
			pageNum = 1;
		}
		System.out.println("페이지 현재 : " + pageNum);
		nowPage = pageNum;

		ArrayList<BookShopDTO> ResultList = new ArrayList<BookShopDTO>();
		Map<Integer, BookShopPhotoDTO> AllBookPhotoList = new HashMap<Integer, BookShopPhotoDTO>();

		ResultList = Bs.PageselectBookShop(nowPage);
		AllBookPhotoList = Bs.AllListBookBoardPhoto();

		for (int i = 0; i < ResultList.size(); i++) {
			System.out.println("컨트롤단의 북제목확인 : " + ResultList.get(i).getBooktitle());
		}

		model.addAttribute("AllBookPhotoList", AllBookPhotoList);
		model.addAttribute("PageboardList", ResultList);

		model.addAttribute("allPageCount", Bs.getBoardListCount());
		model.addAttribute("nowPage", nowPage);

		return "bookshop/bookShopMain";
	}

	@RequestMapping(value = "bookShop/write")
	public String GoWritePage() {

		return "bookshop/bookShopWrite";
	}

	// 게시글 보기
	@GetMapping(value = "bookshop/{nowPage}/{boardId}")
	public String bookShopView(@PathVariable("boardId") int id, Model model) {

		System.out.println("받은값 확인 " + id);
		// 여기다가 model 값을 추가해주면됨
		model.addAttribute("BookShop", Bs.SearchbookshopId(id));
		model.addAttribute("BookShopPhoto",Bs.SearchSelectPhotoBookShop(id));

		return "bookshop/BookShopView";
	}

	// 게시글 삭제
	@DeleteMapping(value = "bookshop/delete/{boardId}")
	public void deleteBookBoard(@PathVariable("boardId") int boardId, HttpServletResponse response) 
			throws IOException {
		Bs.DeleteBookShopDelete(boardId);
	}
	
	
	// 게시글 수정 하는 글을 보는 메소드
	@GetMapping(value = "bookshop/modifyView/{boardId}")
	public String modifyView(@PathVariable("boardId") int boardId,Model model) {
		
		model.addAttribute("BookShop", Bs.SearchbookshopId(boardId));
		model.addAttribute("BookShopPhoto",Bs.SearchSelectPhotoBookShop(boardId));
		
		
		return "bookshop/BookShopModifyView";
	}
	// 게시글 수정 하는 메소드
	// 본래는 위 api컨트롤 쪽으로 가는게 맞는방향인거같다
	@PutMapping(value = "bookShop/modify/{bookId}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String modify(@PathVariable("bookId") int boardId,@RequestBody Map<String, Object>Modifybook) {
		
		
		Bs.ModifyBookShop(Modifybook);
		
		return "{\"result\" : true}";
	}
	
	

}
