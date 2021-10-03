package com.togetherHiking.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.togetherHiking.board.validator.BoardForm;
import com.togetherHiking.common.code.ErrorCode;
import com.togetherHiking.common.exception.HandleableException;
import com.togetherHiking.member.validator.JoinForm;
import com.togetherHiking.member.validator.ModifyForm;
import com.togetherHiking.member.validator.UpdatePwdForm;
import com.togetherHiking.reply.validator.ReplyForm;
import com.togetherHiking.schedule.validator.ScheduleForm;

public class ValidatorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ValidatorFilter() {
        // TODO Auto-generated constructor stub
    }

	/** 
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String[] uriArr = httpRequest.getRequestURI().split("/");
		
		if(uriArr.length != 0) {
			
			//수정, 추가, 삭제의 경우 DB에 영향 => validating 필요
			//	ex) 회원가입, 탈퇴, 게시글 수정, 삭제
			String redirectURI = null;

			switch (uriArr[1]) {
				case "member":
					redirectURI = memberValidation(httpRequest, httpResponse, uriArr);
					break;
				case "schedule":
					redirectURI = scheduleValidation(httpRequest, httpResponse, uriArr);
					break;
				case "board":
					redirectURI = boardValidation(httpRequest, httpResponse, uriArr);
					break;
				case "reply":
					redirectURI = replyValidation(httpRequest, httpResponse, uriArr);
					break;
				
				default:
					break;
			}
			if(redirectURI != null) {
				httpResponse.sendRedirect(redirectURI);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	private String replyValidation(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		String redirectURI = null;
		
		switch (uriArr[2]) {
		case "add-reply":
			ReplyForm replyFrom = new ReplyForm(httpRequest);
			if(!replyFrom.test()) {
				redirectURI = "/reply/board-detail?bd_idx=" + httpRequest.getParameter("bd_idx") + "&err=1";
				return redirectURI;
			}
			break;
		default:
			break;
		}
		return redirectURI;
	}

	private String scheduleValidation(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		String redirectURI = null;
		
		ScheduleForm scheduleForm = new ScheduleForm(httpRequest);
		
		switch (uriArr[2]) {
		case "upload":
			/*
			 * if(!scheduleForm.test()) { redirectURI = "/schedule/calendar"; }
			 */break;
		case "edit":
			if(!scheduleForm.test()) {
				redirectURI = "/schedule/calendar";
			}
			break;

		default:
			break;
		}
		return redirectURI;
	}

	private String boardValidation(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		String redirectURI = null;
		
		switch (uriArr[2]) {
		case "upload":
			BoardForm boardForm = new BoardForm(httpRequest);
			if(!boardForm.test()) {
				redirectURI = "/board/board-form?err=1";
				return redirectURI;
			}
			break;
			
		default:
			break;
		}
		return redirectURI;
	}

	private String memberValidation(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		String redirectURI = null;
		JoinForm joinForm = new JoinForm(httpRequest);;
		ModifyForm modifyForm = new ModifyForm(httpRequest);
		UpdatePwdForm updatePwdForm = new UpdatePwdForm(httpRequest);
		
		switch (uriArr[2]) {
		case "join":
			String password = httpRequest.getParameter("password");
			if(password == null) {
				redirectURI = "/index";
			}
			
			if(!joinForm.test()) {  
				redirectURI = "/member/join-page?err=1";	
			}break;

		case "join-impl":
			String persistToken = httpRequest.getParameter("persist-token");	
			if(!persistToken.equals(httpRequest.getSession().getAttribute("persist-token"))) {	
				throw new HandleableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
			}
			break;
		case "update-pwd":
			if(!updatePwdForm.test()) {
				redirectURI = "/member/reset-pwd?err=1";
			}
			break;
		case "modify":
			if(!modifyForm.test()) {
				redirectURI = "/member/modify-page?err=1";
			}break;
		default:
			break;
		}
		return redirectURI;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
