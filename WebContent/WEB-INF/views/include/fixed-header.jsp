<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
  <header>
    <div class="container con_header">
      <a class="logo" href="/index"><img src="/resources/img/산행동행로고.gif" alt=""></a>
      <ul class="gnb">
        <li><a href="/index" class="gnb_menu" data-gnb="1">HOME</a></li>
        <li><a href="/mountain/search" class="gnb_menu" data-gnb="2">등산로검색</a></li>
        <li><a href="/schedule/calendar" class="gnb_menu" data-gnb="3">모임캘린더</a></li>
        <li><a href="/board/board-page" class="gnb_menu" data-gnb="4">자유게시판</a></li>
      </ul>
      <div class="user">
      <c:if test="${empty authenticatio}">
        <a href="/login-page" class="login">로그인</a>
        <a href="/join-page" class="join">회원가입</a>
      </c:if>
      <c:if test="${not empty authenticatio}">
        <div class="header_profile"><img src="#"></div>
      </c:if>
      </div>
    </div>
  </header>

