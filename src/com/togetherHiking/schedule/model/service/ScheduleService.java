package com.togetherHiking.schedule.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.togetherHiking.common.code.ErrorCode;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.exception.HandleableException;
import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.member.model.service.MemberService;
import com.togetherHiking.schedule.model.dao.ScheduleDao;
import com.togetherHiking.schedule.model.dto.Schedule;


public class ScheduleService {

	private ScheduleDao scheduleDao = new ScheduleDao();
	private MemberService memberService = new MemberService();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public ScheduleService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void insertSchedule(Schedule schedule) {
		Connection conn = template.getConnection();
		
		try {
			scheduleDao.insertSchedule(schedule, conn);
			
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}

	public Map<String, Object> getScheduleDetail(String scIdx) {
		Connection conn = template.getConnection();
		Map<String,Object> datas = new HashMap<String, Object>();
		List<Member> participants = null;
		Schedule schedule = null;
		
		try {
			schedule = scheduleDao. selectSchedule(conn, scIdx);  
			participants = scheduleDao. selectParticipantList(conn, scIdx);
			
			for (Member participant : participants) {
				participant.setProfile(memberService.selectProfile(participant.getUserId()));
			}
			datas.put("schedule", schedule);
			datas.put("participants", participants);
			
			template.commit(conn);
		}catch(DataAccessException e){
			template.rollback(conn);
			throw new HandleableException(ErrorCode.DATABASE_ACCESS_ERROR);
		} finally {
			template.close(conn);
		}
		
		return datas;
	}

	public void updateSchedule(Schedule schedule) {
		Connection conn = template.getConnection();
		
		try {
			scheduleDao.updateSchedule(schedule, conn);
			
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw new HandleableException(ErrorCode.UNMATCHED_USER_AUTH_ERROR);
		} finally {
			template.close(conn);
		}
		
	}

	// ???????????? ????????????.
	public void deleteSchedule(String scIdx) {
		Connection conn = template.getConnection();
		
		try {
			scheduleDao.deleteSchedule(scIdx, conn);
			
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}

	
	//ADMIN
	// ???????????? ????????? ??????
	public List<Schedule> getNonApproveScheduleDTOs() {
		List<Schedule> schedules = null;
		Connection conn = template.getConnection();
		
		try {
			schedules = scheduleDao.selectNonApproveSchedules(conn);
			
		} finally {
			template.close(conn);
		}
		
		return schedules;
	}
	
	//????????? ??????
	public void approveSchedule(String scIdx) {
		Connection conn = template.getConnection();
		
		try {
			scheduleDao.approveSchedule(conn, scIdx);
			
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw new HandleableException(ErrorCode.UNMATCHED_USER_AUTH_ERROR);
		} finally {
			template.close(conn);
		}
		
	}
	
	// ????????? ????????? ??????
	public List<Schedule> getScheduleDTOs() {
		List<Schedule> schedules = null;
		Connection conn = template.getConnection();
		
		try {
			schedules = scheduleDao.selectSchedules(conn);
			
		} finally {
			template.close(conn);
		}
		
		return schedules;
	}


	//????????? ??????
	public void insertParticipant(String scIdx, Member member) {
		Connection conn = template.getConnection();
		
		try {			
			scheduleDao.insertParticipant(scIdx,member, conn);
			
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw new HandleableException(ErrorCode.DATABASE_ACCESS_ERROR);
		} finally {
			template.close(conn);
		}

		
		
	}

	//dao??? ?????? ?????? ??????
	public void cancelParticipant(String scIdx, Member member) {
		Connection conn = template.getConnection();
		
		try {
			scheduleDao.cancelParticipant(scIdx,member, conn);
			
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw new HandleableException(ErrorCode.DATABASE_ACCESS_ERROR);
		} finally {
			template.close(conn);
		}
	}


	public boolean duplicationCheck(String scIdx, Member member) {
		
		Connection conn = template.getConnection();
		
		try {
			return scheduleDao.duplicationCheck(scIdx, member, conn);//?????????????????????
			
		} catch (DataAccessException e) {
			throw new HandleableException(ErrorCode.DATABASE_ACCESS_ERROR);
		} finally {
			template.close(conn);
		}
		
	}


	public void rejectSchedule(String scIdx) {
		Connection conn = template.getConnection();
		
		try {
			scheduleDao.rejectSchedule(conn, scIdx);
			
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
	
		
	}
	
	// ????????? ?????? ?????????
	public void updateState() {
		Connection conn = template.getConnection();
		
		try {
			scheduleDao.updateState(conn);
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
		} finally {
			template.close(conn);
		}
	}
}