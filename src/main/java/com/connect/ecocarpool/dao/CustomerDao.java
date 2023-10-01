package com.connect.ecocarpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.connect.ecocarpool.entities.Admin;
import com.connect.ecocarpool.entities.Customer;
import com.connect.ecocarpool.entities.Driver;
import com.connect.ecocarpool.entities.User;

public class CustomerDao {

	private Connection connection;
	
	public CustomerDao(Connection connection) {
		this.connection = connection;
	}
	
	public boolean isExists(String username) {
		boolean isExists = false;

		try {
			String query = "select * from user where username=?;";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isExists = true;
			}
		} catch (Exception exception) {
			System.out.println("Customer -> isExists method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isExists;
	}
	
	public boolean saveCustomer(Customer customer) {

		boolean isSaved = false;

		try {
			String query = "insert into user (username, fullname, contact, usertype, password) values(?,?,?,?,?);";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			preparedStatement.setString(1, customer.getUsername());
			preparedStatement.setString(2, customer.getFullName());
			preparedStatement.setString(3, customer.getContact());
			preparedStatement.setString(4, customer.getUserType());
			preparedStatement.setString(5, customer.getPassword());

			preparedStatement.executeUpdate();

			isSaved = true;

		} catch (Exception exception) {
			System.out.println("CustomerDao -> saveCustomer method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isSaved;

	}
	
	public boolean updateCustomer(Customer customer) {
		boolean isUpdated = false;

		try {
			String query = "update user set fullname=?, contact=?, usertype=?, password=? where username=?";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			
			preparedStatement.setString(1, customer.getFullName());
			preparedStatement.setString(2, customer.getContact());
			preparedStatement.setString(3, customer.getUserType());
			preparedStatement.setString(4, customer.getPassword());
			preparedStatement.setString(5, customer.getUsername());

			preparedStatement.executeUpdate();

			isUpdated = true;

		} catch (Exception exception) {
			System.out.println("Customer -> updateCustomer method : " + exception.getMessage());
			exception.printStackTrace();
		}

		return isUpdated;
	}
	
	public boolean deleteCustomer(String username) {
		boolean isUpdated = false;

		try {
			String query = "delete from user where username=?;";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			
			preparedStatement.setString(1, username);

			preparedStatement.executeUpdate();

			isUpdated = true;

		} catch (Exception exception) {
			System.out.println("CustomerDao -> deleteCustomer method : " + exception.getMessage());
			exception.printStackTrace();
		}

		return isUpdated;
	}
	
	public Customer getCustomerByUsername(String username) {
		Customer customer = null;

		try {
			String query = "select * from user where username=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				customer = new Customer(resultSet.getString("username"), resultSet.getString("fullName"), resultSet.getString("contact"), resultSet.getString("password"), resultSet.getString("userType"));
			}
		} catch (Exception exception) {
			System.out.println("Customer -> getCustomerByUserName : " + exception.getMessage());
			exception.printStackTrace();
		}

		return customer;
	}
	
	public ArrayList<Customer> getAllCustomer() {
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			String query = "select * from user where userType=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, "customer");
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				customers.add(new Customer(resultSet.getString("username"), resultSet.getString("fullName"), resultSet.getString("contact"), resultSet.getString("password"), resultSet.getString("userType")));
			}
		} catch (Exception exception) {
			System.out.println("DriverDao -> getAllDrivers : " + exception.getMessage());
			exception.printStackTrace();
		}

		return customers;
	}
}
