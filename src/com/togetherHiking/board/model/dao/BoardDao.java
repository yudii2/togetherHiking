package com.togetherHiking.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;

public class BoardDao {
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public BoardDao() {
		// TODO Auto-generated constructor stub
	}

	public List<Board> selectBoardList(Connection conn) {
		List<Board> boardList = new ArrayList<Board>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from board where is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
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

	public int getCount(Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		int res = 0;
		String sql = "select count(*) from board where is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				res = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return res;
	}

	public int getCount(Connection conn, String kwd) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		int res = 0;
		String sql = "select count(*) from board where title like '%'||?||'%' and is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, kwd);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				res = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return res;
	}

	public Board selectBoardByBdIdx(Connection conn, String bdIdx) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Board board = null;
		String sql = "select bd_idx, title, user_id, subject, content, reg_date, grade, is_del "
				+ "from board where bd_idx = ? and is_del = 0";
		
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
}
