package com.togetherHiking.member.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


public class UpdatePwdForm {
	private String password;
	private String confirmPw;
	private HttpServletRequest request;		
	private Map<String,String> failedAttrubute =  new HashMap<String,String>();
	
	public UpdatePwdForm(ServletRequest request) {
		this.request = (HttpServletRequest) request;
		this.password = request.getParameter("reset1");
		this.confirmPw = request.getParameter("reset2");
	}
	
	public boolean test() {
		boolean isFailed = false;
		
				
		if(!Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,15}", password)) {
			failedAttrubute.put("password",password); 
			isFailed = true;
		}
		
		if(!confirmPw.equals(password) ) {
			failedAttrubute.put("confirmPw",confirmPw); 
			isFailed = true;
		}
		
		if(isFailed) {
			request.getSession().setAttribute("updatePwdValid", failedAttrubute);	
			request.getSession().setAttribute("updatePwdForm", this);	
			return false;
		}else {
			request.getSession().removeAttribute("updatePwdValid");
			request.getSession().removeAttribute("updatePwdForm");
			return true;
		}
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPw() {
		return confirmPw;
	}

	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}

	
	
	
	

}
