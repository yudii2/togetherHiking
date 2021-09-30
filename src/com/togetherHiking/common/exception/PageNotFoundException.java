package com.togetherHiking.common.exception;

public class PageNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -930316220873109881L;

	public PageNotFoundException() {
		//stackTrace를 비워준다.
		this.setStackTrace(new StackTraceElement[0]);
		
	}

public PageNotFoundException(String message) {
	// TODO Auto-generated constructor stub
	super(message);
}

}
