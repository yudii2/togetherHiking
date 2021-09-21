package com.togetherHiking.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.board.model.dto.Reply;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.file.FileDTO;

public class BoardDao {
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public BoardDao() {
		// TODO Auto-generated constructor stub
	}

	public List<Board> selectBoardList(Connection conn, String field, String query, int page) {
		List<Board> boardList = new ArrayList<Board>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from"
				+ " (select rownum NUM, N.* from"
				+ " (select * from board where " + field + " like ? order by reg_date desc) N)"
				+ " where NUM between ? and ?";
		
		// ? 사용시 'field' (앞.뒤로 싱글쿼테이션 잡힘)
		// 1, 11, 21, 31 => an = 1 + (page - 1) * 10
		// 10, 20, 30, 40 => page*10
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + query + "%");
			pstm.setInt(2, 1 + (page - 1) * 10);
			pstm.setInt(3, page * 10);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Board board = convertRowToBoard(rset);
				boardList.add(board);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return boardList;
	}
	
	public int getBoardCount(Connection conn) {
		return getBoardCount(conn,"title","");
		
	}
	
	public int getBoardCount(Connection conn, String field, String query) {
		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select count(bd_idx) as count from"
				+ " (select rownum NUM, N.* from"
				+ " (select * from board where " + field + " like ? order by reg_date desc) N) ";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + query + "%");
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("count");
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return count;
		
	}
	
	public Board selectBoard(Connection conn, String bdIdx) {
		Board board = new Board();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from board where bd_idx = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				board = convertRowToBoard(rset);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return board;
	}
	
	public Board getNextBoard(Connection conn, String bdIdx){
		Board board = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from"
				+ " (select * from board where reg_date >"
				+ " (select reg_date from board where bd_idx = ?)"
				+ " order by reg_date asc) where rownum = 1";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				board = convertRowToBoard(rset);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return board;
		
	}
	
	public Board getPrevBoard(Connection conn, String bdIdx){
		Board board = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from"
				+ " (select * from board where reg_date <"
				+ " (select reg_date from board where bd_idx = ?)"
				+ " order by reg_date desc) where rownum = 1";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				board = convertRowToBoard(rset);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return board;
		
	}
	
	public List<Reply> selectReplyList(Connection conn, String bdIdx) {
		List<Reply> replyList = new ArrayList<Reply>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from REPLY where bd_idx = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Reply reply = convertRowToReply(rset);
				replyList.add(reply);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return replyList;
	}
	
	public List<FileDTO> selectFileDTOs(Connection conn, String bdIdx) {
		List<FileDTO> files = new ArrayList<FileDTO>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from (select * from file_info where type_idx= ? order by reg_date desc) where rownum = 1";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				FileDTO fileDTO = convertRowToFileDTO(rset);
				files.add(fileDTO);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return files;
	}

	public FileDTO selectFileDTO(Connection conn, String userId) {
		FileDTO fileDTO = new FileDTO();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from file_info where type_idx = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				fileDTO = convertRowToFileDTO(rset);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return fileDTO;
	}

	private Board convertRowToBoard(ResultSet rset) throws SQLException {
		Board board = new Board();
		board.setBdIdx(rset.getString("bd_idx"));
		board.setContent(rset.getString("content"));
		board.setRegDate(rset.getDate("reg_date"));
		board.setSubject(rset.getString("subject"));
		board.setTitle(rset.getString("title"));
		board.setUserId(rset.getString("user_id"));
		board.setIsDel(rset.getInt("is_del"));

		return board;
	}

	private Reply convertRowToReply(ResultSet rset) throws SQLException {
		Reply reply = new Reply();
		reply.setBdIdx(rset.getString("bd_idx"));
		reply.setCodeIdx(rset.getString("code_idx"));
		reply.setCoIdx(rset.getString("co_idx"));
		reply.setContent(rset.getString("content"));
		reply.setIsDel(rset.getInt("is_del"));
		reply.setRegDate(rset.getDate("reg_date"));
		reply.setUserId(rset.getString("user_id"));
		
		return reply;
	}
	
	private FileDTO convertRowToFileDTO(ResultSet rset) throws SQLException {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFlIdx(rset.getString("fl_idx"));
		fileDTO.setTypeIdx(rset.getString("type_idx"));
		fileDTO.setOriginFileName(rset.getString("origin_file_name"));
		fileDTO.setRenameFileName(rset.getString("rename_file_name"));
		fileDTO.setRegDate(rset.getDate("reg_date"));
		fileDTO.setSavePath(rset.getString("save_path"));
		fileDTO.setIsDel(rset.getInt("is_del"));
		
		return fileDTO;
	}

}
