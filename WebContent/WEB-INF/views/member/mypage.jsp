<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/member/mypage.css">
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
          </li>
          <li><a href="/member/mypage/my-schedule" class="tit_mypage_gnb">신청내역 관리</a></li>
        </ul>
      </div>
      <div class="wrap_my_contents">
        <div class="profile">
          <!-- 비동기통신으로 받아오기 필요 -->
          <div class="profile_img">
          <!-- 사용자 아이디와 일치하는 typeIdx가 존재하면 file_info에서 꺼내 출력 -->
          <!-- 존재하지 않으면 기본 프로필이미지 출력 -->
          <c:if test="${not empty profile}">
          	<img id="target_img" src="http://localhost:7070/file/${profile.savePath}${profile.renameFileName}">
          </c:if>
          <c:if test="${empty profile}">
            <img id="target_img" src="/resources/img/user.png">
          </c:if>
          </div>
          <form action="/member/profile-upload" name="profile" method="POST" enctype="multipart/form-data" >
            <input type="file" id="file" name="file" style="display: none;" onchange="changeValue(this)">
            <input type="hidden" name="target_url">	<!-- 보이지않지만 서버로 submit발생 -->
          </form>

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
            <c:forEach items="${datas.myPosts}" var="myPost">
	            <tr class="contents">
	              <td><input type="checkbox"><span>${myPost.idx}94</span></td>
	              <td>${myPost.title}계양산 등산 가실 분??</td>
	              <td>${myPost.regDate}2021.09.01</td>
	              <td>${myPost.views}10</td>
	            </tr>
			</c:forEach>
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


   <script>
     $('#target_img').click(function (e) {
    	 document.profile.target_url.value = document.getElementById( 'target_img' ).src;
         e.preventDefault();
<<<<<<< HEAD
         console.dir(document.profile.target_url.value);
         
       	 $('#file').click();	//changeValue메서드 호출
=======
         console.dir(document.profile.target_url);
       	document.profile.target_url.value = `http://localhost:7070/upload/${profile.savePath}${profile.renameFileName}`;
       $('#file').click();
>>>>>>> branch 'main' of https://github.com/yudii2/togetherHiking.git
     });

     let changeValue = function(obj) {
    	 document.profile.submit();
         console.dir(document.profile.target_url.value);

     }
   </script>

</body>
</html>