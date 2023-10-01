<%@page import="com.connect.ecocarpool.entities.Customer"%>
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
					<h1>All Customer</h1>
				</div>
			</div>
			<div class="admin-container">
				<table>
					<tr>
						<th>UserName</th>
						<th>Full Name</th>
						<th>Contact Number</th>
						<th>Password</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>

					<%
					ArrayList<Customer> customersList = (ArrayList<Customer>) session.getAttribute("customersList");
					for (int i = 0; i < customersList.size(); i++) {
						Customer customer = customersList.get(i);
						System.out.println("Customer usrname : " + customer.getUsername());
					%>
					<tr>
						<td style="width: 120px"><a><%=customer.getUsername()%></a></td>
						<td><%=customer.getFullName()%></td>
						<td><%=customer.getContact()%></td>
						<td style="width: 120px"><%=customer.getPassword()%></td>
						<td style="width: 90px"><a
							href="task?action=update&userType=customer&username=<%=customer.getUsername()%>"
							class="update-btn">Update</a></td>
						<td style="width: 90px"><a
							href="task?action=delete&userType=customer&username=<%=customer.getUsername()%>"
							class="delete-btn">Delete</a></td>

					</tr>
					<%
					System.out.println(customersList.get(i).getUsername());
					}
					%>

				</table>
			</div>
		</div>
	</div>
</body>
</html>