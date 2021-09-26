package com.togetherHiking.mountain.model.dto;

public class Mountain {

	private String mountainIdx;
	private String mName;
	private String mLoc;
	private String mInfo;
	private String mHight;
	private String xCoor;
	private String yCoor;
	
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

	public String getmHight() {
		return mHight;
	}

	public void setmHight(String mHight) {
		this.mHight = mHight;
	}

	public String getxCoor() {
		return xCoor;
	}

	public void setxCoor(String xCoor) {
		this.xCoor = xCoor;
	}

	public String getyCoor() {
		return yCoor;
	}

	public void setyCoor(String yCoor) {
		this.yCoor = yCoor;
	}

	@Override
	public String toString() {
		return "Mountain [mountainIdx=" + mountainIdx + ", mName=" + mName + ", mLoc=" + mLoc + ", mInfo=" + mInfo
				+ ", mHight=" + mHight + ", xCoor=" + xCoor + ", yCoor=" + yCoor + "]";
	}

	
	
	
}
