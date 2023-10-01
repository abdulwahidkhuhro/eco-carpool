package com.connect.ecocarpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connect.ecocarpool.entities.Admin;
import com.connect.ecocarpool.entities.User;

public class AdminDao {

	private Connection connection;
	
	public AdminDao(Connection connection) {
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
			System.out.println("AdminDao -> isExists method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isExists;
	}

	public boolean saveAdmin(Admin user) {

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
			System.out.println("AdminDao -> saveAdmin method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isSaved;

	}

	public boolean updateAdmin(User user) {
		boolean isUpdated = false;

		try {
			String query = "update user set fullname=?, contact=?, usertype=?, password=? where username=?";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			
			preparedStatement.setString(1, user.getFullName());
			preparedStatement.setString(2, user.getContact());
			preparedStatement.setString(3, user.getUserType());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getUsername());

			preparedStatement.executeUpdate();

			isUpdated = true;

		} catch (Exception exception) {
			System.out.println("AdminDao -> updateAdmin method : " + exception.getMessage());
			exception.printStackTrace();
		}

		return isUpdated;
	}

	public Admin getAdminByUsername(String username) {
		Admin user = null;

		try {
			String query = "select * from user where username=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = new Admin(resultSet.getString("username"), resultSet.getString("fullname"), resultSet.getString("contact"), resultSet.getString("password"), resultSet.getString("usertype"));
			}
		} catch (Exception exception) {
			System.out.println("AdminDao -> getAdminByUserName : " + exception.getMessage());
			exception.printStackTrace();
		}

		return user;
	}
}
