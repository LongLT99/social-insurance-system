<%-- 
    Document   : gdChinhKH
    Created on : Mar 15, 2021, 8:40:10 PM
    Author     : Admin
--%>

<%@page import="src.model.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Hệ thống hỗ trợ tính phí bảo hiểm xã hội</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">


</head>
<body>
	<%
		Member kh = (Member) session.getAttribute("business");
	if (kh == null) {
		response.sendRedirect("../login.jsp?err=timeout");
	}
	%>

	<nav
		class="navbar navbar-expand-sm navbar-light bg-primary sticky-top ">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.html"> <img
				src="https://dichvucong.baohiemxahoi.gov.vn/assets/images/logo_text.svg">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">

			</ul>
		</div>
	</nav>
	<div class="carousel-caption">
		<a href="gdDangky.jsp" type="button" class="btn btn-lg btn-primary">Đăng ký BHXH</a> 
		<a href="" type="button" class="btn btn-lg btn-primary">Khai báo thông tin </a> 
		<a href="insuranceInfo.jsp" type="button" class="btn btn-lg btn-primary">Tính bảo hiểm</a> 
		<a href="" type="button" class="btn btn-lg btn-primary">Đóng bảo hiểm</a>
	</div>

	<div id="slides" class="carousel slide" data-ride="carousel">
		<ul class="carousel-indicators">
			<li data-target="#slides" data-slide-to="0" class="active"></li>
			<li data-target="#slides" data-slide-to="1"></li>

		</ul>

		<div class="carousel-inner">
			<div class="carousel-item active ">
				<img
					src="https://dichvucong.baohiemxahoi.gov.vn/assets/images/banner2.jpg">

			</div>
			a

			<div class="carousel-item">
				<img
					src="https://dichvucong.baohiemxahoi.gov.vn/assets/images/banner4.jpg">

			</div>
		</div>

		<div class="container-fluid">
			<div class="row jumbotron">
				<div class="col-xs-12 col-sm-12 col-md-9 col-lg-9 col-xl-10">
					<p class="lead">Hệ thống hỗ trợ tính phí bảo hiểm xã hội</p>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3 col-xl-2 ">
					<a href="#">
						<button type="button" class="btn btn-outline-primary btn-lg">
							Group DT9</button>
					</a>
				</div>
			</div>
		</div>
		<hr>
		<div id="connect">
			<div class="container-fluid padding">
				<div class="row text-center padding">
					<div class="col-12">
						<h2>Kết Nối</h2>
					</div>
					<div class="col-12 social padding">
						<a href="#"><i class="fab fa-facebook"></i></a> <a href="#"><i
							class="fab fa-twitter"></i></a> <a href="#"><i
							class="fab fa-google-plus-g"></i></a> <a href="#"><i
							class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
		</div>
		<!-- Footer -->
		<footer>
			<div class="container-fluid padding">
				<div class="row text-center">

					<div class="col-12">
						<hr class="light">
						<h5>
							This website is made with <span class="fas fa-heart"></span> by
							groupdt9
						</h5>
					</div>
				</div>
			</div>
		</footer>


		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>
</body>
</html>

<style type="text/css">
.navbar li a {
	color: #FFF !important;
}

.navbar li a:hover {
	color: #000 !important;
}

#images1 {
	max-width: 100px;
}

.navbar .nav-item {
	margin-right: 30px;
	justify-content: center;
}

.carousel-inner img {
	width: 100%;
}

.carousel-caption {
	font-size: 110%;
	text-transform: uppercase;
	text-shadow: 5px 5px 15px #000;
	position: absolute;
	top: 38%;
}

.btn-primary {
	background-color: #0099ff;
	text-transform: uppercase;
	line-height: 80px;
	font-weight: 5%;
	border: 1px solid white;
}

.fa-facebook {
	color: #3b5998;
}

.fa-google-plus-g {
	color: #dd4b39;
}

.fa-instagram {
	color: #517fa4;
}

.fa-twitter {
	color: #00aced;
}

.fa-facebook:hover, .fa-twitter:hover, .fa-google-plus-g:hover,
	.fa-instagram:hover {
	color: #d5d5d5;
}

@media ( max-width : 992px) {
	.carousel-caption {
		top: 20%;
		font-size: 80%;
	}
	.carousel-caption .btn {
		font-size: 60%;
	}
}

@media ( max-width : 768px) {
	.carousel-caption {
		top: 20%;
		font-size: 60%;
	}
	.carousel-caption .btn {
		font-size: 50%;
	}
}

@media ( max-width : 576px) {
	.carousel-caption {
		top: 15%;
		font-size: 50%;
	}
	.carousel-caption .btn {
		font-size: 30%;
	}
}
</style>
