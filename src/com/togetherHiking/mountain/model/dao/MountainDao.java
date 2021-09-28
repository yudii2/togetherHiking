package com.togetherHiking.mountain.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.togetherHiking.board.model.dto.BoardView;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.exception.DataAccessException;
import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.mountain.model.dto.Mountain;

public class MountainDao {
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
//	public List<Mountain> selectMountainList(Connection conn) {
//		List<Mountain>  mountainList = new ArrayList< Mountain>();
//		PreparedStatement pstm = null;
//		ResultSet rset = null;
//		String query = "select * from mountain";
//		
//		try {
//			pstm = conn.prepareStatement(query);
//			rset = pstm.executeQuery();
//			
//			while(rset.next()) {
//				Mountain mountain = convertRowToMountain(rset);
//				mountainList.add(mountain);
//			}
//		} catch (Exception e) {
//			throw new DataAccessException(e);
//		} finally {
//			template.close(rset, pstm);
//		}
//		
//		return mountainList;
//	}
//	
//	
//	public Mountain selectMountainByMountainName(String mName, Connection conn) {
//		Mountain mountain = null;			
//		PreparedStatement pstm = null;
//		ResultSet rset = null;
//		
//		String query = "select * from mountain where m_name = ?";
//		
//		try {			
//			pstm = conn.prepareStatement(query);
//			pstm.setString(1, mName);
//			rset = pstm.executeQuery();
//			
//			if(rset.next()) {
//				mountain = convertRowToMountain(rset);
//			}
//		} catch (SQLException e) {
//			throw new DataAccessException(e);
//		} finally {
//			template.close(rset, pstm);
//		}
//
//		return mountain;
//	}
	
	
	
	public void insertMountain(Mountain mountain, Connection conn){	
		PreparedStatement pstm = null;
		
		String query = "insert into mountain (m_height, m_info," 
					 + "m_loc, m_name, mountain_idx)" 
					 + "values(?,?,?,?,sc_mountain_idx.nextval) ";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mountain.getmHeight());
			pstm.setString(2, mountain.getmInfo());
			pstm.setString(3, mountain.getmLoc());
			pstm.setString(4, mountain.getmName());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		

	}


	public Mountain getMountainInfo(String mName, Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Mountain mountainInfo = new Mountain();
		
		String sql = "select * from mountain where M_NAME = ? ";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,mName);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				mountainInfo = convertRowToMountain(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return mountainInfo;
	}	
	
	public List<Mountain> getSeoulMountainList(Mountain mountain,Connection conn) {	
		PreparedStatement pstm = null;
		ResultSet rset = null;
		List<Mountain> seoulMountain = new ArrayList<Mountain>();	
		
		String sql = "select M_NAME from mountain where M_LOC like '서울%'";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, mountain.getmName());
			rset = pstm.executeQuery();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return seoulMountain;
	}
	
	public List<Mountain> getGyeonggiMountainList(Mountain mountain,Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		List<Mountain>  gyeonggiMountain = new ArrayList<Mountain>();	
		
		String sql = "select M_NAME from mountain where M_LOC like '경기%'";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, mountain.getmName());
			rset = pstm.executeQuery();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return gyeonggiMountain;
	}
	

	private Mountain convertRowToMountain(ResultSet rset) throws SQLException {
		Mountain mountain = new Mountain();
		mountain.setmHeight(rset.getString("m_height"));
		mountain.setmInfo(rset.getString("m_info"));
		mountain.setmLoc(rset.getString("m_loc"));
		mountain.setmName(rset.getString("m_name"));
		mountain.setxCoor(rset.getString("x_coor"));
		mountain.setyCoor(rset.getString("y_coor"));
		return mountain;
	}

}	