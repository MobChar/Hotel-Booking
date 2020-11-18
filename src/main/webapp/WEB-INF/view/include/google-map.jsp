<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<style>
#map {
	height: 470px;
}
#mapWrapper {
	height: 600px;
}
</style>
<body>
	<div id="mapModal" class="modal fade" role="dialog"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog  modal-lg">
			<div class="modal-content text-center" id="mapWrapper">
				<div class="modal-header">
					<h4 class="modal-title">Chọn điểm đến của bạn</h4>
				</div>
				<div class="modal-body">
					<div id="map"></div>
					<div class="row">
						<button  class="btn btn-primary" data-dismiss="modal" style="margin-top:7px">OK</button>
					</div>
				</div>
				
			</div>
		</div>

	</div>
	<script>
		// Note: This example requires that you consent to location sharing when
		// prompted by your browser. If you see the error "The Geolocation service
		// failed.", it means you probably did not give permission for the browser to
		// locate you.
		var locationSuccessed;
		var map, pos;
		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				center : {
					lat : -34.397,
					lng : 150.644
				},
				zoom : 15
			});
			 var marker = new google.maps.Marker({
                 position: {
					lat : -34.397,
					lng : 150.644
				}, 
                 map: map, 
                 title: "Drag Me",
                 draggable: true
             });
			
			 google.maps.event.addListener(marker, "dragend", function(event) {
                pos= {
						lat : marker.getPosition().lat(),
						lng : marker.getPosition().lng()
					};
                map.setCenter(pos);
				marker.setPosition(pos);
				
				var url="https://us1.locationiq.com/v1/reverse.php?key=pk.db53bd0fabd5cca7983965a45b6655a7&lat="+pos.lat+"&lon="+pos.lng+"&format=json";
				
				$.ajax({
					url : url,
					type : 'GET',
					data : '',
					contentType : false,
					processData : false,
					success : locationSuccessed,
					error : function(xhr, ajaxOptions, thrownError) {
						alert("Unable to load address from location")
					}
				});	
			
				
             });

			// Try HTML5 geolocation.
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					pos = {
						lat : position.coords.latitude,
						lng : position.coords.longitude
					};
					
	                map.setCenter(pos);
					marker.setPosition(pos);
					
					var url="https://us1.locationiq.com/v1/reverse.php?key=pk.db53bd0fabd5cca7983965a45b6655a7&lat="+pos.lat+"&lon="+pos.lng+"&format=json";
					
					$.ajax({
						url : url,
						type : 'GET',
						data : '',
						contentType : false,
						processData : false,
						success : locationSuccessed,
						error : function(xhr, ajaxOptions, thrownError) {
							alert("Unable to load address from location")
						}
					});	

				}, function() {
					handleLocationError(true, infoWindow, map.getCenter());
				});
			} else {
				// Browser doesn't support Geolocation
				handleLocationError(false, infoWindow, map.getCenter());
			}
		}

		function handleLocationError(browserHasGeolocation, infoWindow, pos) {
			alert("Your browser doesn\'t support geolocation.");
		}
	</script>
	<script defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCxotr1M867_NIQ3fiDPVgSR9DY2KBJXtE&callback=initMap">
		
	</script>

</body>
</html>