package electro.classes;

public class Pricing {

	int id,start_unit,end_unit;
	double price;
	
	public int getId() {
		return id;
	}
	public int getStart_unit() {
		return start_unit;
	}
	public int getEnd_unit() {
		return end_unit;
	}
	public double getPrice() {
		return price;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStart_unit(int start_unit) {
		this.start_unit = start_unit;
	}
	public void setEnd_unit(int end_unit) {
		this.end_unit = end_unit;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
