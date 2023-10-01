package com.connect.ecocarpool.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class BookingServlet
 */
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingServlet() {
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

		if (currentUser != null) {
			switch (action) {
			case "assignDriver": {
				
				String id = request.getParameter("id");
				
				
				session.setAttribute("id", id);
				

				request.getRequestDispatcher("./WEB-INF/jsp/view/assignBooking.jsp").forward(request, response);
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
		
		if(currentUser != null) {
			String id = request.getParameter("id");
			String username = request.getParameter("driverName");
			
			DriverDao driverDao = new DriverDao(ConnectionProvider.getConnection());
			
			if(driverDao.isExists(username)) {
				
				BookingDao bookingDao = new BookingDao(ConnectionProvider.getConnection());
				
				Booking booking = bookingDao.getBookingByID(Integer.parseInt(id));
				System.out.println("Booking servlet driver name : "+username);
				booking.setDriver(username);
				booking.setStatus(1);
				bookingDao.updateBookingStatus(booking);
				
				response.sendRedirect("admin?action=assignBooking");
			}else {
				
				Message message = new Message("Driver "+username+" doesn't exists.", "Error", "error-message");

				
				session.setAttribute("message", message);
				request.getRequestDispatcher("./WEB-INF/jsp/view/assignBooking.jsp").forward(request, response);
				
			}
		}else {
			
		}
		

	}
	
}
