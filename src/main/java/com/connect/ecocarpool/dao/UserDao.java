package com.connect.ecocarpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connect.ecocarpool.entities.User;

public class UserDao {

	private Connection connection;

	public UserDao(Connection connection) {
		this.connection = connection;
	}

	public String getUserType(String username) {
		try {
			String query = "select * from user where username=?;";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString("usertype");
			}
		} catch (Exception exception) {
			System.out.println("UserDao -> isUserExists method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return null;
	}
	public boolean isUserExists(String username, String password) {
		boolean isExists = false;

		try {
			String query = "select * from user where username=? and password=?;";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isExists = true;
			}
		} catch (Exception exception) {
			System.out.println("UserDao -> isUserExists method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isExists;
	}
	
	public boolean isUserExists(String username) {
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
			System.out.println("UserDao -> isUserExists method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isExists;
	}

	public boolean saveUser(User user) {

		boolean isSaved = false;

		try {
			String query = "insert into user (username, fullname, contact, usertype, password) values(?,?,?,?,?);";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getFullName());
			preparedStatement.setString(3, user.getContact());
			preparedStatement.setString(4, user.getUserType());
			preparedStatement.setString(5, user.getPassword());

			preparedStatement.executeUpdate();

			isSaved = true;

		} catch (Exception exception) {
			System.out.println("UserDao -> saveUser method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isSaved;

	}

	public boolean updateUser(User user) {
		boolean isUpdated = false;

		try {
			String query = "update user set username=?, fullname=?, contact=?, password=? where username=?";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, user.getFullName());
			preparedStatement.setString(2, user.getContact());
			preparedStatement.setString(3, user.getUserType());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getUsername());
			

			preparedStatement.executeUpdate();

			isUpdated = true;

		} catch (Exception exception) {
			System.out.println("UserDao -> updateUser method : " + exception.getMessage());
			exception.printStackTrace();
		}

		return isUpdated;
	}

	public User getUserByUsername(String username) {
		User user = null;

		try {
			String query = "select * from user where username=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = new User(resultSet.getString("username"), resultSet.getString("fullName"), resultSet.getString("contact"), resultSet.getString("password"), resultSet.getString("userType"));
			}
		} catch (Exception exception) {
			System.out.println("UserDao -> getUserByEmail : " + exception.getMessage());
			exception.printStackTrace();
		}

		return user;
	}
}
