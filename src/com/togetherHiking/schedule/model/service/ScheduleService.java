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
	
	// 승인된 schedule 리스트를 조회한다.
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
	// 미승인된 schedule 리스트를 조회한다.
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

	public Map<String, Object> getScheduleDetail(String scIdx) {
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
	}
	
	
/*	// 특정 게시글을 보여준다
	public List<HashMap<String, Object>> selectSample(HashMap<String, Object> selectMap, String contextPath){
		HashMap<String, Object> sampleMap = sampleDao.selectSample(selectMap);
		sampleSettings(sampleMap, contextPath);
		List<HashMap<String, Object>> sampleList = new ArrayList<>();
		sampleList.add(sampleMap);
		return sampleList;
	}
	
	// 게시글의 정보를 풀캘린더에서 바로 읽어들일 수 있도록 세팅한다
	private void sampleSettings (HashMap<String, Object> map, String contextPath) {
		// 풀캘린더에서 데이터를 읽어들일수 있게 값 세팅
		map.put("id", map.get("SEQ"));
		map.put("memberNo", map.get("MEMBER_NO"));
		map.put("url", contextPath+ "/sample/sampleUpdateView?seq=" + map.get("SEQ"));
		map.put("textColor", "white");
		map.put("title", map.get("TITLE"));
		map.put("content", map.get("CONTENT"));
		map.put("start", map.get("START_DATE"));
		map.put("end", map.get("END_DATE"));
		map.put("name", map.get("NAME"));
		map.put("type", map.get("TYPE"));
		
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
}
