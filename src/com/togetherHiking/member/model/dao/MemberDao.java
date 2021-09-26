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
import com.togetherHiking.board.model.dto.Reply;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.member.model.dto.Member;

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
			
			String[] fieldArr = {"user_id","password","email","grade","user_name","is_leave","info","birth","nickname","join_date","is_host"};
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

	public Map<String,List> selectMyReply(Member member, Connection conn) {
		List<Reply> replyList = new ArrayList<>();
		List<Board> boardList = new ArrayList<Board>();
		Map<String, List> myReply = new HashMap<String, List>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String sql = "select rp_idx, b.title, r.content, code_idx, r.reg_date "
				+ "from reply r join board b using (bd_idx) "
				+ "where r.user_id = ? and r.is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, member.getUserId());
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


	
	
	private Member convertRowToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setUserId(rset.getString("user_id"));
		member.setPassword(rset.getString("password"));
		member.setEmail(rset.getString("email"));
		member.setGrade(rset.getString("grade"));
		member.setInfo(rset.getString("info"));
		member.setBirth(rset.getDate("birth"));
		member.setNickname(rset.getString("nickname"));
		member.setUserName(rset.getString("user_name"));
		member.setJoinDate(rset.getDate("join_date"));
		member.setIsHost(rset.getString("is_host"));
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
			case "user_name":member.setUserName(rset.getString("user_name"));break;
			case "join_date":member.setJoinDate(rset.getDate("join_date"));break;
			case "is_host":member.setIsHost(rset.getString("is_host"));
			case "is_leave":member.setIsLeave(rset.getInt("is_leave"));break;

			}
		}
		return member;
	}








	
}
