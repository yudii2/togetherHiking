<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/member/check-id.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>

<section class="container check_id">
	<div class="wrap_match_id">
	    <img src="/resources/img/대지 1.png">
	    <h1 style="color:#008840;">아이디 찾기</h1>
	
	    <span>고객님의 정보와 일차하는 아이디 목록 입니다.</span>
		<!-- 작업해야됌 -->
		<form>  
			<div class="match_id">
				<h2>kh1234</h2>
			</div>
		
		    <button onclick = "location.href = '#'" type="submit" >로그인하기</button>  
		    <button onclick = "location.href = '#'" type="submit">비밀번호찾기</button>  
	    </form>
	
	</div>
</section>

</body>
</html>