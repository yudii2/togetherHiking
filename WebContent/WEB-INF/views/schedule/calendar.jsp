<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- bootstrap 4 -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- fullcalendar.css -->
<link href='/resources/css/schedule/main.css' rel='stylesheet' />
<!-- popper -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- head.jsp -->
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/schedule/calendar.css">
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
  
    <script>
  

    </script>
     <section>
    <div class="container" id="calendarBox">
         <div id='calendar'></div>
    </div>
    </section>
  <input>
    <!-- Layer Popup -->
  <div class="modal">
      <div class="modal_inner">
        <div class="tit_schedule" id="tit_schedule">이번주 일욜 불암산 가실 등린이 모집</div>
        <button class="close_modal">&times;</button>
        <div class="participants" id="participants">
        <!--  <div class="parti">
            <img src="/resources/img/mail-template/img_travel_01.png" alt="" id="profile">
            <span id="nickName">닉넴1</span>
          </div> -->
          <!-- <div class="parti">
            <img src="/resources/img/mail-template/img_travel_02.png" alt="">
            <span>닉넴2</span>
          </div>
          <div class="parti">
            <img src="/resources/img/mail-template/img_travel_03.png" alt="">
            <span>닉넴3</span>
          </div> -->
          <div class="parti part-action">
            <img src="/resources/img/user.png" alt="">
            <a id="part-action">+</a>
            <span></span>
          </div> 
        </div>
        <div class="desc">
          <div class="desc_content">
            <h1 class="tit_content" id="tit_content">모임내용</h1>
            <!-- 테이블에서 info가져오기 -->
            <p>
              <%-- 일요일 불암산 가실 분 구합니다. 내려와서 간단히 식사할 계획입니다.
               ${schedule.info} --%>
            </p>
          </div>
           <div class="desc_content">
            <h1 class="tit_content">안내사항</h1>
            <h2>모임 날짜</h2><span id="dDay">${schedule.dDay}</span>
            <h2>모임 장소</h2><span id="mointainName"> ${schedule.mountainName}</span>
            <h2>인원수</h2><span id="allowedNum">${schedule.allowedNum}</span>
            <h2>오픈채팅방 링크</h2><span id="openChat"> ${schedule.openChat}</span>
            <h2>모집 연령대</h2><span id="age">${schedule.age}</span>
          </div>
          <div class="desc_content">
            <h1 class="tit_content desc_tit_content">함께 동행할 유저 소개</h1>
           <%-- <c:forEach items="${participants}" var="participant">--%>
				<div class="desc_user">
<%--  				  <c:if test="${empty participant.profile}">
				  	<img id="target_img" src="/resources/img/user.png">
				  </c:if> --%>
				 <%--  <c:if test="${not empty participant.profile}"> --%>
	              	<img id="paProfile" src="http://localhost:7070/file/" alt="">
	              <%-- </c:if> --%>
	              <div class="info">
	                <h1 class="paNickname">닉넴1 ${participant.nickname}</h1>
	                <!-- <h2>29살</h2> -->
	                <span class="paInfo">${participant.info}</span>
	              </div>
	            </div>            
           <%-- </c:forEach>--%>
            
            
            
          </div>
          <!-- host가 아닐 때 -->
          <div class="btn" id="btnNotHost">
          	<input type="submit" id="btn_parti" value="동행하러가기" onclick="participant()">
	        <input type="submit" id="btn_cancel" value="동행 취소">
          </div>
          <!-- host일 때 -->
          <div class="btn" id="btnHost">
          	<input type="hidden" id="scid" >
          	<input type="submit" id="btn_edit" value="수정">
          	<input type="submit" id="btn_del" value="삭제">      
          </div>

        </div>
      </div>
  </div>

  <div class="overlay"></div>
    
    <script type="text/javascript">
    

   	document.querySelector('.close_modal').addEventListener('click',function() {
   	  document.querySelector('.modal').style.display='none';
   	  document.querySelector('.overlay').style.display='none';
   	});
    
    
    </script>
    <script type="text/javascript">
    //동행하러가기 버튼 이벤트
	function participant(){
    	
    	const allow = $('#allowedNum').text();
    	const child = $('#participants').children().length;
    	if(child - 1 >= allow){
    		alert('초과하였습니다.');
    		return;
    	}
    	location.href = '/schedule/participant?scIdx='+$('#scid').val();
    
    
    $(document).ready(function(){	// DOM 객체가 로드되면 #btn을 숨긴다.
    	$('#btn').hide();
    
    $('#btn_parti').on('click',function(){
    	location.href = '/schedule/participant?scIdx='+$('#scid').val();
    });
    
    $('#btn_edit').on('click',function(){
    	location.href = '/schedule/schedule-modify-form?scIdx='+$('#scid').val();
    });
    $('#btn_del').on('click',function(){
    	location.href = '/schedule/delete?scIdx='+$('#scid').val();
    });
    
    $('#part-action').on('click', function(){
    	participant();
    })
    	
    });
    document.addEventListener('DOMContentLoaded', function() {
    	
    	var schedule = [];
    	// calendar-list를 ajax 요청으로 받아온다.
		$.ajax({
            
            url:"/schedule/calendar-list", 
            type:"get",
            datatype:"json",
            async:false,
            success:function(json){
                console.log(json);   
                // data를 성공적으로 가져오면, schedule 배열에 object를 생성하여 push한다.
                for (var i = 0; i < json.length; i++){
                	console.log(new Date(json[i].dDay));
                	var object = {
                			'title' : json[i].mountainName,	// title : 산이름
                			'start' :json[i].dDay,	// start : 날짜
                			'id' : json[i].scIdx	// id : schedule id
                	};
                	schedule.push(object);
                }
            }/* ,
            error:function(){
                alert("error");
                return;
            } */
        });
    	console.log(schedule);
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          locale: 'ko',
	      initialView: 'dayGridMonth', // 캘린더 view 타입
	      
          events: 
              	schedule
                 // other events here
               , headerToolbar: {
                  center: 'addEventButton' // headerToolbar에 버튼을 추가
              }, customButtons: {
                  addEventButton: { // 추가한 버튼 설정
                      text : "산행동행 모임 만들기",  // 버튼 내용
                      click : function(){ // 버튼 클릭 시 이벤트 추가
                         
                         location.href = "schedule-form";

                      }
                  }
              }, eventClick : (info) => {
            	  // click EVENT 를 등록한다.
           	   $('div').remove('.partii');
           	  $('div').remove('.desc_uuser');
                             
           	  $.ajax({
                     // schedule-detail로  선택한 schedule의 상세정보를 받아온다.
                     url:"/schedule/schedule-detail", 
                     type:"get",
                     data:{'scIdx':info.event.id},
                     datatype:"json",
                     async:false,
                     success:function(json){
                         console.log(json);   
                         var sh = json.schedule;
                         var pa = json.participants;
                         if(sh){
                       	  // 받아온 schedule 정보를 모달에 등록한다.
                       	  $('#tit_schedule').text(sh.mountainName);
                             $('#tit_content').text(sh.info);
                             $('#dDay').text(sh.dDay);
                             $('#mointainName').text(sh.mountainName);
                             $('#allowedNum').text(sh.allowedNum);
                             $('#openChat').text(sh.openChat);
                             $('#age').text(sh.age + '대');
                         
	                             // 참가동행자가 있을 경우 화면에 표시
	                         if(pa){
	                        	 for (var i = 0; i < pa.length; i++) {
	                        		 console.dir($('#paProfile'));
	                        		 if(!pa[i].profile){
	                        			 $('#paProfile')[0].src = '/resources/img/user.png';
	                        		 }else{
	 	                        		$('#paProfile')[0].src += pa[i].profile;
	                        		 } 
									$('.paNickname').text(pa[i].nickname);
									$('.paInfo').text(pa[i].info);
									
									//클래스 <div class="desc_user"> 추가
	                        	 }
	                         }
                             
                             
                             // 접속 사용자와 작성자가 동일하면 버튼을 노출하고, 아니면 숨긴다
                             if(!json.userIdx){
	                           	  $('#btnNotHost').hide();
	                           	 $('#btnHost').hide();
                           	  return;
                             }
                             if(json.userIdx.userId == sh.userId){
                            	 $('#btnNotHost').hide();
                           	  	$('#btnHost').show();
                             } else {
                           	  $('#btnHost').hide();
                             }
                             $('#scid').val(info.event.id);
                         }
                     }/* ,
                     error:function(){
                         alert("error");
                     } */
                 });
           	  
         	    document.querySelector('.modal').style.display='flex';
       	   	document.querySelector('.overlay').style.display='flex';
             }
       });
      
       calendar.render();
     });
   </script>
   
 </body>
</html>

</body>
</html>