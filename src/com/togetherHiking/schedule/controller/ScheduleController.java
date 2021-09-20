package com.togetherHiking.schedule.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScheduleController
 */
@WebServlet("/schedule/*")
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			upload(request,response);
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
		// TODO Auto-generated method stub
		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private void scheduleForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/schedule-form").forward(request, response);
		
	}

	private void scheduleDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/schedule-detail").forward(request, response);
		
	}

	private void calendar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/calendar").forward(request, response);
		
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post메서드로 upload url요청을 통해 받아온 사용자 작성게시글 정보 받아와
		
		//최종적으로 schedule 페이지로 redirect
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
