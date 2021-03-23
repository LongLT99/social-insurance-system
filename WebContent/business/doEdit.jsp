<%@page import="org.omg.CORBA.OMGVMCID"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="src.model.*,src.dao.*,java.util.*,java.text.*"%>

<%
	Labour selectedLabour = new Labour();
	ArrayList<Labour> labours = (ArrayList<Labour>) session.getAttribute("labours");
	InsuranceProcess insuranceProcess = new InsuranceProcess();
	InsuranceProcessDAO dao = new InsuranceProcessDAO();
	ArrayList<InsuranceProcessType> types = new ArrayList<InsuranceProcessType>();
	InsuranceProcessTypeDAO typeDAO = new InsuranceProcessTypeDAO();
	types = typeDAO.getTypes();
	insuranceProcess.setLabour(selectedLabour);
	insuranceProcess.setInsuranceSalary(Float.parseFloat(request.getParameter("salary")));
	insuranceProcess.setDivision(request.getParameter("division"));
	insuranceProcess.setPosition(request.getParameter("position"));
	insuranceProcess.setContractCode(request.getParameter("contractCode"));
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date sd = format.parse(request.getParameter("startDate").toString());
	java.util.Date ed = format.parse(request.getParameter("endDate").toString());
	Date startDate = new Date(sd.getTime());
	Date endDate = new Date(ed.getTime());
	insuranceProcess.setStartTime(startDate);
	insuranceProcess.setEndTime(endDate);
	for (InsuranceProcessType type : types) {
		if (type.getId() == Integer.parseInt(request.getParameter("processType"))) {
			insuranceProcess.setProcessType(type);
		}
	}
	boolean result = dao.updateProcess(insuranceProcess);
%>