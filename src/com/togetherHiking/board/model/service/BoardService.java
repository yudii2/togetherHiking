package com.togetherHiking.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.togetherHiking.board.model.dao.BoardDao;
import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.common.db.JDBCTemplate;

public class BoardService {
	private BoardDao boardDao = new BoardDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public BoardService() {
		// TODO Auto-generated constructor stub
	}

	public List<Board> selectBoardList() {
		List<Board> boardList = null;
		Connection conn = template.getConnection();
		
		try {
			boardList = boardDao.selectBoardList(conn);
		} finally {
			template.close(conn);
		}
		
		return boardList;
	}

	public List<Board> selectBoardList(String field, String query) {
		List<Board> boardList = null;
		Connection conn = template.getConnection();
		
		try {
			boardList = boardDao.selectBoardList(conn,field,query);
		} finally {
			template.close(conn);
		}
		
		return boardList;
	}

	public Board selectBoardByBdIdx(String bdIdx) {
		Connection conn = template.getConnection();
		Board board = null;
		
		try {
			board = boardDao.selectBoardByBdIdx(conn, bdIdx);
		} finally {
			template.close(conn);
		}
		
		return board;
	}
}
