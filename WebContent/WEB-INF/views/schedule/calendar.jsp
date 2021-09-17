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

<style type="text/css">
  #calendarBox{
	   margin-top: 30px;
   }
   .fc-event-title.fc-sticky{
   		padding:30px;
   }

</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
  
    <script>

    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: 'dayGridMonth'
        });
        calendar.render();
      });
    
      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
           height: 650,
          initialView: 'dayGridMonth',
          locale: 'ko',
          events: [
               {
                 title: '북한산',
                 start: '2021-09-16'
                    ,
                     url: 'http://google.com/'//클릭시 팝업
               }
               // other events here
             ], headerToolbar: {
                  center: 'addEventButton' // headerToolbar에 버튼을 추가
              }, customButtons: {
                  addEventButton: { // 추가한 버튼 설정
                      text : "산행동행 모임 만들기",  // 버튼 내용
                      click : function(){ // 버튼 클릭 시 이벤트 추가
                         
                         location.href = 'schedule-form';

                      }
                  }
              },
       
        });
       
        calendar.render();
      });
      
        

    </script>

  <section>
    <div class="container" id="calendarBox">
         <div id='calendar'></div>
    </div>
    
     <!-- modal 추가 -->
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
    </div>
    </section>
  </body>
</html>

</body>
</html>