<%@page import="java.sql.Date"%>
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


	<%
		if (session.getAttribute("business") != null) {
		Member member = (Member) session.getAttribute("business");
		BusinessUnit businessUnit = (BusinessUnit) session.getAttribute("busUnit");
		request.setCharacterEncoding("utf-8");
	%>
	
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
	<div class="content-wrapper pt-4">
	<h1>Danh sách lao động</h1>
		<a href="addLabour.jsp">Thêm lao động mới</a>
		<table class="table">
			<tbody>

				<%
				LabourDAO labourDAO = new LabourDAO();
				ArrayList<Labour> labours = labourDAO.getLaboursOfBusinessUnit(businessUnit);
				session.setAttribute("labours", labours);
				%>

				<tr>
					<th scope="row">Họ tên</th>
					<th>Mã BHXH</th>
					<th>Mã số thuế</th>
					<th>Phòng ban</th>
					<th>Chức danh</th>
					<th>Trạng thái</th>
					<th>Quá trình tham gia BHXH</th>
				</tr>
				<%
					if (labours != null) {
					for (int i = 0; i < labours.size(); i++) {
				%>
				<tr>

					<th scope="row"><%=labours.get(i).getName()%></th>
					<th><%=labours.get(i).getInsuranceCode()%></th>
					<th><%=labours.get(i).getTaxCode() == null ? "Chưa cập nhật mã số thuế" : labours.get(i).getTaxCode()%></th>
					<th><%=labours.get(i).getDivision() == null ? "Chưa cập nhật" : labours.get(i).getDivision() %></th>
					<th><%=labours.get(i).getPosition() == null ? "Chưa cập nhật" : labours.get(i).getPosition() %></th>
					<th><%=labours.get(i).getIsWorking() == 1 ? "Đang làm việc" : "Đã nghỉ việc"%></th>
					<th><a
						href="labourInsuranceProcess.jsp?idld=<%=labours.get(i).getId()%>">Xem</a></th>
				</tr>
				<%
					}
				if (labours.size() == 0) {
				%>

				<tr>
					<th><a>Không có kết quả để hiển thị</a></th>
				</tr>
				<%
					}
				}
				%>
			</tbody>

		</table>
		<%
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

</style>
