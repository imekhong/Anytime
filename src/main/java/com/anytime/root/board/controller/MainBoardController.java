package com.anytime.root.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anytime.root.board.service.MainBoardService;
import com.anytime.root.bookshop.service.BookShopService;
import com.anytime.root.common.session.SessionName;

@Controller
public class MainBoardController {

	private final MainBoardService boardService;
	
	@Autowired
	public MainBoardController(MainBoardService boardService) {
		this.boardService = boardService;
	}
	
	@Autowired
	public BookShopService BShopService;
	
	@RequestMapping("rule/servicerule")
	public String servicerule() {
		return "rule/servicerule";
	}
	
	@RequestMapping("rule/servicerule2")
	public String servicerule2() {
		return "rule/servicerule2";
	}
	
	@RequestMapping("rule/servicerule3")
	public String servicerule3() {
		return "rule/servicerule3";
	}
	
	@RequestMapping("index")
	public String main(Model model, HttpSession session) {
		String id = (String)session.getAttribute(SessionName.ID);
		String school = (String)session.getAttribute(SessionName.SCHOOL);
		boardService.getMain(model, id, school);
		
		//추가(사진 미리보기 부분)	
		model.addAttribute("previewBookShop", BShopService.preViewBookShop());
		model.addAttribute("previewBookShopPhoto", BShopService.preViewBookShopPhoto());
		return "index";
	}
	
	@GetMapping("my/cboard")
	public String clist(Model model, HttpSession session,
			@RequestParam(value="page", required = false, defaultValue = "1") int page) {
		String id = (String)session.getAttribute(SessionName.ID);
		boardService.getMycBoard(model, page, id);
		return "board/my/clist";
	}
	
	@GetMapping("my/sboard")
	public String slist(Model model, HttpSession session,
			@RequestParam(value="page", required = false, defaultValue = "1") int page) {
		String id = (String)session.getAttribute(SessionName.ID);
		boardService.getMysBoard(model, page, id);
		return "board/my/slist";
	}
	
	@GetMapping("my/creply")
	public String creply(Model model, HttpSession session) {
		String id = (String)session.getAttribute(SessionName.ID);
		boardService.getMycReply(model, id);
		return "board/my/creply";
	}
	
	@GetMapping("my/sreply")
	public String sreply(Model model, HttpSession session) {
		String id = (String)session.getAttribute(SessionName.ID);
		boardService.getMysReply(model, id);
		return "board/my/sreply";
	}
	
	@RequestMapping("my/sreply/{postNo}")
	public String sreplyToPost(@PathVariable int postNo) {
		String section = boardService.getSection(postNo);
		return "redirect:/board/school/p/"+section+"/"+postNo;
	}
}
