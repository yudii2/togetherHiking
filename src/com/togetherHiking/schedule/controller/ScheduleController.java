package com.togetherHiking.schedule.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.member.model.service.MemberService;
import com.togetherHiking.schedule.model.dto.Schedule;
import com.togetherHiking.schedule.model.service.ScheduleService;

/**
 * Servlet implementation class ScheduleController
 */
@WebServlet("/schedule/*")
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ScheduleService scheduleService = new ScheduleService();
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScheduleController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length - 1]) {
		case "calendar":
			calendar(request, response);
			break;
		case "calendar-list":
			calendarList(request, response);
			break;
		case "schedule-detail":
			scheduleDetail(request, response);
			break;
		case "schedule-form":
			scheduleForm(request, response);
			// 수정하기 위한 폼을 띄우기 위해 스케줄 상세내용을 조회
		case "schedule-modify-form":
			scheduleModifyForm(request, response);
			// 스케줄 수정작업을 진행
		case "schedule-modify":
			scheduleModify(request, response);
			break;
		case "upload":
			try {
				upload(request, response);
			} catch (ServletException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		// host한테만 허용되는 수정,삭제 기능
		case "edit":
			edit(request, response);
			break;
		// 스케줄 삭제 작업을 진행한다.
		case "delete":
			delete(request, response);
			break;
		// 동행버튼 이벤트
		case "participant":
			participant(request, response);
			break;
		// 동행버튼 이벤트
		case "cancel":
			cancel(request, response);
			break;
		default:/* throw new PageNotFoundException(); */

		}
	}

	private void calendar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginMember = (Member) request.getSession().getAttribute("authentication");
		scheduleService.updateState();
		Member member = memberService.getMemberDetail(loginMember);
		request.getSession().setAttribute("authentication",member);
		request.getRequestDispatcher("/schedule/calendar").forward(request, response);

	}

	// calendar 리스트를 조회
	private void calendarList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		// 승인된 calendar 리스트를 조회
		scheduleList = scheduleService.getScheduleDTOs();

		// 조회한 리스트를 json 타입으로 리턴
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Gson : Java Object를 Json 문자열로 변환할 수 있고, Json 문자열을 Java Object로 변환할 수 있음
		String gson = new Gson().newBuilder().setDateFormat("yyyy-MM-dd").create().toJson(scheduleList);

		PrintWriter out = response.getWriter();
		out.write(gson);

	}

	// 스케줄 디테일 조회
	private void scheduleDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String scIdx = request.getParameter("scIdx");
		// Map에 schedule : 스케줄 상세정보를 put
		Map<String, Object> datas = scheduleService.getScheduleDetail(scIdx);

		// 팝업에 참가자 조회
		// scIdx를 키로 참가동행자 리스트를 조회

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Map에 userIdx 사용자인증정보 put
		datas.put("userIdx", request.getSession().getAttribute("authentication"));

		// Map 데이터를 json 타입으로 리턴
		String gson = new Gson().newBuilder().setDateFormat("yyyy-MM-dd").create().toJson(datas);
		PrintWriter out = response.getWriter();
		out.write(gson);
	}

	private void scheduleForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/schedule-form").forward(request, response);

	}

	// 수정폼에 상세내용 불러오기
	private void scheduleModifyForm(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String scIdx = request.getParameter("scIdx");
		Map<String, Object> schedule = scheduleService.getScheduleDetail(scIdx);
		request.setAttribute("schedule", schedule.get("schedule"));
		// scIdx를 키로 스케줄 상세내용을 조회하여 modify-form으로 이동
		request.getRequestDispatcher("/schedule/schedule-modify-form").forward(request, response);
	}

	// 스케줄 수정
	// jsp파일 상에서 host에게만 브라우저에 표시해서 거르므로 validation필요없음
	private void scheduleModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 입력된 값들을 가지고, scIdx를 키로 업데이트 작업을 진행
		String scIdx = request.getParameter("scIdx");
		Date dDay = Date.valueOf(request.getParameter("dDay"));
		String mountainName = request.getParameter("mountainName");
		int allowedNum = Integer.parseInt(request.getParameter("allowedNum"));
		String info = request.getParameter("info");
		String openChat = request.getParameter("openChat");
		int age = Integer.parseInt(request.getParameter("age"));
		Schedule schedule = new Schedule();

		schedule.setScIdx(scIdx);
		schedule.setdDay(dDay);
		schedule.setMountainName(mountainName);
		schedule.setAllowedNum(allowedNum);
		schedule.setInfo(info);
		schedule.setOpenChat(openChat);
		schedule.setAge(age);

		scheduleService.updateSchedule(schedule);
		request.getRequestDispatcher("/schedule/calendar").forward(request, response);
	}

	// 스케줄 등록
	private void upload(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException, ParseException {
		// authentication -> member 객체
		Member loginMember = (Member) request.getSession().getAttribute("authentication");
		String userId = loginMember.getUserId();

		Date dDay = Date.valueOf(request.getParameter("dDay"));
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

		Member member = memberService.selectMemberById(userId);
		request.getSession().setAttribute("authentication", member);
		
		response.sendRedirect("/member/my-schedule");

		//request.getRequestDispatcher("/schedule/calendar").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	// 모임글 삭제
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String scIdx = request.getParameter("scIdx");
		scheduleService.deleteSchedule(scIdx);
		response.sendRedirect("/schedule/calendar");
	}

	// 동행버튼 이벤트
	private void participant(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		
		Member member = (Member) request.getSession().getAttribute("authentication");

		if (member == null) {
			out.write("{\"code\":\"login\"}");
			out.flush();
			return;
		}
		
		// getScheduleDetail메서드를 호출 -> Map객체 불러오기
		String scIdx = request.getParameter("scIdx");
		Map<String, Object> datas = scheduleService.getScheduleDetail(scIdx);

		// Map객체를 .get("schedule")로 받아서 allowedNum 을 getter로 부르기
		int allowedNum = ((Schedule) datas.get("schedule")).getAllowedNum();
		List<Member> participants = (List<Member>) datas.get("participants");
		
		
		
		if(participants.size() < allowedNum) {//인원수참여가능
			if(scheduleService.duplicationCheck(scIdx, member)) {//참여중복확인(참여가능)
				scheduleService.insertParticipant(scIdx, member);
				out.write("{\"code\":\"ok\"}");
			}else {
				out.write("{\"code\":\"joined\"}");
			}			
		}else {//참여불가능(모집완료)
			out.write("{\"code\":\"full\"}");
		}
		out.flush();


	}

	// 동행취소
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		if (member == null) {
			response.sendRedirect("/schedule/calendar");
			return;
		} else {
			String scIdx = request.getParameter("scIdx");
			scheduleService.cancelParticipant(scIdx, member);
		}
		response.sendRedirect("/schedule/calendar");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
