<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/member/login-page.css" />
</head>
<body> 
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
    
<section class="container login-form">
	<div class="wrap_login_form">
		<img src="/resources/img/대지 1.png">
	    <h1 style="color:#008840;">산행동행 로그인 해주세요.</h1>
	    <c:if test="${not empty param.err}">
	    	<span class='valid-msg'>아이디나 비밀번호를 잘 못 입력하였습니다.</span>
	    	   </c:if>
	
	    <form action="/member/login" method="post">
	        <div class="int-area">
	            <input type="text" id="userId" name="userId" required placeholder="아이디를 입력하세요.">
	        </div>	
	        <div class="int-area">
	           <input type="password" id="password" name="password" required placeholder="비밀번호를 입력하세요.">
			</div>
	    	<br>
	        <div class="caption">
		        <a href="/member/join-page">회원가입　</a>
		        <a href="/member/search-id">아이디찾기　</a>     
		        <a href="/member/search-password">비밀번호 찾기</a>
	   		</div>
	   		<br>
	       
	        <button type="submit">로그인</button>
	       
	        <a href="/member/login-kakao"><img src="/resources/img/kakao_login_large_wide.png" width="300px"></a>


   		</form>

	</div>

</section>

</body>
</html>