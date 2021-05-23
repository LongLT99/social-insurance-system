<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,src.dao.*,src.model.*,java.sql.*"%>
 
<%
	request.setCharacterEncoding("UTF-8");
	
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
		Member member = new Member(username,password,email,sdt);
		
		boolean res;
		res = thanhvienDAO.luuthanhvien(member);
		
		int idthanhvien;
		idthanhvien = thanhvienDAO.getIdThanhVien(username, password);
		
		AddressDAO diachiDAO = new AddressDAO();
		diachiDAO.luudiachi(chitiet,phuongid);
		
		BusinessUnitDAO donvibhDAO = new BusinessUnitDAO();
		donvibhDAO.luudonvibh(madonvi, ten, masothue, CoquanBHid, idthanhvien);
		
		if(res){
			%>
		    <script type="text/javascript">
		        alert("Đăng kí thành công!");
		    </script>
			<%
			response.sendRedirect("login.jsp?idok=1");
		}else{
			%>
		    <script type="text/javascript">
		        alert("Đã xảy ra lỗi!");
		    </script> 
		<%
		response.sendRedirect("webHome.jsp");
		}
	
	
   	
%>