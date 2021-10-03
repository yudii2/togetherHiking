package com.togetherHiking.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.mountain.model.dto.Mountain;
import com.togetherHiking.schedule.model.dto.Schedule;
import com.togetherHiking.reply.model.dto.Reply;

public class MemberDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public Member memberAuthenticate(String userId, String password, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from member where user_id = ? and password = ? ";			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, password);
			rset = pstm.executeQuery();
			
			String[] fieldArr = {"user_id","password","email","grade","is_leave","info","birth","nickname","join_date","is_host"};
			if(rset.next()) {
				member =  convertRowToMember(rset,fieldArr);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		
		return member;
	}

	
	public Member selectMemberById(String userId, Connection conn) {
		Member member = null;			
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_Id = ?";
		
		try {			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = convertRowToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}

		return member;
	}

	//profile img 등록 메서드
	public void insertProfile(String userId, FileDTO file, Connection conn) {
		
		PreparedStatement pstm = null;
		
		String sql = "insert into file_info "
				+ "(fl_idx,type_idx,origin_file_name,rename_file_name,save_path) "
				+ "values(sc_fl_idx.nextval,?,?,?,?)";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setString(2, file.getOriginFileName());
			pstm.setString(3, file.getRenameFileName());
			pstm.setString(4, file.getSavePath());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
	
	}
	//profile img 조회 메서드
	public FileDTO selectProfile(String userId, Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		FileDTO profile = new FileDTO();
		
		String sql = "select rownum,type_idx, origin_file_name, rename_file_name, save_path "
				+ "from (select type_idx, origin_file_name, rename_file_name, save_path "
				+ "from file_info where type_idx = ? "
				+ "order by reg_date desc) where rownum = 1";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				profile.setTypeIdx(rset.getString("type_idx"));
				profile.setOriginFileName(rset.getString("origin_file_name"));
				profile.setRenameFileName(rset.getString("rename_file_name"));
				profile.setSavePath(rset.getString("save_path"));
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		
		return profile;
	}
	
	
	
	
	public int updateProfile(String userId, FileDTO fileDTO, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		
		String sql = "update file_info set origin_file_name = ? "
				+ ", rename_file_name = ?"
				+ ", save_path = ? "
				+ ", reg_date = sysdate "
				+ "where type_idx = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, fileDTO.getOriginFileName());
			pstm.setString(2, fileDTO.getRenameFileName());
			pstm.setString(3, fileDTO.getSavePath());
			pstm.setString(4, userId);
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}

		
		return res;
	}
	
	
	
	public Member selectByNickname(String nickname, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String sql = "select * from member where nickname = ?";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nickname);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = convertRowToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		
		return member;
	}
	

	
	public int updateMember(Member member, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		
		String sql = "update member set nickname = ? "
				+ ", password = ?"
				+ ", info = ? "
				+ "where user_id = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, member.getNickname());
			pstm.setString(2, member.getPassword());
			pstm.setString(3, member.getInfo());
			pstm.setString(4, member.getUserId());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}

		
		return res;
	}

	public List<Board> selectMyPostById(String userId, Connection conn) {
		List<Board> boardList = new ArrayList<Board>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String sql = "select * from board "
				+ "where user_id = ? and is_del = 0 "
				+ "order by reg_date desc";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setUserId(rset.getString("user_id"));
				board.setBdIdx(rset.getString("bd_idx"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setSubject(rset.getString("subject"));
				board.setTitle(rset.getString("title"));
				board.setViewCnt(rset.getInt("view_cnt"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset,pstm);
		}

		return boardList;
	}
	
	public List<Board> selectPostByPage(String userId, int page, Connection conn) {
		List<Board> boardList = new ArrayList<Board>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
				
		String sql = "select * from (select rownum num, b.* "
				+ "from (select * from board where is_del = 0 order by reg_date desc) b "
				+ ") where (num between ? and ? ) and (user_id = ?) ";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (page-1)*8+1);				//1, 9, 17, 23,, -> (page-1) * 8 + 1 
			pstm.setInt(2, page*8);			//8, 16, 24, 	 -> page * 8
			pstm.setString(3, userId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setUserId(rset.getString("user_id"));
				board.setBdIdx(rset.getString("bd_idx"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setSubject(rset.getString("subject"));
				board.setTitle(rset.getString("title"));
				board.setViewCnt(rset.getInt("view_cnt"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset,pstm);
		}
		
		return boardList;
	}

	public Map<String,List> selectMyReply(String userId, Connection conn) {
		List<Reply> replyList = new ArrayList<Reply>();
		List<Board> boardList = new ArrayList<Board>();
		Map<String, List> myReply = new HashMap<String, List>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String sql = "select rp_idx, b.title, r.content, code_idx, r.reg_date "
				+ "from reply r join board b using (bd_idx) "
				+ "where r.user_id = ? and r.is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Reply reply = new Reply();
				Board board = new Board();
				reply.setRpIdx(rset.getString("rp_idx"));
				reply.setContent(rset.getString("content"));
				reply.setCodeIdx(rset.getString("code_idx"));
				reply.setRegDate(rset.getDate("reg_date"));
				board.setTitle(rset.getString("title"));
				replyList.add(reply);
				boardList.add(board);

			}
			
			myReply.put("reply", replyList);
			myReply.put("boardTitle", boardList);
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset,pstm);
		}
		return myReply;
	}
	
	public int insertMember(Member member, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "insert into member(user_id,password,nickname,birth,email,info) values(?,?,?,?,?,?)";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserId());
			pstm.setString(2, member.getPassword());
			pstm.setString(3, member.getNickname());
			pstm.setDate(4, member.getBirth());
			pstm.setString(5, member.getEmail());
			pstm.setString(6, member.getInfo());

			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}

		return res;
	}
	
	//유진 09/30
	public List<Schedule> selectMySchedule(String userId, Connection conn) {
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String sql = "select mountain_name, d_day,status from schedule S "
				+ "join participant_list L using(sc_idx) "
				+ "join participant_history H using (pl_idx) where H.user_id = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);	//참가자 ID와 비교
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Schedule schedule = new Schedule();
				schedule.setMountainName(rset.getString("mountain_name"));
				schedule.setdDay(rset.getDate("d_day"));
				
				scheduleList.add(schedule);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset,pstm);
		}
		
		return scheduleList;
	}
	
	//유진 09/30
	public Mountain selectMountain(String mountainName, Connection conn) {
		Mountain mountain = new Mountain();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String sql = "select m_height from mountain where m_name = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, mountainName);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				mountain.setmHeight(rset.getString("m_height"));				
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset,pstm);
		}
		
		
		return mountain;
	}

	
	
	
	private Member convertRowToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setUserId(rset.getString("user_id"));
		member.setPassword(rset.getString("password"));
		member.setEmail(rset.getString("email"));
		member.setGrade(rset.getString("grade"));
		member.setInfo(rset.getString("info"));
		member.setBirth(rset.getDate("birth"));
		member.setNickname(rset.getString("nickname"));
		member.setJoinDate(rset.getDate("join_date"));
		member.setIsHost(rset.getInt("is_host"));
		member.setIsLeave(rset.getInt("is_leave"));
		return member;
	}
	
	private Member convertRowToMember(ResultSet rset, String[] fieldArr) throws SQLException {
		Member member = new Member();
		for (int i = 0; i < fieldArr.length; i++) {
			String field = fieldArr[i].toLowerCase();
			switch (field) {
			case "user_id":member.setUserId(rset.getString("user_id")); break;
			case "password":member.setPassword(rset.getString("password"));break;
			case "email":member.setEmail(rset.getString("email"));break;
			case "grade":member.setGrade(rset.getString("grade"));break;
			case "info":member.setInfo(rset.getString("info"));break;
			case "birth":member.setBirth(rset.getDate("birth"));break;
			case "nickname":member.setNickname(rset.getString("nickname"));break;
			case "join_date":member.setJoinDate(rset.getDate("join_date"));break;
			case "is_host":member.setIsHost(rset.getInt("is_host"));
			case "is_leave":member.setIsLeave(rset.getInt("is_leave"));break;

			}
		}
		return member;
	}


	public void insertkakaoMember(Member kakaomember, Connection conn) {
		PreparedStatement pstm = null;
		String query = "insert into member(user_id,nickname,birth,info) values(?,?,?,?)";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kakaomember.getUserId());
			pstm.setString(2, kakaomember.getNickname());
			pstm.setDate(3, kakaomember.getBirth());
			pstm.setString(4, kakaomember.getInfo());

			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}		
	}

}
