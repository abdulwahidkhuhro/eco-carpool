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
			<form action="login" method="POST">
				<div class="title-box">
					<h1>Login with</h1>
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
					name="username"> <input type="password" class="input-field"
					placeholder="Password" name="password">


				<div class="submit-btn-box">
					<input type="submit" value="log in">
				</div>
			</form>
			<div class="have-already-account-container">
				Don't have an account? <a href="login?action=signup">Sign up</a>
			</div>
			<p class="privacy-note">This sites is protected by reCAPTCHA and
				is subjecte to Google and Privacy Policy and Terms of Service</p>
		</div>
	</div>
</body>
</html>