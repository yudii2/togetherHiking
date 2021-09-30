<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/member/my-reply.css">
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
            <h2 class="cnt">내 게시글 수 <span>${authentication.postCnt} 개</span></h2>
            <h2 class="cnt">내 댓글 수 <span>${authentication.replyCnt} 개</span></h2>
            <span class="info">${authentication.info }</span>
          </div>
        </div>
        <ul class="tabs">
          <li id="tab_post"><a href="/member/mypage">내가 쓴 글</a></li>
          <li id="tab_reply"><a href="/member/my-reply">내가 쓴 댓글</a></li>
        </ul>
        <div class="my_posts">
          <div class="col_my_posts">
            <span class="title">댓글</span>
            <span class="date">작성일</span>
            <span class="views">답글</span>
          </div>
        <form action="/member/delete-reply" method="post" name="deleteReply">             
          <table>
          <!-- board패키지 접근 필요 -->
          <c:if test="${empty myReply.reply}">
			<tr class="contents">
              <td></td>
              <td>작성하신 댓글이 존재하지 않습니다.</td>
              <td></td>
              <td></td>
            </tr>          	
          </c:if>
          <c:if test="${not empty myReply.reply}">
            <c:forEach items="${myReply.reply}" var="myReply">
	            <tr class="contents" id="myReply">
	              <td><input type="checkbox" class="checkbox" name="chk" value="${myReply.rpIdx}"><span>${myReply.rpIdx}</span></td>
	              <td>${myReply.content}</td>
	              <td>${myReply.regDate}</td>
	              <td>${myReply.codeIdx}</td>
	            </tr>
			</c:forEach>
          </c:if>
          </table>
          <div class="btns">
            <div class="select_all">
              <input type="checkbox" id="selectAll">
              <label for="select_all">전체선택</label>
            </div>
            <div class="btn">
              <button id="btn_del">삭제하기</button>
            </div>
          </div>
		</form>        
        </div>
      </div>
    </div>
  </section>

<script type="text/javascript" src="/resources/js/member/profileUpdate.js"></script>
<script type="text/javascript" src="/resources/js/member/my-reply.js"></script>

</body>
</html>