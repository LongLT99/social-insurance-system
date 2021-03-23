<%@page import="java.sql.Date"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
	%>
	<div class="content-wrapper pt-4">
		<form method="post" action="excelreport.jsp">
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th rowspan="2">Mã số thuế</th>
					<th rowspan="2">Họ tên</th>
					<th rowspan="2">Lương BH</th>
					<th rowspan="2">Tham gia tổ chức công đoàn</th>
					<th colspan="5" class="text-center">Các khoản tính vào chi phí
						doanh nghiệp</th>
					<th colspan="5" class="text-center">Các khoản trích trừ vào
						lương</th>
				</tr>
				<tr>
					<th>BHXH</th>
					<th>BHYT</th>
					<th>BHTN</th>
					<th>KPCĐ</th>
					<th>Cộng</th>
					<th>BHXH</th>
					<th>BHYT</th>
					<th>BHTN</th>
					<th>KPCĐ</th>
					<th>Cộng</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (session.getAttribute("insuranceMonth") != null && session.getAttribute("busUnit") != null) {
					BusinessUnit busUnit = (BusinessUnit) session.getAttribute("busUnit");
					Integer businessId = busUnit.getId();
					String insuranceMonth = request.getParameter("insuranceMonth");
					LabourDAO dao = new LabourDAO();
					ArrayList<LabourInsuranceInfo> insuranceInfo = dao.getInsuranceInfo(businessId, insuranceMonth);
					session.setAttribute("employees", insuranceInfo);
					for (LabourInsuranceInfo labour : insuranceInfo) {
				%>
				<tr>
					<td><%=labour.getInsuranceCode() %></td>
					<td><%=labour.getName() %></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getInsuranceSalary() %>" /></td>
					<td>Để lại</td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getBusinessSocialInsurance() %>" /></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getBusinessMedicalInsurance() %>" /></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getBusinessUnemployedInsurance() %>" /></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getBusinessUnionFee() %>" /></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getBusinessUnemployedInsurance() +
							labour.getBusinessMedicalInsurance() + labour.getBusinessSocialInsurance() +
							labour.getBusinessUnionFee()%>" /></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getSocialInsurance() %>" /></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getMedicalInsurance() %>" /></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getUnemployedInsurance() %>" /></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getUnionFee() %>" /></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getUnemployedInsurance() +
							labour.getMedicalInsurance() + labour.getSocialInsurance() +
							labour.getUnionFee()%>" /></td>
				</tr>
				<%
					}
				%>
			
		</table>
		<input type="submit" value="Export To Excel"/>
		</form>
		<%
			}
		} else {
			response.sendRedirect("../login.jsp");
		}
		%>
	</div>
</body>
</html>