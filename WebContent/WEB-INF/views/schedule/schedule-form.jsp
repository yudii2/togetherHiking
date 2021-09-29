<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 자동완성 CSS , JS -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- data -->
	<script src="/resources/js/schedule/data.js"></script>
  <!-- jQuery CDN -->
  <script defer src="js/main/gnb.js"></script>
  <!-- head.jsp -->
<%@ include file="/WEB-INF/views/include/head.jsp" %>
  <link rel="stylesheet" href="/resources/css/schedule/schedule-form.css">
</head>
			<script>
			$(function () {	//화면 로딩후 시작
				$("#mountainName").autocomplete({  //오토 컴플릿트 시작
					source: List,	// source는 data.js파일 내부의 List 배열
					focus : function(event, ui) { // 방향키로 자동완성단어 선택 가능하게 만들어줌	
						return false;
					},
					minLength: 1,// 최소 글자수
					delay: 100,	//autocomplete 딜레이 시간(ms)
					//disabled: true, //자동완성 기능 끄기
				});
			}); 
			
		
			</script>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
	<section>
		
		<form  action="/schedule/upload" method="post" class="schedule_form" name="form">
		<table class="table">
		<tr height="50">
			<td width="150" align="center"> 산 이름 </td>
			<td width="350" align="center"> <input type="text" class="valid_msg" name="mountainName" id="mountainName" size="40" required="required" placeholder="북한산" value="">
			</td>
		</tr>
		<tr height="50">
			<td width="150" align="center"> 모임 설명 </td>
			<td width="350" align="center">
			<textarea name="info" id="info" rows="5" cols="40" placeholder="자세한 설명을 입력해주세요" required="required"  value="info"></textarea>
			</td>
		</tr>
		<tr height="50">
			<td width="150" align="center"> 날짜 </td>
			<td width="350" align="center"> <input type="date" min="2021-07-20" name="dDay" id="dDay" size="40" value="date" required="required" >
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
			<td width="350" align="center"><input type="url" name="openChat" required="required"  id="openChat" placeholder="https://open.kakao.com/" size="40" value="">
		</td>

		<tr height="50">
			<td width="150" align="center"> 모집 연령대 </td>
			<td width="350" align="center">
			<input type="radio" name="age" id="age" value="10" checked="checked">10대 &nbsp;&nbsp;
			<input type="radio" name="age" id="age" value="20">20대 &nbsp;&nbsp;
			<input type="radio" name="age" id="age" value="30">30대 &nbsp;&nbsp;
			<input type="radio" name="age" id="age" value="40">40대 &nbsp;&nbsp;
			</td>
		</tr>
		</table>
		
			<button type="button" onclick="location.href='calendar'" >캘린더로 돌아가기</button >
		
			<button type="button" onclick="upload()">모임글 등록하기</button >
			
		
	
		</form>
	</section>
			<script>
			let upload = () => {
				
				if(document.form.checkValidity()){
					alert('검토를 거쳐 24시간 후 결과를 안내드리겠습니다.');
					document.form.submit();
				} else {
					document.form.reportValidity()
					return false;
				}
				
			} 
			
			</script>

</body>
</html>