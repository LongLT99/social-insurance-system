<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="src.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Employee Home page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../header.jsp"%>
<!-- Font awesome -->
	<link rel="stylesheet" href="../static/css/font-awesome.min.css">
	<!-- Sandstone Bootstrap CSS -->
	<link rel="stylesheet" href="../static/css/bootstrap.min.css">
	<link rel="stylesheet" href="../static/css/bootstrap-social.css">
	<!-- Bootstrap select -->
	<link rel="stylesheet" href="../static/css/bootstrap-select.css">
	<!-- Admin Stye -->
	<link rel="stylesheet" href="../static/css/style.css">
	<link rel="stylesheet" href="../static/css/style.less">
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<%
		Member sv = (Member) session.getAttribute("employee");
	if (sv == null) {
		response.sendRedirect("..\\login.jsp?err=timeout");
	}
	
	%>
	<%@include file="menu.jsp"%>
	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="row">
			<%
			if(request.getParameter("idok")!=null){
				%>
					<div class="alert alert-success alert-dismissible fade show" role="alert">
					   Xác nhận thành công!
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>
				<% 
			}
			%>
				<div class="col-md-12">
					<h2 class="page-title">Trang chủ</h2>
				</div>
			</div>
		</div>
	</div>
</body>
</html>