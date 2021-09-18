package com.togetherHiking.board.controller;

import java.io.IOException;
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
		Board board = boardService.selectBoard(bdIdx);
		
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
		//List<Board> boardList = boardService.selectBoardList();
//		int boardCnt = 0;
//		
//		if(boardList != null) {
//			boardCnt = boardList.size();
//			request.setAttribute("boardList", boardList);
//		}
//		request.setAttribute("boardCnt", boardCnt);
		
		//String keyword = request.getParameter("keyword");
		
//		if(request.getParameter("searchOption").val != null) {
//			boardList = boardService.selectBoardListBySubject(request.getParameter("subject"));
//		}else if(request.getParameter("title") != null) {
//			boardList = boardService.selectBoardListByTitle(request.getParameter("title"));
//		}else if(request.getParameter("nickName") != null) {
//			boardList = boardService.selectBoardListByNickName(request.getParameter("nickName"));
//		}
		
		
		
		
		Paging paging = new Paging();
		int blockStartNum = paging.getBlockStartNum();
		int blockLastNum = paging.getBlockLastNum();
		int lastPageNum = paging.getLastPageNum();
		
		request.setAttribute("blockStartNum", blockStartNum);
		request.setAttribute("blockLastNum", blockLastNum);
		request.setAttribute("lastPageNum", lastPageNum);
		
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
	
	// 페이징 이너클래스
	public class Paging {
		
		private final static int pageCount = 5;
	    private int blockStartNum = 0;
	    private int blockLastNum = 0;
	    private int lastPageNum = 0;

	    public int getBlockStartNum() {
	        return blockStartNum;
	    }
	    public void setBlockStartNum(int blockStartNum) {
	        this.blockStartNum = blockStartNum;
	    }
	    public int getBlockLastNum() {
	        return blockLastNum;
	    }
	    public void setBlockLastNum(int blockLastNum) {
	        this.blockLastNum = blockLastNum;
	    }
	    public int getLastPageNum() {
	        return lastPageNum;
	    }
	    public void setLastPageNum(int lastPageNum) {
	        this.lastPageNum = lastPageNum;
	    }

	    // block을 생성
	    // 현재 페이지가 속한 block의 시작 번호, 끝 번호를 계산
	    public void makeBlock(int curPage){
	        int blockNum = 0;

	        blockNum = (int)Math.floor((curPage-1)/ pageCount);
	        blockStartNum = (pageCount * blockNum) + 1;
	        blockLastNum = blockStartNum + (pageCount-1);
	    }

	    // 총 페이지의 마지막 번호
	    public void makeLastPageNum() {
	        BoardService boardService = new BoardService();
	        int total = boardService.getCount();

	        if( total % pageCount == 0 ) {
	            lastPageNum = (int)Math.floor(total/pageCount);
	        }
	        else {
	            lastPageNum = (int)Math.floor(total/pageCount) + 1;
	        }
	    }

	    // 검색을 했을 때 총 페이지의 마지막 번호
	    public void makeLastPageNum(String kwd) {
	    	BoardService boardService = new BoardService();
	        int total = boardService.getCount(kwd);

	        if( total % pageCount == 0 ) {
	            lastPageNum = (int)Math.floor(total/pageCount);
	        }
	        else {
	            lastPageNum = (int)Math.floor(total/pageCount) + 1;
	        }
	    }
		
	}
}
