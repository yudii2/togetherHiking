<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
							<c:if test="${not empty boardList}">
								<c:forEach items="${boardList}" var="board">
									<tr style="height: 30px; line-height: 30px;">
										<td>글번호${board.bdIdx}</td>
										<td><a href="/board/board-page?subject=${board.subject }">말머리${board.subject}</a></td>
										<td><a href="/board/board-detail?boardIdx=${board.bdIdx }">게시글 제목${board.title}</a></td>
										<td><a href="/board/board-page?nickName=${board.userName }">유저명${board.userId}</a></td>
										<td>날짜${board.regDate}</td>
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
					<div class="search_bar_wrap">
						<select class='search_subject' name='searchOption'>
							<option value='none' selected disabled hidden="true">검색조건</option>
							<option value='subject'>말머리</option>
							<option value='title'>제목</option>
							<option value='nickName'>닉네임</option>
						</select>
						<input name="keyword" type="text" placeholder="검색어를 입력하세요."/>
						<button type="button" onclick="search();">검색</button>
					</div>

					<!-- 페이징 처리 코드 도움 필요 -->
					<div class="pager">
						<ul>
							<c:if test="${ curPageNum > 5 && !empty kwd }">
								<li><a href="/board/board-page?page=${ blockStartNum - 1 }&kwd=${ kwd }">◀</a></li>
							</c:if>
	
							<c:if test="${ curPageNum > 5 }">
								<li><a href="/board/board-page?page=${ blockStartNum - 1 }">◀</a></li>
							</c:if>
	
							<c:forEach var="i" begin="${ blockStartNum }" end="${ blockLastNum }">
								<c:choose>
									<c:when test="${ i > lastPageNum }">
										<li>${ i }</li>
									</c:when>
									<c:when test="${ i == curPageNum }">
										<li class="selected">${ i }</li>
									</c:when>
									<c:when test="${ !empty kwd}">
										<li><a href="/board/board-page?a=search&page=${ i }&kwd=${ kwd }">${ i }</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/board/board-page?page=${ i }">${ i }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
	
							<c:if test="${ lastPageNum > blockLastNum && !empty kwd }">
								<li><a href="/board/board-page?a=search&page=${ blockLastNum + 1 }&kwd=${ kwd }">▶</a></li>
							</c:if>
	
							<c:if test="${ lastPageNum > blockLastNum }">
								<li><a href="/board/board-page?page=${ blockLastNum + 1 }">▶</a></li>
							</c:if>
						</ul>
					</div>
					
					
				</div>
			</div>
		</div>
		
	</div>
  </section>

<script type="text/javascript">
	let search = function(){
		location.href="/board/board-page?keyword=";
	};
</script>
</body>
</html>