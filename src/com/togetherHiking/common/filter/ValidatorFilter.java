package com.togetherHiking.common.filter;

import java.io.IOException;
import java.util.Arrays;

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
				//
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

	private String scheduleValidation(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		String redirectURI = null;
		
		ScheduleForm scheduleForm = new ScheduleForm(httpRequest);
		
		switch (uriArr[2]) {
		case "upload":
			if(!scheduleForm.test()) {
				redirectURI = "/schedule/calendar";	
			}break;
		//edit완료 버튼
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
		BoardForm boardForm = new BoardForm(httpRequest);
		String redirectURI = null;
		
		switch (uriArr[2]) {
//		case "upload":
//			if(!boardForm.test()) {
//				redirectURI = "/board/board-form?err=1";
//				return redirectURI;
//			}
//		case "edit":
//			if(!boardForm.test()) {
//				redirectURI = "/board/board-form?err=1";
//				return redirectURI;
//			}

		default:
			break;
		}
		return redirectURI;
	}

	private String memberValidation(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		String redirectURI = null;
		JoinForm joinForm = new JoinForm(httpRequest);

		switch (uriArr[2]) {
		//member/join
		case "join":
			if(!joinForm.test()) {
				redirectURI = "/member/join-form?err=1";	//err파라미터 전달(왜? 이때만 validation출력)
			}break;
		//가입시 이메일 인증절차 : 발송된 이메일 form에서 사이트로 돌아가는 버튼(가입완료)을 클릭하면 거쳐야하는 절차 
		//				--> 우리가 우리사이트에서 외부로 넘어갈때 토큰(유니크한값)을 발생시켜 세션에 저장
		//				--> 뷰단에 저장된 토큰값이 일치하는지를 비교 
		case "join-impl":
			String persistToken = httpRequest.getParameter("persist-token");	//뷰단에 저장된 토큰
			if(!persistToken.equals(httpRequest.getSession().getAttribute("persist-token"))) {	//우리사이트에서 외부로 넘어갈때 토큰(유니크한값)을 발생시켜 세션에 저장
				//같지않으면,
				throw new HandleableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
			}
			break;
		case "modify":
			if(!joinForm.modifyTest()) {
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
