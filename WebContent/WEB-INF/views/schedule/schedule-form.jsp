<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <!-- jQuery CDN -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script defer src="js/main/gnb.js"></script>
  <!-- head.jsp -->
<%@ include file="/WEB-INF/views/include/head.jsp" %>
  <link rel="stylesheet" href="/resources/css/schedule/schedule-form.css">
</head>

<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
	<section>
		
		<form  action="/member/mypage" method="post" class="schedule_form">
		<table class="table">
		<tr height="50">
			<td width="150" align="center"> 산 이름 </td>
			<td width="350" align="center"> <input type="text" name="mountainName" id="mountainName" size="40" placeholder="북한산" value="">
			</td>
		</tr>
		<tr height="50">
			<td width="150" align="center"> 모임 설명 </td>
			<td width="350" align="center">
			<textarea name="info" id="info" rows="5" cols="40" placeholder="자세한 설명을 입력해주세요" value="info"></textarea>
			</td>
		</tr>
		<tr height="50">
			<td width="150" align="center"> 날짜 </td>
			<td width="350" align="center"> <input type="date" min="2021-07-20" name="dDay" id="dDay" size="40" value="date">
			</td>
		</tr>
		<tr height="50">
			<td width="150" align="center"> 시간 </td>
			<td width="350" align="center"> <input type="time" value="time" name="meetingTime" id="meetingTime" size="40" value="time">
		</td>
		<tr height="50">
			<td width="150" align="center"> 모집 인원 </td>
			<td width="350" align="center">
			<select name="allowedNum" id="allowedNum" value="people">
			<option value="2">2명</option>
			<option value="3">3명</option>
			<option value="4">4명</option>
			<option value="5">5명</option>
			<option value="6">6명</option>
			</select></td>
			</tr>
		<tr height="50">
			<td width="150" align="center"> 오픈 채팅방 링크 </td>
			<td width="350" align="center"><input type="url" name="openChat" id="openChat" placeholder="https://open.kakao.com/" size="40" value="">
		</td>
		<tr height="50">
			<td width="150" align="center"> 참가비 </td>
			<td width="350" align="center"><input type="number" name="money" id="money" min="0" max="50000" step="5000" value="0" size="40" value="money">
			</td>
		</tr>
		<tr height="50">
			<td width="150" align="center"> 모집 연령대 </td>
			<td width="350" align="center">
			<input type="radio" name="age" id="age" value="10대">10대 &nbsp;&nbsp;
			<input type="radio" name="age" id="age" value="20대">20대 &nbsp;&nbsp;
			<input type="radio" name="age" id="age" value="30대">30대 &nbsp;&nbsp;
			<input type="radio" name="age" id="age" value="40대">40대 &nbsp;&nbsp;
			</td>
		</tr>
		</table>
		
			<button type="button" onclick="location.href='calendar'">캘린더로 돌아가기</button >
			
			
			<button type="submit" onclick="alert('신청이 완료되었습니다.\n검토를 거쳐 24시간 이내에 결과를 안내해 드리겠습니다.')">모임글 등록하기</button >
			
		
	
		</form>
	</section>

</body>
</html>