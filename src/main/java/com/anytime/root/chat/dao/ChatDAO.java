package com.anytime.root.chat.dao;

import java.util.ArrayList;

import com.anytime.root.chat.dto.ChatDTO;

public interface ChatDAO {
	public void InsertSendChat(ChatDTO dto);
	public ArrayList<ChatDTO> reseiveSelectChatUser(String receiveUser);
	public void deleteOne(int id);
	public void deleteAll(String receiveUser);
}
