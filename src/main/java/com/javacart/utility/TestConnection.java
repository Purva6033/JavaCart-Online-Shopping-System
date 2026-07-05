package com.javacart.utility;
import java.sql.Connection;
public class TestConnection {
public static void main(String[] args) {
	

	Connection connection = DBConnection.getConnection();
	
	if(connection != null) {
		System.out.println("connection successfull");
	}else {
		System.out.println("connection failed");
	}

}
}