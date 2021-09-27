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
          <!-- 비동기통신으로 받아오기 필요 -->
          <div class="profile_img">
          <!-- 사용자 아이디와 일치하는 typeIdx가 존재하면 file_info에서 꺼내 출력 -->
          <!-- 존재하지 않으면 기본 프로필이미지 출력 -->
          <c:if test="${not empty authentication}">
          	<img id="target_img" src="http://localhost:7070/file/${profile.savePath}${profile.renameFileName}">
          </c:if>
          <c:if test="${empty authentication}">
            <img id="target_img" src="/resources/img/user.png">
          </c:if>
          </div>
          <form action="/member/profile-upload" name="profile" method="POST" enctype="multipart/form-data" >
            <input type="file" id="file" name="file" style="display: none;" onchange="changeValue(this)">
            <input type="hidden" name="target_url">	<!-- 보이지않지만 서버로 submit발생 -->
          </form>

          <div class="profile_desc">
            <h1 class="nickname">${authentication.nickname}</h1>
            <h2 class="cnt">내 게시글 수 <span>${postCnt} 개</span></h2>
            <h2 class="cnt">내 댓글 수 <span>${myReply.length} 개</span></h2>
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
          <table>
          <!-- board패키지 접근 필요 -->
          <c:if test="${empty myReply}">
			<tr class="contents">
              <td></td>
              <td>작성하신 댓글이 존재하지 않습니다.</td>
              <td></td>
              <td></td>
            </tr>          	
          </c:if>
          <c:if test="${not empty myReply}">
            <c:forEach items="${myReply.reply}" var="myReply">
	            <tr class="contents" id="myReply">
	              <td><input type="checkbox" id="checkbox"><span><c:out value = '${myReply.rpIdx}'/></span></td>
	              <td>${myReply.content}</td>
	              <td>${myReply.regDate}</td>
	              <td>${myReply.codeIdx}</td>
	            </tr>
			</c:forEach>
          </c:if>
          </table>
          <div class="btns">
            <div class="select_all">
              <input type="checkbox" id="select_all">
              <label for="select_all">전체선택</label>
            </div>
            <div class="btn">
              <button id="btn_del">삭제하기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>


   <script>
   
	
   //비동기 통신으로 댓글리스트 뿌려주기
   
   document.querySelector('#tab_reply').addEventListener('click',(e)=>{
	   //e.preventDefault();
	   
	   //console.dir(e.target);
	   
	   document.querySelector('.title').innerHTML = '댓글';
	   document.querySelector('.views').innerHTML = '답글';
	   document.querySelector('#tab_post').style.border = '1px solid #bbb'
	   document.querySelector('#tab_post').style.backgroundColor = '#fff';
	   document.querySelector('#tab_post>a').style.color = '#555555';

	   document.querySelector('#tab_reply').style.backgroundColor = 'var(--point-color)';
	   document.querySelector('#tab_reply').style.border = 'none';
	   document.querySelector('#tab_reply').style.color = '#fff';

   });
   
   
   
   document.querySelector('#target_img').addEventListener('click', function (e) {
    	 document.profile.target_url.value = document.getElementById( 'target_img' ).src;
         e.preventDefault();
       	 $('#file').click();	//changeValue메서드 호출
     });

     let changeValue = function(obj) {
    	 document.profile.submit();

     }
   </script>

</body>
</html>