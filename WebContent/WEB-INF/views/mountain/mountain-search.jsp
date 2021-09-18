<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<<<<<<< HEAD
<link rel="stylesheet" href="/resources/css/mountain/mountain_search.css">
=======
<link rel="stylesheet" href="/resources/css/mountain/mountain-search.css">
>>>>>>> branch 'main' of https://github.com/yudii2/togetherHiking.git
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
			  <a class="mountain" href="/mountain/detail">가학산</a>  
			  <a class="mountain" href="/mountain/detail">각골산</a>
			  <a class="mountain" href="/mountain/detail">각흘산</a>
		    </div>
		    <div class="mountain_group">
			  <a class="mountain" href="/mountain/detail">갈기산</a>
			  <a class="mountain" href="/mountain/detail">갑악산</a>
			  <a class="mountain" href="/mountain/detail">갑산</a>
		    </div>
		    <div class="mountain_group">
			  <a class="mountain" href="/mountain/detail">개명산</a>
			  <a class="mountain" href="/mountain/detail">강씨봉</a>
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
			  <a class="mountain" href="/mountain/detail">개화산</a>  
			  <a class="mountain" href="/mountain/detail">고은산</a>
			  <a class="mountain" href="/mountain/detail">관악산</a>
			  <a class="mountain" href="/mountain/detail">구룡산</a>
	   		</div>
	    </div>	
    </div>
    
<script type="text/javascript">
let $ = (selector, text) =>{
	if(text){
		document.querySelector(selector).innerHTML += `${text}<br>`
	}
	return document.querySelector(selector);
}

document.querySelectorAll('a[type=button]').forEach(e => {
	
	e.addEventListener('click',event =>{
		document.querySelectorAll('.loc').forEach(e =>{
			e.style.display ='none';
			//모두 none으로 두고, 라디오버튼에 선택 된 항목만 디스플레이값을 block으로 설정해서 나타나게함
			$(event.target.dataset.divId).style.display='block';
		})
	})
})

</script>
    
</section>
</body>
</html>