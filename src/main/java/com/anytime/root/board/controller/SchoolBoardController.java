package com.anytime.root.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anytime.root.board.dto.SchoolBoard;
import com.anytime.root.board.dto.Reply;
import com.anytime.root.board.service.SchoolBoardService;
import com.anytime.root.common.session.SessionName;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("board/school")
public class SchoolBoardController {
	
	private final SchoolBoardService boardService;
	@Autowired
	public SchoolBoardController(SchoolBoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("list/{section}")
	public String list(Model model, HttpSession session, @PathVariable String section,
			@RequestParam(value="page", required = false, defaultValue = "1") int page,
			@RequestParam(value="search_type", required = false) String searchType,
			@RequestParam(value="keyword", required = false) String keyword) {
		String school = (String)session.getAttribute(SessionName.SCHOOL);
		boardService.getListAndLike(school, section, model, page, searchType, keyword);
		return "board/school/list";
	}
	
	
	@RequestMapping("p/{section}/{postNo}")
	public String viewPost(Model model, HttpSession session,
			@PathVariable String section, @PathVariable String postNo) {
		String login = (String)session.getAttribute(SessionName.ID);
		boardService.viewPost(model, Integer.parseInt(postNo), login);
		return "board/school/viewPost";
	}
	
	@RequestMapping("write/{section}")
	public String write(Model model, @PathVariable String section) {
		return "board/school/write";
	}

	@PostMapping("write_save")
	public String writeSave(SchoolBoard board,
			@RequestParam(value="tag", required = false) String tag,
			@RequestParam(value="unknown", required = false, defaultValue = "off") String unknown) {
		int postNo = boardService.writePost(board, tag, unknown);
		return "redirect:/board/school/p/"+board.getSection()+"/"+postNo;
	}
	
	@RequestMapping("modify")
	public String modify(Model model,
			@RequestParam(value="no") String postNo) {
		boardService.getViewPost(model, Integer.parseInt(postNo));
		return "board/school/modify";
	}
	
	@PostMapping("modify_save")
	public String modifySave(SchoolBoard board, 
			@RequestParam(value="tag", required = false) String tag,
			@RequestParam(value="unknown", required = false, defaultValue = "off") String unknown) {
		boardService.modifyPost(board, tag, unknown);
		return "redirect:/board/school/p/"+board.getSection()+"/"+board.getPostNo();
	}
	
	@RequestMapping("delete/{section}")
	public String delete(@PathVariable String section, @RequestParam(value="no") String postNo) {
		boardService.deletePost(Integer.parseInt(postNo));
		return "redirect:/board/school/list/"+section;
	}
	
	@PostMapping(value="likeUpdate")
	@ResponseBody
	public void likeUpdate(@RequestParam(value="no") String postNo,
							@RequestParam(value="id") String userId) {
		boardService.likeUpdate(Integer.parseInt(postNo), userId);
	}

	@PostMapping(value="likeCount", produces = "application/json; charset=utf8")
	@ResponseBody
	public String likeCount(@RequestParam(value="no") String postNo, HttpServletResponse response) {
		JsonObject jsonObject = new JsonObject();
		int count = boardService.likeCount(Integer.parseInt(postNo));
		jsonObject.addProperty("count", count);
		return jsonObject.toString();
	}
	@PostMapping(value="reply")
	@ResponseBody
	public void reply(Reply reply) {
		boardService.reply(reply);
	}
	
	@PostMapping(value="reReply")
	@ResponseBody
	public void reReply(Reply reply) {
		boardService.reReply(reply);
	}
	
	@PostMapping(value="replyList", produces = "application/json; charset=utf8")
	@ResponseBody
	public String replyList(@RequestParam(value="postNo") String postNo) {
		List<Reply> list = boardService.getReplyList(Integer.parseInt(postNo));
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		for(int i=0; i<list.size(); i++) {
			JsonObject jObject = new JsonObject();
			jObject.addProperty("replyNo", list.get(i).getReplyNo());
			jObject.addProperty("parentNo", list.get(i).getParentNo());
			jObject.addProperty("depth", list.get(i).getDepth());
			jObject.addProperty("nickname", list.get(i).getNickname());
			jObject.addProperty("replyContent", list.get(i).getReplyContent());
			jObject.addProperty("writeDate", list.get(i).getWriteDate());
			jsonArray.add(jObject);
		}
		jsonObject.addProperty("count", list.size());
		jsonObject.add("item", jsonArray);
		
		return jsonObject.toString();
	}
	
	@PostMapping(value="modifyReply", produces = "application/json; charset=utf8")
	@ResponseBody
	public void modifyReply(Reply reply) {
		boardService.modifyReply(reply);
	}
	
	@GetMapping(value="deleteReply")
	@ResponseBody
	public void deleteReply(@RequestParam(value="replyNo") int replyNo) {
		boardService.deleteReply(replyNo);
	}
}
