<%@ page language="java" 
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
.avatar {
  vertical-align: middle;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

#navbar-list-4{
	background-color:white;
}
.dropdown-menu {
    min-width:0px;
    max-width:100px;
}


</style>
</head>


	<body>
	<div class="colorlib-loader"></div>

<div id="page">
	<nav class="colorlib-nav" role="navigation">
		<div class="top-menu">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-2">
						<div id="colorlib-logo">
							<a href="/">HOTELL</a>
						</div>
					</div>
					<div class="col-xs-10 text-right menu-1">
						<ul>

							<li class="active"><a href="/">Home</a></li>
							<li><a href="/">About</a></li>
							<li><a href="/">Contact</a></li>

							<c:if test="${empty account}">
								<li><button class="btn btn-primary" id="loginBtn">Login</button></li>
							</c:if>
							<c:if test="${not empty account}">
							<li class="dropdown">
								
								  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="background-color:transparent; border-color:transparent;">
								  
								<img src="${account.avatarPath}" class="avatar">
								    <span class="caret"></span>
								  </button>
								  <ul class="dropdown-menu"  aria-labelledby="dropdownMenu1">
								    <li><small><a href="/logout" style="color: black;">Đăng xuất</a></small></li>

								  </ul>
								
							</li>
							</c:if>
							<li></li>

						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>
	<aside id="colorlib-hero">
		<div class="flexslider">
			<ul class="slides">
				<li style="background-image: url(/image/cover-img-4.jpg);">
					<div class="overlay"></div>
					<div class="container-fluid">
						<div class="row">
							<div
								class="col-md-6 col-md-offset-3 col-sm-12 col-xs-12 slider-text">
								<div class="slider-text-inner text-center">
									<h1>DỊCH VỤ TÌM CHỖ NGHỈ HOTELL</h1>
								</div>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</aside>
	<!-- Bootstrap -->
	<script src="/js/bootstrap.min.js"></script>
	<!-- jQuery -->
	<script src="/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="/js/jquery.easing.1.3.js"></script>
	
	
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

	<!-- Main -->
	<script src="/js/main.js"></script>
	
	<script>
	
	 
				$("#loginBtn").on("click",
						function() {
							var loginWindow = window.open(
									"/oauth2/authorization/google", "",
									"width=400,height=200");
							//loginWindow.onbeforeunload = function() {
							//	alert("Hello");
							//	window.location.reload();
							//}
						});
	</script>
	</body>
</html>