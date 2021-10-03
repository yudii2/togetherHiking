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
	<form action="/member/checkpassword">
		<img src="/resources/img/대지 1.png">
		<h1 style="color: #008840;">새로운 비밀번호 설정</h1>
		<h3>아래 정보를 입력하시면 새로운 비밀번호를 발급 받을 수 있습니다.</h3>
		
		<br>
		
		
		<div class="int-area">
			<input type="password"  id="reset1" name="reset1" required placeholder="새로운 비밀번호를 입력하세요.">
		</div>
		
		<p>
		<div class="int-area">
			<input type="password" id="reset2" name="reset2" required placeholder="새로운 비밀번호 동일하게 입력하세요.">
		</div>
		
		
	
		
		<br>
		<button type="submit">확인</button>
	</form>	
</section>
</body>
</html>