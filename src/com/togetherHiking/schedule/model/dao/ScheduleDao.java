package com.togetherHiking.schedule.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.schedule.model.dto.Participant;
import com.togetherHiking.schedule.model.dto.Schedule;

public class ScheduleDao {

	private JDBCTemplate template = JDBCTemplate.getInstance();

	public Schedule scheduleAuthenticate(String userId, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public interface Calendar {
		public ArrayList<Calendar> calendar();
	}
	
	public List<Schedule> selectSchedules(Connection conn) {
		List<Schedule> schedules = new ArrayList<Schedule> ();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select sc_idx,user_id,d_day,mountain_name,allowed_num,info,openchat,age,reg_date,exp_date"
				+ " from schedule where is_del = 0 and status = 1";
		
		
		try {
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Schedule schedule = convertRowToSchedule(rset);
				schedules.add(schedule);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return schedules;
	}

	public Schedule selectSchedule(Connection conn, String scIdx) {
		Schedule schedule = new Schedule();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select sc_idx, user_id, d_day, mountain_name, allowed_num, info, openchat, age, reg_date, exp_date"
				+ " from schedule"
				+ " where sc_idx = ? and is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, scIdx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				schedule = convertRowToSchedule(rset);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return schedule;
	}

	//스케줄 등록
	public void insertSchedule(Schedule schedule, Connection conn) {
		String sql = "insert into schedule (sc_idx,user_id,d_day,mountain_name,allowed_num,info,openchat,age) "
				+ "values(sc_sc_idx.nextval,?,?,?,?,?,?,?)";
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, schedule.getUserId());
			pstm.setDate(2, schedule.getdDay());
			pstm.setString(3, schedule.getMountainName());
			pstm.setInt(4, schedule.getAllowedNum());
			pstm.setString(5, schedule.getInfo());
			pstm.setString(6, schedule.getOpenChat());
			pstm.setInt(7, schedule.getAge());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	private Schedule convertRowToSchedule(ResultSet rset) throws SQLException {
		Schedule schedule = new Schedule();
		schedule.setScIdx(rset.getString("sc_idx"));
		schedule.setUserId(rset.getString("user_id"));
		schedule.setdDay(rset.getDate("d_day"));
		schedule.setMountainName(rset.getString("mountain_name"));
		schedule.setRegDate(rset.getDate("reg_date"));
		schedule.setExpDate(rset.getDate("exp_date"));
		schedule.setAllowedNum(rset.getInt("allowed_num"));		
		schedule.setInfo(rset.getString("info"));
		schedule.setOpenChat(rset.getString("openchat"));
		schedule.setAge(rset.getInt("age"));
		return schedule;
	}

	//스케줄 수정
	public void updateSchedule(Schedule schedule, Connection conn) {
		String sql = "update schedule set mountain_name = ?, allowed_num = ?,  info = ?, openChat = ?, age = ?, d_day = ?  where sc_idx = ? ";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, schedule.getMountainName());
			pstm.setInt(2, schedule.getAllowedNum());
			pstm.setString(3, schedule.getInfo());
			pstm.setString(4, schedule.getOpenChat());
			pstm.setInt(5, schedule.getAge());
			pstm.setDate(6, schedule.getdDay());
			pstm.setString(7, schedule.getScIdx());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	//스케줄 삭제
	public void deleteSchedule(String scIdx, Connection conn) {
		String sql = "update schedule set is_del = '1' where sc_idx = ? ";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, scIdx);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	//참가자 리스트 가져오기(참가자목록 번호, 일정번호, 유저아이디, 닉네임, 자기소개, (나이는 보류))
	public List<Participant> selectParticipantList(Connection conn, String scIdx) {
		
		List<Participant> list = new ArrayList<Participant>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		//쿼리문 작성 조인하는 법 도움 필요!
		String sql = "select pl_idx, sc_idx, user_id, nickname as userNickName, info as userInfo"
				+ " from participant_list a, member b where a.user_id = b.user_id and a.as_idx = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, scIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Participant participant = new Participant();
				participant.setPlIdx(rset.getString("pl_idx"));
				participant.setScIdx(rset.getString("sc_idx"));
				participant.setUserId(rset.getString("user_id"));
				participant.setUserNickName(rset.getString("userNickName"));
				participant.setUserInfo(rset.getString("userInfo"));
				list.add(participant);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return list;
	}
	
	//참가자 리스트 넣기
	public void insertParticipant(String scIdx, String userId, Connection conn) {
		String sql = "insert into participant_list (pl_idx, sc_idx, user_id) "
				+ "values(SC_PL_IDX.nextval,?,?)";
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, scIdx);
			pstm.setString(2, userId);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	//ADMIN
	// 승인되지 않은 스케줄list
	public List<Schedule> selectNonApproveSchedules(Connection conn) {
		List<Schedule> schedules = new ArrayList<Schedule> ();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select sc_idx,user_id,d_day,mountain_name,allowed_num,info,openchat,age,reg_date,exp_date"
				+ " from schedule where is_del = 0 and status = 0";
		
		
		try {
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Schedule schedule = convertRowToSchedule(rset);
				schedules.add(schedule);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return schedules;
	}
	
	// admin 승인시 status = 1
	public void approveSchedule(Connection conn, String scIdx) {

	}
	

}