package com.togetherHiking.schedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.schedule.model.dto.Schedule;
import com.togetherHiking.schedule.model.service.ScheduleService;

/**
 * Servlet implementation class ScheduleController
 */
@WebServlet("/schedule/*")
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ScheduleService scheduleService = new ScheduleService();
       
	private HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length-1]) {
		case "calendar":
			calendar(request,response);
			break;
		case "schedule-detail":
			scheduleDetail(request,response);
			break;
		case "schedule-form":
			scheduleForm(request,response);
			break;
		case "schedule-modify":
			scheduleModify(request,response);
			break;
		case "upload":
			try {
				upload(request,response);
			} catch (ServletException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		//jsp파일 상에서 host에게만 브라우저에 표시함 --> 1차적으로 거르므로 validation필요없음
		//host한테만 허용되는 수정,삭제 기능
		case "edit":
			edit(request,response);
			break;
		case "delete":
			delete(request,response);
			break;
		default:/* throw new PageNotFoundException(); */
		
		}
	}

	private void scheduleModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값을 post로 담아 마지막에 schedule-form.jsp로 요청 재요청
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scIdx = request.getParameter("sc_idx");
		scheduleService.delete(scIdx);
		
		response.sendRedirect("/schedule/calendar");
	
		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private void scheduleForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/schedule-form").forward(request, response);
		
	}

	private void scheduleDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scIdx = request.getParameter("sc_idx");
		Map<String,Object> datas = scheduleService.getScheduleDetail(scIdx);
		
		request.setAttribute("datas", datas);
		request.getRequestDispatcher("/schedule/calendar").forward(request, response);
		
	}

	private void calendar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/calendar").forward(request, response);
		
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

		Member member = (Member) request.getSession().getAttribute("authentication"); //member 객체
		String userId = member.getUserId();
		
		Date dDay = Date.valueOf(request.getParameter("dDay"));
		System.out.println("dday"+dDay);
		String mountainName = request.getParameter("mountainName");
		int allowedNum = Integer.parseInt(request.getParameter("allowedNum"));
		String info = request.getParameter("info");
		String openChat = request.getParameter("openChat");
		int age = Integer.parseInt(request.getParameter("age"));
		Schedule schedule = new Schedule();

		schedule.setUserId(userId);
		schedule.setdDay(dDay);
		schedule.setMountainName(mountainName);
		schedule.setAllowedNum(allowedNum);
		schedule.setInfo(info);
		schedule.setOpenChat(openChat);
		schedule.setAge(age);
		
		scheduleService.insertSchedule(schedule);
		
		//post메서드로 upload url요청을 통해 받아온 사용자 작성게시글 정보 받아와
		
		
		//최종적으로 schedule 페이지로 redirect
		response.sendRedirect("/schedule/calendar");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public String scrinDetailView(HttpServletRequest request) {
		// 비로그인자가 접근할경우 방지
		if (session.getAttribute("member") == null) return "/member/login-page";
		
		
		// 해당 글작성자 이외의 다른 멤버가 수정권한에 접근할 경우를 방지
		
		return "schedule/calendar";
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
