package electro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import electro.db.DatabaseConnection;
import electro.model.*;

public class PaymentService {

	private String success;
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public void addPayment(Payment payment) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DatabaseConnection.getDBConnection();
			
			// insert value
			preparedStatement = connection.prepareStatement("insert into payment (user_id,card_number,date,total) values (?,?,?,?)");
			preparedStatement.setInt(1, payment.getUser_id());
			preparedStatement.setString(2, payment.getCard_number());
			preparedStatement.setString(3, payment.getDate());
			preparedStatement.setDouble(4, payment.getTotal());
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getPayment() {
		
		ArrayList<Payment> PaymentList = new ArrayList<Payment>();
		Connection connection;
		PreparedStatement preparedStatement;
		String table="";
		
		try {
			//view Payment
			connection = DatabaseConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM payment");

			ResultSet resultSet = preparedStatement.executeQuery();
			
			table = "<table class='table'><thead>"
		            +"<tr>"
		            +"<th >ID</th>"
	                +"<th >User Id</th>"
	                +"<th >Card Number</th>"
	                +"<th >Date</th>"
	                +"<th >Total</th>"
	                +"<th >Action</th>"
	                +"</tr>"
	            +"</thead><tbody>";
			
			while (resultSet.next()) {
				
				String button = "<button type='button' onclick='edit("+resultSet.getString(1)+")' class='btn btn-primary'>Edit</button><br><button type='button' onclick='deletes("+resultSet.getString(1)+")' class='btn btn-warning'>Delete</button>";
				
				table = table+"<tr><td >"+resultSet.getString(1)+"</td>"
						+ "<td >"+resultSet.getString(2)+"</td>"
						+ "<td >"+resultSet.getString(3)+"</td>"
						+ "<td >"+resultSet.getString(4)+"</td>"
						+ "<td >"+resultSet.getString(5)+"</td>"
						+ "<td >"+button+"</td>"
					  + "</tr>";
				
			}
			
			preparedStatement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return table+"</table>";
	}

	public void editPayment(Payment payment) {
		Connection connection;
		PreparedStatement preparedStatement;

		try {
			connection = DatabaseConnection.getDBConnection();
			
				//update Payment
				preparedStatement = connection.prepareStatement("UPDATE payment SET user_id=?,card_number=?,date=?,total=? where id=?");
				preparedStatement.setInt(1, payment.getUser_id());
				preparedStatement.setString(2, payment.getCard_number());
				preparedStatement.setString(3, payment.getDate());
				preparedStatement.setDouble(4, payment.getTotal());
				preparedStatement.setInt(5,payment.getId());
				preparedStatement.execute();
				preparedStatement.close();
				connection.close();
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
		}
	}

	public void deletePayment(int id) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DatabaseConnection.getDBConnection();
			
			//delete Payment
			preparedStatement = connection.prepareStatement("DELETE FROM payment WHERE id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
		}
	}
}
