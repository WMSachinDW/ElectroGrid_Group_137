package electro.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;

import org.json.JSONException;
import org.json.simple.*;
import electro.model.*;
import electro.service.*;
import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/payment")
public class paymentController {

	PaymentService paymentService =new PaymentService();
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String app_text)
	{
		return paymentService.getPayment();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String app_text)
	{
		
		//Convert the input string to a JSON object
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();

		if(app.get("user_id").getAsString()!=""&&app.get("card").getAsString()!=""&&app.get("date").getAsString()!=""&&app.get("total").getAsString()!="") {
	
			Payment bill = new Payment();
			bill.setUser_id(Integer.parseInt(app.get("user_id").getAsString()));
			bill.setCard_number(app.get("card").getAsString());
			bill.setDate(app.get("date").getAsString());
			bill.setTotal(Double.parseDouble(app.get("total").getAsString()));
			
			paymentService.addPayment(bill);
			
			JSONObject json = new JSONObject();
			json.put("success", paymentService.getSuccess());
			return json.toString();

		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", "Required Error");
			return json.toString();
			
		}
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(String app_text)
	{
		
		//Convert the input string to a JSON object
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();

		if(app.get("id").getAsString()!=""&&app.get("user_id").getAsString()!=""&&app.get("card").getAsString()!=""&&app.get("date").getAsString()!=""&&app.get("total").getAsString()!="") {
		
			Payment bill = new Payment();
			bill.setId(Integer.parseInt(app.get("id").getAsString()));
			bill.setUser_id(Integer.parseInt(app.get("user_id").getAsString()));
			bill.setCard_number(app.get("card").getAsString());
			bill.setDate(app.get("date").getAsString());
			bill.setTotal(Double.parseDouble(app.get("total").getAsString()));
			
			paymentService.editPayment(bill);
			
			JSONObject json = new JSONObject();
			json.put("success", paymentService.getSuccess());
			return json.toString();

		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", "Required Error");
			return json.toString();
			
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String app_text)
	{
		
		//Convert the input string to a JSON object
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();

		if(app.get("id").getAsString()!="") {
				
			paymentService.deletePayment(Integer.parseInt(app.get("id").getAsString()));
			
			JSONObject json = new JSONObject();
			json.put("success", paymentService.getSuccess());
			return json.toString();

		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", "Required Error");
			return json.toString();
		}
	}
	
	@POST
	@Path("/get")
	@Produces(MediaType.TEXT_HTML)
	public String viewOne(String app_text) throws NumberFormatException, JSONException{
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();
		PaymentService paymentService =new PaymentService();
		return paymentService.getOnePayment(Integer.parseInt(app.get("id").getAsString())).toString();
	}	
}
