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
		Integer selectedLabourId = Integer.parseInt(request.getParameter("idld"));
		Labour selectedLabour = new Labour();
		ArrayList<Labour> labours = (ArrayList<Labour>) session.getAttribute("labours");
		InsuranceProcess insuranceProcess = new InsuranceProcess();
		InsuranceProcessDAO dao = new InsuranceProcessDAO();
		
		boolean needUpdate = false;
		
		ArrayList<InsuranceProcessType> types = new ArrayList<InsuranceProcessType>();
		InsuranceProcessTypeDAO typeDAO = new InsuranceProcessTypeDAO();
		types = typeDAO.getTypes();
		
		for(Labour labour : labours) {
			if(labour.getId() == selectedLabourId) {
				selectedLabour = labour;
			}
		}
		InsuranceProcess latestProcess = dao.getLatestProcess(selectedLabour);
		insuranceProcess.setLabour(selectedLabour);
		if(request.getParameter("salary") != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date sd = format.parse(request.getParameter("startDate").toString());
			java.util.Date ed = format.parse(request.getParameter("endDate").toString());
			Date startDate = new Date(sd.getTime());
			Date endDate = new Date(ed.getTime());
			
			if(latestProcess.getEndTime() != null &&
					startDate.compareTo(latestProcess.getEndTime()) < 0 ) {
					needUpdate = true;
					%>
					<script type="text/javascript">
						alert("Không thể thêm quá trình mới do thời gian bắt đầu nhỏ hơn thời gian kết thúc của quá trình trước");
					</script>
					<%
			} else {
				insuranceProcess.setInsuranceSalary(Float.parseFloat(request.getParameter("salary")));
				insuranceProcess.setDivision(request.getParameter("division"));
				insuranceProcess.setPosition(request.getParameter("position"));
				insuranceProcess.setContractCode(request.getParameter("contractCode"));
				insuranceProcess.setStartTime(startDate);
				insuranceProcess.setEndTime(endDate);
				for(InsuranceProcessType type : types) {
					if(type.getId() == Integer.parseInt(request.getParameter("processType"))) {
						insuranceProcess.setProcessType(type);
					}
				}
				boolean result = dao.addInsuranceProcess(insuranceProcess);
				if(result) {
					%>
					<script type="text/javascript">
						confirm("Thêm quá trình BHXH mới thành công");
						window.location.replace("labourInsuranceProcess.jsp?idld="+<%=selectedLabour.getId()%>);
					</script>
					<%
				} else {
					%>
					<script type="text/javascript">
						alert("Có lỗi xảy ra!");
					</script>
					<%
				}
			}
			
		}
	%>
	<div class="content-wrapper pt-4">
	<h2>Thêm quá trình tham gia BHXH</h2>
	<form name="addProcess" action="addInsuranceProcess.jsp?idld=<%=selectedLabourId %>" method="<%=needUpdate ? "" : "post"%>">
	  <div class="form-row">
	  <div class="form-group col-md-11">
	    <label >Mã HĐLĐ</label>
	     <input type="text" class="form-control" id="contractCode" name="contractCode" required >
	     </div>
	  </div>
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label>Thời gian bắt đầu</label>
	      <input type="date" class="form-control" id="startDate" name="startDate" required>
	    </div>
	    <div class="form-group col-md-5">
	      <label >Thời gian kết thúc</label>
	      <input type="date" class="form-control" id="endDate" name="endDate" required>
	    </div>
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label >Phòng ban</label>
		    <input type="text" class="form-control" id="division" name="division" required>
		  </div>
		  
	  </div>
	   <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label >Chức danh</label>
		    <input type="text" class="form-control" id="position" name="position" required>
		  </div>
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label >Lương bảo hiểm</label>
		    <input type="number" class="form-control" id="salary" name="salary" required>
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
		    	<option value="<%=type.getId()%>"><%=type.getName() %></option>
		   <%} %>
		    </select>
		  </div>
	  </div>
	  <button type="<%=needUpdate ? "" : "submit"%>" name="btnSubmit" class="btn btn-primary">Xác nhận</button>
	</form>
		</div>
</body>
</html>

	<%
	
	} else {
		response.sendRedirect("../login.jsp");
	}
	%>




