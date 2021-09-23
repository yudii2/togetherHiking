<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/member/search-password.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
<section class="container con_searchPw">
	<form action="#">
		<img src="/resources/img/대지 1.png">
		<h1 style="color: #008840;">비밀번호 찾기</h1>
		<h3>아래 정보를 입력하시면 임시 비밀번호를 메일로 발송해 드립니다.</h3>
		
		
		<div class="int-area">
			<input type="text" required placeholder="이름을 입력하세요.">
		</div>
		
		<p>
		<div class="int-area">
			<input type="email" required placeholder="이메일을 입력하세요..">
		</div>
		
		
		<div class="int-area">
			<input type="text" required placeholder="아이디를 입력하세요.">
		</div>
		
		<br>
		<button type="submit">확인</button>
	</form>	
</section>
</body>
</html>