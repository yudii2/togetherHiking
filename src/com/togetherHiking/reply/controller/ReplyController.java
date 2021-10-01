package com.togetherHiking.reply.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.togetherHiking.common.exception.PageNotFoundException;
import com.togetherHiking.member.model.dto.Member;
import com.togetherHiking.reply.model.dto.Reply;
import com.togetherHiking.reply.model.service.ReplyService;

/**
 * Servlet implementation class ReplyController
 */
@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyService replyService = new ReplyService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length-1]) {
		case "add-reply":
			addReply(request,response);
			break;
		case "delete-reply":
			deleteReply(request,response);
			break;
		default: 
			throw new PageNotFoundException();
		}
	}
	
	private void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		String bdIdx = request.getParameter("bd_idx");
		String content = request.getParameter("content");
		
		Reply reply = new Reply();
		reply.setBdIdx(bdIdx);
		reply.setUserId(member.getUserId());
		reply.setContent(content);
		reply.setNickname(member.getNickname());
		
		replyService.insertReply(reply);
		
		response.sendRedirect("/board/board-detail?bd_idx=" + bdIdx);
	}
	
	private void deleteReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rpIdx = request.getParameter("rp_idx");
		String bdIdx = request.getParameter("bd_idx");
		
		replyService.deleteReply(rpIdx);
		
		response.sendRedirect("/board/board-detail?bd_idx=" + bdIdx);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
