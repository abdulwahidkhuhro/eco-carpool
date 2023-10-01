package com.connect.ecocarpool.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

	private static Connection connection;
	
	private ConnectionProvider() {
		
	}
	
	public static Connection getConnection() {
		
		try {
			if(connection == null) {
				
				Class.forName("com.mysql.jdbc.Driver");
				
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecocarpool","root","4244");
				
			}
		}catch(Exception exception) {
			System.out.println("ConncetionProvider Class : "+exception.getMessage());
			exception.printStackTrace();
		}
		
		return connection;
	}
	
	
}
