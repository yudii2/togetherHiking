package com.togetherHiking.member.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.member.model.service.MemberService;

public class ModifyForm {
	
	private String password;
	private String newPw;		//modify : 새 비밀번호
	private String confirmPw;	//modify : 새 비밀번호 확인
	private String nickname;
	private String info;
	private HttpServletRequest request;	
	private MemberService memberService = new MemberService();
	private Map<String,String> failedAttrubute =  new HashMap<String,String>();
	
	public ModifyForm(ServletRequest request) {
		this.request = (HttpServletRequest) request;
		this.password = request.getParameter("password");
		this.nickname = request.getParameter("nickname");
		this.info = request.getParameter("info");
		this.newPw = request.getParameter("new_pw");
		this.confirmPw = request.getParameter("new_pw_confirm");
	}
	
	//마이페이지 회원정보 수정 validating
	public boolean test() {
		boolean isFailed = false;

		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();

		//현재세션객체에 담긴 userId로 받아온 member의 비밀번호와 동일한지 확인
		if(password.equals("") || !password.equals(memberService.selectMemberById(userId).getPassword())) {
			failedAttrubute.put("password",password);
			isFailed = true;
		}
		
		//비밀번호가 영어, 숫자, 특수문자 조합의 8~15자의 문자열인지 확인
		if(!Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,15}", newPw)) {
			failedAttrubute.put("newPw",newPw);
			isFailed = true;
		}
		
		//새 비밀번호와 일치하는지 확인
		if(!confirmPw.equals(newPw)) {
			failedAttrubute.put("confirmPw",confirmPw);
			isFailed = true;
		}
		
		// **** 닉네임 검증
		//null이면 기존 닉네임 대입(authentication속성 만들어진 후 테스트할 것)
		if(nickname.equals("") || nickname == null) {
			nickname = member.getNickname();
		}
		
		//기존 세션객체 닉네임과 동일하지 않으면서 다른 유저닉네임과 중복된다면 fail
		if(!nickname.equals(member.getNickname()) && memberService.selectByNickname(nickname) != null) {
			failedAttrubute.put("nickname",nickname);
			isFailed = true;
		}
		
		
		if(isFailed) {
			request.getSession().setAttribute("modifyValid", failedAttrubute);	//joinFailed에 검증실패한 값 저장
			request.getSession().setAttribute("modifyForm", this);	//사용자 입력 파라미터값 재사용 위함
			return false;
		}else {
			request.getSession().removeAttribute("modifyForm");
			request.getSession().removeAttribute("modifyValid");
			return true;
		}
	}

	public String getPassword() {
		return password;
	}

	public String getNewPw() {
		return newPw;
	}

	public String getConfirmPw() {
		return confirmPw;
	}

	public String getNickname() {
		return nickname;
	}

	public String getInfo() {
		return info;
	}


	
	
}