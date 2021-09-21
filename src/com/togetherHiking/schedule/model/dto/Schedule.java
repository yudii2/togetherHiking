package com.togetherHiking.schedule.model.dto;

import java.sql.Date;

public class Schedule {
	
	private String scIdx;
	private String userId;
	private Date meetingDate;
	private String place;
	private Date expDate;
	private String plIdx;
	private Date regDate;
	private String info;
	private int capacity;
	private String isDel;

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

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getPlIdx() {
		return plIdx;
	}

	public void setPlIdx(String plIdx) {
		this.plIdx = plIdx;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "Schedule [scIdx=" + scIdx + ", userId=" + userId + ", meetingDate=" + meetingDate + ", place=" + place
				+ ", expDate=" + expDate + ", plIdx=" + plIdx + ", regDate=" + regDate + ", info=" + info
				+ ", capacity=" + capacity + ", isDel=" + isDel + "]";
	}
	
	
	
}
