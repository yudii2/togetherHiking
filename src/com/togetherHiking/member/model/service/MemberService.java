package com.togetherHiking.member.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.member.model.dao.MemberDao;
import com.togetherHiking.member.model.dto.Member;

public class MemberService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();
	
	public void insertProfile(Member member, FileDTO profile) {
		Connection conn = template.getConnection();
		
		try {
			memberDao.insertProfile(profile, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
		}finally {
			template.close(conn);
		}
		
	}
	
	public Map<String, FileDTO> selectProfile(String userId) {
		Connection conn = template.getConnection();
		Map<String, FileDTO> res = new HashMap<String, FileDTO>();
		
		try {
			FileDTO profile = memberDao.selectProfile(userId, conn);
			
			res.put("profile", profile);	
		}finally {
			template.close(conn);	//세션 만료
		}
		return res;
	}

	public Object selectMemberById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
