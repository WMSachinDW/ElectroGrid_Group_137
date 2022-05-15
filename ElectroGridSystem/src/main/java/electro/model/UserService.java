package electro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import org.json.simple.JSONObject;

import electro.classes.*;
import electro.db.DatabaseConnection;

public class UserService {

	private String success;
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public void addUser(User user) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DatabaseConnection.getDBConnection();
			
			// insert value
			preparedStatement = connection.prepareStatement("insert into users (name,email,nic,phone,address,password,privilege) values (?,?,?,?,?,?,?)");
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getNic());
			preparedStatement.setString(4, user.getPhone());
			preparedStatement.setString(5, user.getAddress());
			preparedStatement.setString(6, Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
			preparedStatement.setString(7, user.getPrivilege());
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
			setSuccess("success");

		} catch (ClassNotFoundException | SQLException e) {
			setSuccess("unsuccess");
		}
	}
	
	public String getUser() {
		
		ArrayList<User> UserList = new ArrayList<User>();
		Connection connection;
		PreparedStatement preparedStatement;
		String table="";
		
		try {
			//view User
			connection = DatabaseConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM users");

			ResultSet resultSet = preparedStatement.executeQuery();
			
			table = "<table class='table'><thead>"
		            +"<tr>"
		            +"<th >ID</th>"
	                +"<th >Name</th>"
	                +"<th >Email</th>"
	                +"<th >Nic</th>"
	                +"<th >Phone</th>"
	                +"<th >Address</th>"
	                +"<th >Privilege</th>"
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
						+ "<td >"+resultSet.getString(8)+"</td>"
						+ "<td >"+button+"</td>"
					  + "</tr>";
				
			}
			
			preparedStatement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess("unsuccess");
		}
		
		return table+"</table>";
	}

	public void editUser(User user) {
		Connection connection;
		PreparedStatement preparedStatement;

		try {
			connection = DatabaseConnection.getDBConnection();
			
				//update User
				preparedStatement = connection.prepareStatement("UPDATE users SET name=?,email=?,nic=?,phone=?,address=?,password=?,privilege=? where id=?");
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setString(3, user.getNic());
				preparedStatement.setString(4, user.getPhone());
				preparedStatement.setString(5, user.getAddress());
				preparedStatement.setString(6, Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
				preparedStatement.setString(7, user.getPrivilege());
				preparedStatement.setInt(8,user.getId());
				preparedStatement.execute();
				preparedStatement.close();
				connection.close();
				setSuccess("success");
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess("unsuccess");
		}
	}

	public void deleteUser(int user) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DatabaseConnection.getDBConnection();
			
			//delete User
			preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
			preparedStatement.setInt(1, user);
			preparedStatement.execute();
			setSuccess("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess("unsuccess");
		}
	}
	
	public JSONObject getOneUser(int id) {
		Connection connection;
		PreparedStatement preparedStatement;
		JSONObject json = new JSONObject();
		
		try {
			connection = DatabaseConnection.getDBConnection();
			
			preparedStatement = connection.prepareStatement("SELECT * FROM users where id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				json.put("id", rs.getInt(1));
				json.put("name", rs.getString(2));
				json.put("email", rs.getString(3));
				json.put("nic", rs.getString(4));
				json.put("phone", rs.getString(5));
				json.put("address", rs.getString(6));
				json.put("privilege", rs.getString(8));
			}
			
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess("unsuccess");
		}
		return json;
	}
}
