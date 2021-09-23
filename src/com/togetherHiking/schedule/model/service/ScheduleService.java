package com.togetherHiking.schedule.model.service;

import java.sql.Connection;
import java.util.List;

import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.schedule.model.dao.ScheduleDao;
import com.togetherHiking.schedule.model.dto.Schedule;


public class ScheduleService {

	private ScheduleDao scheduleDao = new ScheduleDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public Schedule scheduleAuthenticate(String userId) {
		Connection conn = template.getConnection();
		Schedule schedule = null;
		
		try {
			schedule = scheduleDao.scheduleAuthenticate(userId, conn);
		}finally {
			template.close(conn);
		}
		return schedule;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
