<%@ page language="java" 
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						 <h3>Khám phá Việt Nam     <i class="icon icon-compass2" ></i></h3>
						<br>
						<div class="row">
							<div class="wrap-division">
							
								
							<c:forEach items="${places}" var="place">
								<div class="col-md-6 col-sm-6 animate-box">
									<div class="hotel-entry">
										<a href="hotel?place=${place.name}&placeType=${place.placeType.id}" class="hotel-img"
											style="background-image: url(${place.thumb.fileName});">
										</a>
										<div>
											<h4 class="place" style="font-weight: bold">${place.name}</h4>
											<p>${place.hotels.size()} chỗ nghỉ</p>
										</div>
									</div>
								</div>
							</c:forEach>

							

							

								
							

								

								
								
							</div>
						</div>
					</div>

					<jsp:include page="include/find-hotel-form.jsp" />
				</div>
			</div>
		</div>

	<jsp:include page="include/footer.jsp" />






</body>
</html>

