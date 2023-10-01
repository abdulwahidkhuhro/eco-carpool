package com.connect.ecocarpool.entities;

public class Customer extends User {

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String username, String fullName, String contact, String password, String userType) {
		super(username, fullName, contact, password, userType);
		// TODO Auto-generated constructor stub
	}

	public Customer customer(String username, String fullName, String contact, String password, String userType) {
		return new Customer(username, fullName, contact, password, userType);
	}
}
