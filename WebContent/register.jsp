<%-- 
    Document   : gdDangky
    Created on : Mar 19, 2021, 10:11:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Đăng ký BHXH</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<style type="text/css">
		.form-group{
			margin-left: 50px;
		}
		label{
			margin-right: 50px;
		}
		.form-group input{
			border: none;
			border-bottom: 1px solid #919aa2; 
			border-radius: 0px;
		}

	</style>
</head>
<body>
		<nav class="navbar navbar-expand-sm navbar-light bg-primary sticky-top ">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.html">
				<img src="https://dichvucong.baohiemxahoi.gov.vn/assets/images/logo_text.svg">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" 
			data-target="#navbarResponsive">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="navbarResponsive">
	
		</div>
	</nav>

	<main class="container">
		<header class="row">
			<div class="row">
				<div class="alert alert-light col"> 
					<h1 style="margin-left: auto;margin-right: auto;width: 520px;text-align: center;">TỜ KHAI</h1>
				</div>
			</div>
		</header>
	</main>
	<section class="row">
		<div class="col-7">
			<div class="row">
				<div class="col">
					<form action="RegisterControl" method="post">
                                        <div class="form-group form-inline " >
						<label for="name" class="col-3">Username</label>
						<input type="text" name="username" id="username" class="form-control col-6" >
					</div>
                                        <div class="form-group form-inline " >
						<label for="name" class="col-3">Password</label>
						<input type="password" name="username" id="username" class="form-control col-6" >
					</div>
					<div class="form-group form-inline " >
						<label for="name" class="col-3">Họ và tên</label>
						<input type="text" name="name" id="name" class="form-control col-6" >
					</div>
					<div class="form-group form-inline ">
						<label for="dob" class="col-3">Ngày sinh</label>
						<input type="text" name="dob" id="dob" class="form-control col-6" placeholder="yyyy-mm-dd">
					</div>

					<div class="form-group">
						<label for="genderM" class="col-3" style="margin-left: 46px">Giới tính</label>
						 <div class="form-check form-check-inline ">
						 	<input type="radio" name="gender" class="form-check-input" id="genderM">
						 	<label for="genderM">Nam</label>
						 </div>

						 <div class="form-check form-check-inline ">
						 	<input type="radio" name="gender" class="form-check-input" id="genderF">
						 	<label for="genderF">Nữ</label>
						 </div>					
					</div>

<!-- 					<div class="form-group form-inline">
						<label for="address">Địa chỉ</label>
						<select name="tinh" >
							<option >
								Ninh Binh
							</option>
						</select>
						<select name="huyen">
							<option>
								Ninh Binh
							</option>
						</select>
						<select name="phuong">
							<option>
								Ninh Binh
							</option>
						</select>
					</div> -->

					<div class="form-group form-inline">
						<label for="nationality" class="col-3">Quốc tịch</label>
						<input type="text" name="nationality" id="nationality" class="form-control col-6">
					</div>
					
						<div class="form-group form-inline">
						<label for="folk" class="col-3">Dân tộc</label>
						<input type="text" name="folk" id="folk" class="form-control col-6">
					</div>
						<div class="form-group form-inline">
						<label for="sicode" class="col-3">Mã số BHXH</label>
						<input type="text" name="sicode" id="sicode" class="form-control col-6">
					</div>
						<div class="form-group form-inline">
						<label for="phone" class="col-3">Số điện thoại</label>
						<input type="text" name="phone" id="phone" class="form-control col-6">
					</div>
						<div class="form-group form-inline">
						<label for="idcode" class="col-3">Số CMND</label>
						<input type="text" name="idcode" id="idcode" class="form-control col-6">
					</div>
						<div class="form-group form-inline">
						<label for="fmcode" class="col-3">Mã hộ gia đình</label>
						<input type="text" name="fmcode" id="fmcode" class="form-control col-6">
					</div>

					<hr>
					<div class="form-group">
						<button type="submit" class="btn btn-primary" style="float: right; margin-right: 150px;">
						Đăng ký
					</button>
					</div>
				</form>
				</div>				
			</div>
		</div>
		<div class="col-5">
			<h3>Thông tin đăng ký</h3>
		</div>
	</section>
	

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
