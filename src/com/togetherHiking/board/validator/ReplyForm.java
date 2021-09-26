package com.togetherHiking.board.validator;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class ReplyForm {
	
	HttpServletRequest request;
	private String content;
	
	public ReplyForm(ServletRequest request) {
		this.request = (HttpServletRequest) request;
		this.content = request.getParameter("content");
	}
	
	public boolean test() {
		boolean isFailed = false;
		//내용이 빈칸이라면
		if(content.equals("") || content == null) {
			isFailed = true;
		}
		
		if(isFailed) {
			request.getSession().setAttribute("replyValid",content);
			request.getSession().setAttribute("replyForm",this);
			return false;
		}else {
			request.getSession().removeAttribute("replyForm");
			request.getSession().removeAttribute("replyValid");
			return true;
		}
		
	}

	public String getContent() {
		return content;
	}
	
	
}
