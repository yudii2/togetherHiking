<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
</body>
</html>