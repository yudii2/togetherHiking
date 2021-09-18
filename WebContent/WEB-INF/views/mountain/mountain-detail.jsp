<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
=======
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file ="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href ="/resources/css/mountain/mountain-detail.css">
</head>   
<body>
  <%@ include file = "/WEB-INF/views/include/fixed-header.jsp"%>
  <section class="container">  
   <form id="search_mountain"> 지역 또는 산 이름 검색 
    	<div>
    		<i class="fas fa-search" style="font-size: 1em; color: white;"></i>
    		<input type="text" class="search_bar">  
    	</div> 	    
    	<div id="loc_bnt_wrap">
    		<a class="loc_bnt" href="/mountain/search">지역 선택하러 가기</a>
		</div>  
    </form>
    
    <div id="weather">날씨api</div>			<%-- 등산호 api로 가져 온 산의 주소에 해당하는 날씨 반환 --%>
    
>>>>>>> branch 'main' of https://github.com/yudii2/togetherHiking.git

<<<<<<< HEAD
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file = "/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href = "/resources/css/mountain/mountain_detail.css">
</head>
   
  <body>
  <%@ include file = "/WEB-INF/views/include/fixed-header.jsp"%>
  <section class="container">  
   <form id="search_mountain"> 지역 또는 산 이름 검색 
    	<div>
    		<i class="fas fa-search" style="font-size: 1em; color: white;"></i>
    		<input type="text" class="search_bar">  
    	</div> 	    
    	<div id="loc_bnt_wrap">
    		<a class="loc_bnt" href="/mountain/search">지역 선택하러 가기</a>
		</div>  
    </form>
    
    <div id="weather">날씨api</div>			<%-- 등산호 api로 가져 온 산의 주소에 해당하는 날씨 반환 --%>
    
    <div id="mountain_trail">
	    <select name="mountain_trail">
			 <option value="">등산로를 선택하세요</option>                <%-- 이거 해야하는지? --%>
			 <option value="trail1" >등산로1</option>			<%-- 산마다 꾸려줘야 할 등산로 갯수 다름 --%>
			 <option value="trail2" >등산로2</option>
		</select>
	</div>
		
    <div id="mountain_detail" style="background-image: url('img/관악산.jpg'); background-size: cover;">
	    <div id="mountain_loc-map" >등산로지도 끌어오기api</div>
		<div id="mountain_info">등산로 관련 정보 끌어오기api <hr>
		관악산<br><br><br>
		서울시 한강 남쪽에 우뚝하게 솟아있는 산이며 높이는 632.2m이다.
		그 뒤쪽으로는 청계산, 백운산, 광교산으로 연결되는 한남정맥(漢南正脈)이 이어진다. 
		산 정상부에는 바위로 이루어져 있는데 그 모습이 갓을 쓰고 있는 모습을 닮아 관악산(冠岳山)이라고 부르게 되었다.
		<br><br><br>
		높이 : 632.2m<br><br>
		위치 : 서울 관악구 신림동, 경기 안양·과천의 경계<br><br>
		교통편 : <br><br>
		칼로리 : kcal
		</div>                      <%-- api로 꾸려와야 할 정보임! 디자인 보려고 넣음 --%>
    </div>
    
    
  </section>
=======
		
    <div id="mountain_detail">
	    <div id="mountain_loc-map"  style="background-image: url('img/관악산.jpg'); background-size: cover;">등산로지도 끌어오기api</div>
	    <!-- 등산로 관련 정보 끌어오기api  -->
		<div id="mountain_info">
			<div class="tit_mountain">
				<span class="mountain_name">관악산</span>
				<!-- 요청을 보내 모달창에 데이터 호출 필요...세션에 mountain객체를 저장? -->
				<form action="mountain/detail/course" id="mountain_trail">
					<select name="mountain_trail" class="mountain_trail">
						<option value="" selected disabled>등산로를 선택하세요</option>
						<option value="1">등산로1</option>
						<option value="2">등산로2</option>
					</select>
				</form>
			</div>
			<p>
				${mountain.info}
				서울시 한강 남쪽에 우뚝하게 솟아있는 산이며 높이는 632.2m이다.
				그 뒤쪽으로는 청계산, 백운산, 광교산으로 연결되는 한남정맥(漢南正脈)이 이어진다. 
				산 정상부에는 바위로 이루어져 있는데 그 모습이 갓을 쓰고 있는 모습을 닮아 관악산(冠岳山)이라고 부르게 되었다.
			</p>
			<div class="desc_mountain">
				<h2>높이 : <span>${mountain.mHeight} 632.2m</span></h2>
				<h2>위치 : <span>${mountain.mLoc}서울 관악구 신림동, 경기 안양·과천의 경계</span></h2>
				<h2>교통편 : <span>${mountain.transport}</span></h2>
				<h2>칼로리 : <span>${mountain.calories}</span></h2>
			</div>
		</div>                      <%-- api로 꾸려와야 할 정보임! 디자인 보려고 넣음 --%>
    </div>
    
    
    
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
    
    document.querySelector('.mountain_trail').addEventListener('change',function() {
	    document.querySelector('.modal').style.display='flex';
	    document.querySelector('.overlay').style.display='flex';
    });
    	
    
    
    </script>
>>>>>>> branch 'main' of https://github.com/yudii2/togetherHiking.git
</body>
</html>