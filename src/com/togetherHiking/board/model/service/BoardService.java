package com.togetherHiking.board.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.togetherHiking.board.model.dao.BoardDao;
import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.board.model.dto.BoardView;
import com.togetherHiking.common.code.ErrorCode;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.exception.HandleableException;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.reply.model.dao.ReplyDao;
import com.togetherHiking.reply.model.dto.Reply;

public class BoardService {
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private BoardDao boardDao = new BoardDao();
	private ReplyDao replyDao = new ReplyDao();
	
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
			throw new HandleableException(ErrorCode.FAILED_BOARD_ACCESS_ERROR);
		} finally {
			template.close(conn);
		}
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
		String prevIdx = null;
		String nextIdx = null;
		
		try {
			boardDao.updateBoardViewCnt(conn, bdIdx); // 조회수 +1
			board = boardDao.selectBoard(conn, bdIdx);
			replys = replyDao.selectReplyList(conn, bdIdx);
			files = boardDao.selectFiles(conn, bdIdx);
			prevIdx = boardDao.selectPrevIdx(conn, bdIdx);
			nextIdx = boardDao.selectNextIdx(conn, bdIdx);
			
			datas.put("board", board);
			datas.put("replys", replys);
			datas.put("files", files);
			datas.put("prevIdx", prevIdx);
			datas.put("nextIdx", nextIdx);
			
			template.commit(conn);
			
		}catch(DataAccessException e){
			template.rollback(conn);
			throw new HandleableException(ErrorCode.FAILED_BOARD_ACCESS_ERROR);
		} finally {
			template.close(conn);
		}
		return datas;
	}
	
	public int getBoardCount(String field, String query) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			res = boardDao.selectBoardCount(conn, field, query);
		} finally {
			template.close(conn);
		}
		return res;
	}

	public int getBoardCount() {
		return getBoardCount("title","");
	}

	public void deleteBoard(String bdIdx) {
		Connection conn = template.getConnection();
		
		try {
			boardDao.deleteBoard(conn, bdIdx);
			template.commit(conn);
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw new HandleableException(ErrorCode.FAILED_BOARD_ACCESS_ERROR);
		} finally {
			template.close(conn);
		}
	}
	// AuthorizationFilter.java 에서 사용중
	public String getWriterId(String table, String idx) {
		Connection conn = template.getConnection();
		String res = null;
		
		try {
			res = boardDao.selectWriter(conn, table, idx);
		} finally {
			template.close(conn);
		}
		return res;
	}
	
	public String getNewBdIdx(String userId) {
		Connection conn = template.getConnection();
		String res = "";
		
		try {
			res = boardDao.selectNewBdIdx(conn, userId);
		} finally {
			template.close(conn);
		}
		return res;
	}
}
