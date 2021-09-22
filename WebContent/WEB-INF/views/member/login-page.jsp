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
    
    <section class="login-form">
    <img src="대지 1.png">
    <h1 style="color:#008840;">로그인 해주세요.</h1>

    <form action="member/login">
        <div class="int-area">
            <input type="text" id="userId" name="userId" required placeholder="아이디를 입력하세요.">
        </div>
       <p> 
           <div class="int-area">
            <input type="password" id="password" name="password" required placeholder="비밀번호를 입력하세요.">
        </P>
    
        <class class="caption"></class>
        <a href="#">회원가입</a>
        <a href="#">아이디찾기</a>     
        <a href="#">비밀번호 찾기</a>
   
        <div></div>
`       <br>
       
            <button type="submit">로그인</button>
       
        

    </br>
    <br>
        <a href=""><img src="kakao_login_large_wide.png" width="300px"></a>
        </br>

    </div>
        </section>
    </form>



</section>
    
    




</body>
</html>