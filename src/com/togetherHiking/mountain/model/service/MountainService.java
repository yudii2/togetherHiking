package com.togetherHiking.mountain.model.service;

import java.sql.Connection;
import java.util.List;

import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.mountain.model.dao.MountainDao;
import com.togetherHiking.mountain.model.dto.Mountain;


public class MountainService {

	private MountainDao mountainDao = new MountainDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public List<Mountain> selectMountainList() {
		Connection conn = template.getConnection();
		List<Mountain> mountainList = null;
		
		try {
			mountainList = mountainDao.selectMountainList(conn);
		}finally {
			template.close(conn);
		}
		return mountainList;
	}
	
	
	public int insertMountain(Mountain mountain) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			res = mountainDao.insertMountain(mountain, conn);						
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		
		return res;
	}
	
	
}
