<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div id="map" style="width:100%; height: 100vh;"></div>
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB0BynjFSEnK1evu8mQktPf2KwJjkHcvH0&callback=initMap&region=kr"></script>
  
  
  <script>

    
    var map;

    function initMap() {
      var seoul = { lat: 37.9417382 ,lng: 126.9689597 };
      map = new google.maps.Map( document.getElementById('map'), {
          zoom: 12,
          center: seoul
        });

      new google.maps.Marker({
        position: seoul,
        map: map,
        label: "서울 중심 좌표"
      });
    }
    
    
    
  </script>
</body>
</html>