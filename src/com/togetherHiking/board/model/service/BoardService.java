package com.togetherHiking.board.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.togetherHiking.board.model.dao.BoardDao;
import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.board.model.dto.Reply;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.file.FileDTO;

public class BoardService {
	private BoardDao boardDao = new BoardDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public BoardService() {
		// TODO Auto-generated constructor stub
	}

	public List<Board> getBoardList() {
		return getBoardList("title","",1);
	}
	
	public List<Board> getBoardList(int page){
		return getBoardList("title","",page);
		
	}
	
	public List<Board> getBoardList(String field, String query, int page){
		List<Board> boardList = null;
		Connection conn = template.getConnection();
		
		try {
			boardList = boardDao.selectBoardList(conn, field, query, page);
		} finally {
			template.close(conn);
		}
		
		return boardList;
		
	}
	
	public int getBoardCount() {
		return getBoardCount("title","");
		
	}
	
	public int getBoardCount(String field, String query) {
		return 0;
		
	}

//	public Map<String,Object> getBoard(String bdIdx) {
//		Map<String,Object> datas = new HashMap<String, Object>();
//		Connection conn = template.getConnection();
//		
//		Board board = null;
//		List<Reply> replys = null;
//		List<FileDTO> files = null;
//		FileDTO profile = null;
//		
//		try {
//			board = boardDao.selectBoard(conn, bdIdx);
//			replys = boardDao.selectReplyList(conn,bdIdx);
//			files = boardDao.selectFileDTOs(conn,bdIdx);
//			profile = boardDao.getProfile(conn, board.getUserId());
//			
//			datas.put("board", board);
//			datas.put("replys", replys);
//			datas.put("files", files);
//			datas.put("profile", profile);
//		} finally {
//			template.close(conn);
//		}
//		
//		return datas;
//	}

	public Board getBoard(String bdIdx) {
		Board board = null;
		Connection conn = template.getConnection();
		
		try {
			board = boardDao.selectBoard(conn, bdIdx);
		} finally {
			template.close(conn);
		}
		return board;
	}
	
	public Board getNextBoard(String bdIdx){
		return null;
		
	}
	
	public Board getPrevBoard(String bdIdx){
		return null;
		
	}

}
