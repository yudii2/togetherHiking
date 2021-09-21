수정 중인 스크립트

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- bootstrap 4 -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->

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
  
    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          locale: 'ko',
       	  // 캘린더 view 타입
	      initialView: 'dayGridMonth',
	      
          events: [
               {
                 title: '북한산',
                 start: '2021-09-16',
                 /* url: "/schedule/calendar" */
                 },
              
                 // other events here

/* 	              {
	                title: '아차산',
	                 start: '2021-09-20',
	                 url: "https://www.naver.com"
	               },
	              {
		               title: '구룡산',
		                start: '2021-09-20',
		                url: "https://www.naver.com"
	                },
	                {
		                title: '남산',
		                start: '2021-09-20',
		                 url: "https://www.naver.com"
	                 },
	                 {
			             title: '인왕산',
			             start: '2021-09-20',
			             url: "https://www.naver.com"
		                 }, */
               
               
               ], headerToolbar: {
                  center: 'addEventButton' // headerToolbar에 버튼을 추가
              }, customButtons: {
                  addEventButton: { // 추가한 버튼 설정
                      text : "산행동행 모임 만들기",  // 버튼 내용
                      click : function(){ // 버튼 클릭 시 이벤트 추가
                         
                         location.href = "schedule-form";

                      }
                  }
              }, eventClick : () => {
          	    document.querySelector('.modal').style.display='flex';
        	    document.querySelector('.overlay').style.display='flex';
              }
              
       
        });
       
        calendar.render();
      });
    
    
/* 모달 */


    

      
        

    </script>
     <section>
    <div class="container" id="calendarBox">
         <div id='calendar'></div>
    </div>
    

    
<!--      modal 추가
    <div class="modal fade" id=calendarModal tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">일정을 입력하세요.</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="taskId" class="col-form-label">일정 내용</label>
                        <input type="text" class="form-control" id="calendar_content" name="calendar_content">
                        <label for="taskId" class="col-form-label">시작 날짜</label>
                        <input type="date" class="form-control" id="calendar_start_date" name="calendar_start_date">
                        <label for="taskId" class="col-form-label">종료 날짜</label>
                        <input type="date" class="form-control" id="calendar_end_date" name="calendar_end_date">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" id="addCalendar">추가</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
                        id="sprintSettingModalClose">취소</button>
                </div>
    
            </div>
        </div>
    </div> -->
    
    
    
    </section>
    
        <!-- Layer Popup -->
    <div class="modal">
      <div class="modal_inner">
        <div class="click_img">맵(등산로)이 들어갈 자리입니다.</div>
        <div class="desc">
          <div class="desc_header">
            <div class="tit_course"><span>북한산 코스1</span></div>
            <button class="close_modal">&times;</button>
          </div>
          <div class="desc_content">
            <div class="wrap_text">
              <h2>등산로 거리</h2><span>${mountain.course[idx].distance}947km</span>
              <h2>난이도</h2><span>상</span>
              <h2>상행시간</h2><span>2시간 22분 소요</span>
              <h2>하행시간</h2><span>1시간 43분 소요</span>
              <h2>총소요시간</h2><span>4시간 5분 예상</span>
              <h2>칼로리 </h2><span> kcal 소모</span>
            </div>
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
    
  </body>
</html>

</body>
</html>