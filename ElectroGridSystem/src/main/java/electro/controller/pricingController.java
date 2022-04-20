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

@Path("/pricing")
public class pricingController {

	PricingService pricing_Service =new PricingService();
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String app_text)
	{
		return pricing_Service.getPricing();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String app_text)
	{
		
		//Convert the input string to a JSON object
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();
		
		Pricing pricing = new Pricing();
		pricing.setStart_unit(Integer.parseInt(app.get("start").getAsString()));
		pricing.setEnd_unit(Integer.parseInt(app.get("end").getAsString()));
		pricing.setPrice(Double.parseDouble(app.get("total").getAsString()));
		
		pricing_Service.addPricing(pricing);
		
		JSONObject json = new JSONObject();
		json.put("success", pricing_Service.getSuccess());
		
		return json.toString();
			
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(String app_text)
	{
		
		//Convert the input string to a JSON object
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();

		Pricing pricing = new Pricing();
		pricing.setId(Integer.parseInt(app.get("id").getAsString()));
		pricing.setStart_unit(Integer.parseInt(app.get("start").getAsString()));
		pricing.setEnd_unit(Integer.parseInt(app.get("end").getAsString()));
		pricing.setPrice(Double.parseDouble(app.get("total").getAsString()));
		
		pricing_Service.editPricing(pricing);
		
		JSONObject json = new JSONObject();
		json.put("success", pricing_Service.getSuccess());
		
		return json.toString();
			
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String app_text)
	{
		
		//Convert the input string to a JSON object
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();
		
		pricing_Service.deletePricing(Integer.parseInt(app.get("id").getAsString()));
		
		JSONObject json = new JSONObject();
		json.put("success", pricing_Service.getSuccess());
		
		return json.toString();
			
		
	}
	
}
