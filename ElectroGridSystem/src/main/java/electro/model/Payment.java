package electro.model;

public class Payment {

	int id,user_id;
	String card_number,date;
	double total;
	
	public int getId() {
		return id;
	}
	public int getUser_id() {
		return user_id;
	}
	public String getCard_number() {
		return card_number;
	}
	public String getDate() {
		return date;
	}
	public double getTotal() {
		return total;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
