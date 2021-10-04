<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/member/my-schedule.css">
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
  <section>
    <div class="container con_mypage">
      <div class="wrap_mypage_side_menu">
        <div class="tit_side_menu">마이페이지</div>
        <ul class="mypage_side_menu">
          <li>
          	<a href="/member/mypage/modify-page" class="tit_mypage_gnb">내정보</a>
            <a href="/member/mypage/modify-page" class="tit_sub_gnb">내정보 수정하기</a>
          </li>
          <li>
          	<a href="/member/mypage" class="tit_mypage_gnb">작성글 관리</a>
          	<a href="/member/mypage" class="tit_sub_gnb">내가 쓴 글 보기</a>
          	<a href="/member/mypage/reply" class="tit_sub_gnb">내가 쓴 댓글 보기</a>
          </li>
          <li><a href="/member/mypage/my-schedule" class="tit_mypage_gnb">신청내역 관리</a></li>
        </ul>
      </div>
      <div class="wrap_my_contents">
        <div class="profile">
          <div class="profile_img">
		  <c:if test="${not empty authentication and not empty authentication.profile}">
	      	<img id="target_img" src="http://localhost:7070/file/${authentication.profile}">
	      </c:if>
	      <c:if test="${not empty authentication and empty authentication.profile}">
	        <img id="target_img" src="/resources/img/user.png">
	      </c:if>
          </div>
          <form action="/member/profile-upload" name="profile" method="POST" enctype="multipart/form-data" >
            <input type="file" id="file" name="file" style="display: none;" onchange="changeValue(this)">
            <input type="hidden" name="target_url">	<!-- 보이지않지만 서버로 submit발생 -->
          </form>

          <div class="profile_desc">
            <h1 class="nickname">${authentication.nickname}</h1>
            <h2 class="cnt" >내 게시글 수 <span id="postCnt">${fn:length(myPosts)}</span> 개</h2>
            <h2 class="cnt">내 댓글 수 <span>${fn:length(myReply.reply)}</span> 개</h2>
            <span class="info">${authentication.info }</span>
          </div>
        </div>
        <div class="wrap_tab">
          <span class="tab_my_schedule">내 모임</span>
        </div>
        <div class="my_posts">
          <select name="schedule_filter" id="filter">
          	<option value="">전체</option>
          	<option value="ing">예정</option>
          	<option value="end">완료</option>
          </select>
          <div class="wrap_post">

          <c:set var="today" value="<%=new java.util.Date()%>"/>
          <fmt:formatDate var="now" type="date" value="${today}" pattern="yyyy.MM.dd"/>
          
          <c:if test="${empty mySchedule}">
          	<div class="nonSchedule">새로운 모임 일정을 등록해 주세요</div>        
          </c:if>
          <c:if test="${not empty mySchedule}">
          	<c:forEach items="${mySchedule}" var="schedule">
	            <div class="post">
	            	<div class="tit_schedule"><h1>${schedule.mountainName}</h1></div>
	            	<div class="date">${schedule.dDay }</div>
	            	<div class="hiking_info">
	            		<span class="heigth">해발 : ${schedule.mHeight} m</span>
	            		<c:if test="${schedule.status == 0}">	
	            			<span class="status">대기중</span>	            		
	            		</c:if>
	            		<c:if test="${schedule.status == 1}">
	            			<span class="status" style="background-color: #ff8080">승인</span>	            		
	            		</c:if>
	            	</div>
	            	<c:if test="${schedule.dDay < today}">
	   		         	<div class="end">등반완료!</div>
	            	</c:if>
	            </div>          	
          	</c:forEach>          	
          </c:if>


          </div>
        </div>
      </div>
    </div>
  </section>
</body>