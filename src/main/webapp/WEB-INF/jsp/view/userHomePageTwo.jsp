<%@page import="com.connect.ecocarpool.entities.User"%>
<%@page import="com.connect.ecocarpool.entities.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.connect.ecocarpool.entities.Message"%>
<%@page import="com.connect.ecocarpool.entities.Customer"%>
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

			<div class="user-home-tabs">
				<div class="user-home-tabs-btns">

					<a class="user-home-tab-btn" href="customer?action=bookRide">Book
						a Ride</a> <a class="user-home-tab-btn-selected"
						href="customer?action=myRides">My Rides</a>

				</div>
			</div>

			<div class="user-rides-container" id="user-rides-container-id">
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
							<th>Bills</th>
						</tr>
						<%
						ArrayList<Booking> bookings = (ArrayList<Booking>) session.getAttribute("bookings");
						for (int i = 0; i < bookings.size(); i++) {
							Booking booking = bookings.get(i);
						%><tr>
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
								%><div class="pending-badge">Pending</div> <%
 }
 %> <%
 if (booking.getStatus() == 1) {
 %><div class="assigned-badge">Assigned</div> <%
 }
 %> <%
 if (booking.getStatus() == 2) {
 %><div class="completed-badge">Completed</div> <%
 }
 %>
							</td>
							<td width="100px">
							<%
							if(booking.getStatus() != 0){
								
								%> <a href="bill?id=<%=booking.getId()%>" class="bill-payment">Bill</a><%
							}else{
								%><a class="no-bill-payment">Bill</a><%
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
	<!--  -->
</body>
</html>