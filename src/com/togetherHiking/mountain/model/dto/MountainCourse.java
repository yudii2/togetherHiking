package com.togetherHiking.mountain.model.dto;

public class MountainCourse {
		private String courseIdx;
		private String mountainIdx;
		private String name;
		private int distance;
		private int upTime;
		private int downTime;
		private int level;
		private String calories;
		
	public MountainCourse() {
		// TODO Auto-generated constructor stub
	}

	public String getCourseIdx() {
		return courseIdx;
	}

	public void setCourseIdx(String courseIdx) {
		this.courseIdx = courseIdx;
	}

	public String getMountainIdx() {
		return mountainIdx;
	}

	public void setMountainIdx(String mountainIdx) {
		this.mountainIdx = mountainIdx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getUpTime() {
		return upTime;
	}

	public void setUpTime(int upTime) {
		this.upTime = upTime;
	}

	public int getDownTime() {
		return downTime;
	}

	public void setDownTime(int downTime) {
		this.downTime = downTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	@Override
	public String toString() {
		return "MountainCourse [courseIdx=" + courseIdx + ", mountainIdx=" + mountainIdx + ", name=" + name
				+ ", distance=" + distance + ", upTime=" + upTime + ", downTime=" + downTime + ", level=" + level
				+ ", calories=" + calories + "]";
	}
	
	
	
}
