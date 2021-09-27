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

import com.togetherHiking.board.model.service.BoardService;
import com.togetherHiking.common.code.ErrorCode;
import com.togetherHiking.common.code.member.MemberGrade;
import com.togetherHiking.common.exception.HandleableException;
import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.schedule.model.dto.Schedule;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */

//권한관련 필터
//회원관리(탈퇴시킬 권한) admin만 허용
//게시글 수정 삭제 권한 host ,admin (일반 member등급을 거르는)
//
//가입시 이메일 인증 절차 -> 이메일 페이지에서 사이트 돌아가기 버튼 누르면 저장된 토큰과 동일한지 체크
//즉, 우리가 토큰을 부여해준 회원과 일치하는지를 체크

//회원이 마이페이지 요청했을 때 세션(24h)이 종료되었는지 체크

//-------------------------------------------------------------------
//board에서 로그인유저와 작성자idx가 동일할 경우에만 수정/삭제 버튼 표시
//board수정/삭제 요청 시 세션이 유효한지 판단

//schedule에서 수정/삭제 버튼 host일 경우에만 표시
//schedule/edit, schedule/delete요청시 세션이 유효한지 판단

// uriArr[1]
// 1. member(회원)
// 		member/join-impl (메일template) : persist-token일치 여부를 판단
// 		member/mypage : 세션이 유효한지 판단

// 2. board
//		board/board-form : 세션이 유효한지 판단
//		board/upload : 세션이 유효한지 판단
//		board/edit : 세션이 유효한지 판단
//		board/delete : 세션이 유효한지 판단

// 3. schedule
//		schedule/schedule-form : 세션이 유효한지 판단
//		schedule/upload : 세션이 유효한지 판단
//		schedule/edit : 세션이 유효한지 판단
//		schedule/delete: 세션이 유효한지 판단

//4. admin(관리자)
//		admin/home
//		추후 추가...
//		admin이 해야 할 일: 모임 승인/ 취소
//			--> admin에게 보여줘야 할 데이터 :모임 내역 
//				(기능 : 모임 승인 처리 --> 모임정보 받아서 모임(schedule)db에다가 등록)
//								





public class AuthorizationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthorizationFilter() {
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
			switch (uriArr[1]) {
				case "member":
					memberAuthorize(httpRequest, httpResponse, uriArr);
					break;
				case "admin":
					adminAuthorize(httpRequest, httpResponse, uriArr);
					break;
				case "board":
					boardAuthorize(httpRequest, httpResponse, uriArr);
					break;
				case "schedule":
					scheduleAuthorize(httpRequest, httpResponse, uriArr);
					break;
				default:
					break;
			}
		}
		
		chain.doFilter(request, response);
	}

	private void scheduleAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {

		//등록된 일정이 있는지 없는지 is_host
		switch (uriArr[2]) {
		//세션 인증 + IS_HOST == N일때만 접근 가능
		case "schedule-form": 
			if(httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			hostAuthorize(httpRequest, httpResponse);
			break;
		//세션 인증하면 접근가능
		case "upload":
			if(httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			break;
		//로그인 유저 == 작성자 비교 후 edit요청이 들어오는 경우
		case "edit":
			if(httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			scheduleHostIsSame(httpRequest,httpResponse);
		case "delete":
			if(httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			scheduleHostIsSame(httpRequest,httpResponse);
			break;
		default:
			break;
		}		
		
		
	}

	private void scheduleHostIsSame(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		//로그인 유저 == 작성자 비교
		Member member = (Member) httpRequest.getSession().getAttribute("authentication");
		
//		ScheduleController scheduleController = new ScheduleController();
//		Schedule schedule = scheduleController.checkEditor(httpRequest.getAttribute("boardIdx"));
//	
		
		Schedule schedule = (Schedule) httpRequest.getAttribute("schedule");
		if(!member.getUserId().equals(schedule.getUserId())) {
			throw new HandleableException(ErrorCode.UNAUTHORIZED_PAGE);
		}
	}

	
	
	private void hostAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		//로그인 시 session 객체에 authentication 속성을 부여하고 value값으로 member객체 담아옴.
		Member member = (Member) httpRequest.getSession().getAttribute("authentication");
		if(member.getIsHost().equals("Y")){
			throw new HandleableException(ErrorCode.NO_MORE_HOSTING);
		}
		
		
	}

	private void boardAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		// 2. board
//		board/board-form : 세션이 유효한지 판단
//		board/upload : 세션이 유효한지 판단
//		board/edit : 세션이 유효한지 판단
//		board/delete : 세션이 유효한지 판단
		Member member = (Member) httpRequest.getSession().getAttribute("authentication");

		switch (uriArr[2]) {
		case "board-form": 
			if(member == null) {
				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			break;
		//로그인 유저 - upload가능
		case "upload":
			if(member == null) {
				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			break;
		case "add-reply":
			if(member == null) {
				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			break;
		//로그인 유저 == 작성자 비교 후 edit요청이 들어오는 경우
//		case "edit":
//			if(httpRequest.getSession().getAttribute("authentication") == null) {
//				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
//			}
//			authBoardEditor(httpRequest,httpResponse);
//			break;
		case "delete-board":
			if(member == null) {
				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			if(authBoardWriter(httpRequest,httpResponse,member)) {
				throw new HandleableException(ErrorCode.UNMATCHED_USER_AUTH_ERROR);
			}
			break;
		case "delete-reply":
			if(member == null) {
				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			if(authReplyWriter(httpRequest,httpResponse,member)) {
				throw new HandleableException(ErrorCode.UNMATCHED_USER_AUTH_ERROR);
			}
			break;
		default: 
			break;
		}
	}

	private boolean authReplyWriter(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Member member) {
		BoardService boardService = new BoardService();
		
		String idx = httpRequest.getParameter("rp_idx");
		String userId = boardService.getWriterId("reply", idx);
		
		if(member.getUserId().equals(userId)) {
			return true;
		}else {
			return false;
		}
	}

	private boolean authBoardWriter(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Member member) {
		BoardService boardService = new BoardService();
		
		String idx = httpRequest.getParameter("bd_idx");
		String userId = boardService.getWriterId("board", idx);
		
		if(member.getUserId().equals(userId)) {
			return true;
		}else {
			return false;
		}
		
	}

	private void adminAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		Member member = (Member) httpRequest.getSession().getAttribute("authentication");
		System.out.println(member);
		System.out.println(member.getGrade());
		MemberGrade grade = MemberGrade.valueOf(member.getGrade());
		
		if(!grade.ROLE.equals("admin")) {
			throw new HandleableException(ErrorCode.UNAUTHORIZED_PAGE);
		}
	}

	private void memberAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		switch (uriArr[2]) {
		//가입시 이메일 인증절차(email-template)
		case "join-impl":
			String serverToken = httpRequest.getParameter("persist-token");
			String clientToken = httpRequest.getParameter("persist-token");
			
			if(serverToken == null || !serverToken.equals(clientToken)) {
				//같지않으면,
				throw new HandleableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
			}
			break;
		//비로그인or 세션 유효하지 않은 유저가 마이페이지 요청시
		case "mypage":
			if(httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandleableException(ErrorCode.REDIRECT_LOGIN_PAGE);
			}
			break;
		default:
			break;
		}
		
	}



	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
