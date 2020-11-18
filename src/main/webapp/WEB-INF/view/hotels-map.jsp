<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <script src="js/jquery.min.js"></script>
<meta charset="utf-8">
<title>Insert title here</title>
 <style>
 #map {
        height: 100%;
        width:80%;
        float:right;
      }
 #hotelList{
 		height:100%;
 		width:20%;
 		float:left;
 		overflow: scroll;
 }
 
 .hotelRow{
 	height:20%;
 	width:100%;
 }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
 </style>
</head>
<body>
<div id="hotelList">
	<c:forEach items="${hotels}" var="hotel">
		<div class="hotelRow")"
		style="background-image: url(${hotel.image.fileName}); background-size: 100% 100%;">
		<span style="color:white;">${hotel.name}</span></div>
	</c:forEach>
</div>
<div id="map"></div>
<!-- jQuery -->
	
<script>
      // Note: This example requires that you consent to location sharing when
      // prompted by your browser. If you see the error "The Geolocation service
      // failed.", it means you probably did not give permission for the browser to
      // locate you.
      var cusLocation={lat: ${location.latitude}, lng:${location.longitude}};
      var map, infoWindow,pos;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: cusLocation,
          zoom: 6
        });
        infoWindow = new google.maps.InfoWindow;
        infoWindow.setPosition(cusLocation);
        infoWindow.setContent('Điểm đến');
        infoWindow.open(map);
        map.setCenter(location);
        
        
        var black = 'http://localhost:8080/image/black-google-map-icon.png';
        var red = 'http://localhost:8080/image/red-google-map-icon.png';
       
        var pos=[];
        <c:forEach items="${hotels}" var="hotel">pos.push({lat:${hotel.latitude},lng:${hotel.longitude}});</c:forEach>
        $('.hotelRow').each(function(i,o){
        	
        	var marker=new google.maps.Marker({
        	    position: pos[i],
        	    map: map,
        	    icon: black
        	  });
        	marker.setMap(map);
        	
        	o.onmouseover=function(){
        		marker.setIcon(red);
        	};
        	o.onmouseout=function(){
        		marker.setIcon(black);
        	};
        });
       
      }

      function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
                              'Error: The Geolocation service failed.' :
                              'Error: Your browser doesn\'t support geolocation.');
        infoWindow.open(map);
      }
      
    </script>
    <script defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCxotr1M867_NIQ3fiDPVgSR9DY2KBJXtE&callback=initMap">
    </script>
    
  
    <script>  
   
    	 

  </script>
</body>
</html>