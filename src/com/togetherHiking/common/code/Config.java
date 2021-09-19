package com.togetherHiking.common.code;

public enum Config {
	
	//DOMAIN("http://www.pclass.com"),
	DOMAIN("http://localhost:7070"),
	SMTP_AUTHENTICATION_ID("khpclass1@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("@project1"),
	COMPANY_EMAIL("khpclass1@gmail.com"),
	//UPLOAD_PATH("C:\\CODE\\after\\upload\\"); 운영서버
	UPLOAD_PATH("C:\\SEMI\\upload\\"); //개발서버
	
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	}	

}
