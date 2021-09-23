package com.togetherHiking.schedule.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.togetherHiking.board.model.dto.BoardView;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.schedule.model.dto.Schedule;

public class ScheduleDao {

	private JDBCTemplate template = JDBCTemplate.getInstance();

	public Schedule scheduleAuthenticate(String userId, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Schedule> selectSchedules(Connection conn, String scIdx) {
		List<Schedule> files = new ArrayList<Schedule>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select sc_idx,user_id,d_day,reg_date,exp_date,info,money,openchat"
				+ " from schedule where sc_idx=? and is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, scIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Schedule schedule = convertRowToSchedule(rset);
				files.add(schedule);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return files;
	}

	private Schedule convertRowToSchedule(ResultSet rset) throws SQLException {
		Schedule schedule = new Schedule();
		schedule.setScIdx(rset.getString("sc_idx"));
		schedule.setUserId(rset.getString("user_id"));
		schedule.setRegDate(rset.getDate("reg_date"));
		schedule.setExpDate(rset.getDate("exp_date"));
		schedule.setInfo(rset.getString("info"));
		schedule.setMoney(rset.getInt("money"));
		schedule.setOpenChat(rset.getString("openchat"));
		return schedule;
	}
	
	

}