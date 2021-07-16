package com.anytime.root.board.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.anytime.root.board.dto.CommonBoard;
import com.anytime.root.board.dto.Reply;

public interface CommonBoardService {
	public void getListAndLike(Model model, int page, String searchType, String keyword);
	public int writePost(CommonBoard board, String tag, String unknown);
	public void getViewPost(Model model, int postNo);
	public void viewPost(Model model, int postNo, String login);
	public void modifyPost(CommonBoard board, String tag, String unknown);
	public void deletePost(int postNo);
	public void likeUpdate(int postNo, String id);
	public int likeCount(int postNo);
	public void reply(Reply reply);
	public void reReply(Reply reply);
	public ArrayList<Reply> getReplyList(int postNo);
	public void modifyReply(Reply reply);
	public void deleteReply(int replyNo);
}
