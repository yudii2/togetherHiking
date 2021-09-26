package com.togetherHiking.schedule.model.service;

import java.sql.Connection;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
