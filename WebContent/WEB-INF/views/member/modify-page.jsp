<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/member/modify-page.css">
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
      <form action="/member/modify" class="modify_form">
        <div class="name">
          <span class="tit_form">이름</span>
          <input type="text" name="name" value="${userName}">
        </div>
        <div class="user_id">
          <span class="tit_form">아이디</span>
          <input type="text" name="user_id" value="${userId}">
        </div>
        <div class="self-intro">
          <span class="tit_form">자기소개</span>
          <textarea rows="5" minlength="15" maxlength="50" >${info}</textarea>
        </div>
        <div class="password">
          <span class="tit_form">비밀번호</span>
          <label for="password" class="tit_pw_label">현재 비밀번호</label>
          <input type="password" name="password" id="password">
          <label for="new_pw" class="tit_pw_label tit_new_pw">새 비밀번호</label>
          <input type="password" name="new_pw" id="new_pw">
          <label for="new_pw_confirm" class="tit_pw_label tit_new_pw">새 비밀번호 확인</label>
          <input type="password" name="new_pw_confirm" id="new_pw_confirm">
        </div>
        <div class="email">
          <span class="tit_form">이메일</span>
          <input type="email" name="email" id="email">
        </div>
        <button>수정완료</button>
      </form>
    </div>
  </section>
</body>
</html>