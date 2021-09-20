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
		case "id-check":
			  checkID(request,response);
			break;
		case "mypage":
			  mypage(request,response);
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

	private void profileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FileUtil util = new FileUtil();
		Map<String,FileDTO> param = util.profileUpload(request);
		System.out.println(param.toString());
		
		//로그인한 사용자만 profile등록 가능
		//Member member = (Member) request.getSession().getAttribute("authentication");
		//String userId = member.getUserId();	//세션에 인증절차 통과한 사용자 정보 등록
		
		String userId = "USER2";
		
		//file정보 가져오기
		FileDTO fileDTO = param.get("com.togetherHiking.common.file");
		System.out.println("여긴 컨트롤러 : " + fileDTO.toString());
		
		//파일을 테이블에 저장
		memberService.insertProfile(userId, fileDTO);
		//프로필정보 조회해 request객체에 전달
		request.setAttribute("profile", memberService.selectProfile(userId));
		request.getRequestDispatcher("/member/mypage").forward(request, response);
			
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private void mySchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/my-schedule").forward(request, response);
		
	}

	private void modifyPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/modify-page").forward(request, response);
		
	}

	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/mypage").forward(request, response);
	}

	private void checkID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/check-id").forward(request, response);
		
	}

	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubs
		
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private void joinPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/join-page").forward(request, response);
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("authentication");
		response.sendRedirect("/");
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId =  request.getParameter("useId");
		String password = request.getParameter("password");
		
		request.getSession().setAttribute("authentication", "test");
		response.sendRedirect("/");
		
		
	}

	private void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/login-page").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}