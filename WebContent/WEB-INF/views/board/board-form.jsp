<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/board/board-form.css">
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
<script src="https://cdn.ckeditor.com/4.16.2/standard/ckeditor.js"></script>

  <section>
    <div class="container">
	
		<div class='section'>
			<form class='frm_post'>
				<div class='frm_header_area'>
					<h2 class='sub_title'>게시판 글쓰기</h2>
					<div class='btn_area'>
						<a href="/board/board-page">목록</a>
						<button type="button" onclick="location.href='#'">등록</button>
					</div>
				</div>
			
				<div class='frm_title_area'>
					<div>
						<select class='select_subject' name='post_subject'>
							<option value='none' selected disabled hidden>분류</option>
							<option value='잡담'>잡담</option>
							<option value='후기'>후기</option>
						</select>
						<input type='text' class="post_title" name="title" placeholder='제목을 입력하세요'>
					</div>
				</div>
				
				<div class='frm_content_area'>
					<textarea name="content" placeholder="내용을 입력하세요"></textarea>
					<script type="text/javascript">
					 	CKEDITOR.replace("content");
					 	CKEDITOR.config.removePlugins = 'resize';
					 	CKEDITOR.config.width = '100%';
					 	CKEDITOR.config.height = '495';
					</script>
				</div>
			</form>
		</div>
	
	</div>
  </section>

</body>
</html>