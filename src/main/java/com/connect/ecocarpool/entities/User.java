package com.connect.ecocarpool.entities;

public class User {

	private String username;
	private String fullName;
	private String contact;
	private String password;
	private String userType;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String fullName, String contact, String password, String userType) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.contact = contact;
		this.password = password;
		this.userType = userType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	@Override
	public String toString() {
		return "User [username=" + username + ", fullName=" + fullName + ", contact=" + contact + ", password="
				+ password + ", userType=" + userType + "]";
	}

	
}
