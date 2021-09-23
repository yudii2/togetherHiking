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
		List<BoardView> boardList = null;
		Connection conn = template.getConnection();
		
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
		int res = 0;
		Connection conn = template.getConnection();
		
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
	
	public String getNextBoard(String bdIdx) {
		String nextIdx = null;
		Connection conn = template.getConnection();
		
		try {
			nextIdx = boardDao.getNextBoard(conn, bdIdx);
			
		} finally {
			template.close(conn);
		}
		
		return nextIdx;
	}
	
	public String getPrevBoard(String bdIdx) {
		String prevIdx = null;
		Connection conn = template.getConnection();
		
		try {
			prevIdx = boardDao.getPrevBoard(conn, bdIdx);
			
		} finally {
			template.close(conn);
		}
		
		return prevIdx;
	}
	
	public Map<String,Object> getBoardDetail(String bdIdx){
		Map<String,Object> datas = new HashMap<String, Object>();
		Board board = null;
		List<Reply> replys = null;
		List<FileDTO> files = null;
		FileDTO profile = null;
		Connection conn = template.getConnection();
		
		try {
			board = boardDao.selectBoard(conn, bdIdx);
			replys = boardDao.selectReplyList(conn, bdIdx);
			files = boardDao.selectFileDTOs(conn, bdIdx);
			profile = boardDao.selectFileDTO(conn, board.getUserId());
			
			datas.put("board", board);
			datas.put("replys", replys);
			datas.put("files", files);
			datas.put("profile", profile);
			
		} finally {
			template.close(conn);
		}
		
		return datas;
	}
	
	public Map<String,Object> editBoard(String bdIdx){
		Map<String,Object> datas = new HashMap<String, Object>();
		Board board = null;
		List<FileDTO> files = null;
		Connection conn = template.getConnection();
		
		try {
			board = boardDao.selectBoard(conn, bdIdx);
			
		} finally {
			template.close(conn);
		}
		
		return datas;
	}
	
	public int getBoardCount(String field, String query) {
		int res = 0;
		Connection conn = template.getConnection();
		
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
	
//	public Board getBoard(String bdIdx) {
//	Board board = null;
//	Connection conn = template.getConnection();
//	
//	try {
//		board = boardDao.selectBoard(conn, bdIdx);
//		
//	} finally {
//		template.close(conn);
//	}
//	
//	return board;
//	}

//	public List<Reply> getReplyList(String bdIdx){
//		List<Reply> replyList = null;
//		Connection conn = template.getConnection();
//		
//		try {
//			replyList = boardDao.selectReplyList(conn, bdIdx);
//			
//		} finally {
//			template.close(conn);
//		}
//		
//		return replyList;
//	}
	
//	public List<FileDTO> getFileDTOs(String bdIdx) {
//		List<FileDTO> fileDTOs = null;
//		Connection conn = template.getConnection();
//		
//		try {
//			fileDTOs = boardDao.selectFileDTOs(conn, bdIdx);
//			
//		} finally {
//			template.close(conn);
//		}
//		
//		return fileDTOs;
//	}

//	public FileDTO getUserProfile(String userId) {
//		FileDTO fileDTO = null;
//		Connection conn = template.getConnection();
//		
//		try {
//			fileDTO = boardDao.selectFileDTO(conn,userId);
//			
//		} finally {
//			template.close(conn);
//		}
//		
//		return fileDTO;
//	}


}
