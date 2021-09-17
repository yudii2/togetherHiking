<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/mountain/mountain_search.css">
<link rel="styleseet" href = "/resources/js/mountain/mountain-search.js">
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>

  <section class="container">   
   <form id="search_mountain"> 지역 또는 산 이름 검색 
    	<div>
    		<i class="fas fa-search" style="font-size: 1em; color: white;"></i>
    		<input type="text" class="search_bar">  
    	</div> 	     
    	<div id="loc_bnt_wrap">
  			 <a type="button" class="loc_bnt" data-div-id='#Gyeonggi'>경기도</a>  
  			 <a type="button" class="loc_bnt" data-div-id='#seoul'>서울</a>  
		</div>  
    </form>
     
 	 <%-- 경기도 산  --%>
   <div class = loc id= "Gyeonggi">
	    <div id="loc_map" style="background-image: url('img/경기도지도.PNG'); background-size: cover;">
	    	이게 api로 올 수 있는지.. 일단 지역 지도 넣어둠
	    </div>        <%-- 여쭤보고 수정 --%>
	   <div class = mountain_wrapper>							
		    <div class="mountain_group">
			  <a class="mountain" href="mountain_detail_page.html">가학산</a>  
			  <a class="mountain" href="mountain_detail_page.html">각골산</a>
			  <a class="mountain" href="mountain_detail_page.html">각흘산</a>
		    </div>
		    <div class="mountain_group">
			  <a class="mountain" href="mountain_detail_page.html">갈기산</a>
			  <a class="mountain" href="mountain_detail_page.html">갑악산</a>
			  <a class="mountain" href="mountain_detail_page.html">갑산</a>
		    </div>
		    <div class="mountain_group">
			  <a class="mountain" href="mountain_detail_page.html">개명산</a>
			  <a class="mountain" href="mountain_detail_page.html">강씨봉</a>
	   		</div>
	    </div>			
	</div>
    
     <%--  서울 산 --%>
    <div class = loc id= "seoul">
	    <div id="loc_map" style="background-image: url('img/서울지도.PNG'); background-size: cover;">
		    	이게 api로 올 수 있는지.. 일단 지역 지도 넣어둠
		</div>        <%-- 여쭤보고 수정 --%>
	    <div class = mountain_wrapper >							
		    <div class="mountain_group">
			  <a class="mountain" href="mountain_detail_page.html">개화산</a>  
			  <a class="mountain" href="mountain_detail_page.html">고은산</a>
			  <a class="mountain" href="mountain_detail_page.html">관악산</a>
			  <a class="mountain" href="mountain_detail_page.html">구룡산</a>
	   		</div>
	    </div>	
    </div>
    
</section>
</body>
</html>