package com.togetherHiking.mountain.model.dto;

public class Mountain {

	private String mountainIdx;
	private String mName;
	private String mLoc;
	private String mInfo;
	private int ContentCnt;
	private String mHight;
	private String Transport;
	
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
		return ContentCnt;
	}
	public void setContentCnt(int contentCnt) {
		ContentCnt = contentCnt;
	}
	public String getmHight() {
		return mHight;
	}
	public void setmHight(String mHight) {
		this.mHight = mHight;
	}
	public String getTransport() {
		return Transport;
	}
	public void setTransport(String transport) {
		Transport = transport;
	}
	@Override
	public String toString() {
		return "Mountain [mountainIdx=" + mountainIdx + ", mName=" + mName + ", mLoc=" + mLoc + ", mInfo=" + mInfo
				+ ", ContentCnt=" + ContentCnt + ", mHight=" + mHight + ", Transport=" + Transport + "]";
	}
	
	
}
