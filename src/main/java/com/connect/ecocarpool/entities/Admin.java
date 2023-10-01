package com.connect.ecocarpool.entities;

public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String fullName, String contact, String password, String userType) {
		super(username, fullName, contact, password, userType);
		// TODO Auto-generated constructor stub
	}
	
	public Admin admin(String username, String fullName, String contact, String password, String userType) {
		return new Admin(username, fullName, contact, password, userType);
	}

}
