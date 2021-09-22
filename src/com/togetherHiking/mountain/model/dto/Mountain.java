package com.togetherHiking.mountain.model.dto;

public class Mountain {

	private String mountainIdx;
	private String mName;
	private String mLoc;
	private String mInfo;
	private int contentCnt;
	private String mHight;
	private String transport;
	
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

	public int getContentCnt() {
		return contentCnt;
	}

	public void setContentCnt(int contentCnt) {
		this.contentCnt = contentCnt;
	}

	public String getmHight() {
		return mHight;
	}

	public void setmHight(String mHight) {
		this.mHight = mHight;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	@Override
	public String toString() {
		return "Mountain [mountainIdx=" + mountainIdx + ", mName=" + mName + ", mLoc=" + mLoc + ", mInfo=" + mInfo
				+ ", contentCnt=" + contentCnt + ", mHight=" + mHight + ", transport=" + transport + "]";
	}
	
	
	
}
