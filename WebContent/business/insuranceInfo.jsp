<%-- 
    Document   : gdChinhKH
    Created on : Mar 15, 2021, 8:40:10 PM
    Author     : Admin
--%>

<%@page import="src.model.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
import="src.model.*,src.dao.*,java.util.*,java.text.*"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Hệ thống hỗ trợ tính phí bảo hiểm xã hội</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />


</head>
<body>
	<%
		Member kh = (Member) session.getAttribute("business");
		if (kh == null) {
			request.getRequestDispatcher("../login.jsp?err=timeout");
		}
	%>

	
	<!-- Menu bar -->
	<nav class="navbar navbar-expand-lg navbar-primary bg-primary sticky-top">
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="businessHome.jsp">Trang chủ <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="labourInfo.jsp">Khai báo thông tin</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="insuranceInfo.jsp">Xem thông tin bảo hiểm</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	
	<!--  Body -->

	<%
		if (session.getAttribute("business") != null) {
		Member member = (Member) session.getAttribute("business");
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		BusinessUnit businessUnit = busUnitDAO.getBusinessUnit(member);
		session.setAttribute("busUnit", businessUnit);
	%>
	<div class ="container">
		<div class=" content-wrapper pt-4">
	
	<div class= row>
		<div class=" col-md-6">
	      <h4>Tên công ty: </h4><a class="label-sm"><%=businessUnit.getName()%></a>
	    </div>
	    <div class=" col-md-6">
	      <h4>Mã số thuế: </h4><a ><%=businessUnit.getTaxCode()%></a>
	    </div>
	</div>
	<hr>
	
	<form method="post" action="insuranceInfo.jsp" class="form-inline">
	<div class="row">
		<div class="col-3">
		<div class="form-group">
			<input type="month" name="insuranceMonth" value="<%=request.getParameter("insuranceMonth")%>">
		</div>
		</div>
		<div class="col-3">
		</div>
	</div>
		<input type="submit" value="Xem phí BHXH">
	</form>
	
	<hr>
	
	<table class="table">
	  <thead class="table table-bordered">
	    <tr>
			<th scope="row">Tổng phí BHXH</th>
			<th>Tổng phí BHYT</th>
			<th>Tổng phí BHTN</th>
			<th>Tổng phí công đoàn</th>
		</tr>
	  </thead>
	  <tbody>
	    <%
		if(request.getParameter("insuranceMonth") != null) {
			BusinessInsuranceInfoDAO dao = new BusinessInsuranceInfoDAO();
			session.setAttribute("insuranceMonth", request.getParameter("insuranceMonth"));
			BusinessInsuranceInfo insuranceInfo = dao.getInsuranceInfo(businessUnit, request.getParameter("insuranceMonth"));
		
		%>
			
			<tr>
				<th scope="row"><fmt:formatNumber type = "number" 
				value = "<%=insuranceInfo.getEmployeeSocialInsurance() + 
					insuranceInfo.getBusinessSocialInsurance() %>" /></th>
				<th><fmt:formatNumber type = "number" 
				value = "<%=insuranceInfo.getEmployeeMedicalInsurance() + 
					insuranceInfo.getBusinessMedicalInsurance() %>" /></th>
				<th><fmt:formatNumber type = "number" 
				value = "<%=insuranceInfo.getEmployeeUnemployedInsurance() + 
					insuranceInfo.getBusinessUnemployedInsurance() %>" /></th>
				<th><fmt:formatNumber type = "number" 
				value = "<%=insuranceInfo.getEmployeeUnionFee() + 
					insuranceInfo.getBusinessUnionFee() %>" /></th>
			</tr>
	  </tbody>
	  <a href="labourDetail.jsp?businessId=<%=businessUnit.getId()%>&insuranceMonth=<%=request.getParameter("insuranceMonth")%>">
		Xem chi tiết người lao động</a>
		</table>
	</div>
	
	</div>
	<%
		}
	} else {
		response.sendRedirect("../login.jsp");
	}
	%>

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




</style>
