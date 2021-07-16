package com.anytime.root.board.dto;

public class Reply {
	private int postNo;
	private int replyNo;
	private int parentNo;
	private int depth;
	private String writerId;
	private String nickname;
	private String replyContent;
	private String writeDate;
	private String school;
	@Override
	public String toString() {
		return "postNo: "+postNo+" , replyNo: "+replyNo+" , parentNo: "+parentNo
				+" , depth: "+depth+" , writerId: "+writerId+" , nickname: "+nickname
				+" , replyContent: "+replyContent+" , school: "+school;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
}
