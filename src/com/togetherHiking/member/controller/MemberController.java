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
import com.togetherHiking.reply.model.dto.Reply;
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
		case "id-check": //??????????????? ????????? ????????????
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
			  deletePost(request,response);	//?????? 10/01
			break;
		case "delete-reply":
			  deleteReply(request,response);	//?????? 10/01
			break;
		case "reset-pwd":
			resetPwd(request,response);	//?????? 10/03
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
			request.setAttribute("msg", "????????? ???????????? ????????????.");
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
			
		//???????????? ????????? ??????
		Member member = memberService.selectMemberById(userId);
		if(member == null || member.getUserId().equals("")) {
			//????????????????????? ???????????? ???????????? ???????????? ????????? ?????? ??????
			request.setAttribute("kakaoId", userId);
			response.getWriter().print("kakaoJoin");
			return;
		}
		request.getSession().setAttribute("authentication", member);
		response.getWriter().print("kakaoLogin");
			
	}
	
	//?????? 10/3
	private void resetPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Member member = (Member) request.getAttribute("member");
		request.setAttribute("member", member);
		request.getRequestDispatcher("/member/reset-pwd").forward(request, response);
		
	}

	private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		

		String userId = request.getParameter("userId");
		String password = request.getParameter("reset1");
		Member member = memberService.selectMemberById(userId);		
		int res = memberService.updateMember(member, password);
		if(res == 0) {
			request.setAttribute("msg", "???????????? ????????? ???????????? ???????????????.");
			request.setAttribute("back", "1");
			request.getRequestDispatcher("/common/result").forward(request, response);		
		};
		response.sendRedirect("/index");
		
	}

	private void deleteReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String[] replyIdx = request.getParameterValues("chk");

		for (int i = 0; i < replyIdx.length; i++) {
			memberService.deleteReply(replyIdx[i]);
		}
		
		
		request.setAttribute("msg", "????????? ?????????????????????.");
		request.setAttribute("url", "/member/mypage");
		request.getRequestDispatcher("/common/result").forward(request, response);
		
		
	}

	//?????? 10/01
	private void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String[] bdIdx = request.getParameterValues("chk");

		for (int i = 0; i < bdIdx.length; i++) {
			memberService.deletePost(bdIdx[i]);
		}
		
		
		request.setAttribute("msg", "???????????? ?????????????????????.");
		request.setAttribute("url", "/member/mypage");
		request.getRequestDispatcher("/common/result").forward(request, response);
		
	}

	private void checkId(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
			String userid = request.getParameter("userid");
			Member member = memberService.selectMemberById(userid);
			//??????????????? ????????? ????????? ??????
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

	//?????? 10/1 
	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		memberService.insertMember((Member) session.getAttribute("persistUser"));

		// ?????? persistUser?????? ??? ??? DB??? ???????????? ????????? ????????? ????????? ????????? ????????????
		session.removeAttribute("persistUser");
		session.removeAttribute("persist-token");
		
		request.setAttribute("msg", "??????????????? ?????????????????????.");
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
		
		

		request.setAttribute("msg", "??????????????? ?????? ???????????? ?????????????????????.");
		request.setAttribute("url", "/member/login-page");
		request.getRequestDispatcher("/common/result").forward(request, response);
		
			
			
		}
		
		
		

	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("authentication");
		request.getSession().removeAttribute("profile");
		
		response.sendRedirect("/");
		
	}
	//?????? 09/29
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId =  request.getParameter("userId");
		String password = request.getParameter("password");
		
		//?????? ????????? ?????????
		Member member = memberService.memberAuthenticate(userId,password);
		
		if(member.getUserId() == null) {
			response.sendRedirect("/member/login-page?err=1");
			return;
		}
		request.getSession().setAttribute("authentication", member);

		response.sendRedirect("/index");
		
	}

	private void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/login-page").forward(request, response);
		
	}
	



	//?????? 09/30
	private void profileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUtil util = new FileUtil();
		Map<String,FileDTO> param = util.profileUpload(request);
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();	

		//file?????? ????????????
		FileDTO fileDTO = param.get("com.togetherHiking.files");
		
		//??????????????? ????????? insert , ?????? ???????????? update
		if(memberService.selectProfile(userId).getRenameFileName() == null) {
			memberService.insertProfile(userId, fileDTO);
		}else {
			int updateProfile = memberService.updateProfile(userId, fileDTO);
			
			if(updateProfile == 0) {
				request.setAttribute("msg", "????????? ????????? ?????????????????????.");
			}
			request.setAttribute("msg", "????????? ????????? ?????????????????????.");
		}
		member = memberService.getMemberDetail(member);
		request.getSession().setAttribute("authentication", member);	//????????? ???????????? ?????????(????????? ??????)
		
		request.setAttribute("url", "/member/mypage");
		request.getRequestDispatcher("/common/result").forward(request, response);
			
	}
	
	private void checkNickname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nickname = request.getParameter("nickname");
		Member member = memberService.selectByNickname(nickname);
		if(member == null) {
			response.getWriter().print("available");	//js?????? ??????
		}else {
			response.getWriter().print("disable");
		}
	}
	//?????? 10/01
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
		
		//?????????????????? ?????????????????? ??????
		if(memberService.updateMember(member,"") != 0) {
			request.setAttribute("msg", "???????????? ????????? ?????????????????????.");
			request.setAttribute("url", "/member/modify-page");
		}else {
			request.setAttribute("msg", "???????????? ????????? ?????????????????????.");
			request.setAttribute("back", "1");
		}
		request.getRequestDispatcher("/common/result").forward(request, response);
		
	}
	//?????? 10/4
	private void mySchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		String selector = request.getParameter("schedule_filter");
		String userId = member.getUserId();
		
		List<Board> myPosts = memberService.selectMyPostById(userId);	
		List<Reply> reply = memberService.selectMyReply(userId);
		
		if(selector != null) {
			if(selector.equals("ing")) {
				request.setAttribute("ingSchedule", myPosts);
			}else if(selector.equals("end")) {
				request.setAttribute("endSchedule", myPosts);
			}
		};
		
		
		List<Schedule> scheduleList = memberService.selectMySchedule(userId);
		
		request.setAttribute("mySchedule", scheduleList);

		request.setAttribute("myPosts", myPosts);
		request.setAttribute("myReply",reply);		
		
		request.getRequestDispatcher("/member/my-schedule").forward(request, response);
		
		
		
	}

	//?????? 10/01
	private void modifyPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member session = (Member) request.getSession().getAttribute("authentication");
		
		//????????? ?????? ?????? ????????? ??????
		Member member = memberService.getMemberDetail(session);
		request.getSession().setAttribute("authentication", member);
		request.getRequestDispatcher("/member/modify-page").forward(request, response);
		
	}

	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();

		List<Board> myPosts = memberService.selectMyPostById(userId);	//?????? ????????? ??????
		List<Reply> reply = memberService.selectMyReply(userId);

		request.setAttribute("myPosts", myPosts);
		request.setAttribute("myReply",reply);
		
		
		//Pagination
		String paramPage = request.getParameter("p");
		int page = 1;	//parameter??? null??? ????????? ????????? ????????? 1??? ??????
		
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
		List<Reply> reply = memberService.selectMyReply(userId);	//boardList????????? reply????????? ?????? ??????. but, jsp????????? ????????? ????????? ???????????? ????????? ??? ??????.

		request.setAttribute("myPosts", myPosts);
		request.setAttribute("myReply",reply);
		
		//Pagination
		String paramPage = request.getParameter("p");
		int page = 1;	//parameter??? null??? ????????? ????????? ????????? 1??? ??????
		
		if(paramPage != null && !paramPage.equals("")) {
			page = Integer.parseInt(paramPage);	
		}

		List<Reply> replyByPage = memberService.selectReplyByPage(userId, page);
		request.setAttribute("replyByPage", replyByPage);

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