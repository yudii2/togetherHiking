package com.togetherHiking.member.model.dto;

import java.sql.Date;

import com.togetherHiking.common.file.FileDTO;

public class Member {
	
	private String userId;
	private String password;
	private Date birth;
	private String info;
	private String nickname;
	private Date joinDate;
	private String email;
	private String grade;
	private int isHost;
	private int isLeave;
	private int postCnt;
	private int replyCnt;
	private String profile;
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Date getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getIsHost() {
		return isHost;
	}


	public void setIsHost(int isHost) {
		this.isHost = isHost;
	}


	public int getIsLeave() {
		return isLeave;
	}


	public void setIsLeave(int isLeave) {
		this.isLeave = isLeave;
	}



	public int getPostCnt() {
		return postCnt;
	}


	public void setPostCnt(int postCnt) {
		this.postCnt = postCnt;
	}


	public int getReplyCnt() {
		return replyCnt;
	}


	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	
	
	public String getProfile() {
		return profile;
	}


	public void setProfile(FileDTO profile) {
		if(profile.getSavePath() == null) {
			this.profile = null;
		}else {
			this.profile = profile.getSavePath() + profile.getRenameFileName();

		}
	}


	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", birth=" + birth + ", info=" + info
				+ ", nickname=" + nickname + ", joinDate=" + joinDate + ", email=" + email + ", grade=" + grade
				+ ", isHost=" + isHost + ", isLeave=" + isLeave + "]";
	}







}
