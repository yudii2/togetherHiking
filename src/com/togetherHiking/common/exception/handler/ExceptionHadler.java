package com.togetherHiking.common.exception.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.togetherHiking.common.exception.HandleableException;

/**
 * Servlet implementation class ExceptionHadler
 */
@WebServlet("/exception-handler")
public class ExceptionHadler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExceptionHadler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//서블릿 컨테이너는 HandleableExceptipon이 발생하면 요청을 ExceptionHandler으로 재지정
		//이때 ExceptionHandler 서비스 메서드로 넘겨주는 request의 속성(javax.servlet.error.exception)에 
		//발생한 예외 객체를 함께 넘겨준다.
		HandleableException e = (HandleableException) request.getAttribute("javax.servlet.error.exception");
		request.setAttribute("msg", e.error.MESSAGE);
		request.setAttribute("url", e.error.URL);
		request.getRequestDispatcher("/WEB-INF/views/common/result.jsp").forward(request, response);
	}

	/**deal with the law
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
