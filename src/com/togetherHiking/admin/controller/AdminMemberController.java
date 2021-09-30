package com.togetherHiking.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.togetherHiking.schedule.model.dto.Schedule;
import com.togetherHiking.schedule.model.service.ScheduleService;

/**
 * Servlet implementation class AdminMemberController
 */
@WebServlet("/admin/*")
public class AdminMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScheduleService scheduleService = new ScheduleService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		System.out.println(uriArr[uriArr.length -1]);
		switch (uriArr[uriArr.length -1]) {
		case "home":
			home(request,response);
			break;
			// 새로 등록된 스케줄 리스트 조회
		case "new-schedule":
			newSchedule(request,response);
			break;
			// 새로 등록된 스케줄을 승인
		case "approve-schedule":
			approveSchedule(request,response);
			break;
		default:
			break;
		}
	}

	private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/admin-page").forward(request, response);
		
	}
	// 새로 등록된 스케줄 조회
	private void newSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Schedule> scheduleList = new ArrayList<Schedule>();
		//getNonApproveScheduleDTOs 미승인된 스케줄
		scheduleList = scheduleService.getNonApproveScheduleDTOs();
		System.out.println(scheduleList);
		request.setAttribute("scheduleList", scheduleList);
		request.getRequestDispatcher("/admin/new-schedule-page").forward(request, response);
	}
	
	// 새로 등록된 스케줄 승인처리
	private void approveSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String scIdx = request.getParameter("scIdx");
		scheduleService.approveSchedule(scIdx);
		response.sendRedirect("/admin/new-schedule");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
