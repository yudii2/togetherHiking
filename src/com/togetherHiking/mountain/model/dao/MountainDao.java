package com.togetherHiking.mountain.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.mountain.model.dto.Mountain;

public class MountainDao {
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public List<Mountain> selectMountainList(Connection conn) {
		List<Mountain>  mountainList = new ArrayList< Mountain>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from mountain";
		
		try {
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Mountain mountain = convertRowToMountain(rset);
				mountainList.add(mountain);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return mountainList;
	}
	private Mountain convertRowToMountain(ResultSet rset) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	// 어느부분까지 api에서 끌어오고, 받을 수 있는지..? 일단 조회만.
	
	
	
	
	
	
}
