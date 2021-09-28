package com.togetherHiking.mountain.model.service;

import java.sql.Connection;
import java.util.List;

import com.togetherHiking.board.model.dto.BoardView;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.mountain.model.dao.MountainDao;
import com.togetherHiking.mountain.model.dto.Mountain;


public class MountainService {
 
	private static MountainDao mountainDao = new MountainDao();
	private static JDBCTemplate template = JDBCTemplate.getInstance();
	
//	public List<Mountain> selectMountainList() {
//		Connection conn = template.getConnection();
//		List<Mountain> mountainList = null;
//		 
//		try {
//			mountainList = mountainDao.selectMountainList(conn);
//		}finally {
//			template.close(conn);
//		}
//		return mountainList;
//	}
//	
//	public static Mountain selectMountainByMountainName (String mName) {
//		Connection conn = template.getConnection();
//		Mountain mountain = null;
//		
//		try {
//			mountain = mountainDao.selectMountainByMountainName(mName, conn);
//		} finally {
//			template.close(conn);
//		}
//		
//		return mountain;
//	}
	
	public void insertMountain(Mountain mountain) {
		Connection conn = template.getConnection();
		
		try {
			mountainDao.insertMountain(mountain, conn);					
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}

	}

	public Mountain getMountainInfo(String mName) {
		Connection conn = template.getConnection();
		Mountain mountainInfo = null;
		
		try {
			mountainInfo = mountainDao.getMountainInfo(mName, conn);
		} finally {
			template.close(conn);
		}
		
		return mountainInfo;
	}
	
	
}
