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
        <div class="tit_schedule">이번주 일욜 불암산 가실 등린이 모집</div>
        <button class="close_modal">&times;</button>
        <div class="participants">
          <div class="parti">
            <img src="/resources/img/mail-template/img_travel_01.png" alt="">
            <span>닉넴1</span>
          </div>
          <div class="parti">
            <img src="/resources/img/mail-template/img_travel_02.png" alt="">
            <span>닉넴2</span>
          </div>
          <div class="parti">
            <img src="/resources/img/mail-template/img_travel_03.png" alt="">
            <span>닉넴3</span>
          </div>
          <div class="parti">
            <img src="/resources/img/user.png" alt="">
            <a href="동행하러가기 버튼과 같은 요청">+</a>
            <span></span>
          </div>
        </div>
        <div class="desc">
          <div class="desc_content">
            <h1 class="tit_content">모임내용</h1>
            <!-- 테이블에서 info가져오기 -->
            <p>
              일요일 불암산 가실 분 구합니다. 내려와서 간단히 식사할 계획입니다.
            </p>
          </div>
          <div class="desc_content">
            <h1 class="tit_content">안내사항</h1>
            <h2>모임 날짜</h2><span>2021-09-28 ${schedule.dDay}</span>
            <h2>모임 장소</h2><span>불암산 버스정류장</span>
            <h2>인원수</h2><span>4</span>
            <h2>오픈채팅방 링크</h2><span>dsfjlsfjlfj454</span>
          </div>
          <div class="desc_content">
            <h1 class="tit_content">함께 동행할 유저 소개</h1>
            <div class="desc_user">
              <img src="/resources/img/mail-template/img_travel_01.png" alt="">
              <div class="info">
                <h1>닉넴1</h1>
                <h2>29살</h2>
                <span>등산을 좋아하는 1인입니다.</span>
              </div>
            </div>
            <div class="desc_user">
              <img src="/resources/img/mail-template/img_travel_02.png" alt="">
              <div class="info">
                <h1>닉넴2</h1>
                <h2>29살</h2>
                <span>등산을 좋아하는 1인입니다.</span>
              </div>
            </div>
            <div class="desc_user">
              <img src="/resources/img/mail-template/img_travel_03.png" alt="">
              <div class="info">
                <h1>닉넴3</h1>
                <h2>29살</h2>
                <span>등산을 좋아하는 1인입니다.</span>
              </div>
            </div>
          </div>
          <!-- host가 아닐 때 -->
          <input type="submit" id="btn_parti" value="동행하러가기">
          <!-- host일 때 -->
          <div class="btn">
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
    
  </body>
</html>

</body>
</html>