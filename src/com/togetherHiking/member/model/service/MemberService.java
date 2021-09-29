package com.togetherHiking.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.member.model.dao.MemberDao;
import com.togetherHiking.member.model.dto.Member;
<<<<<<< HEAD
import com.togetherHiking.schedule.model.dto.Schedule;
=======
import com.togetherHiking.reply.model.dto.Reply;
>>>>>>> branch 'main' of https://github.com/yudii2/togetherHiking.git

public class MemberService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();
	
	//사용자가 프로필 이미지 등록시 dao호출해 info테이블에 등록하도록
	public void insertProfile(String userId, FileDTO profile) {
		Connection conn = template.getConnection();
		
		try {
			memberDao.insertProfile(userId, profile, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
		}finally {
			template.close(conn);
		}
		
	}
	
	public int insertMember(Member member) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			// 회원가입 처리
			res = memberDao.insertMember(member, conn);
			// 회원가입 이후 자동 로그인 처리
			// 방금 가입한 회원의 정보를 다시 조회
			Member m = memberDao.selectMemberById(member.getUserId(), conn);
			// 다오를 통해 사용자 정보를 받아서 해당 정보로 로그인 처리 진행
			System.out.println(member.getUserId() + "의 로그인처리 로직이 동작했습니다.");
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			// 예전처럼 예외처리
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}

	
	
	//프로필 저장경로 조회 목적 --> 프로필이미지 화면출력
	public FileDTO selectProfile(String userId) {
		Connection conn = template.getConnection();
		FileDTO profile = new FileDTO();
		
		try {
			profile = memberDao.selectProfile(userId, conn);
		}finally {
			template.close(conn);	
		}
		return profile;
	}
	
	//유진 09/29
	public int updateProfile(String userId, FileDTO fileDTO) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			res = memberDao.updateProfile(userId,fileDTO, conn);
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			e.printStackTrace();
		}finally {
			template.close(conn);
		}
		
		return res;
		
	}

	public Member selectMemberById(String userId) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.selectMemberById(userId, conn);
		} finally {
			template.close(conn);
		}
		return member;
	}

	//닉네임 중복확인용	
	public Member selectByNickname(String nickname) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.selectByNickname(nickname, conn);
		} finally {
			template.close(conn);	
		}
		
		return member;
		
	}
	
	//유진 09/29
	public List<Schedule> selectMySchedule(String userId) {
		Connection conn  = template.getConnection();
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		
		try {
			scheduleList = memberDao.selectMySchedule(userId,conn);
		} finally {
			template.close(conn);	
		}
		return scheduleList;
	}

	
	public List<Board> selectMyPostById(String userId) {
		Connection conn  = template.getConnection();
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			boardList = memberDao.selectMyPostById(userId, conn);
		} finally {
			template.close(conn);	
		}
		
		return boardList;
	}
	
	public List<Board> selectPostByPage(String userId, int page) {
		Connection conn  = template.getConnection();
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			boardList = memberDao.selectPostByPage(userId, page, conn);
		} finally {
			template.close(conn);	
		}
		
		return boardList;
	}
	
	public int countMyPost(String userId) {
		Connection conn  = template.getConnection();
		List<Board> boardList = new ArrayList<Board>();
		int cnt = 0;
		
		try {
			boardList = memberDao.selectMyPostById(userId, conn);
			for (Board board : boardList) {
				cnt ++;
			}
		} finally {
			template.close(conn);	
		}
		return cnt;
	}
	
	private int countMyReply(String userId) {
		Connection conn  = template.getConnection();
		Map<String,List> replyList = new HashMap<String, List>();
		int cnt = 0;
		
		try {
			replyList = memberDao.selectMyReply(userId, conn);
			List<Reply> myReply = replyList.get("reply");
			for (Reply reply : myReply) {
				cnt++;
			}
		} finally {
			template.close(conn);	
		}
		return cnt;
	}


	public int updateMember(Member member) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			res = memberDao.updateMember(member, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			e.printStackTrace();
		}finally {
			template.close(conn);
		}
		
		return res;
		
	}
	//로그인시 멤버객체 반환
	public Member memberAuthenticate(String userId, String password) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.memberAuthenticate(userId, password, conn);	
			member.setReplyCnt(countMyReply(userId));
			member.setPostCnt(countMyPost(userId));
			member.setProfile(selectProfile(userId));
		}finally {
			template.close(conn);
		}
		return member;
	}


	public Map<String,List> selectMyReply(String userId) {
		Connection conn  = template.getConnection();
		Map<String,List> replyList = new HashMap<String, List>();
		
		try {
			replyList = memberDao.selectMyReply(userId,conn);
		} finally {
			template.close(conn);	
		}
		
		return replyList;
	}





	
	



}
