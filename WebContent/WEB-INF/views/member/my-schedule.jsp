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
          <li><a href="/member/mypage/modify-page" class="tit_mypage_gnb">내정보</a>
            <a href="/member/mypage/modify-page" class="tit_sub_gnb">내정보 수정하기</a>
          </li>
          <li>
          	<a href="/member/mypage" class="tit_mypage_gnb">작성글 관리</a>
          </li>
          <li><a href="/member/mypage/my-schedule" class="tit_mypage_gnb">신청내역 관리</a></li>
        </ul>
      </div>
      <div class="wrap_my_contents">
        <div class="profile">
          <!-- 비동기통신으로 받아오기 필요 -->
          <div class="profile_img"></div>
          <div class="profile_desc">
            <h1 class="nickname">마운틴러너</h1>
            <h1 class="post_cnt">내 게시글 수<span class="comment_cnt">내 댓글 수</span></h1>
            <span class="info">등산, 여행, 맛집여행 동행해요~</span>
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
            <div class="post"></div>
            <div class="post"></div>
            <div class="post"></div>
          </div>
        </div>
      </div>
    </div>
  </section>
</body>