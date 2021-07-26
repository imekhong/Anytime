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

import com.anytime.root.board.dto.CommonBoard;
import com.anytime.root.board.dto.Reply;
import com.anytime.root.board.service.CommonBoardService;
import com.anytime.root.common.session.SessionName;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("board/common")
public class CommonBoardController {
	
	private final CommonBoardService boardService;
	
	@Autowired
	public CommonBoardController(CommonBoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("list")
	public String list(Model model,
			@RequestParam(value="page", required = false, defaultValue = "1") int page,
			@RequestParam(value="search_type", required = false) String searchType,
			@RequestParam(value="keyword", required = false) String keyword) {
		boardService.getListAndLike(model, page, searchType, keyword);
		return "board/common/list";
	}
	
	@RequestMapping("p/{postNo}")
	public String viewPost(Model model, HttpSession session,
			@PathVariable String postNo) {
		String login = (String)session.getAttribute(SessionName.ID);
		boardService.viewPost(model, Integer.parseInt(postNo), login);
		return "board/common/viewPost";
	}
	
	@RequestMapping("write")
	public String write() {
		return "board/common/write";
	}

	@PostMapping("write_save")
	public String writeSave(CommonBoard board, 
			@RequestParam(value="tag", required = false) String tag,
			@RequestParam(value="unknown", required = false, defaultValue = "off") String unknown) {
		int postNo = boardService.writePost(board, tag, unknown);
		return "redirect:/board/common/p/"+postNo;
	}
	
	@RequestMapping("modify")
	public String modify(Model model,
			@RequestParam(value="no") String postNo) {
		boardService.getViewPost(model, Integer.parseInt(postNo));
		return "board/common/modify";
	}
	
	@PostMapping("modify_save")
	public String modifySave(CommonBoard board, 
			@RequestParam(value="tag", required = false) String tag,
			@RequestParam(value="unknown", required = false, defaultValue = "off") String unknown) {
		boardService.modifyPost(board, tag, unknown);
		return "redirect:/board/common/p/"+board.getPostNo();
	}
	
	@RequestMapping("delete")
	public String delete(@RequestParam(value="no") String postNo) {
		boardService.deletePost(Integer.parseInt(postNo));
		return "redirect:/board/common/list";
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
