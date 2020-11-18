<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 4 admin, bootstrap 4, css3 dashboard, bootstrap 4 dashboard, ample admin bootstrap 4 dashboard, frontend, responsive bootstrap 4 admin template, material design, material dashboard bootstrap 4 dashboard template">
<meta name="description"
	content="Ample Lite Free Version is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
<meta name="robots" content="noindex,nofollow">
<title>Ample Lite Free Version Template by WrapPixel</title>
<link rel="canonical"
	href="https://www.wrappixel.com/templates/ample-admin-lite/" />
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="/plugins/images/favicon.png">
<!-- Bootstrap Core CSS -->
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Menu CSS -->
<link
	href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
	rel="stylesheet">
<!-- toast CSS -->
<link href="plugins/bower_components/toast-master/css/jquery.toast.css"
	rel="stylesheet">
<!-- morris CSS -->
<link href="plugins/bower_components/morrisjs/morris.css"
	rel="stylesheet">
<!-- chartist CSS -->
<link href="plugins/bower_components/chartist-js/dist/chartist.min.css"
	rel="stylesheet">
<link
	href="plugins/bower_components/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css"
	rel="stylesheet">
<!-- animation CSS -->
<link href="css/animate.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="css/colors/default.css" id="theme" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</head>


<body class="fix-header">
<jsp:include page="../include/toaster.jsp" />
	<!-- ============================================================== -->
	<!-- Preloader -->
	<!-- ============================================================== -->
	<div class="preloader">
		<svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none"
				stroke-width="2" stroke-miterlimit="10" />
        </svg>
	</div>
	<!-- ============================================================== -->
	<!-- Wrapper -->
	<!-- ============================================================== -->
	<div id="wrapper">
		<!-- ============================================================== -->
		<!-- Topbar header - style you can find in pages.scss -->
		<!-- ============================================================== -->
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav slimscrollsidebar">
				<div class="sidebar-head">
					
				
					<p class="profile-pic"> <img
							src="${account.avatarPath}" alt="user-img" width="48"
							class="img-circle"><b class="hidden-xs">${account.fullName}</b></p></li>
			
				</div>
				<ul class="nav" id="side-menu">
					<li style="padding: 70px 0 0;"><a href="" class="waves-effect"><i
							class="fa fa-user fa-fw" aria-hidden="true"></i>Booking ticket</a></li>
					
				</ul>
			
			</div>

		</div>
		<!-- ============================================================== -->
		<!-- End Left Sidebar -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Page Content -->
		<!-- ============================================================== -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row bg-title text-center">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-color:#142B6F;">
						<h4 class="page-title" style="color:white;">BOOKING TICKET</h4>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<!-- ============================================================== -->
				<!-- Different data widgets -->
				<!-- ============================================================== -->
				<!-- .row -->

				<!--/.row -->
				<!--row -->
				<!-- /.row -->
				<!-- ============================================================== -->
				<!-- table -->
				<!-- ============================================================== -->
				<div class="row">
					<div class="col-md-12 col-lg-12 col-sm-12">
						<div class="white-box">
							<div class="col-md-2 col-sm-4 col-xs-6 pull-right">
							<button class="btn btn-danger" onclick="onDelete()" id="deleteBtn">Delete</button>
							</div>
							<h3 class="box-title">Recent booking</h3>

							<div class="table-responsive">
								<table class="table" id="ticketTable">
									<thead>
										<tr>
											<th>ROOM NAME</th>
											<th>ROOM NUMBER</th>
											<th>CHECK IN</th>
											<th>CHECK OUT</th>
											<th>IDEN CARD NUM</th>
											<th>FULL NAME</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${tickets}" var="ticket" varStatus="loop">
											<tr>
												<td>${ticket.room.name}</td>
												<td>${ticket.roomNumber}</td>
												<td class="txt-oflo"><jsp:useBean id="date" class="java.util.Date"/><fmt:formatDate value="${ticket.checkIn}" type="date" pattern="dd-MM-yyyy"/></td>
												<td><jsp:useBean id="date2" class="java.util.Date"/><fmt:formatDate value="${ticket.checkOut}" type="date" pattern="dd-MM-yyyy"/></td>
												<td class="txt-oflo">${ticket.idenCardNum}</td>
												<td><span class="text-success">${ticket.fullName}</span></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 col-lg-12 col-sm-12">
						<div class="white-box">
							<form id="findAvailableRoomForm">
								<input type="hidden" class="form-control" name="hotelId"
									value="${hotelId}">
								<div class="form-group col-md-6">
									<label>Check in</label> <input type="date" name="checkIn"
										min="1000-01-01" max="3000-12-31" class="form-control">
								</div>
								<div class="form-group col-md-6">
									<label>Check out</label> <input type="date" name="checkOut"
										min="1000-01-01" max="3000-12-31" class="form-control">
								</div>
								<button type="submit" class="btn btn-primary btn-block"
									id="findAvailableRoom">Find</button>


							</form>
						</div>
					</div>
				</div>


				<div class="row" id="bookRoomWrapper">
					<div class="col-md-12 col-lg-12 col-sm-12">
						<div class="white-box">
							<form id="bookRoom">
								<div class="form-group col-md-6">
								<label>Room</label>
									<select class="form-control col-md-6" id="addRoomBookSelectRoom" name="roomId">
										<c:forEach items="${rooms}" var="room" >
											<option value="${room.id}">Name: ${room.name} .Id: ${room.id}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group col-md-6">
								<label>Room number</label>
									 <select class="form-control" id="addRoomBookSelectNumber" name="roomNumber">
									</select>
								</div>
								<div class="form-group col-md-6">
									<label for="pwd">Identification Card Number:</label> <input
													type="text" class="form-control" name="idenCardNum">
								</div>
								<div class="form-group col-md-6">
									<label for="pwd">Full name:</label> <input type="text"
									class="form-control" name="fullName">
								</div>
								<div class="form-group col-md-6">
									<label>Check in</label> <input type="date" name="checkIn"
										min="1000-01-01" max="3000-12-31" class="form-control">
								</div>
								<div class="form-group col-md-6">
									<label>Check out</label> <input type="date" name="checkOut"
										min="1000-01-01" max="3000-12-31" class="form-control">
								</div>
								<button type="submit" class="btn btn-primary btn-block"
									id="findAvailableRoom">Book</button>


							</form>
						</div>
					</div>
				</div>
				
				
			


			</div>
			<!-- /.container-fluid -->
			<footer class="footer text-center">
				Â© 2020 Ample Admin by <a href="https://www.wrappixel.com/">wrappixel.com</a>
			</footer>
		</div>
		<!-- ============================================================== -->
		<!-- End Page Content -->
		<!-- ============================================================== -->
	</div>
	<!-- ============================================================== -->
	<!-- End Wrapper -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- All Jquery -->
	<!-- ============================================================== -->
	<script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Menu Plugin JavaScript -->
	<script
		src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
	<!--slimscroll JavaScript -->
	<script src="js/jquery.slimscroll.js"></script>
	<!--Wave Effects -->
	<script src="js/waves.js"></script>
	<!--Counter js -->
	<script
		src="plugins/bower_components/waypoints/lib/jquery.waypoints.js"></script>
	<script
		src="plugins/bower_components/counterup/jquery.counterup.min.js"></script>
	<!-- chartist chart -->
	<script src="plugins/bower_components/chartist-js/dist/chartist.min.js"></script>
	<script
		src="plugins/bower_components/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
	<!-- Sparkline chart JavaScript -->
	<script
		src="plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="js/custom.min.js"></script>
	<script src="js/dashboard1.js"></script>
	<script src="plugins/bower_components/toast-master/js/jquery.toast.js"></script>
	<script>
	$('#bookRoomWrapper').hide();
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
	
	
	var selectRow=-1;
	var tickets=[];
	<c:forEach items="${tickets}" var="ticket">
 		tickets.push({id:${ticket.id}, roomNumber: ${ticket.roomNumber},
 		 idenCardNum: '${ticket.idenCardNum}', fullName: '${ticket.fullName}',
 		 checkIn: ${ticket.checkIn},
 		 checkOut: ${ticket.checkOut}});
   			
	</c:forEach>
	var deleteIndex=-1;
	
	function onDelete(){
		if(deleteIndex==-1) {
			alert("Please choose a ticket to delete");
			return;
		}
		var r = confirm("Are you sure to delete this ticket");
		if (r == true) {
			$.ajax({
				url : 'http://localhost:8080/admin/booking-ticket?bookingTicketId='+tickets[deleteIndex].id,
				type : 'DELETE',
				contentType : 'application/x-www-form-urlencoded',
				processData : false,
				success : function(data) {
					
					Toast.show('Delete ticket successed','success');
			       	window.location.reload();
			
				},
				error : function(xhr, ajaxOptions, thrownError) {
					Toast.show(xhr.responseText,'error');
				}
			});
		}
	}
	function highlight_row() {
	    var table = document.getElementById('ticketTable');
	    var cells = table.getElementsByTagName('td');

	    for (var i = 0; i < cells.length; i++) {
	        // Take each cell
	        var cell = cells[i];
	        // do something on onclick event for cell
	        cell.onclick = function () {
	            // Get the row id where the cell exists
	            var rowId = this.parentNode.rowIndex;

	            var rowsNotSelected = table.getElementsByTagName('tr');
	            for (var row = 0; row < rowsNotSelected.length; row++) {
	                rowsNotSelected[row].style.backgroundColor = "";
	                rowsNotSelected[row].classList.remove('selected');
	            }
	            var rowSelected = table.getElementsByTagName('tr')[rowId];
	            rowSelected.style.backgroundColor = "yellow";
	            rowSelected.className += " selected";
	            
	         
	            deleteIndex=rowId-1;
	          
	            

	           // msg = 'The ID of the company is: ';
	            //msg += rowSelected.cells[0].innerHTML;
	           // msg += '\nThe cell value is: ' + this.innerHTML;
	            //alert(msg);
	        }
	    }

	} //end of function
	window.onload = highlight_row;
		function buildSelect(arr){
			var re='';
			for(var i=0;i<arr.length;i++){
				re+="<option value='"+arr[i]+"'>"+arr[i]+"</option>";
			}
			return re;
		}
		var availableRoomNumber;
		$(document).ready(function() {
			$('#findAvailableRoomForm').on('submit', function(e) {
				e.preventDefault();
				var data = new FormData(this);
				$.ajax({
					url : 'http://localhost:8080/admin/available-room',
					type : 'POST',
					data : data,
					contentType : false,
					processData : false,
					success : function(data) {
						
						availableRoomNumber=data;
						var htmlContent=buildSelect(availableRoomNumber[$('#addRoomBookSelectRoom').val()]);
						console.log(htmlContent);
						$('#addRoomBookSelectNumber').html(htmlContent);
						$('#bookRoomWrapper').show();
					},
					error : function(xhr, ajaxOptions, thrownError) {
						Toast.show(xhr.responseText,'error');
					}
				});
			});
			
			$('#addRoomBookSelectRoom').on('change', function() {
				var htmlContent=buildSelect(availableRoomNumber[$('#addRoomBookSelectRoom').val()]);
				$('#addRoomBookSelectNumber').html(htmlContent);
			});
			
			
			$('#bookRoom').on('submit', function(e) {
				e.preventDefault();
				var data = new FormData(this);
				$.ajax({
					url : 'http://localhost:8080/admin/booking-ticket',
					type : 'POST',
					data : data,
					contentType : false,
					processData : false,
					success : function(data) {
						 Toast.show('Booking successed','success');
				       	 window.location.reload();
					},
					error : function(xhr, ajaxOptions, thrownError) {
						Toast.show(xhr.responseText,'error');
						
					}
				});
			});
		});
		
		
	</script>
</body>

</html>
