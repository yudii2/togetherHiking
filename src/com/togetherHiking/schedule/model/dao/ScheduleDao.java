package com.togetherHiking.schedule.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.togetherHiking.board.model.dto.Board;
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
		List<Schedule> schedules = new ArrayList<Schedule>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select mountain_name,sc_idx,user_id,d_day,reg_date,exp_date,info,money,openchat,meeting_date"
				+ " from schedule where sc_idx=? and is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, scIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				/* Schedule schedule = convertRowToSchedule(rset); */
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return schedules;
	}




	public void insertSchedule(Schedule schedule, Connection conn) {
		String sql = "insert into schedule (sc_idx,user_id,mountain_name,d_day,info,openchat,meeting_date) "
				+ "values(sc_sc_idx.nextval,?,?,?,?,?,?)";
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, schedule.getUserId());
			pstm.setString(2, schedule.getMountainName());
			pstm.setDate(3, schedule.getdDay());
			pstm.setString(4, schedule.getInfo());
			pstm.setString(5, schedule.getOpenChat());
			pstm.setDate(6, schedule.getMeetingDate());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	
	
	/*private Schedule convertRowToSchedule(ResultSet rset) throws SQLException {
		Schedule schedule = new Schedule();
		schedule.setMountainName(rset.getString("mountain_name"));
		schedule.setScIdx(rset.getString("sc_idx"));
		schedule.setUserId(rset.getString("user_id"));
		schedule.setRegDate(rset.getDate("reg_date"));
		schedule.setExpDate(rset.getDate("exp_date"));
		schedule.setInfo(rset.getString("info"));
		schedule.setMoney(rset.getInt("money"));
		schedule.setOpenChat(rset.getString("openchat"));
		schedule.setMeetingDate(rset.getDate("meeting_date"));
		return schedule;
	} */
	
	

}