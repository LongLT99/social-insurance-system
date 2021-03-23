<%@page import="org.omg.CORBA.OMGVMCID"%>
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
				session.setAttribute("latestProcess", latestProcess);
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
	  <button onclick="needUpdate()" type="<%=needUpdate ? "" : "submit"%>" class="btn btn-primary">Xác nhận</button>
	</form>
		</div>

<script type="text/javascript">
	function needUpdate() {
		var noti = "Thời gian bắt đầu của quá trình mới nhỏ hơn thời gian kết thúc của quá trình cũ. " +
		"Đi đến điều chỉnh thời gian của quá trình cũ?";
		var id = <%=needUpdate%>;
		if(id) {
			if(confirm(noti)) {
				window.location.replace("editInsuranceProcess.jsp?id="+id);
			}
		}
	}
</script>


	<%
	
	} else {
		response.sendRedirect("../login.jsp");
	}
	%>

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


