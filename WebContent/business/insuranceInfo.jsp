<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="src.model.*,src.dao.*,java.util.*,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="../static/css/font-awesome.min.css">
<!-- Sandstone Bootstrap CSS -->
<link rel="stylesheet" href="../static/css/bootstrap.min.css">
<!-- Bootstrap Datatables -->
<link rel="stylesheet" href="../static/css/bootstrap-social.css">
<!-- Bootstrap select -->
<link rel="stylesheet" href="../static/css/bootstrap-select.css">
<link rel="stylesheet" href="../static/css/style.css">
<link rel="stylesheet" href="../static/css/style.less">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<%@include file="header.jsp"%>
	<%
		if (session.getAttribute("business") != null) {
		Member member = (Member) session.getAttribute("business");
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		BusinessUnit businessUnit = busUnitDAO.getBusinessUnit(member);
		session.setAttribute("busUnit", businessUnit);
	%>
	<div class="content-wrapper pt-4">
	<form method="post" action="insuranceInfo.jsp">
		<input type="month" name="insuranceMonth" value="<%=request.getParameter("insuranceMonth")%>">
		<input type="submit" value="Xem phí BHXH">
	</form>
		<table class="table">
			<thead class="thead-light">
				<tr>
					<td colspan="4">Tên công ty: <%=businessUnit.getName()%></td>
				</tr>
				<tr>
					<td colspan="4">Mã số thuế: <%=businessUnit.getTaxCode()%></td>
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
					<th scope="row">Tổng phí BHXH</th>
					<th>Tổng phí BHYT</th>
					<th>Tổng phí BHTN</th>
					<th>Tổng phí công đoàn</th>
				</tr>
				<tr>
					<th scope="row"><%=insuranceInfo.getEmployeeSocialInsurance() + insuranceInfo.getBusinessSocialInsurance() %></th>
					<th><%=insuranceInfo.getEmployeeMedicalInsurance() + insuranceInfo.getBusinessMedicalInsurance() %></th>
					<th><%=insuranceInfo.getEmployeeUnemployedInsurance() + insuranceInfo.getBusinessUnemployedInsurance() %></th>
					<th>Tổng phí công đoàn</th>
				</tr>
			</tbody>
		<a href="labourDetail.jsp?businessId=<%=businessUnit.getId()%>&insuranceMonth=<%=request.getParameter("insuranceMonth")%>">
		Xem chi tiết người lao động</a>
		</table>
	<%
		}
	} else {
		response.sendRedirect("../login.jsp");
	}
	%>
	</div>
</body>
</html>