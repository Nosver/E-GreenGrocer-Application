package application.model;

public class Product {
	int id;
	String name;
	double stock;
	double price;
	double treshold;
	String imagePath;
	
	public Product(String name, double stock, double price) {
		this.name = name;
		this.stock = stock;
		this.price = price;
	}
	
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
