<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/member/mypage.css">
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
            <h2 class="cnt">내 게시글 수 ${postCnt} 개</h2>
            <h2 class="cnt">내 댓글 수 ${commentCnt} 개</h2>
            <span class="info">등산, 여행, 맛집여행 동행해요~</span>
          </div>
        </div>
        <ul class="tabs">
          <li id="tab_post">내가 쓴 글</li>
          <li id="tab_comment">내가 쓴 댓글</li>
        </ul>
        <div class="my_posts">
          <div class="col_my_posts">
            <span class="title">제목</span>
            <span class="date">작성일</span>
            <span class="views">조회</span>
          </div>
          <table>
            <tr class="contents">
              <td><input type="checkbox"><span>94</span></td>
              <td>계양산 등산 가실 분??</td>
              <td>2021.09.01</td>
              <td>10</td>
            </tr>
          </table>
          <div class="btns">
            <div class="select_all">
              <input type="checkbox" id="select_all">
              <label for="select_all">전체선택</label>
            </div>
            <div class="btn">
              <button id="btn_edit">수정하기</button>
              <button id="btn_del">삭제하기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</body>
</html>