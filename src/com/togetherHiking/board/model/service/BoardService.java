package com.togetherHiking.board.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.togetherHiking.board.model.dao.BoardDao;
import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.board.model.dto.BoardView;
import com.togetherHiking.board.model.dto.Reply;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.file.FileDTO;

public class BoardService {
	private BoardDao boardDao = new BoardDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public BoardService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<BoardView> getBoardList(String field, String query, int page){
		Connection conn = template.getConnection();
		List<BoardView> boardList = null;
		
		try {
			boardList = boardDao.selectBoardList(conn, field, query, page);
			
		} finally {
			template.close(conn);
		}
		
		return boardList;
	}

	public List<BoardView> getBoardList() {
		return getBoardList("title","",1);
	}
	
	public List<BoardView> getBoardList(int page){
		return getBoardList("title","",page);
		
	}

	public int insertBoard(Board board, List<FileDTO> fileDTOs) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			res = boardDao.insertBoard(board, conn);
			
			if(fileDTOs != null) {
				for (FileDTO fileDTO : fileDTOs) {
					boardDao.insertFile(fileDTO, conn);
				}
			}
			
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
		return res;
	}
	
	public String getPrevIdx(String bdIdx) {
		Connection conn = template.getConnection();
		String prevIdx = null;
		
		try {
			prevIdx = boardDao.selectPrevIdx(conn, bdIdx);
			
		} finally {
			template.close(conn);
		}
		
		return prevIdx;
	}
	
	public String getNextIdx(String bdIdx) {
		Connection conn = template.getConnection();
		String nextIdx = null;
		
		try {
			nextIdx = boardDao.selectNextIdx(conn, bdIdx);
			
		} finally {
			template.close(conn);
		}
		
		return nextIdx;
	}
	
	public Map<String,Object> getBoardDetail(String bdIdx){
		Connection conn = template.getConnection();
		Map<String,Object> datas = new HashMap<String, Object>();
		Board board = null;
		List<Reply> replys = null;
		List<FileDTO> files = null;
		FileDTO profile = null;
		String prevIdx = null;
		String nextIdx = null;
		
		try {
			board = boardDao.selectBoard(conn, bdIdx);
			replys = boardDao.selectReplyList(conn, bdIdx);
			files = boardDao.selectFileDTOs(conn, bdIdx);
			profile = boardDao.selectFileDTO(conn, board.getUserId());
			prevIdx = boardDao.selectPrevIdx(conn, bdIdx);
			nextIdx = boardDao.selectNextIdx(conn, bdIdx);
			
			datas.put("board", board);
			datas.put("replys", replys);
			datas.put("files", files);
			datas.put("profile", profile);
			datas.put("prevIdx", prevIdx);
			datas.put("nextIdx", nextIdx);
			
		} finally {
			template.close(conn);
		}
		
		return datas;
	}
	
	public Map<String,Object> editBoard(String bdIdx){
		Connection conn = template.getConnection();
		Map<String,Object> datas = new HashMap<String, Object>();
		Board board = null;
		List<FileDTO> files = null;
		
		try {
			board = boardDao.selectBoard(conn, bdIdx);
			
		} finally {
			template.close(conn);
		}
		
		return datas;
	}
	
	public int getBoardCount(String field, String query) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			res = boardDao.getBoardCount(conn, field, query);
			
		} finally {
			template.close(conn);
		}
		
		return res;
	}

	public int getBoardCount() {
		return getBoardCount("title","");
		
	}

}
