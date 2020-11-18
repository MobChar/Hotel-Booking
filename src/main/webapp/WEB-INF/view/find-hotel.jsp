<%@ page language="java" 
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css">

<!-- Magnific Popup -->
<link rel="stylesheet" href="css/magnific-popup.css">

<!-- Flexslider  -->
<link rel="stylesheet" href="css/flexslider.css">

<!-- Owl Carousel -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">

<!-- Date Picker -->
<link rel="stylesheet" href="css/bootstrap-datepicker.css">
<!-- Flaticons  -->
<link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">

<!-- Theme style  -->
<link rel="stylesheet" href="css/style.css">

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

</head>
<body>

		<jsp:include page="include/header.jsp" />

		<div class="colorlib-wrap">

			<div class="container">
				<div class="row">
				
					<div class="col-md-9">
						<div class="row">
							<div class="wrap-division">
								<div  class="col-md-12 col-md-offset-0 heading2 animate-box">
									<a id="hotels-map" target="_blank" rel="noopener noreferrer" style="background: url('/image/hotel-map.png') no-repeat left center; 
									background-size: contain;width:64px; height:64px; float: right;"></a>
									<p style="float:left; font-size:21px;   line-height: 23px; vertical-align: middle; height:25px; margin-top:30px;">${searchResultCount} Chỗ nghỉ</p>
								</div>
							<c:forEach items="${hotels}" var="hotel">
								<div class="col-md-6 col-sm-6 animate-box">
									<div class="hotel-entry" id="${hotel.getHotelInfo().id}">
										<a href="hotel/${hotel.getHotelInfo().id}" class="hotel-img"
											style="background-image: url(${hotel.getHotelInfo().image.fileName});">
											
										</a>
										<div class="desc">
											<h3>
												<a href="hotel/${hotel.getHotelInfo().id}">${hotel.getHotelInfo().name} <small><i class="icon icon-location"><fmt:formatNumber type="number" maxFractionDigits="1" value="${hotel.distance}"/> km</i></small></a>
											</h3>
												<p class="address">${hotel.getHotelInfo().address} </p>
										</div>
									</div>
								</div>
							</c:forEach>

							

							

								
							

								

								
								
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 text-center">
								<ul class="pagination">
								<c:forEach varStatus="i" begin="1" end="${pageCount }">
									<c:if test="${i.index==currentPage}">
										<li class="active"><a onclick="goToPage(${i.index});">${i.index}</a></li>
									</c:if>
									<c:if test="${i.index!=currentPage}">
										<li><a onclick="goToPage(${i.index});">${i.index}</a></li>
									</c:if>
										
								</c:forEach>
								</ul>
							</div>
						</div>
					</div>

					<!-- SIDEBAR-->
					<jsp:include page="include/find-hotel-form.jsp" />
				</div>
			</div>
		</div>


		<jsp:include page="include/footer.jsp" />


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
		var hotelIds={hotelIds:[]};
		var hotelId;
		var addressHtml;
		<c:forEach items="${hotels}" var="hotel">
			hotelIds.hotelIds.push(${hotel.getHotelInfo().id});
			var addressCom=[];
			<c:forEach items="${hotel.getHotelInfo().placeComponents}" var="placeComponent">
				addressCom.push({ name:"${placeComponent.name}", typeId: ${placeComponent.placeType.id}});
			</c:forEach>
			hotelId=${hotel.getHotelInfo().id};
			addressHtml=$('#'+hotelId+' .address').html();
			for(let i=0;i<addressCom.length;i++){
				addressHtml=addressHtml.replace(addressCom[i].name,'<a href="/hotel?place='+addressCom[i].name+'&placeType='+addressCom[i].typeId+'">'+addressCom[i].name+'</a>');
			}
			$('#'+hotelId+' .address').html(addressHtml);
		</c:forEach>
		console.log(hotelIds);
		$('#hotels-map').attr({ href:'/hotels-map?location=${locationStr}'+'&'+$.param(hotelIds)});
		
		
		function goToPage(page){
			let url = new URL(window.location);
			let params = new URLSearchParams(url.search.slice(1));
			console.log(url.search.slice(1));

			// Delete the foo parameter.
			params.delete('page'); //Query string is now: 'bar=2'
			params.set('page',page)
			window.location.replace(location.protocol + '//' + location.host + location.pathname+'?'+params.toString());
		}
		
	</script>




</body>
</html>

