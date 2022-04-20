package electro.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import org.json.simple.*;

import electro.model.*;
import electro.service.*;

import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/user")
public class userController {

	UserService user_Service =new UserService();
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String app_text)
	{
		return user_Service.getUser();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String app_text)
	{
		
		//Convert the input string to a JSON object
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();
		
		User user = new User();
		user.setName(app.get("name").getAsString());
		user.setEmail(app.get("email").getAsString());
		user.setNic(app.get("nic").getAsString());
		user.setPhone(app.get("phone").getAsString());
		user.setAddress(app.get("address").getAsString());
		user.setPassword(app.get("pass").getAsString());
		user.setPrivilege(app.get("privilege").getAsString());
		
		user_Service.addUser(user);
		
		JSONObject json = new JSONObject();
		json.put("success", user_Service.getSuccess());
		
		return json.toString();
			
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(String app_text)
	{
		
		//Convert the input string to a JSON object
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();

		User user = new User();
		user.setId(Integer.parseInt(app.get("id").getAsString()));
		user.setName(app.get("name").getAsString());
		user.setEmail(app.get("email").getAsString());
		user.setNic(app.get("nic").getAsString());
		user.setPhone(app.get("phone").getAsString());
		user.setAddress(app.get("address").getAsString());
		user.setPassword(app.get("pass").getAsString());
		user.setPrivilege(app.get("privilege").getAsString());
		
		user_Service.editUser(user);
		
		JSONObject json = new JSONObject();
		json.put("success", user_Service.getSuccess());
		
		return json.toString();
			
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String app_text)
	{
		
		//Convert the input string to a JSON object
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();
		
		user_Service.deleteUser(Integer.parseInt(app.get("id").getAsString()));
		
		JSONObject json = new JSONObject();
		json.put("success", user_Service.getSuccess());
		
		return json.toString();
			
	}
	
}
