package com.togetherHiking.schedule.model.dto;

import java.sql.Date;

public class Schedule {
	
	private String scIdx;
	private String userId;
	private Date dDay;
	private String mountainName;
	private Date regDate;
	private Date expDate;
	private int allowedNum;
	private int remainNum;
	private String info;
	private String isDel;
	private int status;
	private String openChat;	
	private int age; //모임연령대
	
	public Schedule() {
		// TODO Auto-generated constructor stub
	}

	public String getScIdx() {
		return scIdx;
	}

	public void setScIdx(String scIdx) {
		this.scIdx = scIdx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getdDay() {
		return dDay;
	}

	public void setdDay(Date dDay) {
		this.dDay = dDay;
	}

	public String getMountainName() {
		return mountainName;
	}

	public void setMountainName(String mountainName) {
		this.mountainName = mountainName;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public int getAllowedNum() {
		return allowedNum;
	}

	public void setAllowedNum(int allowedNum) {
		this.allowedNum = allowedNum;
	}

	public int getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(int remainNum) {
		this.remainNum = remainNum;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOpenChat() {
		return openChat;
	}

	public void setOpenChat(String openChat) {
		this.openChat = openChat;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Schedule [scIdx=" + scIdx + ", userId=" + userId + ", dDay=" + dDay + ", mountainName=" + mountainName
				+ ", regDate=" + regDate + ", expDate=" + expDate + ", allowedNum=" + allowedNum + ", remainNum="
				+ remainNum + ", info=" + info + ", isDel=" + isDel + ", status=" + status + ", openChat=" + openChat
				+ ", age=" + age + "]";
	}


	
}
