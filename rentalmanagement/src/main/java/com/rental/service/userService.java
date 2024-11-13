package com.rental.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.rental.RentalmanagementApplication;
public class userService {

	RentalmanagementApplication rentalmanagementApplication = new RentalmanagementApplication();
	public void register1(String firstName, String lastName, String contact) {
		try {
			rentalmanagementApplication.makeConnection(); // Ensures the connection and stmt are set up
	
			// SQL insert query
			String newuser = "INSERT INTO users (firstName, lastName, contact) VALUES ('" 
						 + firstName + "', '" + lastName + "', '" + contact + "');";
	
			// Execute the insert query using the instance variable stmt
			stmt.executeUpdate(newuser);
			System.out.println("User registered successfully.");
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
	