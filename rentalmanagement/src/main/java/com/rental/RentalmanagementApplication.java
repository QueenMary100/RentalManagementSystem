package com.rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.mysql.cj.protocol.Resultset;

@SpringBootApplication
@ComponentScan("com.rental")
public class RentalmanagementApplication {

	private String url = "jdbc:mysql://localhost:3306/rental_management_system";
	private String user = "root";
	private String password = "root";
	private Connection con;
	private Statement stmt;
	//private Resultset rs;
	
	//method for creating connection to the database
	public void makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection( url, user, password);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			System.out.println("Connection made successfully");
			
		}
		catch(Exception exp) {
			System.out.println(exp);
		}
		
	}
	


	public String[][] showHouses(){
		makeConnection();
		String[][] houses = null;

		try { 
			ResultSet rs = stmt.executeQuery("SELECT * FROM houses"); 
			// Move to the last row to get the row count 
			rs.last(); 
			int rowCount = rs.getRow(); 
			rs.beforeFirst(); 
			// Move back to the beginning 
			houses = new String[rowCount][5]; 
			int index = 0; 
			while (rs.next()) { 
//				houses[index][0] = rs.getString("location"); 
//				houses[index][1] = String.valueOf(rs.getInt("vacancies")); 
//				houses[index][2] = rs.getString("contact"); 
//				houses[index][3] = rs.getString("type"); 
//				houses[index][4] = String.valueOf(rs.getDouble("price")); 
				houses[index][0] = rs.getString(2); 
				houses[index][1] = String.valueOf(rs.getInt(3)); 
				houses[index][2] = rs.getString(4); 
				houses[index][3] = rs.getString(5); 
				houses[index][4] = String.valueOf(rs.getDouble(6));
				index++; 
			}
		} catch (Exception exp) {
			exp.printStackTrace(); 
		}
		return houses;
	}
	//method for registering
	public void register1(String firstName, String lastName, String contact) {
		try {
			makeConnection(); // Ensures the connection and stmt are set up
	
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
	
	public String getVacancies() {
		makeConnection();
		ResultSet vaccount = null;

		try {
			
		   vaccount =  stmt.executeQuery("SELECT vacancies FROM houses WHERE location = 'Westlands' ");
			
			if(vaccount.next()){
				
				int vacancies = vaccount.getInt("vacancies");
				return String.valueOf(vacancies) ;
			}
			else {
				String message = "Sold Out!" ;
				return message;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Error retriving vacancies";
		}finally {
	        // Close the resources to avoid memory leaks
	        try {
	            if (vaccount != null) vaccount.close();
	            if (stmt != null) stmt.close();
	            if (con!= null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	//ArrayList mehod to return locations as a list
	public List<String> getLocation(){
		makeConnection();
		List<String>locations = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT location FROM houses");
			while (rs.next()) {
				locations.add(rs.getString("location"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
	        // Close the resources to avoid memory leaks
	        try {
	            if (stmt != null) stmt.close();
	            if (con!= null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
		return locations;
	}

	
	public static void main(String[] args) {
		SpringApplication.run(RentalmanagementApplication.class, args);
	}

}
