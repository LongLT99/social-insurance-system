<%@page import="java.sql.Date"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="src.model.*,src.dao.*,java.util.*,java.text.*"%>
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
	
	<div class="row">
	<div class="container">
	<%
		if (session.getAttribute("business") != null) {
		Member member = (Member) session.getAttribute("business");
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		BusinessUnit businessUnit = busUnitDAO.getBusinessUnit(member);
		session.setAttribute("busUnit", businessUnit);
		if(request.getParameter("idld") == null) {
			response.sendRedirect("labourInfo.jsp");
		}
		Integer selectedLabourId = Integer.parseInt(request.getParameter("idld"));
		Labour selectedLabour = new Labour();
		ArrayList<Labour> labours = (ArrayList<Labour>) session.getAttribute("labours");
		
		for(Labour labour : labours) {
			if(labour.getId() == selectedLabourId) {
				selectedLabour = labour;
			}
		}
		
		InsuranceProcessDAO dao = new InsuranceProcessDAO();
		ArrayList<InsuranceProcess> processes = dao.getInsuranceProcesses(businessUnit, selectedLabour);
		
	%>
	<div class="content-wrapper pt-4">
		<table class="table">
			<thead class="thead-light">
				<tr>
					<td colspan="4">Tên người lao động: <%=selectedLabour.getName()%></td>
				</tr>
				<tr>
					<td colspan="4">Mã BHXH: <%=selectedLabour.getInsuranceCode()%></td>
				</tr>
				<%
		if(processes.size() == 0) {
		
	%>
				<tr>
					<th><a>Người lao động chưa có quá trình tham gia BHXH nào
							tại doanh nghiệp của bạn</a></th>
				</tr>
				<%
		} else {
			%>
			<tr>
				<th scope="row">Mã HĐLĐ</th>
				<th>Thời gian bắt đầu</th>
				<th>Thời gian kết thúc</th>
				<th>Phòng ban</th>
				<th>Chức danh</th>
				<th>Lương bảo hiểm</th>
				<th>Loại quá trình</th>
			</tr>
			</thead>
			<tbody>
			<%
			for(InsuranceProcess process : processes) { 
	%>
				<tr>
					<th scope="row"><%=process.getContractCode() %></th>
					<th><%=process.getStartTime() %></th>
					<th><%=process.getEndTime() %></th>
					<th><%=process.getDivision() %></th>
					<th><%=process.getPosition() %></th>
					<th><fmt:formatNumber type = "number" value = "<%=process.getInsuranceSalary() %>" /></th>
					<th><%=process.getProcessType().getName() %></th>
				</tr>
			<%} %>
			</tbody>
			<a href="addInsuranceProcess.jsp?idld=<%=selectedLabour.getId()%>">Thêm quá trình BHXH mới</a>
		</table>
		<%
		}
	} else {
		response.sendRedirect("../login.jsp");
	}
	%>
	</div>
	
</div>
</div>
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

</style>