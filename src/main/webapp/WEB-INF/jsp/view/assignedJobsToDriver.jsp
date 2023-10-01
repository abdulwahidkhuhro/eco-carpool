<%@page import="com.connect.ecocarpool.entities.Booking"%>
<%@page import="java.util.ArrayList"%>
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
				%>
				<div class="user-name"><%=user.getUsername()%></div>
				<a href="logout" class="logout-btn" class="logout">Log Out</a>
			</div>
		</div>
		<div class="user-home-body">


			<div class="user-rides-container" id="user-rides-container-id">
				<div class="admin-title-container">
					<div class="admin-tile">
						<h1></h1>
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
						ArrayList<Booking> bookings = (ArrayList<Booking>) session.getAttribute("jobs");
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
								if (booking.getStatus() == 1) {
								%><a href="driver?action=complete&id=<%=booking.getId()%>"
								class="assigned-badge">Complete</a> <%
 } else if (booking.getStatus() == 2) {
 %><a class="completed-badge">Completed</a> <%
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
			<div class="user-home-footer">footer</div>
		</div>
	</div>
</body>
</html>