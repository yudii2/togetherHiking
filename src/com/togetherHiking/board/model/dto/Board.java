package com.togetherHiking.board.model.dto;

import java.sql.Date;

public class Board {

	private String bdIdx;
	private String userId;
	private String title;
	private String subject;
	private String content;
	private Date regDate;
	private int isDel;
	private int replyCnt;
	private int viewCnt;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	@Override
	public String toString() {
		return "Board [bdIdx=" + bdIdx + ", userId=" + userId + ", title=" + title + ", subject=" + subject
				+ ", content=" + content + ", regDate=" + regDate + ", isDel=" + isDel + ", replyCnt=" + replyCnt
				+ ", viewCnt=" + viewCnt + "]";
	}

	
}
