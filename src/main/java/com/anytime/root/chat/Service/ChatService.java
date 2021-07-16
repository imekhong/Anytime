package com.anytime.root.chat.Service;

import java.util.ArrayList;
import java.util.Map;

import com.anytime.root.chat.dto.ChatDTO;

public interface ChatService {
	public void insertChat(Map<String,Object> map);
	// 해당 유저의 받은 메세지 확인
	public ArrayList<ChatDTO> userChatList(String UserName);
	public void deleteOne(int id);
	public void deleteAll(String receiveUser);
	
	
}
