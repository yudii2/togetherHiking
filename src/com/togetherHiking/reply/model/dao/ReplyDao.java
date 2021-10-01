
package com.togetherHiking.reply.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.togetherHiking.board.model.dao.BoardDao;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.reply.model.dto.Reply;

public class ReplyDao {
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private BoardDao boardDao = new BoardDao();
	
	public ReplyDao() {
		// TODO Auto-generated constructor stub
	}

	public List<Reply> selectReplyList(Connection conn, String bdIdx) {
		List<Reply> replyList = new ArrayList<Reply>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select rp_idx, bd_idx, user_id, content, code_idx, reg_date"
				+ " from reply_view"
				+ " where bd_idx = ? order by reg_date desc";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Reply reply = convertRowToReply(conn,rset);
				replyList.add(reply);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return replyList;
	}
	
	public void insertReply(Connection conn, Reply reply) {
		PreparedStatement pstm = null;
		String sql = "INSERT INTO REPLY(RP_IDX, BD_IDX, USER_ID, CONTENT)"
				+ " VALUES(SC_RP_IDX.NEXTVAL, ?, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, reply.getBdIdx());
			pstm.setString(2, reply.getUserId());
			pstm.setString(3, reply.getContent());
			int res = pstm.executeUpdate();
			
			if(res>0) {
				System.out.println("댓글 추가 성공");
			}else {
				System.out.println("댓글 추가 실패");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}
	
	public void deleteReply(Connection conn, String rpIdx) {
		PreparedStatement pstm = null;
		String sql = "UPDATE REPLY SET IS_DEL = 1 WHERE RP_IDX = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, rpIdx);
			int res = pstm.executeUpdate();
			
			if(res>0) {
				System.out.println("댓글 삭제 성공");
			}else {
				System.out.println("댓글 삭제 실패");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	private Reply convertRowToReply(Connection conn,ResultSet rset) throws SQLException {
		Reply reply = new Reply();
		FileDTO file = new FileDTO();
		String userId = rset.getString("user_id");
		file = boardDao.selectFile(conn, userId);

		reply.setUserId(userId);
		reply.setRpIdx(rset.getString("rp_idx"));
		reply.setBdIdx(rset.getString("bd_idx"));
		reply.setContent(rset.getString("content"));
		reply.setCodeIdx(rset.getString("code_idx"));
		reply.setRegDate(rset.getDate("reg_date"));
		reply.setProfileRenameFileName(file.getRenameFileName());
		reply.setProfileSavePath(file.getSavePath());
		
		return reply;
	}
	
}
