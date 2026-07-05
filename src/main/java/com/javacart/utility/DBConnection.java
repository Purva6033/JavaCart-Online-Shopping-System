package com.javacart.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {

	private static final String URL="jdbc:mysql://localhost:3306/javacart_db";
	
	private static final String USERNAME = "root";
	
	private static final String PASSWORD = "purva1804";
	
	public static Connection getConnection() {
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("db connected successfully");
		}catch (ClassNotFoundException e) {
			System.out.println("sql driver not found");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("db connection failed");
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
}
