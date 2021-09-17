<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/board/board-detail.css">
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>

  <section>
    <div class="container con_sec01">
		
		<div class='section'>
			<div class='section_wrap'>
				<div class="section_top">
					<h2>게시글 상세페이지</h2>
					<div class='btn_area'>
						<a href="#">이전글</a>
						<a href="#">다음글</a>
						<a href="/board/board-page">목록</a>
					</div>
				</div>
				<div class="section_content_box">
					<div class="section_header">
						<div class="section_title">
							<div class="category_btn">
								<a href="/board/board-page?search-subject=${board.category}">말머리</a>
							</div>
							<h3>글제목${board.title }</h3>
						</div>
						<div class="writer_info">
							<div class="writer_thumb">사진</div>
							<div class="writer_profile">
								<div><span>작성자${board.userId }</span><span>회원등급${board.grade }</span></div>
								<div>${board.regDate }작성일</div>
							</div>
							<div class="comment_number">
								댓글수
							</div>
						</div>
					</div>
					<div class="section_container">
						<div class="content_area">
							게시글 내용${board.content }
						</div>
						<div class="comment_area">
							<div class="line_btns">
								<span>댓글</span>
								<span>등록순</span>
								<span>최신순</span>
							</div>
							
							<%-- <c:if test="${not empty comment}" == ture> --%>
								<c:forEach items="${comment}" var="comment">
									<div class="comment_wrap">
										<div class="commenter_box">
											<div class="commenter_thumb"><!-- 프로필사진 -->${comment.profile }</div>
												<div class="commenter">
													<div><!-- 작성자 -->id${comment.userId }</div>
													<div><!-- 내용 -->${comment.content }</div>
												</div>
											</div>
										<div class="comment_date"><!-- 댓글 등록일 -->${comment.regDate }</div>
									</div>
								</c:forEach>
							<%-- </c:if> --%>
							
						</div>
						<div class="writing_comment">
							<div class="comment_write_box">
								<div><!-- 댓글작성자id --> ${authentication.userId }</div>
								<textarea placeholder="댓글을 입력하세요"></textarea>
							</div>
							<div class="comment_btn">
								<button>등록</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
  </section>

</body>
</html>