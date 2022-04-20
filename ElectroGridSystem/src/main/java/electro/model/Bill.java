package electro.model;

public class Bill {

	int id,user_id,last_month_unit_read,current_unit_read,usage;
	String date;
	double total;
	
	public int getId() {
		return id;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getLast_month_unit_read() {
		return last_month_unit_read;
	}
	public int getCurrent_unit_read() {
		return current_unit_read;
	}
	public int getUsage() {
		return usage;
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
	public void setLast_month_unit_read(int last_month_unit_read) {
		this.last_month_unit_read = last_month_unit_read;
	}
	public void setCurrent_unit_read(int current_unit_read) {
		this.current_unit_read = current_unit_read;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
