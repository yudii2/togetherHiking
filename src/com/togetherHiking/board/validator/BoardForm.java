package com.togetherHiking.board.validator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

//filter에서 호출될 게시판 유효성 검증용 클래스
public class BoardForm {
	
	HttpServletRequest request;
	private String title;
	private String subject;
	private String content;
	private Map<String,String> faildValidation = new HashMap<String,String>(); // validation에 실패한 개체 저장

	public BoardForm(ServletRequest request) {
		this.request = (HttpServletRequest) request;
		this.title = request.getParameter("title");
		this.subject = request.getParameter("subject");
		this.content = request.getParameter("content");
	}

	public boolean test() {
		boolean isFailed = false;
		// 제목이 빈칸이라면
		if(title == null || title.isEmpty()) {
			faildValidation.put("title", title);
			isFailed = true;
			System.out.println("title null");
		}
		// 말머리가 "잡담" 또는 "후기" 가 아니라면
		if(subject == null || !(subject.equals("잡담") || subject.equals("후기"))) {
			faildValidation.put("subject", subject);
			isFailed = true;
			System.out.println("subject null");
		}
		// 내용이 비어있다면
		if(content == null || content.isEmpty()) {
			faildValidation.put("content", content);
			isFailed = true;
			System.out.println("content null");
		}
		
		if(isFailed) {
			System.out.println("BoardForm.java 에러 발생");
			request.getSession().setAttribute("boardValid",faildValidation);
			request.getSession().setAttribute("boardForm",this);
			return false;
		}else {
			request.getSession().removeAttribute("boardForm");
			request.getSession().removeAttribute("boardValid");
			return true;
		}
		
	}

	public String getTitle() {
		return title;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}
	
}
