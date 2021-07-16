package com.anytime.root.board.repository;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.anytime.root.board.dto.CommonBoard;
import com.anytime.root.board.dto.CommonLikeBoard;
import com.anytime.root.board.dto.LikePost;
import com.anytime.root.board.dto.Reply;

public interface CommonBoardRepository {
	public int listCount();
	public int tagListCount(Map<String, Object> map);
	public int searchListCount(Map<String, Object> map);
	public ArrayList<CommonLikeBoard> getListLike(Map<String, Object> map);
	public ArrayList<CommonLikeBoard> getTagListLike(Map<String, Object> map);
	public ArrayList<CommonLikeBoard> getSearchListLike(Map<String, Object> map);
	public void writePost(CommonBoard board);
	public CommonBoard viewPost(int postNo);
	public void viewsUp(int postNo);
	public int viewPostNo();
	public void modifyPost(CommonBoard board);
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
