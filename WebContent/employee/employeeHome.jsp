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
				<div class="col-md-12">
					<h2 class="page-title">Trang chủ</h2>
				</div>
			</div>
		</div>
	</div>
</body>
</html>