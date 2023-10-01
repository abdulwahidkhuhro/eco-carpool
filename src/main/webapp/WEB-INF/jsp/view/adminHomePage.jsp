<%@page import="com.connect.ecocarpool.entities.User"%>
<%@page import="com.connect.ecocarpool.entities.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/style.css">
<jsp:include page="links.jsp" />

</head>
<body>
	<div class="home-container">


		<div class="user-navbar">
			<div class="logo-container">
				<div class="logo">
					<span class="logo-prefix">Eco</span><span class="logo-suffix">Carpool</span>
				</div>
			</div>
			<div class="user-details">
				<%
				User currentUser = (User) session.getAttribute("currentUser");
				
				%>
				<div class="user-name"><%= currentUser.getFullName() %></div>
				<a href="logout" class="logout-btn">Log Out</a>
			</div>
		</div>

		<div class="admin-home-body">
			<div class="admin-title-container">
				<div class="admin-tile">
					<h1>Admin Panel</h1>
				</div>
			</div>
			<div class="admin-container">
				<ul class="admin-menu">
					<li><a class="admin-menu-item"
						href="admin?action=registerDrive">Register New Driver</a></li>
					<li><a class="admin-menu-item"
						href="admin?action=registerCustomer">Register New
							Customer</a></li>
					<li><a class="admin-menu-item"
						href="admin?action=listDrivers">List all Drivers</a></li>
					<li><a class="admin-menu-item"
						href="admin?action=listCustomer">List All Customer</a></li>
					<li><a class="admin-menu-item"
						href="admin?action=assignBooking">Assign Bookings</a></li>
					<li><a class="admin-menu-item" href="admin?action=generateCustomer">Generate Customer
							Report</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>