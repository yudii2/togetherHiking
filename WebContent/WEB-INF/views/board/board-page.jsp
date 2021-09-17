<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/board/board-page.css">
<script type="text/javascript" src="/resources/js/board/board-page.js"></script>
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
						<a href="/board/board-page">목록</a>
						<a href="/board/board-form">글쓰기</a>
					</div>
				</div>
				
				<div class="row">
					<table class="table" style="border: 1px solid #dddddd">
						<thead>
							<tr style="height: 30px">
								<th style="width: 8%">글번호</th>
								<th style="width: 8%">카테고리</th>
								<th style="width: 68%">제목</th>
								<th style="width: 8%">작성자</th>
								<th style="width: 8%">작성일</th>
							</tr>
						</thead>
						<tbody>
							<%-- <c:if test="${not empty board}" == true> --%>
								<%-- <c:forEach items="${board}" var="board"> --%>
									<tr style="height: 30px; line-height: 30px;">
										<td>글번호${board.bdIdx}</td>
										<td><a href="/board/board-page?subject=${board.subject }">말머리${board.subject}</a></td>
										<td><a href="/board/board-detail?boardIdx=${board.bdIdx }">게시글 제목${board.title}</a></td>
										<td><a href="/board/board-page?nickName=${board.userName }">유저명${board.userId}</a></td>
										<td>날짜${board.regDate}</td>
									</tr>
								<%-- </c:forEach> --%>
							<%-- </c:if> --%>
						</tbody>
					</table>
				</div>
				
				<div class="footer">
					<div class="search_bar_wrap">
						<select class='search_subject' name='subject'>
							<option value='none' selected disabled hidden>말머리</option>
							<option value='subject'>말머리</option>
							<option value='title'>제목</option>
							<option value='nickName'>닉네임</option>
						</select>
						<input name="word" type="text" placeholder="검색어를 입력하세요."/>
						<button>검색</button>
					</div>
					<div class="selectPage_area">
						<i class="fas fa-angle-double-left" data-dir="-1"></i>
						<a id="currentPage">1</a>
						<a>2</a>
						<a>3</a>
						<a>4</a>
						<i class="fas fa-angle-double-right" data-dir="1"></i>
					</div>
				</div>
			</div>
		</div>
		
	</div>
  </section>

</body>
</html>