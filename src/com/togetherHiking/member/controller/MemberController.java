package com.togetherHiking.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.togetherHiking.board.model.dto.Board;
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
		case "join-impl":
			  joinImpl(request,response);
			break;
		case "check-id":
			  checkID(request,response);
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
		case "search-id":
			searchId(request,response);
			break;
		case "search-password":
			searchPassword(request,response);
			break;
		case "id-check": //회원가입시 아이디 중복확인
			 checkId(request,response);
			break;
		default:/* throw new PageNotFoundException(); */

		}
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

	private void checkID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/check-id").forward(request, response);
		String userid = request.getParameter("userid");
		Member member = memberService.selectMemberById(userid);
		//회원가입시 아이디 중복값 확인
		if(member == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}
		
	}

	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		memberService.insertMember((Member) session.getAttribute("persistUser"));

		// 예전처럼 예외처리를 했을 경우 직접 request.setAttribute를 사용해 메시지를 입력해서 넣어줬다.
		// 지금과의 차이점이 무엇일까
		// 가독성의 차이. 예외상황이라는 것이 더 명확하게 보인다.

		// 같은 persistUser값이 두 번 DB에 입력되지 않도록 사용자 정보와 인증을 만료시킴
		session.removeAttribute("persistUser");
		session.removeAttribute("persist-token");
		response.sendRedirect("/member/login-page");
		
	}
	private void joinPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/join-page").forward(request, response);
		
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId =  request.getParameter("userId");
		String password = request.getParameter("user_PW1");
		String passwordcheck = request.getParameter("user_PW2");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("user_email");
		String year = request.getParameter("birth");
		String month = request.getParameter("month");
		String date = request.getParameter("day");
		String info = request.getParameter("information");
		System.out.println(info);
		long temp = Integer.parseInt(date+month+year);
		
		
		Date dat = new Date(temp);
		
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		member.setNickname(nickname);
		member.setEmail(email);
		member.setBirth(dat);
		member.setInfo(info);
		  
		
		memberService.insertMember(member);
		response.sendRedirect("/member/login-page");
			
			
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
		if(memberService.updateMember(member) != 0) {
			request.setAttribute("msg", "회원정보 수정을 완료하였습니다.");
			request.setAttribute("url", "/member/mypage");
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

	private void modifyPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/modify-page").forward(request, response);
		
	}

	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();

		List<Board> myPosts = memberService.selectMyPostById(userId);	
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