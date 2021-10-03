package com.togetherHiking.member.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.togetherHiking.member.model.service.MemberService;

public class KakaoJoinForm {
	

	private String nickname;
	private String info;
	private String birth;
	private HttpServletRequest request;	
	private MemberService memberService = new MemberService();
	private Map<String,String> failedAttrubute =  new HashMap<String,String>();
	
	public KakaoJoinForm(ServletRequest request) {
		this.request = (HttpServletRequest) request;
		this.nickname = request.getParameter("nickname");
		this.info = request.getParameter("information");
		this.birth = request.getParameter("birth");
	}
	
	public boolean test() {
		boolean isFailed = false;
		
		//닉네임 검증 : 사용자가 입력한 닉네임이 DB에 이미 존재하는지 확인 (위 아이디 검증과 유사)
		if(nickname.equals("") || memberService.selectMemberById(nickname) != null) {
			failedAttrubute.put("nickname",nickname);
			isFailed = true;
		}
		
		
		if(isFailed) {
			request.getSession().setAttribute("kakaojoinValid", failedAttrubute);	//joinFailed에 검증실패한 값 저장
			request.getSession().setAttribute("kakaojoinForm", this);	//사용자 입력 파라미터값 재사용 위함
			return false;
		}else {
			request.getSession().removeAttribute("kakaojoinForm");
			request.getSession().removeAttribute("kakaojoinValid");
			return true;
		}
	}
	

	public String getNickname() {
		return nickname;
	}

	public String getInfo() {
		return info;
	}

	public String getBirth() {
		return birth;
	}	

}
