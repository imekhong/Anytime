package com.anytime.root.individual.dto;

public class Timetable {
	private int itemNo;
	private String id;
	private String semester;
	private String subject;
	private String day;
	private int start_time;
	private int start_minute;
	private int end_time;
	private int end_minute;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getStart_time() {
		return start_time;
	}
	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}
	public int getStart_minute() {
		return start_minute;
	}
	public void setStart_minute(int start_minute) {
		this.start_minute = start_minute;
	}
	public int getEnd_time() {
		return end_time;
	}
	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}
	public int getEnd_minute() {
		return end_minute;
	}
	public void setEnd_minute(int end_minute) {
		this.end_minute = end_minute;
	}
}
