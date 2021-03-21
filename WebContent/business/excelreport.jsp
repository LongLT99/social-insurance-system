<%@page import="src.model.BusinessUnit"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page import="src.model.Labour" %>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Thông tin đóng BHXH</title>
   </head>
   <body>
   <% 
   	BusinessUnit busUnit = (BusinessUnit) session.getAttribute("busUnit");
   %>
      <h1>Thông tin đóng BHXH tháng <%=session.getAttribute("insuranceMonth") %></h1>
      <h4>Doanh nghiệp: <%=busUnit.getName() %></h4>
      <table cellpadding="1"  cellspacing="1" border="1" bordercolor="gray">
         <tr>
					<th rowspan="2">Mã số thuế</th>
					<th rowspan="2">Họ tên</th>
					<th rowspan="2">Lương BH</th>
					<th rowspan="2">Tham gia tổ chức công đoàn</th>
					<th colspan="5" class="text-center">Các khoản tính vào chi phí
						doanh nghiệp</th>
					<th colspan="5" class="text-center">Các khoản trích trừ vào
						lương</th>
				</tr>
				<tr>
					<th>KPCĐ</th>
					<th>BHXH</th>
					<th>BHYT</th>
					<th>BHTN</th>
					<th>Cộng</th>
					<th>KPCĐ</th>
					<th>BHXH</th>
					<th>BHYT</th>
					<th>BHTN</th>
					<th>Cộng</th>
				</tr>
         <%
            List<Labour> labours  = (List<Labour>)session.getAttribute("employees");
                  if (labours != null) {
                      response.setContentType("application/vnd.ms-excel");
                      response.setHeader("Content-Disposition", "inline; filename="+ "labour_report.xls");
                  }
            for(Labour labour : labours){
            %>
         <tr>
					<td><%=labour.getInsuranceCode() %></td>
					<td><%=labour.getName() %></td>
					<td><fmt:formatNumber type = "number" value = "<%=labour.getInsuranceSalary() %>" /></td>
					<td>Để lại</td>
					<td>Để lại</td>
					<td><%=labour.getBusinessSocialInsurance() %></td>
					<td><%=labour.getBusinessMedicalInsurance() %></td>
					<td><%=labour.getBusinessUnemployedInsurance() %></td>
					<td>Để lại</td>
					<td>Để lại</td>
					<td><%=labour.getSocialInsurance() %></td>
					<td><%=labour.getMedicalInsurance() %></td>
					<td><%=labour.getUnemployedInsurance() %></td>
					<td>Để lại</td>
				</tr>
         <% 
            }
            %>
      </table>
   </body>
</html>