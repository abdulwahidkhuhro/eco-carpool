package com.connect.ecocarpool.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

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
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		User currentUser = (User) session.getAttribute("currentUser");

		String action = request.getParameter("action");

		if (currentUser != null) {
			switch (action) {
			case "bookRide": {
				request.getRequestDispatcher("./WEB-INF/jsp/view/userHomePage.jsp").forward(request, response);
				break;
			}
			case "myRides": {
				BookingDao bookingDao = new BookingDao(ConnectionProvider.getConnection());

				ArrayList<Booking> bookings = bookingDao.getAllBookingByUsername(currentUser.getUsername());
				System.out.println("myRides in Customerservlet size : "+bookings.size());
				session.setAttribute("bookings", bookings);
				request.getRequestDispatcher("./WEB-INF/jsp/view/userHomePageTwo.jsp").forward(request, response);
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

		String pickup = request.getParameter("pickup");
		String dropOff = request.getParameter("dropoff");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String selected = request.getParameter("selected-car");

		User currentUser = (User) session.getAttribute("currentUser");

		if (currentUser != null) {
			if (!pickup.isEmpty() && !dropOff.isEmpty() && !date.isEmpty() && !date.isEmpty() && !time.isEmpty()
					&& !selected.equals("Selected")) {
				
				Random random = new Random();
				int randomNumber = random.nextInt(200);
				Booking booking = new Booking(pickup, dropOff, date, time, randomNumber, currentUser.getUsername(), "None", 0, selected);
				BookingDao bookingDao = new BookingDao(ConnectionProvider.getConnection());
				bookingDao.saveBooking(booking);

				Message message = new Message("Booking completes successfully.", "Error", "success-message");
				session.setAttribute("message", message);
				request.getRequestDispatcher("./WEB-INF/jsp/view/userHomePage.jsp").forward(request, response);

			} else {
				Message message = new Message("All fields are required.", "Error", "error-message");

				session.setAttribute("message", message);
				request.getRequestDispatcher("./WEB-INF/jsp/view/userHomePage.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect("login");
		}

	}
	

}
