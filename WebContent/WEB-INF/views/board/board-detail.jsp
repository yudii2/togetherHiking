<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/resources/css/board/board-detail.css">
<script src="https://cdn.ckeditor.com/4.16.2/basic/ckeditor.js"></script>
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
						<a class="btn" href="/board/delete-board?bd_idx=${datas.board.bdIdx }">글삭제</a>
						</c:if>
						
						<c:if test="${not empty datas.nextIdx }">
							<a class="btn" href="/board/board-detail?bd_idx=${datas.nextIdx }">이전글</a>
						</c:if>
						<c:if test="${empty datas.nextIdx }">
							<a class="btn"><span class="btn" onclick="alert('이전글이 없습니다.');">이전글</span></a>
						</c:if>
						
						<c:if test="${not empty datas.prevIdx }">
							<a class="btn" href="/board/board-detail?bd_idx=${datas.prevIdx }">다음글</a>
						</c:if>
						<c:if test="${empty datas.prevIdx }">
							<a class="btn"><span onclick="alert('다음글이 없습니다.');">다음글</span></a>
						</c:if>
						
						<a class="btn" href="/board/board-page">목록</a>
					</div>
				</div>
				<%-- 해당 게시글이 존재하지 않을 경우 --%>
				<c:if test="${empty datas.board.bdIdx }">
				<script type="text/javascript">
					alert("해당 게시글이 존재하지 않습니다.");
					(()=>{
						location.href="/board/board-page";
					})(); 
				</script>
				</c:if>
				
				<%-- 게시글이 존재할 경우 datas 속성에 담겨져서 넘어온다. --%>
				<c:set var="board" value="${datas.board }"/>
				<c:set var="files" value="${datas.files }"/>
				<c:set var="replys" value="${datas.replys }"/>
				
				<div class="section_content_box">
					<div class="section_header">
						<div class="section_title">
							<div>${board.subject}</div>
							<p>${board.title }</p>
						</div>
						<div class="writer_info">
							<div class="writer_thumb">
								<img alt="프로필이미지" src="http://localhost:7070/file/${board.profileSavePath}${board.profileRenameFileName}">
							</div>
							<div class="writer_profile">
								<div>작성자: ${board.userId }</div>
								<div>작성일: ${board.regDate }</div>
							</div>
							<div class="writer_board_info">
								<div>조회수: ${board.viewCnt}</div>
								
								<ul class="dep1">
									<li>
										<c:if test="${not empty files}">
										<a>첨부파일</a>
										<ul class="dep2">
										<c:forEach items="${files }" var="file" varStatus="status">
											<li><a href="${file.downloadURL }">${file.originFileName }</a></li>
										</c:forEach>
										</ul>
										</c:if>
									</li>
								</ul>
								
							</div>
						</div>
					</div>
					<div class="section_container">
						<div class="content_area" id=editor1>
							<c:out value='${board.content }' escapeXml="false"/>
						</div>
						 <script>
							CKEDITOR.replace( 'editor1', {
								readOnly: true,
								width: '100%',
								height: 200,
								language: 'ko',
								removePlugins: 'resize',
								removeButtons: 'PasteFromWord'
							} );
						</script>
						
						<div class="comment_area">
							<div class="line_btns">
								<span>
									${(fn:length(replys) == null)? 0 : fn:length(replys) }개의 댓글
								</span>
								<span>등록순</span>
								<span>최신순</span>
							</div>
							
							<%-- 댓글이 존재할 경우 --%>
							<c:if test="${not empty replys}">
							<c:forEach items="${replys}" var="reply">
								<div class="cmt_wrap">
									<div class="cmter_picture">
										<img alt="프로필이미지" src="http://localhost:7070/file/${reply.profileSavePath}${reply.profileRenameFileName}"> 
									</div>
									<div>
										<div class="cmt_info">
											<div>${reply.userId }
												<c:if test="${authentication.userId eq reply.userId }">
												<a href="/reply/delete-reply?rp_idx=${reply.rpIdx }&bd_idx=${board.bdIdx }" style="margin-left: 10px;"><i class="fas fa-times"></i></a>
												</c:if>
											</div>
											<div>${reply.regDate }</div>
										</div>
										<div>${reply.content }</div>
									</div>
								</div>
							</c:forEach>
							</c:if>
							
						</div>
						
						<%-- 로그인했을 경우에만 댓글을 쓸 수 있어야함 --%>
						<form class="frm_write_cmt" action="/reply/add-reply?bd_idx=${board.bdIdx }" method="POST">
							<div class="cmt_write_box">
								<c:if test="${not empty authentication.userId }">
									<div class="cmt_writer_id">${authentication.userId }</div>
									<textarea name="content" placeholder="댓글을 입력하세요" required="required"></textarea>
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