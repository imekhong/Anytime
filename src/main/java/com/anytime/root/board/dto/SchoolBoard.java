package com.anytime.root.board.dto;

public class SchoolBoard {

	private String writerId;
	private String nickname;
	private int postNo;
	private String section;
	private String subject;
	private String content;
	private int views;
	private String writeDate;
	private String school;
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
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
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
	
	@Override
	public String toString() {
		return "postNO: "+postNo+" | subject: "+subject
				+" | content: "+content+" | views: "+views
				+" | school: "+school+" | nickname: "+nickname
				+" | writerId: "+writerId;
	}
}
