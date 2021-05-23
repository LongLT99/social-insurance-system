<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="src.model.*,src.dao.*,java.util.*,java.text.*"%>
<%@include file ="../header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Confirm Payment page</title>
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
		int idIT = 0;
		if(request.getParameter("idit")!=null){
			idIT = Integer.parseInt(request.getParameter("idit"));
			session.setAttribute("idit", idIT);
		}
		if (session.getAttribute("employee") != null) {
		Member member = (Member) session.getAttribute("employee");
		BusinessUnit busUnit = (BusinessUnit) session.getAttribute("busUnit");
		
		RealTimePaymentDAO rtdao = new RealTimePaymentDAO();
		boolean check = rtdao.CheckPayment(busUnit.getId(), request.getParameter("insuranceMonth"),idIT);
		if(check == true){
			response.sendRedirect("searchBusiness.jsp");
		}
		
		InsuranceTypeDAO indao = new InsuranceTypeDAO();
		ArrayList <InsuranceType> InsuranceTypeList = indao.getInsuranceType();
		
		BusinessInsuranceInfoDAO dao = new BusinessInsuranceInfoDAO();
		BusinessInsuranceInfo insuranceInfo = dao.getInsuranceInfo(busUnit, request.getParameter("insuranceMonth"));
		float[] InsuranceList = {insuranceInfo.getEmployeeSocialInsurance() + insuranceInfo.getBusinessSocialInsurance(),insuranceInfo.getEmployeeMedicalInsurance() + insuranceInfo.getBusinessMedicalInsurance(),
				insuranceInfo.getEmployeeUnemployedInsurance() + insuranceInfo.getBusinessUnemployedInsurance(), insuranceInfo.getEmployeeUnionFee() + insuranceInfo.getBusinessUnionFee()};
		session.setAttribute("money", InsuranceList[idIT-1]);
		
	%>
	<div class="content-wrapper pt-4">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<h2 class="page-title">Xác nhận thanh toán</h2>
				</div>
			</div>
		</div>
	<form name="confirmPayment" action="doConfirm.jsp" method="post">
	  <div>
	    <label >Thời gian thanh toán</label>
	     <input type="date" id="realTime" name="realTime" required >
	  </div>
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label>Ngân hàng</label>
	      <input type="text" class="form-control" id="inputBank" name="BankName" required>
	    </div>
	    <div class="form-group col-md-5">
	      <label >Mã giao dịch</label>
	      <input type="text" class="form-control" name="inputCode" required>
	    </div>
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label >Nội dung giao dịch</label>
		    <input type="text" class="form-control" id="inputDes" placeholder="Nội dung giao dịch..." name="Description" required>
		  </div>
		  
	  </div>
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputCity">Số tiền</label>
	      <input type="text" class="form-control" id="inputMoney" placeholder="VNĐ" name="Money" value=<fmt:formatNumber type = "number" value = "<%= InsuranceList[idIT-1]%>" /> readonly>
	    </div>
	    <div class="form-group col-md-4">
	      <label >Phương thức thanh toán</label>
	      <input type="text" class="form-control" placeholder="Phương thức giao dịch..." name="inputPayment" required>
	    </div>
	  </div>
	  <button type="submit" class="btn btn-primary">Xác nhận</button>
	</form>
		<table class="table">
			<thead class="thead-light">
				<tr>
					<td colspan="4">Tên công ty: <%=busUnit.getName()%></td>
				</tr>
				<tr>
					<td colspan="4">Mã đơn vị: <%=busUnit.getUnitCode()%></td>
				</tr>
				<tr>
					<td colspan="4">Mã số thuế: <%=busUnit.getTaxCode()%></td>
				</tr>
				<tr>
					<td colspan="4">Loại bảo hiểm: <%=InsuranceTypeList.get(idIT-1).getName()%></td>
				</tr>
			</thead>
			<tbody>
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