package com.togetherHiking.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.togetherHiking.board.model.dto.Board;
import com.togetherHiking.board.model.dto.BoardView;
import com.togetherHiking.board.model.dto.Reply;
import com.togetherHiking.board.model.service.BoardService;
import com.togetherHiking.common.file.FileDTO;
import com.togetherHiking.common.file.FileUtil;
import com.togetherHiking.common.file.MultiPartParams;
import com.togetherHiking.member.model.dto.Member;

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
		case "addReply":
			addReply(request,response);
			break;
		case "delete-board":
			delete(request,response);
			break;
		case "delete-reply":
			deleteReply(request,response);
			break;
		
		default:/* throw new PageNotFoundException(); */
		
		}
	}

	private void deleteReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rpIdx = request.getParameter("rp_idx");
		String bdIdx = request.getParameter("bd_idx");
		
		boardService.deleteReply(rpIdx);
		
		response.sendRedirect("/board/board-detail?bd_idx=" + bdIdx);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bdIdx = request.getParameter("bd_idx");
		boardService.deleteBoard(bdIdx);
		
		response.sendRedirect("/board/board-page");
	}

	private void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = ((Member) request.getSession().getAttribute("authentication")).getUserId();
		String bdIdx = request.getParameter("bd_idx");
		String content = request.getParameter("content");
		
		Reply reply = new Reply();
		reply.setBdIdx(bdIdx);
		reply.setUserId(userId);
		reply.setContent(content);
		
		boardService.insertReply(reply);
		
		response.sendRedirect("/board/board-detail?bd_idx=" + bdIdx);
	}

	private void boardDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bdIdx = request.getParameter("bd_idx");
		Map<String,Object> datas = boardService.getBoardDetail(bdIdx);
		
		request.setAttribute("datas", datas);
		request.getRequestDispatcher("/board/board-detail").forward(request, response);
	}

	private void boardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/board-form").forward(request, response);
		
	}

	private void boardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_ != null && !field_.equals("")) {
			field = field_;
		}
		
		String query = "";
		if(query_ != null && !query_.equals("")) {
			query = query_;
		}
		
		int page = 1;
		if(page_ != null && !page_.equals("")) {
			page = Integer.parseInt(page_);
		}
		
		List<BoardView> boardList = new ArrayList<BoardView>();
		boardList = boardService.getBoardList(field, query, page);
		int count = boardService.getBoardCount(field, query);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/board/board-page").forward(request, response);
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post메서드로 받아온 사용자 작성게시글 정보 받아와
		String userId = ((Member) request.getSession().getAttribute("authentication")).getUserId();
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);

		Board board = new Board();
		board.setUserId(userId);
		board.setTitle(params.getParameter("title"));
		board.setSubject(params.getParameter("subject"));
		board.setContent(params.getParameter("content"));
		
		List<FileDTO> fileDTOs = params.getFilesInfo();
		int res = boardService.insertBoard(board,fileDTOs);
		
		if(res <= 0) {
			System.out.println("업로드 중 에러 발생");
		}
		if(res > 0) {
			System.out.println("업로드 성공");
		}
		
		response.sendRedirect("/board/board-detail?bd_idx=" + board.getBdIdx());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
