<%@page import="com.connect.ecocarpool.entities.Message"%>
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
		<div class="user-navbar" >
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
					<h1>Register Driver</h1>
				</div>
			</div>
			<div class="admin-container">
				<div class="registration-form-container">
					<form class="registration-form" action="admin?action=registerDrive"
						method="POST">
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
							<div class="field-title">Username</div>
							<input type="text" class="field-input-box" name="username">
						</div>
						<div class="field">
							<div class="field-title">Driver Name</div>
							<input type="text" class="field-input-box" name="name">
						</div>
						<div class="field">
							<div class="field-title">Driving License Number</div>
							<input type="text" class="field-input-box"
								name="drivingLicenseNumber">
						</div>
						<div class="field">
							<div class="field-title">Contact Number</div>
							<input type="text" class="field-input-box" name="contactNumber">
						</div>
						<div class="field">
							<div class="field-title">Password</div>
							<input type="password" class="field-input-box" name="password">
						</div>

						<div class="register-btn-container">
							<input type="submit" value="Register" class="register-btn">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>