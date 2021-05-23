<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,src.dao.*,src.model.*,java.sql.*"%>
 
<%
	
	request.setCharacterEncoding("UTF-8");
    String bank = (String)request.getParameter("BankName");
    String descrip = (String)request.getParameter("Description");
    String code = (String)request.getParameter("inputCode");
    float money;
    money = (float)session.getAttribute("money");
    int loaibhid, nhanvienid, iddonvi;
    loaibhid = (int)session.getAttribute("idit");
    String pay = (String)request.getParameter("inputPayment");
    Member member = (Member) session.getAttribute("employee");
    nhanvienid = (int) member.getId();
    BusinessUnit busUnit = (BusinessUnit) session.getAttribute("busUnit");
    iddonvi = (int)busUnit.getId();
    
   
    		
    RealTimePayment rtPay = new RealTimePayment();
    rtPay.setTennganhang(bank);
    rtPay.setMagiaodich(code);
    rtPay.setNoidunggd(descrip);
    rtPay.setSotien(money);
    rtPay.setPhuongthuc(pay);
    rtPay.setLoaibhid(loaibhid);
    rtPay.setNhanvienid(nhanvienid);
    rtPay.setIddonvibh(iddonvi);
    String tgdong = (String)request.getParameter("realTime");
    String thangdong = (String)session.getAttribute("insuranceMonth");
    RealTimePaymentDAO rtdao = new RealTimePaymentDAO();
    boolean check = rtdao.CheckPayment(iddonvi, thangdong,loaibhid);
   	if(check){
   		response.sendRedirect("searchBusiness.jsp?idalert=0");
   	}
    
    RealTimePaymentDAO dao = new RealTimePaymentDAO();
    boolean kq;
    kq = dao.ConfirmPayment(rtPay, tgdong, thangdong);

    if(kq){
    	%>
	    <script type="text/javascript">
	        alert("Đăng kí thành công!");
	    </script>
		<%
        response.sendRedirect("employeeHome.jsp?idok=1");
    }else{
    	%>
	    <script type="text/javascript">
	        alert("Đã xảy ra lỗi lưu xác nhận, chưa xác nhận đóng tiền thành công!");
	    </script> 
	<%
	response.sendRedirect("searchBusiness.jsp?idalert=1");
    }
%>