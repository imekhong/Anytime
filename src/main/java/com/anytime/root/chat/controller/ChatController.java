package com.anytime.root.chat.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anytime.root.chat.Service.ChatService;
import com.anytime.root.script.ScriptUtils;

@Controller
public class ChatController {
	
	@Autowired
	ChatService CS;
	
	
	@RequestMapping(value = {"chatView" , "chatView/{userId}"} )
	public String ChatView(Model model,@PathVariable(value = "userId", required = false )String userid, HttpSession session) {
		//테스트
		System.out.println(" 왜 안붙음? :"+userid);
		if(userid==null) {
			String Severuserid = (String) session.getAttribute("userId");
			System.out.println("작성자확인 : "+Severuserid);
			model.addAttribute("selectChatList", CS.userChatList(Severuserid));
		}else {
			model.addAttribute("selectChatList", CS.userChatList(userid+".com"));
		}
		
		
		
		return "chat/chatView";
	}
	//작성창에 가려면 꼭 json으로 보내줘야할듯 작성하려할때 받는사람 보내는사람을 받아내야해서
	
	@PostMapping(value = "chatSendView")
	public String ChatSend(Model model) {
		
		
		
		return "chat/chatSend";
	}
	
	
	@PostMapping(value = "writeChatView")
	public String ChatSendWriteView(HttpServletRequest httpServletRequest, Model model) {
		//테스트
		
		model.addAttribute("receiveUser",httpServletRequest.getParameter("writer"));
		//확인
		return "chat/chatSend";
	}
	
	//답장 눌렀을때
	@PostMapping(value = "reSendView")
	public String ReChatSendWriteView(HttpServletRequest httpServletRequest, Model model) {
		
		// 나한테 보낸 사람에게 답장해야하니깐
		model.addAttribute("receiveUser",httpServletRequest.getParameter("sendUser"));
		
		
		
		return "chat/chatSend";
	}
	
	
	
	
	// 보내기 할때는 from태그 Post로 저장 보내줘도 보내줘도 될듯 ajax로할까..? 
	@PostMapping(value = "sendChat",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String SendChat(Model model ,@RequestBody Map<String, Object>map
			,HttpServletResponse response) throws IOException {
		
		System.out.println(map.get("receiveUser"));
		
		CS.insertChat(map);
		
		return "{\"result\" : true}";
		
	}
	
	@GetMapping("ChatDelete/{chatId}")
	public void ChatDelete(@PathVariable("chatId")int chatId,HttpServletResponse response) throws IOException {
		
		CS.deleteOne(chatId);
		ScriptUtils.alertAndMovePage(response, "삭제완료했습니다.", "/root/chatView");
	}
	
	@PostMapping("ChatAllDelete")
	public void ChatAllDelete(HttpServletResponse response,HttpServletRequest requ) throws IOException {
		String nowUser=requ.getParameter("nowUser");
		CS.deleteAll(nowUser);
		
		ScriptUtils.alertAndMovePage(response, "모두삭제했습니다.", "/root/chatView");
	}
	
	
}
