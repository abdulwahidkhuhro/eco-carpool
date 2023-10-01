package com.connect.ecocarpool.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.ecocarpool.dao.BookingDao;
import com.connect.ecocarpool.dao.CustomerDao;
import com.connect.ecocarpool.dao.DriverDao;
import com.connect.ecocarpool.dao.UserDao;
import com.connect.ecocarpool.entities.Booking;
import com.connect.ecocarpool.entities.Customer;
import com.connect.ecocarpool.entities.Driver;
import com.connect.ecocarpool.entities.Message;
import com.connect.ecocarpool.entities.User;
import com.connect.ecocarpool.helper.ConnectionProvider;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
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
			case "registerDrive": {
				System.out.println("Register Driver");
				request.getRequestDispatcher("./WEB-INF/jsp/view/registerDriver.jsp").forward(request, response);
				break;
			}

			case "registerCustomer": {
				System.out.println("Register Customer");
				request.getRequestDispatcher("./WEB-INF/jsp/view/registerCustomer.jsp").forward(request, response);
				break;
			}
			case "listDrivers": {
				DriverDao driverDao = new DriverDao(ConnectionProvider.getConnection());
				ArrayList<Driver> driversList = driverDao.getAllDrivers();
				session.setAttribute("driversList", driversList);
				request.getRequestDispatcher("./WEB-INF/jsp/view/allDriversList.jsp").forward(request, response);
				break;
			}
			case "listCustomer": {
				System.out.println("List Customer");
				CustomerDao customerDao = new CustomerDao(ConnectionProvider.getConnection());
				ArrayList<Customer> customersList = customerDao.getAllCustomer();
				session.setAttribute("customersList", customersList);
				request.getRequestDispatcher("./WEB-INF/jsp/view/allCustomerList.jsp").forward(request, response);
				break;
			}
			case "assignBooking": {

					BookingDao bookingDao = new BookingDao(ConnectionProvider.getConnection());
					
					ArrayList<Booking> bookings = bookingDao.getAllBooking();
					System.out.println("Assign booking : "+bookings.size());
					session.setAttribute("bookings", bookings);

					request.getRequestDispatcher("./WEB-INF/jsp/view/assignedBooking.jsp").forward(request, response);
				
				break;
			}
			case "generateCustomer": {
				System.out.println("Generate Customer");
				request.getRequestDispatcher("").forward(request, response);
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

		HttpSession session = request.getSession();

		String action = request.getParameter("action");

		switch (action) {
		case "registerDrive": {
			String username = request.getParameter("username");
			String name = request.getParameter("name");
			String drivingLicenseNumber = request.getParameter("drivingLicenseNumber");
			String contactNumber = request.getParameter("contactNumber");
			String password = request.getParameter("password");

			User currentUser = (User) session.getAttribute("currentUser");

			if (currentUser != null) {
				if (!username.isEmpty() && !name.isEmpty() && !drivingLicenseNumber.isEmpty()
						&& !contactNumber.isEmpty() && !password.isEmpty()) {
					UserDao userDao = new UserDao(ConnectionProvider.getConnection());
					if (!userDao.isUserExists(username)) {
						DriverDao driverDao = new DriverDao(ConnectionProvider.getConnection());

						Driver driver = new Driver(username, name, contactNumber, drivingLicenseNumber, password,
								"driver");

						driverDao.saveDriver(driver);

						Message message = new Message("Driver with username " + username + " registered successfully.",
								"Error", "success-message");

						session.setAttribute("message", message);
						request.getRequestDispatcher("./WEB-INF/jsp/view/registerDriver.jsp").forward(request,
								response);

					} else {
						Message message = new Message("Username " + username + " is already taken, try an other one.",
								"Error", "error-message");

						session.setAttribute("message", message);
						request.getRequestDispatcher("./WEB-INF/jsp/view/registerDriver.jsp").forward(request,
								response);
					}

				} else {
					Message message = new Message("All fields are required.", "Error", "error-message");

					session.setAttribute("message", message);
					request.getRequestDispatcher("./WEB-INF/jsp/view/registerDriver.jsp").forward(request, response);
				}
			} else {
				response.sendRedirect("login");
			}

			break;
		}
		case "registerCustomer": {
			String username = request.getParameter("username");
			String name = request.getParameter("name");
			String contactNumber = request.getParameter("contactNumber");
			String password = request.getParameter("password");

			User currentUser = (User) session.getAttribute("currentUser");

			if (currentUser != null) {
				if (!username.isEmpty() && !name.isEmpty() && !contactNumber.isEmpty() && !password.isEmpty()) {
					UserDao userDao = new UserDao(ConnectionProvider.getConnection());
					if (!userDao.isUserExists(username)) {

						CustomerDao customerDao = new CustomerDao(ConnectionProvider.getConnection());

						Customer customer = new Customer(username, name, contactNumber, password, "customer");

						customerDao.saveCustomer(customer);

						Message message = new Message(
								"Customer with username " + username + " registered successfully.", "Error",
								"success-message");

						session.setAttribute("message", message);
						request.getRequestDispatcher("./WEB-INF/jsp/view/registerCustomer.jsp").forward(request,
								response);

					} else {
						Message message = new Message("Username " + username + " is already taken, try an other one.",
								"Error", "error-message");

						session.setAttribute("message", message);
						request.getRequestDispatcher("./WEB-INF/jsp/view/registerCustomer.jsp").forward(request,
								response);
					}
				} else {
					Message message = new Message("All fields are required.", "Error", "error-message");

					session.setAttribute("message", message);
					request.getRequestDispatcher("./WEB-INF/jsp/view/registerCustomer.jsp").forward(request, response);

				}
			} else {
				response.sendRedirect("login");
			}
			break;
		}

		case "listDrivers": {
			User currentUser = (User) session.getAttribute("currentUser");

			if (currentUser != null) {

				DriverDao driverDao = new DriverDao(ConnectionProvider.getConnection());

				ArrayList<Driver> driversList = driverDao.getAllDrivers();

				session.setAttribute("driversList", driversList);

				request.getRequestDispatcher("./WEB-INF/jsp/view/allDriversList.jsp").forward(request, response);

			} else {
				response.sendRedirect("login");
			}
			break;
		}

		case "listCustomer": {
			User currentUser = (User) session.getAttribute("currentUser");

			if (currentUser != null) {

				CustomerDao customerDao = new CustomerDao(ConnectionProvider.getConnection());

				ArrayList<Customer> cutomersList = customerDao.getAllCustomer();

				session.setAttribute("cutomersList", cutomersList);

				request.getRequestDispatcher("./WEB-INF/jsp/view/allCustomerList.jsp").forward(request, response);
			} else {
				response.sendRedirect("login");
			}
		}
		
		}

	}

}
