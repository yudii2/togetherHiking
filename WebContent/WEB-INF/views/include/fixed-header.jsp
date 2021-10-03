<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
  <header>
    <div class="container con_header">
      <a class="logo" href="/index"><img src="/resources/img/산행동행로고.gif" alt=""></a>
      <ul class="gnb">
        <li><a href="/index" class="gnb_menu" data-gnb="1">HOME</a></li>
        <li><a href="/mountain/search-seoul" class="gnb_menu" data-gnb="2">등산로검색</a></li>
        <li><a href="/schedule/calendar" class="gnb_menu" data-gnb="3">모임캘린더</a></li>
        <li><a href="/board/board-page" class="gnb_menu" data-gnb="4">자유게시판</a></li>
        <li><a href="/admin/home" class="gnb_menu" data-gnb="5">관리자페이지</a></li>
      </ul>
      <div class="user">
      <c:if test="${empty authentication}">
        <a href="/member/login-page" class="login">로그인</a>
        <a href="/member/join-page" class="join">회원가입</a>
      </c:if>
      <c:if test="${not empty authentication and not empty authentication.profile}">     
      	<div onclick="location.href='/member/mypage'" class="header_profile">
	      <img id="target_img" src="http://localhost:7070/file/${authentication.profile}">
	    </div>
	  </c:if>
	  <c:if test="${not empty authentication and empty authentication.profile}">
      	<div onclick="location.href='/member/mypage'" class="header_profile">	  
	        <img id="target_img" src="/resources/img/user.png">
	    </div>    
	  </c:if>


      </div>

    </div>
    <div class="tab_mypage">
      	<em></em>
		<h1 class="tab_user">${authentication.nickname}<a href="/member/logout">LOGOUT</a></h1>
		<div><a href="/member/modify-page">내 정보</a></div>
		<div><a href="/member/mypage">작성글 관리</a></div>
		<div><a href="/member/my-schedule">신청내역 관리</a></div>
    </div>
    <script type="text/javascript">
    
    document.querySelector('.header_profile').addEventListener('mouseover', () => {
    	document.querySelector('.tab_mypage').style.display = 'block';
    	setTimeout(() => {
    		document.querySelector('.tab_mypage').style.display = 'none';
		}, 3000);
    	
    })

    
    
    </script>
    
  </header>



