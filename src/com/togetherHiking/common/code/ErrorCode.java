package com.togetherHiking.common.code;

public enum ErrorCode {
	//작업중
	
	DATABASE_ACCESS_ERROR("데이터베이스와 통신 중 에러가 발생하였습니다."),
	FAILED_VALIDATED_ERROR("데이터의 양식이 적합하지 않습니다."),
	MAIL_SEND_FAIL_ERROR("이메일 발송 중 에러가 발생하였습니다."),
	HTTP_CONNECT_ERROR("HTTP 통신 중 에러가 발생하였습니다."),
	AUTHENTICATION_FAILED_ERROR("유효하지 않은 인증입니다."),
	UNAUTHORIZED_PAGE("접근 권한이 없는 페이지 입니다."),
	REDIRECT_LOGIN_PAGE("로그인이 필요합니다.","/member/login-page"),
	FAILED_FILE_UPLOAD_ERROR("파일업로드에 실패했습니다."),
	NO_MORE_HOSTING("이미 등록된 일정이 존재합니다. 더 추가하실 수 없습니다.","/schedule/calendar"),
	FAILED_BOARD_ACCESS_ERROR("게시물 수정에 실패했습니다.","/board/board-page"),
	UNMATCHED_USER_AUTH_ERROR("권한 없는 사용자입니다.","/board/board-page");
	
	public final String MESSAGE;
	public final String URL;
	
	ErrorCode(String msg){
		this.MESSAGE = msg;
		this.URL = "/index";
	}
	
	ErrorCode(String msg, String url){
		this.MESSAGE = msg;
		this.URL = url;
	}
	
	
	
	
}
