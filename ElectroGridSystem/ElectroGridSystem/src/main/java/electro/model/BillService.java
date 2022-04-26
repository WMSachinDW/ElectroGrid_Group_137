package electro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import electro.classes.*;
import electro.db.DatabaseConnection;

public class BillService {

	private String success;
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public void addBill(Bill bill) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DatabaseConnection.getDBConnection();
			
			// insert value
			preparedStatement = connection.prepareStatement("insert into bill (user_id,last_month_unit_read,current_unit_read,unit_usage,date,total) values (?,?,?,?,?,?)");
			preparedStatement.setInt(1, bill.getUser_id());
			preparedStatement.setInt(2, bill.getLast_month_unit_read());
			preparedStatement.setInt(3, bill.getCurrent_unit_read());
			preparedStatement.setInt(4, bill.getUsage());
			preparedStatement.setString(5, bill.getDate());
			preparedStatement.setDouble(6, bill.getTotal());
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
			setSuccess("Success");

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			setSuccess("Error");
		}
	}
	
	public String getBill() {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String table="";
		
		try {
			//view Bill
			connection = DatabaseConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM bill");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			table = "<table class='table'><thead>"
		            +"<tr>"
		            +"<th >ID</th>"
	                +"<th >User Id</th>"
	                +"<th >Last Month Unit Read</th>"
	                +"<th >Current Unit Read</th>"
	                +"<th >Unit Usage</th>"
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
						+ "<td >"+resultSet.getString(6)+"</td>"
						+ "<td >"+resultSet.getString(7)+"</td>"
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

	public void editBill(Bill bill) {
		Connection connection;
		PreparedStatement preparedStatement;

		try {
			connection = DatabaseConnection.getDBConnection();
			
				//update Bill
				preparedStatement = connection.prepareStatement("UPDATE bill SET user_id=?,last_month_unit_read=?,current_unit_read=?,unit_usage=?,date=?,total=? where id=?");
				preparedStatement.setInt(1, bill.getUser_id());
				preparedStatement.setInt(2, bill.getLast_month_unit_read());
				preparedStatement.setInt(3, bill.getCurrent_unit_read());
				preparedStatement.setInt(4, bill.getUsage());
				preparedStatement.setString(5, bill.getDate());
				preparedStatement.setDouble(6, bill.getTotal());
				preparedStatement.setInt(7,bill.getId());
				preparedStatement.execute();
				preparedStatement.close();
				connection.close();
				setSuccess("Success");
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setSuccess("Error");
		}
	}

	public void deleteBill(int bill) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DatabaseConnection.getDBConnection();
			
			//delete Bill
			preparedStatement = connection.prepareStatement("DELETE FROM bill WHERE id=?");
			preparedStatement.setInt(1, bill);
			preparedStatement.execute();
			setSuccess("Success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setSuccess("Error");
		}
	}
}
