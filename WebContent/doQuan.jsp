<%@page contentType="text/html" pageEncoding="UTF-8" import="src.model.*,src.dao.*,java.util.*,java.text.*"%>
<%
if(request.getParameter("tinh") != "" && request.getParameter("tinh") != null) {
	QuanDAO qdao = new QuanDAO();
	ArrayList<District> ListQuan = qdao.getQuanByTinh(Integer.parseInt(request.getParameter("tinh")));
	session.setAttribute("quan", ListQuan);
}
%>
