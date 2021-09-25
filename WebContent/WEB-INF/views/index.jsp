<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

</head>
<body>
	<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
	
	<c:if test= "${empty authentication}">
	<h2><a href='/member/login-page'>login</a></h2>
	<h2><a href='/member/join-page'>회원가입</a></h2>
	</c:if>
	
	<c:if test= "${not empty authentication}">
		<h2><a href='/member/loginout'>logout</a></h2>
	<h2><a href='/member/mypage'>마이페이지</a></h2>
	</c:if>
</body>
</html>
