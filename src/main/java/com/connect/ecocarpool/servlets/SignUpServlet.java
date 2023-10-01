package com.connect.ecocarpool.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connect.ecocarpool.dao.CustomerDao;
import com.connect.ecocarpool.dao.UserDao;
import com.connect.ecocarpool.entities.Customer;
import com.connect.ecocarpool.entities.Message;
import com.connect.ecocarpool.entities.User;
import com.connect.ecocarpool.helper.ConnectionProvider;

/**
 * Servlet implementation class SignupServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
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
			String fullname = request.getParameter("fullname");
			String contact = request.getParameter("contact");
			String password = request.getParameter("password");

			UserDao userDao = new UserDao(ConnectionProvider.getConnection());

			if (!username.isEmpty() && !fullname.isEmpty() && !contact.isEmpty() && !password.isEmpty()) {

				if (password.length() >= 5) {
					if (!userDao.isUserExists(username)) {

						CustomerDao customerDao = new CustomerDao(ConnectionProvider.getConnection());
						customerDao.saveCustomer(new Customer(username, fullname, contact, password, "customer"));
						response.sendRedirect("login.jsp");

					} else {
						Message message = new Message("Account with this email already exists.", "Error",
								"error-message");

						HttpSession session = request.getSession();
						session.setAttribute("message", message);
						response.sendRedirect("signup.jsp");
					}
				} else {
					Message message = new Message("Password should be greater than or equal to 5!", "Error",
							"error-message");

					HttpSession session = request.getSession();
					session.setAttribute("message", message);
					response.sendRedirect("signup.jsp");
				}

			} else {
				Message message = new Message("All fields are required.", "Error", "error-message");

				HttpSession session = request.getSession();
				session.setAttribute("message", message);
				response.sendRedirect("signup.jsp");
			}

			out.println("</body>");
			out.println("</html>");

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
