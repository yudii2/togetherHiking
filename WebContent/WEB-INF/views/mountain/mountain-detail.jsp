<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file = "/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href = "/resources/css/mountain/mountain-detail.css">
</head>
   
  <body>
  <%@ include file = "/WEB-INF/views/include/fixed-header.jsp"%>
  <section class="container">      							
    <form id="search_mountain" name="search" method = "get"  action="?mName=${param.mName}" onsubmit="return keyword_check()">
		지역 또는 산 이름 검색 <br>           
		<i class="fas fa-search" style="font-size: 1em; color: white;"></i>
		<input type="text" class="search_bar" name= mName value="${param.mName}" >  
		
    	<div id="loc_bnt_wrap">
    		<a class="loc_bnt" href="/mountain/search">지역 선택하러 가기</a>
		</div>  
    </form>
    
    <div id="weather">날씨api</div>			<%-- 등산호 api로 가져 온 산의 주소에 해당하는 날씨 반환 --%>
		
    <div id="mountain_detail">
	    <div id="mountain_loc-map"></div>
		<div id="mountain_info">
			<div class="tit_mountain">
				<span class="mountain_name">${mountainInfo.mName}</span>
				<!-- 요청을 보내 모달창에 데이터 호출 필요...세션에 mountain객체를 저장? -->
				<!-- <form action="mountain/detail/course" id="mountain_trail">
					<select name="mountain_trail" class="mountain_trail">
						<option value="" selected disabled>등산로를 선택하세요</option>
						<option value="1">등산로1</option>
						<option value="2">등산로2</option>
					</select> -->
				</form>
			</div>
			<p>
				${mountainInfo.mInfo}
			</p>
			<div class="desc_mountain">
				<h2>높이 : <span>${mountainInfo.mHeight}</span></h2>
				<h2>위치 : <span>${mountainInfo.mLoc}</span></h2>
			</div>
		</div>          
    </div>
 
  </section>
  
  <%-- 
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
    <div class="overlay"></div> --%>
    
    <script type="text/javascript">
    
	//등산로
 /*   	document.querySelector('.close_modal').addEventListener('click',function() {
   	  document.querySelector('.modal').style.display='none';
   	  document.querySelector('.overlay').style.display='none';
   	});
    
    document.querySelector('.mountain_trail').addEventListener('change',function() {
	    document.querySelector('.modal').style.display='flex';
	    document.querySelector('.overlay').style.display='flex';
    }); */
    	
    //서치바
    function keyword_check(){
    	  if(document.search.mName.value==''){
    	  alert('산이름을 입력하세요'); 
    	  document.search.mName.focus();
    	  return false; 
    	  }
    	  else return true;
    	 };

  </script>	 
    
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB0BynjFSEnK1evu8mQktPf2KwJjkHcvH0&callback=initMap&region=kr"></script>
  <script>
    
    var map;
    var X = ${mountainInfo.xCoor};
    var Y = ${mountainInfo.yCoor};
    var 산이름 = ${mountainInfo.mName};
    
    function initMap() {
      var seoul = { lat: X ,lng: Y };
      map = new google.maps.Map( document.getElementById('mountain_loc-map'), {
          zoom: 12,
          center: seoul
        });

      new google.maps.Marker({
        position: seoul,
        map: map,
        label: 산이름
      });
    }
    
        
  </script>	 

</body>
</html>