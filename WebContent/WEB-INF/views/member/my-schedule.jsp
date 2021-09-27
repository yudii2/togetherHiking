<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		  <c:if test="${not empty authentication and not empty profile}">
	      	<img id="target_img" src="http://localhost:7070/file/${profile.savePath}${profile.renameFileName}">
	      </c:if>
	      <c:if test="${not empty authentication and empty profile}">
	        <img id="target_img" src="/resources/img/user.png">
	      </c:if>
          </div>
          <form action="/member/profile-upload" name="profile" method="POST" enctype="multipart/form-data" >
            <input type="file" id="file" name="file" style="display: none;" onchange="changeValue(this)">
            <input type="hidden" name="target_url">	<!-- 보이지않지만 서버로 submit발생 -->
          </form>

          <div class="profile_desc">
            <h1 class="nickname">${authentication.nickname}</h1>
            <h2 class="cnt">내 게시글 수 <span>${authentication.postCnt} 개</span></h2>
            <h2 class="cnt">내 댓글 수 <span>${authentication.replyCnt} 개</span></h2>
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
            <div class="post">
            	<div class="tit_schedule"><h1>북한산 맛등산</h1></div>
            	<div class="date">2021.09.04 토</div>
            	<div class="hiking_info">
            		<span>해발 : 630m</span>
            		<span>난이도 : 상</span>
            	</div>
            </div>
            <div class="post">
            	<div class="tit_schedule"><h1>북한산 맛등산</h1></div>
            	<div class="date">2021.09.04 토</div>
            	<div class="hiking_info">
            		<span>해발 : 630m</span>
            		<span>난이도 : 상</span>
            	</div>
            	<div class="end">등반완료!</div>
            	
            </div>
            <div class="post"></div>
            
          </div>
        </div>
      </div>
    </div>
  </section>
</body>