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
  
    <section>
     <form action="schedule-form.jsp" method="get">
    <div class="container con_sec01">
    	<h2>산행동행 모임 만들기</h2><br/><br/>
    		
    		<h3>모임 상세내용을 양식에 맞게 작성해주세요.</h3><br/><br/>
    		
    		산 이름
    		<label>
    			<input type="text" placeholder="북한산">
    		</label><br/><br/>   		
    		모임 설명
    		<label>
		 		<textarea placeholder="자세한 설명을 입력해주세요."></textarea>
		 	</label><br/><br/>			 	
		 	날짜
		 	<label>
		 		<input type="date" min="2021-07-20">
		 	</label><br/><br/>		 	
		 	시간		 	
		 	<label>
		 		<input type="time"> 
		 	</label><br/><br/>	 			 	
		 	인원설정
        	<input type="number" min="2" max="5" step="1" value="2"><br/><br/>		    		    		   		
    		오픈 채팅방 링크
    		<input type="url" placeholder="https://open.kakao.com/"><br/><br/>    		    		    		
    		참가비
    		<input type="number" min="0" max="50000" step="5000" placeholder="5,000"><br//><br/>    		   		
    		모집 연령대
				<label>
					10대
					<input type="radio" name="age" value="10">
				</label>
				<label>
					20대
					<input type="radio" name="age" value="20">
				</label>
				<label>
					30대
					<input type="radio" name="age" value="30">
				</label>
				<label>
					40대
					<input type="radio" name="age" value="40">
				</label><br/><br/>

    	<input type="button" value='캘린더로 돌아가기'>
    	<input type="submit" value='모임글 등록하기' onclick="alert('신청이 완료되었습니다.\n검토를 거쳐 24시간 이내에 결과를 안내해 드리겠습니다.')">
    	
    </div>
    </form>
    
    
    
    </section>
</body>
</html>