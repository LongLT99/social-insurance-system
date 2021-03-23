<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList,src.dao.*,src.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background-color: #eee !important;
	background-size: cover;
	background-position: center;
	font-family: sans-serif;
}

.loginbox {
	width: 320px;
	height: 420px;
	background: #fff;
	color: #000;
	top: 50%;
	left: 50%;
	position: absolute;
	transform: translate(-50%, -50%);
	box-sizing: border-box;
	padding: 70px 30px;
}

.avatar {
	width: 100px;
	height: 100px;
	border-radius: 50%;
	position: absolute;
	top: -50px;
	left: calc(50% - 50px);
}

h1 {
	margin: 0;
	padding: 0 0 20px;
	text-align: center;
	font-size: 22px;
}

.loginbox p {
	margin: 0;
	padding: 0;
	font-weight: bold;
}

.loginbox input {
	width: 100%;
	margin-bottom: 20px;
}

.loginbox input[type="text"], input[type="password"] {
	border: none;
	border-bottom: 1px solid #000;
	background: transparent;
	outline: none;
	height: 40px;
	color: #000;
	font-size: 16px;
}

.loginbox input[type="submit"] {
	border: none;
	outline: none;
	height: 40px;
	background: #2f889a;
	color: #fff;
	font-size: 18px;
	border-radius: 20px;
}

.loginbox input[type="submit"]:hover {
	cursor: pointer;
	background: #ffc107;
	color: #000;
}

.loginbox a {
	text-decoration: none;
	font-size: 12px;
	line-height: 20px;
	color: darkgrey;
}

.loginbox a:hover {
	color: #ffc107;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<%
	String username = (String) request.getParameter("username");
String password = (String) request.getParameter("password");
if (username != null || password != null) {
	Member member = new Member();
	member.setUsername(username);
	member.setPassword(password);

	MemberDAO dao = new MemberDAO();
	boolean kq = dao.checkLogin(member);

	if (kq && (member.getRole().equalsIgnoreCase("business"))) {
		session.setAttribute("business", member);
		response.sendRedirect("business\\businessHome.jsp");
	} else if (kq && (member.getRole().equalsIgnoreCase("employee"))) {
		session.setAttribute("employee", member);
		response.sendRedirect("employee\\employeeHome.jsp");
	} else {
		response.sendRedirect("login.jsp?err=fail");
	}
}
%>
<body>
	<%
      if(request.getParameter("err") !=null && request.getParameter("err").equalsIgnoreCase("timeout")){
          %> <h4>Hết phiên làm việc. Làm ơn đăng nhập lại!</h4><%
      }else if(request.getParameter("err") !=null && request.getParameter("err").equalsIgnoreCase("fail")){
          %> <h4 color="red">Sai tên đăng nhập/mật khẩu!</h4><%
      }
      %>
      <%
			if(request.getParameter("idok")!=null){
				%>
					<div class="alert alert-success alert-dismissible fade show" role="alert">
					   Xác nhận thành công!
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>
				<% 
			}
			%>
	<div class="loginbox">
    <img src="https://img.icons8.com/clouds/2x/login-rounded-right.png" class="avatar">
        <h1>Đăng nhập</h1>
        <form name="dangnhap" action="login.jsp" method="post">
            <p>Username</p>
            <input type="text" name="username"  id="username">
            <p>Password</p>
            <input type="password" name="password"  id="password">
            <input type="submit" name="" value="Login">
            <a href="#">Quên mật khẩu ?</a><br>
            <a href="register.jsp">Bạn chưa có tài khoản?</a>
        </form>
        
    </div>
</body>
</html>