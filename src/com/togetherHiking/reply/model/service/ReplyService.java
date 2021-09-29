package com.togetherHiking.reply.model.service;

import java.sql.Connection;

import com.togetherHiking.common.code.ErrorCode;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.exception.HandleableException;
import com.togetherHiking.reply.model.dao.ReplyDao;
import com.togetherHiking.reply.model.dto.Reply;

public class ReplyService {
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private ReplyDao replyDao = new ReplyDao();
	
	public void insertReply(Reply reply) {
		Connection conn = template.getConnection();
		
		try {
			replyDao.insertReply(conn, reply);
			
			template.commit(conn);
			
		} catch(DataAccessException e) {
			template.rollback(conn);
			throw new HandleableException(ErrorCode.FAILED_BOARD_ACCESS_ERROR);
		} finally {
			template.close(conn);
		}
	}

	public void deleteReply(String rpIdx) {
		Connection conn = template.getConnection();
		
		try {
			replyDao.deleteReply(conn, rpIdx);
			
			template.commit(conn);
			
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw new HandleableException(ErrorCode.FAILED_BOARD_ACCESS_ERROR);
		} finally {
			template.close(conn);
		}
	}
	
}
