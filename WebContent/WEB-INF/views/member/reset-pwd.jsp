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
	<form action="/member/update-pwd"  method="post">
		<img src="/resources/img/대지 1.png">
		<h1 style="color: #008840;">새로운 비밀번호 설정</h1>
		<h3>아래 정보를 입력하시면 새로운 비밀번호를 발급 받을 수 있습니다.</h3>
		
		<br>
		
		<input type="hidden" name="userId" value="${member.userId}">
		<div class="int-area">
			<input type="password"  id="reset1" name="reset1" placeholder="새로운 비밀번호를 입력하세요."
			<c:if test="${not empty param.err and empty updatePwdValid.password}">
          		value = ${updatePwdForm.password}
          	</c:if>			
			required/>

		</div>
	    
        <c:if test="${not empty param.err and not empty updatePwdValid.password}">
          	<em id="alert_new_pw" class="alert_auth">
          		영문,숫자,특수문자 포함 8~15자를 입력하세요.
          	</em>
        </c:if>          
        
		
		<p>
		<div class="int-area">
			<input type="password" id="reset2" name="reset2" placeholder="새로운 비밀번호 동일하게 입력하세요."
			<c:if test="${not empty param.err and empty updatePwdValid.confirmPw}">
          		value = ${updatePwdForm.confirmPw}
          	</c:if>				
			required/>
		</div>
		
       	<c:if test="${not empty param.err and not empty updatePwdValid.confirmPw}">
       		<em id="alert_pw" class="alert_auth">
       			현재 비밀번호와 일치하지 않습니다.
       		</em>
       	</c:if>
       	
		
	
		
		<br>
		<button type="submit">확인</button>		
	</form>	
</section>

</body>
</html>