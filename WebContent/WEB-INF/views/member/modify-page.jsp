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
          	<input type="text" name="name" value="${authentication.userName}김이름" disabled>     
        </div>
        <div class="user_id">
          <span class="tit_form">아이디</span>
          	<input type="text" name="user_id" value="USER1" disabled>
        </div>
        <div class="nickname">
          <span class="tit_form">닉네임</span>
          <input type="text" name="nickname" id="nickname" value="${authentication.nickname}">
          <input type="submit" name="check_nick" id="check_nick" value="중복확인">
          <em id="alert_nick" class="alert_auth">
          	<c:if test="${not empty param.err and not empty joinValid.nickname}">
          		닉네임을 사용하실 수 없습니다.
          	</c:if>
          </em>             	
        </div>
        <div class="self-intro">
          <span class="tit_form">자기소개</span>
          <textarea rows="5" minlength="15" maxlength="50" required>${authentication.info}</textarea>
          <em id="alert_info" class="alert_auth">
          	<c:if test="${not empty param.err and not empty joinValid.info}">
          		15자 이상 50자 이하로 작성하세요.
          	</c:if>
          </em>        
        </div>
        <div class="password">
          <span class="tit_form">비밀번호</span>
          <label for="password" class="tit_pw_label">현재 비밀번호</label>
          <em id="alert_pw" class="alert_auth">
          	<c:if test="${not empty param.err and not empty joinValid.confirmPw}">
          		현재 비밀번호와 일치하지 않습니다.
          	</c:if>
          </em>
          <input type="password" name="password" id="password" 
          <c:if test="${not empty param.err and empty joinValid.password}">
          	value = ${joinForm.password}
          </c:if>
          required/>
          
          <label for="new_pw" class="tit_pw_label tit_new_pw">새 비밀번호</label>
          <em id="alert_new_pw" class="alert_auth">
          	<c:if test="${not empty param.err and not empty joinValid.password}">
          		영문,숫자,특수문자 포함 8~15자를 입력하세요.
          	</c:if>          
          </em>
          <input type="password" name="new_pw" id="new_pw" 
          <c:if test="${not empty param.err and empty joinValid.newPw}">
          	value = ${joinForm.newPw}
          </c:if>
          required/>
          
          <label for="new_pw_confirm" class="tit_pw_label tit_new_pw">새 비밀번호 확인</label>
          <em id="alert_check_pw" class="alert_auth">
          	<c:if test="${not empty param.err and not empty joinValid.confirmPw}">
          		비밀번호가 일치하지 않습니다
          	</c:if>           
          </em>          
          <input type="password" name="new_pw_confirm" id="new_pw_confirm" 
          <c:if test="${not empty param.err and empty joinValid.confirmPw}">
          	value = ${joinForm.newPw}
          </c:if>          
          required/>
        </div>
        <div class="email">
          <span class="tit_form">이메일</span>
          <input type="text" name="email" id="email" vlaue="${authentication.email}abc123@gmail.com" disabled>
        </div>
        <button id="btn_modify">수정완료</button>
      </form>
    </div>
  </section>
  
  <script type="text/javascript">
  
  let confirmNickname = '';
  
  document.querySelector('#check_nick').addEventListener('click', ()=>{
	  let nickname = document.querySelector('#nickname').value;
	  
/* 	  if(nickname == null){
		document.querySelector('#alert_nick').innerHTML = '닉네임을 입력하지 않았습니다.';
		return;
	  } */
	  
	  fetch("/member/check-nickname?nickname=" + nickname)
	  .then(response => {
		  if(response.ok){	//통신 성공시
			  return response.text();
		  }else{
			  throw new Error(response.status);
		  }
	  }).then(text => {	//promise객체의 text
		  if(text == 'available'){
			  confirmNick = nickname;
			  
			  document.querySelector('#alert_nick').style.color = 'var(--point-color)';
			  document.querySelector('#alert_nick').innerHTML = '사용 가능한 닉네임입니다.';
		  }else{
			  document.querySelector('#alert_nick').innerHTML = '사용 불가능한 닉네임입니다.';
		  }
	  }).catch(error => {
		  document.querySelector('#alert_nick').innerHTML = '응답에 실패하였습니다.';
	  });
	  
  });
  
  document.querySelector('#btn_modify').addEventListener('click',(e) => {
	  let nickname = document.querySelector('#nickname').value;
	  let originPw = document.querySelector('#password').value;
	  let pwReg = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9])(?=.{8,})/;
	  let newPw = document.querySelector('#new_pw').value;
	  let confirmPw = document.querySelector('#new_pw_confirm').value;
	  
	  
	  if(confirmNick != nickname){
		  console.dir(confirmNick);
		  document.querySelector('#alert_nick').innerHTML = '닉네임 중복 검사를 하지 않았습니다.';
		  document.querySelector('#alert_nick').focus();
		  e.preventDefault();	  		
	  }

  });
  
  
  
  </script>
</body>
</html>