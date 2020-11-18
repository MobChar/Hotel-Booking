<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
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
<title>HOTELL Admin</title>
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
		
		<!-- End Top Navigation -->
		<!-- ============================================================== -->
		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav slimscrollsidebar">
				<div class="sidebar-head">
					
				
					<p class="profile-pic"> <img
							src="${account.avatarPath}" alt="user-img" width="48"
							class="img-circle"><b class="hidden-xs">${account.fullName}</b></p></li>
			
				</div>
				<ul class="nav" id="side-menu">
					<li style="padding: 70px 0 0;"><a href=""
						class="waves-effect"><i class="fa fa-clock-o fa-fw"
							aria-hidden="true"></i>Hotel Dashboard</a></li>
					<li><a href="" class="waves-effect"><i
							class="fa fa-user fa-fw" aria-hidden="true"></i>Booking Ticket</a></li>
					
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
						<h4 class="page-title" style="color:white;">Hotel Dashboard</h4>
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
								<button class="btn btn-success" id="addBtn">Add</button>
								<button class="btn btn-primary" id="editBtn">Edit</button>
							</div>
							<h3 class="box-title">Recent Parter</h3>
	

							<div class="table-responsive">
								<table class="table" id="hotelTable">
									<thead>
										<tr>
											<th>ID</th>
											<th>NAME</th>
											<th>ADDRESS</th>
											<th>CATEGORY</th>
											<th>NUMBER OF ROOMS</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${hotels}" var="hotel" varStatus="loop">
											<tr>
												<td><a href="booking-ticket?hotelId=${hotel.id}" style="text-decoration: underline">${loop.index+1}</a></td>
												<td class="txt-oflo">${hotel.name}</td>
												<td>${hotel.address}</td>
												<td class="txt-oflo">${hotel.category.category}</td>
												<td><span class="text-success">${hotel.rooms.size()}</span></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>
			
				
				
				<div class="row" id="addHotelWrapper">
					<div class="col-md-12 col-lg-12 col-sm-12">
						<div class="white-box">
							<div class="row text-center">
								<h2 class="box-title">Add new hotel</h2>
								<hr>
							</div>
							<form id="newHotelForm" enctype="multipart/form-data">
								<div class="form-row">
									<div class="form-group col-md-4">
									<div  style="width: 240px; height: 240px; background-color:black;">
										<img  class="img-fluid" src="/image/0.jpg" style="width: 100%;height: 100%;margin-top: 20px;display: block;">
									</div>	
										<input type="file" name="thumbImage" accept=".png,.jpg">
									</div>
									<div class="form-group col-md-8">
										<label for="inputEmail4">Name</label> <input type="text"
											class="form-control" id="inputEmail4" placeholder="Name"
											name="name">
									</div>
									<div class="form-group col-md-8">
										<label for="inputState">Category</label> <select
											name="category" id="inputState" class="form-control">
											<c:forEach items="${categories}" var="category">
												<option value="${category.id}">${category.category}</option>
											</c:forEach>
										</select>

									</div>
									<div class="form-group col-md-8">
										<label for="inputState">Facility</label> <select
											class="form-control selectpicker" multiple
											data-live-search="true" id="newFacilitiesSelect">
											<c:forEach items="${facilities}" var="facility">
												<option value="${facility.id}">${facility.name}</option>
											</c:forEach>
										</select>

									</div>
										<div class="form-group col-md-12">
										<label for="inputPassword4">Location</label> <input
											type="text" class="form-control" id="inputPassword4"
											placeholder="Location" name="location">
									</div>
								</div>

								<div class="form-group col-md-12">
									<label for="inputAddress">Address</label> <input type="text"
										class="form-control" id="inputAddress"
										placeholder="Address" name="address" 
										readonly>
								</div>
								<div class="form-group col-md-12">
									
									<table class="table addressComponent">
										<thead class="thead-dark">
											<tr>
												<th scope="col">Address omponent name</th>
												<th scope="col">Value</th>
												
											</tr>
										</thead>
										 <tbody>
										 </tbody>
									</table>
								</div>

								<div class="form-row">
									

									<div class="form-group col-md-12">
										<label for="exampleFormControlTextarea1">Description</label>
										<textarea class="form-control" placeholder="Description"
											id="exampleFormControlTextarea1" rows="3" name="description"></textarea>
									</div>


								</div>
								<div class="form-row">
									<div class="form-group col-md-2">
										<div class="img-picker"></div>
									</div>
									<div class="form-group col-sm-2">
										<div class="img-picker"></div>
									</div>
									<div class="form-group col-sm-2">
										<div class="img-picker"></div>
									</div>
									<div class="form-group col-sm-2">
										<div class="img-picker"></div>
									</div>
									<div class="form-group col-sm-2">
										<div class="img-picker"></div>
									</div>
									<div class="form-group col-sm-2">
										<div class="img-picker"></div>
									</div>
								</div>
								<button type="submit" class="btn btn-primary btn-block"
									id="addNewHotelBtn">Save</button>





							</form>

						</div>
					</div>



				</div>
				<jsp:include page="../include/google-map.jsp" />

				<!-- ============================================================== -->
				<!--Edit, Delete Hotel-->
				<!-- ============================================================== -->
				<div class="row" id="editHotelWrapper">
					<div class="col-md-12 col-lg-12 col-sm-12">
						<div class="white-box">
							<div class="row text-center">
								<h2 class="box-title">Edit hotel</h2>
								<hr>
							</div>
							<form id="editHotelForm" enctype="multipart/form-data">
								<div class="form-row">
									<div class="form-group col-md-4">
									<div  style="width: 240px; height: 240px; background-color:black;">
										<img  class="img-fluid"  style="width: 100%;height: 100%;margin-top: 20px;display: block;">
									</div>		
										<input type="file" name="thumbImage" accept=".png,.jpg">
									</div>
									<div class="form-group col-md-8">
										<label for="inputEmail4">Name</label> <input type="text"
											class="form-control" id="inputEmail4" placeholder="Name"
											name="name" value="Binh vip 123">
									</div>
									<div class="form-group col-md-8">
										<label for="inputState">Category</label> <select
											name="category" id="inputState" class="form-control">
											<c:forEach items="${categories}" var="category">
												<option value="${category.id}">${category.category}</option>
											</c:forEach>
										</select>

									</div>
										<div class="form-group col-md-8">
										<label for="inputState">Facility</label> <select
											class="form-control selectpicker" multiple
											data-live-search="true" id="editFacilitiesSelect"
											name="facilities">
											<c:forEach items="${facilities}" var="facility">
												<option value="${facility.id}">${facility.name}</option>
											</c:forEach>
										</select>

									</div>
									<div class="form-group col-md-12">
										<label for="inputPassword4">Location</label> <input
											type="text" class="form-control" id="inputPassword4"
											placeholder="Location" name="location" value="10,12">
									</div>
									
								</div>

								<div class="form-group col-md-12">
									<label for="inputAddress">Address</label> <input type="text"
										class="form-control" id="inputAddress"
										placeholder="1234 Main St" name="address" value="Binh vip 123"
										readonly>
								</div>
								<div class="form-group col-md-12">
									
									<table class="table addressComponent">
										<thead class="thead-dark">
											<tr>
												<th scope="col">Address component name</th>
												<th scope="col">Value</th>
												
											</tr>
										</thead>
										 <tbody>
										 </tbody>
									</table>
								</div>
								

								<div class="form-row">
								

									<div class="form-group col-md-12">
										<label for="exampleFormControlTextarea1">Description</label>
										<textarea class="form-control"
											id="exampleFormControlTextarea1" rows="8" name="description">Binh vip 123</textarea>
									</div>


								</div>
								<div class="form-row">
									<div class="form-group col-md-2">
										<div class="img-picker"></div>
									</div>
									<div class="form-group col-sm-2">
										<div class="img-picker"></div>
									</div>
									<div class="form-group col-sm-2">
										<div class="img-picker"></div>
									</div>
									<div class="form-group col-sm-2">
										<div class="img-picker"></div>
									</div>
									<div class="form-group col-sm-2">
										<div class="img-picker"></div>
									</div>
									<div class="form-group col-sm-2">
										<div class="img-picker"></div>
									</div>
								</div>
								<button type="submit" class="btn btn-primary btn-block"
									id="addNewHotelBtn">Save</button>





							</form>
							<br>
							<hr>
							<br>
							<div class="row col-md-12">
							<form enctype="multipart/form-data" id="addPreviewImage">
									<div class="form-row">
										<div class="form-group col-md-4 imageChooser">
											<input type="file" name="previewImage" accept=".png,.jpg">
										
										</div>
									</div>
									<div class="form-row col-md-3">
									<button type="submit" class="btn btn-success btn-block btn-md">Add as preview image</button>
									</div>
							</form>
							</div>
							
							<br>
							<br>
							<br>
							<br>
							
							<label>Preview image: </label>
							<div class="row" id="previewImage">		
							</div>
							
							
						

						</div>
					</div>
				</div>

				<div class="row" id="addHotelRoomWrapper">
					<div class="col-md-12 col-lg-12 col-sm-12">
						<div class="white-box">
							<div class="row">
								<div class="row text-center">
									<h2 class="box-title">Add new room</h2>
									<hr>
								</div>
								<form enctype="multipart/form-data" id="addRoomForm">
									<input type="hidden" class="form-control" name="hotelId">
									<div class="form-row">
										<div class="form-group col-md-4 imageChooser">
											<div  style="width: 240px; height: 240px; background-color:black;">
												<img  class="img-fluid" src="/image/0.jpg" style="width: 100%;height: 100%;margin-top: 20px;display: block;">
											</div>	
											<input type="file" name="thumbImage" accept=".png,.jpg">
										
											</div>
										<div class="form-group col-md-8">
											<label for="inputPassword4">Name</label> <input type="text"
												class="form-control" id="inputPassword4"
												placeholder="Room name" name="roomName">
										</div>
									</div>

									<div class="form-group col-md-8">
										<label for="inputPassword4">Price</label> <input type="text"
											class="form-control" id="inputPassword4" placeholder="Price"
											name="roomPrice" value="100">
									</div>
									<div class="form-group col-md-4">
										<label for="inputPassword4">Number of room</label> <input
											type="text" class="form-control" id="inputPassword4"
											placeholder="Number of room" name="maxRoom" value="30">
									</div>
									<div class="form-group col-md-4">
									<label for="inputPassword4">Save your change</label>
										<button type="submit" class="btn btn-primary btn-block">Add</button>
									</div>
								</form>

							</div>
						</div>
					</div>
				</div>



			
				<div id="editRoomWrapper"></div>




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
	
	<script>
	$("#mapModal").modal('hide');
	
	var addressComponentKey=[];
	<c:forEach items="${placeType}" var="type">
		addressComponentKey.push("${type.name}");
	</c:forEach>
	console.log(addressComponentKey);
	var addressComponentBody=[];
	
	var selectedRow=-1;
	$(document).ready(function() {
		
		
					var addHotellocationInput = $("#addHotelWrapper input[name='location']");
				
					
					addHotellocationInput.on("click", function() {
						$("#mapModal").modal('toggle');
						locationSuccessed=function(data){
	
							addHotellocationInput.val(pos.lat+", "+pos.lng);
							$('#addHotelWrapper  input[name="address"]').val(data['display_name']);
							
							let htmlContent="";
							addressComponentBody=new Object();
							for(let i=0;i<addressComponentKey.length;i++){
								if(addressComponentKey[i] in data.address)
								htmlContent+='<tr>'+'<td>'+addressComponentKey[i]+'<td>'+'<td>'+data.address[addressComponentKey[i]]+'<td>'+"</tr>";
								addressComponentBody[addressComponentKey[i]]=data.address[addressComponentKey[i]];
								
							}
		
							$('#addHotelWrapper .addressComponent tbody').html(htmlContent);
							
						};
						var url="https://us1.locationiq.com/v1/reverse.php?key=pk.db53bd0fabd5cca7983965a45b6655a7&lat="+pos.lat+"&lon="+pos.lng+"&format=json";
						
						$.ajax({
							url : url,
							type : 'GET',
							data : '',
							contentType : false,
							processData : false,
							success : locationSuccessed,
							error : function(xhr, ajaxOptions, thrownError) {
								Toast.show('Unable to load address from location','error');
							}
						});	

						
						
					});
					
					
					
					var editHotellocationInput = $("#editHotelWrapper input[name='location']");
					editHotellocationInput.on("click", function() {
						$("#mapModal").modal('toggle');
						locationSuccessed=function(data){
							editHotellocationInput.val(pos.lat+", "+pos.lng);
							$('#editHotelWrapper  input[name="address"]').val(data['display_name']);
							
							let htmlContent="";
							addressComponentBody=new Object();
							for(let i=0;i<addressComponentKey.length;i++){
								if(addressComponentKey[i] in data.address)
								htmlContent+='<tr>'+'<td>'+addressComponentKey[i]+'<td>'+'<td>'+data.address[addressComponentKey[i]]+'<td>'+"</tr>";
								addressComponentBody[addressComponentKey[i]]=data.address[addressComponentKey[i]];
								
							}
		
							$('#editHotelWrapper .addressComponent tbody').html(htmlContent);
							
						};
						
						
					
					});
				
		
		$('#newHotelForm').on('submit', function(e){
		    e.preventDefault();
		    var data=new FormData(this);
		    var facilities=[];
		    $("#newFacilitiesSelect option").each(function()
		    {
		    	if ($(this).is(':selected')){
		    		facilities.push($(this).val());
		    	}
		    	
		    });
		  	console.log(JSON.stringify(facilities));
		    data.append('facilities',facilities);
		    data.append('addressComponent',JSON.stringify(addressComponentBody));
		    $.ajax({
		        url: 'http://localhost:8080/hotel',
		        type: 'POST',
		        data: data,
		        contentType: false,
		        processData: false,
		        success: function (data) {
		            Toast.show('Add hotel successed','success');
		       		window.location.reload();
		        }
		        ,error:function (xhr, ajaxOptions, thrownError) {
		        	  Toast.show(xhr.responseText,'error');
		         }
		    });
		});
		
		
		$('#editHotelForm').on('submit', function(e){
		    e.preventDefault();
		    var data=new FormData(this);
		    var facilities=[];
		    $("#editFacilitiesSelect option").each(function()
		    {
		    	if ($(this).is(':selected')){
		    		facilities.push($(this).val());
		    	}
		    	
		    });
		    data.append('facilities',facilities);
		    data.append('id',hotels[selectedRow].id);
		    data.append('addressComponent',JSON.stringify(addressComponentBody));
		    $.ajax({
		        url: 'http://localhost:8080/hotel',
		        type: 'PUT',
		        data: data,
		        contentType: false,
		        processData: false,
		        success: function (data) {
		        	 Toast.show('Edit hotel successed','success');
		            window.location.reload();
		        }
		        ,error:function (xhr, ajaxOptions, thrownError) {
		        	Toast.show(xhr.responseText,'error');
		         }
		    });
		});
		
		
		$('#addRoomForm').on('submit', function(e){
		    e.preventDefault();
		    var data=new FormData(this);
		    $.ajax({
		        url: 'http://localhost:8080/room',
		        type: 'POST',
		        data: data,
		        contentType: false,
		        processData: false,
		        success: function (data) {
		        	Toast.show("Add room successed",'success');
		            window.location.reload();
		        }
		        ,error:function (xhr, ajaxOptions, thrownError) {
		        	Toast.show(xhr.responseText,'error');
		         }
		    });
		});

	});
	
	//Read all hotel value
	var hotels=[];
	let des;
	<c:forEach items="${hotels}" var="hotel">
		des="";
		<c:forEach varStatus="i" begin="0" end="${(hotel.description.length()/30)+(1-((hotel.description.length()/30)%1))%1}">
			des+="${fn:substring(hotel.description, i.index*30,i.index*30+30)}";
		</c:forEach>
		var tmp={id: ${hotel.id}, name: "${hotel.name}", address: "${hotel.address}",
			 description:  des,  latitude: ${hotel.latitude},
			 longitude: ${hotel.longitude},
			 thumb: "${hotel.image.fileName}",
			 category: ${hotel.category.id}
		 };
		var pImage=[];
	<c:forEach items="${hotel.previewImages}" var="pImage">
		pImage.push({fileName:"${pImage.fileName}", id:${pImage.id}});
	</c:forEach>
		var facilities=[];
	<c:forEach items="${hotel.facilities}" var="facility">
		facilities.push("${facility.id}");
	</c:forEach>
	
		tmp.previewImages=pImage;
		tmp.facilities=facilities;
		hotels.push(tmp);
	</c:forEach>
	
	console.log(hotels);
	
	//Read all room value
	var rooms={};
	<c:forEach items="${rooms}" var="entry">
   			var tmp=[];
   			<c:forEach items="${entry.value}" var="room">
 				tmp.push({id:${room.id}, name:"${room.name}", price: ${room.price}, maxRoom: ${room.maxRoom}, thumb: "${room.image.fileName}"});
   			</c:forEach>
   			rooms[${entry.key}]=tmp;
	</c:forEach>
	console.log(rooms);
	
	
	function onEdit(dataArr, index){
		if(index==-1){
			alert("Hãy chọn chỗ nghỉ để hiệu chỉnh");
			return;
		}
		$("#editHotelWrapper [name='name'").val(dataArr[index].name);
		$("#editHotelWrapper [name='location']").val(dataArr[index].latitude+", "+dataArr[index].longitude);
		$("#editHotelWrapper [name='category']").val(dataArr[index].id);
		$("#editHotelWrapper [name='address']").val(dataArr[index].address);
		$("#editHotelWrapper [name='description']").val(dataArr[index].description);
		$("#editHotelWrapper [name='facilities']").val(dataArr[index].facilities);
		$("#addHotelWrapper").hide();
		$("#editHotelWrapper").show();
		$("#editRoomWrapper").show();
		$("#addHotelRoomWrapper input[name='hotelId']").val(dataArr[index].id);
		$("#addHotelRoomWrapper").show();
		$('#editHotelWrapper  img').attr('src',dataArr[index].thumb);
		
		
		var url="https://us1.locationiq.com/v1/reverse.php?key=pk.db53bd0fabd5cca7983965a45b6655a7&lat="+dataArr[index].latitude+"&lon="+dataArr[index].longitude+"&format=json";
		
		$.ajax({
			url : url,
			type : 'GET',
			data : '',
			contentType : false,
			processData : false,
			success : function(data) {
				$('#editHotelWrapper  input[name="address"]').val(data['display_name']);
				
				let htmlContent="";
				addressComponentBody=new Object();
				for(let i=0;i<addressComponentKey.length;i++){
					if(addressComponentKey[i] in data.address)
					htmlContent+='<tr>'+'<td>'+addressComponentKey[i]+'<td>'+'<td>'+data.address[addressComponentKey[i]]+'<td>'+"</tr>";
					addressComponentBody[addressComponentKey[i]]=data.address[addressComponentKey[i]];
					
				}

				$('#editHotelWrapper .addressComponent tbody').html(htmlContent);
				
			},
			error : function(xhr, ajaxOptions, thrownError) {
				Toast.show('Unable to load address from location','error');
				
			}
		});	
	
		
		
		
		var hotelRooms=rooms[dataArr[index].id];
		var htmlContent="";
		
		
	
		for(var i=0;i< hotelRooms.length; i++){
			console.log(hotelRooms[i].name);
			htmlContent+=
			'<div class="row">'+
				'<div class="col-md-12 col-lg-12 col-sm-12">'+
					'<div class="white-box">'+
						'<div class="row">'+
						'<form  enctype="multipart/form-data" class="editRoomForm">'+
							'<div class="form-row">'+
								'<div class="form-group col-md-4">'+
									'<input type="hidden" class="form-control" name="roomId" value="'+hotelRooms[i].id+'">'+
									'<div style="width: 240px; height: 240px; background-color:black;"><img  class="img-fluid"  style="width: 100%;height: 100%;margin-top: 20px;display: block;" src="'+hotelRooms[i].thumb+'"></div>	<input type="file" name="thumbImage" accept=".png,.jpg">'+
								'</div>'+
								'<div class="form-group col-md-8">'+
									'<label for="inputPassword4">Name</label> <input '+
										'type="text" class="form-control" '+
										'placeholder="Room name" name="roomName" value="'+hotelRooms[i].name+'">'+
								'</div>'+
							'</div>'+	
								'<div class="form-group col-md-8">'+
									'<label for="inputPassword4">Price</label> <input '+
										'type="text" class="form-control" '+
										'placeholder="Price" name="roomPrice" value="'+hotelRooms[i].price+'">'+
								'</div>'+
								'<div class="form-group col-md-4">'+
									'<label for="inputPassword4">Number of room</label> <input '+
										'type="text" class="form-control"'+
										'placeholder="Number of room" name="maxRoom" value="'+hotelRooms[i].maxRoom+'">'+
								'</div>'+
								'<div class="form-group col-md-4"><label for="inputPassword4">Save your change</label><button type="submit" class="btn btn-primary btn-block">Modify</button></div>'+
						'</form>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>';
		
		}
		$("#editRoomWrapper").html(htmlContent);
		
		var previewImages=dataArr[index].previewImages;
		var htmlContent='';
		console.log(previewImages);
		for(var i=0;i< previewImages.length; i++){
			htmlContent+=
			'<div class="col-md-2">'+
			'<div class="col-md-12" style=" height:80px;">'+
			'<img class="img-responsive" src="'+previewImages[i].fileName+'" alt="Chania" style="width: 100%;height: 100%;display: block;">'+
			'</div>'+
			'<button class="btn btn-danger btn-block btn-xs" onclick=removePreviewImage('+previewImages[i].id+')>Remove</button>'+
			'</div>';
		}
		
		$("#previewImage").html(htmlContent);
		
		
		$('.editRoomForm').on('submit', function(e){
		    e.preventDefault();
		    var data=new FormData(this);
		    $.ajax({
		        url: 'http://localhost:8080/room',
		        type: 'PUT',
		        data: data,
		        contentType: false,
		        processData: false,
		        success: function (data) {
		        	Toast.show('Edit room successed','success');
		            window.location.reload();
		        }
		        ,error:function (xhr, ajaxOptions, thrownError) {
		        	Toast.show(xhr.responseText,'error');
		  
		         }
		    });
		});
		
		
		$('#addPreviewImage').on('submit', function(e){
		    e.preventDefault();
		    var data=new FormData(this);
		    console.log("HOTEL ID: "+hotels[selectedRow].id);
		    data.append('hotelId',hotels[selectedRow].id);
		    console.log(data);
		    $.ajax({
		        url: 'http://localhost:8080/admin/previewImage',
		        type: 'POST',
		        data: data,
		        contentType: false,
		        processData: false,
		        success: function (data) {
		        	 Toast.show('Add room successed','success');
		            window.location.reload();
		        }
		        ,error:function (xhr, ajaxOptions, thrownError) {
		        	Toast.show(xhr.responseText,'error');
		         }
		    });
		});
		
		
		
		$('.editRoomForm').each(function(i,v){
			console.log(i);
		$('.editRoomForm:eq('+i+') input[type="file"]').change(function(){

		    var input = this;
		    var url = $(this).val();
		    var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
		    if (input.files && input.files[0]&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) 
		     {
		        var reader = new FileReader();

		        reader.onload = function (e) {
		           $('.editRoomForm:eq('+i+') img').attr('src', e.target.result);
		        }
		       reader.readAsDataURL(input.files[0]);
		    }
		    else
		    {
		      
		    }
		  });
		});
	}
	
	function removePreviewImage(imageId){
		var r = confirm("Are you sure to delete this image");
		if (r == true) {
			$.ajax({
				url : 'http://localhost:8080/admin/previewImage?imageId='+imageId+'&hotelId='+hotels[selectedRow].id,
				type : 'DELETE',
				contentType : 'application/x-www-form-urlencoded',
				processData : false,
				success : function(data) {
					 Toast.show('Delete successed','success');
					window.location.reload();
				},
				error : function(xhr, ajaxOptions, thrownError) {
					Toast.show(xhr.responseText,'error');
				}
			});
		}
	}


	function highlight_row() {
	    var table = document.getElementById('hotelTable');
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
	            
	         
	        	selectedRow=rowId-1;
	            
	            

	           // msg = 'The ID of the company is: ';
	            //msg += rowSelected.cells[0].innerHTML;
	           // msg += '\nThe cell value is: ' + this.innerHTML;
	            //alert(msg);
	        }
	    }

	} //end of function
	window.onload = highlight_row;
	$("#editBtn").on("click",()=>{
		onEdit(hotels, selectedRow);
	})
	
	$("#addHotelWrapper").hide();
	$("#editHotelWrapper").hide();
	$("#addHotelRoomWrapper").hide();
	$("#addBtn").click(function(){
		$("#addHotelWrapper").show();
		$("#editHotelWrapper").hide();
		$("#editRoomWrapper").hide();
		$("#addHotelRoomWrapper").hide();
		
	})
	
	
	$('#newHotelForm input[type="file"]').change(function(){
	    var input = this;
	    var url = $(this).val();
	    var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
	    if (input.files && input.files[0]&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) 
	     {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	           $('#newHotelForm  img').attr('src', e.target.result);
	        }
	       reader.readAsDataURL(input.files[0]);
	    }
	    else
	    {
	    }
	  });
	
	$('#editHotelForm input[type="file"]').change(function(){
	    var input = this;
	    var url = $(this).val();
	    var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
	    if (input.files && input.files[0]&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) 
	     {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	           $('#editHotelForm  img').attr('src', e.target.result);
	        }
	       reader.readAsDataURL(input.files[0]);
	    }
	    else
	    {
	      
	    }
	  });
	
	
	$('#addRoomForm input[type="file"]').change(function(){
	    var input = this;
	    var url = $(this).val();
	    var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
	    if (input.files && input.files[0]&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) 
	     {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	           $('#addRoomForm  img').attr('src', e.target.result);
	        }
	       reader.readAsDataURL(input.files[0]);
	    }
	    else
	    {
	      
	    }
	  });
	
	console.log('SIZE: '+$('.editRoomForm').length);
	
	
	//Address loader
	//Address loader
	
		
		
	</script>
	

</body>

</html>
