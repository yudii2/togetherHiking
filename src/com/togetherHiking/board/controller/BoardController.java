package com.togetherHiking.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length-1]) {
		case "board-page":
			  boardPage(request,response);
			break;
		//board-form: 글쓰기버튼 클릭 -> 요청되는 url
		case "board-form":
			  boardForm(request,response);
			break;
		case "board-detail":
			  boardDetail(request,response);
			break;
		case "upload":
			upload(request,response);
			break;

		default:/* throw new PageNotFoundException(); */
		
		}
	}


	private void boardDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/board-detail").forward(request, response);
		
	}

	private void boardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/board-form").forward(request, response);
		
	}

	private void boardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/board-page").forward(request, response);
		
	}


	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post메서드로 받아온 사용자 작성게시글 정보 받아와
		
		//최종적으로 detail 페이지로 redirect
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
