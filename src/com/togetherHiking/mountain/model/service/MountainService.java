package com.togetherHiking.mountain.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.mountain.model.dao.MountainDao;
import com.togetherHiking.mountain.model.dto.Mountain;


public class MountainService {
 
	private static MountainDao mountainDao = new MountainDao();
	private static JDBCTemplate template = JDBCTemplate.getInstance();
		
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
	
	public List<Mountain> getSeoulMountainList() {
		Connection conn = template.getConnection();
		Mountain mountain = new Mountain();
		List<Mountain> seoulMountain = null;
		
		try {
			seoulMountain = mountainDao.getSeoulMountainList(mountain,conn);
		} finally {
			template.close(conn);
		}
		
		return seoulMountain;
	}
	
	public List<Mountain> getGyeonggiMountainName() {
		Connection conn = template.getConnection();
		Mountain mountain = new Mountain();
		List<Mountain>gyeonggiMountain = null;
		
		try {
			gyeonggiMountain = mountainDao.getGyeonggiMountainList(mountain,conn);
		} finally {
			template.close(conn);
		}
		
		return gyeonggiMountain;
	}

	
//	public Mountain mountainButtonPage(int page) {
//		Connection conn  = template.getConnection();
//		Mountain mountainButton = new Mountain();
//			
//		try {
//			mountainButton = mountainDao.mountainButtonPage(page, conn);
//		} finally {
//			template.close(conn);	
//		}
//			
//		return mountainButton;
//	}
//
//	
	
	
	
	
}
