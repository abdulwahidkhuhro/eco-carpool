package com.connect.ecocarpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.connect.ecocarpool.entities.Admin;
import com.connect.ecocarpool.entities.Driver;
import com.connect.ecocarpool.entities.User;

public class DriverDao {

	private Connection connection;
	
	public DriverDao(Connection connection) {
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
			System.out.println("DriverDao -> isUserExists method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isExists;
	}
	
	public boolean saveDriver(Driver user) {

		boolean isSaved = false;

		try {
			String query = "insert into user (username, fullname, contact, usertype, password, drivinglicense) values(?,?,?,?,?,?);";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getFullName());
			preparedStatement.setString(3, user.getContact());
			preparedStatement.setString(4, user.getUserType());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getDrivingLicense());

			preparedStatement.executeUpdate();

			isSaved = true;

		} catch (Exception exception) {
			System.out.println("DriverDao -> saveUser method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isSaved;

	}
	
	public boolean updateDriver(Driver user) {
		boolean isUpdated = false;

		try {
			String query = "update user set fullname=?, contact=?, password=?, drivinglicense=? where username=?";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			
			preparedStatement.setString(1, user.getFullName());
			preparedStatement.setString(2, user.getContact());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getDrivingLicense());
			preparedStatement.setString(5, user.getUsername());

			preparedStatement.executeUpdate();

			isUpdated = true;

		} catch (Exception exception) {
			System.out.println("DriverDao -> updateUser method : " + exception.getMessage());
			exception.printStackTrace();
		}

		return isUpdated;
	}
	
	public boolean deleteDriver(String username) {
		boolean isUpdated = false;

		try {
			String query = "delete from user where username=?;";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			
			preparedStatement.setString(1, username);

			preparedStatement.executeUpdate();

			isUpdated = true;

		} catch (Exception exception) {
			System.out.println("DriverDao -> deleteDriver method : " + exception.getMessage());
			exception.printStackTrace();
		}

		return isUpdated;
	}
	
	public Driver getDriverByUsername(String username) {
		Driver user = null;

		try {
			String query = "select * from user where username=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = new Driver(resultSet.getString("username"), resultSet.getString("fullName"), resultSet.getString("contact"), resultSet.getString("drivinglicense"), resultSet.getString("password"), resultSet.getString("userType"));
			}
		} catch (Exception exception) {
			System.out.println("DriverDao -> getDriverByUsername : " + exception.getMessage());
			exception.printStackTrace();
		}

		return user;
	}
	
	public ArrayList<Driver> getAllDrivers() {
		ArrayList<Driver> drivers = new ArrayList<>();
		try {
			String query = "select * from user where userType=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, "driver");
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				drivers.add(new Driver(resultSet.getString("username"), resultSet.getString("fullName"), resultSet.getString("contact"), resultSet.getString("drivinglicense"), resultSet.getString("password"), resultSet.getString("userType")));
			}
		} catch (Exception exception) {
			System.out.println("DriverDao -> getAllDrivers : " + exception.getMessage());
			exception.printStackTrace();
		}

		return drivers;
	}
}
