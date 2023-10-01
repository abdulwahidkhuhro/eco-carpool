package com.connect.ecocarpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.connect.ecocarpool.entities.Booking;
import com.connect.ecocarpool.entities.Customer;

public class BookingDao {

	private Connection connection;

	public BookingDao(Connection connection) {
		this.connection = connection;
	}

	public boolean saveBooking(Booking booking) {

		boolean isSaved = false;

		try {
			String query = "insert into booking (pickup, dropoff, date, time, distance, username, driver, status, service) values(?,?,?,?,?,?,?,?,?);";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			preparedStatement.setString(1, booking.getPickup());
			preparedStatement.setString(2, booking.getDropOff());
			preparedStatement.setString(3, booking.getDate());
			preparedStatement.setString(4, booking.getTime());
			preparedStatement.setInt(5, booking.getDistance());
			preparedStatement.setString(6, booking.getUsername());
			preparedStatement.setString(7, booking.getDriver());
			preparedStatement.setInt(8, booking.getStatus());
			preparedStatement.setString(9, booking.getService());

			preparedStatement.executeUpdate();

			isSaved = true;

		} catch (Exception exception) {
			System.out.println("BookingDao -> saveBooking method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isSaved;

	}

	public boolean updateBookingStatus(Booking booking) {
		boolean isUpdated = false;

		try {
			String query = "update booking set driver=?, status=? where id=?;";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			preparedStatement.setString(1, booking.getDriver());
			preparedStatement.setInt(2, booking.getStatus());
			preparedStatement.setInt(3, booking.getId());

			preparedStatement.executeUpdate();

			isUpdated = true;

		} catch (Exception exception) {
			System.out.println("BookingDao -> updateBookingStatus method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isUpdated;
	}

	public boolean updateBookingStatus(int id, int status) {
		boolean isUpdated = false;

		try {
			String query = "update booking set status=? where id=?;";

			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			preparedStatement.setInt(1, status);
			preparedStatement.setInt(2, id);

			preparedStatement.executeUpdate();

			isUpdated = true;

		} catch (Exception exception) {
			System.out.println("BookingDao -> updateBookingStatus 2 method : " + exception.getMessage());
			exception.printStackTrace();
		}
		return isUpdated;
	}

	public ArrayList<Booking> getAllBookingByUsername(String username) {
		ArrayList<Booking> bookings = new ArrayList<>();
		try {
			String query = "select * from booking where username=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				bookings.add(new Booking(resultSet.getInt("id"), resultSet.getString("pickup"),
						resultSet.getString("dropoff"), resultSet.getString("date"), resultSet.getString("time"),
						resultSet.getInt("distance"), resultSet.getString("username"), resultSet.getString("driver"),
						resultSet.getInt("status"), resultSet.getString("service")));
			}
		} catch (Exception exception) {
			System.out.println("BookingDao -> getAllBooking : " + exception.getMessage());
			exception.printStackTrace();
		}

		return bookings;
	}
	
	public ArrayList<Booking> getAllBooking() {
		ArrayList<Booking> bookings = new ArrayList<>();
		try {
			String query = "select * from booking";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				bookings.add(new Booking(resultSet.getInt("id"), resultSet.getString("pickup"),
						resultSet.getString("dropoff"), resultSet.getString("date"), resultSet.getString("time"),
						resultSet.getInt("distance"), resultSet.getString("username"), resultSet.getString("driver"),
						resultSet.getInt("status"), resultSet.getString("service")));
			}
		} catch (Exception exception) {
			System.out.println("BookingDao -> getAllBooking : " + exception.getMessage());
			exception.printStackTrace();
		}

		return bookings;
	}

	public Booking getBookingByID(int id) {
		Booking booking = null;
		try {
			String query = "select * from booking where id=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				booking = new Booking(resultSet.getInt("id"), resultSet.getString("pickup"),
						resultSet.getString("dropoff"), resultSet.getString("date"), resultSet.getString("time"),
						resultSet.getInt("distance"), resultSet.getString("username"), resultSet.getString("driver"),
						resultSet.getInt("status"), resultSet.getString("service"));
			}
		} catch (Exception exception) {
			System.out.println("BookingDao -> getAllBooking : " + exception.getMessage());
			exception.printStackTrace();
		}

		return booking;
	}

	public ArrayList<Booking> getAllBookingByDriverUsername(String username) {
		ArrayList<Booking> bookings = new ArrayList<>();
		try {
			String query = "select * from booking where driver=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				bookings.add(new Booking(resultSet.getInt("id"), resultSet.getString("pickup"),
						resultSet.getString("dropoff"), resultSet.getString("date"), resultSet.getString("time"),
						resultSet.getInt("distance"), resultSet.getString("username"), resultSet.getString("driver"),
						resultSet.getInt("status"), resultSet.getString("service")));
			}
		} catch (Exception exception) {
			System.out.println("BookingDao -> getAllBooking : " + exception.getMessage());
			exception.printStackTrace();
		}

		return bookings;
	}

}
