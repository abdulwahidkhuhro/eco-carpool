<%@page import="com.connect.ecocarpool.entities.User"%>
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
				User user = (User) session.getAttribute("currentUser");
				System.out.println("userHomePage customer : " + user);
				%>
				<div class="user-name">abdul wahid</div>
				<a href="logout" class="logout-btn" class="logout">Log Out</a>
			</div>
		</div>
		<div class="user-home-body">
			<div class="admin-title-container">
				<div class="admin-tile">
					<h1>Driver Panel</h1>
				</div>
			</div>
			<div class="user-container">
				<div class="view-btn-container">
					<a class="view-assigned-jobs-btn" href="driver?action=assignedJobs">View Assigned Jobs</a>
				</div>
			</div>

			<div class="user-home-footer">footer</div>
		</div>

	</div>

</body>
</html>