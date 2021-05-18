<%@page import="org.omg.CORBA.OMGVMCID"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="src.model.*,src.dao.*,java.util.*,java.text.*"%>
<%@include file ="../header.jsp" %>
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
		Member member = (Member) session.getAttribute("business");
		BusinessUnit busUnit = (BusinessUnit) session.getAttribute("busUnit");
		Labour labour = new Labour();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Labour> labours = (ArrayList<Labour>) session.getAttribute("labours");
		LabourDAO dao = new LabourDAO();
		if(request.getParameter("insCode") != null) {
			labour.setName(request.getParameter("name"));
			labour.setIdNumber(request.getParameter("idNumber"));
			labour.setInsuranceCode(request.getParameter("insCode"));
			Date dob = new Date(format.parse(request.getParameter("dob")).getTime());
			labour.setDateOfBirth(dob);
			labour.setGender(request.getParameter("gender") == "1" ? 1 : 2);
			labour.setNationality(request.getParameter("nationality"));
			labour.setEthnic(request.getParameter("ethnic"));
			labour.setBusinessUnit(busUnit);
			labour.setIsUnion(request.getParameter("isCD") == "true" ? 1 : 0);
			boolean result = dao.addLabour(labour, busUnit);
		}
		
	%>
	<div class="content-wrapper pt-4">
	<h2>Thêm người lao động</h2>
	<form name="addLabour" action="addLabour.jsp" method="post">
	  <div class="form-row">
	  <div class="form-group col-md-11">
	    <label>Họ tên</label>
	     <input type="text" class="form-control" id="name" name="name" required >
	     </div>
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label>Mã BHXH</label>
		    <input type="text" class="form-control" id="insCode" name="insCode" required>
		  </div>
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label >Số CMND</label>
		    <input type="text" class="form-control" id="idNumber" name="idNumber" required>
		  </div>
	  </div>
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label>Ngày sinh</label>
	      <input type="date" class="form-control" id="dob" name="dob" required>
	    </div>
	    <div class="form-group col-md-5">
	    	<div class="row">
	    		<div>
	    			<label >Giới tính</label>
	    		</div>
	        	<div class="col-11">
	        		<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" id="male" name="gender" value="1">
					  <label class="form-check-label" for="male">Nam</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" id="female" name="gender" value="2">
					  <label class="form-check-label" for="female">Nữ</label>
					</div>
	        	</div>
	    		
	    	</div>
	    </div>
	    	    
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label>Quốc tịch</label>
		    <input type="text" class="form-control" id="nationality" name="nationality" required>
		  </div>
		  
	  </div>
	   <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label>Dân tộc</label>
		    <input type="text" class="form-control" id="ethnic" name="ethnic" required>
		  </div>
	  </div>
	  <div class="form-row">
	  	<div class="form-group col-md-11">
		    <label>Tham gia công đoàn</label>
		    <input type="checkbox" name="isCD" value="1">
		  </div>
	  </div>
	  <button type="submit" class="btn btn-primary">Thêm lao động</button>
	</form>
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

</style>