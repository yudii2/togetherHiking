<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <!-- fullcalendar.css -->
  <link href='resources/fullcalendar/css/main.css' rel='stylesheet' />
  <script src='resources/fullcalendar/js/main.js'></script>
  <!-- jQuery CDN -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script defer src="js/main/gnb.js"></script>
  <!-- head.jsp -->
<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
<link rel="stylesheet" href="/resources/css/schedule/schedule-form.css">
    <section>
     <form action="calendar" method="post">
    <div class="container">
    	<h2>산행동행 모임 만들기</h2><br/><br/>
    		
    		<h3>모임 상세내용을 양식에 맞게 작성해주세요.</h3><br/><br/>
    		
    		 <div>
    			<span class="tit_form">산 이름</span>
    			<input type="text" name="name" value="${mountainName}">
    		</div>
    		<div>
    			<span class="tit_form">모임 설명</span>
    			<textarea rows="5" minlength="15" maxlength="50" placeholder="자세한 설명을 입력해주세요.">${info}</textarea>
    		</div>
    		<div>
    			<span class="tit_form">날짜</span>
    			<input type="date" min="2021-07-20" value="${date}">
    		</div>
    		<div class="time">
    			<span class="tit_form">시간</span>
    			<input type="time" value="time">
    		</div>		 	
		 	<div class="member-number">
    			<span class="tit_form">모집 인원</span>
    			<input type="number" min="2" max="5" step="1" value="2">
    		</div>		
		 	<div class="open-chat">
    			<span class="tit_form">오픈 채팅방 링크</span>
    			<input type="url" placeholder="https://open.kakao.com/">
    		</div>		 	
		 	<div>
    			<span class="tit_form">참가비</span>
    			<input type="number" min="0" max="50000" step="5000" placeholder="0">
    		</div>		    		    		   		
    		<!-- <div>
    			<input type="radio" name="age" value="10">10대
    			<input type="radio" name="age" value="20">20대
    			<input type="radio" name="age" value="30">30대
    			<input type="radio" name="age" value="40">40대
    		</div>    -->
    				    		
    		    		   		
		<button type="button" onclick="location.href='calendar'">캘린더로 돌아가기</button >
		<button type="submit" onclick="upload(); move();">모임글 등록하기</button >
    	<script type="text/javascript">
    	function upload(){
    		alert('신청이 완료되었습니다.\n검토를 거쳐 24시간 이내에 결과를 안내해 드리겠습니다.');
    	}
    	function move(){
    		window.location = 'calendar';
    	}
    	</script>
    	
    </div>
    </form>
    
    
    
    </section>
</body>
</html>