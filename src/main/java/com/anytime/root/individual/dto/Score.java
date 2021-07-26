package com.anytime.root.individual.dto;

public class Score {
	private int itemNo;
	private String id;
	private String semester;
	private String type;
	private String subject;
	private int score;
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "itemNo: "+itemNo+" , id: "+id+" , semester: "+semester
				+" , type: "+type+" , subject: "+subject+" , score: "+score;
	}
}
