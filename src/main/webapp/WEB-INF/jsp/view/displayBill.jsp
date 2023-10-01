<%@page import="com.connect.ecocarpool.entities.Message"%>
<%@page import="com.connect.ecocarpool.entities.Bill"%>
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
				Bill bill = (Bill) session.getAttribute("bill");
				%>

				<div class="user-name"></div>
				<div class="user-name"><%=user.getUsername()%></div>
				<a href="logout" class="logout-btn" class="logout">Log Out</a>
			</div>
		</div>

		<div class="user-home-body">

			<div class="home-btn-container">
				<div class="home-btn-box">
					<a href="home" class="home-btn">Home</a>
				</div>
			</div>
			<div class="admin-title-container">
				<div class="admin-tile">
					<h1>Bill</h1>
				</div>
			</div>


			<div class="user-rides-container" id="user-rides-container-id">
				<div class="user-container">
					<div class="bill-box">
						<form class="registration-form" action="customer" method="POST">
							<%
							Message message = (Message) session.getAttribute("message");
							if (message != null) {
							%>
							<div class="<%=message.getCssClass()%>">
								<span><%=message.getContent()%></span>
							</div>
							<%
							session.removeAttribute("message");
							}
							%>
							<div class="field">
								<div class="field-title">Ride Id #</div>
								<input type="text" class="field-input-box" name="id"
									disabled="disabled" value="<%=bill.getId()%>">
							</div>
							
							<div class="field">
								<div class="field-title">Driver #</div>
								<input type="text" class="field-input-box" name="date"
									disabled="disabled" value="<%=bill.getDriver()%>">
							</div>
							
							<div class="field">
								<div class="field-title">Date #</div>
								<input type="text" class="field-input-box" name="date"
									disabled="disabled" value="<%=bill.getDate()%>">
							</div>
							<div class="field">
								<div class="field-title">Pickup #</div>
								<input type="text" class="field-input-box" name="pickup"
									disabled="disabled" value="<%=bill.getPickup()%>">
							</div>
							<div class="field">
								<div class="field-title">Drop off #</div>
								<input type="text" class="field-input-box" name="dropoff"
									disabled="disabled" value="<%=bill.getDropoff()%>">
							</div>
							<div class="field">
								<div class="field-title">Distance #</div>
								<input type="text" class="field-input-box" name="distance"
									disabled="disabled" value="<%=bill.getDistance()%>">
							</div>

							<div class="field">
								<div class="field-title">Service #</div>
								<input type="text" class="field-input-box" name="service"
									disabled="disabled" value="<%=bill.getServiceType()%>">
							</div>

							<div class="field">
								<div class="field-title">Payment #</div>
								<input type="text" class="field-input-box" name="service"
									disabled="disabled" value="<%=bill.getPayment()%>">
							</div>


						</form>
					</div>
				</div>

			</div>
			<div class="user-home-footer">footer</div>
		</div>
	</div>




</body>
</html>