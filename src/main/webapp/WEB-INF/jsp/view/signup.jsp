<%@page import="com.connect.ecocarpool.entities.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>

<link rel="stylesheet" href="./css/style.css">
<jsp:include page="links.jsp" />

</head>
<body>

	<div class="container">
		<div class="registration-box">
			<form action="signup" method="POST">
				<div class="title-box">
					<h1>Sign up with</h1>
					<div class="logo">
						<span class="logo-prefix">Eco</span><span class="logo-suffix">Carpool</span>
					</div>
				</div>
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

				<input type="text" class="input-field" placeholder="Username"
					name="username"> <input type="text" class="input-field"
					placeholder="Full Name" name="fullname"> <input type="text"
					class="input-field" placeholder="Contact Number" name="contact">
				<input type="password" class="input-field" placeholder="Password"
					name="password">

				<p class="privacy-note" style="margin: 10px;">By clicking on
					'Sign up' you agree to EcoCarpool's Terms of Service and
					acknowledge that you have read the Privacy Policy</p>
				<div class="submit-btn-box">
					<input type="submit" value="signup">
				</div>
			</form>


			<div class="have-already-account-container">
				Already have an account? <a href="login">login</a>
			</div>
			<h3 style="margin-top: 20px;">By Signing up with Careem you
				agree to our Terms & Conditions</h3>
			<p class="privacy-note">This sites is protected by reCAPTCHA and
				is subjecte to Google and Privacy Policy and Terms of Service</p>
		</div>
	</div>

</body>
</html>