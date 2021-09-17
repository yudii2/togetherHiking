package com.togetherHiking.board.validator;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

//filter에서 호출될 게시판 유효성 검증용 클래스
public class BoardForm {
	
	HttpServletRequest request;

	public BoardForm(ServletRequest request) {
		// TODO Auto-generated constructor stub
	}

	public static boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

	//제목, 내용 validating

}
