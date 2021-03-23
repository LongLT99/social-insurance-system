<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="src.model.*,src.dao.*,java.util.*,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Search Business page</title>
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
	<%@include file="menu.jsp"%>
	<%
	request.setCharacterEncoding("utf-8");
		if (session.getAttribute("employee") != null) {
		Member member = (Member) session.getAttribute("employee");

	%>

	<div class="content-wrapper pt-4">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<h2 class="page-title">Tìm doanh nghiệp</h2>
				</div>
			</div>
		</div>
	
	<form class="form-inline" method="post" action="searchBusiness.jsp">
		<label>Tên doanh nghiệp:</label>
	  <div class="form-group col-md-2"">	    
	    <input type="text" class="form-control" name="busName" placeholder="Nhập tên doanh nghiệp">
	  </div>
	  <button type="submit" class="btn btn-secondary">Tìm kiếm</button>
	</form>
		<table class="table">
			<thead>
				<tr>
					<th scope="row">Tên Doanh nghiệp</th>
					<th>Mã đơn vị</th>
					<th>Mã số thuế</th>
					<th>Chọn</th>
				</tr>
			</thead>
			<tbody>
	
	<%
		if(request.getParameter("busName") != "") {
			BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
			session.setAttribute("busName", request.getParameter("busName"));
			ArrayList <BusinessUnit> ListBusinessUnit = busUnitDAO.searchBusinessUnit(request.getParameter("busName"));

	%>
			
					<%
					if(ListBusinessUnit != null){
						for(int i=0; i < ListBusinessUnit.size(); i++){
					%>
				<tr>
					
					<th scope="row"><%=ListBusinessUnit.get(i).getName() %></th>
					<th><%=ListBusinessUnit.get(i).getUnitCode() %></th>
					<th><%=ListBusinessUnit.get(i).getTaxCode() %></th>
					<th><a href="getInsuranceInfo.jsp?idbu=<%=ListBusinessUnit.get(i).getId()%>">Chọn</a></th>
				</tr>
				<%
						}
							if(ListBusinessUnit.size() == 0){
								%>
									
									<tr>
										<th><a >Không có kết quả để hiển thị xin hãy nhập đúng từ khóa!</a></th>
									</tr>
								<%
							}
						}
						}
						else{
							%>
							<tr>
										<th><a >Tên doanh nghiệp không thể để trống!</a></th>
									</tr>
				    	    
				    		<%
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
</body>
</html>