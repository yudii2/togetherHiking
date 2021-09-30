<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/mountain/mountain-search.css">
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
       
  <section>   
	<div class="container con_mountain">						
	    <form id="search_mountain" name="search" method = "get"  action="/mountain/detail?mName=${param.mName}" onsubmit="return keyword_check()">
			<h1>지역 또는 산 이름 검색</h1>          
			<i class="fas fa-search" style="font-size: 30px; color: white;"></i>
			<input type="text" class="search_bar" name="mName" value="${param.mName}" />  
	
	    	<div id="loc_bnt_wrap">
	  			 <a type="button" class="loc_bnt" data-div-id='#Gyeonggi'>경기도</a>  
	  			 <a type="button" class="loc_bnt" data-div-id='#Seoul'>서울</a>  
			</div>  
		</form>	 
	     
 
		<%-- 경기도 산  --%>
	   <div class = loc id= "Gyeonggi">
		    <img id="loc_map" src ="/resources/img/경기도지도.PNG"/>	
		    
			<c:set var="currPage" value="${(empty param.gpage)? 1 : param.gpage}" ></c:set>	<!-- 현재 페이지 -->
			<c:set var="startNum" value="${currPage-(currPage-1)%5}" ></c:set>	<!-- 페이지넘버링 처음 번호(1,5,9,,,) -->
	        <c:set var="lastPage" value="${empty gyeonggiMountain ? 1 : Math.ceil(fn:length(gyeonggiMountain)/9)}" ></c:set>	<!-- 총 페이지수 -->               
			     
			     <%-- <div class="arrows" >
			        <c:if test="${startNum > 1}">	<!-- 이전페이지로 넘기기-->
				      <a href="?gpage=${startNum-1}"><i class="fas fa-chevron-left leftArrow"></i></a>
				    </c:if>
				    <c:if test="${startNum <= 1}">	<!-- 현재 1페이지일 경우 이전페이지x -->
				      <span onclick="alert('이전 페이지가 존재하지 않습니다.')"><i class="fas fa-chevron-left leftArrow" ></i></span>
		        	</c:if>
					<c:if test="${currPage < lastPage}">	<!-- 다음페이지로 넘기기 -->
						<a href="?gpage=${currPage-1}"><i class="fas fa-chevron-right rightArrow" ></i></a>	
					</c:if>
					<c:if test="${currPage > lastPage}">	<!-- 현재 마지막페이지일 경우 다음페이지x -->
						<span onclick="alert('다음 페이지가 존재하지 않습니다..')"><i class="fas fa-chevron-right rightArrow" ></i></span>
					</c:if>			
				 </div>	 --%>
   
			    <div class="arrows" >
			        <c:if test="${startNum > 1}">	
				      <a href="?gpage=${startNum-1}"><i class="fas fa-chevron-left leftArrow"></i></a>
				    </c:if>
				    <c:if test="${startNum <= 1}">	<!-- 현재 1페이지 -->
				      <span onclick="alert('이전 페이지가 존재하지 않습니다.')"><i class="fas fa-chevron-left leftArrow" ></i></span>
		        	</c:if>
		        	
					<ul class="pageNum">	<!-- 페이지 넘버링 -->
						<c:forEach var="i" begin="0" end="4">
						<c:if test="${(startNum+i) <= lastPage }">	<!-- 같거나 작을 때만 출력 -->
						<li><a href="?gpage=${startNum + i}" class="num ${currPage == (startNum + i) ? 'point' : '' }">${startNum + i}</a></li>	
						</c:if>
						</c:forEach> 		
					</ul>
		
					<c:if test="${startNum + 4 < lastPage}">	<!-- 총 페이지 수가 5를 넘으면 i=[5-9] -->
						<a href="?gpage=${startNum + 5}"><i class="fas fa-chevron-right rightArrow" ></i></a>	<!-- rightArrow 클릭시 5번page 이동 -->
					</c:if>
					<c:if test="${startNum + 4 >= lastPage}">
						<span onclick="alert('더이상 게시글이 존재하지 않습니다.')"><i class="fas fa-chevron-right rightArrow" ></i></span>
					</c:if>			
				</div>     	 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
		    <div class="mountain_group">		    	
			<c:if test="${not empty gyeonggiMountain}">
				<c:forEach items="${gyeonggiMountain}" var="gyeonggiMountain">
		    		<a class="mountain" href="/mountain/detail?mName=${gyeonggiMountain.mName}">${gyeonggiMountain.mName}</a>
		   		</c:forEach>
			</c:if>				 	   
		 </div>
		</div>
	    
	    
	    
	    
	    
	    
	    
	   

	    
	    
	    
	    
	    
	     <%--  서울 산 --%>
	    <div class = loc id= "Seoul">
		    <img id="loc_map" src ="/resources/img/서울지도.PNG"/>	
		    <c:set var="currPage" value="${(empty param.spage)? 1 : param.spage}" ></c:set>	<!-- 현재 페이지 -->
      		<c:set var="startNum" value="${currPage-(currPage-1)%5}" ></c:set>	<!-- 페이지넘버링 처음 번호(1,5,9,,,) -->
      		<c:set var="lastPage" value="${empty seoulMountain ? 1 : Math.ceil(fn:length(seoulMountain)/9)}" ></c:set>	<!-- 총 페이지수 -->               
			     
			     <div class="arrows" >
			        <c:if test="${startNum > 1}">	<!-- 이전페이지로 넘기기-->
				      <a href="?spage=${startNum-1}"><i class="fas fa-chevron-left leftArrow"></i></a>
				    </c:if>
				    <c:if test="${startNum <= 1}">	<!-- 현재 1페이지일 경우 이전페이지x -->
				      <span onclick="alert('이전 페이지가 존재하지 않습니다.')"><i class="fas fa-chevron-left leftArrow" ></i></span>
		        	</c:if>
					<c:if test="${currPage < lastPage}">	<!-- 다음페이지로 넘기기 -->
						<a href="?spage=${currPage-1}"><i class="fas fa-chevron-right rightArrow" ></i></a>	
					</c:if>
					<c:if test="${currPage > lastPage}">	<!-- 현재 마지막페이지일 경우 다음페이지x -->
						<span onclick="alert('다음 페이지가 존재하지 않습니다..')"><i class="fas fa-chevron-right rightArrow" ></i></span>
					</c:if>			
				 </div>	
				 
		    <div class="mountain_group">		    		    			
			<c:if test="${not empty seoulMountain}">
				<c:forEach items="${seoulMountain}" var="seoulMountain">
		    		<a class="mountain" href="/mountain/detail?mName=${seoulMountain.mName}">${seoulMountain.mName}</a>
		   		</c:forEach>
			</c:if>	
			</div>
		</div>			 	   
	</div>		
  </section>  

<script type="text/javascript">

//경기 서울 넘어가는 코드 
let $ = (selector, text) =>{
	if(text){
		document.querySelector(selector).innerHTML += `${text}<br>`
	};
	return document.querySelector(selector);
}

document.querySelectorAll('a[type=button]').forEach(e => {
	
	e.addEventListener('click',event =>{
		document.querySelectorAll('.loc').forEach(e =>{
			e.style.display ='none';
			//모두 none으로 두고, 라디오버튼에 선택 된 항목만 디스플레이값을 flex로 설정해서 나타나게함
			$(event.target.dataset.divId).style.display='flex';
		});
	});
});


//서치바 코드
function keyword_check(){
  if(document.search.mName.value==''){
  alert('산이름을 입력하세요'); 
  document.search.mName.focus();
  return false; 
  }else return true;
 };

 
</script>
    

</body>
</html>