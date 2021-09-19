package com.togetherHiking.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.common.file.FileDTO;

public class MemberDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();

	//profile img 등록 메서드
	public void insertProfile(String userId, FileDTO file, Connection conn) {
		String sql = "insert into file_info "
				+ "(fl_idx,type_idx,origin_file_name,rename_file_name,save_path) "
				+ "values(sc_fl_idx,?,?,?,?)";
		
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setString(2, file.getOriginFileName());
			pstm.setString(3, file.getRenameFileName());
			pstm.setString(4, file.getSavePath());
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
		
	}
	//profile img 조회 메서드
	public FileDTO selectProfile(String userId, Connection conn) {
		String sql = "select type_idx, origin_file_name, rename_file_name, save_path "
				+ "from file_info where type_idx = ?";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		FileDTO profile = new FileDTO();
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				profile.setTypeIdx(rset.getString("type_idx"));
				profile.setOriginFileName(rset.getString("origin_file_name"));
				profile.setRenameFileName(rset.getString("rename_file_name"));
				profile.setSavePath(rset.getString("save_path"));
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		
		return profile;
	}


	
}
