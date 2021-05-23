<%-- 
    Document   : gdDangky
    Created on : Mar 19, 2021, 10:11:27 PM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" import="src.model.*,src.dao.*,java.util.*,java.text.*"%>

<!DOCTYPE html>
<html>
<head>
	<title>Đăng ký BHXH</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script
			src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js' />
	<style type="text/css">
		.form-group{
			margin-left: 50px;
		}
		label{
			margin-right: 50px;
                        margin-top: 20px;
		}
		.form-group input{
			border: none;
			border-bottom: 1px solid #919aa2; 
			border-radius: 0px;
		}

	</style>
	<script type="text/javascript">
		function send(){
			let username = document.getElementById('username').value;
			console.log(username);
		}
	</script>

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
	<%
		CoquanbhDAO cdao = new CoquanbhDAO();
			ArrayList<InsuranceCompany> ListCoQuan = cdao.getCoquanbh();
			TinhDAO tdao = new TinhDAO();
			ArrayList<Province> ListTinh = tdao.getTinh();
			ArrayList<District> ListQuan = (ArrayList<District>) session.getAttribute("quan");
			ArrayList<Ward> ListPhuong = (ArrayList<Ward>) session.getAttribute("phuong");
	%>

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
					<form action="doRegister.jsp" method="post">
                                        <div class="form-group form-inline " >
						<label for="username" class="col-3">Username
						<span style="color:red">*</span>
						</label>
						
						<input type="text" name="username" id="username" class="form-control col-6" required>
					</div>
                                        <div class="form-group form-inline " >
						<label for="password" class="col-3">Password<span style="color:red">*</span></label>
						<input type="password" name="password" id="password" class="form-control col-6" minlength="8" maxlength="12" required >
					</div>
                                        <div class="form-group form-inline " >
						<label for="madonvi" class="col-3">Mã đơn vị<span style="color:red">*</span></label>
						<input type="text" name="madonvi" id="madonvi" class="form-control col-6" required>
					</div>
                                            
                                            
					<div class="form-group form-inline " >
						<label for="ten" class="col-3">Tên cơ quan/Tổ chức<span style="color:red">*</span></label>
						<input type="text" name="ten" id="ten" class="form-control col-6" required>
					</div>		
					
						<div class="form-group form-inline">
						<label for="masothue" class="col-3">Mã số thuế<span style="color:red">*</span></label>
						<input type="text" name="masothue" id="masothue" class="form-control col-6" required>
					</div>
						
					<div class="form-group form-inline">
						<label for="tencqbh" class="col-3">Cơ quan bảo hiểm<span style="color:red">*</span></label>
					<select class="form-control col-6" name="tencqbh" id ="tencqbh" style="margin-top: 20px;">
                                             <% for(int i=0; i<ListCoQuan.size();i++){%>
							                        <option value="<%=ListCoQuan.get(i).getId()%>"><%=ListCoQuan.get(i).getName() %></option>
							                    <%}
							                    %>
                                        </select> 
						
					</div>
                                            
                                        
			

						<div class="form-group form-inline">
						<label for="sdt" class="col-3">Số điện thoại<span style="color:red">*</span></label>
						<input type="text" name="sdt" id="sdt" class="form-control col-6" required>
					</div>
						
						<div class="form-group form-inline">
						<label for="email" class="col-3">Email<span style="color:red">*</span></label>
						<input type="email" name="email" id="email" class="form-control col-6" required>
					</div>
                                        <div class="form-group form-inline">
						<label for="diachi" class="col-3">Địa chỉ<span style="color:red">*</span></label>
						<div class="col-6 form-inline">
                                                    <select class="form-control" name="tinh" id="tinh" style="width:290px;" >
                                                    <option selected>Tỉnh/TP</option>
                                                    <% for(int i=0; i<ListTinh.size();i++){%>
							                        <option value="<%=ListTinh.get(i).getId()%>"><%=ListTinh.get(i).getName() %></option>
								                    <%}
								                    %>
                                                  </select>
                                                    
                                                    <select class="form-control" name="quan" id="quan" style="width:290px; margin-top: 5px;">
                                            		<option selected>Quận</option>
	                                            <% 
	                                            if(ListQuan != null && ListQuan.size() > 0)
	                                            for(int i=0; i<ListQuan.size();i++){%>
							                        <option value="<%=ListQuan.get(i).getId()%>"><%=ListQuan.get(i).getName() %></option>
								                    <%}
								                    %>
                                        </select> 
                                        <input class="billing_address_1" name="" type="hidden" value="">
										<input class="billing_address_2" name="" type="hidden" value="">
                                                    <select class="form-control" name="phuong" style="width:290px; margin-top: 5px;">
                                            <option selected>Phường</option>
                                            <% if(ListPhuong != null && ListPhuong.size() > 0)
                                            for(int i=0; i<ListPhuong.size();i++){%>
							                        <option value="<%=ListPhuong.get(i).getId()%>"><%=ListPhuong.get(i).getName() %></option>
								                    <%}
								                    %>
                                        </select> 
                                        <input type="text" name="chitiet" id="chitiet" class="form-control col-6" required placeholder="Chi tiết địa chỉ">
                                                    
                                                    
						</div>
					</div>
					<hr>
					<div class="form-group">
						<a href="index.html" class="btn btn-secondary" style="margin-left:400px;">Quay lại</a>
						<input type="submit" class="btn btn-primary" value="Đăng ký" style="float: right; margin-right: 150px;" >
					
					</div>
				</form>
				</div>				
			</div>
		</div>
		<div class="col-5">
                    <img src="https://vnn-imgs-f.vgcloud.vn/2020/02/24/10/bao-hiem-3.png"/>
		</div>
	</section>

<script type="text/javascript">
	var isLoading = false;
	$(document).ready(function($) {
		$("#tinh").change(function(event) {
			provinceId = $("#tinh").val();
			$.post("doQuan.jsp?tinh="+provinceId, {"tinh" : provinceId}, function(data) {
				isLoading = true;
				location.reload();
			});
		});
		$("#quan").change(function(event) {
			provinceId = $("#quan").val();
			$.post("doPhuong.jsp?quan="+provinceId, {"quan" : provinceId}, function(data) {
				isLoading = true;
				location.reload();
			});
		});
	});
	window.onbeforeunload = function() {
		   if(isLoading) {
			   localStorage.setItem("tinh", document.getElementById("tinh").value);
			   localStorage.setItem("quan", document.getElementById("quan").value);
			   localStorage.setItem("madonvi", document.getElementById("madonvi").value);
			   localStorage.setItem("ten", document.getElementById("ten").value);
			   localStorage.setItem("masothue", document.getElementById("masothue").value);
			   localStorage.setItem("sdt", document.getElementById("sdt").value);
			   localStorage.setItem("tencqbh", document.getElementById("tencqbh").value);
			   localStorage.setItem("email", document.getElementById("email").value);
			   localStorage.setItem("username", document.getElementById("username").value);
			   localStorage.setItem("password", document.getElementById("password").value);
		   }
		}

	window.onload = function() {
	   var username = localStorage.getItem("username");
	   var password = localStorage.getItem("password");
	   var tinh = localStorage.getItem("tinh");
	   var quan = localStorage.getItem("quan");
	   var madonvi = localStorage.getItem("madonvi");
	   var ten = localStorage.getItem("ten");
	   var masothue = localStorage.getItem("masothue");
	   var sdt = localStorage.getItem("sdt");
	   var tencqbh = localStorage.getItem("tencqbh");
	   var email = localStorage.getItem("email");
	   localStorage.clear();
	   document.getElementById("username").value = username;
	   document.getElementById("password").value = password;
	   document.getElementById("madonvi").value = madonvi;
	   document.getElementById("masothue").value = masothue;
	   document.getElementById("tencqbh").value = tencqbh;
	   document.getElementById("email").value = email;
	   document.getElementById("sdt").value = sdt;
	   document.getElementById("ten").value = ten;
	   document.getElementById("tinh").value = tinh;
	   document.getElementById("quan").value = quan;
	}
	</script>
	
	<script src='https://cdn.jsdelivr.net/g/lodash@4(lodash.min.js+lodash.fp.min.js)'></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>