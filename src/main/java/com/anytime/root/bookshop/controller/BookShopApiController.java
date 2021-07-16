package com.anytime.root.bookshop.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//일반 컨트롤러는 그냥 일반 컨트롤러
//REST컨트롤러는 따로 API라는 이름을 붙여 사용함
//반듯이 restful 하게 만들것
// bookShop/write/{userName}
// 아니면 익명이니깐 bookShop/write로 갈까
// 꼭 끝의 값은 / 가 아닌 변수값을 넣어줘야한다

import com.anytime.root.bookshop.service.BookShopService;

@RestController
public class BookShopApiController {
	
	@Autowired
	BookShopService bs;
	
	
	@GetMapping(value = "bookShop/search/{SearchCode}" ,produces = "application/json;charset=utf-8")
	public String SearchBook(@PathVariable String SearchCode) {
		//System.out.println("SearchCode : " +SearchCode );
		String result = bs.responseBookSearch(SearchCode).getBody();
		//System.out.println("responseBody : " +result);
		
		
		
		return result;
	}
	
	@PostMapping(value = "bookShop/write" , produces = "application/json; charset=utf-8")
	public String WriteBookShope(@RequestBody Map<String, Object>book,
			HttpSession session) {
		System.out.println("가져온 값1 : " +book.get("bookisbn"));
		System.out.println("가져온 값2 : " +book.get("meansOftransaction"));
		System.out.println("아래 책 값 ");
		//System.out.println("가져온 값3 : " +book.get("photo"));
		//게싯글 등록 시작 해야함
		
		try {
			bs.SaveBookShop(book, session);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
		return "{\"result\" : true}";
	}
	
	
	
	
	
	
	
}
