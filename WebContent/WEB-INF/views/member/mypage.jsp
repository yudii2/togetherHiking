<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
          <!-- 비동기통신으로 받아오기 필요 -->
          <div class="profile_img">
          <!-- 사용자 아이디와 일치하는 typeIdx가 존재하면 file_info에서 꺼내 출력 -->
          <!-- 존재하지 않으면 기본 프로필이미지 출력 -->
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
            <h2 class="cnt" >내 게시글 수 <span id="postCnt">${fn:length(myPosts)}</span> 개</h2>
            <h2 class="cnt">내 댓글 수 <span>${fn:length(myReply.reply)}</span> 개</h2>
            <span class="info">${authentication.info }</span>
          </div>
        </div>
        <ul class="tabs">
          <li id="tab_post"><a href="/member/mypage">내가 쓴 글</a></li>
          <li id="tab_reply"><a href="/member/mypage/reply">내가 쓴 댓글</a></li>
        </ul>
        <div class="my_posts">
          <div class="col_my_posts">
            <span class="title">제목</span>
            <span class="date">작성일</span>
            <span class="views">조회</span>
          </div>
          
      	<form action="#" method="post" name="deletePost">        
          <table>
          <!-- board패키지 접근 필요 -->
          <c:if test="${empty postByPage}">
			<tr class="contents">
              <td></td>
              <td>작성하신 게시글이 존재하지 않습니다.</td>
              <td></td>
              <td></td>
            </tr>          	
          </c:if>
          <c:if test="${not empty postByPage}">
            <c:forEach items="${postByPage}" var="myPost">
	            <tr class="contents" id="myPost">
	              <td><input type="checkbox" id="checkbox"><span class="idx">${myPost.bdIdx}</span></td>
	              <td><a href="/board/board-detail?p=${param.p }&f=${param.f}&q=${param.q }&bd_idx=${myPost.bdIdx }" id="tit_content">${myPost.title}</a></td>
	              <td>${myPost.regDate}</td>
	              <td>${myPost.viewCnt}</td>
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
              <!-- <button id="btn_edit">수정하기</button> -->
              <button id="btn_del">삭제하기</button>
            </div>
          </div>           
        </form>
        </div>
        <c:set var="currPage" value="${(empty param.p)? 1 : param.p}" ></c:set>	<!-- 현재 페이지 -->
        <c:set var="startNum" value="${currPage-(currPage-1)%5}" ></c:set>	<!-- 첫 페이지 번호(1 or 0) -->
        <c:set var="lastNum" value="${Math.ceil(fn:length(myPosts)/8)}" ></c:set>	<!-- 총 페이지수 -->
                
	    <div class="arrows" style="display: flex; justify-content: center">
	        <c:if test="${startNum > 1}">
		      <a href="?p=${startNum-1}"><i class="fas fa-chevron-left leftArrow"></i></a>
		    </c:if>
		    <c:if test="${startNum <= 1}">
		      <span onclick="alert('이전 페이지가 존재하지 않습니다.')"><i class="fas fa-chevron-left leftArrow" ></i></span>
        	</c:if>
        	
			<ul style="display:flex; margin: 0 5px">
				<c:forEach var="i" begin="0" end="4">
				<c:if test="${(startNum+i) <= lastNum }">	<!-- 같거나 작을 때만 출력 -->
				<li><a href="?p=${startNum + i}" style="padding: 0 5px">${startNum + i}</a></li>	<!-- 페이지 넘버링 -->
				</c:if>
				</c:forEach> 		
			</ul>

			<c:if test="${startNum + 4 < lastNum}">
				<a href="?p=${startNum + 5}"><i class="fas fa-chevron-right rightArrow" ></i></a>
			</c:if>
			<c:if test="${startNum + 4 >= lastNum}">
				<span onclick="alert('더이상 게시글이 존재하지 않습니다.')"><i class="fas fa-chevron-right rightArrow" ></i></span>
			</c:if>			
		</div>     
   

      </div>
    </div>
  </section>


   <script>

   

	//현재실행안됨..;   
   document.querySelector('.profile_img').addEventListener('click', function (e) {
    	 document.profile.target_url.value = document.getElementById('target_img').src;
         e.preventDefault();
       	 $('#file').click();	//changeValue메서드 호출
   });

   
   let changeValue = function(obj) {
  	 document.profile.submit();

   }
     
   
   
   
   
   
   
   
   
   document.querySelector('#selectAll').addEventListener('click', (e) => {
	   if(e.target.checked == true){
		   document.querySelectorAll('#checkbox').forEach((chk) => {
			   chk.checked = true;
		   })
	   }else{
		   document.querySelectorAll('#checkbox').forEach((chk) => {
			   chk.checked = false;
		   })		   
	   }
   });
  

   
   document.querySelector('#btn_del').addEventListener('click',(btn)=>{
	   btn.preventDefault();
	   
	   let chkArr = [];
	   document.querySelectorAll('#checkbox').forEach((chk) => {
		   if(chk.checked == true){
			   chkArr.push(chk)
		   } 
	   });
	   if(chkArr.length > 1){
		   alert("선택하신 게시글을 모두 삭제하시겠습니까?");
	   }else if(chkArr.length == 0){
		   alert("선택되지 않았습니다.");   
	   }
		document.deletePost.submit();
	   

   })
   </script>

</body>
</html>