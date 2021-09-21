package com.togetherHiking.common.wrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	
	public RequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	public String[] getRequestURIArray() {
		String uri = this.getRequestURI();
		String[] uriArr = uri.split("/");
		return uriArr;
	}
	
	@Override
	public RequestDispatcher getRequestDispatcher(String uri) {
		return request.getRequestDispatcher("/WEB-INF/views/" + uri + ".jsp");
	}
	
	public RequestDispatcher getRequestDispatcher(String uri, String prefix, String suffix) {
		return request.getRequestDispatcher(prefix + uri + suffix);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
