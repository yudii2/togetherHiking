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
								<th style="width: 60%">제목</th>
								<th style="width: 12%">작성자</th>
								<th style="width: 12%">작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty boardList}">
								<c:forEach items="${boardList}" var="board" varStatus="status">
									<c:if test="${status.count < 11 }">
										<tr style="height: 30px; line-height: 30px;">
											<td>${board.bdIdx}</td>
											<td><a href="/board/board-page?searchTag=subject&keyword=${board.subject }">${board.subject}</a></td>
											<td><a href="/board/board-detail?searchTag=title&keyword=${board.title }">${board.title}</a></td>
											<td><a href="/board/board-page?searchTag=userId&keyword=${board.userId }">${board.userId}</a></td>
											<td>${board.regDate}</td>
										</tr>
									</c:if>
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
						<select class='search_subject' name='searchTag'>
							<option value='none' selected disabled hidden="true">검색조건</option>
							<option value='subject'>말머리</option>
							<option value='title'>제목</option>
							<option value='userId'>유저명</option>
						</select>
						<input type="text" name="keyword" placeholder="검색어를 입력하세요."/>
						<button type="button" onclick="search();">검색</button>
					</div>

					<!-- 페이징 처리 코드 도움 필요 -->
					<div class='todo-list' style="text-align: center;">
						<p>
							<i class="fas fa-angle-double-left" id='leftArrow' data-dir='-1'></i>
							<span id='currentPage'>1</span>
							<i class="fas fa-angle-double-right" id='rightArrow' data-dir='1'></i>
						</p>
					</div>
					
					
				</div>
				
			</div>
		</div>
		
	</div>
  </section>

<script type="text/javascript">
let renderBoard = (boardList)=>{
	// 이미 그려졌던 div를 초기화
	document.querySelectorAll('tbody>tr').forEach(e=>{
		e.remove();
	});
	
	// 배열의 값을 div에 다시 그려주기
	boardList.forEach(board=>{
		let tr = document.createElement('tr'); // CSS 스타일 적용을 위해 div 사용
		tr.style.height= 30px;
		tr.style.lineHeight= 30px;
		
		let td = document.createElement('td');
		td.innerHTML = ${board.bdIdx};
		tr.append(td);
		
		td.innerHTML = ${board.subject};
		tr.append(td);
		
		td.innerHTML = ${board.title};
		});
		tr.append(td);
		
		td.innerHTML = ${board.userId};
		});
		tr.append(td);

		td.innerHTML = ${board.regDate};
		tr.append(td);
		
	});
	
}

let renderPagination = (event)=>{
	let dir = Number(event.target.dataset.dir);
	let curPage = Number(document.querySelector('#currentPage').textContent);
	let lastPage = 1;
	let renderPage = curPage + dir;
	
	let boardList = ${boardList};
	
	if(boardList != null){
		let boardCnt = boardList.size;
		lastPage = Math.ceil(boardCnt/10); // ceil: 올림처리
	}
	
	if(renderPage > lastPage){
		alert('마지막 페이지입니다.');
		return;
	}
	
	if(renderPage < 1){
		alert('첫 페이지입니다.');
		return;
	}
	
	let end = renderPage * 10;
	let begin = end - 10;
	
	renderBoard(boardList.slice(begin,end));
	document.querySelector('#currentPage').textContent = renderPage;
}

(()=>{
	let boardList = ${boardList};
	
	if(boardList){
		renderBoard(boardList.slice(0,10));
	}
	
})();
</script>

</body>
</html>