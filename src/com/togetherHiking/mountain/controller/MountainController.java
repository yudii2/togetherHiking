package com.togetherHiking.mountain.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.togetherHiking.mountain.model.dto.Mountain;
import com.togetherHiking.mountain.model.service.MountainService;

/**
 * Servlet implementation class MountainController
 */
@WebServlet("/mountain/*")
public class MountainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MountainService mountainService = new MountainService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MountainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length-1]) {
		case "search":
			search(request,response);
			break;
		case "detail":
			detail(request,response);
			break;
		case "course":
			course(request,response);
			break;
		default:/* throw new PageNotFoundException(); */
		}
	}

	

	private void course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		의논 필요! 분리하는게 팝업창 띄우기에 적합하지 않은 느낌! mountain-detial jsp파일 내에서 자바스크립트 작동으로 팝업을 띄우므로 자체를 분리할 수 없음
//		비동기 통신으로 뿌려주는 것도 괜찮을 듯?
		
		request.getRequestDispatcher("/mountain/mountain-course").forward(request, response);
		
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		String inputmName = request.getParameter("mName");
		
		//쿼리값 전달 안됐으면 공백, 됐으면 그 쿼리 사용하겠다.
		String mName = "";
		if(inputmName != null)
			mName = inputmName;
				
		Mountain mountainInfo = mountainService.getMountainInfo(mName);
		
		request.setAttribute("mountainInfo", mountainInfo);
		
		request.getRequestDispatcher("/mountain/mountain-detail").forward(request, response);
		
		
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mountain/mountain-search").forward(request, response);
		
	}


		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}
