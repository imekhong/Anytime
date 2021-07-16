package com.anytime.root.chat.Service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anytime.root.chat.dao.ChatDAO;
import com.anytime.root.chat.dto.ChatDTO;

@Service
public class ChatServiceImpl implements ChatService{
	@Autowired
	ChatDAO mapper;
	
	
	@Override
	public void insertChat(Map<String, Object> map) {
		ChatDTO dto = new ChatDTO();
		dto.setSendUser((String)map.get("sendUser"));
		dto.setReceiveUser((String)map.get("receiveUser"));
		dto.setChatcontent((String)map.get("chatcontent"));
		
		
		mapper.InsertSendChat(dto);
		
	}


	@Override
	public ArrayList<ChatDTO> userChatList(String UserName) {
		ArrayList<ChatDTO> listdto = new ArrayList<ChatDTO>();
		listdto = mapper.reseiveSelectChatUser(UserName);
		
		return listdto;
	}


	@Override
	public void deleteOne(int id) {
		mapper.deleteOne(id);
		
	}


	@Override
	public void deleteAll(String receiveUser) {
		mapper.deleteAll(receiveUser);
		
	}

}
