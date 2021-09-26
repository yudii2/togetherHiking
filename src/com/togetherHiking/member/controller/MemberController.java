package com.togetherHiking.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.board.model.dto.Reply;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.common.file.FileUtil;
import com.togetherHiking.common.file.MultiPartParams;
import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.member.model.service.MemberService;

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
		default:/* throw new PageNotFoundException(); */

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
		
	}

	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubs
		
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void joinPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/join-page").forward(request, response);
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("authentication");
		response.sendRedirect("/");
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId =  request.getParameter("userId");
		String password = request.getParameter("password");
		
		//유저 정보와 프로필
		Member member = memberService.memberAuthenticate(userId,password);
		FileDTO profile = memberService.selectProfile(userId).get("profile");
		
		if(member == null) {
			response.sendRedirect("/member/login-page?err=1");
			return;
		}
		request.getSession().setAttribute("authentication", member);
		request.getSession().setAttribute("profile", profile);
		response.sendRedirect("/index");
		
	}

	private void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/login-page").forward(request, response);
		
	}

	private void profileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUtil util = new FileUtil();
		Map<String,FileDTO> param = util.profileUpload(request);
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();	

		//file정보 가져오기
		FileDTO fileDTO = param.get("com.togetherHiking.files");
		System.out.println(fileDTO);
		
		//프로필정보 없으면 insert , 이미 존재하면 update
		if(memberService.selectProfile(userId) == null) {
			memberService.insertProfile(userId, fileDTO);
		}else {
			int updateProfile = memberService.updateProfile(userId, fileDTO);
			
			if(updateProfile == 0) {
				request.setAttribute("msg", "프로필 등록에 실패하였습니다.");
			}
		
			FileDTO profile = memberService.selectProfile(userId).get("profile");
			request.getSession().setAttribute("profile", profile);	//세션에 프로필 재등록
			request.setAttribute("msg", "프로필 등록에 성공하였습니다.");

			
			request.setAttribute("url", "/member/mypage");
			request.getRequestDispatcher("/common/result").forward(request, response);

		}
		


			
		//mypage.jsp로 넘어가지 않는 문제
	}
	
	private void checkNickname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nickname = request.getParameter("nickname");
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

	private void mySchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/my-schedule").forward(request, response);
		
	}

	private void modifyPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/modify-page").forward(request, response);
		
	}

	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		if(member == null) {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("url", "/member/login-page");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		
		List<Board> myPosts = memberService.selectMyPostById(member.getUserId());	
		int myPostCnt = memberService.countMyPost(member.getUserId());
		
		//댓글수 가져오기
		
		request.setAttribute("myPosts", myPosts);
		request.setAttribute("postCnt", myPostCnt);
		
		request.getRequestDispatcher("/member/mypage").forward(request, response);
	}
	
	//비동기로 통신할 요청
	private void myReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		Map<String,List> reply = memberService.selectMyReply(member);
		
		if(member == null) {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("url", "/member/login-page");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		
		List<Board> myPosts = memberService.selectMyPostById(member.getUserId());	
		int myPostCnt = memberService.countMyPost(member.getUserId());
		
		//댓글수 가져오기
		
		request.setAttribute("myPosts", myPosts);
		request.setAttribute("postCnt", myPostCnt);	
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