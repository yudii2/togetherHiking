package com.togetherHiking.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.board.model.dto.BoardView;
import com.togetherHiking.board.model.dto.Reply;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.file.FileDTO;

public class BoardDao {
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public BoardDao() {
		// TODO Auto-generated constructor stub
	}

	public List<BoardView> selectBoardList(Connection conn, String field, String query, int page) {
		List<BoardView> boardList = new ArrayList<BoardView>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from"
				+ " (select rownum NUM, N.* from"
				+ " (select * from board_view where " + field + " like ? order by reg_date desc) N)"
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
//				int replyCnt = selectReplyCount(conn,board.getBdIdx());
//				board.setReplyCnt(replyCnt);
				
				boardList.add(boardView);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return boardList;
	}
	// board 테이블에 컬럼으로 추가? 메서드로 처리? - board_view 생성완료
//	public int selectReplyCount(Connection conn, String bdIdx) {
//		int res = 0;
//		PreparedStatement pstm = null;
//		ResultSet rset = null;
//		String sql = "SELECT BD_IDX, COUNT(CO_IDX) REPLY_CNT"
//				+ " FROM BOARD JOIN REPLY USING(BD_IDX)"
//				+ " WHERE BD_IDX = ?"
//				+ " GROUP BY BD_IDX";
//		
//		try {
//			pstm = conn.prepareStatement(sql);
//			pstm.setString(1, bdIdx);
//			rset = pstm.executeQuery();
//			
//			if(rset.next()) {
//				res = rset.getInt("reply_cnt");
//			}
//		} catch (Exception e) {
//			throw new DataAccessException(e);
//		} finally {
//			template.close(rset, pstm);
//		}
//		
//		return res;
//	}
	
	public int getBoardCount(Connection conn, String field, String query) {
		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select count(bd_idx) COUNT from"
				+ " (select rownum NUM, N.* from"
				+ " (select * from board where " + field + " like ? order by reg_date desc) N)";
		
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
		Board board = new Board();
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
		Board board = new Board();
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
		String sql = "select * from REPLY where bd_idx = ? order by reg_date desc";
		
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
		String sql = "select fl_idx,type_idx,origin_file_name,rename_file_name,save_path,reg_date"
				+ " from file_info where type_idx=? and is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				FileDTO file = convertRowToFileDTO(rset);
				files.add(file);
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
		String sql = "select * from"
				+ " (select * from file_info where type_idx = ? order by reg_date desc)"
				+ " where rownum = 1";
		
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
	
	public void insertBoard(Board board, Connection conn) {
		String sql = "insert into board(bd_idx,user_id,title,content,subject) "
				+ "values(sc_board_idx.nextval,?,?,?,?)";
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, board.getUserId());
			pstm.setString(2, board.getTitle());
			pstm.setString(3, board.getContent());
			pstm.setString(4, board.getSubject());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}
	
	public void insertFile(FileDTO fileDTO, Connection conn) {
		// conn.close()를 아직 하지 않아서 세션이 살아있기 때문에
		// currval를 사용할 수 있다.
		String sql = "insert into file_info(fl_idx,type_idx,origin_file_name,rename_file_name,save_path)"
				+ " values(sc_file_idx.nextval,sc_board_idx.currval,?,?,?)";
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, fileDTO.getOriginFileName());
			pstm.setString(2, fileDTO.getRenameFileName());
			pstm.setString(3, fileDTO.getSavePath());
			pstm.executeUpdate();
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	private Board convertRowToBoard(ResultSet rset) throws SQLException {
		Board board = new Board();
		board.setBdIdx(rset.getString("bd_idx"));
		board.setUserId(rset.getString("user_id"));
		board.setSubject(rset.getString("subject"));
		board.setTitle(rset.getString("title"));
		board.setContent(rset.getString("content"));
		board.setRegDate(rset.getDate("reg_date"));
		board.setViewCnt(rset.getInt("view_cnt"));
		
		return board;
	}
	
	private BoardView convertRowToBoardView(ResultSet rset) throws SQLException {
		BoardView boardView = new BoardView();
		boardView.setBdIdx(rset.getString("bd_idx"));
		boardView.setUserId(rset.getString("user_id"));
		boardView.setSubject(rset.getString("subject"));
		boardView.setTitle(rset.getString("title"));
		boardView.setRegDate(rset.getDate("reg_date"));
		boardView.setViewCnt(rset.getInt("view_cnt"));
		boardView.setReplyCnt(rset.getInt("reply_cnt"));
		
		return boardView;
	}

	private Reply convertRowToReply(ResultSet rset) throws SQLException {
		Reply reply = new Reply();
		reply.setBdIdx(rset.getString("bd_idx"));
		reply.setCodeIdx(rset.getString("code_idx"));
		reply.setCoIdx(rset.getString("co_idx"));
		reply.setContent(rset.getString("content"));
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
		
		return fileDTO;
	}

}
