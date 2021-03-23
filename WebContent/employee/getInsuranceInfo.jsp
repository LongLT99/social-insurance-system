<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="src.model.*,src.dao.*,java.util.*,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insurance Info page</title>
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
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<%@include file="menu.jsp"%>
	<%
	request.setCharacterEncoding("utf-8");
		int idBU = 0;
		if(request.getParameter("idbu")!=null){
			idBU = Integer.parseInt(request.getParameter("idbu"));
			session.setAttribute("idbu", idBU);
		}
		if (session.getAttribute("employee") != null && idBU!=0) {
		Member member = (Member) session.getAttribute("employee");
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		BusinessUnit businessUnit = busUnitDAO.getBusinessUnitbyId(idBU);
		session.setAttribute("busUnit", businessUnit);
	%>
	<div class="content-wrapper pt-4">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<h2 class="page-title">Thông tin bảo hiểm của doanh nghiệp</h2>
				</div>
			</div>
		</div>
	<form class="form-inline" method="post" action="getInsuranceInfo.jsp?idbu=<%=idBU%>">
		<div class="form-row">
			<div class="col-md-6">
				

				
			</div>
		</div>
	</form>
	<form class="form-inline" method="post" action="getInsuranceInfo.jsp?idbu=<%=idBU%>">
		<label>Chọn thời gian:</label>
	  <div class="form-group col-md-2"">	    
	    <input type="month" name="insuranceMonth" value="<%=request.getParameter("insuranceMonth")%>">
	  </div>
	  <input type="submit" value="Xem phí BHXH" class="btn btn-secondary">
	</form>
		<table class="table">
			<thead class="thead-light">
				<tr>
					<td colspan="4">Tên công ty: <%=businessUnit.getName()%></td>
				</tr>
				<tr>
					<td colspan="4">Mã đơn vị: <%=businessUnit.getUnitCode()%></td>
				</tr>
				<tr>
					<td colspan="4">Mã số thuế: <%=businessUnit.getTaxCode()%></td>
				</tr>
			</thead>
			<tbody>
	<%
		if(request.getParameter("insuranceMonth") != null) {
			BusinessInsuranceInfoDAO dao = new BusinessInsuranceInfoDAO();
			InsuranceTypeDAO indao = new InsuranceTypeDAO();
			
			ArrayList <InsuranceType> InsuranceTypeList = indao.getInsuranceType();
			session.setAttribute("insuranceMonth", request.getParameter("insuranceMonth"));
			
			BusinessInsuranceInfo insuranceInfo = dao.getInsuranceInfo(businessUnit, request.getParameter("insuranceMonth"));
			float[] InsuranceList = {insuranceInfo.getEmployeeSocialInsurance() + insuranceInfo.getBusinessSocialInsurance(),insuranceInfo.getEmployeeMedicalInsurance() + insuranceInfo.getBusinessMedicalInsurance(),
					insuranceInfo.getEmployeeUnemployedInsurance() + insuranceInfo.getBusinessUnemployedInsurance(), insuranceInfo.getEmployeeUnionFee() + insuranceInfo.getBusinessUnionFee()};
			
			RealTimePaymentDAO rtdao = new RealTimePaymentDAO();
			
	%>
				<tr>
					<th scope="row">Loại Bảo Hiểm</th>
					<th>Tổng phí</th>
					<th>Trạng thái</th>
					<th>Xác nhận đóng phí</th>
				</tr>
				<%
				for(int i=0;i<InsuranceTypeList.size();i++){
				%>
				<tr>
					<th scope="row"><%=InsuranceTypeList.get(i).getName() %></th>
					<th><fmt:formatNumber type = "number" value = "<%= InsuranceList[i]%>" /></th>
					
				<%
					boolean check = rtdao.CheckPayment(idBU, request.getParameter("insuranceMonth"),InsuranceTypeList.get(i).getId());
					if(!check){
					
				%>
					<th>Hoàn thành</th>
					<th>Đã xác nhận</th>
				</tr>
				<%}else{
					%>
					<th>Chưa hoàn thành</th>
					<th><a href="confirmPayment.jsp?idit=<%=InsuranceTypeList.get(i).getId()%>&insuranceMonth=<%=request.getParameter("insuranceMonth")%>">Chọn</a></th>
				</tr>
				<%
				}
				} %>
			</tbody>

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