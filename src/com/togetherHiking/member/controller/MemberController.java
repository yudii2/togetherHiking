package com.togetherHiking.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.common.exception.PageNotFoundException;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.common.file.FileUtil;
import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.member.model.service.MemberService;
import com.togetherHiking.schedule.model.dto.Schedule;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");

		//localhost:7070/member/login-form
		//array -> [member, login-form]
		switch (uriArr[uriArr.length-1]) {
		case "login-page":
			  loginPage(request,response);
			break;
		case "login":
			  login(request,response);
			break;
		case "logout":
			  logout(request,response);
			break;
		case "join-page":
			  joinPage(request,response);
			break;
		case "join":
			  join(request,response);
			break;
		case "kakao-login":
			  kakaoLogin(request,response);
			break;
		case "kakao-join":
			  kakaoJoin(request,response);
			break;
		case "kakao-join-page":
			  kakaoJoinPage(request,response);
			break;			
		case "join-impl":
			  joinImpl(request,response);
			break;
		case "id-check": //회원가입시 아이디 중복확인
			 checkId(request,response);
			break;
		case "search-id":
			searchId(request,response);
			break;
		case "match-id":
			matchId(request,response);
			break;			
		case "search-password":
			searchPassword(request,response);
			break;
		case "check-nickname":
			  checkNickname(request,response);
			break;
		case "mypage":
			  mypage(request,response);
			break;
		case "reply":
			  myReply(request,response);
			break;
		case "profile-upload":
			profileUpload(request,response);
			break;
		case "modify-page":
			  modifyPage(request,response);
			break;
		case "modify":
			modify(request,response);
			break;
		case "my-schedule":
			  mySchedule(request,response);
			break;
		case "delete-post":
			  deletePost(request,response);	//유진 10/01
			break;
		case "delete-reply":
			  deleteReply(request,response);	//유진 10/01
			break;
		case "reset-pwd":
			resetPwd(request,response);	//창준 10/03
			break;
		case "update-pwd":
			updatePwd(request,response);
			break;
			
			
		
		default: throw new PageNotFoundException();

		}
	}

	private void matchId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		Member member = memberService.selectMemberByEmail(email);
		if(member == null) {
			request.setAttribute("msg", "회원이 존재하지 않습니다.");
			request.setAttribute("back", "1");
			request.getRequestDispatcher("/common/result").forward(request, response);		
		}
		request.setAttribute("member", member);
		request.getRequestDispatcher("/member/match-id").forward(request, response);
	}

	private void kakaoJoinPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("kakaoId", request.getParameter("userId"));
		request.getRequestDispatcher("/member/kakao-join").forward(request, response);
		
	}

	private void kakaoJoin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId = request.getParameter("kakaoId");
		
		String nickname = request.getParameter("nickname");
		String year = request.getParameter("birth");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String info = request.getParameter("information");
		
		String birth = year + "-" + month + "-" + day;

		Date date = Date.valueOf(birth);
		
		Member kakaomember = new Member();
		kakaomember.setUserId(userId);
		kakaomember.setNickname(nickname);
		kakaomember.setBirth(date);
		kakaomember.setInfo(info);
		
		memberService.insertkakaoMember(kakaomember);		

		request.getRequestDispatcher("/index").forward(request, response);		
	}

	private void kakaoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId = request.getParameter("userId");
			
		//존재하면 로그인 성공
		Member member = memberService.selectMemberById(userId);
		//System.out.println(member.getUserId());
		System.out.println(member);
		if(member == null || member.getUserId().equals("")) {
			//멤버테이블에서 아이디를 조회해서 존재하지 않으면 계속 진행
			request.setAttribute("kakaoId", userId);
			System.out.println(userId);
			response.getWriter().print("kakaoJoin");
			return;
		}
		System.out.println(member);
		request.getSession().setAttribute("authentication", member);
		response.getWriter().print("kakaoLogin");
			
	}
	
	//유진 10/3
	private void resetPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Member member = (Member) request.getAttribute("member");
		System.out.println("나 resetPwd컨트롤러 : " + member);
		request.setAttribute("member", member);
		request.getRequestDispatcher("/member/reset-pwd").forward(request, response);
		
	}

	private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		

		String userId = request.getParameter("userId");
		String password = request.getParameter("reset1");
		Member member = memberService.selectMemberById(userId);		
		int res = memberService.updateMember(member, password);
		if(res == 0) {
			request.setAttribute("msg", "비밀번호 수정이 완료되지 않았습니다.");
			request.setAttribute("back", "1");
			request.getRequestDispatcher("/common/result").forward(request, response);		
		};
		System.out.println(member);
		response.sendRedirect("/index");
		
	}

	private void deleteReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String[] replyIdx = request.getParameterValues("chk");

		for (int i = 0; i < replyIdx.length; i++) {
			memberService.deleteReply(replyIdx[i]);
		}
		
		
		request.setAttribute("msg", "댓글이 삭제되었습니다.");
		request.setAttribute("url", "/member/mypage");
		request.getRequestDispatcher("/common/result").forward(request, response);
		
		
	}

	//유진 10/01
	private void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String[] bdIdx = request.getParameterValues("chk");

		for (int i = 0; i < bdIdx.length; i++) {
			memberService.deletePost(bdIdx[i]);
		}
		
		
		request.setAttribute("msg", "게시글이 삭제되었습니다.");
		request.setAttribute("url", "/member/mypage");
		request.getRequestDispatcher("/common/result").forward(request, response);
		
	}

	private void checkId(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
			String userid = request.getParameter("userid");
			Member member = memberService.selectMemberById(userid);
			//회원가입시 아이디 중복값 확인
			if(member == null) {
				response.getWriter().print("available");
			}else {
				response.getWriter().print("disable");
			}	
		}
		
	private void searchPassword(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		request.getRequestDispatcher("/member/search-password").forward(request, response);
		
	}

	private void searchId(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		request.getRequestDispatcher("/member/search-id").forward(request, response);
		
	}

	//유진 10/1 
	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		memberService.insertMember((Member) session.getAttribute("persistUser"));

		// 같은 persistUser값이 두 번 DB에 입력되지 않도록 사용자 정보와 인증을 만료시킴
		session.removeAttribute("persistUser");
		session.removeAttribute("persist-token");
		
		request.setAttribute("msg", "회원가입이 완료되었습니다.");
		request.setAttribute("url", "/member/login-page");
		request.getRequestDispatcher("/common/result").forward(request, response);
				
	}
	private void joinPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/join-page").forward(request, response);
		
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId =  request.getParameter("userId");
		String password = request.getParameter("password");
		String passwordcheck = request.getParameter("user_PW2");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("user_email");
		String year = request.getParameter("birth");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String info = request.getParameter("information");
		
		String birth = year + "-" + month + "-" + day;

		Date date = Date.valueOf(birth);
		
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		member.setNickname(nickname);
		member.setEmail(email);
		member.setBirth(date);
		member.setInfo(info);
		  				
		String persistToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("persistUser", member);
		request.getSession().setAttribute("persist-token", persistToken);
		
		

		memberService.authenticateEmail(member, persistToken);
		
		

		request.setAttribute("msg", "회원가입을 위한 이메일이 발송되었습니다.");
		request.setAttribute("url", "/member/login-page");
		request.getRequestDispatcher("/common/result").forward(request, response);
		
			
			
		}
		
		
		

	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("authentication");
		request.getSession().removeAttribute("profile");
		
		response.sendRedirect("/");
		
	}
	//유진 09/29
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId =  request.getParameter("userId");
		String password = request.getParameter("password");
		
		//유저 정보와 프로필
		Member member = memberService.memberAuthenticate(userId,password);
		
		if(member == null) {
			response.sendRedirect("/member/login-page?err=1");
			return;
		}
		request.getSession().setAttribute("authentication", member);

		response.sendRedirect("/index");
		
	}

	private void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/login-page").forward(request, response);
		
	}
	



	//유진 09/30
	private void profileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUtil util = new FileUtil();
		Map<String,FileDTO> param = util.profileUpload(request);
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();	

		//file정보 가져오기
		FileDTO fileDTO = param.get("com.togetherHiking.files");
		
		//프로필정보 없으면 insert , 이미 존재하면 update
		if(memberService.selectProfile(userId).getRenameFileName() == null) {
			memberService.insertProfile(userId, fileDTO);
		}else {
			int updateProfile = memberService.updateProfile(userId, fileDTO);
			
			if(updateProfile == 0) {
				request.setAttribute("msg", "프로필 등록에 실패하였습니다.");
			}
			request.setAttribute("msg", "프로필 등록에 성공하였습니다.");
		}
		member = memberService.getMemberDetail(member);
		request.getSession().setAttribute("authentication", member);	//세션에 멤버객체 재등록(프로필 포함)
		
		request.setAttribute("url", "/member/mypage");
		request.getRequestDispatcher("/common/result").forward(request, response);
			
	}
	
	private void checkNickname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nickname = request.getParameter("nickname");
		System.out.println(nickname);
		Member member = memberService.selectByNickname(nickname);
		if(member == null) {
			response.getWriter().print("available");	//js에게 전달
		}else {
			response.getWriter().print("disable");
		}
	}
	//유진 10/01
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member sessionMember = (Member) request.getSession().getAttribute("authentication");
		String userId = sessionMember.getUserId();
		
		String password = request.getParameter("new_pw");
		String nickname = request.getParameter("nickname");
		String info = request.getParameter("info");
		
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		member.setNickname(nickname);
		member.setInfo(info);
		
		//서비스단에게 멤버정보수정 요청
		if(memberService.updateMember(member,"") != 0) {
			request.setAttribute("msg", "회원정보 수정을 완료하였습니다.");
			request.setAttribute("url", "/member/modify-page");
		}else {
			request.setAttribute("msg", "회원정보 수정에 실패하였습니다.");
			request.setAttribute("back", "1");
		}
		request.getRequestDispatcher("/common/result").forward(request, response);
		
	}
	//유진 09/30
	private void mySchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		
		List<Board> myPosts = memberService.selectMyPostById(userId);	
		Map<String,List> reply = memberService.selectMyReply(userId);
		
		
		List<Schedule> schedule = memberService.selectMySchedule(userId);
		
		
		request.setAttribute("mySchedule", schedule);

		request.setAttribute("myPosts", myPosts);
		request.setAttribute("myReply",reply);		
		
		request.getRequestDispatcher("/member/my-schedule").forward(request, response);
		
		
		
	}

	//유진 10/01
	private void modifyPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member session = (Member) request.getSession().getAttribute("authentication");
		
		//수정된 정보 다시 세션에 저장
		Member member = memberService.getMemberDetail(session);
		request.getSession().setAttribute("authentication", member);
		request.getRequestDispatcher("/member/modify-page").forward(request, response);
		
	}

	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();

		List<Board> myPosts = memberService.selectMyPostById(userId);	//전체 게시글 조회
		Map<String,List> reply = memberService.selectMyReply(userId);

		request.setAttribute("myPosts", myPosts);
		request.setAttribute("myReply",reply);
		
		
		//Pagination
		String paramPage = request.getParameter("p");
		int page = 1;	//parameter가 null일 경우를 대비해 초기값 1로 선언
		
		if(paramPage != null && !paramPage.equals("")) {
			page = Integer.parseInt(paramPage);
		}

		List<Board> postByPage = memberService.selectPostByPage(userId, page);
		request.setAttribute("postByPage", postByPage);
		request.getRequestDispatcher("/member/mypage").forward(request, response);
	}
	

	private void myReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		
		List<Board> myPosts = memberService.selectMyPostById(userId);	
		Map<String,List> reply = memberService.selectMyReply(userId);	//boardList객체와 reply객체가 같이 담김. but, jsp에서는 하나의 객체를 꺼내야만 사용할 수 있다.

		request.setAttribute("myPosts", myPosts);
		request.setAttribute("myReply",reply);

		request.getRequestDispatcher("/member/my-reply").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}