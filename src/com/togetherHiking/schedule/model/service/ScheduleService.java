package com.togetherHiking.schedule.model.service;

import java.sql.Connection;
import java.util.List;

import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.schedule.model.dao.ScheduleDao;
import com.togetherHiking.schedule.model.dto.Schedule;


public class ScheduleService {

	private ScheduleDao scheduleDao = new ScheduleDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
