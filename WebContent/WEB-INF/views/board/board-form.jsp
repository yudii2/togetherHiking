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
<script src="//cdn.ckeditor.com/4.16.2/basic/ckeditor.js"></script>

  <section>
    <div class="container">
	
		<div class='section'>
			<form class='frm_post' action="/board/upload" method="post" enctype="multipart/form-data">
				<div class='frm_header_area'>
					<h2 class='sub_title'>게시판 글쓰기</h2>
					<div class='btn_area'>
						<a class="btn" href="/board/board-page">목록</a>
						<button class="btn" type="submit">등록</button>
					</div>
				</div>
			
				<div class='frm_title_area'>
					<div>
						<select class='select_subject' name='subject'>
							<option selected value='chat'>잡담</option>
							<option value='review'>후기</option>
						</select>
						<input type='text' class="post_title" name="title" required="required" placeholder='제목을 입력하세요'>
					</div>
				</div>
				
				<div class='frm_content_area'>
					<textarea name="content" required="required" placeholder="내용을 입력하세요"></textarea>
					<script type="text/javascript">
					 	CKEDITOR.replace("content");
					 	CKEDITOR.config.removePlugins = 'resize';
					 	CKEDITOR.config.width = '100%';
					 	CKEDITOR.config.height = '250';
					</script>
				</div>
				<div class="add_file_box">파일 : <input type="file" name="files" id="contract_file" multiple/></div>
			</form>
		</div>
	
	</div>
  </section>

</body>
</html>