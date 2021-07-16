package com.anytime.root.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.anytime.root.board.dto.CommonBoard;
import com.anytime.root.board.dto.CommonLikeBoard;
import com.anytime.root.board.dto.LikePost;
import com.anytime.root.board.dto.Reply;
import com.anytime.root.board.repository.CommonBoardRepository;

@Service
public class CommonBoardServiceImpl implements CommonBoardService{
	
	private StringTokenizer st;
	private final CommonBoardRepository boardRepository;

	@Autowired
	public CommonBoardServiceImpl(CommonBoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Override
	public void getListAndLike(Model model, int page, String searchType, String keyword) {
		int pageSize = 10;
		ArrayList<CommonLikeBoard> list; 
		Map<String, Object> map = new HashMap();
		map.put("keyword", keyword);
		map.put("searchType", searchType);
		int allCount = 1;
		if(keyword == null) {
			allCount = boardRepository.listCount();
		}else if(keyword.equals("tag")){
			allCount = boardRepository.tagListCount(map);
		}else {
			allCount = boardRepository.searchListCount(map);
		}
		int pageCount = allCount/pageSize;
		if(allCount%pageSize != 0 || pageCount == 0) {
			pageCount += 1;
		}
		model.addAttribute("pageCount", pageCount);
		int end = page * pageSize;
		int start = end + 1 - pageSize;
		map.put("start", start);
		map.put("end", end);
		if(keyword == null) {
			list = boardRepository.getListLike(map);
		}else if(keyword.equals("tag")){
			list = boardRepository.getTagListLike(map);
		}else {
			list = boardRepository.getSearchListLike(map);
		}
		model.addAttribute("commonBoardList", list);
	}

	@Override
	public int writePost(CommonBoard board, String tag, String unknown) {
		if(unknown.equals("on")){
			board.setNickname("익명이");
		}
		boardRepository.writePost(board);
		int postNo = boardRepository.viewPostNo();
		if(tag != null) {
			String str = tag.replaceAll("[`~!@$%&*+:;]", "").replace(" ", "");	
			st = new StringTokenizer(str, "#");
			while(st.hasMoreTokens()) {
				boardRepository.addTag(postNo, st.nextToken());
			}
		}
		return postNo;
	}
	
	//modify post 데이터용
	@Override
	public void getViewPost(Model model, int postNo) {
		model.addAttribute("board", boardRepository.viewPost(postNo));
		model.addAttribute("replyList", boardRepository.replyList(postNo));
		ArrayList<String> tagList = boardRepository.tagList(postNo);
		if(tagList.size() != 0) {
			String tags = "";
			for (String str : tagList) {
				tags += "#"+str+" ";
			}
			model.addAttribute("tagList", tags);
		}
	}
	
	//veiwPost용
	@Override
	public void viewPost(Model model, int postNo, String login) {
		CommonBoard board = boardRepository.viewPost(postNo);
		if(!board.getWriterId().equals(login)) {
			boardRepository.viewsUp(postNo);
		}
		model.addAttribute("board", boardRepository.viewPost(postNo));
		model.addAttribute("replyList", boardRepository.replyList(postNo));
		model.addAttribute("tagList", boardRepository.tagList(postNo));
		model.addAttribute("replyCount", boardRepository.replyCount(postNo));
	}
	
	@Override
	public void modifyPost(CommonBoard board, String tag, String unknown) {
		if(unknown.equals("on")){
			board.setNickname("익명이");
		}
		boardRepository.modifyPost(board);		
		int postNo = board.getPostNo();
		if(tag != null) {
			boardRepository.deleteAllTag(postNo);
			String str = tag.replaceAll("[`~!@$%&*+:;]", "").replace(" ", "");	
			st = new StringTokenizer(str, "#");
			while(st.hasMoreTokens()) {
				boardRepository.addTag(postNo, st.nextToken());
			}
		}
	}
	
	@Override
	public void deletePost(int postNo) {
		boardRepository.likeDeleteByPostNo(postNo);
		boardRepository.deleteReplyByPostNo(postNo);
		boardRepository.deleteAllTag(postNo);
		boardRepository.deletePost(postNo);
	}

	@Override
	public void likeUpdate(int postNo, String id) {
		LikePost like = new LikePost();
		like.setPostNo(postNo);
		like.setId(id);
		int result = boardRepository.likeCheck(like);
		if(result == 0) {
			boardRepository.likeUpdate(like);
		}else {
			boardRepository.likeDelete(like);
		}
	}

	@Override
	public int likeCount(int postNo) {
		return boardRepository.likeCount(postNo);
	}

	@Override
	public void reply(Reply reply) {
		boardRepository.reply(reply);
	}
	
	@Override
	public void reReply(Reply reply) {
		reply.setParentNo(reply.getReplyNo());
		boardRepository.reReply(reply);
	}

	@Override
	public ArrayList<Reply> getReplyList(int postNo) {
		return boardRepository.replyList(postNo);
	}

	@Override
	public void modifyReply(Reply reply) {
		boardRepository.modifyReply(reply);		
	}

	@Override
	public void deleteReply(int replyNo) {
		boardRepository.deleteReply(replyNo);
	}

}
