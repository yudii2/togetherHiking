package com.togetherHiking.reply.model.dto;

import java.sql.Date;

public class Reply {
	private String rpIdx;
	private String bdIdx;
	private String userId;
	private String content;
	private String codeIdx;
	private Date regDate;
	
	public String getRpIdx() {
		return rpIdx;
	}
	public void setRpIdx(String rpIdx) {
		this.rpIdx = rpIdx;
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
	@Override
	public String toString() {
		return "Reply [rpIdx=" + rpIdx + ", bdIdx=" + bdIdx + ", userId=" + userId + ", content=" + content
				+ ", codeIdx=" + codeIdx + ", regDate=" + regDate + "]";
	}
	
	
}