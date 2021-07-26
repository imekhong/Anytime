package com.anytime.root.board.repository;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.anytime.root.board.dto.SchoolBoard;
import com.anytime.root.board.dto.SchoolLikeBoard;
import com.anytime.root.board.dto.LikePost;
import com.anytime.root.board.dto.Reply;

public interface SchoolBoardRepository {
	public int listCount(Map<String, Object> map);
	public int tagListCount(Map<String, Object> map);
	public int searchListCount(Map<String, Object> map);
	public ArrayList<SchoolLikeBoard> getListLike(Map<String, Object> map);
	public ArrayList<SchoolLikeBoard> getTagListLike(Map<String, Object> map);
	public ArrayList<SchoolLikeBoard> getSearchListLike(Map<String, Object> map);
	public void writePost(SchoolBoard board);
	public SchoolBoard viewPost(int postNo);
	public void viewsUp(int postNo);
	public int viewPostNo();
	public void modifyPost(SchoolBoard board);
	public void deletePost(int postNo);
	public int likeCheck(LikePost like);
	public void likeUpdate(LikePost like);
	public void likeDelete(LikePost like);
	public void likeDeleteByPostNo(int postNo);
	public int likeCount(int postNo);
	public void reply(Reply reply);
	public void reReply(Reply reply);
	public int replyCount(int postNo);
	public ArrayList<Reply> replyList(int postNo);
	public void modifyReply(Reply reply);
	public void deleteReply(int no);
	public void deleteReplyByPostNo(int postNo);
	public void addTag(@Param("postNo")int postNo, @Param("tag")String tag);
	public ArrayList<String> tagList(int postNo);
	public void deleteAllTag(int postNo);
	
}
