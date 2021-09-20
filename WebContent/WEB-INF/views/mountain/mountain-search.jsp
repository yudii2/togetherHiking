<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/mountain/mountain-search.css">
<link rel="styleseet" href = "/resources/js/mountain/mountain-search.js">
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
       
  <section class="container">   
    <form id="search_mountain" name="search" method = "get"  action ="/mountain/detail" onsubmit="return keyword_check()">
		지역 또는 산 이름 검색 <br>            <%-- action 지금은 페이지만 정해놓음. 나중에 api로 받은 파람값?(산이름)으로보내기. 확인 후 수정하기--%>
		<i class="fas fa-search" style="font-size: 1em; color: white;"></i>
		<input type="text" class="search_bar" name="mName" >  

    	<div id="loc_bnt_wrap">
  			 <a type="button" class="loc_bnt" data-div-id='#Gyeonggi'>경기도</a>  
  			 <a type="button" class="loc_bnt" data-div-id='#seoul'>서울</a>  
		</div>  
	</form>	 
     
 	 <%-- 경기도 산  --%>
   <div class = loc id= "Gyeonggi">
	    <img id="loc_map" src ="https://blogfiles.pstatic.net/MjAyMTA5MTdfMTcw/MDAxNjMxODc3ODM5MjIz.57-bm-LMspYMLmYYKFu8MloXugKGbdMA8bOcQ-W30YAg.5scsakRs8ktTCCfKyUpNNpyIsbyVRljFfSQ5dVqcKuYg.PNG.lyrmt0320/%EA%B2%BD%EA%B8%B0%EB%8F%84%EC%A7%80%EB%8F%84.PNG"/>
	   <div class = mountain_wrapper>							
		    <div class="mountain_group">
			  <a class="mountain" href="/mountain/detail">가학산</a>  
			  <a class="mountain" href="/mountain/detail">각흘산</a>
			  <a class="mountain" href="/mountain/detail">갈기산</a>
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
	    <img id="loc_map" src ="https://blogfiles.pstatic.net/MjAyMTA5MTdfMjMx/MDAxNjMxODc3ODMzNTQz.nEDibuUED7s2a0pdCrG5ckMUYvgDqmwiW2VxbWjPLGEg.M4KCtSms16oopNtzj3Uresd3Ys12BpuuvNsv_6-fMDYg.PNG.lyrmt0320/%EC%84%9C%EC%9A%B8%EC%A7%80%EB%8F%84.PNG"/>
	    <div class = mountain_wrapper >							
		    <div class="mountain_group">
			  <a class="mountain" href="/mountain/detail">개화산</a>  
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
});


function keyword_check(){
  if(document.search.mName.value==''){
  alert('산이름을 입력하세요'); 
  document.search.mName.focus();
  return false; 
  }else return true;
 };

 
</script>
    
</section>
</body>
</html>