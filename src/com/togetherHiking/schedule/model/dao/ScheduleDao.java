package com.togetherHiking.schedule.model.dao;

import java.sql.CallableStatement;
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
import com.togetherHiking.member.model.dto.Member;
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
	
	
	//스케줄 등록
	public void insertSchedule(Schedule schedule, Connection conn) {
		String sql = "insert into schedule (sc_idx,user_id,d_day,mountain_name,allowed_num,info,openchat,age) "
				+ "values(sc_sc_idx.nextval,?,?,?,?,?,?,?)";
		PreparedStatement pstm = null;
		//ResultSet rset = null;
		//String field = null;
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
			//template.close(pstm);
			
			//sql = "select sc_sc_idx.currval from dual";
			//pstm = conn.prepareStatement(sql);
//			rset = pstm.executeQuery();
//			if(rset.next()) {
//				field = rset.getString(1);
//			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}
	//필요한 이유 고민
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

	
	//쿼리문 피드백 필요..
	//member에 있는 유저의 닉네임과 아이디를 조인해서 가져오려고 하는데 이렇게 하는게 맞는 방식인지..
	
	//ScheduleDetail가져오기 (일정 정보만 조회)
	public Schedule selectSchedule(Connection conn, String scIdx) {
		Schedule schedule = new Schedule();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select a.sc_idx, a.user_id, a.d_day, a.mountain_name, a.allowed_num, a.info, a.openchat, a.age, a.reg_date, a.exp_date "
				+ " from schedule a  "
				+ " where a.sc_idx = ? and a.is_del = 0";
		
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
	
	//팝업 참가자 리스트
	//참가자 리스트 가져오기(참가자목록 번호, 일정번호, 유저아이디, 닉네임, 자기소개, (나이는 보류))
	public List<Member> selectParticipantList(Connection conn, String scIdx) {
		
		List<Member> ParticipantList = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		//쿼리문 작성 조인하는 법 도움 필요!
		String sql = "select M.user_id, M.nickname as userNickName, M.info as userInfo " + 
				"from participant_list L " + 
				"join participant_history H using(pl_idx) " + 
				"join member M on H.user_id = M.user_id " + 
				"where H.user_id = M.user_id and L.sc_idx = ? ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, scIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Member participant = new Member();
				participant.setUserId(rset.getString("user_id"));
				participant.setNickname(rset.getString("userNickName"));
				participant.setInfo(rset.getString("userInfo"));
				ParticipantList.add(participant);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return ParticipantList;
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

	//스케줄 삭제(호스트)
	public void deleteSchedule(String scIdx, Connection conn) {
		PreparedStatement pstm = null;
		String sql = "update schedule set is_del = 1 where sc_idx = ? "; //exception : is_del null값???
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, scIdx);
			int res = pstm.executeUpdate();
			
			if(res>0) {
				System.out.println("모임글 삭제 성공");
			}else {
				System.out.println("모임글 삭제 실패");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}


	
	//참가자 리스트 넣기
	public void insertParticipant(String scIdx, Member member, Connection conn) {

		CallableStatement cstm = null;
		String sql  = "{call sp_insert_participant(?,?)}";
		
		try {
			cstm = conn.prepareCall(sql);
			cstm.setString(1, scIdx);
			cstm.setString(2, member.getUserId());
			cstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(cstm);
		}
		
	} 
	

	public void cancleParticipant(String scIdx, Member member, Connection conn  ) {
		CallableStatement cstm = null;
		String sql = "{call sp_leave_participant(?,?)}";		
		int res = 0;

		try {
			cstm = conn.prepareCall(sql);
			cstm.setString(1, scIdx);
			cstm.setString(2, member.getUserId());
			res = cstm.executeUpdate();
			
			if(res>0) {
				System.out.println("동행 취소 성공");
			}else {
				System.out.println("동행 취소 실패");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(cstm);
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

		PreparedStatement pstm = null;
		String sql = "update schedule set status = 1 where sc_idx = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, scIdx);
			int res = pstm.executeUpdate();
			
			if(res>0) {
				System.out.println("승인성공");
			}else {
				System.out.println("승인 실패");
			}
			
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
	

}