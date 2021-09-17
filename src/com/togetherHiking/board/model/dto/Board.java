package com.togetherHiking.board.model.dto;

import java.sql.Date;

public class Board {

	private String bdIdx;
	private String userId;
	private String title;
	private String category;
	private String content;
	private String flIdx;
	private Date regDate;
	private String grade;
	private String isDel;
	

	public String getBdIdx() {
		return bdIdx;
	}

	public void setBdIdx(String bdIdx) {
		this.bdIdx = bdIdx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFlIdx() {
		return flIdx;
	}

	public void setFlIdx(String flIdx) {
		this.flIdx = flIdx;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "Board [bdIdx=" + bdIdx + ", userId=" + userId + ", title=" + title + ", category=" + category
				+ ", content=" + content + ", flIdx=" + flIdx + ", regDate=" + regDate + ", grade=" + grade + ", isDel="
				+ isDel + "]";
	}
	
	
}
