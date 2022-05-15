package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class service
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_database", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertService(String code, String topic, String description, String date){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into service (`serviceCode`,`topic`,`decription`,`date`)"+" values (?, ?, ?, ?, )"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						 
						preparedStmt.setString(2, code); 
						preparedStmt.setString(3, topic); 
						preparedStmt.setString(4, description); 
						preparedStmt.setString(5, date); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newService = readService(); 
						output = "{\"status\":\"success\",\"data\":\""+newService+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the service.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readService() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		
		 output = "<table border=\"1\" class=\"table\"><tr><th>Service Code</th>"
		 		+ "<th>Topic</th>"
		 		+ "<th> Description</th>"
		 		+"<th> Date</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; 
		
		 String query = "select * from service"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String serviceCode = rs.getString("serviceCode"); 
		 String topic = rs.getString("topic"); 
		 String description = rs.getString("description");  
		 String date = rs.getString("date"); 
		
		 output += "<td>" + serviceCode + "</td>"; 
		 output += "<td>" + topic + "</td>"; 
		 output += "<td>" + description + "</td>"; 
		 output += "<td>" + date + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-serviceCode='" + serviceCode+ "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-serviceCode='" + serviceCode + "'></td></tr>"; 
		 
		 } 
		 con.close(); 


		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the service."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updateService( String code, String topic, String description, String date){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE service SET serviceCode=?,topic=?,description=?, date=? "; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, code); 
							preparedStmt.setString(2, topic); 
							preparedStmt.setString(3, description); 
							preparedStmt.setString(4, date); 
							 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newService = readService(); 
							output = "{\"status\":\"success\",\"data\":\""+newService+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the Service.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteService(String serviceCode){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from service where serviceCode=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(serviceCode)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newService = readService(); 
						 output = "{\"status\":\"success\",\"data\":\""+newService+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the service.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			}




			
			
			
			
} 

