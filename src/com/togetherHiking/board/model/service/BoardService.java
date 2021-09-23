package com.togetherHiking.board.model.service;

import java.sql.Connection;
import java.util.List;

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
	
	public List<Reply> getReplyList(String bdIdx){
		List<Reply> replyList = null;
		Connection conn = template.getConnection();
		
		try {
			replyList = boardDao.selectReplyList(conn, bdIdx);
			
		} finally {
			template.close(conn);
		}
		
		return replyList;
	}

	public List<BoardView> getBoardList() {
		return getBoardList("title","",1);
	}
	
	public List<BoardView> getBoardList(int page){
		return getBoardList("title","",page);
		
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

	public int getBoardCount() {
		return getBoardCount("title","");
		
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

	public List<FileDTO> getFileDTOs(String bdIdx) {
		List<FileDTO> fileDTOs = null;
		Connection conn = template.getConnection();
		
		try {
			fileDTOs = boardDao.selectFileDTOs(conn, bdIdx);
			
		} finally {
			template.close(conn);
		}
		
		return fileDTOs;
	}

	public FileDTO getUserProfile(String userId) {
		FileDTO fileDTO = null;
		Connection conn = template.getConnection();
		
		try {
			fileDTO = boardDao.selectFileDTO(conn,userId);
			
		} finally {
			template.close(conn);
		}
		
		return fileDTO;
	}

	public void insertBoard(Board board, List<FileDTO> fileDTOs) {
		Connection conn = template.getConnection();
		
		try {
			boardDao.insertBoard(board, conn);
			
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
	}
	
	public Board getNextBoard(String bdIdx) {
		Board board = null;
		Connection conn = template.getConnection();
		
		try {
			board = boardDao.getNextBoard(conn, bdIdx);
			
		} finally {
			template.close(conn);
		}
		
		return board;
	}
	
	public Board getPrevBoard(String bdIdx) {
		Board board = null;
		Connection conn = template.getConnection();
		
		try {
			board = boardDao.getPrevBoard(conn, bdIdx);
			
		} finally {
			template.close(conn);
		}
		
		return board;
	}

}
