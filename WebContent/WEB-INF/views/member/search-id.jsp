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

		<img src="/resources/img/대지 1.png">
		<h1 style="color: #008840;">아이디 찾기</h1>
		<h3>아래 정보를 입력하시면 아이디를 메일로 발송해 드립니다.</h3>

		<div class="int-area">
			<input type="text" required placeholder="이름을 입력하세요.">
		</div>

		<p>
		<div class="int-area">
			<input type="email" required placeholder="이메일을 입력하세요..">
		</div>

		<button type="submit">확인</button>



</body>
</html>