<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 자동완성 CSS , JS -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
		
		<form  action="/schedule/upload" method="post" class="schedule_form" name="form">
		<table class="table">
		<tr height="50">
			<td width="150" align="center"> 산 이름 </td>
			<td width="350" align="center"> <input type="text" name="mountainName" id="searchMountainName" size="40" >
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
			<td width="150" align="center"> 모집 연령대 </td>
			<td width="350" align="center">
			<input type="radio" name="age" id="age" value="10">10대 &nbsp;&nbsp;
			<input type="radio" name="age" id="age" value="20">20대 &nbsp;&nbsp;
			<input type="radio" name="age" id="age" value="30">30대 &nbsp;&nbsp;
			<input type="radio" name="age" id="age" value="40">40대 &nbsp;&nbsp;
			</td>
		</tr>
		</table>
		
			<button type="button" onclick="location.href='calendar'">캘린더로 돌아가기</button >
		
			<button type="submit" onclick="upload()">모임글 등록하기</button >
			
		
	
		</form>
			</section>
		

		

			<script>
			let upload = () => {
				document.form.submit();
			}
			
			
			
			</script>

</body>
</html>