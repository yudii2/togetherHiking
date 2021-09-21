package com.togetherHiking.board.model.dto;

import java.sql.Date;

public class Reply {
	private String coIdx;
	private String bdIdx;
	private String userId;
	private String content;
	private String codeIdx;
	private Date regDate;
	private int isDel;
	
	public String getCoIdx() {
		return coIdx;
	}
	public void setCoIdx(String coIdx) {
		this.coIdx = coIdx;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCodeIdx() {
		return codeIdx;
	}
	public void setCodeIdx(String codeIdx) {
		this.codeIdx = codeIdx;
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
	@Override
	public String toString() {
		return "Reply [coIdx=" + coIdx + ", bdIdx=" + bdIdx + ", userId=" + userId + ", content=" + content
				+ ", codeIdx=" + codeIdx + ", regDate=" + regDate + ", isDel=" + isDel + "]";
	}
	
	
}
