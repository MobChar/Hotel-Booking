<%@ page language="java" 
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>HOTELL</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="" />

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<link
	href="https://fonts.googleapis.com/css?family=Quicksand:300,400,500,700"
	rel="stylesheet">

<!-- Animate.css -->
<link rel="stylesheet" href="/css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="/css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="/css/bootstrap.css">

<!-- Magnific Popup -->
<link rel="stylesheet" href="/css/magnific-popup.css">

<!-- Flexslider  -->
<link rel="stylesheet" href="/css/flexslider.css">

<!-- Owl Carousel -->
<link rel="stylesheet" href="/css/owl.carousel.min.css">
<link rel="stylesheet" href="/css/owl.theme.default.min.css">

<!-- Date Picker -->
<link rel="stylesheet" href="/css/bootstrap-datepicker.css">
<!-- Flaticons  -->
<link rel="stylesheet" href="/fonts/flaticon/font/flaticon.css">

<!-- Theme style  -->
<link rel="stylesheet" href="/css/style.css">


<!-- Modernizr JS -->
<script src="/js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="/js/respond.min.js"></script>
	<![endif]-->
<style>
.loader, .loader:before, .loader:after {
	border-radius: 50%;
}

.loader {
	color: #ffffff;
	font-size: 11px;
	text-indent: -99999em;
	margin: 55px auto;
	position: relative;
	width: 10em;
	height: 10em;
	box-shadow: inset 0 0 0 1em;
	-webkit-transform: translateZ(0);
	-ms-transform: translateZ(0);
	transform: translateZ(0);
}

.loader:before, .loader:after {
	position: absolute;
	content: '';
}

.loader:before {
	width: 5.2em;
	height: 10.2em;
	background: #0dc5c1;
	border-radius: 10.2em 0 0 10.2em;
	top: -0.1em;
	left: -0.1em;
	-webkit-transform-origin: 5.1em 5.1em;
	transform-origin: 5.1em 5.1em;
	-webkit-animation: load2 2s infinite ease 1.5s;
	animation: load2 2s infinite ease 1.5s;
}

.loader:after {
	width: 5.2em;
	height: 10.2em;
	background: #0dc5c1;
	border-radius: 0 10.2em 10.2em 0;
	top: -0.1em;
	left: 4.9em;
	-webkit-transform-origin: 0.1em 5.1em;
	transform-origin: 0.1em 5.1em;
	-webkit-animation: load2 2s infinite ease;
	animation: load2 2s infinite ease;
}

@
-webkit-keyframes load2 { 0% {
	-webkit-transform: rotate(0deg);
	transform: rotate(0deg);
}

100






%
{
-webkit-transform






:






rotate




(






360deg






)




;
transform






:






rotate




(






360deg






)




;
}
}
@
keyframes load2 { 0% {
	-webkit-transform: rotate(0deg);
	transform: rotate(0deg);
}
100






%
{
-webkit-transform






:






rotate




(






360deg






)




;
transform






:






rotate




(






360deg






)




;
}
}
</style>
</head>
<body>
	<jsp:include page="include/header.jsp" />
		
		<div class="colorlib-wrap">
			<div class="container">
				<div class="row">
					<div class="col-md-9">
						<div class="row">
							<div class="col-md-12">
								<div class="wrap-division">
									<div class="col-md-12 col-md-offset-0  animate-box">
										<div class="hotel-entry" id="${hotel.id}">
											<a href="${hotel.id}" class="hotel-img"
												style="background-image: url(${hotel.image.fileName});">
											</a>
											<div class="desc">
											
											
												<h3 >
													<strong>${hotel.name}</strong>
												</h3>
												<strong class="address"><i class="icon icon-location" ></i>${hotel.address}</strong>
												<br><br>
												<p>${hotel.description}</p>
												<strong>Loại chỗ nghỉ: </strong><span>${hotel.category.category}</span>
												<br>
												<strong>Cơ sở vật chất:</strong>
												<ul>
													<c:forEach items="${hotelFacilities}" var="facility">
														<li>${facility.name}</li>
													</c:forEach>
												</ul>
												
											</div>
										</div>
									</div>
									<div class="col-md-12 col-md-offset-0 heading2 animate-box">
										<h2>Các loại phòng</h2>
									</div>
									<div class="row">
										<c:forEach items="${rooms}" var="room">
											<div class="col-md-12 animate-box">
												<div class="room-wrap">
													<div class="row">
														<div class="col-md-6 col-sm-6">
															<div class="room-img"
																style="background-image: url(${room.image.fileName});">
															</div>
														</div>
														<div class="col-md-6 col-sm-6">
															<div class="desc">
																<h2 class="roomType">${room.name}</h2>
																<p class="price">
																	<span>${room.price}$</span> <small>/ đêm</small>
																</p>
																<h4><span class='text-danger'>${services.getCountAvailableRoomNumber(room.id,checkIn,checkOut)}</span> phòng còn trống trong thời gian từ: </h4>
																 <p><jsp:useBean id="chIn" class="java.util.Date"/>
<fmt:formatDate value="${checkIn}" type="date" pattern="dd-MM-yyyy"/> tới <jsp:useBean id="chOut" class="java.util.Date"/> <fmt:formatDate value="${checkOut}" type="date" pattern="dd-MM-yyyy"/></p>
															
																<button type="button" class="btn btn-primary bookingBtn"
																	data-toggle="modal" data-target="#bookingModal">Đặt phòng ngay !</button>
															</div>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>


					<!--Booking Modal-->
					<div id="bookingModal" class="modal fade" role="dialog"
						data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog  modal-lg">
							<c:if test="${empty account}">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Booking</h4>
									</div>
									<div class="modal-body">
										<h4>Hãy đang nhập để thực hiện chức năng này</h4>
									</div>
								</div>
							</c:if>

							<c:if test="${not empty account}">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Đặt phòng</h4>
									</div>
									<div class="modal-body row">
										<form id="form1" class="col-md-6" data-toggle="validator" role="form">
											<input id="bookingModalRoomId" type="hidden"
												class="form-control" name="roomId">
											<div class="form-group">
												<label for="pwd">Loại phòng:</label> <input
													id="bookingModalRoomType" type="text" class="form-control"
													readonly>
											</div>


											<div class="form-group">
												<label for="pwd">Số CMND/CCCD:</label></small> <input pattern="^([0-9]{9})|([0-9]{12})$"
													type="text" class="form-control" name="idenCardNum" placeholder="9 hoặc 12 chữ số" required>
											</div>
											<div class="form-group">
												<label for="pwd">Họ và tên:</label> <input type="text" data-minlength="2"
													class="form-control" name="fullName" placeholder="Không được để trống ít nhất 2 kí tự" required>
											</div>


										</form>

										<form id="form2" class="col-md-6">
											<input id="bookingModalRoomId" type="hidden"
												class="form-control">

											<div class="form-group">
												<label for="date"><i class="icon icon-calendar2"></i>
													Check-in:</label>
												<div class="form-field">
													<jsp:useBean id="date" class="java.util.Date"/>
														<input type="date" id="date" class="form-control" min="1000-01-01" max="3000-12-31"
														placeholder="Check-in date" name="checkIn" value="<fmt:formatDate value="${checkIn}" type="date" pattern="yyyy-MM-dd"/>" readonly>
													
												
												</div>
											</div>

											<div class="form-group">
												<label for="date"><i class="icon icon-calendar2"></i>
													Check-out:</label>
												<div class="form-field">
													<jsp:useBean id="date2" class="java.util.Date"/>
														<input type="date" id="date" class="form-control" min="1000-01-01" max="3000-12-31"
														placeholder="Check-out date" name="checkOut" value="<fmt:formatDate value="${checkOut}" type="date" pattern="yyyy-MM-dd"/>" readonly>
												</div>
											</div>

											<div class="form-group">
												<label for="pwd">Số lượng phòng:</label> <input type="number"
													class="form-control" name="numberOfRoom"  value="1" min="1" max="10">
											</div>



										</form>
										<div class="row text-center">
											<button type="submit" class="btn btn-primary"
												data-toggle="modal" data-target="#confirmBookingModal">Đặt phòng</button>
										</div>
									</div>

								</div>
							</c:if>

						</div>
					</div>

					<!--Confirm Booking Modal-->
					<div id="confirmBookingModal" class="modal fade" role="dialog"
						data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog  modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Đặt phòng</h4>
								</div>
								<div class="modal-body">
									<h4>Bạn có chắc muốn đặt phòng này chứ?</h4>
									<div class="loader text-center"><h4>Xin vui lòng đợi</h4></div>
									


								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-success"
										id="confirmYesToBookingBtn">Yes</button>
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">No</button>
								</div>

							</div>
						</div>
					</div>

				

					<!--Booking Successed Modal-->
					<div id="bookingSuccessedModal" class="modal fade" role="dialog"
						data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog  modal-sm">
							<div class="modal-content">
								<div class="modal-body">
									<h4>Bạn đã đặt phòng thành công</h4>
								</div>
								<div class="modal-footer">
									<div class="text-center">
										<button type="button" class="btn btn-success"
											data-dismiss="modal">OK</button>
									</div>
								</div>

							</div>


						</div>
					</div>

					<!--Booking Failed Modal-->
					<div id="bookingFailedModal" class="modal fade" role="dialog"
						data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog  modal-sm">
							<div class="modal-content">
								<div class="modal-body">
									<h4>Xin lỗi chúng tôi không thể đặt phòng cho bạn lúc này</h4>
								</div>

								<div class="modal-footer">
									<div class="text-center">
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">OK</button>
									</div>
								</div>

							</div>
						</div>
					</div>





					<!-- SIDEBAR-->
				<div class="container">
					<div class="row">
					<c:forEach items="${previewImages}" var="image">
					<div class="col-md-3">
						<div class="sidebar-wrap">
							<div class="row" style="margin-top:10px;">
								<a href="${image.fileName}" target="_blank"
									class="col-md-12"
									style="background-image: url(${image.fileName}); width: 100%; height: 200px; background-size: cover;">
								</a>

							</div>

						</div>
					</div>
					</c:forEach>
					</div>
				</div>
				<jsp:include page="include/comment.jsp" />
				</div>
			</div>
		</div>
	

	
	<jsp:include page="include/footer.jsp" />
	
	

	<!-- jQuery -->
	<script src="/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="/js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="/js/jquery.flexslider-min.js"></script>
	<!-- Owl carousel -->
	<script src="/js/owl.carousel.min.js"></script>
	<!-- Magnific Popup -->
	<script src="/js/jquery.magnific-popup.min.js"></script>
	<script src="/js/magnific-popup-options.js"></script>
	<!-- Date Picker -->
	<script src="/js/bootstrap-datepicker.js"></script>
	<!-- Stellar Parallax -->
	<script src="/js/jquery.stellar.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
	<!-- Main -->
	<script src="/js/main.js"></script>

	<script>
	
		var addressCom=[];
		<c:forEach items="${hotel.placeComponents}" var="placeComponent">
			addressCom.push({ name:"${placeComponent.name}", typeId: ${placeComponent.placeType.id}});
		</c:forEach>
		let hotelId=${hotel.id};
		let addressHtml=$('#'+hotelId+' .address').html();
		for(let i=0;i<addressCom.length;i++){
			addressHtml=addressHtml.replace(addressCom[i].name,'<a href="/hotel?place='+addressCom[i].name+'&placeType='+addressCom[i].typeId+'">'+addressCom[i].name+'</a>');
		}
		$('#'+hotelId+' .address').html(addressHtml);
	
	
		var rooms=[];
		<c:forEach items="${rooms}" var="room">rooms.push({roomId: ${room.id}, roomName: "${room.name}"});</c:forEach>
		console.log(rooms);
		
		$('.bookingBtn').click(
		function() {
			 var index=$('.bookingBtn').index(this);
			 $('#bookingModalRoomType').val(rooms[index].roomName);
			 $('#bookingModalRoomId').val(rooms[index].roomId);
		});
		
		$("#loginBtn").on("click",function(){
			var loginWindow = window.open("/oauth2/authorization/google", "", "width=400,height=200");
			loginWindow.onbeforeunload=function(){
      			window.location.reload();
      		}
		});
		
	
		$('#confirmBookingModal .loader').hide();
		$('#bookingSuccessedModal').modal('hide');
		$('#bookingFailedModal').modal('hide');
		
		$("#confirmYesToBookingBtn").on("click",function(){
			//Get form data
			var unindexed_array = $('#form1, #form2').serializeArray();
    		var indexed_array = {};

		    $.map(unindexed_array, function(n, i){
		        indexed_array[n['name']] = n['value'];
		    });
		    
		   
		    $('#confirmBookingModal').modal('hide');;
		    $('#loadingModal .loader').show();

			$.post("/booking",indexed_array,function(data, status){})
			.done(function() {
				$('#confirmBookingModal .loader').modal('hide');
				$('#bookingModal').modal('hide');
				$('#bookingSuccessedModal').modal('toggle');})
			.fail(function() {
					$('#confirmBookingModal .loader').modal('hide');
					$('#bookingFailedModal').modal('toggle');
			});
					   
		});
		
		
		
		
		
	</script>

</body>
</html>

