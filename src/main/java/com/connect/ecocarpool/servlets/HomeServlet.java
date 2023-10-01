package com.connect.ecocarpool.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.ecocarpool.dao.AdminDao;
import com.connect.ecocarpool.dao.BookingDao;
import com.connect.ecocarpool.dao.CustomerDao;
import com.connect.ecocarpool.dao.DriverDao;
import com.connect.ecocarpool.dao.UserDao;
import com.connect.ecocarpool.entities.Admin;
import com.connect.ecocarpool.entities.Booking;
import com.connect.ecocarpool.entities.Customer;
import com.connect.ecocarpool.entities.Driver;
import com.connect.ecocarpool.entities.Message;
import com.connect.ecocarpool.entities.User;
import com.connect.ecocarpool.helper.ConnectionProvider;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Home get request....");

		HttpSession session = request.getSession();

		User currentUser = (User) session.getAttribute("currentUser");

		if (currentUser != null) {

			switch (currentUser.getUserType()) {
			case "admin": {
				request.getRequestDispatcher("./WEB-INF/jsp/view/adminHomePage.jsp").forward(request, response);
				break;
			}
			case "customer": {
				BookingDao bookingDao  = new BookingDao(ConnectionProvider.getConnection());
				
				ArrayList<Booking> bookings = bookingDao.getAllBookingByUsername(currentUser.getUsername());
				
				session.setAttribute("bookings", bookings);
				
				request.getRequestDispatcher("./WEB-INF/jsp/view/userHomePage.jsp").forward(request, response);
				break;
			}
			case "driver": {
				request.getRequestDispatcher("./WEB-INF/jsp/view/driverHomePage.jsp").forward(request, response);
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
		System.out.println("Home post request....");
		HttpSession session = request.getSession();

		User currentUser = (User) session.getAttribute("currentUser");

		if (currentUser != null) {

		} else {

		}
	}

}
