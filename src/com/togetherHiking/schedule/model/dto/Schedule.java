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
	private String plIdx; //참가자 목록 번호
	
	private int capacity; // ??
	

	private String info;
	private String isDel;
	private int money;
	private String openChat;
	
	private int meetingTime; //모임시간
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

	public String getPlIdx() {
		return plIdx;
	}

	public void setPlIdx(String plIdx) {
		this.plIdx = plIdx;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getOpenChat() {
		return openChat;
	}

	public void setOpenChat(String openChat) {
		this.openChat = openChat;
	}

	public int getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(int meetingTime) {
		this.meetingTime = meetingTime;
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
				+ ", regDate=" + regDate + ", expDate=" + expDate + ", allowedNum=" + allowedNum + ", plIdx=" + plIdx
				+ ", capacity=" + capacity + ", info=" + info + ", isDel=" + isDel + ", money=" + money + ", openChat="
				+ openChat + ", meetingTime=" + meetingTime + ", age=" + age + "]";
	}
	


	
}
