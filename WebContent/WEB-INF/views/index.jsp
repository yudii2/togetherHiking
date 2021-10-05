<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="resources/css/slick.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/slick-theme.css"/>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" href="/resources/css/index.css">
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script defer type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script defer type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script defer type="text/javascript" src="resources/js/slick.min.js"></script>
<script defer type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
  
</head>
<body>
	<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>
<section class="main">
	<div class="main_txt">
		<span><h1>우리는 등산을 통해</h1><h1>바쁜 일상 속에서 여유로움을 찾고</h1><h1> 새로움을 알아가며 새로운 인연들과</h1><h1> 함께하는 법을 배웁니다.</h1></span>
	</div>
</section>
<section class="sec02">
<img src="/resources/img/banner2.jpg">

</section>
<section class="sec03">	
<div class="container your-class">
<c:forEach items="${schedules}" var="schedule">
  <div class="schedule">
	<h1>${schedule.mountainName } 모임</h1>
	<div class="info">${schedule.info}</div>
	<div class="date">모임날짜 : ${schedule.dDay}<i class="fas fa-mountain"></i></div>
  </div>
</c:forEach>

</div>
</section>

<section class="container sec04">
	<img src="/resources/img/usage.jpg">
</section>
<section class="sec05">
	<img src="/resources/img/banner1.jpg">
</section>



<script type="text/javascript">

$(document).ready(function(){
	  $('.your-class').slick({
		  dots: true,
		  infinite: false,
		  speed: 300,
		  slidesToShow: 4,
		  slidesToScroll: 4,
		  responsive: [
		    {
		      breakpoint: 1024,
		      settings: {
		        slidesToShow: 3,
		        slidesToScroll: 3,
		        infinite: true,
		        dots: true
		      }
		    },
		    {
		      breakpoint: 600,
		      settings: {
		        slidesToShow: 2,
		        slidesToScroll: 2
		      }
		    },
		    {
		      breakpoint: 480,
		      settings: {
		        slidesToShow: 1,
		        slidesToScroll: 1
		      }
		    }
		    // You can unslick at a given breakpoint now by adding:
		    // settings: "unslick"
		    // instead of a settings object
		  ]
	  });
	});



</script>
</body>
</html>
