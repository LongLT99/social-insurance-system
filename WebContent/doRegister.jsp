<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,src.dao.*,src.model.*,java.sql.*"%>
 
<%
	request.setCharacterEncoding("UTF-8");
	try{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String madonvi = request.getParameter("madonvi");
		String ten = request.getParameter("ten");
		String masothue = request.getParameter("masothue");
		int CoquanBHid = Integer.parseInt(request.getParameter("tencqbh")) ;
		String email = request.getParameter("email");
		String sdt = request.getParameter("sdt");
		
		int phuongid = Integer.parseInt(request.getParameter("phuong"));
		String chitiet = request.getParameter("chitiet");
		
		
		MemberDAO thanhvienDAO = new MemberDAO();
		thanhvienDAO.luuthanhvien(username, password, email, sdt);
		
		int idthanhvien;
		idthanhvien = thanhvienDAO.getIdThanhVien(username, password);
		
		AddressDAO diachiDAO = new AddressDAO();
		diachiDAO.luudiachi(chitiet,phuongid);
		
		BusinessUnitDAO donvibhDAO = new BusinessUnitDAO();
		donvibhDAO.luudonvibh(madonvi, ten, masothue, CoquanBHid, idthanhvien);
		
		
		response.sendRedirect("login.jsp?idok=1");
	}catch(Exception e){
		response.sendRedirect("webHome.jsp");
	}
	
   	
%>