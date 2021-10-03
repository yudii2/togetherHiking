package com.togetherHiking.mountain.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.togetherHiking.board.model.dto.Board;
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
		case "search-seoul":
			seoul(request,response);
			break;
		case "search-gyeonggi":
			gyeonggi(request,response);
			break;
		case "detail":
			detail(request,response);
			break;		
		default:/* throw new PageNotFoundException(); */
		}
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

	private void seoul(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int spage = 1;	//parameter가 null일 경우를 대비해 초기값 1로 선언
		
		String seoulparamPage = request.getParameter("sp");
			
		if(seoulparamPage != null && !seoulparamPage.equals("")) {
			spage = Integer.parseInt(seoulparamPage);
		}
		
		List<Mountain> seoulMountain = mountainService.getSeoulMountainList(spage);
				
		request.setAttribute("seoulMountain", seoulMountain);
		
		request.getRequestDispatcher("/mountain/mountain-search-seoul").forward(request, response);
		
	}
	
	private void gyeonggi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gpage = 1;	//parameter가 null일 경우를 대비해 초기값 1로 선언
		
		String gyeonggiparamPage = request.getParameter("gp");
		
		if(gyeonggiparamPage != null && !gyeonggiparamPage.equals("")) {
			gpage = Integer.parseInt(gyeonggiparamPage);
		}
		
		List<Mountain> gyeonggiMountain = mountainService.getGyeonggiMountainList(gpage);
		
		request.setAttribute("gyeonggiMountain", gyeonggiMountain);
		
		request.setAttribute("currPage", gyeonggiparamPage==null?1:gyeonggiparamPage);
		request.setAttribute("lastPage", 11);
		
		request.getRequestDispatcher("/mountain/mountain-search-gyeonggi").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}
