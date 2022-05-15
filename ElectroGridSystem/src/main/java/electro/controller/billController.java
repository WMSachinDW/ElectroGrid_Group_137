package electro.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import org.json.simple.*;

import electro.classes.*;
import electro.model.*;

import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/bill")
public class billController {

	BillService bill_Service =new BillService();
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String app_text)
	{
		return bill_Service.getBill();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String app_text)
	{
		
		//Convert the input string to a JSON object
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();

		if(app.get("user_id").getAsString()!=""&&app.get("m_unit").getAsString()!=""&&app.get("c_unit").getAsString()!=""&&app.get("usage").getAsString()!=""&&app.get("date").getAsString()!=""&&app.get("total").getAsString()!="") {

			Bill bill = new Bill();
			bill.setUser_id(Integer.parseInt(app.get("user_id").getAsString()));
			bill.setLast_month_unit_read(Integer.parseInt(app.get("m_unit").getAsString()));
			bill.setCurrent_unit_read(Integer.parseInt(app.get("c_unit").getAsString()));
			bill.setUsage(Integer.parseInt(app.get("usage").getAsString()));
			bill.setDate(app.get("date").getAsString());
			bill.setTotal(Double.parseDouble(app.get("total").getAsString()));
			
			bill_Service.addBill(bill);
			
			JSONObject json = new JSONObject();
			json.put("success", bill_Service.getSuccess());
			
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

		if(app.get("id").getAsString()!=""&&app.get("user_id").getAsString()!=""&&app.get("m_unit").getAsString()!=""&&app.get("c_unit").getAsString()!=""&&app.get("usage").getAsString()!=""&&app.get("date").getAsString()!=""&&app.get("total").getAsString()!="") {

			Bill bill = new Bill();
			bill.setId(Integer.parseInt(app.get("id").getAsString()));
			bill.setUser_id(Integer.parseInt(app.get("user_id").getAsString()));
			bill.setLast_month_unit_read(Integer.parseInt(app.get("m_unit").getAsString()));
			bill.setCurrent_unit_read(Integer.parseInt(app.get("c_unit").getAsString()));
			bill.setUsage(Integer.parseInt(app.get("usage").getAsString()));
			bill.setDate(app.get("date").getAsString());
			bill.setTotal(Double.parseDouble(app.get("total").getAsString()));
			
			bill_Service.editBill(bill);
			
			JSONObject json = new JSONObject();
			json.put("success", bill_Service.getSuccess());
			
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
	
			bill_Service.deleteBill(Integer.parseInt(app.get("id").getAsString()));
			
			JSONObject json = new JSONObject();
			json.put("success", bill_Service.getSuccess());
			
			return json.toString();

		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", "Required Error");
			
			return json.toString();
			
		}
		
	}
	
}
