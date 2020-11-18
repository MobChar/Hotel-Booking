<%@ page language="java" 
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
input[type="date"]::-webkit-calendar-picker-indicator {
  color: transparent;
  background: none;
  z-index: 1;
}
</style>
</head>
<body>
	<!-- SIDEBAR-->
	<div class="col-md-3">
		<div class="sidebar-wrap">
			<div class="side search-wrap animate-box">
				<h3 class="sidebar-heading">Bạn muốn đi đâu ?</h3>
				<form id="form1" class="colorlib-form">
					<input  type="hidden"	class="form-control" name="location">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="date">Điểm đến:</label>
								<div class="form-field">
									<i class="icon icon-location"></i> <input type="text"
										id="location" class="form-control" name="locationAddress"
										placeholder="Điểm đến" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="date">Nhận phòng:</label>
								<div class="form-field">
								<i class="icon icon-calender"></i>
									 <input type="date"
										class="form-control" name="checkIn"
										>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="date">Trả phòng:</label>
								<div class="form-field">
									<i class="icon icon-calender"></i>
									 <input type="date"
										class="form-control" name="checkOut"
										>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="distance">Khoảng cách tối đa:</label>
								<div class="form-field">
									<i class="icon icon-arrow-down3"></i> <select name="distance"
										class="form-control">
										<option value="1" selected style="color: black;">1 km</option>
										<option value="5" style="color: black;">5 km</option>
										<option value="10" style="color: black;">10 km</option>
										<option value="20" style="color: black;">20 km</option>
										<option value="30" style="color: black;">30 km</option>
										<option value="3000" style="color: black;">3000 km</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<input type="submit" name="submit" id="submit" value="Tìm chỗ nghỉ"
								class="btn btn-primary btn-block">
						</div>
					</div>
				</form>
			</div>
			<div class="side animate-box">
				<div class="row">
					<div class="col-md-12">
						<h3 class="sidebar-heading">Loại chỗ nghỉ</h3>
						<form method="post" id="form4" class="colorlib-form-2">
							<c:forEach items="${categories}" var="category">
								<div class="form-check">
									<input type="checkbox" class="form-check-input"
										name="category[]" value="${category.id}" id="exampleCheck1"
										checked> <label class="form-check-label"
										for="exampleCheck1">
										<h4 class="place">${category.category}</h4>
									</label>
								</div>
							</c:forEach>

						</form>
					</div>
				</div>
			</div>
			<div class="side animate-box">
				<div class="row">
					<div class="col-md-12">
						<h3 class="sidebar-heading">Cơ sở vật chất</h3>
						<form method="post" id="form5" class="colorlib-form-2">
							<c:forEach items="${facilities}" var="facility">
								<div class="form-check">
									<input type="checkbox" class="form-check-input"
										id="exampleCheck1" name="facility[]" value="${facility.id}">
									<label class="form-check-label" for="exampleCheck1">
										<h4 class="place">${facility.name}</h4>
									</label>
								</div>
							</c:forEach>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="google-map.jsp" />
	
	<!--Booking Modal-->
	<div id="failedbookingModal" class="modal fade" role="dialog"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog  modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Booking</h4>
				</div>
				<div class="modal-body row">
					<div class="row text-center">
						<button type="button" class="btn btn-danger"
										data-dismiss="modal">OK</button>
					</div>
				</div>

			</div>


		</div>
	</div>
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="js/jquery.flexslider-min.js"></script>
	<!-- Owl carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>
	<!-- Date Picker -->
	<script src="js/bootstrap-datepicker.js"></script>
	<!-- Stellar Parallax -->
	<script src="js/jquery.stellar.min.js"></script>

	<!-- Main -->
	<script src="js/main.js"></script>

	<script>
		function today() {
		    return new Date();
		}

		function tomorrow() {
	    	return new Date(today().getTime() + 24 * 60 * 60 * 1000);
		}
		function getFormattedDate(date) {
			console.log(date.getDate());
			let str=date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0" + date.getDate()).slice(-2);
		    return  str;
		}
		
		$('input[type=date]').val(getFormattedDate(tomorrow()));
		
		$("#mapModal").modal('hide');
		var locationInput = $("#form1 input[name='locationAddress']");
		locationInput.on("click", function() {
			$("#mapModal").modal('toggle');	
			locationSuccessed=function(data){
				 $("#form1 input[name='location']").val(pos.lat+", "+pos.lng);
					$("#form1 input[name='locationAddress']").val(data['display_name']);
			}
			
			
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
		
		

	
		
		
		$('#failedbookingModal').modal('hide');
		
		
		console.log($('#form1, #form2, #form3, #form4, #form5').serialize());

		$('#form1').on("submit",
			function(e) {
					e.preventDefault();
					//Check input
					 var checkInStr= $('input[type=date]').eq(0).val().split('-');
					 var checkOutStr = $('input[type=date]').eq(1).val().split('-');
					 var checkIn=new Date(parseInt(checkInStr[0]),parseInt(checkInStr[1])-1,parseInt(checkInStr[2]));
					 var checkOut=new Date(parseInt(checkOutStr[0]),parseInt(checkOutStr[1])-1,parseInt(checkOutStr[2]));
					
					  var now = new Date();
						
					  var locationStr=$('input[name=location]').val();
					  var pattern=/^[+-]?\d+(\.\d+)?\s*,\s* [+-]?\d+(\.\d+)?$/;
					
					  
					  if(!pattern.test(locationStr)){
						$('#failedbookingModal h4').text("Location must not empty");
						$('#failedbookingModal').modal('toggle');
						return;
						  
					  }
					  
					  if (checkIn <= now) {
						  console.log(checkIn);
						  console.log(now);
						 $('#failedbookingModal h4').text("Check in must in the future");
						 $('#failedbookingModal').modal('toggle');
						 return;
					  
					  } 
					  if (checkOut <= now) {
						$('#failedbookingModal h4').text("Check out must in the future");
						$('#failedbookingModal').modal('toggle');
						return;
						  
					}
					 if(checkIn>checkOut){
						  $('#failedbookingModal h4').text("Check out date must greater than check in date");
						  $('#failedbookingModal').modal('toggle');
						  return;
					  }
					var str = '/hotel?'
							+ $('#form1, #form2, #form3, #form4, #form5')
									.serialize();
					window.location.replace(str);
		});

	
		
		
	</script>


</body>
</html>