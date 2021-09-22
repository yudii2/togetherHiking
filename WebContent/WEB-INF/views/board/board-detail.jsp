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
						<c:if test="${authentication.userId == datas.board.userId }">
						<a class="btn" href="/board/board-form?">수정하기</a>
						</c:if>
						<%-- 
						<c:if test="">
							<a class="btn" href="#">이전글</a>
						</c:if>
						<c:if test="#">
							<span onclick="alert('이전글이 없습니다.');"><div></div></span>
						</c:if>
						
						<c:if test="">
							<a class="btn" href="#">다음글</a>
						</c:if>
						<c:if test="#">
							<span onclick="alert('다음글이 없습니다.');"><div></div></span>
						</c:if>
						 --%>
						<a class="btn" href="/board/board-page">목록</a>
					</div>
				</div>
				<%-- 해당 게시글이 존재하지 않을 경우 --%>
				<c:if test="${empty datas }">
				<script type="text/javascript">
					alert("해당 게시글이 존재하지 않습니다.");
					(()=>{
						location.href="/board/board-page";
					})(); 
				</script>
				</c:if>
				
				<%-- 게시글이 존재할 경우 datas 속성에 담겨져서 넘어온다. --%>
				<c:set var="board" value="${datas.board }"/>
				<c:set var="fileDTOs" value="${datas.fileDTOs }"/>
				<c:set var="profile" value="${datas.profile }"/>
				
				<div class="section_content_box">
					<div class="section_header">
						<div class="section_title">
							<div>${board.subject}</div>
							<p>${board.title }</p>
						</div>
						<div class="writer_info">
							<div class="writer_thumb">
								<img alt="프로필이미지" src="${profile}">
							</div>
							<div class="writer_profile">
								<div>작성자: ${board.userId }</div>
								<div>작성일: ${board.regDate }</div>
							</div>
							<div class="view_cnt">
								<div>
									<div>조회수: <%-- ${board.viewCnt} --%></div>
								</div>
								<div>
									<c:if test="${not empty files}">
									<div>첨부파일: 
									<c:forEach items="${fileDTOs }" var="file" varStatus="status">
										<a href="${file.downloadURL }" style="color: blue;">${file.originFileName }</a>
										<c:if test="${!status.last }">/</c:if>
									</c:forEach>
									</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="section_container">
						<div class="content_area">${board.content }</div>
						
						<div class="comment_area">
							<div class="line_btns">
								<span>${board.replyCnt }개의 댓글</span>
								<span>등록순</span>
								<span>최신순</span>
							</div>
							
							<%-- 댓글이 존재할 경우 --%>
							<c:if test="${not empty datas.replyList}">
							<c:forEach items="${datas.replyList}" var="reply">
								<div class="cmt_wrap">
									<div class="cmt_info">
										<div>${reply.userId }</div>
										<div>${reply.regDate }</div>
									</div>
									<div>${reply.content }</div>
								</div>
							</c:forEach>
							</c:if>
							
						</div>
						
						<%-- 로그인했을 경우에만 댓글을 쓸 수 있어야함 --%>
						<form class="frm_write_cmt">
							<div class="cmt_write_box">
								<c:if test="${not empty authentication.userId }">
									<div class="cmt_writer_id">${authentication.userId }</div>
									<textarea placeholder="댓글을 입력하세요"></textarea>
									<button class="cmt_write_btn">등록</button>
								</c:if>
								<c:if test="${empty authentication.userId }">
									<div>로그인해야 댓글을 작성할 수 있습니다.</div>
								</c:if>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</div>
  </section>

</body>
</html>