package com.togetherHiking.mountain.model.dto;

public class Mountain {

	private String mountainIdx;
	private String mName;
	private String mLoc;
	private String mInfo;
	private String mHight;
	private String X_COOR;
	private String Y_COOR;
	
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

	public String getX_COOR() {
		return X_COOR;
	}

	public void setX_COOR(String x_COOR) {
		X_COOR = x_COOR;
	}

	public String getY_COOR() {
		return Y_COOR;
	}

	public void setY_COOR(String y_COOR) {
		Y_COOR = y_COOR;
	}

	@Override
	public String toString() {
		return "Mountain [mountainIdx=" + mountainIdx + ", mName=" + mName + ", mLoc=" + mLoc + ", mInfo=" + mInfo
				+ ", mHight=" + mHight + ", X_COOR=" + X_COOR + ", Y_COOR=" + Y_COOR + "]";
	}
	
	
	
}
