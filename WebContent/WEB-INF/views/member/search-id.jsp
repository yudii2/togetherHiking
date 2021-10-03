<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/member/search-id.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>

<section class="container con_searchId">
	<form action="/member/match-id" method= "post">
		<img src="/resources/img/대지 1.png">
		<h1 style="color: #008840;">아이디 찾기</h1>
		<h3>아래 정보를 입력하시면 아이디 찾을 수 있습니다.</h3>
			<!-- 컨트롤러에서 파라미터(겟파라미터("이메일"))로 이메일 값받아와서 서비스단으로 넘겨->
			서비스는 다오에게 멤버 조회하라고 명령 -> 컨트롤러에서 리퀘스트객체에 멤버 저장해  -> 
			제이에스피에서 멤버 아이디 뿌려주면 됨  -->
		
		<div class="int-area">
			<input type="email"  id="email" name="email" required placeholder="이메일을 입력하세요..">
		</div>
	
		<button type="submit">확인</button>
	</form>
</section>

</body>
</html>