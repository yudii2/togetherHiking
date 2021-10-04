<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- fullcalendar.css -->
<link href='/resources/css/schedule/main.css' rel='stylesheet' />
<!-- popper -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<!-- bootstrap 4 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<!-- head.jsp -->
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="/resources/css/schedule/calendar.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/include/fixed-header.jsp"%>

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
				<div class="parti part_action">
					<!-- <img src="/resources/img/user.png" alt=""> <a id="part_action">+</a> <span></span> -->
				</div>
			</div>
			<div class="desc">
				<div class="desc_content">
					<h1 class="tit_content" id="tit_content"></h1>
					<!-- 테이블에서 info가져오기 -->
					<p>
						<%-- 일요일 불암산 가실 분 구합니다. 내려와서 간단히 식사할 계획입니다.
               ${schedule.info} --%>
					</p>
				</div>
				<div class="desc_content sc_info">
					<h1 class="tit_content">안내사항</h1>
					<h2>모임 날짜</h2>
					<span id="dDay">${schedule.dDay}</span>
					<h2>모임 장소</h2>
					<span id="mointainName"> ${schedule.mountainName}</span>
					<h2>인원수</h2>
					<span id="allowedNum">${schedule.allowedNum}</span>
					<h2>오픈채팅방 링크</h2>
					<span id="openChat"> ${schedule.openChat}</span>
					<h2>모집 연령대</h2>
					<span id="age">${schedule.age}</span>
				</div>
				<div class="desc_content">
					<h1 class="tit_content desc_tit_content">함께 동행할 유저 소개</h1>
					<div class="desc_user">
					</div>

				</div>
								<!-- host가 아닐 때 -->
				<div class="btn" id="btnNotHost">
					<input type="submit" id="btn_parti" value="동행하러가기" onclick="participant()"> <input type="submit" id="btn_cancel" value="동행 취소">
				</div>
				<!-- host일 때 -->
				<div class="btn" id="btnHost">
					<input type="hidden" id="scid"> <input type="submit" id="btn_edit" value="수정"> <input type="submit" id="btn_del" value="삭제">
				</div>
			</div>
		</div>
	</div>

	<div class="overlay"></div>

	<script type="text/javascript">
    
   
   	function close_modal(){
   		document.querySelector('.modal').style.display='none';
        document.querySelector('.overlay').style.display='none';
   	}

      document.querySelector('.close_modal').addEventListener('click',function() {
    	  close_modal();
      });
    
    
    </script>
	<script type="text/javascript">
    //동행하러가기 버튼 이벤트
   function participant(){
       
       const allow = $('#allowedNum').text();
       const member = $('#participants').members().length;
       if(member - 1 >= allow){
          alert('초과하였습니다.');
          return;
       }
       location.href = '/schedule/participant?scIdx='+$('#scid').val();
    }
    

    
    
    
    $(document).ready(function(){   // DOM 객체가 로드되면 #btn을 숨긴다.
       $('#btn').hide();
    
    $('#btn_parti').on('click',function(){
    	$.ajax({
    		url:'/schedule/participant?scIdx='+$('#scid').val(),
    		type:'get',
    		dataType:'json',    		
    		success:function(data){
    			if(data.code == 'login'){
    				location.href='/schedule/calendar';
    			}else if(data.code == 'ok'){
    				alert('참가되었습니다.');
    				close_modal();
    			}else if(data.code == 'full'){
    				alert('모집이 완료된 동행입니다.');
    			}else if(data.code == 'joined'){
    				alert('이미 참여된 동행입니다.');
    			}
    		}
    	});
    	
    });
    
    $('#btn_edit').on('click',function(){
       location.href = '/schedule/schedule-modify-form?scIdx='+$('#scid').val();
    });
    $('#btn_del').on('click',function(){
       location.href = '/schedule/delete?scIdx='+$('#scid').val();
    });
    
    $('#btn_cancel').on('click',function(){
       location.href = '/schedule/cancel?scIdx='+$('#scid').val();
    });
    
    $('#part_action').on('click', function(){
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
                // data를 성공적으로 가져오면, schedule 배열에 object를 생성하여 push한다.
                for (var i = 0; i < json.length; i++){
                   var object = {
                         'title' : json[i].mountainName,   // title : 산이름
                         'start' :json[i].dDay,   // start : 날짜
                         'id' : json[i].scIdx   // id : schedule id
                   };
                   schedule.push(object);
                }
            }/* ,
            error:function(){
                alert("error");
                return;
            } */
        });
       /* console.log(schedule); */
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          locale: 'ko',
         initialView: 'dayGridMonth', // 캘린더 view 타입
         eventLimit: true,
         views: { 
                 month : { eventLimit : 12 } // 한 날짜에 최대 이벤트 12개, 나머지는 + 처리됨
        }, events: 
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
                         /* console.log(json); */   
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

                               var top = '';
                               var bottom = '';
                               
                               for (var i = 0; i < pa.length; i++) {
		
		                           //클래스 <div class="desc_user"> 추가
		                           console.dir(pa[i].profile);
		                           if(pa[i].profile == 'null'){											/* onerror="onErrorImage(this) */
		                               top += ' <div class="parti partii"><img src="/resources/img/user.png" alt=""><span>'+ pa[i].nickname + '</span></div>'
		                               bottom += '<div class="desc_user desc_uuser"><img src="/resources/img/user.png" alt=""><div class="info"><h1>'+pa[i].nickname+'</h1><span>'+pa[i].info+'</span></div></div>';
		
		                           }else{
		                               top += ' <div class="parti partii"><img src="http://localhost:7070/file/' + pa[i].profile + '"alt=""><span>'+ pa[i].nickname + '</span></div>';                               
		                               bottom += '<div class="desc_user desc_uuser"><img src="http://localhost:7070/file/' + pa[i].profile + '" alt=""><div class="info"><h1>'+pa[i].nickname+'</h1><span>'+pa[i].info+'</span></div></div>';
		
		                           }
                               }
                                $('.part_action').before(top);
                                $('.desc_tit_content').after(bottom);  
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
                             } else {   //호스트가 아닐때
                            	 $('#btnHost').hide();
                              	 $('#btnNotHost').show();
                            }                         
                             $('#scid').val(info.event.id);
                            }
                     }   //success끝 
                     /* ,
                     error:function(){
                         alert("error");
                     } */
                         
                 }); //ajax끝
                     
                 document.querySelector('.modal').style.display='flex';
                 document.querySelector('.overlay').style.display='flex';
                }   //eventClick 끝
             });       
       calendar.render();
     });       
   </script>
<!-- <script>
function onErrorImage(_img) {
	// 처음 에러가 나면 data-load-num에 0부여
	var loadNum = $(_img).attr('data-load-num') == undefined ? 0 : $(_img).attr('data-load-num');
    
	if (loadNum < 3){		// 이미지를 3번 불러옴
		$(_img).attr('data-load-num', ++loadNum);
		$(_img).attr('src', "http://localhost:7070/file/");
	} else {			// 이미지 없음 표시
		$(_img).attr('src', "/resources/img/user.png");
	}
}
</script> -->
</body>
</html>

</body>
</html>