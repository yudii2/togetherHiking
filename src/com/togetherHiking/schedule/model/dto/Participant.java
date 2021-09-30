package com.togetherHiking.schedule.model.dto;

import com.togetherHiking.common.file.FileDTO;

public class Participant {

	private String plIdx;
	private String scIdx;
	private String userId;
	private String userNickName;
	private String userInfo;
	private FileDTO profileDTO;
	
	public FileDTO getProfileDTO() {
		return profileDTO;
	}
	public void setProfileDTO(FileDTO profileDTO) {
		this.profileDTO = profileDTO;
	}
	public String getPlIdx() {
		return plIdx;
	}
	public void setPlIdx(String plIdx) {
		this.plIdx = plIdx;
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
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}



	
	
}
