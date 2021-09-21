package com.togetherHiking.board.model.service;

import java.sql.Connection;
import java.util.List;

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
		FileDTO fileDto = null;
		Connection conn = template.getConnection();
		
		try {
			fileDto = boardDao.selectFileDTO(conn,userId);
		} finally {
			template.close(conn);
		}
		
		return fileDto;
	}

}
