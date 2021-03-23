<%@page contentType="text/html" pageEncoding="UTF-8" import="src.model.*,src.dao.*,java.util.*,java.text.*"%>
<%
if(request.getParameter("quan") != "" && request.getParameter("quan") != null) {
	PhuongDAO qdao = new PhuongDAO();
	ArrayList<Ward> ListPhuong = qdao.getPhuongByQuan(Integer.parseInt(request.getParameter("quan")));
	session.setAttribute("phuong", ListPhuong);
}
%>
