package com.togetherHiking.schedule.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.common.code.ErrorCode;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.exception.HandleableException;
import com.togetherHiking.schedule.model.dao.ScheduleDao;
import com.togetherHiking.schedule.model.dto.Schedule;


public class ScheduleService {

	private ScheduleDao scheduleDao = new ScheduleDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public ScheduleService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Schedule> getScheduleDTOs(String scIdx) {
		List<Schedule> schedules = null;
		Connection conn = template.getConnection();
		
		try {
			schedules = scheduleDao.selectSchedules(conn, scIdx);
			
		} finally {
			template.close(conn);
		}
		
		return schedules;
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

	/*public Map<String, Object> getScheduleDetail(String scIdx) {
		Connection conn = template.getConnection();
		Map<String,Object> datas = new HashMap<String, Object>();
		Schedule schedule = null;
		
		try {
			schedule = scheduleDao. selectSchedule(conn, scIdx);
			
			datas.put("schedule", schedule);
			
			template.commit(conn);
		}catch(DataAccessException e){
			template.rollback(conn);
			throw new HandleableException(ErrorCode.DATABSE_ACCESS_ERROR);
		} finally {
			template.close(conn);
		}
		
		return datas;
	}*/
	
	
/*	// 특정 게시글을 보여준다
	public List<HashMap<String, Object>> selectSchedule(HashMap<String, Object> selectMap, String contextPath){
		HashMap<String, Object> scheduleMap = scheduleDao.selectSchedule(selectMap);
		scheduleSettings(scheduleMap, contextPath);
		List<HashMap<String, Object>> scheduleList = new ArrayList<>();
		scheduleList.add(scheduleMap);
		return scheduleList;
	} 
	
	// 스케줄디테일 정보를 풀캘린더에서 바로 읽어들일 수 있도록 세팅한다
	private void scheduleSettings (HashMap<String, Object> map, String contextPath) {
		// 풀캘린더에서 데이터를 읽어들일수 있게 값 세팅
		map.put("userId", map.get("USER_ID"));
		map.put("info", map.get("INFO"));
		map.put("dDay", map.get("D_DAY"));
		map.put("mountainName", map.get("MOUNTAIN_NAME"));
		map.put("allowedNum", map.get("ALLOWED_NUM"));
		map.put("openChat", map.get("OPENCHAT"));
		map.put("age", map.get("AGE"));
	}


	public void delete(String scIdx) {

		// TODO Auto-generated method stub

		Connection conn = template.getConnection();
		
		try {
			scheduleDao.delete(conn, scIdx);
			
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw new HandleableException(ErrorCode.UNMATCHED_USER_AUTH_ERROR);
		} finally {
			template.close(conn);
		}

		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
}
