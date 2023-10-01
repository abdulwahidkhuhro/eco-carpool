<%@page import="com.connect.ecocarpool.entities.Driver"%>
<%@page import="java.util.ArrayList"%>
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
				<div class="user-name">Abdul Wahid Khuhro</div>
				<a href="#" class="logout-btn">Log Out</a>
			</div>
		</div>
		<div class="admin-home-body">
			<div class="home-btn-container">
				<div class="home-btn-box">
					<a href="home" class="home-btn">Home</a>
				</div>
			</div>

			<div class="admin-title-container">
				<div class="admin-tile">
					<h1>All Drivers</h1>
				</div>
			</div>
			<div class="admin-container">
				<table>
					<tr>
						<th>UserName</th>
						<th>Driver Name</th>
						<th>Driving License</th>
						<th>Password</th>
						<th>Update</th>
						<th>Delete</th>
						<th>View Jobs</th>
					</tr>

					<%
					ArrayList<Driver> driversList = (ArrayList<Driver>) session.getAttribute("driversList");
					for (int i = 0; i < driversList.size(); i++) {
						Driver driver = driversList.get(i);
					%>
					<tr>
						<td style="width: 120px"><a><%=driver.getUsername()%></a></td>
						<td><%=driver.getFullName()%></td>
						<td><%=driver.getDrivingLicense()%></td>
						<td style="width: 120px"><%=driver.getPassword()%></td>
						<td style="width: 90px"><a href="task?action=update&userType=driver&username=<%= driver.getUsername() %>" class="update-btn">Update</a></td>
						<td style="width: 90px"><a href="task?action=delete&userType=driver&username=<%= driver.getUsername() %>" class="delete-btn">Delete</a></td>
						<td style="width: 120px"><a href="task?action=jobs&userType=driver&username=<%= driver.getUsername() %>" class="view-jobs-btn">View
								Jobs</a></td>
					</tr>
					<%
					System.out.println(driversList.get(i).getUsername());
					}
					%>

				</table>
			</div>
		</div>
	</div>
</body>
</html>