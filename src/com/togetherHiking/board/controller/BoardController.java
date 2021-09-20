package com.togetherHiking.board.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.board.model.service.BoardService;
import com.togetherHiking.common.exception.PageNotFoundException;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
       
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
		String bdIdx = request.getParameter("bdIdx");
		
		Board board = boardService.selectBoardByBdIdx(bdIdx);
		
		if(board == null) {
			throw new PageNotFoundException();
		}
		request.setAttribute("board", board);
		request.getRequestDispatcher("/board/board-detail").forward(request, response);
		
	}

	private void boardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/board-form").forward(request, response);
		
	}

	private void boardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		
		String field = "title";
		if(field_ != null) {
			field = field_;
		}
		
		String query = "";
		if(query_ != null) {
			query = query_;
		}
		
		
		
		List<Board> boardList = new ArrayList<Board>();
		boardList = boardService.selectBoardList(field,query);
		int lastNum = boardList.size();
		
		request.setAttribute("lastNum", lastNum);
		request.setAttribute("boardList", boardList);
		
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
