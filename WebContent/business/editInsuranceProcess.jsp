<%@page import="org.omg.CORBA.OMGVMCID"%>
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
	<%
		request.setCharacterEncoding("UTF-8");
		if (session.getAttribute("business") != null) {
		Member member = (Member) session.getAttribute("employee");
		BusinessUnit busUnit = (BusinessUnit) session.getAttribute("busUnit");
		ArrayList<Labour> labours = (ArrayList<Labour>) session.getAttribute("labours");
		InsuranceProcess insuranceProcess = (InsuranceProcess) session.getAttribute("latestProcess");
		ArrayList<InsuranceProcessType> types = new ArrayList<InsuranceProcessType>();
		InsuranceProcessTypeDAO typeDAO = new InsuranceProcessTypeDAO();
		types = typeDAO.getTypes();
		
	%>
	<div class="content-wrapper pt-4">
	<h2>Sửa quá trình tham gia BHXH</h2>
	<form name="addProcess" action="doEdit.jsp" method="post">
	  <div class="form-row">
	  <div class="form-group col-md-11">
	    <label >Mã HĐLĐ</label>
	     <input type="text" class="form-control" id="contractCode" name="contractCode" 
	     	value="<%=insuranceProcess.getContractCode() == null ? "" : insuranceProcess.getContractCode()%>" required >
	     </div>
	  </div>
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label>Thời gian bắt đầu</label>
	      <input type="date" class="form-control" id="startDate" name="startDate" 
	      	value="<%=insuranceProcess.getStartTime() %>" required>
	    </div>
	    <div class="form-group col-md-5">
	      <label >Thời gian kết thúc</label>
	      <input type="date" class="form-control" id="endDate" name="endDate"
	      	value="<%=insuranceProcess.getEndTime() == null ? "" : insuranceProcess.getEndTime()%>" required>
	    </div>
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label >Phòng ban</label>
		    <input type="text" class="form-control" id="division" name="division"
		    	value="<%=insuranceProcess.getDivision() == null ? "" : insuranceProcess.getDivision()%>" required>
		  </div>
		  
	  </div>
	   <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label >Chức danh</label>
		    <input type="text" class="form-control" id="position" name="position"
		    	value="<%=insuranceProcess.getPosition() == null ? "" : insuranceProcess.getPosition()%>" required>
		  </div>
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label >Lương bảo hiểm</label>
		    <input type="number" class="form-control" id="salary" name="salary"
		    	value="<%=insuranceProcess.getInsuranceSalary() == null ? "" : insuranceProcess.getInsuranceSalary()%>" required>
		  </div>
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label >Loại tăng giảm</label>
		    <select name="processType" required>
		    <option value="">-- Chọn --</option>
		    <%
		    	for(InsuranceProcessType type : types) {
    		 %>	
		    	<option value="<%=type.getId()%>" selected="<%=insuranceProcess.getProcessType().getId() == type.getId()%>"><%=type.getName() %></option>
		   <%} %>
		    </select>
		  </div>
	  </div>
	  <button type="submit" class="btn btn-primary">Xác nhận</button>
	</form>
		</div>
</body>
</html>
	<%
	
	} else {
		response.sendRedirect("../login.jsp");
	}
	%>




