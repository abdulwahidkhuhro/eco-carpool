<%@page import="com.connect.ecocarpool.entities.Booking"%>
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
					<h1>All Bookings</h1>
				</div>
			</div>
			<div class="user-container">
				<table>
					<tr>
						<th>ID</th>
						<th>Pickup</th>
						<th>Drop Off</th>
						<th>Date</th>
						<th>Time</th>
						<th>Service</th>
						<th>Username</th>
						<th>Status</th>
					</tr>
					<%
					ArrayList<Booking> bookings = (ArrayList<Booking>) session.getAttribute("bookings");
					for (int i = 0; i < bookings.size(); i++) {
						Booking booking = bookings.get(i);
					%>
					<tr>
						<td><%=booking.getId()%></td>
						<td><%=booking.getPickup()%></td>
						<td><%=booking.getDropOff()%></td>
						<td><%=booking.getDate()%></td>
						<td><%=booking.getTime()%></td>
						<td><%=booking.getService()%></td>
						<td><%=booking.getUsername()%></td>

						<td width="100px">
							<%
							if (booking.getStatus() == 0) {
							%><a href="booking?action=assignDriver&id=<%=booking.getId()%>"
							class="pending-badge">Assign</a> <%
 } else if (booking.getStatus() == 1) {
 %><a class="assigned-badge">Assigned</a> <%
 }
 %>
						</td>
					</tr>

					<%
					}
					%>

				</table>
			</div>
		</div>
	</div>
</body>
</html>