package com.togetherHiking.mountain.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private Mountain convertRowToMountain(ResultSet rset) throws SQLException {
		Mountain mountain = new Mountain();
		mountain.setmHight(rset.getString("m_hight"));
		mountain.setmInfo(rset.getString("m_info"));
		mountain.setmLoc(rset.getString("m_loc"));
		mountain.setmName(rset.getString("m_name"));
		mountain.setMountainIdx(rset.getString("mountain_idx"));
		return mountain;
	}

	
	public void insertMountain(Mountain mountain, Connection conn){	
		PreparedStatement pstm = null;
		
		String query = "insert into mountain (m_hight, m_info," 
					 + "m_loc, m_name, mountain_idx)" 
					 + "values(?,?,?,?,?) ";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mountain.getmHight());
			pstm.setString(2, mountain.getmInfo());
			pstm.setString(3, mountain.getmLoc());
			pstm.setString(4, mountain.getmName());
			pstm.setString(5, mountain.getMountainIdx());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		

	}
	
	
	// 어느부분까지 api에서 끌어오고, 받을 수 있는지..? 일단 조회만.+삽입(api데이터를 db에 넣기위해)
	
	
}
