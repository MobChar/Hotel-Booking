<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Tour Template</title>
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
<link rel="stylesheet" href="/css/emojis.css" />
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
.dropdown-menu {
    min-width:0px;
    max-width:100px;
}
img .emoji{
	max-width:16px;
	max-height:16px;
}

img.emoji {
   height: 1.5em;
   width: 1.5em;
   margin: 0 .05em 0 .1em;
   vertical-align: -0.1em;
}


</style>
</head>
<body>



	<div class="commentContainer container" style="margin-top:128px;">
		<div class="row">
			<c:if test="${empty account}">
				<div
					class="col-md-12 col-md-offset-0 heading2 animate-box text-center">
					<h4>Hãy đang nhập để bình luận về chỗ nghỉ này</h4>
				</div>
			</c:if>
			<c:if test="${not empty account}">


				<form class="col-md-12" id="postCommentForm">
					<input type="hidden" class="form-control" name="username"
						value="${account.username }">
					<input type="hidden" class="form-control" name="hotelId"
						value="${hotel.id}">
					<div class="form-group col-sm-1 text-center">
						<img src="${account.avatarPath}" alt="Avatar"
							class="avatar center-block"> <p><b>${account.fullName}</b></p>
					</div>

					<div class="form-group col-md-11">

						<textarea class="form-control" rows="5" id="comment"
							name="comment"></textarea>
						<i class="fa fa-share icon icon-happy" id="iconPicker"><div id="emojis" style="position: absolute; z-index: 2; top: -320px; left: -20px;"></div></i>


					</div>


					<div class="form-group">
						<button type="submit" class="btn btn-success btn-xs pull-right">
							Bình luận</button>
					</div>







				</form>
			</c:if>

			<div class="row">
				<div class="col-md-12 bs-linebreak" style="height: 50px;"></div>
			</div>

		</div>
		
		
		<div id="commentContainer">
			<c:forEach items="${comments}" var="comment">
				<div class="row">
					<div class="col-md-12">

						<div class="form-group col-sm-1 text-center">
							<img src="${comment.account.avatarPath}" alt="Avatar"
								class="avatar center-block"> <p><b>${comment.account.fullName}</b></p>
						</div>

						<div class="form-group col-md-10">
							<p>${comment.comment }</p>
						</div>

					</div>



				</div>
			</c:forEach>
		</div>
	</div>


	<div id="failedPostComment" class="modal fade" role="dialog"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog  modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Thêm bình luận thất bại</h4>
				</div>
				<div class="modal-body row">
					<div class="row text-center">
						<button type="button" class="btn btn-danger" data-dismiss="modal">OK</button>
					</div>
				</div>

			</div>


		</div>
	</div>
	
	<script src="https://twemoji.maxcdn.com/v/latest/twemoji.min.js"
		crossorigin="anonymous"></script>
	<script src="/js/DisMojiPicker.js"></script>

	
	<script>
	$("#emojis").disMojiPicker();
	twemoji.parse(document.body);
	$("#emojis").picker(emoji =>{
		var cursorPos = $('#postCommentForm textarea').prop('selectionStart');
	    var v = $('#postCommentForm textarea').val();
	    var textBefore = v.substring(0,  cursorPos);
	    var textAfter  = v.substring(cursorPos, v.length);

	    $('#postCommentForm textarea').val(textBefore + emoji + textAfter);
	});
	
	$('#emojis').hide();
	$("#iconPicker").click(function() {     
		   $('#emojis').toggle();
	});
	
	
	function renderComment(comments){
		var htmlContent='';
		for(var i=0;i<comments.length;i++){
			htmlContent+=
			'<div class="row">'+
			'<div class="col-md-12">'+
				'<div class="form-group col-sm-1 text-center">'+
					'<img src="'+comments[i].avatarPath+'" alt="Avatar"'+
						'class="avatar center-block"> <p><b>'+comments[i].fullName+'</b></p>'+
				'</div>'+
				'<div class="form-group col-md-10">'+
				'<p>'+twemoji.parse(comments[i].comment)+'</p>'+
				'</div>'+
			'</div>'+
		'</div>';
		
		}
		$('#commentContainer').html(htmlContent);
	}
	
	$('#postCommentForm').on("submit",
		function(e) {
			e.preventDefault();
	
			//Get form data
			var unindexed_array = $('#postCommentForm').serializeArray();
	    		var indexed_array = {};
	
			    $.map(unindexed_array, function(n, i){
			        indexed_array[n['name']] = n['value'];
			    });
			    
			   
		
			$.post("/comment",unindexed_array)
			.done(function(data) {
				renderComment(data);
			})
			.fail(function() {
				console.log('failed');
				
				$('#failedPostComment').modal('toggle');
			});
		});
				   
	

	
	</script>
</body>
</html>

