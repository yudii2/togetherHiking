package com.togetherHiking.mountain.model.dto;

import java.sql.Date;

public class Mountain {

	private String mountainIdx;
	private String mName;
	private String mLoc;
	private String mInfo;
	private String ContentCnt;
	private Date mHight;
	private int Transport;
	
	public Mountain() {
		// TODO Auto-generated constructor stub
	}

	public String getMountainIdx() {
		return mountainIdx;
	}

	public void setMountainIdx(String mountainIdx) {
		this.mountainIdx = mountainIdx;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmLoc() {
		return mLoc;
	}

	public void setmLoc(String mLoc) {
		this.mLoc = mLoc;
	}

	public String getmInfo() {
		return mInfo;
	}

	public void setmInfo(String mInfo) {
		this.mInfo = mInfo;
	}

	public String getContentCnt() {
		return ContentCnt;
	}

	public void setContentCnt(String contentCnt) {
		ContentCnt = contentCnt;
	}

	public Date getmHight() {
		return mHight;
	}

	public void setmHight(Date mHight) {
		this.mHight = mHight;
	}

	public int getTransport() {
		return Transport;
	}

	public void setTransport(int transport) {
		Transport = transport;
	}

	@Override
	public String toString() {
		return "Mountain [mountainIdx=" + mountainIdx + ", mName=" + mName + ", mLoc=" + mLoc + ", mInfo=" + mInfo
				+ ", ContentCnt=" + ContentCnt + ", mHight=" + mHight + ", Transport=" + Transport + "]";
	}
	
	
}
