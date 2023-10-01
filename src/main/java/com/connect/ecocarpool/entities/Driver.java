package com.connect.ecocarpool.entities;

public class Driver extends User{

	private String drivingLicense;

	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Driver(String username, String fullName, String contact, String drivingLicense, String password, String userType) {
		super(username, fullName, contact, password, userType);
		// TODO Auto-generated constructor stub
		this.drivingLicense = drivingLicense;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	
	
	
	public Driver driver(String username, String fullName, String contact, String drivingLicense, String password, String userType) {
		return new Driver(username, fullName, contact, drivingLicense, password, userType);
	}
}
