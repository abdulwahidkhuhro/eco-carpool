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
import com.connect.ecocarpool.dao.UserDao;
import com.connect.ecocarpool.entities.Admin;
import com.connect.ecocarpool.entities.Booking;
import com.connect.ecocarpool.entities.Customer;
import com.connect.ecocarpool.entities.Message;
import com.connect.ecocarpool.entities.User;
import com.connect.ecocarpool.helper.ConnectionProvider;

/**
 * Servlet implementation class LogInServlet
 */
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = (String) request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "signup": {
				request.getRequestDispatcher("./WEB-INF/jsp/view/signup.jsp").forward(request, response);
				break;
			}
			case "login": {
				request.getRequestDispatcher("./WEB-INF/jsp/view/login.jsp").forward(request, response);
				break;
			}
			default:
				break;
			}
		} else {
			request.getRequestDispatcher("./WEB-INF/jsp/view/login.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao(ConnectionProvider.getConnection());

		if (!username.isEmpty() && !password.isEmpty()) {
			if (userDao.isUserExists(username, password)) {
				
				User currentUser = userDao.getUserByUsername(username);
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", currentUser);
				
				response.sendRedirect("home");
			} else {
				Message message = new Message("User doesn't exists.", "Error", "error-message");

				HttpSession session = request.getSession();
				session.setAttribute("message", message);
				request.getRequestDispatcher("./WEB-INF/jsp/view/login.jsp").forward(request, response);
			}
		} else {
			Message message = new Message("All fields are required.", "Error", "error-message");

			HttpSession session = request.getSession();
			session.setAttribute("message", message);
			request.getRequestDispatcher("./WEB-INF/jsp/view/login.jsp").forward(request, response);
		}

	}

}
