package com.connect.ecocarpool.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.ecocarpool.dao.BookingDao;
import com.connect.ecocarpool.dao.DriverDao;
import com.connect.ecocarpool.dao.UserDao;
import com.connect.ecocarpool.entities.Booking;
import com.connect.ecocarpool.entities.Driver;
import com.connect.ecocarpool.entities.Message;
import com.connect.ecocarpool.entities.User;
import com.connect.ecocarpool.helper.ConnectionProvider;

/**
 * Servlet implementation class DriverServlet
 */
public class DriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DriverServlet() {
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
		String action = request.getParameter("action");
		System.out.println("action DriverServlet : "+action);

		if (currentUser != null) {
			switch (action) {
			case "assignedJobs": {

				BookingDao bookingDao = new BookingDao(ConnectionProvider.getConnection());

				ArrayList<Booking> assignedJobs = bookingDao.getAllBookingByDriverUsername(currentUser.getUsername());
				
				System.out.println("Assigned Jobs to Driver : "+assignedJobs.size());
				session.setAttribute("jobs", assignedJobs);
				System.out.println("Driver Servlet : " + assignedJobs.size());
				
				request.getRequestDispatcher("./WEB-INF/jsp/view/assignedJobsToDriver.jsp").forward(request, response);
				break;
			}

			case "complete": {
				int id = Integer.parseInt(request.getParameter("id"));

				BookingDao bookingDao = new BookingDao(ConnectionProvider.getConnection());

				bookingDao.updateBookingStatus(id, 2);
				ArrayList<Booking> assignedJobs = bookingDao.getAllBookingByDriverUsername(currentUser.getUsername());

				session.setAttribute("jobs", assignedJobs);

				request.getRequestDispatcher("./WEB-INF/jsp/view/assignedJobsToDriver.jsp").forward(request, response);
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

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Title</title>");
			out.println("</head>");
			out.println("<body>");

			String username = request.getParameter("username");
			String name = request.getParameter("name");
			String drivingLicenseNumber = request.getParameter("drivingLicenseNumber");
			String contactNumber = request.getParameter("contactNumber");
			String password = request.getParameter("password");

			UserDao userDao = new UserDao(ConnectionProvider.getConnection());

			if (!username.isEmpty() && !name.isEmpty() && !drivingLicenseNumber.isEmpty() && !contactNumber.isEmpty()
					&& !password.isEmpty()) {

				if (password.length() >= 5) {
					if (!userDao.isUserExists(username)) {
						DriverDao driverDao = new DriverDao(ConnectionProvider.getConnection());
						driverDao.saveDriver(
								new Driver(username, name, contactNumber, drivingLicenseNumber, password, "driver"));

						Message message = new Message("Driver with username " + username + " registered.", "Error",
								"success-message");

						HttpSession session = request.getSession();
						session.setAttribute("message", message);
						response.sendRedirect("registerDriver.jsp");
					} else {
						Message message = new Message("Account with this email already exists.", "Error",
								"error-message");

						HttpSession session = request.getSession();
						session.setAttribute("message", message);
						response.sendRedirect("registerDriver.jsp");
					}
				} else {
					Message message = new Message("Password should be greater than or equal to 5!", "Error",
							"error-message");

					HttpSession session = request.getSession();
					session.setAttribute("message", message);
					response.sendRedirect("registerDriver.jsp");
				}
			} else {
				Message message = new Message("All fields are required.", "Error", "error-message");

				HttpSession session = request.getSession();
				session.setAttribute("message", message);
				response.sendRedirect("registerDriver.jsp");
			}

			out.println("</body>");
			out.println("</html>");

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
