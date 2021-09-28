<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/board/board-form.css">
<script src="//cdn.ckeditor.com/4.16.2/basic/ckeditor.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>

  <section>
    <div class="container">
	
		<div class='section'>
			<form class='frm_post' action="/board/upload" method="post" enctype="multipart/form-data">
				<div class='frm_header_area'>
					<h2 class='sub_title'>게시판 글쓰기</h2>
					<div class='btn_area'>
						<a class="btn" href="/board/board-page">목록</a>
						<button id="btnSubmit" class="btn">등록</button>
					</div>
				</div>
			
				<div class='frm_title_area'>
					<div>
						<select class='select_subject' name='subject'>
							<option selected value='잡담'>잡담</option>
							<option value='후기'>후기</option>
						</select>
						<input type='text' class="post_title" name="title" required="required" placeholder='제목을 입력하세요'>
					</div>
				</div>
				
				<div class='frm_content_area'>
					<input type="hidden" name="content" id="content">
					<textarea name="contentbox" required="required" placeholder="내용을 입력하세요"></textarea>
					<script type="text/javascript">
					 	CKEDITOR.replace("contentbox",{
					 		removePlugins: "resize",
					 		width: "100%",
					 		height: "250"
					 	});
					</script>
					<script type="text/javascript"> /* 에러발생중 */
						document.querySelector("#btnSubmit").addEventListener("click",()=>{
							document.querySelector("#content").innerHTML = CKEDITOR.instances.content.getData();
						});
					</script>
				</div>
				<div class="add_file_box">파일 : <input type="file" name="files" id="contract_file" multiple/></div>
			</form>
		</div>
	
	</div>
  </section>
</body>
</html>