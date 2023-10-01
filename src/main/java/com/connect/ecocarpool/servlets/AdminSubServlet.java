package com.connect.ecocarpool.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.ecocarpool.dao.CustomerDao;
import com.connect.ecocarpool.dao.DriverDao;
import com.connect.ecocarpool.entities.Customer;
import com.connect.ecocarpool.entities.Driver;
import com.connect.ecocarpool.entities.Message;
import com.connect.ecocarpool.entities.User;
import com.connect.ecocarpool.helper.ConnectionProvider;

/**
 * Servlet implementation class AdminSubServlet
 */
public class AdminSubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminSubServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		User currentUser = (User) session.getAttribute("currentUser");

		if (currentUser != null) {
			String action = request.getParameter("action");

			switch (action) {
			case "update": {
				String userType = request.getParameter("userType");

				switch (userType) {
				case "driver": {

					String username = request.getParameter("username");

					DriverDao driverDao = new DriverDao(ConnectionProvider.getConnection());

					Driver driver = driverDao.getDriverByUsername(username);

					session.setAttribute("userToUpdate", driver);

					request.getRequestDispatcher("./WEB-INF/jsp/view/updateDriver.jsp").forward(request, response);

					break;
				}
				case "customer": {

					String username = request.getParameter("username");
					System.out.println("customer in adminsub customer update : " + username);
					CustomerDao customerDao = new CustomerDao(ConnectionProvider.getConnection());

					Customer customer = customerDao.getCustomerByUsername(username);

					session.setAttribute("userToUpdate", customer);

					request.getRequestDispatcher("./WEB-INF/jsp/view/updateCustomer.jsp").forward(request, response);

					break;
				}
				}

				break;
			}
			case "delete": {

				String userType = request.getParameter("userType");
				switch (userType) {
				case "driver": {
					String username = request.getParameter("username");

					DriverDao driverDao = new DriverDao(ConnectionProvider.getConnection());

					driverDao.deleteDriver(username);

					request.getRequestDispatcher("admin?action=listDrivers").forward(request, response);
					break;

				}
				case "customer": {

					String username = request.getParameter("username");

					CustomerDao customerDao = new CustomerDao(ConnectionProvider.getConnection());

					customerDao.deleteCustomer(username);

					request.getRequestDispatcher("admin?action=listCustomer").forward(request, response);
					break;
				}
				}
				
				break;
			}

			}
		} else {

			response.sendRedirect("login");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		User currentUser = (User) session.getAttribute("currentUser");

		String action = request.getParameter("action");

		if (currentUser != null) {
			switch (action) {
			case "update": {
				String userType = request.getParameter("userType");

				switch (userType) {
				case "driver": {

					String username = request.getParameter("username");

					String name = request.getParameter("name");

					String drivingLicenseNumber = request.getParameter("drivingLicenseNumber");

					String contactNumber = request.getParameter("contactNumber");

					String password = request.getParameter("password");

					if (!username.isEmpty() && !name.isEmpty() && !drivingLicenseNumber.isEmpty()
							&& !contactNumber.isEmpty() && !password.isEmpty()) {
						Driver driver = new Driver(username, name, contactNumber, drivingLicenseNumber, password,
								"driver");

						DriverDao driverDao = new DriverDao(ConnectionProvider.getConnection());

						driverDao.updateDriver(driver);

						Message message = new Message("Driver updated successfully..", "Error", "success-message");

						session.setAttribute("message", message);

						request.getRequestDispatcher("./WEB-INF/jsp/view/updateDriver.jsp").forward(request, response);

					} else {
						Message message = new Message("All fields are required.", "Error", "error-message");

						session.setAttribute("message", message);

						request.getRequestDispatcher("./WEB-INF/jsp/view/updateDriver.jsp").forward(request, response);

					}

					break;
				}
				case "customer": {

					String username = request.getParameter("username");

					String name = request.getParameter("name");

					String contactNumber = request.getParameter("contactNumber");

					String password = request.getParameter("password");

					if (!username.isEmpty() && !name.isEmpty() && !contactNumber.isEmpty() && !password.isEmpty()) {
						Customer customer = new Customer(username, name, contactNumber, password, "customer");

						CustomerDao customerDao = new CustomerDao(ConnectionProvider.getConnection());

						customerDao.updateCustomer(customer);

						Message message = new Message("Customer updated successfully.", "Error", "success-message");

						session.setAttribute("message", message);

						request.getRequestDispatcher("./WEB-INF/jsp/view/updateCustomer.jsp").forward(request,
								response);
					} else {

						Message message = new Message("All fields are required.", "Error", "error-message");

						session.setAttribute("message", message);

						request.getRequestDispatcher("./WEB-INF/jsp/view/updateCustomer.jsp").forward(request,
								response);

					}

					break;
				}
				}

				break;
			}
			case "delete": {
				break;
			}

			case "jobs": {

			}

			}
		} else {
			response.sendRedirect("login");
		}

	}

}
