package com.anytime.root.bookshop.dto;

import java.sql.Date;

public class BookShopDTO {
	private int boardId;
	private String booktitle;
	private String bookisbn;
	private String writer;
	private String school; 
	private String underline;
	private String handwrite;
	private String cover;
	private String nameWrite;
	private String page;
	private String meansOftransaction;
	private String SalesStatus;
	private Date createdate;
	private int price;
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getBookisbn() {
		return bookisbn;
	}
	public void setBookisbn(String bookisbn) {
		this.bookisbn = bookisbn;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getUnderline() {
		return underline;
	}
	public void setUnderline(String underline) {
		this.underline = underline;
	}
	public String getHandwrite() {
		return handwrite;
	}
	public void setHandwrite(String handwrite) {
		this.handwrite = handwrite;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getNameWrite() {
		return nameWrite;
	}
	public void setNameWrite(String nameWrite) {
		this.nameWrite = nameWrite;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getMeansOftransaction() {
		return meansOftransaction;
	}
	public void setMeansOftransaction(String meansOftransaction) {
		this.meansOftransaction = meansOftransaction;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSalesStatus() {
		return SalesStatus;
	}
	public void setSalesStatus(String salesStatus) {
		SalesStatus = salesStatus;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	
	
	
}
