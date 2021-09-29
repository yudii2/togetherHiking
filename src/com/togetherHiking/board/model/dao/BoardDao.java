package com.togetherHiking.board.model.dao;

import java.sql.CallableStatement;
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
import com.togetherHiking.reply.model.dto.Reply;

public class BoardDao {
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public BoardDao() {
		// TODO Auto-generated constructor stub
	}

	public List<BoardView> selectBoardList(Connection conn, String field, String query, int page) {
		List<BoardView> boardList = new ArrayList<BoardView>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select *"
				+ " from (select rownum NUM, N.*"
				+ " from (select * from board_view where " + field + " like ? order by reg_date desc) N)"
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
				BoardView boardView = convertRowToBoardView(rset);
				
				boardList.add(boardView);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return boardList;
	}
	
	public int selectBoardCount(Connection conn, String field, String query) {
		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
//		String sql = "select count(bd_idx) COUNT"
//				+ " from (select rownum NUM, N.*"
//				+ " from (select * from board"
//				+ " where " + field + " like ? and is_del = 0 order by reg_date desc) N)";
		String sql = "select count(bd_idx) COUNT"
				+ " from (select bd_idx from board"
				+ " where " + field + " like ? and is_del = 0)";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + query + "%");
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("count");
			}
			
		} catch (SQLException e) {
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
		String sql = "select bd_idx, user_id, title, subject, content, reg_date, view_cnt"
				+ " from board"
				+ " where bd_idx = ? and is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				board = convertRowToBoard(rset);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return board;
	}
	
	public String selectNextIdx(Connection conn, String bdIdx){
		String nextIdx = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select bd_idx"
				+ " from (select bd_idx from board where reg_date >"
				+ " (select reg_date from board where bd_idx = ? and is_del = 0)"
				+ " order by reg_date asc)"
				+ " where rownum = 1";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				nextIdx = rset.getString("bd_idx");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return nextIdx;
		
	}
	
	public String selectPrevIdx(Connection conn, String bdIdx){
		String prevIdx = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select bd_idx"
				+ " from (select bd_idx from board where reg_date <"
				+ " (select reg_date from board where bd_idx = ? and is_del = 0)"
				+ " order by reg_date desc)"
				+ " where rownum = 1";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				prevIdx = rset.getString("bd_idx");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return prevIdx;
		
	}
	
	public List<FileDTO> selectFiles(Connection conn, String bdIdx) {
		List<FileDTO> files = new ArrayList<FileDTO>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select fl_idx, type_idx, origin_file_name, rename_file_name, save_path, reg_date"
				+ " from file_info"
				+ " where type_idx = ? and is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				FileDTO file = convertRowToFileDTO(rset);
				files.add(file);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return files;
	}

	public FileDTO selectFile(Connection conn, String userId) {
		FileDTO fileDTO = new FileDTO();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select fl_idx, type_idx, origin_file_name, rename_file_name, save_path, reg_date"
				+ " from (select fl_idx, type_idx, origin_file_name, rename_file_name, save_path, reg_date"
				+ " from file_info"
				+ " where type_idx = ? and is_del = 0"
				+ " order by reg_date desc)"
				+ " where rownum = 1";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				fileDTO = convertRowToFileDTO(rset);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return fileDTO;
	}
	
	public int insertBoard(Board board, Connection conn) {
		String sql = "insert into board(bd_idx,user_id,title,content,subject) "
				+ "values(sc_bd_idx.nextval,?,?,?,?)";
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, board.getUserId());
			pstm.setString(2, board.getTitle());
			pstm.setString(3, board.getContent());
			pstm.setString(4, board.getSubject());
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
		return res;
	}
	
	public void insertFile(FileDTO fileDTO, Connection conn) {
		// conn.close()를 아직 하지 않아서 세션이 살아있기 때문에
		// currval를 사용할 수 있다.
		String sql = "insert into file_info(fl_idx,type_idx,origin_file_name,rename_file_name,save_path)"
				+ " values(sc_fl_idx.nextval,sc_bd_idx.currval,?,?,?)";
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, fileDTO.getOriginFileName());
			pstm.setString(2, fileDTO.getRenameFileName());
			pstm.setString(3, fileDTO.getSavePath());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void deleteBoard(Connection conn, String bdIdx) {
		CallableStatement cstm = null;
		String sql = "{CALL SP_DELETE_BOARD(?)}";
		
		try {
			cstm = conn.prepareCall(sql);
			cstm.setString(1,bdIdx);
			int res = cstm.executeUpdate();
			
			if(res>0) {
				System.out.println("게시글 삭제 성공");
			}else {
				System.out.println("게시글 삭제 실패");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(cstm);
		}
		
	}

	public void updateBoardViewCnt(Connection conn, String bdIdx) {
		PreparedStatement pstm = null;
		String sql = "UPDATE BOARD SET VIEW_CNT = VIEW_CNT + 1 WHERE BD_IDX = ? AND IS_DEL = 0";
		
		try {
			pstm = conn.prepareCall(sql);
			pstm.setString(1, bdIdx);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public String selectWriter(Connection conn, String table, String idx) {
		String res = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "SELECT USER_ID FROM " + table + " WHERE " + (table.equals("board")? "bd_idx" : "rp_idx" ) + " = ? AND IS_DEL = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				res = rset.getString("user_id");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	private Board convertRowToBoard(ResultSet rset) throws SQLException {
		Board board = new Board();
		board.setBdIdx(rset.getString("bd_idx"));
		board.setUserId(rset.getString("user_id"));
		board.setTitle(rset.getString("title"));
		board.setSubject(rset.getString("subject"));
		board.setContent(rset.getString("content"));
		board.setRegDate(rset.getDate("reg_date"));
		board.setViewCnt(rset.getInt("view_cnt"));
		
		return board;
	}
	
	private BoardView convertRowToBoardView(ResultSet rset) throws SQLException {
		BoardView boardView = new BoardView();
		boardView.setBdIdx(rset.getString("bd_idx"));
		boardView.setUserId(rset.getString("user_id"));
		boardView.setTitle(rset.getString("title"));
		boardView.setSubject(rset.getString("subject"));
		boardView.setRegDate(rset.getDate("reg_date"));
		boardView.setViewCnt(rset.getInt("view_cnt"));
		boardView.setReplyCnt(rset.getInt("reply_cnt"));
		
		return boardView;
	}
	
	private FileDTO convertRowToFileDTO(ResultSet rset) throws SQLException {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFlIdx(rset.getString("fl_idx"));
		fileDTO.setTypeIdx(rset.getString("type_idx"));
		fileDTO.setOriginFileName(rset.getString("origin_file_name"));
		fileDTO.setRenameFileName(rset.getString("rename_file_name"));
		fileDTO.setRegDate(rset.getDate("reg_date"));
		fileDTO.setSavePath(rset.getString("save_path"));
		
		return fileDTO;
	}
	
}
