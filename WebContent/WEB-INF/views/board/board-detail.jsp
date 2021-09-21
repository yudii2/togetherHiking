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
    <div class="container">
		
		<div class='section'>
			<div class='section_wrap'>
				<div class="section_top">
					<h2>게시글 상세페이지</h2>
					<div class='btn_area'>
						<%-- 
						<c:if test="">
							<a class="btn" href="/board/board-detail?bd_idx=">이전글</a>
						</c:if>
						<c:if test="${param.p <= 1 }">
							<span onclick="alert('이전글이 없습니다.');"><div></div></span>
						</c:if>
						
						<c:if test="">
							<a class="btn" href="/board/board-detail?bd_idx=">다음글</a>
						</c:if>
						<c:if test="${param.p >= ${lastNum } }">
							<span onclick="alert('다음글이 없습니다.');"><div></div></span>
						</c:if>
						 --%>
						<a class="btn" href="/board/board-page">목록</a>
					</div>
				</div>
				<div class="section_content_box">
					<div class="section_header">
						<div class="section_title">
							<div>${board.subject}</div>
							<p>${board.title }</p>
						</div>
						<div class="writer_info">
							<div class="writer_thumb">사진</div>
							<div class="writer_profile">
								<div>${board.userId }</div>
								<div>${board.regDate }</div>
							</div>
							<div class="view_cnt">
								<div>
									<div>조회수</div>
									<div>111<%-- ${board.count} --%></div>
								</div>
								<div>
									<div>첨부파일</div>
									<div>1111
									<c:if test="${datas.files != null }">
										<c:forEach items="${datas.files }" var="file">
										<a class="" href="${file.downloadURL }">${file.originFileName }</a>
										</c:forEach>
									</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="section_container">
						<div class="content_area">${board.content }</div>
						
						<div class="comment_area">
							<div class="line_btns">
								<span>댓글</span>
								<span>등록순</span>
								<span>최신순</span>
							</div>
							
							<c:if test="${not empty comment}">
								<c:forEach items="${comment}" var="comment">
									<div class="cmt_wrap">
										<div class="cmt_box">
											<div class="cmt_thumb">프로필사진${comment.profile }</div>
												<div class="cmt_info">
													<div>id${comment.userId }</div>
													<div>${comment.content }</div>
												</div>
											</div>
										<div class="cmt_date">${comment.regDate }</div>
									</div>
								</c:forEach>
							</c:if>
							
						</div>
						<form class="frm_write_cmt">
							<div class="cmt_write_box">
								<div class="cmt_writer_id">아이디${authentication.userId }</div>
								<textarea placeholder="댓글을 입력하세요"></textarea>
							</div>
							<button class="cmt_write_btn">등록</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</div>
  </section>

</body>
</html>