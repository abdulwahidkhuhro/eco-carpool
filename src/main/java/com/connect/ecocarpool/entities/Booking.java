package com.connect.ecocarpool.entities;

public class Booking {

	private int id;
	private String pickup;
	private String dropOff;
	private String date;
	private String time;
	private int distance;
	private String username;
	private String driver;
	private int status;
	private String service;
	

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Booking(String pickup, String dropOff, String date, String time, int distance, String username,
			String driver, int status, String service) {
		super();
		this.pickup = pickup;
		this.dropOff = dropOff;
		this.date = date;
		this.time = time;
		this.distance = distance;
		this.username = username;
		this.driver = driver;
		this.status = status;
		this.service = service;
	}
	
	public Booking(int id, String pickup, String dropOff, String date, String time, int distance, String username,
			String driver, int status, String service) {
		super();
		this.id = id;
		this.pickup = pickup;
		this.dropOff = dropOff;
		this.date = date;
		this.time = time;
		this.distance = distance;
		this.username = username;
		this.driver = driver;
		this.status = status;
		this.service = service;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPickup() {
		return pickup;
	}


	public void setPickup(String pickup) {
		this.pickup = pickup;
	}


	public String getDropOff() {
		return dropOff;
	}


	public void setDropOff(String dropOff) {
		this.dropOff = dropOff;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	

	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


	@Override
	public String toString() {
		return "Booking [id=" + id + ", pickup=" + pickup + ", dropOff=" + dropOff + ", date=" + date + ", time=" + time
				+ ", distance=" + distance + ", username=" + username + ", driver=" + driver + ", status=" + status+", service="+service
				+ "]";
	}

	

}
