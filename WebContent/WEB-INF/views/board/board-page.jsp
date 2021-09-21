<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/board/board-page.css">
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>

  <section>
    <div class="container">
    
		<div class='section'>
			<div class='wrapper'>
				<div class="sub_title">
					<h2>자유게시판</h2>
					<div class='btn_area'>
						<a href="/board/board-page" class="btn">목록</a>
						<a href="/board/board-form" class="btn">글쓰기</a>
					</div>
				</div>
				
				<div class="row">
					<table class="table" style="border: 1px solid #dddddd">
						<thead>
							<tr style="height: 30px">
								<th style="width: 8%">글번호</th>
								<th style="width: 8%">카테고리</th>
								<th style="width: 60%">제목</th>
								<th style="width: 12%">작성자</th>
								<th style="width: 12%">작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty boardList}">
								<c:forEach items="${boardList}" var="board">
								<tr style="height: 30px; line-height: 30px;">
									<td>${board.bdIdx}</td>
									<td>${board.subject}</td>
									<td><a href="/board/board-detail?bdIdx=${board.bdIdx }">${board.title}</a></td>
									<td>${board.userId}</td>
									<td>${board.regDate}</td>
								</tr>
								</c:forEach>
							</c:if>
							
							<c:if test="${empty boardList }">
							<tr style="height: 30px; line-height: 30px;">
								<td/>
								<td/>
								<td rowspan="3" style="text-align: center;">
									게시글이 없습니다.
								</td>
							</tr>
							</c:if>
							
						</tbody>
					</table>
				</div>
				
				<div class="footer">
					<form class="search_bar_wrap">
						<select class='search_subject' name='f'>
							<option ${(param.f == "title")? "selected" : "" } value='title'>제목</option>
							<option ${(param.f == "user_id")? "selected" : "" } value='user_id'>작성자</option>
						</select>
						<input type="text" name="q" value="${param.q }" placeholder="검색어를 입력하세요."/>
						<button type="button" onclick="submit">검색</button>
					</form>

					<!-- 페이징 처리 코드 도움 필요 -->
					<c:set var="page" value="${(param.p == null)? 1 : param.p}"/>
					<c:set var="startNum" value="${page - (page-1)%5 }"/>
					<c:set var="lastNum" value="${lastNum }"/>
					
					<div class="paging_box">
						<c:if test="${startNum > 1 }">
							<a href="/board/board-page?p=${startNum-1 }&f=&q="><i class="far fa-caret-square-left"></i></a>
						</c:if>
						<c:if test="${startNum <= 1 }">
							<span onclick="alert('이전 페이지가 없습니다.');"><i class="far fa-caret-square-left"></i></span>
						</c:if>
						
						<ul>
							<c:forEach var="i" begin="0" end="4">
							<li><a href="/board/board-page?p=${startNum+i }&f=&q=">${startNum+i }</a></li>
							</c:forEach>
						</ul>
						
						<c:if test="${startNum+5 < lastNum }">
							<a href="/board/board-page?p=${startNum+5 }&f=&q="><i class="far fa-caret-square-right"></i></a>
						</c:if>
						<c:if test="${startNum+5 >= lastNum }">
							<span onclick="alert('다음 페이지가 없습니다.');"><i class="far fa-caret-square-right"></i></span>
						</c:if>
					</div>
					
					
				</div>
				
			</div>
		</div>
		
	</div>
  </section>


<!-- <script type="text/javascript" src="/resources/js/board/board-page.js"></script> -->
</body>
</html>