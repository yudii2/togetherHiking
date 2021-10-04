package com.togetherHiking.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.togetherHiking.common.http.HttpConnector;
import com.togetherHiking.common.http.RequestParams;
import com.togetherHiking.common.mail.MailSender;
import com.togetherHiking.board.model.dao.BoardDao;
import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.common.db.JDBCTemplate;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.member.model.dao.MemberDao;
import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.schedule.model.dto.Schedule;
import com.togetherHiking.reply.model.dao.ReplyDao;
import com.togetherHiking.reply.model.dto.Reply;


public class MemberService {
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();

	//사용자가 프로필 이미지 등록시 info테이블에 등록 및 멤버 객체 profile 속성 set
	//유진 09/29
	public void insertProfile(String userId, FileDTO profile) {
		Connection conn = template.getConnection();
		try {
			Member member = new Member();
			memberDao.insertProfile(userId, profile, conn);
			member.setProfile(selectProfile(userId));
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
			Member member = new Member();
			res = memberDao.updateProfile(userId,fileDTO, conn);	//file_info테이블에 프로필 업데이트 
			if(res > 0) {
				member.setProfile(selectProfile(userId));				//새로운 프로필 조회해 멤버 객체에 set
			}
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
		}finally {
			template.close(conn);
		}
		return res;
	}
	//유진 09/30 
	//아이디 중복확인용
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
	
	public Member selectMemberByEmail(String email) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.selectMemberByEmail(email, conn);
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
	//유진 09/30
	public List<Schedule> selectMySchedule(String userId) {
		Connection conn  = template.getConnection();
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		
		try {
			scheduleList = memberDao.selectMySchedule(userId,conn);
			for (Schedule schedule : scheduleList) {
				schedule.setmHeight(memberDao.selectMountain(schedule.getMountainName(),conn));
			}
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
	
	
	public List<Reply> selectReplyByPage(String userId, int page) {
		Connection conn  = template.getConnection();
		List<Reply> replyList = new ArrayList<Reply>();
		
		try {
			replyList = memberDao.selectReplyByPage(userId, page, conn);
		} finally {
			template.close(conn);	
		}
		
		return replyList;
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


	public int updateMember(Member member, String password) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			if(password == null || password.equals("")) {
				password = member.getPassword();
			}
			res = memberDao.updateMember(member,password, conn);
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
	//유진 09/29
	public Member memberAuthenticate(String userId, String password) {
		Connection conn = template.getConnection();
		Member member = null;
		try {
			member = memberDao.memberAuthenticate(userId, password, conn);
			if(member != null) {
				member.setReplyCnt(countMyReply(userId));
				member.setPostCnt(countMyPost(userId));
				member.setProfile(selectProfile(userId));				
			}

		}finally {
			template.close(conn);
		}
		return member;
	}
	
	//유진 09/30 멤버 세부정보 조회(프로필 포함)
	public Member getMemberDetail(Member member) {
		Connection conn = template.getConnection();
		Member memberDetail = null;
		
		try {
			memberDetail = memberDao.selectMemberById(member.getUserId(), conn);
			memberDetail.setReplyCnt(countMyReply(member.getUserId()));
			memberDetail.setPostCnt(countMyPost(member.getUserId()));
			memberDetail.setProfile(selectProfile(member.getUserId()));
			
		}finally {
			template.close(conn);
		}
		return memberDetail;
	}
	
	public void authenticateEmail(Member member, String persistToken) {
		
	      HttpConnector conn = new HttpConnector();
	      
	      Map<String,String> params = new HashMap<String, String>();
	      params.put("mail-template", "mail-template");
	      params.put("persistToken", persistToken);
	      params.put("userId", member.getUserId());

	      String queryString = conn.urlEncodedForm(params);
	      
	      String mailTemplate = conn.get("http://localhost:7070/mail?" + queryString);
	      MailSender sender = new MailSender();
	      sender.sendEmail(member.getEmail(), "환영합니다" + member.getUserId() + "님", mailTemplate);

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


	//유진 10/01
	public void deletePost(String bdIdx) {
		Connection conn = template.getConnection();
		BoardDao boardDao = new BoardDao();
		
		try {
			boardDao.deleteBoard(conn, bdIdx);		
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
		}finally {
			template.close(conn);
		}
	}

	public void deleteReply(String replyIdx) {
		Connection conn = template.getConnection();
		ReplyDao replyDao = new ReplyDao();
		
		try {
			replyDao.deleteReply(conn, replyIdx);		
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
		}finally {
			template.close(conn);
		}
	}

	public int insertkakaoMember(Member kakaomember) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			memberDao.insertkakaoMember(kakaomember, conn);
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
	
	public Member selectMemberBySearching(String userId, String email) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.selectMemberBySearching(userId, email, conn);
		} finally {
			template.close(conn);
		}
		return member;
	}




		
	
}
