package com.connect.ecocarpool.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.ecocarpool.dao.BookingDao;
import com.connect.ecocarpool.entities.Bill;
import com.connect.ecocarpool.entities.Booking;
import com.connect.ecocarpool.entities.Message;
import com.connect.ecocarpool.entities.User;
import com.connect.ecocarpool.helper.ConnectionProvider;

/**
 * Servlet implementation class BillServlet
 */
public class BillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BillServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		User currentUser = (User) session.getAttribute("currentUser");

		if (currentUser != null) {

			int id = Integer.parseInt(request.getParameter("id"));

			BookingDao bookingDao = new BookingDao(ConnectionProvider.getConnection());

			Booking booking = bookingDao.getBookingByID(id);

			Bill bill = new Bill(booking.getId(), booking.getDate(), booking.getPickup(), booking.getDropOff(),
					booking.getDistance(), booking.getService(), booking.getDriver());

			if (booking.getStatus() == 1) {
				Message message = new Message("Status# Not Paid.", "Error", "error-message");

				session.setAttribute("message", message);

				session.setAttribute("bill", bill);

				request.getRequestDispatcher("./WEB-INF/jsp/view/displayBill.jsp").forward(request, response);

			} else if (booking.getStatus() == 2) {

				Message message = new Message("Status# Paid.", "Error", "success-message");

				session.setAttribute("message", message);

				session.setAttribute("bill", bill);

				request.getRequestDispatcher("./WEB-INF/jsp/view/displayBill.jsp").forward(request, response);

			}

		}else {
			response.sendRedirect("login");
		}

//		(int id, String date, String pickup, String dropoff, int distance, String serviceType, int payment)

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
